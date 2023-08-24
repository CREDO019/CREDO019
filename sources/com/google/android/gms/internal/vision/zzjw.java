package com.google.android.gms.internal.vision;

import com.google.common.base.Ascii;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
final class zzjw extends zzjt {
    @Override // com.google.android.gms.internal.vision.zzjt
    final int zzb(int r7, byte[] bArr, int r9, int r10) {
        int zzg;
        int zzg2;
        while (r9 < r10 && bArr[r9] >= 0) {
            r9++;
        }
        if (r9 >= r10) {
            return 0;
        }
        while (r9 < r10) {
            int r0 = r9 + 1;
            byte b = bArr[r9];
            if (b < 0) {
                if (b < -32) {
                    if (r0 >= r10) {
                        return b;
                    }
                    if (b >= -62) {
                        r9 = r0 + 1;
                        if (bArr[r0] > -65) {
                        }
                    }
                    return -1;
                } else if (b >= -16) {
                    if (r0 < r10 - 2) {
                        int r1 = r0 + 1;
                        byte b2 = bArr[r0];
                        if (b2 <= -65 && (((b << Ascii.f1122FS) + (b2 + 112)) >> 30) == 0) {
                            int r92 = r1 + 1;
                            if (bArr[r1] <= -65) {
                                r0 = r92 + 1;
                                if (bArr[r92] > -65) {
                                }
                            }
                        }
                        return -1;
                    }
                    zzg2 = zzjs.zzg(bArr, r0, r10);
                    return zzg2;
                } else if (r0 < r10 - 1) {
                    int r4 = r0 + 1;
                    byte b3 = bArr[r0];
                    if (b3 <= -65 && ((b != -32 || b3 >= -96) && (b != -19 || b3 < -96))) {
                        r9 = r4 + 1;
                        if (bArr[r4] > -65) {
                        }
                    }
                    return -1;
                } else {
                    zzg = zzjs.zzg(bArr, r0, r10);
                    return zzg;
                }
            }
            r9 = r0;
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzjt
    public final String zzh(byte[] bArr, int r13, int r14) throws zzhc {
        boolean zzd;
        boolean zzd2;
        boolean zze;
        boolean zzf;
        boolean zzd3;
        if ((r13 | r14 | ((bArr.length - r13) - r14)) < 0) {
            throw new ArrayIndexOutOfBoundsException(String.format("buffer length=%d, index=%d, size=%d", Integer.valueOf(bArr.length), Integer.valueOf(r13), Integer.valueOf(r14)));
        }
        int r0 = r13 + r14;
        char[] cArr = new char[r14];
        int r3 = 0;
        while (r13 < r0) {
            byte b = bArr[r13];
            zzd3 = zzju.zzd(b);
            if (!zzd3) {
                break;
            }
            r13++;
            zzju.zza(b, cArr, r3);
            r3++;
        }
        int r8 = r3;
        while (r13 < r0) {
            int r32 = r13 + 1;
            byte b2 = bArr[r13];
            zzd = zzju.zzd(b2);
            if (zzd) {
                int r4 = r8 + 1;
                zzju.zza(b2, cArr, r8);
                while (r32 < r0) {
                    byte b3 = bArr[r32];
                    zzd2 = zzju.zzd(b3);
                    if (!zzd2) {
                        break;
                    }
                    r32++;
                    zzju.zza(b3, cArr, r4);
                    r4++;
                }
                r13 = r32;
                r8 = r4;
            } else {
                zze = zzju.zze(b2);
                if (!zze) {
                    zzf = zzju.zzf(b2);
                    if (zzf) {
                        if (r32 < r0 - 1) {
                            int r42 = r32 + 1;
                            zzju.zza(b2, bArr[r32], bArr[r42], cArr, r8);
                            r13 = r42 + 1;
                            r8++;
                        } else {
                            throw zzhc.zzgt();
                        }
                    } else if (r32 >= r0 - 2) {
                        throw zzhc.zzgt();
                    } else {
                        int r43 = r32 + 1;
                        byte b4 = bArr[r32];
                        int r33 = r43 + 1;
                        zzju.zza(b2, b4, bArr[r43], bArr[r33], cArr, r8);
                        r13 = r33 + 1;
                        r8 = r8 + 1 + 1;
                    }
                } else if (r32 < r0) {
                    zzju.zza(b2, bArr[r32], cArr, r8);
                    r13 = r32 + 1;
                    r8++;
                } else {
                    throw zzhc.zzgt();
                }
            }
        }
        return new String(cArr, 0, r8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x001d, code lost:
        return r10 + r0;
     */
    @Override // com.google.android.gms.internal.vision.zzjt
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int zzb(java.lang.CharSequence r8, byte[] r9, int r10, int r11) {
        /*
            Method dump skipped, instructions count: 256
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzjw.zzb(java.lang.CharSequence, byte[], int, int):int");
    }
}
