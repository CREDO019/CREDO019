package com.google.common.primitives;

import com.google.common.base.Preconditions;
import java.util.Arrays;
import java.util.Comparator;

@ElementTypesAreNonnullByDefault
/* loaded from: classes3.dex */
public final class UnsignedInts {
    static final long INT_MASK = 4294967295L;

    static int flip(int r1) {
        return r1 ^ Integer.MIN_VALUE;
    }

    public static int saturatedCast(long j) {
        if (j <= 0) {
            return 0;
        }
        if (j >= 4294967296L) {
            return -1;
        }
        return (int) j;
    }

    public static long toLong(int r4) {
        return r4 & 4294967295L;
    }

    private UnsignedInts() {
    }

    public static int compare(int r0, int r1) {
        return Ints.compare(flip(r0), flip(r1));
    }

    public static int checkedCast(long j) {
        Preconditions.checkArgument((j >> 32) == 0, "out of range: %s", j);
        return (int) j;
    }

    public static int min(int... r3) {
        Preconditions.checkArgument(r3.length > 0);
        int flip = flip(r3[0]);
        for (int r2 = 1; r2 < r3.length; r2++) {
            int flip2 = flip(r3[r2]);
            if (flip2 < flip) {
                flip = flip2;
            }
        }
        return flip(flip);
    }

    public static int max(int... r3) {
        Preconditions.checkArgument(r3.length > 0);
        int flip = flip(r3[0]);
        for (int r2 = 1; r2 < r3.length; r2++) {
            int flip2 = flip(r3[r2]);
            if (flip2 > flip) {
                flip = flip2;
            }
        }
        return flip(flip);
    }

    public static String join(String str, int... r4) {
        Preconditions.checkNotNull(str);
        if (r4.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(r4.length * 5);
        sb.append(toString(r4[0]));
        for (int r1 = 1; r1 < r4.length; r1++) {
            sb.append(str);
            sb.append(toString(r4[r1]));
        }
        return sb.toString();
    }

    public static Comparator<int[]> lexicographicalComparator() {
        return LexicographicalComparator.INSTANCE;
    }

    /* loaded from: classes3.dex */
    enum LexicographicalComparator implements Comparator<int[]> {
        INSTANCE;

        @Override // java.lang.Enum
        public String toString() {
            return "UnsignedInts.lexicographicalComparator()";
        }

        @Override // java.util.Comparator
        public int compare(int[] r5, int[] r6) {
            int min = Math.min(r5.length, r6.length);
            for (int r1 = 0; r1 < min; r1++) {
                if (r5[r1] != r6[r1]) {
                    return UnsignedInts.compare(r5[r1], r6[r1]);
                }
            }
            return r5.length - r6.length;
        }
    }

    public static void sort(int[] r2) {
        Preconditions.checkNotNull(r2);
        sort(r2, 0, r2.length);
    }

    public static void sort(int[] r2, int r3, int r4) {
        Preconditions.checkNotNull(r2);
        Preconditions.checkPositionIndexes(r3, r4, r2.length);
        for (int r0 = r3; r0 < r4; r0++) {
            r2[r0] = flip(r2[r0]);
        }
        Arrays.sort(r2, r3, r4);
        while (r3 < r4) {
            r2[r3] = flip(r2[r3]);
            r3++;
        }
    }

    public static void sortDescending(int[] r2) {
        Preconditions.checkNotNull(r2);
        sortDescending(r2, 0, r2.length);
    }

    public static void sortDescending(int[] r3, int r4, int r5) {
        Preconditions.checkNotNull(r3);
        Preconditions.checkPositionIndexes(r4, r5, r3.length);
        for (int r0 = r4; r0 < r5; r0++) {
            r3[r0] = Integer.MAX_VALUE ^ r3[r0];
        }
        Arrays.sort(r3, r4, r5);
        while (r4 < r5) {
            r3[r4] = r3[r4] ^ Integer.MAX_VALUE;
            r4++;
        }
    }

    public static int divide(int r2, int r3) {
        return (int) (toLong(r2) / toLong(r3));
    }

    public static int remainder(int r2, int r3) {
        return (int) (toLong(r2) % toLong(r3));
    }

    public static int decode(String str) {
        ParseRequest fromString = ParseRequest.fromString(str);
        try {
            return parseUnsignedInt(fromString.rawValue, fromString.radix);
        } catch (NumberFormatException e) {
            String valueOf = String.valueOf(str);
            NumberFormatException numberFormatException = new NumberFormatException(valueOf.length() != 0 ? "Error parsing value: ".concat(valueOf) : new String("Error parsing value: "));
            numberFormatException.initCause(e);
            throw numberFormatException;
        }
    }

    public static int parseUnsignedInt(String str) {
        return parseUnsignedInt(str, 10);
    }

    public static int parseUnsignedInt(String str, int r6) {
        Preconditions.checkNotNull(str);
        long parseLong = Long.parseLong(str, r6);
        if ((4294967295L & parseLong) == parseLong) {
            return (int) parseLong;
        }
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 69);
        sb.append("Input ");
        sb.append(str);
        sb.append(" in base ");
        sb.append(r6);
        sb.append(" is not in the range of an unsigned integer");
        throw new NumberFormatException(sb.toString());
    }

    public static String toString(int r1) {
        return toString(r1, 10);
    }

    public static String toString(int r4, int r5) {
        return Long.toString(r4 & 4294967295L, r5);
    }
}
