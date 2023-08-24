package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.C1856C;
import com.google.android.exoplayer2.Format;

/* loaded from: classes2.dex */
public final class MediaLoadData {
    public final int dataType;
    public final long mediaEndTimeMs;
    public final long mediaStartTimeMs;
    public final Format trackFormat;
    public final Object trackSelectionData;
    public final int trackSelectionReason;
    public final int trackType;

    public MediaLoadData(int r11) {
        this(r11, -1, null, 0, null, C1856C.TIME_UNSET, C1856C.TIME_UNSET);
    }

    public MediaLoadData(int r1, int r2, Format format, int r4, Object obj, long j, long j2) {
        this.dataType = r1;
        this.trackType = r2;
        this.trackFormat = format;
        this.trackSelectionReason = r4;
        this.trackSelectionData = obj;
        this.mediaStartTimeMs = j;
        this.mediaEndTimeMs = j2;
    }
}
