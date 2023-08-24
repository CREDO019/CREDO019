package androidx.transition;

import android.view.View;

/* loaded from: classes.dex */
public abstract class VisibilityPropagation extends TransitionPropagation {
    private static final String PROPNAME_VISIBILITY = "android:visibilityPropagation:visibility";
    private static final String PROPNAME_VIEW_CENTER = "android:visibilityPropagation:center";
    private static final String[] VISIBILITY_PROPAGATION_VALUES = {PROPNAME_VISIBILITY, PROPNAME_VIEW_CENTER};

    @Override // androidx.transition.TransitionPropagation
    public void captureValues(TransitionValues transitionValues) {
        View view = transitionValues.view;
        Integer num = (Integer) transitionValues.values.get("android:visibility:visibility");
        if (num == null) {
            num = Integer.valueOf(view.getVisibility());
        }
        transitionValues.values.put(PROPNAME_VISIBILITY, num);
        view.getLocationOnScreen(r2);
        int[] r2 = {r2[0] + Math.round(view.getTranslationX())};
        r2[0] = r2[0] + (view.getWidth() / 2);
        r2[1] = r2[1] + Math.round(view.getTranslationY());
        r2[1] = r2[1] + (view.getHeight() / 2);
        transitionValues.values.put(PROPNAME_VIEW_CENTER, r2);
    }

    @Override // androidx.transition.TransitionPropagation
    public String[] getPropagationProperties() {
        return VISIBILITY_PROPAGATION_VALUES;
    }

    public int getViewVisibility(TransitionValues transitionValues) {
        Integer num;
        if (transitionValues == null || (num = (Integer) transitionValues.values.get(PROPNAME_VISIBILITY)) == null) {
            return 8;
        }
        return num.intValue();
    }

    public int getViewX(TransitionValues transitionValues) {
        return getViewCoordinate(transitionValues, 0);
    }

    public int getViewY(TransitionValues transitionValues) {
        return getViewCoordinate(transitionValues, 1);
    }

    private static int getViewCoordinate(TransitionValues transitionValues, int r3) {
        int[] r2;
        if (transitionValues == null || (r2 = (int[]) transitionValues.values.get(PROPNAME_VIEW_CENTER)) == null) {
            return -1;
        }
        return r2[r3];
    }
}
