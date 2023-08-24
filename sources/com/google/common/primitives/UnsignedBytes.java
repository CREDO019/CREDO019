package com.google.common.primitives;

import com.google.common.base.Preconditions;
import java.lang.reflect.Field;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import sun.misc.Unsafe;

@ElementTypesAreNonnullByDefault
/* loaded from: classes3.dex */
public final class UnsignedBytes {
    public static final byte MAX_POWER_OF_TWO = Byte.MIN_VALUE;
    public static final byte MAX_VALUE = -1;
    private static final int UNSIGNED_MASK = 255;

    private static byte flip(byte b) {
        return (byte) (b ^ 128);
    }

    public static int toInt(byte b) {
        return b & 255;
    }

    private UnsignedBytes() {
    }

    public static byte checkedCast(long j) {
        Preconditions.checkArgument((j >> 8) == 0, "out of range: %s", j);
        return (byte) j;
    }

    public static byte saturatedCast(long j) {
        if (j > toInt((byte) -1)) {
            return (byte) -1;
        }
        if (j < 0) {
            return (byte) 0;
        }
        return (byte) j;
    }

    public static int compare(byte b, byte b2) {
        return toInt(b) - toInt(b2);
    }

    public static byte min(byte... bArr) {
        Preconditions.checkArgument(bArr.length > 0);
        int r0 = toInt(bArr[0]);
        for (int r2 = 1; r2 < bArr.length; r2++) {
            int r1 = toInt(bArr[r2]);
            if (r1 < r0) {
                r0 = r1;
            }
        }
        return (byte) r0;
    }

    public static byte max(byte... bArr) {
        Preconditions.checkArgument(bArr.length > 0);
        int r0 = toInt(bArr[0]);
        for (int r2 = 1; r2 < bArr.length; r2++) {
            int r1 = toInt(bArr[r2]);
            if (r1 > r0) {
                r0 = r1;
            }
        }
        return (byte) r0;
    }

    public static String toString(byte b) {
        return toString(b, 10);
    }

    public static String toString(byte b, int r3) {
        Preconditions.checkArgument(r3 >= 2 && r3 <= 36, "radix (%s) must be between Character.MIN_RADIX and Character.MAX_RADIX", r3);
        return Integer.toString(toInt(b), r3);
    }

    public static byte parseUnsignedByte(String str) {
        return parseUnsignedByte(str, 10);
    }

    public static byte parseUnsignedByte(String str, int r3) {
        int parseInt = Integer.parseInt((String) Preconditions.checkNotNull(str), r3);
        if ((parseInt >> 8) == 0) {
            return (byte) parseInt;
        }
        StringBuilder sb = new StringBuilder(25);
        sb.append("out of range: ");
        sb.append(parseInt);
        throw new NumberFormatException(sb.toString());
    }

    public static String join(String str, byte... bArr) {
        Preconditions.checkNotNull(str);
        if (bArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(bArr.length * (str.length() + 3));
        sb.append(toInt(bArr[0]));
        for (int r1 = 1; r1 < bArr.length; r1++) {
            sb.append(str);
            sb.append(toString(bArr[r1]));
        }
        return sb.toString();
    }

    public static Comparator<byte[]> lexicographicalComparator() {
        return LexicographicalComparatorHolder.BEST_COMPARATOR;
    }

    static Comparator<byte[]> lexicographicalComparatorJavaImpl() {
        return LexicographicalComparatorHolder.PureJavaComparator.INSTANCE;
    }

    /* loaded from: classes3.dex */
    static class LexicographicalComparatorHolder {
        static final String UNSAFE_COMPARATOR_NAME = String.valueOf(LexicographicalComparatorHolder.class.getName()).concat("$UnsafeComparator");
        static final Comparator<byte[]> BEST_COMPARATOR = getBestComparator();

        LexicographicalComparatorHolder() {
        }

        /* loaded from: classes3.dex */
        enum UnsafeComparator implements Comparator<byte[]> {
            INSTANCE;
            
            static final boolean BIG_ENDIAN = ByteOrder.nativeOrder().equals(ByteOrder.BIG_ENDIAN);
            static final int BYTE_ARRAY_BASE_OFFSET;
            static final Unsafe theUnsafe;

            @Override // java.lang.Enum
            public String toString() {
                return "UnsignedBytes.lexicographicalComparator() (sun.misc.Unsafe version)";
            }

            static {
                Unsafe unsafe = getUnsafe();
                theUnsafe = unsafe;
                int arrayBaseOffset = unsafe.arrayBaseOffset(byte[].class);
                BYTE_ARRAY_BASE_OFFSET = arrayBaseOffset;
                if (!"64".equals(System.getProperty("sun.arch.data.model")) || arrayBaseOffset % 8 != 0 || unsafe.arrayIndexScale(byte[].class) != 1) {
                    throw new Error();
                }
            }

            private static Unsafe getUnsafe() {
                try {
                    try {
                        return Unsafe.getUnsafe();
                    } catch (SecurityException unused) {
                        return (Unsafe) AccessController.doPrivileged(new PrivilegedExceptionAction<Unsafe>() { // from class: com.google.common.primitives.UnsignedBytes.LexicographicalComparatorHolder.UnsafeComparator.1
                            @Override // java.security.PrivilegedExceptionAction
                            public Unsafe run() throws Exception {
                                Field[] declaredFields;
                                for (Field field : Unsafe.class.getDeclaredFields()) {
                                    field.setAccessible(true);
                                    Object obj = field.get(null);
                                    if (Unsafe.class.isInstance(obj)) {
                                        return (Unsafe) Unsafe.class.cast(obj);
                                    }
                                }
                                throw new NoSuchFieldError("the Unsafe");
                            }
                        });
                    }
                } catch (PrivilegedActionException e) {
                    throw new RuntimeException("Could not initialize intrinsics", e.getCause());
                }
            }

            @Override // java.util.Comparator
            public int compare(byte[] bArr, byte[] bArr2) {
                int min = Math.min(bArr.length, bArr2.length);
                int r1 = min & (-8);
                int r2 = 0;
                while (r2 < r1) {
                    Unsafe unsafe = theUnsafe;
                    int r4 = BYTE_ARRAY_BASE_OFFSET;
                    long j = r2;
                    long j2 = unsafe.getLong(bArr, r4 + j);
                    long j3 = unsafe.getLong(bArr2, r4 + j);
                    if (j2 != j3) {
                        if (BIG_ENDIAN) {
                            return UnsignedLongs.compare(j2, j3);
                        }
                        int numberOfTrailingZeros = Long.numberOfTrailingZeros(j2 ^ j3) & (-8);
                        return ((int) ((j2 >>> numberOfTrailingZeros) & 255)) - ((int) ((j3 >>> numberOfTrailingZeros) & 255));
                    }
                    r2 += 8;
                }
                while (r2 < min) {
                    int compare = UnsignedBytes.compare(bArr[r2], bArr2[r2]);
                    if (compare != 0) {
                        return compare;
                    }
                    r2++;
                }
                return bArr.length - bArr2.length;
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes3.dex */
        public enum PureJavaComparator implements Comparator<byte[]> {
            INSTANCE;

            @Override // java.lang.Enum
            public String toString() {
                return "UnsignedBytes.lexicographicalComparator() (pure Java version)";
            }

            @Override // java.util.Comparator
            public int compare(byte[] bArr, byte[] bArr2) {
                int min = Math.min(bArr.length, bArr2.length);
                for (int r1 = 0; r1 < min; r1++) {
                    int compare = UnsignedBytes.compare(bArr[r1], bArr2[r1]);
                    if (compare != 0) {
                        return compare;
                    }
                }
                return bArr.length - bArr2.length;
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        static Comparator<byte[]> getBestComparator() {
            try {
                Object[] enumConstants = Class.forName(UNSAFE_COMPARATOR_NAME).getEnumConstants();
                Objects.requireNonNull(enumConstants);
                return (Comparator) enumConstants[0];
            } catch (Throwable unused) {
                return UnsignedBytes.lexicographicalComparatorJavaImpl();
            }
        }
    }

    public static void sort(byte[] bArr) {
        Preconditions.checkNotNull(bArr);
        sort(bArr, 0, bArr.length);
    }

    public static void sort(byte[] bArr, int r3, int r4) {
        Preconditions.checkNotNull(bArr);
        Preconditions.checkPositionIndexes(r3, r4, bArr.length);
        for (int r0 = r3; r0 < r4; r0++) {
            bArr[r0] = flip(bArr[r0]);
        }
        Arrays.sort(bArr, r3, r4);
        while (r3 < r4) {
            bArr[r3] = flip(bArr[r3]);
            r3++;
        }
    }

    public static void sortDescending(byte[] bArr) {
        Preconditions.checkNotNull(bArr);
        sortDescending(bArr, 0, bArr.length);
    }

    public static void sortDescending(byte[] bArr, int r3, int r4) {
        Preconditions.checkNotNull(bArr);
        Preconditions.checkPositionIndexes(r3, r4, bArr.length);
        for (int r0 = r3; r0 < r4; r0++) {
            bArr[r0] = (byte) (bArr[r0] ^ Byte.MAX_VALUE);
        }
        Arrays.sort(bArr, r3, r4);
        while (r3 < r4) {
            bArr[r3] = (byte) (bArr[r3] ^ Byte.MAX_VALUE);
            r3++;
        }
    }
}
