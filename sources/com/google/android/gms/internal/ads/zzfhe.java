package com.google.android.gms.internal.ads;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfhe implements zzfyk {
    final /* synthetic */ zzfgu zza;
    final /* synthetic */ zzfhg zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfhe(zzfhg zzfhgVar, zzfgu zzfguVar) {
        this.zzb = zzfhgVar;
        this.zza = zzfguVar;
    }

    @Override // com.google.android.gms.internal.ads.zzfyk
    public final void zza(Throwable th) {
        zzfhi zzfhiVar;
        zzfhiVar = this.zzb.zza.zzd;
        zzfhiVar.zzb(this.zza, th);
    }

    @Override // com.google.android.gms.internal.ads.zzfyk
    public final void zzb(Object obj) {
        zzfhi zzfhiVar;
        zzfhiVar = this.zzb.zza.zzd;
        zzfhiVar.zzd(this.zza);
    }
}
