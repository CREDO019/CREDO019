package com.facebook.fresco.animation.drawable;

/* loaded from: classes.dex */
public interface AnimationListener {
    void onAnimationFrame(AnimatedDrawable2 drawable, int frameNumber);

    void onAnimationRepeat(AnimatedDrawable2 drawable);

    void onAnimationReset(AnimatedDrawable2 drawable);

    void onAnimationStart(AnimatedDrawable2 drawable);

    void onAnimationStop(AnimatedDrawable2 drawable);
}
