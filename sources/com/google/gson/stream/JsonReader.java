package com.google.gson.stream;

import com.facebook.hermes.intl.Constants;
import com.google.gson.internal.JsonReaderInternalAccess;
import com.google.gson.internal.bind.JsonTreeReader;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.util.Objects;
import kotlin.text.Typography;
import org.apache.commons.lang3.CharUtils;

/* loaded from: classes3.dex */
public class JsonReader implements Closeable {
    private static final long MIN_INCOMPLETE_INTEGER = -922337203685477580L;
    private static final char[] NON_EXECUTE_PREFIX = ")]}'\n".toCharArray();
    private static final int NUMBER_CHAR_DECIMAL = 3;
    private static final int NUMBER_CHAR_DIGIT = 2;
    private static final int NUMBER_CHAR_EXP_DIGIT = 7;
    private static final int NUMBER_CHAR_EXP_E = 5;
    private static final int NUMBER_CHAR_EXP_SIGN = 6;
    private static final int NUMBER_CHAR_FRACTION_DIGIT = 4;
    private static final int NUMBER_CHAR_NONE = 0;
    private static final int NUMBER_CHAR_SIGN = 1;
    private static final int PEEKED_BEGIN_ARRAY = 3;
    private static final int PEEKED_BEGIN_OBJECT = 1;
    private static final int PEEKED_BUFFERED = 11;
    private static final int PEEKED_DOUBLE_QUOTED = 9;
    private static final int PEEKED_DOUBLE_QUOTED_NAME = 13;
    private static final int PEEKED_END_ARRAY = 4;
    private static final int PEEKED_END_OBJECT = 2;
    private static final int PEEKED_EOF = 17;
    private static final int PEEKED_FALSE = 6;
    private static final int PEEKED_LONG = 15;
    private static final int PEEKED_NONE = 0;
    private static final int PEEKED_NULL = 7;
    private static final int PEEKED_NUMBER = 16;
    private static final int PEEKED_SINGLE_QUOTED = 8;
    private static final int PEEKED_SINGLE_QUOTED_NAME = 12;
    private static final int PEEKED_TRUE = 5;
    private static final int PEEKED_UNQUOTED = 10;
    private static final int PEEKED_UNQUOTED_NAME = 14;

    /* renamed from: in */
    private final Reader f1210in;
    private int[] pathIndices;
    private String[] pathNames;
    private long peekedLong;
    private int peekedNumberLength;
    private String peekedString;
    private int[] stack;
    private boolean lenient = false;
    private final char[] buffer = new char[1024];
    private int pos = 0;
    private int limit = 0;
    private int lineNumber = 0;
    private int lineStart = 0;
    private int peeked = 0;
    private int stackSize = 0 + 1;

    static {
        JsonReaderInternalAccess.INSTANCE = new JsonReaderInternalAccess() { // from class: com.google.gson.stream.JsonReader.1
            @Override // com.google.gson.internal.JsonReaderInternalAccess
            public void promoteNameToValue(JsonReader jsonReader) throws IOException {
                if (!(jsonReader instanceof JsonTreeReader)) {
                    int r0 = jsonReader.peeked;
                    if (r0 == 0) {
                        r0 = jsonReader.doPeek();
                    }
                    if (r0 == 13) {
                        jsonReader.peeked = 9;
                        return;
                    } else if (r0 == 12) {
                        jsonReader.peeked = 8;
                        return;
                    } else if (r0 == 14) {
                        jsonReader.peeked = 10;
                        return;
                    } else {
                        throw new IllegalStateException("Expected a name but was " + jsonReader.peek() + "  at line " + jsonReader.getLineNumber() + " column " + jsonReader.getColumnNumber() + " path " + jsonReader.getPath());
                    }
                }
                ((JsonTreeReader) jsonReader).promoteNameToValue();
            }
        };
    }

    public JsonReader(Reader reader) {
        int[] r2 = new int[32];
        this.stack = r2;
        r2[0] = 6;
        this.pathNames = new String[32];
        this.pathIndices = new int[32];
        Objects.requireNonNull(reader, "in == null");
        this.f1210in = reader;
    }

    public final void setLenient(boolean z) {
        this.lenient = z;
    }

    public final boolean isLenient() {
        return this.lenient;
    }

    public void beginArray() throws IOException {
        int r0 = this.peeked;
        if (r0 == 0) {
            r0 = doPeek();
        }
        if (r0 == 3) {
            push(1);
            this.pathIndices[this.stackSize - 1] = 0;
            this.peeked = 0;
            return;
        }
        throw new IllegalStateException("Expected BEGIN_ARRAY but was " + peek() + " at line " + getLineNumber() + " column " + getColumnNumber() + " path " + getPath());
    }

    public void endArray() throws IOException {
        int r0 = this.peeked;
        if (r0 == 0) {
            r0 = doPeek();
        }
        if (r0 == 4) {
            int r02 = this.stackSize - 1;
            this.stackSize = r02;
            int[] r1 = this.pathIndices;
            int r03 = r02 - 1;
            r1[r03] = r1[r03] + 1;
            this.peeked = 0;
            return;
        }
        throw new IllegalStateException("Expected END_ARRAY but was " + peek() + " at line " + getLineNumber() + " column " + getColumnNumber() + " path " + getPath());
    }

    public void beginObject() throws IOException {
        int r0 = this.peeked;
        if (r0 == 0) {
            r0 = doPeek();
        }
        if (r0 == 1) {
            push(3);
            this.peeked = 0;
            return;
        }
        throw new IllegalStateException("Expected BEGIN_OBJECT but was " + peek() + " at line " + getLineNumber() + " column " + getColumnNumber() + " path " + getPath());
    }

    public void endObject() throws IOException {
        int r0 = this.peeked;
        if (r0 == 0) {
            r0 = doPeek();
        }
        if (r0 == 2) {
            int r02 = this.stackSize - 1;
            this.stackSize = r02;
            this.pathNames[r02] = null;
            int[] r1 = this.pathIndices;
            int r03 = r02 - 1;
            r1[r03] = r1[r03] + 1;
            this.peeked = 0;
            return;
        }
        throw new IllegalStateException("Expected END_OBJECT but was " + peek() + " at line " + getLineNumber() + " column " + getColumnNumber() + " path " + getPath());
    }

    public boolean hasNext() throws IOException {
        int r0 = this.peeked;
        if (r0 == 0) {
            r0 = doPeek();
        }
        return (r0 == 2 || r0 == 4) ? false : true;
    }

    public JsonToken peek() throws IOException {
        int r0 = this.peeked;
        if (r0 == 0) {
            r0 = doPeek();
        }
        switch (r0) {
            case 1:
                return JsonToken.BEGIN_OBJECT;
            case 2:
                return JsonToken.END_OBJECT;
            case 3:
                return JsonToken.BEGIN_ARRAY;
            case 4:
                return JsonToken.END_ARRAY;
            case 5:
            case 6:
                return JsonToken.BOOLEAN;
            case 7:
                return JsonToken.NULL;
            case 8:
            case 9:
            case 10:
            case 11:
                return JsonToken.STRING;
            case 12:
            case 13:
            case 14:
                return JsonToken.NAME;
            case 15:
            case 16:
                return JsonToken.NUMBER;
            case 17:
                return JsonToken.END_DOCUMENT;
            default:
                throw new AssertionError();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int doPeek() throws IOException {
        int nextNonWhitespace;
        int[] r0 = this.stack;
        int r1 = this.stackSize;
        int r2 = r0[r1 - 1];
        if (r2 == 1) {
            r0[r1 - 1] = 2;
        } else if (r2 == 2) {
            int nextNonWhitespace2 = nextNonWhitespace(true);
            if (nextNonWhitespace2 != 44) {
                if (nextNonWhitespace2 != 59) {
                    if (nextNonWhitespace2 == 93) {
                        this.peeked = 4;
                        return 4;
                    }
                    throw syntaxError("Unterminated array");
                }
                checkLenient();
            }
        } else if (r2 == 3 || r2 == 5) {
            r0[r1 - 1] = 4;
            if (r2 == 5 && (nextNonWhitespace = nextNonWhitespace(true)) != 44) {
                if (nextNonWhitespace != 59) {
                    if (nextNonWhitespace == 125) {
                        this.peeked = 2;
                        return 2;
                    }
                    throw syntaxError("Unterminated object");
                }
                checkLenient();
            }
            int nextNonWhitespace3 = nextNonWhitespace(true);
            if (nextNonWhitespace3 == 34) {
                this.peeked = 13;
                return 13;
            } else if (nextNonWhitespace3 == 39) {
                checkLenient();
                this.peeked = 12;
                return 12;
            } else if (nextNonWhitespace3 == 125) {
                if (r2 != 5) {
                    this.peeked = 2;
                    return 2;
                }
                throw syntaxError("Expected name");
            } else {
                checkLenient();
                this.pos--;
                if (isLiteral((char) nextNonWhitespace3)) {
                    this.peeked = 14;
                    return 14;
                }
                throw syntaxError("Expected name");
            }
        } else if (r2 == 4) {
            r0[r1 - 1] = 5;
            int nextNonWhitespace4 = nextNonWhitespace(true);
            if (nextNonWhitespace4 != 58) {
                if (nextNonWhitespace4 == 61) {
                    checkLenient();
                    if (this.pos < this.limit || fillBuffer(1)) {
                        char[] cArr = this.buffer;
                        int r12 = this.pos;
                        if (cArr[r12] == '>') {
                            this.pos = r12 + 1;
                        }
                    }
                } else {
                    throw syntaxError("Expected ':'");
                }
            }
        } else if (r2 == 6) {
            if (this.lenient) {
                consumeNonExecutePrefix();
            }
            this.stack[this.stackSize - 1] = 7;
        } else if (r2 == 7) {
            if (nextNonWhitespace(false) == -1) {
                this.peeked = 17;
                return 17;
            }
            checkLenient();
            this.pos--;
        } else if (r2 == 8) {
            throw new IllegalStateException("JsonReader is closed");
        }
        int nextNonWhitespace5 = nextNonWhitespace(true);
        if (nextNonWhitespace5 == 34) {
            if (this.stackSize == 1) {
                checkLenient();
            }
            this.peeked = 9;
            return 9;
        } else if (nextNonWhitespace5 == 39) {
            checkLenient();
            this.peeked = 8;
            return 8;
        } else {
            if (nextNonWhitespace5 != 44 && nextNonWhitespace5 != 59) {
                if (nextNonWhitespace5 == 91) {
                    this.peeked = 3;
                    return 3;
                } else if (nextNonWhitespace5 != 93) {
                    if (nextNonWhitespace5 == 123) {
                        this.peeked = 1;
                        return 1;
                    }
                    this.pos--;
                    if (this.stackSize == 1) {
                        checkLenient();
                    }
                    int peekKeyword = peekKeyword();
                    if (peekKeyword != 0) {
                        return peekKeyword;
                    }
                    int peekNumber = peekNumber();
                    if (peekNumber != 0) {
                        return peekNumber;
                    }
                    if (!isLiteral(this.buffer[this.pos])) {
                        throw syntaxError("Expected value");
                    }
                    checkLenient();
                    this.peeked = 10;
                    return 10;
                } else if (r2 == 1) {
                    this.peeked = 4;
                    return 4;
                }
            }
            if (r2 == 1 || r2 == 2) {
                checkLenient();
                this.pos--;
                this.peeked = 7;
                return 7;
            }
            throw syntaxError("Unexpected value");
        }
    }

    private int peekKeyword() throws IOException {
        int r0;
        String str;
        String str2;
        char c = this.buffer[this.pos];
        if (c == 't' || c == 'T') {
            r0 = 5;
            str = "true";
            str2 = "TRUE";
        } else if (c == 'f' || c == 'F') {
            r0 = 6;
            str = Constants.CASEFIRST_FALSE;
            str2 = "FALSE";
        } else if (c != 'n' && c != 'N') {
            return 0;
        } else {
            r0 = 7;
            str = "null";
            str2 = "NULL";
        }
        int length = str.length();
        for (int r5 = 1; r5 < length; r5++) {
            if (this.pos + r5 >= this.limit && !fillBuffer(r5 + 1)) {
                return 0;
            }
            char c2 = this.buffer[this.pos + r5];
            if (c2 != str.charAt(r5) && c2 != str2.charAt(r5)) {
                return 0;
            }
        }
        if ((this.pos + length < this.limit || fillBuffer(length + 1)) && isLiteral(this.buffer[this.pos + length])) {
            return 0;
        }
        this.pos += length;
        this.peeked = r0;
        return r0;
    }

    /* JADX WARN: Code restructure failed: missing block: B:55:0x0099, code lost:
        if (isLiteral(r14) != false) goto L76;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x009b, code lost:
        if (r9 != 2) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x009d, code lost:
        if (r10 == false) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x00a3, code lost:
        if (r11 != Long.MIN_VALUE) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x00a5, code lost:
        if (r13 == false) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x00a7, code lost:
        if (r13 == false) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x00aa, code lost:
        r11 = -r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x00ab, code lost:
        r18.peekedLong = r11;
        r18.pos += r8;
        r18.peeked = 15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x00b6, code lost:
        return 15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x00b7, code lost:
        if (r9 == 2) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x00ba, code lost:
        if (r9 == 4) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x00bd, code lost:
        if (r9 != 7) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x00c0, code lost:
        return 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x00c2, code lost:
        r18.peekedNumberLength = r8;
        r18.peeked = 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x00c8, code lost:
        return 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x00c9, code lost:
        return 0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int peekNumber() throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 248
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.stream.JsonReader.peekNumber():int");
    }

    private boolean isLiteral(char c) throws IOException {
        if (c == '\t' || c == '\n' || c == '\f' || c == '\r' || c == ' ') {
            return false;
        }
        if (c != '#') {
            if (c == ',') {
                return false;
            }
            if (c != '/' && c != '=') {
                if (c == '{' || c == '}' || c == ':') {
                    return false;
                }
                if (c != ';') {
                    switch (c) {
                        case '[':
                        case ']':
                            return false;
                        case '\\':
                            break;
                        default:
                            return true;
                    }
                }
            }
        }
        checkLenient();
        return false;
    }

    public String nextName() throws IOException {
        String nextQuotedValue;
        int r0 = this.peeked;
        if (r0 == 0) {
            r0 = doPeek();
        }
        if (r0 == 14) {
            nextQuotedValue = nextUnquotedValue();
        } else if (r0 == 12) {
            nextQuotedValue = nextQuotedValue('\'');
        } else if (r0 == 13) {
            nextQuotedValue = nextQuotedValue(Typography.quote);
        } else {
            throw new IllegalStateException("Expected a name but was " + peek() + " at line " + getLineNumber() + " column " + getColumnNumber() + " path " + getPath());
        }
        this.peeked = 0;
        this.pathNames[this.stackSize - 1] = nextQuotedValue;
        return nextQuotedValue;
    }

    public String nextString() throws IOException {
        String str;
        int r0 = this.peeked;
        if (r0 == 0) {
            r0 = doPeek();
        }
        if (r0 == 10) {
            str = nextUnquotedValue();
        } else if (r0 == 8) {
            str = nextQuotedValue('\'');
        } else if (r0 == 9) {
            str = nextQuotedValue(Typography.quote);
        } else if (r0 == 11) {
            str = this.peekedString;
            this.peekedString = null;
        } else if (r0 == 15) {
            str = Long.toString(this.peekedLong);
        } else if (r0 == 16) {
            str = new String(this.buffer, this.pos, this.peekedNumberLength);
            this.pos += this.peekedNumberLength;
        } else {
            throw new IllegalStateException("Expected a string but was " + peek() + " at line " + getLineNumber() + " column " + getColumnNumber() + " path " + getPath());
        }
        this.peeked = 0;
        int[] r1 = this.pathIndices;
        int r2 = this.stackSize - 1;
        r1[r2] = r1[r2] + 1;
        return str;
    }

    public boolean nextBoolean() throws IOException {
        int r0 = this.peeked;
        if (r0 == 0) {
            r0 = doPeek();
        }
        if (r0 == 5) {
            this.peeked = 0;
            int[] r02 = this.pathIndices;
            int r1 = this.stackSize - 1;
            r02[r1] = r02[r1] + 1;
            return true;
        } else if (r0 == 6) {
            this.peeked = 0;
            int[] r03 = this.pathIndices;
            int r12 = this.stackSize - 1;
            r03[r12] = r03[r12] + 1;
            return false;
        } else {
            throw new IllegalStateException("Expected a boolean but was " + peek() + " at line " + getLineNumber() + " column " + getColumnNumber() + " path " + getPath());
        }
    }

    public void nextNull() throws IOException {
        int r0 = this.peeked;
        if (r0 == 0) {
            r0 = doPeek();
        }
        if (r0 == 7) {
            this.peeked = 0;
            int[] r02 = this.pathIndices;
            int r1 = this.stackSize - 1;
            r02[r1] = r02[r1] + 1;
            return;
        }
        throw new IllegalStateException("Expected null but was " + peek() + " at line " + getLineNumber() + " column " + getColumnNumber() + " path " + getPath());
    }

    public double nextDouble() throws IOException {
        int r0 = this.peeked;
        if (r0 == 0) {
            r0 = doPeek();
        }
        if (r0 == 15) {
            this.peeked = 0;
            int[] r02 = this.pathIndices;
            int r1 = this.stackSize - 1;
            r02[r1] = r02[r1] + 1;
            return this.peekedLong;
        }
        if (r0 == 16) {
            this.peekedString = new String(this.buffer, this.pos, this.peekedNumberLength);
            this.pos += this.peekedNumberLength;
        } else if (r0 == 8 || r0 == 9) {
            this.peekedString = nextQuotedValue(r0 == 8 ? '\'' : Typography.quote);
        } else if (r0 == 10) {
            this.peekedString = nextUnquotedValue();
        } else if (r0 != 11) {
            throw new IllegalStateException("Expected a double but was " + peek() + " at line " + getLineNumber() + " column " + getColumnNumber() + " path " + getPath());
        }
        this.peeked = 11;
        double parseDouble = Double.parseDouble(this.peekedString);
        if (!this.lenient && (Double.isNaN(parseDouble) || Double.isInfinite(parseDouble))) {
            throw new MalformedJsonException("JSON forbids NaN and infinities: " + parseDouble + " at line " + getLineNumber() + " column " + getColumnNumber() + " path " + getPath());
        }
        this.peekedString = null;
        this.peeked = 0;
        int[] r2 = this.pathIndices;
        int r3 = this.stackSize - 1;
        r2[r3] = r2[r3] + 1;
        return parseDouble;
    }

    public long nextLong() throws IOException {
        int r0 = this.peeked;
        if (r0 == 0) {
            r0 = doPeek();
        }
        if (r0 == 15) {
            this.peeked = 0;
            int[] r02 = this.pathIndices;
            int r1 = this.stackSize - 1;
            r02[r1] = r02[r1] + 1;
            return this.peekedLong;
        }
        if (r0 == 16) {
            this.peekedString = new String(this.buffer, this.pos, this.peekedNumberLength);
            this.pos += this.peekedNumberLength;
        } else if (r0 == 8 || r0 == 9) {
            String nextQuotedValue = nextQuotedValue(r0 == 8 ? '\'' : Typography.quote);
            this.peekedString = nextQuotedValue;
            try {
                long parseLong = Long.parseLong(nextQuotedValue);
                this.peeked = 0;
                int[] r7 = this.pathIndices;
                int r8 = this.stackSize - 1;
                r7[r8] = r7[r8] + 1;
                return parseLong;
            } catch (NumberFormatException unused) {
            }
        } else {
            throw new IllegalStateException("Expected a long but was " + peek() + " at line " + getLineNumber() + " column " + getColumnNumber() + " path " + getPath());
        }
        this.peeked = 11;
        double parseDouble = Double.parseDouble(this.peekedString);
        long j = (long) parseDouble;
        if (j != parseDouble) {
            throw new NumberFormatException("Expected a long but was " + this.peekedString + " at line " + getLineNumber() + " column " + getColumnNumber() + " path " + getPath());
        }
        this.peekedString = null;
        this.peeked = 0;
        int[] r03 = this.pathIndices;
        int r12 = this.stackSize - 1;
        r03[r12] = r03[r12] + 1;
        return j;
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0046, code lost:
        r1.append(r0, r3, r2 - r3);
        r8.pos = r2;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String nextQuotedValue(char r9) throws java.io.IOException {
        /*
            r8 = this;
            char[] r0 = r8.buffer
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
        L7:
            int r2 = r8.pos
            int r3 = r8.limit
        Lb:
            r4 = r3
            r3 = r2
        Ld:
            r5 = 1
            if (r2 >= r4) goto L46
            int r6 = r2 + 1
            char r2 = r0[r2]
            if (r2 != r9) goto L22
            r8.pos = r6
            int r6 = r6 - r3
            int r6 = r6 - r5
            r1.append(r0, r3, r6)
            java.lang.String r9 = r1.toString()
            return r9
        L22:
            r7 = 92
            if (r2 != r7) goto L39
            r8.pos = r6
            int r6 = r6 - r3
            int r6 = r6 - r5
            r1.append(r0, r3, r6)
            char r2 = r8.readEscapeCharacter()
            r1.append(r2)
            int r2 = r8.pos
            int r3 = r8.limit
            goto Lb
        L39:
            r7 = 10
            if (r2 != r7) goto L44
            int r2 = r8.lineNumber
            int r2 = r2 + r5
            r8.lineNumber = r2
            r8.lineStart = r6
        L44:
            r2 = r6
            goto Ld
        L46:
            int r4 = r2 - r3
            r1.append(r0, r3, r4)
            r8.pos = r2
            boolean r2 = r8.fillBuffer(r5)
            if (r2 == 0) goto L54
            goto L7
        L54:
            java.lang.String r9 = "Unterminated string"
            java.io.IOException r9 = r8.syntaxError(r9)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.stream.JsonReader.nextQuotedValue(char):java.lang.String");
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x004a, code lost:
        checkLenient();
     */
    /* JADX WARN: Removed duplicated region for block: B:46:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0084  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String nextUnquotedValue() throws java.io.IOException {
        /*
            r6 = this;
            r0 = 0
            r1 = 0
        L2:
            r2 = 0
        L3:
            int r3 = r6.pos
            int r4 = r3 + r2
            int r5 = r6.limit
            if (r4 >= r5) goto L4e
            char[] r4 = r6.buffer
            int r3 = r3 + r2
            char r3 = r4[r3]
            r4 = 9
            if (r3 == r4) goto L5c
            r4 = 10
            if (r3 == r4) goto L5c
            r4 = 12
            if (r3 == r4) goto L5c
            r4 = 13
            if (r3 == r4) goto L5c
            r4 = 32
            if (r3 == r4) goto L5c
            r4 = 35
            if (r3 == r4) goto L4a
            r4 = 44
            if (r3 == r4) goto L5c
            r4 = 47
            if (r3 == r4) goto L4a
            r4 = 61
            if (r3 == r4) goto L4a
            r4 = 123(0x7b, float:1.72E-43)
            if (r3 == r4) goto L5c
            r4 = 125(0x7d, float:1.75E-43)
            if (r3 == r4) goto L5c
            r4 = 58
            if (r3 == r4) goto L5c
            r4 = 59
            if (r3 == r4) goto L4a
            switch(r3) {
                case 91: goto L5c;
                case 92: goto L4a;
                case 93: goto L5c;
                default: goto L47;
            }
        L47:
            int r2 = r2 + 1
            goto L3
        L4a:
            r6.checkLenient()
            goto L5c
        L4e:
            char[] r3 = r6.buffer
            int r3 = r3.length
            if (r2 >= r3) goto L5e
            int r3 = r2 + 1
            boolean r3 = r6.fillBuffer(r3)
            if (r3 == 0) goto L5c
            goto L3
        L5c:
            r0 = r2
            goto L78
        L5e:
            if (r1 != 0) goto L65
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
        L65:
            char[] r3 = r6.buffer
            int r4 = r6.pos
            r1.append(r3, r4, r2)
            int r3 = r6.pos
            int r3 = r3 + r2
            r6.pos = r3
            r2 = 1
            boolean r2 = r6.fillBuffer(r2)
            if (r2 != 0) goto L2
        L78:
            if (r1 != 0) goto L84
            java.lang.String r1 = new java.lang.String
            char[] r2 = r6.buffer
            int r3 = r6.pos
            r1.<init>(r2, r3, r0)
            goto L8f
        L84:
            char[] r2 = r6.buffer
            int r3 = r6.pos
            r1.append(r2, r3, r0)
            java.lang.String r1 = r1.toString()
        L8f:
            int r2 = r6.pos
            int r2 = r2 + r0
            r6.pos = r2
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.stream.JsonReader.nextUnquotedValue():java.lang.String");
    }

    private void skipQuotedValue(char c) throws IOException {
        char[] cArr = this.buffer;
        do {
            int r1 = this.pos;
            int r2 = this.limit;
            while (r1 < r2) {
                int r4 = r1 + 1;
                char c2 = cArr[r1];
                if (c2 == c) {
                    this.pos = r4;
                    return;
                } else if (c2 == '\\') {
                    this.pos = r4;
                    readEscapeCharacter();
                    r1 = this.pos;
                    r2 = this.limit;
                } else {
                    if (c2 == '\n') {
                        this.lineNumber++;
                        this.lineStart = r4;
                    }
                    r1 = r4;
                }
            }
            this.pos = r1;
        } while (fillBuffer(1));
        throw syntaxError("Unterminated string");
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x0048, code lost:
        checkLenient();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void skipUnquotedValue() throws java.io.IOException {
        /*
            r4 = this;
        L0:
            r0 = 0
        L1:
            int r1 = r4.pos
            int r2 = r1 + r0
            int r3 = r4.limit
            if (r2 >= r3) goto L51
            char[] r2 = r4.buffer
            int r1 = r1 + r0
            char r1 = r2[r1]
            r2 = 9
            if (r1 == r2) goto L4b
            r2 = 10
            if (r1 == r2) goto L4b
            r2 = 12
            if (r1 == r2) goto L4b
            r2 = 13
            if (r1 == r2) goto L4b
            r2 = 32
            if (r1 == r2) goto L4b
            r2 = 35
            if (r1 == r2) goto L48
            r2 = 44
            if (r1 == r2) goto L4b
            r2 = 47
            if (r1 == r2) goto L48
            r2 = 61
            if (r1 == r2) goto L48
            r2 = 123(0x7b, float:1.72E-43)
            if (r1 == r2) goto L4b
            r2 = 125(0x7d, float:1.75E-43)
            if (r1 == r2) goto L4b
            r2 = 58
            if (r1 == r2) goto L4b
            r2 = 59
            if (r1 == r2) goto L48
            switch(r1) {
                case 91: goto L4b;
                case 92: goto L48;
                case 93: goto L4b;
                default: goto L45;
            }
        L45:
            int r0 = r0 + 1
            goto L1
        L48:
            r4.checkLenient()
        L4b:
            int r1 = r4.pos
            int r1 = r1 + r0
            r4.pos = r1
            return
        L51:
            int r1 = r1 + r0
            r4.pos = r1
            r0 = 1
            boolean r0 = r4.fillBuffer(r0)
            if (r0 != 0) goto L0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.stream.JsonReader.skipUnquotedValue():void");
    }

    public int nextInt() throws IOException {
        int r0 = this.peeked;
        if (r0 == 0) {
            r0 = doPeek();
        }
        if (r0 == 15) {
            long j = this.peekedLong;
            int r7 = (int) j;
            if (j != r7) {
                throw new NumberFormatException("Expected an int but was " + this.peekedLong + " at line " + getLineNumber() + " column " + getColumnNumber() + " path " + getPath());
            }
            this.peeked = 0;
            int[] r02 = this.pathIndices;
            int r1 = this.stackSize - 1;
            r02[r1] = r02[r1] + 1;
            return r7;
        }
        if (r0 == 16) {
            this.peekedString = new String(this.buffer, this.pos, this.peekedNumberLength);
            this.pos += this.peekedNumberLength;
        } else if (r0 == 8 || r0 == 9) {
            String nextQuotedValue = nextQuotedValue(r0 == 8 ? '\'' : Typography.quote);
            this.peekedString = nextQuotedValue;
            try {
                int parseInt = Integer.parseInt(nextQuotedValue);
                this.peeked = 0;
                int[] r12 = this.pathIndices;
                int r72 = this.stackSize - 1;
                r12[r72] = r12[r72] + 1;
                return parseInt;
            } catch (NumberFormatException unused) {
            }
        } else {
            throw new IllegalStateException("Expected an int but was " + peek() + " at line " + getLineNumber() + " column " + getColumnNumber() + " path " + getPath());
        }
        this.peeked = 11;
        double parseDouble = Double.parseDouble(this.peekedString);
        int r73 = (int) parseDouble;
        if (r73 != parseDouble) {
            throw new NumberFormatException("Expected an int but was " + this.peekedString + " at line " + getLineNumber() + " column " + getColumnNumber() + " path " + getPath());
        }
        this.peekedString = null;
        this.peeked = 0;
        int[] r03 = this.pathIndices;
        int r13 = this.stackSize - 1;
        r03[r13] = r03[r13] + 1;
        return r73;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.peeked = 0;
        this.stack[0] = 8;
        this.stackSize = 1;
        this.f1210in.close();
    }

    public void skipValue() throws IOException {
        int r1 = 0;
        do {
            int r2 = this.peeked;
            if (r2 == 0) {
                r2 = doPeek();
            }
            if (r2 == 3) {
                push(1);
            } else if (r2 == 1) {
                push(3);
            } else {
                if (r2 == 4) {
                    this.stackSize--;
                } else if (r2 == 2) {
                    this.stackSize--;
                } else {
                    if (r2 == 14 || r2 == 10) {
                        skipUnquotedValue();
                    } else if (r2 == 8 || r2 == 12) {
                        skipQuotedValue('\'');
                    } else if (r2 == 9 || r2 == 13) {
                        skipQuotedValue(Typography.quote);
                    } else if (r2 == 16) {
                        this.pos += this.peekedNumberLength;
                    }
                    this.peeked = 0;
                }
                r1--;
                this.peeked = 0;
            }
            r1++;
            this.peeked = 0;
        } while (r1 != 0);
        int[] r0 = this.pathIndices;
        int r12 = this.stackSize;
        int r22 = r12 - 1;
        r0[r22] = r0[r22] + 1;
        this.pathNames[r12 - 1] = "null";
    }

    private void push(int r7) {
        int r0 = this.stackSize;
        int[] r1 = this.stack;
        if (r0 == r1.length) {
            int[] r2 = new int[r0 * 2];
            int[] r3 = new int[r0 * 2];
            String[] strArr = new String[r0 * 2];
            System.arraycopy(r1, 0, r2, 0, r0);
            System.arraycopy(this.pathIndices, 0, r3, 0, this.stackSize);
            System.arraycopy(this.pathNames, 0, strArr, 0, this.stackSize);
            this.stack = r2;
            this.pathIndices = r3;
            this.pathNames = strArr;
        }
        int[] r02 = this.stack;
        int r12 = this.stackSize;
        this.stackSize = r12 + 1;
        r02[r12] = r7;
    }

    private boolean fillBuffer(int r8) throws IOException {
        int r2;
        int r1;
        char[] cArr = this.buffer;
        int r12 = this.lineStart;
        int r22 = this.pos;
        this.lineStart = r12 - r22;
        int r13 = this.limit;
        if (r13 != r22) {
            int r14 = r13 - r22;
            this.limit = r14;
            System.arraycopy(cArr, r22, cArr, 0, r14);
        } else {
            this.limit = 0;
        }
        this.pos = 0;
        do {
            Reader reader = this.f1210in;
            int r23 = this.limit;
            int read = reader.read(cArr, r23, cArr.length - r23);
            if (read == -1) {
                return false;
            }
            r2 = this.limit + read;
            this.limit = r2;
            if (this.lineNumber == 0 && (r1 = this.lineStart) == 0 && r2 > 0 && cArr[0] == 65279) {
                this.pos++;
                this.lineStart = r1 + 1;
                r8++;
                continue;
            }
        } while (r2 < r8);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getLineNumber() {
        return this.lineNumber + 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getColumnNumber() {
        return (this.pos - this.lineStart) + 1;
    }

    private int nextNonWhitespace(boolean z) throws IOException {
        char[] cArr = this.buffer;
        int r1 = this.pos;
        int r2 = this.limit;
        while (true) {
            if (r1 == r2) {
                this.pos = r1;
                if (!fillBuffer(1)) {
                    if (z) {
                        throw new EOFException("End of input at line " + getLineNumber() + " column " + getColumnNumber());
                    }
                    return -1;
                }
                r1 = this.pos;
                r2 = this.limit;
            }
            int r4 = r1 + 1;
            char c = cArr[r1];
            if (c == '\n') {
                this.lineNumber++;
                this.lineStart = r4;
            } else if (c != ' ' && c != '\r' && c != '\t') {
                if (c == '/') {
                    this.pos = r4;
                    if (r4 == r2) {
                        this.pos = r4 - 1;
                        boolean fillBuffer = fillBuffer(2);
                        this.pos++;
                        if (!fillBuffer) {
                            return c;
                        }
                    }
                    checkLenient();
                    int r22 = this.pos;
                    char c2 = cArr[r22];
                    if (c2 == '*') {
                        this.pos = r22 + 1;
                        if (!skipTo("*/")) {
                            throw syntaxError("Unterminated comment");
                        }
                        r1 = this.pos + 2;
                        r2 = this.limit;
                    } else if (c2 != '/') {
                        return c;
                    } else {
                        this.pos = r22 + 1;
                        skipToEndOfLine();
                        r1 = this.pos;
                        r2 = this.limit;
                    }
                } else if (c == '#') {
                    this.pos = r4;
                    checkLenient();
                    skipToEndOfLine();
                    r1 = this.pos;
                    r2 = this.limit;
                } else {
                    this.pos = r4;
                    return c;
                }
            }
            r1 = r4;
        }
    }

    private void checkLenient() throws IOException {
        if (!this.lenient) {
            throw syntaxError("Use JsonReader.setLenient(true) to accept malformed JSON");
        }
    }

    private void skipToEndOfLine() throws IOException {
        char c;
        do {
            if (this.pos >= this.limit && !fillBuffer(1)) {
                return;
            }
            char[] cArr = this.buffer;
            int r1 = this.pos;
            int r3 = r1 + 1;
            this.pos = r3;
            c = cArr[r1];
            if (c == '\n') {
                this.lineNumber++;
                this.lineStart = r3;
                return;
            }
        } while (c != '\r');
    }

    private boolean skipTo(String str) throws IOException {
        while (true) {
            if (this.pos + str.length() > this.limit && !fillBuffer(str.length())) {
                return false;
            }
            char[] cArr = this.buffer;
            int r1 = this.pos;
            if (cArr[r1] == '\n') {
                this.lineNumber++;
                this.lineStart = r1 + 1;
            } else {
                for (int r2 = 0; r2 < str.length(); r2++) {
                    if (this.buffer[this.pos + r2] != str.charAt(r2)) {
                        break;
                    }
                }
                return true;
            }
            this.pos++;
        }
    }

    public String toString() {
        return getClass().getSimpleName() + " at line " + getLineNumber() + " column " + getColumnNumber();
    }

    public String getPath() {
        StringBuilder sb = new StringBuilder();
        sb.append('$');
        int r1 = this.stackSize;
        for (int r2 = 0; r2 < r1; r2++) {
            int r3 = this.stack[r2];
            if (r3 == 1 || r3 == 2) {
                sb.append('[');
                sb.append(this.pathIndices[r2]);
                sb.append(']');
            } else if (r3 == 3 || r3 == 4 || r3 == 5) {
                sb.append('.');
                String[] strArr = this.pathNames;
                if (strArr[r2] != null) {
                    sb.append(strArr[r2]);
                }
            }
        }
        return sb.toString();
    }

    private char readEscapeCharacter() throws IOException {
        int r6;
        int r62;
        if (this.pos == this.limit && !fillBuffer(1)) {
            throw syntaxError("Unterminated escape sequence");
        }
        char[] cArr = this.buffer;
        int r1 = this.pos;
        int r4 = r1 + 1;
        this.pos = r4;
        char c = cArr[r1];
        if (c == '\n') {
            this.lineNumber++;
            this.lineStart = r4;
        } else if (c == 'b') {
            return '\b';
        } else {
            if (c == 'f') {
                return '\f';
            }
            if (c == 'n') {
                return '\n';
            }
            if (c == 'r') {
                return CharUtils.f1567CR;
            }
            if (c == 't') {
                return '\t';
            }
            if (c == 'u') {
                if (r4 + 4 > this.limit && !fillBuffer(4)) {
                    throw syntaxError("Unterminated escape sequence");
                }
                char c2 = 0;
                int r42 = this.pos;
                int r5 = r42 + 4;
                while (r42 < r5) {
                    char c3 = this.buffer[r42];
                    char c4 = (char) (c2 << 4);
                    if (c3 < '0' || c3 > '9') {
                        if (c3 >= 'a' && c3 <= 'f') {
                            r6 = c3 - 'a';
                        } else if (c3 < 'A' || c3 > 'F') {
                            throw new NumberFormatException("\\u" + new String(this.buffer, this.pos, 4));
                        } else {
                            r6 = c3 - 'A';
                        }
                        r62 = r6 + 10;
                    } else {
                        r62 = c3 - '0';
                    }
                    c2 = (char) (c4 + r62);
                    r42++;
                }
                this.pos += 4;
                return c2;
            }
        }
        return c;
    }

    private IOException syntaxError(String str) throws IOException {
        throw new MalformedJsonException(str + " at line " + getLineNumber() + " column " + getColumnNumber() + " path " + getPath());
    }

    private void consumeNonExecutePrefix() throws IOException {
        nextNonWhitespace(true);
        int r1 = this.pos - 1;
        this.pos = r1;
        char[] cArr = NON_EXECUTE_PREFIX;
        if (r1 + cArr.length > this.limit && !fillBuffer(cArr.length)) {
            return;
        }
        int r0 = 0;
        while (true) {
            char[] cArr2 = NON_EXECUTE_PREFIX;
            if (r0 < cArr2.length) {
                if (this.buffer[this.pos + r0] != cArr2[r0]) {
                    return;
                }
                r0++;
            } else {
                this.pos += cArr2.length;
                return;
            }
        }
    }
}
