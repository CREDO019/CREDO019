package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.ads.zzfmf;
import com.google.android.gms.internal.ads.zzfni;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzh implements zzfni {
    final /* synthetic */ zzi zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzh(zzi zziVar) {
        this.zza = zziVar;
    }

    @Override // com.google.android.gms.internal.ads.zzfni
    public final void zza(int r4, long j) {
        zzfmf zzfmfVar;
        zzfmfVar = this.zza.zzi;
        zzfmfVar.zzd(r4, System.currentTimeMillis() - j);
    }

    @Override // com.google.android.gms.internal.ads.zzfni
    public final void zzb(int r4, long j, String str) {
        zzfmf zzfmfVar;
        zzfmfVar = this.zza.zzi;
        zzfmfVar.zze(r4, System.currentTimeMillis() - j, str);
    }
}
