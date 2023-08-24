package com.google.android.gms.internal.clearcut;

import com.google.android.exoplayer2.extractor.p011ts.PsExtractor;
import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ReadOnlyBufferException;

/* loaded from: classes2.dex */
public final class zzfs {
    private final ByteBuffer zzgd;
    private zzbn zzrh;
    private int zzri;

    private zzfs(ByteBuffer byteBuffer) {
        this.zzgd = byteBuffer;
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
    }

    private zzfs(byte[] bArr, int r2, int r3) {
        this(ByteBuffer.wrap(bArr, r2, r3));
    }

    private static int zza(CharSequence charSequence) {
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
                                StringBuilder sb = new StringBuilder(39);
                                sb.append("Unpaired surrogate at index ");
                                sb.append(r2);
                                throw new IllegalArgumentException(sb.toString());
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
        StringBuilder sb2 = new StringBuilder(54);
        sb2.append("UTF-8 length does not fit in int: ");
        sb2.append(r3 + 4294967296L);
        throw new IllegalArgumentException(sb2.toString());
    }

    private final void zzao(int r3) throws IOException {
        byte b = (byte) r3;
        if (!this.zzgd.hasRemaining()) {
            throw new zzft(this.zzgd.position(), this.zzgd.limit());
        }
        this.zzgd.put(b);
    }

    private final void zzap(int r2) throws IOException {
        while ((r2 & (-128)) != 0) {
            zzao((r2 & 127) | 128);
            r2 >>>= 7;
        }
        zzao(r2);
    }

    public static int zzb(int r1, zzfz zzfzVar) {
        int zzr = zzr(r1);
        int zzas = zzfzVar.zzas();
        return zzr + zzz(zzas) + zzas;
    }

    public static int zzb(int r0, String str) {
        return zzr(r0) + zzh(str);
    }

    public static int zzb(int r0, byte[] bArr) {
        return zzr(r0) + zzh(bArr);
    }

    public static int zzd(int r0, long j) {
        return zzr(r0) + zzo(j);
    }

    private static void zzd(CharSequence charSequence, ByteBuffer byteBuffer) {
        int r8;
        int r12;
        char charAt;
        int r9;
        if (byteBuffer.isReadOnly()) {
            throw new ReadOnlyBufferException();
        }
        int r6 = 0;
        if (!byteBuffer.hasArray()) {
            int length = charSequence.length();
            while (r6 < length) {
                char charAt2 = charSequence.charAt(r6);
                char c = charAt2;
                if (charAt2 >= 128) {
                    if (charAt2 < 2048) {
                        r9 = (charAt2 >>> 6) | 960;
                    } else if (charAt2 >= 55296 && 57343 >= charAt2) {
                        int r92 = r6 + 1;
                        if (r92 != charSequence.length()) {
                            char charAt3 = charSequence.charAt(r92);
                            if (Character.isSurrogatePair(charAt2, charAt3)) {
                                int codePoint = Character.toCodePoint(charAt2, charAt3);
                                byteBuffer.put((byte) ((codePoint >>> 18) | PsExtractor.VIDEO_STREAM_MASK));
                                byteBuffer.put((byte) (((codePoint >>> 12) & 63) | 128));
                                byteBuffer.put((byte) (((codePoint >>> 6) & 63) | 128));
                                byteBuffer.put((byte) ((codePoint & 63) | 128));
                                r6 = r92;
                                r6++;
                            } else {
                                r6 = r92;
                            }
                        }
                        StringBuilder sb = new StringBuilder(39);
                        sb.append("Unpaired surrogate at index ");
                        sb.append(r6 - 1);
                        throw new IllegalArgumentException(sb.toString());
                    } else {
                        byteBuffer.put((byte) ((charAt2 >>> '\f') | 480));
                        r9 = ((charAt2 >>> 6) & 63) | 128;
                    }
                    byteBuffer.put((byte) r9);
                    c = (charAt2 & '?') | 128;
                }
                byteBuffer.put((byte) c);
                r6++;
            }
            return;
        }
        try {
            byte[] array = byteBuffer.array();
            int arrayOffset = byteBuffer.arrayOffset() + byteBuffer.position();
            int remaining = byteBuffer.remaining();
            int length2 = charSequence.length();
            int r93 = remaining + arrayOffset;
            while (r6 < length2) {
                int r11 = r6 + arrayOffset;
                if (r11 >= r93 || (charAt = charSequence.charAt(r6)) >= 128) {
                    break;
                }
                array[r11] = (byte) charAt;
                r6++;
            }
            if (r6 == length2) {
                r8 = arrayOffset + length2;
            } else {
                r8 = arrayOffset + r6;
                while (r6 < length2) {
                    char charAt4 = charSequence.charAt(r6);
                    if (charAt4 >= 128 || r8 >= r93) {
                        if (charAt4 < 2048 && r8 <= r93 - 2) {
                            int r122 = r8 + 1;
                            array[r8] = (byte) ((charAt4 >>> 6) | 960);
                            r8 = r122 + 1;
                            array[r122] = (byte) ((charAt4 & '?') | 128);
                        } else if ((charAt4 >= 55296 && 57343 >= charAt4) || r8 > r93 - 3) {
                            if (r8 > r93 - 4) {
                                StringBuilder sb2 = new StringBuilder(37);
                                sb2.append("Failed writing ");
                                sb2.append(charAt4);
                                sb2.append(" at index ");
                                sb2.append(r8);
                                throw new ArrayIndexOutOfBoundsException(sb2.toString());
                            }
                            int r123 = r6 + 1;
                            if (r123 != charSequence.length()) {
                                char charAt5 = charSequence.charAt(r123);
                                if (Character.isSurrogatePair(charAt4, charAt5)) {
                                    int codePoint2 = Character.toCodePoint(charAt4, charAt5);
                                    int r112 = r8 + 1;
                                    array[r8] = (byte) ((codePoint2 >>> 18) | PsExtractor.VIDEO_STREAM_MASK);
                                    int r82 = r112 + 1;
                                    array[r112] = (byte) (((codePoint2 >>> 12) & 63) | 128);
                                    int r113 = r82 + 1;
                                    array[r82] = (byte) (((codePoint2 >>> 6) & 63) | 128);
                                    r8 = r113 + 1;
                                    array[r113] = (byte) ((codePoint2 & 63) | 128);
                                    r6 = r123;
                                } else {
                                    r6 = r123;
                                }
                            }
                            StringBuilder sb3 = new StringBuilder(39);
                            sb3.append("Unpaired surrogate at index ");
                            sb3.append(r6 - 1);
                            throw new IllegalArgumentException(sb3.toString());
                        } else {
                            int r124 = r8 + 1;
                            array[r8] = (byte) ((charAt4 >>> '\f') | 480);
                            int r83 = r124 + 1;
                            array[r124] = (byte) (((charAt4 >>> 6) & 63) | 128);
                            r12 = r83 + 1;
                            array[r83] = (byte) ((charAt4 & '?') | 128);
                        }
                        r6++;
                    } else {
                        r12 = r8 + 1;
                        array[r8] = (byte) charAt4;
                    }
                    r8 = r12;
                    r6++;
                }
            }
            byteBuffer.position(r8 - byteBuffer.arrayOffset());
        } catch (ArrayIndexOutOfBoundsException e) {
            BufferOverflowException bufferOverflowException = new BufferOverflowException();
            bufferOverflowException.initCause(e);
            throw bufferOverflowException;
        }
    }

    public static zzfs zzg(byte[] bArr) {
        return zzh(bArr, 0, bArr.length);
    }

    public static int zzh(String str) {
        int zza = zza(str);
        return zzz(zza) + zza;
    }

    public static int zzh(byte[] bArr) {
        return zzz(bArr.length) + bArr.length;
    }

    public static zzfs zzh(byte[] bArr, int r2, int r3) {
        return new zzfs(bArr, 0, r3);
    }

    public static long zzj(long j) {
        return (j >> 63) ^ (j << 1);
    }

    public static int zzo(long j) {
        if (((-128) & j) == 0) {
            return 1;
        }
        if (((-16384) & j) == 0) {
            return 2;
        }
        if (((-2097152) & j) == 0) {
            return 3;
        }
        if (((-268435456) & j) == 0) {
            return 4;
        }
        if (((-34359738368L) & j) == 0) {
            return 5;
        }
        if (((-4398046511104L) & j) == 0) {
            return 6;
        }
        if (((-562949953421312L) & j) == 0) {
            return 7;
        }
        if (((-72057594037927936L) & j) == 0) {
            return 8;
        }
        return (j & Long.MIN_VALUE) == 0 ? 9 : 10;
    }

    public static int zzr(int r0) {
        return zzz(r0 << 3);
    }

    public static int zzs(int r0) {
        if (r0 >= 0) {
            return zzz(r0);
        }
        return 10;
    }

    private static int zzz(int r1) {
        if ((r1 & (-128)) == 0) {
            return 1;
        }
        if ((r1 & (-16384)) == 0) {
            return 2;
        }
        if (((-2097152) & r1) == 0) {
            return 3;
        }
        return (r1 & (-268435456)) == 0 ? 4 : 5;
    }

    public final void zza(int r2, zzfz zzfzVar) throws IOException {
        zzb(r2, 2);
        if (zzfzVar.zzrs < 0) {
            zzfzVar.zzas();
        }
        zzap(zzfzVar.zzrs);
        zzfzVar.zza(this);
    }

    public final void zza(int r4, String str) throws IOException {
        zzb(r4, 2);
        try {
            int zzz = zzz(str.length());
            if (zzz != zzz(str.length() * 3)) {
                zzap(zza(str));
                zzd(str, this.zzgd);
                return;
            }
            int position = this.zzgd.position();
            if (this.zzgd.remaining() < zzz) {
                throw new zzft(position + zzz, this.zzgd.limit());
            }
            this.zzgd.position(position + zzz);
            zzd(str, this.zzgd);
            int position2 = this.zzgd.position();
            this.zzgd.position(position);
            zzap((position2 - position) - zzz);
            this.zzgd.position(position2);
        } catch (BufferOverflowException e) {
            zzft zzftVar = new zzft(this.zzgd.position(), this.zzgd.limit());
            zzftVar.initCause(e);
            throw zzftVar;
        }
    }

    public final void zza(int r3, byte[] bArr) throws IOException {
        zzb(r3, 2);
        zzap(bArr.length);
        int length = bArr.length;
        if (this.zzgd.remaining() < length) {
            throw new zzft(this.zzgd.position(), this.zzgd.limit());
        }
        this.zzgd.put(bArr, 0, length);
    }

    public final void zzb(int r1, int r2) throws IOException {
        zzap((r1 << 3) | r2);
    }

    public final void zzb(int r2, boolean z) throws IOException {
        zzb(25, 0);
        byte b = z ? (byte) 1 : (byte) 0;
        if (!this.zzgd.hasRemaining()) {
            throw new zzft(this.zzgd.position(), this.zzgd.limit());
        }
        this.zzgd.put(b);
    }

    public final void zzc(int r2, int r3) throws IOException {
        zzb(r2, 0);
        if (r3 >= 0) {
            zzap(r3);
        } else {
            zzn(r3);
        }
    }

    public final void zze(int r6, zzdo zzdoVar) throws IOException {
        if (this.zzrh != null) {
            if (this.zzri != this.zzgd.position()) {
                this.zzrh.write(this.zzgd.array(), this.zzri, this.zzgd.position() - this.zzri);
            }
            zzbn zzbnVar = this.zzrh;
            zzbnVar.zza(r6, zzdoVar);
            zzbnVar.flush();
            this.zzri = this.zzgd.position();
        }
        this.zzrh = zzbn.zza(this.zzgd);
        this.zzri = this.zzgd.position();
        zzbn zzbnVar2 = this.zzrh;
        zzbnVar2.zza(r6, zzdoVar);
        zzbnVar2.flush();
        this.zzri = this.zzgd.position();
    }

    public final void zzem() {
        if (this.zzgd.remaining() != 0) {
            throw new IllegalStateException(String.format("Did not write as much data as expected, %s bytes remaining.", Integer.valueOf(this.zzgd.remaining())));
        }
    }

    public final void zzi(int r2, long j) throws IOException {
        zzb(r2, 0);
        zzn(j);
    }

    public final void zzn(long j) throws IOException {
        while (((-128) & j) != 0) {
            zzao((((int) j) & 127) | 128);
            j >>>= 7;
        }
        zzao((int) j);
    }
}
