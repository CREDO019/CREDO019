package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Queue;

@ElementTypesAreNonnullByDefault
/* loaded from: classes3.dex */
public final class EvictingQueue<E> extends ForwardingQueue<E> implements Serializable {
    private static final long serialVersionUID = 0;
    private final Queue<E> delegate;
    final int maxSize;

    private EvictingQueue(int r3) {
        Preconditions.checkArgument(r3 >= 0, "maxSize (%s) must >= 0", r3);
        this.delegate = new ArrayDeque(r3);
        this.maxSize = r3;
    }

    public static <E> EvictingQueue<E> create(int r1) {
        return new EvictingQueue<>(r1);
    }

    public int remainingCapacity() {
        return this.maxSize - size();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.common.collect.ForwardingQueue, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
    public Queue<E> delegate() {
        return this.delegate;
    }

    @Override // com.google.common.collect.ForwardingQueue, java.util.Queue
    public boolean offer(E e) {
        return add(e);
    }

    @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Queue
    public boolean add(E e) {
        Preconditions.checkNotNull(e);
        if (this.maxSize == 0) {
            return true;
        }
        if (size() == this.maxSize) {
            this.delegate.remove();
        }
        this.delegate.add(e);
        return true;
    }

    @Override // com.google.common.collect.ForwardingCollection, java.util.Collection
    public boolean addAll(Collection<? extends E> collection) {
        int size = collection.size();
        if (size >= this.maxSize) {
            clear();
            return Iterables.addAll(this, Iterables.skip(collection, size - this.maxSize));
        }
        return standardAddAll(collection);
    }

    @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Set
    public Object[] toArray() {
        return super.toArray();
    }
}