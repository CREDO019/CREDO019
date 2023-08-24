package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzepv implements zzeun {
    private final zzfyy zza;
    private final zzfdn zzb;
    private final zzcgt zzc;
    private final zzcga zzd;

    public zzepv(zzfyy zzfyyVar, zzfdn zzfdnVar, zzcgt zzcgtVar, zzcga zzcgaVar) {
        this.zza = zzfyyVar;
        this.zzb = zzfdnVar;
        this.zzc = zzcgtVar;
        this.zzd = zzcgaVar;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final int zza() {
        return 9;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final zzfyx zzb() {
        return this.zza.zzb(new Callable() { // from class: com.google.android.gms.internal.ads.zzepu
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return zzepv.this.zzc();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzepw zzc() throws Exception {
        return new zzepw(this.zzb.zzj, this.zzc, this.zzd.zzj());
    }
}
