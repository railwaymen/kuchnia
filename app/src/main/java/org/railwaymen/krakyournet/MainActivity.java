package org.railwaymen.krakyournet;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.railwaymen.krakyournet.beans.Kitchen;
import org.railwaymen.krakyournet.communication.Endpoints;
import org.railwaymen.krakyournet.communication.SyncPusher;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {
	@Bind(R.id.recycler_menu)
	RecyclerView recyclerView;
	@Bind(R.id.progress)
	ProgressBar progress;
	@Bind(R.id.progress_container)
	View progressContainer;
	@Bind(R.id.coordinatorlayout)
	CoordinatorLayout coordinatorLayout;
	@Bind(R.id.name)
	AutoCompleteTextView kitchenName;
	@Bind(R.id.toolbar)
	Toolbar toolbar;
	@Bind(R.id.logout)
	Button buttonLogout;

	private static Endpoints endpoints;
	private Kitchen kitchen;
	private SyncPusher syncPusher;
	private RecyclerView.Adapter adapter;
	private RecyclerView.LayoutManager layoutManager;

	private Snackbar snackbar;
	private boolean clicked = false;
	private ExecutorService executor;
	public static Gson gson = new Gson();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);
		setSupportActionBar(toolbar);
		executor = Executors.newSingleThreadExecutor();
		progress.getIndeterminateDrawable().setColorFilter(
				getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);

	}

	public Endpoints getEndpoints() {
		if (endpoints == null) {
			endpoints = Utils.buildRetrofit();
		}
		return endpoints;
	}

	public ExecutorService getExecutor() {
		return executor;
	}

	public Kitchen getKitchen() {
		return kitchen;
	}

	public void showSnackbar(int textId) {
		showSnackbar(getString(textId));
	}

	public void showSnackbar(String text) {
		if (snackbar != null && snackbar.isShown()) {
			snackbar.dismiss();
		}
		boolean isLong = text.length() > 60;
		snackbar = Snackbar.make(coordinatorLayout, text,
				isLong ? Snackbar.LENGTH_LONG : Snackbar.LENGTH_SHORT);
		snackbar.getView()
				.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
		((TextView) snackbar.getView().findViewById(android.support.design.R.id.snackbar_text))
				.setTextColor(getResources().getColor(android.R.color.white));
		snackbar.show();
	}

	@OnClick(R.id.progress_container)
	public void registerLocation() {
		if (!clicked) {
			if (Utils.isDeviceConnected(this)) {
				String name = kitchenName.getEditableText().toString();
				if (Utils.isTextValid(name)) {
					clicked = true;
					progress.setVisibility(View.VISIBLE);
					try {
						Map<String, Object> map = new HashMap<>();
						map.put(Constants.Keys.NAME, name);
						map.put(Constants.Keys.DEVICE_ID, Utils.getDeviceId(this));
						Call<Kitchen> call = getEndpoints().register(map);
						call.enqueue(callback);
					} catch (Exception e) {
						Log.d(MainActivity.class.getSimpleName(), e.toString());
						showSnackbar(getString(R.string.error));
						progress.setVisibility(View.GONE);
						clicked = false;
					}
				} else {
					showSnackbar(R.string.error_empty);
				}
			} else {
				showSnackbar(R.string.error_disconnected);
			}
		}
	}

	@OnClick(R.id.logout)
	public void logout() {
		if (kitchen != null) {
			Call<Void> call = getEndpoints().removeKitchen(kitchen.getId());
			call.enqueue(removeCallback);
		}
	}

	public RecyclerView.Adapter getAdapter() {
		return adapter;
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (syncPusher != null) {
			syncPusher.disconnect();
		}
	}

	private void buildMenu() {
		progress.setVisibility(View.INVISIBLE);
		progressContainer.setVisibility(View.GONE);
		recyclerView.setVisibility(View.VISIBLE);
		recyclerView.setHasFixedSize(true);
		recyclerView.addItemDecoration(new SpaceItemDecorator(10));
		layoutManager = new GridLayoutManager(this, 2);
		recyclerView.setLayoutManager(layoutManager);
		adapter = new MenuRecyclerAdapter(this);
		recyclerView.setAdapter(adapter);

	}

	private Callback<Kitchen> callback = new Callback<Kitchen>() {
		@Override
		public void onResponse(Call<Kitchen> call, Response<Kitchen> response) {
			if (response != null) {
				switch (response.code()) {
				case 200:
					buttonLogout.setEnabled(true);
					kitchen = response.body();
					if (kitchen != null) {
						getSupportActionBar().setTitle(kitchen.getName());
						progress.setVisibility(View.INVISIBLE);
						buildMenu();
						syncPusher = new SyncPusher(MainActivity.this, kitchen.getId().toString());
					}
					break;
				case 403:
					progress.setVisibility(View.GONE);
					showSnackbar(R.string.kitchen_limit);
					break;
				default:
					break;
				}
			}
			clicked = false;
		}

		@Override
		public void onFailure(Call<Kitchen> call, Throwable t) {
			clicked = false;
			progress.setVisibility(View.INVISIBLE);
		}
	};
	private Callback<Void> removeCallback = new Callback<Void>() {
		@Override
		public void onResponse(Call<Void> call, Response<Void> response) {
			if (response != null) {
				switch (response.code()) {
				case 200:
					if (syncPusher != null) {
						syncPusher.disconnect();
					}
					buttonLogout.setEnabled(false);
					progressContainer.setVisibility(View.VISIBLE);
					recyclerView.setVisibility(View.GONE);
					kitchen = null;
					break;
				}
			}
		}

		@Override
		public void onFailure(Call<Void> call, Throwable t) {
			Log.e(MainActivity.class.getSimpleName(), t.toString());
		}
	};
}
