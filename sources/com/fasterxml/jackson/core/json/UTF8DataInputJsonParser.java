package com.fasterxml.jackson.core.json;

import com.facebook.hermes.intl.Constants;
import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.base.ParserBase;
import com.fasterxml.jackson.core.p009io.CharTypes;
import com.fasterxml.jackson.core.p009io.IOContext;
import com.fasterxml.jackson.core.sym.ByteQuadsCanonicalizer;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.google.android.exoplayer2.analytics.AnalyticsListener;
import java.io.DataInput;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Arrays;
import org.apache.commons.lang3.CharUtils;

/* loaded from: classes.dex */
public class UTF8DataInputJsonParser extends ParserBase {
    static final byte BYTE_LF = 10;
    protected DataInput _inputData;
    protected int _nextByte;
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

    @Override // com.fasterxml.jackson.core.base.ParserBase
    protected void _closeInput() throws IOException {
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public int releaseBuffered(OutputStream outputStream) throws IOException {
        return 0;
    }

    public UTF8DataInputJsonParser(IOContext iOContext, int r2, DataInput dataInput, ObjectCodec objectCodec, ByteQuadsCanonicalizer byteQuadsCanonicalizer, int r6) {
        super(iOContext, r2);
        this._quadBuffer = new int[16];
        this._objectCodec = objectCodec;
        this._symbols = byteQuadsCanonicalizer;
        this._inputData = dataInput;
        this._nextByte = r6;
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
    public Object getInputSource() {
        return this._inputData;
    }

    @Override // com.fasterxml.jackson.core.base.ParserBase
    protected void _releaseBuffers() throws IOException {
        super._releaseBuffers();
        this._symbols.release();
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
        if (this._currToken == JsonToken.VALUE_STRING) {
            if (this._tokenIncomplete) {
                this._tokenIncomplete = false;
                _finishString();
            }
            return this._textBuffer.size();
        } else if (this._currToken == JsonToken.FIELD_NAME) {
            return this._parsingContext.getCurrentName().length();
        } else {
            if (this._currToken != null) {
                if (this._currToken.isNumeric()) {
                    return this._textBuffer.size();
                }
                return this._currToken.asCharArray().length;
            }
            return 0;
        }
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
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.UTF8DataInputJsonParser.getTextOffset():int");
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
            int readUnsignedByte = this._inputData.readUnsignedByte();
            if (readUnsignedByte > 32) {
                int decodeBase64Char = base64Variant.decodeBase64Char(readUnsignedByte);
                if (decodeBase64Char < 0) {
                    if (readUnsignedByte == 34) {
                        break;
                    }
                    decodeBase64Char = _decodeBase64Escape(base64Variant, readUnsignedByte, 0);
                    if (decodeBase64Char < 0) {
                        continue;
                    }
                }
                if (r3 > length) {
                    r4 += r3;
                    outputStream.write(bArr, 0, r3);
                    r3 = 0;
                }
                int readUnsignedByte2 = this._inputData.readUnsignedByte();
                int decodeBase64Char2 = base64Variant.decodeBase64Char(readUnsignedByte2);
                if (decodeBase64Char2 < 0) {
                    decodeBase64Char2 = _decodeBase64Escape(base64Variant, readUnsignedByte2, 1);
                }
                int r5 = (decodeBase64Char << 6) | decodeBase64Char2;
                int readUnsignedByte3 = this._inputData.readUnsignedByte();
                int decodeBase64Char3 = base64Variant.decodeBase64Char(readUnsignedByte3);
                if (decodeBase64Char3 < 0) {
                    if (decodeBase64Char3 != -2) {
                        if (readUnsignedByte3 == 34 && !base64Variant.usesPadding()) {
                            bArr[r3] = (byte) (r5 >> 4);
                            r3++;
                            break;
                        }
                        decodeBase64Char3 = _decodeBase64Escape(base64Variant, readUnsignedByte3, 2);
                    }
                    if (decodeBase64Char3 == -2) {
                        int readUnsignedByte4 = this._inputData.readUnsignedByte();
                        if (!base64Variant.usesPaddingChar(readUnsignedByte4)) {
                            throw reportInvalidBase64Char(base64Variant, readUnsignedByte4, 3, "expected padding character '" + base64Variant.getPaddingChar() + "'");
                        }
                        r6 = r3 + 1;
                        bArr[r3] = (byte) (r5 >> 4);
                        r3 = r6;
                    }
                }
                int r52 = (r5 << 6) | decodeBase64Char3;
                int readUnsignedByte5 = this._inputData.readUnsignedByte();
                int decodeBase64Char4 = base64Variant.decodeBase64Char(readUnsignedByte5);
                if (decodeBase64Char4 < 0) {
                    if (decodeBase64Char4 != -2) {
                        if (readUnsignedByte5 == 34 && !base64Variant.usesPadding()) {
                            int r12 = r52 >> 2;
                            int r0 = r3 + 1;
                            bArr[r3] = (byte) (r12 >> 8);
                            r3 = r0 + 1;
                            bArr[r0] = (byte) r12;
                            break;
                        }
                        decodeBase64Char4 = _decodeBase64Escape(base64Variant, readUnsignedByte5, 3);
                    }
                    if (decodeBase64Char4 == -2) {
                        int r53 = r52 >> 2;
                        int r62 = r3 + 1;
                        bArr[r3] = (byte) (r53 >> 8);
                        r3 = r62 + 1;
                        bArr[r62] = (byte) r53;
                    }
                }
                int r54 = (r52 << 6) | decodeBase64Char4;
                int r63 = r3 + 1;
                bArr[r3] = (byte) (r54 >> 16);
                int r32 = r63 + 1;
                bArr[r63] = (byte) (r54 >> 8);
                r6 = r32 + 1;
                bArr[r32] = (byte) r54;
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
        int _skipWS = _skipWS();
        this._binaryValue = null;
        this._tokenInputRow = this._currInputRow;
        if (_skipWS == 93) {
            if (!this._parsingContext.inArray()) {
                _reportMismatchedEndMarker(_skipWS, '}');
            }
            this._parsingContext = this._parsingContext.clearAndGetParent();
            JsonToken jsonToken = JsonToken.END_ARRAY;
            this._currToken = jsonToken;
            return jsonToken;
        } else if (_skipWS == 125) {
            if (!this._parsingContext.inObject()) {
                _reportMismatchedEndMarker(_skipWS, ']');
            }
            this._parsingContext = this._parsingContext.clearAndGetParent();
            JsonToken jsonToken2 = JsonToken.END_OBJECT;
            this._currToken = jsonToken2;
            return jsonToken2;
        } else {
            if (this._parsingContext.expectComma()) {
                if (_skipWS != 44) {
                    _reportUnexpectedChar(_skipWS, "was expecting comma to separate " + this._parsingContext.typeDesc() + " entries");
                }
                _skipWS = _skipWS();
            }
            if (!this._parsingContext.inObject()) {
                return _nextTokenNotInObject(_skipWS);
            }
            this._parsingContext.setCurrentName(_parseName(_skipWS));
            this._currToken = JsonToken.FIELD_NAME;
            int _skipColon = _skipColon();
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
        int _skipWS = _skipWS();
        this._binaryValue = null;
        this._tokenInputRow = this._currInputRow;
        if (_skipWS == 93) {
            if (!this._parsingContext.inArray()) {
                _reportMismatchedEndMarker(_skipWS, '}');
            }
            this._parsingContext = this._parsingContext.clearAndGetParent();
            this._currToken = JsonToken.END_ARRAY;
            return null;
        } else if (_skipWS == 125) {
            if (!this._parsingContext.inObject()) {
                _reportMismatchedEndMarker(_skipWS, ']');
            }
            this._parsingContext = this._parsingContext.clearAndGetParent();
            this._currToken = JsonToken.END_OBJECT;
            return null;
        } else {
            if (this._parsingContext.expectComma()) {
                if (_skipWS != 44) {
                    _reportUnexpectedChar(_skipWS, "was expecting comma to separate " + this._parsingContext.typeDesc() + " entries");
                }
                _skipWS = _skipWS();
            }
            if (!this._parsingContext.inObject()) {
                _nextTokenNotInObject(_skipWS);
                return null;
            }
            String _parseName = _parseName(_skipWS);
            this._parsingContext.setCurrentName(_parseName);
            this._currToken = JsonToken.FIELD_NAME;
            int _skipColon = _skipColon();
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

    protected JsonToken _parsePosNumber(int r8) throws IOException {
        int readUnsignedByte;
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        int r1 = 1;
        if (r8 == 48) {
            readUnsignedByte = _handleLeadingZeroes();
            if (readUnsignedByte > 57 || readUnsignedByte < 48) {
                emptyAndGetCurrentSegment[0] = '0';
            } else {
                r1 = 0;
            }
        } else {
            emptyAndGetCurrentSegment[0] = (char) r8;
            readUnsignedByte = this._inputData.readUnsignedByte();
        }
        int r5 = readUnsignedByte;
        int r82 = r1;
        int r6 = r82;
        while (r5 <= 57 && r5 >= 48) {
            r6++;
            emptyAndGetCurrentSegment[r82] = (char) r5;
            r5 = this._inputData.readUnsignedByte();
            r82++;
        }
        if (r5 == 46 || r5 == 101 || r5 == 69) {
            return _parseFloat(emptyAndGetCurrentSegment, r82, r5, false, r6);
        }
        this._textBuffer.setCurrentLength(r82);
        if (this._parsingContext.inRoot()) {
            _verifyRootSpace();
        } else {
            this._nextByte = r5;
        }
        return resetInt(false, r6);
    }

    protected JsonToken _parseNegNumber() throws IOException {
        int readUnsignedByte;
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        emptyAndGetCurrentSegment[0] = '-';
        int readUnsignedByte2 = this._inputData.readUnsignedByte();
        emptyAndGetCurrentSegment[1] = (char) readUnsignedByte2;
        if (readUnsignedByte2 <= 48) {
            if (readUnsignedByte2 == 48) {
                readUnsignedByte = _handleLeadingZeroes();
            } else {
                return _handleInvalidNumberStart(readUnsignedByte2, true);
            }
        } else if (readUnsignedByte2 > 57) {
            return _handleInvalidNumberStart(readUnsignedByte2, true);
        } else {
            readUnsignedByte = this._inputData.readUnsignedByte();
        }
        int r5 = 2;
        int r6 = 1;
        while (readUnsignedByte <= 57 && readUnsignedByte >= 48) {
            r6++;
            emptyAndGetCurrentSegment[r5] = (char) readUnsignedByte;
            readUnsignedByte = this._inputData.readUnsignedByte();
            r5++;
        }
        if (readUnsignedByte == 46 || readUnsignedByte == 101 || readUnsignedByte == 69) {
            return _parseFloat(emptyAndGetCurrentSegment, r5, readUnsignedByte, true, r6);
        }
        this._textBuffer.setCurrentLength(r5);
        this._nextByte = readUnsignedByte;
        if (this._parsingContext.inRoot()) {
            _verifyRootSpace();
        }
        return resetInt(true, r6);
    }

    private final int _handleLeadingZeroes() throws IOException {
        int readUnsignedByte = this._inputData.readUnsignedByte();
        if (readUnsignedByte >= 48 && readUnsignedByte <= 57) {
            if (!isEnabled(JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS)) {
                reportInvalidNumber("Leading zeroes not allowed");
            }
            while (readUnsignedByte == 48) {
                readUnsignedByte = this._inputData.readUnsignedByte();
            }
        }
        return readUnsignedByte;
    }

    private final JsonToken _parseFloat(char[] cArr, int r9, int r10, boolean z, int r12) throws IOException {
        int r3;
        int r4;
        int readUnsignedByte;
        int r2 = 0;
        if (r10 == 46) {
            cArr[r9] = (char) r10;
            r9++;
            int r102 = 0;
            while (true) {
                readUnsignedByte = this._inputData.readUnsignedByte();
                if (readUnsignedByte < 48 || readUnsignedByte > 57) {
                    break;
                }
                r102++;
                if (r9 >= cArr.length) {
                    cArr = this._textBuffer.finishCurrentSegment();
                    r9 = 0;
                }
                cArr[r9] = (char) readUnsignedByte;
                r9++;
            }
            if (r102 == 0) {
                reportUnexpectedNumberChar(readUnsignedByte, "Decimal point not followed by a digit");
            }
            r3 = r102;
            r10 = readUnsignedByte;
        } else {
            r3 = 0;
        }
        if (r10 == 101 || r10 == 69) {
            if (r9 >= cArr.length) {
                cArr = this._textBuffer.finishCurrentSegment();
                r9 = 0;
            }
            int r42 = r9 + 1;
            cArr[r9] = (char) r10;
            int readUnsignedByte2 = this._inputData.readUnsignedByte();
            if (readUnsignedByte2 == 45 || readUnsignedByte2 == 43) {
                if (r42 >= cArr.length) {
                    cArr = this._textBuffer.finishCurrentSegment();
                    r42 = 0;
                }
                int r103 = r42 + 1;
                cArr[r42] = (char) readUnsignedByte2;
                r4 = 0;
                r10 = this._inputData.readUnsignedByte();
                r9 = r103;
            } else {
                r10 = readUnsignedByte2;
                r9 = r42;
                r4 = 0;
            }
            while (r10 <= 57 && r10 >= 48) {
                r4++;
                if (r9 >= cArr.length) {
                    cArr = this._textBuffer.finishCurrentSegment();
                    r9 = 0;
                }
                cArr[r9] = (char) r10;
                r10 = this._inputData.readUnsignedByte();
                r9++;
            }
            if (r4 == 0) {
                reportUnexpectedNumberChar(r10, "Exponent indicator not followed by a digit");
            }
            r2 = r4;
        }
        this._nextByte = r10;
        if (this._parsingContext.inRoot()) {
            _verifyRootSpace();
        }
        this._textBuffer.setCurrentLength(r9);
        return resetFloat(z, r12, r3, r2);
    }

    private final void _verifyRootSpace() throws IOException {
        int r0 = this._nextByte;
        if (r0 <= 32) {
            this._nextByte = -1;
            if (r0 == 13 || r0 == 10) {
                this._currInputRow++;
                return;
            }
            return;
        }
        _reportMissingRootWS(r0);
    }

    protected final String _parseName(int r5) throws IOException {
        if (r5 != 34) {
            return _handleOddName(r5);
        }
        int[] r52 = _icLatin1;
        int readUnsignedByte = this._inputData.readUnsignedByte();
        if (r52[readUnsignedByte] != 0) {
            return readUnsignedByte == 34 ? "" : parseName(0, readUnsignedByte, 0);
        }
        int readUnsignedByte2 = this._inputData.readUnsignedByte();
        if (r52[readUnsignedByte2] != 0) {
            if (readUnsignedByte2 == 34) {
                return findName(readUnsignedByte, 1);
            }
            return parseName(readUnsignedByte, readUnsignedByte2, 1);
        }
        int r1 = (readUnsignedByte << 8) | readUnsignedByte2;
        int readUnsignedByte3 = this._inputData.readUnsignedByte();
        if (r52[readUnsignedByte3] != 0) {
            if (readUnsignedByte3 == 34) {
                return findName(r1, 2);
            }
            return parseName(r1, readUnsignedByte3, 2);
        }
        int r12 = (r1 << 8) | readUnsignedByte3;
        int readUnsignedByte4 = this._inputData.readUnsignedByte();
        if (r52[readUnsignedByte4] != 0) {
            if (readUnsignedByte4 == 34) {
                return findName(r12, 3);
            }
            return parseName(r12, readUnsignedByte4, 3);
        }
        int r13 = (r12 << 8) | readUnsignedByte4;
        int readUnsignedByte5 = this._inputData.readUnsignedByte();
        if (r52[readUnsignedByte5] == 0) {
            this._quad1 = r13;
            return _parseMediumName(readUnsignedByte5);
        } else if (readUnsignedByte5 == 34) {
            return findName(r13, 4);
        } else {
            return parseName(r13, readUnsignedByte5, 4);
        }
    }

    private final String _parseMediumName(int r5) throws IOException {
        int[] r0 = _icLatin1;
        int readUnsignedByte = this._inputData.readUnsignedByte();
        if (r0[readUnsignedByte] != 0) {
            if (readUnsignedByte == 34) {
                return findName(this._quad1, r5, 1);
            }
            return parseName(this._quad1, r5, readUnsignedByte, 1);
        }
        int r52 = (r5 << 8) | readUnsignedByte;
        int readUnsignedByte2 = this._inputData.readUnsignedByte();
        if (r0[readUnsignedByte2] != 0) {
            if (readUnsignedByte2 == 34) {
                return findName(this._quad1, r52, 2);
            }
            return parseName(this._quad1, r52, readUnsignedByte2, 2);
        }
        int r53 = (r52 << 8) | readUnsignedByte2;
        int readUnsignedByte3 = this._inputData.readUnsignedByte();
        if (r0[readUnsignedByte3] != 0) {
            if (readUnsignedByte3 == 34) {
                return findName(this._quad1, r53, 3);
            }
            return parseName(this._quad1, r53, readUnsignedByte3, 3);
        }
        int r54 = (r53 << 8) | readUnsignedByte3;
        int readUnsignedByte4 = this._inputData.readUnsignedByte();
        if (r0[readUnsignedByte4] != 0) {
            if (readUnsignedByte4 == 34) {
                return findName(this._quad1, r54, 4);
            }
            return parseName(this._quad1, r54, readUnsignedByte4, 4);
        }
        return _parseMediumName2(readUnsignedByte4, r54);
    }

    private final String _parseMediumName2(int r7, int r8) throws IOException {
        int[] r0 = _icLatin1;
        int readUnsignedByte = this._inputData.readUnsignedByte();
        if (r0[readUnsignedByte] != 0) {
            if (readUnsignedByte == 34) {
                return findName(this._quad1, r8, r7, 1);
            }
            return parseName(this._quad1, r8, r7, readUnsignedByte, 1);
        }
        int r3 = (r7 << 8) | readUnsignedByte;
        int readUnsignedByte2 = this._inputData.readUnsignedByte();
        if (r0[readUnsignedByte2] != 0) {
            if (readUnsignedByte2 == 34) {
                return findName(this._quad1, r8, r3, 2);
            }
            return parseName(this._quad1, r8, r3, readUnsignedByte2, 2);
        }
        int r32 = (r3 << 8) | readUnsignedByte2;
        int readUnsignedByte3 = this._inputData.readUnsignedByte();
        if (r0[readUnsignedByte3] != 0) {
            if (readUnsignedByte3 == 34) {
                return findName(this._quad1, r8, r32, 3);
            }
            return parseName(this._quad1, r8, r32, readUnsignedByte3, 3);
        }
        int r33 = (r32 << 8) | readUnsignedByte3;
        int readUnsignedByte4 = this._inputData.readUnsignedByte();
        if (r0[readUnsignedByte4] != 0) {
            if (readUnsignedByte4 == 34) {
                return findName(this._quad1, r8, r33, 4);
            }
            return parseName(this._quad1, r8, r33, readUnsignedByte4, 4);
        }
        return _parseLongName(readUnsignedByte4, r8, r33);
    }

    private final String _parseLongName(int r13, int r14, int r15) throws IOException {
        int[] r0 = this._quadBuffer;
        r0[0] = this._quad1;
        r0[1] = r14;
        r0[2] = r15;
        int[] r152 = _icLatin1;
        int r5 = r13;
        int r8 = 3;
        while (true) {
            int readUnsignedByte = this._inputData.readUnsignedByte();
            if (r152[readUnsignedByte] != 0) {
                if (readUnsignedByte == 34) {
                    return findName(this._quadBuffer, r8, r5, 1);
                }
                return parseEscapedName(this._quadBuffer, r8, r5, readUnsignedByte, 1);
            }
            int r9 = (r5 << 8) | readUnsignedByte;
            int readUnsignedByte2 = this._inputData.readUnsignedByte();
            if (r152[readUnsignedByte2] != 0) {
                if (readUnsignedByte2 == 34) {
                    return findName(this._quadBuffer, r8, r9, 2);
                }
                return parseEscapedName(this._quadBuffer, r8, r9, readUnsignedByte2, 2);
            }
            int r92 = (r9 << 8) | readUnsignedByte2;
            int readUnsignedByte3 = this._inputData.readUnsignedByte();
            if (r152[readUnsignedByte3] != 0) {
                if (readUnsignedByte3 == 34) {
                    return findName(this._quadBuffer, r8, r92, 3);
                }
                return parseEscapedName(this._quadBuffer, r8, r92, readUnsignedByte3, 3);
            }
            int r93 = (r92 << 8) | readUnsignedByte3;
            int readUnsignedByte4 = this._inputData.readUnsignedByte();
            if (r152[readUnsignedByte4] != 0) {
                if (readUnsignedByte4 == 34) {
                    return findName(this._quadBuffer, r8, r93, 4);
                }
                return parseEscapedName(this._quadBuffer, r8, r93, readUnsignedByte4, 4);
            }
            int[] r132 = this._quadBuffer;
            if (r8 >= r132.length) {
                this._quadBuffer = _growArrayBy(r132, r8);
            }
            this._quadBuffer[r8] = r93;
            r8++;
            r5 = readUnsignedByte4;
        }
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
                            r6 = _growArrayBy(r6, r6.length);
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
                                r6 = _growArrayBy(r6, r6.length);
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
                    r6 = _growArrayBy(r6, r6.length);
                    this._quadBuffer = r6;
                }
                r6[r7] = r8;
                r8 = r9;
                r7++;
                r10 = 1;
            }
            r9 = this._inputData.readUnsignedByte();
        }
        if (r10 > 0) {
            if (r7 >= r6.length) {
                r6 = _growArrayBy(r6, r6.length);
                this._quadBuffer = r6;
            }
            r6[r7] = pad(r8, r10);
            r7++;
        }
        String findName = this._symbols.findName(r6, r7);
        return findName == null ? addName(r6, r7, r10) : findName;
    }

    protected String _handleOddName(int r7) throws IOException {
        if (r7 == 39 && isEnabled(JsonParser.Feature.ALLOW_SINGLE_QUOTES)) {
            return _parseAposName();
        }
        if (!isEnabled(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES)) {
            _reportUnexpectedChar((char) _decodeCharForError(r7), "was expecting double-quote to start field name");
        }
        int[] inputCodeUtf8JsNames = CharTypes.getInputCodeUtf8JsNames();
        if (inputCodeUtf8JsNames[r7] != 0) {
            _reportUnexpectedChar(r7, "was expecting either valid name character (for unquoted name) or double-quote (for quoted) to start field name");
        }
        int[] r1 = this._quadBuffer;
        int r2 = 0;
        int r3 = 0;
        int r4 = 0;
        do {
            if (r2 < 4) {
                r2++;
                r4 = r7 | (r4 << 8);
            } else {
                if (r3 >= r1.length) {
                    r1 = _growArrayBy(r1, r1.length);
                    this._quadBuffer = r1;
                }
                r1[r3] = r4;
                r4 = r7;
                r3++;
                r2 = 1;
            }
            r7 = this._inputData.readUnsignedByte();
        } while (inputCodeUtf8JsNames[r7] == 0);
        this._nextByte = r7;
        if (r2 > 0) {
            if (r3 >= r1.length) {
                int[] _growArrayBy = _growArrayBy(r1, r1.length);
                this._quadBuffer = _growArrayBy;
                r1 = _growArrayBy;
            }
            r1[r3] = r4;
            r3++;
        }
        String findName = this._symbols.findName(r1, r3);
        return findName == null ? addName(r1, r3, r2) : findName;
    }

    protected String _parseAposName() throws IOException {
        int readUnsignedByte = this._inputData.readUnsignedByte();
        if (readUnsignedByte == 39) {
            return "";
        }
        int[] r2 = this._quadBuffer;
        int[] r3 = _icLatin1;
        int r5 = 0;
        int r6 = 0;
        int r7 = 0;
        while (readUnsignedByte != 39) {
            if (readUnsignedByte != 34 && r3[readUnsignedByte] != 0) {
                if (readUnsignedByte != 92) {
                    _throwUnquotedSpace(readUnsignedByte, "name");
                } else {
                    readUnsignedByte = _decodeEscaped();
                }
                if (readUnsignedByte > 127) {
                    if (r5 >= 4) {
                        if (r6 >= r2.length) {
                            r2 = _growArrayBy(r2, r2.length);
                            this._quadBuffer = r2;
                        }
                        r2[r6] = r7;
                        r6++;
                        r5 = 0;
                        r7 = 0;
                    }
                    if (readUnsignedByte < 2048) {
                        r7 = (r7 << 8) | (readUnsignedByte >> 6) | 192;
                        r5++;
                    } else {
                        int r72 = (r7 << 8) | (readUnsignedByte >> 12) | 224;
                        int r52 = r5 + 1;
                        if (r52 >= 4) {
                            if (r6 >= r2.length) {
                                r2 = _growArrayBy(r2, r2.length);
                                this._quadBuffer = r2;
                            }
                            r2[r6] = r72;
                            r6++;
                            r52 = 0;
                            r72 = 0;
                        }
                        r7 = (r72 << 8) | ((readUnsignedByte >> 6) & 63) | 128;
                        r5 = r52 + 1;
                    }
                    readUnsignedByte = (readUnsignedByte & 63) | 128;
                }
            }
            if (r5 < 4) {
                r5++;
                r7 = readUnsignedByte | (r7 << 8);
            } else {
                if (r6 >= r2.length) {
                    r2 = _growArrayBy(r2, r2.length);
                    this._quadBuffer = r2;
                }
                r2[r6] = r7;
                r7 = readUnsignedByte;
                r6++;
                r5 = 1;
            }
            readUnsignedByte = this._inputData.readUnsignedByte();
        }
        if (r5 > 0) {
            if (r6 >= r2.length) {
                int[] _growArrayBy = _growArrayBy(r2, r2.length);
                this._quadBuffer = _growArrayBy;
                r2 = _growArrayBy;
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
            r2 = _growArrayBy(r2, r2.length);
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
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.UTF8DataInputJsonParser.addName(int[], int, int):java.lang.String");
    }

    @Override // com.fasterxml.jackson.core.base.ParserBase
    protected void _finishString() throws IOException {
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        int[] r1 = _icUTF8;
        int length = emptyAndGetCurrentSegment.length;
        int r3 = 0;
        while (true) {
            int readUnsignedByte = this._inputData.readUnsignedByte();
            if (r1[readUnsignedByte] != 0) {
                if (readUnsignedByte == 34) {
                    this._textBuffer.setCurrentLength(r3);
                    return;
                } else {
                    _finishString2(emptyAndGetCurrentSegment, r3, readUnsignedByte);
                    return;
                }
            }
            int r5 = r3 + 1;
            emptyAndGetCurrentSegment[r3] = (char) readUnsignedByte;
            if (r5 >= length) {
                _finishString2(emptyAndGetCurrentSegment, r5, this._inputData.readUnsignedByte());
                return;
            }
            r3 = r5;
        }
    }

    private String _finishAndReturnString() throws IOException {
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        int[] r1 = _icUTF8;
        int length = emptyAndGetCurrentSegment.length;
        int r3 = 0;
        while (true) {
            int readUnsignedByte = this._inputData.readUnsignedByte();
            if (r1[readUnsignedByte] != 0) {
                if (readUnsignedByte == 34) {
                    return this._textBuffer.setCurrentAndReturn(r3);
                }
                _finishString2(emptyAndGetCurrentSegment, r3, readUnsignedByte);
                return this._textBuffer.contentsAsString();
            }
            int r5 = r3 + 1;
            emptyAndGetCurrentSegment[r3] = (char) readUnsignedByte;
            if (r5 >= length) {
                _finishString2(emptyAndGetCurrentSegment, r5, this._inputData.readUnsignedByte());
                return this._textBuffer.contentsAsString();
            }
            r3 = r5;
        }
    }

    private final void _finishString2(char[] cArr, int r8, int r9) throws IOException {
        int[] r0 = _icUTF8;
        int length = cArr.length;
        while (true) {
            int r3 = 0;
            if (r0[r9] == 0) {
                if (r8 >= length) {
                    cArr = this._textBuffer.finishCurrentSegment();
                    length = cArr.length;
                    r8 = 0;
                }
                cArr[r8] = (char) r9;
                r9 = this._inputData.readUnsignedByte();
                r8++;
            } else if (r9 != 34) {
                int r2 = r0[r9];
                if (r2 == 1) {
                    r9 = _decodeEscaped();
                } else if (r2 == 2) {
                    r9 = _decodeUtf8_2(r9);
                } else if (r2 == 3) {
                    r9 = _decodeUtf8_3(r9);
                } else if (r2 == 4) {
                    int _decodeUtf8_4 = _decodeUtf8_4(r9);
                    int r22 = r8 + 1;
                    cArr[r8] = (char) (55296 | (_decodeUtf8_4 >> 10));
                    if (r22 >= cArr.length) {
                        cArr = this._textBuffer.finishCurrentSegment();
                        length = cArr.length;
                        r8 = 0;
                    } else {
                        r8 = r22;
                    }
                    r9 = (_decodeUtf8_4 & AnalyticsListener.EVENT_DRM_KEYS_LOADED) | 56320;
                } else if (r9 < 32) {
                    _throwUnquotedSpace(r9, "string value");
                } else {
                    _reportInvalidChar(r9);
                }
                if (r8 >= cArr.length) {
                    cArr = this._textBuffer.finishCurrentSegment();
                    length = cArr.length;
                } else {
                    r3 = r8;
                }
                r8 = r3 + 1;
                cArr[r3] = (char) r9;
                r9 = this._inputData.readUnsignedByte();
            } else {
                this._textBuffer.setCurrentLength(r8);
                return;
            }
        }
    }

    protected void _skipString() throws IOException {
        this._tokenIncomplete = false;
        int[] r0 = _icUTF8;
        while (true) {
            int readUnsignedByte = this._inputData.readUnsignedByte();
            if (r0[readUnsignedByte] != 0) {
                if (readUnsignedByte == 34) {
                    return;
                }
                int r2 = r0[readUnsignedByte];
                if (r2 == 1) {
                    _decodeEscaped();
                } else if (r2 == 2) {
                    _skipUtf8_2();
                } else if (r2 == 3) {
                    _skipUtf8_3();
                } else if (r2 == 4) {
                    _skipUtf8_4();
                } else if (readUnsignedByte < 32) {
                    _throwUnquotedSpace(readUnsignedByte, "string value");
                } else {
                    _reportInvalidChar(readUnsignedByte);
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x001b, code lost:
        if (r4 != 44) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0030, code lost:
        if (r3._parsingContext.inArray() == false) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0039, code lost:
        if (isEnabled(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_MISSING_VALUES) == false) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x003b, code lost:
        r3._nextByte = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x003f, code lost:
        return com.fasterxml.jackson.core.JsonToken.VALUE_NULL;
     */
    /* JADX WARN: Removed duplicated region for block: B:43:0x008d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected com.fasterxml.jackson.core.JsonToken _handleUnexpectedValue(int r4) throws java.io.IOException {
        /*
            r3 = this;
            r0 = 39
            if (r4 == r0) goto L7a
            r0 = 73
            r1 = 1
            if (r4 == r0) goto L60
            r0 = 78
            if (r4 == r0) goto L46
            r0 = 93
            if (r4 == r0) goto L2a
            r0 = 125(0x7d, float:1.75E-43)
            if (r4 == r0) goto L40
            r0 = 43
            if (r4 == r0) goto L1e
            r0 = 44
            if (r4 == r0) goto L33
            goto L87
        L1e:
            java.io.DataInput r4 = r3._inputData
            int r4 = r4.readUnsignedByte()
            r0 = 0
            com.fasterxml.jackson.core.JsonToken r4 = r3._handleInvalidNumberStart(r4, r0)
            return r4
        L2a:
            com.fasterxml.jackson.core.json.JsonReadContext r0 = r3._parsingContext
            boolean r0 = r0.inArray()
            if (r0 != 0) goto L33
            goto L87
        L33:
            com.fasterxml.jackson.core.JsonParser$Feature r0 = com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_MISSING_VALUES
            boolean r0 = r3.isEnabled(r0)
            if (r0 == 0) goto L40
            r3._nextByte = r4
            com.fasterxml.jackson.core.JsonToken r4 = com.fasterxml.jackson.core.JsonToken.VALUE_NULL
            return r4
        L40:
            java.lang.String r0 = "expected a value"
            r3._reportUnexpectedChar(r4, r0)
            goto L7a
        L46:
            java.lang.String r0 = "NaN"
            r3._matchToken(r0, r1)
            com.fasterxml.jackson.core.JsonParser$Feature r1 = com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS
            boolean r1 = r3.isEnabled(r1)
            if (r1 == 0) goto L5a
            r1 = 9221120237041090560(0x7ff8000000000000, double:NaN)
            com.fasterxml.jackson.core.JsonToken r4 = r3.resetAsNaN(r0, r1)
            return r4
        L5a:
            java.lang.String r0 = "Non-standard token 'NaN': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow"
            r3._reportError(r0)
            goto L87
        L60:
            java.lang.String r0 = "Infinity"
            r3._matchToken(r0, r1)
            com.fasterxml.jackson.core.JsonParser$Feature r1 = com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS
            boolean r1 = r3.isEnabled(r1)
            if (r1 == 0) goto L74
            r1 = 9218868437227405312(0x7ff0000000000000, double:Infinity)
            com.fasterxml.jackson.core.JsonToken r4 = r3.resetAsNaN(r0, r1)
            return r4
        L74:
            java.lang.String r0 = "Non-standard token 'Infinity': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow"
            r3._reportError(r0)
            goto L87
        L7a:
            com.fasterxml.jackson.core.JsonParser$Feature r0 = com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_SINGLE_QUOTES
            boolean r0 = r3.isEnabled(r0)
            if (r0 == 0) goto L87
            com.fasterxml.jackson.core.JsonToken r4 = r3._handleApos()
            return r4
        L87:
            boolean r0 = java.lang.Character.isJavaIdentifierStart(r4)
            if (r0 == 0) goto La4
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = ""
            r0.append(r1)
            char r1 = (char) r4
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "('true', 'false' or 'null')"
            r3._reportInvalidToken(r4, r0, r1)
        La4:
            java.lang.String r0 = "expected a valid value (number, String, array, object, 'true', 'false' or 'null')"
            r3._reportUnexpectedChar(r4, r0)
            r4 = 0
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.UTF8DataInputJsonParser._handleUnexpectedValue(int):com.fasterxml.jackson.core.JsonToken");
    }

    protected JsonToken _handleApos() throws IOException {
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        int[] r1 = _icUTF8;
        int r3 = 0;
        while (true) {
            int length = emptyAndGetCurrentSegment.length;
            if (r3 >= emptyAndGetCurrentSegment.length) {
                emptyAndGetCurrentSegment = this._textBuffer.finishCurrentSegment();
                length = emptyAndGetCurrentSegment.length;
                r3 = 0;
            }
            while (true) {
                int readUnsignedByte = this._inputData.readUnsignedByte();
                if (readUnsignedByte != 39) {
                    if (r1[readUnsignedByte] == 0) {
                        int r6 = r3 + 1;
                        emptyAndGetCurrentSegment[r3] = (char) readUnsignedByte;
                        r3 = r6;
                        if (r6 >= length) {
                            break;
                        }
                    } else {
                        int r4 = r1[readUnsignedByte];
                        if (r4 == 1) {
                            readUnsignedByte = _decodeEscaped();
                        } else if (r4 == 2) {
                            readUnsignedByte = _decodeUtf8_2(readUnsignedByte);
                        } else if (r4 == 3) {
                            readUnsignedByte = _decodeUtf8_3(readUnsignedByte);
                        } else if (r4 == 4) {
                            int _decodeUtf8_4 = _decodeUtf8_4(readUnsignedByte);
                            int r5 = r3 + 1;
                            emptyAndGetCurrentSegment[r3] = (char) (55296 | (_decodeUtf8_4 >> 10));
                            if (r5 >= emptyAndGetCurrentSegment.length) {
                                emptyAndGetCurrentSegment = this._textBuffer.finishCurrentSegment();
                                r3 = 0;
                            } else {
                                r3 = r5;
                            }
                            readUnsignedByte = 56320 | (_decodeUtf8_4 & AnalyticsListener.EVENT_DRM_KEYS_LOADED);
                        } else {
                            if (readUnsignedByte < 32) {
                                _throwUnquotedSpace(readUnsignedByte, "string value");
                            }
                            _reportInvalidChar(readUnsignedByte);
                        }
                        if (r3 >= emptyAndGetCurrentSegment.length) {
                            emptyAndGetCurrentSegment = this._textBuffer.finishCurrentSegment();
                            r3 = 0;
                        }
                        emptyAndGetCurrentSegment[r3] = (char) readUnsignedByte;
                        r3++;
                    }
                } else {
                    this._textBuffer.setCurrentLength(r3);
                    return JsonToken.VALUE_STRING;
                }
            }
        }
    }

    protected JsonToken _handleInvalidNumberStart(int r4, boolean z) throws IOException {
        String str;
        while (r4 == 73) {
            r4 = this._inputData.readUnsignedByte();
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
        do {
            int readUnsignedByte = this._inputData.readUnsignedByte();
            if (readUnsignedByte != str.charAt(r5)) {
                _reportInvalidToken(readUnsignedByte, str.substring(0, r5));
            }
            r5++;
        } while (r5 < length);
        int readUnsignedByte2 = this._inputData.readUnsignedByte();
        if (readUnsignedByte2 >= 48 && readUnsignedByte2 != 93 && readUnsignedByte2 != 125) {
            _checkMatchEnd(str, r5, readUnsignedByte2);
        }
        this._nextByte = readUnsignedByte2;
    }

    private final void _checkMatchEnd(String str, int r3, int r4) throws IOException {
        char _decodeCharForError = (char) _decodeCharForError(r4);
        if (Character.isJavaIdentifierPart(_decodeCharForError)) {
            _reportInvalidToken(_decodeCharForError, str.substring(0, r3));
        }
    }

    private final int _skipWS() throws IOException {
        int r0 = this._nextByte;
        if (r0 < 0) {
            r0 = this._inputData.readUnsignedByte();
        } else {
            this._nextByte = -1;
        }
        while (r0 <= 32) {
            if (r0 == 13 || r0 == 10) {
                this._currInputRow++;
            }
            r0 = this._inputData.readUnsignedByte();
        }
        return (r0 == 47 || r0 == 35) ? _skipWSComment(r0) : r0;
    }

    private final int _skipWSComment(int r2) throws IOException {
        while (true) {
            if (r2 > 32) {
                if (r2 == 47) {
                    _skipComment();
                } else if (r2 != 35 || !_skipYAMLComment()) {
                    break;
                }
            } else if (r2 == 13 || r2 == 10) {
                this._currInputRow++;
            }
            r2 = this._inputData.readUnsignedByte();
        }
        return r2;
    }

    private final int _skipColon() throws IOException {
        int r0 = this._nextByte;
        if (r0 < 0) {
            r0 = this._inputData.readUnsignedByte();
        } else {
            this._nextByte = -1;
        }
        if (r0 == 58) {
            int readUnsignedByte = this._inputData.readUnsignedByte();
            if (readUnsignedByte > 32) {
                return (readUnsignedByte == 47 || readUnsignedByte == 35) ? _skipColon2(readUnsignedByte, true) : readUnsignedByte;
            } else if ((readUnsignedByte == 32 || readUnsignedByte == 9) && (readUnsignedByte = this._inputData.readUnsignedByte()) > 32) {
                return (readUnsignedByte == 47 || readUnsignedByte == 35) ? _skipColon2(readUnsignedByte, true) : readUnsignedByte;
            } else {
                return _skipColon2(readUnsignedByte, true);
            }
        }
        if (r0 == 32 || r0 == 9) {
            r0 = this._inputData.readUnsignedByte();
        }
        if (r0 == 58) {
            int readUnsignedByte2 = this._inputData.readUnsignedByte();
            if (readUnsignedByte2 > 32) {
                return (readUnsignedByte2 == 47 || readUnsignedByte2 == 35) ? _skipColon2(readUnsignedByte2, true) : readUnsignedByte2;
            } else if ((readUnsignedByte2 == 32 || readUnsignedByte2 == 9) && (readUnsignedByte2 = this._inputData.readUnsignedByte()) > 32) {
                return (readUnsignedByte2 == 47 || readUnsignedByte2 == 35) ? _skipColon2(readUnsignedByte2, true) : readUnsignedByte2;
            } else {
                return _skipColon2(readUnsignedByte2, true);
            }
        }
        return _skipColon2(r0, false);
    }

    private final int _skipColon2(int r3, boolean z) throws IOException {
        while (true) {
            if (r3 > 32) {
                if (r3 == 47) {
                    _skipComment();
                } else if (r3 != 35 || !_skipYAMLComment()) {
                    if (z) {
                        return r3;
                    }
                    if (r3 != 58) {
                        _reportUnexpectedChar(r3, "was expecting a colon to separate field name and value");
                    }
                    z = true;
                }
            } else if (r3 == 13 || r3 == 10) {
                this._currInputRow++;
            }
            r3 = this._inputData.readUnsignedByte();
        }
    }

    private final void _skipComment() throws IOException {
        if (!isEnabled(JsonParser.Feature.ALLOW_COMMENTS)) {
            _reportUnexpectedChar(47, "maybe a (non-standard) comment? (not recognized as one since Feature 'ALLOW_COMMENTS' not enabled for parser)");
        }
        int readUnsignedByte = this._inputData.readUnsignedByte();
        if (readUnsignedByte == 47) {
            _skipLine();
        } else if (readUnsignedByte == 42) {
            _skipCComment();
        } else {
            _reportUnexpectedChar(readUnsignedByte, "was expecting either '*' or '/' for a comment");
        }
    }

    private final void _skipCComment() throws IOException {
        int[] inputCodeComment = CharTypes.getInputCodeComment();
        int readUnsignedByte = this._inputData.readUnsignedByte();
        while (true) {
            int r2 = inputCodeComment[readUnsignedByte];
            if (r2 != 0) {
                if (r2 == 2) {
                    _skipUtf8_2();
                } else if (r2 == 3) {
                    _skipUtf8_3();
                } else if (r2 == 4) {
                    _skipUtf8_4();
                } else if (r2 == 10 || r2 == 13) {
                    this._currInputRow++;
                } else if (r2 == 42) {
                    readUnsignedByte = this._inputData.readUnsignedByte();
                    if (readUnsignedByte == 47) {
                        return;
                    }
                } else {
                    _reportInvalidChar(readUnsignedByte);
                }
            }
            readUnsignedByte = this._inputData.readUnsignedByte();
        }
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
            int readUnsignedByte = this._inputData.readUnsignedByte();
            int r2 = inputCodeComment[readUnsignedByte];
            if (r2 != 0) {
                if (r2 == 2) {
                    _skipUtf8_2();
                } else if (r2 == 3) {
                    _skipUtf8_3();
                } else if (r2 == 4) {
                    _skipUtf8_4();
                } else if (r2 == 10 || r2 == 13) {
                    break;
                } else if (r2 != 42 && r2 < 0) {
                    _reportInvalidChar(readUnsignedByte);
                }
            }
        }
        this._currInputRow++;
    }

    @Override // com.fasterxml.jackson.core.base.ParserBase
    protected char _decodeEscaped() throws IOException {
        int readUnsignedByte = this._inputData.readUnsignedByte();
        if (readUnsignedByte == 34 || readUnsignedByte == 47 || readUnsignedByte == 92) {
            return (char) readUnsignedByte;
        }
        if (readUnsignedByte != 98) {
            if (readUnsignedByte != 102) {
                if (readUnsignedByte != 110) {
                    if (readUnsignedByte != 114) {
                        if (readUnsignedByte != 116) {
                            if (readUnsignedByte != 117) {
                                return _handleUnrecognizedCharacterEscape((char) _decodeCharForError(readUnsignedByte));
                            }
                            int r1 = 0;
                            for (int r0 = 0; r0 < 4; r0++) {
                                int readUnsignedByte2 = this._inputData.readUnsignedByte();
                                int charToHex = CharTypes.charToHex(readUnsignedByte2);
                                if (charToHex < 0) {
                                    _reportUnexpectedChar(readUnsignedByte2, "expected a hex-digit for character escape sequence");
                                }
                                r1 = (r1 << 4) | charToHex;
                            }
                            return (char) r1;
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

    /* JADX WARN: Removed duplicated region for block: B:17:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0044  */
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
            if (r7 <= r0) goto L6e
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
            java.io.DataInput r3 = r6._inputData
            int r3 = r3.readUnsignedByte()
            r4 = r3 & 192(0xc0, float:2.69E-43)
            r5 = 128(0x80, float:1.794E-43)
            if (r4 == r5) goto L3d
            r4 = r3 & 255(0xff, float:3.57E-43)
            r6._reportInvalidOther(r4)
        L3d:
            int r7 = r7 << 6
            r3 = r3 & 63
            r7 = r7 | r3
            if (r0 <= r2) goto L6e
            java.io.DataInput r2 = r6._inputData
            int r2 = r2.readUnsignedByte()
            r3 = r2 & 192(0xc0, float:2.69E-43)
            if (r3 == r5) goto L53
            r3 = r2 & 255(0xff, float:3.57E-43)
            r6._reportInvalidOther(r3)
        L53:
            int r7 = r7 << 6
            r2 = r2 & 63
            r7 = r7 | r2
            if (r0 <= r1) goto L6e
            java.io.DataInput r0 = r6._inputData
            int r0 = r0.readUnsignedByte()
            r1 = r0 & 192(0xc0, float:2.69E-43)
            if (r1 == r5) goto L69
            r1 = r0 & 255(0xff, float:3.57E-43)
            r6._reportInvalidOther(r1)
        L69:
            int r7 = r7 << 6
            r0 = r0 & 63
            r7 = r7 | r0
        L6e:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.UTF8DataInputJsonParser._decodeCharForError(int):int");
    }

    private final int _decodeUtf8_2(int r4) throws IOException {
        int readUnsignedByte = this._inputData.readUnsignedByte();
        if ((readUnsignedByte & 192) != 128) {
            _reportInvalidOther(readUnsignedByte & 255);
        }
        return ((r4 & 31) << 6) | (readUnsignedByte & 63);
    }

    private final int _decodeUtf8_3(int r4) throws IOException {
        int r42 = r4 & 15;
        int readUnsignedByte = this._inputData.readUnsignedByte();
        if ((readUnsignedByte & 192) != 128) {
            _reportInvalidOther(readUnsignedByte & 255);
        }
        int r43 = (r42 << 6) | (readUnsignedByte & 63);
        int readUnsignedByte2 = this._inputData.readUnsignedByte();
        if ((readUnsignedByte2 & 192) != 128) {
            _reportInvalidOther(readUnsignedByte2 & 255);
        }
        return (r43 << 6) | (readUnsignedByte2 & 63);
    }

    private final int _decodeUtf8_4(int r4) throws IOException {
        int readUnsignedByte = this._inputData.readUnsignedByte();
        if ((readUnsignedByte & 192) != 128) {
            _reportInvalidOther(readUnsignedByte & 255);
        }
        int r42 = ((r4 & 7) << 6) | (readUnsignedByte & 63);
        int readUnsignedByte2 = this._inputData.readUnsignedByte();
        if ((readUnsignedByte2 & 192) != 128) {
            _reportInvalidOther(readUnsignedByte2 & 255);
        }
        int r43 = (r42 << 6) | (readUnsignedByte2 & 63);
        int readUnsignedByte3 = this._inputData.readUnsignedByte();
        if ((readUnsignedByte3 & 192) != 128) {
            _reportInvalidOther(readUnsignedByte3 & 255);
        }
        return ((r43 << 6) | (readUnsignedByte3 & 63)) - 65536;
    }

    private final void _skipUtf8_2() throws IOException {
        int readUnsignedByte = this._inputData.readUnsignedByte();
        if ((readUnsignedByte & 192) != 128) {
            _reportInvalidOther(readUnsignedByte & 255);
        }
    }

    private final void _skipUtf8_3() throws IOException {
        int readUnsignedByte = this._inputData.readUnsignedByte();
        if ((readUnsignedByte & 192) != 128) {
            _reportInvalidOther(readUnsignedByte & 255);
        }
        int readUnsignedByte2 = this._inputData.readUnsignedByte();
        if ((readUnsignedByte2 & 192) != 128) {
            _reportInvalidOther(readUnsignedByte2 & 255);
        }
    }

    private final void _skipUtf8_4() throws IOException {
        int readUnsignedByte = this._inputData.readUnsignedByte();
        if ((readUnsignedByte & 192) != 128) {
            _reportInvalidOther(readUnsignedByte & 255);
        }
        int readUnsignedByte2 = this._inputData.readUnsignedByte();
        if ((readUnsignedByte2 & 192) != 128) {
            _reportInvalidOther(readUnsignedByte2 & 255);
        }
        int readUnsignedByte3 = this._inputData.readUnsignedByte();
        if ((readUnsignedByte3 & 192) != 128) {
            _reportInvalidOther(readUnsignedByte3 & 255);
        }
    }

    protected void _reportInvalidToken(int r2, String str) throws IOException {
        _reportInvalidToken(r2, str, "'null', 'true', 'false' or NaN");
    }

    protected void _reportInvalidToken(int r2, String str, String str2) throws IOException {
        StringBuilder sb = new StringBuilder(str);
        while (true) {
            char _decodeCharForError = (char) _decodeCharForError(r2);
            if (Character.isJavaIdentifierPart(_decodeCharForError)) {
                sb.append(_decodeCharForError);
                r2 = this._inputData.readUnsignedByte();
            } else {
                _reportError("Unrecognized token '" + sb.toString() + "': was expecting " + str2);
                return;
            }
        }
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

    private void _reportInvalidOther(int r3) throws JsonParseException {
        _reportError("Invalid UTF-8 middle byte 0x" + Integer.toHexString(r3));
    }

    private static int[] _growArrayBy(int[] r1, int r2) {
        if (r1 == null) {
            return new int[r2];
        }
        return Arrays.copyOf(r1, r1.length + r2);
    }

    protected final byte[] _decodeBase64(Base64Variant base64Variant) throws IOException {
        ByteArrayBuilder _getByteArrayBuilder = _getByteArrayBuilder();
        while (true) {
            int readUnsignedByte = this._inputData.readUnsignedByte();
            if (readUnsignedByte > 32) {
                int decodeBase64Char = base64Variant.decodeBase64Char(readUnsignedByte);
                if (decodeBase64Char < 0) {
                    if (readUnsignedByte == 34) {
                        return _getByteArrayBuilder.toByteArray();
                    }
                    decodeBase64Char = _decodeBase64Escape(base64Variant, readUnsignedByte, 0);
                    if (decodeBase64Char < 0) {
                        continue;
                    }
                }
                int readUnsignedByte2 = this._inputData.readUnsignedByte();
                int decodeBase64Char2 = base64Variant.decodeBase64Char(readUnsignedByte2);
                if (decodeBase64Char2 < 0) {
                    decodeBase64Char2 = _decodeBase64Escape(base64Variant, readUnsignedByte2, 1);
                }
                int r1 = (decodeBase64Char << 6) | decodeBase64Char2;
                int readUnsignedByte3 = this._inputData.readUnsignedByte();
                int decodeBase64Char3 = base64Variant.decodeBase64Char(readUnsignedByte3);
                if (decodeBase64Char3 < 0) {
                    if (decodeBase64Char3 != -2) {
                        if (readUnsignedByte3 == 34 && !base64Variant.usesPadding()) {
                            _getByteArrayBuilder.append(r1 >> 4);
                            return _getByteArrayBuilder.toByteArray();
                        }
                        decodeBase64Char3 = _decodeBase64Escape(base64Variant, readUnsignedByte3, 2);
                    }
                    if (decodeBase64Char3 == -2) {
                        int readUnsignedByte4 = this._inputData.readUnsignedByte();
                        if (!base64Variant.usesPaddingChar(readUnsignedByte4)) {
                            throw reportInvalidBase64Char(base64Variant, readUnsignedByte4, 3, "expected padding character '" + base64Variant.getPaddingChar() + "'");
                        }
                        _getByteArrayBuilder.append(r1 >> 4);
                    }
                }
                int r12 = (r1 << 6) | decodeBase64Char3;
                int readUnsignedByte5 = this._inputData.readUnsignedByte();
                int decodeBase64Char4 = base64Variant.decodeBase64Char(readUnsignedByte5);
                if (decodeBase64Char4 < 0) {
                    if (decodeBase64Char4 != -2) {
                        if (readUnsignedByte5 == 34 && !base64Variant.usesPadding()) {
                            _getByteArrayBuilder.appendTwoBytes(r12 >> 2);
                            return _getByteArrayBuilder.toByteArray();
                        }
                        decodeBase64Char4 = _decodeBase64Escape(base64Variant, readUnsignedByte5, 3);
                    }
                    if (decodeBase64Char4 == -2) {
                        _getByteArrayBuilder.appendTwoBytes(r12 >> 2);
                    }
                }
                _getByteArrayBuilder.appendThreeBytes((r12 << 6) | decodeBase64Char4);
            }
        }
    }

    @Override // com.fasterxml.jackson.core.base.ParserBase, com.fasterxml.jackson.core.JsonParser
    public JsonLocation getTokenLocation() {
        return new JsonLocation(this._ioContext.getSourceReference(), -1L, -1L, this._tokenInputRow, -1);
    }

    @Override // com.fasterxml.jackson.core.base.ParserBase, com.fasterxml.jackson.core.JsonParser
    public JsonLocation getCurrentLocation() {
        return new JsonLocation(this._ioContext.getSourceReference(), -1L, -1L, this._currInputRow, -1);
    }
}
