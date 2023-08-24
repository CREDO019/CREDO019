package com.google.android.gms.ads;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class AdValue {
    private final int zza;
    private final String zzb;
    private final long zzc;

    /* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface PrecisionType {
        public static final int ESTIMATED = 1;
        public static final int PRECISE = 3;
        public static final int PUBLISHER_PROVIDED = 2;
        public static final int UNKNOWN = 0;
    }

    private AdValue(int r1, String str, long j) {
        this.zza = r1;
        this.zzb = str;
        this.zzc = j;
    }

    public static AdValue zza(int r1, String str, long j) {
        return new AdValue(r1, str, j);
    }

    public String getCurrencyCode() {
        return this.zzb;
    }

    public int getPrecisionType() {
        return this.zza;
    }

    public long getValueMicros() {
        return this.zzc;
    }
}
