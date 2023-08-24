package com.google.common.collect;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Ints;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import javax.annotation.CheckForNull;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;

/* JADX INFO: Access modifiers changed from: package-private */
@ElementTypesAreNonnullByDefault
/* loaded from: classes3.dex */
public class CompactHashSet<E> extends AbstractSet<E> implements Serializable {
    static final double HASH_FLOODING_FPP = 0.001d;
    private static final int MAX_HASH_BUCKET_LENGTH = 9;
    @CheckForNull
    transient Object[] elements;
    @CheckForNull
    private transient int[] entries;
    private transient int metadata;
    private transient int size;
    @CheckForNull
    private transient Object table;

    int adjustAfterRemove(int r1, int r2) {
        return r1 - 1;
    }

    public static <E> CompactHashSet<E> create() {
        return new CompactHashSet<>();
    }

    public static <E> CompactHashSet<E> create(Collection<? extends E> collection) {
        CompactHashSet<E> createWithExpectedSize = createWithExpectedSize(collection.size());
        createWithExpectedSize.addAll(collection);
        return createWithExpectedSize;
    }

    @SafeVarargs
    public static <E> CompactHashSet<E> create(E... eArr) {
        CompactHashSet<E> createWithExpectedSize = createWithExpectedSize(eArr.length);
        Collections.addAll(createWithExpectedSize, eArr);
        return createWithExpectedSize;
    }

    public static <E> CompactHashSet<E> createWithExpectedSize(int r1) {
        return new CompactHashSet<>(r1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CompactHashSet() {
        init(3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CompactHashSet(int r1) {
        init(r1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void init(int r4) {
        Preconditions.checkArgument(r4 >= 0, "Expected size must be >= 0");
        this.metadata = Ints.constrainToRange(r4, 1, LockFreeTaskQueueCore.MAX_CAPACITY_MASK);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean needsAllocArrays() {
        return this.table == null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int allocArrays() {
        Preconditions.checkState(needsAllocArrays(), "Arrays already allocated");
        int r0 = this.metadata;
        int tableSize = CompactHashing.tableSize(r0);
        this.table = CompactHashing.createTable(tableSize);
        setHashTableMask(tableSize - 1);
        this.entries = new int[r0];
        this.elements = new Object[r0];
        return r0;
    }

    @CheckForNull
    Set<E> delegateOrNull() {
        Object obj = this.table;
        if (obj instanceof Set) {
            return (Set) obj;
        }
        return null;
    }

    private Set<E> createHashFloodingResistantDelegate(int r3) {
        return new LinkedHashSet(r3, 1.0f);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Set<E> convertToHashFloodingResistantImplementation() {
        Set<E> createHashFloodingResistantDelegate = createHashFloodingResistantDelegate(hashTableMask() + 1);
        int firstEntryIndex = firstEntryIndex();
        while (firstEntryIndex >= 0) {
            createHashFloodingResistantDelegate.add(element(firstEntryIndex));
            firstEntryIndex = getSuccessor(firstEntryIndex);
        }
        this.table = createHashFloodingResistantDelegate;
        this.entries = null;
        this.elements = null;
        incrementModCount();
        return createHashFloodingResistantDelegate;
    }

    boolean isUsingHashFloodingResistance() {
        return delegateOrNull() != null;
    }

    private void setHashTableMask(int r3) {
        this.metadata = CompactHashing.maskCombine(this.metadata, 32 - Integer.numberOfLeadingZeros(r3), 31);
    }

    private int hashTableMask() {
        return (1 << (this.metadata & 31)) - 1;
    }

    void incrementModCount() {
        this.metadata += 32;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean add(@ParametricNullness E e) {
        if (needsAllocArrays()) {
            allocArrays();
        }
        Set<E> delegateOrNull = delegateOrNull();
        if (delegateOrNull != null) {
            return delegateOrNull.add(e);
        }
        int[] requireEntries = requireEntries();
        Object[] requireElements = requireElements();
        int r2 = this.size;
        int r3 = r2 + 1;
        int smearedHash = Hashing.smearedHash(e);
        int hashTableMask = hashTableMask();
        int r6 = smearedHash & hashTableMask;
        int tableGet = CompactHashing.tableGet(requireTable(), r6);
        if (tableGet != 0) {
            int hashPrefix = CompactHashing.getHashPrefix(smearedHash, hashTableMask);
            int r10 = 0;
            while (true) {
                int r7 = tableGet - 1;
                int r11 = requireEntries[r7];
                if (CompactHashing.getHashPrefix(r11, hashTableMask) == hashPrefix && Objects.equal(e, requireElements[r7])) {
                    return false;
                }
                int next = CompactHashing.getNext(r11, hashTableMask);
                r10++;
                if (next != 0) {
                    tableGet = next;
                } else if (r10 >= 9) {
                    return convertToHashFloodingResistantImplementation().add(e);
                } else {
                    if (r3 > hashTableMask) {
                        hashTableMask = resizeTable(hashTableMask, CompactHashing.newCapacity(hashTableMask), smearedHash, r2);
                    } else {
                        requireEntries[r7] = CompactHashing.maskCombine(r11, r3, hashTableMask);
                    }
                }
            }
        } else if (r3 > hashTableMask) {
            hashTableMask = resizeTable(hashTableMask, CompactHashing.newCapacity(hashTableMask), smearedHash, r2);
        } else {
            CompactHashing.tableSet(requireTable(), r6, r3);
        }
        resizeMeMaybe(r3);
        insertEntry(r2, e, smearedHash, hashTableMask);
        this.size = r3;
        incrementModCount();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void insertEntry(int r2, @ParametricNullness E e, int r4, int r5) {
        setEntry(r2, CompactHashing.maskCombine(r4, 0, r5));
        setElement(r2, e);
    }

    private void resizeMeMaybe(int r4) {
        int min;
        int length = requireEntries().length;
        if (r4 <= length || (min = Math.min((int) LockFreeTaskQueueCore.MAX_CAPACITY_MASK, (Math.max(1, length >>> 1) + length) | 1)) == length) {
            return;
        }
        resizeEntries(min);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void resizeEntries(int r2) {
        this.entries = Arrays.copyOf(requireEntries(), r2);
        this.elements = Arrays.copyOf(requireElements(), r2);
    }

    private int resizeTable(int r9, int r10, int r11, int r12) {
        Object createTable = CompactHashing.createTable(r10);
        int r102 = r10 - 1;
        if (r12 != 0) {
            CompactHashing.tableSet(createTable, r11 & r102, r12 + 1);
        }
        Object requireTable = requireTable();
        int[] requireEntries = requireEntries();
        for (int r1 = 0; r1 <= r9; r1++) {
            int tableGet = CompactHashing.tableGet(requireTable, r1);
            while (tableGet != 0) {
                int r3 = tableGet - 1;
                int r4 = requireEntries[r3];
                int hashPrefix = CompactHashing.getHashPrefix(r4, r9) | r1;
                int r6 = hashPrefix & r102;
                int tableGet2 = CompactHashing.tableGet(createTable, r6);
                CompactHashing.tableSet(createTable, r6, tableGet);
                requireEntries[r3] = CompactHashing.maskCombine(hashPrefix, tableGet2, r102);
                tableGet = CompactHashing.getNext(r4, r9);
            }
        }
        this.table = createTable;
        setHashTableMask(r102);
        return r102;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean contains(@CheckForNull Object obj) {
        if (needsAllocArrays()) {
            return false;
        }
        Set<E> delegateOrNull = delegateOrNull();
        if (delegateOrNull != null) {
            return delegateOrNull.contains(obj);
        }
        int smearedHash = Hashing.smearedHash(obj);
        int hashTableMask = hashTableMask();
        int tableGet = CompactHashing.tableGet(requireTable(), smearedHash & hashTableMask);
        if (tableGet == 0) {
            return false;
        }
        int hashPrefix = CompactHashing.getHashPrefix(smearedHash, hashTableMask);
        do {
            int r3 = tableGet - 1;
            int entry = entry(r3);
            if (CompactHashing.getHashPrefix(entry, hashTableMask) == hashPrefix && Objects.equal(obj, element(r3))) {
                return true;
            }
            tableGet = CompactHashing.getNext(entry, hashTableMask);
        } while (tableGet != 0);
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean remove(@CheckForNull Object obj) {
        if (needsAllocArrays()) {
            return false;
        }
        Set<E> delegateOrNull = delegateOrNull();
        if (delegateOrNull != null) {
            return delegateOrNull.remove(obj);
        }
        int hashTableMask = hashTableMask();
        int remove = CompactHashing.remove(obj, null, hashTableMask, requireTable(), requireEntries(), requireElements(), null);
        if (remove == -1) {
            return false;
        }
        moveLastEntry(remove, hashTableMask);
        this.size--;
        incrementModCount();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void moveLastEntry(int r8, int r9) {
        Object requireTable = requireTable();
        int[] requireEntries = requireEntries();
        Object[] requireElements = requireElements();
        int size = size() - 1;
        if (r8 < size) {
            Object obj = requireElements[size];
            requireElements[r8] = obj;
            requireElements[size] = null;
            requireEntries[r8] = requireEntries[size];
            requireEntries[size] = 0;
            int smearedHash = Hashing.smearedHash(obj) & r9;
            int tableGet = CompactHashing.tableGet(requireTable, smearedHash);
            int r3 = size + 1;
            if (tableGet == r3) {
                CompactHashing.tableSet(requireTable, smearedHash, r8 + 1);
                return;
            }
            while (true) {
                int r4 = tableGet - 1;
                int r0 = requireEntries[r4];
                int next = CompactHashing.getNext(r0, r9);
                if (next == r3) {
                    requireEntries[r4] = CompactHashing.maskCombine(r0, r8 + 1, r9);
                    return;
                }
                tableGet = next;
            }
        } else {
            requireElements[r8] = null;
            requireEntries[r8] = 0;
        }
    }

    int firstEntryIndex() {
        return isEmpty() ? -1 : 0;
    }

    int getSuccessor(int r2) {
        int r22 = r2 + 1;
        if (r22 < this.size) {
            return r22;
        }
        return -1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public Iterator<E> iterator() {
        Set<E> delegateOrNull = delegateOrNull();
        if (delegateOrNull != null) {
            return delegateOrNull.iterator();
        }
        return new Iterator<E>() { // from class: com.google.common.collect.CompactHashSet.1
            int currentIndex;
            int expectedMetadata;
            int indexToRemove = -1;

            {
                this.expectedMetadata = CompactHashSet.this.metadata;
                this.currentIndex = CompactHashSet.this.firstEntryIndex();
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.currentIndex >= 0;
            }

            @Override // java.util.Iterator
            @ParametricNullness
            public E next() {
                checkForConcurrentModification();
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                int r0 = this.currentIndex;
                this.indexToRemove = r0;
                E e = (E) CompactHashSet.this.element(r0);
                this.currentIndex = CompactHashSet.this.getSuccessor(this.currentIndex);
                return e;
            }

            @Override // java.util.Iterator
            public void remove() {
                checkForConcurrentModification();
                CollectPreconditions.checkRemove(this.indexToRemove >= 0);
                incrementExpectedModCount();
                CompactHashSet compactHashSet = CompactHashSet.this;
                compactHashSet.remove(compactHashSet.element(this.indexToRemove));
                this.currentIndex = CompactHashSet.this.adjustAfterRemove(this.currentIndex, this.indexToRemove);
                this.indexToRemove = -1;
            }

            void incrementExpectedModCount() {
                this.expectedMetadata += 32;
            }

            private void checkForConcurrentModification() {
                if (CompactHashSet.this.metadata != this.expectedMetadata) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public int size() {
        Set<E> delegateOrNull = delegateOrNull();
        return delegateOrNull != null ? delegateOrNull.size() : this.size;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public Object[] toArray() {
        if (needsAllocArrays()) {
            return new Object[0];
        }
        Set<E> delegateOrNull = delegateOrNull();
        return delegateOrNull != null ? delegateOrNull.toArray() : Arrays.copyOf(requireElements(), this.size);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public <T> T[] toArray(T[] tArr) {
        if (needsAllocArrays()) {
            if (tArr.length > 0) {
                tArr[0] = null;
            }
            return tArr;
        }
        Set<E> delegateOrNull = delegateOrNull();
        if (delegateOrNull != null) {
            return (T[]) delegateOrNull.toArray(tArr);
        }
        return (T[]) ObjectArrays.toArrayImpl(requireElements(), 0, this.size, tArr);
    }

    public void trimToSize() {
        if (needsAllocArrays()) {
            return;
        }
        Set<E> delegateOrNull = delegateOrNull();
        if (delegateOrNull != null) {
            Set<E> createHashFloodingResistantDelegate = createHashFloodingResistantDelegate(size());
            createHashFloodingResistantDelegate.addAll(delegateOrNull);
            this.table = createHashFloodingResistantDelegate;
            return;
        }
        int r0 = this.size;
        if (r0 < requireEntries().length) {
            resizeEntries(r0);
        }
        int tableSize = CompactHashing.tableSize(r0);
        int hashTableMask = hashTableMask();
        if (tableSize < hashTableMask) {
            resizeTable(hashTableMask, tableSize, 0, 0);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public void clear() {
        if (needsAllocArrays()) {
            return;
        }
        incrementModCount();
        Set<E> delegateOrNull = delegateOrNull();
        if (delegateOrNull != null) {
            this.metadata = Ints.constrainToRange(size(), 3, LockFreeTaskQueueCore.MAX_CAPACITY_MASK);
            delegateOrNull.clear();
            this.table = null;
            this.size = 0;
            return;
        }
        Arrays.fill(requireElements(), 0, this.size, (Object) null);
        CompactHashing.tableClear(requireTable());
        Arrays.fill(requireEntries(), 0, this.size, 0);
        this.size = 0;
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeInt(size());
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            objectOutputStream.writeObject(it.next());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        int readInt = objectInputStream.readInt();
        if (readInt < 0) {
            StringBuilder sb = new StringBuilder(25);
            sb.append("Invalid size: ");
            sb.append(readInt);
            throw new InvalidObjectException(sb.toString());
        }
        init(readInt);
        for (int r1 = 0; r1 < readInt; r1++) {
            add(objectInputStream.readObject());
        }
    }

    private Object requireTable() {
        Object obj = this.table;
        java.util.Objects.requireNonNull(obj);
        return obj;
    }

    private int[] requireEntries() {
        int[] r0 = this.entries;
        java.util.Objects.requireNonNull(r0);
        return r0;
    }

    private Object[] requireElements() {
        Object[] objArr = this.elements;
        java.util.Objects.requireNonNull(objArr);
        return objArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public E element(int r2) {
        return (E) requireElements()[r2];
    }

    private int entry(int r2) {
        return requireEntries()[r2];
    }

    private void setElement(int r2, E e) {
        requireElements()[r2] = e;
    }

    private void setEntry(int r2, int r3) {
        requireEntries()[r2] = r3;
    }
}
