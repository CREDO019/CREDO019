package com.fasterxml.jackson.core.json;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.p009io.CharTypes;
import com.fasterxml.jackson.core.p009io.CharacterEscapes;
import com.fasterxml.jackson.core.p009io.IOContext;
import com.fasterxml.jackson.core.p009io.NumberOutput;
import com.google.android.exoplayer2.extractor.p011ts.PsExtractor;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;

/* loaded from: classes.dex */
public class UTF8JsonGenerator extends JsonGeneratorImpl {
    private static final byte BYTE_0 = 48;
    private static final byte BYTE_BACKSLASH = 92;
    private static final byte BYTE_COLON = 58;
    private static final byte BYTE_COMMA = 44;
    private static final byte BYTE_LBRACKET = 91;
    private static final byte BYTE_LCURLY = 123;
    private static final byte BYTE_RBRACKET = 93;
    private static final byte BYTE_RCURLY = 125;
    private static final int MAX_BYTES_TO_BUFFER = 512;
    protected boolean _bufferRecyclable;
    protected char[] _charBuffer;
    protected final int _charBufferLength;
    protected byte[] _entityBuffer;
    protected byte[] _outputBuffer;
    protected final int _outputEnd;
    protected final int _outputMaxContiguous;
    protected final OutputStream _outputStream;
    protected int _outputTail;
    protected byte _quoteChar;
    private static final byte[] HEX_CHARS = CharTypes.copyHexBytes();
    private static final byte BYTE_u = 117;
    private static final byte[] NULL_BYTES = {110, BYTE_u, 108, 108};
    private static final byte[] TRUE_BYTES = {116, 114, BYTE_u, 101};
    private static final byte[] FALSE_BYTES = {102, 97, 108, 115, 101};

    public UTF8JsonGenerator(IOContext iOContext, int r2, ObjectCodec objectCodec, OutputStream outputStream) {
        super(iOContext, r2, objectCodec);
        this._quoteChar = (byte) 34;
        this._outputStream = outputStream;
        this._bufferRecyclable = true;
        byte[] allocWriteEncodingBuffer = iOContext.allocWriteEncodingBuffer();
        this._outputBuffer = allocWriteEncodingBuffer;
        int length = allocWriteEncodingBuffer.length;
        this._outputEnd = length;
        this._outputMaxContiguous = length >> 3;
        char[] allocConcatBuffer = iOContext.allocConcatBuffer();
        this._charBuffer = allocConcatBuffer;
        this._charBufferLength = allocConcatBuffer.length;
        if (isEnabled(JsonGenerator.Feature.ESCAPE_NON_ASCII)) {
            setHighestNonEscapedChar(127);
        }
    }

    public UTF8JsonGenerator(IOContext iOContext, int r2, ObjectCodec objectCodec, OutputStream outputStream, byte[] bArr, int r6, boolean z) {
        super(iOContext, r2, objectCodec);
        this._quoteChar = (byte) 34;
        this._outputStream = outputStream;
        this._bufferRecyclable = z;
        this._outputTail = r6;
        this._outputBuffer = bArr;
        int length = bArr.length;
        this._outputEnd = length;
        this._outputMaxContiguous = length >> 3;
        char[] allocConcatBuffer = iOContext.allocConcatBuffer();
        this._charBuffer = allocConcatBuffer;
        this._charBufferLength = allocConcatBuffer.length;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public Object getOutputTarget() {
        return this._outputStream;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public int getOutputBuffered() {
        return this._outputTail;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeFieldName(String str) throws IOException {
        if (this._cfgPrettyPrinter != null) {
            _writePPFieldName(str);
            return;
        }
        int writeFieldName = this._writeContext.writeFieldName(str);
        if (writeFieldName == 4) {
            _reportError("Can not write a field name, expecting a value");
        }
        if (writeFieldName == 1) {
            if (this._outputTail >= this._outputEnd) {
                _flushBuffer();
            }
            byte[] bArr = this._outputBuffer;
            int r2 = this._outputTail;
            this._outputTail = r2 + 1;
            bArr[r2] = BYTE_COMMA;
        }
        if (this._cfgUnqNames) {
            _writeStringSegments(str, false);
            return;
        }
        int length = str.length();
        if (length > this._charBufferLength) {
            _writeStringSegments(str, true);
            return;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr2 = this._outputBuffer;
        int r3 = this._outputTail;
        int r4 = r3 + 1;
        this._outputTail = r4;
        bArr2[r3] = this._quoteChar;
        if (length <= this._outputMaxContiguous) {
            if (r4 + length > this._outputEnd) {
                _flushBuffer();
            }
            _writeStringSegment(str, 0, length);
        } else {
            _writeStringSegments(str, 0, length);
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr3 = this._outputBuffer;
        int r0 = this._outputTail;
        this._outputTail = r0 + 1;
        bArr3[r0] = this._quoteChar;
    }

    @Override // com.fasterxml.jackson.core.base.GeneratorBase, com.fasterxml.jackson.core.JsonGenerator
    public void writeFieldName(SerializableString serializableString) throws IOException {
        if (this._cfgPrettyPrinter != null) {
            _writePPFieldName(serializableString);
            return;
        }
        int writeFieldName = this._writeContext.writeFieldName(serializableString.getValue());
        if (writeFieldName == 4) {
            _reportError("Can not write a field name, expecting a value");
        }
        if (writeFieldName == 1) {
            if (this._outputTail >= this._outputEnd) {
                _flushBuffer();
            }
            byte[] bArr = this._outputBuffer;
            int r1 = this._outputTail;
            this._outputTail = r1 + 1;
            bArr[r1] = BYTE_COMMA;
        }
        if (this._cfgUnqNames) {
            _writeUnq(serializableString);
            return;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr2 = this._outputBuffer;
        int r12 = this._outputTail;
        int r2 = r12 + 1;
        this._outputTail = r2;
        bArr2[r12] = this._quoteChar;
        int appendQuotedUTF8 = serializableString.appendQuotedUTF8(bArr2, r2);
        if (appendQuotedUTF8 < 0) {
            _writeBytes(serializableString.asQuotedUTF8());
        } else {
            this._outputTail += appendQuotedUTF8;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr3 = this._outputBuffer;
        int r0 = this._outputTail;
        this._outputTail = r0 + 1;
        bArr3[r0] = this._quoteChar;
    }

    private final void _writeUnq(SerializableString serializableString) throws IOException {
        int appendQuotedUTF8 = serializableString.appendQuotedUTF8(this._outputBuffer, this._outputTail);
        if (appendQuotedUTF8 < 0) {
            _writeBytes(serializableString.asQuotedUTF8());
        } else {
            this._outputTail += appendQuotedUTF8;
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public final void writeStartArray() throws IOException {
        _verifyValueWrite("start an array");
        this._writeContext = this._writeContext.createChildArrayContext();
        if (this._cfgPrettyPrinter != null) {
            this._cfgPrettyPrinter.writeStartArray(this);
            return;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int r1 = this._outputTail;
        this._outputTail = r1 + 1;
        bArr[r1] = BYTE_LBRACKET;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public final void writeEndArray() throws IOException {
        if (!this._writeContext.inArray()) {
            _reportError("Current context not Array but " + this._writeContext.typeDesc());
        }
        if (this._cfgPrettyPrinter != null) {
            this._cfgPrettyPrinter.writeEndArray(this, this._writeContext.getEntryCount());
        } else {
            if (this._outputTail >= this._outputEnd) {
                _flushBuffer();
            }
            byte[] bArr = this._outputBuffer;
            int r1 = this._outputTail;
            this._outputTail = r1 + 1;
            bArr[r1] = BYTE_RBRACKET;
        }
        this._writeContext = this._writeContext.clearAndGetParent();
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public final void writeStartObject() throws IOException {
        _verifyValueWrite("start an object");
        this._writeContext = this._writeContext.createChildObjectContext();
        if (this._cfgPrettyPrinter != null) {
            this._cfgPrettyPrinter.writeStartObject(this);
            return;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int r1 = this._outputTail;
        this._outputTail = r1 + 1;
        bArr[r1] = BYTE_LCURLY;
    }

    @Override // com.fasterxml.jackson.core.base.GeneratorBase, com.fasterxml.jackson.core.JsonGenerator
    public void writeStartObject(Object obj) throws IOException {
        _verifyValueWrite("start an object");
        JsonWriteContext createChildObjectContext = this._writeContext.createChildObjectContext();
        this._writeContext = createChildObjectContext;
        if (obj != null) {
            createChildObjectContext.setCurrentValue(obj);
        }
        if (this._cfgPrettyPrinter != null) {
            this._cfgPrettyPrinter.writeStartObject(this);
            return;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int r0 = this._outputTail;
        this._outputTail = r0 + 1;
        bArr[r0] = BYTE_LCURLY;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public final void writeEndObject() throws IOException {
        if (!this._writeContext.inObject()) {
            _reportError("Current context not Object but " + this._writeContext.typeDesc());
        }
        if (this._cfgPrettyPrinter != null) {
            this._cfgPrettyPrinter.writeEndObject(this, this._writeContext.getEntryCount());
        } else {
            if (this._outputTail >= this._outputEnd) {
                _flushBuffer();
            }
            byte[] bArr = this._outputBuffer;
            int r1 = this._outputTail;
            this._outputTail = r1 + 1;
            bArr[r1] = BYTE_RCURLY;
        }
        this._writeContext = this._writeContext.clearAndGetParent();
    }

    protected final void _writePPFieldName(String str) throws IOException {
        int writeFieldName = this._writeContext.writeFieldName(str);
        if (writeFieldName == 4) {
            _reportError("Can not write a field name, expecting a value");
        }
        if (writeFieldName == 1) {
            this._cfgPrettyPrinter.writeObjectEntrySeparator(this);
        } else {
            this._cfgPrettyPrinter.beforeObjectEntries(this);
        }
        if (this._cfgUnqNames) {
            _writeStringSegments(str, false);
            return;
        }
        int length = str.length();
        if (length > this._charBufferLength) {
            _writeStringSegments(str, true);
            return;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int r3 = this._outputTail;
        this._outputTail = r3 + 1;
        bArr[r3] = this._quoteChar;
        str.getChars(0, length, this._charBuffer, 0);
        if (length <= this._outputMaxContiguous) {
            if (this._outputTail + length > this._outputEnd) {
                _flushBuffer();
            }
            _writeStringSegment(this._charBuffer, 0, length);
        } else {
            _writeStringSegments(this._charBuffer, 0, length);
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr2 = this._outputBuffer;
        int r0 = this._outputTail;
        this._outputTail = r0 + 1;
        bArr2[r0] = this._quoteChar;
    }

    protected final void _writePPFieldName(SerializableString serializableString) throws IOException {
        int writeFieldName = this._writeContext.writeFieldName(serializableString.getValue());
        if (writeFieldName == 4) {
            _reportError("Can not write a field name, expecting a value");
        }
        if (writeFieldName == 1) {
            this._cfgPrettyPrinter.writeObjectEntrySeparator(this);
        } else {
            this._cfgPrettyPrinter.beforeObjectEntries(this);
        }
        boolean z = !this._cfgUnqNames;
        if (z) {
            if (this._outputTail >= this._outputEnd) {
                _flushBuffer();
            }
            byte[] bArr = this._outputBuffer;
            int r2 = this._outputTail;
            this._outputTail = r2 + 1;
            bArr[r2] = this._quoteChar;
        }
        _writeBytes(serializableString.asQuotedUTF8());
        if (z) {
            if (this._outputTail >= this._outputEnd) {
                _flushBuffer();
            }
            byte[] bArr2 = this._outputBuffer;
            int r0 = this._outputTail;
            this._outputTail = r0 + 1;
            bArr2[r0] = this._quoteChar;
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeString(String str) throws IOException {
        _verifyValueWrite("write a string");
        if (str == null) {
            _writeNull();
            return;
        }
        int length = str.length();
        if (length > this._outputMaxContiguous) {
            _writeStringSegments(str, true);
            return;
        }
        if (this._outputTail + length >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int r2 = this._outputTail;
        this._outputTail = r2 + 1;
        bArr[r2] = this._quoteChar;
        _writeStringSegment(str, 0, length);
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr2 = this._outputBuffer;
        int r0 = this._outputTail;
        this._outputTail = r0 + 1;
        bArr2[r0] = this._quoteChar;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeString(char[] cArr, int r6, int r7) throws IOException {
        _verifyValueWrite("write a string");
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int r1 = this._outputTail;
        int r2 = r1 + 1;
        this._outputTail = r2;
        bArr[r1] = this._quoteChar;
        if (r7 <= this._outputMaxContiguous) {
            if (r2 + r7 > this._outputEnd) {
                _flushBuffer();
            }
            _writeStringSegment(cArr, r6, r7);
        } else {
            _writeStringSegments(cArr, r6, r7);
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr2 = this._outputBuffer;
        int r62 = this._outputTail;
        this._outputTail = r62 + 1;
        bArr2[r62] = this._quoteChar;
    }

    @Override // com.fasterxml.jackson.core.base.GeneratorBase, com.fasterxml.jackson.core.JsonGenerator
    public final void writeString(SerializableString serializableString) throws IOException {
        _verifyValueWrite("write a string");
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int r1 = this._outputTail;
        int r2 = r1 + 1;
        this._outputTail = r2;
        bArr[r1] = this._quoteChar;
        int appendQuotedUTF8 = serializableString.appendQuotedUTF8(bArr, r2);
        if (appendQuotedUTF8 < 0) {
            _writeBytes(serializableString.asQuotedUTF8());
        } else {
            this._outputTail += appendQuotedUTF8;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr2 = this._outputBuffer;
        int r0 = this._outputTail;
        this._outputTail = r0 + 1;
        bArr2[r0] = this._quoteChar;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeRawUTF8String(byte[] bArr, int r5, int r6) throws IOException {
        _verifyValueWrite("write a string");
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr2 = this._outputBuffer;
        int r1 = this._outputTail;
        this._outputTail = r1 + 1;
        bArr2[r1] = this._quoteChar;
        _writeBytes(bArr, r5, r6);
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr3 = this._outputBuffer;
        int r52 = this._outputTail;
        this._outputTail = r52 + 1;
        bArr3[r52] = this._quoteChar;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeUTF8String(byte[] bArr, int r5, int r6) throws IOException {
        _verifyValueWrite("write a string");
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr2 = this._outputBuffer;
        int r1 = this._outputTail;
        this._outputTail = r1 + 1;
        bArr2[r1] = this._quoteChar;
        if (r6 <= this._outputMaxContiguous) {
            _writeUTF8Segment(bArr, r5, r6);
        } else {
            _writeUTF8Segments(bArr, r5, r6);
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr3 = this._outputBuffer;
        int r52 = this._outputTail;
        this._outputTail = r52 + 1;
        bArr3[r52] = this._quoteChar;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeRaw(String str) throws IOException {
        int length = str.length();
        char[] cArr = this._charBuffer;
        if (length <= cArr.length) {
            str.getChars(0, length, cArr, 0);
            writeRaw(cArr, 0, length);
            return;
        }
        writeRaw(str, 0, length);
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeRaw(String str, int r9, int r10) throws IOException {
        char c;
        char[] cArr = this._charBuffer;
        int length = cArr.length;
        if (r10 <= length) {
            str.getChars(r9, r9 + r10, cArr, 0);
            writeRaw(cArr, 0, r10);
            return;
        }
        int r3 = this._outputEnd;
        int min = Math.min(length, (r3 >> 2) + (r3 >> 4));
        int r32 = min * 3;
        while (r10 > 0) {
            int min2 = Math.min(min, r10);
            str.getChars(r9, r9 + min2, cArr, 0);
            if (this._outputTail + r32 > this._outputEnd) {
                _flushBuffer();
            }
            if (min2 > 1 && (c = cArr[min2 - 1]) >= 55296 && c <= 56319) {
                min2--;
            }
            _writeRawSegment(cArr, 0, min2);
            r9 += min2;
            r10 -= min2;
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeRaw(SerializableString serializableString) throws IOException {
        byte[] asUnquotedUTF8 = serializableString.asUnquotedUTF8();
        if (asUnquotedUTF8.length > 0) {
            _writeBytes(asUnquotedUTF8);
        }
    }

    @Override // com.fasterxml.jackson.core.base.GeneratorBase, com.fasterxml.jackson.core.JsonGenerator
    public void writeRawValue(SerializableString serializableString) throws IOException {
        _verifyValueWrite("write a raw (unencoded) value");
        byte[] asUnquotedUTF8 = serializableString.asUnquotedUTF8();
        if (asUnquotedUTF8.length > 0) {
            _writeBytes(asUnquotedUTF8);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x001c, code lost:
        r0 = r7 + 1;
        r7 = r6[r7];
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0022, code lost:
        if (r7 >= 2048) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0024, code lost:
        r1 = r5._outputBuffer;
        r2 = r5._outputTail;
        r3 = r2 + 1;
        r5._outputTail = r3;
        r1[r2] = (byte) ((r7 >> 6) | 192);
        r5._outputTail = r3 + 1;
        r1[r3] = (byte) ((r7 & '?') | 128);
        r7 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0040, code lost:
        r7 = _outputRawMultiByteChar(r7, r6, r0, r8);
     */
    @Override // com.fasterxml.jackson.core.JsonGenerator
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void writeRaw(char[] r6, int r7, int r8) throws java.io.IOException {
        /*
            r5 = this;
            int r0 = r8 + r8
            int r0 = r0 + r8
            int r1 = r5._outputTail
            int r1 = r1 + r0
            int r2 = r5._outputEnd
            if (r1 <= r2) goto L13
            if (r2 >= r0) goto L10
            r5._writeSegmentedRaw(r6, r7, r8)
            return
        L10:
            r5._flushBuffer()
        L13:
            int r8 = r8 + r7
        L14:
            if (r7 >= r8) goto L54
        L16:
            char r0 = r6[r7]
            r1 = 127(0x7f, float:1.78E-43)
            if (r0 <= r1) goto L45
            int r0 = r7 + 1
            char r7 = r6[r7]
            r1 = 2048(0x800, float:2.87E-42)
            if (r7 >= r1) goto L40
            byte[] r1 = r5._outputBuffer
            int r2 = r5._outputTail
            int r3 = r2 + 1
            r5._outputTail = r3
            int r4 = r7 >> 6
            r4 = r4 | 192(0xc0, float:2.69E-43)
            byte r4 = (byte) r4
            r1[r2] = r4
            int r2 = r3 + 1
            r5._outputTail = r2
            r7 = r7 & 63
            r7 = r7 | 128(0x80, float:1.794E-43)
            byte r7 = (byte) r7
            r1[r3] = r7
            r7 = r0
            goto L14
        L40:
            int r7 = r5._outputRawMultiByteChar(r7, r6, r0, r8)
            goto L14
        L45:
            byte[] r1 = r5._outputBuffer
            int r2 = r5._outputTail
            int r3 = r2 + 1
            r5._outputTail = r3
            byte r0 = (byte) r0
            r1[r2] = r0
            int r7 = r7 + 1
            if (r7 < r8) goto L16
        L54:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.UTF8JsonGenerator.writeRaw(char[], int, int):void");
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeRaw(char c) throws IOException {
        if (this._outputTail + 3 >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        if (c <= 127) {
            int r1 = this._outputTail;
            this._outputTail = r1 + 1;
            bArr[r1] = (byte) c;
        } else if (c < 2048) {
            int r12 = this._outputTail;
            int r2 = r12 + 1;
            this._outputTail = r2;
            bArr[r12] = (byte) ((c >> 6) | 192);
            this._outputTail = r2 + 1;
            bArr[r2] = (byte) ((c & '?') | 128);
        } else {
            _outputRawMultiByteChar(c, null, 0, 0);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x001e, code lost:
        if (r9 >= 2048) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0020, code lost:
        r4 = r7._outputTail;
        r5 = r4 + 1;
        r7._outputTail = r5;
        r1[r4] = (byte) ((r9 >> 6) | 192);
        r7._outputTail = r5 + 1;
        r1[r5] = (byte) ((r9 & '?') | 128);
        r9 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0039, code lost:
        r9 = _outputRawMultiByteChar(r9, r8, r2, r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0013, code lost:
        if ((r7._outputTail + 3) < r7._outputEnd) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0015, code lost:
        _flushBuffer();
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0018, code lost:
        r2 = r9 + 1;
        r9 = r8[r9];
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void _writeSegmentedRaw(char[] r8, int r9, int r10) throws java.io.IOException {
        /*
            r7 = this;
            int r0 = r7._outputEnd
            byte[] r1 = r7._outputBuffer
            int r10 = r10 + r9
        L5:
            if (r9 >= r10) goto L52
        L7:
            char r2 = r8[r9]
            r3 = 128(0x80, float:1.794E-43)
            if (r2 < r3) goto L3e
            int r2 = r7._outputTail
            int r2 = r2 + 3
            int r4 = r7._outputEnd
            if (r2 < r4) goto L18
            r7._flushBuffer()
        L18:
            int r2 = r9 + 1
            char r9 = r8[r9]
            r4 = 2048(0x800, float:2.87E-42)
            if (r9 >= r4) goto L39
            int r4 = r7._outputTail
            int r5 = r4 + 1
            r7._outputTail = r5
            int r6 = r9 >> 6
            r6 = r6 | 192(0xc0, float:2.69E-43)
            byte r6 = (byte) r6
            r1[r4] = r6
            int r4 = r5 + 1
            r7._outputTail = r4
            r9 = r9 & 63
            r9 = r9 | r3
            byte r9 = (byte) r9
            r1[r5] = r9
            r9 = r2
            goto L5
        L39:
            int r9 = r7._outputRawMultiByteChar(r9, r8, r2, r10)
            goto L5
        L3e:
            int r3 = r7._outputTail
            if (r3 < r0) goto L45
            r7._flushBuffer()
        L45:
            int r3 = r7._outputTail
            int r4 = r3 + 1
            r7._outputTail = r4
            byte r2 = (byte) r2
            r1[r3] = r2
            int r9 = r9 + 1
            if (r9 < r10) goto L7
        L52:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.UTF8JsonGenerator._writeSegmentedRaw(char[], int, int):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:5:0x0008, code lost:
        r0 = r7 + 1;
        r7 = r6[r7];
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x000e, code lost:
        if (r7 >= 2048) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0010, code lost:
        r1 = r5._outputBuffer;
        r2 = r5._outputTail;
        r3 = r2 + 1;
        r5._outputTail = r3;
        r1[r2] = (byte) ((r7 >> 6) | 192);
        r5._outputTail = r3 + 1;
        r1[r3] = (byte) ((r7 & '?') | 128);
        r7 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x002c, code lost:
        r7 = _outputRawMultiByteChar(r7, r6, r0, r8);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void _writeRawSegment(char[] r6, int r7, int r8) throws java.io.IOException {
        /*
            r5 = this;
        L0:
            if (r7 >= r8) goto L40
        L2:
            char r0 = r6[r7]
            r1 = 127(0x7f, float:1.78E-43)
            if (r0 <= r1) goto L31
            int r0 = r7 + 1
            char r7 = r6[r7]
            r1 = 2048(0x800, float:2.87E-42)
            if (r7 >= r1) goto L2c
            byte[] r1 = r5._outputBuffer
            int r2 = r5._outputTail
            int r3 = r2 + 1
            r5._outputTail = r3
            int r4 = r7 >> 6
            r4 = r4 | 192(0xc0, float:2.69E-43)
            byte r4 = (byte) r4
            r1[r2] = r4
            int r2 = r3 + 1
            r5._outputTail = r2
            r7 = r7 & 63
            r7 = r7 | 128(0x80, float:1.794E-43)
            byte r7 = (byte) r7
            r1[r3] = r7
            r7 = r0
            goto L0
        L2c:
            int r7 = r5._outputRawMultiByteChar(r7, r6, r0, r8)
            goto L0
        L31:
            byte[] r1 = r5._outputBuffer
            int r2 = r5._outputTail
            int r3 = r2 + 1
            r5._outputTail = r3
            byte r0 = (byte) r0
            r1[r2] = r0
            int r7 = r7 + 1
            if (r7 < r8) goto L2
        L40:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.UTF8JsonGenerator._writeRawSegment(char[], int, int):void");
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeBinary(Base64Variant base64Variant, byte[] bArr, int r6, int r7) throws IOException, JsonGenerationException {
        _verifyValueWrite("write a binary value");
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr2 = this._outputBuffer;
        int r1 = this._outputTail;
        this._outputTail = r1 + 1;
        bArr2[r1] = this._quoteChar;
        _writeBinary(base64Variant, bArr, r6, r7 + r6);
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr3 = this._outputBuffer;
        int r5 = this._outputTail;
        this._outputTail = r5 + 1;
        bArr3[r5] = this._quoteChar;
    }

    @Override // com.fasterxml.jackson.core.base.GeneratorBase, com.fasterxml.jackson.core.JsonGenerator
    public int writeBinary(Base64Variant base64Variant, InputStream inputStream, int r6) throws IOException, JsonGenerationException {
        _verifyValueWrite("write a binary value");
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int r1 = this._outputTail;
        this._outputTail = r1 + 1;
        bArr[r1] = this._quoteChar;
        byte[] allocBase64Buffer = this._ioContext.allocBase64Buffer();
        try {
            if (r6 < 0) {
                r6 = _writeBinary(base64Variant, inputStream, allocBase64Buffer);
            } else {
                int _writeBinary = _writeBinary(base64Variant, inputStream, allocBase64Buffer, r6);
                if (_writeBinary > 0) {
                    _reportError("Too few bytes available: missing " + _writeBinary + " bytes (out of " + r6 + ")");
                }
            }
            this._ioContext.releaseBase64Buffer(allocBase64Buffer);
            if (this._outputTail >= this._outputEnd) {
                _flushBuffer();
            }
            byte[] bArr2 = this._outputBuffer;
            int r5 = this._outputTail;
            this._outputTail = r5 + 1;
            bArr2[r5] = this._quoteChar;
            return r6;
        } catch (Throwable th) {
            this._ioContext.releaseBase64Buffer(allocBase64Buffer);
            throw th;
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(short s) throws IOException {
        _verifyValueWrite("write a number");
        if (this._outputTail + 6 >= this._outputEnd) {
            _flushBuffer();
        }
        if (this._cfgNumbersAsStrings) {
            _writeQuotedShort(s);
        } else {
            this._outputTail = NumberOutput.outputInt(s, this._outputBuffer, this._outputTail);
        }
    }

    private final void _writeQuotedShort(short s) throws IOException {
        if (this._outputTail + 8 >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int r1 = this._outputTail;
        int r2 = r1 + 1;
        this._outputTail = r2;
        bArr[r1] = this._quoteChar;
        int outputInt = NumberOutput.outputInt(s, bArr, r2);
        byte[] bArr2 = this._outputBuffer;
        this._outputTail = outputInt + 1;
        bArr2[outputInt] = this._quoteChar;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(int r3) throws IOException {
        _verifyValueWrite("write a number");
        if (this._outputTail + 11 >= this._outputEnd) {
            _flushBuffer();
        }
        if (this._cfgNumbersAsStrings) {
            _writeQuotedInt(r3);
        } else {
            this._outputTail = NumberOutput.outputInt(r3, this._outputBuffer, this._outputTail);
        }
    }

    private final void _writeQuotedInt(int r5) throws IOException {
        if (this._outputTail + 13 >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int r1 = this._outputTail;
        int r2 = r1 + 1;
        this._outputTail = r2;
        bArr[r1] = this._quoteChar;
        int outputInt = NumberOutput.outputInt(r5, bArr, r2);
        byte[] bArr2 = this._outputBuffer;
        this._outputTail = outputInt + 1;
        bArr2[outputInt] = this._quoteChar;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(long j) throws IOException {
        _verifyValueWrite("write a number");
        if (this._cfgNumbersAsStrings) {
            _writeQuotedLong(j);
            return;
        }
        if (this._outputTail + 21 >= this._outputEnd) {
            _flushBuffer();
        }
        this._outputTail = NumberOutput.outputLong(j, this._outputBuffer, this._outputTail);
    }

    private final void _writeQuotedLong(long j) throws IOException {
        if (this._outputTail + 23 >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int r1 = this._outputTail;
        int r2 = r1 + 1;
        this._outputTail = r2;
        bArr[r1] = this._quoteChar;
        int outputLong = NumberOutput.outputLong(j, bArr, r2);
        byte[] bArr2 = this._outputBuffer;
        this._outputTail = outputLong + 1;
        bArr2[outputLong] = this._quoteChar;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(BigInteger bigInteger) throws IOException {
        _verifyValueWrite("write a number");
        if (bigInteger == null) {
            _writeNull();
        } else if (this._cfgNumbersAsStrings) {
            _writeQuotedRaw(bigInteger.toString());
        } else {
            writeRaw(bigInteger.toString());
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(double d) throws IOException {
        if (this._cfgNumbersAsStrings || ((Double.isNaN(d) || Double.isInfinite(d)) && JsonGenerator.Feature.QUOTE_NON_NUMERIC_NUMBERS.enabledIn(this._features))) {
            writeString(String.valueOf(d));
            return;
        }
        _verifyValueWrite("write a number");
        writeRaw(String.valueOf(d));
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(float f) throws IOException {
        if (this._cfgNumbersAsStrings || ((Float.isNaN(f) || Float.isInfinite(f)) && JsonGenerator.Feature.QUOTE_NON_NUMERIC_NUMBERS.enabledIn(this._features))) {
            writeString(String.valueOf(f));
            return;
        }
        _verifyValueWrite("write a number");
        writeRaw(String.valueOf(f));
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(BigDecimal bigDecimal) throws IOException {
        _verifyValueWrite("write a number");
        if (bigDecimal == null) {
            _writeNull();
        } else if (this._cfgNumbersAsStrings) {
            _writeQuotedRaw(_asString(bigDecimal));
        } else {
            writeRaw(_asString(bigDecimal));
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(String str) throws IOException {
        _verifyValueWrite("write a number");
        if (this._cfgNumbersAsStrings) {
            _writeQuotedRaw(str);
        } else {
            writeRaw(str);
        }
    }

    private final void _writeQuotedRaw(String str) throws IOException {
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int r1 = this._outputTail;
        this._outputTail = r1 + 1;
        bArr[r1] = this._quoteChar;
        writeRaw(str);
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr2 = this._outputBuffer;
        int r0 = this._outputTail;
        this._outputTail = r0 + 1;
        bArr2[r0] = this._quoteChar;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeBoolean(boolean z) throws IOException {
        _verifyValueWrite("write a boolean value");
        if (this._outputTail + 5 >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = z ? TRUE_BYTES : FALSE_BYTES;
        int length = bArr.length;
        System.arraycopy(bArr, 0, this._outputBuffer, this._outputTail, length);
        this._outputTail += length;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNull() throws IOException {
        _verifyValueWrite("write a null");
        _writeNull();
    }

    @Override // com.fasterxml.jackson.core.base.GeneratorBase
    protected final void _verifyValueWrite(String str) throws IOException {
        byte b;
        int writeValue = this._writeContext.writeValue();
        if (this._cfgPrettyPrinter != null) {
            _verifyPrettyValueWrite(str, writeValue);
            return;
        }
        if (writeValue == 1) {
            b = BYTE_COMMA;
        } else if (writeValue != 2) {
            if (writeValue != 3) {
                if (writeValue != 5) {
                    return;
                }
                _reportCantWriteValueExpectName(str);
                return;
            } else if (this._rootValueSeparator != null) {
                byte[] asUnquotedUTF8 = this._rootValueSeparator.asUnquotedUTF8();
                if (asUnquotedUTF8.length > 0) {
                    _writeBytes(asUnquotedUTF8);
                    return;
                }
                return;
            } else {
                return;
            }
        } else {
            b = BYTE_COLON;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int r1 = this._outputTail;
        this._outputTail = r1 + 1;
        bArr[r1] = b;
    }

    @Override // com.fasterxml.jackson.core.base.GeneratorBase, com.fasterxml.jackson.core.JsonGenerator, java.io.Flushable
    public void flush() throws IOException {
        _flushBuffer();
        if (this._outputStream == null || !isEnabled(JsonGenerator.Feature.FLUSH_PASSED_TO_STREAM)) {
            return;
        }
        this._outputStream.flush();
    }

    @Override // com.fasterxml.jackson.core.base.GeneratorBase, com.fasterxml.jackson.core.JsonGenerator, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        super.close();
        if (this._outputBuffer != null && isEnabled(JsonGenerator.Feature.AUTO_CLOSE_JSON_CONTENT)) {
            while (true) {
                JsonStreamContext outputContext = getOutputContext();
                if (outputContext.inArray()) {
                    writeEndArray();
                } else if (!outputContext.inObject()) {
                    break;
                } else {
                    writeEndObject();
                }
            }
        }
        _flushBuffer();
        this._outputTail = 0;
        if (this._outputStream != null) {
            if (this._ioContext.isResourceManaged() || isEnabled(JsonGenerator.Feature.AUTO_CLOSE_TARGET)) {
                this._outputStream.close();
            } else if (isEnabled(JsonGenerator.Feature.FLUSH_PASSED_TO_STREAM)) {
                this._outputStream.flush();
            }
        }
        _releaseBuffers();
    }

    @Override // com.fasterxml.jackson.core.base.GeneratorBase
    protected void _releaseBuffers() {
        byte[] bArr = this._outputBuffer;
        if (bArr != null && this._bufferRecyclable) {
            this._outputBuffer = null;
            this._ioContext.releaseWriteEncodingBuffer(bArr);
        }
        char[] cArr = this._charBuffer;
        if (cArr != null) {
            this._charBuffer = null;
            this._ioContext.releaseConcatBuffer(cArr);
        }
    }

    private final void _writeBytes(byte[] bArr) throws IOException {
        int length = bArr.length;
        if (this._outputTail + length > this._outputEnd) {
            _flushBuffer();
            if (length > 512) {
                this._outputStream.write(bArr, 0, length);
                return;
            }
        }
        System.arraycopy(bArr, 0, this._outputBuffer, this._outputTail, length);
        this._outputTail += length;
    }

    private final void _writeBytes(byte[] bArr, int r4, int r5) throws IOException {
        if (this._outputTail + r5 > this._outputEnd) {
            _flushBuffer();
            if (r5 > 512) {
                this._outputStream.write(bArr, r4, r5);
                return;
            }
        }
        System.arraycopy(bArr, r4, this._outputBuffer, this._outputTail, r5);
        this._outputTail += r5;
    }

    private final void _writeStringSegments(String str, boolean z) throws IOException {
        if (z) {
            if (this._outputTail >= this._outputEnd) {
                _flushBuffer();
            }
            byte[] bArr = this._outputBuffer;
            int r1 = this._outputTail;
            this._outputTail = r1 + 1;
            bArr[r1] = this._quoteChar;
        }
        int length = str.length();
        int r12 = 0;
        while (length > 0) {
            int min = Math.min(this._outputMaxContiguous, length);
            if (this._outputTail + min > this._outputEnd) {
                _flushBuffer();
            }
            _writeStringSegment(str, r12, min);
            r12 += min;
            length -= min;
        }
        if (z) {
            if (this._outputTail >= this._outputEnd) {
                _flushBuffer();
            }
            byte[] bArr2 = this._outputBuffer;
            int r7 = this._outputTail;
            this._outputTail = r7 + 1;
            bArr2[r7] = this._quoteChar;
        }
    }

    private final void _writeStringSegments(char[] cArr, int r5, int r6) throws IOException {
        do {
            int min = Math.min(this._outputMaxContiguous, r6);
            if (this._outputTail + min > this._outputEnd) {
                _flushBuffer();
            }
            _writeStringSegment(cArr, r5, min);
            r5 += min;
            r6 -= min;
        } while (r6 > 0);
    }

    private final void _writeStringSegments(String str, int r5, int r6) throws IOException {
        do {
            int min = Math.min(this._outputMaxContiguous, r6);
            if (this._outputTail + min > this._outputEnd) {
                _flushBuffer();
            }
            _writeStringSegment(str, r5, min);
            r5 += min;
            r6 -= min;
        } while (r6 > 0);
    }

    private final void _writeStringSegment(char[] cArr, int r7, int r8) throws IOException {
        int r82 = r8 + r7;
        int r0 = this._outputTail;
        byte[] bArr = this._outputBuffer;
        int[] r2 = this._outputEscapes;
        while (r7 < r82) {
            char c = cArr[r7];
            if (c > 127 || r2[c] != 0) {
                break;
            }
            bArr[r0] = (byte) c;
            r7++;
            r0++;
        }
        this._outputTail = r0;
        if (r7 < r82) {
            if (this._characterEscapes != null) {
                _writeCustomStringSegment2(cArr, r7, r82);
            } else if (this._maximumNonEscapedChar == 0) {
                _writeStringSegment2(cArr, r7, r82);
            } else {
                _writeStringSegmentASCII2(cArr, r7, r82);
            }
        }
    }

    private final void _writeStringSegment(String str, int r7, int r8) throws IOException {
        int r82 = r8 + r7;
        int r0 = this._outputTail;
        byte[] bArr = this._outputBuffer;
        int[] r2 = this._outputEscapes;
        while (r7 < r82) {
            char charAt = str.charAt(r7);
            if (charAt > 127 || r2[charAt] != 0) {
                break;
            }
            bArr[r0] = (byte) charAt;
            r7++;
            r0++;
        }
        this._outputTail = r0;
        if (r7 < r82) {
            if (this._characterEscapes != null) {
                _writeCustomStringSegment2(str, r7, r82);
            } else if (this._maximumNonEscapedChar == 0) {
                _writeStringSegment2(str, r7, r82);
            } else {
                _writeStringSegmentASCII2(str, r7, r82);
            }
        }
    }

    private final void _writeStringSegment2(char[] cArr, int r8, int r9) throws IOException {
        if (this._outputTail + ((r9 - r8) * 6) > this._outputEnd) {
            _flushBuffer();
        }
        int r0 = this._outputTail;
        byte[] bArr = this._outputBuffer;
        int[] r2 = this._outputEscapes;
        while (r8 < r9) {
            int r3 = r8 + 1;
            char c = cArr[r8];
            if (c <= 127) {
                if (r2[c] == 0) {
                    bArr[r0] = (byte) c;
                    r8 = r3;
                    r0++;
                } else {
                    int r4 = r2[c];
                    if (r4 > 0) {
                        int r82 = r0 + 1;
                        bArr[r0] = BYTE_BACKSLASH;
                        r0 = r82 + 1;
                        bArr[r82] = (byte) r4;
                    } else {
                        r0 = _writeGenericEscape(c, r0);
                    }
                }
            } else if (c <= 2047) {
                int r42 = r0 + 1;
                bArr[r0] = (byte) ((c >> 6) | 192);
                r0 = r42 + 1;
                bArr[r42] = (byte) ((c & '?') | 128);
            } else {
                r0 = _outputMultiByteChar(c, r0);
            }
            r8 = r3;
        }
        this._outputTail = r0;
    }

    private final void _writeStringSegment2(String str, int r8, int r9) throws IOException {
        if (this._outputTail + ((r9 - r8) * 6) > this._outputEnd) {
            _flushBuffer();
        }
        int r0 = this._outputTail;
        byte[] bArr = this._outputBuffer;
        int[] r2 = this._outputEscapes;
        while (r8 < r9) {
            int r3 = r8 + 1;
            char charAt = str.charAt(r8);
            if (charAt <= 127) {
                if (r2[charAt] == 0) {
                    bArr[r0] = (byte) charAt;
                    r8 = r3;
                    r0++;
                } else {
                    int r4 = r2[charAt];
                    if (r4 > 0) {
                        int r82 = r0 + 1;
                        bArr[r0] = BYTE_BACKSLASH;
                        r0 = r82 + 1;
                        bArr[r82] = (byte) r4;
                    } else {
                        r0 = _writeGenericEscape(charAt, r0);
                    }
                }
            } else if (charAt <= 2047) {
                int r42 = r0 + 1;
                bArr[r0] = (byte) ((charAt >> 6) | 192);
                r0 = r42 + 1;
                bArr[r42] = (byte) ((charAt & '?') | 128);
            } else {
                r0 = _outputMultiByteChar(charAt, r0);
            }
            r8 = r3;
        }
        this._outputTail = r0;
    }

    private final void _writeStringSegmentASCII2(char[] cArr, int r9, int r10) throws IOException {
        if (this._outputTail + ((r10 - r9) * 6) > this._outputEnd) {
            _flushBuffer();
        }
        int r0 = this._outputTail;
        byte[] bArr = this._outputBuffer;
        int[] r2 = this._outputEscapes;
        int r3 = this._maximumNonEscapedChar;
        while (r9 < r10) {
            int r4 = r9 + 1;
            char c = cArr[r9];
            if (c <= 127) {
                if (r2[c] == 0) {
                    bArr[r0] = (byte) c;
                    r9 = r4;
                    r0++;
                } else {
                    int r5 = r2[c];
                    if (r5 > 0) {
                        int r92 = r0 + 1;
                        bArr[r0] = BYTE_BACKSLASH;
                        r0 = r92 + 1;
                        bArr[r92] = (byte) r5;
                    } else {
                        r0 = _writeGenericEscape(c, r0);
                    }
                }
            } else if (c > r3) {
                r0 = _writeGenericEscape(c, r0);
            } else if (c <= 2047) {
                int r52 = r0 + 1;
                bArr[r0] = (byte) ((c >> 6) | 192);
                r0 = r52 + 1;
                bArr[r52] = (byte) ((c & '?') | 128);
            } else {
                r0 = _outputMultiByteChar(c, r0);
            }
            r9 = r4;
        }
        this._outputTail = r0;
    }

    private final void _writeStringSegmentASCII2(String str, int r9, int r10) throws IOException {
        if (this._outputTail + ((r10 - r9) * 6) > this._outputEnd) {
            _flushBuffer();
        }
        int r0 = this._outputTail;
        byte[] bArr = this._outputBuffer;
        int[] r2 = this._outputEscapes;
        int r3 = this._maximumNonEscapedChar;
        while (r9 < r10) {
            int r4 = r9 + 1;
            char charAt = str.charAt(r9);
            if (charAt <= 127) {
                if (r2[charAt] == 0) {
                    bArr[r0] = (byte) charAt;
                    r9 = r4;
                    r0++;
                } else {
                    int r5 = r2[charAt];
                    if (r5 > 0) {
                        int r92 = r0 + 1;
                        bArr[r0] = BYTE_BACKSLASH;
                        r0 = r92 + 1;
                        bArr[r92] = (byte) r5;
                    } else {
                        r0 = _writeGenericEscape(charAt, r0);
                    }
                }
            } else if (charAt > r3) {
                r0 = _writeGenericEscape(charAt, r0);
            } else if (charAt <= 2047) {
                int r52 = r0 + 1;
                bArr[r0] = (byte) ((charAt >> 6) | 192);
                r0 = r52 + 1;
                bArr[r52] = (byte) ((charAt & '?') | 128);
            } else {
                r0 = _outputMultiByteChar(charAt, r0);
            }
            r9 = r4;
        }
        this._outputTail = r0;
    }

    private final void _writeCustomStringSegment2(char[] cArr, int r11, int r12) throws IOException {
        if (this._outputTail + ((r12 - r11) * 6) > this._outputEnd) {
            _flushBuffer();
        }
        int r0 = this._outputTail;
        byte[] bArr = this._outputBuffer;
        int[] r2 = this._outputEscapes;
        int r3 = this._maximumNonEscapedChar <= 0 ? 65535 : this._maximumNonEscapedChar;
        CharacterEscapes characterEscapes = this._characterEscapes;
        while (r11 < r12) {
            int r5 = r11 + 1;
            char c = cArr[r11];
            if (c <= 127) {
                if (r2[c] == 0) {
                    bArr[r0] = (byte) c;
                    r11 = r5;
                    r0++;
                } else {
                    int r6 = r2[c];
                    if (r6 > 0) {
                        int r112 = r0 + 1;
                        bArr[r0] = BYTE_BACKSLASH;
                        r0 = r112 + 1;
                        bArr[r112] = (byte) r6;
                    } else if (r6 == -2) {
                        SerializableString escapeSequence = characterEscapes.getEscapeSequence(c);
                        if (escapeSequence == null) {
                            _reportError("Invalid custom escape definitions; custom escape not found for character code 0x" + Integer.toHexString(c) + ", although was supposed to have one");
                        }
                        r0 = _writeCustomEscape(bArr, r0, escapeSequence, r12 - r5);
                    } else {
                        r0 = _writeGenericEscape(c, r0);
                    }
                }
            } else if (c > r3) {
                r0 = _writeGenericEscape(c, r0);
            } else {
                SerializableString escapeSequence2 = characterEscapes.getEscapeSequence(c);
                if (escapeSequence2 != null) {
                    r0 = _writeCustomEscape(bArr, r0, escapeSequence2, r12 - r5);
                } else if (c <= 2047) {
                    int r62 = r0 + 1;
                    bArr[r0] = (byte) ((c >> 6) | 192);
                    r0 = r62 + 1;
                    bArr[r62] = (byte) ((c & '?') | 128);
                } else {
                    r0 = _outputMultiByteChar(c, r0);
                }
            }
            r11 = r5;
        }
        this._outputTail = r0;
    }

    private final void _writeCustomStringSegment2(String str, int r11, int r12) throws IOException {
        if (this._outputTail + ((r12 - r11) * 6) > this._outputEnd) {
            _flushBuffer();
        }
        int r0 = this._outputTail;
        byte[] bArr = this._outputBuffer;
        int[] r2 = this._outputEscapes;
        int r3 = this._maximumNonEscapedChar <= 0 ? 65535 : this._maximumNonEscapedChar;
        CharacterEscapes characterEscapes = this._characterEscapes;
        while (r11 < r12) {
            int r5 = r11 + 1;
            char charAt = str.charAt(r11);
            if (charAt <= 127) {
                if (r2[charAt] == 0) {
                    bArr[r0] = (byte) charAt;
                    r11 = r5;
                    r0++;
                } else {
                    int r6 = r2[charAt];
                    if (r6 > 0) {
                        int r112 = r0 + 1;
                        bArr[r0] = BYTE_BACKSLASH;
                        r0 = r112 + 1;
                        bArr[r112] = (byte) r6;
                    } else if (r6 == -2) {
                        SerializableString escapeSequence = characterEscapes.getEscapeSequence(charAt);
                        if (escapeSequence == null) {
                            _reportError("Invalid custom escape definitions; custom escape not found for character code 0x" + Integer.toHexString(charAt) + ", although was supposed to have one");
                        }
                        r0 = _writeCustomEscape(bArr, r0, escapeSequence, r12 - r5);
                    } else {
                        r0 = _writeGenericEscape(charAt, r0);
                    }
                }
            } else if (charAt > r3) {
                r0 = _writeGenericEscape(charAt, r0);
            } else {
                SerializableString escapeSequence2 = characterEscapes.getEscapeSequence(charAt);
                if (escapeSequence2 != null) {
                    r0 = _writeCustomEscape(bArr, r0, escapeSequence2, r12 - r5);
                } else if (charAt <= 2047) {
                    int r62 = r0 + 1;
                    bArr[r0] = (byte) ((charAt >> 6) | 192);
                    r0 = r62 + 1;
                    bArr[r62] = (byte) ((charAt & '?') | 128);
                } else {
                    r0 = _outputMultiByteChar(charAt, r0);
                }
            }
            r11 = r5;
        }
        this._outputTail = r0;
    }

    private final int _writeCustomEscape(byte[] bArr, int r8, SerializableString serializableString, int r10) throws IOException, JsonGenerationException {
        byte[] asUnquotedUTF8 = serializableString.asUnquotedUTF8();
        int length = asUnquotedUTF8.length;
        if (length > 6) {
            return _handleLongCustomEscape(bArr, r8, this._outputEnd, asUnquotedUTF8, r10);
        }
        System.arraycopy(asUnquotedUTF8, 0, bArr, r8, length);
        return r8 + length;
    }

    private final int _handleLongCustomEscape(byte[] bArr, int r5, int r6, byte[] bArr2, int r8) throws IOException, JsonGenerationException {
        int length = bArr2.length;
        if (r5 + length > r6) {
            this._outputTail = r5;
            _flushBuffer();
            int r52 = this._outputTail;
            if (length > bArr.length) {
                this._outputStream.write(bArr2, 0, length);
                return r52;
            }
            System.arraycopy(bArr2, 0, bArr, r52, length);
            r5 = r52 + length;
        }
        if ((r8 * 6) + r5 > r6) {
            _flushBuffer();
            return this._outputTail;
        }
        return r5;
    }

    private final void _writeUTF8Segments(byte[] bArr, int r3, int r4) throws IOException, JsonGenerationException {
        do {
            int min = Math.min(this._outputMaxContiguous, r4);
            _writeUTF8Segment(bArr, r3, min);
            r3 += min;
            r4 -= min;
        } while (r4 > 0);
    }

    private final void _writeUTF8Segment(byte[] bArr, int r6, int r7) throws IOException, JsonGenerationException {
        int[] r0 = this._outputEscapes;
        int r1 = r6 + r7;
        int r2 = r6;
        while (r2 < r1) {
            int r3 = r2 + 1;
            byte b = bArr[r2];
            if (b >= 0 && r0[b] != 0) {
                _writeUTF8Segment2(bArr, r6, r7);
                return;
            }
            r2 = r3;
        }
        if (this._outputTail + r7 > this._outputEnd) {
            _flushBuffer();
        }
        System.arraycopy(bArr, r6, this._outputBuffer, this._outputTail, r7);
        this._outputTail += r7;
    }

    private final void _writeUTF8Segment2(byte[] bArr, int r8, int r9) throws IOException, JsonGenerationException {
        int r0 = this._outputTail;
        if ((r9 * 6) + r0 > this._outputEnd) {
            _flushBuffer();
            r0 = this._outputTail;
        }
        byte[] bArr2 = this._outputBuffer;
        int[] r2 = this._outputEscapes;
        int r92 = r9 + r8;
        while (r8 < r92) {
            int r3 = r8 + 1;
            byte b = bArr[r8];
            if (b < 0 || r2[b] == 0) {
                bArr2[r0] = b;
                r8 = r3;
                r0++;
            } else {
                int r4 = r2[b];
                if (r4 > 0) {
                    int r82 = r0 + 1;
                    bArr2[r0] = BYTE_BACKSLASH;
                    r0 = r82 + 1;
                    bArr2[r82] = (byte) r4;
                } else {
                    r0 = _writeGenericEscape(b, r0);
                }
                r8 = r3;
            }
        }
        this._outputTail = r0;
    }

    protected final void _writeBinary(Base64Variant base64Variant, byte[] bArr, int r10, int r11) throws IOException, JsonGenerationException {
        int r0 = r11 - 3;
        int r1 = this._outputEnd - 6;
        int maxLineLength = base64Variant.getMaxLineLength() >> 2;
        while (r10 <= r0) {
            if (this._outputTail > r1) {
                _flushBuffer();
            }
            int r4 = r10 + 1;
            int r5 = r4 + 1;
            int r42 = r5 + 1;
            int encodeBase64Chunk = base64Variant.encodeBase64Chunk((((bArr[r10] << 8) | (bArr[r4] & 255)) << 8) | (bArr[r5] & 255), this._outputBuffer, this._outputTail);
            this._outputTail = encodeBase64Chunk;
            maxLineLength--;
            if (maxLineLength <= 0) {
                byte[] bArr2 = this._outputBuffer;
                int r52 = encodeBase64Chunk + 1;
                this._outputTail = r52;
                bArr2[encodeBase64Chunk] = BYTE_BACKSLASH;
                this._outputTail = r52 + 1;
                bArr2[r52] = 110;
                maxLineLength = base64Variant.getMaxLineLength() >> 2;
            }
            r10 = r42;
        }
        int r112 = r11 - r10;
        if (r112 > 0) {
            if (this._outputTail > r1) {
                _flushBuffer();
            }
            int r02 = r10 + 1;
            int r102 = bArr[r10] << 16;
            if (r112 == 2) {
                r102 |= (bArr[r02] & 255) << 8;
            }
            this._outputTail = base64Variant.encodeBase64Partial(r102, r112, this._outputBuffer, this._outputTail);
        }
    }

    protected final int _writeBinary(Base64Variant base64Variant, InputStream inputStream, byte[] bArr, int r15) throws IOException, JsonGenerationException {
        int _readMore;
        int r0 = this._outputEnd - 6;
        int r2 = 2;
        int maxLineLength = base64Variant.getMaxLineLength() >> 2;
        int r3 = -3;
        int r8 = 0;
        int r9 = 0;
        while (true) {
            if (r15 <= 2) {
                break;
            }
            if (r8 > r3) {
                r9 = _readMore(inputStream, bArr, r8, r9, r15);
                if (r9 < 3) {
                    r8 = 0;
                    break;
                }
                r3 = r9 - 3;
                r8 = 0;
            }
            if (this._outputTail > r0) {
                _flushBuffer();
            }
            int r5 = r8 + 1;
            int r7 = r5 + 1;
            r8 = r7 + 1;
            r15 -= 3;
            int encodeBase64Chunk = base64Variant.encodeBase64Chunk((((bArr[r5] & 255) | (bArr[r8] << 8)) << 8) | (bArr[r7] & 255), this._outputBuffer, this._outputTail);
            this._outputTail = encodeBase64Chunk;
            maxLineLength--;
            if (maxLineLength <= 0) {
                byte[] bArr2 = this._outputBuffer;
                int r6 = encodeBase64Chunk + 1;
                this._outputTail = r6;
                bArr2[encodeBase64Chunk] = BYTE_BACKSLASH;
                this._outputTail = r6 + 1;
                bArr2[r6] = 110;
                maxLineLength = base64Variant.getMaxLineLength() >> 2;
            }
        }
        if (r15 <= 0 || (_readMore = _readMore(inputStream, bArr, r8, r9, r15)) <= 0) {
            return r15;
        }
        if (this._outputTail > r0) {
            _flushBuffer();
        }
        int r02 = bArr[0] << 16;
        if (1 < _readMore) {
            r02 |= (bArr[1] & 255) << 8;
        } else {
            r2 = 1;
        }
        this._outputTail = base64Variant.encodeBase64Partial(r02, r2, this._outputBuffer, this._outputTail);
        return r15 - r2;
    }

    protected final int _writeBinary(Base64Variant base64Variant, InputStream inputStream, byte[] bArr) throws IOException, JsonGenerationException {
        int r0 = this._outputEnd - 6;
        int r2 = 2;
        int maxLineLength = base64Variant.getMaxLineLength() >> 2;
        int r4 = -3;
        int r8 = 0;
        int r9 = 0;
        int r11 = 0;
        while (true) {
            if (r8 > r4) {
                r9 = _readMore(inputStream, bArr, r8, r9, bArr.length);
                if (r9 < 3) {
                    break;
                }
                r4 = r9 - 3;
                r8 = 0;
            }
            if (this._outputTail > r0) {
                _flushBuffer();
            }
            int r5 = r8 + 1;
            int r7 = r5 + 1;
            r8 = r7 + 1;
            r11 += 3;
            int encodeBase64Chunk = base64Variant.encodeBase64Chunk((((bArr[r5] & 255) | (bArr[r8] << 8)) << 8) | (bArr[r7] & 255), this._outputBuffer, this._outputTail);
            this._outputTail = encodeBase64Chunk;
            maxLineLength--;
            if (maxLineLength <= 0) {
                byte[] bArr2 = this._outputBuffer;
                int r6 = encodeBase64Chunk + 1;
                this._outputTail = r6;
                bArr2[encodeBase64Chunk] = BYTE_BACKSLASH;
                this._outputTail = r6 + 1;
                bArr2[r6] = 110;
                maxLineLength = base64Variant.getMaxLineLength() >> 2;
            }
        }
        if (r9 > 0) {
            if (this._outputTail > r0) {
                _flushBuffer();
            }
            int r14 = bArr[0] << 16;
            if (1 < r9) {
                r14 |= (bArr[1] & 255) << 8;
            } else {
                r2 = 1;
            }
            int r112 = r11 + r2;
            this._outputTail = base64Variant.encodeBase64Partial(r14, r2, this._outputBuffer, this._outputTail);
            return r112;
        }
        return r11;
    }

    private final int _readMore(InputStream inputStream, byte[] bArr, int r6, int r7, int r8) throws IOException {
        int r0 = 0;
        while (r6 < r7) {
            bArr[r0] = bArr[r6];
            r0++;
            r6++;
        }
        int min = Math.min(r8, bArr.length);
        do {
            int r72 = min - r0;
            if (r72 == 0) {
                break;
            }
            int read = inputStream.read(bArr, r0, r72);
            if (read < 0) {
                return r0;
            }
            r0 += read;
        } while (r0 < 3);
        return r0;
    }

    private final int _outputRawMultiByteChar(int r4, char[] cArr, int r6, int r7) throws IOException {
        if (r4 >= 55296 && r4 <= 57343) {
            if (r6 >= r7 || cArr == null) {
                _reportError(String.format("Split surrogate on writeRaw() input (last character): first character 0x%4x", Integer.valueOf(r4)));
            }
            _outputSurrogates(r4, cArr[r6]);
            return r6 + 1;
        }
        byte[] bArr = this._outputBuffer;
        int r72 = this._outputTail;
        int r0 = r72 + 1;
        this._outputTail = r0;
        bArr[r72] = (byte) ((r4 >> 12) | 224);
        int r73 = r0 + 1;
        this._outputTail = r73;
        bArr[r0] = (byte) (((r4 >> 6) & 63) | 128);
        this._outputTail = r73 + 1;
        bArr[r73] = (byte) ((r4 & 63) | 128);
        return r6;
    }

    protected final void _outputSurrogates(int r4, int r5) throws IOException {
        int _decodeSurrogate = _decodeSurrogate(r4, r5);
        if (this._outputTail + 4 > this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int r0 = this._outputTail;
        int r1 = r0 + 1;
        this._outputTail = r1;
        bArr[r0] = (byte) ((_decodeSurrogate >> 18) | PsExtractor.VIDEO_STREAM_MASK);
        int r02 = r1 + 1;
        this._outputTail = r02;
        bArr[r1] = (byte) (((_decodeSurrogate >> 12) & 63) | 128);
        int r12 = r02 + 1;
        this._outputTail = r12;
        bArr[r02] = (byte) (((_decodeSurrogate >> 6) & 63) | 128);
        this._outputTail = r12 + 1;
        bArr[r12] = (byte) ((_decodeSurrogate & 63) | 128);
    }

    private final int _outputMultiByteChar(int r5, int r6) throws IOException {
        byte[] bArr = this._outputBuffer;
        if (r5 >= 55296 && r5 <= 57343) {
            int r1 = r6 + 1;
            bArr[r6] = BYTE_BACKSLASH;
            int r62 = r1 + 1;
            bArr[r1] = BYTE_u;
            int r12 = r62 + 1;
            byte[] bArr2 = HEX_CHARS;
            bArr[r62] = bArr2[(r5 >> 12) & 15];
            int r63 = r12 + 1;
            bArr[r12] = bArr2[(r5 >> 8) & 15];
            int r13 = r63 + 1;
            bArr[r63] = bArr2[(r5 >> 4) & 15];
            int r64 = r13 + 1;
            bArr[r13] = bArr2[r5 & 15];
            return r64;
        }
        int r14 = r6 + 1;
        bArr[r6] = (byte) ((r5 >> 12) | 224);
        int r65 = r14 + 1;
        bArr[r14] = (byte) (((r5 >> 6) & 63) | 128);
        int r15 = r65 + 1;
        bArr[r65] = (byte) ((r5 & 63) | 128);
        return r15;
    }

    private final void _writeNull() throws IOException {
        if (this._outputTail + 4 >= this._outputEnd) {
            _flushBuffer();
        }
        System.arraycopy(NULL_BYTES, 0, this._outputBuffer, this._outputTail, 4);
        this._outputTail += 4;
    }

    private int _writeGenericEscape(int r6, int r7) throws IOException {
        int r72;
        byte[] bArr = this._outputBuffer;
        int r1 = r7 + 1;
        bArr[r7] = BYTE_BACKSLASH;
        int r73 = r1 + 1;
        bArr[r1] = BYTE_u;
        if (r6 > 255) {
            int r12 = 255 & (r6 >> 8);
            int r2 = r73 + 1;
            byte[] bArr2 = HEX_CHARS;
            bArr[r73] = bArr2[r12 >> 4];
            r72 = r2 + 1;
            bArr[r2] = bArr2[r12 & 15];
            r6 &= 255;
        } else {
            int r13 = r73 + 1;
            bArr[r73] = BYTE_0;
            r72 = r13 + 1;
            bArr[r13] = BYTE_0;
        }
        int r14 = r72 + 1;
        byte[] bArr3 = HEX_CHARS;
        bArr[r72] = bArr3[r6 >> 4];
        int r74 = r14 + 1;
        bArr[r14] = bArr3[r6 & 15];
        return r74;
    }

    protected final void _flushBuffer() throws IOException {
        int r0 = this._outputTail;
        if (r0 > 0) {
            this._outputTail = 0;
            this._outputStream.write(this._outputBuffer, 0, r0);
        }
    }
}
