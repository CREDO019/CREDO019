package com.google.common.hash;

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Ints;
import com.google.common.primitives.UnsignedBytes;
import com.google.errorprone.annotations.Immutable;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import javax.annotation.CheckForNull;
import org.bouncycastle.asn1.cmc.BodyPartID;

/* JADX INFO: Access modifiers changed from: package-private */
@Immutable
@ElementTypesAreNonnullByDefault
/* loaded from: classes3.dex */
public final class Murmur3_32HashFunction extends AbstractHashFunction implements Serializable {

    /* renamed from: C1 */
    private static final int f1168C1 = -862048943;

    /* renamed from: C2 */
    private static final int f1169C2 = 461845907;
    private static final int CHUNK_SIZE = 4;
    private static final long serialVersionUID = 0;
    private final int seed;
    private final boolean supplementaryPlaneFix;
    static final HashFunction MURMUR3_32 = new Murmur3_32HashFunction(0, false);
    static final HashFunction MURMUR3_32_FIXED = new Murmur3_32HashFunction(0, true);
    static final HashFunction GOOD_FAST_HASH_32 = new Murmur3_32HashFunction(Hashing.GOOD_FAST_HASH_SEED, true);

    /* JADX INFO: Access modifiers changed from: private */
    public static long charToThreeUtf8Bytes(char c) {
        return (c >>> '\f') | 224 | ((((c >>> 6) & 63) | 128) << 8) | (((c & '?') | 128) << 16);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static long charToTwoUtf8Bytes(char c) {
        return (c >>> 6) | 192 | (((c & '?') | 128) << 8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static long codePointToFourUtf8Bytes(int r7) {
        return (r7 >>> 18) | 240 | ((((r7 >>> 12) & 63) | 128) << 8) | ((((r7 >>> 6) & 63) | 128) << 16) | (((r7 & 63) | 128) << 24);
    }

    @Override // com.google.common.hash.HashFunction
    public int bits() {
        return 32;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Murmur3_32HashFunction(int r1, boolean z) {
        this.seed = r1;
        this.supplementaryPlaneFix = z;
    }

    @Override // com.google.common.hash.HashFunction
    public Hasher newHasher() {
        return new Murmur3_32Hasher(this.seed);
    }

    public String toString() {
        int r0 = this.seed;
        StringBuilder sb = new StringBuilder(31);
        sb.append("Hashing.murmur3_32(");
        sb.append(r0);
        sb.append(")");
        return sb.toString();
    }

    public boolean equals(@CheckForNull Object obj) {
        if (obj instanceof Murmur3_32HashFunction) {
            Murmur3_32HashFunction murmur3_32HashFunction = (Murmur3_32HashFunction) obj;
            return this.seed == murmur3_32HashFunction.seed && this.supplementaryPlaneFix == murmur3_32HashFunction.supplementaryPlaneFix;
        }
        return false;
    }

    public int hashCode() {
        return getClass().hashCode() ^ this.seed;
    }

    @Override // com.google.common.hash.AbstractHashFunction, com.google.common.hash.HashFunction
    public HashCode hashInt(int r2) {
        return fmix(mixH1(this.seed, mixK1(r2)), 4);
    }

    @Override // com.google.common.hash.AbstractHashFunction, com.google.common.hash.HashFunction
    public HashCode hashLong(long j) {
        int r4 = (int) (j >>> 32);
        return fmix(mixH1(mixH1(this.seed, mixK1((int) j)), mixK1(r4)), 8);
    }

    @Override // com.google.common.hash.AbstractHashFunction, com.google.common.hash.HashFunction
    public HashCode hashUnencodedChars(CharSequence charSequence) {
        int r0 = this.seed;
        for (int r2 = 1; r2 < charSequence.length(); r2 += 2) {
            r0 = mixH1(r0, mixK1(charSequence.charAt(r2 - 1) | (charSequence.charAt(r2) << 16)));
        }
        if ((charSequence.length() & 1) == 1) {
            r0 ^= mixK1(charSequence.charAt(charSequence.length() - 1));
        }
        return fmix(r0, charSequence.length() * 2);
    }

    @Override // com.google.common.hash.AbstractHashFunction, com.google.common.hash.HashFunction
    public HashCode hashString(CharSequence charSequence, Charset charset) {
        if (Charsets.UTF_8.equals(charset)) {
            int length = charSequence.length();
            int r1 = this.seed;
            int r2 = 0;
            int r3 = 0;
            int r4 = 0;
            while (true) {
                int r5 = r3 + 4;
                if (r5 > length) {
                    break;
                }
                char charAt = charSequence.charAt(r3);
                char charAt2 = charSequence.charAt(r3 + 1);
                char charAt3 = charSequence.charAt(r3 + 2);
                char charAt4 = charSequence.charAt(r3 + 3);
                if (charAt >= 128 || charAt2 >= 128 || charAt3 >= 128 || charAt4 >= 128) {
                    break;
                }
                r1 = mixH1(r1, mixK1((charAt2 << '\b') | charAt | (charAt3 << 16) | (charAt4 << 24)));
                r4 += 4;
                r3 = r5;
            }
            long j = 0;
            while (r3 < length) {
                char charAt5 = charSequence.charAt(r3);
                if (charAt5 < 128) {
                    j |= charAt5 << r2;
                    r2 += 8;
                    r4++;
                } else if (charAt5 < 2048) {
                    j |= charToTwoUtf8Bytes(charAt5) << r2;
                    r2 += 16;
                    r4 += 2;
                } else if (charAt5 < 55296 || charAt5 > 57343) {
                    j |= charToThreeUtf8Bytes(charAt5) << r2;
                    r2 += 24;
                    r4 += 3;
                } else {
                    int codePointAt = Character.codePointAt(charSequence, r3);
                    if (codePointAt == charAt5) {
                        return hashBytes(charSequence.toString().getBytes(charset));
                    }
                    r3++;
                    j |= codePointToFourUtf8Bytes(codePointAt) << r2;
                    if (this.supplementaryPlaneFix) {
                        r2 += 32;
                    }
                    r4 += 4;
                }
                if (r2 >= 32) {
                    r1 = mixH1(r1, mixK1((int) j));
                    j >>>= 32;
                    r2 -= 32;
                }
                r3++;
            }
            return fmix(mixK1((int) j) ^ r1, r4);
        }
        return hashBytes(charSequence.toString().getBytes(charset));
    }

    @Override // com.google.common.hash.AbstractHashFunction, com.google.common.hash.HashFunction
    public HashCode hashBytes(byte[] bArr, int r7, int r8) {
        Preconditions.checkPositionIndexes(r7, r7 + r8, bArr.length);
        int r0 = this.seed;
        int r1 = 0;
        int r2 = 0;
        while (true) {
            int r3 = r2 + 4;
            if (r3 > r8) {
                break;
            }
            r0 = mixH1(r0, mixK1(getIntLittleEndian(bArr, r2 + r7)));
            r2 = r3;
        }
        int r32 = r2;
        int r22 = 0;
        while (r32 < r8) {
            r1 ^= UnsignedBytes.toInt(bArr[r7 + r32]) << r22;
            r32++;
            r22 += 8;
        }
        return fmix(mixK1(r1) ^ r0, r8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getIntLittleEndian(byte[] bArr, int r4) {
        return Ints.fromBytes(bArr[r4 + 3], bArr[r4 + 2], bArr[r4 + 1], bArr[r4]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int mixK1(int r1) {
        return Integer.rotateLeft(r1 * f1168C1, 15) * f1169C2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int mixH1(int r0, int r1) {
        return (Integer.rotateLeft(r0 ^ r1, 13) * 5) - 430675100;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static HashCode fmix(int r0, int r1) {
        int r02 = r0 ^ r1;
        int r03 = (r02 ^ (r02 >>> 16)) * (-2048144789);
        int r04 = (r03 ^ (r03 >>> 13)) * (-1028477387);
        return HashCode.fromInt(r04 ^ (r04 >>> 16));
    }

    /* loaded from: classes3.dex */
    private static final class Murmur3_32Hasher extends AbstractHasher {
        private long buffer;

        /* renamed from: h1 */
        private int f1170h1;
        private int shift;
        private int length = 0;
        private boolean isDone = false;

        Murmur3_32Hasher(int r1) {
            this.f1170h1 = r1;
        }

        private void update(int r5, long j) {
            long j2 = this.buffer;
            long j3 = j & BodyPartID.bodyIdMax;
            int r2 = this.shift;
            long j4 = (j3 << r2) | j2;
            this.buffer = j4;
            int r22 = r2 + (r5 * 8);
            this.shift = r22;
            this.length += r5;
            if (r22 >= 32) {
                this.f1170h1 = Murmur3_32HashFunction.mixH1(this.f1170h1, Murmur3_32HashFunction.mixK1((int) j4));
                this.buffer >>>= 32;
                this.shift -= 32;
            }
        }

        @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
        public Hasher putByte(byte b) {
            update(1, b & 255);
            return this;
        }

        @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.PrimitiveSink
        public Hasher putBytes(byte[] bArr, int r6, int r7) {
            Preconditions.checkPositionIndexes(r6, r6 + r7, bArr.length);
            int r0 = 0;
            while (true) {
                int r1 = r0 + 4;
                if (r1 > r7) {
                    break;
                }
                update(4, Murmur3_32HashFunction.getIntLittleEndian(bArr, r0 + r6));
                r0 = r1;
            }
            while (r0 < r7) {
                putByte(bArr[r6 + r0]);
                r0++;
            }
            return this;
        }

        @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.PrimitiveSink
        public Hasher putBytes(ByteBuffer byteBuffer) {
            ByteOrder order = byteBuffer.order();
            byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
            while (byteBuffer.remaining() >= 4) {
                putInt(byteBuffer.getInt());
            }
            while (byteBuffer.hasRemaining()) {
                putByte(byteBuffer.get());
            }
            byteBuffer.order(order);
            return this;
        }

        @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.PrimitiveSink
        public Hasher putInt(int r3) {
            update(4, r3);
            return this;
        }

        @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.PrimitiveSink
        public Hasher putLong(long j) {
            update(4, (int) j);
            update(4, j >>> 32);
            return this;
        }

        @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.PrimitiveSink
        public Hasher putChar(char c) {
            update(2, c);
            return this;
        }

        @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.PrimitiveSink
        public Hasher putString(CharSequence charSequence, Charset charset) {
            if (Charsets.UTF_8.equals(charset)) {
                int length = charSequence.length();
                int r1 = 0;
                while (true) {
                    int r2 = r1 + 4;
                    if (r2 > length) {
                        break;
                    }
                    char charAt = charSequence.charAt(r1);
                    char charAt2 = charSequence.charAt(r1 + 1);
                    char charAt3 = charSequence.charAt(r1 + 2);
                    char charAt4 = charSequence.charAt(r1 + 3);
                    if (charAt >= 128 || charAt2 >= 128 || charAt3 >= 128 || charAt4 >= 128) {
                        break;
                    }
                    update(4, (charAt2 << '\b') | charAt | (charAt3 << 16) | (charAt4 << 24));
                    r1 = r2;
                }
                while (r1 < length) {
                    char charAt5 = charSequence.charAt(r1);
                    if (charAt5 < 128) {
                        update(1, charAt5);
                    } else if (charAt5 < 2048) {
                        update(2, Murmur3_32HashFunction.charToTwoUtf8Bytes(charAt5));
                    } else if (charAt5 < 55296 || charAt5 > 57343) {
                        update(3, Murmur3_32HashFunction.charToThreeUtf8Bytes(charAt5));
                    } else {
                        int codePointAt = Character.codePointAt(charSequence, r1);
                        if (codePointAt != charAt5) {
                            r1++;
                            update(4, Murmur3_32HashFunction.codePointToFourUtf8Bytes(codePointAt));
                        } else {
                            putBytes(charSequence.subSequence(r1, length).toString().getBytes(charset));
                            return this;
                        }
                    }
                    r1++;
                }
                return this;
            }
            return super.putString(charSequence, charset);
        }

        @Override // com.google.common.hash.Hasher
        public HashCode hash() {
            Preconditions.checkState(!this.isDone);
            this.isDone = true;
            int mixK1 = this.f1170h1 ^ Murmur3_32HashFunction.mixK1((int) this.buffer);
            this.f1170h1 = mixK1;
            return Murmur3_32HashFunction.fmix(mixK1, this.length);
        }
    }
}
