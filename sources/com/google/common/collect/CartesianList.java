package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.math.IntMath;
import java.util.AbstractList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import javax.annotation.CheckForNull;

/* JADX INFO: Access modifiers changed from: package-private */
@ElementTypesAreNonnullByDefault
/* loaded from: classes3.dex */
public final class CartesianList<E> extends AbstractList<List<E>> implements RandomAccess {
    private final transient ImmutableList<List<E>> axes;
    private final transient int[] axesSizeProduct;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <E> List<List<E>> create(List<? extends List<? extends E>> list) {
        ImmutableList.Builder builder = new ImmutableList.Builder(list.size());
        for (List<? extends E> list2 : list) {
            ImmutableList copyOf = ImmutableList.copyOf((Collection) list2);
            if (copyOf.isEmpty()) {
                return ImmutableList.m409of();
            }
            builder.add((ImmutableList.Builder) copyOf);
        }
        return new CartesianList(builder.build());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CartesianList(ImmutableList<List<E>> immutableList) {
        this.axes = immutableList;
        int[] r0 = new int[immutableList.size() + 1];
        r0[immutableList.size()] = 1;
        try {
            for (int size = immutableList.size() - 1; size >= 0; size--) {
                r0[size] = IntMath.checkedMultiply(r0[size + 1], immutableList.get(size).size());
            }
            this.axesSizeProduct = r0;
        } catch (ArithmeticException unused) {
            throw new IllegalArgumentException("Cartesian product too large; must have size at most Integer.MAX_VALUE");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getAxisIndexForProductIndex(int r3, int r4) {
        return (r3 / this.axesSizeProduct[r4 + 1]) % this.axes.get(r4).size();
    }

    @Override // java.util.AbstractList, java.util.List
    public int indexOf(@CheckForNull Object obj) {
        if (obj instanceof List) {
            List list = (List) obj;
            if (list.size() != this.axes.size()) {
                return -1;
            }
            ListIterator<E> listIterator = list.listIterator();
            int r0 = 0;
            while (listIterator.hasNext()) {
                int nextIndex = listIterator.nextIndex();
                int indexOf = this.axes.get(nextIndex).indexOf(listIterator.next());
                if (indexOf == -1) {
                    return -1;
                }
                r0 += indexOf * this.axesSizeProduct[nextIndex + 1];
            }
            return r0;
        }
        return -1;
    }

    @Override // java.util.AbstractList, java.util.List
    public int lastIndexOf(@CheckForNull Object obj) {
        if (obj instanceof List) {
            List list = (List) obj;
            if (list.size() != this.axes.size()) {
                return -1;
            }
            ListIterator<E> listIterator = list.listIterator();
            int r0 = 0;
            while (listIterator.hasNext()) {
                int nextIndex = listIterator.nextIndex();
                int lastIndexOf = this.axes.get(nextIndex).lastIndexOf(listIterator.next());
                if (lastIndexOf == -1) {
                    return -1;
                }
                r0 += lastIndexOf * this.axesSizeProduct[nextIndex + 1];
            }
            return r0;
        }
        return -1;
    }

    @Override // java.util.AbstractList, java.util.List
    public ImmutableList<E> get(final int r2) {
        Preconditions.checkElementIndex(r2, size());
        return new ImmutableList<E>() { // from class: com.google.common.collect.CartesianList.1
            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // com.google.common.collect.ImmutableCollection
            public boolean isPartialView() {
                return true;
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
            public int size() {
                return CartesianList.this.axes.size();
            }

            @Override // java.util.List
            public E get(int r3) {
                Preconditions.checkElementIndex(r3, size());
                return (E) ((List) CartesianList.this.axes.get(r3)).get(CartesianList.this.getAxisIndexForProductIndex(r2, r3));
            }
        };
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return this.axesSizeProduct[0];
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean contains(@CheckForNull Object obj) {
        if (obj instanceof List) {
            List<E> list = (List) obj;
            if (list.size() != this.axes.size()) {
                return false;
            }
            int r0 = 0;
            for (E e : list) {
                if (!this.axes.get(r0).contains(e)) {
                    return false;
                }
                r0++;
            }
            return true;
        }
        return false;
    }
}
