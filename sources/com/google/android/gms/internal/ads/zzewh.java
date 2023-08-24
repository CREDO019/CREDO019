package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzewh implements zzeun {
    final zzfyy zza;
    final String zzb;
    final zzcfk zzc;

    public zzewh(zzcfk zzcfkVar, zzfyy zzfyyVar, String str, byte[] bArr) {
        this.zzc = zzcfkVar;
        this.zza = zzfyyVar;
        this.zzb = str;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final int zza() {
        return 47;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final zzfyx zzb() {
        final zzfyx zzi = zzfyo.zzi(null);
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzeQ)).booleanValue()) {
            zzi = zzfyo.zzi(null);
        }
        final zzfyx zzi2 = zzfyo.zzi(null);
        return zzfyo.zzd(zzi, zzi2).zza(new Callable() { // from class: com.google.android.gms.internal.ads.zzewg
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return new zzewi((String) zzfyx.this.get(), (String) zzi2.get());
            }
        }, zzcha.zza);
    }
}
