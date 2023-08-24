package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzeqz implements zzeun {
    private final zzfyy zza;
    private final zzdvo zzb;
    private final String zzc;
    private final zzfdn zzd;

    public zzeqz(zzfyy zzfyyVar, zzdvo zzdvoVar, zzfdn zzfdnVar, String str) {
        this.zza = zzfyyVar;
        this.zzb = zzdvoVar;
        this.zzd = zzfdnVar;
        this.zzc = str;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final int zza() {
        return 17;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final zzfyx zzb() {
        return this.zza.zzb(new Callable() { // from class: com.google.android.gms.internal.ads.zzeqy
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return zzeqz.this.zzc();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzera zzc() throws Exception {
        return new zzera(this.zzb.zzb(this.zzd.zzf, this.zzc), this.zzb.zza());
    }
}
