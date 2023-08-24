package com.google.android.gms.internal.ads;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
final class zzbzu extends zzbzo {
    final /* synthetic */ List zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbzu(zzbzx zzbzxVar, List list) {
        this.zza = list;
    }

    @Override // com.google.android.gms.internal.ads.zzbzp
    public final void zze(String str) {
        zzcgn.zzg("Error recording impression urls: ".concat(String.valueOf(str)));
    }

    @Override // com.google.android.gms.internal.ads.zzbzp
    public final void zzf(List list) {
        zzcgn.zzi("Recorded impression urls: ".concat(this.zza.toString()));
    }
}
