package org.railwaymen.krakyournet.communication;

import java.util.Map;
import java.util.UUID;

import org.railwaymen.krakyournet.model.Kitchen;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by tomek on 01.04.16.
 */
public interface Endpoints {

	// CREATE LOCATION AND GET PUSHER CHANNEL
	@POST("kitchens")
	Call<Kitchen> register(@Body Map<String, Object> map);

	// DELETE KITCHEN
	@DELETE("kitchens/{kitchen_id}")
	Call<Void> removeKitchen(@Path("kitchen_id") UUID kitchenId);

	// COMPLETE MEAL
	@POST("kitchens/{kitchen_id}/menu_items/{menu_item_id}")
	Call<Void> completeMeal(@Path("kitchen_id") UUID kitchenId,
			@Path("menu_item_id") UUID menuItemId, @Body Map<String, Object> map);
}
