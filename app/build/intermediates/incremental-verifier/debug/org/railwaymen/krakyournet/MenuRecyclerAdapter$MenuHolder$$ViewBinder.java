// Generated code from Butter Knife. Do not modify!
package org.railwaymen.krakyournet;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MenuRecyclerAdapter$MenuHolder$$ViewBinder<T extends org.railwaymen.krakyournet.MenuRecyclerAdapter.MenuHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492996, "field 'text'");
    target.text = finder.castView(view, 2131492996, "field 'text'");
    view = finder.findRequiredView(source, 2131493000, "field 'dateText'");
    target.dateText = finder.castView(view, 2131493000, "field 'dateText'");
    view = finder.findRequiredView(source, 2131492999, "field 'cardView'");
    target.cardView = finder.castView(view, 2131492999, "field 'cardView'");
  }

  @Override public void unbind(T target) {
    target.text = null;
    target.dateText = null;
    target.cardView = null;
  }
}
