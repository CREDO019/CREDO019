package kotlin.ranges;

import kotlin.Metadata;
import kotlin.ULong;
import kotlin.UnsignedUtils;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: ULongRange.kt */
@Metadata(m184d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0007\u0018\u0000 \u00172\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002:\u0001\u0017B\u0018\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u0006J\u001b\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0003H\u0096\u0002ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000eJ\u0013\u0010\u000f\u001a\u00020\u000b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0096\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u000bH\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016R\u001a\u0010\u0005\u001a\u00020\u00038VX\u0096\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\u0004\u001a\u00020\u00038VX\u0096\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\t\u0010\bø\u0001\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006\u0018"}, m183d2 = {"Lkotlin/ranges/ULongRange;", "Lkotlin/ranges/ULongProgression;", "Lkotlin/ranges/ClosedRange;", "Lkotlin/ULong;", "start", "endInclusive", "(JJLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getEndInclusive-s-VKNKU", "()J", "getStart-s-VKNKU", "contains", "", "value", "contains-VKZWuLQ", "(J)Z", "equals", "other", "", "hashCode", "", "isEmpty", "toString", "", "Companion", "kotlin-stdlib"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes5.dex */
public final class ULongRange extends ULongProgression implements ClosedRange<ULong> {
    public static final Companion Companion = new Companion(null);
    private static final ULongRange EMPTY = new ULongRange(-1, 0, null);

    public /* synthetic */ ULongRange(long j, long j2, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, j2);
    }

    @Override // kotlin.ranges.ClosedRange
    public /* bridge */ /* synthetic */ boolean contains(ULong uLong) {
        return m2963containsVKZWuLQ(uLong.m1972unboximpl());
    }

    @Override // kotlin.ranges.ClosedRange
    public /* bridge */ /* synthetic */ ULong getEndInclusive() {
        return ULong.m1915boximpl(m2964getEndInclusivesVKNKU());
    }

    @Override // kotlin.ranges.ClosedRange
    public /* bridge */ /* synthetic */ ULong getStart() {
        return ULong.m1915boximpl(m2965getStartsVKNKU());
    }

    private ULongRange(long j, long j2) {
        super(j, j2, 1L, null);
    }

    /* renamed from: getStart-s-VKNKU  reason: not valid java name */
    public long m2965getStartsVKNKU() {
        return m2960getFirstsVKNKU();
    }

    /* renamed from: getEndInclusive-s-VKNKU  reason: not valid java name */
    public long m2964getEndInclusivesVKNKU() {
        return m2961getLastsVKNKU();
    }

    /* renamed from: contains-VKZWuLQ  reason: not valid java name */
    public boolean m2963containsVKZWuLQ(long j) {
        return UnsignedUtils.ulongCompare(m2960getFirstsVKNKU(), j) <= 0 && UnsignedUtils.ulongCompare(j, m2961getLastsVKNKU()) <= 0;
    }

    @Override // kotlin.ranges.ULongProgression, kotlin.ranges.ClosedRange
    public boolean isEmpty() {
        return UnsignedUtils.ulongCompare(m2960getFirstsVKNKU(), m2961getLastsVKNKU()) > 0;
    }

    @Override // kotlin.ranges.ULongProgression
    public boolean equals(Object obj) {
        if (obj instanceof ULongRange) {
            if (!isEmpty() || !((ULongRange) obj).isEmpty()) {
                ULongRange uLongRange = (ULongRange) obj;
                if (m2960getFirstsVKNKU() != uLongRange.m2960getFirstsVKNKU() || m2961getLastsVKNKU() != uLongRange.m2961getLastsVKNKU()) {
                }
            }
            return true;
        }
        return false;
    }

    @Override // kotlin.ranges.ULongProgression
    public int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return ((int) ULong.m1921constructorimpl(m2961getLastsVKNKU() ^ ULong.m1921constructorimpl(m2961getLastsVKNKU() >>> 32))) + (((int) ULong.m1921constructorimpl(m2960getFirstsVKNKU() ^ ULong.m1921constructorimpl(m2960getFirstsVKNKU() >>> 32))) * 31);
    }

    @Override // kotlin.ranges.ULongProgression
    public String toString() {
        return ((Object) ULong.m1966toStringimpl(m2960getFirstsVKNKU())) + ".." + ((Object) ULong.m1966toStringimpl(m2961getLastsVKNKU()));
    }

    /* compiled from: ULongRange.kt */
    @Metadata(m184d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, m183d2 = {"Lkotlin/ranges/ULongRange$Companion;", "", "()V", "EMPTY", "Lkotlin/ranges/ULongRange;", "getEMPTY", "()Lkotlin/ranges/ULongRange;", "kotlin-stdlib"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes5.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ULongRange getEMPTY() {
            return ULongRange.EMPTY;
        }
    }
}
