package com.google.common.hash;

import com.google.common.base.Ascii;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Ints;
import com.google.common.primitives.UnsignedInts;
import java.io.Serializable;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
/* loaded from: classes3.dex */
public abstract class HashCode {
    private static final char[] hexDigits = "0123456789abcdef".toCharArray();

    public abstract byte[] asBytes();

    public abstract int asInt();

    public abstract long asLong();

    public abstract int bits();

    abstract boolean equalsSameBits(HashCode hashCode);

    public abstract long padToLong();

    abstract void writeBytesToImpl(byte[] bArr, int r2, int r3);

    HashCode() {
    }

    public int writeBytesTo(byte[] bArr, int r4, int r5) {
        int min = Ints.min(r5, bits() / 8);
        Preconditions.checkPositionIndexes(r4, r4 + min, bArr.length);
        writeBytesToImpl(bArr, r4, min);
        return min;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] getBytesInternal() {
        return asBytes();
    }

    public static HashCode fromInt(int r1) {
        return new IntHashCode(r1);
    }

    /* loaded from: classes3.dex */
    private static final class IntHashCode extends HashCode implements Serializable {
        private static final long serialVersionUID = 0;
        final int hash;

        @Override // com.google.common.hash.HashCode
        public int bits() {
            return 32;
        }

        IntHashCode(int r1) {
            this.hash = r1;
        }

        @Override // com.google.common.hash.HashCode
        public byte[] asBytes() {
            int r1 = this.hash;
            return new byte[]{(byte) r1, (byte) (r1 >> 8), (byte) (r1 >> 16), (byte) (r1 >> 24)};
        }

        @Override // com.google.common.hash.HashCode
        public int asInt() {
            return this.hash;
        }

        @Override // com.google.common.hash.HashCode
        public long asLong() {
            throw new IllegalStateException("this HashCode only has 32 bits; cannot create a long");
        }

        @Override // com.google.common.hash.HashCode
        public long padToLong() {
            return UnsignedInts.toLong(this.hash);
        }

        @Override // com.google.common.hash.HashCode
        void writeBytesToImpl(byte[] bArr, int r6, int r7) {
            for (int r0 = 0; r0 < r7; r0++) {
                bArr[r6 + r0] = (byte) (this.hash >> (r0 * 8));
            }
        }

        @Override // com.google.common.hash.HashCode
        boolean equalsSameBits(HashCode hashCode) {
            return this.hash == hashCode.asInt();
        }
    }

    public static HashCode fromLong(long j) {
        return new LongHashCode(j);
    }

    /* loaded from: classes3.dex */
    private static final class LongHashCode extends HashCode implements Serializable {
        private static final long serialVersionUID = 0;
        final long hash;

        @Override // com.google.common.hash.HashCode
        public int bits() {
            return 64;
        }

        LongHashCode(long j) {
            this.hash = j;
        }

        @Override // com.google.common.hash.HashCode
        public byte[] asBytes() {
            long j = this.hash;
            return new byte[]{(byte) j, (byte) (j >> 8), (byte) (j >> 16), (byte) (j >> 24), (byte) (j >> 32), (byte) (j >> 40), (byte) (j >> 48), (byte) (j >> 56)};
        }

        @Override // com.google.common.hash.HashCode
        public int asInt() {
            return (int) this.hash;
        }

        @Override // com.google.common.hash.HashCode
        public long asLong() {
            return this.hash;
        }

        @Override // com.google.common.hash.HashCode
        public long padToLong() {
            return this.hash;
        }

        @Override // com.google.common.hash.HashCode
        void writeBytesToImpl(byte[] bArr, int r7, int r8) {
            for (int r0 = 0; r0 < r8; r0++) {
                bArr[r7 + r0] = (byte) (this.hash >> (r0 * 8));
            }
        }

        @Override // com.google.common.hash.HashCode
        boolean equalsSameBits(HashCode hashCode) {
            return this.hash == hashCode.asLong();
        }
    }

    public static HashCode fromBytes(byte[] bArr) {
        Preconditions.checkArgument(bArr.length >= 1, "A HashCode must contain at least 1 byte.");
        return fromBytesNoCopy((byte[]) bArr.clone());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static HashCode fromBytesNoCopy(byte[] bArr) {
        return new BytesHashCode(bArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static final class BytesHashCode extends HashCode implements Serializable {
        private static final long serialVersionUID = 0;
        final byte[] bytes;

        BytesHashCode(byte[] bArr) {
            this.bytes = (byte[]) Preconditions.checkNotNull(bArr);
        }

        @Override // com.google.common.hash.HashCode
        public int bits() {
            return this.bytes.length * 8;
        }

        @Override // com.google.common.hash.HashCode
        public byte[] asBytes() {
            return (byte[]) this.bytes.clone();
        }

        @Override // com.google.common.hash.HashCode
        public int asInt() {
            byte[] bArr = this.bytes;
            Preconditions.checkState(bArr.length >= 4, "HashCode#asInt() requires >= 4 bytes (it only has %s bytes).", bArr.length);
            byte[] bArr2 = this.bytes;
            return ((bArr2[3] & 255) << 24) | (bArr2[0] & 255) | ((bArr2[1] & 255) << 8) | ((bArr2[2] & 255) << 16);
        }

        @Override // com.google.common.hash.HashCode
        public long asLong() {
            byte[] bArr = this.bytes;
            Preconditions.checkState(bArr.length >= 8, "HashCode#asLong() requires >= 8 bytes (it only has %s bytes).", bArr.length);
            return padToLong();
        }

        @Override // com.google.common.hash.HashCode
        public long padToLong() {
            long j = this.bytes[0] & 255;
            for (int r2 = 1; r2 < Math.min(this.bytes.length, 8); r2++) {
                j |= (this.bytes[r2] & 255) << (r2 * 8);
            }
            return j;
        }

        @Override // com.google.common.hash.HashCode
        void writeBytesToImpl(byte[] bArr, int r4, int r5) {
            System.arraycopy(this.bytes, 0, bArr, r4, r5);
        }

        @Override // com.google.common.hash.HashCode
        byte[] getBytesInternal() {
            return this.bytes;
        }

        @Override // com.google.common.hash.HashCode
        boolean equalsSameBits(HashCode hashCode) {
            if (this.bytes.length != hashCode.getBytesInternal().length) {
                return false;
            }
            int r1 = 0;
            boolean z = true;
            while (true) {
                byte[] bArr = this.bytes;
                if (r1 >= bArr.length) {
                    return z;
                }
                z &= bArr[r1] == hashCode.getBytesInternal()[r1];
                r1++;
            }
        }
    }

    public static HashCode fromString(String str) {
        Preconditions.checkArgument(str.length() >= 2, "input string (%s) must have at least 2 characters", str);
        Preconditions.checkArgument(str.length() % 2 == 0, "input string (%s) must have an even number of characters", str);
        byte[] bArr = new byte[str.length() / 2];
        for (int r2 = 0; r2 < str.length(); r2 += 2) {
            bArr[r2 / 2] = (byte) ((decode(str.charAt(r2)) << 4) + decode(str.charAt(r2 + 1)));
        }
        return fromBytesNoCopy(bArr);
    }

    private static int decode(char c) {
        if (c < '0' || c > '9') {
            if (c < 'a' || c > 'f') {
                StringBuilder sb = new StringBuilder(32);
                sb.append("Illegal hexadecimal character: ");
                sb.append(c);
                throw new IllegalArgumentException(sb.toString());
            }
            return (c - 'a') + 10;
        }
        return c - '0';
    }

    public final boolean equals(@CheckForNull Object obj) {
        if (obj instanceof HashCode) {
            HashCode hashCode = (HashCode) obj;
            return bits() == hashCode.bits() && equalsSameBits(hashCode);
        }
        return false;
    }

    public final int hashCode() {
        if (bits() >= 32) {
            return asInt();
        }
        byte[] bytesInternal = getBytesInternal();
        int r1 = bytesInternal[0] & 255;
        for (int r2 = 1; r2 < bytesInternal.length; r2++) {
            r1 |= (bytesInternal[r2] & 255) << (r2 * 8);
        }
        return r1;
    }

    public final String toString() {
        byte[] bytesInternal = getBytesInternal();
        StringBuilder sb = new StringBuilder(bytesInternal.length * 2);
        for (byte b : bytesInternal) {
            char[] cArr = hexDigits;
            sb.append(cArr[(b >> 4) & 15]);
            sb.append(cArr[b & Ascii.f1128SI]);
        }
        return sb.toString();
    }
}
