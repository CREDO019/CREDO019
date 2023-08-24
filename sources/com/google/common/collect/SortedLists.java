package com.google.common.collect;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import java.util.Comparator;
import java.util.List;
import java.util.RandomAccess;

@ElementTypesAreNonnullByDefault
/* loaded from: classes3.dex */
final class SortedLists {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public enum KeyAbsentBehavior {
        NEXT_LOWER { // from class: com.google.common.collect.SortedLists.KeyAbsentBehavior.1
            @Override // com.google.common.collect.SortedLists.KeyAbsentBehavior
            int resultIndex(int r1) {
                return r1 - 1;
            }
        },
        NEXT_HIGHER { // from class: com.google.common.collect.SortedLists.KeyAbsentBehavior.2
            @Override // com.google.common.collect.SortedLists.KeyAbsentBehavior
            public int resultIndex(int r1) {
                return r1;
            }
        },
        INVERTED_INSERTION_INDEX { // from class: com.google.common.collect.SortedLists.KeyAbsentBehavior.3
            @Override // com.google.common.collect.SortedLists.KeyAbsentBehavior
            public int resultIndex(int r1) {
                return ~r1;
            }
        };

        abstract int resultIndex(int r1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public enum KeyPresentBehavior {
        ANY_PRESENT { // from class: com.google.common.collect.SortedLists.KeyPresentBehavior.1
            @Override // com.google.common.collect.SortedLists.KeyPresentBehavior
            <E> int resultIndex(Comparator<? super E> comparator, @ParametricNullness E e, List<? extends E> list, int r4) {
                return r4;
            }
        },
        LAST_PRESENT { // from class: com.google.common.collect.SortedLists.KeyPresentBehavior.2
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.common.collect.SortedLists.KeyPresentBehavior
            <E> int resultIndex(Comparator<? super E> comparator, @ParametricNullness E e, List<? extends E> list, int r7) {
                int size = list.size() - 1;
                while (r7 < size) {
                    int r1 = ((r7 + size) + 1) >>> 1;
                    if (comparator.compare((E) list.get(r1), e) > 0) {
                        size = r1 - 1;
                    } else {
                        r7 = r1;
                    }
                }
                return r7;
            }
        },
        FIRST_PRESENT { // from class: com.google.common.collect.SortedLists.KeyPresentBehavior.3
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.common.collect.SortedLists.KeyPresentBehavior
            <E> int resultIndex(Comparator<? super E> comparator, @ParametricNullness E e, List<? extends E> list, int r7) {
                int r0 = 0;
                while (r0 < r7) {
                    int r1 = (r0 + r7) >>> 1;
                    if (comparator.compare((E) list.get(r1), e) < 0) {
                        r0 = r1 + 1;
                    } else {
                        r7 = r1;
                    }
                }
                return r0;
            }
        },
        FIRST_AFTER { // from class: com.google.common.collect.SortedLists.KeyPresentBehavior.4
            @Override // com.google.common.collect.SortedLists.KeyPresentBehavior
            public <E> int resultIndex(Comparator<? super E> comparator, @ParametricNullness E e, List<? extends E> list, int r5) {
                return LAST_PRESENT.resultIndex(comparator, e, list, r5) + 1;
            }
        },
        LAST_BEFORE { // from class: com.google.common.collect.SortedLists.KeyPresentBehavior.5
            @Override // com.google.common.collect.SortedLists.KeyPresentBehavior
            public <E> int resultIndex(Comparator<? super E> comparator, @ParametricNullness E e, List<? extends E> list, int r5) {
                return FIRST_PRESENT.resultIndex(comparator, e, list, r5) - 1;
            }
        };

        abstract <E> int resultIndex(Comparator<? super E> comparator, @ParametricNullness E e, List<? extends E> list, int r4);
    }

    private SortedLists() {
    }

    public static <E extends Comparable> int binarySearch(List<? extends E> list, E e, KeyPresentBehavior keyPresentBehavior, KeyAbsentBehavior keyAbsentBehavior) {
        Preconditions.checkNotNull(e);
        return binarySearch(list, e, Ordering.natural(), keyPresentBehavior, keyAbsentBehavior);
    }

    public static <E, K extends Comparable> int binarySearch(List<E> list, Function<? super E, K> function, K k, KeyPresentBehavior keyPresentBehavior, KeyAbsentBehavior keyAbsentBehavior) {
        Preconditions.checkNotNull(k);
        return binarySearch(list, function, k, Ordering.natural(), keyPresentBehavior, keyAbsentBehavior);
    }

    public static <E, K> int binarySearch(List<E> list, Function<? super E, K> function, @ParametricNullness K k, Comparator<? super K> comparator, KeyPresentBehavior keyPresentBehavior, KeyAbsentBehavior keyAbsentBehavior) {
        return binarySearch(Lists.transform(list, function), k, comparator, keyPresentBehavior, keyAbsentBehavior);
    }

    public static <E> int binarySearch(List<? extends E> list, @ParametricNullness E e, Comparator<? super E> comparator, KeyPresentBehavior keyPresentBehavior, KeyAbsentBehavior keyAbsentBehavior) {
        Preconditions.checkNotNull(comparator);
        Preconditions.checkNotNull(list);
        Preconditions.checkNotNull(keyPresentBehavior);
        Preconditions.checkNotNull(keyAbsentBehavior);
        if (!(list instanceof RandomAccess)) {
            list = Lists.newArrayList(list);
        }
        int r0 = 0;
        int size = list.size() - 1;
        while (r0 <= size) {
            int r2 = (r0 + size) >>> 1;
            int compare = comparator.compare(e, (E) list.get(r2));
            if (compare < 0) {
                size = r2 - 1;
            } else if (compare <= 0) {
                return r0 + keyPresentBehavior.resultIndex(comparator, e, list.subList(r0, size + 1), r2 - r0);
            } else {
                r0 = r2 + 1;
            }
        }
        return keyAbsentBehavior.resultIndex(r0);
    }
}
