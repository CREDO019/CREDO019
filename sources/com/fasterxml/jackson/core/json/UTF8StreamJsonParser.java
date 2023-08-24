package com.fasterxml.jackson.core.json;

import com.facebook.hermes.intl.Constants;
import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.base.ParserBase;
import com.fasterxml.jackson.core.p009io.CharTypes;
import com.fasterxml.jackson.core.p009io.IOContext;
import com.fasterxml.jackson.core.sym.ByteQuadsCanonicalizer;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.google.android.exoplayer2.analytics.AnalyticsListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Arrays;
import okio.Utf8;
import org.apache.commons.lang3.CharUtils;

/* loaded from: classes.dex */
public class UTF8StreamJsonParser extends ParserBase {
    static final byte BYTE_LF = 10;
    protected boolean _bufferRecyclable;
    protected byte[] _inputBuffer;
    protected InputStream _inputStream;
    protected int _nameStartCol;
    protected int _nameStartOffset;
    protected int _nameStartRow;
    protected ObjectCodec _objectCodec;
    private int _quad1;
    protected int[] _quadBuffer;
    protected final ByteQuadsCanonicalizer _symbols;
    protected boolean _tokenIncomplete;
    private static final int[] _icUTF8 = CharTypes.getInputCodeUtf8();
    protected static final int[] _icLatin1 = CharTypes.getInputCodeLatin1();

    private static final int pad(int r1, int r2) {
        return r2 == 4 ? r1 : r1 | ((-1) << (r2 << 3));
    }

    public UTF8StreamJsonParser(IOContext iOContext, int r2, InputStream inputStream, ObjectCodec objectCodec, ByteQuadsCanonicalizer byteQuadsCanonicalizer, byte[] bArr, int r7, int r8, boolean z) {
        super(iOContext, r2);
        this._quadBuffer = new int[16];
        this._inputStream = inputStream;
        this._objectCodec = objectCodec;
        this._symbols = byteQuadsCanonicalizer;
        this._inputBuffer = bArr;
        this._inputPtr = r7;
        this._inputEnd = r8;
        this._currInputRowStart = r7;
        this._currInputProcessed = -r7;
        this._bufferRecyclable = z;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public ObjectCodec getCodec() {
        return this._objectCodec;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public void setCodec(ObjectCodec objectCodec) {
        this._objectCodec = objectCodec;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public int releaseBuffered(OutputStream outputStream) throws IOException {
        int r0 = this._inputEnd - this._inputPtr;
        if (r0 < 1) {
            return 0;
        }
        outputStream.write(this._inputBuffer, this._inputPtr, r0);
        return r0;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public Object getInputSource() {
        return this._inputStream;
    }

    protected final boolean _loadMore() throws IOException {
        byte[] bArr;
        int length;
        int r0 = this._inputEnd;
        this._currInputProcessed += this._inputEnd;
        this._currInputRowStart -= this._inputEnd;
        this._nameStartOffset -= r0;
        InputStream inputStream = this._inputStream;
        if (inputStream == null || (length = (bArr = this._inputBuffer).length) == 0) {
            return false;
        }
        int read = inputStream.read(bArr, 0, length);
        if (read > 0) {
            this._inputPtr = 0;
            this._inputEnd = read;
            return true;
        }
        _closeInput();
        if (read == 0) {
            throw new IOException("InputStream.read() returned 0 characters when trying to read " + this._inputBuffer.length + " bytes");
        }
        return false;
    }

    protected final boolean _loadToHaveAtLeast(int r9) throws IOException {
        if (this._inputStream == null) {
            return false;
        }
        int r0 = this._inputEnd - this._inputPtr;
        if (r0 > 0 && this._inputPtr > 0) {
            int r2 = this._inputPtr;
            this._currInputProcessed += r2;
            this._currInputRowStart -= r2;
            this._nameStartOffset -= r2;
            byte[] bArr = this._inputBuffer;
            System.arraycopy(bArr, r2, bArr, 0, r0);
            this._inputEnd = r0;
        } else {
            this._inputEnd = 0;
        }
        this._inputPtr = 0;
        while (this._inputEnd < r9) {
            int read = this._inputStream.read(this._inputBuffer, this._inputEnd, this._inputBuffer.length - this._inputEnd);
            if (read < 1) {
                _closeInput();
                if (read != 0) {
                    return false;
                }
                throw new IOException("InputStream.read() returned 0 characters when trying to read " + r0 + " bytes");
            }
            this._inputEnd += read;
        }
        return true;
    }

    @Override // com.fasterxml.jackson.core.base.ParserBase
    protected void _closeInput() throws IOException {
        if (this._inputStream != null) {
            if (this._ioContext.isResourceManaged() || isEnabled(JsonParser.Feature.AUTO_CLOSE_SOURCE)) {
                this._inputStream.close();
            }
            this._inputStream = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.fasterxml.jackson.core.base.ParserBase
    public void _releaseBuffers() throws IOException {
        byte[] bArr;
        super._releaseBuffers();
        this._symbols.release();
        if (!this._bufferRecyclable || (bArr = this._inputBuffer) == null) {
            return;
        }
        this._inputBuffer = ByteArrayBuilder.NO_BYTES;
        this._ioContext.releaseReadIOBuffer(bArr);
    }

    @Override // com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
    public String getText() throws IOException {
        if (this._currToken == JsonToken.VALUE_STRING) {
            if (this._tokenIncomplete) {
                this._tokenIncomplete = false;
                return _finishAndReturnString();
            }
            return this._textBuffer.contentsAsString();
        }
        return _getText2(this._currToken);
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public int getText(Writer writer) throws IOException {
        JsonToken jsonToken = this._currToken;
        if (jsonToken == JsonToken.VALUE_STRING) {
            if (this._tokenIncomplete) {
                this._tokenIncomplete = false;
                _finishString();
            }
            return this._textBuffer.contentsToWriter(writer);
        } else if (jsonToken == JsonToken.FIELD_NAME) {
            String currentName = this._parsingContext.getCurrentName();
            writer.write(currentName);
            return currentName.length();
        } else if (jsonToken != null) {
            if (jsonToken.isNumeric()) {
                return this._textBuffer.contentsToWriter(writer);
            }
            char[] asCharArray = jsonToken.asCharArray();
            writer.write(asCharArray);
            return asCharArray.length;
        } else {
            return 0;
        }
    }

    @Override // com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
    public String getValueAsString() throws IOException {
        if (this._currToken == JsonToken.VALUE_STRING) {
            if (this._tokenIncomplete) {
                this._tokenIncomplete = false;
                return _finishAndReturnString();
            }
            return this._textBuffer.contentsAsString();
        } else if (this._currToken == JsonToken.FIELD_NAME) {
            return getCurrentName();
        } else {
            return super.getValueAsString(null);
        }
    }

    @Override // com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
    public String getValueAsString(String str) throws IOException {
        if (this._currToken == JsonToken.VALUE_STRING) {
            if (this._tokenIncomplete) {
                this._tokenIncomplete = false;
                return _finishAndReturnString();
            }
            return this._textBuffer.contentsAsString();
        } else if (this._currToken == JsonToken.FIELD_NAME) {
            return getCurrentName();
        } else {
            return super.getValueAsString(str);
        }
    }

    @Override // com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
    public int getValueAsInt() throws IOException {
        JsonToken jsonToken = this._currToken;
        if (jsonToken == JsonToken.VALUE_NUMBER_INT || jsonToken == JsonToken.VALUE_NUMBER_FLOAT) {
            if ((this._numTypesValid & 1) == 0) {
                if (this._numTypesValid == 0) {
                    return _parseIntValue();
                }
                if ((this._numTypesValid & 1) == 0) {
                    convertNumberToInt();
                }
            }
            return this._numberInt;
        }
        return super.getValueAsInt(0);
    }

    @Override // com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
    public int getValueAsInt(int r3) throws IOException {
        JsonToken jsonToken = this._currToken;
        if (jsonToken == JsonToken.VALUE_NUMBER_INT || jsonToken == JsonToken.VALUE_NUMBER_FLOAT) {
            if ((this._numTypesValid & 1) == 0) {
                if (this._numTypesValid == 0) {
                    return _parseIntValue();
                }
                if ((this._numTypesValid & 1) == 0) {
                    convertNumberToInt();
                }
            }
            return this._numberInt;
        }
        return super.getValueAsInt(r3);
    }

    protected final String _getText2(JsonToken jsonToken) {
        if (jsonToken == null) {
            return null;
        }
        int m1248id = jsonToken.m1248id();
        if (m1248id != 5) {
            if (m1248id == 6 || m1248id == 7 || m1248id == 8) {
                return this._textBuffer.contentsAsString();
            }
            return jsonToken.asString();
        }
        return this._parsingContext.getCurrentName();
    }

    @Override // com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
    public char[] getTextCharacters() throws IOException {
        if (this._currToken != null) {
            int m1248id = this._currToken.m1248id();
            if (m1248id == 5) {
                if (!this._nameCopied) {
                    String currentName = this._parsingContext.getCurrentName();
                    int length = currentName.length();
                    if (this._nameCopyBuffer == null) {
                        this._nameCopyBuffer = this._ioContext.allocNameCopyBuffer(length);
                    } else if (this._nameCopyBuffer.length < length) {
                        this._nameCopyBuffer = new char[length];
                    }
                    currentName.getChars(0, length, this._nameCopyBuffer, 0);
                    this._nameCopied = true;
                }
                return this._nameCopyBuffer;
            }
            if (m1248id != 6) {
                if (m1248id != 7 && m1248id != 8) {
                    return this._currToken.asCharArray();
                }
            } else if (this._tokenIncomplete) {
                this._tokenIncomplete = false;
                _finishString();
            }
            return this._textBuffer.getTextBuffer();
        }
        return null;
    }

    @Override // com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
    public int getTextLength() throws IOException {
        if (this._currToken != null) {
            int m1248id = this._currToken.m1248id();
            if (m1248id == 5) {
                return this._parsingContext.getCurrentName().length();
            }
            if (m1248id != 6) {
                if (m1248id != 7 && m1248id != 8) {
                    return this._currToken.asCharArray().length;
                }
            } else if (this._tokenIncomplete) {
                this._tokenIncomplete = false;
                _finishString();
            }
            return this._textBuffer.size();
        }
        return 0;
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x0013, code lost:
        if (r0 != 8) goto L15;
     */
    @Override // com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int getTextOffset() throws java.io.IOException {
        /*
            r3 = this;
            com.fasterxml.jackson.core.JsonToken r0 = r3._currToken
            r1 = 0
            if (r0 == 0) goto L26
            com.fasterxml.jackson.core.JsonToken r0 = r3._currToken
            int r0 = r0.m1248id()
            r2 = 6
            if (r0 == r2) goto L16
            r2 = 7
            if (r0 == r2) goto L1f
            r2 = 8
            if (r0 == r2) goto L1f
            goto L26
        L16:
            boolean r0 = r3._tokenIncomplete
            if (r0 == 0) goto L1f
            r3._tokenIncomplete = r1
            r3._finishString()
        L1f:
            com.fasterxml.jackson.core.util.TextBuffer r0 = r3._textBuffer
            int r0 = r0.getTextOffset()
            return r0
        L26:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.UTF8StreamJsonParser.getTextOffset():int");
    }

    @Override // com.fasterxml.jackson.core.base.ParserBase, com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
    public byte[] getBinaryValue(Base64Variant base64Variant) throws IOException {
        if (this._currToken != JsonToken.VALUE_STRING && (this._currToken != JsonToken.VALUE_EMBEDDED_OBJECT || this._binaryValue == null)) {
            _reportError("Current token (" + this._currToken + ") not VALUE_STRING or VALUE_EMBEDDED_OBJECT, can not access as binary");
        }
        if (this._tokenIncomplete) {
            try {
                this._binaryValue = _decodeBase64(base64Variant);
                this._tokenIncomplete = false;
            } catch (IllegalArgumentException e) {
                throw _constructError("Failed to decode VALUE_STRING as base64 (" + base64Variant + "): " + e.getMessage());
            }
        } else if (this._binaryValue == null) {
            ByteArrayBuilder _getByteArrayBuilder = _getByteArrayBuilder();
            _decodeBase64(getText(), _getByteArrayBuilder, base64Variant);
            this._binaryValue = _getByteArrayBuilder.toByteArray();
        }
        return this._binaryValue;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public int readBinaryValue(Base64Variant base64Variant, OutputStream outputStream) throws IOException {
        if (!this._tokenIncomplete || this._currToken != JsonToken.VALUE_STRING) {
            byte[] binaryValue = getBinaryValue(base64Variant);
            outputStream.write(binaryValue);
            return binaryValue.length;
        }
        byte[] allocBase64Buffer = this._ioContext.allocBase64Buffer();
        try {
            return _readBinary(base64Variant, outputStream, allocBase64Buffer);
        } finally {
            this._ioContext.releaseBase64Buffer(allocBase64Buffer);
        }
    }

    protected int _readBinary(Base64Variant base64Variant, OutputStream outputStream, byte[] bArr) throws IOException {
        int r6;
        int length = bArr.length - 3;
        int r3 = 0;
        int r4 = 0;
        while (true) {
            if (this._inputPtr >= this._inputEnd) {
                _loadMoreGuaranteed();
            }
            byte[] bArr2 = this._inputBuffer;
            int r62 = this._inputPtr;
            this._inputPtr = r62 + 1;
            int r5 = bArr2[r62] & 255;
            if (r5 > 32) {
                int decodeBase64Char = base64Variant.decodeBase64Char(r5);
                if (decodeBase64Char < 0) {
                    if (r5 == 34) {
                        break;
                    }
                    decodeBase64Char = _decodeBase64Escape(base64Variant, r5, 0);
                    if (decodeBase64Char < 0) {
                        continue;
                    }
                }
                if (r3 > length) {
                    r4 += r3;
                    outputStream.write(bArr, 0, r3);
                    r3 = 0;
                }
                if (this._inputPtr >= this._inputEnd) {
                    _loadMoreGuaranteed();
                }
                byte[] bArr3 = this._inputBuffer;
                int r8 = this._inputPtr;
                this._inputPtr = r8 + 1;
                int r52 = bArr3[r8] & 255;
                int decodeBase64Char2 = base64Variant.decodeBase64Char(r52);
                if (decodeBase64Char2 < 0) {
                    decodeBase64Char2 = _decodeBase64Escape(base64Variant, r52, 1);
                }
                int r53 = (decodeBase64Char << 6) | decodeBase64Char2;
                if (this._inputPtr >= this._inputEnd) {
                    _loadMoreGuaranteed();
                }
                byte[] bArr4 = this._inputBuffer;
                int r82 = this._inputPtr;
                this._inputPtr = r82 + 1;
                int r63 = bArr4[r82] & 255;
                int decodeBase64Char3 = base64Variant.decodeBase64Char(r63);
                if (decodeBase64Char3 < 0) {
                    if (decodeBase64Char3 != -2) {
                        if (r63 == 34 && !base64Variant.usesPadding()) {
                            bArr[r3] = (byte) (r53 >> 4);
                            r3++;
                            break;
                        }
                        decodeBase64Char3 = _decodeBase64Escape(base64Variant, r63, 2);
                    }
                    if (decodeBase64Char3 == -2) {
                        if (this._inputPtr >= this._inputEnd) {
                            _loadMoreGuaranteed();
                        }
                        byte[] bArr5 = this._inputBuffer;
                        int r7 = this._inputPtr;
                        this._inputPtr = r7 + 1;
                        int r64 = bArr5[r7] & 255;
                        if (!base64Variant.usesPaddingChar(r64)) {
                            throw reportInvalidBase64Char(base64Variant, r64, 3, "expected padding character '" + base64Variant.getPaddingChar() + "'");
                        }
                        r6 = r3 + 1;
                        bArr[r3] = (byte) (r53 >> 4);
                        r3 = r6;
                    }
                }
                int r54 = (r53 << 6) | decodeBase64Char3;
                if (this._inputPtr >= this._inputEnd) {
                    _loadMoreGuaranteed();
                }
                byte[] bArr6 = this._inputBuffer;
                int r83 = this._inputPtr;
                this._inputPtr = r83 + 1;
                int r65 = bArr6[r83] & 255;
                int decodeBase64Char4 = base64Variant.decodeBase64Char(r65);
                if (decodeBase64Char4 < 0) {
                    if (decodeBase64Char4 != -2) {
                        if (r65 == 34 && !base64Variant.usesPadding()) {
                            int r13 = r54 >> 2;
                            int r0 = r3 + 1;
                            bArr[r3] = (byte) (r13 >> 8);
                            r3 = r0 + 1;
                            bArr[r0] = (byte) r13;
                            break;
                        }
                        decodeBase64Char4 = _decodeBase64Escape(base64Variant, r65, 3);
                    }
                    if (decodeBase64Char4 == -2) {
                        int r55 = r54 >> 2;
                        int r66 = r3 + 1;
                        bArr[r3] = (byte) (r55 >> 8);
                        r3 = r66 + 1;
                        bArr[r66] = (byte) r55;
                    }
                }
                int r56 = (r54 << 6) | decodeBase64Char4;
                int r67 = r3 + 1;
                bArr[r3] = (byte) (r56 >> 16);
                int r32 = r67 + 1;
                bArr[r67] = (byte) (r56 >> 8);
                r6 = r32 + 1;
                bArr[r32] = (byte) r56;
                r3 = r6;
            }
        }
        this._tokenIncomplete = false;
        if (r3 > 0) {
            int r42 = r4 + r3;
            outputStream.write(bArr, 0, r3);
            return r42;
        }
        return r4;
    }

    @Override // com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
    public JsonToken nextToken() throws IOException {
        JsonToken _parseNegNumber;
        if (this._currToken == JsonToken.FIELD_NAME) {
            return _nextAfterName();
        }
        this._numTypesValid = 0;
        if (this._tokenIncomplete) {
            _skipString();
        }
        int _skipWSOrEnd = _skipWSOrEnd();
        if (_skipWSOrEnd < 0) {
            close();
            this._currToken = null;
            return null;
        }
        this._binaryValue = null;
        if (_skipWSOrEnd == 93) {
            _updateLocation();
            if (!this._parsingContext.inArray()) {
                _reportMismatchedEndMarker(_skipWSOrEnd, '}');
            }
            this._parsingContext = this._parsingContext.clearAndGetParent();
            JsonToken jsonToken = JsonToken.END_ARRAY;
            this._currToken = jsonToken;
            return jsonToken;
        } else if (_skipWSOrEnd == 125) {
            _updateLocation();
            if (!this._parsingContext.inObject()) {
                _reportMismatchedEndMarker(_skipWSOrEnd, ']');
            }
            this._parsingContext = this._parsingContext.clearAndGetParent();
            JsonToken jsonToken2 = JsonToken.END_OBJECT;
            this._currToken = jsonToken2;
            return jsonToken2;
        } else {
            if (this._parsingContext.expectComma()) {
                if (_skipWSOrEnd != 44) {
                    _reportUnexpectedChar(_skipWSOrEnd, "was expecting comma to separate " + this._parsingContext.typeDesc() + " entries");
                }
                _skipWSOrEnd = _skipWS();
            }
            if (!this._parsingContext.inObject()) {
                _updateLocation();
                return _nextTokenNotInObject(_skipWSOrEnd);
            }
            _updateNameLocation();
            this._parsingContext.setCurrentName(_parseName(_skipWSOrEnd));
            this._currToken = JsonToken.FIELD_NAME;
            int _skipColon = _skipColon();
            _updateLocation();
            if (_skipColon == 34) {
                this._tokenIncomplete = true;
                this._nextToken = JsonToken.VALUE_STRING;
                return this._currToken;
            }
            if (_skipColon == 45) {
                _parseNegNumber = _parseNegNumber();
            } else if (_skipColon == 91) {
                _parseNegNumber = JsonToken.START_ARRAY;
            } else if (_skipColon == 102) {
                _matchToken(Constants.CASEFIRST_FALSE, 1);
                _parseNegNumber = JsonToken.VALUE_FALSE;
            } else if (_skipColon == 110) {
                _matchToken("null", 1);
                _parseNegNumber = JsonToken.VALUE_NULL;
            } else if (_skipColon == 116) {
                _matchToken("true", 1);
                _parseNegNumber = JsonToken.VALUE_TRUE;
            } else if (_skipColon != 123) {
                switch (_skipColon) {
                    case 48:
                    case 49:
                    case 50:
                    case 51:
                    case 52:
                    case 53:
                    case 54:
                    case 55:
                    case 56:
                    case 57:
                        _parseNegNumber = _parsePosNumber(_skipColon);
                        break;
                    default:
                        _parseNegNumber = _handleUnexpectedValue(_skipColon);
                        break;
                }
            } else {
                _parseNegNumber = JsonToken.START_OBJECT;
            }
            this._nextToken = _parseNegNumber;
            return this._currToken;
        }
    }

    private final JsonToken _nextTokenNotInObject(int r3) throws IOException {
        if (r3 == 34) {
            this._tokenIncomplete = true;
            JsonToken jsonToken = JsonToken.VALUE_STRING;
            this._currToken = jsonToken;
            return jsonToken;
        } else if (r3 == 45) {
            JsonToken _parseNegNumber = _parseNegNumber();
            this._currToken = _parseNegNumber;
            return _parseNegNumber;
        } else if (r3 == 91) {
            this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
            JsonToken jsonToken2 = JsonToken.START_ARRAY;
            this._currToken = jsonToken2;
            return jsonToken2;
        } else if (r3 == 102) {
            _matchToken(Constants.CASEFIRST_FALSE, 1);
            JsonToken jsonToken3 = JsonToken.VALUE_FALSE;
            this._currToken = jsonToken3;
            return jsonToken3;
        } else if (r3 == 110) {
            _matchToken("null", 1);
            JsonToken jsonToken4 = JsonToken.VALUE_NULL;
            this._currToken = jsonToken4;
            return jsonToken4;
        } else if (r3 == 116) {
            _matchToken("true", 1);
            JsonToken jsonToken5 = JsonToken.VALUE_TRUE;
            this._currToken = jsonToken5;
            return jsonToken5;
        } else if (r3 == 123) {
            this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
            JsonToken jsonToken6 = JsonToken.START_OBJECT;
            this._currToken = jsonToken6;
            return jsonToken6;
        } else {
            switch (r3) {
                case 48:
                case 49:
                case 50:
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                    JsonToken _parsePosNumber = _parsePosNumber(r3);
                    this._currToken = _parsePosNumber;
                    return _parsePosNumber;
                default:
                    JsonToken _handleUnexpectedValue = _handleUnexpectedValue(r3);
                    this._currToken = _handleUnexpectedValue;
                    return _handleUnexpectedValue;
            }
        }
    }

    private final JsonToken _nextAfterName() {
        this._nameCopied = false;
        JsonToken jsonToken = this._nextToken;
        this._nextToken = null;
        if (jsonToken == JsonToken.START_ARRAY) {
            this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
        } else if (jsonToken == JsonToken.START_OBJECT) {
            this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
        }
        this._currToken = jsonToken;
        return jsonToken;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public void finishToken() throws IOException {
        if (this._tokenIncomplete) {
            this._tokenIncomplete = false;
            _finishString();
        }
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public boolean nextFieldName(SerializableString serializableString) throws IOException {
        int r0 = 0;
        this._numTypesValid = 0;
        if (this._currToken == JsonToken.FIELD_NAME) {
            _nextAfterName();
            return false;
        }
        if (this._tokenIncomplete) {
            _skipString();
        }
        int _skipWSOrEnd = _skipWSOrEnd();
        if (_skipWSOrEnd < 0) {
            close();
            this._currToken = null;
            return false;
        }
        this._binaryValue = null;
        if (_skipWSOrEnd == 93) {
            _updateLocation();
            if (!this._parsingContext.inArray()) {
                _reportMismatchedEndMarker(_skipWSOrEnd, '}');
            }
            this._parsingContext = this._parsingContext.clearAndGetParent();
            this._currToken = JsonToken.END_ARRAY;
            return false;
        } else if (_skipWSOrEnd == 125) {
            _updateLocation();
            if (!this._parsingContext.inObject()) {
                _reportMismatchedEndMarker(_skipWSOrEnd, ']');
            }
            this._parsingContext = this._parsingContext.clearAndGetParent();
            this._currToken = JsonToken.END_OBJECT;
            return false;
        } else {
            if (this._parsingContext.expectComma()) {
                if (_skipWSOrEnd != 44) {
                    _reportUnexpectedChar(_skipWSOrEnd, "was expecting comma to separate " + this._parsingContext.typeDesc() + " entries");
                }
                _skipWSOrEnd = _skipWS();
            }
            if (!this._parsingContext.inObject()) {
                _updateLocation();
                _nextTokenNotInObject(_skipWSOrEnd);
                return false;
            }
            _updateNameLocation();
            if (_skipWSOrEnd == 34) {
                byte[] asQuotedUTF8 = serializableString.asQuotedUTF8();
                int length = asQuotedUTF8.length;
                if (this._inputPtr + length + 4 < this._inputEnd) {
                    int r5 = this._inputPtr + length;
                    if (this._inputBuffer[r5] == 34) {
                        int r2 = this._inputPtr;
                        while (r2 != r5) {
                            if (asQuotedUTF8[r0] == this._inputBuffer[r2]) {
                                r0++;
                                r2++;
                            }
                        }
                        this._parsingContext.setCurrentName(serializableString.getValue());
                        _isNextTokenNameYes(_skipColonFast(r2 + 1));
                        return true;
                    }
                }
            }
            return _isNextTokenNameMaybe(_skipWSOrEnd, serializableString);
        }
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public String nextFieldName() throws IOException {
        JsonToken _parseNegNumber;
        this._numTypesValid = 0;
        if (this._currToken == JsonToken.FIELD_NAME) {
            _nextAfterName();
            return null;
        }
        if (this._tokenIncomplete) {
            _skipString();
        }
        int _skipWSOrEnd = _skipWSOrEnd();
        if (_skipWSOrEnd < 0) {
            close();
            this._currToken = null;
            return null;
        }
        this._binaryValue = null;
        if (_skipWSOrEnd == 93) {
            _updateLocation();
            if (!this._parsingContext.inArray()) {
                _reportMismatchedEndMarker(_skipWSOrEnd, '}');
            }
            this._parsingContext = this._parsingContext.clearAndGetParent();
            this._currToken = JsonToken.END_ARRAY;
            return null;
        } else if (_skipWSOrEnd == 125) {
            _updateLocation();
            if (!this._parsingContext.inObject()) {
                _reportMismatchedEndMarker(_skipWSOrEnd, ']');
            }
            this._parsingContext = this._parsingContext.clearAndGetParent();
            this._currToken = JsonToken.END_OBJECT;
            return null;
        } else {
            if (this._parsingContext.expectComma()) {
                if (_skipWSOrEnd != 44) {
                    _reportUnexpectedChar(_skipWSOrEnd, "was expecting comma to separate " + this._parsingContext.typeDesc() + " entries");
                }
                _skipWSOrEnd = _skipWS();
            }
            if (!this._parsingContext.inObject()) {
                _updateLocation();
                _nextTokenNotInObject(_skipWSOrEnd);
                return null;
            }
            _updateNameLocation();
            String _parseName = _parseName(_skipWSOrEnd);
            this._parsingContext.setCurrentName(_parseName);
            this._currToken = JsonToken.FIELD_NAME;
            int _skipColon = _skipColon();
            _updateLocation();
            if (_skipColon == 34) {
                this._tokenIncomplete = true;
                this._nextToken = JsonToken.VALUE_STRING;
                return _parseName;
            }
            if (_skipColon == 45) {
                _parseNegNumber = _parseNegNumber();
            } else if (_skipColon == 91) {
                _parseNegNumber = JsonToken.START_ARRAY;
            } else if (_skipColon == 102) {
                _matchToken(Constants.CASEFIRST_FALSE, 1);
                _parseNegNumber = JsonToken.VALUE_FALSE;
            } else if (_skipColon == 110) {
                _matchToken("null", 1);
                _parseNegNumber = JsonToken.VALUE_NULL;
            } else if (_skipColon == 116) {
                _matchToken("true", 1);
                _parseNegNumber = JsonToken.VALUE_TRUE;
            } else if (_skipColon != 123) {
                switch (_skipColon) {
                    case 48:
                    case 49:
                    case 50:
                    case 51:
                    case 52:
                    case 53:
                    case 54:
                    case 55:
                    case 56:
                    case 57:
                        _parseNegNumber = _parsePosNumber(_skipColon);
                        break;
                    default:
                        _parseNegNumber = _handleUnexpectedValue(_skipColon);
                        break;
                }
            } else {
                _parseNegNumber = JsonToken.START_OBJECT;
            }
            this._nextToken = _parseNegNumber;
            return _parseName;
        }
    }

    private final int _skipColonFast(int r10) throws IOException {
        byte[] bArr = this._inputBuffer;
        int r1 = r10 + 1;
        byte b = bArr[r10];
        if (b == 58) {
            int r102 = r1 + 1;
            byte b2 = bArr[r1];
            if (b2 > 32) {
                if (b2 != 47 && b2 != 35) {
                    this._inputPtr = r102;
                    return b2;
                }
            } else if (b2 == 32 || b2 == 9) {
                int r12 = r102 + 1;
                byte b3 = bArr[r102];
                if (b3 > 32 && b3 != 47 && b3 != 35) {
                    this._inputPtr = r12;
                    return b3;
                }
                r102 = r12;
            }
            this._inputPtr = r102 - 1;
            return _skipColon2(true);
        }
        if (b == 32 || b == 9) {
            int r103 = r1 + 1;
            byte b4 = bArr[r1];
            r1 = r103;
            b = b4;
        }
        if (b == 58) {
            int r104 = r1 + 1;
            byte b5 = bArr[r1];
            if (b5 > 32) {
                if (b5 != 47 && b5 != 35) {
                    this._inputPtr = r104;
                    return b5;
                }
            } else if (b5 == 32 || b5 == 9) {
                int r13 = r104 + 1;
                byte b6 = bArr[r104];
                if (b6 > 32 && b6 != 47 && b6 != 35) {
                    this._inputPtr = r13;
                    return b6;
                }
                r104 = r13;
            }
            this._inputPtr = r104 - 1;
            return _skipColon2(true);
        }
        this._inputPtr = r1 - 1;
        return _skipColon2(false);
    }

    private final void _isNextTokenNameYes(int r3) throws IOException {
        this._currToken = JsonToken.FIELD_NAME;
        _updateLocation();
        if (r3 == 34) {
            this._tokenIncomplete = true;
            this._nextToken = JsonToken.VALUE_STRING;
        } else if (r3 == 45) {
            this._nextToken = _parseNegNumber();
        } else if (r3 == 91) {
            this._nextToken = JsonToken.START_ARRAY;
        } else if (r3 == 102) {
            _matchToken(Constants.CASEFIRST_FALSE, 1);
            this._nextToken = JsonToken.VALUE_FALSE;
        } else if (r3 == 110) {
            _matchToken("null", 1);
            this._nextToken = JsonToken.VALUE_NULL;
        } else if (r3 == 116) {
            _matchToken("true", 1);
            this._nextToken = JsonToken.VALUE_TRUE;
        } else if (r3 == 123) {
            this._nextToken = JsonToken.START_OBJECT;
        } else {
            switch (r3) {
                case 48:
                case 49:
                case 50:
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                    this._nextToken = _parsePosNumber(r3);
                    return;
                default:
                    this._nextToken = _handleUnexpectedValue(r3);
                    return;
            }
        }
    }

    private final boolean _isNextTokenNameMaybe(int r3, SerializableString serializableString) throws IOException {
        JsonToken _parseNegNumber;
        String _parseName = _parseName(r3);
        this._parsingContext.setCurrentName(_parseName);
        boolean equals = _parseName.equals(serializableString.getValue());
        this._currToken = JsonToken.FIELD_NAME;
        int _skipColon = _skipColon();
        _updateLocation();
        if (_skipColon == 34) {
            this._tokenIncomplete = true;
            this._nextToken = JsonToken.VALUE_STRING;
            return equals;
        }
        if (_skipColon == 45) {
            _parseNegNumber = _parseNegNumber();
        } else if (_skipColon == 91) {
            _parseNegNumber = JsonToken.START_ARRAY;
        } else if (_skipColon == 102) {
            _matchToken(Constants.CASEFIRST_FALSE, 1);
            _parseNegNumber = JsonToken.VALUE_FALSE;
        } else if (_skipColon == 110) {
            _matchToken("null", 1);
            _parseNegNumber = JsonToken.VALUE_NULL;
        } else if (_skipColon == 116) {
            _matchToken("true", 1);
            _parseNegNumber = JsonToken.VALUE_TRUE;
        } else if (_skipColon == 123) {
            _parseNegNumber = JsonToken.START_OBJECT;
        } else {
            switch (_skipColon) {
                case 48:
                case 49:
                case 50:
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                    _parseNegNumber = _parsePosNumber(_skipColon);
                    break;
                default:
                    _parseNegNumber = _handleUnexpectedValue(_skipColon);
                    break;
            }
        }
        this._nextToken = _parseNegNumber;
        return equals;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public String nextTextValue() throws IOException {
        if (this._currToken == JsonToken.FIELD_NAME) {
            this._nameCopied = false;
            JsonToken jsonToken = this._nextToken;
            this._nextToken = null;
            this._currToken = jsonToken;
            if (jsonToken == JsonToken.VALUE_STRING) {
                if (this._tokenIncomplete) {
                    this._tokenIncomplete = false;
                    return _finishAndReturnString();
                }
                return this._textBuffer.contentsAsString();
            }
            if (jsonToken == JsonToken.START_ARRAY) {
                this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
            } else if (jsonToken == JsonToken.START_OBJECT) {
                this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
            }
            return null;
        } else if (nextToken() == JsonToken.VALUE_STRING) {
            return getText();
        } else {
            return null;
        }
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public int nextIntValue(int r4) throws IOException {
        if (this._currToken != JsonToken.FIELD_NAME) {
            return nextToken() == JsonToken.VALUE_NUMBER_INT ? getIntValue() : r4;
        }
        this._nameCopied = false;
        JsonToken jsonToken = this._nextToken;
        this._nextToken = null;
        this._currToken = jsonToken;
        if (jsonToken == JsonToken.VALUE_NUMBER_INT) {
            return getIntValue();
        }
        if (jsonToken == JsonToken.START_ARRAY) {
            this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
        } else if (jsonToken == JsonToken.START_OBJECT) {
            this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
        }
        return r4;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public long nextLongValue(long j) throws IOException {
        if (this._currToken != JsonToken.FIELD_NAME) {
            return nextToken() == JsonToken.VALUE_NUMBER_INT ? getLongValue() : j;
        }
        this._nameCopied = false;
        JsonToken jsonToken = this._nextToken;
        this._nextToken = null;
        this._currToken = jsonToken;
        if (jsonToken == JsonToken.VALUE_NUMBER_INT) {
            return getLongValue();
        }
        if (jsonToken == JsonToken.START_ARRAY) {
            this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
        } else if (jsonToken == JsonToken.START_OBJECT) {
            this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
        }
        return j;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public Boolean nextBooleanValue() throws IOException {
        if (this._currToken == JsonToken.FIELD_NAME) {
            this._nameCopied = false;
            JsonToken jsonToken = this._nextToken;
            this._nextToken = null;
            this._currToken = jsonToken;
            if (jsonToken == JsonToken.VALUE_TRUE) {
                return Boolean.TRUE;
            }
            if (jsonToken == JsonToken.VALUE_FALSE) {
                return Boolean.FALSE;
            }
            if (jsonToken == JsonToken.START_ARRAY) {
                this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
            } else if (jsonToken == JsonToken.START_OBJECT) {
                this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
            }
            return null;
        }
        JsonToken nextToken = nextToken();
        if (nextToken == JsonToken.VALUE_TRUE) {
            return Boolean.TRUE;
        }
        if (nextToken == JsonToken.VALUE_FALSE) {
            return Boolean.FALSE;
        }
        return null;
    }

    protected JsonToken _parsePosNumber(int r10) throws IOException {
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        if (r10 == 48) {
            r10 = _verifyNoLeadingZeroes();
        }
        emptyAndGetCurrentSegment[0] = (char) r10;
        int length = (this._inputPtr + emptyAndGetCurrentSegment.length) - 1;
        if (length > this._inputEnd) {
            length = this._inputEnd;
        }
        int r4 = 1;
        int r6 = 1;
        while (this._inputPtr < length) {
            byte[] bArr = this._inputBuffer;
            int r7 = this._inputPtr;
            this._inputPtr = r7 + 1;
            int r5 = bArr[r7] & 255;
            if (r5 < 48 || r5 > 57) {
                if (r5 == 46 || r5 == 101 || r5 == 69) {
                    return _parseFloat(emptyAndGetCurrentSegment, r4, r5, false, r6);
                }
                this._inputPtr--;
                this._textBuffer.setCurrentLength(r4);
                if (this._parsingContext.inRoot()) {
                    _verifyRootSpace(r5);
                }
                return resetInt(false, r6);
            }
            r6++;
            emptyAndGetCurrentSegment[r4] = (char) r5;
            r4++;
        }
        return _parseNumber2(emptyAndGetCurrentSegment, r4, false, r6);
    }

    protected JsonToken _parseNegNumber() throws IOException {
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        emptyAndGetCurrentSegment[0] = '-';
        if (this._inputPtr >= this._inputEnd) {
            _loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int r1 = this._inputPtr;
        this._inputPtr = r1 + 1;
        int r0 = bArr[r1] & 255;
        if (r0 < 48 || r0 > 57) {
            return _handleInvalidNumberStart(r0, true);
        }
        if (r0 == 48) {
            r0 = _verifyNoLeadingZeroes();
        }
        int r5 = 2;
        emptyAndGetCurrentSegment[1] = (char) r0;
        int length = (this._inputPtr + emptyAndGetCurrentSegment.length) - 2;
        if (length > this._inputEnd) {
            length = this._inputEnd;
        }
        int r6 = 1;
        while (this._inputPtr < length) {
            byte[] bArr2 = this._inputBuffer;
            int r8 = this._inputPtr;
            this._inputPtr = r8 + 1;
            int r7 = bArr2[r8] & 255;
            if (r7 < 48 || r7 > 57) {
                if (r7 == 46 || r7 == 101 || r7 == 69) {
                    return _parseFloat(emptyAndGetCurrentSegment, r5, r7, true, r6);
                }
                this._inputPtr--;
                this._textBuffer.setCurrentLength(r5);
                if (this._parsingContext.inRoot()) {
                    _verifyRootSpace(r7);
                }
                return resetInt(true, r6);
            }
            r6++;
            emptyAndGetCurrentSegment[r5] = (char) r7;
            r5++;
        }
        return _parseNumber2(emptyAndGetCurrentSegment, r5, true, r6);
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0044, code lost:
        if (r3 == 46) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0048, code lost:
        if (r3 == 101) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x004c, code lost:
        if (r3 != 69) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x004f, code lost:
        r6._inputPtr--;
        r6._textBuffer.setCurrentLength(r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0060, code lost:
        if (r6._parsingContext.inRoot() == false) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0062, code lost:
        r7 = r6._inputBuffer;
        r8 = r6._inputPtr;
        r6._inputPtr = r8 + 1;
        _verifyRootSpace(r7[r8] & 255);
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0075, code lost:
        return resetInt(r9, r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x007c, code lost:
        return _parseFloat(r1, r2, r3, r9, r5);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final com.fasterxml.jackson.core.JsonToken _parseNumber2(char[] r7, int r8, boolean r9, int r10) throws java.io.IOException {
        /*
            r6 = this;
            r1 = r7
            r2 = r8
            r5 = r10
        L3:
            int r7 = r6._inputPtr
            int r8 = r6._inputEnd
            if (r7 < r8) goto L19
            boolean r7 = r6._loadMore()
            if (r7 != 0) goto L19
            com.fasterxml.jackson.core.util.TextBuffer r7 = r6._textBuffer
            r7.setCurrentLength(r2)
            com.fasterxml.jackson.core.JsonToken r7 = r6.resetInt(r9, r5)
            return r7
        L19:
            byte[] r7 = r6._inputBuffer
            int r8 = r6._inputPtr
            int r10 = r8 + 1
            r6._inputPtr = r10
            r7 = r7[r8]
            r3 = r7 & 255(0xff, float:3.57E-43)
            r7 = 57
            if (r3 > r7) goto L42
            r7 = 48
            if (r3 >= r7) goto L2e
            goto L42
        L2e:
            int r7 = r1.length
            if (r2 < r7) goto L39
            com.fasterxml.jackson.core.util.TextBuffer r7 = r6._textBuffer
            char[] r7 = r7.finishCurrentSegment()
            r2 = 0
            r1 = r7
        L39:
            int r7 = r2 + 1
            char r8 = (char) r3
            r1[r2] = r8
            int r5 = r5 + 1
            r2 = r7
            goto L3
        L42:
            r7 = 46
            if (r3 == r7) goto L76
            r7 = 101(0x65, float:1.42E-43)
            if (r3 == r7) goto L76
            r7 = 69
            if (r3 != r7) goto L4f
            goto L76
        L4f:
            int r7 = r6._inputPtr
            int r7 = r7 + (-1)
            r6._inputPtr = r7
            com.fasterxml.jackson.core.util.TextBuffer r7 = r6._textBuffer
            r7.setCurrentLength(r2)
            com.fasterxml.jackson.core.json.JsonReadContext r7 = r6._parsingContext
            boolean r7 = r7.inRoot()
            if (r7 == 0) goto L71
            byte[] r7 = r6._inputBuffer
            int r8 = r6._inputPtr
            int r10 = r8 + 1
            r6._inputPtr = r10
            r7 = r7[r8]
            r7 = r7 & 255(0xff, float:3.57E-43)
            r6._verifyRootSpace(r7)
        L71:
            com.fasterxml.jackson.core.JsonToken r7 = r6.resetInt(r9, r5)
            return r7
        L76:
            r0 = r6
            r4 = r9
            com.fasterxml.jackson.core.JsonToken r7 = r0._parseFloat(r1, r2, r3, r4, r5)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.UTF8StreamJsonParser._parseNumber2(char[], int, boolean, int):com.fasterxml.jackson.core.JsonToken");
    }

    private final int _verifyNoLeadingZeroes() throws IOException {
        int r0;
        if ((this._inputPtr < this._inputEnd || _loadMore()) && (r0 = this._inputBuffer[this._inputPtr] & 255) >= 48 && r0 <= 57) {
            if (!isEnabled(JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS)) {
                reportInvalidNumber("Leading zeroes not allowed");
            }
            this._inputPtr++;
            if (r0 == 48) {
                do {
                    if (this._inputPtr >= this._inputEnd && !_loadMore()) {
                        break;
                    }
                    r0 = this._inputBuffer[this._inputPtr] & 255;
                    if (r0 < 48 || r0 > 57) {
                        return 48;
                    }
                    this._inputPtr++;
                } while (r0 == 48);
            }
            return r0;
        }
        return 48;
    }

    private final JsonToken _parseFloat(char[] cArr, int r11, int r12, boolean z, int r14) throws IOException {
        int r4;
        boolean z2;
        int r2 = 0;
        if (r12 == 46) {
            if (r11 >= cArr.length) {
                cArr = this._textBuffer.finishCurrentSegment();
                r11 = 0;
            }
            cArr[r11] = (char) r12;
            r11++;
            r4 = 0;
            while (true) {
                if (this._inputPtr >= this._inputEnd && !_loadMore()) {
                    z2 = true;
                    break;
                }
                byte[] bArr = this._inputBuffer;
                int r5 = this._inputPtr;
                this._inputPtr = r5 + 1;
                r12 = bArr[r5] & 255;
                if (r12 < 48 || r12 > 57) {
                    break;
                }
                r4++;
                if (r11 >= cArr.length) {
                    cArr = this._textBuffer.finishCurrentSegment();
                    r11 = 0;
                }
                cArr[r11] = (char) r12;
                r11++;
            }
            z2 = false;
            if (r4 == 0) {
                reportUnexpectedNumberChar(r12, "Decimal point not followed by a digit");
            }
        } else {
            r4 = 0;
            z2 = false;
        }
        if (r12 == 101 || r12 == 69) {
            if (r11 >= cArr.length) {
                cArr = this._textBuffer.finishCurrentSegment();
                r11 = 0;
            }
            int r6 = r11 + 1;
            cArr[r11] = (char) r12;
            if (this._inputPtr >= this._inputEnd) {
                _loadMoreGuaranteed();
            }
            byte[] bArr2 = this._inputBuffer;
            int r122 = this._inputPtr;
            this._inputPtr = r122 + 1;
            int r112 = bArr2[r122] & 255;
            if (r112 == 45 || r112 == 43) {
                if (r6 >= cArr.length) {
                    cArr = this._textBuffer.finishCurrentSegment();
                    r6 = 0;
                }
                int r123 = r6 + 1;
                cArr[r6] = (char) r112;
                if (this._inputPtr >= this._inputEnd) {
                    _loadMoreGuaranteed();
                }
                byte[] bArr3 = this._inputBuffer;
                int r62 = this._inputPtr;
                this._inputPtr = r62 + 1;
                r112 = bArr3[r62] & 255;
                r6 = r123;
            }
            r12 = r112;
            int r113 = 0;
            while (r12 <= 57 && r12 >= 48) {
                r113++;
                if (r6 >= cArr.length) {
                    cArr = this._textBuffer.finishCurrentSegment();
                    r6 = 0;
                }
                int r7 = r6 + 1;
                cArr[r6] = (char) r12;
                if (this._inputPtr >= this._inputEnd && !_loadMore()) {
                    r2 = r113;
                    r11 = r7;
                    z2 = true;
                    break;
                }
                byte[] bArr4 = this._inputBuffer;
                int r63 = this._inputPtr;
                this._inputPtr = r63 + 1;
                r12 = bArr4[r63] & 255;
                r6 = r7;
            }
            r2 = r113;
            r11 = r6;
            if (r2 == 0) {
                reportUnexpectedNumberChar(r12, "Exponent indicator not followed by a digit");
            }
        }
        if (!z2) {
            this._inputPtr--;
            if (this._parsingContext.inRoot()) {
                _verifyRootSpace(r12);
            }
        }
        this._textBuffer.setCurrentLength(r11);
        return resetFloat(z, r14, r4, r2);
    }

    private final void _verifyRootSpace(int r2) throws IOException {
        this._inputPtr++;
        if (r2 != 9) {
            if (r2 == 10) {
                this._currInputRow++;
                this._currInputRowStart = this._inputPtr;
            } else if (r2 == 13) {
                _skipCR();
            } else if (r2 != 32) {
                _reportMissingRootWS(r2);
            }
        }
    }

    protected final String _parseName(int r6) throws IOException {
        if (r6 != 34) {
            return _handleOddName(r6);
        }
        if (this._inputPtr + 13 > this._inputEnd) {
            return slowParseName();
        }
        byte[] bArr = this._inputBuffer;
        int[] r1 = _icLatin1;
        int r2 = this._inputPtr;
        this._inputPtr = r2 + 1;
        int r22 = bArr[r2] & 255;
        if (r1[r22] != 0) {
            return r22 == 34 ? "" : parseName(0, r22, 0);
        }
        int r3 = this._inputPtr;
        this._inputPtr = r3 + 1;
        int r32 = bArr[r3] & 255;
        if (r1[r32] != 0) {
            if (r32 == 34) {
                return findName(r22, 1);
            }
            return parseName(r22, r32, 1);
        }
        int r23 = (r22 << 8) | r32;
        int r33 = this._inputPtr;
        this._inputPtr = r33 + 1;
        int r34 = bArr[r33] & 255;
        if (r1[r34] != 0) {
            if (r34 == 34) {
                return findName(r23, 2);
            }
            return parseName(r23, r34, 2);
        }
        int r24 = (r23 << 8) | r34;
        int r35 = this._inputPtr;
        this._inputPtr = r35 + 1;
        int r36 = bArr[r35] & 255;
        if (r1[r36] != 0) {
            if (r36 == 34) {
                return findName(r24, 3);
            }
            return parseName(r24, r36, 3);
        }
        int r25 = (r24 << 8) | r36;
        int r37 = this._inputPtr;
        this._inputPtr = r37 + 1;
        int r62 = bArr[r37] & 255;
        if (r1[r62] == 0) {
            this._quad1 = r25;
            return parseMediumName(r62);
        } else if (r62 == 34) {
            return findName(r25, 4);
        } else {
            return parseName(r25, r62, 4);
        }
    }

    protected final String parseMediumName(int r6) throws IOException {
        byte[] bArr = this._inputBuffer;
        int[] r1 = _icLatin1;
        int r2 = this._inputPtr;
        this._inputPtr = r2 + 1;
        int r22 = bArr[r2] & 255;
        if (r1[r22] != 0) {
            if (r22 == 34) {
                return findName(this._quad1, r6, 1);
            }
            return parseName(this._quad1, r6, r22, 1);
        }
        int r62 = (r6 << 8) | r22;
        int r23 = this._inputPtr;
        this._inputPtr = r23 + 1;
        int r24 = bArr[r23] & 255;
        if (r1[r24] != 0) {
            if (r24 == 34) {
                return findName(this._quad1, r62, 2);
            }
            return parseName(this._quad1, r62, r24, 2);
        }
        int r63 = (r62 << 8) | r24;
        int r25 = this._inputPtr;
        this._inputPtr = r25 + 1;
        int r26 = bArr[r25] & 255;
        if (r1[r26] != 0) {
            if (r26 == 34) {
                return findName(this._quad1, r63, 3);
            }
            return parseName(this._quad1, r63, r26, 3);
        }
        int r64 = (r63 << 8) | r26;
        int r27 = this._inputPtr;
        this._inputPtr = r27 + 1;
        int r0 = bArr[r27] & 255;
        if (r1[r0] != 0) {
            if (r0 == 34) {
                return findName(this._quad1, r64, 4);
            }
            return parseName(this._quad1, r64, r0, 4);
        }
        return parseMediumName2(r0, r64);
    }

    protected final String parseMediumName2(int r8, int r9) throws IOException {
        byte[] bArr = this._inputBuffer;
        int[] r1 = _icLatin1;
        int r4 = this._inputPtr;
        this._inputPtr = r4 + 1;
        int r42 = bArr[r4] & 255;
        if (r1[r42] != 0) {
            if (r42 == 34) {
                return findName(this._quad1, r9, r8, 1);
            }
            return parseName(this._quad1, r9, r8, r42, 1);
        }
        int r3 = (r8 << 8) | r42;
        int r43 = this._inputPtr;
        this._inputPtr = r43 + 1;
        int r44 = bArr[r43] & 255;
        if (r1[r44] != 0) {
            if (r44 == 34) {
                return findName(this._quad1, r9, r3, 2);
            }
            return parseName(this._quad1, r9, r3, r44, 2);
        }
        int r32 = (r3 << 8) | r44;
        int r45 = this._inputPtr;
        this._inputPtr = r45 + 1;
        int r46 = bArr[r45] & 255;
        if (r1[r46] != 0) {
            if (r46 == 34) {
                return findName(this._quad1, r9, r32, 3);
            }
            return parseName(this._quad1, r9, r32, r46, 3);
        }
        int r33 = (r32 << 8) | r46;
        int r47 = this._inputPtr;
        this._inputPtr = r47 + 1;
        int r48 = bArr[r47] & 255;
        if (r1[r48] != 0) {
            if (r48 == 34) {
                return findName(this._quad1, r9, r33, 4);
            }
            return parseName(this._quad1, r9, r33, r48, 4);
        }
        return parseLongName(r48, r9, r33);
    }

    protected final String parseLongName(int r12, int r13, int r14) throws IOException {
        int[] r0 = this._quadBuffer;
        r0[0] = this._quad1;
        r0[1] = r13;
        r0[2] = r14;
        byte[] bArr = this._inputBuffer;
        int[] r3 = _icLatin1;
        int r6 = r12;
        int r5 = 3;
        while (this._inputPtr + 4 <= this._inputEnd) {
            int r7 = this._inputPtr;
            this._inputPtr = r7 + 1;
            int r72 = bArr[r7] & 255;
            if (r3[r72] != 0) {
                if (r72 == 34) {
                    return findName(this._quadBuffer, r5, r6, 1);
                }
                return parseEscapedName(this._quadBuffer, r5, r6, r72, 1);
            }
            int r62 = (r6 << 8) | r72;
            int r73 = this._inputPtr;
            this._inputPtr = r73 + 1;
            int r74 = bArr[r73] & 255;
            if (r3[r74] != 0) {
                if (r74 == 34) {
                    return findName(this._quadBuffer, r5, r62, 2);
                }
                return parseEscapedName(this._quadBuffer, r5, r62, r74, 2);
            }
            int r63 = (r62 << 8) | r74;
            int r75 = this._inputPtr;
            this._inputPtr = r75 + 1;
            int r76 = bArr[r75] & 255;
            if (r3[r76] != 0) {
                if (r76 == 34) {
                    return findName(this._quadBuffer, r5, r63, 3);
                }
                return parseEscapedName(this._quadBuffer, r5, r63, r76, 3);
            }
            int r64 = (r63 << 8) | r76;
            int r77 = this._inputPtr;
            this._inputPtr = r77 + 1;
            int r78 = bArr[r77] & 255;
            if (r3[r78] != 0) {
                if (r78 == 34) {
                    return findName(this._quadBuffer, r5, r64, 4);
                }
                return parseEscapedName(this._quadBuffer, r5, r64, r78, 4);
            }
            int[] r8 = this._quadBuffer;
            if (r5 >= r8.length) {
                this._quadBuffer = growArrayBy(r8, r5);
            }
            this._quadBuffer[r5] = r64;
            r6 = r78;
            r5++;
        }
        return parseEscapedName(this._quadBuffer, r5, 0, r6, 0);
    }

    protected String slowParseName() throws IOException {
        if (this._inputPtr >= this._inputEnd && !_loadMore()) {
            _reportInvalidEOF(": was expecting closing '\"' for name", JsonToken.FIELD_NAME);
        }
        byte[] bArr = this._inputBuffer;
        int r1 = this._inputPtr;
        this._inputPtr = r1 + 1;
        int r5 = bArr[r1] & 255;
        return r5 == 34 ? "" : parseEscapedName(this._quadBuffer, 0, 0, r5, 0);
    }

    private final String parseName(int r7, int r8, int r9) throws IOException {
        return parseEscapedName(this._quadBuffer, 0, r7, r8, r9);
    }

    private final String parseName(int r7, int r8, int r9, int r10) throws IOException {
        int[] r1 = this._quadBuffer;
        r1[0] = r7;
        return parseEscapedName(r1, 1, r8, r9, r10);
    }

    private final String parseName(int r7, int r8, int r9, int r10, int r11) throws IOException {
        int[] r1 = this._quadBuffer;
        r1[0] = r7;
        r1[1] = r8;
        return parseEscapedName(r1, 2, r9, r10, r11);
    }

    protected final String parseEscapedName(int[] r6, int r7, int r8, int r9, int r10) throws IOException {
        int[] r0 = _icLatin1;
        while (true) {
            if (r0[r9] != 0) {
                if (r9 == 34) {
                    break;
                }
                if (r9 != 92) {
                    _throwUnquotedSpace(r9, "name");
                } else {
                    r9 = _decodeEscaped();
                }
                if (r9 > 127) {
                    int r1 = 0;
                    if (r10 >= 4) {
                        if (r7 >= r6.length) {
                            r6 = growArrayBy(r6, r6.length);
                            this._quadBuffer = r6;
                        }
                        r6[r7] = r8;
                        r7++;
                        r8 = 0;
                        r10 = 0;
                    }
                    if (r9 < 2048) {
                        r8 = (r8 << 8) | (r9 >> 6) | 192;
                        r10++;
                    } else {
                        int r82 = (r8 << 8) | (r9 >> 12) | 224;
                        int r102 = r10 + 1;
                        if (r102 >= 4) {
                            if (r7 >= r6.length) {
                                r6 = growArrayBy(r6, r6.length);
                                this._quadBuffer = r6;
                            }
                            r6[r7] = r82;
                            r7++;
                            r102 = 0;
                        } else {
                            r1 = r82;
                        }
                        r8 = (r1 << 8) | ((r9 >> 6) & 63) | 128;
                        r10 = r102 + 1;
                    }
                    r9 = (r9 & 63) | 128;
                }
            }
            if (r10 < 4) {
                r10++;
                r8 = (r8 << 8) | r9;
            } else {
                if (r7 >= r6.length) {
                    r6 = growArrayBy(r6, r6.length);
                    this._quadBuffer = r6;
                }
                r6[r7] = r8;
                r8 = r9;
                r7++;
                r10 = 1;
            }
            if (this._inputPtr >= this._inputEnd && !_loadMore()) {
                _reportInvalidEOF(" in field name", JsonToken.FIELD_NAME);
            }
            byte[] bArr = this._inputBuffer;
            int r12 = this._inputPtr;
            this._inputPtr = r12 + 1;
            r9 = bArr[r12] & 255;
        }
        if (r10 > 0) {
            if (r7 >= r6.length) {
                r6 = growArrayBy(r6, r6.length);
                this._quadBuffer = r6;
            }
            r6[r7] = pad(r8, r10);
            r7++;
        }
        String findName = this._symbols.findName(r6, r7);
        return findName == null ? addName(r6, r7, r10) : findName;
    }

    protected String _handleOddName(int r8) throws IOException {
        if (r8 == 39 && isEnabled(JsonParser.Feature.ALLOW_SINGLE_QUOTES)) {
            return _parseAposName();
        }
        if (!isEnabled(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES)) {
            _reportUnexpectedChar((char) _decodeCharForError(r8), "was expecting double-quote to start field name");
        }
        int[] inputCodeUtf8JsNames = CharTypes.getInputCodeUtf8JsNames();
        if (inputCodeUtf8JsNames[r8] != 0) {
            _reportUnexpectedChar(r8, "was expecting either valid name character (for unquoted name) or double-quote (for quoted) to start field name");
        }
        int[] r1 = this._quadBuffer;
        int r2 = 0;
        int r3 = 0;
        int r4 = 0;
        while (true) {
            if (r2 < 4) {
                r2++;
                r4 = r8 | (r4 << 8);
            } else {
                if (r3 >= r1.length) {
                    r1 = growArrayBy(r1, r1.length);
                    this._quadBuffer = r1;
                }
                r1[r3] = r4;
                r4 = r8;
                r3++;
                r2 = 1;
            }
            if (this._inputPtr >= this._inputEnd && !_loadMore()) {
                _reportInvalidEOF(" in field name", JsonToken.FIELD_NAME);
            }
            r8 = this._inputBuffer[this._inputPtr] & 255;
            if (inputCodeUtf8JsNames[r8] != 0) {
                break;
            }
            this._inputPtr++;
        }
        if (r2 > 0) {
            if (r3 >= r1.length) {
                int[] growArrayBy = growArrayBy(r1, r1.length);
                this._quadBuffer = growArrayBy;
                r1 = growArrayBy;
            }
            r1[r3] = r4;
            r3++;
        }
        String findName = this._symbols.findName(r1, r3);
        return findName == null ? addName(r1, r3, r2) : findName;
    }

    protected String _parseAposName() throws IOException {
        if (this._inputPtr >= this._inputEnd && !_loadMore()) {
            _reportInvalidEOF(": was expecting closing ''' for field name", JsonToken.FIELD_NAME);
        }
        byte[] bArr = this._inputBuffer;
        int r1 = this._inputPtr;
        this._inputPtr = r1 + 1;
        int r0 = bArr[r1] & 255;
        if (r0 == 39) {
            return "";
        }
        int[] r2 = this._quadBuffer;
        int[] r3 = _icLatin1;
        int r5 = 0;
        int r6 = 0;
        int r7 = 0;
        while (r0 != 39) {
            if (r0 != 34 && r3[r0] != 0) {
                if (r0 != 92) {
                    _throwUnquotedSpace(r0, "name");
                } else {
                    r0 = _decodeEscaped();
                }
                if (r0 > 127) {
                    if (r5 >= 4) {
                        if (r6 >= r2.length) {
                            r2 = growArrayBy(r2, r2.length);
                            this._quadBuffer = r2;
                        }
                        r2[r6] = r7;
                        r6++;
                        r5 = 0;
                        r7 = 0;
                    }
                    if (r0 < 2048) {
                        r7 = (r7 << 8) | (r0 >> 6) | 192;
                        r5++;
                    } else {
                        int r72 = (r7 << 8) | (r0 >> 12) | 224;
                        int r52 = r5 + 1;
                        if (r52 >= 4) {
                            if (r6 >= r2.length) {
                                r2 = growArrayBy(r2, r2.length);
                                this._quadBuffer = r2;
                            }
                            r2[r6] = r72;
                            r6++;
                            r52 = 0;
                            r72 = 0;
                        }
                        r7 = (r72 << 8) | ((r0 >> 6) & 63) | 128;
                        r5 = r52 + 1;
                    }
                    r0 = (r0 & 63) | 128;
                }
            }
            if (r5 < 4) {
                r5++;
                r7 = r0 | (r7 << 8);
            } else {
                if (r6 >= r2.length) {
                    r2 = growArrayBy(r2, r2.length);
                    this._quadBuffer = r2;
                }
                r2[r6] = r7;
                r7 = r0;
                r6++;
                r5 = 1;
            }
            if (this._inputPtr >= this._inputEnd && !_loadMore()) {
                _reportInvalidEOF(" in field name", JsonToken.FIELD_NAME);
            }
            byte[] bArr2 = this._inputBuffer;
            int r8 = this._inputPtr;
            this._inputPtr = r8 + 1;
            r0 = bArr2[r8] & 255;
        }
        if (r5 > 0) {
            if (r6 >= r2.length) {
                int[] growArrayBy = growArrayBy(r2, r2.length);
                this._quadBuffer = growArrayBy;
                r2 = growArrayBy;
            }
            r2[r6] = pad(r7, r5);
            r6++;
        }
        String findName = this._symbols.findName(r2, r6);
        return findName == null ? addName(r2, r6, r5) : findName;
    }

    private final String findName(int r3, int r4) throws JsonParseException {
        int pad = pad(r3, r4);
        String findName = this._symbols.findName(pad);
        if (findName != null) {
            return findName;
        }
        int[] r0 = this._quadBuffer;
        r0[0] = pad;
        return addName(r0, 1, r4);
    }

    private final String findName(int r3, int r4, int r5) throws JsonParseException {
        int pad = pad(r4, r5);
        String findName = this._symbols.findName(r3, pad);
        if (findName != null) {
            return findName;
        }
        int[] r0 = this._quadBuffer;
        r0[0] = r3;
        r0[1] = pad;
        return addName(r0, 2, r5);
    }

    private final String findName(int r3, int r4, int r5, int r6) throws JsonParseException {
        int pad = pad(r5, r6);
        String findName = this._symbols.findName(r3, r4, pad);
        if (findName != null) {
            return findName;
        }
        int[] r0 = this._quadBuffer;
        r0[0] = r3;
        r0[1] = r4;
        r0[2] = pad(pad, r6);
        return addName(r0, 3, r6);
    }

    private final String findName(int[] r2, int r3, int r4, int r5) throws JsonParseException {
        if (r3 >= r2.length) {
            r2 = growArrayBy(r2, r2.length);
            this._quadBuffer = r2;
        }
        int r0 = r3 + 1;
        r2[r3] = pad(r4, r5);
        String findName = this._symbols.findName(r2, r0);
        return findName == null ? addName(r2, r0, r5) : findName;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00bf  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final java.lang.String addName(int[] r17, int r18, int r19) throws com.fasterxml.jackson.core.JsonParseException {
        /*
            Method dump skipped, instructions count: 262
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.UTF8StreamJsonParser.addName(int[], int, int):java.lang.String");
    }

    protected void _loadMoreGuaranteed() throws IOException {
        if (_loadMore()) {
            return;
        }
        _reportInvalidEOF();
    }

    @Override // com.fasterxml.jackson.core.base.ParserBase
    protected void _finishString() throws IOException {
        int r0 = this._inputPtr;
        if (r0 >= this._inputEnd) {
            _loadMoreGuaranteed();
            r0 = this._inputPtr;
        }
        int r1 = 0;
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        int[] r3 = _icUTF8;
        int min = Math.min(this._inputEnd, emptyAndGetCurrentSegment.length + r0);
        byte[] bArr = this._inputBuffer;
        while (true) {
            if (r0 >= min) {
                break;
            }
            int r6 = bArr[r0] & 255;
            if (r3[r6] == 0) {
                r0++;
                emptyAndGetCurrentSegment[r1] = (char) r6;
                r1++;
            } else if (r6 == 34) {
                this._inputPtr = r0 + 1;
                this._textBuffer.setCurrentLength(r1);
                return;
            }
        }
        this._inputPtr = r0;
        _finishString2(emptyAndGetCurrentSegment, r1);
    }

    protected String _finishAndReturnString() throws IOException {
        int r0 = this._inputPtr;
        if (r0 >= this._inputEnd) {
            _loadMoreGuaranteed();
            r0 = this._inputPtr;
        }
        int r1 = 0;
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        int[] r3 = _icUTF8;
        int min = Math.min(this._inputEnd, emptyAndGetCurrentSegment.length + r0);
        byte[] bArr = this._inputBuffer;
        while (true) {
            if (r0 >= min) {
                break;
            }
            int r6 = bArr[r0] & 255;
            if (r3[r6] == 0) {
                r0++;
                emptyAndGetCurrentSegment[r1] = (char) r6;
                r1++;
            } else if (r6 == 34) {
                this._inputPtr = r0 + 1;
                return this._textBuffer.setCurrentAndReturn(r1);
            }
        }
        this._inputPtr = r0;
        _finishString2(emptyAndGetCurrentSegment, r1);
        return this._textBuffer.contentsAsString();
    }

    private final void _finishString2(char[] cArr, int r9) throws IOException {
        int[] r0 = _icUTF8;
        byte[] bArr = this._inputBuffer;
        while (true) {
            int r2 = this._inputPtr;
            if (r2 >= this._inputEnd) {
                _loadMoreGuaranteed();
                r2 = this._inputPtr;
            }
            int r4 = 0;
            if (r9 >= cArr.length) {
                cArr = this._textBuffer.finishCurrentSegment();
                r9 = 0;
            }
            int min = Math.min(this._inputEnd, (cArr.length - r9) + r2);
            while (true) {
                if (r2 < min) {
                    int r5 = r2 + 1;
                    int r22 = bArr[r2] & 255;
                    if (r0[r22] != 0) {
                        this._inputPtr = r5;
                        if (r22 != 34) {
                            int r3 = r0[r22];
                            if (r3 == 1) {
                                r22 = _decodeEscaped();
                            } else if (r3 == 2) {
                                r22 = _decodeUtf8_2(r22);
                            } else if (r3 == 3) {
                                r22 = this._inputEnd - this._inputPtr >= 2 ? _decodeUtf8_3fast(r22) : _decodeUtf8_3(r22);
                            } else if (r3 == 4) {
                                int _decodeUtf8_4 = _decodeUtf8_4(r22);
                                int r32 = r9 + 1;
                                cArr[r9] = (char) (55296 | (_decodeUtf8_4 >> 10));
                                if (r32 >= cArr.length) {
                                    cArr = this._textBuffer.finishCurrentSegment();
                                    r9 = 0;
                                } else {
                                    r9 = r32;
                                }
                                r22 = (_decodeUtf8_4 & AnalyticsListener.EVENT_DRM_KEYS_LOADED) | 56320;
                            } else if (r22 < 32) {
                                _throwUnquotedSpace(r22, "string value");
                            } else {
                                _reportInvalidChar(r22);
                            }
                            if (r9 >= cArr.length) {
                                cArr = this._textBuffer.finishCurrentSegment();
                            } else {
                                r4 = r9;
                            }
                            r9 = r4 + 1;
                            cArr[r4] = (char) r22;
                        } else {
                            this._textBuffer.setCurrentLength(r9);
                            return;
                        }
                    } else {
                        cArr[r9] = (char) r22;
                        r2 = r5;
                        r9++;
                    }
                } else {
                    this._inputPtr = r2;
                    break;
                }
            }
        }
    }

    protected void _skipString() throws IOException {
        this._tokenIncomplete = false;
        int[] r0 = _icUTF8;
        byte[] bArr = this._inputBuffer;
        while (true) {
            int r2 = this._inputPtr;
            int r3 = this._inputEnd;
            if (r2 >= r3) {
                _loadMoreGuaranteed();
                r2 = this._inputPtr;
                r3 = this._inputEnd;
            }
            while (true) {
                if (r2 < r3) {
                    int r4 = r2 + 1;
                    int r22 = bArr[r2] & 255;
                    if (r0[r22] != 0) {
                        this._inputPtr = r4;
                        if (r22 == 34) {
                            return;
                        }
                        int r32 = r0[r22];
                        if (r32 == 1) {
                            _decodeEscaped();
                        } else if (r32 == 2) {
                            _skipUtf8_2();
                        } else if (r32 == 3) {
                            _skipUtf8_3();
                        } else if (r32 == 4) {
                            _skipUtf8_4(r22);
                        } else if (r22 < 32) {
                            _throwUnquotedSpace(r22, "string value");
                        } else {
                            _reportInvalidChar(r22);
                        }
                    } else {
                        r2 = r4;
                    }
                } else {
                    this._inputPtr = r2;
                    break;
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x001b, code lost:
        if (r4 != 44) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0048, code lost:
        if (r3._parsingContext.inArray() == false) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0051, code lost:
        if (isEnabled(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_MISSING_VALUES) == false) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0053, code lost:
        r3._inputPtr--;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x005a, code lost:
        return com.fasterxml.jackson.core.JsonToken.VALUE_NULL;
     */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00a8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected com.fasterxml.jackson.core.JsonToken _handleUnexpectedValue(int r4) throws java.io.IOException {
        /*
            r3 = this;
            r0 = 39
            if (r4 == r0) goto L95
            r0 = 73
            r1 = 1
            if (r4 == r0) goto L7b
            r0 = 78
            if (r4 == r0) goto L61
            r0 = 93
            if (r4 == r0) goto L42
            r0 = 125(0x7d, float:1.75E-43)
            if (r4 == r0) goto L5b
            r0 = 43
            if (r4 == r0) goto L1f
            r0 = 44
            if (r4 == r0) goto L4b
            goto La2
        L1f:
            int r4 = r3._inputPtr
            int r0 = r3._inputEnd
            if (r4 < r0) goto L30
            boolean r4 = r3._loadMore()
            if (r4 != 0) goto L30
            com.fasterxml.jackson.core.JsonToken r4 = com.fasterxml.jackson.core.JsonToken.VALUE_NUMBER_INT
            r3._reportInvalidEOFInValue(r4)
        L30:
            byte[] r4 = r3._inputBuffer
            int r0 = r3._inputPtr
            int r1 = r0 + 1
            r3._inputPtr = r1
            r4 = r4[r0]
            r4 = r4 & 255(0xff, float:3.57E-43)
            r0 = 0
            com.fasterxml.jackson.core.JsonToken r4 = r3._handleInvalidNumberStart(r4, r0)
            return r4
        L42:
            com.fasterxml.jackson.core.json.JsonReadContext r0 = r3._parsingContext
            boolean r0 = r0.inArray()
            if (r0 != 0) goto L4b
            goto La2
        L4b:
            com.fasterxml.jackson.core.JsonParser$Feature r0 = com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_MISSING_VALUES
            boolean r0 = r3.isEnabled(r0)
            if (r0 == 0) goto L5b
            int r4 = r3._inputPtr
            int r4 = r4 - r1
            r3._inputPtr = r4
            com.fasterxml.jackson.core.JsonToken r4 = com.fasterxml.jackson.core.JsonToken.VALUE_NULL
            return r4
        L5b:
            java.lang.String r0 = "expected a value"
            r3._reportUnexpectedChar(r4, r0)
            goto L95
        L61:
            java.lang.String r0 = "NaN"
            r3._matchToken(r0, r1)
            com.fasterxml.jackson.core.JsonParser$Feature r1 = com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS
            boolean r1 = r3.isEnabled(r1)
            if (r1 == 0) goto L75
            r1 = 9221120237041090560(0x7ff8000000000000, double:NaN)
            com.fasterxml.jackson.core.JsonToken r4 = r3.resetAsNaN(r0, r1)
            return r4
        L75:
            java.lang.String r0 = "Non-standard token 'NaN': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow"
            r3._reportError(r0)
            goto La2
        L7b:
            java.lang.String r0 = "Infinity"
            r3._matchToken(r0, r1)
            com.fasterxml.jackson.core.JsonParser$Feature r1 = com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS
            boolean r1 = r3.isEnabled(r1)
            if (r1 == 0) goto L8f
            r1 = 9218868437227405312(0x7ff0000000000000, double:Infinity)
            com.fasterxml.jackson.core.JsonToken r4 = r3.resetAsNaN(r0, r1)
            return r4
        L8f:
            java.lang.String r0 = "Non-standard token 'Infinity': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow"
            r3._reportError(r0)
            goto La2
        L95:
            com.fasterxml.jackson.core.JsonParser$Feature r0 = com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_SINGLE_QUOTES
            boolean r0 = r3.isEnabled(r0)
            if (r0 == 0) goto La2
            com.fasterxml.jackson.core.JsonToken r4 = r3._handleApos()
            return r4
        La2:
            boolean r0 = java.lang.Character.isJavaIdentifierStart(r4)
            if (r0 == 0) goto Lbf
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = ""
            r0.append(r1)
            char r1 = (char) r4
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "('true', 'false' or 'null')"
            r3._reportInvalidToken(r0, r1)
        Lbf:
            java.lang.String r0 = "expected a valid value (number, String, array, object, 'true', 'false' or 'null')"
            r3._reportUnexpectedChar(r4, r0)
            r4 = 0
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.UTF8StreamJsonParser._handleUnexpectedValue(int):com.fasterxml.jackson.core.JsonToken");
    }

    protected JsonToken _handleApos() throws IOException {
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        int[] r1 = _icUTF8;
        byte[] bArr = this._inputBuffer;
        int r4 = 0;
        while (true) {
            if (this._inputPtr >= this._inputEnd) {
                _loadMoreGuaranteed();
            }
            if (r4 >= emptyAndGetCurrentSegment.length) {
                emptyAndGetCurrentSegment = this._textBuffer.finishCurrentSegment();
                r4 = 0;
            }
            int r5 = this._inputEnd;
            int length = this._inputPtr + (emptyAndGetCurrentSegment.length - r4);
            if (length < r5) {
                r5 = length;
            }
            while (this._inputPtr < r5) {
                int r6 = this._inputPtr;
                this._inputPtr = r6 + 1;
                int r62 = bArr[r6] & 255;
                if (r62 != 39 && r1[r62] == 0) {
                    emptyAndGetCurrentSegment[r4] = (char) r62;
                    r4++;
                } else if (r62 != 39) {
                    int r52 = r1[r62];
                    if (r52 == 1) {
                        r62 = _decodeEscaped();
                    } else if (r52 == 2) {
                        r62 = _decodeUtf8_2(r62);
                    } else if (r52 != 3) {
                        if (r52 == 4) {
                            int _decodeUtf8_4 = _decodeUtf8_4(r62);
                            int r63 = r4 + 1;
                            emptyAndGetCurrentSegment[r4] = (char) (55296 | (_decodeUtf8_4 >> 10));
                            if (r63 >= emptyAndGetCurrentSegment.length) {
                                emptyAndGetCurrentSegment = this._textBuffer.finishCurrentSegment();
                                r4 = 0;
                            } else {
                                r4 = r63;
                            }
                            r62 = 56320 | (_decodeUtf8_4 & AnalyticsListener.EVENT_DRM_KEYS_LOADED);
                        } else {
                            if (r62 < 32) {
                                _throwUnquotedSpace(r62, "string value");
                            }
                            _reportInvalidChar(r62);
                        }
                    } else if (this._inputEnd - this._inputPtr >= 2) {
                        r62 = _decodeUtf8_3fast(r62);
                    } else {
                        r62 = _decodeUtf8_3(r62);
                    }
                    if (r4 >= emptyAndGetCurrentSegment.length) {
                        emptyAndGetCurrentSegment = this._textBuffer.finishCurrentSegment();
                        r4 = 0;
                    }
                    emptyAndGetCurrentSegment[r4] = (char) r62;
                    r4++;
                } else {
                    this._textBuffer.setCurrentLength(r4);
                    return JsonToken.VALUE_STRING;
                }
            }
        }
    }

    protected JsonToken _handleInvalidNumberStart(int r4, boolean z) throws IOException {
        String str;
        while (r4 == 73) {
            if (this._inputPtr >= this._inputEnd && !_loadMore()) {
                _reportInvalidEOFInValue(JsonToken.VALUE_NUMBER_FLOAT);
            }
            byte[] bArr = this._inputBuffer;
            int r0 = this._inputPtr;
            this._inputPtr = r0 + 1;
            r4 = bArr[r0];
            if (r4 != 78) {
                if (r4 != 110) {
                    break;
                }
                str = z ? "-Infinity" : "+Infinity";
            } else {
                str = z ? "-INF" : "+INF";
            }
            _matchToken(str, 3);
            if (isEnabled(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS)) {
                return resetAsNaN(str, z ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY);
            }
            _reportError("Non-standard token '" + str + "': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
        }
        reportUnexpectedNumberChar(r4, "expected digit (0-9) to follow minus sign, for valid numeric value");
        return null;
    }

    protected final void _matchToken(String str, int r5) throws IOException {
        int length = str.length();
        if (this._inputPtr + length >= this._inputEnd) {
            _matchToken2(str, r5);
            return;
        }
        do {
            if (this._inputBuffer[this._inputPtr] != str.charAt(r5)) {
                _reportInvalidToken(str.substring(0, r5));
            }
            this._inputPtr++;
            r5++;
        } while (r5 < length);
        int r0 = this._inputBuffer[this._inputPtr] & 255;
        if (r0 < 48 || r0 == 93 || r0 == 125) {
            return;
        }
        _checkMatchEnd(str, r5, r0);
    }

    private final void _matchToken2(String str, int r5) throws IOException {
        int r0;
        int length = str.length();
        do {
            if ((this._inputPtr >= this._inputEnd && !_loadMore()) || this._inputBuffer[this._inputPtr] != str.charAt(r5)) {
                _reportInvalidToken(str.substring(0, r5));
            }
            this._inputPtr++;
            r5++;
        } while (r5 < length);
        if ((this._inputPtr < this._inputEnd || _loadMore()) && (r0 = this._inputBuffer[this._inputPtr] & 255) >= 48 && r0 != 93 && r0 != 125) {
            _checkMatchEnd(str, r5, r0);
        }
    }

    private final void _checkMatchEnd(String str, int r2, int r3) throws IOException {
        if (Character.isJavaIdentifierPart((char) _decodeCharForError(r3))) {
            _reportInvalidToken(str.substring(0, r2));
        }
    }

    private final int _skipWS() throws IOException {
        while (this._inputPtr < this._inputEnd) {
            byte[] bArr = this._inputBuffer;
            int r1 = this._inputPtr;
            this._inputPtr = r1 + 1;
            int r0 = bArr[r1] & 255;
            if (r0 > 32) {
                if (r0 == 47 || r0 == 35) {
                    this._inputPtr--;
                    return _skipWS2();
                }
                return r0;
            } else if (r0 != 32) {
                if (r0 == 10) {
                    this._currInputRow++;
                    this._currInputRowStart = this._inputPtr;
                } else if (r0 == 13) {
                    _skipCR();
                } else if (r0 != 9) {
                    _throwInvalidSpace(r0);
                }
            }
        }
        return _skipWS2();
    }

    private final int _skipWS2() throws IOException {
        int r0;
        while (true) {
            if (this._inputPtr < this._inputEnd || _loadMore()) {
                byte[] bArr = this._inputBuffer;
                int r1 = this._inputPtr;
                this._inputPtr = r1 + 1;
                r0 = bArr[r1] & 255;
                if (r0 > 32) {
                    if (r0 == 47) {
                        _skipComment();
                    } else if (r0 != 35 || !_skipYAMLComment()) {
                        break;
                    }
                } else if (r0 != 32) {
                    if (r0 == 10) {
                        this._currInputRow++;
                        this._currInputRowStart = this._inputPtr;
                    } else if (r0 == 13) {
                        _skipCR();
                    } else if (r0 != 9) {
                        _throwInvalidSpace(r0);
                    }
                }
            } else {
                throw _constructError("Unexpected end-of-input within/between " + this._parsingContext.typeDesc() + " entries");
            }
        }
        return r0;
    }

    private final int _skipWSOrEnd() throws IOException {
        if (this._inputPtr >= this._inputEnd && !_loadMore()) {
            return _eofAsNextChar();
        }
        byte[] bArr = this._inputBuffer;
        int r1 = this._inputPtr;
        this._inputPtr = r1 + 1;
        int r0 = bArr[r1] & 255;
        if (r0 > 32) {
            if (r0 == 47 || r0 == 35) {
                this._inputPtr--;
                return _skipWSOrEnd2();
            }
            return r0;
        }
        if (r0 != 32) {
            if (r0 == 10) {
                this._currInputRow++;
                this._currInputRowStart = this._inputPtr;
            } else if (r0 == 13) {
                _skipCR();
            } else if (r0 != 9) {
                _throwInvalidSpace(r0);
            }
        }
        while (this._inputPtr < this._inputEnd) {
            byte[] bArr2 = this._inputBuffer;
            int r7 = this._inputPtr;
            this._inputPtr = r7 + 1;
            int r02 = bArr2[r7] & 255;
            if (r02 > 32) {
                if (r02 == 47 || r02 == 35) {
                    this._inputPtr--;
                    return _skipWSOrEnd2();
                }
                return r02;
            } else if (r02 != 32) {
                if (r02 == 10) {
                    this._currInputRow++;
                    this._currInputRowStart = this._inputPtr;
                } else if (r02 == 13) {
                    _skipCR();
                } else if (r02 != 9) {
                    _throwInvalidSpace(r02);
                }
            }
        }
        return _skipWSOrEnd2();
    }

    private final int _skipWSOrEnd2() throws IOException {
        int r0;
        while (true) {
            if (this._inputPtr < this._inputEnd || _loadMore()) {
                byte[] bArr = this._inputBuffer;
                int r1 = this._inputPtr;
                this._inputPtr = r1 + 1;
                r0 = bArr[r1] & 255;
                if (r0 > 32) {
                    if (r0 == 47) {
                        _skipComment();
                    } else if (r0 != 35 || !_skipYAMLComment()) {
                        break;
                    }
                } else if (r0 != 32) {
                    if (r0 == 10) {
                        this._currInputRow++;
                        this._currInputRowStart = this._inputPtr;
                    } else if (r0 == 13) {
                        _skipCR();
                    } else if (r0 != 9) {
                        _throwInvalidSpace(r0);
                    }
                }
            } else {
                return _eofAsNextChar();
            }
        }
        return r0;
    }

    private final int _skipColon() throws IOException {
        if (this._inputPtr + 4 >= this._inputEnd) {
            return _skipColon2(false);
        }
        byte b = this._inputBuffer[this._inputPtr];
        if (b == 58) {
            byte[] bArr = this._inputBuffer;
            int r1 = this._inputPtr + 1;
            this._inputPtr = r1;
            byte b2 = bArr[r1];
            if (b2 > 32) {
                if (b2 == 47 || b2 == 35) {
                    return _skipColon2(true);
                }
                this._inputPtr++;
                return b2;
            }
            if (b2 == 32 || b2 == 9) {
                byte[] bArr2 = this._inputBuffer;
                int r12 = this._inputPtr + 1;
                this._inputPtr = r12;
                byte b3 = bArr2[r12];
                if (b3 > 32) {
                    if (b3 == 47 || b3 == 35) {
                        return _skipColon2(true);
                    }
                    this._inputPtr++;
                    return b3;
                }
            }
            return _skipColon2(true);
        }
        if (b == 32 || b == 9) {
            byte[] bArr3 = this._inputBuffer;
            int r8 = this._inputPtr + 1;
            this._inputPtr = r8;
            b = bArr3[r8];
        }
        if (b == 58) {
            byte[] bArr4 = this._inputBuffer;
            int r13 = this._inputPtr + 1;
            this._inputPtr = r13;
            byte b4 = bArr4[r13];
            if (b4 > 32) {
                if (b4 == 47 || b4 == 35) {
                    return _skipColon2(true);
                }
                this._inputPtr++;
                return b4;
            }
            if (b4 == 32 || b4 == 9) {
                byte[] bArr5 = this._inputBuffer;
                int r14 = this._inputPtr + 1;
                this._inputPtr = r14;
                byte b5 = bArr5[r14];
                if (b5 > 32) {
                    if (b5 == 47 || b5 == 35) {
                        return _skipColon2(true);
                    }
                    this._inputPtr++;
                    return b5;
                }
            }
            return _skipColon2(true);
        }
        return _skipColon2(false);
    }

    private final int _skipColon2(boolean z) throws IOException {
        while (true) {
            if (this._inputPtr < this._inputEnd || _loadMore()) {
                byte[] bArr = this._inputBuffer;
                int r1 = this._inputPtr;
                this._inputPtr = r1 + 1;
                int r0 = bArr[r1] & 255;
                if (r0 > 32) {
                    if (r0 == 47) {
                        _skipComment();
                    } else if (r0 != 35 || !_skipYAMLComment()) {
                        if (z) {
                            return r0;
                        }
                        if (r0 != 58) {
                            _reportUnexpectedChar(r0, "was expecting a colon to separate field name and value");
                        }
                        z = true;
                    }
                } else if (r0 != 32) {
                    if (r0 == 10) {
                        this._currInputRow++;
                        this._currInputRowStart = this._inputPtr;
                    } else if (r0 == 13) {
                        _skipCR();
                    } else if (r0 != 9) {
                        _throwInvalidSpace(r0);
                    }
                }
            } else {
                _reportInvalidEOF(" within/between " + this._parsingContext.typeDesc() + " entries", null);
                return -1;
            }
        }
    }

    private final void _skipComment() throws IOException {
        if (!isEnabled(JsonParser.Feature.ALLOW_COMMENTS)) {
            _reportUnexpectedChar(47, "maybe a (non-standard) comment? (not recognized as one since Feature 'ALLOW_COMMENTS' not enabled for parser)");
        }
        if (this._inputPtr >= this._inputEnd && !_loadMore()) {
            _reportInvalidEOF(" in a comment", null);
        }
        byte[] bArr = this._inputBuffer;
        int r2 = this._inputPtr;
        this._inputPtr = r2 + 1;
        int r0 = bArr[r2] & 255;
        if (r0 == 47) {
            _skipLine();
        } else if (r0 == 42) {
            _skipCComment();
        } else {
            _reportUnexpectedChar(r0, "was expecting either '*' or '/' for a comment");
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x0045, code lost:
        _reportInvalidEOF(" in a comment", null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x004b, code lost:
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void _skipCComment() throws java.io.IOException {
        /*
            r4 = this;
            int[] r0 = com.fasterxml.jackson.core.p009io.CharTypes.getInputCodeComment()
        L4:
            int r1 = r4._inputPtr
            int r2 = r4._inputEnd
            if (r1 < r2) goto L10
            boolean r1 = r4._loadMore()
            if (r1 == 0) goto L45
        L10:
            byte[] r1 = r4._inputBuffer
            int r2 = r4._inputPtr
            int r3 = r2 + 1
            r4._inputPtr = r3
            r1 = r1[r2]
            r1 = r1 & 255(0xff, float:3.57E-43)
            r2 = r0[r1]
            if (r2 == 0) goto L4
            r3 = 2
            if (r2 == r3) goto L74
            r3 = 3
            if (r2 == r3) goto L70
            r3 = 4
            if (r2 == r3) goto L6c
            r3 = 10
            if (r2 == r3) goto L61
            r3 = 13
            if (r2 == r3) goto L5d
            r3 = 42
            if (r2 == r3) goto L39
            r4._reportInvalidChar(r1)
            goto L4
        L39:
            int r1 = r4._inputPtr
            int r2 = r4._inputEnd
            if (r1 < r2) goto L4c
            boolean r1 = r4._loadMore()
            if (r1 != 0) goto L4c
        L45:
            r0 = 0
            java.lang.String r1 = " in a comment"
            r4._reportInvalidEOF(r1, r0)
            return
        L4c:
            byte[] r1 = r4._inputBuffer
            int r2 = r4._inputPtr
            r1 = r1[r2]
            r2 = 47
            if (r1 != r2) goto L4
            int r0 = r4._inputPtr
            int r0 = r0 + 1
            r4._inputPtr = r0
            return
        L5d:
            r4._skipCR()
            goto L4
        L61:
            int r1 = r4._currInputRow
            int r1 = r1 + 1
            r4._currInputRow = r1
            int r1 = r4._inputPtr
            r4._currInputRowStart = r1
            goto L4
        L6c:
            r4._skipUtf8_4(r1)
            goto L4
        L70:
            r4._skipUtf8_3()
            goto L4
        L74:
            r4._skipUtf8_2()
            goto L4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.UTF8StreamJsonParser._skipCComment():void");
    }

    private final boolean _skipYAMLComment() throws IOException {
        if (isEnabled(JsonParser.Feature.ALLOW_YAML_COMMENTS)) {
            _skipLine();
            return true;
        }
        return false;
    }

    private final void _skipLine() throws IOException {
        int[] inputCodeComment = CharTypes.getInputCodeComment();
        while (true) {
            if (this._inputPtr >= this._inputEnd && !_loadMore()) {
                return;
            }
            byte[] bArr = this._inputBuffer;
            int r2 = this._inputPtr;
            this._inputPtr = r2 + 1;
            int r1 = bArr[r2] & 255;
            int r22 = inputCodeComment[r1];
            if (r22 != 0) {
                if (r22 == 2) {
                    _skipUtf8_2();
                } else if (r22 == 3) {
                    _skipUtf8_3();
                } else if (r22 == 4) {
                    _skipUtf8_4(r1);
                } else if (r22 == 10) {
                    this._currInputRow++;
                    this._currInputRowStart = this._inputPtr;
                    return;
                } else if (r22 == 13) {
                    _skipCR();
                    return;
                } else if (r22 != 42 && r22 < 0) {
                    _reportInvalidChar(r1);
                }
            }
        }
    }

    @Override // com.fasterxml.jackson.core.base.ParserBase
    protected char _decodeEscaped() throws IOException {
        if (this._inputPtr >= this._inputEnd && !_loadMore()) {
            _reportInvalidEOF(" in character escape sequence", JsonToken.VALUE_STRING);
        }
        byte[] bArr = this._inputBuffer;
        int r1 = this._inputPtr;
        this._inputPtr = r1 + 1;
        byte b = bArr[r1];
        if (b == 34 || b == 47 || b == 92) {
            return (char) b;
        }
        if (b != 98) {
            if (b != 102) {
                if (b != 110) {
                    if (b != 114) {
                        if (b != 116) {
                            if (b != 117) {
                                return _handleUnrecognizedCharacterEscape((char) _decodeCharForError(b));
                            }
                            int r12 = 0;
                            for (int r0 = 0; r0 < 4; r0++) {
                                if (this._inputPtr >= this._inputEnd && !_loadMore()) {
                                    _reportInvalidEOF(" in character escape sequence", JsonToken.VALUE_STRING);
                                }
                                byte[] bArr2 = this._inputBuffer;
                                int r4 = this._inputPtr;
                                this._inputPtr = r4 + 1;
                                byte b2 = bArr2[r4];
                                int charToHex = CharTypes.charToHex(b2);
                                if (charToHex < 0) {
                                    _reportUnexpectedChar(b2, "expected a hex-digit for character escape sequence");
                                }
                                r12 = (r12 << 4) | charToHex;
                            }
                            return (char) r12;
                        }
                        return '\t';
                    }
                    return CharUtils.f1567CR;
                }
                return '\n';
            }
            return '\f';
        }
        return '\b';
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:31:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected int _decodeCharForError(int r7) throws java.io.IOException {
        /*
            r6 = this;
            r7 = r7 & 255(0xff, float:3.57E-43)
            r0 = 127(0x7f, float:1.78E-43)
            if (r7 <= r0) goto L68
            r0 = r7 & 224(0xe0, float:3.14E-43)
            r1 = 2
            r2 = 1
            r3 = 192(0xc0, float:2.69E-43)
            if (r0 != r3) goto L12
            r7 = r7 & 31
        L10:
            r0 = 1
            goto L2c
        L12:
            r0 = r7 & 240(0xf0, float:3.36E-43)
            r3 = 224(0xe0, float:3.14E-43)
            if (r0 != r3) goto L1c
            r7 = r7 & 15
            r0 = 2
            goto L2c
        L1c:
            r0 = r7 & 248(0xf8, float:3.48E-43)
            r3 = 240(0xf0, float:3.36E-43)
            if (r0 != r3) goto L26
            r7 = r7 & 7
            r0 = 3
            goto L2c
        L26:
            r0 = r7 & 255(0xff, float:3.57E-43)
            r6._reportInvalidInitial(r0)
            goto L10
        L2c:
            int r3 = r6.nextByte()
            r4 = r3 & 192(0xc0, float:2.69E-43)
            r5 = 128(0x80, float:1.794E-43)
            if (r4 == r5) goto L3b
            r4 = r3 & 255(0xff, float:3.57E-43)
            r6._reportInvalidOther(r4)
        L3b:
            int r7 = r7 << 6
            r3 = r3 & 63
            r7 = r7 | r3
            if (r0 <= r2) goto L68
            int r2 = r6.nextByte()
            r3 = r2 & 192(0xc0, float:2.69E-43)
            if (r3 == r5) goto L4f
            r3 = r2 & 255(0xff, float:3.57E-43)
            r6._reportInvalidOther(r3)
        L4f:
            int r7 = r7 << 6
            r2 = r2 & 63
            r7 = r7 | r2
            if (r0 <= r1) goto L68
            int r0 = r6.nextByte()
            r1 = r0 & 192(0xc0, float:2.69E-43)
            if (r1 == r5) goto L63
            r1 = r0 & 255(0xff, float:3.57E-43)
            r6._reportInvalidOther(r1)
        L63:
            int r7 = r7 << 6
            r0 = r0 & 63
            r7 = r7 | r0
        L68:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.UTF8StreamJsonParser._decodeCharForError(int):int");
    }

    private final int _decodeUtf8_2(int r4) throws IOException {
        if (this._inputPtr >= this._inputEnd) {
            _loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int r1 = this._inputPtr;
        this._inputPtr = r1 + 1;
        byte b = bArr[r1];
        if ((b & 192) != 128) {
            _reportInvalidOther(b & 255, this._inputPtr);
        }
        return ((r4 & 31) << 6) | (b & Utf8.REPLACEMENT_BYTE);
    }

    private final int _decodeUtf8_3(int r5) throws IOException {
        if (this._inputPtr >= this._inputEnd) {
            _loadMoreGuaranteed();
        }
        int r52 = r5 & 15;
        byte[] bArr = this._inputBuffer;
        int r1 = this._inputPtr;
        this._inputPtr = r1 + 1;
        byte b = bArr[r1];
        if ((b & 192) != 128) {
            _reportInvalidOther(b & 255, this._inputPtr);
        }
        int r53 = (r52 << 6) | (b & Utf8.REPLACEMENT_BYTE);
        if (this._inputPtr >= this._inputEnd) {
            _loadMoreGuaranteed();
        }
        byte[] bArr2 = this._inputBuffer;
        int r12 = this._inputPtr;
        this._inputPtr = r12 + 1;
        byte b2 = bArr2[r12];
        if ((b2 & 192) != 128) {
            _reportInvalidOther(b2 & 255, this._inputPtr);
        }
        return (r53 << 6) | (b2 & Utf8.REPLACEMENT_BYTE);
    }

    private final int _decodeUtf8_3fast(int r5) throws IOException {
        int r52 = r5 & 15;
        byte[] bArr = this._inputBuffer;
        int r1 = this._inputPtr;
        this._inputPtr = r1 + 1;
        byte b = bArr[r1];
        if ((b & 192) != 128) {
            _reportInvalidOther(b & 255, this._inputPtr);
        }
        int r53 = (r52 << 6) | (b & Utf8.REPLACEMENT_BYTE);
        byte[] bArr2 = this._inputBuffer;
        int r12 = this._inputPtr;
        this._inputPtr = r12 + 1;
        byte b2 = bArr2[r12];
        if ((b2 & 192) != 128) {
            _reportInvalidOther(b2 & 255, this._inputPtr);
        }
        return (r53 << 6) | (b2 & Utf8.REPLACEMENT_BYTE);
    }

    private final int _decodeUtf8_4(int r5) throws IOException {
        if (this._inputPtr >= this._inputEnd) {
            _loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int r1 = this._inputPtr;
        this._inputPtr = r1 + 1;
        byte b = bArr[r1];
        if ((b & 192) != 128) {
            _reportInvalidOther(b & 255, this._inputPtr);
        }
        int r52 = ((r5 & 7) << 6) | (b & Utf8.REPLACEMENT_BYTE);
        if (this._inputPtr >= this._inputEnd) {
            _loadMoreGuaranteed();
        }
        byte[] bArr2 = this._inputBuffer;
        int r12 = this._inputPtr;
        this._inputPtr = r12 + 1;
        byte b2 = bArr2[r12];
        if ((b2 & 192) != 128) {
            _reportInvalidOther(b2 & 255, this._inputPtr);
        }
        int r53 = (r52 << 6) | (b2 & Utf8.REPLACEMENT_BYTE);
        if (this._inputPtr >= this._inputEnd) {
            _loadMoreGuaranteed();
        }
        byte[] bArr3 = this._inputBuffer;
        int r13 = this._inputPtr;
        this._inputPtr = r13 + 1;
        byte b3 = bArr3[r13];
        if ((b3 & 192) != 128) {
            _reportInvalidOther(b3 & 255, this._inputPtr);
        }
        return ((r53 << 6) | (b3 & Utf8.REPLACEMENT_BYTE)) - 65536;
    }

    private final void _skipUtf8_2() throws IOException {
        if (this._inputPtr >= this._inputEnd) {
            _loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int r1 = this._inputPtr;
        this._inputPtr = r1 + 1;
        byte b = bArr[r1];
        if ((b & 192) != 128) {
            _reportInvalidOther(b & 255, this._inputPtr);
        }
    }

    private final void _skipUtf8_3() throws IOException {
        if (this._inputPtr >= this._inputEnd) {
            _loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int r1 = this._inputPtr;
        this._inputPtr = r1 + 1;
        byte b = bArr[r1];
        if ((b & 192) != 128) {
            _reportInvalidOther(b & 255, this._inputPtr);
        }
        if (this._inputPtr >= this._inputEnd) {
            _loadMoreGuaranteed();
        }
        byte[] bArr2 = this._inputBuffer;
        int r12 = this._inputPtr;
        this._inputPtr = r12 + 1;
        byte b2 = bArr2[r12];
        if ((b2 & 192) != 128) {
            _reportInvalidOther(b2 & 255, this._inputPtr);
        }
    }

    private final void _skipUtf8_4(int r4) throws IOException {
        if (this._inputPtr >= this._inputEnd) {
            _loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int r0 = this._inputPtr;
        this._inputPtr = r0 + 1;
        byte b = bArr[r0];
        if ((b & 192) != 128) {
            _reportInvalidOther(b & 255, this._inputPtr);
        }
        if (this._inputPtr >= this._inputEnd) {
            _loadMoreGuaranteed();
        }
        byte[] bArr2 = this._inputBuffer;
        int r02 = this._inputPtr;
        this._inputPtr = r02 + 1;
        byte b2 = bArr2[r02];
        if ((b2 & 192) != 128) {
            _reportInvalidOther(b2 & 255, this._inputPtr);
        }
        if (this._inputPtr >= this._inputEnd) {
            _loadMoreGuaranteed();
        }
        byte[] bArr3 = this._inputBuffer;
        int r03 = this._inputPtr;
        this._inputPtr = r03 + 1;
        byte b3 = bArr3[r03];
        if ((b3 & 192) != 128) {
            _reportInvalidOther(b3 & 255, this._inputPtr);
        }
    }

    protected final void _skipCR() throws IOException {
        if ((this._inputPtr < this._inputEnd || _loadMore()) && this._inputBuffer[this._inputPtr] == 10) {
            this._inputPtr++;
        }
        this._currInputRow++;
        this._currInputRowStart = this._inputPtr;
    }

    private int nextByte() throws IOException {
        if (this._inputPtr >= this._inputEnd) {
            _loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int r1 = this._inputPtr;
        this._inputPtr = r1 + 1;
        return bArr[r1] & 255;
    }

    protected void _reportInvalidToken(String str) throws IOException {
        _reportInvalidToken(str, "'null', 'true', 'false' or NaN");
    }

    protected void _reportInvalidToken(String str, String str2) throws IOException {
        StringBuilder sb = new StringBuilder(str);
        while (sb.length() < 256 && (this._inputPtr < this._inputEnd || _loadMore())) {
            byte[] bArr = this._inputBuffer;
            int r2 = this._inputPtr;
            this._inputPtr = r2 + 1;
            char _decodeCharForError = (char) _decodeCharForError(bArr[r2]);
            if (!Character.isJavaIdentifierPart(_decodeCharForError)) {
                break;
            }
            sb.append(_decodeCharForError);
        }
        if (sb.length() == 256) {
            sb.append("...");
        }
        _reportError("Unrecognized token '" + sb.toString() + "': was expecting " + str2);
    }

    protected void _reportInvalidChar(int r2) throws JsonParseException {
        if (r2 < 32) {
            _throwInvalidSpace(r2);
        }
        _reportInvalidInitial(r2);
    }

    protected void _reportInvalidInitial(int r3) throws JsonParseException {
        _reportError("Invalid UTF-8 start byte 0x" + Integer.toHexString(r3));
    }

    protected void _reportInvalidOther(int r3) throws JsonParseException {
        _reportError("Invalid UTF-8 middle byte 0x" + Integer.toHexString(r3));
    }

    protected void _reportInvalidOther(int r1, int r2) throws JsonParseException {
        this._inputPtr = r2;
        _reportInvalidOther(r1);
    }

    public static int[] growArrayBy(int[] r1, int r2) {
        if (r1 == null) {
            return new int[r2];
        }
        return Arrays.copyOf(r1, r1.length + r2);
    }

    protected final byte[] _decodeBase64(Base64Variant base64Variant) throws IOException {
        ByteArrayBuilder _getByteArrayBuilder = _getByteArrayBuilder();
        while (true) {
            if (this._inputPtr >= this._inputEnd) {
                _loadMoreGuaranteed();
            }
            byte[] bArr = this._inputBuffer;
            int r2 = this._inputPtr;
            this._inputPtr = r2 + 1;
            int r1 = bArr[r2] & 255;
            if (r1 > 32) {
                int decodeBase64Char = base64Variant.decodeBase64Char(r1);
                if (decodeBase64Char < 0) {
                    if (r1 == 34) {
                        return _getByteArrayBuilder.toByteArray();
                    }
                    decodeBase64Char = _decodeBase64Escape(base64Variant, r1, 0);
                    if (decodeBase64Char < 0) {
                        continue;
                    }
                }
                if (this._inputPtr >= this._inputEnd) {
                    _loadMoreGuaranteed();
                }
                byte[] bArr2 = this._inputBuffer;
                int r4 = this._inputPtr;
                this._inputPtr = r4 + 1;
                int r12 = bArr2[r4] & 255;
                int decodeBase64Char2 = base64Variant.decodeBase64Char(r12);
                if (decodeBase64Char2 < 0) {
                    decodeBase64Char2 = _decodeBase64Escape(base64Variant, r12, 1);
                }
                int r13 = (decodeBase64Char << 6) | decodeBase64Char2;
                if (this._inputPtr >= this._inputEnd) {
                    _loadMoreGuaranteed();
                }
                byte[] bArr3 = this._inputBuffer;
                int r42 = this._inputPtr;
                this._inputPtr = r42 + 1;
                int r22 = bArr3[r42] & 255;
                int decodeBase64Char3 = base64Variant.decodeBase64Char(r22);
                if (decodeBase64Char3 < 0) {
                    if (decodeBase64Char3 != -2) {
                        if (r22 == 34 && !base64Variant.usesPadding()) {
                            _getByteArrayBuilder.append(r13 >> 4);
                            return _getByteArrayBuilder.toByteArray();
                        }
                        decodeBase64Char3 = _decodeBase64Escape(base64Variant, r22, 2);
                    }
                    if (decodeBase64Char3 == -2) {
                        if (this._inputPtr >= this._inputEnd) {
                            _loadMoreGuaranteed();
                        }
                        byte[] bArr4 = this._inputBuffer;
                        int r3 = this._inputPtr;
                        this._inputPtr = r3 + 1;
                        int r23 = bArr4[r3] & 255;
                        if (!base64Variant.usesPaddingChar(r23)) {
                            throw reportInvalidBase64Char(base64Variant, r23, 3, "expected padding character '" + base64Variant.getPaddingChar() + "'");
                        }
                        _getByteArrayBuilder.append(r13 >> 4);
                    }
                }
                int r14 = (r13 << 6) | decodeBase64Char3;
                if (this._inputPtr >= this._inputEnd) {
                    _loadMoreGuaranteed();
                }
                byte[] bArr5 = this._inputBuffer;
                int r43 = this._inputPtr;
                this._inputPtr = r43 + 1;
                int r24 = bArr5[r43] & 255;
                int decodeBase64Char4 = base64Variant.decodeBase64Char(r24);
                if (decodeBase64Char4 < 0) {
                    if (decodeBase64Char4 != -2) {
                        if (r24 == 34 && !base64Variant.usesPadding()) {
                            _getByteArrayBuilder.appendTwoBytes(r14 >> 2);
                            return _getByteArrayBuilder.toByteArray();
                        }
                        decodeBase64Char4 = _decodeBase64Escape(base64Variant, r24, 3);
                    }
                    if (decodeBase64Char4 == -2) {
                        _getByteArrayBuilder.appendTwoBytes(r14 >> 2);
                    }
                }
                _getByteArrayBuilder.appendThreeBytes((r14 << 6) | decodeBase64Char4);
            }
        }
    }

    @Override // com.fasterxml.jackson.core.base.ParserBase, com.fasterxml.jackson.core.JsonParser
    public JsonLocation getTokenLocation() {
        Object sourceReference = this._ioContext.getSourceReference();
        if (this._currToken == JsonToken.FIELD_NAME) {
            return new JsonLocation(sourceReference, (this._nameStartOffset - 1) + this._currInputProcessed, -1L, this._nameStartRow, this._nameStartCol);
        }
        return new JsonLocation(sourceReference, this._tokenInputTotal - 1, -1L, this._tokenInputRow, this._tokenInputCol);
    }

    @Override // com.fasterxml.jackson.core.base.ParserBase, com.fasterxml.jackson.core.JsonParser
    public JsonLocation getCurrentLocation() {
        return new JsonLocation(this._ioContext.getSourceReference(), this._currInputProcessed + this._inputPtr, -1L, this._currInputRow, (this._inputPtr - this._currInputRowStart) + 1);
    }

    private final void _updateLocation() {
        this._tokenInputRow = this._currInputRow;
        int r0 = this._inputPtr;
        this._tokenInputTotal = this._currInputProcessed + r0;
        this._tokenInputCol = r0 - this._currInputRowStart;
    }

    private final void _updateNameLocation() {
        this._nameStartRow = this._currInputRow;
        int r0 = this._inputPtr;
        this._nameStartOffset = r0;
        this._nameStartCol = r0 - this._currInputRowStart;
    }
}
