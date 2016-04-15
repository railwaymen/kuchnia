package org.railwaymen.krakyournet;

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


	}

}
