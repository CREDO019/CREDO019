package com.google.android.exoplayer2.text;

import java.util.List;

/* loaded from: classes2.dex */
public interface Subtitle {
    List<Cue> getCues(long j);

    long getEventTime(int r1);

    int getEventTimeCount();

    int getNextEventTimeIndex(long j);
}
