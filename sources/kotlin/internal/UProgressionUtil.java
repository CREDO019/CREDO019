package kotlin.internal;

import com.onesignal.NotificationBundleProcessor;
import kotlin.Metadata;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UnsignedUtils;

@Metadata(m184d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0001H\u0002ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u001a*\u0010\u0000\u001a\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u0007H\u0002ø\u0001\u0000¢\u0006\u0004\b\b\u0010\t\u001a*\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u000eH\u0001ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0006\u001a*\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0010H\u0001ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\t\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"}, m183d2 = {"differenceModulo", "Lkotlin/UInt;", NotificationBundleProcessor.PUSH_ADDITIONAL_DATA_KEY, "b", "c", "differenceModulo-WZ9TVnA", "(III)I", "Lkotlin/ULong;", "differenceModulo-sambcqE", "(JJJ)J", "getProgressionLastElement", "start", "end", "step", "", "getProgressionLastElement-Nkh28Cs", "", "getProgressionLastElement-7ftBX0g", "kotlin-stdlib"}, m182k = 2, m181mv = {1, 6, 0}, m179xi = 48)
/* renamed from: kotlin.internal.UProgressionUtilKt */
/* loaded from: classes5.dex */
public final class UProgressionUtil {
    /* renamed from: differenceModulo-WZ9TVnA  reason: not valid java name */
    private static final int m2937differenceModuloWZ9TVnA(int r1, int r2, int r3) {
        int m2097uintRemainderJ1ME1BU = UnsignedUtils.m2097uintRemainderJ1ME1BU(r1, r3);
        int m2097uintRemainderJ1ME1BU2 = UnsignedUtils.m2097uintRemainderJ1ME1BU(r2, r3);
        int uintCompare = UnsignedUtils.uintCompare(m2097uintRemainderJ1ME1BU, m2097uintRemainderJ1ME1BU2);
        int m1843constructorimpl = UInt.m1843constructorimpl(m2097uintRemainderJ1ME1BU - m2097uintRemainderJ1ME1BU2);
        return uintCompare >= 0 ? m1843constructorimpl : UInt.m1843constructorimpl(m1843constructorimpl + r3);
    }

    /* renamed from: differenceModulo-sambcqE  reason: not valid java name */
    private static final long m2938differenceModulosambcqE(long j, long j2, long j3) {
        long m2099ulongRemaindereb3DHEI = UnsignedUtils.m2099ulongRemaindereb3DHEI(j, j3);
        long m2099ulongRemaindereb3DHEI2 = UnsignedUtils.m2099ulongRemaindereb3DHEI(j2, j3);
        int ulongCompare = UnsignedUtils.ulongCompare(m2099ulongRemaindereb3DHEI, m2099ulongRemaindereb3DHEI2);
        long m1921constructorimpl = ULong.m1921constructorimpl(m2099ulongRemaindereb3DHEI - m2099ulongRemaindereb3DHEI2);
        return ulongCompare >= 0 ? m1921constructorimpl : ULong.m1921constructorimpl(m1921constructorimpl + j3);
    }

    /* renamed from: getProgressionLastElement-Nkh28Cs  reason: not valid java name */
    public static final int m2940getProgressionLastElementNkh28Cs(int r1, int r2, int r3) {
        if (r3 > 0) {
            return UnsignedUtils.uintCompare(r1, r2) >= 0 ? r2 : UInt.m1843constructorimpl(r2 - m2937differenceModuloWZ9TVnA(r2, r1, UInt.m1843constructorimpl(r3)));
        } else if (r3 < 0) {
            return UnsignedUtils.uintCompare(r1, r2) <= 0 ? r2 : UInt.m1843constructorimpl(r2 + m2937differenceModuloWZ9TVnA(r1, r2, UInt.m1843constructorimpl(-r3)));
        } else {
            throw new IllegalArgumentException("Step is zero.");
        }
    }

    /* renamed from: getProgressionLastElement-7ftBX0g  reason: not valid java name */
    public static final long m2939getProgressionLastElement7ftBX0g(long j, long j2, long j3) {
        int r2 = (j3 > 0L ? 1 : (j3 == 0L ? 0 : -1));
        if (r2 > 0) {
            return UnsignedUtils.ulongCompare(j, j2) >= 0 ? j2 : ULong.m1921constructorimpl(j2 - m2938differenceModulosambcqE(j2, j, ULong.m1921constructorimpl(j3)));
        } else if (r2 < 0) {
            return UnsignedUtils.ulongCompare(j, j2) <= 0 ? j2 : ULong.m1921constructorimpl(j2 + m2938differenceModulosambcqE(j, j2, ULong.m1921constructorimpl(-j3)));
        } else {
            throw new IllegalArgumentException("Step is zero.");
        }
    }
}
