package org.railwaymen.krakyournet;

import java.util.List;
import java.util.UUID;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

import org.railwaymen.krakyournet.model.MenuItem;
import org.railwaymen.krakyournet.communication.Endpoints;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.text.TextUtils;

/**
 * Created by tomek on 03.04.16.
 */
public class Utils {
	public static int findMenuItemPosition(List<MenuItem> menuItemsList, UUID id) {
		for (int i = 0; i < menuItemsList.size(); i++) {
			if (menuItemsList.get(i).equals(id)) {
				return i;
			}
		}
		return -1;
	}

	public static boolean isTextValid(String text) {
		if (!TextUtils.isEmpty(text)) {
			return true;
		}
		return false;
	}

	public static boolean isDeviceConnected(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		NetworkInfo mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		if (wifi != null && wifi.isConnected()) {
			return true;
		} else if (mobile != null && mobile.isConnected()) {
			return true;
		}
		return false;
	}

	public static String getDeviceId(Context context) {
		return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
	}

	public static Endpoints buildRetrofit() {
		OkHttpClient okHttpClient = new OkHttpClient.Builder()
				.addInterceptor(
						new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
				.build();
		Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.API_URL).client(okHttpClient)
				.addConverterFactory(GsonConverterFactory.create()).build();
		return retrofit.create(Endpoints.class);
	}
}
