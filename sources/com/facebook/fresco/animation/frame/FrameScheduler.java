package com.facebook.fresco.animation.frame;

/* loaded from: classes.dex */
public interface FrameScheduler {
    public static final int FRAME_NUMBER_DONE = -1;
    public static final int NO_NEXT_TARGET_RENDER_TIME = -1;

    int getFrameNumberToRender(long animationTimeMs, long lastFrameTimeMs);

    long getLoopDurationMs();

    long getTargetRenderTimeForNextFrameMs(long animationTimeMs);

    long getTargetRenderTimeMs(int frameNumber);

    boolean isInfiniteAnimation();
}
