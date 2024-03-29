package com.google.android.gms.internal.ads;

import java.lang.ref.WeakReference;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdnl implements zzbpq {
    private final WeakReference zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzdnl(zzdno zzdnoVar, zzdnk zzdnkVar) {
        this.zza = new WeakReference(zzdnoVar);
    }

    @Override // com.google.android.gms.internal.ads.zzbpq
    public final void zza(Object obj, Map map) {
        zzdcw zzdcwVar;
        zzdkj zzdkjVar;
        zzdno zzdnoVar = (zzdno) this.zza.get();
        if (zzdnoVar == null) {
            return;
        }
        zzdcwVar = zzdnoVar.zzh;
        zzdcwVar.onAdClicked();
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zziq)).booleanValue()) {
            zzdkjVar = zzdnoVar.zzi;
            zzdkjVar.zzq();
        }
    }
}
