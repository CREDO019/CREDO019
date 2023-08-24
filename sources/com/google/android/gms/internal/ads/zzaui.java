package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public class zzaui {
    private int zza;

    public final void zza(int r2) {
        this.zza |= Integer.MIN_VALUE;
    }

    public void zzb() {
        this.zza = 0;
    }

    public final void zzc(int r1) {
        this.zza = r1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean zzd(int r2) {
        return (this.zza & r2) == r2;
    }

    public final boolean zze() {
        return zzd(Integer.MIN_VALUE);
    }

    public final boolean zzf() {
        return zzd(4);
    }

    public final boolean zzg() {
        return zzd(1);
    }
}
