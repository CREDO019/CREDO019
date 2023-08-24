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
public final class ImmutableIntArray implements Serializable {
    private static final ImmutableIntArray EMPTY = new ImmutableIntArray(new int[0]);
    private final int[] array;
    private final int end;
    private final transient int start;

    /* renamed from: of */
    public static ImmutableIntArray m275of() {
        return EMPTY;
    }

    /* renamed from: of */
    public static ImmutableIntArray m274of(int r3) {
        return new ImmutableIntArray(new int[]{r3});
    }

    /* renamed from: of */
    public static ImmutableIntArray m273of(int r3, int r4) {
        return new ImmutableIntArray(new int[]{r3, r4});
    }

    /* renamed from: of */
    public static ImmutableIntArray m272of(int r3, int r4, int r5) {
        return new ImmutableIntArray(new int[]{r3, r4, r5});
    }

    /* renamed from: of */
    public static ImmutableIntArray m271of(int r3, int r4, int r5, int r6) {
        return new ImmutableIntArray(new int[]{r3, r4, r5, r6});
    }

    /* renamed from: of */
    public static ImmutableIntArray m270of(int r3, int r4, int r5, int r6, int r7) {
        return new ImmutableIntArray(new int[]{r3, r4, r5, r6, r7});
    }

    /* renamed from: of */
    public static ImmutableIntArray m269of(int r3, int r4, int r5, int r6, int r7, int r8) {
        return new ImmutableIntArray(new int[]{r3, r4, r5, r6, r7, r8});
    }

    /* renamed from: of */
    public static ImmutableIntArray m268of(int r4, int... r5) {
        Preconditions.checkArgument(r5.length <= 2147483646, "the total number of elements must fit in an int");
        int[] r0 = new int[r5.length + 1];
        r0[0] = r4;
        System.arraycopy(r5, 0, r0, 1, r5.length);
        return new ImmutableIntArray(r0);
    }

    public static ImmutableIntArray copyOf(int[] r2) {
        return r2.length == 0 ? EMPTY : new ImmutableIntArray(Arrays.copyOf(r2, r2.length));
    }

    public static ImmutableIntArray copyOf(Collection<Integer> collection) {
        return collection.isEmpty() ? EMPTY : new ImmutableIntArray(Ints.toArray(collection));
    }

    public static ImmutableIntArray copyOf(Iterable<Integer> iterable) {
        if (iterable instanceof Collection) {
            return copyOf((Collection<Integer>) iterable);
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
        private int[] array;
        private int count = 0;

        Builder(int r2) {
            this.array = new int[r2];
        }

        public Builder add(int r4) {
            ensureRoomFor(1);
            int[] r1 = this.array;
            int r2 = this.count;
            r1[r2] = r4;
            this.count = r2 + 1;
            return this;
        }

        public Builder addAll(int[] r5) {
            ensureRoomFor(r5.length);
            System.arraycopy(r5, 0, this.array, this.count, r5.length);
            this.count += r5.length;
            return this;
        }

        public Builder addAll(Iterable<Integer> iterable) {
            if (iterable instanceof Collection) {
                return addAll((Collection) iterable);
            }
            for (Integer num : iterable) {
                add(num.intValue());
            }
            return this;
        }

        public Builder addAll(Collection<Integer> collection) {
            ensureRoomFor(collection.size());
            for (Integer num : collection) {
                int[] r1 = this.array;
                int r2 = this.count;
                this.count = r2 + 1;
                r1[r2] = num.intValue();
            }
            return this;
        }

        public Builder addAll(ImmutableIntArray immutableIntArray) {
            ensureRoomFor(immutableIntArray.length());
            System.arraycopy(immutableIntArray.array, immutableIntArray.start, this.array, this.count, immutableIntArray.length());
            this.count += immutableIntArray.length();
            return this;
        }

        private void ensureRoomFor(int r3) {
            int r0 = this.count + r3;
            int[] r32 = this.array;
            if (r0 > r32.length) {
                this.array = Arrays.copyOf(r32, expandedCapacity(r32.length, r0));
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
        public ImmutableIntArray build() {
            return this.count == 0 ? ImmutableIntArray.EMPTY : new ImmutableIntArray(this.array, 0, this.count);
        }
    }

    private ImmutableIntArray(int[] r3) {
        this(r3, 0, r3.length);
    }

    private ImmutableIntArray(int[] r1, int r2, int r3) {
        this.array = r1;
        this.start = r2;
        this.end = r3;
    }

    public int length() {
        return this.end - this.start;
    }

    public boolean isEmpty() {
        return this.end == this.start;
    }

    public int get(int r3) {
        Preconditions.checkElementIndex(r3, length());
        return this.array[this.start + r3];
    }

    public int indexOf(int r3) {
        for (int r0 = this.start; r0 < this.end; r0++) {
            if (this.array[r0] == r3) {
                return r0 - this.start;
            }
        }
        return -1;
    }

    public int lastIndexOf(int r4) {
        int r0 = this.end;
        while (true) {
            r0--;
            int r1 = this.start;
            if (r0 < r1) {
                return -1;
            }
            if (this.array[r0] == r4) {
                return r0 - r1;
            }
        }
    }

    public boolean contains(int r1) {
        return indexOf(r1) >= 0;
    }

    public int[] toArray() {
        return Arrays.copyOfRange(this.array, this.start, this.end);
    }

    public ImmutableIntArray subArray(int r4, int r5) {
        Preconditions.checkPositionIndexes(r4, r5, length());
        if (r4 == r5) {
            return EMPTY;
        }
        int[] r1 = this.array;
        int r2 = this.start;
        return new ImmutableIntArray(r1, r4 + r2, r2 + r5);
    }

    public List<Integer> asList() {
        return new AsList();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class AsList extends AbstractList<Integer> implements RandomAccess, Serializable {
        private final ImmutableIntArray parent;

        private AsList(ImmutableIntArray immutableIntArray) {
            this.parent = immutableIntArray;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.parent.length();
        }

        @Override // java.util.AbstractList, java.util.List
        public Integer get(int r2) {
            return Integer.valueOf(this.parent.get(r2));
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean contains(@CheckForNull Object obj) {
            return indexOf(obj) >= 0;
        }

        @Override // java.util.AbstractList, java.util.List
        public int indexOf(@CheckForNull Object obj) {
            if (obj instanceof Integer) {
                return this.parent.indexOf(((Integer) obj).intValue());
            }
            return -1;
        }

        @Override // java.util.AbstractList, java.util.List
        public int lastIndexOf(@CheckForNull Object obj) {
            if (obj instanceof Integer) {
                return this.parent.lastIndexOf(((Integer) obj).intValue());
            }
            return -1;
        }

        @Override // java.util.AbstractList, java.util.List
        public List<Integer> subList(int r2, int r3) {
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
                    if (obj2 instanceof Integer) {
                        int r4 = r0 + 1;
                        if (this.parent.array[r0] == ((Integer) obj2).intValue()) {
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
        if (obj instanceof ImmutableIntArray) {
            ImmutableIntArray immutableIntArray = (ImmutableIntArray) obj;
            if (length() != immutableIntArray.length()) {
                return false;
            }
            for (int r1 = 0; r1 < length(); r1++) {
                if (get(r1) != immutableIntArray.get(r1)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public int hashCode() {
        int r1 = 1;
        for (int r0 = this.start; r0 < this.end; r0++) {
            r1 = (r1 * 31) + Ints.hashCode(this.array[r0]);
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

    public ImmutableIntArray trimmed() {
        return isPartialView() ? new ImmutableIntArray(toArray()) : this;
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
