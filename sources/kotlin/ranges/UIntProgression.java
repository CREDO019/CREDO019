package kotlin.ranges;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.UInt;
import kotlin.UnsignedUtils;
import kotlin.internal.UProgressionUtil;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.markers.KMarkers;

/* compiled from: UIntRange.kt */
@Metadata(m184d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010(\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0017\u0018\u0000 \u00192\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0019B\"\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0006ø\u0001\u0000¢\u0006\u0002\u0010\u0007J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0096\u0002J\b\u0010\u0013\u001a\u00020\u0006H\u0016J\b\u0010\u0014\u001a\u00020\u0010H\u0016J\u0012\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00020\u0016H\u0086\u0002ø\u0001\u0000J\b\u0010\u0017\u001a\u00020\u0018H\u0016R\u0019\u0010\b\u001a\u00020\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u0019\u0010\f\u001a\u00020\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\r\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\nø\u0001\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006\u001a"}, m183d2 = {"Lkotlin/ranges/UIntProgression;", "", "Lkotlin/UInt;", "start", "endInclusive", "step", "", "(IIILkotlin/jvm/internal/DefaultConstructorMarker;)V", "first", "getFirst-pVg5ArA", "()I", "I", "last", "getLast-pVg5ArA", "getStep", "equals", "", "other", "", "hashCode", "isEmpty", "iterator", "", "toString", "", "Companion", "kotlin-stdlib"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes5.dex */
public class UIntProgression implements Iterable<UInt>, KMarkers {
    public static final Companion Companion = new Companion(null);
    private final int first;
    private final int last;
    private final int step;

    public /* synthetic */ UIntProgression(int r1, int r2, int r3, DefaultConstructorMarker defaultConstructorMarker) {
        this(r1, r2, r3);
    }

    private UIntProgression(int r2, int r3, int r4) {
        if (r4 == 0) {
            throw new IllegalArgumentException("Step must be non-zero.");
        }
        if (r4 == Integer.MIN_VALUE) {
            throw new IllegalArgumentException("Step must be greater than Int.MIN_VALUE to avoid overflow on negation.");
        }
        this.first = r2;
        this.last = UProgressionUtil.m2940getProgressionLastElementNkh28Cs(r2, r3, r4);
        this.step = r4;
    }

    /* renamed from: getFirst-pVg5ArA  reason: not valid java name */
    public final int m2954getFirstpVg5ArA() {
        return this.first;
    }

    /* renamed from: getLast-pVg5ArA  reason: not valid java name */
    public final int m2955getLastpVg5ArA() {
        return this.last;
    }

    public final int getStep() {
        return this.step;
    }

    @Override // java.lang.Iterable
    public final Iterator<UInt> iterator() {
        return new UIntProgressionIterator(m2954getFirstpVg5ArA(), m2955getLastpVg5ArA(), this.step, null);
    }

    public boolean isEmpty() {
        if (this.step > 0) {
            if (UnsignedUtils.uintCompare(m2954getFirstpVg5ArA(), m2955getLastpVg5ArA()) > 0) {
                return true;
            }
        } else if (UnsignedUtils.uintCompare(m2954getFirstpVg5ArA(), m2955getLastpVg5ArA()) < 0) {
            return true;
        }
        return false;
    }

    public boolean equals(Object obj) {
        if (obj instanceof UIntProgression) {
            if (!isEmpty() || !((UIntProgression) obj).isEmpty()) {
                UIntProgression uIntProgression = (UIntProgression) obj;
                if (m2954getFirstpVg5ArA() != uIntProgression.m2954getFirstpVg5ArA() || m2955getLastpVg5ArA() != uIntProgression.m2955getLastpVg5ArA() || this.step != uIntProgression.step) {
                }
            }
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return (((m2954getFirstpVg5ArA() * 31) + m2955getLastpVg5ArA()) * 31) + this.step;
    }

    public String toString() {
        StringBuilder sb;
        int r1;
        if (this.step > 0) {
            sb = new StringBuilder();
            sb.append((Object) UInt.m1888toStringimpl(m2954getFirstpVg5ArA()));
            sb.append("..");
            sb.append((Object) UInt.m1888toStringimpl(m2955getLastpVg5ArA()));
            sb.append(" step ");
            r1 = this.step;
        } else {
            sb = new StringBuilder();
            sb.append((Object) UInt.m1888toStringimpl(m2954getFirstpVg5ArA()));
            sb.append(" downTo ");
            sb.append((Object) UInt.m1888toStringimpl(m2955getLastpVg5ArA()));
            sb.append(" step ");
            r1 = -this.step;
        }
        sb.append(r1);
        return sb.toString();
    }

    /* compiled from: UIntRange.kt */
    @Metadata(m184d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J(\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, m183d2 = {"Lkotlin/ranges/UIntProgression$Companion;", "", "()V", "fromClosedRange", "Lkotlin/ranges/UIntProgression;", "rangeStart", "Lkotlin/UInt;", "rangeEnd", "step", "", "fromClosedRange-Nkh28Cs", "(III)Lkotlin/ranges/UIntProgression;", "kotlin-stdlib"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes5.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* renamed from: fromClosedRange-Nkh28Cs  reason: not valid java name */
        public final UIntProgression m2956fromClosedRangeNkh28Cs(int r3, int r4, int r5) {
            return new UIntProgression(r3, r4, r5, null);
        }
    }
}
