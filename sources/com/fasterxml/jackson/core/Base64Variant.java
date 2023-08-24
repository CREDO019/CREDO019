package com.fasterxml.jackson.core;

import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import java.io.Serializable;
import java.util.Arrays;
import kotlin.text.Typography;
import org.apache.commons.p028io.IOUtils;

/* loaded from: classes.dex */
public final class Base64Variant implements Serializable {
    public static final int BASE64_VALUE_INVALID = -1;
    public static final int BASE64_VALUE_PADDING = -2;
    private static final int INT_SPACE = 32;
    static final char PADDING_CHAR_NONE = 0;
    private static final long serialVersionUID = 1;
    private final transient int[] _asciiToBase64;
    private final transient byte[] _base64ToAsciiB;
    private final transient char[] _base64ToAsciiC;
    private final transient int _maxLineLength;
    final String _name;
    private final transient char _paddingChar;
    private final transient boolean _usesPadding;

    public boolean equals(Object obj) {
        return obj == this;
    }

    public Base64Variant(String str, String str2, boolean z, char c, int r9) {
        int[] r0 = new int[128];
        this._asciiToBase64 = r0;
        char[] cArr = new char[64];
        this._base64ToAsciiC = cArr;
        this._base64ToAsciiB = new byte[64];
        this._name = str;
        this._usesPadding = z;
        this._paddingChar = c;
        this._maxLineLength = r9;
        int length = str2.length();
        if (length != 64) {
            throw new IllegalArgumentException("Base64Alphabet length must be exactly 64 (was " + length + ")");
        }
        str2.getChars(0, length, cArr, 0);
        Arrays.fill(r0, -1);
        for (int r92 = 0; r92 < length; r92++) {
            char c2 = this._base64ToAsciiC[r92];
            this._base64ToAsciiB[r92] = (byte) c2;
            this._asciiToBase64[c2] = r92;
        }
        if (z) {
            this._asciiToBase64[c] = -2;
        }
    }

    public Base64Variant(Base64Variant base64Variant, String str, int r9) {
        this(base64Variant, str, base64Variant._usesPadding, base64Variant._paddingChar, r9);
    }

    public Base64Variant(Base64Variant base64Variant, String str, boolean z, char c, int r10) {
        int[] r0 = new int[128];
        this._asciiToBase64 = r0;
        char[] cArr = new char[64];
        this._base64ToAsciiC = cArr;
        byte[] bArr = new byte[64];
        this._base64ToAsciiB = bArr;
        this._name = str;
        byte[] bArr2 = base64Variant._base64ToAsciiB;
        System.arraycopy(bArr2, 0, bArr, 0, bArr2.length);
        char[] cArr2 = base64Variant._base64ToAsciiC;
        System.arraycopy(cArr2, 0, cArr, 0, cArr2.length);
        int[] r6 = base64Variant._asciiToBase64;
        System.arraycopy(r6, 0, r0, 0, r6.length);
        this._usesPadding = z;
        this._paddingChar = c;
        this._maxLineLength = r10;
    }

    protected Object readResolve() {
        return Base64Variants.valueOf(this._name);
    }

    public String getName() {
        return this._name;
    }

    public boolean usesPadding() {
        return this._usesPadding;
    }

    public boolean usesPaddingChar(char c) {
        return c == this._paddingChar;
    }

    public boolean usesPaddingChar(int r2) {
        return r2 == this._paddingChar;
    }

    public char getPaddingChar() {
        return this._paddingChar;
    }

    public byte getPaddingByte() {
        return (byte) this._paddingChar;
    }

    public int getMaxLineLength() {
        return this._maxLineLength;
    }

    public int decodeBase64Char(char c) {
        if (c <= 127) {
            return this._asciiToBase64[c];
        }
        return -1;
    }

    public int decodeBase64Char(int r2) {
        if (r2 <= 127) {
            return this._asciiToBase64[r2];
        }
        return -1;
    }

    public int decodeBase64Byte(byte b) {
        if (b < 0) {
            return -1;
        }
        return this._asciiToBase64[b];
    }

    public char encodeBase64BitsAsChar(int r2) {
        return this._base64ToAsciiC[r2];
    }

    public int encodeBase64Chunk(int r4, char[] cArr, int r6) {
        int r0 = r6 + 1;
        char[] cArr2 = this._base64ToAsciiC;
        cArr[r6] = cArr2[(r4 >> 18) & 63];
        int r62 = r0 + 1;
        cArr[r0] = cArr2[(r4 >> 12) & 63];
        int r02 = r62 + 1;
        cArr[r62] = cArr2[(r4 >> 6) & 63];
        int r63 = r02 + 1;
        cArr[r02] = cArr2[r4 & 63];
        return r63;
    }

    public void encodeBase64Chunk(StringBuilder sb, int r4) {
        sb.append(this._base64ToAsciiC[(r4 >> 18) & 63]);
        sb.append(this._base64ToAsciiC[(r4 >> 12) & 63]);
        sb.append(this._base64ToAsciiC[(r4 >> 6) & 63]);
        sb.append(this._base64ToAsciiC[r4 & 63]);
    }

    public int encodeBase64Partial(int r4, int r5, char[] cArr, int r7) {
        int r0 = r7 + 1;
        char[] cArr2 = this._base64ToAsciiC;
        cArr[r7] = cArr2[(r4 >> 18) & 63];
        int r72 = r0 + 1;
        cArr[r0] = cArr2[(r4 >> 12) & 63];
        if (this._usesPadding) {
            int r02 = r72 + 1;
            cArr[r72] = r5 == 2 ? cArr2[(r4 >> 6) & 63] : this._paddingChar;
            int r73 = r02 + 1;
            cArr[r02] = this._paddingChar;
            return r73;
        } else if (r5 == 2) {
            int r52 = r72 + 1;
            cArr[r72] = cArr2[(r4 >> 6) & 63];
            return r52;
        } else {
            return r72;
        }
    }

    public void encodeBase64Partial(StringBuilder sb, int r4, int r5) {
        sb.append(this._base64ToAsciiC[(r4 >> 18) & 63]);
        sb.append(this._base64ToAsciiC[(r4 >> 12) & 63]);
        if (this._usesPadding) {
            sb.append(r5 == 2 ? this._base64ToAsciiC[(r4 >> 6) & 63] : this._paddingChar);
            sb.append(this._paddingChar);
        } else if (r5 == 2) {
            sb.append(this._base64ToAsciiC[(r4 >> 6) & 63]);
        }
    }

    public byte encodeBase64BitsAsByte(int r2) {
        return this._base64ToAsciiB[r2];
    }

    public int encodeBase64Chunk(int r4, byte[] bArr, int r6) {
        int r0 = r6 + 1;
        byte[] bArr2 = this._base64ToAsciiB;
        bArr[r6] = bArr2[(r4 >> 18) & 63];
        int r62 = r0 + 1;
        bArr[r0] = bArr2[(r4 >> 12) & 63];
        int r02 = r62 + 1;
        bArr[r62] = bArr2[(r4 >> 6) & 63];
        int r63 = r02 + 1;
        bArr[r02] = bArr2[r4 & 63];
        return r63;
    }

    public int encodeBase64Partial(int r5, int r6, byte[] bArr, int r8) {
        int r0 = r8 + 1;
        byte[] bArr2 = this._base64ToAsciiB;
        bArr[r8] = bArr2[(r5 >> 18) & 63];
        int r82 = r0 + 1;
        bArr[r0] = bArr2[(r5 >> 12) & 63];
        if (!this._usesPadding) {
            if (r6 == 2) {
                int r62 = r82 + 1;
                bArr[r82] = bArr2[(r5 >> 6) & 63];
                return r62;
            }
            return r82;
        }
        byte b = (byte) this._paddingChar;
        int r3 = r82 + 1;
        bArr[r82] = r6 == 2 ? bArr2[(r5 >> 6) & 63] : b;
        int r83 = r3 + 1;
        bArr[r3] = b;
        return r83;
    }

    public String encode(byte[] bArr) {
        return encode(bArr, false);
    }

    public String encode(byte[] bArr, boolean z) {
        int length = bArr.length;
        StringBuilder sb = new StringBuilder((length >> 2) + length + (length >> 3));
        if (z) {
            sb.append(Typography.quote);
        }
        int maxLineLength = getMaxLineLength() >> 2;
        int r5 = 0;
        int r6 = length - 3;
        while (r5 <= r6) {
            int r7 = r5 + 1;
            int r8 = r7 + 1;
            int r72 = r8 + 1;
            encodeBase64Chunk(sb, (((bArr[r5] << 8) | (bArr[r7] & 255)) << 8) | (bArr[r8] & 255));
            maxLineLength--;
            if (maxLineLength <= 0) {
                sb.append(IOUtils.DIR_SEPARATOR_WINDOWS);
                sb.append('n');
                maxLineLength = getMaxLineLength() >> 2;
            }
            r5 = r72;
        }
        int r0 = length - r5;
        if (r0 > 0) {
            int r3 = r5 + 1;
            int r52 = bArr[r5] << 16;
            if (r0 == 2) {
                r52 |= (bArr[r3] & 255) << 8;
            }
            encodeBase64Partial(sb, r52, r0);
        }
        if (z) {
            sb.append(Typography.quote);
        }
        return sb.toString();
    }

    public byte[] decode(String str) throws IllegalArgumentException {
        ByteArrayBuilder byteArrayBuilder = new ByteArrayBuilder();
        decode(str, byteArrayBuilder);
        return byteArrayBuilder.toByteArray();
    }

    public void decode(String str, ByteArrayBuilder byteArrayBuilder) throws IllegalArgumentException {
        int r3;
        char charAt;
        int length = str.length();
        int r2 = 0;
        while (r2 < length) {
            while (true) {
                r3 = r2 + 1;
                charAt = str.charAt(r2);
                if (r3 >= length || charAt > ' ') {
                    break;
                }
                r2 = r3;
            }
            int decodeBase64Char = decodeBase64Char(charAt);
            if (decodeBase64Char < 0) {
                _reportInvalidBase64(charAt, 0, null);
            }
            if (r3 >= length) {
                _reportBase64EOF();
            }
            int r22 = r3 + 1;
            char charAt2 = str.charAt(r3);
            int decodeBase64Char2 = decodeBase64Char(charAt2);
            if (decodeBase64Char2 < 0) {
                _reportInvalidBase64(charAt2, 1, null);
            }
            int r32 = (decodeBase64Char << 6) | decodeBase64Char2;
            if (r22 >= length) {
                if (!usesPadding()) {
                    byteArrayBuilder.append(r32 >> 4);
                    return;
                }
                _reportBase64EOF();
            }
            int r4 = r22 + 1;
            char charAt3 = str.charAt(r22);
            int decodeBase64Char3 = decodeBase64Char(charAt3);
            if (decodeBase64Char3 < 0) {
                if (decodeBase64Char3 != -2) {
                    _reportInvalidBase64(charAt3, 2, null);
                }
                if (r4 >= length) {
                    _reportBase64EOF();
                }
                r2 = r4 + 1;
                char charAt4 = str.charAt(r4);
                if (!usesPaddingChar(charAt4)) {
                    _reportInvalidBase64(charAt4, 3, "expected padding character '" + getPaddingChar() + "'");
                }
                byteArrayBuilder.append(r32 >> 4);
            } else {
                int r23 = (r32 << 6) | decodeBase64Char3;
                if (r4 >= length) {
                    if (!usesPadding()) {
                        byteArrayBuilder.appendTwoBytes(r23 >> 2);
                        return;
                    }
                    _reportBase64EOF();
                }
                int r33 = r4 + 1;
                char charAt5 = str.charAt(r4);
                int decodeBase64Char4 = decodeBase64Char(charAt5);
                if (decodeBase64Char4 < 0) {
                    if (decodeBase64Char4 != -2) {
                        _reportInvalidBase64(charAt5, 3, null);
                    }
                    byteArrayBuilder.appendTwoBytes(r23 >> 2);
                } else {
                    byteArrayBuilder.appendThreeBytes((r23 << 6) | decodeBase64Char4);
                }
                r2 = r33;
            }
        }
    }

    public String toString() {
        return this._name;
    }

    public int hashCode() {
        return this._name.hashCode();
    }

    protected void _reportInvalidBase64(char c, int r4, String str) throws IllegalArgumentException {
        String str2;
        if (c <= ' ') {
            str2 = "Illegal white space character (code 0x" + Integer.toHexString(c) + ") as character #" + (r4 + 1) + " of 4-char base64 unit: can only used between units";
        } else if (usesPaddingChar(c)) {
            str2 = "Unexpected padding character ('" + getPaddingChar() + "') as character #" + (r4 + 1) + " of 4-char base64 unit: padding only legal as 3rd or 4th character";
        } else if (!Character.isDefined(c) || Character.isISOControl(c)) {
            str2 = "Illegal character (code 0x" + Integer.toHexString(c) + ") in base64 content";
        } else {
            str2 = "Illegal character '" + c + "' (code 0x" + Integer.toHexString(c) + ") in base64 content";
        }
        if (str != null) {
            str2 = str2 + ": " + str;
        }
        throw new IllegalArgumentException(str2);
    }

    protected void _reportBase64EOF() throws IllegalArgumentException {
        throw new IllegalArgumentException("Unexpected end-of-String in base64 content");
    }
}
