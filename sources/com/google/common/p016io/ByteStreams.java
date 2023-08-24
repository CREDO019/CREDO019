package com.google.common.p016io;

import android.support.p001v4.media.session.PlaybackStateCompat;
import com.google.common.base.Preconditions;
import com.google.common.math.IntMath;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
/* renamed from: com.google.common.io.ByteStreams */
/* loaded from: classes3.dex */
public final class ByteStreams {
    private static final int BUFFER_SIZE = 8192;
    private static final int MAX_ARRAY_LEN = 2147483639;
    private static final OutputStream NULL_OUTPUT_STREAM = new OutputStream() { // from class: com.google.common.io.ByteStreams.1
        public String toString() {
            return "ByteStreams.nullOutputStream()";
        }

        @Override // java.io.OutputStream
        public void write(int r1) {
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr) {
            Preconditions.checkNotNull(bArr);
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr, int r2, int r3) {
            Preconditions.checkNotNull(bArr);
        }
    };
    private static final int TO_BYTE_ARRAY_DEQUE_SIZE = 20;
    private static final int ZERO_COPY_CHUNK_SIZE = 524288;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] createBuffer() {
        return new byte[8192];
    }

    private ByteStreams() {
    }

    public static long copy(InputStream inputStream, OutputStream outputStream) throws IOException {
        Preconditions.checkNotNull(inputStream);
        Preconditions.checkNotNull(outputStream);
        byte[] createBuffer = createBuffer();
        long j = 0;
        while (true) {
            int read = inputStream.read(createBuffer);
            if (read == -1) {
                return j;
            }
            outputStream.write(createBuffer, 0, read);
            j += read;
        }
    }

    public static long copy(ReadableByteChannel readableByteChannel, WritableByteChannel writableByteChannel) throws IOException {
        Preconditions.checkNotNull(readableByteChannel);
        Preconditions.checkNotNull(writableByteChannel);
        long j = 0;
        if (readableByteChannel instanceof FileChannel) {
            FileChannel fileChannel = (FileChannel) readableByteChannel;
            long position = fileChannel.position();
            long j2 = position;
            while (true) {
                long transferTo = fileChannel.transferTo(j2, PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED, writableByteChannel);
                j2 += transferTo;
                fileChannel.position(j2);
                if (transferTo <= 0 && j2 >= fileChannel.size()) {
                    return j2 - position;
                }
            }
        } else {
            ByteBuffer wrap = ByteBuffer.wrap(createBuffer());
            while (readableByteChannel.read(wrap) != -1) {
                Java8Compatibility.flip(wrap);
                while (wrap.hasRemaining()) {
                    j += writableByteChannel.write(wrap);
                }
                Java8Compatibility.clear(wrap);
            }
            return j;
        }
    }

    private static byte[] toByteArrayInternal(InputStream inputStream, Queue<byte[]> queue, int r8) throws IOException {
        int r0 = 8192;
        while (r8 < MAX_ARRAY_LEN) {
            int min = Math.min(r0, MAX_ARRAY_LEN - r8);
            byte[] bArr = new byte[min];
            queue.add(bArr);
            int r4 = 0;
            while (r4 < min) {
                int read = inputStream.read(bArr, r4, min - r4);
                if (read == -1) {
                    return combineBuffers(queue, r8);
                }
                r4 += read;
                r8 += read;
            }
            r0 = IntMath.saturatedMultiply(r0, 2);
        }
        if (inputStream.read() == -1) {
            return combineBuffers(queue, MAX_ARRAY_LEN);
        }
        throw new OutOfMemoryError("input is too large to fit in a byte array");
    }

    private static byte[] combineBuffers(Queue<byte[]> queue, int r7) {
        byte[] bArr = new byte[r7];
        int r1 = r7;
        while (r1 > 0) {
            byte[] remove = queue.remove();
            int min = Math.min(r1, remove.length);
            System.arraycopy(remove, 0, bArr, r7 - r1, min);
            r1 -= min;
        }
        return bArr;
    }

    public static byte[] toByteArray(InputStream inputStream) throws IOException {
        Preconditions.checkNotNull(inputStream);
        return toByteArrayInternal(inputStream, new ArrayDeque(20), 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] toByteArray(InputStream inputStream, long j) throws IOException {
        Preconditions.checkArgument(j >= 0, "expectedSize (%s) must be non-negative", j);
        if (j > 2147483639) {
            StringBuilder sb = new StringBuilder(62);
            sb.append(j);
            sb.append(" bytes is too large to fit in a byte array");
            throw new OutOfMemoryError(sb.toString());
        }
        int r8 = (int) j;
        byte[] bArr = new byte[r8];
        int r2 = r8;
        while (r2 > 0) {
            int r4 = r8 - r2;
            int read = inputStream.read(bArr, r4, r2);
            if (read == -1) {
                return Arrays.copyOf(bArr, r4);
            }
            r2 -= read;
        }
        int read2 = inputStream.read();
        if (read2 == -1) {
            return bArr;
        }
        ArrayDeque arrayDeque = new ArrayDeque(22);
        arrayDeque.add(bArr);
        arrayDeque.add(new byte[]{(byte) read2});
        return toByteArrayInternal(inputStream, arrayDeque, r8 + 1);
    }

    public static long exhaust(InputStream inputStream) throws IOException {
        byte[] createBuffer = createBuffer();
        long j = 0;
        while (true) {
            long read = inputStream.read(createBuffer);
            if (read == -1) {
                return j;
            }
            j += read;
        }
    }

    public static ByteArrayDataInput newDataInput(byte[] bArr) {
        return newDataInput(new ByteArrayInputStream(bArr));
    }

    public static ByteArrayDataInput newDataInput(byte[] bArr, int r3) {
        Preconditions.checkPositionIndex(r3, bArr.length);
        return newDataInput(new ByteArrayInputStream(bArr, r3, bArr.length - r3));
    }

    public static ByteArrayDataInput newDataInput(ByteArrayInputStream byteArrayInputStream) {
        return new ByteArrayDataInputStream((ByteArrayInputStream) Preconditions.checkNotNull(byteArrayInputStream));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.google.common.io.ByteStreams$ByteArrayDataInputStream */
    /* loaded from: classes3.dex */
    public static class ByteArrayDataInputStream implements ByteArrayDataInput {
        final DataInput input;

        ByteArrayDataInputStream(ByteArrayInputStream byteArrayInputStream) {
            this.input = new DataInputStream(byteArrayInputStream);
        }

        @Override // com.google.common.p016io.ByteArrayDataInput, java.io.DataInput
        public void readFully(byte[] bArr) {
            try {
                this.input.readFully(bArr);
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        @Override // com.google.common.p016io.ByteArrayDataInput, java.io.DataInput
        public void readFully(byte[] bArr, int r3, int r4) {
            try {
                this.input.readFully(bArr, r3, r4);
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        @Override // com.google.common.p016io.ByteArrayDataInput, java.io.DataInput
        public int skipBytes(int r2) {
            try {
                return this.input.skipBytes(r2);
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        @Override // com.google.common.p016io.ByteArrayDataInput, java.io.DataInput
        public boolean readBoolean() {
            try {
                return this.input.readBoolean();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        @Override // com.google.common.p016io.ByteArrayDataInput, java.io.DataInput
        public byte readByte() {
            try {
                return this.input.readByte();
            } catch (EOFException e) {
                throw new IllegalStateException(e);
            } catch (IOException e2) {
                throw new AssertionError(e2);
            }
        }

        @Override // com.google.common.p016io.ByteArrayDataInput, java.io.DataInput
        public int readUnsignedByte() {
            try {
                return this.input.readUnsignedByte();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        @Override // com.google.common.p016io.ByteArrayDataInput, java.io.DataInput
        public short readShort() {
            try {
                return this.input.readShort();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        @Override // com.google.common.p016io.ByteArrayDataInput, java.io.DataInput
        public int readUnsignedShort() {
            try {
                return this.input.readUnsignedShort();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        @Override // com.google.common.p016io.ByteArrayDataInput, java.io.DataInput
        public char readChar() {
            try {
                return this.input.readChar();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        @Override // com.google.common.p016io.ByteArrayDataInput, java.io.DataInput
        public int readInt() {
            try {
                return this.input.readInt();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        @Override // com.google.common.p016io.ByteArrayDataInput, java.io.DataInput
        public long readLong() {
            try {
                return this.input.readLong();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        @Override // com.google.common.p016io.ByteArrayDataInput, java.io.DataInput
        public float readFloat() {
            try {
                return this.input.readFloat();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        @Override // com.google.common.p016io.ByteArrayDataInput, java.io.DataInput
        public double readDouble() {
            try {
                return this.input.readDouble();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        @Override // com.google.common.p016io.ByteArrayDataInput, java.io.DataInput
        @CheckForNull
        public String readLine() {
            try {
                return this.input.readLine();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        @Override // com.google.common.p016io.ByteArrayDataInput, java.io.DataInput
        public String readUTF() {
            try {
                return this.input.readUTF();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    public static ByteArrayDataOutput newDataOutput() {
        return newDataOutput(new ByteArrayOutputStream());
    }

    public static ByteArrayDataOutput newDataOutput(int r3) {
        if (r3 < 0) {
            throw new IllegalArgumentException(String.format("Invalid size: %s", Integer.valueOf(r3)));
        }
        return newDataOutput(new ByteArrayOutputStream(r3));
    }

    public static ByteArrayDataOutput newDataOutput(ByteArrayOutputStream byteArrayOutputStream) {
        return new ByteArrayDataOutputStream((ByteArrayOutputStream) Preconditions.checkNotNull(byteArrayOutputStream));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.google.common.io.ByteStreams$ByteArrayDataOutputStream */
    /* loaded from: classes3.dex */
    public static class ByteArrayDataOutputStream implements ByteArrayDataOutput {
        final ByteArrayOutputStream byteArrayOutputStream;
        final DataOutput output;

        ByteArrayDataOutputStream(ByteArrayOutputStream byteArrayOutputStream) {
            this.byteArrayOutputStream = byteArrayOutputStream;
            this.output = new DataOutputStream(byteArrayOutputStream);
        }

        @Override // com.google.common.p016io.ByteArrayDataOutput, java.io.DataOutput
        public void write(int r2) {
            try {
                this.output.write(r2);
            } catch (IOException e) {
                throw new AssertionError(e);
            }
        }

        @Override // com.google.common.p016io.ByteArrayDataOutput, java.io.DataOutput
        public void write(byte[] bArr) {
            try {
                this.output.write(bArr);
            } catch (IOException e) {
                throw new AssertionError(e);
            }
        }

        @Override // com.google.common.p016io.ByteArrayDataOutput, java.io.DataOutput
        public void write(byte[] bArr, int r3, int r4) {
            try {
                this.output.write(bArr, r3, r4);
            } catch (IOException e) {
                throw new AssertionError(e);
            }
        }

        @Override // com.google.common.p016io.ByteArrayDataOutput, java.io.DataOutput
        public void writeBoolean(boolean z) {
            try {
                this.output.writeBoolean(z);
            } catch (IOException e) {
                throw new AssertionError(e);
            }
        }

        @Override // com.google.common.p016io.ByteArrayDataOutput, java.io.DataOutput
        public void writeByte(int r2) {
            try {
                this.output.writeByte(r2);
            } catch (IOException e) {
                throw new AssertionError(e);
            }
        }

        @Override // com.google.common.p016io.ByteArrayDataOutput, java.io.DataOutput
        public void writeBytes(String str) {
            try {
                this.output.writeBytes(str);
            } catch (IOException e) {
                throw new AssertionError(e);
            }
        }

        @Override // com.google.common.p016io.ByteArrayDataOutput, java.io.DataOutput
        public void writeChar(int r2) {
            try {
                this.output.writeChar(r2);
            } catch (IOException e) {
                throw new AssertionError(e);
            }
        }

        @Override // com.google.common.p016io.ByteArrayDataOutput, java.io.DataOutput
        public void writeChars(String str) {
            try {
                this.output.writeChars(str);
            } catch (IOException e) {
                throw new AssertionError(e);
            }
        }

        @Override // com.google.common.p016io.ByteArrayDataOutput, java.io.DataOutput
        public void writeDouble(double d) {
            try {
                this.output.writeDouble(d);
            } catch (IOException e) {
                throw new AssertionError(e);
            }
        }

        @Override // com.google.common.p016io.ByteArrayDataOutput, java.io.DataOutput
        public void writeFloat(float f) {
            try {
                this.output.writeFloat(f);
            } catch (IOException e) {
                throw new AssertionError(e);
            }
        }

        @Override // com.google.common.p016io.ByteArrayDataOutput, java.io.DataOutput
        public void writeInt(int r2) {
            try {
                this.output.writeInt(r2);
            } catch (IOException e) {
                throw new AssertionError(e);
            }
        }

        @Override // com.google.common.p016io.ByteArrayDataOutput, java.io.DataOutput
        public void writeLong(long j) {
            try {
                this.output.writeLong(j);
            } catch (IOException e) {
                throw new AssertionError(e);
            }
        }

        @Override // com.google.common.p016io.ByteArrayDataOutput, java.io.DataOutput
        public void writeShort(int r2) {
            try {
                this.output.writeShort(r2);
            } catch (IOException e) {
                throw new AssertionError(e);
            }
        }

        @Override // com.google.common.p016io.ByteArrayDataOutput, java.io.DataOutput
        public void writeUTF(String str) {
            try {
                this.output.writeUTF(str);
            } catch (IOException e) {
                throw new AssertionError(e);
            }
        }

        @Override // com.google.common.p016io.ByteArrayDataOutput
        public byte[] toByteArray() {
            return this.byteArrayOutputStream.toByteArray();
        }
    }

    public static OutputStream nullOutputStream() {
        return NULL_OUTPUT_STREAM;
    }

    public static InputStream limit(InputStream inputStream, long j) {
        return new LimitedInputStream(inputStream, j);
    }

    /* renamed from: com.google.common.io.ByteStreams$LimitedInputStream */
    /* loaded from: classes3.dex */
    private static final class LimitedInputStream extends FilterInputStream {
        private long left;
        private long mark;

        LimitedInputStream(InputStream inputStream, long j) {
            super(inputStream);
            this.mark = -1L;
            Preconditions.checkNotNull(inputStream);
            Preconditions.checkArgument(j >= 0, "limit must be non-negative");
            this.left = j;
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public int available() throws IOException {
            return (int) Math.min(this.in.available(), this.left);
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public synchronized void mark(int r3) {
            this.in.mark(r3);
            this.mark = this.left;
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public int read() throws IOException {
            if (this.left == 0) {
                return -1;
            }
            int read = this.in.read();
            if (read != -1) {
                this.left--;
            }
            return read;
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public int read(byte[] bArr, int r8, int r9) throws IOException {
            long j = this.left;
            if (j == 0) {
                return -1;
            }
            int read = this.in.read(bArr, r8, (int) Math.min(r9, j));
            if (read != -1) {
                this.left -= read;
            }
            return read;
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public synchronized void reset() throws IOException {
            if (!this.in.markSupported()) {
                throw new IOException("Mark not supported");
            }
            if (this.mark == -1) {
                throw new IOException("Mark not set");
            }
            this.in.reset();
            this.left = this.mark;
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public long skip(long j) throws IOException {
            long skip = this.in.skip(Math.min(j, this.left));
            this.left -= skip;
            return skip;
        }
    }

    public static void readFully(InputStream inputStream, byte[] bArr) throws IOException {
        readFully(inputStream, bArr, 0, bArr.length);
    }

    public static void readFully(InputStream inputStream, byte[] bArr, int r3, int r4) throws IOException {
        int read = read(inputStream, bArr, r3, r4);
        if (read == r4) {
            return;
        }
        StringBuilder sb = new StringBuilder(81);
        sb.append("reached end of stream after reading ");
        sb.append(read);
        sb.append(" bytes; ");
        sb.append(r4);
        sb.append(" bytes expected");
        throw new EOFException(sb.toString());
    }

    public static void skipFully(InputStream inputStream, long j) throws IOException {
        long skipUpTo = skipUpTo(inputStream, j);
        if (skipUpTo >= j) {
            return;
        }
        StringBuilder sb = new StringBuilder(100);
        sb.append("reached end of stream after skipping ");
        sb.append(skipUpTo);
        sb.append(" bytes; ");
        sb.append(j);
        sb.append(" bytes expected");
        throw new EOFException(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long skipUpTo(InputStream inputStream, long j) throws IOException {
        byte[] bArr = null;
        long j2 = 0;
        while (j2 < j) {
            long j3 = j - j2;
            long skipSafely = skipSafely(inputStream, j3);
            if (skipSafely == 0) {
                int min = (int) Math.min(j3, (long) PlaybackStateCompat.ACTION_PLAY_FROM_URI);
                if (bArr == null) {
                    bArr = new byte[min];
                }
                skipSafely = inputStream.read(bArr, 0, min);
                if (skipSafely == -1) {
                    break;
                }
            }
            j2 += skipSafely;
        }
        return j2;
    }

    private static long skipSafely(InputStream inputStream, long j) throws IOException {
        int available = inputStream.available();
        if (available == 0) {
            return 0L;
        }
        return inputStream.skip(Math.min(available, j));
    }

    @ParametricNullness
    public static <T> T readBytes(InputStream inputStream, ByteProcessor<T> byteProcessor) throws IOException {
        int read;
        Preconditions.checkNotNull(inputStream);
        Preconditions.checkNotNull(byteProcessor);
        byte[] createBuffer = createBuffer();
        do {
            read = inputStream.read(createBuffer);
            if (read == -1) {
                break;
            }
        } while (byteProcessor.processBytes(createBuffer, 0, read));
        return byteProcessor.getResult();
    }

    public static int read(InputStream inputStream, byte[] bArr, int r5, int r6) throws IOException {
        Preconditions.checkNotNull(inputStream);
        Preconditions.checkNotNull(bArr);
        int r0 = 0;
        if (r6 < 0) {
            throw new IndexOutOfBoundsException(String.format("len (%s) cannot be negative", Integer.valueOf(r6)));
        }
        Preconditions.checkPositionIndexes(r5, r5 + r6, bArr.length);
        while (r0 < r6) {
            int read = inputStream.read(bArr, r5 + r0, r6 - r0);
            if (read == -1) {
                break;
            }
            r0 += read;
        }
        return r0;
    }
}
