package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzog {
    private zzoi zzc;
    private zznb zzb = zznb.zza;
    final zzof zza = zzof.zza;

    public final zzog zzb(zznb zznbVar) {
        this.zzb = zznbVar;
        return this;
    }

    public final zzog zzc(zzne[] zzneVarArr) {
        this.zzc = new zzoi(zzneVarArr);
        return this;
    }

    public final zzos zzd() {
        if (this.zzc == null) {
            this.zzc = new zzoi(new zzne[0]);
        }
        return new zzos(this, null);
    }
}
