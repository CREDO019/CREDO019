package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzaev {
    public final int zza;
    public int zzb;
    public int zzc;
    public long zzd;
    private final boolean zze;
    private final zzed zzf;
    private final zzed zzg;
    private int zzh;
    private int zzi;

    public zzaev(zzed zzedVar, zzed zzedVar2, boolean z) throws zzbu {
        this.zzg = zzedVar;
        this.zzf = zzedVar2;
        this.zze = z;
        zzedVar2.zzF(12);
        this.zza = zzedVar2.zzn();
        zzedVar.zzF(12);
        this.zzi = zzedVar.zzn();
        zzzj.zzb(zzedVar.zze() == 1, "first_chunk must be 1");
        this.zzb = -1;
    }

    public final boolean zza() {
        int r0 = this.zzb + 1;
        this.zzb = r0;
        if (r0 == this.zza) {
            return false;
        }
        this.zzd = this.zze ? this.zzf.zzt() : this.zzf.zzs();
        if (this.zzb == this.zzh) {
            this.zzc = this.zzg.zzn();
            this.zzg.zzG(4);
            int r02 = this.zzi - 1;
            this.zzi = r02;
            this.zzh = r02 > 0 ? (-1) + this.zzg.zzn() : -1;
        }
        return true;
    }
}
