package org.railwaymen.krakyournet;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by tomek on 21.09.15.
 */
public class SpaceItemDecorator extends RecyclerView.ItemDecoration {
	private int space;

	public SpaceItemDecorator(int spaceInDp) {
		this.space = spaceInDp;
	}

	@Override
	public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
			RecyclerView.State state) {
		outRect.bottom = space;
		if (parent.getChildAdapterPosition(view) == 0)
			outRect.top = space;
	}
}
