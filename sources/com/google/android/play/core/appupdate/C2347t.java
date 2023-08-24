package com.google.android.play.core.appupdate;

import android.app.PendingIntent;
import java.util.Objects;

/* renamed from: com.google.android.play.core.appupdate.t */
/* loaded from: classes3.dex */
final class C2347t extends AppUpdateInfo {

    /* renamed from: a */
    private final String f330a;

    /* renamed from: b */
    private final int f331b;

    /* renamed from: c */
    private final int f332c;

    /* renamed from: d */
    private final int f333d;

    /* renamed from: e */
    private final Integer f334e;

    /* renamed from: f */
    private final int f335f;

    /* renamed from: g */
    private final long f336g;

    /* renamed from: h */
    private final long f337h;

    /* renamed from: i */
    private final long f338i;

    /* renamed from: j */
    private final long f339j;

    /* renamed from: k */
    private final PendingIntent f340k;

    /* renamed from: l */
    private final PendingIntent f341l;

    /* renamed from: m */
    private final PendingIntent f342m;

    /* renamed from: n */
    private final PendingIntent f343n;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2347t(String str, int r5, int r6, int r7, Integer num, int r9, long j, long j2, long j3, long j4, PendingIntent pendingIntent, PendingIntent pendingIntent2, PendingIntent pendingIntent3, PendingIntent pendingIntent4) {
        Objects.requireNonNull(str, "Null packageName");
        this.f330a = str;
        this.f331b = r5;
        this.f332c = r6;
        this.f333d = r7;
        this.f334e = num;
        this.f335f = r9;
        this.f336g = j;
        this.f337h = j2;
        this.f338i = j3;
        this.f339j = j4;
        this.f340k = pendingIntent;
        this.f341l = pendingIntent2;
        this.f342m = pendingIntent3;
        this.f343n = pendingIntent4;
    }

    @Override // com.google.android.play.core.appupdate.AppUpdateInfo
    /* renamed from: a */
    final long mo1057a() {
        return this.f338i;
    }

    @Override // com.google.android.play.core.appupdate.AppUpdateInfo
    public final int availableVersionCode() {
        return this.f331b;
    }

    @Override // com.google.android.play.core.appupdate.AppUpdateInfo
    /* renamed from: b */
    final long mo1056b() {
        return this.f339j;
    }

    @Override // com.google.android.play.core.appupdate.AppUpdateInfo
    public final long bytesDownloaded() {
        return this.f336g;
    }

    @Override // com.google.android.play.core.appupdate.AppUpdateInfo
    /* renamed from: c */
    final PendingIntent mo1055c() {
        return this.f340k;
    }

    @Override // com.google.android.play.core.appupdate.AppUpdateInfo
    public final Integer clientVersionStalenessDays() {
        return this.f334e;
    }

    @Override // com.google.android.play.core.appupdate.AppUpdateInfo
    /* renamed from: d */
    final PendingIntent mo1054d() {
        return this.f341l;
    }

    @Override // com.google.android.play.core.appupdate.AppUpdateInfo
    /* renamed from: e */
    final PendingIntent mo1053e() {
        return this.f342m;
    }

    public final boolean equals(Object obj) {
        Integer num;
        PendingIntent pendingIntent;
        PendingIntent pendingIntent2;
        PendingIntent pendingIntent3;
        if (obj == this) {
            return true;
        }
        if (obj instanceof AppUpdateInfo) {
            AppUpdateInfo appUpdateInfo = (AppUpdateInfo) obj;
            if (this.f330a.equals(appUpdateInfo.packageName()) && this.f331b == appUpdateInfo.availableVersionCode() && this.f332c == appUpdateInfo.updateAvailability() && this.f333d == appUpdateInfo.installStatus() && ((num = this.f334e) != null ? num.equals(appUpdateInfo.clientVersionStalenessDays()) : appUpdateInfo.clientVersionStalenessDays() == null) && this.f335f == appUpdateInfo.updatePriority() && this.f336g == appUpdateInfo.bytesDownloaded() && this.f337h == appUpdateInfo.totalBytesToDownload() && this.f338i == appUpdateInfo.mo1057a() && this.f339j == appUpdateInfo.mo1056b() && ((pendingIntent = this.f340k) != null ? pendingIntent.equals(appUpdateInfo.mo1055c()) : appUpdateInfo.mo1055c() == null) && ((pendingIntent2 = this.f341l) != null ? pendingIntent2.equals(appUpdateInfo.mo1054d()) : appUpdateInfo.mo1054d() == null) && ((pendingIntent3 = this.f342m) != null ? pendingIntent3.equals(appUpdateInfo.mo1053e()) : appUpdateInfo.mo1053e() == null)) {
                PendingIntent pendingIntent4 = this.f343n;
                PendingIntent mo1052f = appUpdateInfo.mo1052f();
                if (pendingIntent4 != null ? pendingIntent4.equals(mo1052f) : mo1052f == null) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // com.google.android.play.core.appupdate.AppUpdateInfo
    /* renamed from: f */
    final PendingIntent mo1052f() {
        return this.f343n;
    }

    public final int hashCode() {
        int hashCode = (((((((this.f330a.hashCode() ^ 1000003) * 1000003) ^ this.f331b) * 1000003) ^ this.f332c) * 1000003) ^ this.f333d) * 1000003;
        Integer num = this.f334e;
        int hashCode2 = num == null ? 0 : num.hashCode();
        int r4 = this.f335f;
        long j = this.f336g;
        long j2 = this.f337h;
        long j3 = this.f338i;
        long j4 = this.f339j;
        int r0 = (((((((((((hashCode ^ hashCode2) * 1000003) ^ r4) * 1000003) ^ ((int) ((j >>> 32) ^ j))) * 1000003) ^ ((int) ((j2 >>> 32) ^ j2))) * 1000003) ^ ((int) ((j3 >>> 32) ^ j3))) * 1000003) ^ ((int) ((j4 >>> 32) ^ j4))) * 1000003;
        PendingIntent pendingIntent = this.f340k;
        int hashCode3 = (r0 ^ (pendingIntent == null ? 0 : pendingIntent.hashCode())) * 1000003;
        PendingIntent pendingIntent2 = this.f341l;
        int hashCode4 = (hashCode3 ^ (pendingIntent2 == null ? 0 : pendingIntent2.hashCode())) * 1000003;
        PendingIntent pendingIntent3 = this.f342m;
        int hashCode5 = (hashCode4 ^ (pendingIntent3 == null ? 0 : pendingIntent3.hashCode())) * 1000003;
        PendingIntent pendingIntent4 = this.f343n;
        return hashCode5 ^ (pendingIntent4 != null ? pendingIntent4.hashCode() : 0);
    }

    @Override // com.google.android.play.core.appupdate.AppUpdateInfo
    public final int installStatus() {
        return this.f333d;
    }

    @Override // com.google.android.play.core.appupdate.AppUpdateInfo
    public final String packageName() {
        return this.f330a;
    }

    public final String toString() {
        String str = this.f330a;
        int r2 = this.f331b;
        int r3 = this.f332c;
        int r4 = this.f333d;
        String valueOf = String.valueOf(this.f334e);
        int r6 = this.f335f;
        long j = this.f336g;
        long j2 = this.f337h;
        long j3 = this.f338i;
        long j4 = this.f339j;
        String valueOf2 = String.valueOf(this.f340k);
        String valueOf3 = String.valueOf(this.f341l);
        String valueOf4 = String.valueOf(this.f342m);
        String valueOf5 = String.valueOf(this.f343n);
        int length = str.length();
        int length2 = String.valueOf(valueOf).length();
        int length3 = String.valueOf(valueOf2).length();
        int length4 = String.valueOf(valueOf3).length();
        StringBuilder sb = new StringBuilder(length + 463 + length2 + length3 + length4 + String.valueOf(valueOf4).length() + String.valueOf(valueOf5).length());
        sb.append("AppUpdateInfo{packageName=");
        sb.append(str);
        sb.append(", availableVersionCode=");
        sb.append(r2);
        sb.append(", updateAvailability=");
        sb.append(r3);
        sb.append(", installStatus=");
        sb.append(r4);
        sb.append(", clientVersionStalenessDays=");
        sb.append(valueOf);
        sb.append(", updatePriority=");
        sb.append(r6);
        sb.append(", bytesDownloaded=");
        sb.append(j);
        sb.append(", totalBytesToDownload=");
        sb.append(j2);
        sb.append(", additionalSpaceRequired=");
        sb.append(j3);
        sb.append(", assetPackStorageSize=");
        sb.append(j4);
        sb.append(", immediateUpdateIntent=");
        sb.append(valueOf2);
        sb.append(", flexibleUpdateIntent=");
        sb.append(valueOf3);
        sb.append(", immediateDestructiveUpdateIntent=");
        sb.append(valueOf4);
        sb.append(", flexibleDestructiveUpdateIntent=");
        sb.append(valueOf5);
        sb.append("}");
        return sb.toString();
    }

    @Override // com.google.android.play.core.appupdate.AppUpdateInfo
    public final long totalBytesToDownload() {
        return this.f337h;
    }

    @Override // com.google.android.play.core.appupdate.AppUpdateInfo
    public final int updateAvailability() {
        return this.f332c;
    }

    @Override // com.google.android.play.core.appupdate.AppUpdateInfo
    public final int updatePriority() {
        return this.f335f;
    }
}
