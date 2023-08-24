package com.google.android.gms.internal.ads;

import android.content.Context;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
@ParametersAreNonnullByDefault
/* loaded from: classes2.dex */
public final class zzbuf {
    static final com.google.android.gms.ads.internal.util.zzbb zza = new zzbud();
    static final com.google.android.gms.ads.internal.util.zzbb zzb = new zzbue();
    private final zzbtr zzc;

    public zzbuf(Context context, zzcgt zzcgtVar, String str, @Nullable zzfje zzfjeVar) {
        this.zzc = new zzbtr(context, zzcgtVar, str, zza, zzb, zzfjeVar);
    }

    public final zzbtv zza(String str, zzbty zzbtyVar, zzbtx zzbtxVar) {
        return new zzbuj(this.zzc, str, zzbtyVar, zzbtxVar);
    }

    public final zzbuo zzb() {
        return new zzbuo(this.zzc);
    }
}
