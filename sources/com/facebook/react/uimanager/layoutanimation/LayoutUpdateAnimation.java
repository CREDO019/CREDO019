package com.facebook.react.uimanager.layoutanimation;

import android.view.View;
import android.view.animation.Animation;

/* loaded from: classes.dex */
class LayoutUpdateAnimation extends AbstractLayoutAnimation {
    private static final boolean USE_TRANSLATE_ANIMATION = false;

    @Override // com.facebook.react.uimanager.layoutanimation.AbstractLayoutAnimation
    boolean isValid() {
        return this.mDurationMs > 0;
    }

    @Override // com.facebook.react.uimanager.layoutanimation.AbstractLayoutAnimation
    Animation createAnimationImpl(View view, int r9, int r10, int r11, int r12) {
        boolean z = false;
        boolean z2 = (view.getX() == ((float) r9) && view.getY() == ((float) r10)) ? false : true;
        z = (view.getWidth() == r11 && view.getHeight() == r12) ? true : true;
        if (z2 || z) {
            return new PositionAndSizeAnimation(view, r9, r10, r11, r12);
        }
        return null;
    }
}
