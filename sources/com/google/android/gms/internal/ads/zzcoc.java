package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcoc {
    public final int zza;
    public final int zzb;
    private final int zzc;

    private zzcoc(int r1, int r2, int r3) {
        this.zzc = r1;
        this.zzb = r2;
        this.zza = r3;
    }

    public static zzcoc zza() {
        return new zzcoc(0, 0, 0);
    }

    public static zzcoc zzb(int r2, int r3) {
        return new zzcoc(1, r2, r3);
    }

    public static zzcoc zzc(com.google.android.gms.ads.internal.client.zzq zzqVar) {
        return zzqVar.zzd ? new zzcoc(3, 0, 0) : zzqVar.zzi ? new zzcoc(2, 0, 0) : zzqVar.zzh ? zza() : zzb(zzqVar.zzf, zzqVar.zzc);
    }

    public static zzcoc zzd() {
        return new zzcoc(5, 0, 0);
    }

    public static zzcoc zze() {
        return new zzcoc(4, 0, 0);
    }

    public final boolean zzf() {
        return this.zzc == 0;
    }

    public final boolean zzg() {
        return this.zzc == 2;
    }

    public final boolean zzh() {
        return this.zzc == 5;
    }

    public final boolean zzi() {
        return this.zzc == 3;
    }

    public final boolean zzj() {
        return this.zzc == 4;
    }
}
