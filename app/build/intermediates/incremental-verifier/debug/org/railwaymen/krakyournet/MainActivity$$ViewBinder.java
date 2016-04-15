// Generated code from Butter Knife. Do not modify!
package org.railwaymen.krakyournet;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MainActivity$$ViewBinder<T extends org.railwaymen.krakyournet.MainActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492976, "field 'recyclerView'");
    target.recyclerView = finder.castView(view, 2131492976, "field 'recyclerView'");
    view = finder.findRequiredView(source, 2131492975, "field 'progress'");
    target.progress = finder.castView(view, 2131492975, "field 'progress'");
    view = finder.findRequiredView(source, 2131492972, "field 'progressContainer' and method 'registerLocation'");
    target.progressContainer = view;
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.registerLocation();
        }
      });
    view = finder.findRequiredView(source, 2131492969, "field 'coordinatorLayout'");
    target.coordinatorLayout = finder.castView(view, 2131492969, "field 'coordinatorLayout'");
    view = finder.findRequiredView(source, 2131492974, "field 'kitchenName'");
    target.kitchenName = finder.castView(view, 2131492974, "field 'kitchenName'");
    view = finder.findRequiredView(source, 2131492970, "field 'toolbar'");
    target.toolbar = finder.castView(view, 2131492970, "field 'toolbar'");
    view = finder.findRequiredView(source, 2131492971, "field 'buttonLogout' and method 'logout'");
    target.buttonLogout = finder.castView(view, 2131492971, "field 'buttonLogout'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.logout();
        }
      });
  }

  @Override public void unbind(T target) {
    target.recyclerView = null;
    target.progress = null;
    target.progressContainer = null;
    target.coordinatorLayout = null;
    target.kitchenName = null;
    target.toolbar = null;
    target.buttonLogout = null;
  }
}
