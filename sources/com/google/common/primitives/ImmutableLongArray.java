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
public final class ImmutableLongArray implements Serializable {
    private static final ImmutableLongArray EMPTY = new ImmutableLongArray(new long[0]);
    private final long[] array;
    private final int end;
    private final transient int start;

    /* renamed from: of */
    public static ImmutableLongArray m267of() {
        return EMPTY;
    }

    /* renamed from: of */
    public static ImmutableLongArray m266of(long j) {
        return new ImmutableLongArray(new long[]{j});
    }

    /* renamed from: of */
    public static ImmutableLongArray m265of(long j, long j2) {
        return new ImmutableLongArray(new long[]{j, j2});
    }

    /* renamed from: of */
    public static ImmutableLongArray m264of(long j, long j2, long j3) {
        return new ImmutableLongArray(new long[]{j, j2, j3});
    }

    /* renamed from: of */
    public static ImmutableLongArray m263of(long j, long j2, long j3, long j4) {
        return new ImmutableLongArray(new long[]{j, j2, j3, j4});
    }

    /* renamed from: of */
    public static ImmutableLongArray m262of(long j, long j2, long j3, long j4, long j5) {
        return new ImmutableLongArray(new long[]{j, j2, j3, j4, j5});
    }

    /* renamed from: of */
    public static ImmutableLongArray m261of(long j, long j2, long j3, long j4, long j5, long j6) {
        return new ImmutableLongArray(new long[]{j, j2, j3, j4, j5, j6});
    }

    /* renamed from: of */
    public static ImmutableLongArray m260of(long j, long... jArr) {
        Preconditions.checkArgument(jArr.length <= 2147483646, "the total number of elements must fit in an int");
        long[] jArr2 = new long[jArr.length + 1];
        jArr2[0] = j;
        System.arraycopy(jArr, 0, jArr2, 1, jArr.length);
        return new ImmutableLongArray(jArr2);
    }

    public static ImmutableLongArray copyOf(long[] jArr) {
        if (jArr.length == 0) {
            return EMPTY;
        }
        return new ImmutableLongArray(Arrays.copyOf(jArr, jArr.length));
    }

    public static ImmutableLongArray copyOf(Collection<Long> collection) {
        return collection.isEmpty() ? EMPTY : new ImmutableLongArray(Longs.toArray(collection));
    }

    public static ImmutableLongArray copyOf(Iterable<Long> iterable) {
        if (iterable instanceof Collection) {
            return copyOf((Collection<Long>) iterable);
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
        private long[] array;
        private int count = 0;

        Builder(int r2) {
            this.array = new long[r2];
        }

        public Builder add(long j) {
            ensureRoomFor(1);
            long[] jArr = this.array;
            int r2 = this.count;
            jArr[r2] = j;
            this.count = r2 + 1;
            return this;
        }

        public Builder addAll(long[] jArr) {
            ensureRoomFor(jArr.length);
            System.arraycopy(jArr, 0, this.array, this.count, jArr.length);
            this.count += jArr.length;
            return this;
        }

        public Builder addAll(Iterable<Long> iterable) {
            if (iterable instanceof Collection) {
                return addAll((Collection) iterable);
            }
            for (Long l : iterable) {
                add(l.longValue());
            }
            return this;
        }

        public Builder addAll(Collection<Long> collection) {
            ensureRoomFor(collection.size());
            for (Long l : collection) {
                long[] jArr = this.array;
                int r2 = this.count;
                this.count = r2 + 1;
                jArr[r2] = l.longValue();
            }
            return this;
        }

        public Builder addAll(ImmutableLongArray immutableLongArray) {
            ensureRoomFor(immutableLongArray.length());
            System.arraycopy(immutableLongArray.array, immutableLongArray.start, this.array, this.count, immutableLongArray.length());
            this.count += immutableLongArray.length();
            return this;
        }

        private void ensureRoomFor(int r3) {
            int r0 = this.count + r3;
            long[] jArr = this.array;
            if (r0 > jArr.length) {
                this.array = Arrays.copyOf(jArr, expandedCapacity(jArr.length, r0));
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
        public ImmutableLongArray build() {
            return this.count == 0 ? ImmutableLongArray.EMPTY : new ImmutableLongArray(this.array, 0, this.count);
        }
    }

    private ImmutableLongArray(long[] jArr) {
        this(jArr, 0, jArr.length);
    }

    private ImmutableLongArray(long[] jArr, int r2, int r3) {
        this.array = jArr;
        this.start = r2;
        this.end = r3;
    }

    public int length() {
        return this.end - this.start;
    }

    public boolean isEmpty() {
        return this.end == this.start;
    }

    public long get(int r4) {
        Preconditions.checkElementIndex(r4, length());
        return this.array[this.start + r4];
    }

    public int indexOf(long j) {
        for (int r0 = this.start; r0 < this.end; r0++) {
            if (this.array[r0] == j) {
                return r0 - this.start;
            }
        }
        return -1;
    }

    public int lastIndexOf(long j) {
        int r0 = this.end;
        while (true) {
            r0--;
            int r1 = this.start;
            if (r0 < r1) {
                return -1;
            }
            if (this.array[r0] == j) {
                return r0 - r1;
            }
        }
    }

    public boolean contains(long j) {
        return indexOf(j) >= 0;
    }

    public long[] toArray() {
        return Arrays.copyOfRange(this.array, this.start, this.end);
    }

    public ImmutableLongArray subArray(int r4, int r5) {
        Preconditions.checkPositionIndexes(r4, r5, length());
        if (r4 == r5) {
            return EMPTY;
        }
        long[] jArr = this.array;
        int r2 = this.start;
        return new ImmutableLongArray(jArr, r4 + r2, r2 + r5);
    }

    public List<Long> asList() {
        return new AsList();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class AsList extends AbstractList<Long> implements RandomAccess, Serializable {
        private final ImmutableLongArray parent;

        private AsList(ImmutableLongArray immutableLongArray) {
            this.parent = immutableLongArray;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.parent.length();
        }

        @Override // java.util.AbstractList, java.util.List
        public Long get(int r3) {
            return Long.valueOf(this.parent.get(r3));
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean contains(@CheckForNull Object obj) {
            return indexOf(obj) >= 0;
        }

        @Override // java.util.AbstractList, java.util.List
        public int indexOf(@CheckForNull Object obj) {
            if (obj instanceof Long) {
                return this.parent.indexOf(((Long) obj).longValue());
            }
            return -1;
        }

        @Override // java.util.AbstractList, java.util.List
        public int lastIndexOf(@CheckForNull Object obj) {
            if (obj instanceof Long) {
                return this.parent.lastIndexOf(((Long) obj).longValue());
            }
            return -1;
        }

        @Override // java.util.AbstractList, java.util.List
        public List<Long> subList(int r2, int r3) {
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
                    if (obj2 instanceof Long) {
                        int r4 = r0 + 1;
                        if (this.parent.array[r0] == ((Long) obj2).longValue()) {
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
        if (obj instanceof ImmutableLongArray) {
            ImmutableLongArray immutableLongArray = (ImmutableLongArray) obj;
            if (length() != immutableLongArray.length()) {
                return false;
            }
            for (int r1 = 0; r1 < length(); r1++) {
                if (get(r1) != immutableLongArray.get(r1)) {
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
            r1 = (r1 * 31) + Longs.hashCode(this.array[r0]);
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

    public ImmutableLongArray trimmed() {
        return isPartialView() ? new ImmutableLongArray(toArray()) : this;
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
