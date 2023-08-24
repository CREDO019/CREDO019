package kotlin.random;

import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import com.google.firebase.messaging.Constants;
import kotlin.Metadata;
import kotlin.UByteArray;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UnsignedUtils;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.UIntRange;
import kotlin.ranges.ULongRange;
import org.bouncycastle.asn1.cmc.BodyPartID;

@Metadata(m184d1 = {"\u0000:\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\"\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0000ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u001a\"\u0010\u0007\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\bH\u0000ø\u0001\u0000¢\u0006\u0004\b\t\u0010\n\u001a\u001c\u0010\u000b\u001a\u00020\f*\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0010\u001a\u001e\u0010\u000b\u001a\u00020\f*\u00020\r2\u0006\u0010\u0011\u001a\u00020\fH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013\u001a2\u0010\u000b\u001a\u00020\f*\u00020\r2\u0006\u0010\u0011\u001a\u00020\f2\b\b\u0002\u0010\u0014\u001a\u00020\u000f2\b\b\u0002\u0010\u0015\u001a\u00020\u000fH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\u0017\u001a\u0014\u0010\u0018\u001a\u00020\u0003*\u00020\rH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0019\u001a\u001e\u0010\u0018\u001a\u00020\u0003*\u00020\r2\u0006\u0010\u0004\u001a\u00020\u0003H\u0007ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u001b\u001a&\u0010\u0018\u001a\u00020\u0003*\u00020\r2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0007ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001d\u001a\u001c\u0010\u0018\u001a\u00020\u0003*\u00020\r2\u0006\u0010\u001e\u001a\u00020\u001fH\u0007ø\u0001\u0000¢\u0006\u0002\u0010 \u001a\u0014\u0010!\u001a\u00020\b*\u00020\rH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\"\u001a\u001e\u0010!\u001a\u00020\b*\u00020\r2\u0006\u0010\u0004\u001a\u00020\bH\u0007ø\u0001\u0000¢\u0006\u0004\b#\u0010$\u001a&\u0010!\u001a\u00020\b*\u00020\r2\u0006\u0010\u0002\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\bH\u0007ø\u0001\u0000¢\u0006\u0004\b%\u0010&\u001a\u001c\u0010!\u001a\u00020\b*\u00020\r2\u0006\u0010\u001e\u001a\u00020'H\u0007ø\u0001\u0000¢\u0006\u0002\u0010(\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006)"}, m183d2 = {"checkUIntRangeBounds", "", Constants.MessagePayloadKeys.FROM, "Lkotlin/UInt;", "until", "checkUIntRangeBounds-J1ME1BU", "(II)V", "checkULongRangeBounds", "Lkotlin/ULong;", "checkULongRangeBounds-eb3DHEI", "(JJ)V", "nextUBytes", "Lkotlin/UByteArray;", "Lkotlin/random/Random;", "size", "", "(Lkotlin/random/Random;I)[B", "array", "nextUBytes-EVgfTAA", "(Lkotlin/random/Random;[B)[B", "fromIndex", "toIndex", "nextUBytes-Wvrt4B4", "(Lkotlin/random/Random;[BII)[B", "nextUInt", "(Lkotlin/random/Random;)I", "nextUInt-qCasIEU", "(Lkotlin/random/Random;I)I", "nextUInt-a8DCA5k", "(Lkotlin/random/Random;II)I", SessionDescription.ATTR_RANGE, "Lkotlin/ranges/UIntRange;", "(Lkotlin/random/Random;Lkotlin/ranges/UIntRange;)I", "nextULong", "(Lkotlin/random/Random;)J", "nextULong-V1Xi4fY", "(Lkotlin/random/Random;J)J", "nextULong-jmpaW-c", "(Lkotlin/random/Random;JJ)J", "Lkotlin/ranges/ULongRange;", "(Lkotlin/random/Random;Lkotlin/ranges/ULongRange;)J", "kotlin-stdlib"}, m182k = 2, m181mv = {1, 6, 0}, m179xi = 48)
/* renamed from: kotlin.random.URandomKt */
/* loaded from: classes5.dex */
public final class URandom {
    public static final int nextUInt(Random random) {
        Intrinsics.checkNotNullParameter(random, "<this>");
        return UInt.m1843constructorimpl(random.nextInt());
    }

    /* renamed from: nextUInt-qCasIEU  reason: not valid java name */
    public static final int m2951nextUIntqCasIEU(Random nextUInt, int r2) {
        Intrinsics.checkNotNullParameter(nextUInt, "$this$nextUInt");
        return m2950nextUInta8DCA5k(nextUInt, 0, r2);
    }

    /* renamed from: nextUInt-a8DCA5k  reason: not valid java name */
    public static final int m2950nextUInta8DCA5k(Random nextUInt, int r2, int r3) {
        Intrinsics.checkNotNullParameter(nextUInt, "$this$nextUInt");
        m2945checkUIntRangeBoundsJ1ME1BU(r2, r3);
        return UInt.m1843constructorimpl(nextUInt.nextInt(r2 ^ Integer.MIN_VALUE, r3 ^ Integer.MIN_VALUE) ^ Integer.MIN_VALUE);
    }

    public static final int nextUInt(Random random, UIntRange range) {
        Intrinsics.checkNotNullParameter(random, "<this>");
        Intrinsics.checkNotNullParameter(range, "range");
        if (!range.isEmpty()) {
            return UnsignedUtils.uintCompare(range.m2955getLastpVg5ArA(), -1) < 0 ? m2950nextUInta8DCA5k(random, range.m2954getFirstpVg5ArA(), UInt.m1843constructorimpl(range.m2955getLastpVg5ArA() + 1)) : UnsignedUtils.uintCompare(range.m2954getFirstpVg5ArA(), 0) > 0 ? UInt.m1843constructorimpl(m2950nextUInta8DCA5k(random, UInt.m1843constructorimpl(range.m2954getFirstpVg5ArA() - 1), range.m2955getLastpVg5ArA()) + 1) : nextUInt(random);
        }
        throw new IllegalArgumentException("Cannot get random in empty range: " + range);
    }

    public static final long nextULong(Random random) {
        Intrinsics.checkNotNullParameter(random, "<this>");
        return ULong.m1921constructorimpl(random.nextLong());
    }

    /* renamed from: nextULong-V1Xi4fY  reason: not valid java name */
    public static final long m2952nextULongV1Xi4fY(Random nextULong, long j) {
        Intrinsics.checkNotNullParameter(nextULong, "$this$nextULong");
        return m2953nextULongjmpaWc(nextULong, 0L, j);
    }

    /* renamed from: nextULong-jmpaW-c  reason: not valid java name */
    public static final long m2953nextULongjmpaWc(Random nextULong, long j, long j2) {
        Intrinsics.checkNotNullParameter(nextULong, "$this$nextULong");
        m2946checkULongRangeBoundseb3DHEI(j, j2);
        return ULong.m1921constructorimpl(nextULong.nextLong(j ^ Long.MIN_VALUE, j2 ^ Long.MIN_VALUE) ^ Long.MIN_VALUE);
    }

    public static final long nextULong(Random random, ULongRange range) {
        Intrinsics.checkNotNullParameter(random, "<this>");
        Intrinsics.checkNotNullParameter(range, "range");
        if (range.isEmpty()) {
            throw new IllegalArgumentException("Cannot get random in empty range: " + range);
        } else if (UnsignedUtils.ulongCompare(range.m2961getLastsVKNKU(), -1L) < 0) {
            return m2953nextULongjmpaWc(random, range.m2960getFirstsVKNKU(), ULong.m1921constructorimpl(range.m2961getLastsVKNKU() + ULong.m1921constructorimpl(1 & BodyPartID.bodyIdMax)));
        } else {
            if (UnsignedUtils.ulongCompare(range.m2960getFirstsVKNKU(), 0L) > 0) {
                long j = range.m2960getFirstsVKNKU();
                long j2 = 1 & BodyPartID.bodyIdMax;
                return ULong.m1921constructorimpl(m2953nextULongjmpaWc(random, ULong.m1921constructorimpl(j - ULong.m1921constructorimpl(j2)), range.m2961getLastsVKNKU()) + ULong.m1921constructorimpl(j2));
            }
            return nextULong(random);
        }
    }

    /* renamed from: nextUBytes-EVgfTAA  reason: not valid java name */
    public static final byte[] m2947nextUBytesEVgfTAA(Random nextUBytes, byte[] array) {
        Intrinsics.checkNotNullParameter(nextUBytes, "$this$nextUBytes");
        Intrinsics.checkNotNullParameter(array, "array");
        nextUBytes.nextBytes(array);
        return array;
    }

    public static final byte[] nextUBytes(Random random, int r2) {
        Intrinsics.checkNotNullParameter(random, "<this>");
        return UByteArray.m1819constructorimpl(random.nextBytes(r2));
    }

    /* renamed from: nextUBytes-Wvrt4B4$default  reason: not valid java name */
    public static /* synthetic */ byte[] m2949nextUBytesWvrt4B4$default(Random random, byte[] bArr, int r2, int r3, int r4, Object obj) {
        if ((r4 & 2) != 0) {
            r2 = 0;
        }
        if ((r4 & 4) != 0) {
            r3 = UByteArray.m1825getSizeimpl(bArr);
        }
        return m2948nextUBytesWvrt4B4(random, bArr, r2, r3);
    }

    /* renamed from: nextUBytes-Wvrt4B4  reason: not valid java name */
    public static final byte[] m2948nextUBytesWvrt4B4(Random nextUBytes, byte[] array, int r3, int r4) {
        Intrinsics.checkNotNullParameter(nextUBytes, "$this$nextUBytes");
        Intrinsics.checkNotNullParameter(array, "array");
        nextUBytes.nextBytes(array, r3, r4);
        return array;
    }

    /* renamed from: checkUIntRangeBounds-J1ME1BU  reason: not valid java name */
    public static final void m2945checkUIntRangeBoundsJ1ME1BU(int r1, int r2) {
        if (!(UnsignedUtils.uintCompare(r2, r1) > 0)) {
            throw new IllegalArgumentException(RandomKt.boundsErrorMessage(UInt.m1837boximpl(r1), UInt.m1837boximpl(r2)).toString());
        }
    }

    /* renamed from: checkULongRangeBounds-eb3DHEI  reason: not valid java name */
    public static final void m2946checkULongRangeBoundseb3DHEI(long j, long j2) {
        if (!(UnsignedUtils.ulongCompare(j2, j) > 0)) {
            throw new IllegalArgumentException(RandomKt.boundsErrorMessage(ULong.m1915boximpl(j), ULong.m1915boximpl(j2)).toString());
        }
    }
}
