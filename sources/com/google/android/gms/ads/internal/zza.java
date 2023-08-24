package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.ads.zzcid;
import com.google.android.gms.internal.ads.zzcio;
import com.google.android.gms.internal.ads.zzckl;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zza {
    public final zzcid zza;
    public final zzckl zzb;

    public zza(zzckl zzcklVar, zzcid zzcidVar, byte[] bArr) {
        this.zzb = zzcklVar;
        this.zza = zzcidVar;
    }

    public static zza zza() {
        return new zza(new zzckl(), new zzcio(), null);
    }
}
