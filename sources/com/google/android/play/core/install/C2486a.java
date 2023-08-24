package com.google.android.play.core.install;

import java.util.Objects;

/* renamed from: com.google.android.play.core.install.a */
/* loaded from: classes3.dex */
final class C2486a extends InstallState {

    /* renamed from: a */
    private final int f796a;

    /* renamed from: b */
    private final long f797b;

    /* renamed from: c */
    private final long f798c;

    /* renamed from: d */
    private final int f799d;

    /* renamed from: e */
    private final String f800e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2486a(int r1, long j, long j2, int r6, String str) {
        this.f796a = r1;
        this.f797b = j;
        this.f798c = j2;
        this.f799d = r6;
        Objects.requireNonNull(str, "Null packageName");
        this.f800e = str;
    }

    @Override // com.google.android.play.core.install.InstallState
    public final long bytesDownloaded() {
        return this.f797b;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof InstallState) {
            InstallState installState = (InstallState) obj;
            if (this.f796a == installState.installStatus() && this.f797b == installState.bytesDownloaded() && this.f798c == installState.totalBytesToDownload() && this.f799d == installState.installErrorCode() && this.f800e.equals(installState.packageName())) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int r0 = this.f796a;
        long j = this.f797b;
        long j2 = this.f798c;
        return ((((((((r0 ^ 1000003) * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003) ^ ((int) ((j2 >>> 32) ^ j2))) * 1000003) ^ this.f799d) * 1000003) ^ this.f800e.hashCode();
    }

    @Override // com.google.android.play.core.install.InstallState
    public final int installErrorCode() {
        return this.f799d;
    }

    @Override // com.google.android.play.core.install.InstallState
    public final int installStatus() {
        return this.f796a;
    }

    @Override // com.google.android.play.core.install.InstallState
    public final String packageName() {
        return this.f800e;
    }

    public final String toString() {
        int r0 = this.f796a;
        long j = this.f797b;
        long j2 = this.f798c;
        int r5 = this.f799d;
        String str = this.f800e;
        StringBuilder sb = new StringBuilder(str.length() + 164);
        sb.append("InstallState{installStatus=");
        sb.append(r0);
        sb.append(", bytesDownloaded=");
        sb.append(j);
        sb.append(", totalBytesToDownload=");
        sb.append(j2);
        sb.append(", installErrorCode=");
        sb.append(r5);
        sb.append(", packageName=");
        sb.append(str);
        sb.append("}");
        return sb.toString();
    }

    @Override // com.google.android.play.core.install.InstallState
    public final long totalBytesToDownload() {
        return this.f798c;
    }
}
