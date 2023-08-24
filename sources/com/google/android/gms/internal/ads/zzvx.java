package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzvx {
    public final int zza;
    public final zzka[] zzb;
    public final zzvq[] zzc;
    public final zzcy zzd;
    public final Object zze;

    public zzvx(zzka[] zzkaVarArr, zzvq[] zzvqVarArr, zzcy zzcyVar, Object obj) {
        this.zzb = zzkaVarArr;
        this.zzc = (zzvq[]) zzvqVarArr.clone();
        this.zzd = zzcyVar;
        this.zze = obj;
        this.zza = zzkaVarArr.length;
    }

    public final boolean zza(zzvx zzvxVar, int r5) {
        return zzvxVar != null && zzel.zzT(this.zzb[r5], zzvxVar.zzb[r5]) && zzel.zzT(this.zzc[r5], zzvxVar.zzc[r5]);
    }

    public final boolean zzb(int r2) {
        return this.zzb[r2] != null;
    }
}
