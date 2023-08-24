package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Callable;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzevl implements zzeun {
    private final Context zza;
    private final String zzb;
    private final zzfyy zzc;

    public zzevl(zzcbc zzcbcVar, Context context, String str, zzfyy zzfyyVar) {
        this.zza = context;
        this.zzb = str;
        this.zzc = zzfyyVar;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final int zza() {
        return 42;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final zzfyx zzb() {
        return this.zzc.zzb(new Callable() { // from class: com.google.android.gms.internal.ads.zzevk
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return new zzevm(new JSONObject());
            }
        });
    }
}
