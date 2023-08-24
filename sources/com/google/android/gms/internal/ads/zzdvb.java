package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdvb implements zzddt {
    private final zzcmn zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzdvb(zzcmn zzcmnVar) {
        this.zza = zzcmnVar;
    }

    @Override // com.google.android.gms.internal.ads.zzddt
    public final void zzbq(Context context) {
        zzcmn zzcmnVar = this.zza;
        if (zzcmnVar != null) {
            zzcmnVar.destroy();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzddt
    public final void zzbs(Context context) {
        zzcmn zzcmnVar = this.zza;
        if (zzcmnVar != null) {
            zzcmnVar.onPause();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzddt
    public final void zzbt(Context context) {
        zzcmn zzcmnVar = this.zza;
        if (zzcmnVar != null) {
            zzcmnVar.onResume();
        }
    }
}
