package com.google.android.gms.internal.ads;

import android.view.MotionEvent;
import android.view.ViewGroup;
import java.util.Map;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzdpn implements zzbln {
    final /* synthetic */ zzdqj zza;
    final /* synthetic */ ViewGroup zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzdpn(zzdqj zzdqjVar, ViewGroup viewGroup) {
        this.zza = zzdqjVar;
        this.zzb = viewGroup;
    }

    @Override // com.google.android.gms.internal.ads.zzbln
    public final JSONObject zza() {
        return this.zza.zzo();
    }

    @Override // com.google.android.gms.internal.ads.zzbln
    public final JSONObject zzb() {
        return this.zza.zzp();
    }

    @Override // com.google.android.gms.internal.ads.zzbln
    public final void zzc() {
        zzdqj zzdqjVar = this.zza;
        zzfuv zzfuvVar = zzdpk.zza;
        Map zzm = zzdqjVar.zzm();
        if (zzm == null) {
            return;
        }
        int size = zzfuvVar.size();
        int r3 = 0;
        while (r3 < size) {
            int r4 = r3 + 1;
            if (zzm.get((String) zzfuvVar.get(r3)) != null) {
                this.zza.onClick(this.zzb);
                return;
            }
            r3 = r4;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbln
    public final void zzd(MotionEvent motionEvent) {
        this.zza.onTouch(null, motionEvent);
    }
}
