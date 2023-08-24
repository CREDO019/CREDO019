package com.google.common.collect;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
/* loaded from: classes3.dex */
public interface Multiset<E> extends Collection<E> {

    /* loaded from: classes3.dex */
    public interface Entry<E> {
        boolean equals(@CheckForNull Object obj);

        int getCount();

        @ParametricNullness
        E getElement();

        int hashCode();

        String toString();
    }

    int add(@ParametricNullness E e, int r2);

    @Override // java.util.Collection, com.google.common.collect.Multiset
    boolean add(@ParametricNullness E e);

    @Override // java.util.Collection, com.google.common.collect.Multiset
    boolean contains(@CheckForNull Object obj);

    @Override // java.util.Collection
    boolean containsAll(Collection<?> collection);

    int count(@CheckForNull Object obj);

    Set<E> elementSet();

    Set<Entry<E>> entrySet();

    @Override // com.google.common.collect.Multiset
    boolean equals(@CheckForNull Object obj);

    @Override // com.google.common.collect.Multiset
    int hashCode();

    @Override // java.util.Collection, java.lang.Iterable, com.google.common.collect.Multiset
    Iterator<E> iterator();

    int remove(@CheckForNull Object obj, int r2);

    @Override // java.util.Collection, com.google.common.collect.Multiset
    boolean remove(@CheckForNull Object obj);

    @Override // java.util.Collection, com.google.common.collect.Multiset
    boolean removeAll(Collection<?> collection);

    @Override // java.util.Collection, com.google.common.collect.Multiset
    boolean retainAll(Collection<?> collection);

    int setCount(@ParametricNullness E e, int r2);

    boolean setCount(@ParametricNullness E e, int r2, int r3);

    @Override // java.util.Collection, com.google.common.collect.Multiset
    int size();

    String toString();
}
