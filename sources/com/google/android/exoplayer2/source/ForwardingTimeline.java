package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.Timeline;

/* loaded from: classes2.dex */
public abstract class ForwardingTimeline extends Timeline {
    protected final Timeline timeline;

    public ForwardingTimeline(Timeline timeline) {
        this.timeline = timeline;
    }

    @Override // com.google.android.exoplayer2.Timeline
    public int getWindowCount() {
        return this.timeline.getWindowCount();
    }

    @Override // com.google.android.exoplayer2.Timeline
    public int getNextWindowIndex(int r2, int r3, boolean z) {
        return this.timeline.getNextWindowIndex(r2, r3, z);
    }

    @Override // com.google.android.exoplayer2.Timeline
    public int getPreviousWindowIndex(int r2, int r3, boolean z) {
        return this.timeline.getPreviousWindowIndex(r2, r3, z);
    }

    @Override // com.google.android.exoplayer2.Timeline
    public int getLastWindowIndex(boolean z) {
        return this.timeline.getLastWindowIndex(z);
    }

    @Override // com.google.android.exoplayer2.Timeline
    public int getFirstWindowIndex(boolean z) {
        return this.timeline.getFirstWindowIndex(z);
    }

    @Override // com.google.android.exoplayer2.Timeline
    public Timeline.Window getWindow(int r2, Timeline.Window window, long j) {
        return this.timeline.getWindow(r2, window, j);
    }

    @Override // com.google.android.exoplayer2.Timeline
    public int getPeriodCount() {
        return this.timeline.getPeriodCount();
    }

    @Override // com.google.android.exoplayer2.Timeline
    public Timeline.Period getPeriod(int r2, Timeline.Period period, boolean z) {
        return this.timeline.getPeriod(r2, period, z);
    }

    @Override // com.google.android.exoplayer2.Timeline
    public int getIndexOfPeriod(Object obj) {
        return this.timeline.getIndexOfPeriod(obj);
    }

    @Override // com.google.android.exoplayer2.Timeline
    public Object getUidOfPeriod(int r2) {
        return this.timeline.getUidOfPeriod(r2);
    }
}
