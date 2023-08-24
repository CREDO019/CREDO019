package com.google.android.gms.internal.ads;

import java.lang.ref.WeakReference;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzdtk implements zzbpq {
    final /* synthetic */ zzdtl zza;
    private final WeakReference zzb;
    private final String zzc;
    private final zzbpq zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzdtk(zzdtl zzdtlVar, WeakReference weakReference, String str, zzbpq zzbpqVar, zzdtj zzdtjVar) {
        this.zza = zzdtlVar;
        this.zzb = weakReference;
        this.zzc = str;
        this.zzd = zzbpqVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbpq
    public final void zza(Object obj, Map map) {
        Object obj2 = this.zzb.get();
        if (obj2 == null) {
            this.zza.zzk(this.zzc, this);
        } else {
            this.zzd.zza(obj2, map);
        }
    }
}
