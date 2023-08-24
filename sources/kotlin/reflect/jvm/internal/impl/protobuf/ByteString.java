package kotlin.reflect.jvm.internal.impl.protobuf;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/* loaded from: classes5.dex */
public abstract class ByteString implements Iterable<Byte> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final ByteString EMPTY = new LiteralByteString(new byte[0]);

    /* loaded from: classes5.dex */
    public interface ByteIterator extends Iterator<Byte> {
        byte nextByte();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void copyToInternal(byte[] bArr, int r2, int r3, int r4);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract int getTreeDepth();

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract boolean isBalanced();

    public abstract boolean isValidUtf8();

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.lang.Iterable
    public abstract Iterator<Byte> iterator();

    public abstract CodedInputStream newCodedInput();

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract int partialHash(int r1, int r2, int r3);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract int partialIsValidUtf8(int r1, int r2, int r3);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract int peekCachedHashCode();

    public abstract int size();

    public abstract String toString(String str) throws UnsupportedEncodingException;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void writeToInternal(OutputStream outputStream, int r2, int r3) throws IOException;

    public boolean isEmpty() {
        return size() == 0;
    }

    public static ByteString copyFrom(byte[] bArr, int r3, int r4) {
        byte[] bArr2 = new byte[r4];
        System.arraycopy(bArr, r3, bArr2, 0, r4);
        return new LiteralByteString(bArr2);
    }

    public static ByteString copyFrom(byte[] bArr) {
        return copyFrom(bArr, 0, bArr.length);
    }

    public static ByteString copyFromUtf8(String str) {
        try {
            return new LiteralByteString(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 not supported?", e);
        }
    }

    public ByteString concat(ByteString byteString) {
        int size = size();
        int size2 = byteString.size();
        if (size + size2 >= 2147483647L) {
            StringBuilder sb = new StringBuilder(53);
            sb.append("ByteString would be too long: ");
            sb.append(size);
            sb.append("+");
            sb.append(size2);
            throw new IllegalArgumentException(sb.toString());
        }
        return RopeByteString.concatenate(this, byteString);
    }

    public static ByteString copyFrom(Iterable<ByteString> iterable) {
        Collection collection;
        if (!(iterable instanceof Collection)) {
            collection = new ArrayList();
            for (ByteString byteString : iterable) {
                collection.add(byteString);
            }
        } else {
            collection = (Collection) iterable;
        }
        if (collection.isEmpty()) {
            return EMPTY;
        }
        return balancedConcat(collection.iterator(), collection.size());
    }

    private static ByteString balancedConcat(Iterator<ByteString> it, int r3) {
        if (r3 == 1) {
            return it.next();
        }
        int r0 = r3 >>> 1;
        return balancedConcat(it, r0).concat(balancedConcat(it, r3 - r0));
    }

    public void copyTo(byte[] bArr, int r5, int r6, int r7) {
        if (r5 < 0) {
            StringBuilder sb = new StringBuilder(30);
            sb.append("Source offset < 0: ");
            sb.append(r5);
            throw new IndexOutOfBoundsException(sb.toString());
        } else if (r6 < 0) {
            StringBuilder sb2 = new StringBuilder(30);
            sb2.append("Target offset < 0: ");
            sb2.append(r6);
            throw new IndexOutOfBoundsException(sb2.toString());
        } else if (r7 < 0) {
            StringBuilder sb3 = new StringBuilder(23);
            sb3.append("Length < 0: ");
            sb3.append(r7);
            throw new IndexOutOfBoundsException(sb3.toString());
        } else {
            int r0 = r5 + r7;
            if (r0 > size()) {
                StringBuilder sb4 = new StringBuilder(34);
                sb4.append("Source end offset < 0: ");
                sb4.append(r0);
                throw new IndexOutOfBoundsException(sb4.toString());
            }
            int r02 = r6 + r7;
            if (r02 <= bArr.length) {
                if (r7 > 0) {
                    copyToInternal(bArr, r5, r6, r7);
                    return;
                }
                return;
            }
            StringBuilder sb5 = new StringBuilder(34);
            sb5.append("Target end offset < 0: ");
            sb5.append(r02);
            throw new IndexOutOfBoundsException(sb5.toString());
        }
    }

    public byte[] toByteArray() {
        int size = size();
        if (size == 0) {
            return Internal.EMPTY_BYTE_ARRAY;
        }
        byte[] bArr = new byte[size];
        copyToInternal(bArr, 0, 0, size);
        return bArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void writeTo(OutputStream outputStream, int r4, int r5) throws IOException {
        if (r4 < 0) {
            StringBuilder sb = new StringBuilder(30);
            sb.append("Source offset < 0: ");
            sb.append(r4);
            throw new IndexOutOfBoundsException(sb.toString());
        } else if (r5 < 0) {
            StringBuilder sb2 = new StringBuilder(23);
            sb2.append("Length < 0: ");
            sb2.append(r5);
            throw new IndexOutOfBoundsException(sb2.toString());
        } else {
            int r0 = r4 + r5;
            if (r0 <= size()) {
                if (r5 > 0) {
                    writeToInternal(outputStream, r4, r5);
                    return;
                }
                return;
            }
            StringBuilder sb3 = new StringBuilder(39);
            sb3.append("Source end offset exceeded: ");
            sb3.append(r0);
            throw new IndexOutOfBoundsException(sb3.toString());
        }
    }

    public String toStringUtf8() {
        try {
            return toString("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 not supported?", e);
        }
    }

    public static Output newOutput() {
        return new Output(128);
    }

    /* loaded from: classes5.dex */
    public static final class Output extends OutputStream {
        private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
        private byte[] buffer;
        private int bufferPos;
        private final ArrayList<ByteString> flushedBuffers;
        private int flushedBuffersTotalBytes;
        private final int initialCapacity;

        Output(int r2) {
            if (r2 < 0) {
                throw new IllegalArgumentException("Buffer size < 0");
            }
            this.initialCapacity = r2;
            this.flushedBuffers = new ArrayList<>();
            this.buffer = new byte[r2];
        }

        @Override // java.io.OutputStream
        public synchronized void write(int r4) {
            if (this.bufferPos == this.buffer.length) {
                flushFullBuffer(1);
            }
            byte[] bArr = this.buffer;
            int r1 = this.bufferPos;
            this.bufferPos = r1 + 1;
            bArr[r1] = (byte) r4;
        }

        @Override // java.io.OutputStream
        public synchronized void write(byte[] bArr, int r5, int r6) {
            byte[] bArr2 = this.buffer;
            int length = bArr2.length;
            int r2 = this.bufferPos;
            if (r6 <= length - r2) {
                System.arraycopy(bArr, r5, bArr2, r2, r6);
                this.bufferPos += r6;
            } else {
                int length2 = bArr2.length - r2;
                System.arraycopy(bArr, r5, bArr2, r2, length2);
                int r62 = r6 - length2;
                flushFullBuffer(r62);
                System.arraycopy(bArr, r5 + length2, this.buffer, 0, r62);
                this.bufferPos = r62;
            }
        }

        public synchronized ByteString toByteString() {
            flushLastBuffer();
            return ByteString.copyFrom(this.flushedBuffers);
        }

        private byte[] copyArray(byte[] bArr, int r4) {
            byte[] bArr2 = new byte[r4];
            System.arraycopy(bArr, 0, bArr2, 0, Math.min(bArr.length, r4));
            return bArr2;
        }

        public synchronized int size() {
            return this.flushedBuffersTotalBytes + this.bufferPos;
        }

        public String toString() {
            return String.format("<ByteString.Output@%s size=%d>", Integer.toHexString(System.identityHashCode(this)), Integer.valueOf(size()));
        }

        private void flushFullBuffer(int r4) {
            this.flushedBuffers.add(new LiteralByteString(this.buffer));
            int length = this.flushedBuffersTotalBytes + this.buffer.length;
            this.flushedBuffersTotalBytes = length;
            this.buffer = new byte[Math.max(this.initialCapacity, Math.max(r4, length >>> 1))];
            this.bufferPos = 0;
        }

        private void flushLastBuffer() {
            int r0 = this.bufferPos;
            byte[] bArr = this.buffer;
            if (r0 >= bArr.length) {
                this.flushedBuffers.add(new LiteralByteString(this.buffer));
                this.buffer = EMPTY_BYTE_ARRAY;
            } else if (r0 > 0) {
                this.flushedBuffers.add(new LiteralByteString(copyArray(bArr, r0)));
            }
            this.flushedBuffersTotalBytes += this.bufferPos;
            this.bufferPos = 0;
        }
    }

    public String toString() {
        return String.format("<ByteString@%s size=%d>", Integer.toHexString(System.identityHashCode(this)), Integer.valueOf(size()));
    }
}
