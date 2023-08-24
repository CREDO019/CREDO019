package com.google.android.gms.internal.clearcut;

import com.google.android.exoplayer2.extractor.p011ts.PsExtractor;
import java.nio.ByteBuffer;

/* loaded from: classes2.dex */
final class zzfj extends zzfg {
    private static int zza(byte[] bArr, int r3, long j, int r6) {
        int zzam;
        int zzp;
        int zzd;
        if (r6 == 0) {
            zzam = zzff.zzam(r3);
            return zzam;
        } else if (r6 == 1) {
            zzp = zzff.zzp(r3, zzfd.zza(bArr, j));
            return zzp;
        } else if (r6 == 2) {
            zzd = zzff.zzd(r3, zzfd.zza(bArr, j), zzfd.zza(bArr, j + 1));
            return zzd;
        } else {
            throw new AssertionError();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x0061, code lost:
        return -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x00b6, code lost:
        return -1;
     */
    @Override // com.google.android.gms.internal.clearcut.zzfg
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final int zzb(int r16, byte[] r17, int r18, int r19) {
        /*
            Method dump skipped, instructions count: 217
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.clearcut.zzfj.zzb(int, byte[], int, int):int");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.clearcut.zzfg
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
            zzfd.zza(bArr, j4, (byte) charAt);
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
                    zzfd.zza(bArr, j4, (byte) ((charAt3 >>> 6) | 960));
                    zzfd.zza(bArr, j6, (byte) ((charAt3 & '?') | 128));
                    j2 = j6 + j;
                    j3 = j;
                } else if ((charAt3 >= 55296 && 57343 >= charAt3) || j4 > j5 - 3) {
                    if (j4 > j5 - 4) {
                        if (55296 > charAt3 || charAt3 > 57343 || ((r1 = r2 + 1) != length && Character.isSurrogatePair(charAt3, charSequence.charAt(r1)))) {
                            StringBuilder sb2 = new StringBuilder(46);
                            sb2.append("Failed writing ");
                            sb2.append(charAt3);
                            sb2.append(" at index ");
                            sb2.append(j4);
                            throw new ArrayIndexOutOfBoundsException(sb2.toString());
                        }
                        throw new zzfi(r2, length);
                    }
                    int r3 = r2 + 1;
                    if (r3 != length) {
                        char charAt4 = charSequence.charAt(r3);
                        if (Character.isSurrogatePair(charAt3, charAt4)) {
                            int codePoint = Character.toCodePoint(charAt3, charAt4);
                            long j7 = j4 + 1;
                            zzfd.zza(bArr, j4, (byte) ((codePoint >>> 18) | PsExtractor.VIDEO_STREAM_MASK));
                            long j8 = j7 + 1;
                            zzfd.zza(bArr, j7, (byte) (((codePoint >>> 12) & 63) | 128));
                            long j9 = j8 + 1;
                            zzfd.zza(bArr, j8, (byte) (((codePoint >>> 6) & 63) | 128));
                            j3 = 1;
                            j2 = j9 + 1;
                            zzfd.zza(bArr, j9, (byte) ((codePoint & 63) | 128));
                            r2 = r3;
                        } else {
                            r2 = r3;
                        }
                    }
                    throw new zzfi(r2 - 1, length);
                } else {
                    long j10 = j4 + j;
                    zzfd.zza(bArr, j4, (byte) ((charAt3 >>> '\f') | 480));
                    long j11 = j10 + j;
                    zzfd.zza(bArr, j10, (byte) (((charAt3 >>> 6) & 63) | 128));
                    zzfd.zza(bArr, j11, (byte) ((charAt3 & '?') | 128));
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
                zzfd.zza(bArr, j4, (byte) charAt3);
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

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.clearcut.zzfg
    public final void zzb(CharSequence charSequence, ByteBuffer byteBuffer) {
        char c;
        int r0;
        long j;
        int r1;
        char charAt;
        ByteBuffer byteBuffer2 = byteBuffer;
        long zzb = zzfd.zzb(byteBuffer);
        long position = byteBuffer.position() + zzb;
        long limit = byteBuffer.limit() + zzb;
        int length = charSequence.length();
        if (length > limit - position) {
            char charAt2 = charSequence.charAt(length - 1);
            int limit2 = byteBuffer.limit();
            StringBuilder sb = new StringBuilder(37);
            sb.append("Failed writing ");
            sb.append(charAt2);
            sb.append(" at index ");
            sb.append(limit2);
            throw new ArrayIndexOutOfBoundsException(sb.toString());
        }
        int r9 = 0;
        while (true) {
            c = 128;
            if (r9 >= length || (charAt = charSequence.charAt(r9)) >= 128) {
                break;
            }
            zzfd.zza(position, (byte) charAt);
            r9++;
            position++;
        }
        if (r9 == length) {
            r0 = (int) (position - zzb);
        } else {
            while (r9 < length) {
                char charAt3 = charSequence.charAt(r9);
                if (charAt3 < c && position < limit) {
                    zzfd.zza(position, (byte) charAt3);
                    position++;
                    j = zzb;
                } else if (charAt3 >= 2048 || position > limit - 2) {
                    j = zzb;
                    if ((charAt3 >= 55296 && 57343 >= charAt3) || position > limit - 3) {
                        if (position > limit - 4) {
                            if (55296 <= charAt3 && charAt3 <= 57343 && ((r1 = r9 + 1) == length || !Character.isSurrogatePair(charAt3, charSequence.charAt(r1)))) {
                                throw new zzfi(r9, length);
                            }
                            StringBuilder sb2 = new StringBuilder(46);
                            sb2.append("Failed writing ");
                            sb2.append(charAt3);
                            sb2.append(" at index ");
                            sb2.append(position);
                            throw new ArrayIndexOutOfBoundsException(sb2.toString());
                        }
                        int r12 = r9 + 1;
                        if (r12 != length) {
                            char charAt4 = charSequence.charAt(r12);
                            if (Character.isSurrogatePair(charAt3, charAt4)) {
                                int codePoint = Character.toCodePoint(charAt3, charAt4);
                                long j2 = position + 1;
                                zzfd.zza(position, (byte) ((codePoint >>> 18) | PsExtractor.VIDEO_STREAM_MASK));
                                long j3 = j2 + 1;
                                zzfd.zza(j2, (byte) (((codePoint >>> 12) & 63) | 128));
                                long j4 = j3 + 1;
                                zzfd.zza(j3, (byte) (((codePoint >>> 6) & 63) | 128));
                                long j5 = j4 + 1;
                                zzfd.zza(j4, (byte) ((codePoint & 63) | 128));
                                r9 = r12;
                                position = j5;
                            } else {
                                r9 = r12;
                            }
                        }
                        throw new zzfi(r9 - 1, length);
                    }
                    long j6 = position + 1;
                    zzfd.zza(position, (byte) ((charAt3 >>> '\f') | 480));
                    long j7 = j6 + 1;
                    zzfd.zza(j6, (byte) (((charAt3 >>> 6) & 63) | 128));
                    zzfd.zza(j7, (byte) ((charAt3 & '?') | 128));
                    position = j7 + 1;
                } else {
                    j = zzb;
                    long j8 = position + 1;
                    zzfd.zza(position, (byte) ((charAt3 >>> 6) | 960));
                    zzfd.zza(j8, (byte) ((charAt3 & '?') | 128));
                    position = j8 + 1;
                }
                r9++;
                zzb = j;
                c = 128;
            }
            r0 = (int) (position - zzb);
            byteBuffer2 = byteBuffer;
        }
        byteBuffer2.position(r0);
    }
}
