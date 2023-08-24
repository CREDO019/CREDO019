package com.fasterxml.jackson.core.json;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.p009io.CharTypes;
import com.fasterxml.jackson.core.p009io.IOContext;
import com.fasterxml.jackson.core.p009io.NumberOutput;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;
import kotlin.text.Typography;
import org.apache.commons.p028io.IOUtils;

/* loaded from: classes.dex */
public final class WriterBasedJsonGenerator extends JsonGeneratorImpl {
    protected static final char[] HEX_CHARS = CharTypes.copyHexChars();
    protected static final int SHORT_WRITE = 32;
    protected SerializableString _currentEscape;
    protected char[] _entityBuffer;
    protected char[] _outputBuffer;
    protected int _outputEnd;
    protected int _outputHead;
    protected int _outputTail;
    protected char _quoteChar;
    protected final Writer _writer;

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public boolean canWriteFormattedNumbers() {
        return true;
    }

    public WriterBasedJsonGenerator(IOContext iOContext, int r2, ObjectCodec objectCodec, Writer writer) {
        super(iOContext, r2, objectCodec);
        this._quoteChar = Typography.quote;
        this._writer = writer;
        char[] allocConcatBuffer = iOContext.allocConcatBuffer();
        this._outputBuffer = allocConcatBuffer;
        this._outputEnd = allocConcatBuffer.length;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public Object getOutputTarget() {
        return this._writer;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public int getOutputBuffered() {
        return Math.max(0, this._outputTail - this._outputHead);
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeFieldName(String str) throws IOException {
        int writeFieldName = this._writeContext.writeFieldName(str);
        if (writeFieldName == 4) {
            _reportError("Can not write a field name, expecting a value");
        }
        _writeFieldName(str, writeFieldName == 1);
    }

    @Override // com.fasterxml.jackson.core.base.GeneratorBase, com.fasterxml.jackson.core.JsonGenerator
    public void writeFieldName(SerializableString serializableString) throws IOException {
        int writeFieldName = this._writeContext.writeFieldName(serializableString.getValue());
        if (writeFieldName == 4) {
            _reportError("Can not write a field name, expecting a value");
        }
        _writeFieldName(serializableString, writeFieldName == 1);
    }

    protected void _writeFieldName(String str, boolean z) throws IOException {
        if (this._cfgPrettyPrinter != null) {
            _writePPFieldName(str, z);
            return;
        }
        if (this._outputTail + 1 >= this._outputEnd) {
            _flushBuffer();
        }
        if (z) {
            char[] cArr = this._outputBuffer;
            int r0 = this._outputTail;
            this._outputTail = r0 + 1;
            cArr[r0] = ',';
        }
        if (this._cfgUnqNames) {
            _writeString(str);
            return;
        }
        char[] cArr2 = this._outputBuffer;
        int r02 = this._outputTail;
        this._outputTail = r02 + 1;
        cArr2[r02] = this._quoteChar;
        _writeString(str);
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr3 = this._outputBuffer;
        int r4 = this._outputTail;
        this._outputTail = r4 + 1;
        cArr3[r4] = this._quoteChar;
    }

    protected void _writeFieldName(SerializableString serializableString, boolean z) throws IOException {
        if (this._cfgPrettyPrinter != null) {
            _writePPFieldName(serializableString, z);
            return;
        }
        if (this._outputTail + 1 >= this._outputEnd) {
            _flushBuffer();
        }
        if (z) {
            char[] cArr = this._outputBuffer;
            int r0 = this._outputTail;
            this._outputTail = r0 + 1;
            cArr[r0] = ',';
        }
        char[] asQuotedChars = serializableString.asQuotedChars();
        if (this._cfgUnqNames) {
            writeRaw(asQuotedChars, 0, asQuotedChars.length);
            return;
        }
        char[] cArr2 = this._outputBuffer;
        int r1 = this._outputTail;
        int r2 = r1 + 1;
        this._outputTail = r2;
        cArr2[r1] = this._quoteChar;
        int length = asQuotedChars.length;
        if (r2 + length + 1 >= this._outputEnd) {
            writeRaw(asQuotedChars, 0, length);
            if (this._outputTail >= this._outputEnd) {
                _flushBuffer();
            }
            char[] cArr3 = this._outputBuffer;
            int r7 = this._outputTail;
            this._outputTail = r7 + 1;
            cArr3[r7] = this._quoteChar;
            return;
        }
        System.arraycopy(asQuotedChars, 0, cArr2, r2, length);
        int r6 = this._outputTail + length;
        char[] cArr4 = this._outputBuffer;
        this._outputTail = r6 + 1;
        cArr4[r6] = this._quoteChar;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeStartArray() throws IOException {
        _verifyValueWrite("start an array");
        this._writeContext = this._writeContext.createChildArrayContext();
        if (this._cfgPrettyPrinter != null) {
            this._cfgPrettyPrinter.writeStartArray(this);
            return;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr = this._outputBuffer;
        int r1 = this._outputTail;
        this._outputTail = r1 + 1;
        cArr[r1] = '[';
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeEndArray() throws IOException {
        if (!this._writeContext.inArray()) {
            _reportError("Current context not Array but " + this._writeContext.typeDesc());
        }
        if (this._cfgPrettyPrinter != null) {
            this._cfgPrettyPrinter.writeEndArray(this, this._writeContext.getEntryCount());
        } else {
            if (this._outputTail >= this._outputEnd) {
                _flushBuffer();
            }
            char[] cArr = this._outputBuffer;
            int r1 = this._outputTail;
            this._outputTail = r1 + 1;
            cArr[r1] = ']';
        }
        this._writeContext = this._writeContext.clearAndGetParent();
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
        char[] cArr = this._outputBuffer;
        int r0 = this._outputTail;
        this._outputTail = r0 + 1;
        cArr[r0] = '{';
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeStartObject() throws IOException {
        _verifyValueWrite("start an object");
        this._writeContext = this._writeContext.createChildObjectContext();
        if (this._cfgPrettyPrinter != null) {
            this._cfgPrettyPrinter.writeStartObject(this);
            return;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr = this._outputBuffer;
        int r1 = this._outputTail;
        this._outputTail = r1 + 1;
        cArr[r1] = '{';
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeEndObject() throws IOException {
        if (!this._writeContext.inObject()) {
            _reportError("Current context not Object but " + this._writeContext.typeDesc());
        }
        if (this._cfgPrettyPrinter != null) {
            this._cfgPrettyPrinter.writeEndObject(this, this._writeContext.getEntryCount());
        } else {
            if (this._outputTail >= this._outputEnd) {
                _flushBuffer();
            }
            char[] cArr = this._outputBuffer;
            int r1 = this._outputTail;
            this._outputTail = r1 + 1;
            cArr[r1] = '}';
        }
        this._writeContext = this._writeContext.clearAndGetParent();
    }

    protected void _writePPFieldName(String str, boolean z) throws IOException {
        if (z) {
            this._cfgPrettyPrinter.writeObjectEntrySeparator(this);
        } else {
            this._cfgPrettyPrinter.beforeObjectEntries(this);
        }
        if (this._cfgUnqNames) {
            _writeString(str);
            return;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr = this._outputBuffer;
        int r0 = this._outputTail;
        this._outputTail = r0 + 1;
        cArr[r0] = this._quoteChar;
        _writeString(str);
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr2 = this._outputBuffer;
        int r4 = this._outputTail;
        this._outputTail = r4 + 1;
        cArr2[r4] = this._quoteChar;
    }

    protected void _writePPFieldName(SerializableString serializableString, boolean z) throws IOException {
        if (z) {
            this._cfgPrettyPrinter.writeObjectEntrySeparator(this);
        } else {
            this._cfgPrettyPrinter.beforeObjectEntries(this);
        }
        char[] asQuotedChars = serializableString.asQuotedChars();
        if (this._cfgUnqNames) {
            writeRaw(asQuotedChars, 0, asQuotedChars.length);
            return;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr = this._outputBuffer;
        int r1 = this._outputTail;
        this._outputTail = r1 + 1;
        cArr[r1] = this._quoteChar;
        writeRaw(asQuotedChars, 0, asQuotedChars.length);
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr2 = this._outputBuffer;
        int r5 = this._outputTail;
        this._outputTail = r5 + 1;
        cArr2[r5] = this._quoteChar;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeString(String str) throws IOException {
        _verifyValueWrite("write a string");
        if (str == null) {
            _writeNull();
            return;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr = this._outputBuffer;
        int r1 = this._outputTail;
        this._outputTail = r1 + 1;
        cArr[r1] = this._quoteChar;
        _writeString(str);
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr2 = this._outputBuffer;
        int r0 = this._outputTail;
        this._outputTail = r0 + 1;
        cArr2[r0] = this._quoteChar;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeString(char[] cArr, int r5, int r6) throws IOException {
        _verifyValueWrite("write a string");
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr2 = this._outputBuffer;
        int r1 = this._outputTail;
        this._outputTail = r1 + 1;
        cArr2[r1] = this._quoteChar;
        _writeString(cArr, r5, r6);
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr3 = this._outputBuffer;
        int r52 = this._outputTail;
        this._outputTail = r52 + 1;
        cArr3[r52] = this._quoteChar;
    }

    @Override // com.fasterxml.jackson.core.base.GeneratorBase, com.fasterxml.jackson.core.JsonGenerator
    public void writeString(SerializableString serializableString) throws IOException {
        _verifyValueWrite("write a string");
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr = this._outputBuffer;
        int r1 = this._outputTail;
        this._outputTail = r1 + 1;
        cArr[r1] = this._quoteChar;
        char[] asQuotedChars = serializableString.asQuotedChars();
        int length = asQuotedChars.length;
        if (length < 32) {
            if (length > this._outputEnd - this._outputTail) {
                _flushBuffer();
            }
            System.arraycopy(asQuotedChars, 0, this._outputBuffer, this._outputTail, length);
            this._outputTail += length;
        } else {
            _flushBuffer();
            this._writer.write(asQuotedChars, 0, length);
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr2 = this._outputBuffer;
        int r0 = this._outputTail;
        this._outputTail = r0 + 1;
        cArr2[r0] = this._quoteChar;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeRawUTF8String(byte[] bArr, int r2, int r3) throws IOException {
        _reportUnsupportedOperation();
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeUTF8String(byte[] bArr, int r2, int r3) throws IOException {
        _reportUnsupportedOperation();
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeRaw(String str) throws IOException {
        int length = str.length();
        int r1 = this._outputEnd - this._outputTail;
        if (r1 == 0) {
            _flushBuffer();
            r1 = this._outputEnd - this._outputTail;
        }
        if (r1 >= length) {
            str.getChars(0, length, this._outputBuffer, this._outputTail);
            this._outputTail += length;
            return;
        }
        writeRawLong(str);
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeRaw(String str, int r5, int r6) throws IOException {
        int r0 = this._outputEnd - this._outputTail;
        if (r0 < r6) {
            _flushBuffer();
            r0 = this._outputEnd - this._outputTail;
        }
        if (r0 >= r6) {
            str.getChars(r5, r5 + r6, this._outputBuffer, this._outputTail);
            this._outputTail += r6;
            return;
        }
        writeRawLong(str.substring(r5, r6 + r5));
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeRaw(SerializableString serializableString) throws IOException {
        writeRaw(serializableString.getValue());
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeRaw(char[] cArr, int r4, int r5) throws IOException {
        if (r5 < 32) {
            if (r5 > this._outputEnd - this._outputTail) {
                _flushBuffer();
            }
            System.arraycopy(cArr, r4, this._outputBuffer, this._outputTail, r5);
            this._outputTail += r5;
            return;
        }
        _flushBuffer();
        this._writer.write(cArr, r4, r5);
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeRaw(char c) throws IOException {
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr = this._outputBuffer;
        int r1 = this._outputTail;
        this._outputTail = r1 + 1;
        cArr[r1] = c;
    }

    private void writeRawLong(String str) throws IOException {
        int r0 = this._outputEnd;
        int r1 = this._outputTail;
        int r02 = r0 - r1;
        str.getChars(0, r02, this._outputBuffer, r1);
        this._outputTail += r02;
        _flushBuffer();
        int length = str.length() - r02;
        while (true) {
            int r2 = this._outputEnd;
            if (length > r2) {
                int r4 = r02 + r2;
                str.getChars(r02, r4, this._outputBuffer, 0);
                this._outputHead = 0;
                this._outputTail = r2;
                _flushBuffer();
                length -= r2;
                r02 = r4;
            } else {
                str.getChars(r02, r02 + length, this._outputBuffer, 0);
                this._outputHead = 0;
                this._outputTail = length;
                return;
            }
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeBinary(Base64Variant base64Variant, byte[] bArr, int r6, int r7) throws IOException, JsonGenerationException {
        _verifyValueWrite("write a binary value");
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr = this._outputBuffer;
        int r1 = this._outputTail;
        this._outputTail = r1 + 1;
        cArr[r1] = this._quoteChar;
        _writeBinary(base64Variant, bArr, r6, r7 + r6);
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr2 = this._outputBuffer;
        int r5 = this._outputTail;
        this._outputTail = r5 + 1;
        cArr2[r5] = this._quoteChar;
    }

    @Override // com.fasterxml.jackson.core.base.GeneratorBase, com.fasterxml.jackson.core.JsonGenerator
    public int writeBinary(Base64Variant base64Variant, InputStream inputStream, int r6) throws IOException, JsonGenerationException {
        _verifyValueWrite("write a binary value");
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr = this._outputBuffer;
        int r1 = this._outputTail;
        this._outputTail = r1 + 1;
        cArr[r1] = this._quoteChar;
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
            char[] cArr2 = this._outputBuffer;
            int r5 = this._outputTail;
            this._outputTail = r5 + 1;
            cArr2[r5] = this._quoteChar;
            return r6;
        } catch (Throwable th) {
            this._ioContext.releaseBase64Buffer(allocBase64Buffer);
            throw th;
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(short s) throws IOException {
        _verifyValueWrite("write a number");
        if (this._cfgNumbersAsStrings) {
            _writeQuotedShort(s);
            return;
        }
        if (this._outputTail + 6 >= this._outputEnd) {
            _flushBuffer();
        }
        this._outputTail = NumberOutput.outputInt(s, this._outputBuffer, this._outputTail);
    }

    private void _writeQuotedShort(short s) throws IOException {
        if (this._outputTail + 8 >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr = this._outputBuffer;
        int r1 = this._outputTail;
        int r2 = r1 + 1;
        this._outputTail = r2;
        cArr[r1] = this._quoteChar;
        int outputInt = NumberOutput.outputInt(s, cArr, r2);
        char[] cArr2 = this._outputBuffer;
        this._outputTail = outputInt + 1;
        cArr2[outputInt] = this._quoteChar;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(int r3) throws IOException {
        _verifyValueWrite("write a number");
        if (this._cfgNumbersAsStrings) {
            _writeQuotedInt(r3);
            return;
        }
        if (this._outputTail + 11 >= this._outputEnd) {
            _flushBuffer();
        }
        this._outputTail = NumberOutput.outputInt(r3, this._outputBuffer, this._outputTail);
    }

    private void _writeQuotedInt(int r5) throws IOException {
        if (this._outputTail + 13 >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr = this._outputBuffer;
        int r1 = this._outputTail;
        int r2 = r1 + 1;
        this._outputTail = r2;
        cArr[r1] = this._quoteChar;
        int outputInt = NumberOutput.outputInt(r5, cArr, r2);
        char[] cArr2 = this._outputBuffer;
        this._outputTail = outputInt + 1;
        cArr2[outputInt] = this._quoteChar;
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

    private void _writeQuotedLong(long j) throws IOException {
        if (this._outputTail + 23 >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr = this._outputBuffer;
        int r1 = this._outputTail;
        int r2 = r1 + 1;
        this._outputTail = r2;
        cArr[r1] = this._quoteChar;
        int outputLong = NumberOutput.outputLong(j, cArr, r2);
        char[] cArr2 = this._outputBuffer;
        this._outputTail = outputLong + 1;
        cArr2[outputLong] = this._quoteChar;
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
        if (this._cfgNumbersAsStrings || (isEnabled(JsonGenerator.Feature.QUOTE_NON_NUMERIC_NUMBERS) && (Double.isNaN(d) || Double.isInfinite(d)))) {
            writeString(String.valueOf(d));
            return;
        }
        _verifyValueWrite("write a number");
        writeRaw(String.valueOf(d));
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(float f) throws IOException {
        if (this._cfgNumbersAsStrings || (isEnabled(JsonGenerator.Feature.QUOTE_NON_NUMERIC_NUMBERS) && (Float.isNaN(f) || Float.isInfinite(f)))) {
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

    private void _writeQuotedRaw(String str) throws IOException {
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr = this._outputBuffer;
        int r1 = this._outputTail;
        this._outputTail = r1 + 1;
        cArr[r1] = this._quoteChar;
        writeRaw(str);
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr2 = this._outputBuffer;
        int r0 = this._outputTail;
        this._outputTail = r0 + 1;
        cArr2[r0] = this._quoteChar;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeBoolean(boolean z) throws IOException {
        int r0;
        _verifyValueWrite("write a boolean value");
        if (this._outputTail + 5 >= this._outputEnd) {
            _flushBuffer();
        }
        int r02 = this._outputTail;
        char[] cArr = this._outputBuffer;
        if (z) {
            cArr[r02] = 't';
            int r03 = r02 + 1;
            cArr[r03] = 'r';
            int r04 = r03 + 1;
            cArr[r04] = 'u';
            r0 = r04 + 1;
            cArr[r0] = 'e';
        } else {
            cArr[r02] = 'f';
            int r05 = r02 + 1;
            cArr[r05] = 'a';
            int r06 = r05 + 1;
            cArr[r06] = 'l';
            int r07 = r06 + 1;
            cArr[r07] = 's';
            r0 = r07 + 1;
            cArr[r0] = 'e';
        }
        this._outputTail = r0 + 1;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNull() throws IOException {
        _verifyValueWrite("write a null");
        _writeNull();
    }

    @Override // com.fasterxml.jackson.core.base.GeneratorBase
    protected void _verifyValueWrite(String str) throws IOException {
        char c;
        int writeValue = this._writeContext.writeValue();
        if (this._cfgPrettyPrinter != null) {
            _verifyPrettyValueWrite(str, writeValue);
            return;
        }
        if (writeValue == 1) {
            c = ',';
        } else if (writeValue != 2) {
            if (writeValue != 3) {
                if (writeValue != 5) {
                    return;
                }
                _reportCantWriteValueExpectName(str);
                return;
            } else if (this._rootValueSeparator != null) {
                writeRaw(this._rootValueSeparator.getValue());
                return;
            } else {
                return;
            }
        } else {
            c = ':';
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr = this._outputBuffer;
        int r1 = this._outputTail;
        this._outputTail = r1 + 1;
        cArr[r1] = c;
    }

    @Override // com.fasterxml.jackson.core.base.GeneratorBase, com.fasterxml.jackson.core.JsonGenerator, java.io.Flushable
    public void flush() throws IOException {
        _flushBuffer();
        if (this._writer == null || !isEnabled(JsonGenerator.Feature.FLUSH_PASSED_TO_STREAM)) {
            return;
        }
        this._writer.flush();
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
        this._outputHead = 0;
        this._outputTail = 0;
        if (this._writer != null) {
            if (this._ioContext.isResourceManaged() || isEnabled(JsonGenerator.Feature.AUTO_CLOSE_TARGET)) {
                this._writer.close();
            } else if (isEnabled(JsonGenerator.Feature.FLUSH_PASSED_TO_STREAM)) {
                this._writer.flush();
            }
        }
        _releaseBuffers();
    }

    @Override // com.fasterxml.jackson.core.base.GeneratorBase
    protected void _releaseBuffers() {
        char[] cArr = this._outputBuffer;
        if (cArr != null) {
            this._outputBuffer = null;
            this._ioContext.releaseConcatBuffer(cArr);
        }
    }

    private void _writeString(String str) throws IOException {
        int length = str.length();
        int r1 = this._outputEnd;
        if (length > r1) {
            _writeLongString(str);
            return;
        }
        if (this._outputTail + length > r1) {
            _flushBuffer();
        }
        str.getChars(0, length, this._outputBuffer, this._outputTail);
        if (this._characterEscapes != null) {
            _writeStringCustom(length);
        } else if (this._maximumNonEscapedChar != 0) {
            _writeStringASCII(length, this._maximumNonEscapedChar);
        } else {
            _writeString2(length);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0019, code lost:
        if (r3 <= 0) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x001b, code lost:
        r6._writer.write(r2, r4, r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0020, code lost:
        r2 = r6._outputBuffer;
        r3 = r6._outputTail;
        r6._outputTail = r3 + 1;
        r2 = r2[r3];
        _prependOrWriteCharacterEscape(r2, r7[r2]);
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0016, code lost:
        r4 = r6._outputHead;
        r3 = r3 - r4;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void _writeString2(int r7) throws java.io.IOException {
        /*
            r6 = this;
            int r0 = r6._outputTail
            int r0 = r0 + r7
            int[] r7 = r6._outputEscapes
            int r1 = r7.length
        L6:
            int r2 = r6._outputTail
            if (r2 >= r0) goto L36
        La:
            char[] r2 = r6._outputBuffer
            int r3 = r6._outputTail
            char r4 = r2[r3]
            if (r4 >= r1) goto L30
            r4 = r7[r4]
            if (r4 == 0) goto L30
            int r4 = r6._outputHead
            int r3 = r3 - r4
            if (r3 <= 0) goto L20
            java.io.Writer r5 = r6._writer
            r5.write(r2, r4, r3)
        L20:
            char[] r2 = r6._outputBuffer
            int r3 = r6._outputTail
            int r4 = r3 + 1
            r6._outputTail = r4
            char r2 = r2[r3]
            r3 = r7[r2]
            r6._prependOrWriteCharacterEscape(r2, r3)
            goto L6
        L30:
            int r3 = r3 + 1
            r6._outputTail = r3
            if (r3 < r0) goto La
        L36:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.WriterBasedJsonGenerator._writeString2(int):void");
    }

    private void _writeLongString(String str) throws IOException {
        _flushBuffer();
        int length = str.length();
        int r2 = 0;
        while (true) {
            int r3 = this._outputEnd;
            if (r2 + r3 > length) {
                r3 = length - r2;
            }
            int r4 = r2 + r3;
            str.getChars(r2, r4, this._outputBuffer, 0);
            if (this._characterEscapes != null) {
                _writeSegmentCustom(r3);
            } else if (this._maximumNonEscapedChar != 0) {
                _writeSegmentASCII(r3, this._maximumNonEscapedChar);
            } else {
                _writeSegment(r3);
            }
            if (r4 >= length) {
                return;
            }
            r2 = r4;
        }
    }

    private void _writeSegment(int r12) throws IOException {
        char[] cArr;
        char c;
        int[] r0 = this._outputEscapes;
        int length = r0.length;
        int r2 = 0;
        int r3 = 0;
        while (r2 < r12) {
            do {
                cArr = this._outputBuffer;
                c = cArr[r2];
                if (c < length && r0[c] != 0) {
                    break;
                }
                r2++;
            } while (r2 < r12);
            int r5 = r2 - r3;
            if (r5 > 0) {
                this._writer.write(cArr, r3, r5);
                if (r2 >= r12) {
                    return;
                }
            }
            r2++;
            r3 = _prependOrWriteCharacterEscape(this._outputBuffer, r2, r12, c, r0[c]);
        }
    }

    private void _writeString(char[] cArr, int r8, int r9) throws IOException {
        if (this._characterEscapes != null) {
            _writeStringCustom(cArr, r8, r9);
        } else if (this._maximumNonEscapedChar != 0) {
            _writeStringASCII(cArr, r8, r9, this._maximumNonEscapedChar);
        } else {
            int r92 = r9 + r8;
            int[] r0 = this._outputEscapes;
            int length = r0.length;
            while (r8 < r92) {
                int r2 = r8;
                do {
                    char c = cArr[r2];
                    if (c < length && r0[c] != 0) {
                        break;
                    }
                    r2++;
                } while (r2 < r92);
                int r3 = r2 - r8;
                if (r3 < 32) {
                    if (this._outputTail + r3 > this._outputEnd) {
                        _flushBuffer();
                    }
                    if (r3 > 0) {
                        System.arraycopy(cArr, r8, this._outputBuffer, this._outputTail, r3);
                        this._outputTail += r3;
                    }
                } else {
                    _flushBuffer();
                    this._writer.write(cArr, r8, r3);
                }
                if (r2 >= r92) {
                    return;
                }
                r8 = r2 + 1;
                char c2 = cArr[r2];
                _appendCharacterEscape(c2, r0[c2]);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x003a A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void _writeStringASCII(int r9, int r10) throws java.io.IOException, com.fasterxml.jackson.core.JsonGenerationException {
        /*
            r8 = this;
            int r0 = r8._outputTail
            int r0 = r0 + r9
            int[] r9 = r8._outputEscapes
            int r1 = r9.length
            int r2 = r10 + 1
            int r1 = java.lang.Math.min(r1, r2)
        Lc:
            int r2 = r8._outputTail
            if (r2 >= r0) goto L3a
        L10:
            char[] r2 = r8._outputBuffer
            int r3 = r8._outputTail
            char r4 = r2[r3]
            if (r4 >= r1) goto L1d
            r5 = r9[r4]
            if (r5 == 0) goto L34
            goto L20
        L1d:
            if (r4 <= r10) goto L34
            r5 = -1
        L20:
            int r6 = r8._outputHead
            int r3 = r3 - r6
            if (r3 <= 0) goto L2a
            java.io.Writer r7 = r8._writer
            r7.write(r2, r6, r3)
        L2a:
            int r2 = r8._outputTail
            int r2 = r2 + 1
            r8._outputTail = r2
            r8._prependOrWriteCharacterEscape(r4, r5)
            goto Lc
        L34:
            int r3 = r3 + 1
            r8._outputTail = r3
            if (r3 < r0) goto L10
        L3a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.WriterBasedJsonGenerator._writeStringASCII(int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0021 A[EDGE_INSN: B:26:0x0021->B:13:0x0021 ?: BREAK  , SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void _writeSegmentASCII(int r13, int r14) throws java.io.IOException, com.fasterxml.jackson.core.JsonGenerationException {
        /*
            r12 = this;
            int[] r0 = r12._outputEscapes
            int r1 = r0.length
            int r2 = r14 + 1
            int r1 = java.lang.Math.min(r1, r2)
            r2 = 0
            r3 = 0
            r4 = 0
        Lc:
            if (r2 >= r13) goto L3a
        Le:
            char[] r5 = r12._outputBuffer
            char r10 = r5[r2]
            if (r10 >= r1) goto L19
            r4 = r0[r10]
            if (r4 == 0) goto L1d
            goto L21
        L19:
            if (r10 <= r14) goto L1d
            r4 = -1
            goto L21
        L1d:
            int r2 = r2 + 1
            if (r2 < r13) goto Le
        L21:
            int r6 = r2 - r3
            if (r6 <= 0) goto L2d
            java.io.Writer r7 = r12._writer
            r7.write(r5, r3, r6)
            if (r2 < r13) goto L2d
            goto L3a
        L2d:
            int r2 = r2 + 1
            char[] r7 = r12._outputBuffer
            r6 = r12
            r8 = r2
            r9 = r13
            r11 = r4
            int r3 = r6._prependOrWriteCharacterEscape(r7, r8, r9, r10, r11)
            goto Lc
        L3a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.WriterBasedJsonGenerator._writeSegmentASCII(int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x001f A[EDGE_INSN: B:30:0x001f->B:14:0x001f ?: BREAK  , SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void _writeStringASCII(char[] r9, int r10, int r11, int r12) throws java.io.IOException, com.fasterxml.jackson.core.JsonGenerationException {
        /*
            r8 = this;
            int r11 = r11 + r10
            int[] r0 = r8._outputEscapes
            int r1 = r0.length
            int r2 = r12 + 1
            int r1 = java.lang.Math.min(r1, r2)
            r2 = 0
        Lb:
            if (r10 >= r11) goto L4f
            r3 = r10
        Le:
            char r4 = r9[r3]
            if (r4 >= r1) goto L17
            r2 = r0[r4]
            if (r2 == 0) goto L1b
            goto L1f
        L17:
            if (r4 <= r12) goto L1b
            r2 = -1
            goto L1f
        L1b:
            int r3 = r3 + 1
            if (r3 < r11) goto Le
        L1f:
            int r5 = r3 - r10
            r6 = 32
            if (r5 >= r6) goto L3e
            int r6 = r8._outputTail
            int r6 = r6 + r5
            int r7 = r8._outputEnd
            if (r6 <= r7) goto L2f
            r8._flushBuffer()
        L2f:
            if (r5 <= 0) goto L46
            char[] r6 = r8._outputBuffer
            int r7 = r8._outputTail
            java.lang.System.arraycopy(r9, r10, r6, r7, r5)
            int r10 = r8._outputTail
            int r10 = r10 + r5
            r8._outputTail = r10
            goto L46
        L3e:
            r8._flushBuffer()
            java.io.Writer r6 = r8._writer
            r6.write(r9, r10, r5)
        L46:
            if (r3 < r11) goto L49
            goto L4f
        L49:
            int r10 = r3 + 1
            r8._appendCharacterEscape(r4, r2)
            goto Lb
        L4f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.WriterBasedJsonGenerator._writeStringASCII(char[], int, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0055 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void _writeStringCustom(int r12) throws java.io.IOException, com.fasterxml.jackson.core.JsonGenerationException {
        /*
            r11 = this;
            int r0 = r11._outputTail
            int r0 = r0 + r12
            int[] r12 = r11._outputEscapes
            int r1 = r11._maximumNonEscapedChar
            r2 = 1
            if (r1 >= r2) goto Le
            r1 = 65535(0xffff, float:9.1834E-41)
            goto L10
        Le:
            int r1 = r11._maximumNonEscapedChar
        L10:
            int r3 = r12.length
            int r4 = r1 + 1
            int r3 = java.lang.Math.min(r3, r4)
            com.fasterxml.jackson.core.io.CharacterEscapes r4 = r11._characterEscapes
        L19:
            int r5 = r11._outputTail
            if (r5 >= r0) goto L55
        L1d:
            char[] r5 = r11._outputBuffer
            int r6 = r11._outputTail
            char r5 = r5[r6]
            if (r5 >= r3) goto L2a
            r6 = r12[r5]
            if (r6 == 0) goto L4e
            goto L37
        L2a:
            if (r5 <= r1) goto L2e
            r6 = -1
            goto L37
        L2e:
            com.fasterxml.jackson.core.SerializableString r6 = r4.getEscapeSequence(r5)
            r11._currentEscape = r6
            if (r6 == 0) goto L4e
            r6 = -2
        L37:
            int r7 = r11._outputTail
            int r8 = r11._outputHead
            int r7 = r7 - r8
            if (r7 <= 0) goto L45
            java.io.Writer r9 = r11._writer
            char[] r10 = r11._outputBuffer
            r9.write(r10, r8, r7)
        L45:
            int r7 = r11._outputTail
            int r7 = r7 + r2
            r11._outputTail = r7
            r11._prependOrWriteCharacterEscape(r5, r6)
            goto L19
        L4e:
            int r5 = r11._outputTail
            int r5 = r5 + r2
            r11._outputTail = r5
            if (r5 < r0) goto L1d
        L55:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.WriterBasedJsonGenerator._writeStringCustom(int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0038 A[EDGE_INSN: B:33:0x0038->B:20:0x0038 ?: BREAK  , SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void _writeSegmentCustom(int r15) throws java.io.IOException, com.fasterxml.jackson.core.JsonGenerationException {
        /*
            r14 = this;
            int[] r0 = r14._outputEscapes
            int r1 = r14._maximumNonEscapedChar
            r2 = 1
            if (r1 >= r2) goto Lb
            r1 = 65535(0xffff, float:9.1834E-41)
            goto Ld
        Lb:
            int r1 = r14._maximumNonEscapedChar
        Ld:
            int r2 = r0.length
            int r3 = r1 + 1
            int r2 = java.lang.Math.min(r2, r3)
            com.fasterxml.jackson.core.io.CharacterEscapes r3 = r14._characterEscapes
            r4 = 0
            r5 = 0
            r6 = 0
        L19:
            if (r4 >= r15) goto L53
        L1b:
            char[] r7 = r14._outputBuffer
            char r12 = r7[r4]
            if (r12 >= r2) goto L26
            r6 = r0[r12]
            if (r6 == 0) goto L34
            goto L38
        L26:
            if (r12 <= r1) goto L2a
            r6 = -1
            goto L38
        L2a:
            com.fasterxml.jackson.core.SerializableString r7 = r3.getEscapeSequence(r12)
            r14._currentEscape = r7
            if (r7 == 0) goto L34
            r6 = -2
            goto L38
        L34:
            int r4 = r4 + 1
            if (r4 < r15) goto L1b
        L38:
            int r7 = r4 - r5
            if (r7 <= 0) goto L46
            java.io.Writer r8 = r14._writer
            char[] r9 = r14._outputBuffer
            r8.write(r9, r5, r7)
            if (r4 < r15) goto L46
            goto L53
        L46:
            int r4 = r4 + 1
            char[] r9 = r14._outputBuffer
            r8 = r14
            r10 = r4
            r11 = r15
            r13 = r6
            int r5 = r8._prependOrWriteCharacterEscape(r9, r10, r11, r12, r13)
            goto L19
        L53:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.WriterBasedJsonGenerator._writeSegmentCustom(int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x0036 A[EDGE_INSN: B:36:0x0036->B:21:0x0036 ?: BREAK  , SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void _writeStringCustom(char[] r11, int r12, int r13) throws java.io.IOException, com.fasterxml.jackson.core.JsonGenerationException {
        /*
            r10 = this;
            int r13 = r13 + r12
            int[] r0 = r10._outputEscapes
            int r1 = r10._maximumNonEscapedChar
            r2 = 1
            if (r1 >= r2) goto Lc
            r1 = 65535(0xffff, float:9.1834E-41)
            goto Le
        Lc:
            int r1 = r10._maximumNonEscapedChar
        Le:
            int r2 = r0.length
            int r3 = r1 + 1
            int r2 = java.lang.Math.min(r2, r3)
            com.fasterxml.jackson.core.io.CharacterEscapes r3 = r10._characterEscapes
            r4 = 0
        L18:
            if (r12 >= r13) goto L66
            r5 = r12
        L1b:
            char r6 = r11[r5]
            if (r6 >= r2) goto L24
            r4 = r0[r6]
            if (r4 == 0) goto L32
            goto L36
        L24:
            if (r6 <= r1) goto L28
            r4 = -1
            goto L36
        L28:
            com.fasterxml.jackson.core.SerializableString r7 = r3.getEscapeSequence(r6)
            r10._currentEscape = r7
            if (r7 == 0) goto L32
            r4 = -2
            goto L36
        L32:
            int r5 = r5 + 1
            if (r5 < r13) goto L1b
        L36:
            int r7 = r5 - r12
            r8 = 32
            if (r7 >= r8) goto L55
            int r8 = r10._outputTail
            int r8 = r8 + r7
            int r9 = r10._outputEnd
            if (r8 <= r9) goto L46
            r10._flushBuffer()
        L46:
            if (r7 <= 0) goto L5d
            char[] r8 = r10._outputBuffer
            int r9 = r10._outputTail
            java.lang.System.arraycopy(r11, r12, r8, r9, r7)
            int r12 = r10._outputTail
            int r12 = r12 + r7
            r10._outputTail = r12
            goto L5d
        L55:
            r10._flushBuffer()
            java.io.Writer r8 = r10._writer
            r8.write(r11, r12, r7)
        L5d:
            if (r5 < r13) goto L60
            goto L66
        L60:
            int r12 = r5 + 1
            r10._appendCharacterEscape(r6, r4)
            goto L18
        L66:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.WriterBasedJsonGenerator._writeStringCustom(char[], int, int):void");
    }

    protected void _writeBinary(Base64Variant base64Variant, byte[] bArr, int r10, int r11) throws IOException, JsonGenerationException {
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
                char[] cArr = this._outputBuffer;
                int r52 = encodeBase64Chunk + 1;
                this._outputTail = r52;
                cArr[encodeBase64Chunk] = IOUtils.DIR_SEPARATOR_WINDOWS;
                this._outputTail = r52 + 1;
                cArr[r52] = 'n';
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

    protected int _writeBinary(Base64Variant base64Variant, InputStream inputStream, byte[] bArr, int r15) throws IOException, JsonGenerationException {
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
                char[] cArr = this._outputBuffer;
                int r6 = encodeBase64Chunk + 1;
                this._outputTail = r6;
                cArr[encodeBase64Chunk] = IOUtils.DIR_SEPARATOR_WINDOWS;
                this._outputTail = r6 + 1;
                cArr[r6] = 'n';
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

    protected int _writeBinary(Base64Variant base64Variant, InputStream inputStream, byte[] bArr) throws IOException, JsonGenerationException {
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
                char[] cArr = this._outputBuffer;
                int r6 = encodeBase64Chunk + 1;
                this._outputTail = r6;
                cArr[encodeBase64Chunk] = IOUtils.DIR_SEPARATOR_WINDOWS;
                this._outputTail = r6 + 1;
                cArr[r6] = 'n';
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

    private int _readMore(InputStream inputStream, byte[] bArr, int r6, int r7, int r8) throws IOException {
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

    private final void _writeNull() throws IOException {
        if (this._outputTail + 4 >= this._outputEnd) {
            _flushBuffer();
        }
        int r0 = this._outputTail;
        char[] cArr = this._outputBuffer;
        cArr[r0] = 'n';
        int r02 = r0 + 1;
        cArr[r02] = 'u';
        int r03 = r02 + 1;
        cArr[r03] = 'l';
        int r04 = r03 + 1;
        cArr[r04] = 'l';
        this._outputTail = r04 + 1;
    }

    private void _prependOrWriteCharacterEscape(char c, int r7) throws IOException, JsonGenerationException {
        String value;
        int r72;
        if (r7 >= 0) {
            int r6 = this._outputTail;
            if (r6 >= 2) {
                int r62 = r6 - 2;
                this._outputHead = r62;
                char[] cArr = this._outputBuffer;
                cArr[r62] = IOUtils.DIR_SEPARATOR_WINDOWS;
                cArr[r62 + 1] = (char) r7;
                return;
            }
            char[] cArr2 = this._entityBuffer;
            if (cArr2 == null) {
                cArr2 = _allocateEntityBuffer();
            }
            this._outputHead = this._outputTail;
            cArr2[1] = (char) r7;
            this._writer.write(cArr2, 0, 2);
        } else if (r7 != -2) {
            int r73 = this._outputTail;
            if (r73 >= 6) {
                char[] cArr3 = this._outputBuffer;
                int r74 = r73 - 6;
                this._outputHead = r74;
                cArr3[r74] = IOUtils.DIR_SEPARATOR_WINDOWS;
                int r75 = r74 + 1;
                cArr3[r75] = 'u';
                if (c > 255) {
                    int r0 = (c >> '\b') & 255;
                    int r76 = r75 + 1;
                    char[] cArr4 = HEX_CHARS;
                    cArr3[r76] = cArr4[r0 >> 4];
                    r72 = r76 + 1;
                    cArr3[r72] = cArr4[r0 & 15];
                    c = (char) (c & 255);
                } else {
                    int r77 = r75 + 1;
                    cArr3[r77] = '0';
                    r72 = r77 + 1;
                    cArr3[r72] = '0';
                }
                int r78 = r72 + 1;
                char[] cArr5 = HEX_CHARS;
                cArr3[r78] = cArr5[c >> 4];
                cArr3[r78 + 1] = cArr5[c & 15];
                return;
            }
            char[] cArr6 = this._entityBuffer;
            if (cArr6 == null) {
                cArr6 = _allocateEntityBuffer();
            }
            this._outputHead = this._outputTail;
            if (c > 255) {
                int r1 = (c >> '\b') & 255;
                int r63 = c & 255;
                char[] cArr7 = HEX_CHARS;
                cArr6[10] = cArr7[r1 >> 4];
                cArr6[11] = cArr7[r1 & 15];
                cArr6[12] = cArr7[r63 >> 4];
                cArr6[13] = cArr7[r63 & 15];
                this._writer.write(cArr6, 8, 6);
                return;
            }
            char[] cArr8 = HEX_CHARS;
            cArr6[6] = cArr8[c >> 4];
            cArr6[7] = cArr8[c & 15];
            this._writer.write(cArr6, 2, 6);
        } else {
            SerializableString serializableString = this._currentEscape;
            if (serializableString == null) {
                value = this._characterEscapes.getEscapeSequence(c).getValue();
            } else {
                value = serializableString.getValue();
                this._currentEscape = null;
            }
            int length = value.length();
            int r12 = this._outputTail;
            if (r12 >= length) {
                int r13 = r12 - length;
                this._outputHead = r13;
                value.getChars(0, length, this._outputBuffer, r13);
                return;
            }
            this._outputHead = r12;
            this._writer.write(value);
        }
    }

    private int _prependOrWriteCharacterEscape(char[] cArr, int r6, int r7, char c, int r9) throws IOException, JsonGenerationException {
        String value;
        int r62;
        if (r9 >= 0) {
            if (r6 > 1 && r6 < r7) {
                int r63 = r6 - 2;
                cArr[r63] = IOUtils.DIR_SEPARATOR_WINDOWS;
                cArr[r63 + 1] = (char) r9;
                return r63;
            }
            char[] cArr2 = this._entityBuffer;
            if (cArr2 == null) {
                cArr2 = _allocateEntityBuffer();
            }
            cArr2[1] = (char) r9;
            this._writer.write(cArr2, 0, 2);
            return r6;
        } else if (r9 == -2) {
            SerializableString serializableString = this._currentEscape;
            if (serializableString == null) {
                value = this._characterEscapes.getEscapeSequence(c).getValue();
            } else {
                value = serializableString.getValue();
                this._currentEscape = null;
            }
            int length = value.length();
            if (r6 >= length && r6 < r7) {
                int r64 = r6 - length;
                value.getChars(0, length, cArr, r64);
                return r64;
            }
            this._writer.write(value);
            return r6;
        } else if (r6 > 5 && r6 < r7) {
            int r65 = r6 - 6;
            int r72 = r65 + 1;
            cArr[r65] = IOUtils.DIR_SEPARATOR_WINDOWS;
            int r66 = r72 + 1;
            cArr[r72] = 'u';
            if (c > 255) {
                int r73 = (c >> '\b') & 255;
                int r92 = r66 + 1;
                char[] cArr3 = HEX_CHARS;
                cArr[r66] = cArr3[r73 >> 4];
                r62 = r92 + 1;
                cArr[r92] = cArr3[r73 & 15];
                c = (char) (c & 255);
            } else {
                int r74 = r66 + 1;
                cArr[r66] = '0';
                r62 = r74 + 1;
                cArr[r74] = '0';
            }
            int r75 = r62 + 1;
            char[] cArr4 = HEX_CHARS;
            cArr[r62] = cArr4[c >> 4];
            cArr[r75] = cArr4[c & 15];
            return r75 - 5;
        } else {
            char[] cArr5 = this._entityBuffer;
            if (cArr5 == null) {
                cArr5 = _allocateEntityBuffer();
            }
            this._outputHead = this._outputTail;
            if (c > 255) {
                int r93 = (c >> '\b') & 255;
                int r8 = c & 255;
                char[] cArr6 = HEX_CHARS;
                cArr5[10] = cArr6[r93 >> 4];
                cArr5[11] = cArr6[r93 & 15];
                cArr5[12] = cArr6[r8 >> 4];
                cArr5[13] = cArr6[r8 & 15];
                this._writer.write(cArr5, 8, 6);
                return r6;
            }
            char[] cArr7 = HEX_CHARS;
            cArr5[6] = cArr7[c >> 4];
            cArr5[7] = cArr7[c & 15];
            this._writer.write(cArr5, 2, 6);
            return r6;
        }
    }

    private void _appendCharacterEscape(char c, int r7) throws IOException, JsonGenerationException {
        String value;
        int r72;
        if (r7 >= 0) {
            if (this._outputTail + 2 > this._outputEnd) {
                _flushBuffer();
            }
            char[] cArr = this._outputBuffer;
            int r1 = this._outputTail;
            int r2 = r1 + 1;
            this._outputTail = r2;
            cArr[r1] = IOUtils.DIR_SEPARATOR_WINDOWS;
            this._outputTail = r2 + 1;
            cArr[r2] = (char) r7;
        } else if (r7 != -2) {
            if (this._outputTail + 5 >= this._outputEnd) {
                _flushBuffer();
            }
            int r73 = this._outputTail;
            char[] cArr2 = this._outputBuffer;
            int r22 = r73 + 1;
            cArr2[r73] = IOUtils.DIR_SEPARATOR_WINDOWS;
            int r74 = r22 + 1;
            cArr2[r22] = 'u';
            if (c > 255) {
                int r0 = 255 & (c >> '\b');
                int r23 = r74 + 1;
                char[] cArr3 = HEX_CHARS;
                cArr2[r74] = cArr3[r0 >> 4];
                r72 = r23 + 1;
                cArr2[r23] = cArr3[r0 & 15];
                c = (char) (c & 255);
            } else {
                int r02 = r74 + 1;
                cArr2[r74] = '0';
                r72 = r02 + 1;
                cArr2[r02] = '0';
            }
            int r03 = r72 + 1;
            char[] cArr4 = HEX_CHARS;
            cArr2[r72] = cArr4[c >> 4];
            cArr2[r03] = cArr4[c & 15];
            this._outputTail = r03 + 1;
        } else {
            SerializableString serializableString = this._currentEscape;
            if (serializableString == null) {
                value = this._characterEscapes.getEscapeSequence(c).getValue();
            } else {
                value = serializableString.getValue();
                this._currentEscape = null;
            }
            int length = value.length();
            if (this._outputTail + length > this._outputEnd) {
                _flushBuffer();
                if (length > this._outputEnd) {
                    this._writer.write(value);
                    return;
                }
            }
            value.getChars(0, length, this._outputBuffer, this._outputTail);
            this._outputTail += length;
        }
    }

    private char[] _allocateEntityBuffer() {
        char[] cArr = {IOUtils.DIR_SEPARATOR_WINDOWS, 0, IOUtils.DIR_SEPARATOR_WINDOWS, 'u', '0', '0', 0, 0, IOUtils.DIR_SEPARATOR_WINDOWS, 'u'};
        this._entityBuffer = cArr;
        return cArr;
    }

    protected void _flushBuffer() throws IOException {
        int r0 = this._outputTail;
        int r1 = this._outputHead;
        int r02 = r0 - r1;
        if (r02 > 0) {
            this._outputHead = 0;
            this._outputTail = 0;
            this._writer.write(this._outputBuffer, r1, r02);
        }
    }
}
