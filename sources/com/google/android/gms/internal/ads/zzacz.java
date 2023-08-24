package com.google.android.gms.internal.ads;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Locale;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzacz {
    public static final zzacx zza = new zzacx() { // from class: com.google.android.gms.internal.ads.zzacw
    };

    /* JADX WARN: Removed duplicated region for block: B:34:0x00af A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00b0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final com.google.android.gms.internal.ads.zzbq zza(byte[] r11, int r12, com.google.android.gms.internal.ads.zzacx r13, com.google.android.gms.internal.ads.zzaca r14) {
        /*
            Method dump skipped, instructions count: 284
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzacz.zza(byte[], int, com.google.android.gms.internal.ads.zzacx, com.google.android.gms.internal.ads.zzaca):com.google.android.gms.internal.ads.zzbq");
    }

    private static int zzb(int r1) {
        return (r1 == 0 || r1 == 3) ? 1 : 2;
    }

    private static int zzc(byte[] bArr, int r3, int r4) {
        int zzd = zzd(bArr, r3);
        if (r4 == 0 || r4 == 3) {
            return zzd;
        }
        while (true) {
            int length = bArr.length;
            if (zzd >= length - 1) {
                return length;
            }
            if ((zzd - r3) % 2 == 0 && bArr[zzd + 1] == 0) {
                return zzd;
            }
            zzd = zzd(bArr, zzd + 1);
        }
    }

    private static int zzd(byte[] bArr, int r2) {
        while (true) {
            int length = bArr.length;
            if (r2 >= length) {
                return length;
            }
            if (bArr[r2] == 0) {
                return r2;
            }
            r2++;
        }
    }

    private static int zze(zzed zzedVar, int r6) {
        byte[] zzH = zzedVar.zzH();
        int zzc = zzedVar.zzc();
        int r1 = zzc;
        while (true) {
            int r2 = r1 + 1;
            if (r2 >= zzc + r6) {
                return r6;
            }
            if ((zzH[r1] & 255) == 255 && zzH[r2] == 0) {
                System.arraycopy(zzH, r1 + 2, zzH, r2, (r6 - (r1 - zzc)) - 2);
                r6--;
            }
            r1 = r2;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:140:0x0273, code lost:
        if (r9 == 67) goto L121;
     */
    /* JADX WARN: Not initialized variable reg: 22, insn: 0x0542: MOVE  (r2 I:??[OBJECT, ARRAY]) = (r22 I:??[OBJECT, ARRAY]), block:B:240:0x0540 */
    /* JADX WARN: Removed duplicated region for block: B:214:0x0486  */
    /* JADX WARN: Removed duplicated region for block: B:225:0x04f4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static com.google.android.gms.internal.ads.zzada zzf(int r34, com.google.android.gms.internal.ads.zzed r35, boolean r36, int r37, com.google.android.gms.internal.ads.zzacx r38) {
        /*
            Method dump skipped, instructions count: 1378
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzacz.zzf(int, com.google.android.gms.internal.ads.zzed, boolean, int, com.google.android.gms.internal.ads.zzacx):com.google.android.gms.internal.ads.zzada");
    }

    private static String zzg(byte[] bArr, int r2, int r3, String str) throws UnsupportedEncodingException {
        return (r3 <= r2 || r3 > bArr.length) ? "" : new String(bArr, r2, r3 - r2, str);
    }

    private static String zzh(int r1) {
        return r1 != 1 ? r1 != 2 ? r1 != 3 ? "ISO-8859-1" : "UTF-8" : "UTF-16BE" : "UTF-16";
    }

    private static String zzi(int r5, int r6, int r7, int r8, int r9) {
        return r5 == 2 ? String.format(Locale.US, "%c%c%c", Integer.valueOf(r6), Integer.valueOf(r7), Integer.valueOf(r8)) : String.format(Locale.US, "%c%c%c%c", Integer.valueOf(r6), Integer.valueOf(r7), Integer.valueOf(r8), Integer.valueOf(r9));
    }

    /* JADX WARN: Code restructure failed: missing block: B:38:0x0085, code lost:
        if ((r10 & 128) != 0) goto L31;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static boolean zzj(com.google.android.gms.internal.ads.zzed r19, int r20, int r21, boolean r22) {
        /*
            r1 = r19
            r0 = r20
            int r2 = r19.zzc()
        L8:
            int r3 = r19.zza()     // Catch: java.lang.Throwable -> Lae
            r4 = 1
            r5 = r21
            if (r3 < r5) goto Laa
            r3 = 3
            r6 = 0
            if (r0 < r3) goto L22
            int r7 = r19.zze()     // Catch: java.lang.Throwable -> Lae
            long r8 = r19.zzs()     // Catch: java.lang.Throwable -> Lae
            int r10 = r19.zzo()     // Catch: java.lang.Throwable -> Lae
            goto L2c
        L22:
            int r7 = r19.zzm()     // Catch: java.lang.Throwable -> Lae
            int r8 = r19.zzm()     // Catch: java.lang.Throwable -> Lae
            long r8 = (long) r8
            r10 = 0
        L2c:
            r11 = 0
            if (r7 != 0) goto L3b
            int r7 = (r8 > r11 ? 1 : (r8 == r11 ? 0 : -1))
            if (r7 != 0) goto L3b
            if (r10 == 0) goto L37
            goto L3b
        L37:
            r1.zzF(r2)
            return r4
        L3b:
            r7 = 4
            if (r0 != r7) goto L6a
            if (r22 != 0) goto L6a
            r13 = 8421504(0x808080, double:4.160776E-317)
            long r13 = r13 & r8
            int r15 = (r13 > r11 ? 1 : (r13 == r11 ? 0 : -1))
            if (r15 == 0) goto L4c
            r1.zzF(r2)
            return r6
        L4c:
            r11 = 255(0xff, double:1.26E-321)
            long r13 = r8 & r11
            r15 = 8
            long r15 = r8 >> r15
            long r15 = r15 & r11
            r17 = 7
            long r15 = r15 << r17
            long r13 = r13 | r15
            r15 = 16
            long r15 = r8 >> r15
            long r11 = r11 & r15
            r15 = 14
            long r11 = r11 << r15
            long r11 = r11 | r13
            r13 = 24
            long r8 = r8 >> r13
            r13 = 21
            long r8 = r8 << r13
            long r8 = r8 | r11
        L6a:
            if (r0 != r7) goto L7a
            r3 = r10 & 64
            if (r3 == 0) goto L71
            goto L72
        L71:
            r4 = 0
        L72:
            r3 = r10 & 1
            r18 = r4
            r4 = r3
            r3 = r18
            goto L8a
        L7a:
            if (r0 != r3) goto L88
            r3 = r10 & 32
            if (r3 == 0) goto L82
            r3 = 1
            goto L83
        L82:
            r3 = 0
        L83:
            r7 = r10 & 128(0x80, float:1.794E-43)
            if (r7 == 0) goto L89
            goto L8a
        L88:
            r3 = 0
        L89:
            r4 = 0
        L8a:
            if (r4 == 0) goto L8e
            int r3 = r3 + 4
        L8e:
            long r3 = (long) r3
            int r7 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1))
            if (r7 >= 0) goto L97
            r1.zzF(r2)
            return r6
        L97:
            int r3 = r19.zza()     // Catch: java.lang.Throwable -> Lae
            long r3 = (long) r3
            int r7 = (r3 > r8 ? 1 : (r3 == r8 ? 0 : -1))
            if (r7 >= 0) goto La4
            r1.zzF(r2)
            return r6
        La4:
            int r3 = (int) r8
            r1.zzG(r3)     // Catch: java.lang.Throwable -> Lae
            goto L8
        Laa:
            r1.zzF(r2)
            return r4
        Lae:
            r0 = move-exception
            r1.zzF(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzacz.zzj(com.google.android.gms.internal.ads.zzed, int, int, boolean):boolean");
    }

    private static byte[] zzk(byte[] bArr, int r1, int r2) {
        if (r2 <= r1) {
            return zzel.zzf;
        }
        return Arrays.copyOfRange(bArr, r1, r2);
    }
}
