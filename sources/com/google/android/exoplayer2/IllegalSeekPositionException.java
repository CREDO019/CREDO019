package com.google.android.exoplayer2;

/* loaded from: classes2.dex */
public final class IllegalSeekPositionException extends IllegalStateException {
    public final long positionMs;
    public final Timeline timeline;
    public final int windowIndex;

    public IllegalSeekPositionException(Timeline timeline, int r2, long j) {
        this.timeline = timeline;
        this.windowIndex = r2;
        this.positionMs = j;
    }
}
