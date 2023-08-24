package com.google.common.collect;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Ints;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import javax.annotation.CheckForNull;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;

/* JADX INFO: Access modifiers changed from: package-private */
@ElementTypesAreNonnullByDefault
/* loaded from: classes3.dex */
public class CompactHashMap<K, V> extends AbstractMap<K, V> implements Serializable {
    static final double HASH_FLOODING_FPP = 0.001d;
    private static final int MAX_HASH_BUCKET_LENGTH = 9;
    private static final Object NOT_FOUND = new Object();
    @CheckForNull
    transient int[] entries;
    @CheckForNull
    private transient Set<Map.Entry<K, V>> entrySetView;
    @CheckForNull
    private transient Set<K> keySetView;
    @CheckForNull
    transient Object[] keys;
    private transient int metadata;
    private transient int size;
    @CheckForNull
    private transient Object table;
    @CheckForNull
    transient Object[] values;
    @CheckForNull
    private transient Collection<V> valuesView;

    void accessEntry(int r1) {
    }

    int adjustAfterRemove(int r1, int r2) {
        return r1 - 1;
    }

    static /* synthetic */ int access$1210(CompactHashMap compactHashMap) {
        int r0 = compactHashMap.size;
        compactHashMap.size = r0 - 1;
        return r0;
    }

    public static <K, V> CompactHashMap<K, V> create() {
        return new CompactHashMap<>();
    }

    public static <K, V> CompactHashMap<K, V> createWithExpectedSize(int r1) {
        return new CompactHashMap<>(r1);
    }

    CompactHashMap() {
        init(3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CompactHashMap(int r1) {
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
        this.keys = new Object[r0];
        this.values = new Object[r0];
        return r0;
    }

    @CheckForNull
    Map<K, V> delegateOrNull() {
        Object obj = this.table;
        if (obj instanceof Map) {
            return (Map) obj;
        }
        return null;
    }

    Map<K, V> createHashFloodingResistantDelegate(int r3) {
        return new LinkedHashMap(r3, 1.0f);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Map<K, V> convertToHashFloodingResistantImplementation() {
        Map<K, V> createHashFloodingResistantDelegate = createHashFloodingResistantDelegate(hashTableMask() + 1);
        int firstEntryIndex = firstEntryIndex();
        while (firstEntryIndex >= 0) {
            createHashFloodingResistantDelegate.put(key(firstEntryIndex), value(firstEntryIndex));
            firstEntryIndex = getSuccessor(firstEntryIndex);
        }
        this.table = createHashFloodingResistantDelegate;
        this.entries = null;
        this.keys = null;
        this.values = null;
        incrementModCount();
        return createHashFloodingResistantDelegate;
    }

    private void setHashTableMask(int r3) {
        this.metadata = CompactHashing.maskCombine(this.metadata, 32 - Integer.numberOfLeadingZeros(r3), 31);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int hashTableMask() {
        return (1 << (this.metadata & 31)) - 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void incrementModCount() {
        this.metadata += 32;
    }

    @Override // java.util.AbstractMap, java.util.Map
    @CheckForNull
    public V put(@ParametricNullness K k, @ParametricNullness V v) {
        int resizeTable;
        int r8;
        if (needsAllocArrays()) {
            allocArrays();
        }
        Map<K, V> delegateOrNull = delegateOrNull();
        if (delegateOrNull != null) {
            return delegateOrNull.put(k, v);
        }
        int[] requireEntries = requireEntries();
        Object[] requireKeys = requireKeys();
        Object[] requireValues = requireValues();
        int r4 = this.size;
        int r9 = r4 + 1;
        int smearedHash = Hashing.smearedHash(k);
        int hashTableMask = hashTableMask();
        int r5 = smearedHash & hashTableMask;
        int tableGet = CompactHashing.tableGet(requireTable(), r5);
        if (tableGet != 0) {
            int hashPrefix = CompactHashing.getHashPrefix(smearedHash, hashTableMask);
            int r82 = 0;
            while (true) {
                int r6 = tableGet - 1;
                int r10 = requireEntries[r6];
                if (CompactHashing.getHashPrefix(r10, hashTableMask) == hashPrefix && Objects.equal(k, requireKeys[r6])) {
                    V v2 = (V) requireValues[r6];
                    requireValues[r6] = v;
                    accessEntry(r6);
                    return v2;
                }
                int next = CompactHashing.getNext(r10, hashTableMask);
                r82++;
                if (next != 0) {
                    tableGet = next;
                } else if (r82 >= 9) {
                    return convertToHashFloodingResistantImplementation().put(k, v);
                } else {
                    if (r9 > hashTableMask) {
                        resizeTable = resizeTable(hashTableMask, CompactHashing.newCapacity(hashTableMask), smearedHash, r4);
                    } else {
                        requireEntries[r6] = CompactHashing.maskCombine(r10, r9, hashTableMask);
                    }
                }
            }
        } else if (r9 > hashTableMask) {
            resizeTable = resizeTable(hashTableMask, CompactHashing.newCapacity(hashTableMask), smearedHash, r4);
            r8 = resizeTable;
        } else {
            CompactHashing.tableSet(requireTable(), r5, r9);
            r8 = hashTableMask;
        }
        resizeMeMaybe(r9);
        insertEntry(r4, k, v, smearedHash, r8);
        this.size = r9;
        incrementModCount();
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void insertEntry(int r2, @ParametricNullness K k, @ParametricNullness V v, int r5, int r6) {
        setEntry(r2, CompactHashing.maskCombine(r5, 0, r6));
        setKey(r2, k);
        setValue(r2, v);
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
        this.keys = Arrays.copyOf(requireKeys(), r2);
        this.values = Arrays.copyOf(requireValues(), r2);
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

    /* JADX INFO: Access modifiers changed from: private */
    public int indexOf(@CheckForNull Object obj) {
        if (needsAllocArrays()) {
            return -1;
        }
        int smearedHash = Hashing.smearedHash(obj);
        int hashTableMask = hashTableMask();
        int tableGet = CompactHashing.tableGet(requireTable(), smearedHash & hashTableMask);
        if (tableGet == 0) {
            return -1;
        }
        int hashPrefix = CompactHashing.getHashPrefix(smearedHash, hashTableMask);
        do {
            int r3 = tableGet - 1;
            int entry = entry(r3);
            if (CompactHashing.getHashPrefix(entry, hashTableMask) == hashPrefix && Objects.equal(obj, key(r3))) {
                return r3;
            }
            tableGet = CompactHashing.getNext(entry, hashTableMask);
        } while (tableGet != 0);
        return -1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(@CheckForNull Object obj) {
        Map<K, V> delegateOrNull = delegateOrNull();
        if (delegateOrNull != null) {
            return delegateOrNull.containsKey(obj);
        }
        return indexOf(obj) != -1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    @CheckForNull
    public V get(@CheckForNull Object obj) {
        Map<K, V> delegateOrNull = delegateOrNull();
        if (delegateOrNull != null) {
            return delegateOrNull.get(obj);
        }
        int indexOf = indexOf(obj);
        if (indexOf == -1) {
            return null;
        }
        accessEntry(indexOf);
        return value(indexOf);
    }

    @Override // java.util.AbstractMap, java.util.Map
    @CheckForNull
    public V remove(@CheckForNull Object obj) {
        Map<K, V> delegateOrNull = delegateOrNull();
        if (delegateOrNull != null) {
            return delegateOrNull.remove(obj);
        }
        V v = (V) removeHelper(obj);
        if (v == NOT_FOUND) {
            return null;
        }
        return v;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Object removeHelper(@CheckForNull Object obj) {
        if (needsAllocArrays()) {
            return NOT_FOUND;
        }
        int hashTableMask = hashTableMask();
        int remove = CompactHashing.remove(obj, null, hashTableMask, requireTable(), requireEntries(), requireKeys(), null);
        if (remove == -1) {
            return NOT_FOUND;
        }
        V value = value(remove);
        moveLastEntry(remove, hashTableMask);
        this.size--;
        incrementModCount();
        return value;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void moveLastEntry(int r10, int r11) {
        Object requireTable = requireTable();
        int[] requireEntries = requireEntries();
        Object[] requireKeys = requireKeys();
        Object[] requireValues = requireValues();
        int size = size() - 1;
        if (r10 < size) {
            Object obj = requireKeys[size];
            requireKeys[r10] = obj;
            requireValues[r10] = requireValues[size];
            requireKeys[size] = null;
            requireValues[size] = null;
            requireEntries[r10] = requireEntries[size];
            requireEntries[size] = 0;
            int smearedHash = Hashing.smearedHash(obj) & r11;
            int tableGet = CompactHashing.tableGet(requireTable, smearedHash);
            int r4 = size + 1;
            if (tableGet == r4) {
                CompactHashing.tableSet(requireTable, smearedHash, r10 + 1);
                return;
            }
            while (true) {
                int r3 = tableGet - 1;
                int r0 = requireEntries[r3];
                int next = CompactHashing.getNext(r0, r11);
                if (next == r4) {
                    requireEntries[r3] = CompactHashing.maskCombine(r0, r10 + 1, r11);
                    return;
                }
                tableGet = next;
            }
        } else {
            requireKeys[r10] = null;
            requireValues[r10] = null;
            requireEntries[r10] = 0;
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

    /* loaded from: classes3.dex */
    private abstract class Itr<T> implements Iterator<T> {
        int currentIndex;
        int expectedMetadata;
        int indexToRemove;

        @ParametricNullness
        abstract T getOutput(int r1);

        private Itr() {
            this.expectedMetadata = CompactHashMap.this.metadata;
            this.currentIndex = CompactHashMap.this.firstEntryIndex();
            this.indexToRemove = -1;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.currentIndex >= 0;
        }

        @Override // java.util.Iterator
        @ParametricNullness
        public T next() {
            checkForConcurrentModification();
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            int r0 = this.currentIndex;
            this.indexToRemove = r0;
            T output = getOutput(r0);
            this.currentIndex = CompactHashMap.this.getSuccessor(this.currentIndex);
            return output;
        }

        @Override // java.util.Iterator
        public void remove() {
            checkForConcurrentModification();
            CollectPreconditions.checkRemove(this.indexToRemove >= 0);
            incrementExpectedModCount();
            CompactHashMap compactHashMap = CompactHashMap.this;
            compactHashMap.remove(compactHashMap.key(this.indexToRemove));
            this.currentIndex = CompactHashMap.this.adjustAfterRemove(this.currentIndex, this.indexToRemove);
            this.indexToRemove = -1;
        }

        void incrementExpectedModCount() {
            this.expectedMetadata += 32;
        }

        private void checkForConcurrentModification() {
            if (CompactHashMap.this.metadata != this.expectedMetadata) {
                throw new ConcurrentModificationException();
            }
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<K> keySet() {
        Set<K> set = this.keySetView;
        if (set == null) {
            Set<K> createKeySet = createKeySet();
            this.keySetView = createKeySet;
            return createKeySet;
        }
        return set;
    }

    Set<K> createKeySet() {
        return new KeySetView();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public class KeySetView extends AbstractSet<K> {
        KeySetView() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return CompactHashMap.this.size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(@CheckForNull Object obj) {
            return CompactHashMap.this.containsKey(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(@CheckForNull Object obj) {
            Map<K, V> delegateOrNull = CompactHashMap.this.delegateOrNull();
            if (delegateOrNull != null) {
                return delegateOrNull.keySet().remove(obj);
            }
            return CompactHashMap.this.removeHelper(obj) != CompactHashMap.NOT_FOUND;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<K> iterator() {
            return CompactHashMap.this.keySetIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            CompactHashMap.this.clear();
        }
    }

    Iterator<K> keySetIterator() {
        Map<K, V> delegateOrNull = delegateOrNull();
        if (delegateOrNull != null) {
            return delegateOrNull.keySet().iterator();
        }
        return new CompactHashMap<K, V>.Itr<K>() { // from class: com.google.common.collect.CompactHashMap.1
            @Override // com.google.common.collect.CompactHashMap.Itr
            @ParametricNullness
            K getOutput(int r2) {
                return (K) CompactHashMap.this.key(r2);
            }
        };
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = this.entrySetView;
        if (set == null) {
            Set<Map.Entry<K, V>> createEntrySet = createEntrySet();
            this.entrySetView = createEntrySet;
            return createEntrySet;
        }
        return set;
    }

    Set<Map.Entry<K, V>> createEntrySet() {
        return new EntrySetView();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public class EntrySetView extends AbstractSet<Map.Entry<K, V>> {
        EntrySetView() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return CompactHashMap.this.size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            CompactHashMap.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<K, V>> iterator() {
            return CompactHashMap.this.entrySetIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(@CheckForNull Object obj) {
            Map<K, V> delegateOrNull = CompactHashMap.this.delegateOrNull();
            if (delegateOrNull != null) {
                return delegateOrNull.entrySet().contains(obj);
            }
            if (obj instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry) obj;
                int indexOf = CompactHashMap.this.indexOf(entry.getKey());
                return indexOf != -1 && Objects.equal(CompactHashMap.this.value(indexOf), entry.getValue());
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(@CheckForNull Object obj) {
            Map<K, V> delegateOrNull = CompactHashMap.this.delegateOrNull();
            if (delegateOrNull != null) {
                return delegateOrNull.entrySet().remove(obj);
            }
            if (obj instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry) obj;
                if (CompactHashMap.this.needsAllocArrays()) {
                    return false;
                }
                int hashTableMask = CompactHashMap.this.hashTableMask();
                int remove = CompactHashing.remove(entry.getKey(), entry.getValue(), hashTableMask, CompactHashMap.this.requireTable(), CompactHashMap.this.requireEntries(), CompactHashMap.this.requireKeys(), CompactHashMap.this.requireValues());
                if (remove == -1) {
                    return false;
                }
                CompactHashMap.this.moveLastEntry(remove, hashTableMask);
                CompactHashMap.access$1210(CompactHashMap.this);
                CompactHashMap.this.incrementModCount();
                return true;
            }
            return false;
        }
    }

    Iterator<Map.Entry<K, V>> entrySetIterator() {
        Map<K, V> delegateOrNull = delegateOrNull();
        if (delegateOrNull != null) {
            return delegateOrNull.entrySet().iterator();
        }
        return new CompactHashMap<K, V>.Itr<Map.Entry<K, V>>() { // from class: com.google.common.collect.CompactHashMap.2
            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // com.google.common.collect.CompactHashMap.Itr
            public Map.Entry<K, V> getOutput(int r3) {
                return new MapEntry(r3);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public final class MapEntry extends AbstractMapEntry<K, V> {
        @ParametricNullness
        private final K key;
        private int lastKnownIndex;

        MapEntry(int r2) {
            this.key = (K) CompactHashMap.this.key(r2);
            this.lastKnownIndex = r2;
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        @ParametricNullness
        public K getKey() {
            return this.key;
        }

        private void updateLastKnownIndex() {
            int r0 = this.lastKnownIndex;
            if (r0 == -1 || r0 >= CompactHashMap.this.size() || !Objects.equal(this.key, CompactHashMap.this.key(this.lastKnownIndex))) {
                this.lastKnownIndex = CompactHashMap.this.indexOf(this.key);
            }
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        @ParametricNullness
        public V getValue() {
            Map<K, V> delegateOrNull = CompactHashMap.this.delegateOrNull();
            if (delegateOrNull != null) {
                return (V) NullnessCasts.uncheckedCastNullableTToT(delegateOrNull.get(this.key));
            }
            updateLastKnownIndex();
            int r0 = this.lastKnownIndex;
            return r0 == -1 ? (V) NullnessCasts.unsafeNull() : (V) CompactHashMap.this.value(r0);
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        @ParametricNullness
        public V setValue(@ParametricNullness V v) {
            Map<K, V> delegateOrNull = CompactHashMap.this.delegateOrNull();
            if (delegateOrNull != null) {
                return (V) NullnessCasts.uncheckedCastNullableTToT(delegateOrNull.put(this.key, v));
            }
            updateLastKnownIndex();
            int r0 = this.lastKnownIndex;
            if (r0 != -1) {
                V v2 = (V) CompactHashMap.this.value(r0);
                CompactHashMap.this.setValue(this.lastKnownIndex, v);
                return v2;
            }
            CompactHashMap.this.put(this.key, v);
            return (V) NullnessCasts.unsafeNull();
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        Map<K, V> delegateOrNull = delegateOrNull();
        return delegateOrNull != null ? delegateOrNull.size() : this.size;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsValue(@CheckForNull Object obj) {
        Map<K, V> delegateOrNull = delegateOrNull();
        if (delegateOrNull != null) {
            return delegateOrNull.containsValue(obj);
        }
        for (int r1 = 0; r1 < this.size; r1++) {
            if (Objects.equal(obj, value(r1))) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Collection<V> values() {
        Collection<V> collection = this.valuesView;
        if (collection == null) {
            Collection<V> createValues = createValues();
            this.valuesView = createValues;
            return createValues;
        }
        return collection;
    }

    Collection<V> createValues() {
        return new ValuesView();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public class ValuesView extends AbstractCollection<V> {
        ValuesView() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return CompactHashMap.this.size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public void clear() {
            CompactHashMap.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<V> iterator() {
            return CompactHashMap.this.valuesIterator();
        }
    }

    Iterator<V> valuesIterator() {
        Map<K, V> delegateOrNull = delegateOrNull();
        if (delegateOrNull != null) {
            return delegateOrNull.values().iterator();
        }
        return new CompactHashMap<K, V>.Itr<V>() { // from class: com.google.common.collect.CompactHashMap.3
            @Override // com.google.common.collect.CompactHashMap.Itr
            @ParametricNullness
            V getOutput(int r2) {
                return (V) CompactHashMap.this.value(r2);
            }
        };
    }

    public void trimToSize() {
        if (needsAllocArrays()) {
            return;
        }
        Map<K, V> delegateOrNull = delegateOrNull();
        if (delegateOrNull != null) {
            Map<K, V> createHashFloodingResistantDelegate = createHashFloodingResistantDelegate(size());
            createHashFloodingResistantDelegate.putAll(delegateOrNull);
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

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        if (needsAllocArrays()) {
            return;
        }
        incrementModCount();
        Map<K, V> delegateOrNull = delegateOrNull();
        if (delegateOrNull != null) {
            this.metadata = Ints.constrainToRange(size(), 3, LockFreeTaskQueueCore.MAX_CAPACITY_MASK);
            delegateOrNull.clear();
            this.table = null;
            this.size = 0;
            return;
        }
        Arrays.fill(requireKeys(), 0, this.size, (Object) null);
        Arrays.fill(requireValues(), 0, this.size, (Object) null);
        CompactHashing.tableClear(requireTable());
        Arrays.fill(requireEntries(), 0, this.size, 0);
        this.size = 0;
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeInt(size());
        Iterator<Map.Entry<K, V>> entrySetIterator = entrySetIterator();
        while (entrySetIterator.hasNext()) {
            Map.Entry<K, V> next = entrySetIterator.next();
            objectOutputStream.writeObject(next.getKey());
            objectOutputStream.writeObject(next.getValue());
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
            put(objectInputStream.readObject(), objectInputStream.readObject());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Object requireTable() {
        Object obj = this.table;
        java.util.Objects.requireNonNull(obj);
        return obj;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int[] requireEntries() {
        int[] r0 = this.entries;
        java.util.Objects.requireNonNull(r0);
        return r0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Object[] requireKeys() {
        Object[] objArr = this.keys;
        java.util.Objects.requireNonNull(objArr);
        return objArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Object[] requireValues() {
        Object[] objArr = this.values;
        java.util.Objects.requireNonNull(objArr);
        return objArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public K key(int r2) {
        return (K) requireKeys()[r2];
    }

    /* JADX INFO: Access modifiers changed from: private */
    public V value(int r2) {
        return (V) requireValues()[r2];
    }

    private int entry(int r2) {
        return requireEntries()[r2];
    }

    private void setKey(int r2, K k) {
        requireKeys()[r2] = k;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setValue(int r2, V v) {
        requireValues()[r2] = v;
    }

    private void setEntry(int r2, int r3) {
        requireEntries()[r2] = r3;
    }
}
