package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableCollection;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.SortedSet;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
/* loaded from: classes3.dex */
public abstract class ImmutableSet<E> extends ImmutableCollection<E> implements Set<E> {
    private static final int CUTOFF = 751619276;
    private static final double DESIRED_LOAD_FACTOR = 0.7d;
    static final int MAX_TABLE_SIZE = 1073741824;
    @CheckForNull
    @LazyInit
    private transient ImmutableList<E> asList;

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean shouldTrim(int r1, int r2) {
        return r1 < (r2 >> 1) + (r2 >> 2);
    }

    boolean isHashCodeFast() {
        return false;
    }

    @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set, java.util.NavigableSet, com.google.common.collect.SortedIterable
    public abstract UnmodifiableIterator<E> iterator();

    /* renamed from: of */
    public static <E> ImmutableSet<E> m362of() {
        return RegularImmutableSet.EMPTY;
    }

    /* renamed from: of */
    public static <E> ImmutableSet<E> m361of(E e) {
        return new SingletonImmutableSet(e);
    }

    /* renamed from: of */
    public static <E> ImmutableSet<E> m360of(E e, E e2) {
        return construct(2, e, e2);
    }

    /* renamed from: of */
    public static <E> ImmutableSet<E> m359of(E e, E e2, E e3) {
        return construct(3, e, e2, e3);
    }

    /* renamed from: of */
    public static <E> ImmutableSet<E> m358of(E e, E e2, E e3, E e4) {
        return construct(4, e, e2, e3, e4);
    }

    /* renamed from: of */
    public static <E> ImmutableSet<E> m357of(E e, E e2, E e3, E e4, E e5) {
        return construct(5, e, e2, e3, e4, e5);
    }

    @SafeVarargs
    /* renamed from: of */
    public static <E> ImmutableSet<E> m356of(E e, E e2, E e3, E e4, E e5, E e6, E... eArr) {
        Preconditions.checkArgument(eArr.length <= 2147483641, "the total number of elements must fit in an int");
        int length = eArr.length + 6;
        Object[] objArr = new Object[length];
        objArr[0] = e;
        objArr[1] = e2;
        objArr[2] = e3;
        objArr[3] = e4;
        objArr[4] = e5;
        objArr[5] = e6;
        System.arraycopy(eArr, 0, objArr, 6, eArr.length);
        return construct(length, objArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <E> ImmutableSet<E> construct(int r13, Object... objArr) {
        if (r13 != 0) {
            if (r13 == 1) {
                Object obj = objArr[0];
                Objects.requireNonNull(obj);
                return m361of(obj);
            }
            int chooseTableSize = chooseTableSize(r13);
            Object[] objArr2 = new Object[chooseTableSize];
            int r7 = chooseTableSize - 1;
            int r5 = 0;
            int r8 = 0;
            for (int r3 = 0; r3 < r13; r3++) {
                Object checkElementNotNull = ObjectArrays.checkElementNotNull(objArr[r3], r3);
                int hashCode = checkElementNotNull.hashCode();
                int smear = Hashing.smear(hashCode);
                while (true) {
                    int r11 = smear & r7;
                    Object obj2 = objArr2[r11];
                    if (obj2 == null) {
                        objArr[r8] = checkElementNotNull;
                        objArr2[r11] = checkElementNotNull;
                        r5 += hashCode;
                        r8++;
                        break;
                    } else if (obj2.equals(checkElementNotNull)) {
                        break;
                    } else {
                        smear++;
                    }
                }
            }
            Arrays.fill(objArr, r8, r13, (Object) null);
            if (r8 == 1) {
                Object obj3 = objArr[0];
                Objects.requireNonNull(obj3);
                return new SingletonImmutableSet(obj3);
            } else if (chooseTableSize(r8) < chooseTableSize / 2) {
                return construct(r8, objArr);
            } else {
                if (shouldTrim(r8, objArr.length)) {
                    objArr = Arrays.copyOf(objArr, r8);
                }
                return new RegularImmutableSet(objArr, r5, objArr2, r7, r8);
            }
        }
        return m362of();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int chooseTableSize(int r6) {
        int max = Math.max(r6, 2);
        if (max < CUTOFF) {
            int highestOneBit = Integer.highestOneBit(max - 1) << 1;
            while (highestOneBit * 0.7d < max) {
                highestOneBit <<= 1;
            }
            return highestOneBit;
        }
        Preconditions.checkArgument(max < 1073741824, "collection too large");
        return 1073741824;
    }

    public static <E> ImmutableSet<E> copyOf(Collection<? extends E> collection) {
        if ((collection instanceof ImmutableSet) && !(collection instanceof SortedSet)) {
            ImmutableSet<E> immutableSet = (ImmutableSet) collection;
            if (!immutableSet.isPartialView()) {
                return immutableSet;
            }
        }
        Object[] array = collection.toArray();
        return construct(array.length, array);
    }

    public static <E> ImmutableSet<E> copyOf(Iterable<? extends E> iterable) {
        if (iterable instanceof Collection) {
            return copyOf((Collection) iterable);
        }
        return copyOf(iterable.iterator());
    }

    public static <E> ImmutableSet<E> copyOf(Iterator<? extends E> it) {
        if (!it.hasNext()) {
            return m362of();
        }
        E next = it.next();
        if (!it.hasNext()) {
            return m361of((Object) next);
        }
        return new Builder().add((Builder) next).addAll((Iterator) it).build();
    }

    public static <E> ImmutableSet<E> copyOf(E[] eArr) {
        int length = eArr.length;
        if (length != 0) {
            if (length == 1) {
                return m361of((Object) eArr[0]);
            }
            return construct(eArr.length, (Object[]) eArr.clone());
        }
        return m362of();
    }

    @Override // java.util.Collection, java.util.Set
    public boolean equals(@CheckForNull Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof ImmutableSet) && isHashCodeFast() && ((ImmutableSet) obj).isHashCodeFast() && hashCode() != obj.hashCode()) {
            return false;
        }
        return Sets.equalsImpl(this, obj);
    }

    @Override // java.util.Collection, java.util.Set
    public int hashCode() {
        return Sets.hashCodeImpl(this);
    }

    @Override // com.google.common.collect.ImmutableCollection
    public ImmutableList<E> asList() {
        ImmutableList<E> immutableList = this.asList;
        if (immutableList == null) {
            ImmutableList<E> createAsList = createAsList();
            this.asList = createAsList;
            return createAsList;
        }
        return immutableList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ImmutableList<E> createAsList() {
        return ImmutableList.asImmutableList(toArray());
    }

    /* loaded from: classes3.dex */
    private static class SerializedForm implements Serializable {
        private static final long serialVersionUID = 0;
        final Object[] elements;

        SerializedForm(Object[] objArr) {
            this.elements = objArr;
        }

        Object readResolve() {
            return ImmutableSet.copyOf(this.elements);
        }
    }

    @Override // com.google.common.collect.ImmutableCollection
    Object writeReplace() {
        return new SerializedForm(toArray());
    }

    public static <E> Builder<E> builder() {
        return new Builder<>();
    }

    public static <E> Builder<E> builderWithExpectedSize(int r1) {
        CollectPreconditions.checkNonnegative(r1, "expectedSize");
        return new Builder<>(r1);
    }

    /* loaded from: classes3.dex */
    public static class Builder<E> extends ImmutableCollection.ArrayBasedBuilder<E> {
        private int hashCode;
        @CheckForNull
        Object[] hashTable;

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.ImmutableCollection.ArrayBasedBuilder, com.google.common.collect.ImmutableCollection.Builder
        public /* bridge */ /* synthetic */ ImmutableCollection.ArrayBasedBuilder add(Object obj) {
            return add((Builder<E>) obj);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.ImmutableCollection.ArrayBasedBuilder, com.google.common.collect.ImmutableCollection.Builder
        public /* bridge */ /* synthetic */ ImmutableCollection.Builder add(Object obj) {
            return add((Builder<E>) obj);
        }

        public Builder() {
            super(4);
        }

        Builder(int r1) {
            super(r1);
            this.hashTable = new Object[ImmutableSet.chooseTableSize(r1)];
        }

        @Override // com.google.common.collect.ImmutableCollection.ArrayBasedBuilder, com.google.common.collect.ImmutableCollection.Builder
        public Builder<E> add(E e) {
            Preconditions.checkNotNull(e);
            if (this.hashTable != null && ImmutableSet.chooseTableSize(this.size) <= this.hashTable.length) {
                addDeduping(e);
                return this;
            }
            this.hashTable = null;
            super.add((Builder<E>) e);
            return this;
        }

        @Override // com.google.common.collect.ImmutableCollection.ArrayBasedBuilder, com.google.common.collect.ImmutableCollection.Builder
        public Builder<E> add(E... eArr) {
            if (this.hashTable != null) {
                for (E e : eArr) {
                    add((Builder<E>) e);
                }
            } else {
                super.add((Object[]) eArr);
            }
            return this;
        }

        private void addDeduping(E e) {
            Objects.requireNonNull(this.hashTable);
            int length = this.hashTable.length - 1;
            int hashCode = e.hashCode();
            int smear = Hashing.smear(hashCode);
            while (true) {
                int r2 = smear & length;
                Object[] objArr = this.hashTable;
                Object obj = objArr[r2];
                if (obj == null) {
                    objArr[r2] = e;
                    this.hashCode += hashCode;
                    super.add((Builder<E>) e);
                    return;
                } else if (obj.equals(e)) {
                    return;
                } else {
                    smear = r2 + 1;
                }
            }
        }

        @Override // com.google.common.collect.ImmutableCollection.ArrayBasedBuilder, com.google.common.collect.ImmutableCollection.Builder
        public Builder<E> addAll(Iterable<? extends E> iterable) {
            Preconditions.checkNotNull(iterable);
            if (this.hashTable != null) {
                for (E e : iterable) {
                    add((Builder<E>) e);
                }
            } else {
                super.addAll((Iterable) iterable);
            }
            return this;
        }

        @Override // com.google.common.collect.ImmutableCollection.Builder
        public Builder<E> addAll(Iterator<? extends E> it) {
            Preconditions.checkNotNull(it);
            while (it.hasNext()) {
                add((Builder<E>) it.next());
            }
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Multi-variable type inference failed */
        public Builder<E> combine(Builder<E> builder) {
            if (this.hashTable != null) {
                for (int r0 = 0; r0 < builder.size; r0++) {
                    Object obj = builder.contents[r0];
                    Objects.requireNonNull(obj);
                    add((Builder<E>) obj);
                }
            } else {
                addAll(builder.contents, builder.size);
            }
            return this;
        }

        @Override // com.google.common.collect.ImmutableCollection.Builder
        public ImmutableSet<E> build() {
            ImmutableSet<E> construct;
            int r0 = this.size;
            if (r0 != 0) {
                if (r0 == 1) {
                    Object obj = this.contents[0];
                    Objects.requireNonNull(obj);
                    return ImmutableSet.m361of(obj);
                }
                if (this.hashTable == null || ImmutableSet.chooseTableSize(this.size) != this.hashTable.length) {
                    construct = ImmutableSet.construct(this.size, this.contents);
                    this.size = construct.size();
                } else {
                    Object[] copyOf = ImmutableSet.shouldTrim(this.size, this.contents.length) ? Arrays.copyOf(this.contents, this.size) : this.contents;
                    int r4 = this.hashCode;
                    Object[] objArr = this.hashTable;
                    construct = new RegularImmutableSet<>(copyOf, r4, objArr, objArr.length - 1, this.size);
                }
                this.forceCopy = true;
                this.hashTable = null;
                return construct;
            }
            return ImmutableSet.m362of();
        }
    }
}
