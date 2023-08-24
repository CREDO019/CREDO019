package com.google.android.gms.internal.measurement;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.2 */
/* loaded from: classes3.dex */
public final class zzna {
    private static final zzmx zza;

    static {
        if (zzmv.zzx() && zzmv.zzy()) {
            int r0 = zzin.zza;
        }
        zza = new zzmy();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ int zza(byte[] bArr, int r7, int r8) {
        byte b = bArr[r7 - 1];
        int r82 = r8 - r7;
        if (r82 != 0) {
            if (r82 == 1) {
                byte b2 = bArr[r7];
                if (b <= -12 && b2 <= -65) {
                    return b ^ (b2 << 8);
                }
            } else if (r82 == 2) {
                byte b3 = bArr[r7];
                byte b4 = bArr[r7 + 1];
                if (b <= -12 && b3 <= -65 && b4 <= -65) {
                    return ((b3 << 8) ^ b) ^ (b4 << 16);
                }
            } else {
                throw new AssertionError();
            }
        } else if (b <= -12) {
            return b;
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00fe, code lost:
        return r9 + r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int zzb(java.lang.CharSequence r7, byte[] r8, int r9, int r10) {
        /*
            Method dump skipped, instructions count: 255
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzna.zzb(java.lang.CharSequence, byte[], int, int):int");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzc(CharSequence charSequence) {
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
                                throw new zzmz(r2, length2);
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
    public static String zzd(byte[] bArr, int r11, int r12) throws zzkm {
        int length = bArr.length;
        if ((r11 | r12 | ((length - r11) - r12)) < 0) {
            throw new ArrayIndexOutOfBoundsException(String.format("buffer length=%d, index=%d, size=%d", Integer.valueOf(length), Integer.valueOf(r11), Integer.valueOf(r12)));
        }
        int r0 = r11 + r12;
        char[] cArr = new char[r12];
        int r1 = 0;
        while (r11 < r0) {
            byte b = bArr[r11];
            if (!zzmw.zzd(b)) {
                break;
            }
            r11++;
            cArr[r1] = (char) b;
            r1++;
        }
        while (r11 < r0) {
            int r3 = r11 + 1;
            byte b2 = bArr[r11];
            if (zzmw.zzd(b2)) {
                int r4 = r1 + 1;
                cArr[r1] = (char) b2;
                r11 = r3;
                while (true) {
                    r1 = r4;
                    if (r11 < r0) {
                        byte b3 = bArr[r11];
                        if (!zzmw.zzd(b3)) {
                            break;
                        }
                        r11++;
                        r4 = r1 + 1;
                        cArr[r1] = (char) b3;
                    }
                }
            } else if (b2 < -32) {
                if (r3 < r0) {
                    zzmw.zzc(b2, bArr[r3], cArr, r1);
                    r11 = r3 + 1;
                    r1++;
                } else {
                    throw zzkm.zzc();
                }
            } else if (b2 < -16) {
                if (r3 < r0 - 1) {
                    int r42 = r3 + 1;
                    zzmw.zzb(b2, bArr[r3], bArr[r42], cArr, r1);
                    r11 = r42 + 1;
                    r1++;
                } else {
                    throw zzkm.zzc();
                }
            } else if (r3 < r0 - 2) {
                int r43 = r3 + 1;
                int r5 = r43 + 1;
                zzmw.zza(b2, bArr[r3], bArr[r43], bArr[r5], cArr, r1);
                r1 += 2;
                r11 = r5 + 1;
            } else {
                throw zzkm.zzc();
            }
        }
        return new String(cArr, 0, r1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean zze(byte[] bArr) {
        return zza.zzb(bArr, 0, bArr.length);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean zzf(byte[] bArr, int r2, int r3) {
        return zza.zzb(bArr, r2, r3);
    }
}
