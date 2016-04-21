package org.railwaymen.krakyournet;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.railwaymen.krakyournet.beans.MenuItem;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.View;

/**
 * Created by tomek on 01.04.16.
 */
public class OnMenuClickListener implements View.OnClickListener {
	private MenuRecyclerAdapter.MenuHolder holder;
	private MainActivity hostActivity;

	public OnMenuClickListener(MenuRecyclerAdapter.MenuHolder holder, MainActivity hostActivity) {
		this.holder = holder;
		this.hostActivity = hostActivity;
	}

	@Override
	public void onClick(View view) {
		if (Utils.isDeviceConnected(hostActivity)) {
			int position = holder.getAdapterPosition();
			MenuItem menuElement = hostActivity.getKitchen().getMenu().get(position);
			Date date = new Date();
			menuElement.setSelected(true);
			menuElement.setTakeDate(date);
			hostActivity.getAdapter().notifyItemChanged(position);
			Map<String, Object> map = new HashMap<>();
			map.put(Constants.Keys.ID, menuElement.getId());
			map.put(Constants.Keys.NAME, menuElement.getName());
			map.put(Constants.Keys.DATE, date);

			Call<Void> call = hostActivity.getEndpoints()
					.completeMeal(hostActivity.getKitchen().getId(), menuElement.getId(), map);
			call.enqueue(callback);

		} else {
			hostActivity.showSnackbar(R.string.error_disconnected);
		}

	}

	private Callback<Void> callback = new Callback<Void>() {
		@Override
		public void onResponse(Call<Void> call, Response<Void> response) {
			switch (response.code()) {
			case 423:
				hostActivity.showSnackbar(R.string.error_already_addded);
				break;
			}
		}

		@Override
		public void onFailure(Call<Void> call, Throwable t) {

		}
	};
}
