package org.railwaymen.krakyournet;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by tomek on 01.04.16.
 */
public class MenuRecyclerAdapter extends RecyclerView.Adapter<MenuRecyclerAdapter.MenuHolder> {

	private MainActivity hostActivity;
	private static final DateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("HH:mm");

	public MenuRecyclerAdapter(MainActivity hostActivity) {
		this.hostActivity = hostActivity;
	}

	@Override
	public MenuHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(hostActivity).inflate(R.layout.recycler_menu_item, parent,
				false);
		MenuHolder holder = new MenuHolder(view);
		view.setOnClickListener(new OnMenuClickListener(holder, hostActivity));
		return holder;
	}

	@Override
	public void onBindViewHolder(MenuHolder holder, int position) {


	}

	@Override
	public int getItemCount() {
		return 0;
	}

	public class MenuHolder extends RecyclerView.ViewHolder {
		@Bind(R.id.text)
		TextView text;
		@Bind(R.id.date)
		TextView dateText;
		@Bind(R.id.card_view)
		CardView cardView;

		public MenuHolder(View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);
		}
	}
}
