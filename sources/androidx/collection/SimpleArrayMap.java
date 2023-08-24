package androidx.collection;

import java.util.ConcurrentModificationException;
import java.util.Map;

/* loaded from: classes.dex */
public class SimpleArrayMap<K, V> {
    private static final int BASE_SIZE = 4;
    private static final int CACHE_SIZE = 10;
    private static final boolean CONCURRENT_MODIFICATION_EXCEPTIONS = true;
    private static final boolean DEBUG = false;
    private static final String TAG = "ArrayMap";
    static Object[] mBaseCache;
    static int mBaseCacheSize;
    static Object[] mTwiceBaseCache;
    static int mTwiceBaseCacheSize;
    Object[] mArray;
    int[] mHashes;
    int mSize;

    private static int binarySearchHashes(int[] r0, int r1, int r2) {
        try {
            return ContainerHelpers.binarySearch(r0, r1, r2);
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new ConcurrentModificationException();
        }
    }

    int indexOf(Object obj, int r7) {
        int r0 = this.mSize;
        if (r0 == 0) {
            return -1;
        }
        int binarySearchHashes = binarySearchHashes(this.mHashes, r0, r7);
        if (binarySearchHashes >= 0 && !obj.equals(this.mArray[binarySearchHashes << 1])) {
            int r2 = binarySearchHashes + 1;
            while (r2 < r0 && this.mHashes[r2] == r7) {
                if (obj.equals(this.mArray[r2 << 1])) {
                    return r2;
                }
                r2++;
            }
            for (int r1 = binarySearchHashes - 1; r1 >= 0 && this.mHashes[r1] == r7; r1--) {
                if (obj.equals(this.mArray[r1 << 1])) {
                    return r1;
                }
            }
            return ~r2;
        }
        return binarySearchHashes;
    }

    int indexOfNull() {
        int r0 = this.mSize;
        if (r0 == 0) {
            return -1;
        }
        int binarySearchHashes = binarySearchHashes(this.mHashes, r0, 0);
        if (binarySearchHashes >= 0 && this.mArray[binarySearchHashes << 1] != null) {
            int r2 = binarySearchHashes + 1;
            while (r2 < r0 && this.mHashes[r2] == 0) {
                if (this.mArray[r2 << 1] == null) {
                    return r2;
                }
                r2++;
            }
            for (int r1 = binarySearchHashes - 1; r1 >= 0 && this.mHashes[r1] == 0; r1--) {
                if (this.mArray[r1 << 1] == null) {
                    return r1;
                }
            }
            return ~r2;
        }
        return binarySearchHashes;
    }

    private void allocArrays(int r6) {
        if (r6 == 8) {
            synchronized (SimpleArrayMap.class) {
                Object[] objArr = mTwiceBaseCache;
                if (objArr != null) {
                    this.mArray = objArr;
                    mTwiceBaseCache = (Object[]) objArr[0];
                    this.mHashes = (int[]) objArr[1];
                    objArr[1] = null;
                    objArr[0] = null;
                    mTwiceBaseCacheSize--;
                    return;
                }
            }
        } else if (r6 == 4) {
            synchronized (SimpleArrayMap.class) {
                Object[] objArr2 = mBaseCache;
                if (objArr2 != null) {
                    this.mArray = objArr2;
                    mBaseCache = (Object[]) objArr2[0];
                    this.mHashes = (int[]) objArr2[1];
                    objArr2[1] = null;
                    objArr2[0] = null;
                    mBaseCacheSize--;
                    return;
                }
            }
        }
        this.mHashes = new int[r6];
        this.mArray = new Object[r6 << 1];
    }

    private static void freeArrays(int[] r8, Object[] objArr, int r10) {
        if (r8.length == 8) {
            synchronized (SimpleArrayMap.class) {
                if (mTwiceBaseCacheSize < 10) {
                    objArr[0] = mTwiceBaseCache;
                    objArr[1] = r8;
                    for (int r82 = (r10 << 1) - 1; r82 >= 2; r82--) {
                        objArr[r82] = null;
                    }
                    mTwiceBaseCache = objArr;
                    mTwiceBaseCacheSize++;
                }
            }
        } else if (r8.length == 4) {
            synchronized (SimpleArrayMap.class) {
                if (mBaseCacheSize < 10) {
                    objArr[0] = mBaseCache;
                    objArr[1] = r8;
                    for (int r83 = (r10 << 1) - 1; r83 >= 2; r83--) {
                        objArr[r83] = null;
                    }
                    mBaseCache = objArr;
                    mBaseCacheSize++;
                }
            }
        }
    }

    public SimpleArrayMap() {
        this.mHashes = ContainerHelpers.EMPTY_INTS;
        this.mArray = ContainerHelpers.EMPTY_OBJECTS;
        this.mSize = 0;
    }

    public SimpleArrayMap(int r1) {
        if (r1 == 0) {
            this.mHashes = ContainerHelpers.EMPTY_INTS;
            this.mArray = ContainerHelpers.EMPTY_OBJECTS;
        } else {
            allocArrays(r1);
        }
        this.mSize = 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SimpleArrayMap(SimpleArrayMap<K, V> simpleArrayMap) {
        this();
        if (simpleArrayMap != 0) {
            putAll(simpleArrayMap);
        }
    }

    public void clear() {
        int r0 = this.mSize;
        if (r0 > 0) {
            int[] r1 = this.mHashes;
            Object[] objArr = this.mArray;
            this.mHashes = ContainerHelpers.EMPTY_INTS;
            this.mArray = ContainerHelpers.EMPTY_OBJECTS;
            this.mSize = 0;
            freeArrays(r1, objArr, r0);
        }
        if (this.mSize > 0) {
            throw new ConcurrentModificationException();
        }
    }

    public void ensureCapacity(int r6) {
        int r0 = this.mSize;
        int[] r1 = this.mHashes;
        if (r1.length < r6) {
            Object[] objArr = this.mArray;
            allocArrays(r6);
            if (this.mSize > 0) {
                System.arraycopy(r1, 0, this.mHashes, 0, r0);
                System.arraycopy(objArr, 0, this.mArray, 0, r0 << 1);
            }
            freeArrays(r1, objArr, r0);
        }
        if (this.mSize != r0) {
            throw new ConcurrentModificationException();
        }
    }

    public boolean containsKey(Object obj) {
        return indexOfKey(obj) >= 0;
    }

    public int indexOfKey(Object obj) {
        return obj == null ? indexOfNull() : indexOf(obj, obj.hashCode());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int indexOfValue(Object obj) {
        int r0 = this.mSize * 2;
        Object[] objArr = this.mArray;
        if (obj == null) {
            for (int r6 = 1; r6 < r0; r6 += 2) {
                if (objArr[r6] == null) {
                    return r6 >> 1;
                }
            }
            return -1;
        }
        for (int r3 = 1; r3 < r0; r3 += 2) {
            if (obj.equals(objArr[r3])) {
                return r3 >> 1;
            }
        }
        return -1;
    }

    public boolean containsValue(Object obj) {
        return indexOfValue(obj) >= 0;
    }

    public V get(Object obj) {
        return getOrDefault(obj, null);
    }

    public V getOrDefault(Object obj, V v) {
        int indexOfKey = indexOfKey(obj);
        return indexOfKey >= 0 ? (V) this.mArray[(indexOfKey << 1) + 1] : v;
    }

    public K keyAt(int r2) {
        return (K) this.mArray[r2 << 1];
    }

    public V valueAt(int r2) {
        return (V) this.mArray[(r2 << 1) + 1];
    }

    public V setValueAt(int r3, V v) {
        int r32 = (r3 << 1) + 1;
        Object[] objArr = this.mArray;
        V v2 = (V) objArr[r32];
        objArr[r32] = v;
        return v2;
    }

    public boolean isEmpty() {
        return this.mSize <= 0;
    }

    public V put(K k, V v) {
        int r3;
        int indexOf;
        int r0 = this.mSize;
        if (k == null) {
            indexOf = indexOfNull();
            r3 = 0;
        } else {
            int hashCode = k.hashCode();
            r3 = hashCode;
            indexOf = indexOf(k, hashCode);
        }
        if (indexOf >= 0) {
            int r10 = (indexOf << 1) + 1;
            Object[] objArr = this.mArray;
            V v2 = (V) objArr[r10];
            objArr[r10] = v;
            return v2;
        }
        int r2 = ~indexOf;
        int[] r4 = this.mHashes;
        if (r0 >= r4.length) {
            int r5 = 4;
            if (r0 >= 8) {
                r5 = (r0 >> 1) + r0;
            } else if (r0 >= 4) {
                r5 = 8;
            }
            Object[] objArr2 = this.mArray;
            allocArrays(r5);
            if (r0 != this.mSize) {
                throw new ConcurrentModificationException();
            }
            int[] r52 = this.mHashes;
            if (r52.length > 0) {
                System.arraycopy(r4, 0, r52, 0, r4.length);
                System.arraycopy(objArr2, 0, this.mArray, 0, objArr2.length);
            }
            freeArrays(r4, objArr2, r0);
        }
        if (r2 < r0) {
            int[] r1 = this.mHashes;
            int r42 = r2 + 1;
            System.arraycopy(r1, r2, r1, r42, r0 - r2);
            Object[] objArr3 = this.mArray;
            System.arraycopy(objArr3, r2 << 1, objArr3, r42 << 1, (this.mSize - r2) << 1);
        }
        int r12 = this.mSize;
        if (r0 == r12) {
            int[] r02 = this.mHashes;
            if (r2 < r02.length) {
                r02[r2] = r3;
                Object[] objArr4 = this.mArray;
                int r22 = r2 << 1;
                objArr4[r22] = k;
                objArr4[r22 + 1] = v;
                this.mSize = r12 + 1;
                return null;
            }
        }
        throw new ConcurrentModificationException();
    }

    public void putAll(SimpleArrayMap<? extends K, ? extends V> simpleArrayMap) {
        int r0 = simpleArrayMap.mSize;
        ensureCapacity(this.mSize + r0);
        if (this.mSize != 0) {
            for (int r2 = 0; r2 < r0; r2++) {
                put(simpleArrayMap.keyAt(r2), simpleArrayMap.valueAt(r2));
            }
        } else if (r0 > 0) {
            System.arraycopy(simpleArrayMap.mHashes, 0, this.mHashes, 0, r0);
            System.arraycopy(simpleArrayMap.mArray, 0, this.mArray, 0, r0 << 1);
            this.mSize = r0;
        }
    }

    public V putIfAbsent(K k, V v) {
        V v2 = get(k);
        return v2 == null ? put(k, v) : v2;
    }

    public V remove(Object obj) {
        int indexOfKey = indexOfKey(obj);
        if (indexOfKey >= 0) {
            return removeAt(indexOfKey);
        }
        return null;
    }

    public boolean remove(Object obj, Object obj2) {
        int indexOfKey = indexOfKey(obj);
        if (indexOfKey >= 0) {
            V valueAt = valueAt(indexOfKey);
            if (obj2 == valueAt || (obj2 != null && obj2.equals(valueAt))) {
                removeAt(indexOfKey);
                return true;
            }
            return false;
        }
        return false;
    }

    public V removeAt(int r11) {
        Object[] objArr = this.mArray;
        int r1 = r11 << 1;
        V v = (V) objArr[r1 + 1];
        int r3 = this.mSize;
        int r4 = 0;
        if (r3 <= 1) {
            freeArrays(this.mHashes, objArr, r3);
            this.mHashes = ContainerHelpers.EMPTY_INTS;
            this.mArray = ContainerHelpers.EMPTY_OBJECTS;
        } else {
            int r6 = r3 - 1;
            int[] r7 = this.mHashes;
            if (r7.length > 8 && r3 < r7.length / 3) {
                allocArrays(r3 > 8 ? r3 + (r3 >> 1) : 8);
                if (r3 != this.mSize) {
                    throw new ConcurrentModificationException();
                }
                if (r11 > 0) {
                    System.arraycopy(r7, 0, this.mHashes, 0, r11);
                    System.arraycopy(objArr, 0, this.mArray, 0, r1);
                }
                if (r11 < r6) {
                    int r42 = r11 + 1;
                    int r9 = r6 - r11;
                    System.arraycopy(r7, r42, this.mHashes, r11, r9);
                    System.arraycopy(objArr, r42 << 1, this.mArray, r1, r9 << 1);
                }
            } else {
                if (r11 < r6) {
                    int r0 = r11 + 1;
                    int r43 = r6 - r11;
                    System.arraycopy(r7, r0, r7, r11, r43);
                    Object[] objArr2 = this.mArray;
                    System.arraycopy(objArr2, r0 << 1, objArr2, r1, r43 << 1);
                }
                Object[] objArr3 = this.mArray;
                int r02 = r6 << 1;
                objArr3[r02] = null;
                objArr3[r02 + 1] = null;
            }
            r4 = r6;
        }
        if (r3 != this.mSize) {
            throw new ConcurrentModificationException();
        }
        this.mSize = r4;
        return v;
    }

    public V replace(K k, V v) {
        int indexOfKey = indexOfKey(k);
        if (indexOfKey >= 0) {
            return setValueAt(indexOfKey, v);
        }
        return null;
    }

    public boolean replace(K k, V v, V v2) {
        int indexOfKey = indexOfKey(k);
        if (indexOfKey >= 0) {
            V valueAt = valueAt(indexOfKey);
            if (valueAt == v || (v != null && v.equals(valueAt))) {
                setValueAt(indexOfKey, v2);
                return true;
            }
            return false;
        }
        return false;
    }

    public int size() {
        return this.mSize;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SimpleArrayMap) {
            SimpleArrayMap simpleArrayMap = (SimpleArrayMap) obj;
            if (size() != simpleArrayMap.size()) {
                return false;
            }
            for (int r1 = 0; r1 < this.mSize; r1++) {
                try {
                    K keyAt = keyAt(r1);
                    V valueAt = valueAt(r1);
                    Object obj2 = simpleArrayMap.get(keyAt);
                    if (valueAt == null) {
                        if (obj2 != null || !simpleArrayMap.containsKey(keyAt)) {
                            return false;
                        }
                    } else if (!valueAt.equals(obj2)) {
                        return false;
                    }
                } catch (ClassCastException | NullPointerException unused) {
                    return false;
                }
            }
            return true;
        }
        if (obj instanceof Map) {
            Map map = (Map) obj;
            if (size() != map.size()) {
                return false;
            }
            for (int r12 = 0; r12 < this.mSize; r12++) {
                try {
                    K keyAt2 = keyAt(r12);
                    V valueAt2 = valueAt(r12);
                    Object obj3 = map.get(keyAt2);
                    if (valueAt2 == null) {
                        if (obj3 != null || !map.containsKey(keyAt2)) {
                            return false;
                        }
                    } else if (!valueAt2.equals(obj3)) {
                        return false;
                    }
                } catch (ClassCastException | NullPointerException unused2) {
                }
            }
            return true;
        }
        return false;
    }

    public int hashCode() {
        int[] r0 = this.mHashes;
        Object[] objArr = this.mArray;
        int r2 = this.mSize;
        int r3 = 1;
        int r5 = 0;
        int r6 = 0;
        while (r5 < r2) {
            Object obj = objArr[r3];
            r6 += (obj == null ? 0 : obj.hashCode()) ^ r0[r5];
            r5++;
            r3 += 2;
        }
        return r6;
    }

    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.mSize * 28);
        sb.append('{');
        for (int r1 = 0; r1 < this.mSize; r1++) {
            if (r1 > 0) {
                sb.append(", ");
            }
            K keyAt = keyAt(r1);
            if (keyAt != this) {
                sb.append(keyAt);
            } else {
                sb.append("(this Map)");
            }
            sb.append('=');
            V valueAt = valueAt(r1);
            if (valueAt != this) {
                sb.append(valueAt);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }
}
