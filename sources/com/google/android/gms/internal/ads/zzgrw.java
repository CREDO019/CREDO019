package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgrw {
    private static final zzgrt zza;

    static {
        if (zzgrr.zzA() && zzgrr.zzB()) {
            int r0 = zzgmq.zza;
        }
        zza = new zzgru();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ int zzc(byte[] bArr, int r4, int r5) {
        byte b = bArr[r4 - 1];
        int r52 = r5 - r4;
        if (r52 == 0) {
            if (b > -12) {
                return -1;
            }
            return b;
        } else if (r52 != 1) {
            if (r52 == 2) {
                return zzl(b, bArr[r4], bArr[r4 + 1]);
            }
            throw new AssertionError();
        } else {
            return zzk(b, bArr[r4]);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00fe, code lost:
        return r9 + r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int zzd(java.lang.CharSequence r7, byte[] r8, int r9, int r10) {
        /*
            Method dump skipped, instructions count: 255
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzgrw.zzd(java.lang.CharSequence, byte[], int, int):int");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zze(CharSequence charSequence) {
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
                        if (charAt2 >= 55296 && charAt2 <= 57343) {
                            if (Character.codePointAt(charSequence, r2) < 65536) {
                                throw new zzgrv(r2, length2);
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
        throw new IllegalArgumentException("UTF-8 length does not fit in int: " + (r3 + 4294967296L));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzf(int r1, byte[] bArr, int r3, int r4) {
        return zza.zza(r1, bArr, r3, r4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String zzg(ByteBuffer byteBuffer, int r3, int r4) throws zzgoz {
        zzgrt zzgrtVar = zza;
        if (byteBuffer.hasArray()) {
            return zzgrtVar.zzb(byteBuffer.array(), byteBuffer.arrayOffset() + r3, r4);
        } else if (!byteBuffer.isDirect()) {
            return zzgrt.zzd(byteBuffer, r3, r4);
        } else {
            return zzgru.zzd(byteBuffer, r3, r4);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String zzh(byte[] bArr, int r2, int r3) throws zzgoz {
        return zza.zzb(bArr, r2, r3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean zzi(byte[] bArr) {
        return zza.zzc(bArr, 0, bArr.length);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean zzj(byte[] bArr, int r2, int r3) {
        return zza.zzc(bArr, r2, r3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int zzk(int r1, int r2) {
        if (r1 > -12 || r2 > -65) {
            return -1;
        }
        return r1 ^ (r2 << 8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int zzl(int r1, int r2, int r3) {
        if (r1 > -12 || r2 > -65 || r3 > -65) {
            return -1;
        }
        return (r1 ^ (r2 << 8)) ^ (r3 << 16);
    }
}
