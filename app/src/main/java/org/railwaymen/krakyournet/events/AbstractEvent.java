package org.railwaymen.krakyournet.events;

import org.railwaymen.krakyournet.MainActivity;

/**
 * Created by tomek on 03.04.16.
 */
public abstract class AbstractEvent {
	protected MainActivity mainActivity;

	public AbstractEvent(String data, MainActivity mainActivity) {
		this.mainActivity = mainActivity;
		parseData(data);
	}

	public abstract void handleEvent();

	abstract void parseData(String data);
}
