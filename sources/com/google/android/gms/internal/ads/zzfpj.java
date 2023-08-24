package com.google.android.gms.internal.ads;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfpj extends zzfxf {
    Object zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfpj(Object obj) {
        this.zza = obj;
    }

    @Override // com.google.android.gms.internal.ads.zzfxf
    public final String zza() {
        Object obj = this.zza;
        return obj == null ? "" : obj.toString();
    }

    @Override // com.google.android.gms.internal.ads.zzfxf
    protected final void zzb() {
        this.zza = null;
    }

    @Override // com.google.android.gms.internal.ads.zzfxf
    public final boolean zzd(Object obj) {
        return super.zzd(obj);
    }

    @Override // com.google.android.gms.internal.ads.zzfxf
    public final boolean zze(Throwable th) {
        return super.zze(th);
    }
}
