package com.google.common.primitives;

import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CheckReturnValue;
import com.google.errorprone.annotations.Immutable;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.RandomAccess;
import javax.annotation.CheckForNull;
import okhttp3.HttpUrl;

@ElementTypesAreNonnullByDefault
@Immutable
/* loaded from: classes3.dex */
public final class ImmutableDoubleArray implements Serializable {
    private static final ImmutableDoubleArray EMPTY = new ImmutableDoubleArray(new double[0]);
    private final double[] array;
    private final int end;
    private final transient int start;

    /* renamed from: of */
    public static ImmutableDoubleArray m283of() {
        return EMPTY;
    }

    /* renamed from: of */
    public static ImmutableDoubleArray m282of(double d) {
        return new ImmutableDoubleArray(new double[]{d});
    }

    /* renamed from: of */
    public static ImmutableDoubleArray m281of(double d, double d2) {
        return new ImmutableDoubleArray(new double[]{d, d2});
    }

    /* renamed from: of */
    public static ImmutableDoubleArray m280of(double d, double d2, double d3) {
        return new ImmutableDoubleArray(new double[]{d, d2, d3});
    }

    /* renamed from: of */
    public static ImmutableDoubleArray m279of(double d, double d2, double d3, double d4) {
        return new ImmutableDoubleArray(new double[]{d, d2, d3, d4});
    }

    /* renamed from: of */
    public static ImmutableDoubleArray m278of(double d, double d2, double d3, double d4, double d5) {
        return new ImmutableDoubleArray(new double[]{d, d2, d3, d4, d5});
    }

    /* renamed from: of */
    public static ImmutableDoubleArray m277of(double d, double d2, double d3, double d4, double d5, double d6) {
        return new ImmutableDoubleArray(new double[]{d, d2, d3, d4, d5, d6});
    }

    /* renamed from: of */
    public static ImmutableDoubleArray m276of(double d, double... dArr) {
        Preconditions.checkArgument(dArr.length <= 2147483646, "the total number of elements must fit in an int");
        double[] dArr2 = new double[dArr.length + 1];
        dArr2[0] = d;
        System.arraycopy(dArr, 0, dArr2, 1, dArr.length);
        return new ImmutableDoubleArray(dArr2);
    }

    public static ImmutableDoubleArray copyOf(double[] dArr) {
        if (dArr.length == 0) {
            return EMPTY;
        }
        return new ImmutableDoubleArray(Arrays.copyOf(dArr, dArr.length));
    }

    public static ImmutableDoubleArray copyOf(Collection<Double> collection) {
        return collection.isEmpty() ? EMPTY : new ImmutableDoubleArray(Doubles.toArray(collection));
    }

    public static ImmutableDoubleArray copyOf(Iterable<Double> iterable) {
        if (iterable instanceof Collection) {
            return copyOf((Collection<Double>) iterable);
        }
        return builder().addAll(iterable).build();
    }

    public static Builder builder(int r2) {
        Preconditions.checkArgument(r2 >= 0, "Invalid initialCapacity: %s", r2);
        return new Builder(r2);
    }

    public static Builder builder() {
        return new Builder(10);
    }

    /* loaded from: classes3.dex */
    public static final class Builder {
        private double[] array;
        private int count = 0;

        Builder(int r2) {
            this.array = new double[r2];
        }

        public Builder add(double d) {
            ensureRoomFor(1);
            double[] dArr = this.array;
            int r2 = this.count;
            dArr[r2] = d;
            this.count = r2 + 1;
            return this;
        }

        public Builder addAll(double[] dArr) {
            ensureRoomFor(dArr.length);
            System.arraycopy(dArr, 0, this.array, this.count, dArr.length);
            this.count += dArr.length;
            return this;
        }

        public Builder addAll(Iterable<Double> iterable) {
            if (iterable instanceof Collection) {
                return addAll((Collection) iterable);
            }
            for (Double d : iterable) {
                add(d.doubleValue());
            }
            return this;
        }

        public Builder addAll(Collection<Double> collection) {
            ensureRoomFor(collection.size());
            for (Double d : collection) {
                double[] dArr = this.array;
                int r2 = this.count;
                this.count = r2 + 1;
                dArr[r2] = d.doubleValue();
            }
            return this;
        }

        public Builder addAll(ImmutableDoubleArray immutableDoubleArray) {
            ensureRoomFor(immutableDoubleArray.length());
            System.arraycopy(immutableDoubleArray.array, immutableDoubleArray.start, this.array, this.count, immutableDoubleArray.length());
            this.count += immutableDoubleArray.length();
            return this;
        }

        private void ensureRoomFor(int r3) {
            int r0 = this.count + r3;
            double[] dArr = this.array;
            if (r0 > dArr.length) {
                this.array = Arrays.copyOf(dArr, expandedCapacity(dArr.length, r0));
            }
        }

        private static int expandedCapacity(int r1, int r2) {
            if (r2 >= 0) {
                int r12 = r1 + (r1 >> 1) + 1;
                if (r12 < r2) {
                    r12 = Integer.highestOneBit(r2 - 1) << 1;
                }
                if (r12 < 0) {
                    return Integer.MAX_VALUE;
                }
                return r12;
            }
            throw new AssertionError("cannot store more than MAX_VALUE elements");
        }

        @CheckReturnValue
        public ImmutableDoubleArray build() {
            return this.count == 0 ? ImmutableDoubleArray.EMPTY : new ImmutableDoubleArray(this.array, 0, this.count);
        }
    }

    private ImmutableDoubleArray(double[] dArr) {
        this(dArr, 0, dArr.length);
    }

    private ImmutableDoubleArray(double[] dArr, int r2, int r3) {
        this.array = dArr;
        this.start = r2;
        this.end = r3;
    }

    public int length() {
        return this.end - this.start;
    }

    public boolean isEmpty() {
        return this.end == this.start;
    }

    public double get(int r4) {
        Preconditions.checkElementIndex(r4, length());
        return this.array[this.start + r4];
    }

    public int indexOf(double d) {
        for (int r0 = this.start; r0 < this.end; r0++) {
            if (areEqual(this.array[r0], d)) {
                return r0 - this.start;
            }
        }
        return -1;
    }

    public int lastIndexOf(double d) {
        int r0 = this.end;
        while (true) {
            r0--;
            if (r0 < this.start) {
                return -1;
            }
            if (areEqual(this.array[r0], d)) {
                return r0 - this.start;
            }
        }
    }

    public boolean contains(double d) {
        return indexOf(d) >= 0;
    }

    public double[] toArray() {
        return Arrays.copyOfRange(this.array, this.start, this.end);
    }

    public ImmutableDoubleArray subArray(int r4, int r5) {
        Preconditions.checkPositionIndexes(r4, r5, length());
        if (r4 == r5) {
            return EMPTY;
        }
        double[] dArr = this.array;
        int r2 = this.start;
        return new ImmutableDoubleArray(dArr, r4 + r2, r2 + r5);
    }

    public List<Double> asList() {
        return new AsList();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class AsList extends AbstractList<Double> implements RandomAccess, Serializable {
        private final ImmutableDoubleArray parent;

        private AsList(ImmutableDoubleArray immutableDoubleArray) {
            this.parent = immutableDoubleArray;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.parent.length();
        }

        @Override // java.util.AbstractList, java.util.List
        public Double get(int r3) {
            return Double.valueOf(this.parent.get(r3));
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean contains(@CheckForNull Object obj) {
            return indexOf(obj) >= 0;
        }

        @Override // java.util.AbstractList, java.util.List
        public int indexOf(@CheckForNull Object obj) {
            if (obj instanceof Double) {
                return this.parent.indexOf(((Double) obj).doubleValue());
            }
            return -1;
        }

        @Override // java.util.AbstractList, java.util.List
        public int lastIndexOf(@CheckForNull Object obj) {
            if (obj instanceof Double) {
                return this.parent.lastIndexOf(((Double) obj).doubleValue());
            }
            return -1;
        }

        @Override // java.util.AbstractList, java.util.List
        public List<Double> subList(int r2, int r3) {
            return this.parent.subArray(r2, r3).asList();
        }

        @Override // java.util.AbstractList, java.util.Collection, java.util.List
        public boolean equals(@CheckForNull Object obj) {
            if (obj instanceof AsList) {
                return this.parent.equals(((AsList) obj).parent);
            }
            if (obj instanceof List) {
                List list = (List) obj;
                if (size() != list.size()) {
                    return false;
                }
                int r0 = this.parent.start;
                for (Object obj2 : list) {
                    if (obj2 instanceof Double) {
                        int r4 = r0 + 1;
                        if (ImmutableDoubleArray.areEqual(this.parent.array[r0], ((Double) obj2).doubleValue())) {
                            r0 = r4;
                        }
                    }
                    return false;
                }
                return true;
            }
            return false;
        }

        @Override // java.util.AbstractList, java.util.Collection, java.util.List
        public int hashCode() {
            return this.parent.hashCode();
        }

        @Override // java.util.AbstractCollection
        public String toString() {
            return this.parent.toString();
        }
    }

    public boolean equals(@CheckForNull Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ImmutableDoubleArray) {
            ImmutableDoubleArray immutableDoubleArray = (ImmutableDoubleArray) obj;
            if (length() != immutableDoubleArray.length()) {
                return false;
            }
            for (int r1 = 0; r1 < length(); r1++) {
                if (!areEqual(get(r1), immutableDoubleArray.get(r1))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean areEqual(double d, double d2) {
        return Double.doubleToLongBits(d) == Double.doubleToLongBits(d2);
    }

    public int hashCode() {
        int r1 = 1;
        for (int r0 = this.start; r0 < this.end; r0++) {
            r1 = (r1 * 31) + Doubles.hashCode(this.array[r0]);
        }
        return r1;
    }

    public String toString() {
        if (isEmpty()) {
            return HttpUrl.PATH_SEGMENT_ENCODE_SET_URI;
        }
        StringBuilder sb = new StringBuilder(length() * 5);
        sb.append('[');
        sb.append(this.array[this.start]);
        int r1 = this.start;
        while (true) {
            r1++;
            if (r1 < this.end) {
                sb.append(", ");
                sb.append(this.array[r1]);
            } else {
                sb.append(']');
                return sb.toString();
            }
        }
    }

    public ImmutableDoubleArray trimmed() {
        return isPartialView() ? new ImmutableDoubleArray(toArray()) : this;
    }

    private boolean isPartialView() {
        return this.start > 0 || this.end < this.array.length;
    }

    Object writeReplace() {
        return trimmed();
    }

    Object readResolve() {
        return isEmpty() ? EMPTY : this;
    }
}
