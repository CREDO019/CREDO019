package com.google.android.play.core.install;

/* loaded from: classes3.dex */
public abstract class InstallState {
    /* renamed from: a */
    public static InstallState m816a(int r9, long j, long j2, int r14, String str) {
        return new C2486a(r9, j, j2, r14, str);
    }

    public abstract long bytesDownloaded();

    public abstract int installErrorCode();

    public abstract int installStatus();

    public abstract String packageName();

    public abstract long totalBytesToDownload();
}
