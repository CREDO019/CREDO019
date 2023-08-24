package com.google.android.exoplayer2.offline;

import com.google.android.exoplayer2.util.Assertions;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* loaded from: classes2.dex */
public final class Download {
    public static final int FAILURE_REASON_NONE = 0;
    public static final int FAILURE_REASON_UNKNOWN = 1;
    public static final int STATE_COMPLETED = 3;
    public static final int STATE_DOWNLOADING = 2;
    public static final int STATE_FAILED = 4;
    public static final int STATE_QUEUED = 0;
    public static final int STATE_REMOVING = 5;
    public static final int STATE_RESTARTING = 7;
    public static final int STATE_STOPPED = 1;
    public static final int STOP_REASON_NONE = 0;
    public final long contentLength;
    public final int failureReason;
    final DownloadProgress progress;
    public final DownloadRequest request;
    public final long startTimeMs;
    public final int state;
    public final int stopReason;
    public final long updateTimeMs;

    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface FailureReason {
    }

    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface State {
    }

    public Download(DownloadRequest downloadRequest, int r14, long j, long j2, long j3, int r21, int r22) {
        this(downloadRequest, r14, j, j2, j3, r21, r22, new DownloadProgress());
    }

    public Download(DownloadRequest downloadRequest, int r6, long j, long j2, long j3, int r13, int r14, DownloadProgress downloadProgress) {
        Assertions.checkNotNull(downloadProgress);
        boolean z = true;
        Assertions.checkArgument((r14 == 0) == (r6 != 4));
        if (r13 != 0) {
            Assertions.checkArgument((r6 == 2 || r6 == 0) ? false : false);
        }
        this.request = downloadRequest;
        this.state = r6;
        this.startTimeMs = j;
        this.updateTimeMs = j2;
        this.contentLength = j3;
        this.stopReason = r13;
        this.failureReason = r14;
        this.progress = downloadProgress;
    }

    public boolean isTerminalState() {
        int r0 = this.state;
        return r0 == 3 || r0 == 4;
    }

    public long getBytesDownloaded() {
        return this.progress.bytesDownloaded;
    }

    public float getPercentDownloaded() {
        return this.progress.percentDownloaded;
    }
}
