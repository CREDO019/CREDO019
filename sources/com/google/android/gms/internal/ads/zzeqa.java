package com.google.android.gms.internal.ads;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzeqa implements zzeun {
    private final Executor zza;
    private final zzcfw zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzeqa(Executor executor, zzcfw zzcfwVar) {
        this.zza = executor;
        this.zzb = zzcfwVar;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final int zza() {
        return 10;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final zzfyx zzb() {
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzcj)).booleanValue()) {
            return zzfyo.zzi(null);
        }
        return zzfyo.zzm(this.zzb.zzj(), new zzfru() { // from class: com.google.android.gms.internal.ads.zzepy
            @Override // com.google.android.gms.internal.ads.zzfru
            public final Object apply(Object obj) {
                final ArrayList arrayList = (ArrayList) obj;
                if (arrayList.isEmpty()) {
                    return null;
                }
                return new zzeum() { // from class: com.google.android.gms.internal.ads.zzepz
                    @Override // com.google.android.gms.internal.ads.zzeum
                    public final void zzf(Object obj2) {
                        ((Bundle) obj2).putStringArrayList("android_permissions", arrayList);
                    }
                };
            }
        }, this.zza);
    }
}
