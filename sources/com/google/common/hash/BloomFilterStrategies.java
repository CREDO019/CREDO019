package com.google.common.hash;

import com.google.common.base.Preconditions;
import com.google.common.hash.BloomFilter;
import com.google.common.math.LongMath;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLongArray;
import javax.annotation.CheckForNull;

/* JADX INFO: Access modifiers changed from: package-private */
@ElementTypesAreNonnullByDefault
/* loaded from: classes3.dex */
public enum BloomFilterStrategies implements BloomFilter.Strategy {
    MURMUR128_MITZ_32 { // from class: com.google.common.hash.BloomFilterStrategies.1
        @Override // com.google.common.hash.BloomFilter.Strategy
        public <T> boolean put(@ParametricNullness T t, Funnel<? super T> funnel, int r9, LockFreeBitArray lockFreeBitArray) {
            long bitSize = lockFreeBitArray.bitSize();
            long asLong = Hashing.murmur3_128().hashObject(t, funnel).asLong();
            int r2 = (int) asLong;
            int r8 = (int) (asLong >>> 32);
            boolean z = false;
            for (int r7 = 1; r7 <= r9; r7++) {
                int r4 = (r7 * r8) + r2;
                if (r4 < 0) {
                    r4 = ~r4;
                }
                z |= lockFreeBitArray.set(r4 % bitSize);
            }
            return z;
        }

        @Override // com.google.common.hash.BloomFilter.Strategy
        public <T> boolean mightContain(@ParametricNullness T t, Funnel<? super T> funnel, int r9, LockFreeBitArray lockFreeBitArray) {
            long bitSize = lockFreeBitArray.bitSize();
            long asLong = Hashing.murmur3_128().hashObject(t, funnel).asLong();
            int r2 = (int) asLong;
            int r8 = (int) (asLong >>> 32);
            for (int r3 = 1; r3 <= r9; r3++) {
                int r4 = (r3 * r8) + r2;
                if (r4 < 0) {
                    r4 = ~r4;
                }
                if (!lockFreeBitArray.get(r4 % bitSize)) {
                    return false;
                }
            }
            return true;
        }
    },
    MURMUR128_MITZ_64 { // from class: com.google.common.hash.BloomFilterStrategies.2
        @Override // com.google.common.hash.BloomFilter.Strategy
        public <T> boolean put(@ParametricNullness T t, Funnel<? super T> funnel, int r11, LockFreeBitArray lockFreeBitArray) {
            long bitSize = lockFreeBitArray.bitSize();
            byte[] bytesInternal = Hashing.murmur3_128().hashObject(t, funnel).getBytesInternal();
            long lowerEight = lowerEight(bytesInternal);
            long upperEight = upperEight(bytesInternal);
            boolean z = false;
            for (int r4 = 0; r4 < r11; r4++) {
                z |= lockFreeBitArray.set((Long.MAX_VALUE & lowerEight) % bitSize);
                lowerEight += upperEight;
            }
            return z;
        }

        @Override // com.google.common.hash.BloomFilter.Strategy
        public <T> boolean mightContain(@ParametricNullness T t, Funnel<? super T> funnel, int r11, LockFreeBitArray lockFreeBitArray) {
            long bitSize = lockFreeBitArray.bitSize();
            byte[] bytesInternal = Hashing.murmur3_128().hashObject(t, funnel).getBytesInternal();
            long lowerEight = lowerEight(bytesInternal);
            long upperEight = upperEight(bytesInternal);
            for (int r5 = 0; r5 < r11; r5++) {
                if (!lockFreeBitArray.get((Long.MAX_VALUE & lowerEight) % bitSize)) {
                    return false;
                }
                lowerEight += upperEight;
            }
            return true;
        }

        private long lowerEight(byte[] bArr) {
            return Longs.fromBytes(bArr[7], bArr[6], bArr[5], bArr[4], bArr[3], bArr[2], bArr[1], bArr[0]);
        }

        private long upperEight(byte[] bArr) {
            return Longs.fromBytes(bArr[15], bArr[14], bArr[13], bArr[12], bArr[11], bArr[10], bArr[9], bArr[8]);
        }
    };

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static final class LockFreeBitArray {
        private static final int LONG_ADDRESSABLE_BITS = 6;
        private final LongAddable bitCount;
        final AtomicLongArray data;

        /* JADX INFO: Access modifiers changed from: package-private */
        public LockFreeBitArray(long j) {
            Preconditions.checkArgument(j > 0, "data length is zero!");
            this.data = new AtomicLongArray(Ints.checkedCast(LongMath.divide(j, 64L, RoundingMode.CEILING)));
            this.bitCount = LongAddables.create();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public LockFreeBitArray(long[] jArr) {
            Preconditions.checkArgument(jArr.length > 0, "data length is zero!");
            this.data = new AtomicLongArray(jArr);
            this.bitCount = LongAddables.create();
            long j = 0;
            for (long j2 : jArr) {
                j += Long.bitCount(j2);
            }
            this.bitCount.add(j);
        }

        boolean set(long j) {
            long j2;
            long j3;
            if (get(j)) {
                return false;
            }
            int r0 = (int) (j >>> 6);
            long j4 = 1 << ((int) j);
            do {
                j2 = this.data.get(r0);
                j3 = j2 | j4;
                if (j2 == j3) {
                    return false;
                }
            } while (!this.data.compareAndSet(r0, j2, j3));
            this.bitCount.increment();
            return true;
        }

        boolean get(long j) {
            return ((1 << ((int) j)) & this.data.get((int) (j >>> 6))) != 0;
        }

        public static long[] toPlainArray(AtomicLongArray atomicLongArray) {
            int length = atomicLongArray.length();
            long[] jArr = new long[length];
            for (int r2 = 0; r2 < length; r2++) {
                jArr[r2] = atomicLongArray.get(r2);
            }
            return jArr;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public long bitSize() {
            return this.data.length() * 64;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public long bitCount() {
            return this.bitCount.sum();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public LockFreeBitArray copy() {
            return new LockFreeBitArray(toPlainArray(this.data));
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void putAll(LockFreeBitArray lockFreeBitArray) {
            long j;
            long j2;
            boolean z;
            Preconditions.checkArgument(this.data.length() == lockFreeBitArray.data.length(), "BitArrays must be of equal length (%s != %s)", this.data.length(), lockFreeBitArray.data.length());
            for (int r2 = 0; r2 < this.data.length(); r2++) {
                long j3 = lockFreeBitArray.data.get(r2);
                while (true) {
                    j = this.data.get(r2);
                    j2 = j | j3;
                    if (j == j2) {
                        z = false;
                        break;
                    } else if (this.data.compareAndSet(r2, j, j2)) {
                        z = true;
                        break;
                    }
                }
                if (z) {
                    this.bitCount.add(Long.bitCount(j2) - Long.bitCount(j));
                }
            }
        }

        public boolean equals(@CheckForNull Object obj) {
            if (obj instanceof LockFreeBitArray) {
                return Arrays.equals(toPlainArray(this.data), toPlainArray(((LockFreeBitArray) obj).data));
            }
            return false;
        }

        public int hashCode() {
            return Arrays.hashCode(toPlainArray(this.data));
        }
    }
}
