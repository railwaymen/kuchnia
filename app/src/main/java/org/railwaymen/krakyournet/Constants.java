package org.railwaymen.krakyournet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tomek on 01.04.16.
 */
public class Constants {
	// testing on localhost
	public static final String HOST = "kuchnia-api-railwaymen.herokuapp.com";
	public static final String API_URL = String.format("http://%s/api/", HOST);

	public abstract class Pusher {
		public static final String APP_ID = "195791";
		public static final String APP_KEY = "be378fc6b685348ae04c";
		public static final String SECRET = "6d6fb334363868e5bbcd";
	}

	public abstract class Keys {
		public static final String NAME = "name";
		public static final String ID = "id";
		public static final String DATE = "date";
		public static final String DEVICE_ID = "device_id";
		public static final String WAITER = "waiter";
		public static final String MENU_ITEM = "menu_item";

	}

	public static final List<String> WISHLIST_EVENT_LIST = new ArrayList<>();

	public abstract class Event {
		public static final String CONNECTED = "event_connected";
		public static final String DISCONNECTED = "event_disconnected";
		public static final String TAKED = "event_take";
	}

	static {
		WISHLIST_EVENT_LIST.add(Event.CONNECTED);
		WISHLIST_EVENT_LIST.add(Event.DISCONNECTED);
		WISHLIST_EVENT_LIST.add(Event.TAKED);
	}
}
