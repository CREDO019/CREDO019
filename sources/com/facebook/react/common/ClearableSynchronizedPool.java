package com.facebook.react.common;

import androidx.core.util.Pools;

/* loaded from: classes.dex */
public class ClearableSynchronizedPool<T> implements Pools.Pool<T> {
    private final Object[] mPool;
    private int mSize = 0;

    public ClearableSynchronizedPool(int r2) {
        this.mPool = new Object[r2];
    }

    @Override // androidx.core.util.Pools.Pool
    public synchronized T acquire() {
        int r0 = this.mSize;
        if (r0 == 0) {
            return null;
        }
        int r02 = r0 - 1;
        this.mSize = r02;
        Object[] objArr = this.mPool;
        T t = (T) objArr[r02];
        objArr[r02] = null;
        return t;
    }

    @Override // androidx.core.util.Pools.Pool
    public synchronized boolean release(T t) {
        int r0 = this.mSize;
        Object[] objArr = this.mPool;
        if (r0 == objArr.length) {
            return false;
        }
        objArr[r0] = t;
        this.mSize = r0 + 1;
        return true;
    }

    public synchronized void clear() {
        for (int r1 = 0; r1 < this.mSize; r1++) {
            this.mPool[r1] = null;
        }
        this.mSize = 0;
    }
}
