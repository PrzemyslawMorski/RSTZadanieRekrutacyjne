// Generated code from Butter Knife. Do not modify!
package com.example.filu.deckofcards;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity_ViewBinding implements Unbinder {
  private MainActivity target;

  private View view2131427430;

  @UiThread
  public MainActivity_ViewBinding(MainActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MainActivity_ViewBinding(final MainActivity target, View source) {
    this.target = target;

    View view;
    target.numDecksBar = Utils.findRequiredViewAsType(source, R.id.numDecksBar, "field 'numDecksBar'", SeekBar.class);
    target.numDecksText = Utils.findRequiredViewAsType(source, R.id.numDecksText, "field 'numDecksText'", TextView.class);
    view = Utils.findRequiredView(source, R.id.drawCardsBtn, "method 'onLosujClicked'");
    view2131427430 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onLosujClicked(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    MainActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.numDecksBar = null;
    target.numDecksText = null;

    view2131427430.setOnClickListener(null);
    view2131427430 = null;
  }
}
