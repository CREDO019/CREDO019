package com.google.android.gms.internal.ads;

import android.os.Bundle;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzepd implements zzeun {
    private final zzfyx zza;
    private final Executor zzb;

    public zzepd(zzfyx zzfyxVar, Executor executor) {
        this.zza = zzfyxVar;
        this.zzb = executor;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final int zza() {
        return 6;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final zzfyx zzb() {
        return zzfyo.zzn(this.zza, new zzfxv() { // from class: com.google.android.gms.internal.ads.zzepc
            @Override // com.google.android.gms.internal.ads.zzfxv
            public final zzfyx zza(Object obj) {
                final String str = (String) obj;
                return zzfyo.zzi(new zzeum() { // from class: com.google.android.gms.internal.ads.zzepb
                    @Override // com.google.android.gms.internal.ads.zzeum
                    public final void zzf(Object obj2) {
                        ((Bundle) obj2).putString("ms", str);
                    }
                });
            }
        }, this.zzb);
    }
}
