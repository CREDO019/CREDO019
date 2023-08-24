package com.google.android.gms.internal.ads;

import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzaac {
    public static final byte[] zza = {0, 0, 0, 1};
    public static final float[] zzb = {1.0f, 1.0f, 1.0909091f, 0.90909094f, 1.4545455f, 1.2121212f, 2.1818182f, 1.8181819f, 2.909091f, 2.4242425f, 1.6363636f, 1.3636364f, 1.939394f, 1.6161616f, 1.3333334f, 1.5f, 2.0f};
    private static final Object zzc = new Object();
    private static int[] zzd = new int[10];

    public static int zza(byte[] bArr, int r9, int r10, boolean[] zArr) {
        int r0 = r10 - r9;
        zzdd.zzf(r0 >= 0);
        if (r0 == 0) {
            return r10;
        }
        if (zArr[0]) {
            zze(zArr);
            return r9 - 3;
        } else if (r0 <= 1 || !zArr[1] || bArr[r9] != 1) {
            if (r0 <= 2 || !zArr[2] || bArr[r9] != 0 || bArr[r9 + 1] != 1) {
                int r4 = r10 - 1;
                int r92 = r9 + 2;
                while (r92 < r4) {
                    byte b = bArr[r92];
                    if ((b & 254) == 0) {
                        int r6 = r92 - 2;
                        if (bArr[r6] == 0 && bArr[r92 - 1] == 0 && b == 1) {
                            zze(zArr);
                            return r6;
                        }
                        r92 = r6;
                    }
                    r92 += 3;
                }
                zArr[0] = r0 <= 2 ? !(r0 != 2 ? !(zArr[1] && bArr[r4] == 1) : !(zArr[2] && bArr[r10 + (-2)] == 0 && bArr[r4] == 1)) : bArr[r10 + (-3)] == 0 && bArr[r10 + (-2)] == 0 && bArr[r4] == 1;
                zArr[1] = r0 <= 1 ? zArr[2] && bArr[r4] == 0 : bArr[r10 + (-2)] == 0 && bArr[r4] == 0;
                zArr[2] = bArr[r4] == 0;
                return r10;
            }
            zze(zArr);
            return r9 - 1;
        } else {
            zze(zArr);
            return r9 - 2;
        }
    }

    public static int zzb(byte[] bArr, int r9) {
        int r92;
        synchronized (zzc) {
            int r2 = 0;
            int r3 = 0;
            while (r2 < r9) {
                while (true) {
                    if (r2 >= r9 - 2) {
                        r2 = r9;
                        break;
                    }
                    try {
                        if (bArr[r2] == 0 && bArr[r2 + 1] == 0 && bArr[r2 + 2] == 3) {
                            break;
                        }
                        r2++;
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                if (r2 < r9) {
                    int[] r4 = zzd;
                    int length = r4.length;
                    if (length <= r3) {
                        zzd = Arrays.copyOf(r4, length + length);
                    }
                    zzd[r3] = r2;
                    r2 += 3;
                    r3++;
                }
            }
            r92 = r9 - r3;
            int r42 = 0;
            int r5 = 0;
            for (int r22 = 0; r22 < r3; r22++) {
                int r6 = zzd[r22] - r42;
                System.arraycopy(bArr, r42, bArr, r5, r6);
                int r52 = r5 + r6;
                int r7 = r52 + 1;
                bArr[r52] = 0;
                r5 = r7 + 1;
                bArr[r7] = 0;
                r42 += r6 + 3;
            }
            System.arraycopy(bArr, r42, bArr, r5, r92 - r5);
        }
        return r92;
    }

    public static zzaaa zzc(byte[] bArr, int r2, int r3) {
        zzaae zzaaeVar = new zzaae(bArr, 4, r3);
        int zzc2 = zzaaeVar.zzc();
        int zzc3 = zzaaeVar.zzc();
        zzaaeVar.zzd();
        return new zzaaa(zzc2, zzc3, zzaaeVar.zzf());
    }

    /* JADX WARN: Removed duplicated region for block: B:51:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0107  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0117  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x014d  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x015d  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x016e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.google.android.gms.internal.ads.zzaab zzd(byte[] r23, int r24, int r25) {
        /*
            Method dump skipped, instructions count: 414
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaac.zzd(byte[], int, int):com.google.android.gms.internal.ads.zzaab");
    }

    public static void zze(boolean[] zArr) {
        zArr[0] = false;
        zArr[1] = false;
        zArr[2] = false;
    }
}
