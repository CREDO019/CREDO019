package com.google.common.collect;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
/* loaded from: classes3.dex */
public abstract class ForwardingList<E> extends ForwardingCollection<E> implements List<E> {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
    public abstract List<E> delegate();

    public void add(int r2, @ParametricNullness E e) {
        delegate().add(r2, e);
    }

    public boolean addAll(int r2, Collection<? extends E> collection) {
        return delegate().addAll(r2, collection);
    }

    @Override // java.util.List
    @ParametricNullness
    public E get(int r2) {
        return delegate().get(r2);
    }

    @Override // java.util.List
    public int indexOf(@CheckForNull Object obj) {
        return delegate().indexOf(obj);
    }

    @Override // java.util.List
    public int lastIndexOf(@CheckForNull Object obj) {
        return delegate().lastIndexOf(obj);
    }

    @Override // java.util.List
    public ListIterator<E> listIterator() {
        return delegate().listIterator();
    }

    @Override // java.util.List
    public ListIterator<E> listIterator(int r2) {
        return delegate().listIterator(r2);
    }

    @Override // java.util.List
    @ParametricNullness
    public E remove(int r2) {
        return delegate().remove(r2);
    }

    @Override // java.util.List
    @ParametricNullness
    public E set(int r2, @ParametricNullness E e) {
        return delegate().set(r2, e);
    }

    @Override // java.util.List
    public List<E> subList(int r2, int r3) {
        return delegate().subList(r2, r3);
    }

    @Override // java.util.Collection, java.util.List
    public boolean equals(@CheckForNull Object obj) {
        return obj == this || delegate().equals(obj);
    }

    @Override // java.util.Collection, java.util.List
    public int hashCode() {
        return delegate().hashCode();
    }

    protected boolean standardAdd(@ParametricNullness E e) {
        add(size(), e);
        return true;
    }

    protected boolean standardAddAll(int r1, Iterable<? extends E> iterable) {
        return Lists.addAllImpl(this, r1, iterable);
    }

    protected int standardIndexOf(@CheckForNull Object obj) {
        return Lists.indexOfImpl(this, obj);
    }

    protected int standardLastIndexOf(@CheckForNull Object obj) {
        return Lists.lastIndexOfImpl(this, obj);
    }

    protected Iterator<E> standardIterator() {
        return listIterator();
    }

    protected ListIterator<E> standardListIterator() {
        return listIterator(0);
    }

    protected ListIterator<E> standardListIterator(int r1) {
        return Lists.listIteratorImpl(this, r1);
    }

    protected List<E> standardSubList(int r1, int r2) {
        return Lists.subListImpl(this, r1, r2);
    }

    protected boolean standardEquals(@CheckForNull Object obj) {
        return Lists.equalsImpl(this, obj);
    }

    protected int standardHashCode() {
        return Lists.hashCodeImpl(this);
    }
}
