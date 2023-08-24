package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Callable;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzewa implements zzeun {
    final zzfyy zza;
    final Context zzb;
    final zzbea zzc;

    public zzewa(zzbea zzbeaVar, zzfyy zzfyyVar, Context context, byte[] bArr) {
        this.zzc = zzbeaVar;
        this.zza = zzfyyVar;
        this.zzb = context;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final int zza() {
        return 45;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final zzfyx zzb() {
        return this.zza.zzb(new Callable() { // from class: com.google.android.gms.internal.ads.zzevz
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return new zzewb(new JSONObject());
            }
        });
    }
}
