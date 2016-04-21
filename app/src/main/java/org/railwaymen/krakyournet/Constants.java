package org.railwaymen.krakyournet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tomek on 01.04.16.
 */
public class Constants {

	public static final String API_URL = "http://kuchnia-api-railwaymen.herokuapp.com/api/";

	public abstract class Pusher {
		public static final String APP_KEY = "be378fc6b685348ae04c";
	}

	public abstract class Keys {
		public static final String NAME = "name";
		public static final String ID = "id";
		public static final String DATE = "date";
		public static final String DEVICE_ID = "device_id";
		public static final String WAITER = "waiter";
		public static final String MENU_ITEM = "menu_item";

	}

	public static final List<String> PUSHER_EVENT_LIST = new ArrayList<>();

	public abstract class Event {
		public static final String CONNECTED = "event_connected";
		public static final String DISCONNECTED = "event_disconnected";
		public static final String TAKED = "event_take";
	}

	static {
		PUSHER_EVENT_LIST.add(Event.CONNECTED);
		PUSHER_EVENT_LIST.add(Event.DISCONNECTED);
		PUSHER_EVENT_LIST.add(Event.TAKED);
	}
}
