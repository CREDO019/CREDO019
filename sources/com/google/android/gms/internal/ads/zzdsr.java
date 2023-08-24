package com.google.android.gms.internal.ads;

import android.view.MotionEvent;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzdsr implements zzbln {
    final /* synthetic */ zzdss zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzdsr(zzdss zzdssVar) {
        this.zza = zzdssVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbln
    public final JSONObject zza() {
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzbln
    public final JSONObject zzb() {
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzbln
    public final void zzc() {
        zzdoj zzdojVar;
        zzdoj zzdojVar2;
        zzdss zzdssVar = this.zza;
        zzdojVar = zzdssVar.zzd;
        if (zzdojVar != null) {
            zzdojVar2 = zzdssVar.zzd;
            zzdojVar2.zzy("_videoMediaView");
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbln
    public final void zzd(MotionEvent motionEvent) {
    }
}
