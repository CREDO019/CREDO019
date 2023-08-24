package com.google.android.gms.internal.ads;

import android.os.Bundle;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzepw implements zzeum {
    private final com.google.android.gms.ads.internal.client.zzw zza;
    private final zzcgt zzb;
    private final boolean zzc;

    public zzepw(com.google.android.gms.ads.internal.client.zzw zzwVar, zzcgt zzcgtVar, boolean z) {
        this.zza = zzwVar;
        this.zzb = zzcgtVar;
        this.zzc = z;
    }

    @Override // com.google.android.gms.internal.ads.zzeum
    public final /* bridge */ /* synthetic */ void zzf(Object obj) {
        Bundle bundle = (Bundle) obj;
        if (this.zzb.zzc >= ((Integer) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzen)).intValue()) {
            bundle.putString("app_open_version", "2");
        }
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzeo)).booleanValue()) {
            bundle.putBoolean("app_switched", this.zzc);
        }
        com.google.android.gms.ads.internal.client.zzw zzwVar = this.zza;
        if (zzwVar != null) {
            int r0 = zzwVar.zza;
            if (r0 == 1) {
                bundle.putString("avo", "p");
            } else if (r0 == 2) {
                bundle.putString("avo", "l");
            }
        }
    }
}
