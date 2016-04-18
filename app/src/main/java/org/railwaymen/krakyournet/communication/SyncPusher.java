package org.railwaymen.krakyournet.communication;

import org.railwaymen.krakyournet.Constants;
import org.railwaymen.krakyournet.MainActivity;
import org.railwaymen.krakyournet.events.AbstractEvent;

import android.util.Log;

import com.pusher.client.Pusher;
import com.pusher.client.PusherOptions;
import com.pusher.client.channel.Channel;
import com.pusher.client.channel.SubscriptionEventListener;
import com.pusher.client.connection.ConnectionEventListener;
import com.pusher.client.connection.ConnectionState;
import com.pusher.client.connection.ConnectionStateChange;

/**
 * Created by tomek on 01.04.16.
 */
public class SyncPusher {
	private Pusher pusher;
	private Channel channel;
	private MainActivity mainActivity;
	private String channelName;

	public SyncPusher(MainActivity mainActivity, String channelName) {
		this.mainActivity = mainActivity;
		this.channelName = channelName;
		connectPusher();
	}

	public void disconnect() {
		pusher.disconnect();
	}

	private void connectPusher() {
		pusher = new Pusher(Constants.Pusher.APP_KEY, new PusherOptions().setCluster("eu"));
		pusher.connect(connectionEventListener, ConnectionState.ALL);
		subscribeChannel(channelName);
	}

	private void subscribeChannel(String channelName) {
		channel = pusher.subscribe(channelName);
		for (String eventName : Constants.PUSHER_EVENT_LIST) {
			channel.bind(eventName, subscriptionEventListener);
		}
	}

	private ConnectionEventListener connectionEventListener = new ConnectionEventListener() {
		@Override
		public void onConnectionStateChange(ConnectionStateChange change) {
			Log.e(SyncPusher.class.getSimpleName(), change.getCurrentState().toString());
		}

		@Override
		public void onError(String message, String code, Exception e) {
			Log.e(SyncPusher.class.getSimpleName(), message);
		}
	};
	private SubscriptionEventListener subscriptionEventListener = new SubscriptionEventListener() {
		@Override
		public void onEvent(final String channelName, final String eventName, final String data) {
			Runnable eventRunnable = new Runnable() {
				@Override
				public void run() {
					AbstractEvent event = EventFactory.getEvent(eventName, data, mainActivity);
					if (event != null) {
						event.handleEvent();
					}
				}
			};
			mainActivity.getExecutor().submit(eventRunnable);
		}
	};
}
