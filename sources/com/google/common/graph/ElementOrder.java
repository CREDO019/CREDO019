package com.google.common.graph;

import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import com.google.errorprone.annotations.Immutable;
import java.util.Comparator;
import java.util.Map;
import javax.annotation.CheckForNull;

@Immutable
@ElementTypesAreNonnullByDefault
/* loaded from: classes3.dex */
public final class ElementOrder<T> {
    @CheckForNull
    private final Comparator<T> comparator;
    private final Type type;

    /* loaded from: classes3.dex */
    public enum Type {
        UNORDERED,
        STABLE,
        INSERTION,
        SORTED
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    public <T1 extends T> ElementOrder<T1> cast() {
        return this;
    }

    private ElementOrder(Type type, @CheckForNull Comparator<T> comparator) {
        this.type = (Type) Preconditions.checkNotNull(type);
        this.comparator = comparator;
        Preconditions.checkState((type == Type.SORTED) == (comparator != null));
    }

    public static <S> ElementOrder<S> unordered() {
        return new ElementOrder<>(Type.UNORDERED, null);
    }

    public static <S> ElementOrder<S> stable() {
        return new ElementOrder<>(Type.STABLE, null);
    }

    public static <S> ElementOrder<S> insertion() {
        return new ElementOrder<>(Type.INSERTION, null);
    }

    public static <S extends Comparable<? super S>> ElementOrder<S> natural() {
        return new ElementOrder<>(Type.SORTED, Ordering.natural());
    }

    public static <S> ElementOrder<S> sorted(Comparator<S> comparator) {
        return new ElementOrder<>(Type.SORTED, (Comparator) Preconditions.checkNotNull(comparator));
    }

    public Type type() {
        return this.type;
    }

    public Comparator<T> comparator() {
        Comparator<T> comparator = this.comparator;
        if (comparator != null) {
            return comparator;
        }
        throw new UnsupportedOperationException("This ordering does not define a comparator.");
    }

    public boolean equals(@CheckForNull Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ElementOrder) {
            ElementOrder elementOrder = (ElementOrder) obj;
            return this.type == elementOrder.type && Objects.equal(this.comparator, elementOrder.comparator);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(this.type, this.comparator);
    }

    public String toString() {
        MoreObjects.ToStringHelper add = MoreObjects.toStringHelper(this).add(SessionDescription.ATTR_TYPE, this.type);
        Comparator<T> comparator = this.comparator;
        if (comparator != null) {
            add.add("comparator", comparator);
        }
        return add.toString();
    }

    /* renamed from: com.google.common.graph.ElementOrder$1 */
    /* loaded from: classes3.dex */
    static /* synthetic */ class C30211 {
        static final /* synthetic */ int[] $SwitchMap$com$google$common$graph$ElementOrder$Type;

        static {
            int[] r0 = new int[Type.values().length];
            $SwitchMap$com$google$common$graph$ElementOrder$Type = r0;
            try {
                r0[Type.UNORDERED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$common$graph$ElementOrder$Type[Type.INSERTION.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$common$graph$ElementOrder$Type[Type.STABLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$common$graph$ElementOrder$Type[Type.SORTED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public <K extends T, V> Map<K, V> createMap(int r3) {
        int r0 = C30211.$SwitchMap$com$google$common$graph$ElementOrder$Type[this.type.ordinal()];
        if (r0 != 1) {
            if (r0 == 2 || r0 == 3) {
                return Maps.newLinkedHashMapWithExpectedSize(r3);
            }
            if (r0 == 4) {
                return Maps.newTreeMap(comparator());
            }
            throw new AssertionError();
        }
        return Maps.newHashMapWithExpectedSize(r3);
    }
}
