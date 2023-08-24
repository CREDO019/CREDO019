package com.google.android.exoplayer2.source.dash.manifest;

import java.util.Collections;
import java.util.List;

/* loaded from: classes2.dex */
public class Period {
    public final List<AdaptationSet> adaptationSets;
    public final Descriptor assetIdentifier;
    public final List<EventStream> eventStreams;

    /* renamed from: id */
    public final String f244id;
    public final long startMs;

    public Period(String str, long j, List<AdaptationSet> list) {
        this(str, j, list, Collections.emptyList(), null);
    }

    public Period(String str, long j, List<AdaptationSet> list, List<EventStream> list2) {
        this(str, j, list, list2, null);
    }

    public Period(String str, long j, List<AdaptationSet> list, List<EventStream> list2, Descriptor descriptor) {
        this.f244id = str;
        this.startMs = j;
        this.adaptationSets = Collections.unmodifiableList(list);
        this.eventStreams = Collections.unmodifiableList(list2);
        this.assetIdentifier = descriptor;
    }

    public int getAdaptationSetIndex(int r4) {
        int size = this.adaptationSets.size();
        for (int r1 = 0; r1 < size; r1++) {
            if (this.adaptationSets.get(r1).type == r4) {
                return r1;
            }
        }
        return -1;
    }
}
