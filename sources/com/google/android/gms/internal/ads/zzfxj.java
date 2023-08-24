package com.google.android.gms.internal.ads;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfxj extends zzfxl {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfxj(zzfyx zzfyxVar, zzfxv zzfxvVar) {
        super(zzfyxVar, zzfxvVar);
    }

    @Override // com.google.android.gms.internal.ads.zzfxl
    final /* bridge */ /* synthetic */ Object zzf(Object obj, Object obj2) throws Exception {
        zzfxv zzfxvVar = (zzfxv) obj;
        zzfyx zza = zzfxvVar.zza(obj2);
        zzfsf.zzd(zza, "AsyncFunction.apply returned null instead of a Future. Did you mean to return immediateFuture(null)? %s", zzfxvVar);
        return zza;
    }

    @Override // com.google.android.gms.internal.ads.zzfxl
    final /* synthetic */ void zzg(Object obj) {
        zzt((zzfyx) obj);
    }
}
