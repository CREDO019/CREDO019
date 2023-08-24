package com.fasterxml.jackson.core.util;

import java.io.OutputStream;
import java.util.Iterator;
import java.util.LinkedList;

/* loaded from: classes.dex */
public final class ByteArrayBuilder extends OutputStream {
    static final int DEFAULT_BLOCK_ARRAY_SIZE = 40;
    private static final int INITIAL_BLOCK_SIZE = 500;
    private static final int MAX_BLOCK_SIZE = 262144;
    public static final byte[] NO_BYTES = new byte[0];
    private final BufferRecycler _bufferRecycler;
    private byte[] _currBlock;
    private int _currBlockPtr;
    private final LinkedList<byte[]> _pastBlocks;
    private int _pastLen;

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() {
    }

    public ByteArrayBuilder() {
        this((BufferRecycler) null);
    }

    public ByteArrayBuilder(BufferRecycler bufferRecycler) {
        this(bufferRecycler, 500);
    }

    public ByteArrayBuilder(int r2) {
        this(null, r2);
    }

    public ByteArrayBuilder(BufferRecycler bufferRecycler, int r3) {
        this._pastBlocks = new LinkedList<>();
        this._bufferRecycler = bufferRecycler;
        this._currBlock = bufferRecycler == null ? new byte[r3] : bufferRecycler.allocByteBuffer(2);
    }

    public void reset() {
        this._pastLen = 0;
        this._currBlockPtr = 0;
        if (this._pastBlocks.isEmpty()) {
            return;
        }
        this._pastBlocks.clear();
    }

    public void release() {
        byte[] bArr;
        reset();
        BufferRecycler bufferRecycler = this._bufferRecycler;
        if (bufferRecycler == null || (bArr = this._currBlock) == null) {
            return;
        }
        bufferRecycler.releaseByteBuffer(2, bArr);
        this._currBlock = null;
    }

    public void append(int r4) {
        if (this._currBlockPtr >= this._currBlock.length) {
            _allocMore();
        }
        byte[] bArr = this._currBlock;
        int r1 = this._currBlockPtr;
        this._currBlockPtr = r1 + 1;
        bArr[r1] = (byte) r4;
    }

    public void appendTwoBytes(int r5) {
        int r0 = this._currBlockPtr;
        int r1 = r0 + 1;
        byte[] bArr = this._currBlock;
        if (r1 < bArr.length) {
            int r12 = r0 + 1;
            this._currBlockPtr = r12;
            bArr[r0] = (byte) (r5 >> 8);
            this._currBlockPtr = r12 + 1;
            bArr[r12] = (byte) r5;
            return;
        }
        append(r5 >> 8);
        append(r5);
    }

    public void appendThreeBytes(int r5) {
        int r0 = this._currBlockPtr;
        int r1 = r0 + 2;
        byte[] bArr = this._currBlock;
        if (r1 < bArr.length) {
            int r12 = r0 + 1;
            this._currBlockPtr = r12;
            bArr[r0] = (byte) (r5 >> 16);
            int r02 = r12 + 1;
            this._currBlockPtr = r02;
            bArr[r12] = (byte) (r5 >> 8);
            this._currBlockPtr = r02 + 1;
            bArr[r02] = (byte) r5;
            return;
        }
        append(r5 >> 16);
        append(r5 >> 8);
        append(r5);
    }

    public byte[] toByteArray() {
        int r0 = this._pastLen + this._currBlockPtr;
        if (r0 == 0) {
            return NO_BYTES;
        }
        byte[] bArr = new byte[r0];
        Iterator<byte[]> it = this._pastBlocks.iterator();
        int r4 = 0;
        while (it.hasNext()) {
            byte[] next = it.next();
            int length = next.length;
            System.arraycopy(next, 0, bArr, r4, length);
            r4 += length;
        }
        System.arraycopy(this._currBlock, 0, bArr, r4, this._currBlockPtr);
        int r42 = r4 + this._currBlockPtr;
        if (r42 != r0) {
            throw new RuntimeException("Internal error: total len assumed to be " + r0 + ", copied " + r42 + " bytes");
        }
        if (!this._pastBlocks.isEmpty()) {
            reset();
        }
        return bArr;
    }

    public byte[] resetAndGetFirstSegment() {
        reset();
        return this._currBlock;
    }

    public byte[] finishCurrentSegment() {
        _allocMore();
        return this._currBlock;
    }

    public byte[] completeAndCoalesce(int r1) {
        this._currBlockPtr = r1;
        return toByteArray();
    }

    public byte[] getCurrentSegment() {
        return this._currBlock;
    }

    public void setCurrentSegmentLength(int r1) {
        this._currBlockPtr = r1;
    }

    public int getCurrentSegmentLength() {
        return this._currBlockPtr;
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) {
        write(bArr, 0, bArr.length);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int r5, int r6) {
        while (true) {
            int min = Math.min(this._currBlock.length - this._currBlockPtr, r6);
            if (min > 0) {
                System.arraycopy(bArr, r5, this._currBlock, this._currBlockPtr, min);
                r5 += min;
                this._currBlockPtr += min;
                r6 -= min;
            }
            if (r6 <= 0) {
                return;
            }
            _allocMore();
        }
    }

    @Override // java.io.OutputStream
    public void write(int r1) {
        append(r1);
    }

    private void _allocMore() {
        int length = this._pastLen + this._currBlock.length;
        if (length < 0) {
            throw new IllegalStateException("Maximum Java array size (2GB) exceeded by `ByteArrayBuilder`");
        }
        this._pastLen = length;
        int max = Math.max(length >> 1, 1000);
        if (max > 262144) {
            max = 262144;
        }
        this._pastBlocks.add(this._currBlock);
        this._currBlock = new byte[max];
        this._currBlockPtr = 0;
    }
}
