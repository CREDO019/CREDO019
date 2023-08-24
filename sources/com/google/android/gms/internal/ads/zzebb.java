package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzebb implements zzgur {
    private final zzgve zza;
    private final zzgve zzb;
    private final zzgve zzc;

    public zzebb(zzgve zzgveVar, zzgve zzgveVar2, zzgve zzgveVar3) {
        this.zza = zzgveVar;
        this.zzb = zzgveVar2;
        this.zzc = zzgveVar3;
    }

    @Override // com.google.android.gms.internal.ads.zzgve
    public final /* bridge */ /* synthetic */ Object zzb() {
        final zzapb zzapbVar = (zzapb) this.zza.zzb();
        final Context zza = ((zzcoq) this.zzb).zza();
        zzfyy zzfyyVar = zzcha.zza;
        zzguz.zzb(zzfyyVar);
        zzfyx zzb = zzfyyVar.zzb(new Callable() { // from class: com.google.android.gms.internal.ads.zzeay
            @Override // java.util.concurrent.Callable
            public final Object call() {
                zzapb zzapbVar2 = zzapb.this;
                return zzapbVar2.zzc().zzg(zza);
            }
        });
        zzguz.zzb(zzb);
        return zzb;
    }
}
