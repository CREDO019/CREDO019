package com.fasterxml.jackson.databind.util;

/* loaded from: classes2.dex */
public abstract class PrimitiveArrayBuilder<T> {
    static final int INITIAL_CHUNK_SIZE = 12;
    static final int MAX_CHUNK_SIZE = 262144;
    static final int SMALL_CHUNK_SIZE = 16384;
    protected Node<T> _bufferHead;
    protected Node<T> _bufferTail;
    protected int _bufferedEntryCount;
    protected T _freeBuffer;

    protected abstract T _constructArray(int r1);

    public int bufferedSize() {
        return this._bufferedEntryCount;
    }

    public T resetAndStart() {
        _reset();
        T t = this._freeBuffer;
        return t == null ? _constructArray(12) : t;
    }

    public final T appendCompletedChunk(T t, int r3) {
        Node<T> node = new Node<>(t, r3);
        if (this._bufferHead == null) {
            this._bufferTail = node;
            this._bufferHead = node;
        } else {
            this._bufferTail.linkNext(node);
            this._bufferTail = node;
        }
        this._bufferedEntryCount += r3;
        return _constructArray(r3 < 16384 ? r3 + r3 : r3 + (r3 >> 2));
    }

    public T completeAndClearBuffer(T t, int r7) {
        int r0 = this._bufferedEntryCount + r7;
        T _constructArray = _constructArray(r0);
        int r4 = 0;
        for (Node<T> node = this._bufferHead; node != null; node = node.next()) {
            r4 = node.copyData(_constructArray, r4);
        }
        System.arraycopy(t, 0, _constructArray, r4, r7);
        int r42 = r4 + r7;
        if (r42 == r0) {
            return _constructArray;
        }
        throw new IllegalStateException("Should have gotten " + r0 + " entries, got " + r42);
    }

    protected void _reset() {
        Node<T> node = this._bufferTail;
        if (node != null) {
            this._freeBuffer = node.getData();
        }
        this._bufferTail = null;
        this._bufferHead = null;
        this._bufferedEntryCount = 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class Node<T> {
        final T _data;
        final int _dataLength;
        Node<T> _next;

        public Node(T t, int r2) {
            this._data = t;
            this._dataLength = r2;
        }

        public T getData() {
            return this._data;
        }

        public int copyData(T t, int r5) {
            System.arraycopy(this._data, 0, t, r5, this._dataLength);
            return r5 + this._dataLength;
        }

        public Node<T> next() {
            return this._next;
        }

        public void linkNext(Node<T> node) {
            if (this._next != null) {
                throw new IllegalStateException();
            }
            this._next = node;
        }
    }
}
