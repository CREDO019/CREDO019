package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzeev {
    private final zzeer zza;
    private final zzfyy zzb;

    public zzeev(zzeer zzeerVar, zzfyy zzfyyVar) {
        this.zza = zzeerVar;
        this.zzb = zzfyyVar;
    }

    public final void zza(zzfgs zzfgsVar) {
        zzfyy zzfyyVar = this.zzb;
        final zzeer zzeerVar = this.zza;
        zzfyo.zzr(zzfyyVar.zzb(new Callable() { // from class: com.google.android.gms.internal.ads.zzeet
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return zzeer.this.getWritableDatabase();
            }
        }), new zzeeu(this, zzfgsVar), this.zzb);
    }
}
