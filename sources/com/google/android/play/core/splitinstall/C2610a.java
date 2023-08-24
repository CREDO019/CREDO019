package com.google.android.play.core.splitinstall;

import android.app.PendingIntent;
import android.content.Intent;
import java.util.List;

/* renamed from: com.google.android.play.core.splitinstall.a */
/* loaded from: classes3.dex */
final class C2610a extends SplitInstallSessionState {

    /* renamed from: a */
    private final int f936a;

    /* renamed from: b */
    private final int f937b;

    /* renamed from: c */
    private final int f938c;

    /* renamed from: d */
    private final long f939d;

    /* renamed from: e */
    private final long f940e;

    /* renamed from: f */
    private final List<String> f941f;

    /* renamed from: g */
    private final List<String> f942g;

    /* renamed from: h */
    private final PendingIntent f943h;

    /* renamed from: i */
    private final List<Intent> f944i;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2610a(int r1, int r2, int r3, long j, long j2, List<String> list, List<String> list2, PendingIntent pendingIntent, List<Intent> list3) {
        this.f936a = r1;
        this.f937b = r2;
        this.f938c = r3;
        this.f939d = j;
        this.f940e = j2;
        this.f941f = list;
        this.f942g = list2;
        this.f943h = pendingIntent;
        this.f944i = list3;
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallSessionState
    /* renamed from: a */
    final List<String> mo569a() {
        return this.f941f;
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallSessionState
    /* renamed from: b */
    final List<String> mo568b() {
        return this.f942g;
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallSessionState
    public final long bytesDownloaded() {
        return this.f939d;
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallSessionState
    /* renamed from: c */
    final List<Intent> mo567c() {
        return this.f944i;
    }

    public final boolean equals(Object obj) {
        List<String> list;
        List<String> list2;
        PendingIntent pendingIntent;
        if (obj == this) {
            return true;
        }
        if (obj instanceof SplitInstallSessionState) {
            SplitInstallSessionState splitInstallSessionState = (SplitInstallSessionState) obj;
            if (this.f936a == splitInstallSessionState.sessionId() && this.f937b == splitInstallSessionState.status() && this.f938c == splitInstallSessionState.errorCode() && this.f939d == splitInstallSessionState.bytesDownloaded() && this.f940e == splitInstallSessionState.totalBytesToDownload() && ((list = this.f941f) != null ? list.equals(splitInstallSessionState.mo569a()) : splitInstallSessionState.mo569a() == null) && ((list2 = this.f942g) != null ? list2.equals(splitInstallSessionState.mo568b()) : splitInstallSessionState.mo568b() == null) && ((pendingIntent = this.f943h) != null ? pendingIntent.equals(splitInstallSessionState.resolutionIntent()) : splitInstallSessionState.resolutionIntent() == null)) {
                List<Intent> list3 = this.f944i;
                List<Intent> mo567c = splitInstallSessionState.mo567c();
                if (list3 != null ? list3.equals(mo567c) : mo567c == null) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallSessionState
    public final int errorCode() {
        return this.f938c;
    }

    public final int hashCode() {
        int r0 = this.f936a;
        int r1 = this.f937b;
        int r2 = this.f938c;
        long j = this.f939d;
        long j2 = this.f940e;
        int r02 = (((((((((r0 ^ 1000003) * 1000003) ^ r1) * 1000003) ^ r2) * 1000003) ^ ((int) ((j >>> 32) ^ j))) * 1000003) ^ ((int) ((j2 >>> 32) ^ j2))) * 1000003;
        List<String> list = this.f941f;
        int hashCode = (r02 ^ (list == null ? 0 : list.hashCode())) * 1000003;
        List<String> list2 = this.f942g;
        int hashCode2 = (hashCode ^ (list2 == null ? 0 : list2.hashCode())) * 1000003;
        PendingIntent pendingIntent = this.f943h;
        int hashCode3 = (hashCode2 ^ (pendingIntent == null ? 0 : pendingIntent.hashCode())) * 1000003;
        List<Intent> list3 = this.f944i;
        return hashCode3 ^ (list3 != null ? list3.hashCode() : 0);
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallSessionState
    @Deprecated
    public final PendingIntent resolutionIntent() {
        return this.f943h;
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallSessionState
    public final int sessionId() {
        return this.f936a;
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallSessionState
    public final int status() {
        return this.f937b;
    }

    public final String toString() {
        int r1 = this.f936a;
        int r2 = this.f937b;
        int r3 = this.f938c;
        long j = this.f939d;
        long j2 = this.f940e;
        String valueOf = String.valueOf(this.f941f);
        String valueOf2 = String.valueOf(this.f942g);
        String valueOf3 = String.valueOf(this.f943h);
        String valueOf4 = String.valueOf(this.f944i);
        int length = String.valueOf(valueOf).length();
        int length2 = String.valueOf(valueOf2).length();
        StringBuilder sb = new StringBuilder(length + 251 + length2 + String.valueOf(valueOf3).length() + String.valueOf(valueOf4).length());
        sb.append("SplitInstallSessionState{sessionId=");
        sb.append(r1);
        sb.append(", status=");
        sb.append(r2);
        sb.append(", errorCode=");
        sb.append(r3);
        sb.append(", bytesDownloaded=");
        sb.append(j);
        sb.append(", totalBytesToDownload=");
        sb.append(j2);
        sb.append(", moduleNamesNullable=");
        sb.append(valueOf);
        sb.append(", languagesNullable=");
        sb.append(valueOf2);
        sb.append(", resolutionIntent=");
        sb.append(valueOf3);
        sb.append(", splitFileIntents=");
        sb.append(valueOf4);
        sb.append("}");
        return sb.toString();
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallSessionState
    public final long totalBytesToDownload() {
        return this.f940e;
    }
}
