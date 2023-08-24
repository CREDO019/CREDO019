package com.google.common.primitives;

import com.google.common.base.Preconditions;
import java.util.Arrays;
import java.util.Comparator;

@ElementTypesAreNonnullByDefault
/* loaded from: classes3.dex */
public final class SignedBytes {
    public static final byte MAX_POWER_OF_TWO = 64;

    public static int compare(byte b, byte b2) {
        return b - b2;
    }

    public static byte saturatedCast(long j) {
        if (j > 127) {
            return Byte.MAX_VALUE;
        }
        if (j < -128) {
            return Byte.MIN_VALUE;
        }
        return (byte) j;
    }

    private SignedBytes() {
    }

    public static byte checkedCast(long j) {
        byte b = (byte) j;
        Preconditions.checkArgument(((long) b) == j, "Out of range: %s", j);
        return b;
    }

    public static byte min(byte... bArr) {
        Preconditions.checkArgument(bArr.length > 0);
        byte b = bArr[0];
        for (int r2 = 1; r2 < bArr.length; r2++) {
            if (bArr[r2] < b) {
                b = bArr[r2];
            }
        }
        return b;
    }

    public static byte max(byte... bArr) {
        Preconditions.checkArgument(bArr.length > 0);
        byte b = bArr[0];
        for (int r2 = 1; r2 < bArr.length; r2++) {
            if (bArr[r2] > b) {
                b = bArr[r2];
            }
        }
        return b;
    }

    public static String join(String str, byte... bArr) {
        Preconditions.checkNotNull(str);
        if (bArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(bArr.length * 5);
        sb.append((int) bArr[0]);
        for (int r1 = 1; r1 < bArr.length; r1++) {
            sb.append(str);
            sb.append((int) bArr[r1]);
        }
        return sb.toString();
    }

    public static Comparator<byte[]> lexicographicalComparator() {
        return LexicographicalComparator.INSTANCE;
    }

    /* loaded from: classes3.dex */
    private enum LexicographicalComparator implements Comparator<byte[]> {
        INSTANCE;

        @Override // java.lang.Enum
        public String toString() {
            return "SignedBytes.lexicographicalComparator()";
        }

        @Override // java.util.Comparator
        public int compare(byte[] bArr, byte[] bArr2) {
            int min = Math.min(bArr.length, bArr2.length);
            for (int r1 = 0; r1 < min; r1++) {
                int compare = SignedBytes.compare(bArr[r1], bArr2[r1]);
                if (compare != 0) {
                    return compare;
                }
            }
            return bArr.length - bArr2.length;
        }
    }

    public static void sortDescending(byte[] bArr) {
        Preconditions.checkNotNull(bArr);
        sortDescending(bArr, 0, bArr.length);
    }

    public static void sortDescending(byte[] bArr, int r2, int r3) {
        Preconditions.checkNotNull(bArr);
        Preconditions.checkPositionIndexes(r2, r3, bArr.length);
        Arrays.sort(bArr, r2, r3);
        Bytes.reverse(bArr, r2, r3);
    }
}
