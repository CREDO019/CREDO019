package com.google.android.exoplayer2.source.rtsp;

import com.google.common.collect.ImmutableList;
import java.util.Collection;
import java.util.List;

/* loaded from: classes2.dex */
final class RtspOptionsResponse {
    public final int status;
    public final ImmutableList<Integer> supportedMethods;

    public RtspOptionsResponse(int r1, List<Integer> list) {
        this.status = r1;
        this.supportedMethods = ImmutableList.copyOf((Collection) list);
    }
}
