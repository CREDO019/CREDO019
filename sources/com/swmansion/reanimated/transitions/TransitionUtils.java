package com.swmansion.reanimated.transitions;

import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import androidx.transition.ChangeBounds;
import androidx.transition.ChangeTransform;
import androidx.transition.Fade;
import androidx.transition.Slide;
import androidx.transition.Transition;
import androidx.transition.TransitionSet;
import androidx.transition.Visibility;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.ViewProps;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import javax.annotation.Nullable;

/* loaded from: classes3.dex */
class TransitionUtils {
    TransitionUtils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public static Transition inflate(ReadableMap readableMap) {
        String string = readableMap.getString(SessionDescription.ATTR_TYPE);
        if ("group".equals(string)) {
            return inflateGroup(readableMap);
        }
        if ("in".equals(string)) {
            return inflateIn(readableMap);
        }
        if ("out".equals(string)) {
            return inflateOut(readableMap);
        }
        if ("change".equals(string)) {
            return inflateChange(readableMap);
        }
        throw new JSApplicationIllegalArgumentException("Unrecognized transition type " + string);
    }

    @Nullable
    private static Transition inflateGroup(ReadableMap readableMap) {
        TransitionSet transitionSet = new TransitionSet();
        if (readableMap.hasKey("sequence") && readableMap.getBoolean("sequence")) {
            transitionSet.setOrdering(1);
        } else {
            transitionSet.setOrdering(0);
        }
        ReadableArray array = readableMap.getArray("transitions");
        int size = array.size();
        for (int r3 = 0; r3 < size; r3++) {
            Transition inflate = inflate(array.getMap(r3));
            if (inflate != null) {
                transitionSet.addTransition(inflate);
            }
        }
        return transitionSet;
    }

    static Visibility createVisibilityTransition(String str) {
        if (str == null || "none".equals(str)) {
            return null;
        }
        if ("fade".equals(str)) {
            return new Fade(3);
        }
        if ("scale".equals(str)) {
            return new Scale();
        }
        if ("slide-top".equals(str)) {
            return new Slide(48);
        }
        if ("slide-bottom".equals(str)) {
            return new Slide(80);
        }
        if ("slide-right".equals(str)) {
            return new Slide(5);
        }
        if ("slide-left".equals(str)) {
            return new Slide(3);
        }
        throw new JSApplicationIllegalArgumentException("Invalid transition type " + str);
    }

    private static Transition inflateIn(ReadableMap readableMap) {
        Visibility createTransition = createTransition(readableMap.getString("animation"));
        if (createTransition == null) {
            return null;
        }
        createTransition.setMode(1);
        configureTransition(createTransition, readableMap);
        return createTransition;
    }

    private static Transition inflateOut(ReadableMap readableMap) {
        Visibility createTransition = createTransition(readableMap.getString("animation"));
        if (createTransition == null) {
            return null;
        }
        createTransition.setMode(2);
        configureTransition(createTransition, readableMap);
        return createTransition;
    }

    private static Transition inflateChange(ReadableMap readableMap) {
        ChangeBounds changeBounds = new ChangeBounds();
        ChangeTransform changeTransform = new ChangeTransform();
        configureTransition(changeBounds, readableMap);
        configureTransition(changeTransform, readableMap);
        return new TransitionSet().addTransition(changeBounds).addTransition(changeTransform);
    }

    private static Visibility createTransition(String str) {
        if (str == null || "none".equals(str)) {
            return null;
        }
        if ("fade".equals(str)) {
            return new Fade(3);
        }
        if ("scale".equals(str)) {
            return new Scale();
        }
        if ("slide-top".equals(str)) {
            return new Slide(48);
        }
        if ("slide-bottom".equals(str)) {
            return new Slide(80);
        }
        if ("slide-right".equals(str)) {
            return new Slide(5);
        }
        if ("slide-left".equals(str)) {
            return new Slide(3);
        }
        throw new JSApplicationIllegalArgumentException("Invalid transition type " + str);
    }

    private static void configureTransition(Transition transition, ReadableMap readableMap) {
        if (readableMap.hasKey("durationMs")) {
            transition.setDuration(readableMap.getInt("durationMs"));
        }
        if (readableMap.hasKey("interpolation")) {
            String string = readableMap.getString("interpolation");
            if (string.equals("easeIn")) {
                transition.setInterpolator(new AccelerateInterpolator());
            } else if (string.equals("easeOut")) {
                transition.setInterpolator(new DecelerateInterpolator());
            } else if (string.equals("easeInOut")) {
                transition.setInterpolator(new AccelerateDecelerateInterpolator());
            } else if (string.equals("linear")) {
                transition.setInterpolator(new LinearInterpolator());
            } else {
                throw new JSApplicationIllegalArgumentException("Invalid interpolation type " + string);
            }
        }
        if (readableMap.hasKey("propagation")) {
            String string2 = readableMap.getString("propagation");
            SaneSidePropagation saneSidePropagation = new SaneSidePropagation();
            if (ViewProps.TOP.equals(string2)) {
                saneSidePropagation.setSide(80);
            } else if (ViewProps.BOTTOM.equals(string2)) {
                saneSidePropagation.setSide(48);
            } else if ("left".equals(string2)) {
                saneSidePropagation.setSide(5);
            } else if ("right".equals(string2)) {
                saneSidePropagation.setSide(3);
            }
            transition.setPropagation(saneSidePropagation);
        } else {
            transition.setPropagation(null);
        }
        if (readableMap.hasKey("delayMs")) {
            transition.setStartDelay(readableMap.getInt("delayMs"));
        }
    }
}
