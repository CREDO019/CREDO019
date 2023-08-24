package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzeoy implements zzeun {
    private final zzfyy zza;
    private final zzfdn zzb;
    private final zzfdz zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzeoy(zzfyy zzfyyVar, zzfdn zzfdnVar, zzfdz zzfdzVar) {
        this.zza = zzfyyVar;
        this.zzb = zzfdnVar;
        this.zzc = zzfdzVar;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final int zza() {
        return 5;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final zzfyx zzb() {
        return this.zza.zzb(new Callable() { // from class: com.google.android.gms.internal.ads.zzeox
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return zzeoy.this.zzc();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzeoz zzc() throws Exception {
        String str = null;
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzgj)).booleanValue() && "requester_type_2".equals(com.google.android.gms.ads.nonagon.signalgeneration.zzf.zzb(this.zzb.zzd))) {
            str = zzfdz.zza();
        }
        return new zzeoz(str);
    }
}
