package com.google.android.gms.internal.ads;

import android.net.Uri;
import androidx.browser.trusted.sharing.ShareTarget;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfa {
    public final Uri zza;
    public final int zzb;
    public final byte[] zzc;
    public final Map zzd;
    @Deprecated
    public final long zze;
    public final long zzf;
    public final long zzg;
    public final String zzh;
    public final int zzi;

    static {
        zzbh.zzb("media3.datasource");
    }

    private zzfa(Uri uri, long j, int r16, byte[] bArr, Map map, long j2, long j3, String str, int r24, Object obj) {
        long j4 = j + j2;
        boolean z = false;
        zzdd.zzd(j4 >= 0);
        zzdd.zzd(j2 >= 0);
        long j5 = -1;
        if (j3 > 0) {
            j5 = j3;
        } else if (j3 != -1) {
            j5 = j3;
            zzdd.zzd(z);
            this.zza = uri;
            this.zzb = 1;
            this.zzc = null;
            this.zzd = Collections.unmodifiableMap(new HashMap(map));
            this.zzf = j2;
            this.zze = j4;
            this.zzg = j5;
            this.zzh = null;
            this.zzi = r24;
        }
        z = true;
        zzdd.zzd(z);
        this.zza = uri;
        this.zzb = 1;
        this.zzc = null;
        this.zzd = Collections.unmodifiableMap(new HashMap(map));
        this.zzf = j2;
        this.zze = j4;
        this.zzg = j5;
        this.zzh = null;
        this.zzi = r24;
    }

    public static String zza(int r0) {
        return ShareTarget.METHOD_GET;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zza);
        long j = this.zzf;
        long j2 = this.zzg;
        int r5 = this.zzi;
        return "DataSpec[" + zza(1) + " " + valueOf + ", " + j + ", " + j2 + ", null, " + r5 + "]";
    }

    public final boolean zzb(int r2) {
        return (this.zzi & r2) == r2;
    }

    @Deprecated
    public zzfa(Uri uri, byte[] bArr, long j, long j2, long j3, String str, int r24) {
        this(uri, j - j2, 1, null, Collections.emptyMap(), j2, j3, null, r24, null);
    }
}
