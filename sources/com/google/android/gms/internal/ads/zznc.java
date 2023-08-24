package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zznc {
    public static final zznc zza = new zznc(-1, -1, -1);
    public final int zzb;
    public final int zzc;
    public final int zzd;
    public final int zze;

    public zznc(int r1, int r2, int r3) {
        this.zzb = r1;
        this.zzc = r2;
        this.zzd = r3;
        this.zze = zzel.zzV(r3) ? zzel.zzo(r3, r2) : -1;
    }

    public final String toString() {
        int r0 = this.zzb;
        int r1 = this.zzc;
        int r2 = this.zzd;
        return "AudioFormat[sampleRate=" + r0 + ", channelCount=" + r1 + ", encoding=" + r2 + "]";
    }
}
