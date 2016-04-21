package org.railwaymen.krakyournet.communication;

import org.railwaymen.krakyournet.Constants;
import org.railwaymen.krakyournet.MainActivity;
import org.railwaymen.krakyournet.events.AbstractEvent;
import org.railwaymen.krakyournet.events.ConnectedEvent;
import org.railwaymen.krakyournet.events.DisconnectedEvent;
import org.railwaymen.krakyournet.events.TakedEvent;

/**
 * Created by tomek on 03.04.16.
 */
public class EventFactory {

	private EventFactory() {
	};

	public static AbstractEvent getEvent(String eventName, String data, MainActivity mainActivity) {
		if (eventName.equals(Constants.Event.CONNECTED)) {
			return new ConnectedEvent(data, mainActivity);
		} else if (eventName.equals(Constants.Event.DISCONNECTED)) {
			return new DisconnectedEvent(data, mainActivity);
		} else if (eventName.equals(Constants.Event.TAKED)) {
			return new TakedEvent(data, mainActivity);
		}
		return null;
	}
}