// Generated code from Butter Knife. Do not modify!
package com.example.filu.deckofcards;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DeckViewActivity_ViewBinding implements Unbinder {
  private DeckViewActivity target;

  private View view2131427426;

  @UiThread
  public DeckViewActivity_ViewBinding(DeckViewActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public DeckViewActivity_ViewBinding(final DeckViewActivity target, View source) {
    this.target = target;

    View view;
    target.cardsInfo = Utils.findRequiredViewAsType(source, R.id.cardsInfoText, "field 'cardsInfo'", TextView.class);
    view = Utils.findRequiredView(source, R.id.drawMoreCardsBtn, "method 'onDrawMoreCardsClick'");
    view2131427426 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onDrawMoreCardsClick(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    DeckViewActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.cardsInfo = null;

    view2131427426.setOnClickListener(null);
    view2131427426 = null;
  }
}
