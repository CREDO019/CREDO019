package com.facebook.fresco.animation.drawable.animator;

import android.animation.ValueAnimator;
import com.facebook.fresco.animation.drawable.AnimatedDrawable2;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class AnimatedDrawable2ValueAnimatorHelper {
    @Nullable
    public static ValueAnimator createValueAnimator(AnimatedDrawable2 animatedDrawable, int maxDurationMs) {
        ValueAnimator createValueAnimator = createValueAnimator(animatedDrawable);
        if (createValueAnimator == null) {
            return null;
        }
        createValueAnimator.setRepeatCount((int) Math.max(maxDurationMs / animatedDrawable.getLoopDurationMs(), 1L));
        return createValueAnimator;
    }

    public static ValueAnimator createValueAnimator(AnimatedDrawable2 animatedDrawable) {
        int loopCount = animatedDrawable.getLoopCount();
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setIntValues(0, (int) animatedDrawable.getLoopDurationMs());
        valueAnimator.setDuration(animatedDrawable.getLoopDurationMs());
        if (loopCount == 0) {
            loopCount = -1;
        }
        valueAnimator.setRepeatCount(loopCount);
        valueAnimator.setRepeatMode(1);
        valueAnimator.setInterpolator(null);
        valueAnimator.addUpdateListener(createAnimatorUpdateListener(animatedDrawable));
        return valueAnimator;
    }

    public static ValueAnimator.AnimatorUpdateListener createAnimatorUpdateListener(final AnimatedDrawable2 drawable) {
        return new ValueAnimator.AnimatorUpdateListener() { // from class: com.facebook.fresco.animation.drawable.animator.AnimatedDrawable2ValueAnimatorHelper.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator animation) {
                AnimatedDrawable2.this.setLevel(((Integer) animation.getAnimatedValue()).intValue());
            }
        };
    }

    private AnimatedDrawable2ValueAnimatorHelper() {
    }
}
