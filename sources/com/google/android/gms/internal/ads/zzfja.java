package com.google.android.gms.internal.ads;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfja implements zzfyk {
    final /* synthetic */ zzfjc zza;
    final /* synthetic */ zzfir zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfja(zzfjc zzfjcVar, zzfir zzfirVar) {
        this.zza = zzfjcVar;
        this.zzb = zzfirVar;
    }

    @Override // com.google.android.gms.internal.ads.zzfyk
    public final void zza(Throwable th) {
        zzfjc zzfjcVar = this.zza;
        zzfir zzfirVar = this.zzb;
        zzfirVar.zze(false);
        zzfjcVar.zza(zzfirVar);
    }

    @Override // com.google.android.gms.internal.ads.zzfyk
    public final void zzb(Object obj) {
    }
}
