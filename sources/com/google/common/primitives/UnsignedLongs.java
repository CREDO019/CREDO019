package com.google.common.primitives;

import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import com.google.common.base.Preconditions;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Comparator;

@ElementTypesAreNonnullByDefault
/* loaded from: classes3.dex */
public final class UnsignedLongs {
    public static final long MAX_VALUE = -1;

    private static long flip(long j) {
        return j ^ Long.MIN_VALUE;
    }

    private UnsignedLongs() {
    }

    public static int compare(long j, long j2) {
        return Longs.compare(flip(j), flip(j2));
    }

    public static long min(long... jArr) {
        Preconditions.checkArgument(jArr.length > 0);
        long flip = flip(jArr[0]);
        for (int r2 = 1; r2 < jArr.length; r2++) {
            long flip2 = flip(jArr[r2]);
            if (flip2 < flip) {
                flip = flip2;
            }
        }
        return flip(flip);
    }

    public static long max(long... jArr) {
        Preconditions.checkArgument(jArr.length > 0);
        long flip = flip(jArr[0]);
        for (int r2 = 1; r2 < jArr.length; r2++) {
            long flip2 = flip(jArr[r2]);
            if (flip2 > flip) {
                flip = flip2;
            }
        }
        return flip(flip);
    }

    public static String join(String str, long... jArr) {
        Preconditions.checkNotNull(str);
        if (jArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(jArr.length * 5);
        sb.append(toString(jArr[0]));
        for (int r1 = 1; r1 < jArr.length; r1++) {
            sb.append(str);
            sb.append(toString(jArr[r1]));
        }
        return sb.toString();
    }

    public static Comparator<long[]> lexicographicalComparator() {
        return LexicographicalComparator.INSTANCE;
    }

    /* loaded from: classes3.dex */
    enum LexicographicalComparator implements Comparator<long[]> {
        INSTANCE;

        @Override // java.lang.Enum
        public String toString() {
            return "UnsignedLongs.lexicographicalComparator()";
        }

        @Override // java.util.Comparator
        public int compare(long[] jArr, long[] jArr2) {
            int min = Math.min(jArr.length, jArr2.length);
            for (int r1 = 0; r1 < min; r1++) {
                if (jArr[r1] != jArr2[r1]) {
                    return UnsignedLongs.compare(jArr[r1], jArr2[r1]);
                }
            }
            return jArr.length - jArr2.length;
        }
    }

    public static void sort(long[] jArr) {
        Preconditions.checkNotNull(jArr);
        sort(jArr, 0, jArr.length);
    }

    public static void sort(long[] jArr, int r4, int r5) {
        Preconditions.checkNotNull(jArr);
        Preconditions.checkPositionIndexes(r4, r5, jArr.length);
        for (int r0 = r4; r0 < r5; r0++) {
            jArr[r0] = flip(jArr[r0]);
        }
        Arrays.sort(jArr, r4, r5);
        while (r4 < r5) {
            jArr[r4] = flip(jArr[r4]);
            r4++;
        }
    }

    public static void sortDescending(long[] jArr) {
        Preconditions.checkNotNull(jArr);
        sortDescending(jArr, 0, jArr.length);
    }

    public static void sortDescending(long[] jArr, int r6, int r7) {
        Preconditions.checkNotNull(jArr);
        Preconditions.checkPositionIndexes(r6, r7, jArr.length);
        for (int r0 = r6; r0 < r7; r0++) {
            jArr[r0] = Long.MAX_VALUE ^ jArr[r0];
        }
        Arrays.sort(jArr, r6, r7);
        while (r6 < r7) {
            jArr[r6] = jArr[r6] ^ Long.MAX_VALUE;
            r6++;
        }
    }

    public static long divide(long j, long j2) {
        if (j2 < 0) {
            return compare(j, j2) < 0 ? 0L : 1L;
        } else if (j >= 0) {
            return j / j2;
        } else {
            long j3 = ((j >>> 1) / j2) << 1;
            return j3 + (compare(j - (j3 * j2), j2) < 0 ? 0 : 1);
        }
    }

    public static long remainder(long j, long j2) {
        if (j2 < 0) {
            return compare(j, j2) < 0 ? j : j - j2;
        } else if (j >= 0) {
            return j % j2;
        } else {
            long j3 = j - ((((j >>> 1) / j2) << 1) * j2);
            if (compare(j3, j2) < 0) {
                j2 = 0;
            }
            return j3 - j2;
        }
    }

    public static long parseUnsignedLong(String str) {
        return parseUnsignedLong(str, 10);
    }

    public static long parseUnsignedLong(String str, int r8) {
        Preconditions.checkNotNull(str);
        if (str.length() != 0) {
            if (r8 < 2 || r8 > 36) {
                StringBuilder sb = new StringBuilder(26);
                sb.append("illegal radix: ");
                sb.append(r8);
                throw new NumberFormatException(sb.toString());
            }
            int r0 = ParseOverflowDetection.maxSafeDigits[r8] - 1;
            long j = 0;
            for (int r3 = 0; r3 < str.length(); r3++) {
                int digit = Character.digit(str.charAt(r3), r8);
                if (digit == -1) {
                    throw new NumberFormatException(str);
                }
                if (r3 > r0 && ParseOverflowDetection.overflowInParse(j, digit, r8)) {
                    String valueOf = String.valueOf(str);
                    throw new NumberFormatException(valueOf.length() != 0 ? "Too large for unsigned long: ".concat(valueOf) : new String("Too large for unsigned long: "));
                }
                j = (j * r8) + digit;
            }
            return j;
        }
        throw new NumberFormatException("empty string");
    }

    public static long decode(String str) {
        ParseRequest fromString = ParseRequest.fromString(str);
        try {
            return parseUnsignedLong(fromString.rawValue, fromString.radix);
        } catch (NumberFormatException e) {
            String valueOf = String.valueOf(str);
            NumberFormatException numberFormatException = new NumberFormatException(valueOf.length() != 0 ? "Error parsing value: ".concat(valueOf) : new String("Error parsing value: "));
            numberFormatException.initCause(e);
            throw numberFormatException;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static final class ParseOverflowDetection {
        static final long[] maxValueDivs = new long[37];
        static final int[] maxValueMods = new int[37];
        static final int[] maxSafeDigits = new int[37];

        private ParseOverflowDetection() {
        }

        static {
            BigInteger bigInteger = new BigInteger("10000000000000000", 16);
            for (int r1 = 2; r1 <= 36; r1++) {
                long j = r1;
                maxValueDivs[r1] = UnsignedLongs.divide(-1L, j);
                maxValueMods[r1] = (int) UnsignedLongs.remainder(-1L, j);
                maxSafeDigits[r1] = bigInteger.toString(r1).length() - 1;
            }
        }

        static boolean overflowInParse(long j, int r8, int r9) {
            if (j >= 0) {
                long[] jArr = maxValueDivs;
                if (j < jArr[r9]) {
                    return false;
                }
                return j > jArr[r9] || r8 > maxValueMods[r9];
            }
            return true;
        }
    }

    public static String toString(long j) {
        return toString(j, 10);
    }

    public static String toString(long j, int r13) {
        long divide;
        Preconditions.checkArgument(r13 >= 2 && r13 <= 36, "radix (%s) must be between Character.MIN_RADIX and Character.MAX_RADIX", r13);
        int r3 = (j > 0L ? 1 : (j == 0L ? 0 : -1));
        if (r3 == 0) {
            return SessionDescription.SUPPORTED_SDP_VERSION;
        }
        if (r3 > 0) {
            return Long.toString(j, r13);
        }
        int r32 = 64;
        char[] cArr = new char[64];
        int r5 = r13 - 1;
        if ((r13 & r5) == 0) {
            int numberOfTrailingZeros = Integer.numberOfTrailingZeros(r13);
            do {
                r32--;
                cArr[r32] = Character.forDigit(((int) j) & r5, r13);
                j >>>= numberOfTrailingZeros;
            } while (j != 0);
        } else {
            if ((r13 & 1) == 0) {
                divide = (j >>> 1) / (r13 >>> 1);
            } else {
                divide = divide(j, r13);
            }
            long j2 = r13;
            cArr[63] = Character.forDigit((int) (j - (divide * j2)), r13);
            r32 = 63;
            while (divide > 0) {
                r32--;
                cArr[r32] = Character.forDigit((int) (divide % j2), r13);
                divide /= j2;
            }
        }
        return new String(cArr, r32, 64 - r32);
    }
}
