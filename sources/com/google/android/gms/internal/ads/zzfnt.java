package com.google.android.gms.internal.ads;

import java.io.File;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfnt {
    private final zzaru zza;
    private final File zzb;
    private final File zzc;
    private final File zzd;
    private byte[] zze;

    public zzfnt(zzaru zzaruVar, File file, File file2, File file3) {
        this.zza = zzaruVar;
        this.zzb = file;
        this.zzc = file3;
        this.zzd = file2;
    }

    public final zzaru zza() {
        return this.zza;
    }

    public final File zzb() {
        return this.zzc;
    }

    public final File zzc() {
        return this.zzb;
    }

    public final boolean zzd(long j) {
        return this.zza.zzc() - (System.currentTimeMillis() / 1000) < 3600;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x002b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x002c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final byte[] zze() {
        /*
            r3 = this;
            byte[] r0 = r3.zze
            r1 = 0
            if (r0 != 0) goto L27
            java.io.File r0 = r3.zzd
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L1b java.io.IOException -> L20
            r2.<init>(r0)     // Catch: java.lang.Throwable -> L1b java.io.IOException -> L20
            com.google.android.gms.internal.ads.zzgnf r0 = com.google.android.gms.internal.ads.zzgnf.zzy(r2)     // Catch: java.lang.Throwable -> L18 java.io.IOException -> L21
            byte[] r0 = r0.zzE()     // Catch: java.lang.Throwable -> L18 java.io.IOException -> L21
            com.google.android.gms.common.util.IOUtils.closeQuietly(r2)
            goto L25
        L18:
            r0 = move-exception
            r1 = r2
            goto L1c
        L1b:
            r0 = move-exception
        L1c:
            com.google.android.gms.common.util.IOUtils.closeQuietly(r1)
            throw r0
        L20:
            r2 = r1
        L21:
            com.google.android.gms.common.util.IOUtils.closeQuietly(r2)
            r0 = r1
        L25:
            r3.zze = r0
        L27:
            byte[] r0 = r3.zze
            if (r0 != 0) goto L2c
            return r1
        L2c:
            int r1 = r0.length
            byte[] r0 = java.util.Arrays.copyOf(r0, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzfnt.zze():byte[]");
    }
}
