package com.google.android.gms.internal.clearcut;

import java.nio.ByteBuffer;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzff {
    private static final zzfg zzqb;

    static {
        zzqb = zzfd.zzed() && zzfd.zzee() ? new zzfj() : new zzfh();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(CharSequence charSequence) {
        int length = charSequence.length();
        int r1 = 0;
        int r2 = 0;
        while (r2 < length && charSequence.charAt(r2) < 128) {
            r2++;
        }
        int r3 = length;
        while (true) {
            if (r2 >= length) {
                break;
            }
            char charAt = charSequence.charAt(r2);
            if (charAt < 2048) {
                r3 += (127 - charAt) >>> 31;
                r2++;
            } else {
                int length2 = charSequence.length();
                while (r2 < length2) {
                    char charAt2 = charSequence.charAt(r2);
                    if (charAt2 < 2048) {
                        r1 += (127 - charAt2) >>> 31;
                    } else {
                        r1 += 2;
                        if (55296 <= charAt2 && charAt2 <= 57343) {
                            if (Character.codePointAt(charSequence, r2) < 65536) {
                                throw new zzfi(r2, length2);
                            }
                            r2++;
                        }
                    }
                    r2++;
                }
                r3 += r1;
            }
        }
        if (r3 >= length) {
            return r3;
        }
        StringBuilder sb = new StringBuilder(54);
        sb.append("UTF-8 length does not fit in int: ");
        sb.append(r3 + 4294967296L);
        throw new IllegalArgumentException(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(CharSequence charSequence, byte[] bArr, int r3, int r4) {
        return zzqb.zzb(charSequence, bArr, r3, r4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zza(CharSequence charSequence, ByteBuffer byteBuffer) {
        zzfg zzfgVar = zzqb;
        if (byteBuffer.hasArray()) {
            int arrayOffset = byteBuffer.arrayOffset();
            byteBuffer.position(zza(charSequence, byteBuffer.array(), byteBuffer.position() + arrayOffset, byteBuffer.remaining()) - arrayOffset);
        } else if (byteBuffer.isDirect()) {
            zzfgVar.zzb(charSequence, byteBuffer);
        } else {
            zzfg.zzc(charSequence, byteBuffer);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int zzam(int r1) {
        if (r1 > -12) {
            return -1;
        }
        return r1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int zzd(int r1, int r2, int r3) {
        if (r1 > -12 || r2 > -65 || r3 > -65) {
            return -1;
        }
        return (r1 ^ (r2 << 8)) ^ (r3 << 16);
    }

    public static boolean zze(byte[] bArr) {
        return zzqb.zze(bArr, 0, bArr.length);
    }

    public static boolean zze(byte[] bArr, int r2, int r3) {
        return zzqb.zze(bArr, r2, r3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int zzf(byte[] bArr, int r4, int r5) {
        byte b = bArr[r4 - 1];
        int r52 = r5 - r4;
        if (r52 != 0) {
            if (r52 != 1) {
                if (r52 == 2) {
                    return zzd(b, bArr[r4], bArr[r4 + 1]);
                }
                throw new AssertionError();
            }
            return zzp(b, bArr[r4]);
        }
        return zzam(b);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int zzp(int r1, int r2) {
        if (r1 > -12 || r2 > -65) {
            return -1;
        }
        return r1 ^ (r2 << 8);
    }
}
