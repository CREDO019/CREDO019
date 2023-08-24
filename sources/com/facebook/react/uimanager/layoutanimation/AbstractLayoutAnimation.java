package com.facebook.react.uimanager.layoutanimation;

import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.BaseInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.IllegalViewOperationException;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class AbstractLayoutAnimation {
    private static final Map<InterpolatorType, BaseInterpolator> INTERPOLATOR = MapBuilder.m1258of(InterpolatorType.LINEAR, new LinearInterpolator(), InterpolatorType.EASE_IN, new AccelerateInterpolator(), InterpolatorType.EASE_OUT, new DecelerateInterpolator(), InterpolatorType.EASE_IN_EASE_OUT, new AccelerateDecelerateInterpolator());
    private static final boolean SLOWDOWN_ANIMATION_MODE = false;
    protected AnimatedPropertyType mAnimatedProperty;
    private int mDelayMs;
    protected int mDurationMs;
    private Interpolator mInterpolator;

    abstract Animation createAnimationImpl(View view, int r2, int r3, int r4, int r5);

    abstract boolean isValid();

    public void reset() {
        this.mAnimatedProperty = null;
        this.mDurationMs = 0;
        this.mDelayMs = 0;
        this.mInterpolator = null;
    }

    public void initializeFromConfig(ReadableMap readableMap, int r4) {
        this.mAnimatedProperty = readableMap.hasKey("property") ? AnimatedPropertyType.fromString(readableMap.getString("property")) : null;
        if (readableMap.hasKey("duration")) {
            r4 = readableMap.getInt("duration");
        }
        this.mDurationMs = r4;
        this.mDelayMs = readableMap.hasKey("delay") ? readableMap.getInt("delay") : 0;
        if (!readableMap.hasKey(SessionDescription.ATTR_TYPE)) {
            throw new IllegalArgumentException("Missing interpolation type.");
        }
        this.mInterpolator = getInterpolator(InterpolatorType.fromString(readableMap.getString(SessionDescription.ATTR_TYPE)), readableMap);
        if (isValid()) {
            return;
        }
        throw new IllegalViewOperationException("Invalid layout animation : " + readableMap);
    }

    public final Animation createAnimation(View view, int r3, int r4, int r5, int r6) {
        if (isValid()) {
            Animation createAnimationImpl = createAnimationImpl(view, r3, r4, r5, r6);
            if (createAnimationImpl != null) {
                createAnimationImpl.setDuration(this.mDurationMs * 1);
                createAnimationImpl.setStartOffset(this.mDelayMs * 1);
                createAnimationImpl.setInterpolator(this.mInterpolator);
            }
            return createAnimationImpl;
        }
        return null;
    }

    private static Interpolator getInterpolator(InterpolatorType interpolatorType, ReadableMap readableMap) {
        BaseInterpolator baseInterpolator;
        if (interpolatorType.equals(InterpolatorType.SPRING)) {
            baseInterpolator = new SimpleSpringInterpolator(SimpleSpringInterpolator.getSpringDamping(readableMap));
        } else {
            baseInterpolator = INTERPOLATOR.get(interpolatorType);
        }
        if (baseInterpolator != null) {
            return baseInterpolator;
        }
        throw new IllegalArgumentException("Missing interpolator for type : " + interpolatorType);
    }
}
