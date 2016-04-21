package org.railwaymen.krakyournet.events;

import org.json.JSONException;
import org.json.JSONObject;
import org.railwaymen.krakyournet.Constants;
import org.railwaymen.krakyournet.MainActivity;
import org.railwaymen.krakyournet.R;
import org.railwaymen.krakyournet.Utils;
import org.railwaymen.krakyournet.beans.MenuItem;
import org.railwaymen.krakyournet.beans.Waiter;

import android.util.Log;

/**
 * Created by tomek on 03.04.16.
 */
public class TakedEvent extends AbstractEvent {
	private MenuItem menuItem;
	private Waiter waiter;

	public TakedEvent(String data, MainActivity mainActivity) {
		super(data, mainActivity);
	}

	@Override
	public void handleEvent() {
		if (menuItem.getId() != null) {
			int position = Utils.findMenuItemPosition(mainActivity.getKitchen().getMenu(),
					menuItem.getId());
			MenuItem menuItem = mainActivity.getKitchen().getMenu().get(position);
			menuItem.setSelected(false);
			menuItem.setTakeDate(null);
			mainActivity.getAdapter().notifyItemChanged(position);
			mainActivity.showSnackbar(String.format("%s %s %s", menuItem.getName(),
					mainActivity.getString(R.string.waiter_take), waiter.getName()));
		}
	}

	@Override
	protected void parseData(String data) {
		JSONObject jsonObject = null;
		try {
			jsonObject = new JSONObject(data);
			if (jsonObject != null) {
				menuItem = mainActivity.gson.fromJson(
						jsonObject.get(Constants.Keys.MENU_ITEM).toString(), MenuItem.class);
				waiter = mainActivity.gson
						.fromJson(jsonObject.get(Constants.Keys.WAITER).toString(), Waiter.class);
			}
		} catch (JSONException e) {
			Log.e(TakedEvent.class.getSimpleName(), e.toString());
		}

	}
}
