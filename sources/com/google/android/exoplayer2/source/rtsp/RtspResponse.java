package com.google.android.exoplayer2.source.rtsp;

/* loaded from: classes2.dex */
final class RtspResponse {
    public final RtspHeaders headers;
    public final String messageBody;
    public final int status;

    public RtspResponse(int r1, RtspHeaders rtspHeaders, String str) {
        this.status = r1;
        this.headers = rtspHeaders;
        this.messageBody = str;
    }

    public RtspResponse(int r2, RtspHeaders rtspHeaders) {
        this(r2, rtspHeaders, "");
    }
}
