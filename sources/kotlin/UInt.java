package kotlin;

import com.google.android.exoplayer2.text.ttml.TtmlNode;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.UIntRange;
import org.bouncycastle.asn1.cmc.BodyPartID;

/* compiled from: UInt.kt */
@Metadata(m184d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b!\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\n\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000e\b\u0087@\u0018\u0000 y2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001yB\u0014\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\fø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u001b\u0010\f\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\rH\u0087\nø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000fJ\u001b\u0010\f\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0000H\u0097\nø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u000bJ\u001b\u0010\f\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013J\u001b\u0010\f\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016J\u0016\u0010\u0017\u001a\u00020\u0000H\u0087\nø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0018\u0010\u0005J\u001b\u0010\u0019\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\rH\u0087\nø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u000fJ\u001b\u0010\u0019\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u000bJ\u001b\u0010\u0019\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ\u001b\u0010\u0019\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u0016J\u001a\u0010\u001f\u001a\u00020 2\b\u0010\t\u001a\u0004\u0018\u00010!HÖ\u0003¢\u0006\u0004\b\"\u0010#J\u001b\u0010$\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\rH\u0087\bø\u0001\u0000¢\u0006\u0004\b%\u0010\u000fJ\u001b\u0010$\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\bø\u0001\u0000¢\u0006\u0004\b&\u0010\u000bJ\u001b\u0010$\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\bø\u0001\u0000¢\u0006\u0004\b'\u0010\u001dJ\u001b\u0010$\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0014H\u0087\bø\u0001\u0000¢\u0006\u0004\b(\u0010\u0016J\u0010\u0010)\u001a\u00020\u0003HÖ\u0001¢\u0006\u0004\b*\u0010\u0005J\u0016\u0010+\u001a\u00020\u0000H\u0087\nø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b,\u0010\u0005J\u0016\u0010-\u001a\u00020\u0000H\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b.\u0010\u0005J\u001b\u0010/\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\rH\u0087\nø\u0001\u0000¢\u0006\u0004\b0\u0010\u000fJ\u001b\u0010/\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b1\u0010\u000bJ\u001b\u0010/\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\b2\u0010\u001dJ\u001b\u0010/\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001\u0000¢\u0006\u0004\b3\u0010\u0016J\u001b\u00104\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\rH\u0087\bø\u0001\u0000¢\u0006\u0004\b5\u00106J\u001b\u00104\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\bø\u0001\u0000¢\u0006\u0004\b7\u0010\u000bJ\u001b\u00104\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\bø\u0001\u0000¢\u0006\u0004\b8\u0010\u001dJ\u001b\u00104\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\u0014H\u0087\bø\u0001\u0000¢\u0006\u0004\b9\u0010:J\u001b\u0010;\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\fø\u0001\u0000¢\u0006\u0004\b<\u0010\u000bJ\u001b\u0010=\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\rH\u0087\nø\u0001\u0000¢\u0006\u0004\b>\u0010\u000fJ\u001b\u0010=\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b?\u0010\u000bJ\u001b\u0010=\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\b@\u0010\u001dJ\u001b\u0010=\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001\u0000¢\u0006\u0004\bA\u0010\u0016J\u001b\u0010B\u001a\u00020C2\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\bD\u0010EJ\u001b\u0010F\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\rH\u0087\nø\u0001\u0000¢\u0006\u0004\bG\u0010\u000fJ\u001b\u0010F\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\bH\u0010\u000bJ\u001b\u0010F\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\bI\u0010\u001dJ\u001b\u0010F\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001\u0000¢\u0006\u0004\bJ\u0010\u0016J\u001e\u0010K\u001a\u00020\u00002\u0006\u0010L\u001a\u00020\u0003H\u0087\fø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bM\u0010\u000bJ\u001e\u0010N\u001a\u00020\u00002\u0006\u0010L\u001a\u00020\u0003H\u0087\fø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bO\u0010\u000bJ\u001b\u0010P\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\rH\u0087\nø\u0001\u0000¢\u0006\u0004\bQ\u0010\u000fJ\u001b\u0010P\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\bR\u0010\u000bJ\u001b\u0010P\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\bS\u0010\u001dJ\u001b\u0010P\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001\u0000¢\u0006\u0004\bT\u0010\u0016J\u0010\u0010U\u001a\u00020VH\u0087\b¢\u0006\u0004\bW\u0010XJ\u0010\u0010Y\u001a\u00020ZH\u0087\b¢\u0006\u0004\b[\u0010\\J\u0010\u0010]\u001a\u00020^H\u0087\b¢\u0006\u0004\b_\u0010`J\u0010\u0010a\u001a\u00020\u0003H\u0087\b¢\u0006\u0004\bb\u0010\u0005J\u0010\u0010c\u001a\u00020dH\u0087\b¢\u0006\u0004\be\u0010fJ\u0010\u0010g\u001a\u00020hH\u0087\b¢\u0006\u0004\bi\u0010jJ\u000f\u0010k\u001a\u00020lH\u0016¢\u0006\u0004\bm\u0010nJ\u0016\u0010o\u001a\u00020\rH\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bp\u0010XJ\u0016\u0010q\u001a\u00020\u0000H\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\br\u0010\u0005J\u0016\u0010s\u001a\u00020\u0011H\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bt\u0010fJ\u0016\u0010u\u001a\u00020\u0014H\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bv\u0010jJ\u001b\u0010w\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\fø\u0001\u0000¢\u0006\u0004\bx\u0010\u000bR\u0016\u0010\u0002\u001a\u00020\u00038\u0000X\u0081\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0007\u0088\u0001\u0002\u0092\u0001\u00020\u0003ø\u0001\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006z"}, m183d2 = {"Lkotlin/UInt;", "", "data", "", "constructor-impl", "(I)I", "getData$annotations", "()V", "and", "other", "and-WZ4Q5Ns", "(II)I", "compareTo", "Lkotlin/UByte;", "compareTo-7apg3OU", "(IB)I", "compareTo-WZ4Q5Ns", "Lkotlin/ULong;", "compareTo-VKZWuLQ", "(IJ)I", "Lkotlin/UShort;", "compareTo-xj2QHRw", "(IS)I", "dec", "dec-pVg5ArA", TtmlNode.TAG_DIV, "div-7apg3OU", "div-WZ4Q5Ns", "div-VKZWuLQ", "(IJ)J", "div-xj2QHRw", "equals", "", "", "equals-impl", "(ILjava/lang/Object;)Z", "floorDiv", "floorDiv-7apg3OU", "floorDiv-WZ4Q5Ns", "floorDiv-VKZWuLQ", "floorDiv-xj2QHRw", "hashCode", "hashCode-impl", "inc", "inc-pVg5ArA", "inv", "inv-pVg5ArA", "minus", "minus-7apg3OU", "minus-WZ4Q5Ns", "minus-VKZWuLQ", "minus-xj2QHRw", "mod", "mod-7apg3OU", "(IB)B", "mod-WZ4Q5Ns", "mod-VKZWuLQ", "mod-xj2QHRw", "(IS)S", "or", "or-WZ4Q5Ns", "plus", "plus-7apg3OU", "plus-WZ4Q5Ns", "plus-VKZWuLQ", "plus-xj2QHRw", "rangeTo", "Lkotlin/ranges/UIntRange;", "rangeTo-WZ4Q5Ns", "(II)Lkotlin/ranges/UIntRange;", "rem", "rem-7apg3OU", "rem-WZ4Q5Ns", "rem-VKZWuLQ", "rem-xj2QHRw", "shl", "bitCount", "shl-pVg5ArA", "shr", "shr-pVg5ArA", "times", "times-7apg3OU", "times-WZ4Q5Ns", "times-VKZWuLQ", "times-xj2QHRw", "toByte", "", "toByte-impl", "(I)B", "toDouble", "", "toDouble-impl", "(I)D", "toFloat", "", "toFloat-impl", "(I)F", "toInt", "toInt-impl", "toLong", "", "toLong-impl", "(I)J", "toShort", "", "toShort-impl", "(I)S", "toString", "", "toString-impl", "(I)Ljava/lang/String;", "toUByte", "toUByte-w2LRezQ", "toUInt", "toUInt-pVg5ArA", "toULong", "toULong-s-VKNKU", "toUShort", "toUShort-Mh2AYeg", "xor", "xor-WZ4Q5Ns", "Companion", "kotlin-stdlib"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
@JvmInline
/* loaded from: classes5.dex */
public final class UInt implements Comparable<UInt> {
    public static final Companion Companion = new Companion(null);
    public static final int MAX_VALUE = -1;
    public static final int MIN_VALUE = 0;
    public static final int SIZE_BITS = 32;
    public static final int SIZE_BYTES = 4;
    private final int data;

    /* renamed from: box-impl  reason: not valid java name */
    public static final /* synthetic */ UInt m1837boximpl(int r1) {
        return new UInt(r1);
    }

    /* renamed from: constructor-impl  reason: not valid java name */
    public static int m1843constructorimpl(int r0) {
        return r0;
    }

    /* renamed from: equals-impl  reason: not valid java name */
    public static boolean m1849equalsimpl(int r2, Object obj) {
        return (obj instanceof UInt) && r2 == ((UInt) obj).m1894unboximpl();
    }

    /* renamed from: equals-impl0  reason: not valid java name */
    public static final boolean m1850equalsimpl0(int r0, int r1) {
        return r0 == r1;
    }

    public static /* synthetic */ void getData$annotations() {
    }

    /* renamed from: hashCode-impl  reason: not valid java name */
    public static int m1855hashCodeimpl(int r0) {
        return r0;
    }

    /* renamed from: toByte-impl  reason: not valid java name */
    private static final byte m1882toByteimpl(int r0) {
        return (byte) r0;
    }

    /* renamed from: toInt-impl  reason: not valid java name */
    private static final int m1885toIntimpl(int r0) {
        return r0;
    }

    /* renamed from: toLong-impl  reason: not valid java name */
    private static final long m1886toLongimpl(int r4) {
        return r4 & BodyPartID.bodyIdMax;
    }

    /* renamed from: toShort-impl  reason: not valid java name */
    private static final short m1887toShortimpl(int r0) {
        return (short) r0;
    }

    /* renamed from: toUInt-pVg5ArA  reason: not valid java name */
    private static final int m1890toUIntpVg5ArA(int r0) {
        return r0;
    }

    public boolean equals(Object obj) {
        return m1849equalsimpl(this.data, obj);
    }

    public int hashCode() {
        return m1855hashCodeimpl(this.data);
    }

    /* renamed from: unbox-impl  reason: not valid java name */
    public final /* synthetic */ int m1894unboximpl() {
        return this.data;
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(UInt uInt) {
        return UnsignedUtils.uintCompare(m1894unboximpl(), uInt.m1894unboximpl());
    }

    private /* synthetic */ UInt(int r1) {
        this.data = r1;
    }

    /* compiled from: UInt.kt */
    @Metadata(m184d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\u00020\u0004X\u0086Tø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\u0005R\u0016\u0010\u0006\u001a\u00020\u0004X\u0086Tø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\u0005R\u000e\u0010\u0007\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006\n"}, m183d2 = {"Lkotlin/UInt$Companion;", "", "()V", "MAX_VALUE", "Lkotlin/UInt;", "I", "MIN_VALUE", "SIZE_BITS", "", "SIZE_BYTES", "kotlin-stdlib"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes5.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* renamed from: compareTo-7apg3OU  reason: not valid java name */
    private static final int m1838compareTo7apg3OU(int r0, byte b) {
        return UnsignedUtils.uintCompare(r0, m1843constructorimpl(b & 255));
    }

    /* renamed from: compareTo-xj2QHRw  reason: not valid java name */
    private static final int m1842compareToxj2QHRw(int r1, short s) {
        return UnsignedUtils.uintCompare(r1, m1843constructorimpl(s & UShort.MAX_VALUE));
    }

    /* renamed from: compareTo-WZ4Q5Ns  reason: not valid java name */
    private int m1840compareToWZ4Q5Ns(int r2) {
        return UnsignedUtils.uintCompare(m1894unboximpl(), r2);
    }

    /* renamed from: compareTo-WZ4Q5Ns  reason: not valid java name */
    private static int m1841compareToWZ4Q5Ns(int r0, int r1) {
        return UnsignedUtils.uintCompare(r0, r1);
    }

    /* renamed from: compareTo-VKZWuLQ  reason: not valid java name */
    private static final int m1839compareToVKZWuLQ(int r4, long j) {
        return UnsignedUtils.ulongCompare(ULong.m1921constructorimpl(r4 & BodyPartID.bodyIdMax), j);
    }

    /* renamed from: plus-7apg3OU  reason: not valid java name */
    private static final int m1867plus7apg3OU(int r0, byte b) {
        return m1843constructorimpl(r0 + m1843constructorimpl(b & 255));
    }

    /* renamed from: plus-xj2QHRw  reason: not valid java name */
    private static final int m1870plusxj2QHRw(int r1, short s) {
        return m1843constructorimpl(r1 + m1843constructorimpl(s & UShort.MAX_VALUE));
    }

    /* renamed from: plus-WZ4Q5Ns  reason: not valid java name */
    private static final int m1869plusWZ4Q5Ns(int r0, int r1) {
        return m1843constructorimpl(r0 + r1);
    }

    /* renamed from: plus-VKZWuLQ  reason: not valid java name */
    private static final long m1868plusVKZWuLQ(int r4, long j) {
        return ULong.m1921constructorimpl(ULong.m1921constructorimpl(r4 & BodyPartID.bodyIdMax) + j);
    }

    /* renamed from: minus-7apg3OU  reason: not valid java name */
    private static final int m1858minus7apg3OU(int r0, byte b) {
        return m1843constructorimpl(r0 - m1843constructorimpl(b & 255));
    }

    /* renamed from: minus-xj2QHRw  reason: not valid java name */
    private static final int m1861minusxj2QHRw(int r1, short s) {
        return m1843constructorimpl(r1 - m1843constructorimpl(s & UShort.MAX_VALUE));
    }

    /* renamed from: minus-WZ4Q5Ns  reason: not valid java name */
    private static final int m1860minusWZ4Q5Ns(int r0, int r1) {
        return m1843constructorimpl(r0 - r1);
    }

    /* renamed from: minus-VKZWuLQ  reason: not valid java name */
    private static final long m1859minusVKZWuLQ(int r4, long j) {
        return ULong.m1921constructorimpl(ULong.m1921constructorimpl(r4 & BodyPartID.bodyIdMax) - j);
    }

    /* renamed from: times-7apg3OU  reason: not valid java name */
    private static final int m1878times7apg3OU(int r0, byte b) {
        return m1843constructorimpl(r0 * m1843constructorimpl(b & 255));
    }

    /* renamed from: times-xj2QHRw  reason: not valid java name */
    private static final int m1881timesxj2QHRw(int r1, short s) {
        return m1843constructorimpl(r1 * m1843constructorimpl(s & UShort.MAX_VALUE));
    }

    /* renamed from: times-WZ4Q5Ns  reason: not valid java name */
    private static final int m1880timesWZ4Q5Ns(int r0, int r1) {
        return m1843constructorimpl(r0 * r1);
    }

    /* renamed from: times-VKZWuLQ  reason: not valid java name */
    private static final long m1879timesVKZWuLQ(int r4, long j) {
        return ULong.m1921constructorimpl(ULong.m1921constructorimpl(r4 & BodyPartID.bodyIdMax) * j);
    }

    /* renamed from: div-7apg3OU  reason: not valid java name */
    private static final int m1845div7apg3OU(int r0, byte b) {
        return UnsignedUtils.m2096uintDivideJ1ME1BU(r0, m1843constructorimpl(b & 255));
    }

    /* renamed from: div-xj2QHRw  reason: not valid java name */
    private static final int m1848divxj2QHRw(int r1, short s) {
        return UnsignedUtils.m2096uintDivideJ1ME1BU(r1, m1843constructorimpl(s & UShort.MAX_VALUE));
    }

    /* renamed from: div-WZ4Q5Ns  reason: not valid java name */
    private static final int m1847divWZ4Q5Ns(int r0, int r1) {
        return UnsignedUtils.m2096uintDivideJ1ME1BU(r0, r1);
    }

    /* renamed from: div-VKZWuLQ  reason: not valid java name */
    private static final long m1846divVKZWuLQ(int r4, long j) {
        return UnsignedUtils.m2098ulongDivideeb3DHEI(ULong.m1921constructorimpl(r4 & BodyPartID.bodyIdMax), j);
    }

    /* renamed from: rem-7apg3OU  reason: not valid java name */
    private static final int m1872rem7apg3OU(int r0, byte b) {
        return UnsignedUtils.m2097uintRemainderJ1ME1BU(r0, m1843constructorimpl(b & 255));
    }

    /* renamed from: rem-xj2QHRw  reason: not valid java name */
    private static final int m1875remxj2QHRw(int r1, short s) {
        return UnsignedUtils.m2097uintRemainderJ1ME1BU(r1, m1843constructorimpl(s & UShort.MAX_VALUE));
    }

    /* renamed from: rem-WZ4Q5Ns  reason: not valid java name */
    private static final int m1874remWZ4Q5Ns(int r0, int r1) {
        return UnsignedUtils.m2097uintRemainderJ1ME1BU(r0, r1);
    }

    /* renamed from: rem-VKZWuLQ  reason: not valid java name */
    private static final long m1873remVKZWuLQ(int r4, long j) {
        return UnsignedUtils.m2099ulongRemaindereb3DHEI(ULong.m1921constructorimpl(r4 & BodyPartID.bodyIdMax), j);
    }

    /* renamed from: floorDiv-7apg3OU  reason: not valid java name */
    private static final int m1851floorDiv7apg3OU(int r0, byte b) {
        return UnsignedUtils.m2096uintDivideJ1ME1BU(r0, m1843constructorimpl(b & 255));
    }

    /* renamed from: floorDiv-xj2QHRw  reason: not valid java name */
    private static final int m1854floorDivxj2QHRw(int r1, short s) {
        return UnsignedUtils.m2096uintDivideJ1ME1BU(r1, m1843constructorimpl(s & UShort.MAX_VALUE));
    }

    /* renamed from: floorDiv-WZ4Q5Ns  reason: not valid java name */
    private static final int m1853floorDivWZ4Q5Ns(int r0, int r1) {
        return UnsignedUtils.m2096uintDivideJ1ME1BU(r0, r1);
    }

    /* renamed from: floorDiv-VKZWuLQ  reason: not valid java name */
    private static final long m1852floorDivVKZWuLQ(int r4, long j) {
        return UnsignedUtils.m2098ulongDivideeb3DHEI(ULong.m1921constructorimpl(r4 & BodyPartID.bodyIdMax), j);
    }

    /* renamed from: mod-7apg3OU  reason: not valid java name */
    private static final byte m1862mod7apg3OU(int r0, byte b) {
        return UByte.m1767constructorimpl((byte) UnsignedUtils.m2097uintRemainderJ1ME1BU(r0, m1843constructorimpl(b & 255)));
    }

    /* renamed from: mod-xj2QHRw  reason: not valid java name */
    private static final short m1865modxj2QHRw(int r1, short s) {
        return UShort.m2027constructorimpl((short) UnsignedUtils.m2097uintRemainderJ1ME1BU(r1, m1843constructorimpl(s & UShort.MAX_VALUE)));
    }

    /* renamed from: mod-WZ4Q5Ns  reason: not valid java name */
    private static final int m1864modWZ4Q5Ns(int r0, int r1) {
        return UnsignedUtils.m2097uintRemainderJ1ME1BU(r0, r1);
    }

    /* renamed from: mod-VKZWuLQ  reason: not valid java name */
    private static final long m1863modVKZWuLQ(int r4, long j) {
        return UnsignedUtils.m2099ulongRemaindereb3DHEI(ULong.m1921constructorimpl(r4 & BodyPartID.bodyIdMax), j);
    }

    /* renamed from: inc-pVg5ArA  reason: not valid java name */
    private static final int m1856incpVg5ArA(int r0) {
        return m1843constructorimpl(r0 + 1);
    }

    /* renamed from: dec-pVg5ArA  reason: not valid java name */
    private static final int m1844decpVg5ArA(int r0) {
        return m1843constructorimpl(r0 - 1);
    }

    /* renamed from: rangeTo-WZ4Q5Ns  reason: not valid java name */
    private static final UIntRange m1871rangeToWZ4Q5Ns(int r2, int r3) {
        return new UIntRange(r2, r3, null);
    }

    /* renamed from: shl-pVg5ArA  reason: not valid java name */
    private static final int m1876shlpVg5ArA(int r0, int r1) {
        return m1843constructorimpl(r0 << r1);
    }

    /* renamed from: shr-pVg5ArA  reason: not valid java name */
    private static final int m1877shrpVg5ArA(int r0, int r1) {
        return m1843constructorimpl(r0 >>> r1);
    }

    /* renamed from: and-WZ4Q5Ns  reason: not valid java name */
    private static final int m1836andWZ4Q5Ns(int r0, int r1) {
        return m1843constructorimpl(r0 & r1);
    }

    /* renamed from: or-WZ4Q5Ns  reason: not valid java name */
    private static final int m1866orWZ4Q5Ns(int r0, int r1) {
        return m1843constructorimpl(r0 | r1);
    }

    /* renamed from: xor-WZ4Q5Ns  reason: not valid java name */
    private static final int m1893xorWZ4Q5Ns(int r0, int r1) {
        return m1843constructorimpl(r0 ^ r1);
    }

    /* renamed from: inv-pVg5ArA  reason: not valid java name */
    private static final int m1857invpVg5ArA(int r0) {
        return m1843constructorimpl(~r0);
    }

    /* renamed from: toUByte-w2LRezQ  reason: not valid java name */
    private static final byte m1889toUBytew2LRezQ(int r0) {
        return UByte.m1767constructorimpl((byte) r0);
    }

    /* renamed from: toUShort-Mh2AYeg  reason: not valid java name */
    private static final short m1892toUShortMh2AYeg(int r0) {
        return UShort.m2027constructorimpl((short) r0);
    }

    /* renamed from: toULong-s-VKNKU  reason: not valid java name */
    private static final long m1891toULongsVKNKU(int r4) {
        return ULong.m1921constructorimpl(r4 & BodyPartID.bodyIdMax);
    }

    /* renamed from: toFloat-impl  reason: not valid java name */
    private static final float m1884toFloatimpl(int r2) {
        return (float) UnsignedUtils.uintToDouble(r2);
    }

    /* renamed from: toDouble-impl  reason: not valid java name */
    private static final double m1883toDoubleimpl(int r2) {
        return UnsignedUtils.uintToDouble(r2);
    }

    /* renamed from: toString-impl  reason: not valid java name */
    public static String m1888toStringimpl(int r4) {
        return String.valueOf(r4 & BodyPartID.bodyIdMax);
    }

    public String toString() {
        return m1888toStringimpl(this.data);
    }
}
