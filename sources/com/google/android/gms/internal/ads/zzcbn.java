package com.google.android.gms.internal.ads;

import com.google.android.gms.common.internal.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcbn extends zzcbp {
    private final String zza;
    private final int zzb;

    public zzcbn(String str, int r2) {
        this.zza = str;
        this.zzb = r2;
    }

    public final boolean equals(Object obj) {
        if (obj != null && (obj instanceof zzcbn)) {
            zzcbn zzcbnVar = (zzcbn) obj;
            if (Objects.equal(this.zza, zzcbnVar.zza) && Objects.equal(Integer.valueOf(this.zzb), Integer.valueOf(zzcbnVar.zzb))) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.gms.internal.ads.zzcbq
    public final int zzb() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.ads.zzcbq
    public final String zzc() {
        return this.zza;
    }
}
