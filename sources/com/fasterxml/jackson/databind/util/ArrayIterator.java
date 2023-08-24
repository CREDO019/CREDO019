package com.fasterxml.jackson.databind.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* loaded from: classes2.dex */
public class ArrayIterator<T> implements Iterator<T>, Iterable<T> {

    /* renamed from: _a */
    private final T[] f199_a;
    private int _index = 0;

    @Override // java.lang.Iterable
    public Iterator<T> iterator() {
        return this;
    }

    public ArrayIterator(T[] tArr) {
        this.f199_a = tArr;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this._index < this.f199_a.length;
    }

    @Override // java.util.Iterator
    public T next() {
        int r0 = this._index;
        T[] tArr = this.f199_a;
        if (r0 >= tArr.length) {
            throw new NoSuchElementException();
        }
        this._index = r0 + 1;
        return tArr[r0];
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
