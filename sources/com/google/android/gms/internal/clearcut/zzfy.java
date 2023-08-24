package com.google.android.gms.internal.clearcut;

import java.nio.charset.Charset;
import java.util.Arrays;

/* loaded from: classes2.dex */
public final class zzfy {
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");
    public static final Object zzrr = new Object();

    public static boolean equals(int[] r1, int[] r2) {
        return (r1 == null || r1.length == 0) ? r2 == null || r2.length == 0 : Arrays.equals(r1, r2);
    }

    public static boolean equals(long[] jArr, long[] jArr2) {
        return (jArr == null || jArr.length == 0) ? jArr2 == null || jArr2.length == 0 : Arrays.equals(jArr, jArr2);
    }

    public static boolean equals(Object[] objArr, Object[] objArr2) {
        int length = objArr == null ? 0 : objArr.length;
        int length2 = objArr2 == null ? 0 : objArr2.length;
        int r3 = 0;
        int r4 = 0;
        while (true) {
            if (r3 >= length || objArr[r3] != null) {
                while (r4 < length2 && objArr2[r4] == null) {
                    r4++;
                }
                boolean z = r3 >= length;
                boolean z2 = r4 >= length2;
                if (z && z2) {
                    return true;
                }
                if (z != z2 || !objArr[r3].equals(objArr2[r4])) {
                    return false;
                }
                r3++;
                r4++;
            } else {
                r3++;
            }
        }
    }

    public static int hashCode(int[] r1) {
        if (r1 == null || r1.length == 0) {
            return 0;
        }
        return Arrays.hashCode(r1);
    }

    public static int hashCode(long[] jArr) {
        if (jArr == null || jArr.length == 0) {
            return 0;
        }
        return Arrays.hashCode(jArr);
    }

    public static int hashCode(Object[] objArr) {
        int length = objArr == null ? 0 : objArr.length;
        int r2 = 0;
        for (int r0 = 0; r0 < length; r0++) {
            Object obj = objArr[r0];
            if (obj != null) {
                r2 = (r2 * 31) + obj.hashCode();
            }
        }
        return r2;
    }

    public static int zza(byte[][] bArr) {
        int length = bArr == null ? 0 : bArr.length;
        int r2 = 0;
        for (int r0 = 0; r0 < length; r0++) {
            byte[] bArr2 = bArr[r0];
            if (bArr2 != null) {
                r2 = (r2 * 31) + Arrays.hashCode(bArr2);
            }
        }
        return r2;
    }

    public static void zza(zzfu zzfuVar, zzfu zzfuVar2) {
        if (zzfuVar.zzrj != null) {
            zzfuVar2.zzrj = (zzfw) zzfuVar.zzrj.clone();
        }
    }

    public static boolean zza(byte[][] bArr, byte[][] bArr2) {
        int length = bArr == null ? 0 : bArr.length;
        int length2 = bArr2 == null ? 0 : bArr2.length;
        int r3 = 0;
        int r4 = 0;
        while (true) {
            if (r3 >= length || bArr[r3] != null) {
                while (r4 < length2 && bArr2[r4] == null) {
                    r4++;
                }
                boolean z = r3 >= length;
                boolean z2 = r4 >= length2;
                if (z && z2) {
                    return true;
                }
                if (z != z2 || !Arrays.equals(bArr[r3], bArr2[r4])) {
                    return false;
                }
                r3++;
                r4++;
            } else {
                r3++;
            }
        }
    }
}
