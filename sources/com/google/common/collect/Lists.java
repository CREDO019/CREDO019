package com.google.common.collect;

import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.math.IntMath;
import com.google.common.primitives.Ints;
import java.io.Serializable;
import java.math.RoundingMode;
import java.util.AbstractList;
import java.util.AbstractSequentialList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
/* loaded from: classes3.dex */
public final class Lists {
    private Lists() {
    }

    public static <E> ArrayList<E> newArrayList() {
        return new ArrayList<>();
    }

    @SafeVarargs
    public static <E> ArrayList<E> newArrayList(E... eArr) {
        Preconditions.checkNotNull(eArr);
        ArrayList<E> arrayList = new ArrayList<>(computeArrayListCapacity(eArr.length));
        Collections.addAll(arrayList, eArr);
        return arrayList;
    }

    public static <E> ArrayList<E> newArrayList(Iterable<? extends E> iterable) {
        Preconditions.checkNotNull(iterable);
        if (iterable instanceof Collection) {
            return new ArrayList<>((Collection) iterable);
        }
        return newArrayList(iterable.iterator());
    }

    public static <E> ArrayList<E> newArrayList(Iterator<? extends E> it) {
        ArrayList<E> newArrayList = newArrayList();
        Iterators.addAll(newArrayList, it);
        return newArrayList;
    }

    static int computeArrayListCapacity(int r4) {
        CollectPreconditions.checkNonnegative(r4, "arraySize");
        return Ints.saturatedCast(r4 + 5 + (r4 / 10));
    }

    public static <E> ArrayList<E> newArrayListWithCapacity(int r1) {
        CollectPreconditions.checkNonnegative(r1, "initialArraySize");
        return new ArrayList<>(r1);
    }

    public static <E> ArrayList<E> newArrayListWithExpectedSize(int r1) {
        return new ArrayList<>(computeArrayListCapacity(r1));
    }

    public static <E> LinkedList<E> newLinkedList() {
        return new LinkedList<>();
    }

    public static <E> LinkedList<E> newLinkedList(Iterable<? extends E> iterable) {
        LinkedList<E> newLinkedList = newLinkedList();
        Iterables.addAll(newLinkedList, iterable);
        return newLinkedList;
    }

    public static <E> CopyOnWriteArrayList<E> newCopyOnWriteArrayList() {
        return new CopyOnWriteArrayList<>();
    }

    public static <E> CopyOnWriteArrayList<E> newCopyOnWriteArrayList(Iterable<? extends E> iterable) {
        Collection newArrayList;
        if (iterable instanceof Collection) {
            newArrayList = (Collection) iterable;
        } else {
            newArrayList = newArrayList(iterable);
        }
        return new CopyOnWriteArrayList<>(newArrayList);
    }

    public static <E> List<E> asList(@ParametricNullness E e, E[] eArr) {
        return new OnePlusArrayList(e, eArr);
    }

    public static <E> List<E> asList(@ParametricNullness E e, @ParametricNullness E e2, E[] eArr) {
        return new TwoPlusArrayList(e, e2, eArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class OnePlusArrayList<E> extends AbstractList<E> implements Serializable, RandomAccess {
        private static final long serialVersionUID = 0;
        @ParametricNullness
        final E first;
        final E[] rest;

        OnePlusArrayList(@ParametricNullness E e, E[] eArr) {
            this.first = e;
            this.rest = (E[]) ((Object[]) Preconditions.checkNotNull(eArr));
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return IntMath.saturatedAdd(this.rest.length, 1);
        }

        @Override // java.util.AbstractList, java.util.List
        @ParametricNullness
        public E get(int r2) {
            Preconditions.checkElementIndex(r2, size());
            return r2 == 0 ? this.first : this.rest[r2 - 1];
        }
    }

    /* loaded from: classes3.dex */
    private static class TwoPlusArrayList<E> extends AbstractList<E> implements Serializable, RandomAccess {
        private static final long serialVersionUID = 0;
        @ParametricNullness
        final E first;
        final E[] rest;
        @ParametricNullness
        final E second;

        TwoPlusArrayList(@ParametricNullness E e, @ParametricNullness E e2, E[] eArr) {
            this.first = e;
            this.second = e2;
            this.rest = (E[]) ((Object[]) Preconditions.checkNotNull(eArr));
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return IntMath.saturatedAdd(this.rest.length, 2);
        }

        @Override // java.util.AbstractList, java.util.List
        @ParametricNullness
        public E get(int r2) {
            if (r2 != 0) {
                if (r2 == 1) {
                    return this.second;
                }
                Preconditions.checkElementIndex(r2, size());
                return this.rest[r2 - 2];
            }
            return this.first;
        }
    }

    public static <B> List<List<B>> cartesianProduct(List<? extends List<? extends B>> list) {
        return CartesianList.create(list);
    }

    @SafeVarargs
    public static <B> List<List<B>> cartesianProduct(List<? extends B>... listArr) {
        return cartesianProduct(Arrays.asList(listArr));
    }

    public static <F, T> List<T> transform(List<F> list, Function<? super F, ? extends T> function) {
        if (list instanceof RandomAccess) {
            return new TransformingRandomAccessList(list, function);
        }
        return new TransformingSequentialList(list, function);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class TransformingSequentialList<F, T> extends AbstractSequentialList<T> implements Serializable {
        private static final long serialVersionUID = 0;
        final List<F> fromList;
        final Function<? super F, ? extends T> function;

        TransformingSequentialList(List<F> list, Function<? super F, ? extends T> function) {
            this.fromList = (List) Preconditions.checkNotNull(list);
            this.function = (Function) Preconditions.checkNotNull(function);
        }

        @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
        public void clear() {
            this.fromList.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.fromList.size();
        }

        @Override // java.util.AbstractSequentialList, java.util.AbstractList, java.util.List
        public ListIterator<T> listIterator(int r3) {
            return new TransformedListIterator<F, T>(this.fromList.listIterator(r3)) { // from class: com.google.common.collect.Lists.TransformingSequentialList.1
                /* JADX INFO: Access modifiers changed from: package-private */
                @Override // com.google.common.collect.TransformedIterator
                @ParametricNullness
                public T transform(@ParametricNullness F f) {
                    return TransformingSequentialList.this.function.apply(f);
                }
            };
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class TransformingRandomAccessList<F, T> extends AbstractList<T> implements RandomAccess, Serializable {
        private static final long serialVersionUID = 0;
        final List<F> fromList;
        final Function<? super F, ? extends T> function;

        TransformingRandomAccessList(List<F> list, Function<? super F, ? extends T> function) {
            this.fromList = (List) Preconditions.checkNotNull(list);
            this.function = (Function) Preconditions.checkNotNull(function);
        }

        @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
        public void clear() {
            this.fromList.clear();
        }

        @Override // java.util.AbstractList, java.util.List
        @ParametricNullness
        public T get(int r3) {
            return this.function.apply((F) this.fromList.get(r3));
        }

        @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
        public Iterator<T> iterator() {
            return listIterator();
        }

        @Override // java.util.AbstractList, java.util.List
        public ListIterator<T> listIterator(int r3) {
            return new TransformedListIterator<F, T>(this.fromList.listIterator(r3)) { // from class: com.google.common.collect.Lists.TransformingRandomAccessList.1
                /* JADX INFO: Access modifiers changed from: package-private */
                @Override // com.google.common.collect.TransformedIterator
                public T transform(F f) {
                    return TransformingRandomAccessList.this.function.apply(f);
                }
            };
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean isEmpty() {
            return this.fromList.isEmpty();
        }

        @Override // java.util.AbstractList, java.util.List
        public T remove(int r3) {
            return this.function.apply((F) this.fromList.remove(r3));
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.fromList.size();
        }
    }

    public static <T> List<List<T>> partition(List<T> list, int r2) {
        Preconditions.checkNotNull(list);
        Preconditions.checkArgument(r2 > 0);
        if (list instanceof RandomAccess) {
            return new RandomAccessPartition(list, r2);
        }
        return new Partition(list, r2);
    }

    /* loaded from: classes3.dex */
    private static class Partition<T> extends AbstractList<List<T>> {
        final List<T> list;
        final int size;

        Partition(List<T> list, int r2) {
            this.list = list;
            this.size = r2;
        }

        @Override // java.util.AbstractList, java.util.List
        public List<T> get(int r3) {
            Preconditions.checkElementIndex(r3, size());
            int r0 = this.size;
            int r32 = r3 * r0;
            return this.list.subList(r32, Math.min(r0 + r32, this.list.size()));
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return IntMath.divide(this.list.size(), this.size, RoundingMode.CEILING);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean isEmpty() {
            return this.list.isEmpty();
        }
    }

    /* loaded from: classes3.dex */
    private static class RandomAccessPartition<T> extends Partition<T> implements RandomAccess {
        RandomAccessPartition(List<T> list, int r2) {
            super(list, r2);
        }
    }

    public static ImmutableList<Character> charactersOf(String str) {
        return new StringAsImmutableList((String) Preconditions.checkNotNull(str));
    }

    public static List<Character> charactersOf(CharSequence charSequence) {
        return new CharSequenceAsList((CharSequence) Preconditions.checkNotNull(charSequence));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static final class StringAsImmutableList extends ImmutableList<Character> {
        private final String string;

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableCollection
        public boolean isPartialView() {
            return false;
        }

        StringAsImmutableList(String str) {
            this.string = str;
        }

        @Override // com.google.common.collect.ImmutableList, java.util.List
        public int indexOf(@CheckForNull Object obj) {
            if (obj instanceof Character) {
                return this.string.indexOf(((Character) obj).charValue());
            }
            return -1;
        }

        @Override // com.google.common.collect.ImmutableList, java.util.List
        public int lastIndexOf(@CheckForNull Object obj) {
            if (obj instanceof Character) {
                return this.string.lastIndexOf(((Character) obj).charValue());
            }
            return -1;
        }

        @Override // com.google.common.collect.ImmutableList, java.util.List
        public ImmutableList<Character> subList(int r2, int r3) {
            Preconditions.checkPositionIndexes(r2, r3, size());
            return Lists.charactersOf(this.string.substring(r2, r3));
        }

        @Override // java.util.List
        public Character get(int r2) {
            Preconditions.checkElementIndex(r2, size());
            return Character.valueOf(this.string.charAt(r2));
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.string.length();
        }
    }

    /* loaded from: classes3.dex */
    private static final class CharSequenceAsList extends AbstractList<Character> {
        private final CharSequence sequence;

        CharSequenceAsList(CharSequence charSequence) {
            this.sequence = charSequence;
        }

        @Override // java.util.AbstractList, java.util.List
        public Character get(int r2) {
            Preconditions.checkElementIndex(r2, size());
            return Character.valueOf(this.sequence.charAt(r2));
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.sequence.length();
        }
    }

    public static <T> List<T> reverse(List<T> list) {
        if (list instanceof ImmutableList) {
            return ((ImmutableList) list).reverse();
        }
        if (list instanceof ReverseList) {
            return ((ReverseList) list).getForwardList();
        }
        if (list instanceof RandomAccess) {
            return new RandomAccessReverseList(list);
        }
        return new ReverseList(list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class ReverseList<T> extends AbstractList<T> {
        private final List<T> forwardList;

        ReverseList(List<T> list) {
            this.forwardList = (List) Preconditions.checkNotNull(list);
        }

        List<T> getForwardList() {
            return this.forwardList;
        }

        private int reverseIndex(int r2) {
            int size = size();
            Preconditions.checkElementIndex(r2, size);
            return (size - 1) - r2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int reversePosition(int r2) {
            int size = size();
            Preconditions.checkPositionIndex(r2, size);
            return size - r2;
        }

        @Override // java.util.AbstractList, java.util.List
        public void add(int r2, @ParametricNullness T t) {
            this.forwardList.add(reversePosition(r2), t);
        }

        @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
        public void clear() {
            this.forwardList.clear();
        }

        @Override // java.util.AbstractList, java.util.List
        @ParametricNullness
        public T remove(int r2) {
            return this.forwardList.remove(reverseIndex(r2));
        }

        @Override // java.util.AbstractList
        protected void removeRange(int r1, int r2) {
            subList(r1, r2).clear();
        }

        @Override // java.util.AbstractList, java.util.List
        @ParametricNullness
        public T set(int r2, @ParametricNullness T t) {
            return this.forwardList.set(reverseIndex(r2), t);
        }

        @Override // java.util.AbstractList, java.util.List
        @ParametricNullness
        public T get(int r2) {
            return this.forwardList.get(reverseIndex(r2));
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.forwardList.size();
        }

        @Override // java.util.AbstractList, java.util.List
        public List<T> subList(int r2, int r3) {
            Preconditions.checkPositionIndexes(r2, r3, size());
            return Lists.reverse(this.forwardList.subList(reversePosition(r3), reversePosition(r2)));
        }

        @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
        public Iterator<T> iterator() {
            return listIterator();
        }

        @Override // java.util.AbstractList, java.util.List
        public ListIterator<T> listIterator(int r2) {
            final ListIterator<T> listIterator = this.forwardList.listIterator(reversePosition(r2));
            return new ListIterator<T>() { // from class: com.google.common.collect.Lists.ReverseList.1
                boolean canRemoveOrSet;

                @Override // java.util.ListIterator
                public void add(@ParametricNullness T t) {
                    listIterator.add(t);
                    listIterator.previous();
                    this.canRemoveOrSet = false;
                }

                @Override // java.util.ListIterator, java.util.Iterator
                public boolean hasNext() {
                    return listIterator.hasPrevious();
                }

                @Override // java.util.ListIterator
                public boolean hasPrevious() {
                    return listIterator.hasNext();
                }

                @Override // java.util.ListIterator, java.util.Iterator
                @ParametricNullness
                public T next() {
                    if (!hasNext()) {
                        throw new NoSuchElementException();
                    }
                    this.canRemoveOrSet = true;
                    return (T) listIterator.previous();
                }

                @Override // java.util.ListIterator
                public int nextIndex() {
                    return ReverseList.this.reversePosition(listIterator.nextIndex());
                }

                @Override // java.util.ListIterator
                @ParametricNullness
                public T previous() {
                    if (!hasPrevious()) {
                        throw new NoSuchElementException();
                    }
                    this.canRemoveOrSet = true;
                    return (T) listIterator.next();
                }

                @Override // java.util.ListIterator
                public int previousIndex() {
                    return nextIndex() - 1;
                }

                @Override // java.util.ListIterator, java.util.Iterator
                public void remove() {
                    CollectPreconditions.checkRemove(this.canRemoveOrSet);
                    listIterator.remove();
                    this.canRemoveOrSet = false;
                }

                @Override // java.util.ListIterator
                public void set(@ParametricNullness T t) {
                    Preconditions.checkState(this.canRemoveOrSet);
                    listIterator.set(t);
                }
            };
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class RandomAccessReverseList<T> extends ReverseList<T> implements RandomAccess {
        RandomAccessReverseList(List<T> list) {
            super(list);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int hashCodeImpl(List<?> list) {
        Iterator<?> it = list.iterator();
        int r0 = 1;
        while (it.hasNext()) {
            Object next = it.next();
            r0 = ~(~((r0 * 31) + (next == null ? 0 : next.hashCode())));
        }
        return r0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean equalsImpl(List<?> list, @CheckForNull Object obj) {
        if (obj == Preconditions.checkNotNull(list)) {
            return true;
        }
        if (obj instanceof List) {
            List list2 = (List) obj;
            int size = list.size();
            if (size != list2.size()) {
                return false;
            }
            if ((list instanceof RandomAccess) && (list2 instanceof RandomAccess)) {
                for (int r3 = 0; r3 < size; r3++) {
                    if (!Objects.equal(list.get(r3), list2.get(r3))) {
                        return false;
                    }
                }
                return true;
            }
            return Iterators.elementsEqual(list.iterator(), list2.iterator());
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <E> boolean addAllImpl(List<E> list, int r2, Iterable<? extends E> iterable) {
        ListIterator<E> listIterator = list.listIterator(r2);
        boolean z = false;
        for (E e : iterable) {
            listIterator.add(e);
            z = true;
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int indexOfImpl(List<?> list, @CheckForNull Object obj) {
        if (list instanceof RandomAccess) {
            return indexOfRandomAccess(list, obj);
        }
        ListIterator<?> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            if (Objects.equal(obj, listIterator.next())) {
                return listIterator.previousIndex();
            }
        }
        return -1;
    }

    private static int indexOfRandomAccess(List<?> list, @CheckForNull Object obj) {
        int size = list.size();
        int r1 = 0;
        if (obj == null) {
            while (r1 < size) {
                if (list.get(r1) == null) {
                    return r1;
                }
                r1++;
            }
            return -1;
        }
        while (r1 < size) {
            if (obj.equals(list.get(r1))) {
                return r1;
            }
            r1++;
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int lastIndexOfImpl(List<?> list, @CheckForNull Object obj) {
        if (list instanceof RandomAccess) {
            return lastIndexOfRandomAccess(list, obj);
        }
        ListIterator<?> listIterator = list.listIterator(list.size());
        while (listIterator.hasPrevious()) {
            if (Objects.equal(obj, listIterator.previous())) {
                return listIterator.nextIndex();
            }
        }
        return -1;
    }

    private static int lastIndexOfRandomAccess(List<?> list, @CheckForNull Object obj) {
        if (obj == null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                if (list.get(size) == null) {
                    return size;
                }
            }
            return -1;
        }
        for (int size2 = list.size() - 1; size2 >= 0; size2--) {
            if (obj.equals(list.get(size2))) {
                return size2;
            }
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <E> ListIterator<E> listIteratorImpl(List<E> list, int r2) {
        return new AbstractListWrapper(list).listIterator(r2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <E> List<E> subListImpl(List<E> list, int r2, int r3) {
        List list2;
        if (list instanceof RandomAccess) {
            list2 = new RandomAccessListWrapper<E>(list) { // from class: com.google.common.collect.Lists.1
                private static final long serialVersionUID = 0;

                @Override // java.util.AbstractList, java.util.List
                public ListIterator<E> listIterator(int r22) {
                    return this.backingList.listIterator(r22);
                }
            };
        } else {
            list2 = new AbstractListWrapper<E>(list) { // from class: com.google.common.collect.Lists.2
                private static final long serialVersionUID = 0;

                @Override // java.util.AbstractList, java.util.List
                public ListIterator<E> listIterator(int r22) {
                    return this.backingList.listIterator(r22);
                }
            };
        }
        return list2.subList(r2, r3);
    }

    /* loaded from: classes3.dex */
    private static class AbstractListWrapper<E> extends AbstractList<E> {
        final List<E> backingList;

        AbstractListWrapper(List<E> list) {
            this.backingList = (List) Preconditions.checkNotNull(list);
        }

        @Override // java.util.AbstractList, java.util.List
        public void add(int r2, @ParametricNullness E e) {
            this.backingList.add(r2, e);
        }

        @Override // java.util.AbstractList, java.util.List
        public boolean addAll(int r2, Collection<? extends E> collection) {
            return this.backingList.addAll(r2, collection);
        }

        @Override // java.util.AbstractList, java.util.List
        @ParametricNullness
        public E get(int r2) {
            return this.backingList.get(r2);
        }

        @Override // java.util.AbstractList, java.util.List
        @ParametricNullness
        public E remove(int r2) {
            return this.backingList.remove(r2);
        }

        @Override // java.util.AbstractList, java.util.List
        @ParametricNullness
        public E set(int r2, @ParametricNullness E e) {
            return this.backingList.set(r2, e);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean contains(@CheckForNull Object obj) {
            return this.backingList.contains(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.backingList.size();
        }
    }

    /* loaded from: classes3.dex */
    private static class RandomAccessListWrapper<E> extends AbstractListWrapper<E> implements RandomAccess {
        RandomAccessListWrapper(List<E> list) {
            super(list);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> List<T> cast(Iterable<T> iterable) {
        return (List) iterable;
    }
}
