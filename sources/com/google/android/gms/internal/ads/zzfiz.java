package com.google.android.gms.internal.ads;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfiz implements zzfyk {
    final /* synthetic */ zzfjc zza;
    final /* synthetic */ zzfir zzb;
    final /* synthetic */ boolean zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfiz(zzfjc zzfjcVar, zzfir zzfirVar, boolean z) {
        this.zza = zzfjcVar;
        this.zzb = zzfirVar;
        this.zzc = z;
    }

    @Override // com.google.android.gms.internal.ads.zzfyk
    public final void zza(Throwable th) {
        zzfir zzfirVar = this.zzb;
        if (zzfirVar.zzh()) {
            zzfjc zzfjcVar = this.zza;
            zzfirVar.zze(false);
            zzfjcVar.zza(zzfirVar);
            if (this.zzc) {
                this.zza.zzg();
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzfyk
    public final void zzb(Object obj) {
        zzfjc zzfjcVar = this.zza;
        zzfir zzfirVar = this.zzb;
        zzfirVar.zze(true);
        zzfjcVar.zza(zzfirVar);
        if (this.zzc) {
            this.zza.zzg();
        }
    }
}
