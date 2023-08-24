package com.fasterxml.jackson.databind.util;

import java.lang.reflect.Array;
import java.util.List;

/* loaded from: classes2.dex */
public final class ObjectBuffer {
    private static final int MAX_CHUNK = 262144;
    private static final int SMALL_CHUNK = 16384;
    private Object[] _freeBuffer;
    private LinkedNode<Object[]> _head;
    private int _size;
    private LinkedNode<Object[]> _tail;

    public Object[] resetAndStart() {
        _reset();
        Object[] objArr = this._freeBuffer;
        return objArr == null ? new Object[12] : objArr;
    }

    public Object[] appendCompletedChunk(Object[] objArr) {
        LinkedNode<Object[]> linkedNode = new LinkedNode<>(objArr, null);
        if (this._head == null) {
            this._tail = linkedNode;
            this._head = linkedNode;
        } else {
            this._tail.linkNext(linkedNode);
            this._tail = linkedNode;
        }
        int length = objArr.length;
        this._size += length;
        if (length < 16384) {
            length += length;
        } else if (length < 262144) {
            length += length >> 2;
        }
        return new Object[length];
    }

    public Object[] completeAndClearBuffer(Object[] objArr, int r4) {
        int r0 = this._size + r4;
        Object[] objArr2 = new Object[r0];
        _copyTo(objArr2, r0, objArr, r4);
        return objArr2;
    }

    public <T> T[] completeAndClearBuffer(Object[] objArr, int r3, Class<T> cls) {
        int r0 = this._size + r3;
        T[] tArr = (T[]) ((Object[]) Array.newInstance((Class<?>) cls, r0));
        _copyTo(tArr, r0, objArr, r3);
        _reset();
        return tArr;
    }

    public void completeAndClearBuffer(Object[] objArr, int r7, List<Object> list) {
        int r1;
        LinkedNode<Object[]> linkedNode = this._head;
        while (true) {
            r1 = 0;
            if (linkedNode == null) {
                break;
            }
            Object[] value = linkedNode.value();
            int length = value.length;
            while (r1 < length) {
                list.add(value[r1]);
                r1++;
            }
            linkedNode = linkedNode.next();
        }
        while (r1 < r7) {
            list.add(objArr[r1]);
            r1++;
        }
    }

    public int initialCapacity() {
        Object[] objArr = this._freeBuffer;
        if (objArr == null) {
            return 0;
        }
        return objArr.length;
    }

    public int bufferedSize() {
        return this._size;
    }

    protected void _reset() {
        LinkedNode<Object[]> linkedNode = this._tail;
        if (linkedNode != null) {
            this._freeBuffer = linkedNode.value();
        }
        this._tail = null;
        this._head = null;
        this._size = 0;
    }

    protected final void _copyTo(Object obj, int r7, Object[] objArr, int r9) {
        int r2 = 0;
        for (LinkedNode<Object[]> linkedNode = this._head; linkedNode != null; linkedNode = linkedNode.next()) {
            Object[] value = linkedNode.value();
            int length = value.length;
            System.arraycopy(value, 0, obj, r2, length);
            r2 += length;
        }
        System.arraycopy(objArr, 0, obj, r2, r9);
        int r22 = r2 + r9;
        if (r22 == r7) {
            return;
        }
        throw new IllegalStateException("Should have gotten " + r7 + " entries, got " + r22);
    }
}
