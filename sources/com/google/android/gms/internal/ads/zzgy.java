package com.google.android.gms.internal.ads;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgy extends zzbw {
    public static final zzn zzd = new zzn() { // from class: com.google.android.gms.internal.ads.zzgx
    };
    public final int zze;
    public final String zzf;
    public final int zzg;
    public final zzaf zzh;
    public final int zzi;
    public final zzbn zzj;
    final boolean zzk;

    private zzgy(int r11, Throwable th, int r13) {
        this(r11, th, null, r13, null, -1, null, 4, false);
    }

    public static zzgy zzb(Throwable th, String str, int r13, zzaf zzafVar, int r15, boolean z, int r17) {
        return new zzgy(1, th, null, r17, str, r13, zzafVar, zzafVar == null ? 4 : r15, z);
    }

    public static zzgy zzc(IOException iOException, int r3) {
        return new zzgy(0, iOException, r3);
    }

    public static zzgy zzd(RuntimeException runtimeException, int r3) {
        return new zzgy(2, runtimeException, r3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzgy zza(zzbn zzbnVar) {
        String message = getMessage();
        int r0 = zzel.zza;
        return new zzgy(message, getCause(), this.zzb, this.zze, this.zzf, this.zzg, this.zzh, this.zzi, zzbnVar, this.zzc, this.zzk);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private zzgy(int r14, java.lang.Throwable r15, java.lang.String r16, int r17, java.lang.String r18, int r19, com.google.android.gms.internal.ads.zzaf r20, int r21, boolean r22) {
        /*
            r13 = this;
            r4 = r14
            if (r4 == 0) goto L3e
            r0 = 1
            if (r4 == r0) goto Ld
            java.lang.String r0 = "Unexpected runtime error"
            r5 = r18
            r6 = r19
            goto L44
        Ld:
            java.lang.String r0 = java.lang.String.valueOf(r20)
            java.lang.String r1 = com.google.android.gms.internal.ads.zzel.zzM(r21)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r5 = r18
            r2.append(r5)
            java.lang.String r3 = " error, index="
            r2.append(r3)
            r6 = r19
            r2.append(r6)
            java.lang.String r3 = ", format="
            r2.append(r3)
            r2.append(r0)
            java.lang.String r0 = ", format_supported="
            r2.append(r0)
            r2.append(r1)
            java.lang.String r0 = r2.toString()
            goto L44
        L3e:
            r5 = r18
            r6 = r19
            java.lang.String r0 = "Source error"
        L44:
            r1 = 0
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 != 0) goto L51
            java.lang.String r1 = ": null"
            java.lang.String r0 = r0.concat(r1)
        L51:
            r1 = r0
            r9 = 0
            long r10 = android.os.SystemClock.elapsedRealtime()
            r0 = r13
            r2 = r15
            r3 = r17
            r4 = r14
            r5 = r18
            r6 = r19
            r7 = r20
            r8 = r21
            r12 = r22
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r12)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzgy.<init>(int, java.lang.Throwable, java.lang.String, int, java.lang.String, int, com.google.android.gms.internal.ads.zzaf, int, boolean):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x001f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private zzgy(java.lang.String r9, java.lang.Throwable r10, int r11, int r12, java.lang.String r13, int r14, com.google.android.gms.internal.ads.zzaf r15, int r16, com.google.android.gms.internal.ads.zzbn r17, long r18, boolean r20) {
        /*
            r8 = this;
            r6 = r8
            r7 = r20
            r0 = r8
            r1 = r9
            r2 = r10
            r3 = r11
            r4 = r18
            r0.<init>(r1, r2, r3, r4)
            r0 = 0
            r1 = 1
            if (r7 == 0) goto L17
            r2 = r12
            if (r2 != r1) goto L15
            r2 = 1
            goto L18
        L15:
            r3 = 0
            goto L19
        L17:
            r2 = r12
        L18:
            r3 = 1
        L19:
            com.google.android.gms.internal.ads.zzdd.zzd(r3)
            if (r10 != 0) goto L1f
            goto L20
        L1f:
            r0 = 1
        L20:
            com.google.android.gms.internal.ads.zzdd.zzd(r0)
            r6.zze = r2
            r0 = r13
            r6.zzf = r0
            r0 = r14
            r6.zzg = r0
            r0 = r15
            r6.zzh = r0
            r0 = r16
            r6.zzi = r0
            r0 = r17
            r6.zzj = r0
            r6.zzk = r7
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzgy.<init>(java.lang.String, java.lang.Throwable, int, int, java.lang.String, int, com.google.android.gms.internal.ads.zzaf, int, com.google.android.gms.internal.ads.zzbn, long, boolean):void");
    }
}