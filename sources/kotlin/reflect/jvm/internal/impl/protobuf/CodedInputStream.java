package kotlin.reflect.jvm.internal.impl.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import kotlin.reflect.jvm.internal.impl.protobuf.MessageLite;

/* loaded from: classes5.dex */
public final class CodedInputStream {
    private final byte[] buffer;
    private final boolean bufferIsImmutable;
    private int bufferPos;
    private int bufferSize;
    private int bufferSizeAfterLimit;
    private int currentLimit;
    private boolean enableAliasing;
    private final InputStream input;
    private int lastTag;
    private int recursionDepth;
    private int recursionLimit;
    private RefillCallback refillCallback;
    private int sizeLimit;
    private int totalBytesRetired;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public interface RefillCallback {
        void onRefill();
    }

    public static int decodeZigZag32(int r1) {
        return (-(r1 & 1)) ^ (r1 >>> 1);
    }

    public static long decodeZigZag64(long j) {
        return (-(j & 1)) ^ (j >>> 1);
    }

    public static CodedInputStream newInstance(InputStream inputStream) {
        return new CodedInputStream(inputStream);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static CodedInputStream newInstance(LiteralByteString literalByteString) {
        CodedInputStream codedInputStream = new CodedInputStream(literalByteString);
        try {
            codedInputStream.pushLimit(literalByteString.size());
            return codedInputStream;
        } catch (InvalidProtocolBufferException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public int readTag() throws IOException {
        if (isAtEnd()) {
            this.lastTag = 0;
            return 0;
        }
        int readRawVarint32 = readRawVarint32();
        this.lastTag = readRawVarint32;
        if (WireFormat.getTagFieldNumber(readRawVarint32) == 0) {
            throw InvalidProtocolBufferException.invalidTag();
        }
        return this.lastTag;
    }

    public void checkLastTagWas(int r2) throws InvalidProtocolBufferException {
        if (this.lastTag != r2) {
            throw InvalidProtocolBufferException.invalidEndTag();
        }
    }

    public boolean skipField(int r5, CodedOutputStream codedOutputStream) throws IOException {
        int tagWireType = WireFormat.getTagWireType(r5);
        if (tagWireType == 0) {
            long readInt64 = readInt64();
            codedOutputStream.writeRawVarint32(r5);
            codedOutputStream.writeUInt64NoTag(readInt64);
            return true;
        } else if (tagWireType == 1) {
            long readRawLittleEndian64 = readRawLittleEndian64();
            codedOutputStream.writeRawVarint32(r5);
            codedOutputStream.writeFixed64NoTag(readRawLittleEndian64);
            return true;
        } else if (tagWireType == 2) {
            ByteString readBytes = readBytes();
            codedOutputStream.writeRawVarint32(r5);
            codedOutputStream.writeBytesNoTag(readBytes);
            return true;
        } else if (tagWireType == 3) {
            codedOutputStream.writeRawVarint32(r5);
            skipMessage(codedOutputStream);
            int makeTag = WireFormat.makeTag(WireFormat.getTagFieldNumber(r5), 4);
            checkLastTagWas(makeTag);
            codedOutputStream.writeRawVarint32(makeTag);
            return true;
        } else if (tagWireType != 4) {
            if (tagWireType == 5) {
                int readRawLittleEndian32 = readRawLittleEndian32();
                codedOutputStream.writeRawVarint32(r5);
                codedOutputStream.writeFixed32NoTag(readRawLittleEndian32);
                return true;
            }
            throw InvalidProtocolBufferException.invalidWireType();
        } else {
            return false;
        }
    }

    public void skipMessage(CodedOutputStream codedOutputStream) throws IOException {
        int readTag;
        do {
            readTag = readTag();
            if (readTag == 0) {
                return;
            }
        } while (skipField(readTag, codedOutputStream));
    }

    public double readDouble() throws IOException {
        return Double.longBitsToDouble(readRawLittleEndian64());
    }

    public float readFloat() throws IOException {
        return Float.intBitsToFloat(readRawLittleEndian32());
    }

    public long readUInt64() throws IOException {
        return readRawVarint64();
    }

    public long readInt64() throws IOException {
        return readRawVarint64();
    }

    public int readInt32() throws IOException {
        return readRawVarint32();
    }

    public long readFixed64() throws IOException {
        return readRawLittleEndian64();
    }

    public int readFixed32() throws IOException {
        return readRawLittleEndian32();
    }

    public boolean readBool() throws IOException {
        return readRawVarint64() != 0;
    }

    public String readString() throws IOException {
        int readRawVarint32 = readRawVarint32();
        int r1 = this.bufferSize;
        int r2 = this.bufferPos;
        if (readRawVarint32 > r1 - r2 || readRawVarint32 <= 0) {
            return readRawVarint32 == 0 ? "" : new String(readRawBytesSlowPath(readRawVarint32), "UTF-8");
        }
        String str = new String(this.buffer, r2, readRawVarint32, "UTF-8");
        this.bufferPos += readRawVarint32;
        return str;
    }

    public String readStringRequireUtf8() throws IOException {
        byte[] readRawBytesSlowPath;
        int readRawVarint32 = readRawVarint32();
        int r1 = this.bufferPos;
        if (readRawVarint32 <= this.bufferSize - r1 && readRawVarint32 > 0) {
            readRawBytesSlowPath = this.buffer;
            this.bufferPos = r1 + readRawVarint32;
        } else if (readRawVarint32 == 0) {
            return "";
        } else {
            readRawBytesSlowPath = readRawBytesSlowPath(readRawVarint32);
            r1 = 0;
        }
        if (!Utf8.isValidUtf8(readRawBytesSlowPath, r1, r1 + readRawVarint32)) {
            throw InvalidProtocolBufferException.invalidUtf8();
        }
        return new String(readRawBytesSlowPath, r1, readRawVarint32, "UTF-8");
    }

    public void readGroup(int r3, MessageLite.Builder builder, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        int r0 = this.recursionDepth;
        if (r0 >= this.recursionLimit) {
            throw InvalidProtocolBufferException.recursionLimitExceeded();
        }
        this.recursionDepth = r0 + 1;
        builder.mergeFrom(this, extensionRegistryLite);
        checkLastTagWas(WireFormat.makeTag(r3, 4));
        this.recursionDepth--;
    }

    public void readMessage(MessageLite.Builder builder, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        int readRawVarint32 = readRawVarint32();
        if (this.recursionDepth >= this.recursionLimit) {
            throw InvalidProtocolBufferException.recursionLimitExceeded();
        }
        int pushLimit = pushLimit(readRawVarint32);
        this.recursionDepth++;
        builder.mergeFrom(this, extensionRegistryLite);
        checkLastTagWas(0);
        this.recursionDepth--;
        popLimit(pushLimit);
    }

    public <T extends MessageLite> T readMessage(Parser<T> parser, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        int readRawVarint32 = readRawVarint32();
        if (this.recursionDepth >= this.recursionLimit) {
            throw InvalidProtocolBufferException.recursionLimitExceeded();
        }
        int pushLimit = pushLimit(readRawVarint32);
        this.recursionDepth++;
        T parsePartialFrom = parser.parsePartialFrom(this, extensionRegistryLite);
        checkLastTagWas(0);
        this.recursionDepth--;
        popLimit(pushLimit);
        return parsePartialFrom;
    }

    public ByteString readBytes() throws IOException {
        int readRawVarint32 = readRawVarint32();
        int r1 = this.bufferSize;
        int r2 = this.bufferPos;
        if (readRawVarint32 > r1 - r2 || readRawVarint32 <= 0) {
            if (readRawVarint32 == 0) {
                return ByteString.EMPTY;
            }
            return new LiteralByteString(readRawBytesSlowPath(readRawVarint32));
        }
        ByteString boundedByteString = (this.bufferIsImmutable && this.enableAliasing) ? new BoundedByteString(this.buffer, this.bufferPos, readRawVarint32) : ByteString.copyFrom(this.buffer, r2, readRawVarint32);
        this.bufferPos += readRawVarint32;
        return boundedByteString;
    }

    public int readUInt32() throws IOException {
        return readRawVarint32();
    }

    public int readEnum() throws IOException {
        return readRawVarint32();
    }

    public int readSFixed32() throws IOException {
        return readRawLittleEndian32();
    }

    public long readSFixed64() throws IOException {
        return readRawLittleEndian64();
    }

    public int readSInt32() throws IOException {
        return decodeZigZag32(readRawVarint32());
    }

    public long readSInt64() throws IOException {
        return decodeZigZag64(readRawVarint64());
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x007a, code lost:
        if (r2[r3] < 0) goto L35;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int readRawVarint32() throws java.io.IOException {
        /*
            r9 = this;
            int r0 = r9.bufferPos
            int r1 = r9.bufferSize
            if (r1 != r0) goto L8
            goto L7c
        L8:
            byte[] r2 = r9.buffer
            int r3 = r0 + 1
            r0 = r2[r0]
            if (r0 < 0) goto L13
            r9.bufferPos = r3
            return r0
        L13:
            int r1 = r1 - r3
            r4 = 9
            if (r1 >= r4) goto L19
            goto L7c
        L19:
            int r1 = r3 + 1
            r3 = r2[r3]
            int r3 = r3 << 7
            r0 = r0 ^ r3
            long r3 = (long) r0
            r5 = 0
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 >= 0) goto L2d
            r5 = -128(0xffffffffffffff80, double:NaN)
        L29:
            long r2 = r3 ^ r5
            int r0 = (int) r2
            goto L82
        L2d:
            int r3 = r1 + 1
            r1 = r2[r1]
            int r1 = r1 << 14
            r0 = r0 ^ r1
            long r7 = (long) r0
            int r1 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
            if (r1 < 0) goto L3f
            r0 = 16256(0x3f80, double:8.0315E-320)
            long r0 = r0 ^ r7
            int r0 = (int) r0
        L3d:
            r1 = r3
            goto L82
        L3f:
            int r1 = r3 + 1
            r3 = r2[r3]
            int r3 = r3 << 21
            r0 = r0 ^ r3
            long r3 = (long) r0
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 >= 0) goto L4f
            r5 = -2080896(0xffffffffffe03f80, double:NaN)
            goto L29
        L4f:
            int r3 = r1 + 1
            r1 = r2[r1]
            int r4 = r1 << 28
            r0 = r0 ^ r4
            long r4 = (long) r0
            r6 = 266354560(0xfe03f80, double:1.315966377E-315)
            long r4 = r4 ^ r6
            int r0 = (int) r4
            if (r1 >= 0) goto L3d
            int r1 = r3 + 1
            r3 = r2[r3]
            if (r3 >= 0) goto L82
            int r3 = r1 + 1
            r1 = r2[r1]
            if (r1 >= 0) goto L3d
            int r1 = r3 + 1
            r3 = r2[r3]
            if (r3 >= 0) goto L82
            int r3 = r1 + 1
            r1 = r2[r1]
            if (r1 >= 0) goto L3d
            int r1 = r3 + 1
            r2 = r2[r3]
            if (r2 >= 0) goto L82
        L7c:
            long r0 = r9.readRawVarint64SlowPath()
            int r1 = (int) r0
            return r1
        L82:
            r9.bufferPos = r1
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.protobuf.CodedInputStream.readRawVarint32():int");
    }

    public static int readRawVarint32(int r3, InputStream inputStream) throws IOException {
        if ((r3 & 128) == 0) {
            return r3;
        }
        int r32 = r3 & 127;
        int r0 = 7;
        while (r0 < 32) {
            int read = inputStream.read();
            if (read == -1) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            r32 |= (read & 127) << r0;
            if ((read & 128) == 0) {
                return r32;
            }
            r0 += 7;
        }
        while (r0 < 64) {
            int read2 = inputStream.read();
            if (read2 == -1) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            if ((read2 & 128) == 0) {
                return r32;
            }
            r0 += 7;
        }
        throw InvalidProtocolBufferException.malformedVarint();
    }

    /* JADX WARN: Code restructure failed: missing block: B:38:0x00b6, code lost:
        if (r2[r0] < 0) goto L41;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public long readRawVarint64() throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 194
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.protobuf.CodedInputStream.readRawVarint64():long");
    }

    long readRawVarint64SlowPath() throws IOException {
        long j = 0;
        for (int r2 = 0; r2 < 64; r2 += 7) {
            byte readRawByte = readRawByte();
            j |= (readRawByte & Byte.MAX_VALUE) << r2;
            if ((readRawByte & 128) == 0) {
                return j;
            }
        }
        throw InvalidProtocolBufferException.malformedVarint();
    }

    public int readRawLittleEndian32() throws IOException {
        int r0 = this.bufferPos;
        if (this.bufferSize - r0 < 4) {
            refillBuffer(4);
            r0 = this.bufferPos;
        }
        byte[] bArr = this.buffer;
        this.bufferPos = r0 + 4;
        return ((bArr[r0 + 3] & 255) << 24) | (bArr[r0] & 255) | ((bArr[r0 + 1] & 255) << 8) | ((bArr[r0 + 2] & 255) << 16);
    }

    public long readRawLittleEndian64() throws IOException {
        int r0 = this.bufferPos;
        if (this.bufferSize - r0 < 8) {
            refillBuffer(8);
            r0 = this.bufferPos;
        }
        byte[] bArr = this.buffer;
        this.bufferPos = r0 + 8;
        return ((bArr[r0 + 7] & 255) << 56) | (bArr[r0] & 255) | ((bArr[r0 + 1] & 255) << 8) | ((bArr[r0 + 2] & 255) << 16) | ((bArr[r0 + 3] & 255) << 24) | ((bArr[r0 + 4] & 255) << 32) | ((bArr[r0 + 5] & 255) << 40) | ((bArr[r0 + 6] & 255) << 48);
    }

    private CodedInputStream(InputStream inputStream) {
        this.enableAliasing = false;
        this.currentLimit = Integer.MAX_VALUE;
        this.recursionLimit = 64;
        this.sizeLimit = 67108864;
        this.refillCallback = null;
        this.buffer = new byte[4096];
        this.bufferSize = 0;
        this.bufferPos = 0;
        this.totalBytesRetired = 0;
        this.input = inputStream;
        this.bufferIsImmutable = false;
    }

    private CodedInputStream(LiteralByteString literalByteString) {
        this.enableAliasing = false;
        this.currentLimit = Integer.MAX_VALUE;
        this.recursionLimit = 64;
        this.sizeLimit = 67108864;
        this.refillCallback = null;
        this.buffer = literalByteString.bytes;
        int offsetIntoBytes = literalByteString.getOffsetIntoBytes();
        this.bufferPos = offsetIntoBytes;
        this.bufferSize = offsetIntoBytes + literalByteString.size();
        this.totalBytesRetired = -this.bufferPos;
        this.input = null;
        this.bufferIsImmutable = true;
    }

    public int pushLimit(int r3) throws InvalidProtocolBufferException {
        if (r3 < 0) {
            throw InvalidProtocolBufferException.negativeSize();
        }
        int r32 = r3 + this.totalBytesRetired + this.bufferPos;
        int r0 = this.currentLimit;
        if (r32 > r0) {
            throw InvalidProtocolBufferException.truncatedMessage();
        }
        this.currentLimit = r32;
        recomputeBufferSizeAfterLimit();
        return r0;
    }

    private void recomputeBufferSizeAfterLimit() {
        int r0 = this.bufferSize + this.bufferSizeAfterLimit;
        this.bufferSize = r0;
        int r1 = this.totalBytesRetired + r0;
        int r2 = this.currentLimit;
        if (r1 > r2) {
            int r12 = r1 - r2;
            this.bufferSizeAfterLimit = r12;
            this.bufferSize = r0 - r12;
            return;
        }
        this.bufferSizeAfterLimit = 0;
    }

    public void popLimit(int r1) {
        this.currentLimit = r1;
        recomputeBufferSizeAfterLimit();
    }

    public int getBytesUntilLimit() {
        int r0 = this.currentLimit;
        if (r0 == Integer.MAX_VALUE) {
            return -1;
        }
        return r0 - (this.totalBytesRetired + this.bufferPos);
    }

    public boolean isAtEnd() throws IOException {
        return this.bufferPos == this.bufferSize && !tryRefillBuffer(1);
    }

    private void ensureAvailable(int r3) throws IOException {
        if (this.bufferSize - this.bufferPos < r3) {
            refillBuffer(r3);
        }
    }

    private void refillBuffer(int r1) throws IOException {
        if (!tryRefillBuffer(r1)) {
            throw InvalidProtocolBufferException.truncatedMessage();
        }
    }

    private boolean tryRefillBuffer(int r6) throws IOException {
        int r0 = this.bufferPos;
        if (r0 + r6 <= this.bufferSize) {
            StringBuilder sb = new StringBuilder(77);
            sb.append("refillBuffer() called when ");
            sb.append(r6);
            sb.append(" bytes were already available in buffer");
            throw new IllegalStateException(sb.toString());
        } else if (this.totalBytesRetired + r0 + r6 > this.currentLimit) {
            return false;
        } else {
            RefillCallback refillCallback = this.refillCallback;
            if (refillCallback != null) {
                refillCallback.onRefill();
            }
            if (this.input != null) {
                int r02 = this.bufferPos;
                if (r02 > 0) {
                    int r1 = this.bufferSize;
                    if (r1 > r02) {
                        byte[] bArr = this.buffer;
                        System.arraycopy(bArr, r02, bArr, 0, r1 - r02);
                    }
                    this.totalBytesRetired += r02;
                    this.bufferSize -= r02;
                    this.bufferPos = 0;
                }
                InputStream inputStream = this.input;
                byte[] bArr2 = this.buffer;
                int r3 = this.bufferSize;
                int read = inputStream.read(bArr2, r3, bArr2.length - r3);
                if (read == 0 || read < -1 || read > this.buffer.length) {
                    StringBuilder sb2 = new StringBuilder(102);
                    sb2.append("InputStream#read(byte[]) returned invalid result: ");
                    sb2.append(read);
                    sb2.append("\nThe InputStream implementation is buggy.");
                    throw new IllegalStateException(sb2.toString());
                } else if (read > 0) {
                    this.bufferSize += read;
                    if ((this.totalBytesRetired + r6) - this.sizeLimit > 0) {
                        throw InvalidProtocolBufferException.sizeLimitExceeded();
                    }
                    recomputeBufferSizeAfterLimit();
                    if (this.bufferSize >= r6) {
                        return true;
                    }
                    return tryRefillBuffer(r6);
                }
            }
            return false;
        }
    }

    public byte readRawByte() throws IOException {
        if (this.bufferPos == this.bufferSize) {
            refillBuffer(1);
        }
        byte[] bArr = this.buffer;
        int r1 = this.bufferPos;
        this.bufferPos = r1 + 1;
        return bArr[r1];
    }

    private byte[] readRawBytesSlowPath(int r13) throws IOException {
        if (r13 <= 0) {
            if (r13 == 0) {
                return Internal.EMPTY_BYTE_ARRAY;
            }
            throw InvalidProtocolBufferException.negativeSize();
        }
        int r0 = this.totalBytesRetired;
        int r1 = this.bufferPos;
        int r2 = r0 + r1 + r13;
        int r3 = this.currentLimit;
        if (r2 > r3) {
            skipRawBytes((r3 - r0) - r1);
            throw InvalidProtocolBufferException.truncatedMessage();
        } else if (r13 < 4096) {
            byte[] bArr = new byte[r13];
            int r22 = this.bufferSize - r1;
            System.arraycopy(this.buffer, r1, bArr, 0, r22);
            this.bufferPos = this.bufferSize;
            int r132 = r13 - r22;
            ensureAvailable(r132);
            System.arraycopy(this.buffer, 0, bArr, r22, r132);
            this.bufferPos = r132;
            return bArr;
        } else {
            int r4 = this.bufferSize;
            this.totalBytesRetired = r0 + r4;
            this.bufferPos = 0;
            this.bufferSize = 0;
            int r42 = r4 - r1;
            int r02 = r13 - r42;
            ArrayList<byte[]> arrayList = new ArrayList();
            while (r02 > 0) {
                int min = Math.min(r02, 4096);
                byte[] bArr2 = new byte[min];
                int r8 = 0;
                while (r8 < min) {
                    InputStream inputStream = this.input;
                    int read = inputStream == null ? -1 : inputStream.read(bArr2, r8, min - r8);
                    if (read == -1) {
                        throw InvalidProtocolBufferException.truncatedMessage();
                    }
                    this.totalBytesRetired += read;
                    r8 += read;
                }
                r02 -= min;
                arrayList.add(bArr2);
            }
            byte[] bArr3 = new byte[r13];
            System.arraycopy(this.buffer, r1, bArr3, 0, r42);
            for (byte[] bArr4 : arrayList) {
                System.arraycopy(bArr4, 0, bArr3, r42, bArr4.length);
                r42 += bArr4.length;
            }
            return bArr3;
        }
    }

    public void skipRawBytes(int r3) throws IOException {
        int r0 = this.bufferSize;
        int r1 = this.bufferPos;
        if (r3 <= r0 - r1 && r3 >= 0) {
            this.bufferPos = r1 + r3;
        } else {
            skipRawBytesSlowPath(r3);
        }
    }

    private void skipRawBytesSlowPath(int r5) throws IOException {
        if (r5 < 0) {
            throw InvalidProtocolBufferException.negativeSize();
        }
        int r0 = this.totalBytesRetired;
        int r1 = this.bufferPos;
        int r2 = r0 + r1 + r5;
        int r3 = this.currentLimit;
        if (r2 > r3) {
            skipRawBytes((r3 - r0) - r1);
            throw InvalidProtocolBufferException.truncatedMessage();
        }
        int r02 = this.bufferSize;
        int r12 = r02 - r1;
        this.bufferPos = r02;
        refillBuffer(1);
        while (true) {
            int r22 = r5 - r12;
            int r32 = this.bufferSize;
            if (r22 > r32) {
                r12 += r32;
                this.bufferPos = r32;
                refillBuffer(1);
            } else {
                this.bufferPos = r22;
                return;
            }
        }
    }
}
