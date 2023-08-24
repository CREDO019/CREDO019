package com.google.android.gms.internal.ads;

import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbqc implements zzbqd {
    final /* synthetic */ zzchf zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbqc(zzbqe zzbqeVar, zzchf zzchfVar) {
        this.zza = zzchfVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbqd
    public final void zza(String str) {
        this.zza.zze(new zzbtu(str));
    }

    @Override // com.google.android.gms.internal.ads.zzbqd
    public final void zzb(JSONObject jSONObject) {
        this.zza.zzd(jSONObject);
    }
}
