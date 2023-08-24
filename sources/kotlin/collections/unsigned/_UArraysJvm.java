package kotlin.collections.unsigned;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.UInt;
import kotlin.UIntArray;
import kotlin.ULong;
import kotlin.ULongArray;
import kotlin.UShort;
import kotlin.UShortArray;
import kotlin.UnsignedUtils;
import kotlin.collections.AbstractList;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m184d1 = {"\u0000T\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u001a\u001c\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u001c\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00060\u0001*\u00020\u0007H\u0007ø\u0001\u0000¢\u0006\u0004\b\b\u0010\t\u001a\u001c\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\n0\u0001*\u00020\u000bH\u0007ø\u0001\u0000¢\u0006\u0004\b\f\u0010\r\u001a\u001c\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0001*\u00020\u000fH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u0011\u001a2\u0010\u0012\u001a\u00020\u0013*\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u00022\b\b\u0002\u0010\u0015\u001a\u00020\u00132\b\b\u0002\u0010\u0016\u001a\u00020\u0013H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018\u001a2\u0010\u0012\u001a\u00020\u0013*\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00062\b\b\u0002\u0010\u0015\u001a\u00020\u00132\b\b\u0002\u0010\u0016\u001a\u00020\u0013H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001a\u001a2\u0010\u0012\u001a\u00020\u0013*\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\n2\b\b\u0002\u0010\u0015\u001a\u00020\u00132\b\b\u0002\u0010\u0016\u001a\u00020\u0013H\u0007ø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u001c\u001a2\u0010\u0012\u001a\u00020\u0013*\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u000e2\b\b\u0002\u0010\u0015\u001a\u00020\u00132\b\b\u0002\u0010\u0016\u001a\u00020\u0013H\u0007ø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u001e\u001a\u001f\u0010\u001f\u001a\u00020\u0002*\u00020\u00032\u0006\u0010 \u001a\u00020\u0013H\u0087\bø\u0001\u0000¢\u0006\u0004\b!\u0010\"\u001a\u001f\u0010\u001f\u001a\u00020\u0006*\u00020\u00072\u0006\u0010 \u001a\u00020\u0013H\u0087\bø\u0001\u0000¢\u0006\u0004\b#\u0010$\u001a\u001f\u0010\u001f\u001a\u00020\n*\u00020\u000b2\u0006\u0010 \u001a\u00020\u0013H\u0087\bø\u0001\u0000¢\u0006\u0004\b%\u0010&\u001a\u001f\u0010\u001f\u001a\u00020\u000e*\u00020\u000f2\u0006\u0010 \u001a\u00020\u0013H\u0087\bø\u0001\u0000¢\u0006\u0004\b'\u0010(\u001a.\u0010)\u001a\u00020**\u00020\u00032\u0012\u0010+\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020*0,H\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b-\u0010.\u001a.\u0010)\u001a\u00020/*\u00020\u00032\u0012\u0010+\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020/0,H\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b0\u00101\u001a.\u0010)\u001a\u00020**\u00020\u00072\u0012\u0010+\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020*0,H\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b-\u00102\u001a.\u0010)\u001a\u00020/*\u00020\u00072\u0012\u0010+\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020/0,H\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b0\u00103\u001a.\u0010)\u001a\u00020**\u00020\u000b2\u0012\u0010+\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020*0,H\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b-\u00104\u001a.\u0010)\u001a\u00020/*\u00020\u000b2\u0012\u0010+\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020/0,H\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b0\u00105\u001a.\u0010)\u001a\u00020**\u00020\u000f2\u0012\u0010+\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020*0,H\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b-\u00106\u001a.\u0010)\u001a\u00020/*\u00020\u000f2\u0012\u0010+\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020/0,H\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b0\u00107\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b\u009920\u0001¨\u00068"}, m183d2 = {"asList", "", "Lkotlin/UByte;", "Lkotlin/UByteArray;", "asList-GBYM_sE", "([B)Ljava/util/List;", "Lkotlin/UInt;", "Lkotlin/UIntArray;", "asList--ajY-9A", "([I)Ljava/util/List;", "Lkotlin/ULong;", "Lkotlin/ULongArray;", "asList-QwZRm1k", "([J)Ljava/util/List;", "Lkotlin/UShort;", "Lkotlin/UShortArray;", "asList-rL5Bavg", "([S)Ljava/util/List;", "binarySearch", "", "element", "fromIndex", "toIndex", "binarySearch-WpHrYlw", "([BBII)I", "binarySearch-2fe2U9s", "([IIII)I", "binarySearch-K6DWlUc", "([JJII)I", "binarySearch-EtDCXyQ", "([SSII)I", "elementAt", "index", "elementAt-PpDY95g", "([BI)B", "elementAt-qFRl0hI", "([II)I", "elementAt-r7IrZao", "([JI)J", "elementAt-nggk6HY", "([SI)S", "sumOf", "Ljava/math/BigDecimal;", "selector", "Lkotlin/Function1;", "sumOfBigDecimal", "([BLkotlin/jvm/functions/Function1;)Ljava/math/BigDecimal;", "Ljava/math/BigInteger;", "sumOfBigInteger", "([BLkotlin/jvm/functions/Function1;)Ljava/math/BigInteger;", "([ILkotlin/jvm/functions/Function1;)Ljava/math/BigDecimal;", "([ILkotlin/jvm/functions/Function1;)Ljava/math/BigInteger;", "([JLkotlin/jvm/functions/Function1;)Ljava/math/BigDecimal;", "([JLkotlin/jvm/functions/Function1;)Ljava/math/BigInteger;", "([SLkotlin/jvm/functions/Function1;)Ljava/math/BigDecimal;", "([SLkotlin/jvm/functions/Function1;)Ljava/math/BigInteger;", "kotlin-stdlib"}, m182k = 5, m181mv = {1, 6, 0}, m180pn = "kotlin.collections", m179xi = 49, m178xs = "kotlin/collections/unsigned/UArraysKt")
/* renamed from: kotlin.collections.unsigned.UArraysKt___UArraysJvmKt */
/* loaded from: classes5.dex */
class _UArraysJvm {
    /* renamed from: elementAt-qFRl0hI  reason: not valid java name */
    private static final int m2226elementAtqFRl0hI(int[] elementAt, int r2) {
        Intrinsics.checkNotNullParameter(elementAt, "$this$elementAt");
        return UIntArray.m1902getpVg5ArA(elementAt, r2);
    }

    /* renamed from: elementAt-r7IrZao  reason: not valid java name */
    private static final long m2227elementAtr7IrZao(long[] elementAt, int r2) {
        Intrinsics.checkNotNullParameter(elementAt, "$this$elementAt");
        return ULongArray.m1980getsVKNKU(elementAt, r2);
    }

    /* renamed from: elementAt-PpDY95g  reason: not valid java name */
    private static final byte m2224elementAtPpDY95g(byte[] elementAt, int r2) {
        Intrinsics.checkNotNullParameter(elementAt, "$this$elementAt");
        return UByteArray.m1824getw2LRezQ(elementAt, r2);
    }

    /* renamed from: elementAt-nggk6HY  reason: not valid java name */
    private static final short m2225elementAtnggk6HY(short[] elementAt, int r2) {
        Intrinsics.checkNotNullParameter(elementAt, "$this$elementAt");
        return UShortArray.m2084getMh2AYeg(elementAt, r2);
    }

    /* renamed from: asList--ajY-9A  reason: not valid java name */
    public static final List<UInt> m2212asListajY9A(int[] asList) {
        Intrinsics.checkNotNullParameter(asList, "$this$asList");
        return new UArraysKt___UArraysJvmKt$asList$1(asList);
    }

    /* renamed from: asList-QwZRm1k  reason: not valid java name */
    public static final List<ULong> m2214asListQwZRm1k(long[] asList) {
        Intrinsics.checkNotNullParameter(asList, "$this$asList");
        return new UArraysKt___UArraysJvmKt$asList$2(asList);
    }

    /* renamed from: asList-GBYM_sE  reason: not valid java name */
    public static final List<UByte> m2213asListGBYM_sE(byte[] asList) {
        Intrinsics.checkNotNullParameter(asList, "$this$asList");
        return new UArraysKt___UArraysJvmKt$asList$3(asList);
    }

    /* renamed from: asList-rL5Bavg  reason: not valid java name */
    public static final List<UShort> m2215asListrL5Bavg(short[] asList) {
        Intrinsics.checkNotNullParameter(asList, "$this$asList");
        return new UArraysKt___UArraysJvmKt$asList$4(asList);
    }

    /* renamed from: binarySearch-2fe2U9s$default  reason: not valid java name */
    public static /* synthetic */ int m2217binarySearch2fe2U9s$default(int[] r0, int r1, int r2, int r3, int r4, Object obj) {
        if ((r4 & 2) != 0) {
            r2 = 0;
        }
        if ((r4 & 4) != 0) {
            r3 = UIntArray.m1903getSizeimpl(r0);
        }
        return UArraysKt.m2216binarySearch2fe2U9s(r0, r1, r2, r3);
    }

    /* renamed from: binarySearch-2fe2U9s  reason: not valid java name */
    public static final int m2216binarySearch2fe2U9s(int[] binarySearch, int r3, int r4, int r5) {
        Intrinsics.checkNotNullParameter(binarySearch, "$this$binarySearch");
        AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(r4, r5, UIntArray.m1903getSizeimpl(binarySearch));
        int r52 = r5 - 1;
        while (r4 <= r52) {
            int r0 = (r4 + r52) >>> 1;
            int uintCompare = UnsignedUtils.uintCompare(binarySearch[r0], r3);
            if (uintCompare < 0) {
                r4 = r0 + 1;
            } else if (uintCompare <= 0) {
                return r0;
            } else {
                r52 = r0 - 1;
            }
        }
        return -(r4 + 1);
    }

    /* renamed from: binarySearch-K6DWlUc$default  reason: not valid java name */
    public static /* synthetic */ int m2221binarySearchK6DWlUc$default(long[] jArr, long j, int r3, int r4, int r5, Object obj) {
        if ((r5 & 2) != 0) {
            r3 = 0;
        }
        if ((r5 & 4) != 0) {
            r4 = ULongArray.m1981getSizeimpl(jArr);
        }
        return UArraysKt.m2220binarySearchK6DWlUc(jArr, j, r3, r4);
    }

    /* renamed from: binarySearch-K6DWlUc  reason: not valid java name */
    public static final int m2220binarySearchK6DWlUc(long[] binarySearch, long j, int r6, int r7) {
        Intrinsics.checkNotNullParameter(binarySearch, "$this$binarySearch");
        AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(r6, r7, ULongArray.m1981getSizeimpl(binarySearch));
        int r72 = r7 - 1;
        while (r6 <= r72) {
            int r0 = (r6 + r72) >>> 1;
            int ulongCompare = UnsignedUtils.ulongCompare(binarySearch[r0], j);
            if (ulongCompare < 0) {
                r6 = r0 + 1;
            } else if (ulongCompare <= 0) {
                return r0;
            } else {
                r72 = r0 - 1;
            }
        }
        return -(r6 + 1);
    }

    /* renamed from: binarySearch-WpHrYlw$default  reason: not valid java name */
    public static /* synthetic */ int m2223binarySearchWpHrYlw$default(byte[] bArr, byte b, int r2, int r3, int r4, Object obj) {
        if ((r4 & 2) != 0) {
            r2 = 0;
        }
        if ((r4 & 4) != 0) {
            r3 = UByteArray.m1825getSizeimpl(bArr);
        }
        return UArraysKt.m2222binarySearchWpHrYlw(bArr, b, r2, r3);
    }

    /* renamed from: binarySearch-WpHrYlw  reason: not valid java name */
    public static final int m2222binarySearchWpHrYlw(byte[] binarySearch, byte b, int r4, int r5) {
        Intrinsics.checkNotNullParameter(binarySearch, "$this$binarySearch");
        AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(r4, r5, UByteArray.m1825getSizeimpl(binarySearch));
        int r3 = b & 255;
        int r52 = r5 - 1;
        while (r4 <= r52) {
            int r0 = (r4 + r52) >>> 1;
            int uintCompare = UnsignedUtils.uintCompare(binarySearch[r0], r3);
            if (uintCompare < 0) {
                r4 = r0 + 1;
            } else if (uintCompare <= 0) {
                return r0;
            } else {
                r52 = r0 - 1;
            }
        }
        return -(r4 + 1);
    }

    /* renamed from: binarySearch-EtDCXyQ$default  reason: not valid java name */
    public static /* synthetic */ int m2219binarySearchEtDCXyQ$default(short[] sArr, short s, int r2, int r3, int r4, Object obj) {
        if ((r4 & 2) != 0) {
            r2 = 0;
        }
        if ((r4 & 4) != 0) {
            r3 = UShortArray.m2085getSizeimpl(sArr);
        }
        return UArraysKt.m2218binarySearchEtDCXyQ(sArr, s, r2, r3);
    }

    /* renamed from: binarySearch-EtDCXyQ  reason: not valid java name */
    public static final int m2218binarySearchEtDCXyQ(short[] binarySearch, short s, int r4, int r5) {
        Intrinsics.checkNotNullParameter(binarySearch, "$this$binarySearch");
        AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(r4, r5, UShortArray.m2085getSizeimpl(binarySearch));
        int r3 = s & UShort.MAX_VALUE;
        int r52 = r5 - 1;
        while (r4 <= r52) {
            int r0 = (r4 + r52) >>> 1;
            int uintCompare = UnsignedUtils.uintCompare(binarySearch[r0], r3);
            if (uintCompare < 0) {
                r4 = r0 + 1;
            } else if (uintCompare <= 0) {
                return r0;
            } else {
                r52 = r0 - 1;
            }
        }
        return -(r4 + 1);
    }

    private static final BigDecimal sumOfBigDecimal(int[] sumOf, Function1<? super UInt, ? extends BigDecimal> selector) {
        Intrinsics.checkNotNullParameter(sumOf, "$this$sumOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        BigDecimal valueOf = BigDecimal.valueOf(0L);
        Intrinsics.checkNotNullExpressionValue(valueOf, "valueOf(this.toLong())");
        int m1903getSizeimpl = UIntArray.m1903getSizeimpl(sumOf);
        for (int r2 = 0; r2 < m1903getSizeimpl; r2++) {
            valueOf = valueOf.add(selector.invoke(UInt.m1837boximpl(UIntArray.m1902getpVg5ArA(sumOf, r2))));
            Intrinsics.checkNotNullExpressionValue(valueOf, "this.add(other)");
        }
        return valueOf;
    }

    private static final BigDecimal sumOfBigDecimal(long[] sumOf, Function1<? super ULong, ? extends BigDecimal> selector) {
        Intrinsics.checkNotNullParameter(sumOf, "$this$sumOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        BigDecimal valueOf = BigDecimal.valueOf(0L);
        Intrinsics.checkNotNullExpressionValue(valueOf, "valueOf(this.toLong())");
        int m1981getSizeimpl = ULongArray.m1981getSizeimpl(sumOf);
        for (int r2 = 0; r2 < m1981getSizeimpl; r2++) {
            valueOf = valueOf.add(selector.invoke(ULong.m1915boximpl(ULongArray.m1980getsVKNKU(sumOf, r2))));
            Intrinsics.checkNotNullExpressionValue(valueOf, "this.add(other)");
        }
        return valueOf;
    }

    private static final BigDecimal sumOfBigDecimal(byte[] sumOf, Function1<? super UByte, ? extends BigDecimal> selector) {
        Intrinsics.checkNotNullParameter(sumOf, "$this$sumOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        BigDecimal valueOf = BigDecimal.valueOf(0L);
        Intrinsics.checkNotNullExpressionValue(valueOf, "valueOf(this.toLong())");
        int m1825getSizeimpl = UByteArray.m1825getSizeimpl(sumOf);
        for (int r2 = 0; r2 < m1825getSizeimpl; r2++) {
            valueOf = valueOf.add(selector.invoke(UByte.m1761boximpl(UByteArray.m1824getw2LRezQ(sumOf, r2))));
            Intrinsics.checkNotNullExpressionValue(valueOf, "this.add(other)");
        }
        return valueOf;
    }

    private static final BigDecimal sumOfBigDecimal(short[] sumOf, Function1<? super UShort, ? extends BigDecimal> selector) {
        Intrinsics.checkNotNullParameter(sumOf, "$this$sumOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        BigDecimal valueOf = BigDecimal.valueOf(0L);
        Intrinsics.checkNotNullExpressionValue(valueOf, "valueOf(this.toLong())");
        int m2085getSizeimpl = UShortArray.m2085getSizeimpl(sumOf);
        for (int r2 = 0; r2 < m2085getSizeimpl; r2++) {
            valueOf = valueOf.add(selector.invoke(UShort.m2021boximpl(UShortArray.m2084getMh2AYeg(sumOf, r2))));
            Intrinsics.checkNotNullExpressionValue(valueOf, "this.add(other)");
        }
        return valueOf;
    }

    private static final BigInteger sumOfBigInteger(int[] sumOf, Function1<? super UInt, ? extends BigInteger> selector) {
        Intrinsics.checkNotNullParameter(sumOf, "$this$sumOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        BigInteger valueOf = BigInteger.valueOf(0L);
        Intrinsics.checkNotNullExpressionValue(valueOf, "valueOf(this.toLong())");
        int m1903getSizeimpl = UIntArray.m1903getSizeimpl(sumOf);
        for (int r2 = 0; r2 < m1903getSizeimpl; r2++) {
            valueOf = valueOf.add(selector.invoke(UInt.m1837boximpl(UIntArray.m1902getpVg5ArA(sumOf, r2))));
            Intrinsics.checkNotNullExpressionValue(valueOf, "this.add(other)");
        }
        return valueOf;
    }

    private static final BigInteger sumOfBigInteger(long[] sumOf, Function1<? super ULong, ? extends BigInteger> selector) {
        Intrinsics.checkNotNullParameter(sumOf, "$this$sumOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        BigInteger valueOf = BigInteger.valueOf(0L);
        Intrinsics.checkNotNullExpressionValue(valueOf, "valueOf(this.toLong())");
        int m1981getSizeimpl = ULongArray.m1981getSizeimpl(sumOf);
        for (int r2 = 0; r2 < m1981getSizeimpl; r2++) {
            valueOf = valueOf.add(selector.invoke(ULong.m1915boximpl(ULongArray.m1980getsVKNKU(sumOf, r2))));
            Intrinsics.checkNotNullExpressionValue(valueOf, "this.add(other)");
        }
        return valueOf;
    }

    private static final BigInteger sumOfBigInteger(byte[] sumOf, Function1<? super UByte, ? extends BigInteger> selector) {
        Intrinsics.checkNotNullParameter(sumOf, "$this$sumOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        BigInteger valueOf = BigInteger.valueOf(0L);
        Intrinsics.checkNotNullExpressionValue(valueOf, "valueOf(this.toLong())");
        int m1825getSizeimpl = UByteArray.m1825getSizeimpl(sumOf);
        for (int r2 = 0; r2 < m1825getSizeimpl; r2++) {
            valueOf = valueOf.add(selector.invoke(UByte.m1761boximpl(UByteArray.m1824getw2LRezQ(sumOf, r2))));
            Intrinsics.checkNotNullExpressionValue(valueOf, "this.add(other)");
        }
        return valueOf;
    }

    private static final BigInteger sumOfBigInteger(short[] sumOf, Function1<? super UShort, ? extends BigInteger> selector) {
        Intrinsics.checkNotNullParameter(sumOf, "$this$sumOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        BigInteger valueOf = BigInteger.valueOf(0L);
        Intrinsics.checkNotNullExpressionValue(valueOf, "valueOf(this.toLong())");
        int m2085getSizeimpl = UShortArray.m2085getSizeimpl(sumOf);
        for (int r2 = 0; r2 < m2085getSizeimpl; r2++) {
            valueOf = valueOf.add(selector.invoke(UShort.m2021boximpl(UShortArray.m2084getMh2AYeg(sumOf, r2))));
            Intrinsics.checkNotNullExpressionValue(valueOf, "this.add(other)");
        }
        return valueOf;
    }
}
