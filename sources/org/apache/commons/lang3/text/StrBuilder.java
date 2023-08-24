package org.apache.commons.lang3.text;

import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
import java.nio.CharBuffer;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.builder.Builder;

@Deprecated
/* loaded from: classes5.dex */
public class StrBuilder implements CharSequence, Appendable, Serializable, Builder<String> {
    static final int CAPACITY = 32;
    private static final long serialVersionUID = 7628716375283629643L;
    protected char[] buffer;
    private String newLine;
    private String nullText;
    protected int size;

    public StrBuilder() {
        this(32);
    }

    public StrBuilder(int r1) {
        this.buffer = new char[r1 <= 0 ? 32 : r1];
    }

    public StrBuilder(String str) {
        if (str == null) {
            this.buffer = new char[32];
            return;
        }
        this.buffer = new char[str.length() + 32];
        append(str);
    }

    public String getNewLineText() {
        return this.newLine;
    }

    public StrBuilder setNewLineText(String str) {
        this.newLine = str;
        return this;
    }

    public String getNullText() {
        return this.nullText;
    }

    public StrBuilder setNullText(String str) {
        if (str != null && str.isEmpty()) {
            str = null;
        }
        this.nullText = str;
        return this;
    }

    @Override // java.lang.CharSequence
    public int length() {
        return this.size;
    }

    public StrBuilder setLength(int r4) {
        if (r4 < 0) {
            throw new StringIndexOutOfBoundsException(r4);
        }
        int r0 = this.size;
        if (r4 < r0) {
            this.size = r4;
        } else if (r4 > r0) {
            ensureCapacity(r4);
            this.size = r4;
            for (int r02 = this.size; r02 < r4; r02++) {
                this.buffer[r02] = 0;
            }
        }
        return this;
    }

    public int capacity() {
        return this.buffer.length;
    }

    public StrBuilder ensureCapacity(int r4) {
        char[] cArr = this.buffer;
        if (r4 > cArr.length) {
            char[] cArr2 = new char[r4 * 2];
            this.buffer = cArr2;
            System.arraycopy(cArr, 0, cArr2, 0, this.size);
        }
        return this;
    }

    public StrBuilder minimizeCapacity() {
        if (this.buffer.length > length()) {
            char[] cArr = this.buffer;
            char[] cArr2 = new char[length()];
            this.buffer = cArr2;
            System.arraycopy(cArr, 0, cArr2, 0, this.size);
        }
        return this;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public StrBuilder clear() {
        this.size = 0;
        return this;
    }

    @Override // java.lang.CharSequence
    public char charAt(int r2) {
        if (r2 < 0 || r2 >= length()) {
            throw new StringIndexOutOfBoundsException(r2);
        }
        return this.buffer[r2];
    }

    public StrBuilder setCharAt(int r2, char c) {
        if (r2 < 0 || r2 >= length()) {
            throw new StringIndexOutOfBoundsException(r2);
        }
        this.buffer[r2] = c;
        return this;
    }

    public StrBuilder deleteCharAt(int r3) {
        if (r3 < 0 || r3 >= this.size) {
            throw new StringIndexOutOfBoundsException(r3);
        }
        deleteImpl(r3, r3 + 1, 1);
        return this;
    }

    public char[] toCharArray() {
        int r0 = this.size;
        if (r0 == 0) {
            return ArrayUtils.EMPTY_CHAR_ARRAY;
        }
        char[] cArr = new char[r0];
        System.arraycopy(this.buffer, 0, cArr, 0, r0);
        return cArr;
    }

    public char[] toCharArray(int r4, int r5) {
        int validateRange = validateRange(r4, r5) - r4;
        if (validateRange == 0) {
            return ArrayUtils.EMPTY_CHAR_ARRAY;
        }
        char[] cArr = new char[validateRange];
        System.arraycopy(this.buffer, r4, cArr, 0, validateRange);
        return cArr;
    }

    public char[] getChars(char[] cArr) {
        int length = length();
        if (cArr == null || cArr.length < length) {
            cArr = new char[length];
        }
        System.arraycopy(this.buffer, 0, cArr, 0, length);
        return cArr;
    }

    public void getChars(int r2, int r3, char[] cArr, int r5) {
        if (r2 < 0) {
            throw new StringIndexOutOfBoundsException(r2);
        }
        if (r3 < 0 || r3 > length()) {
            throw new StringIndexOutOfBoundsException(r3);
        }
        if (r2 > r3) {
            throw new StringIndexOutOfBoundsException("end < start");
        }
        System.arraycopy(this.buffer, r2, cArr, r5, r3 - r2);
    }

    public int readFrom(Readable readable) throws IOException {
        int r0 = this.size;
        if (readable instanceof Reader) {
            Reader reader = (Reader) readable;
            ensureCapacity(r0 + 1);
            while (true) {
                char[] cArr = this.buffer;
                int r3 = this.size;
                int read = reader.read(cArr, r3, cArr.length - r3);
                if (read == -1) {
                    break;
                }
                int r32 = this.size + read;
                this.size = r32;
                ensureCapacity(r32 + 1);
            }
        } else if (readable instanceof CharBuffer) {
            CharBuffer charBuffer = (CharBuffer) readable;
            int remaining = charBuffer.remaining();
            ensureCapacity(this.size + remaining);
            charBuffer.get(this.buffer, this.size, remaining);
            this.size += remaining;
        } else {
            while (true) {
                ensureCapacity(this.size + 1);
                char[] cArr2 = this.buffer;
                int r33 = this.size;
                int read2 = readable.read(CharBuffer.wrap(cArr2, r33, cArr2.length - r33));
                if (read2 == -1) {
                    break;
                }
                this.size += read2;
            }
        }
        return this.size - r0;
    }

    public StrBuilder appendNewLine() {
        String str = this.newLine;
        if (str == null) {
            append(System.lineSeparator());
            return this;
        }
        return append(str);
    }

    public StrBuilder appendNull() {
        String str = this.nullText;
        return str == null ? this : append(str);
    }

    public StrBuilder append(Object obj) {
        if (obj == null) {
            return appendNull();
        }
        if (obj instanceof CharSequence) {
            return append((CharSequence) obj);
        }
        return append(obj.toString());
    }

    @Override // java.lang.Appendable
    public StrBuilder append(CharSequence charSequence) {
        if (charSequence == null) {
            return appendNull();
        }
        if (charSequence instanceof StrBuilder) {
            return append((StrBuilder) charSequence);
        }
        if (charSequence instanceof StringBuilder) {
            return append((StringBuilder) charSequence);
        }
        if (charSequence instanceof StringBuffer) {
            return append((StringBuffer) charSequence);
        }
        if (charSequence instanceof CharBuffer) {
            return append((CharBuffer) charSequence);
        }
        return append(charSequence.toString());
    }

    @Override // java.lang.Appendable
    public StrBuilder append(CharSequence charSequence, int r2, int r3) {
        if (charSequence == null) {
            return appendNull();
        }
        return append(charSequence.toString(), r2, r3);
    }

    public StrBuilder append(String str) {
        if (str == null) {
            return appendNull();
        }
        int length = str.length();
        if (length > 0) {
            int length2 = length();
            ensureCapacity(length2 + length);
            str.getChars(0, length, this.buffer, length2);
            this.size += length;
        }
        return this;
    }

    public StrBuilder append(String str, int r5, int r6) {
        int r0;
        if (str == null) {
            return appendNull();
        }
        if (r5 < 0 || r5 > str.length()) {
            throw new StringIndexOutOfBoundsException("startIndex must be valid");
        }
        if (r6 < 0 || (r0 = r5 + r6) > str.length()) {
            throw new StringIndexOutOfBoundsException("length must be valid");
        }
        if (r6 > 0) {
            int length = length();
            ensureCapacity(length + r6);
            str.getChars(r5, r0, this.buffer, length);
            this.size += r6;
        }
        return this;
    }

    public StrBuilder append(String str, Object... objArr) {
        return append(String.format(str, objArr));
    }

    public StrBuilder append(CharBuffer charBuffer) {
        if (charBuffer == null) {
            return appendNull();
        }
        if (charBuffer.hasArray()) {
            int remaining = charBuffer.remaining();
            int length = length();
            ensureCapacity(length + remaining);
            System.arraycopy(charBuffer.array(), charBuffer.arrayOffset() + charBuffer.position(), this.buffer, length, remaining);
            this.size += remaining;
        } else {
            append(charBuffer.toString());
        }
        return this;
    }

    public StrBuilder append(CharBuffer charBuffer, int r5, int r6) {
        if (charBuffer == null) {
            return appendNull();
        }
        if (charBuffer.hasArray()) {
            int remaining = charBuffer.remaining();
            if (r5 < 0 || r5 > remaining) {
                throw new StringIndexOutOfBoundsException("startIndex must be valid");
            }
            if (r6 < 0 || r5 + r6 > remaining) {
                throw new StringIndexOutOfBoundsException("length must be valid");
            }
            int length = length();
            ensureCapacity(length + r6);
            System.arraycopy(charBuffer.array(), charBuffer.arrayOffset() + charBuffer.position() + r5, this.buffer, length, r6);
            this.size += r6;
        } else {
            append(charBuffer.toString(), r5, r6);
        }
        return this;
    }

    public StrBuilder append(StringBuffer stringBuffer) {
        if (stringBuffer == null) {
            return appendNull();
        }
        int length = stringBuffer.length();
        if (length > 0) {
            int length2 = length();
            ensureCapacity(length2 + length);
            stringBuffer.getChars(0, length, this.buffer, length2);
            this.size += length;
        }
        return this;
    }

    public StrBuilder append(StringBuffer stringBuffer, int r5, int r6) {
        int r0;
        if (stringBuffer == null) {
            return appendNull();
        }
        if (r5 < 0 || r5 > stringBuffer.length()) {
            throw new StringIndexOutOfBoundsException("startIndex must be valid");
        }
        if (r6 < 0 || (r0 = r5 + r6) > stringBuffer.length()) {
            throw new StringIndexOutOfBoundsException("length must be valid");
        }
        if (r6 > 0) {
            int length = length();
            ensureCapacity(length + r6);
            stringBuffer.getChars(r5, r0, this.buffer, length);
            this.size += r6;
        }
        return this;
    }

    public StrBuilder append(StringBuilder sb) {
        if (sb == null) {
            return appendNull();
        }
        int length = sb.length();
        if (length > 0) {
            int length2 = length();
            ensureCapacity(length2 + length);
            sb.getChars(0, length, this.buffer, length2);
            this.size += length;
        }
        return this;
    }

    public StrBuilder append(StringBuilder sb, int r5, int r6) {
        int r0;
        if (sb == null) {
            return appendNull();
        }
        if (r5 < 0 || r5 > sb.length()) {
            throw new StringIndexOutOfBoundsException("startIndex must be valid");
        }
        if (r6 < 0 || (r0 = r5 + r6) > sb.length()) {
            throw new StringIndexOutOfBoundsException("length must be valid");
        }
        if (r6 > 0) {
            int length = length();
            ensureCapacity(length + r6);
            sb.getChars(r5, r0, this.buffer, length);
            this.size += r6;
        }
        return this;
    }

    public StrBuilder append(StrBuilder strBuilder) {
        if (strBuilder == null) {
            return appendNull();
        }
        int length = strBuilder.length();
        if (length > 0) {
            int length2 = length();
            ensureCapacity(length2 + length);
            System.arraycopy(strBuilder.buffer, 0, this.buffer, length2, length);
            this.size += length;
        }
        return this;
    }

    public StrBuilder append(StrBuilder strBuilder, int r5, int r6) {
        int r0;
        if (strBuilder == null) {
            return appendNull();
        }
        if (r5 < 0 || r5 > strBuilder.length()) {
            throw new StringIndexOutOfBoundsException("startIndex must be valid");
        }
        if (r6 < 0 || (r0 = r5 + r6) > strBuilder.length()) {
            throw new StringIndexOutOfBoundsException("length must be valid");
        }
        if (r6 > 0) {
            int length = length();
            ensureCapacity(length + r6);
            strBuilder.getChars(r5, r0, this.buffer, length);
            this.size += r6;
        }
        return this;
    }

    public StrBuilder append(char[] cArr) {
        if (cArr == null) {
            return appendNull();
        }
        int length = cArr.length;
        if (length > 0) {
            int length2 = length();
            ensureCapacity(length2 + length);
            System.arraycopy(cArr, 0, this.buffer, length2, length);
            this.size += length;
        }
        return this;
    }

    public StrBuilder append(char[] cArr, int r4, int r5) {
        if (cArr == null) {
            return appendNull();
        }
        if (r4 < 0 || r4 > cArr.length) {
            throw new StringIndexOutOfBoundsException("Invalid startIndex: " + r5);
        } else if (r5 < 0 || r4 + r5 > cArr.length) {
            throw new StringIndexOutOfBoundsException("Invalid length: " + r5);
        } else {
            if (r5 > 0) {
                int length = length();
                ensureCapacity(length + r5);
                System.arraycopy(cArr, r4, this.buffer, length, r5);
                this.size += r5;
            }
            return this;
        }
    }

    public StrBuilder append(boolean z) {
        if (z) {
            ensureCapacity(this.size + 4);
            char[] cArr = this.buffer;
            int r1 = this.size;
            int r2 = r1 + 1;
            this.size = r2;
            cArr[r1] = 't';
            int r12 = r2 + 1;
            this.size = r12;
            cArr[r2] = 'r';
            int r22 = r12 + 1;
            this.size = r22;
            cArr[r12] = 'u';
            this.size = r22 + 1;
            cArr[r22] = 'e';
        } else {
            ensureCapacity(this.size + 5);
            char[] cArr2 = this.buffer;
            int r13 = this.size;
            int r23 = r13 + 1;
            this.size = r23;
            cArr2[r13] = 'f';
            int r14 = r23 + 1;
            this.size = r14;
            cArr2[r23] = 'a';
            int r24 = r14 + 1;
            this.size = r24;
            cArr2[r14] = 'l';
            int r15 = r24 + 1;
            this.size = r15;
            cArr2[r24] = 's';
            this.size = r15 + 1;
            cArr2[r15] = 'e';
        }
        return this;
    }

    @Override // java.lang.Appendable
    public StrBuilder append(char c) {
        ensureCapacity(length() + 1);
        char[] cArr = this.buffer;
        int r1 = this.size;
        this.size = r1 + 1;
        cArr[r1] = c;
        return this;
    }

    public StrBuilder append(int r1) {
        return append(String.valueOf(r1));
    }

    public StrBuilder append(long j) {
        return append(String.valueOf(j));
    }

    public StrBuilder append(float f) {
        return append(String.valueOf(f));
    }

    public StrBuilder append(double d) {
        return append(String.valueOf(d));
    }

    public StrBuilder appendln(Object obj) {
        return append(obj).appendNewLine();
    }

    public StrBuilder appendln(String str) {
        return append(str).appendNewLine();
    }

    public StrBuilder appendln(String str, int r2, int r3) {
        return append(str, r2, r3).appendNewLine();
    }

    public StrBuilder appendln(String str, Object... objArr) {
        return append(str, objArr).appendNewLine();
    }

    public StrBuilder appendln(StringBuffer stringBuffer) {
        return append(stringBuffer).appendNewLine();
    }

    public StrBuilder appendln(StringBuilder sb) {
        return append(sb).appendNewLine();
    }

    public StrBuilder appendln(StringBuilder sb, int r2, int r3) {
        return append(sb, r2, r3).appendNewLine();
    }

    public StrBuilder appendln(StringBuffer stringBuffer, int r2, int r3) {
        return append(stringBuffer, r2, r3).appendNewLine();
    }

    public StrBuilder appendln(StrBuilder strBuilder) {
        return append(strBuilder).appendNewLine();
    }

    public StrBuilder appendln(StrBuilder strBuilder, int r2, int r3) {
        return append(strBuilder, r2, r3).appendNewLine();
    }

    public StrBuilder appendln(char[] cArr) {
        return append(cArr).appendNewLine();
    }

    public StrBuilder appendln(char[] cArr, int r2, int r3) {
        return append(cArr, r2, r3).appendNewLine();
    }

    public StrBuilder appendln(boolean z) {
        return append(z).appendNewLine();
    }

    public StrBuilder appendln(char c) {
        return append(c).appendNewLine();
    }

    public StrBuilder appendln(int r1) {
        return append(r1).appendNewLine();
    }

    public StrBuilder appendln(long j) {
        return append(j).appendNewLine();
    }

    public StrBuilder appendln(float f) {
        return append(f).appendNewLine();
    }

    public StrBuilder appendln(double d) {
        return append(d).appendNewLine();
    }

    public <T> StrBuilder appendAll(T... tArr) {
        if (tArr != null && tArr.length > 0) {
            for (T t : tArr) {
                append(t);
            }
        }
        return this;
    }

    public StrBuilder appendAll(Iterable<?> iterable) {
        if (iterable != null) {
            Iterator<?> it = iterable.iterator();
            while (it.hasNext()) {
                append(it.next());
            }
        }
        return this;
    }

    public StrBuilder appendAll(Iterator<?> it) {
        if (it != null) {
            while (it.hasNext()) {
                append(it.next());
            }
        }
        return this;
    }

    public StrBuilder appendWithSeparators(Object[] objArr, String str) {
        if (objArr != null && objArr.length > 0) {
            String objects = Objects.toString(str, "");
            append(objArr[0]);
            for (int r0 = 1; r0 < objArr.length; r0++) {
                append(objects);
                append(objArr[r0]);
            }
        }
        return this;
    }

    public StrBuilder appendWithSeparators(Iterable<?> iterable, String str) {
        if (iterable != null) {
            String objects = Objects.toString(str, "");
            Iterator<?> it = iterable.iterator();
            while (it.hasNext()) {
                append(it.next());
                if (it.hasNext()) {
                    append(objects);
                }
            }
        }
        return this;
    }

    public StrBuilder appendWithSeparators(Iterator<?> it, String str) {
        if (it != null) {
            String objects = Objects.toString(str, "");
            while (it.hasNext()) {
                append(it.next());
                if (it.hasNext()) {
                    append(objects);
                }
            }
        }
        return this;
    }

    public StrBuilder appendSeparator(String str) {
        return appendSeparator(str, (String) null);
    }

    public StrBuilder appendSeparator(String str, String str2) {
        if (isEmpty()) {
            str = str2;
        }
        if (str != null) {
            append(str);
        }
        return this;
    }

    public StrBuilder appendSeparator(char c) {
        if (size() > 0) {
            append(c);
        }
        return this;
    }

    public StrBuilder appendSeparator(char c, char c2) {
        if (size() > 0) {
            append(c);
        } else {
            append(c2);
        }
        return this;
    }

    public StrBuilder appendSeparator(String str, int r2) {
        if (str != null && r2 > 0) {
            append(str);
        }
        return this;
    }

    public StrBuilder appendSeparator(char c, int r2) {
        if (r2 > 0) {
            append(c);
        }
        return this;
    }

    public StrBuilder appendPadding(int r5, char c) {
        if (r5 >= 0) {
            ensureCapacity(this.size + r5);
            for (int r0 = 0; r0 < r5; r0++) {
                char[] cArr = this.buffer;
                int r2 = this.size;
                this.size = r2 + 1;
                cArr[r2] = c;
            }
        }
        return this;
    }

    public StrBuilder appendFixedWidthPadLeft(Object obj, int r8, char c) {
        if (r8 > 0) {
            ensureCapacity(this.size + r8);
            String nullText = obj == null ? getNullText() : obj.toString();
            if (nullText == null) {
                nullText = "";
            }
            int length = nullText.length();
            if (length >= r8) {
                nullText.getChars(length - r8, length, this.buffer, this.size);
            } else {
                int r1 = r8 - length;
                for (int r3 = 0; r3 < r1; r3++) {
                    this.buffer[this.size + r3] = c;
                }
                nullText.getChars(0, length, this.buffer, this.size + r1);
            }
            this.size += r8;
        }
        return this;
    }

    public StrBuilder appendFixedWidthPadLeft(int r1, int r2, char c) {
        return appendFixedWidthPadLeft(String.valueOf(r1), r2, c);
    }

    public StrBuilder appendFixedWidthPadRight(Object obj, int r7, char c) {
        if (r7 > 0) {
            ensureCapacity(this.size + r7);
            String nullText = obj == null ? getNullText() : obj.toString();
            if (nullText == null) {
                nullText = "";
            }
            int length = nullText.length();
            if (length >= r7) {
                nullText.getChars(0, r7, this.buffer, this.size);
            } else {
                int r2 = r7 - length;
                nullText.getChars(0, length, this.buffer, this.size);
                for (int r1 = 0; r1 < r2; r1++) {
                    this.buffer[this.size + length + r1] = c;
                }
            }
            this.size += r7;
        }
        return this;
    }

    public StrBuilder appendFixedWidthPadRight(int r1, int r2, char c) {
        return appendFixedWidthPadRight(String.valueOf(r1), r2, c);
    }

    public StrBuilder insert(int r1, Object obj) {
        if (obj == null) {
            return insert(r1, this.nullText);
        }
        return insert(r1, obj.toString());
    }

    public StrBuilder insert(int r6, String str) {
        int length;
        validateIndex(r6);
        if (str == null) {
            str = this.nullText;
        }
        if (str != null && (length = str.length()) > 0) {
            int r1 = this.size + length;
            ensureCapacity(r1);
            char[] cArr = this.buffer;
            System.arraycopy(cArr, r6, cArr, r6 + length, this.size - r6);
            this.size = r1;
            str.getChars(0, length, this.buffer, r6);
        }
        return this;
    }

    public StrBuilder insert(int r5, char[] cArr) {
        validateIndex(r5);
        if (cArr == null) {
            return insert(r5, this.nullText);
        }
        int length = cArr.length;
        if (length > 0) {
            ensureCapacity(this.size + length);
            char[] cArr2 = this.buffer;
            System.arraycopy(cArr2, r5, cArr2, r5 + length, this.size - r5);
            System.arraycopy(cArr, 0, this.buffer, r5, length);
            this.size += length;
        }
        return this;
    }

    public StrBuilder insert(int r4, char[] cArr, int r6, int r7) {
        validateIndex(r4);
        if (cArr == null) {
            return insert(r4, this.nullText);
        }
        if (r6 < 0 || r6 > cArr.length) {
            throw new StringIndexOutOfBoundsException("Invalid offset: " + r6);
        } else if (r7 < 0 || r6 + r7 > cArr.length) {
            throw new StringIndexOutOfBoundsException("Invalid length: " + r7);
        } else {
            if (r7 > 0) {
                ensureCapacity(this.size + r7);
                char[] cArr2 = this.buffer;
                System.arraycopy(cArr2, r4, cArr2, r4 + r7, this.size - r4);
                System.arraycopy(cArr, r6, this.buffer, r4, r7);
                this.size += r7;
            }
            return this;
        }
    }

    public StrBuilder insert(int r4, boolean z) {
        validateIndex(r4);
        if (z) {
            ensureCapacity(this.size + 4);
            char[] cArr = this.buffer;
            System.arraycopy(cArr, r4, cArr, r4 + 4, this.size - r4);
            char[] cArr2 = this.buffer;
            int r1 = r4 + 1;
            cArr2[r4] = 't';
            int r42 = r1 + 1;
            cArr2[r1] = 'r';
            cArr2[r42] = 'u';
            cArr2[r42 + 1] = 'e';
            this.size += 4;
        } else {
            ensureCapacity(this.size + 5);
            char[] cArr3 = this.buffer;
            System.arraycopy(cArr3, r4, cArr3, r4 + 5, this.size - r4);
            char[] cArr4 = this.buffer;
            int r12 = r4 + 1;
            cArr4[r4] = 'f';
            int r43 = r12 + 1;
            cArr4[r12] = 'a';
            int r13 = r43 + 1;
            cArr4[r43] = 'l';
            cArr4[r13] = 's';
            cArr4[r13 + 1] = 'e';
            this.size += 5;
        }
        return this;
    }

    public StrBuilder insert(int r4, char c) {
        validateIndex(r4);
        ensureCapacity(this.size + 1);
        char[] cArr = this.buffer;
        System.arraycopy(cArr, r4, cArr, r4 + 1, this.size - r4);
        this.buffer[r4] = c;
        this.size++;
        return this;
    }

    public StrBuilder insert(int r1, int r2) {
        return insert(r1, String.valueOf(r2));
    }

    public StrBuilder insert(int r1, long j) {
        return insert(r1, String.valueOf(j));
    }

    public StrBuilder insert(int r1, float f) {
        return insert(r1, String.valueOf(f));
    }

    public StrBuilder insert(int r1, double d) {
        return insert(r1, String.valueOf(d));
    }

    private void deleteImpl(int r3, int r4, int r5) {
        char[] cArr = this.buffer;
        System.arraycopy(cArr, r4, cArr, r3, this.size - r4);
        this.size -= r5;
    }

    public StrBuilder delete(int r2, int r3) {
        int validateRange = validateRange(r2, r3);
        int r0 = validateRange - r2;
        if (r0 > 0) {
            deleteImpl(r2, validateRange, r0);
        }
        return this;
    }

    public StrBuilder deleteAll(char c) {
        int r0 = 0;
        while (r0 < this.size) {
            if (this.buffer[r0] == c) {
                int r1 = r0;
                do {
                    r1++;
                    if (r1 >= this.size) {
                        break;
                    }
                } while (this.buffer[r1] == c);
                int r2 = r1 - r0;
                deleteImpl(r0, r1, r2);
                r0 = r1 - r2;
            }
            r0++;
        }
        return this;
    }

    public StrBuilder deleteFirst(char c) {
        int r0 = 0;
        while (true) {
            if (r0 >= this.size) {
                break;
            } else if (this.buffer[r0] == c) {
                deleteImpl(r0, r0 + 1, 1);
                break;
            } else {
                r0++;
            }
        }
        return this;
    }

    public StrBuilder deleteAll(String str) {
        int length = str == null ? 0 : str.length();
        if (length > 0) {
            int indexOf = indexOf(str, 0);
            while (indexOf >= 0) {
                deleteImpl(indexOf, indexOf + length, length);
                indexOf = indexOf(str, indexOf);
            }
        }
        return this;
    }

    public StrBuilder deleteFirst(String str) {
        int indexOf;
        int length = str == null ? 0 : str.length();
        if (length > 0 && (indexOf = indexOf(str, 0)) >= 0) {
            deleteImpl(indexOf, indexOf + length, length);
        }
        return this;
    }

    public StrBuilder deleteAll(StrMatcher strMatcher) {
        return replace(strMatcher, null, 0, this.size, -1);
    }

    public StrBuilder deleteFirst(StrMatcher strMatcher) {
        return replace(strMatcher, null, 0, this.size, 1);
    }

    private void replaceImpl(int r4, int r5, int r6, String str, int r8) {
        int r0 = (this.size - r6) + r8;
        if (r8 != r6) {
            ensureCapacity(r0);
            char[] cArr = this.buffer;
            System.arraycopy(cArr, r5, cArr, r4 + r8, this.size - r5);
            this.size = r0;
        }
        if (r8 > 0) {
            str.getChars(0, r8, this.buffer, r4);
        }
    }

    public StrBuilder replace(int r7, int r8, String str) {
        int validateRange = validateRange(r7, r8);
        replaceImpl(r7, validateRange, validateRange - r7, str, str == null ? 0 : str.length());
        return this;
    }

    public StrBuilder replaceAll(char c, char c2) {
        if (c != c2) {
            for (int r0 = 0; r0 < this.size; r0++) {
                char[] cArr = this.buffer;
                if (cArr[r0] == c) {
                    cArr[r0] = c2;
                }
            }
        }
        return this;
    }

    public StrBuilder replaceFirst(char c, char c2) {
        if (c != c2) {
            int r0 = 0;
            while (true) {
                if (r0 >= this.size) {
                    break;
                }
                char[] cArr = this.buffer;
                if (cArr[r0] == c) {
                    cArr[r0] = c2;
                    break;
                }
                r0++;
            }
        }
        return this;
    }

    public StrBuilder replaceAll(String str, String str2) {
        int length = str == null ? 0 : str.length();
        if (length > 0) {
            int length2 = str2 == null ? 0 : str2.length();
            int indexOf = indexOf(str, 0);
            while (indexOf >= 0) {
                replaceImpl(indexOf, indexOf + length, length, str2, length2);
                indexOf = indexOf(str, indexOf + length2);
            }
        }
        return this;
    }

    public StrBuilder replaceFirst(String str, String str2) {
        int indexOf;
        int length = str == null ? 0 : str.length();
        if (length > 0 && (indexOf = indexOf(str, 0)) >= 0) {
            replaceImpl(indexOf, indexOf + length, length, str2, str2 == null ? 0 : str2.length());
        }
        return this;
    }

    public StrBuilder replaceAll(StrMatcher strMatcher, String str) {
        return replace(strMatcher, str, 0, this.size, -1);
    }

    public StrBuilder replaceFirst(StrMatcher strMatcher, String str) {
        return replace(strMatcher, str, 0, this.size, 1);
    }

    public StrBuilder replace(StrMatcher strMatcher, String str, int r9, int r10, int r11) {
        return replaceImpl(strMatcher, str, r9, validateRange(r9, r10), r11);
    }

    private StrBuilder replaceImpl(StrMatcher strMatcher, String str, int r12, int r13, int r14) {
        if (strMatcher != null && this.size != 0) {
            int length = str == null ? 0 : str.length();
            int r7 = r12;
            while (r7 < r13 && r14 != 0) {
                int isMatch = strMatcher.isMatch(this.buffer, r7, r12, r13);
                if (isMatch > 0) {
                    replaceImpl(r7, r7 + isMatch, isMatch, str, length);
                    r13 = (r13 - isMatch) + length;
                    r7 = (r7 + length) - 1;
                    if (r14 > 0) {
                        r14--;
                    }
                }
                r7++;
            }
        }
        return this;
    }

    public StrBuilder reverse() {
        int r0 = this.size;
        if (r0 == 0) {
            return this;
        }
        int r1 = r0 / 2;
        char[] cArr = this.buffer;
        int r3 = 0;
        int r02 = r0 - 1;
        while (r3 < r1) {
            char c = cArr[r3];
            cArr[r3] = cArr[r02];
            cArr[r02] = c;
            r3++;
            r02--;
        }
        return this;
    }

    public StrBuilder trim() {
        int r0 = this.size;
        if (r0 == 0) {
            return this;
        }
        char[] cArr = this.buffer;
        int r3 = 0;
        while (r3 < r0 && cArr[r3] <= ' ') {
            r3++;
        }
        while (r3 < r0 && cArr[r0 - 1] <= ' ') {
            r0--;
        }
        int r1 = this.size;
        if (r0 < r1) {
            delete(r0, r1);
        }
        if (r3 > 0) {
            delete(0, r3);
        }
        return this;
    }

    public boolean startsWith(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return true;
        }
        if (length > this.size) {
            return false;
        }
        for (int r3 = 0; r3 < length; r3++) {
            if (this.buffer[r3] != str.charAt(r3)) {
                return false;
            }
        }
        return true;
    }

    public boolean endsWith(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return true;
        }
        int r3 = this.size;
        if (length > r3) {
            return false;
        }
        int r32 = r3 - length;
        int r4 = 0;
        while (r4 < length) {
            if (this.buffer[r32] != str.charAt(r4)) {
                return false;
            }
            r4++;
            r32++;
        }
        return true;
    }

    @Override // java.lang.CharSequence
    public CharSequence subSequence(int r2, int r3) {
        if (r2 < 0) {
            throw new StringIndexOutOfBoundsException(r2);
        }
        if (r3 <= this.size) {
            if (r2 > r3) {
                throw new StringIndexOutOfBoundsException(r3 - r2);
            }
            return substring(r2, r3);
        }
        throw new StringIndexOutOfBoundsException(r3);
    }

    public String substring(int r2) {
        return substring(r2, this.size);
    }

    public String substring(int r3, int r4) {
        return new String(this.buffer, r3, validateRange(r3, r4) - r3);
    }

    public String leftString(int r4) {
        if (r4 <= 0) {
            return "";
        }
        int r0 = this.size;
        if (r4 >= r0) {
            return new String(this.buffer, 0, r0);
        }
        return new String(this.buffer, 0, r4);
    }

    public String rightString(int r4) {
        if (r4 <= 0) {
            return "";
        }
        int r0 = this.size;
        if (r4 >= r0) {
            return new String(this.buffer, 0, r0);
        }
        return new String(this.buffer, r0 - r4, r4);
    }

    public String midString(int r3, int r4) {
        int r0;
        if (r3 < 0) {
            r3 = 0;
        }
        if (r4 <= 0 || r3 >= (r0 = this.size)) {
            return "";
        }
        if (r0 <= r3 + r4) {
            return new String(this.buffer, r3, r0 - r3);
        }
        return new String(this.buffer, r3, r4);
    }

    public boolean contains(char c) {
        char[] cArr = this.buffer;
        for (int r2 = 0; r2 < this.size; r2++) {
            if (cArr[r2] == c) {
                return true;
            }
        }
        return false;
    }

    public boolean contains(String str) {
        return indexOf(str, 0) >= 0;
    }

    public boolean contains(StrMatcher strMatcher) {
        return indexOf(strMatcher, 0) >= 0;
    }

    public int indexOf(char c) {
        return indexOf(c, 0);
    }

    public int indexOf(char c, int r5) {
        if (r5 < 0) {
            r5 = 0;
        }
        if (r5 >= this.size) {
            return -1;
        }
        char[] cArr = this.buffer;
        while (r5 < this.size) {
            if (cArr[r5] == c) {
                return r5;
            }
            r5++;
        }
        return -1;
    }

    public int indexOf(String str) {
        return indexOf(str, 0);
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x0037, code lost:
        r10 = r10 + 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int indexOf(java.lang.String r9, int r10) {
        /*
            r8 = this;
            r0 = 0
            if (r10 >= 0) goto L4
            r10 = 0
        L4:
            r1 = -1
            if (r9 == 0) goto L3e
            int r2 = r8.size
            if (r10 < r2) goto Lc
            goto L3e
        Lc:
            int r2 = r9.length()
            r3 = 1
            if (r2 != r3) goto L1c
            char r9 = r9.charAt(r0)
            int r9 = r8.indexOf(r9, r10)
            return r9
        L1c:
            if (r2 != 0) goto L1f
            return r10
        L1f:
            int r4 = r8.size
            if (r2 <= r4) goto L24
            return r1
        L24:
            char[] r5 = r8.buffer
            int r4 = r4 - r2
            int r4 = r4 + r3
        L28:
            if (r10 >= r4) goto L3e
            r3 = 0
        L2b:
            if (r3 >= r2) goto L3d
            char r6 = r9.charAt(r3)
            int r7 = r10 + r3
            char r7 = r5[r7]
            if (r6 == r7) goto L3a
            int r10 = r10 + 1
            goto L28
        L3a:
            int r3 = r3 + 1
            goto L2b
        L3d:
            return r10
        L3e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang3.text.StrBuilder.indexOf(java.lang.String, int):int");
    }

    public int indexOf(StrMatcher strMatcher) {
        return indexOf(strMatcher, 0);
    }

    public int indexOf(StrMatcher strMatcher, int r7) {
        int r1;
        if (r7 < 0) {
            r7 = 0;
        }
        if (strMatcher != null && r7 < (r1 = this.size)) {
            char[] cArr = this.buffer;
            for (int r3 = r7; r3 < r1; r3++) {
                if (strMatcher.isMatch(cArr, r3, r7, r1) > 0) {
                    return r3;
                }
            }
        }
        return -1;
    }

    public int lastIndexOf(char c) {
        return lastIndexOf(c, this.size - 1);
    }

    public int lastIndexOf(char c, int r4) {
        int r0 = this.size;
        if (r4 >= r0) {
            r4 = r0 - 1;
        }
        if (r4 < 0) {
            return -1;
        }
        while (r4 >= 0) {
            if (this.buffer[r4] == c) {
                return r4;
            }
            r4--;
        }
        return -1;
    }

    public int lastIndexOf(String str) {
        return lastIndexOf(str, this.size - 1);
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x0036, code lost:
        r9 = r9 - 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int lastIndexOf(java.lang.String r8, int r9) {
        /*
            r7 = this;
            int r0 = r7.size
            r1 = 1
            if (r9 < r0) goto L7
            int r9 = r0 + (-1)
        L7:
            r0 = -1
            if (r8 == 0) goto L40
            if (r9 >= 0) goto Ld
            goto L40
        Ld:
            int r2 = r8.length()
            if (r2 <= 0) goto L3d
            int r3 = r7.size
            if (r2 > r3) goto L3d
            r3 = 0
            if (r2 != r1) goto L23
            char r8 = r8.charAt(r3)
            int r8 = r7.lastIndexOf(r8, r9)
            return r8
        L23:
            int r9 = r9 - r2
            int r9 = r9 + r1
        L25:
            if (r9 < 0) goto L40
            r1 = 0
        L28:
            if (r1 >= r2) goto L3c
            char r4 = r8.charAt(r1)
            char[] r5 = r7.buffer
            int r6 = r9 + r1
            char r5 = r5[r6]
            if (r4 == r5) goto L39
            int r9 = r9 + (-1)
            goto L25
        L39:
            int r1 = r1 + 1
            goto L28
        L3c:
            return r9
        L3d:
            if (r2 != 0) goto L40
            return r9
        L40:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang3.text.StrBuilder.lastIndexOf(java.lang.String, int):int");
    }

    public int lastIndexOf(StrMatcher strMatcher) {
        return lastIndexOf(strMatcher, this.size);
    }

    public int lastIndexOf(StrMatcher strMatcher, int r6) {
        int r0 = this.size;
        if (r6 >= r0) {
            r6 = r0 - 1;
        }
        if (strMatcher != null && r6 >= 0) {
            char[] cArr = this.buffer;
            int r2 = r6 + 1;
            while (r6 >= 0) {
                if (strMatcher.isMatch(cArr, r6, 0, r2) > 0) {
                    return r6;
                }
                r6--;
            }
        }
        return -1;
    }

    public StrTokenizer asTokenizer() {
        return new StrBuilderTokenizer();
    }

    public Reader asReader() {
        return new StrBuilderReader();
    }

    public Writer asWriter() {
        return new StrBuilderWriter();
    }

    public void appendTo(Appendable appendable) throws IOException {
        if (appendable instanceof Writer) {
            ((Writer) appendable).write(this.buffer, 0, this.size);
        } else if (appendable instanceof StringBuilder) {
            ((StringBuilder) appendable).append(this.buffer, 0, this.size);
        } else if (appendable instanceof StringBuffer) {
            ((StringBuffer) appendable).append(this.buffer, 0, this.size);
        } else if (appendable instanceof CharBuffer) {
            ((CharBuffer) appendable).put(this.buffer, 0, this.size);
        } else {
            appendable.append(this);
        }
    }

    public boolean equalsIgnoreCase(StrBuilder strBuilder) {
        if (this == strBuilder) {
            return true;
        }
        int r1 = this.size;
        if (r1 != strBuilder.size) {
            return false;
        }
        char[] cArr = this.buffer;
        char[] cArr2 = strBuilder.buffer;
        for (int r12 = r1 - 1; r12 >= 0; r12--) {
            char c = cArr[r12];
            char c2 = cArr2[r12];
            if (c != c2 && Character.toUpperCase(c) != Character.toUpperCase(c2)) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(StrBuilder strBuilder) {
        int r2;
        if (this == strBuilder) {
            return true;
        }
        if (strBuilder != null && (r2 = this.size) == strBuilder.size) {
            char[] cArr = this.buffer;
            char[] cArr2 = strBuilder.buffer;
            for (int r22 = r2 - 1; r22 >= 0; r22--) {
                if (cArr[r22] != cArr2[r22]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public boolean equals(Object obj) {
        return (obj instanceof StrBuilder) && equals((StrBuilder) obj);
    }

    public int hashCode() {
        char[] cArr = this.buffer;
        int r2 = 0;
        for (int r1 = this.size - 1; r1 >= 0; r1--) {
            r2 = (r2 * 31) + cArr[r1];
        }
        return r2;
    }

    @Override // java.lang.CharSequence
    public String toString() {
        return new String(this.buffer, 0, this.size);
    }

    public StringBuffer toStringBuffer() {
        StringBuffer stringBuffer = new StringBuffer(this.size);
        stringBuffer.append(this.buffer, 0, this.size);
        return stringBuffer;
    }

    public StringBuilder toStringBuilder() {
        StringBuilder sb = new StringBuilder(this.size);
        sb.append(this.buffer, 0, this.size);
        return sb;
    }

    @Override // org.apache.commons.lang3.builder.Builder
    public String build() {
        return toString();
    }

    protected int validateRange(int r2, int r3) {
        if (r2 < 0) {
            throw new StringIndexOutOfBoundsException(r2);
        }
        int r0 = this.size;
        if (r3 > r0) {
            r3 = r0;
        }
        if (r2 <= r3) {
            return r3;
        }
        throw new StringIndexOutOfBoundsException("end < start");
    }

    protected void validateIndex(int r2) {
        if (r2 < 0 || r2 > this.size) {
            throw new StringIndexOutOfBoundsException(r2);
        }
    }

    /* loaded from: classes5.dex */
    class StrBuilderTokenizer extends StrTokenizer {
        StrBuilderTokenizer() {
        }

        @Override // org.apache.commons.lang3.text.StrTokenizer
        protected List<String> tokenize(char[] cArr, int r2, int r3) {
            if (cArr == null) {
                return super.tokenize(StrBuilder.this.buffer, 0, StrBuilder.this.size());
            }
            return super.tokenize(cArr, r2, r3);
        }

        @Override // org.apache.commons.lang3.text.StrTokenizer
        public String getContent() {
            String content = super.getContent();
            return content == null ? StrBuilder.this.toString() : content;
        }
    }

    /* loaded from: classes5.dex */
    class StrBuilderReader extends Reader {
        private int mark;
        private int pos;

        @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        @Override // java.io.Reader
        public boolean markSupported() {
            return true;
        }

        StrBuilderReader() {
        }

        @Override // java.io.Reader
        public int read() {
            if (ready()) {
                StrBuilder strBuilder = StrBuilder.this;
                int r1 = this.pos;
                this.pos = r1 + 1;
                return strBuilder.charAt(r1);
            }
            return -1;
        }

        @Override // java.io.Reader
        public int read(char[] cArr, int r5, int r6) {
            int r0;
            if (r5 < 0 || r6 < 0 || r5 > cArr.length || (r0 = r5 + r6) > cArr.length || r0 < 0) {
                throw new IndexOutOfBoundsException();
            }
            if (r6 == 0) {
                return 0;
            }
            if (this.pos >= StrBuilder.this.size()) {
                return -1;
            }
            if (this.pos + r6 > StrBuilder.this.size()) {
                r6 = StrBuilder.this.size() - this.pos;
            }
            StrBuilder strBuilder = StrBuilder.this;
            int r1 = this.pos;
            strBuilder.getChars(r1, r1 + r6, cArr, r5);
            this.pos += r6;
            return r6;
        }

        @Override // java.io.Reader
        public long skip(long j) {
            if (this.pos + j > StrBuilder.this.size()) {
                j = StrBuilder.this.size() - this.pos;
            }
            if (j < 0) {
                return 0L;
            }
            this.pos = (int) (this.pos + j);
            return j;
        }

        @Override // java.io.Reader
        public boolean ready() {
            return this.pos < StrBuilder.this.size();
        }

        @Override // java.io.Reader
        public void mark(int r1) {
            this.mark = this.pos;
        }

        @Override // java.io.Reader
        public void reset() {
            this.pos = this.mark;
        }
    }

    /* loaded from: classes5.dex */
    class StrBuilderWriter extends Writer {
        @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        @Override // java.io.Writer, java.io.Flushable
        public void flush() {
        }

        StrBuilderWriter() {
        }

        @Override // java.io.Writer
        public void write(int r2) {
            StrBuilder.this.append((char) r2);
        }

        @Override // java.io.Writer
        public void write(char[] cArr) {
            StrBuilder.this.append(cArr);
        }

        @Override // java.io.Writer
        public void write(char[] cArr, int r3, int r4) {
            StrBuilder.this.append(cArr, r3, r4);
        }

        @Override // java.io.Writer
        public void write(String str) {
            StrBuilder.this.append(str);
        }

        @Override // java.io.Writer
        public void write(String str, int r3, int r4) {
            StrBuilder.this.append(str, r3, r4);
        }
    }
}
