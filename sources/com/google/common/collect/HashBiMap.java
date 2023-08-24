package com.google.common.collect;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableCollection;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
/* loaded from: classes3.dex */
public final class HashBiMap<K, V> extends AbstractMap<K, V> implements BiMap<K, V>, Serializable {
    private static final int ABSENT = -1;
    private static final int ENDPOINT = -2;
    private transient Set<Map.Entry<K, V>> entrySet;
    private transient int firstInInsertionOrder;
    private transient int[] hashTableKToV;
    private transient int[] hashTableVToK;
    @CheckForNull
    @LazyInit
    private transient BiMap<V, K> inverse;
    private transient Set<K> keySet;
    transient K[] keys;
    private transient int lastInInsertionOrder;
    transient int modCount;
    private transient int[] nextInBucketKToV;
    private transient int[] nextInBucketVToK;
    private transient int[] nextInInsertionOrder;
    private transient int[] prevInInsertionOrder;
    transient int size;
    private transient Set<V> valueSet;
    transient V[] values;

    public static <K, V> HashBiMap<K, V> create() {
        return create(16);
    }

    public static <K, V> HashBiMap<K, V> create(int r1) {
        return new HashBiMap<>(r1);
    }

    public static <K, V> HashBiMap<K, V> create(Map<? extends K, ? extends V> map) {
        HashBiMap<K, V> create = create(map.size());
        create.putAll(map);
        return create;
    }

    private HashBiMap(int r1) {
        init(r1);
    }

    void init(int r3) {
        CollectPreconditions.checkNonnegative(r3, "expectedSize");
        int closedTableSize = Hashing.closedTableSize(r3, 1.0d);
        this.size = 0;
        this.keys = (K[]) new Object[r3];
        this.values = (V[]) new Object[r3];
        this.hashTableKToV = createFilledWithAbsent(closedTableSize);
        this.hashTableVToK = createFilledWithAbsent(closedTableSize);
        this.nextInBucketKToV = createFilledWithAbsent(r3);
        this.nextInBucketVToK = createFilledWithAbsent(r3);
        this.firstInInsertionOrder = -2;
        this.lastInInsertionOrder = -2;
        this.prevInInsertionOrder = createFilledWithAbsent(r3);
        this.nextInInsertionOrder = createFilledWithAbsent(r3);
    }

    private static int[] createFilledWithAbsent(int r1) {
        int[] r12 = new int[r1];
        Arrays.fill(r12, -1);
        return r12;
    }

    private static int[] expandAndFillWithAbsent(int[] r2, int r3) {
        int length = r2.length;
        int[] copyOf = Arrays.copyOf(r2, r3);
        Arrays.fill(copyOf, length, r3, -1);
        return copyOf;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        return this.size;
    }

    private void ensureCapacity(int r5) {
        int[] r0 = this.nextInBucketKToV;
        if (r0.length < r5) {
            int expandedCapacity = ImmutableCollection.Builder.expandedCapacity(r0.length, r5);
            this.keys = (K[]) Arrays.copyOf(this.keys, expandedCapacity);
            this.values = (V[]) Arrays.copyOf(this.values, expandedCapacity);
            this.nextInBucketKToV = expandAndFillWithAbsent(this.nextInBucketKToV, expandedCapacity);
            this.nextInBucketVToK = expandAndFillWithAbsent(this.nextInBucketVToK, expandedCapacity);
            this.prevInInsertionOrder = expandAndFillWithAbsent(this.prevInInsertionOrder, expandedCapacity);
            this.nextInInsertionOrder = expandAndFillWithAbsent(this.nextInInsertionOrder, expandedCapacity);
        }
        if (this.hashTableKToV.length < r5) {
            int closedTableSize = Hashing.closedTableSize(r5, 1.0d);
            this.hashTableKToV = createFilledWithAbsent(closedTableSize);
            this.hashTableVToK = createFilledWithAbsent(closedTableSize);
            for (int r52 = 0; r52 < this.size; r52++) {
                int bucket = bucket(Hashing.smearedHash(this.keys[r52]));
                int[] r1 = this.nextInBucketKToV;
                int[] r2 = this.hashTableKToV;
                r1[r52] = r2[bucket];
                r2[bucket] = r52;
                int bucket2 = bucket(Hashing.smearedHash(this.values[r52]));
                int[] r12 = this.nextInBucketVToK;
                int[] r22 = this.hashTableVToK;
                r12[r52] = r22[bucket2];
                r22[bucket2] = r52;
            }
        }
    }

    private int bucket(int r2) {
        return r2 & (this.hashTableKToV.length - 1);
    }

    int findEntryByKey(@CheckForNull Object obj) {
        return findEntryByKey(obj, Hashing.smearedHash(obj));
    }

    int findEntryByKey(@CheckForNull Object obj, int r8) {
        return findEntry(obj, r8, this.hashTableKToV, this.nextInBucketKToV, this.keys);
    }

    int findEntryByValue(@CheckForNull Object obj) {
        return findEntryByValue(obj, Hashing.smearedHash(obj));
    }

    int findEntryByValue(@CheckForNull Object obj, int r8) {
        return findEntry(obj, r8, this.hashTableVToK, this.nextInBucketVToK, this.values);
    }

    int findEntry(@CheckForNull Object obj, int r2, int[] r3, int[] r4, Object[] objArr) {
        int r22 = r3[bucket(r2)];
        while (r22 != -1) {
            if (Objects.equal(objArr[r22], obj)) {
                return r22;
            }
            r22 = r4[r22];
        }
        return -1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(@CheckForNull Object obj) {
        return findEntryByKey(obj) != -1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsValue(@CheckForNull Object obj) {
        return findEntryByValue(obj) != -1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    @CheckForNull
    public V get(@CheckForNull Object obj) {
        int findEntryByKey = findEntryByKey(obj);
        if (findEntryByKey == -1) {
            return null;
        }
        return this.values[findEntryByKey];
    }

    @CheckForNull
    K getInverse(@CheckForNull Object obj) {
        int findEntryByValue = findEntryByValue(obj);
        if (findEntryByValue == -1) {
            return null;
        }
        return this.keys[findEntryByValue];
    }

    @Override // java.util.AbstractMap, java.util.Map, com.google.common.collect.BiMap
    @CheckForNull
    public V put(@ParametricNullness K k, @ParametricNullness V v) {
        return put(k, v, false);
    }

    @CheckForNull
    V put(@ParametricNullness K k, @ParametricNullness V v, boolean z) {
        int smearedHash = Hashing.smearedHash(k);
        int findEntryByKey = findEntryByKey(k, smearedHash);
        if (findEntryByKey != -1) {
            V v2 = this.values[findEntryByKey];
            if (Objects.equal(v2, v)) {
                return v;
            }
            replaceValueInEntry(findEntryByKey, v, z);
            return v2;
        }
        int smearedHash2 = Hashing.smearedHash(v);
        int findEntryByValue = findEntryByValue(v, smearedHash2);
        if (!z) {
            Preconditions.checkArgument(findEntryByValue == -1, "Value already present: %s", v);
        } else if (findEntryByValue != -1) {
            removeEntryValueHashKnown(findEntryByValue, smearedHash2);
        }
        ensureCapacity(this.size + 1);
        K[] kArr = this.keys;
        int r2 = this.size;
        kArr[r2] = k;
        this.values[r2] = v;
        insertIntoTableKToV(r2, smearedHash);
        insertIntoTableVToK(this.size, smearedHash2);
        setSucceeds(this.lastInInsertionOrder, this.size);
        setSucceeds(this.size, -2);
        this.size++;
        this.modCount++;
        return null;
    }

    @Override // com.google.common.collect.BiMap
    @CheckForNull
    public V forcePut(@ParametricNullness K k, @ParametricNullness V v) {
        return put(k, v, true);
    }

    @CheckForNull
    K putInverse(@ParametricNullness V v, @ParametricNullness K k, boolean z) {
        int smearedHash = Hashing.smearedHash(v);
        int findEntryByValue = findEntryByValue(v, smearedHash);
        if (findEntryByValue != -1) {
            K k2 = this.keys[findEntryByValue];
            if (Objects.equal(k2, k)) {
                return k;
            }
            replaceKeyInEntry(findEntryByValue, k, z);
            return k2;
        }
        int r1 = this.lastInInsertionOrder;
        int smearedHash2 = Hashing.smearedHash(k);
        int findEntryByKey = findEntryByKey(k, smearedHash2);
        if (!z) {
            Preconditions.checkArgument(findEntryByKey == -1, "Key already present: %s", k);
        } else if (findEntryByKey != -1) {
            r1 = this.prevInInsertionOrder[findEntryByKey];
            removeEntryKeyHashKnown(findEntryByKey, smearedHash2);
        }
        ensureCapacity(this.size + 1);
        K[] kArr = this.keys;
        int r2 = this.size;
        kArr[r2] = k;
        this.values[r2] = v;
        insertIntoTableKToV(r2, smearedHash2);
        insertIntoTableVToK(this.size, smearedHash);
        int r7 = r1 == -2 ? this.firstInInsertionOrder : this.nextInInsertionOrder[r1];
        setSucceeds(r1, this.size);
        setSucceeds(this.size, r7);
        this.size++;
        this.modCount++;
        return null;
    }

    private void setSucceeds(int r3, int r4) {
        if (r3 == -2) {
            this.firstInInsertionOrder = r4;
        } else {
            this.nextInInsertionOrder[r3] = r4;
        }
        if (r4 == -2) {
            this.lastInInsertionOrder = r3;
        } else {
            this.prevInInsertionOrder[r4] = r3;
        }
    }

    private void insertIntoTableKToV(int r4, int r5) {
        Preconditions.checkArgument(r4 != -1);
        int bucket = bucket(r5);
        int[] r0 = this.nextInBucketKToV;
        int[] r1 = this.hashTableKToV;
        r0[r4] = r1[bucket];
        r1[bucket] = r4;
    }

    private void insertIntoTableVToK(int r4, int r5) {
        Preconditions.checkArgument(r4 != -1);
        int bucket = bucket(r5);
        int[] r0 = this.nextInBucketVToK;
        int[] r1 = this.hashTableVToK;
        r0[r4] = r1[bucket];
        r1[bucket] = r4;
    }

    private void deleteFromTableKToV(int r6, int r7) {
        Preconditions.checkArgument(r6 != -1);
        int bucket = bucket(r7);
        int[] r1 = this.hashTableKToV;
        if (r1[bucket] == r6) {
            int[] r2 = this.nextInBucketKToV;
            r1[bucket] = r2[r6];
            r2[r6] = -1;
            return;
        }
        int r72 = r1[bucket];
        int r12 = this.nextInBucketKToV[r72];
        while (true) {
            int r4 = r12;
            int r13 = r72;
            r72 = r4;
            if (r72 == -1) {
                String valueOf = String.valueOf(this.keys[r6]);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 32);
                sb.append("Expected to find entry with key ");
                sb.append(valueOf);
                throw new AssertionError(sb.toString());
            } else if (r72 != r6) {
                r12 = this.nextInBucketKToV[r72];
            } else {
                int[] r73 = this.nextInBucketKToV;
                r73[r13] = r73[r6];
                r73[r6] = -1;
                return;
            }
        }
    }

    private void deleteFromTableVToK(int r6, int r7) {
        Preconditions.checkArgument(r6 != -1);
        int bucket = bucket(r7);
        int[] r1 = this.hashTableVToK;
        if (r1[bucket] == r6) {
            int[] r2 = this.nextInBucketVToK;
            r1[bucket] = r2[r6];
            r2[r6] = -1;
            return;
        }
        int r72 = r1[bucket];
        int r12 = this.nextInBucketVToK[r72];
        while (true) {
            int r4 = r12;
            int r13 = r72;
            r72 = r4;
            if (r72 == -1) {
                String valueOf = String.valueOf(this.values[r6]);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 34);
                sb.append("Expected to find entry with value ");
                sb.append(valueOf);
                throw new AssertionError(sb.toString());
            } else if (r72 != r6) {
                r12 = this.nextInBucketVToK[r72];
            } else {
                int[] r73 = this.nextInBucketVToK;
                r73[r13] = r73[r6];
                r73[r6] = -1;
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void replaceValueInEntry(int r4, @ParametricNullness V v, boolean z) {
        Preconditions.checkArgument(r4 != -1);
        int smearedHash = Hashing.smearedHash(v);
        int findEntryByValue = findEntryByValue(v, smearedHash);
        if (findEntryByValue != -1) {
            if (z) {
                removeEntryValueHashKnown(findEntryByValue, smearedHash);
                if (r4 == this.size) {
                    r4 = findEntryByValue;
                }
            } else {
                String valueOf = String.valueOf(v);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 30);
                sb.append("Value already present in map: ");
                sb.append(valueOf);
                throw new IllegalArgumentException(sb.toString());
            }
        }
        deleteFromTableVToK(r4, Hashing.smearedHash(this.values[r4]));
        this.values[r4] = v;
        insertIntoTableVToK(r4, smearedHash);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void replaceKeyInEntry(int r6, @ParametricNullness K k, boolean z) {
        Preconditions.checkArgument(r6 != -1);
        int smearedHash = Hashing.smearedHash(k);
        int findEntryByKey = findEntryByKey(k, smearedHash);
        int r3 = this.lastInInsertionOrder;
        int r4 = -2;
        if (findEntryByKey != -1) {
            if (z) {
                r3 = this.prevInInsertionOrder[findEntryByKey];
                r4 = this.nextInInsertionOrder[findEntryByKey];
                removeEntryKeyHashKnown(findEntryByKey, smearedHash);
                if (r6 == this.size) {
                    r6 = findEntryByKey;
                }
            } else {
                String valueOf = String.valueOf(k);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 28);
                sb.append("Key already present in map: ");
                sb.append(valueOf);
                throw new IllegalArgumentException(sb.toString());
            }
        }
        if (r3 == r6) {
            r3 = this.prevInInsertionOrder[r6];
        } else if (r3 == this.size) {
            r3 = findEntryByKey;
        }
        if (r4 == r6) {
            findEntryByKey = this.nextInInsertionOrder[r6];
        } else if (r4 != this.size) {
            findEntryByKey = r4;
        }
        setSucceeds(this.prevInInsertionOrder[r6], this.nextInInsertionOrder[r6]);
        deleteFromTableKToV(r6, Hashing.smearedHash(this.keys[r6]));
        this.keys[r6] = k;
        insertIntoTableKToV(r6, Hashing.smearedHash(k));
        setSucceeds(r3, r6);
        setSucceeds(r6, findEntryByKey);
    }

    @Override // java.util.AbstractMap, java.util.Map
    @CheckForNull
    public V remove(@CheckForNull Object obj) {
        int smearedHash = Hashing.smearedHash(obj);
        int findEntryByKey = findEntryByKey(obj, smearedHash);
        if (findEntryByKey == -1) {
            return null;
        }
        V v = this.values[findEntryByKey];
        removeEntryKeyHashKnown(findEntryByKey, smearedHash);
        return v;
    }

    @CheckForNull
    K removeInverse(@CheckForNull Object obj) {
        int smearedHash = Hashing.smearedHash(obj);
        int findEntryByValue = findEntryByValue(obj, smearedHash);
        if (findEntryByValue == -1) {
            return null;
        }
        K k = this.keys[findEntryByValue];
        removeEntryValueHashKnown(findEntryByValue, smearedHash);
        return k;
    }

    void removeEntry(int r2) {
        removeEntryKeyHashKnown(r2, Hashing.smearedHash(this.keys[r2]));
    }

    private void removeEntry(int r3, int r4, int r5) {
        Preconditions.checkArgument(r3 != -1);
        deleteFromTableKToV(r3, r4);
        deleteFromTableVToK(r3, r5);
        setSucceeds(this.prevInInsertionOrder[r3], this.nextInInsertionOrder[r3]);
        moveEntryToIndex(this.size - 1, r3);
        K[] kArr = this.keys;
        int r42 = this.size;
        kArr[r42 - 1] = null;
        this.values[r42 - 1] = null;
        this.size = r42 - 1;
        this.modCount++;
    }

    void removeEntryKeyHashKnown(int r2, int r3) {
        removeEntry(r2, r3, Hashing.smearedHash(this.values[r2]));
    }

    void removeEntryValueHashKnown(int r2, int r3) {
        removeEntry(r2, Hashing.smearedHash(this.keys[r2]), r3);
    }

    private void moveEntryToIndex(int r6, int r7) {
        int r1;
        int r2;
        if (r6 == r7) {
            return;
        }
        int r0 = this.prevInInsertionOrder[r6];
        int r12 = this.nextInInsertionOrder[r6];
        setSucceeds(r0, r7);
        setSucceeds(r7, r12);
        K[] kArr = this.keys;
        K k = kArr[r6];
        V[] vArr = this.values;
        V v = vArr[r6];
        kArr[r7] = k;
        vArr[r7] = v;
        int bucket = bucket(Hashing.smearedHash(k));
        int[] r13 = this.hashTableKToV;
        if (r13[bucket] == r6) {
            r13[bucket] = r7;
        } else {
            int r02 = r13[bucket];
            int r14 = this.nextInBucketKToV[r02];
            while (true) {
                int r4 = r14;
                r1 = r02;
                r02 = r4;
                if (r02 == r6) {
                    break;
                }
                r14 = this.nextInBucketKToV[r02];
            }
            this.nextInBucketKToV[r1] = r7;
        }
        int[] r03 = this.nextInBucketKToV;
        r03[r7] = r03[r6];
        r03[r6] = -1;
        int bucket2 = bucket(Hashing.smearedHash(v));
        int[] r22 = this.hashTableVToK;
        if (r22[bucket2] == r6) {
            r22[bucket2] = r7;
        } else {
            int r04 = r22[bucket2];
            int r23 = this.nextInBucketVToK[r04];
            while (true) {
                int r42 = r23;
                r2 = r04;
                r04 = r42;
                if (r04 == r6) {
                    break;
                }
                r23 = this.nextInBucketVToK[r04];
            }
            this.nextInBucketVToK[r2] = r7;
        }
        int[] r05 = this.nextInBucketVToK;
        r05[r7] = r05[r6];
        r05[r6] = -1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        Arrays.fill(this.keys, 0, this.size, (Object) null);
        Arrays.fill(this.values, 0, this.size, (Object) null);
        Arrays.fill(this.hashTableKToV, -1);
        Arrays.fill(this.hashTableVToK, -1);
        Arrays.fill(this.nextInBucketKToV, 0, this.size, -1);
        Arrays.fill(this.nextInBucketVToK, 0, this.size, -1);
        Arrays.fill(this.prevInInsertionOrder, 0, this.size, -1);
        Arrays.fill(this.nextInInsertionOrder, 0, this.size, -1);
        this.size = 0;
        this.firstInInsertionOrder = -2;
        this.lastInInsertionOrder = -2;
        this.modCount++;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static abstract class View<K, V, T> extends AbstractSet<T> {
        final HashBiMap<K, V> biMap;

        @ParametricNullness
        abstract T forEntry(int r1);

        View(HashBiMap<K, V> hashBiMap) {
            this.biMap = hashBiMap;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<T> iterator() {
            return new Iterator<T>() { // from class: com.google.common.collect.HashBiMap.View.1
                private int expectedModCount;
                private int index;
                private int indexToRemove = -1;
                private int remaining;

                {
                    this.index = ((HashBiMap) View.this.biMap).firstInInsertionOrder;
                    this.expectedModCount = View.this.biMap.modCount;
                    this.remaining = View.this.biMap.size;
                }

                private void checkForComodification() {
                    if (View.this.biMap.modCount != this.expectedModCount) {
                        throw new ConcurrentModificationException();
                    }
                }

                @Override // java.util.Iterator
                public boolean hasNext() {
                    checkForComodification();
                    return this.index != -2 && this.remaining > 0;
                }

                @Override // java.util.Iterator
                @ParametricNullness
                public T next() {
                    if (!hasNext()) {
                        throw new NoSuchElementException();
                    }
                    T t = (T) View.this.forEntry(this.index);
                    this.indexToRemove = this.index;
                    this.index = ((HashBiMap) View.this.biMap).nextInInsertionOrder[this.index];
                    this.remaining--;
                    return t;
                }

                @Override // java.util.Iterator
                public void remove() {
                    checkForComodification();
                    CollectPreconditions.checkRemove(this.indexToRemove != -1);
                    View.this.biMap.removeEntry(this.indexToRemove);
                    if (this.index == View.this.biMap.size) {
                        this.index = this.indexToRemove;
                    }
                    this.indexToRemove = -1;
                    this.expectedModCount = View.this.biMap.modCount;
                }
            };
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return this.biMap.size;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            this.biMap.clear();
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<K> keySet() {
        Set<K> set = this.keySet;
        if (set == null) {
            KeySet keySet = new KeySet();
            this.keySet = keySet;
            return keySet;
        }
        return set;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public final class KeySet extends View<K, V, K> {
        KeySet() {
            super(HashBiMap.this);
        }

        @Override // com.google.common.collect.HashBiMap.View
        @ParametricNullness
        K forEntry(int r2) {
            return (K) NullnessCasts.uncheckedCastNullableTToT(HashBiMap.this.keys[r2]);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(@CheckForNull Object obj) {
            return HashBiMap.this.containsKey(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(@CheckForNull Object obj) {
            int smearedHash = Hashing.smearedHash(obj);
            int findEntryByKey = HashBiMap.this.findEntryByKey(obj, smearedHash);
            if (findEntryByKey != -1) {
                HashBiMap.this.removeEntryKeyHashKnown(findEntryByKey, smearedHash);
                return true;
            }
            return false;
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<V> values() {
        Set<V> set = this.valueSet;
        if (set == null) {
            ValueSet valueSet = new ValueSet();
            this.valueSet = valueSet;
            return valueSet;
        }
        return set;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public final class ValueSet extends View<K, V, V> {
        ValueSet() {
            super(HashBiMap.this);
        }

        @Override // com.google.common.collect.HashBiMap.View
        @ParametricNullness
        V forEntry(int r2) {
            return (V) NullnessCasts.uncheckedCastNullableTToT(HashBiMap.this.values[r2]);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(@CheckForNull Object obj) {
            return HashBiMap.this.containsValue(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(@CheckForNull Object obj) {
            int smearedHash = Hashing.smearedHash(obj);
            int findEntryByValue = HashBiMap.this.findEntryByValue(obj, smearedHash);
            if (findEntryByValue != -1) {
                HashBiMap.this.removeEntryValueHashKnown(findEntryByValue, smearedHash);
                return true;
            }
            return false;
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = this.entrySet;
        if (set == null) {
            EntrySet entrySet = new EntrySet();
            this.entrySet = entrySet;
            return entrySet;
        }
        return set;
    }

    /* loaded from: classes3.dex */
    final class EntrySet extends View<K, V, Map.Entry<K, V>> {
        EntrySet() {
            super(HashBiMap.this);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(@CheckForNull Object obj) {
            if (obj instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry) obj;
                Object key = entry.getKey();
                Object value = entry.getValue();
                int findEntryByKey = HashBiMap.this.findEntryByKey(key);
                return findEntryByKey != -1 && Objects.equal(value, HashBiMap.this.values[findEntryByKey]);
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(@CheckForNull Object obj) {
            if (obj instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry) obj;
                Object key = entry.getKey();
                Object value = entry.getValue();
                int smearedHash = Hashing.smearedHash(key);
                int findEntryByKey = HashBiMap.this.findEntryByKey(key, smearedHash);
                if (findEntryByKey == -1 || !Objects.equal(value, HashBiMap.this.values[findEntryByKey])) {
                    return false;
                }
                HashBiMap.this.removeEntryKeyHashKnown(findEntryByKey, smearedHash);
                return true;
            }
            return false;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.HashBiMap.View
        public Map.Entry<K, V> forEntry(int r3) {
            return new EntryForKey(r3);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public final class EntryForKey extends AbstractMapEntry<K, V> {
        int index;
        @ParametricNullness
        final K key;

        EntryForKey(int r2) {
            this.key = (K) NullnessCasts.uncheckedCastNullableTToT(HashBiMap.this.keys[r2]);
            this.index = r2;
        }

        void updateIndex() {
            int r0 = this.index;
            if (r0 == -1 || r0 > HashBiMap.this.size || !Objects.equal(HashBiMap.this.keys[this.index], this.key)) {
                this.index = HashBiMap.this.findEntryByKey(this.key);
            }
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        @ParametricNullness
        public K getKey() {
            return this.key;
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        @ParametricNullness
        public V getValue() {
            updateIndex();
            return this.index == -1 ? (V) NullnessCasts.unsafeNull() : (V) NullnessCasts.uncheckedCastNullableTToT(HashBiMap.this.values[this.index]);
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        @ParametricNullness
        public V setValue(@ParametricNullness V v) {
            updateIndex();
            if (this.index == -1) {
                HashBiMap.this.put(this.key, v);
                return (V) NullnessCasts.unsafeNull();
            }
            V v2 = (V) NullnessCasts.uncheckedCastNullableTToT(HashBiMap.this.values[this.index]);
            if (Objects.equal(v2, v)) {
                return v;
            }
            HashBiMap.this.replaceValueInEntry(this.index, v, false);
            return v2;
        }
    }

    @Override // com.google.common.collect.BiMap
    public BiMap<V, K> inverse() {
        BiMap<V, K> biMap = this.inverse;
        if (biMap == null) {
            Inverse inverse = new Inverse(this);
            this.inverse = inverse;
            return inverse;
        }
        return biMap;
    }

    /* loaded from: classes3.dex */
    static class Inverse<K, V> extends AbstractMap<V, K> implements BiMap<V, K>, Serializable {
        private final HashBiMap<K, V> forward;
        private transient Set<Map.Entry<V, K>> inverseEntrySet;

        Inverse(HashBiMap<K, V> hashBiMap) {
            this.forward = hashBiMap;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public int size() {
            return this.forward.size;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean containsKey(@CheckForNull Object obj) {
            return this.forward.containsValue(obj);
        }

        @Override // java.util.AbstractMap, java.util.Map
        @CheckForNull
        public K get(@CheckForNull Object obj) {
            return this.forward.getInverse(obj);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean containsValue(@CheckForNull Object obj) {
            return this.forward.containsKey(obj);
        }

        @Override // java.util.AbstractMap, java.util.Map, com.google.common.collect.BiMap
        @CheckForNull
        public K put(@ParametricNullness V v, @ParametricNullness K k) {
            return this.forward.putInverse(v, k, false);
        }

        @Override // com.google.common.collect.BiMap
        @CheckForNull
        public K forcePut(@ParametricNullness V v, @ParametricNullness K k) {
            return this.forward.putInverse(v, k, true);
        }

        @Override // com.google.common.collect.BiMap
        public BiMap<K, V> inverse() {
            return this.forward;
        }

        @Override // java.util.AbstractMap, java.util.Map
        @CheckForNull
        public K remove(@CheckForNull Object obj) {
            return this.forward.removeInverse(obj);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public void clear() {
            this.forward.clear();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Set<V> keySet() {
            return this.forward.values();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Set<K> values() {
            return this.forward.keySet();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Set<Map.Entry<V, K>> entrySet() {
            Set<Map.Entry<V, K>> set = this.inverseEntrySet;
            if (set == null) {
                InverseEntrySet inverseEntrySet = new InverseEntrySet(this.forward);
                this.inverseEntrySet = inverseEntrySet;
                return inverseEntrySet;
            }
            return set;
        }

        private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
            objectInputStream.defaultReadObject();
            ((HashBiMap) this.forward).inverse = this;
        }
    }

    /* loaded from: classes3.dex */
    static class InverseEntrySet<K, V> extends View<K, V, Map.Entry<V, K>> {
        InverseEntrySet(HashBiMap<K, V> hashBiMap) {
            super(hashBiMap);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(@CheckForNull Object obj) {
            if (obj instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry) obj;
                Object key = entry.getKey();
                Object value = entry.getValue();
                int findEntryByValue = this.biMap.findEntryByValue(key);
                return findEntryByValue != -1 && Objects.equal(this.biMap.keys[findEntryByValue], value);
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(@CheckForNull Object obj) {
            if (obj instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry) obj;
                Object key = entry.getKey();
                Object value = entry.getValue();
                int smearedHash = Hashing.smearedHash(key);
                int findEntryByValue = this.biMap.findEntryByValue(key, smearedHash);
                if (findEntryByValue == -1 || !Objects.equal(this.biMap.keys[findEntryByValue], value)) {
                    return false;
                }
                this.biMap.removeEntryValueHashKnown(findEntryByValue, smearedHash);
                return true;
            }
            return false;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.HashBiMap.View
        public Map.Entry<V, K> forEntry(int r3) {
            return new EntryForValue(this.biMap, r3);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static final class EntryForValue<K, V> extends AbstractMapEntry<V, K> {
        final HashBiMap<K, V> biMap;
        int index;
        @ParametricNullness
        final V value;

        EntryForValue(HashBiMap<K, V> hashBiMap, int r2) {
            this.biMap = hashBiMap;
            this.value = (V) NullnessCasts.uncheckedCastNullableTToT(hashBiMap.values[r2]);
            this.index = r2;
        }

        private void updateIndex() {
            int r0 = this.index;
            if (r0 == -1 || r0 > this.biMap.size || !Objects.equal(this.value, this.biMap.values[this.index])) {
                this.index = this.biMap.findEntryByValue(this.value);
            }
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        @ParametricNullness
        public V getKey() {
            return this.value;
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        @ParametricNullness
        public K getValue() {
            updateIndex();
            return this.index == -1 ? (K) NullnessCasts.unsafeNull() : (K) NullnessCasts.uncheckedCastNullableTToT(this.biMap.keys[this.index]);
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        @ParametricNullness
        public K setValue(@ParametricNullness K k) {
            updateIndex();
            if (this.index == -1) {
                this.biMap.putInverse(this.value, k, false);
                return (K) NullnessCasts.unsafeNull();
            }
            K k2 = (K) NullnessCasts.uncheckedCastNullableTToT(this.biMap.keys[this.index]);
            if (Objects.equal(k2, k)) {
                return k;
            }
            this.biMap.replaceKeyInEntry(this.index, k, false);
            return k2;
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        Serialization.writeMap(this, objectOutputStream);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        int readCount = Serialization.readCount(objectInputStream);
        init(16);
        Serialization.populateMap(this, objectInputStream, readCount);
    }
}
