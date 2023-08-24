package com.google.android.gms.ads.nonagon.signalgeneration;

import android.util.Pair;
import com.google.android.gms.internal.ads.zzdxj;
import com.google.android.gms.internal.ads.zzdxt;
import com.google.android.gms.internal.ads.zzfyk;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzz implements zzfyk {
    final /* synthetic */ zzaa zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzz(zzaa zzaaVar) {
        this.zza = zzaaVar;
    }

    @Override // com.google.android.gms.internal.ads.zzfyk
    public final void zza(Throwable th) {
        zzdxt zzdxtVar;
        zzdxj zzdxjVar;
        com.google.android.gms.ads.internal.zzt.zzp().zzt(th, "SignalGeneratorImpl.initializeWebViewForSignalCollection");
        zzaa zzaaVar = this.zza;
        zzdxtVar = zzaaVar.zzr;
        zzdxjVar = zzaaVar.zzj;
        zzf.zzc(zzdxtVar, zzdxjVar, "sgf", new Pair("sgf_reason", th.getMessage()));
        com.google.android.gms.ads.internal.util.zze.zzh("Failed to initialize webview for loading SDKCore. ", th);
    }

    @Override // com.google.android.gms.internal.ads.zzfyk
    public final /* synthetic */ void zzb(Object obj) {
        zzam zzamVar = (zzam) obj;
        com.google.android.gms.ads.internal.util.zze.zze("Initialized webview successfully for SDKCore.");
    }
}
