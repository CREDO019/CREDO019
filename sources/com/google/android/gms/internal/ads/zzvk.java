package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
abstract class zzvk {
    public final int zza;
    public final zzcp zzb;
    public final int zzc;
    public final zzaf zzd;

    public zzvk(int r1, zzcp zzcpVar, int r3) {
        this.zza = r1;
        this.zzb = zzcpVar;
        this.zzc = r3;
        this.zzd = zzcpVar.zzb(r3);
    }

    public abstract int zzb();

    public abstract boolean zzc(zzvk zzvkVar);
}
