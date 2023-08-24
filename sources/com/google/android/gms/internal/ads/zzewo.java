package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzewo implements zzeun {
    final ScheduledExecutorService zza;
    final Context zzb;
    final zzbze zzc;

    public zzewo(zzbze zzbzeVar, ScheduledExecutorService scheduledExecutorService, Context context, byte[] bArr) {
        this.zzc = zzbzeVar;
        this.zza = scheduledExecutorService;
        this.zzb = context;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final int zza() {
        return 49;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final zzfyx zzb() {
        return zzfyo.zzm(zzfyo.zzo(zzfyo.zzi(new Bundle()), ((Long) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzdg)).longValue(), TimeUnit.MILLISECONDS, this.zza), new zzfru() { // from class: com.google.android.gms.internal.ads.zzewn
            @Override // com.google.android.gms.internal.ads.zzfru
            public final Object apply(Object obj) {
                return new zzewp((Bundle) obj);
            }
        }, zzcha.zza);
    }
}
