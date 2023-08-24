package com.facebook.react.views.scroll;

import android.content.Context;
import com.facebook.react.modules.i18nmanager.I18nUtil;
import com.facebook.react.views.view.ReactViewGroup;

/* loaded from: classes.dex */
public class ReactHorizontalScrollContainerView extends ReactViewGroup {
    private int mCurrentWidth;
    private int mLayoutDirection;

    public ReactHorizontalScrollContainerView(Context context) {
        super(context);
        this.mLayoutDirection = I18nUtil.getInstance().isRTL(context) ? 1 : 0;
        this.mCurrentWidth = 0;
    }

    @Override // com.facebook.react.views.view.ReactViewGroup, com.facebook.react.uimanager.ReactClippingViewGroup
    public void setRemoveClippedSubviews(boolean z) {
        if (this.mLayoutDirection == 1) {
            super.setRemoveClippedSubviews(false);
        } else {
            super.setRemoveClippedSubviews(z);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.views.view.ReactViewGroup, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int r2, int r3, int r4, int r5) {
        if (this.mLayoutDirection == 1) {
            setLeft(0);
            setRight((r4 - r2) + 0);
            if (this.mCurrentWidth != getWidth()) {
                ReactHorizontalScrollView reactHorizontalScrollView = (ReactHorizontalScrollView) getParent();
                reactHorizontalScrollView.scrollTo(((reactHorizontalScrollView.getScrollX() + getWidth()) - this.mCurrentWidth) - reactHorizontalScrollView.getWidth(), reactHorizontalScrollView.getScrollY());
            }
        }
        this.mCurrentWidth = getWidth();
    }
}
