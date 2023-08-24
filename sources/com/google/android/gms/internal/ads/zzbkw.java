package com.google.android.gms.internal.ads;

import androidx.work.WorkRequest;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbkw {
    public static final zzbka zza = zzbka.zzb("gads:dynamite_load:fail:sample_rate", WorkRequest.MIN_BACKOFF_MILLIS);
    public static final zzbka zzb = zzbka.zzd("gads:report_dynamite_crash_in_background_thread", false);
    public static final zzbka zzc = zzbka.zzc("gads:public_beta:traffic_multiplier", "1.0");
    public static final zzbka zzd = zzbka.zzc("gads:sdk_crash_report_class_prefix", "com.google.");
    public static final zzbka zze = zzbka.zzd("gads:sdk_crash_report_enabled", false);
    public static final zzbka zzf = zzbka.zzd("gads:sdk_crash_report_full_stacktrace", false);
    public static final zzbka zzg = zzbka.zza("gads:trapped_exception_sample_rate", 0.01d);
}
