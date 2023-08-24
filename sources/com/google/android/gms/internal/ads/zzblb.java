package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.ExoPlayer;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzblb {
    public static final zzbka zza = zzbka.zzd("gads:always_collect_trustless_token_at_native_side", false);
    public static final zzbka zzb = zzbka.zzd("gms:expose_token_for_gma:enabled", true);
    public static final zzbka zzc = zzbka.zzb("gads:timeout_for_trustless_token:millis", ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS);
    public static final zzbka zzd = zzbka.zzb("gads:cached_token:ttl_millis", 10800000);
}
