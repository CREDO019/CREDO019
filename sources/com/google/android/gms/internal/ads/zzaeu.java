package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
class zzaeu {
    public final int zzd;

    public zzaeu(int r1) {
        this.zzd = r1;
    }

    public static int zze(int r0) {
        return (r0 >> 24) & 255;
    }

    public static String zzf(int r2) {
        StringBuilder sb = new StringBuilder();
        sb.append((char) ((r2 >> 24) & 255));
        sb.append((char) ((r2 >> 16) & 255));
        sb.append((char) ((r2 >> 8) & 255));
        sb.append((char) (r2 & 255));
        return sb.toString();
    }

    public String toString() {
        return zzf(this.zzd);
    }
}
