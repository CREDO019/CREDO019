package androidx.collection;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public final class ArraySet<E> implements Collection<E>, Set<E> {
    private static final int BASE_SIZE = 4;
    private static final int CACHE_SIZE = 10;
    private static final boolean DEBUG = false;
    private static final int[] INT = new int[0];
    private static final Object[] OBJECT = new Object[0];
    private static final String TAG = "ArraySet";
    private static Object[] sBaseCache;
    private static int sBaseCacheSize;
    private static Object[] sTwiceBaseCache;
    private static int sTwiceBaseCacheSize;
    Object[] mArray;
    private MapCollections<E, E> mCollections;
    private int[] mHashes;
    int mSize;

    private int indexOf(Object obj, int r6) {
        int r0 = this.mSize;
        if (r0 == 0) {
            return -1;
        }
        int binarySearch = ContainerHelpers.binarySearch(this.mHashes, r0, r6);
        if (binarySearch >= 0 && !obj.equals(this.mArray[binarySearch])) {
            int r2 = binarySearch + 1;
            while (r2 < r0 && this.mHashes[r2] == r6) {
                if (obj.equals(this.mArray[r2])) {
                    return r2;
                }
                r2++;
            }
            for (int r1 = binarySearch - 1; r1 >= 0 && this.mHashes[r1] == r6; r1--) {
                if (obj.equals(this.mArray[r1])) {
                    return r1;
                }
            }
            return ~r2;
        }
        return binarySearch;
    }

    private int indexOfNull() {
        int r0 = this.mSize;
        if (r0 == 0) {
            return -1;
        }
        int binarySearch = ContainerHelpers.binarySearch(this.mHashes, r0, 0);
        if (binarySearch >= 0 && this.mArray[binarySearch] != null) {
            int r2 = binarySearch + 1;
            while (r2 < r0 && this.mHashes[r2] == 0) {
                if (this.mArray[r2] == null) {
                    return r2;
                }
                r2++;
            }
            for (int r1 = binarySearch - 1; r1 >= 0 && this.mHashes[r1] == 0; r1--) {
                if (this.mArray[r1] == null) {
                    return r1;
                }
            }
            return ~r2;
        }
        return binarySearch;
    }

    private void allocArrays(int r6) {
        if (r6 == 8) {
            synchronized (ArraySet.class) {
                Object[] objArr = sTwiceBaseCache;
                if (objArr != null) {
                    this.mArray = objArr;
                    sTwiceBaseCache = (Object[]) objArr[0];
                    this.mHashes = (int[]) objArr[1];
                    objArr[1] = null;
                    objArr[0] = null;
                    sTwiceBaseCacheSize--;
                    return;
                }
            }
        } else if (r6 == 4) {
            synchronized (ArraySet.class) {
                Object[] objArr2 = sBaseCache;
                if (objArr2 != null) {
                    this.mArray = objArr2;
                    sBaseCache = (Object[]) objArr2[0];
                    this.mHashes = (int[]) objArr2[1];
                    objArr2[1] = null;
                    objArr2[0] = null;
                    sBaseCacheSize--;
                    return;
                }
            }
        }
        this.mHashes = new int[r6];
        this.mArray = new Object[r6];
    }

    private static void freeArrays(int[] r8, Object[] objArr, int r10) {
        if (r8.length == 8) {
            synchronized (ArraySet.class) {
                if (sTwiceBaseCacheSize < 10) {
                    objArr[0] = sTwiceBaseCache;
                    objArr[1] = r8;
                    for (int r102 = r10 - 1; r102 >= 2; r102--) {
                        objArr[r102] = null;
                    }
                    sTwiceBaseCache = objArr;
                    sTwiceBaseCacheSize++;
                }
            }
        } else if (r8.length == 4) {
            synchronized (ArraySet.class) {
                if (sBaseCacheSize < 10) {
                    objArr[0] = sBaseCache;
                    objArr[1] = r8;
                    for (int r103 = r10 - 1; r103 >= 2; r103--) {
                        objArr[r103] = null;
                    }
                    sBaseCache = objArr;
                    sBaseCacheSize++;
                }
            }
        }
    }

    public ArraySet() {
        this(0);
    }

    public ArraySet(int r1) {
        if (r1 == 0) {
            this.mHashes = INT;
            this.mArray = OBJECT;
        } else {
            allocArrays(r1);
        }
        this.mSize = 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ArraySet(ArraySet<E> arraySet) {
        this();
        if (arraySet != 0) {
            addAll((ArraySet) arraySet);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ArraySet(Collection<E> collection) {
        this();
        if (collection != 0) {
            addAll(collection);
        }
    }

    @Override // java.util.Collection, java.util.Set
    public void clear() {
        int r0 = this.mSize;
        if (r0 != 0) {
            freeArrays(this.mHashes, this.mArray, r0);
            this.mHashes = INT;
            this.mArray = OBJECT;
            this.mSize = 0;
        }
    }

    public void ensureCapacity(int r5) {
        int[] r0 = this.mHashes;
        if (r0.length < r5) {
            Object[] objArr = this.mArray;
            allocArrays(r5);
            int r52 = this.mSize;
            if (r52 > 0) {
                System.arraycopy(r0, 0, this.mHashes, 0, r52);
                System.arraycopy(objArr, 0, this.mArray, 0, this.mSize);
            }
            freeArrays(r0, objArr, this.mSize);
        }
    }

    @Override // java.util.Collection, java.util.Set
    public boolean contains(Object obj) {
        return indexOf(obj) >= 0;
    }

    public int indexOf(Object obj) {
        return obj == null ? indexOfNull() : indexOf(obj, obj.hashCode());
    }

    public E valueAt(int r2) {
        return (E) this.mArray[r2];
    }

    @Override // java.util.Collection, java.util.Set
    public boolean isEmpty() {
        return this.mSize <= 0;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean add(E e) {
        int r2;
        int indexOf;
        if (e == null) {
            indexOf = indexOfNull();
            r2 = 0;
        } else {
            int hashCode = e.hashCode();
            r2 = hashCode;
            indexOf = indexOf(e, hashCode);
        }
        if (indexOf >= 0) {
            return false;
        }
        int r1 = ~indexOf;
        int r3 = this.mSize;
        int[] r4 = this.mHashes;
        if (r3 >= r4.length) {
            int r5 = 4;
            if (r3 >= 8) {
                r5 = (r3 >> 1) + r3;
            } else if (r3 >= 4) {
                r5 = 8;
            }
            Object[] objArr = this.mArray;
            allocArrays(r5);
            int[] r52 = this.mHashes;
            if (r52.length > 0) {
                System.arraycopy(r4, 0, r52, 0, r4.length);
                System.arraycopy(objArr, 0, this.mArray, 0, objArr.length);
            }
            freeArrays(r4, objArr, this.mSize);
        }
        int r0 = this.mSize;
        if (r1 < r0) {
            int[] r32 = this.mHashes;
            int r42 = r1 + 1;
            System.arraycopy(r32, r1, r32, r42, r0 - r1);
            Object[] objArr2 = this.mArray;
            System.arraycopy(objArr2, r1, objArr2, r42, this.mSize - r1);
        }
        this.mHashes[r1] = r2;
        this.mArray[r1] = e;
        this.mSize++;
        return true;
    }

    public void addAll(ArraySet<? extends E> arraySet) {
        int r0 = arraySet.mSize;
        ensureCapacity(this.mSize + r0);
        if (this.mSize != 0) {
            for (int r2 = 0; r2 < r0; r2++) {
                add(arraySet.valueAt(r2));
            }
        } else if (r0 > 0) {
            System.arraycopy(arraySet.mHashes, 0, this.mHashes, 0, r0);
            System.arraycopy(arraySet.mArray, 0, this.mArray, 0, r0);
            this.mSize = r0;
        }
    }

    @Override // java.util.Collection, java.util.Set
    public boolean remove(Object obj) {
        int indexOf = indexOf(obj);
        if (indexOf >= 0) {
            removeAt(indexOf);
            return true;
        }
        return false;
    }

    public E removeAt(int r9) {
        Object[] objArr = this.mArray;
        E e = (E) objArr[r9];
        int r2 = this.mSize;
        if (r2 <= 1) {
            freeArrays(this.mHashes, objArr, r2);
            this.mHashes = INT;
            this.mArray = OBJECT;
            this.mSize = 0;
        } else {
            int[] r5 = this.mHashes;
            if (r5.length > 8 && r2 < r5.length / 3) {
                allocArrays(r2 > 8 ? r2 + (r2 >> 1) : 8);
                this.mSize--;
                if (r9 > 0) {
                    System.arraycopy(r5, 0, this.mHashes, 0, r9);
                    System.arraycopy(objArr, 0, this.mArray, 0, r9);
                }
                int r22 = this.mSize;
                if (r9 < r22) {
                    int r3 = r9 + 1;
                    System.arraycopy(r5, r3, this.mHashes, r9, r22 - r9);
                    System.arraycopy(objArr, r3, this.mArray, r9, this.mSize - r9);
                }
            } else {
                int r23 = r2 - 1;
                this.mSize = r23;
                if (r9 < r23) {
                    int r0 = r9 + 1;
                    System.arraycopy(r5, r0, r5, r9, r23 - r9);
                    Object[] objArr2 = this.mArray;
                    System.arraycopy(objArr2, r0, objArr2, r9, this.mSize - r9);
                }
                this.mArray[this.mSize] = null;
            }
        }
        return e;
    }

    public boolean removeAll(ArraySet<? extends E> arraySet) {
        int r0 = arraySet.mSize;
        int r1 = this.mSize;
        for (int r3 = 0; r3 < r0; r3++) {
            remove(arraySet.valueAt(r3));
        }
        return r1 != this.mSize;
    }

    @Override // java.util.Collection, java.util.Set
    public int size() {
        return this.mSize;
    }

    @Override // java.util.Collection, java.util.Set
    public Object[] toArray() {
        int r0 = this.mSize;
        Object[] objArr = new Object[r0];
        System.arraycopy(this.mArray, 0, objArr, 0, r0);
        return objArr;
    }

    @Override // java.util.Collection, java.util.Set
    public <T> T[] toArray(T[] tArr) {
        if (tArr.length < this.mSize) {
            tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), this.mSize));
        }
        System.arraycopy(this.mArray, 0, tArr, 0, this.mSize);
        int length = tArr.length;
        int r1 = this.mSize;
        if (length > r1) {
            tArr[r1] = null;
        }
        return tArr;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Set) {
            Set set = (Set) obj;
            if (size() != set.size()) {
                return false;
            }
            for (int r1 = 0; r1 < this.mSize; r1++) {
                try {
                    if (!set.contains(valueAt(r1))) {
                        return false;
                    }
                } catch (ClassCastException | NullPointerException unused) {
                }
            }
            return true;
        }
        return false;
    }

    @Override // java.util.Collection, java.util.Set
    public int hashCode() {
        int[] r0 = this.mHashes;
        int r1 = this.mSize;
        int r3 = 0;
        for (int r2 = 0; r2 < r1; r2++) {
            r3 += r0[r2];
        }
        return r3;
    }

    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.mSize * 14);
        sb.append('{');
        for (int r1 = 0; r1 < this.mSize; r1++) {
            if (r1 > 0) {
                sb.append(", ");
            }
            E valueAt = valueAt(r1);
            if (valueAt != this) {
                sb.append(valueAt);
            } else {
                sb.append("(this Set)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    private MapCollections<E, E> getCollection() {
        if (this.mCollections == null) {
            this.mCollections = new MapCollections<E, E>() { // from class: androidx.collection.ArraySet.1
                @Override // androidx.collection.MapCollections
                protected int colGetSize() {
                    return ArraySet.this.mSize;
                }

                @Override // androidx.collection.MapCollections
                protected Object colGetEntry(int r1, int r2) {
                    return ArraySet.this.mArray[r1];
                }

                @Override // androidx.collection.MapCollections
                protected int colIndexOfKey(Object obj) {
                    return ArraySet.this.indexOf(obj);
                }

                @Override // androidx.collection.MapCollections
                protected int colIndexOfValue(Object obj) {
                    return ArraySet.this.indexOf(obj);
                }

                @Override // androidx.collection.MapCollections
                protected Map<E, E> colGetMap() {
                    throw new UnsupportedOperationException("not a map");
                }

                @Override // androidx.collection.MapCollections
                protected void colPut(E e, E e2) {
                    ArraySet.this.add(e);
                }

                @Override // androidx.collection.MapCollections
                protected E colSetValue(int r1, E e) {
                    throw new UnsupportedOperationException("not a map");
                }

                @Override // androidx.collection.MapCollections
                protected void colRemoveAt(int r2) {
                    ArraySet.this.removeAt(r2);
                }

                @Override // androidx.collection.MapCollections
                protected void colClear() {
                    ArraySet.this.clear();
                }
            };
        }
        return this.mCollections;
    }

    @Override // java.util.Collection, java.lang.Iterable, java.util.Set
    public Iterator<E> iterator() {
        return getCollection().getKeySet().iterator();
    }

    @Override // java.util.Collection, java.util.Set
    public boolean containsAll(Collection<?> collection) {
        Iterator<?> it = collection.iterator();
        while (it.hasNext()) {
            if (!contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean addAll(Collection<? extends E> collection) {
        ensureCapacity(this.mSize + collection.size());
        boolean z = false;
        for (E e : collection) {
            z |= add(e);
        }
        return z;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean removeAll(Collection<?> collection) {
        Iterator<?> it = collection.iterator();
        boolean z = false;
        while (it.hasNext()) {
            z |= remove(it.next());
        }
        return z;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean retainAll(Collection<?> collection) {
        boolean z = false;
        for (int r0 = this.mSize - 1; r0 >= 0; r0--) {
            if (!collection.contains(this.mArray[r0])) {
                removeAt(r0);
                z = true;
            }
        }
        return z;
    }
}
