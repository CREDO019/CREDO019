package org.apache.commons.codec.binary;

import java.util.Arrays;
import org.apache.commons.codec.BinaryDecoder;
import org.apache.commons.codec.BinaryEncoder;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;

/* loaded from: classes5.dex */
public abstract class BaseNCodec implements BinaryEncoder, BinaryDecoder {
    private static final int DEFAULT_BUFFER_RESIZE_FACTOR = 2;
    private static final int DEFAULT_BUFFER_SIZE = 8192;
    static final int EOF = -1;
    protected static final int MASK_8BITS = 255;
    public static final int MIME_CHUNK_SIZE = 76;
    protected static final byte PAD_DEFAULT = 61;
    public static final int PEM_CHUNK_SIZE = 64;
    @Deprecated
    protected final byte PAD;
    private final int chunkSeparatorLength;
    private final int encodedBlockSize;
    protected final int lineLength;
    protected final byte pad;
    private final int unencodedBlockSize;

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean isWhiteSpace(byte b) {
        return b == 9 || b == 10 || b == 13 || b == 32;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void decode(byte[] bArr, int r2, int r3, Context context);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void encode(byte[] bArr, int r2, int r3, Context context);

    protected int getDefaultBufferSize() {
        return 8192;
    }

    protected abstract boolean isInAlphabet(byte b);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public static class Context {
        byte[] buffer;
        int currentLinePos;
        boolean eof;
        int ibitWorkArea;
        long lbitWorkArea;
        int modulus;
        int pos;
        int readPos;

        public String toString() {
            return String.format("%s[buffer=%s, currentLinePos=%s, eof=%s, ibitWorkArea=%s, lbitWorkArea=%s, modulus=%s, pos=%s, readPos=%s]", getClass().getSimpleName(), Arrays.toString(this.buffer), Integer.valueOf(this.currentLinePos), Boolean.valueOf(this.eof), Integer.valueOf(this.ibitWorkArea), Long.valueOf(this.lbitWorkArea), Integer.valueOf(this.modulus), Integer.valueOf(this.pos), Integer.valueOf(this.readPos));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BaseNCodec(int r7, int r8, int r9, int r10) {
        this(r7, r8, r9, r10, PAD_DEFAULT);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BaseNCodec(int r2, int r3, int r4, int r5, byte b) {
        this.PAD = PAD_DEFAULT;
        this.unencodedBlockSize = r2;
        this.encodedBlockSize = r3;
        this.lineLength = r4 > 0 && r5 > 0 ? (r4 / r3) * r3 : 0;
        this.chunkSeparatorLength = r5;
        this.pad = b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean hasData(Context context) {
        return context.buffer != null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int available(Context context) {
        if (context.buffer != null) {
            return context.pos - context.readPos;
        }
        return 0;
    }

    private byte[] resizeBuffer(Context context) {
        if (context.buffer == null) {
            context.buffer = new byte[getDefaultBufferSize()];
            context.pos = 0;
            context.readPos = 0;
        } else {
            byte[] bArr = new byte[context.buffer.length * 2];
            System.arraycopy(context.buffer, 0, bArr, 0, context.buffer.length);
            context.buffer = bArr;
        }
        return context.buffer;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public byte[] ensureBufferSize(int r3, Context context) {
        if (context.buffer == null || context.buffer.length < context.pos + r3) {
            return resizeBuffer(context);
        }
        return context.buffer;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int readResults(byte[] bArr, int r4, int r5, Context context) {
        if (context.buffer == null) {
            return context.eof ? -1 : 0;
        }
        int min = Math.min(available(context), r5);
        System.arraycopy(context.buffer, context.readPos, bArr, r4, min);
        context.readPos += min;
        if (context.readPos >= context.pos) {
            context.buffer = null;
        }
        return min;
    }

    @Override // org.apache.commons.codec.Encoder
    public Object encode(Object obj) throws EncoderException {
        if (!(obj instanceof byte[])) {
            throw new EncoderException("Parameter supplied to Base-N encode is not a byte[]");
        }
        return encode((byte[]) obj);
    }

    public String encodeToString(byte[] bArr) {
        return StringUtils.newStringUtf8(encode(bArr));
    }

    public String encodeAsString(byte[] bArr) {
        return StringUtils.newStringUtf8(encode(bArr));
    }

    @Override // org.apache.commons.codec.Decoder
    public Object decode(Object obj) throws DecoderException {
        if (obj instanceof byte[]) {
            return decode((byte[]) obj);
        }
        if (obj instanceof String) {
            return decode((String) obj);
        }
        throw new DecoderException("Parameter supplied to Base-N decode is not a byte[] or a String");
    }

    public byte[] decode(String str) {
        return decode(StringUtils.getBytesUtf8(str));
    }

    @Override // org.apache.commons.codec.BinaryDecoder
    public byte[] decode(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return bArr;
        }
        Context context = new Context();
        decode(bArr, 0, bArr.length, context);
        decode(bArr, 0, -1, context);
        int r4 = context.pos;
        byte[] bArr2 = new byte[r4];
        readResults(bArr2, 0, r4, context);
        return bArr2;
    }

    @Override // org.apache.commons.codec.BinaryEncoder
    public byte[] encode(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return bArr;
        }
        Context context = new Context();
        encode(bArr, 0, bArr.length, context);
        encode(bArr, 0, -1, context);
        int r4 = context.pos - context.readPos;
        byte[] bArr2 = new byte[r4];
        readResults(bArr2, 0, r4, context);
        return bArr2;
    }

    public boolean isInAlphabet(byte[] bArr, boolean z) {
        for (int r1 = 0; r1 < bArr.length; r1++) {
            if (!isInAlphabet(bArr[r1]) && (!z || (bArr[r1] != this.pad && !isWhiteSpace(bArr[r1])))) {
                return false;
            }
        }
        return true;
    }

    public boolean isInAlphabet(String str) {
        return isInAlphabet(StringUtils.getBytesUtf8(str), true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean containsAlphabetOrPad(byte[] bArr) {
        if (bArr == null) {
            return false;
        }
        for (byte b : bArr) {
            if (this.pad == b || isInAlphabet(b)) {
                return true;
            }
        }
        return false;
    }

    public long getEncodedLength(byte[] bArr) {
        int length = bArr.length;
        int r0 = this.unencodedBlockSize;
        long j = (((length + r0) - 1) / r0) * this.encodedBlockSize;
        int r7 = this.lineLength;
        return r7 > 0 ? j + ((((r7 + j) - 1) / r7) * this.chunkSeparatorLength) : j;
    }
}
