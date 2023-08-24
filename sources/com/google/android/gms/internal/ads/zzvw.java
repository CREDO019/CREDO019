package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzvw {
    private zzvv zza;
    private zzwe zzb;

    public void zzh() {
        this.zza = null;
        this.zzb = null;
    }

    public void zzi(zzk zzkVar) {
        throw null;
    }

    public boolean zzl() {
        throw null;
    }

    public abstract zzvx zzn(zzjz[] zzjzVarArr, zzue zzueVar, zzsg zzsgVar, zzcn zzcnVar) throws zzgy;

    public abstract void zzo(Object obj);

    /* JADX INFO: Access modifiers changed from: protected */
    public final zzwe zzp() {
        zzwe zzweVar = this.zzb;
        zzdd.zzb(zzweVar);
        return zzweVar;
    }

    public final void zzq(zzvv zzvvVar, zzwe zzweVar) {
        this.zza = zzvvVar;
        this.zzb = zzweVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void zzr() {
        zzvv zzvvVar = this.zza;
        if (zzvvVar != null) {
            zzvvVar.zzj();
        }
    }
}
