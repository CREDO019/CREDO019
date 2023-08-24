package com.google.android.gms.internal.ads;

import java.util.List;
import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfgx {
    final /* synthetic */ zzfhh zza;
    private final Object zzb;
    private final List zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzfgx(zzfhh zzfhhVar, Object obj, List list, zzfgw zzfgwVar) {
        this.zza = zzfhhVar;
        this.zzb = obj;
        this.zzc = list;
    }

    public final zzfhg zza(Callable callable) {
        zzfyy zzfyyVar;
        zzfyn zzc = zzfyo.zzc(this.zzc);
        zzfyx zza = zzc.zza(new Callable() { // from class: com.google.android.gms.internal.ads.zzfgv
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return null;
            }
        }, zzcha.zzf);
        zzfhh zzfhhVar = this.zza;
        Object obj = this.zzb;
        List list = this.zzc;
        zzfyyVar = zzfhhVar.zzb;
        return new zzfhg(zzfhhVar, obj, zza, list, zzc.zza(callable, zzfyyVar));
    }
}
