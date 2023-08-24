package kotlin.text;

import kotlin.ExceptionsH;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UShort;
import kotlin.UnsignedUtils;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.asn1.cmc.BodyPartID;

@Metadata(m184d1 = {"\u0000,\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0013\u001a\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u001a\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b\b\u0010\t\u001a\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\n2\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\f\u001a\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\r2\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000f\u001a\u0014\u0010\u0010\u001a\u00020\u0002*\u00020\u0001H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0011\u001a\u001c\u0010\u0010\u001a\u00020\u0002*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001a\u0011\u0010\u0013\u001a\u0004\u0018\u00010\u0002*\u00020\u0001H\u0007ø\u0001\u0000\u001a\u0019\u0010\u0013\u001a\u0004\u0018\u00010\u0002*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000\u001a\u0014\u0010\u0014\u001a\u00020\u0007*\u00020\u0001H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0015\u001a\u001c\u0010\u0014\u001a\u00020\u0007*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0016\u001a\u0011\u0010\u0017\u001a\u0004\u0018\u00010\u0007*\u00020\u0001H\u0007ø\u0001\u0000\u001a\u0019\u0010\u0017\u001a\u0004\u0018\u00010\u0007*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000\u001a\u0014\u0010\u0018\u001a\u00020\n*\u00020\u0001H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0019\u001a\u001c\u0010\u0018\u001a\u00020\n*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u001a\u001a\u0011\u0010\u001b\u001a\u0004\u0018\u00010\n*\u00020\u0001H\u0007ø\u0001\u0000\u001a\u0019\u0010\u001b\u001a\u0004\u0018\u00010\n*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000\u001a\u0014\u0010\u001c\u001a\u00020\r*\u00020\u0001H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u001d\u001a\u001c\u0010\u001c\u001a\u00020\r*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u001e\u001a\u0011\u0010\u001f\u001a\u0004\u0018\u00010\r*\u00020\u0001H\u0007ø\u0001\u0000\u001a\u0019\u0010\u001f\u001a\u0004\u0018\u00010\r*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006 "}, m183d2 = {"toString", "", "Lkotlin/UByte;", "radix", "", "toString-LxnNnR4", "(BI)Ljava/lang/String;", "Lkotlin/UInt;", "toString-V7xB4Y4", "(II)Ljava/lang/String;", "Lkotlin/ULong;", "toString-JSWoG40", "(JI)Ljava/lang/String;", "Lkotlin/UShort;", "toString-olVBNx4", "(SI)Ljava/lang/String;", "toUByte", "(Ljava/lang/String;)B", "(Ljava/lang/String;I)B", "toUByteOrNull", "toUInt", "(Ljava/lang/String;)I", "(Ljava/lang/String;I)I", "toUIntOrNull", "toULong", "(Ljava/lang/String;)J", "(Ljava/lang/String;I)J", "toULongOrNull", "toUShort", "(Ljava/lang/String;)S", "(Ljava/lang/String;I)S", "toUShortOrNull", "kotlin-stdlib"}, m182k = 2, m181mv = {1, 6, 0}, m179xi = 48)
/* renamed from: kotlin.text.UStringsKt */
/* loaded from: classes5.dex */
public final class UStrings {
    /* renamed from: toString-LxnNnR4  reason: not valid java name */
    public static final String m3045toStringLxnNnR4(byte b, int r1) {
        String num = Integer.toString(b & 255, CharsKt.checkRadix(r1));
        Intrinsics.checkNotNullExpressionValue(num, "toString(this, checkRadix(radix))");
        return num;
    }

    /* renamed from: toString-olVBNx4  reason: not valid java name */
    public static final String m3047toStringolVBNx4(short s, int r2) {
        String num = Integer.toString(s & UShort.MAX_VALUE, CharsKt.checkRadix(r2));
        Intrinsics.checkNotNullExpressionValue(num, "toString(this, checkRadix(radix))");
        return num;
    }

    /* renamed from: toString-V7xB4Y4  reason: not valid java name */
    public static final String m3046toStringV7xB4Y4(int r4, int r5) {
        String l = Long.toString(r4 & BodyPartID.bodyIdMax, CharsKt.checkRadix(r5));
        Intrinsics.checkNotNullExpressionValue(l, "toString(this, checkRadix(radix))");
        return l;
    }

    /* renamed from: toString-JSWoG40  reason: not valid java name */
    public static final String m3044toStringJSWoG40(long j, int r2) {
        return UnsignedUtils.ulongToString(j, CharsKt.checkRadix(r2));
    }

    public static final byte toUByte(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        UByte uByteOrNull = toUByteOrNull(str);
        if (uByteOrNull != null) {
            return uByteOrNull.m1816unboximpl();
        }
        StringsKt.numberFormatError(str);
        throw new ExceptionsH();
    }

    public static final byte toUByte(String str, int r2) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        UByte uByteOrNull = toUByteOrNull(str, r2);
        if (uByteOrNull != null) {
            return uByteOrNull.m1816unboximpl();
        }
        StringsKt.numberFormatError(str);
        throw new ExceptionsH();
    }

    public static final short toUShort(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        UShort uShortOrNull = toUShortOrNull(str);
        if (uShortOrNull != null) {
            return uShortOrNull.m2076unboximpl();
        }
        StringsKt.numberFormatError(str);
        throw new ExceptionsH();
    }

    public static final short toUShort(String str, int r2) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        UShort uShortOrNull = toUShortOrNull(str, r2);
        if (uShortOrNull != null) {
            return uShortOrNull.m2076unboximpl();
        }
        StringsKt.numberFormatError(str);
        throw new ExceptionsH();
    }

    public static final int toUInt(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        UInt uIntOrNull = toUIntOrNull(str);
        if (uIntOrNull != null) {
            return uIntOrNull.m1894unboximpl();
        }
        StringsKt.numberFormatError(str);
        throw new ExceptionsH();
    }

    public static final int toUInt(String str, int r2) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        UInt uIntOrNull = toUIntOrNull(str, r2);
        if (uIntOrNull != null) {
            return uIntOrNull.m1894unboximpl();
        }
        StringsKt.numberFormatError(str);
        throw new ExceptionsH();
    }

    public static final long toULong(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        ULong uLongOrNull = toULongOrNull(str);
        if (uLongOrNull != null) {
            return uLongOrNull.m1972unboximpl();
        }
        StringsKt.numberFormatError(str);
        throw new ExceptionsH();
    }

    public static final long toULong(String str, int r2) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        ULong uLongOrNull = toULongOrNull(str, r2);
        if (uLongOrNull != null) {
            return uLongOrNull.m1972unboximpl();
        }
        StringsKt.numberFormatError(str);
        throw new ExceptionsH();
    }

    public static final UByte toUByteOrNull(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return toUByteOrNull(str, 10);
    }

    public static final UByte toUByteOrNull(String str, int r2) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        UInt uIntOrNull = toUIntOrNull(str, r2);
        if (uIntOrNull != null) {
            int m1894unboximpl = uIntOrNull.m1894unboximpl();
            if (UnsignedUtils.uintCompare(m1894unboximpl, UInt.m1843constructorimpl(255)) > 0) {
                return null;
            }
            return UByte.m1761boximpl(UByte.m1767constructorimpl((byte) m1894unboximpl));
        }
        return null;
    }

    public static final UShort toUShortOrNull(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return toUShortOrNull(str, 10);
    }

    public static final UShort toUShortOrNull(String str, int r2) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        UInt uIntOrNull = toUIntOrNull(str, r2);
        if (uIntOrNull != null) {
            int m1894unboximpl = uIntOrNull.m1894unboximpl();
            if (UnsignedUtils.uintCompare(m1894unboximpl, UInt.m1843constructorimpl(65535)) > 0) {
                return null;
            }
            return UShort.m2021boximpl(UShort.m2027constructorimpl((short) m1894unboximpl));
        }
        return null;
    }

    public static final UInt toUIntOrNull(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return toUIntOrNull(str, 10);
    }

    public static final UInt toUIntOrNull(String str, int r11) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        CharsKt.checkRadix(r11);
        int length = str.length();
        if (length == 0) {
            return null;
        }
        int r3 = 0;
        char charAt = str.charAt(0);
        int r6 = 1;
        if (Intrinsics.compare((int) charAt, 48) >= 0) {
            r6 = 0;
        } else if (length == 1 || charAt != '+') {
            return null;
        }
        int m1843constructorimpl = UInt.m1843constructorimpl(r11);
        int r7 = 119304647;
        while (r6 < length) {
            int digitOf = CharsKt.digitOf(str.charAt(r6), r11);
            if (digitOf < 0) {
                return null;
            }
            if (UnsignedUtils.uintCompare(r3, r7) > 0) {
                if (r7 == 119304647) {
                    r7 = UnsignedUtils.m2096uintDivideJ1ME1BU(-1, m1843constructorimpl);
                    if (UnsignedUtils.uintCompare(r3, r7) > 0) {
                    }
                }
                return null;
            }
            int m1843constructorimpl2 = UInt.m1843constructorimpl(r3 * m1843constructorimpl);
            int m1843constructorimpl3 = UInt.m1843constructorimpl(UInt.m1843constructorimpl(digitOf) + m1843constructorimpl2);
            if (UnsignedUtils.uintCompare(m1843constructorimpl3, m1843constructorimpl2) < 0) {
                return null;
            }
            r6++;
            r3 = m1843constructorimpl3;
        }
        return UInt.m1837boximpl(r3);
    }

    public static final ULong toULongOrNull(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return toULongOrNull(str, 10);
    }

    public static final ULong toULongOrNull(String str, int r20) {
        int digitOf;
        Intrinsics.checkNotNullParameter(str, "<this>");
        CharsKt.checkRadix(r20);
        int length = str.length();
        if (length == 0) {
            return null;
        }
        long j = -1;
        int r6 = 0;
        char charAt = str.charAt(0);
        if (Intrinsics.compare((int) charAt, 48) < 0) {
            if (length == 1 || charAt != '+') {
                return null;
            }
            r6 = 1;
        }
        long m1921constructorimpl = ULong.m1921constructorimpl(r20);
        long j2 = 0;
        long j3 = 512409557603043100L;
        while (r6 < length) {
            if (CharsKt.digitOf(str.charAt(r6), r20) < 0) {
                return null;
            }
            if (UnsignedUtils.ulongCompare(j2, j3) > 0) {
                if (j3 == 512409557603043100L) {
                    j3 = UnsignedUtils.m2098ulongDivideeb3DHEI(j, m1921constructorimpl);
                    if (UnsignedUtils.ulongCompare(j2, j3) > 0) {
                    }
                }
                return null;
            }
            long m1921constructorimpl2 = ULong.m1921constructorimpl(j2 * m1921constructorimpl);
            long m1921constructorimpl3 = ULong.m1921constructorimpl(ULong.m1921constructorimpl(UInt.m1843constructorimpl(digitOf) & BodyPartID.bodyIdMax) + m1921constructorimpl2);
            if (UnsignedUtils.ulongCompare(m1921constructorimpl3, m1921constructorimpl2) < 0) {
                return null;
            }
            r6++;
            j2 = m1921constructorimpl3;
            j = -1;
        }
        return ULong.m1915boximpl(j2);
    }
}
