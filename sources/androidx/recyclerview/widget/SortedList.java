package androidx.recyclerview.widget;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

/* loaded from: classes.dex */
public class SortedList<T> {
    private static final int CAPACITY_GROWTH = 10;
    private static final int DELETION = 2;
    private static final int INSERTION = 1;
    public static final int INVALID_POSITION = -1;
    private static final int LOOKUP = 4;
    private static final int MIN_CAPACITY = 10;
    private BatchedCallback mBatchedCallback;
    private Callback mCallback;
    T[] mData;
    private int mNewDataStart;
    private T[] mOldData;
    private int mOldDataSize;
    private int mOldDataStart;
    private int mSize;
    private final Class<T> mTClass;

    public SortedList(Class<T> cls, Callback<T> callback) {
        this(cls, callback, 10);
    }

    public SortedList(Class<T> cls, Callback<T> callback, int r3) {
        this.mTClass = cls;
        this.mData = (T[]) ((Object[]) Array.newInstance((Class<?>) cls, r3));
        this.mCallback = callback;
        this.mSize = 0;
    }

    public int size() {
        return this.mSize;
    }

    public int add(T t) {
        throwIfInMutationOperation();
        return add(t, true);
    }

    public void addAll(T[] tArr, boolean z) {
        throwIfInMutationOperation();
        if (tArr.length == 0) {
            return;
        }
        if (z) {
            addAllInternal(tArr);
        } else {
            addAllInternal(copyArray(tArr));
        }
    }

    public void addAll(T... tArr) {
        addAll(tArr, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void addAll(Collection<T> collection) {
        addAll(collection.toArray((Object[]) Array.newInstance((Class<?>) this.mTClass, collection.size())), true);
    }

    public void replaceAll(T[] tArr, boolean z) {
        throwIfInMutationOperation();
        if (z) {
            replaceAllInternal(tArr);
        } else {
            replaceAllInternal(copyArray(tArr));
        }
    }

    public void replaceAll(T... tArr) {
        replaceAll(tArr, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void replaceAll(Collection<T> collection) {
        replaceAll(collection.toArray((Object[]) Array.newInstance((Class<?>) this.mTClass, collection.size())), true);
    }

    private void addAllInternal(T[] tArr) {
        if (tArr.length < 1) {
            return;
        }
        int sortAndDedup = sortAndDedup(tArr);
        if (this.mSize == 0) {
            this.mData = tArr;
            this.mSize = sortAndDedup;
            this.mCallback.onInserted(0, sortAndDedup);
            return;
        }
        merge(tArr, sortAndDedup);
    }

    private void replaceAllInternal(T[] tArr) {
        boolean z = !(this.mCallback instanceof BatchedCallback);
        if (z) {
            beginBatchedUpdates();
        }
        this.mOldDataStart = 0;
        this.mOldDataSize = this.mSize;
        this.mOldData = this.mData;
        this.mNewDataStart = 0;
        int sortAndDedup = sortAndDedup(tArr);
        this.mData = (T[]) ((Object[]) Array.newInstance((Class<?>) this.mTClass, sortAndDedup));
        while (true) {
            int r3 = this.mNewDataStart;
            if (r3 >= sortAndDedup && this.mOldDataStart >= this.mOldDataSize) {
                break;
            }
            int r4 = this.mOldDataStart;
            int r5 = this.mOldDataSize;
            if (r4 >= r5) {
                int r2 = sortAndDedup - r3;
                System.arraycopy(tArr, r3, this.mData, r3, r2);
                this.mNewDataStart += r2;
                this.mSize += r2;
                this.mCallback.onInserted(r3, r2);
                break;
            } else if (r3 >= sortAndDedup) {
                int r52 = r5 - r4;
                this.mSize -= r52;
                this.mCallback.onRemoved(r3, r52);
                break;
            } else {
                T t = this.mOldData[r4];
                T t2 = tArr[r3];
                int compare = this.mCallback.compare(t, t2);
                if (compare < 0) {
                    replaceAllRemove();
                } else if (compare > 0) {
                    replaceAllInsert(t2);
                } else if (!this.mCallback.areItemsTheSame(t, t2)) {
                    replaceAllRemove();
                    replaceAllInsert(t2);
                } else {
                    T[] tArr2 = this.mData;
                    int r6 = this.mNewDataStart;
                    tArr2[r6] = t2;
                    this.mOldDataStart++;
                    this.mNewDataStart = r6 + 1;
                    if (!this.mCallback.areContentsTheSame(t, t2)) {
                        Callback callback = this.mCallback;
                        callback.onChanged(this.mNewDataStart - 1, 1, callback.getChangePayload(t, t2));
                    }
                }
            }
        }
        this.mOldData = null;
        if (z) {
            endBatchedUpdates();
        }
    }

    private void replaceAllInsert(T t) {
        T[] tArr = this.mData;
        int r1 = this.mNewDataStart;
        tArr[r1] = t;
        int r12 = r1 + 1;
        this.mNewDataStart = r12;
        this.mSize++;
        this.mCallback.onInserted(r12 - 1, 1);
    }

    private void replaceAllRemove() {
        this.mSize--;
        this.mOldDataStart++;
        this.mCallback.onRemoved(this.mNewDataStart, 1);
    }

    private int sortAndDedup(T[] tArr) {
        if (tArr.length == 0) {
            return 0;
        }
        Arrays.sort(tArr, this.mCallback);
        int r1 = 1;
        int r2 = 0;
        for (int r0 = 1; r0 < tArr.length; r0++) {
            T t = tArr[r0];
            if (this.mCallback.compare(tArr[r2], t) == 0) {
                int findSameItem = findSameItem(t, tArr, r2, r1);
                if (findSameItem != -1) {
                    tArr[findSameItem] = t;
                } else {
                    if (r1 != r0) {
                        tArr[r1] = t;
                    }
                    r1++;
                }
            } else {
                if (r1 != r0) {
                    tArr[r1] = t;
                }
                r2 = r1;
                r1++;
            }
        }
        return r1;
    }

    private int findSameItem(T t, T[] tArr, int r5, int r6) {
        while (r5 < r6) {
            if (this.mCallback.areItemsTheSame(tArr[r5], t)) {
                return r5;
            }
            r5++;
        }
        return -1;
    }

    private void merge(T[] tArr, int r10) {
        boolean z = !(this.mCallback instanceof BatchedCallback);
        if (z) {
            beginBatchedUpdates();
        }
        this.mOldData = this.mData;
        int r2 = 0;
        this.mOldDataStart = 0;
        int r3 = this.mSize;
        this.mOldDataSize = r3;
        this.mData = (T[]) ((Object[]) Array.newInstance((Class<?>) this.mTClass, r3 + r10 + 10));
        this.mNewDataStart = 0;
        while (true) {
            int r32 = this.mOldDataStart;
            int r4 = this.mOldDataSize;
            if (r32 >= r4 && r2 >= r10) {
                break;
            } else if (r32 == r4) {
                int r102 = r10 - r2;
                System.arraycopy(tArr, r2, this.mData, this.mNewDataStart, r102);
                int r9 = this.mNewDataStart + r102;
                this.mNewDataStart = r9;
                this.mSize += r102;
                this.mCallback.onInserted(r9 - r102, r102);
                break;
            } else if (r2 == r10) {
                int r42 = r4 - r32;
                System.arraycopy(this.mOldData, r32, this.mData, this.mNewDataStart, r42);
                this.mNewDataStart += r42;
                break;
            } else {
                T t = this.mOldData[r32];
                T t2 = tArr[r2];
                int compare = this.mCallback.compare(t, t2);
                if (compare > 0) {
                    T[] tArr2 = this.mData;
                    int r5 = this.mNewDataStart;
                    int r6 = r5 + 1;
                    this.mNewDataStart = r6;
                    tArr2[r5] = t2;
                    this.mSize++;
                    r2++;
                    this.mCallback.onInserted(r6 - 1, 1);
                } else if (compare == 0 && this.mCallback.areItemsTheSame(t, t2)) {
                    T[] tArr3 = this.mData;
                    int r62 = this.mNewDataStart;
                    this.mNewDataStart = r62 + 1;
                    tArr3[r62] = t2;
                    r2++;
                    this.mOldDataStart++;
                    if (!this.mCallback.areContentsTheSame(t, t2)) {
                        Callback callback = this.mCallback;
                        callback.onChanged(this.mNewDataStart - 1, 1, callback.getChangePayload(t, t2));
                    }
                } else {
                    T[] tArr4 = this.mData;
                    int r52 = this.mNewDataStart;
                    this.mNewDataStart = r52 + 1;
                    tArr4[r52] = t;
                    this.mOldDataStart++;
                }
            }
        }
        this.mOldData = null;
        if (z) {
            endBatchedUpdates();
        }
    }

    private void throwIfInMutationOperation() {
        if (this.mOldData != null) {
            throw new IllegalStateException("Data cannot be mutated in the middle of a batch update operation such as addAll or replaceAll.");
        }
    }

    public void beginBatchedUpdates() {
        throwIfInMutationOperation();
        Callback callback = this.mCallback;
        if (callback instanceof BatchedCallback) {
            return;
        }
        if (this.mBatchedCallback == null) {
            this.mBatchedCallback = new BatchedCallback(callback);
        }
        this.mCallback = this.mBatchedCallback;
    }

    public void endBatchedUpdates() {
        throwIfInMutationOperation();
        Callback callback = this.mCallback;
        if (callback instanceof BatchedCallback) {
            ((BatchedCallback) callback).dispatchLastEvent();
        }
        Callback callback2 = this.mCallback;
        BatchedCallback batchedCallback = this.mBatchedCallback;
        if (callback2 == batchedCallback) {
            this.mCallback = batchedCallback.mWrappedCallback;
        }
    }

    private int add(T t, boolean z) {
        int findIndexOf = findIndexOf(t, this.mData, 0, this.mSize, 1);
        if (findIndexOf == -1) {
            findIndexOf = 0;
        } else if (findIndexOf < this.mSize) {
            T t2 = this.mData[findIndexOf];
            if (this.mCallback.areItemsTheSame(t2, t)) {
                if (this.mCallback.areContentsTheSame(t2, t)) {
                    this.mData[findIndexOf] = t;
                    return findIndexOf;
                }
                this.mData[findIndexOf] = t;
                Callback callback = this.mCallback;
                callback.onChanged(findIndexOf, 1, callback.getChangePayload(t2, t));
                return findIndexOf;
            }
        }
        addToData(findIndexOf, t);
        if (z) {
            this.mCallback.onInserted(findIndexOf, 1);
        }
        return findIndexOf;
    }

    public boolean remove(T t) {
        throwIfInMutationOperation();
        return remove(t, true);
    }

    public T removeItemAt(int r3) {
        throwIfInMutationOperation();
        T t = get(r3);
        removeItemAtIndex(r3, true);
        return t;
    }

    private boolean remove(T t, boolean z) {
        int findIndexOf = findIndexOf(t, this.mData, 0, this.mSize, 2);
        if (findIndexOf == -1) {
            return false;
        }
        removeItemAtIndex(findIndexOf, z);
        return true;
    }

    private void removeItemAtIndex(int r5, boolean z) {
        T[] tArr = this.mData;
        System.arraycopy(tArr, r5 + 1, tArr, r5, (this.mSize - r5) - 1);
        int r0 = this.mSize - 1;
        this.mSize = r0;
        this.mData[r0] = null;
        if (z) {
            this.mCallback.onRemoved(r5, 1);
        }
    }

    public void updateItemAt(int r6, T t) {
        throwIfInMutationOperation();
        T t2 = get(r6);
        boolean z = t2 == t || !this.mCallback.areContentsTheSame(t2, t);
        if (t2 != t && this.mCallback.compare(t2, t) == 0) {
            this.mData[r6] = t;
            if (z) {
                Callback callback = this.mCallback;
                callback.onChanged(r6, 1, callback.getChangePayload(t2, t));
                return;
            }
            return;
        }
        if (z) {
            Callback callback2 = this.mCallback;
            callback2.onChanged(r6, 1, callback2.getChangePayload(t2, t));
        }
        removeItemAtIndex(r6, false);
        int add = add(t, false);
        if (r6 != add) {
            this.mCallback.onMoved(r6, add);
        }
    }

    public void recalculatePositionOfItemAt(int r3) {
        throwIfInMutationOperation();
        T t = get(r3);
        removeItemAtIndex(r3, false);
        int add = add(t, false);
        if (r3 != add) {
            this.mCallback.onMoved(r3, add);
        }
    }

    public T get(int r4) throws IndexOutOfBoundsException {
        int r1;
        if (r4 >= this.mSize || r4 < 0) {
            throw new IndexOutOfBoundsException("Asked to get item at " + r4 + " but size is " + this.mSize);
        }
        T[] tArr = this.mOldData;
        if (tArr != null && r4 >= (r1 = this.mNewDataStart)) {
            return tArr[(r4 - r1) + this.mOldDataStart];
        }
        return this.mData[r4];
    }

    public int indexOf(T t) {
        if (this.mOldData != null) {
            int findIndexOf = findIndexOf(t, this.mData, 0, this.mNewDataStart, 4);
            if (findIndexOf != -1) {
                return findIndexOf;
            }
            int findIndexOf2 = findIndexOf(t, this.mOldData, this.mOldDataStart, this.mOldDataSize, 4);
            if (findIndexOf2 != -1) {
                return (findIndexOf2 - this.mOldDataStart) + this.mNewDataStart;
            }
            return -1;
        }
        return findIndexOf(t, this.mData, 0, this.mSize, 4);
    }

    private int findIndexOf(T t, T[] tArr, int r8, int r9, int r10) {
        while (r8 < r9) {
            int r2 = (r8 + r9) / 2;
            T t2 = tArr[r2];
            int compare = this.mCallback.compare(t2, t);
            if (compare < 0) {
                r8 = r2 + 1;
            } else if (compare == 0) {
                if (this.mCallback.areItemsTheSame(t2, t)) {
                    return r2;
                }
                int linearEqualitySearch = linearEqualitySearch(t, r2, r8, r9);
                return (r10 == 1 && linearEqualitySearch == -1) ? r2 : linearEqualitySearch;
            } else {
                r9 = r2;
            }
        }
        if (r10 == 1) {
            return r8;
        }
        return -1;
    }

    private int linearEqualitySearch(T t, int r5, int r6, int r7) {
        T t2;
        for (int r0 = r5 - 1; r0 >= r6; r0--) {
            T t3 = this.mData[r0];
            if (this.mCallback.compare(t3, t) != 0) {
                break;
            } else if (this.mCallback.areItemsTheSame(t3, t)) {
                return r0;
            }
        }
        do {
            r5++;
            if (r5 >= r7) {
                return -1;
            }
            t2 = this.mData[r5];
            if (this.mCallback.compare(t2, t) != 0) {
                return -1;
            }
        } while (!this.mCallback.areItemsTheSame(t2, t));
        return r5;
    }

    private void addToData(int r4, T t) {
        int r0 = this.mSize;
        if (r4 > r0) {
            throw new IndexOutOfBoundsException("cannot add item to " + r4 + " because size is " + this.mSize);
        }
        T[] tArr = this.mData;
        if (r0 == tArr.length) {
            T[] tArr2 = (T[]) ((Object[]) Array.newInstance((Class<?>) this.mTClass, tArr.length + 10));
            System.arraycopy(this.mData, 0, tArr2, 0, r4);
            tArr2[r4] = t;
            System.arraycopy(this.mData, r4, tArr2, r4 + 1, this.mSize - r4);
            this.mData = tArr2;
        } else {
            System.arraycopy(tArr, r4, tArr, r4 + 1, r0 - r4);
            this.mData[r4] = t;
        }
        this.mSize++;
    }

    private T[] copyArray(T[] tArr) {
        T[] tArr2 = (T[]) ((Object[]) Array.newInstance((Class<?>) this.mTClass, tArr.length));
        System.arraycopy(tArr, 0, tArr2, 0, tArr.length);
        return tArr2;
    }

    public void clear() {
        throwIfInMutationOperation();
        int r0 = this.mSize;
        if (r0 == 0) {
            return;
        }
        Arrays.fill(this.mData, 0, r0, (Object) null);
        this.mSize = 0;
        this.mCallback.onRemoved(0, r0);
    }

    /* loaded from: classes.dex */
    public static abstract class Callback<T2> implements Comparator<T2>, ListUpdateCallback {
        public abstract boolean areContentsTheSame(T2 t2, T2 t22);

        public abstract boolean areItemsTheSame(T2 t2, T2 t22);

        @Override // java.util.Comparator
        public abstract int compare(T2 t2, T2 t22);

        public Object getChangePayload(T2 t2, T2 t22) {
            return null;
        }

        public abstract void onChanged(int r1, int r2);

        public void onChanged(int r1, int r2, Object obj) {
            onChanged(r1, r2);
        }
    }

    /* loaded from: classes.dex */
    public static class BatchedCallback<T2> extends Callback<T2> {
        private final BatchingListUpdateCallback mBatchingListUpdateCallback;
        final Callback<T2> mWrappedCallback;

        public BatchedCallback(Callback<T2> callback) {
            this.mWrappedCallback = callback;
            this.mBatchingListUpdateCallback = new BatchingListUpdateCallback(callback);
        }

        @Override // androidx.recyclerview.widget.SortedList.Callback, java.util.Comparator
        public int compare(T2 t2, T2 t22) {
            return this.mWrappedCallback.compare(t2, t22);
        }

        @Override // androidx.recyclerview.widget.ListUpdateCallback
        public void onInserted(int r2, int r3) {
            this.mBatchingListUpdateCallback.onInserted(r2, r3);
        }

        @Override // androidx.recyclerview.widget.ListUpdateCallback
        public void onRemoved(int r2, int r3) {
            this.mBatchingListUpdateCallback.onRemoved(r2, r3);
        }

        @Override // androidx.recyclerview.widget.ListUpdateCallback
        public void onMoved(int r2, int r3) {
            this.mBatchingListUpdateCallback.onMoved(r2, r3);
        }

        @Override // androidx.recyclerview.widget.SortedList.Callback
        public void onChanged(int r3, int r4) {
            this.mBatchingListUpdateCallback.onChanged(r3, r4, null);
        }

        @Override // androidx.recyclerview.widget.SortedList.Callback, androidx.recyclerview.widget.ListUpdateCallback
        public void onChanged(int r2, int r3, Object obj) {
            this.mBatchingListUpdateCallback.onChanged(r2, r3, obj);
        }

        @Override // androidx.recyclerview.widget.SortedList.Callback
        public boolean areContentsTheSame(T2 t2, T2 t22) {
            return this.mWrappedCallback.areContentsTheSame(t2, t22);
        }

        @Override // androidx.recyclerview.widget.SortedList.Callback
        public boolean areItemsTheSame(T2 t2, T2 t22) {
            return this.mWrappedCallback.areItemsTheSame(t2, t22);
        }

        @Override // androidx.recyclerview.widget.SortedList.Callback
        public Object getChangePayload(T2 t2, T2 t22) {
            return this.mWrappedCallback.getChangePayload(t2, t22);
        }

        public void dispatchLastEvent() {
            this.mBatchingListUpdateCallback.dispatchLastEvent();
        }
    }
}
