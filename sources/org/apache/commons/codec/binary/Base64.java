package org.apache.commons.codec.binary;

import com.google.common.base.Ascii;
import java.math.BigInteger;
import java.util.Objects;
import okio.Utf8;
import org.apache.commons.codec.binary.BaseNCodec;
import org.apache.commons.fileupload.MultipartStream;

/* loaded from: classes5.dex */
public class Base64 extends BaseNCodec {
    private static final int BITS_PER_ENCODED_BYTE = 6;
    private static final int BYTES_PER_ENCODED_BLOCK = 4;
    private static final int BYTES_PER_UNENCODED_BLOCK = 3;
    private static final int MASK_6BITS = 63;
    private final int decodeSize;
    private final byte[] decodeTable;
    private final int encodeSize;
    private final byte[] encodeTable;
    private final byte[] lineSeparator;
    static final byte[] CHUNK_SEPARATOR = {13, 10};
    private static final byte[] STANDARD_ENCODE_TABLE = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};
    private static final byte[] URL_SAFE_ENCODE_TABLE = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, MultipartStream.DASH, 95};
    private static final byte[] DECODE_TABLE = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, 62, -1, Utf8.REPLACEMENT_BYTE, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, Ascii.f1132VT, Ascii.f1121FF, 13, Ascii.f1129SO, Ascii.f1128SI, 16, 17, Ascii.DC2, 19, Ascii.DC4, Ascii.NAK, Ascii.SYN, Ascii.ETB, Ascii.CAN, Ascii.f1120EM, -1, -1, -1, -1, Utf8.REPLACEMENT_BYTE, -1, Ascii.SUB, Ascii.ESC, Ascii.f1122FS, Ascii.f1123GS, Ascii.f1127RS, Ascii.f1131US, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, MultipartStream.DASH, 46, 47, 48, 49, 50, 51};

    public Base64() {
        this(0);
    }

    public Base64(boolean z) {
        this(76, CHUNK_SEPARATOR, z);
    }

    public Base64(int r2) {
        this(r2, CHUNK_SEPARATOR);
    }

    public Base64(int r2, byte[] bArr) {
        this(r2, bArr, false);
    }

    public Base64(int r5, byte[] bArr, boolean z) {
        super(3, 4, r5, bArr == null ? 0 : bArr.length);
        this.decodeTable = DECODE_TABLE;
        if (bArr != null) {
            if (containsAlphabetOrPad(bArr)) {
                String newStringUtf8 = StringUtils.newStringUtf8(bArr);
                throw new IllegalArgumentException("lineSeparator must not contain base64 characters: [" + newStringUtf8 + "]");
            } else if (r5 > 0) {
                this.encodeSize = bArr.length + 4;
                byte[] bArr2 = new byte[bArr.length];
                this.lineSeparator = bArr2;
                System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            } else {
                this.encodeSize = 4;
                this.lineSeparator = null;
            }
        } else {
            this.encodeSize = 4;
            this.lineSeparator = null;
        }
        this.decodeSize = this.encodeSize - 1;
        this.encodeTable = z ? URL_SAFE_ENCODE_TABLE : STANDARD_ENCODE_TABLE;
    }

    public boolean isUrlSafe() {
        return this.encodeTable == URL_SAFE_ENCODE_TABLE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.commons.codec.binary.BaseNCodec
    public void encode(byte[] bArr, int r9, int r10, BaseNCodec.Context context) {
        if (context.eof) {
            return;
        }
        if (r10 >= 0) {
            int r2 = 0;
            while (r2 < r10) {
                byte[] ensureBufferSize = ensureBufferSize(this.encodeSize, context);
                context.modulus = (context.modulus + 1) % 3;
                int r4 = r9 + 1;
                int r92 = bArr[r9];
                if (r92 < 0) {
                    r92 += 256;
                }
                context.ibitWorkArea = (context.ibitWorkArea << 8) + r92;
                if (context.modulus == 0) {
                    int r93 = context.pos;
                    context.pos = r93 + 1;
                    ensureBufferSize[r93] = this.encodeTable[(context.ibitWorkArea >> 18) & 63];
                    int r94 = context.pos;
                    context.pos = r94 + 1;
                    ensureBufferSize[r94] = this.encodeTable[(context.ibitWorkArea >> 12) & 63];
                    int r95 = context.pos;
                    context.pos = r95 + 1;
                    ensureBufferSize[r95] = this.encodeTable[(context.ibitWorkArea >> 6) & 63];
                    int r96 = context.pos;
                    context.pos = r96 + 1;
                    ensureBufferSize[r96] = this.encodeTable[context.ibitWorkArea & 63];
                    context.currentLinePos += 4;
                    if (this.lineLength > 0 && this.lineLength <= context.currentLinePos) {
                        System.arraycopy(this.lineSeparator, 0, ensureBufferSize, context.pos, this.lineSeparator.length);
                        context.pos += this.lineSeparator.length;
                        context.currentLinePos = 0;
                    }
                }
                r2++;
                r9 = r4;
            }
            return;
        }
        context.eof = true;
        if (context.modulus == 0 && this.lineLength == 0) {
            return;
        }
        byte[] ensureBufferSize2 = ensureBufferSize(this.encodeSize, context);
        int r97 = context.pos;
        int r102 = context.modulus;
        if (r102 != 0) {
            if (r102 == 1) {
                int r103 = context.pos;
                context.pos = r103 + 1;
                ensureBufferSize2[r103] = this.encodeTable[(context.ibitWorkArea >> 2) & 63];
                int r104 = context.pos;
                context.pos = r104 + 1;
                ensureBufferSize2[r104] = this.encodeTable[(context.ibitWorkArea << 4) & 63];
                if (this.encodeTable == STANDARD_ENCODE_TABLE) {
                    int r105 = context.pos;
                    context.pos = r105 + 1;
                    ensureBufferSize2[r105] = this.pad;
                    int r106 = context.pos;
                    context.pos = r106 + 1;
                    ensureBufferSize2[r106] = this.pad;
                }
            } else if (r102 == 2) {
                int r107 = context.pos;
                context.pos = r107 + 1;
                ensureBufferSize2[r107] = this.encodeTable[(context.ibitWorkArea >> 10) & 63];
                int r108 = context.pos;
                context.pos = r108 + 1;
                ensureBufferSize2[r108] = this.encodeTable[(context.ibitWorkArea >> 4) & 63];
                int r109 = context.pos;
                context.pos = r109 + 1;
                ensureBufferSize2[r109] = this.encodeTable[(context.ibitWorkArea << 2) & 63];
                if (this.encodeTable == STANDARD_ENCODE_TABLE) {
                    int r1010 = context.pos;
                    context.pos = r1010 + 1;
                    ensureBufferSize2[r1010] = this.pad;
                }
            } else {
                throw new IllegalStateException("Impossible modulus " + context.modulus);
            }
        }
        context.currentLinePos += context.pos - r97;
        if (this.lineLength <= 0 || context.currentLinePos <= 0) {
            return;
        }
        System.arraycopy(this.lineSeparator, 0, ensureBufferSize2, context.pos, this.lineSeparator.length);
        context.pos += this.lineSeparator.length;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.commons.codec.binary.BaseNCodec
    public void decode(byte[] bArr, int r8, int r9, BaseNCodec.Context context) {
        byte b;
        if (context.eof) {
            return;
        }
        if (r9 < 0) {
            context.eof = true;
        }
        int r1 = 0;
        while (true) {
            if (r1 >= r9) {
                break;
            }
            byte[] ensureBufferSize = ensureBufferSize(this.decodeSize, context);
            int r3 = r8 + 1;
            byte b2 = bArr[r8];
            if (b2 == this.pad) {
                context.eof = true;
                break;
            }
            if (b2 >= 0) {
                byte[] bArr2 = DECODE_TABLE;
                if (b2 < bArr2.length && (b = bArr2[b2]) >= 0) {
                    context.modulus = (context.modulus + 1) % 4;
                    context.ibitWorkArea = (context.ibitWorkArea << 6) + b;
                    if (context.modulus == 0) {
                        int r82 = context.pos;
                        context.pos = r82 + 1;
                        ensureBufferSize[r82] = (byte) ((context.ibitWorkArea >> 16) & 255);
                        int r83 = context.pos;
                        context.pos = r83 + 1;
                        ensureBufferSize[r83] = (byte) ((context.ibitWorkArea >> 8) & 255);
                        int r84 = context.pos;
                        context.pos = r84 + 1;
                        ensureBufferSize[r84] = (byte) (context.ibitWorkArea & 255);
                    }
                }
            }
            r1++;
            r8 = r3;
        }
        if (!context.eof || context.modulus == 0) {
            return;
        }
        byte[] ensureBufferSize2 = ensureBufferSize(this.decodeSize, context);
        int r85 = context.modulus;
        if (r85 != 1) {
            if (r85 == 2) {
                context.ibitWorkArea >>= 4;
                int r86 = context.pos;
                context.pos = r86 + 1;
                ensureBufferSize2[r86] = (byte) (context.ibitWorkArea & 255);
            } else if (r85 == 3) {
                context.ibitWorkArea >>= 2;
                int r87 = context.pos;
                context.pos = r87 + 1;
                ensureBufferSize2[r87] = (byte) ((context.ibitWorkArea >> 8) & 255);
                int r88 = context.pos;
                context.pos = r88 + 1;
                ensureBufferSize2[r88] = (byte) (context.ibitWorkArea & 255);
            } else {
                throw new IllegalStateException("Impossible modulus " + context.modulus);
            }
        }
    }

    @Deprecated
    public static boolean isArrayByteBase64(byte[] bArr) {
        return isBase64(bArr);
    }

    public static boolean isBase64(byte b) {
        if (b != 61) {
            if (b >= 0) {
                byte[] bArr = DECODE_TABLE;
                if (b >= bArr.length || bArr[b] == -1) {
                }
            }
            return false;
        }
        return true;
    }

    public static boolean isBase64(String str) {
        return isBase64(StringUtils.getBytesUtf8(str));
    }

    public static boolean isBase64(byte[] bArr) {
        for (int r1 = 0; r1 < bArr.length; r1++) {
            if (!isBase64(bArr[r1]) && !isWhiteSpace(bArr[r1])) {
                return false;
            }
        }
        return true;
    }

    public static byte[] encodeBase64(byte[] bArr) {
        return encodeBase64(bArr, false);
    }

    public static String encodeBase64String(byte[] bArr) {
        return StringUtils.newStringUtf8(encodeBase64(bArr, false));
    }

    public static byte[] encodeBase64URLSafe(byte[] bArr) {
        return encodeBase64(bArr, false, true);
    }

    public static String encodeBase64URLSafeString(byte[] bArr) {
        return StringUtils.newStringUtf8(encodeBase64(bArr, false, true));
    }

    public static byte[] encodeBase64Chunked(byte[] bArr) {
        return encodeBase64(bArr, true);
    }

    public static byte[] encodeBase64(byte[] bArr, boolean z) {
        return encodeBase64(bArr, z, false);
    }

    public static byte[] encodeBase64(byte[] bArr, boolean z, boolean z2) {
        return encodeBase64(bArr, z, z2, Integer.MAX_VALUE);
    }

    public static byte[] encodeBase64(byte[] bArr, boolean z, boolean z2, int r7) {
        if (bArr == null || bArr.length == 0) {
            return bArr;
        }
        Base64 base64 = z ? new Base64(z2) : new Base64(0, CHUNK_SEPARATOR, z2);
        long encodedLength = base64.getEncodedLength(bArr);
        if (encodedLength > r7) {
            throw new IllegalArgumentException("Input array too big, the output array would be bigger (" + encodedLength + ") than the specified maximum size of " + r7);
        }
        return base64.encode(bArr);
    }

    public static byte[] decodeBase64(String str) {
        return new Base64().decode(str);
    }

    public static byte[] decodeBase64(byte[] bArr) {
        return new Base64().decode(bArr);
    }

    public static BigInteger decodeInteger(byte[] bArr) {
        return new BigInteger(1, decodeBase64(bArr));
    }

    public static byte[] encodeInteger(BigInteger bigInteger) {
        Objects.requireNonNull(bigInteger, "encodeInteger called with null parameter");
        return encodeBase64(toIntegerBytes(bigInteger), false);
    }

    static byte[] toIntegerBytes(BigInteger bigInteger) {
        int bitLength = ((bigInteger.bitLength() + 7) >> 3) << 3;
        byte[] byteArray = bigInteger.toByteArray();
        int r3 = 1;
        if (bigInteger.bitLength() % 8 == 0 || (bigInteger.bitLength() / 8) + 1 != bitLength / 8) {
            int length = byteArray.length;
            if (bigInteger.bitLength() % 8 == 0) {
                length--;
            } else {
                r3 = 0;
            }
            int r0 = bitLength / 8;
            int r5 = r0 - length;
            byte[] bArr = new byte[r0];
            System.arraycopy(byteArray, r3, bArr, r5, length);
            return bArr;
        }
        return byteArray;
    }

    @Override // org.apache.commons.codec.binary.BaseNCodec
    protected boolean isInAlphabet(byte b) {
        if (b >= 0) {
            byte[] bArr = this.decodeTable;
            if (b < bArr.length && bArr[b] != -1) {
                return true;
            }
        }
        return false;
    }
}
