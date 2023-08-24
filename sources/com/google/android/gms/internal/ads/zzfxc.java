package com.google.android.gms.internal.ads;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfxc extends zzfxe {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfxc(zzfyx zzfyxVar, Class cls, zzfxv zzfxvVar) {
        super(zzfyxVar, cls, zzfxvVar);
    }

    @Override // com.google.android.gms.internal.ads.zzfxe
    final /* bridge */ /* synthetic */ Object zzf(Object obj, Throwable th) throws Exception {
        zzfxv zzfxvVar = (zzfxv) obj;
        zzfyx zza = zzfxvVar.zza(th);
        zzfsf.zzd(zza, "AsyncFunction.apply returned null instead of a Future. Did you mean to return immediateFuture(null)? %s", zzfxvVar);
        return zza;
    }

    @Override // com.google.android.gms.internal.ads.zzfxe
    final /* synthetic */ void zzg(Object obj) {
        zzt((zzfyx) obj);
    }
}
