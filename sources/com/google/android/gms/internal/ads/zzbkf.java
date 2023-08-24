package com.google.android.gms.internal.ads;

import androidx.work.WorkRequest;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbkf {
    public static final zzbka zza = zzbkb.zzf("gads:consent:gmscore:dsid:enabled", true);
    public static final zzbka zzb = zzbkb.zzf("gads:consent:gmscore:lat:enabled", true);
    public static final zzbka zzc = new zzbkb("gads:consent:gmscore:backend_url", "https://adservice.google.com/getconfig/pubvendors", 4);
    public static final zzbka zzd = new zzbkb("gads:consent:gmscore:time_out", Long.valueOf((long) WorkRequest.MIN_BACKOFF_MILLIS), 2);
    public static final zzbka zze = zzbkb.zzf("gads:consent:gmscore:enabled", true);
}
