package com.google.common.collect;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import javax.annotation.CheckForNull;

/* JADX INFO: Access modifiers changed from: package-private */
@ElementTypesAreNonnullByDefault
/* loaded from: classes3.dex */
public class CompactLinkedHashSet<E> extends CompactHashSet<E> {
    private static final int ENDPOINT = -2;
    private transient int firstEntry;
    private transient int lastEntry;
    @CheckForNull
    private transient int[] predecessor;
    @CheckForNull
    private transient int[] successor;

    public static <E> CompactLinkedHashSet<E> create() {
        return new CompactLinkedHashSet<>();
    }

    public static <E> CompactLinkedHashSet<E> create(Collection<? extends E> collection) {
        CompactLinkedHashSet<E> createWithExpectedSize = createWithExpectedSize(collection.size());
        createWithExpectedSize.addAll(collection);
        return createWithExpectedSize;
    }

    @SafeVarargs
    public static <E> CompactLinkedHashSet<E> create(E... eArr) {
        CompactLinkedHashSet<E> createWithExpectedSize = createWithExpectedSize(eArr.length);
        Collections.addAll(createWithExpectedSize, eArr);
        return createWithExpectedSize;
    }

    public static <E> CompactLinkedHashSet<E> createWithExpectedSize(int r1) {
        return new CompactLinkedHashSet<>(r1);
    }

    CompactLinkedHashSet() {
    }

    CompactLinkedHashSet(int r1) {
        super(r1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.CompactHashSet
    public void init(int r1) {
        super.init(r1);
        this.firstEntry = -2;
        this.lastEntry = -2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.CompactHashSet
    public int allocArrays() {
        int allocArrays = super.allocArrays();
        this.predecessor = new int[allocArrays];
        this.successor = new int[allocArrays];
        return allocArrays;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.CompactHashSet
    public Set<E> convertToHashFloodingResistantImplementation() {
        Set<E> convertToHashFloodingResistantImplementation = super.convertToHashFloodingResistantImplementation();
        this.predecessor = null;
        this.successor = null;
        return convertToHashFloodingResistantImplementation;
    }

    private int getPredecessor(int r2) {
        return requirePredecessors()[r2] - 1;
    }

    @Override // com.google.common.collect.CompactHashSet
    int getSuccessor(int r2) {
        return requireSuccessors()[r2] - 1;
    }

    private void setSuccessor(int r2, int r3) {
        requireSuccessors()[r2] = r3 + 1;
    }

    private void setPredecessor(int r2, int r3) {
        requirePredecessors()[r2] = r3 + 1;
    }

    private void setSucceeds(int r2, int r3) {
        if (r2 == -2) {
            this.firstEntry = r3;
        } else {
            setSuccessor(r2, r3);
        }
        if (r3 == -2) {
            this.lastEntry = r2;
        } else {
            setPredecessor(r3, r2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.CompactHashSet
    public void insertEntry(int r1, @ParametricNullness E e, int r3, int r4) {
        super.insertEntry(r1, e, r3, r4);
        setSucceeds(this.lastEntry, r1);
        setSucceeds(r1, -2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.CompactHashSet
    public void moveLastEntry(int r3, int r4) {
        int size = size() - 1;
        super.moveLastEntry(r3, r4);
        setSucceeds(getPredecessor(r3), getSuccessor(r3));
        if (r3 < size) {
            setSucceeds(getPredecessor(size), r3);
            setSucceeds(r3, getSuccessor(size));
        }
        requirePredecessors()[size] = 0;
        requireSuccessors()[size] = 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.CompactHashSet
    public void resizeEntries(int r2) {
        super.resizeEntries(r2);
        this.predecessor = Arrays.copyOf(requirePredecessors(), r2);
        this.successor = Arrays.copyOf(requireSuccessors(), r2);
    }

    @Override // com.google.common.collect.CompactHashSet
    int firstEntryIndex() {
        return this.firstEntry;
    }

    @Override // com.google.common.collect.CompactHashSet
    int adjustAfterRemove(int r2, int r3) {
        return r2 >= size() ? r3 : r2;
    }

    @Override // com.google.common.collect.CompactHashSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public Object[] toArray() {
        return ObjectArrays.toArrayImpl(this);
    }

    @Override // com.google.common.collect.CompactHashSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public <T> T[] toArray(T[] tArr) {
        return (T[]) ObjectArrays.toArrayImpl(this, tArr);
    }

    @Override // com.google.common.collect.CompactHashSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public void clear() {
        if (needsAllocArrays()) {
            return;
        }
        this.firstEntry = -2;
        this.lastEntry = -2;
        int[] r0 = this.predecessor;
        if (r0 != null && this.successor != null) {
            Arrays.fill(r0, 0, size(), 0);
            Arrays.fill(this.successor, 0, size(), 0);
        }
        super.clear();
    }

    private int[] requirePredecessors() {
        int[] r0 = this.predecessor;
        Objects.requireNonNull(r0);
        return r0;
    }

    private int[] requireSuccessors() {
        int[] r0 = this.successor;
        Objects.requireNonNull(r0);
        return r0;
    }
}
