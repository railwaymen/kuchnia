package org.railwaymen.krakyournet;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.railwaymen.krakyournet.model.Kitchen;
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

	}

	@OnClick(R.id.logout)
	public void logout() {

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


}
