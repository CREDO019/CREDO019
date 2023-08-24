package com.google.android.gms.internal.ads;

import androidx.exifinterface.media.ExifInterface;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdf {
    public static final /* synthetic */ int zza = 0;
    private static final byte[] zzb = {0, 0, 0, 1};
    private static final String[] zzc = {"", ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, "B", "C"};

    public static String zza(int r2, int r3, int r4) {
        return String.format("avc1.%02X%02X%02X", Integer.valueOf(r2), Integer.valueOf(r3), Integer.valueOf(r4));
    }

    public static String zzb(int r3, boolean z, int r5, int r6, int[] r7, int r8) {
        Object[] objArr = new Object[5];
        objArr[0] = zzc[r3];
        objArr[1] = Integer.valueOf(r5);
        objArr[2] = Integer.valueOf(r6);
        objArr[3] = Character.valueOf(true != z ? Matrix.MATRIX_TYPE_RANDOM_LT : 'H');
        objArr[4] = Integer.valueOf(r8);
        StringBuilder sb = new StringBuilder(zzel.zzI("hvc1.%s%d.%X.%c%d", objArr));
        int r32 = 6;
        while (r32 > 0) {
            int r4 = r32 - 1;
            if (r7[r4] != 0) {
                break;
            }
            r32 = r4;
        }
        for (int r42 = 0; r42 < r32; r42++) {
            sb.append(String.format(".%02X", Integer.valueOf(r7[r42])));
        }
        return sb.toString();
    }

    public static byte[] zzc(byte[] bArr, int r5, int r6) {
        byte[] bArr2 = new byte[r6 + 4];
        System.arraycopy(zzb, 0, bArr2, 0, 4);
        System.arraycopy(bArr, r5, bArr2, 4, r6);
        return bArr2;
    }
}
