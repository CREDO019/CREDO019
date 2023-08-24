package com.google.android.gms.internal.ads;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdkp {
    private final List zza;
    private final zzfju zzb;
    private boolean zzc;

    public zzdkp(zzfcs zzfcsVar, zzfju zzfjuVar) {
        this.zza = zzfcsVar.zzq;
        this.zzb = zzfjuVar;
    }

    public final void zza() {
        if (this.zzc) {
            return;
        }
        this.zzb.zzd(this.zza);
        this.zzc = true;
    }
}
