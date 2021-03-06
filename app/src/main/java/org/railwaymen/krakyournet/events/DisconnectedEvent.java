package org.railwaymen.krakyournet.events;

import org.railwaymen.krakyournet.MainActivity;
import org.railwaymen.krakyournet.R;
import org.railwaymen.krakyournet.beans.Waiter;

/**
 * Created by tomek on 03.04.16.
 */
public class DisconnectedEvent extends AbstractEvent {
	private Waiter waiter;

	public DisconnectedEvent(String data, MainActivity mainActivity) {
		super(data, mainActivity);
	}

	@Override
	public void handleEvent() {
		mainActivity.showSnackbar(waiter == null ? "Kelner"
				: waiter.getName() + " " + mainActivity.getString(R.string.waiter_disconnected));
	}

	@Override
	protected void parseData(String data) {
		if (data != null) {
			waiter = MainActivity.gson.fromJson(data, Waiter.class);
		}
	}
}
