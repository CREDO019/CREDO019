package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzavt {
    public final int zza;
    public int zzb;
    public int zzc;
    public long zzd;
    private final boolean zze;
    private final zzbag zzf;
    private final zzbag zzg;
    private int zzh;
    private int zzi;

    public zzavt(zzbag zzbagVar, zzbag zzbagVar2, boolean z) {
        this.zzg = zzbagVar;
        this.zzf = zzbagVar2;
        this.zze = z;
        zzbagVar2.zzv(12);
        this.zza = zzbagVar2.zzi();
        zzbagVar.zzv(12);
        this.zzi = zzbagVar.zzi();
        zzazy.zzf(zzbagVar.zze() == 1, "first_chunk must be 1");
        this.zzb = -1;
    }

    public final boolean zza() {
        int r0 = this.zzb + 1;
        this.zzb = r0;
        if (r0 == this.zza) {
            return false;
        }
        this.zzd = this.zze ? this.zzf.zzn() : this.zzf.zzm();
        if (this.zzb == this.zzh) {
            this.zzc = this.zzg.zzi();
            this.zzg.zzw(4);
            int r02 = this.zzi - 1;
            this.zzi = r02;
            this.zzh = r02 > 0 ? (-1) + this.zzg.zzi() : -1;
        }
        return true;
    }
}
