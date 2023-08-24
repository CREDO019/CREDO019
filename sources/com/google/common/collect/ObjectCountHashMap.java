package com.google.common.collect;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.onesignal.shortcutbadger.impl.NewHtcHomeBadger;
import java.util.Arrays;
import javax.annotation.CheckForNull;

/* JADX INFO: Access modifiers changed from: package-private */
@ElementTypesAreNonnullByDefault
/* loaded from: classes3.dex */
public class ObjectCountHashMap<K> {
    static final float DEFAULT_LOAD_FACTOR = 1.0f;
    static final int DEFAULT_SIZE = 3;
    private static final long HASH_MASK = -4294967296L;
    private static final int MAXIMUM_CAPACITY = 1073741824;
    private static final long NEXT_MASK = 4294967295L;
    static final int UNSET = -1;
    transient long[] entries;
    transient Object[] keys;
    private transient float loadFactor;
    transient int modCount;
    transient int size;
    private transient int[] table;
    private transient int threshold;
    transient int[] values;

    private static int getHash(long j) {
        return (int) (j >>> 32);
    }

    private static int getNext(long j) {
        return (int) j;
    }

    private static long swapNext(long j, int r6) {
        return (j & HASH_MASK) | (r6 & 4294967295L);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int nextIndexAfterRemove(int r1, int r2) {
        return r1 - 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <K> ObjectCountHashMap<K> create() {
        return new ObjectCountHashMap<>();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <K> ObjectCountHashMap<K> createWithExpectedSize(int r1) {
        return new ObjectCountHashMap<>(r1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ObjectCountHashMap() {
        init(3, 1.0f);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ObjectCountHashMap(ObjectCountHashMap<? extends K> objectCountHashMap) {
        init(objectCountHashMap.size(), 1.0f);
        int firstIndex = objectCountHashMap.firstIndex();
        while (firstIndex != -1) {
            put(objectCountHashMap.getKey(firstIndex), objectCountHashMap.getValue(firstIndex));
            firstIndex = objectCountHashMap.nextIndex(firstIndex);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ObjectCountHashMap(int r2) {
        this(r2, 1.0f);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ObjectCountHashMap(int r1, float f) {
        init(r1, f);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void init(int r5, float f) {
        Preconditions.checkArgument(r5 >= 0, "Initial capacity must be non-negative");
        Preconditions.checkArgument(f > 0.0f, "Illegal load factor");
        int closedTableSize = Hashing.closedTableSize(r5, f);
        this.table = newTable(closedTableSize);
        this.loadFactor = f;
        this.keys = new Object[r5];
        this.values = new int[r5];
        this.entries = newEntries(r5);
        this.threshold = Math.max(1, (int) (closedTableSize * f));
    }

    private static int[] newTable(int r1) {
        int[] r12 = new int[r1];
        Arrays.fill(r12, -1);
        return r12;
    }

    private static long[] newEntries(int r2) {
        long[] jArr = new long[r2];
        Arrays.fill(jArr, -1L);
        return jArr;
    }

    private int hashTableMask() {
        return this.table.length - 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int firstIndex() {
        return this.size == 0 ? -1 : 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int nextIndex(int r2) {
        int r22 = r2 + 1;
        if (r22 < this.size) {
            return r22;
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int size() {
        return this.size;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @ParametricNullness
    public K getKey(int r2) {
        Preconditions.checkElementIndex(r2, this.size);
        return (K) this.keys[r2];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getValue(int r2) {
        Preconditions.checkElementIndex(r2, this.size);
        return this.values[r2];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setValue(int r2, int r3) {
        Preconditions.checkElementIndex(r2, this.size);
        this.values[r2] = r3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Multiset.Entry<K> getEntry(int r2) {
        Preconditions.checkElementIndex(r2, this.size);
        return new MapEntry(r2);
    }

    /* loaded from: classes3.dex */
    class MapEntry extends Multisets.AbstractEntry<K> {
        @ParametricNullness
        final K key;
        int lastKnownIndex;

        MapEntry(int r2) {
            this.key = (K) ObjectCountHashMap.this.keys[r2];
            this.lastKnownIndex = r2;
        }

        @Override // com.google.common.collect.Multiset.Entry
        @ParametricNullness
        public K getElement() {
            return this.key;
        }

        void updateLastKnownIndex() {
            int r0 = this.lastKnownIndex;
            if (r0 == -1 || r0 >= ObjectCountHashMap.this.size() || !Objects.equal(this.key, ObjectCountHashMap.this.keys[this.lastKnownIndex])) {
                this.lastKnownIndex = ObjectCountHashMap.this.indexOf(this.key);
            }
        }

        @Override // com.google.common.collect.Multiset.Entry
        public int getCount() {
            updateLastKnownIndex();
            if (this.lastKnownIndex == -1) {
                return 0;
            }
            return ObjectCountHashMap.this.values[this.lastKnownIndex];
        }

        public int setCount(int r4) {
            updateLastKnownIndex();
            if (this.lastKnownIndex == -1) {
                ObjectCountHashMap.this.put(this.key, r4);
                return 0;
            }
            int r0 = ObjectCountHashMap.this.values[this.lastKnownIndex];
            ObjectCountHashMap.this.values[this.lastKnownIndex] = r4;
            return r0;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void ensureCapacity(int r2) {
        if (r2 > this.entries.length) {
            resizeEntries(r2);
        }
        if (r2 >= this.threshold) {
            resizeTable(Math.max(2, Integer.highestOneBit(r2 - 1) << 1));
        }
    }

    public int put(@ParametricNullness K k, int r13) {
        CollectPreconditions.checkPositive(r13, NewHtcHomeBadger.COUNT);
        long[] jArr = this.entries;
        Object[] objArr = this.keys;
        int[] r2 = this.values;
        int smearedHash = Hashing.smearedHash(k);
        int hashTableMask = hashTableMask() & smearedHash;
        int r5 = this.size;
        int[] r6 = this.table;
        int r7 = r6[hashTableMask];
        if (r7 == -1) {
            r6[hashTableMask] = r5;
        } else {
            while (true) {
                long j = jArr[r7];
                if (getHash(j) == smearedHash && Objects.equal(k, objArr[r7])) {
                    int r12 = r2[r7];
                    r2[r7] = r13;
                    return r12;
                }
                int next = getNext(j);
                if (next == -1) {
                    jArr[r7] = swapNext(j, r5);
                    break;
                }
                r7 = next;
            }
        }
        if (r5 == Integer.MAX_VALUE) {
            throw new IllegalStateException("Cannot contain more than Integer.MAX_VALUE elements!");
        }
        int r0 = r5 + 1;
        resizeMeMaybe(r0);
        insertEntry(r5, k, r13, smearedHash);
        this.size = r0;
        if (r5 >= this.threshold) {
            resizeTable(this.table.length * 2);
        }
        this.modCount++;
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void insertEntry(int r6, @ParametricNullness K k, int r8, int r9) {
        this.entries[r6] = (r9 << 32) | 4294967295L;
        this.keys[r6] = k;
        this.values[r6] = r8;
    }

    private void resizeMeMaybe(int r3) {
        int length = this.entries.length;
        if (r3 > length) {
            int max = Math.max(1, length >>> 1) + length;
            if (max < 0) {
                max = Integer.MAX_VALUE;
            }
            if (max != length) {
                resizeEntries(max);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void resizeEntries(int r5) {
        this.keys = Arrays.copyOf(this.keys, r5);
        this.values = Arrays.copyOf(this.values, r5);
        long[] jArr = this.entries;
        int length = jArr.length;
        long[] copyOf = Arrays.copyOf(jArr, r5);
        if (r5 > length) {
            Arrays.fill(copyOf, length, r5, -1L);
        }
        this.entries = copyOf;
    }

    private void resizeTable(int r12) {
        if (this.table.length >= 1073741824) {
            this.threshold = Integer.MAX_VALUE;
            return;
        }
        int r0 = ((int) (r12 * this.loadFactor)) + 1;
        int[] newTable = newTable(r12);
        long[] jArr = this.entries;
        int length = newTable.length - 1;
        for (int r3 = 0; r3 < this.size; r3++) {
            int hash = getHash(jArr[r3]);
            int r5 = hash & length;
            int r6 = newTable[r5];
            newTable[r5] = r3;
            jArr[r3] = (hash << 32) | (r6 & 4294967295L);
        }
        this.threshold = r0;
        this.table = newTable;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int indexOf(@CheckForNull Object obj) {
        int smearedHash = Hashing.smearedHash(obj);
        int r1 = this.table[hashTableMask() & smearedHash];
        while (r1 != -1) {
            long j = this.entries[r1];
            if (getHash(j) == smearedHash && Objects.equal(obj, this.keys[r1])) {
                return r1;
            }
            r1 = getNext(j);
        }
        return -1;
    }

    public boolean containsKey(@CheckForNull Object obj) {
        return indexOf(obj) != -1;
    }

    public int get(@CheckForNull Object obj) {
        int indexOf = indexOf(obj);
        if (indexOf == -1) {
            return 0;
        }
        return this.values[indexOf];
    }

    public int remove(@CheckForNull Object obj) {
        return remove(obj, Hashing.smearedHash(obj));
    }

    private int remove(@CheckForNull Object obj, int r11) {
        int hashTableMask = hashTableMask() & r11;
        int r1 = this.table[hashTableMask];
        if (r1 == -1) {
            return 0;
        }
        int r4 = -1;
        while (true) {
            if (getHash(this.entries[r1]) == r11 && Objects.equal(obj, this.keys[r1])) {
                int r10 = this.values[r1];
                if (r4 == -1) {
                    this.table[hashTableMask] = getNext(this.entries[r1]);
                } else {
                    long[] jArr = this.entries;
                    jArr[r4] = swapNext(jArr[r4], getNext(jArr[r1]));
                }
                moveLastEntry(r1);
                this.size--;
                this.modCount++;
                return r10;
            }
            int next = getNext(this.entries[r1]);
            if (next == -1) {
                return 0;
            }
            r4 = r1;
            r1 = next;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int removeEntry(int r5) {
        return remove(this.keys[r5], getHash(this.entries[r5]));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void moveLastEntry(int r9) {
        int size = size() - 1;
        if (r9 < size) {
            Object[] objArr = this.keys;
            objArr[r9] = objArr[size];
            int[] r6 = this.values;
            r6[r9] = r6[size];
            objArr[size] = null;
            r6[size] = 0;
            long[] jArr = this.entries;
            long j = jArr[size];
            jArr[r9] = j;
            jArr[size] = -1;
            int hash = getHash(j) & hashTableMask();
            int[] r2 = this.table;
            int r3 = r2[hash];
            if (r3 == size) {
                r2[hash] = r9;
                return;
            }
            while (true) {
                long j2 = this.entries[r3];
                int next = getNext(j2);
                if (next == size) {
                    this.entries[r3] = swapNext(j2, r9);
                    return;
                }
                r3 = next;
            }
        } else {
            this.keys[r9] = null;
            this.values[r9] = 0;
            this.entries[r9] = -1;
        }
    }

    public void clear() {
        this.modCount++;
        Arrays.fill(this.keys, 0, this.size, (Object) null);
        Arrays.fill(this.values, 0, this.size, 0);
        Arrays.fill(this.table, -1);
        Arrays.fill(this.entries, -1L);
        this.size = 0;
    }
}
