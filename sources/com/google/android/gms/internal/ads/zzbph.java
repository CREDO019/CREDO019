package com.google.android.gms.internal.ads;

import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzbph implements zzbpq {
    @Override // com.google.android.gms.internal.ads.zzbpq
    public final /* bridge */ /* synthetic */ void zza(Object obj, Map map) {
        zzcmn zzcmnVar = (zzcmn) obj;
        if (zzcmnVar.zzL() != null) {
            zzcmnVar.zzL().zza();
        }
        com.google.android.gms.ads.internal.overlay.zzl zzN = zzcmnVar.zzN();
        if (zzN != null) {
            zzN.zzb();
            return;
        }
        com.google.android.gms.ads.internal.overlay.zzl zzO = zzcmnVar.zzO();
        if (zzO != null) {
            zzO.zzb();
        } else {
            com.google.android.gms.ads.internal.util.zze.zzj("A GMSG tried to close something that wasn't an overlay.");
        }
    }
}
