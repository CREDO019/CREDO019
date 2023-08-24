package kotlin.collections;

import kotlin.Metadata;
import kotlin.UByteArray;
import kotlin.UIntArray;
import kotlin.ULongArray;
import kotlin.UShort;
import kotlin.UShortArray;
import kotlin.UnsignedUtils;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m184d1 = {"\u00000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0010\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\t\u0010\n\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\f\u0010\r\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010\u001a*\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014\u001a*\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016\u001a*\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018\u001a*\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001a\u001a*\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001H\u0001ø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u0014\u001a*\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001H\u0001ø\u0001\u0000¢\u0006\u0004\b\u001f\u0010\u0016\u001a*\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001H\u0001ø\u0001\u0000¢\u0006\u0004\b \u0010\u0018\u001a*\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001H\u0001ø\u0001\u0000¢\u0006\u0004\b!\u0010\u001a\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\""}, m183d2 = {"partition", "", "array", "Lkotlin/UByteArray;", "left", "right", "partition-4UcCI2c", "([BII)I", "Lkotlin/UIntArray;", "partition-oBK06Vg", "([III)I", "Lkotlin/ULongArray;", "partition--nroSd4", "([JII)I", "Lkotlin/UShortArray;", "partition-Aa5vz7o", "([SII)I", "quickSort", "", "quickSort-4UcCI2c", "([BII)V", "quickSort-oBK06Vg", "([III)V", "quickSort--nroSd4", "([JII)V", "quickSort-Aa5vz7o", "([SII)V", "sortArray", "fromIndex", "toIndex", "sortArray-4UcCI2c", "sortArray-oBK06Vg", "sortArray--nroSd4", "sortArray-Aa5vz7o", "kotlin-stdlib"}, m182k = 2, m181mv = {1, 6, 0}, m179xi = 48)
/* renamed from: kotlin.collections.UArraySortingKt */
/* loaded from: classes5.dex */
public final class UArraySorting {
    /* renamed from: partition-4UcCI2c  reason: not valid java name */
    private static final int m2197partition4UcCI2c(byte[] bArr, int r4, int r5) {
        int r2;
        byte m1824getw2LRezQ = UByteArray.m1824getw2LRezQ(bArr, (r4 + r5) / 2);
        while (r4 <= r5) {
            while (true) {
                r2 = m1824getw2LRezQ & 255;
                if (Intrinsics.compare(UByteArray.m1824getw2LRezQ(bArr, r4) & 255, r2) >= 0) {
                    break;
                }
                r4++;
            }
            while (Intrinsics.compare(UByteArray.m1824getw2LRezQ(bArr, r5) & 255, r2) > 0) {
                r5--;
            }
            if (r4 <= r5) {
                byte m1824getw2LRezQ2 = UByteArray.m1824getw2LRezQ(bArr, r4);
                UByteArray.m1829setVurrAj0(bArr, r4, UByteArray.m1824getw2LRezQ(bArr, r5));
                UByteArray.m1829setVurrAj0(bArr, r5, m1824getw2LRezQ2);
                r4++;
                r5--;
            }
        }
        return r4;
    }

    /* renamed from: quickSort-4UcCI2c  reason: not valid java name */
    private static final void m2201quickSort4UcCI2c(byte[] bArr, int r3, int r4) {
        int m2197partition4UcCI2c = m2197partition4UcCI2c(bArr, r3, r4);
        int r1 = m2197partition4UcCI2c - 1;
        if (r3 < r1) {
            m2201quickSort4UcCI2c(bArr, r3, r1);
        }
        if (m2197partition4UcCI2c < r4) {
            m2201quickSort4UcCI2c(bArr, m2197partition4UcCI2c, r4);
        }
    }

    /* renamed from: partition-Aa5vz7o  reason: not valid java name */
    private static final int m2198partitionAa5vz7o(short[] sArr, int r5, int r6) {
        int r3;
        short m2084getMh2AYeg = UShortArray.m2084getMh2AYeg(sArr, (r5 + r6) / 2);
        while (r5 <= r6) {
            while (true) {
                int m2084getMh2AYeg2 = UShortArray.m2084getMh2AYeg(sArr, r5) & UShort.MAX_VALUE;
                r3 = m2084getMh2AYeg & UShort.MAX_VALUE;
                if (Intrinsics.compare(m2084getMh2AYeg2, r3) >= 0) {
                    break;
                }
                r5++;
            }
            while (Intrinsics.compare(UShortArray.m2084getMh2AYeg(sArr, r6) & UShort.MAX_VALUE, r3) > 0) {
                r6--;
            }
            if (r5 <= r6) {
                short m2084getMh2AYeg3 = UShortArray.m2084getMh2AYeg(sArr, r5);
                UShortArray.m2089set01HTLdE(sArr, r5, UShortArray.m2084getMh2AYeg(sArr, r6));
                UShortArray.m2089set01HTLdE(sArr, r6, m2084getMh2AYeg3);
                r5++;
                r6--;
            }
        }
        return r5;
    }

    /* renamed from: quickSort-Aa5vz7o  reason: not valid java name */
    private static final void m2202quickSortAa5vz7o(short[] sArr, int r3, int r4) {
        int m2198partitionAa5vz7o = m2198partitionAa5vz7o(sArr, r3, r4);
        int r1 = m2198partitionAa5vz7o - 1;
        if (r3 < r1) {
            m2202quickSortAa5vz7o(sArr, r3, r1);
        }
        if (m2198partitionAa5vz7o < r4) {
            m2202quickSortAa5vz7o(sArr, m2198partitionAa5vz7o, r4);
        }
    }

    /* renamed from: partition-oBK06Vg  reason: not valid java name */
    private static final int m2199partitionoBK06Vg(int[] r3, int r4, int r5) {
        int m1902getpVg5ArA = UIntArray.m1902getpVg5ArA(r3, (r4 + r5) / 2);
        while (r4 <= r5) {
            while (UnsignedUtils.uintCompare(UIntArray.m1902getpVg5ArA(r3, r4), m1902getpVg5ArA) < 0) {
                r4++;
            }
            while (UnsignedUtils.uintCompare(UIntArray.m1902getpVg5ArA(r3, r5), m1902getpVg5ArA) > 0) {
                r5--;
            }
            if (r4 <= r5) {
                int m1902getpVg5ArA2 = UIntArray.m1902getpVg5ArA(r3, r4);
                UIntArray.m1907setVXSXFK8(r3, r4, UIntArray.m1902getpVg5ArA(r3, r5));
                UIntArray.m1907setVXSXFK8(r3, r5, m1902getpVg5ArA2);
                r4++;
                r5--;
            }
        }
        return r4;
    }

    /* renamed from: quickSort-oBK06Vg  reason: not valid java name */
    private static final void m2203quickSortoBK06Vg(int[] r2, int r3, int r4) {
        int m2199partitionoBK06Vg = m2199partitionoBK06Vg(r2, r3, r4);
        int r1 = m2199partitionoBK06Vg - 1;
        if (r3 < r1) {
            m2203quickSortoBK06Vg(r2, r3, r1);
        }
        if (m2199partitionoBK06Vg < r4) {
            m2203quickSortoBK06Vg(r2, m2199partitionoBK06Vg, r4);
        }
    }

    /* renamed from: partition--nroSd4  reason: not valid java name */
    private static final int m2196partitionnroSd4(long[] jArr, int r7, int r8) {
        long m1980getsVKNKU = ULongArray.m1980getsVKNKU(jArr, (r7 + r8) / 2);
        while (r7 <= r8) {
            while (UnsignedUtils.ulongCompare(ULongArray.m1980getsVKNKU(jArr, r7), m1980getsVKNKU) < 0) {
                r7++;
            }
            while (UnsignedUtils.ulongCompare(ULongArray.m1980getsVKNKU(jArr, r8), m1980getsVKNKU) > 0) {
                r8--;
            }
            if (r7 <= r8) {
                long m1980getsVKNKU2 = ULongArray.m1980getsVKNKU(jArr, r7);
                ULongArray.m1985setk8EXiF4(jArr, r7, ULongArray.m1980getsVKNKU(jArr, r8));
                ULongArray.m1985setk8EXiF4(jArr, r8, m1980getsVKNKU2);
                r7++;
                r8--;
            }
        }
        return r7;
    }

    /* renamed from: quickSort--nroSd4  reason: not valid java name */
    private static final void m2200quickSortnroSd4(long[] jArr, int r3, int r4) {
        int m2196partitionnroSd4 = m2196partitionnroSd4(jArr, r3, r4);
        int r1 = m2196partitionnroSd4 - 1;
        if (r3 < r1) {
            m2200quickSortnroSd4(jArr, r3, r1);
        }
        if (m2196partitionnroSd4 < r4) {
            m2200quickSortnroSd4(jArr, m2196partitionnroSd4, r4);
        }
    }

    /* renamed from: sortArray-4UcCI2c  reason: not valid java name */
    public static final void m2205sortArray4UcCI2c(byte[] array, int r2, int r3) {
        Intrinsics.checkNotNullParameter(array, "array");
        m2201quickSort4UcCI2c(array, r2, r3 - 1);
    }

    /* renamed from: sortArray-Aa5vz7o  reason: not valid java name */
    public static final void m2206sortArrayAa5vz7o(short[] array, int r2, int r3) {
        Intrinsics.checkNotNullParameter(array, "array");
        m2202quickSortAa5vz7o(array, r2, r3 - 1);
    }

    /* renamed from: sortArray-oBK06Vg  reason: not valid java name */
    public static final void m2207sortArrayoBK06Vg(int[] array, int r2, int r3) {
        Intrinsics.checkNotNullParameter(array, "array");
        m2203quickSortoBK06Vg(array, r2, r3 - 1);
    }

    /* renamed from: sortArray--nroSd4  reason: not valid java name */
    public static final void m2204sortArraynroSd4(long[] array, int r2, int r3) {
        Intrinsics.checkNotNullParameter(array, "array");
        m2200quickSortnroSd4(array, r2, r3 - 1);
    }
}
