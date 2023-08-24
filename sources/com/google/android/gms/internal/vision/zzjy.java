package com.google.android.gms.internal.vision;

import com.google.android.exoplayer2.extractor.p011ts.PsExtractor;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
final class zzjy extends zzjt {
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0061, code lost:
        return -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x00b6, code lost:
        return -1;
     */
    @Override // com.google.android.gms.internal.vision.zzjt
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final int zzb(int r16, byte[] r17, int r18, int r19) {
        /*
            Method dump skipped, instructions count: 217
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzjy.zzb(int, byte[], int, int):int");
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
            byte zza = zzjp.zza(bArr, r13);
            zzd3 = zzju.zzd(zza);
            if (!zzd3) {
                break;
            }
            r13++;
            zzju.zza(zza, cArr, r3);
            r3++;
        }
        int r8 = r3;
        while (r13 < r0) {
            int r32 = r13 + 1;
            byte zza2 = zzjp.zza(bArr, r13);
            zzd = zzju.zzd(zza2);
            if (zzd) {
                int r4 = r8 + 1;
                zzju.zza(zza2, cArr, r8);
                while (r32 < r0) {
                    byte zza3 = zzjp.zza(bArr, r32);
                    zzd2 = zzju.zzd(zza3);
                    if (!zzd2) {
                        break;
                    }
                    r32++;
                    zzju.zza(zza3, cArr, r4);
                    r4++;
                }
                r13 = r32;
                r8 = r4;
            } else {
                zze = zzju.zze(zza2);
                if (!zze) {
                    zzf = zzju.zzf(zza2);
                    if (zzf) {
                        if (r32 < r0 - 1) {
                            int r42 = r32 + 1;
                            zzju.zza(zza2, zzjp.zza(bArr, r32), zzjp.zza(bArr, r42), cArr, r8);
                            r13 = r42 + 1;
                            r8++;
                        } else {
                            throw zzhc.zzgt();
                        }
                    } else if (r32 < r0 - 2) {
                        int r43 = r32 + 1;
                        int r33 = r43 + 1;
                        zzju.zza(zza2, zzjp.zza(bArr, r32), zzjp.zza(bArr, r43), zzjp.zza(bArr, r33), cArr, r8);
                        r13 = r33 + 1;
                        r8 = r8 + 1 + 1;
                    } else {
                        throw zzhc.zzgt();
                    }
                } else if (r32 < r0) {
                    zzju.zza(zza2, zzjp.zza(bArr, r32), cArr, r8);
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
    @Override // com.google.android.gms.internal.vision.zzjt
    public final int zzb(CharSequence charSequence, byte[] bArr, int r25, int r26) {
        char c;
        long j;
        long j2;
        long j3;
        int r1;
        char charAt;
        long j4 = r25;
        long j5 = r26 + j4;
        int length = charSequence.length();
        if (length > r26 || bArr.length - r26 < r25) {
            char charAt2 = charSequence.charAt(length - 1);
            StringBuilder sb = new StringBuilder(37);
            sb.append("Failed writing ");
            sb.append(charAt2);
            sb.append(" at index ");
            sb.append(r25 + r26);
            throw new ArrayIndexOutOfBoundsException(sb.toString());
        }
        int r2 = 0;
        while (true) {
            c = 128;
            j = 1;
            if (r2 >= length || (charAt = charSequence.charAt(r2)) >= 128) {
                break;
            }
            zzjp.zza(bArr, j4, (byte) charAt);
            r2++;
            j4 = 1 + j4;
        }
        if (r2 == length) {
            return (int) j4;
        }
        while (r2 < length) {
            char charAt3 = charSequence.charAt(r2);
            if (charAt3 >= c || j4 >= j5) {
                if (charAt3 < 2048 && j4 <= j5 - 2) {
                    long j6 = j4 + j;
                    zzjp.zza(bArr, j4, (byte) ((charAt3 >>> 6) | 960));
                    zzjp.zza(bArr, j6, (byte) ((charAt3 & '?') | 128));
                    j2 = j6 + j;
                    j3 = j;
                } else if ((charAt3 >= 55296 && 57343 >= charAt3) || j4 > j5 - 3) {
                    if (j4 <= j5 - 4) {
                        int r3 = r2 + 1;
                        if (r3 != length) {
                            char charAt4 = charSequence.charAt(r3);
                            if (Character.isSurrogatePair(charAt3, charAt4)) {
                                int codePoint = Character.toCodePoint(charAt3, charAt4);
                                long j7 = j4 + 1;
                                zzjp.zza(bArr, j4, (byte) ((codePoint >>> 18) | PsExtractor.VIDEO_STREAM_MASK));
                                long j8 = j7 + 1;
                                zzjp.zza(bArr, j7, (byte) (((codePoint >>> 12) & 63) | 128));
                                long j9 = j8 + 1;
                                zzjp.zza(bArr, j8, (byte) (((codePoint >>> 6) & 63) | 128));
                                j3 = 1;
                                j2 = j9 + 1;
                                zzjp.zza(bArr, j9, (byte) ((codePoint & 63) | 128));
                                r2 = r3;
                            } else {
                                r2 = r3;
                            }
                        }
                        throw new zzjv(r2 - 1, length);
                    } else if (55296 <= charAt3 && charAt3 <= 57343 && ((r1 = r2 + 1) == length || !Character.isSurrogatePair(charAt3, charSequence.charAt(r1)))) {
                        throw new zzjv(r2, length);
                    } else {
                        StringBuilder sb2 = new StringBuilder(46);
                        sb2.append("Failed writing ");
                        sb2.append(charAt3);
                        sb2.append(" at index ");
                        sb2.append(j4);
                        throw new ArrayIndexOutOfBoundsException(sb2.toString());
                    }
                } else {
                    long j10 = j4 + j;
                    zzjp.zza(bArr, j4, (byte) ((charAt3 >>> '\f') | 480));
                    long j11 = j10 + j;
                    zzjp.zza(bArr, j10, (byte) (((charAt3 >>> 6) & 63) | 128));
                    zzjp.zza(bArr, j11, (byte) ((charAt3 & '?') | 128));
                    j2 = j11 + 1;
                    j3 = 1;
                }
                r2++;
                c = 128;
                long j12 = j3;
                j4 = j2;
                j = j12;
            } else {
                long j13 = j4 + j;
                zzjp.zza(bArr, j4, (byte) charAt3);
                j3 = j;
                j2 = j13;
            }
            r2++;
            c = 128;
            long j122 = j3;
            j4 = j2;
            j = j122;
        }
        return (int) j4;
    }

    private static int zza(byte[] bArr, int r3, long j, int r6) {
        int zzbw;
        int zzv;
        int zzd;
        if (r6 == 0) {
            zzbw = zzjs.zzbw(r3);
            return zzbw;
        } else if (r6 == 1) {
            zzv = zzjs.zzv(r3, zzjp.zza(bArr, j));
            return zzv;
        } else if (r6 == 2) {
            zzd = zzjs.zzd(r3, zzjp.zza(bArr, j), zzjp.zza(bArr, j + 1));
            return zzd;
        } else {
            throw new AssertionError();
        }
    }
}
