package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zziz {
    public zzjs zza;
    public int zzb;
    public boolean zzc;
    public int zzd;
    public boolean zze;
    public int zzf;
    private boolean zzg;

    public zziz(zzjs zzjsVar) {
        this.zza = zzjsVar;
    }

    public final void zza(int r3) {
        this.zzg = 1 == ((this.zzg ? 1 : 0) | r3);
        this.zzb += r3;
    }

    public final void zzb(int r2) {
        this.zzg = true;
        this.zze = true;
        this.zzf = r2;
    }

    public final void zzc(zzjs zzjsVar) {
        this.zzg |= this.zza != zzjsVar;
        this.zza = zzjsVar;
    }

    public final void zzd(int r4) {
        if (this.zzc && this.zzd != 5) {
            zzdd.zzd(r4 == 5);
            return;
        }
        this.zzg = true;
        this.zzc = true;
        this.zzd = r4;
    }
}
