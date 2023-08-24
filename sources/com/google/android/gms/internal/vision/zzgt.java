package com.google.android.gms.internal.vision;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
public final class zzgt {
    public static final byte[] zzxc;
    private static final ByteBuffer zzxd;
    private static final zzft zzxe;
    static final Charset UTF_8 = Charset.forName("UTF-8");
    private static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");

    public static int zzab(long j) {
        return (int) (j ^ (j >>> 32));
    }

    public static int zzm(boolean z) {
        return z ? 1231 : 1237;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> T checkNotNull(T t) {
        Objects.requireNonNull(t);
        return t;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> T zza(T t, String str) {
        Objects.requireNonNull(t, str);
        return t;
    }

    public static boolean zzg(byte[] bArr) {
        return zzjs.zzg(bArr);
    }

    public static String zzh(byte[] bArr) {
        return new String(bArr, UTF_8);
    }

    public static int hashCode(byte[] bArr) {
        int length = bArr.length;
        int zza = zza(length, bArr, 0, length);
        if (zza == 0) {
            return 1;
        }
        return zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(int r2, byte[] bArr, int r4, int r5) {
        for (int r0 = r4; r0 < r4 + r5; r0++) {
            r2 = (r2 * 31) + bArr[r0];
        }
        return r2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean zzf(zzic zzicVar) {
        if (zzicVar instanceof zzev) {
            zzev zzevVar = (zzev) zzicVar;
            return false;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object zzb(Object obj, Object obj2) {
        return ((zzic) obj).zzgi().zza((zzic) obj2).zzgb();
    }

    static {
        byte[] bArr = new byte[0];
        zzxc = bArr;
        zzxd = ByteBuffer.wrap(bArr);
        zzxe = zzft.zza(bArr, 0, bArr.length, false);
    }
}
