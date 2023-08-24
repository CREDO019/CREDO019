package kotlin.reflect.jvm.internal.impl.protobuf;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

/* loaded from: classes5.dex */
public final class CodedOutputStream {
    private final byte[] buffer;
    private final int limit;
    private final OutputStream output;
    private int totalBytesWritten = 0;
    private int position = 0;

    public static int computeBoolSizeNoTag(boolean z) {
        return 1;
    }

    public static int computeDoubleSizeNoTag(double d) {
        return 8;
    }

    public static int computeFixed32SizeNoTag(int r0) {
        return 4;
    }

    public static int computeFixed64SizeNoTag(long j) {
        return 8;
    }

    public static int computeFloatSizeNoTag(float f) {
        return 4;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int computePreferredBufferSize(int r1) {
        if (r1 > 4096) {
            return 4096;
        }
        return r1;
    }

    public static int computeRawVarint32Size(int r1) {
        if ((r1 & (-128)) == 0) {
            return 1;
        }
        if ((r1 & (-16384)) == 0) {
            return 2;
        }
        if (((-2097152) & r1) == 0) {
            return 3;
        }
        return (r1 & (-268435456)) == 0 ? 4 : 5;
    }

    public static int computeRawVarint64Size(long j) {
        if (((-128) & j) == 0) {
            return 1;
        }
        if (((-16384) & j) == 0) {
            return 2;
        }
        if (((-2097152) & j) == 0) {
            return 3;
        }
        if (((-268435456) & j) == 0) {
            return 4;
        }
        if (((-34359738368L) & j) == 0) {
            return 5;
        }
        if (((-4398046511104L) & j) == 0) {
            return 6;
        }
        if (((-562949953421312L) & j) == 0) {
            return 7;
        }
        if (((-72057594037927936L) & j) == 0) {
            return 8;
        }
        return (j & Long.MIN_VALUE) == 0 ? 9 : 10;
    }

    public static int computeSFixed32SizeNoTag(int r0) {
        return 4;
    }

    public static int computeSFixed64SizeNoTag(long j) {
        return 8;
    }

    public static int encodeZigZag32(int r1) {
        return (r1 >> 31) ^ (r1 << 1);
    }

    public static long encodeZigZag64(long j) {
        return (j >> 63) ^ (j << 1);
    }

    private CodedOutputStream(OutputStream outputStream, byte[] bArr) {
        this.output = outputStream;
        this.buffer = bArr;
        this.limit = bArr.length;
    }

    public static CodedOutputStream newInstance(OutputStream outputStream, int r2) {
        return new CodedOutputStream(outputStream, new byte[r2]);
    }

    public void writeDouble(int r2, double d) throws IOException {
        writeTag(r2, 1);
        writeDoubleNoTag(d);
    }

    public void writeFloat(int r2, float f) throws IOException {
        writeTag(r2, 5);
        writeFloatNoTag(f);
    }

    public void writeInt32(int r2, int r3) throws IOException {
        writeTag(r2, 0);
        writeInt32NoTag(r3);
    }

    public void writeBool(int r2, boolean z) throws IOException {
        writeTag(r2, 0);
        writeBoolNoTag(z);
    }

    public void writeGroup(int r2, MessageLite messageLite) throws IOException {
        writeTag(r2, 3);
        writeGroupNoTag(messageLite);
        writeTag(r2, 4);
    }

    public void writeMessage(int r2, MessageLite messageLite) throws IOException {
        writeTag(r2, 2);
        writeMessageNoTag(messageLite);
    }

    public void writeBytes(int r2, ByteString byteString) throws IOException {
        writeTag(r2, 2);
        writeBytesNoTag(byteString);
    }

    public void writeUInt32(int r2, int r3) throws IOException {
        writeTag(r2, 0);
        writeUInt32NoTag(r3);
    }

    public void writeEnum(int r2, int r3) throws IOException {
        writeTag(r2, 0);
        writeEnumNoTag(r3);
    }

    public void writeSInt64(int r2, long j) throws IOException {
        writeTag(r2, 0);
        writeSInt64NoTag(j);
    }

    public void writeMessageSetExtension(int r4, MessageLite messageLite) throws IOException {
        writeTag(1, 3);
        writeUInt32(2, r4);
        writeMessage(3, messageLite);
        writeTag(1, 4);
    }

    public void writeDoubleNoTag(double d) throws IOException {
        writeRawLittleEndian64(Double.doubleToRawLongBits(d));
    }

    public void writeFloatNoTag(float f) throws IOException {
        writeRawLittleEndian32(Float.floatToRawIntBits(f));
    }

    public void writeUInt64NoTag(long j) throws IOException {
        writeRawVarint64(j);
    }

    public void writeInt64NoTag(long j) throws IOException {
        writeRawVarint64(j);
    }

    public void writeInt32NoTag(int r3) throws IOException {
        if (r3 >= 0) {
            writeRawVarint32(r3);
        } else {
            writeRawVarint64(r3);
        }
    }

    public void writeFixed64NoTag(long j) throws IOException {
        writeRawLittleEndian64(j);
    }

    public void writeFixed32NoTag(int r1) throws IOException {
        writeRawLittleEndian32(r1);
    }

    public void writeBoolNoTag(boolean z) throws IOException {
        writeRawByte(z ? 1 : 0);
    }

    public void writeStringNoTag(String str) throws IOException {
        byte[] bytes = str.getBytes("UTF-8");
        writeRawVarint32(bytes.length);
        writeRawBytes(bytes);
    }

    public void writeGroupNoTag(MessageLite messageLite) throws IOException {
        messageLite.writeTo(this);
    }

    public void writeMessageNoTag(MessageLite messageLite) throws IOException {
        writeRawVarint32(messageLite.getSerializedSize());
        messageLite.writeTo(this);
    }

    public void writeBytesNoTag(ByteString byteString) throws IOException {
        writeRawVarint32(byteString.size());
        writeRawBytes(byteString);
    }

    public void writeByteArrayNoTag(byte[] bArr) throws IOException {
        writeRawVarint32(bArr.length);
        writeRawBytes(bArr);
    }

    public void writeUInt32NoTag(int r1) throws IOException {
        writeRawVarint32(r1);
    }

    public void writeEnumNoTag(int r1) throws IOException {
        writeInt32NoTag(r1);
    }

    public void writeSFixed32NoTag(int r1) throws IOException {
        writeRawLittleEndian32(r1);
    }

    public void writeSFixed64NoTag(long j) throws IOException {
        writeRawLittleEndian64(j);
    }

    public void writeSInt32NoTag(int r1) throws IOException {
        writeRawVarint32(encodeZigZag32(r1));
    }

    public void writeSInt64NoTag(long j) throws IOException {
        writeRawVarint64(encodeZigZag64(j));
    }

    public static int computeDoubleSize(int r0, double d) {
        return computeTagSize(r0) + computeDoubleSizeNoTag(d);
    }

    public static int computeFloatSize(int r0, float f) {
        return computeTagSize(r0) + computeFloatSizeNoTag(f);
    }

    public static int computeInt32Size(int r0, int r1) {
        return computeTagSize(r0) + computeInt32SizeNoTag(r1);
    }

    public static int computeBoolSize(int r0, boolean z) {
        return computeTagSize(r0) + computeBoolSizeNoTag(z);
    }

    public static int computeMessageSize(int r0, MessageLite messageLite) {
        return computeTagSize(r0) + computeMessageSizeNoTag(messageLite);
    }

    public static int computeBytesSize(int r0, ByteString byteString) {
        return computeTagSize(r0) + computeBytesSizeNoTag(byteString);
    }

    public static int computeEnumSize(int r0, int r1) {
        return computeTagSize(r0) + computeEnumSizeNoTag(r1);
    }

    public static int computeSInt64Size(int r0, long j) {
        return computeTagSize(r0) + computeSInt64SizeNoTag(j);
    }

    public static int computeUInt64SizeNoTag(long j) {
        return computeRawVarint64Size(j);
    }

    public static int computeInt64SizeNoTag(long j) {
        return computeRawVarint64Size(j);
    }

    public static int computeInt32SizeNoTag(int r0) {
        if (r0 >= 0) {
            return computeRawVarint32Size(r0);
        }
        return 10;
    }

    public static int computeStringSizeNoTag(String str) {
        try {
            byte[] bytes = str.getBytes("UTF-8");
            return computeRawVarint32Size(bytes.length) + bytes.length;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 not supported.", e);
        }
    }

    public static int computeGroupSizeNoTag(MessageLite messageLite) {
        return messageLite.getSerializedSize();
    }

    public static int computeMessageSizeNoTag(MessageLite messageLite) {
        int serializedSize = messageLite.getSerializedSize();
        return computeRawVarint32Size(serializedSize) + serializedSize;
    }

    public static int computeLazyFieldSizeNoTag(LazyFieldLite lazyFieldLite) {
        int serializedSize = lazyFieldLite.getSerializedSize();
        return computeRawVarint32Size(serializedSize) + serializedSize;
    }

    public static int computeBytesSizeNoTag(ByteString byteString) {
        return computeRawVarint32Size(byteString.size()) + byteString.size();
    }

    public static int computeByteArraySizeNoTag(byte[] bArr) {
        return computeRawVarint32Size(bArr.length) + bArr.length;
    }

    public static int computeUInt32SizeNoTag(int r0) {
        return computeRawVarint32Size(r0);
    }

    public static int computeEnumSizeNoTag(int r0) {
        return computeInt32SizeNoTag(r0);
    }

    public static int computeSInt32SizeNoTag(int r0) {
        return computeRawVarint32Size(encodeZigZag32(r0));
    }

    public static int computeSInt64SizeNoTag(long j) {
        return computeRawVarint64Size(encodeZigZag64(j));
    }

    private void refreshBuffer() throws IOException {
        OutputStream outputStream = this.output;
        if (outputStream == null) {
            throw new OutOfSpaceException();
        }
        outputStream.write(this.buffer, 0, this.position);
        this.position = 0;
    }

    public void flush() throws IOException {
        if (this.output != null) {
            refreshBuffer();
        }
    }

    /* loaded from: classes5.dex */
    public static class OutOfSpaceException extends IOException {
        OutOfSpaceException() {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.");
        }
    }

    public void writeRawByte(byte b) throws IOException {
        if (this.position == this.limit) {
            refreshBuffer();
        }
        byte[] bArr = this.buffer;
        int r1 = this.position;
        this.position = r1 + 1;
        bArr[r1] = b;
        this.totalBytesWritten++;
    }

    public void writeRawByte(int r1) throws IOException {
        writeRawByte((byte) r1);
    }

    public void writeRawBytes(ByteString byteString) throws IOException {
        writeRawBytes(byteString, 0, byteString.size());
    }

    public void writeRawBytes(byte[] bArr) throws IOException {
        writeRawBytes(bArr, 0, bArr.length);
    }

    public void writeRawBytes(byte[] bArr, int r5, int r6) throws IOException {
        int r0 = this.limit;
        int r1 = this.position;
        if (r0 - r1 >= r6) {
            System.arraycopy(bArr, r5, this.buffer, r1, r6);
            this.position += r6;
            this.totalBytesWritten += r6;
            return;
        }
        int r02 = r0 - r1;
        System.arraycopy(bArr, r5, this.buffer, r1, r02);
        int r52 = r5 + r02;
        int r62 = r6 - r02;
        this.position = this.limit;
        this.totalBytesWritten += r02;
        refreshBuffer();
        if (r62 <= this.limit) {
            System.arraycopy(bArr, r52, this.buffer, 0, r62);
            this.position = r62;
        } else {
            this.output.write(bArr, r52, r62);
        }
        this.totalBytesWritten += r62;
    }

    public void writeRawBytes(ByteString byteString, int r5, int r6) throws IOException {
        int r0 = this.limit;
        int r1 = this.position;
        if (r0 - r1 >= r6) {
            byteString.copyTo(this.buffer, r5, r1, r6);
            this.position += r6;
            this.totalBytesWritten += r6;
            return;
        }
        int r02 = r0 - r1;
        byteString.copyTo(this.buffer, r5, r1, r02);
        int r52 = r5 + r02;
        int r62 = r6 - r02;
        this.position = this.limit;
        this.totalBytesWritten += r02;
        refreshBuffer();
        if (r62 <= this.limit) {
            byteString.copyTo(this.buffer, r52, 0, r62);
            this.position = r62;
        } else {
            byteString.writeTo(this.output, r52, r62);
        }
        this.totalBytesWritten += r62;
    }

    public void writeTag(int r1, int r2) throws IOException {
        writeRawVarint32(WireFormat.makeTag(r1, r2));
    }

    public static int computeTagSize(int r1) {
        return computeRawVarint32Size(WireFormat.makeTag(r1, 0));
    }

    public void writeRawVarint32(int r2) throws IOException {
        while ((r2 & (-128)) != 0) {
            writeRawByte((r2 & 127) | 128);
            r2 >>>= 7;
        }
        writeRawByte(r2);
    }

    public void writeRawVarint64(long j) throws IOException {
        while (((-128) & j) != 0) {
            writeRawByte((((int) j) & 127) | 128);
            j >>>= 7;
        }
        writeRawByte((int) j);
    }

    public void writeRawLittleEndian32(int r2) throws IOException {
        writeRawByte(r2 & 255);
        writeRawByte((r2 >> 8) & 255);
        writeRawByte((r2 >> 16) & 255);
        writeRawByte((r2 >> 24) & 255);
    }

    public void writeRawLittleEndian64(long j) throws IOException {
        writeRawByte(((int) j) & 255);
        writeRawByte(((int) (j >> 8)) & 255);
        writeRawByte(((int) (j >> 16)) & 255);
        writeRawByte(((int) (j >> 24)) & 255);
        writeRawByte(((int) (j >> 32)) & 255);
        writeRawByte(((int) (j >> 40)) & 255);
        writeRawByte(((int) (j >> 48)) & 255);
        writeRawByte(((int) (j >> 56)) & 255);
    }
}
