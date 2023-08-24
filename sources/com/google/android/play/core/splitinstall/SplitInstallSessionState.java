package com.google.android.play.core.splitinstall;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public abstract class SplitInstallSessionState {
    /* renamed from: a */
    public static SplitInstallSessionState m570a(Bundle bundle) {
        return new C2610a(bundle.getInt("session_id"), bundle.getInt("status"), bundle.getInt("error_code"), bundle.getLong("bytes_downloaded"), bundle.getLong("total_bytes_to_download"), bundle.getStringArrayList("module_names"), bundle.getStringArrayList("languages"), (PendingIntent) bundle.getParcelable("user_confirmation_intent"), bundle.getParcelableArrayList("split_file_intents"));
    }

    public static SplitInstallSessionState create(int r13, int r14, int r15, long j, long j2, List<String> list, List<String> list2) {
        if (r14 != 8) {
            return new C2610a(r13, r14, r15, j, j2, list, list2, null, null);
        }
        throw new IllegalArgumentException("REQUIRES_USER_CONFIRMATION state not supported.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public abstract List<String> mo569a();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public abstract List<String> mo568b();

    public abstract long bytesDownloaded();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public abstract List<Intent> mo567c();

    public abstract int errorCode();

    public boolean hasTerminalStatus() {
        int status = status();
        return status == 0 || status == 5 || status == 6 || status == 7;
    }

    public List<String> languages() {
        return mo568b() != null ? new ArrayList(mo568b()) : new ArrayList();
    }

    public List<String> moduleNames() {
        return mo569a() != null ? new ArrayList(mo569a()) : new ArrayList();
    }

    @Deprecated
    public abstract PendingIntent resolutionIntent();

    public abstract int sessionId();

    public abstract int status();

    public abstract long totalBytesToDownload();
}
