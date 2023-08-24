package com.google.android.gms.internal.ads;

import java.lang.ref.WeakReference;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzdnn implements zzbpq {
    private final WeakReference zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzdnn(zzdno zzdnoVar, zzdnm zzdnmVar) {
        this.zza = new WeakReference(zzdnoVar);
    }

    @Override // com.google.android.gms.internal.ads.zzbpq
    public final void zza(Object obj, Map map) {
        zzdno zzdnoVar = (zzdno) this.zza.get();
        if (zzdnoVar == null) {
            return;
        }
        zzdno.zzb(zzdnoVar).zza();
    }
}
