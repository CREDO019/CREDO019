package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.util.NoSuchElementException;

@ElementTypesAreNonnullByDefault
/* loaded from: classes3.dex */
abstract class AbstractIndexedListIterator<E> extends UnmodifiableListIterator<E> {
    private int position;
    private final int size;

    @ParametricNullness
    protected abstract E get(int r1);

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractIndexedListIterator(int r2) {
        this(r2, 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractIndexedListIterator(int r1, int r2) {
        Preconditions.checkPositionIndex(r2, r1);
        this.size = r1;
        this.position = r2;
    }

    @Override // java.util.Iterator, java.util.ListIterator
    public final boolean hasNext() {
        return this.position < this.size;
    }

    @Override // java.util.Iterator, java.util.ListIterator
    @ParametricNullness
    public final E next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int r0 = this.position;
        this.position = r0 + 1;
        return get(r0);
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return this.position;
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return this.position > 0;
    }

    @Override // java.util.ListIterator
    @ParametricNullness
    public final E previous() {
        if (!hasPrevious()) {
            throw new NoSuchElementException();
        }
        int r0 = this.position - 1;
        this.position = r0;
        return get(r0);
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return this.position - 1;
    }
}
