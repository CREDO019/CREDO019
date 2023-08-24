package androidx.transition;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

/* loaded from: classes.dex */
public class Explode extends Visibility {
    private static final String PROPNAME_SCREEN_BOUNDS = "android:explode:screenBounds";
    private int[] mTempLoc;
    private static final TimeInterpolator sDecelerate = new DecelerateInterpolator();
    private static final TimeInterpolator sAccelerate = new AccelerateInterpolator();

    public Explode() {
        this.mTempLoc = new int[2];
        setPropagation(new CircularPropagation());
    }

    public Explode(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mTempLoc = new int[2];
        setPropagation(new CircularPropagation());
    }

    private void captureValues(TransitionValues transitionValues) {
        View view = transitionValues.view;
        view.getLocationOnScreen(this.mTempLoc);
        int[] r1 = this.mTempLoc;
        int r2 = r1[0];
        int r12 = r1[1];
        transitionValues.values.put(PROPNAME_SCREEN_BOUNDS, new Rect(r2, r12, view.getWidth() + r2, view.getHeight() + r12));
    }

    @Override // androidx.transition.Visibility, androidx.transition.Transition
    public void captureStartValues(TransitionValues transitionValues) {
        super.captureStartValues(transitionValues);
        captureValues(transitionValues);
    }

    @Override // androidx.transition.Visibility, androidx.transition.Transition
    public void captureEndValues(TransitionValues transitionValues) {
        super.captureEndValues(transitionValues);
        captureValues(transitionValues);
    }

    @Override // androidx.transition.Visibility
    public Animator onAppear(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        if (transitionValues2 == null) {
            return null;
        }
        Rect rect = (Rect) transitionValues2.values.get(PROPNAME_SCREEN_BOUNDS);
        float translationX = view.getTranslationX();
        float translationY = view.getTranslationY();
        calculateOut(viewGroup, rect, this.mTempLoc);
        int[] r11 = this.mTempLoc;
        return TranslationAnimationCreator.createAnimation(view, transitionValues2, rect.left, rect.top, translationX + r11[0], translationY + r11[1], translationX, translationY, sDecelerate, this);
    }

    @Override // androidx.transition.Visibility
    public Animator onDisappear(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        float f;
        float f2;
        if (transitionValues == null) {
            return null;
        }
        Rect rect = (Rect) transitionValues.values.get(PROPNAME_SCREEN_BOUNDS);
        int r2 = rect.left;
        int r3 = rect.top;
        float translationX = view.getTranslationX();
        float translationY = view.getTranslationY();
        int[] r0 = (int[]) transitionValues.view.getTag(C0699R.C0702id.transition_position);
        if (r0 != null) {
            f = (r0[0] - rect.left) + translationX;
            f2 = (r0[1] - rect.top) + translationY;
            rect.offsetTo(r0[0], r0[1]);
        } else {
            f = translationX;
            f2 = translationY;
        }
        calculateOut(viewGroup, rect, this.mTempLoc);
        int[] r11 = this.mTempLoc;
        return TranslationAnimationCreator.createAnimation(view, transitionValues, r2, r3, translationX, translationY, f + r11[0], f2 + r11[1], sAccelerate, this);
    }

    private void calculateOut(View view, Rect rect, int[] r18) {
        int centerY;
        int r6;
        view.getLocationOnScreen(this.mTempLoc);
        int[] r2 = this.mTempLoc;
        int r4 = r2[0];
        int r22 = r2[1];
        Rect epicenter = getEpicenter();
        if (epicenter == null) {
            r6 = (view.getWidth() / 2) + r4 + Math.round(view.getTranslationX());
            centerY = (view.getHeight() / 2) + r22 + Math.round(view.getTranslationY());
        } else {
            int centerX = epicenter.centerX();
            centerY = epicenter.centerY();
            r6 = centerX;
        }
        float centerX2 = rect.centerX() - r6;
        float centerY2 = rect.centerY() - centerY;
        if (centerX2 == 0.0f && centerY2 == 0.0f) {
            centerX2 = ((float) (Math.random() * 2.0d)) - 1.0f;
            centerY2 = ((float) (Math.random() * 2.0d)) - 1.0f;
        }
        float calculateDistance = calculateDistance(centerX2, centerY2);
        float calculateMaxDistance = calculateMaxDistance(view, r6 - r4, centerY - r22);
        r18[0] = Math.round((centerX2 / calculateDistance) * calculateMaxDistance);
        r18[1] = Math.round(calculateMaxDistance * (centerY2 / calculateDistance));
    }

    private static float calculateMaxDistance(View view, int r2, int r3) {
        return calculateDistance(Math.max(r2, view.getWidth() - r2), Math.max(r3, view.getHeight() - r3));
    }

    private static float calculateDistance(float f, float f2) {
        return (float) Math.sqrt((f * f) + (f2 * f2));
    }
}
