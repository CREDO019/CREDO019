package org.apache.commons.codec.binary;

import com.google.common.base.Ascii;
import org.apache.commons.codec.binary.BaseNCodec;

/* loaded from: classes5.dex */
public class Base32 extends BaseNCodec {
    private static final int BITS_PER_ENCODED_BYTE = 5;
    private static final int BYTES_PER_ENCODED_BLOCK = 8;
    private static final int BYTES_PER_UNENCODED_BLOCK = 5;
    private static final byte[] CHUNK_SEPARATOR = {13, 10};
    private static final byte[] DECODE_TABLE = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, Ascii.SUB, Ascii.ESC, Ascii.f1122FS, Ascii.f1123GS, Ascii.f1127RS, Ascii.f1131US, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, Ascii.f1132VT, Ascii.f1121FF, 13, Ascii.f1129SO, Ascii.f1128SI, 16, 17, Ascii.DC2, 19, Ascii.DC4, Ascii.NAK, Ascii.SYN, Ascii.ETB, Ascii.CAN, Ascii.f1120EM};
    private static final byte[] ENCODE_TABLE = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 50, 51, 52, 53, 54, 55};
    private static final byte[] HEX_DECODE_TABLE = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, -1, -1, -1, -1, -1, -1, -1, 10, Ascii.f1132VT, Ascii.f1121FF, 13, Ascii.f1129SO, Ascii.f1128SI, 16, 17, Ascii.DC2, 19, Ascii.DC4, Ascii.NAK, Ascii.SYN, Ascii.ETB, Ascii.CAN, Ascii.f1120EM, Ascii.SUB, Ascii.ESC, Ascii.f1122FS, Ascii.f1123GS, Ascii.f1127RS, Ascii.f1131US, 32};
    private static final byte[] HEX_ENCODE_TABLE = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86};
    private static final int MASK_5BITS = 31;
    private final int decodeSize;
    private final byte[] decodeTable;
    private final int encodeSize;
    private final byte[] encodeTable;
    private final byte[] lineSeparator;

    public Base32() {
        this(false);
    }

    public Base32(byte b) {
        this(false, b);
    }

    public Base32(boolean z) {
        this(0, null, z, (byte) 61);
    }

    public Base32(boolean z, byte b) {
        this(0, null, z, b);
    }

    public Base32(int r2) {
        this(r2, CHUNK_SEPARATOR);
    }

    public Base32(int r3, byte[] bArr) {
        this(r3, bArr, false, (byte) 61);
    }

    public Base32(int r2, byte[] bArr, boolean z) {
        this(r2, bArr, z, (byte) 61);
    }

    public Base32(int r9, byte[] bArr, boolean z, byte b) {
        super(5, 8, r9, bArr == null ? 0 : bArr.length, b);
        if (z) {
            this.encodeTable = HEX_ENCODE_TABLE;
            this.decodeTable = HEX_DECODE_TABLE;
        } else {
            this.encodeTable = ENCODE_TABLE;
            this.decodeTable = DECODE_TABLE;
        }
        if (r9 <= 0) {
            this.encodeSize = 8;
            this.lineSeparator = null;
        } else if (bArr == null) {
            throw new IllegalArgumentException("lineLength " + r9 + " > 0, but lineSeparator is null");
        } else if (containsAlphabetOrPad(bArr)) {
            String newStringUtf8 = StringUtils.newStringUtf8(bArr);
            throw new IllegalArgumentException("lineSeparator must not contain Base32 characters: [" + newStringUtf8 + "]");
        } else {
            this.encodeSize = bArr.length + 8;
            byte[] bArr2 = new byte[bArr.length];
            this.lineSeparator = bArr2;
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        }
        this.decodeSize = this.encodeSize - 1;
        if (isInAlphabet(b) || isWhiteSpace(b)) {
            throw new IllegalArgumentException("pad must not be in alphabet or whitespace");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v2, types: [int, boolean] */
    /* JADX WARN: Type inference failed for: r3v27 */
    @Override // org.apache.commons.codec.binary.BaseNCodec
    public void decode(byte[] bArr, int r18, int r19, BaseNCodec.Context context) {
        byte b;
        if (context.eof) {
            return;
        }
        ?? r3 = 1;
        if (r19 < 0) {
            context.eof = true;
        }
        int r4 = r18;
        int r5 = 0;
        while (true) {
            if (r5 >= r19) {
                break;
            }
            int r11 = r4 + 1;
            byte b2 = bArr[r4];
            if (b2 == this.pad) {
                context.eof = r3;
                break;
            }
            byte[] ensureBufferSize = ensureBufferSize(this.decodeSize, context);
            if (b2 >= 0) {
                byte[] bArr2 = this.decodeTable;
                if (b2 < bArr2.length && (b = bArr2[b2]) >= 0) {
                    context.modulus = (context.modulus + r3) % 8;
                    context.lbitWorkArea = (context.lbitWorkArea << 5) + b;
                    if (context.modulus == 0) {
                        int r32 = context.pos;
                        context.pos = r32 + 1;
                        ensureBufferSize[r32] = (byte) ((context.lbitWorkArea >> 32) & 255);
                        int r33 = context.pos;
                        context.pos = r33 + 1;
                        ensureBufferSize[r33] = (byte) ((context.lbitWorkArea >> 24) & 255);
                        int r34 = context.pos;
                        context.pos = r34 + 1;
                        ensureBufferSize[r34] = (byte) ((context.lbitWorkArea >> 16) & 255);
                        int r35 = context.pos;
                        context.pos = r35 + 1;
                        ensureBufferSize[r35] = (byte) ((context.lbitWorkArea >> 8) & 255);
                        int r36 = context.pos;
                        context.pos = r36 + 1;
                        ensureBufferSize[r36] = (byte) (context.lbitWorkArea & 255);
                    }
                }
            }
            r5++;
            r4 = r11;
            r3 = 1;
        }
        if (!context.eof || context.modulus < 2) {
            return;
        }
        byte[] ensureBufferSize2 = ensureBufferSize(this.decodeSize, context);
        switch (context.modulus) {
            case 2:
                int r42 = context.pos;
                context.pos = r42 + 1;
                ensureBufferSize2[r42] = (byte) ((context.lbitWorkArea >> 2) & 255);
                return;
            case 3:
                int r37 = context.pos;
                context.pos = r37 + 1;
                ensureBufferSize2[r37] = (byte) ((context.lbitWorkArea >> 7) & 255);
                return;
            case 4:
                context.lbitWorkArea >>= 4;
                int r38 = context.pos;
                context.pos = r38 + 1;
                ensureBufferSize2[r38] = (byte) ((context.lbitWorkArea >> 8) & 255);
                int r39 = context.pos;
                context.pos = r39 + 1;
                ensureBufferSize2[r39] = (byte) (context.lbitWorkArea & 255);
                return;
            case 5:
                context.lbitWorkArea >>= 1;
                int r310 = context.pos;
                context.pos = r310 + 1;
                ensureBufferSize2[r310] = (byte) ((context.lbitWorkArea >> 16) & 255);
                int r311 = context.pos;
                context.pos = r311 + 1;
                ensureBufferSize2[r311] = (byte) ((context.lbitWorkArea >> 8) & 255);
                int r312 = context.pos;
                context.pos = r312 + 1;
                ensureBufferSize2[r312] = (byte) (context.lbitWorkArea & 255);
                return;
            case 6:
                context.lbitWorkArea >>= 6;
                int r313 = context.pos;
                context.pos = r313 + 1;
                ensureBufferSize2[r313] = (byte) ((context.lbitWorkArea >> 16) & 255);
                int r314 = context.pos;
                context.pos = r314 + 1;
                ensureBufferSize2[r314] = (byte) ((context.lbitWorkArea >> 8) & 255);
                int r315 = context.pos;
                context.pos = r315 + 1;
                ensureBufferSize2[r315] = (byte) (context.lbitWorkArea & 255);
                return;
            case 7:
                context.lbitWorkArea >>= 3;
                int r316 = context.pos;
                context.pos = r316 + 1;
                ensureBufferSize2[r316] = (byte) ((context.lbitWorkArea >> 24) & 255);
                int r317 = context.pos;
                context.pos = r317 + 1;
                ensureBufferSize2[r317] = (byte) ((context.lbitWorkArea >> 16) & 255);
                int r318 = context.pos;
                context.pos = r318 + 1;
                ensureBufferSize2[r318] = (byte) ((context.lbitWorkArea >> 8) & 255);
                int r319 = context.pos;
                context.pos = r319 + 1;
                ensureBufferSize2[r319] = (byte) (context.lbitWorkArea & 255);
                return;
            default:
                throw new IllegalStateException("Impossible modulus " + context.modulus);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.commons.codec.binary.BaseNCodec
    public void encode(byte[] bArr, int r13, int r14, BaseNCodec.Context context) {
        if (context.eof) {
            return;
        }
        if (r14 >= 0) {
            int r2 = 0;
            while (r2 < r14) {
                byte[] ensureBufferSize = ensureBufferSize(this.encodeSize, context);
                context.modulus = (context.modulus + 1) % 5;
                int r4 = r13 + 1;
                int r132 = bArr[r13];
                if (r132 < 0) {
                    r132 += 256;
                }
                context.lbitWorkArea = (context.lbitWorkArea << 8) + r132;
                if (context.modulus == 0) {
                    int r133 = context.pos;
                    context.pos = r133 + 1;
                    ensureBufferSize[r133] = this.encodeTable[((int) (context.lbitWorkArea >> 35)) & 31];
                    int r134 = context.pos;
                    context.pos = r134 + 1;
                    ensureBufferSize[r134] = this.encodeTable[((int) (context.lbitWorkArea >> 30)) & 31];
                    int r135 = context.pos;
                    context.pos = r135 + 1;
                    ensureBufferSize[r135] = this.encodeTable[((int) (context.lbitWorkArea >> 25)) & 31];
                    int r136 = context.pos;
                    context.pos = r136 + 1;
                    ensureBufferSize[r136] = this.encodeTable[((int) (context.lbitWorkArea >> 20)) & 31];
                    int r137 = context.pos;
                    context.pos = r137 + 1;
                    ensureBufferSize[r137] = this.encodeTable[((int) (context.lbitWorkArea >> 15)) & 31];
                    int r138 = context.pos;
                    context.pos = r138 + 1;
                    ensureBufferSize[r138] = this.encodeTable[((int) (context.lbitWorkArea >> 10)) & 31];
                    int r139 = context.pos;
                    context.pos = r139 + 1;
                    ensureBufferSize[r139] = this.encodeTable[((int) (context.lbitWorkArea >> 5)) & 31];
                    int r1310 = context.pos;
                    context.pos = r1310 + 1;
                    ensureBufferSize[r1310] = this.encodeTable[((int) context.lbitWorkArea) & 31];
                    context.currentLinePos += 8;
                    if (this.lineLength > 0 && this.lineLength <= context.currentLinePos) {
                        System.arraycopy(this.lineSeparator, 0, ensureBufferSize, context.pos, this.lineSeparator.length);
                        context.pos += this.lineSeparator.length;
                        context.currentLinePos = 0;
                    }
                }
                r2++;
                r13 = r4;
            }
            return;
        }
        context.eof = true;
        if (context.modulus == 0 && this.lineLength == 0) {
            return;
        }
        byte[] ensureBufferSize2 = ensureBufferSize(this.encodeSize, context);
        int r1311 = context.pos;
        int r142 = context.modulus;
        if (r142 != 0) {
            if (r142 == 1) {
                int r143 = context.pos;
                context.pos = r143 + 1;
                ensureBufferSize2[r143] = this.encodeTable[((int) (context.lbitWorkArea >> 3)) & 31];
                int r144 = context.pos;
                context.pos = r144 + 1;
                ensureBufferSize2[r144] = this.encodeTable[((int) (context.lbitWorkArea << 2)) & 31];
                int r145 = context.pos;
                context.pos = r145 + 1;
                ensureBufferSize2[r145] = this.pad;
                int r146 = context.pos;
                context.pos = r146 + 1;
                ensureBufferSize2[r146] = this.pad;
                int r147 = context.pos;
                context.pos = r147 + 1;
                ensureBufferSize2[r147] = this.pad;
                int r148 = context.pos;
                context.pos = r148 + 1;
                ensureBufferSize2[r148] = this.pad;
                int r149 = context.pos;
                context.pos = r149 + 1;
                ensureBufferSize2[r149] = this.pad;
                int r1410 = context.pos;
                context.pos = r1410 + 1;
                ensureBufferSize2[r1410] = this.pad;
            } else if (r142 == 2) {
                int r1411 = context.pos;
                context.pos = r1411 + 1;
                ensureBufferSize2[r1411] = this.encodeTable[((int) (context.lbitWorkArea >> 11)) & 31];
                int r1412 = context.pos;
                context.pos = r1412 + 1;
                ensureBufferSize2[r1412] = this.encodeTable[((int) (context.lbitWorkArea >> 6)) & 31];
                int r1413 = context.pos;
                context.pos = r1413 + 1;
                ensureBufferSize2[r1413] = this.encodeTable[((int) (context.lbitWorkArea >> 1)) & 31];
                int r1414 = context.pos;
                context.pos = r1414 + 1;
                ensureBufferSize2[r1414] = this.encodeTable[((int) (context.lbitWorkArea << 4)) & 31];
                int r1415 = context.pos;
                context.pos = r1415 + 1;
                ensureBufferSize2[r1415] = this.pad;
                int r1416 = context.pos;
                context.pos = r1416 + 1;
                ensureBufferSize2[r1416] = this.pad;
                int r1417 = context.pos;
                context.pos = r1417 + 1;
                ensureBufferSize2[r1417] = this.pad;
                int r1418 = context.pos;
                context.pos = r1418 + 1;
                ensureBufferSize2[r1418] = this.pad;
            } else if (r142 == 3) {
                int r1419 = context.pos;
                context.pos = r1419 + 1;
                ensureBufferSize2[r1419] = this.encodeTable[((int) (context.lbitWorkArea >> 19)) & 31];
                int r1420 = context.pos;
                context.pos = r1420 + 1;
                ensureBufferSize2[r1420] = this.encodeTable[((int) (context.lbitWorkArea >> 14)) & 31];
                int r1421 = context.pos;
                context.pos = r1421 + 1;
                ensureBufferSize2[r1421] = this.encodeTable[((int) (context.lbitWorkArea >> 9)) & 31];
                int r1422 = context.pos;
                context.pos = r1422 + 1;
                ensureBufferSize2[r1422] = this.encodeTable[((int) (context.lbitWorkArea >> 4)) & 31];
                int r1423 = context.pos;
                context.pos = r1423 + 1;
                ensureBufferSize2[r1423] = this.encodeTable[((int) (context.lbitWorkArea << 1)) & 31];
                int r1424 = context.pos;
                context.pos = r1424 + 1;
                ensureBufferSize2[r1424] = this.pad;
                int r1425 = context.pos;
                context.pos = r1425 + 1;
                ensureBufferSize2[r1425] = this.pad;
                int r1426 = context.pos;
                context.pos = r1426 + 1;
                ensureBufferSize2[r1426] = this.pad;
            } else if (r142 == 4) {
                int r1427 = context.pos;
                context.pos = r1427 + 1;
                ensureBufferSize2[r1427] = this.encodeTable[((int) (context.lbitWorkArea >> 27)) & 31];
                int r1428 = context.pos;
                context.pos = r1428 + 1;
                ensureBufferSize2[r1428] = this.encodeTable[((int) (context.lbitWorkArea >> 22)) & 31];
                int r1429 = context.pos;
                context.pos = r1429 + 1;
                ensureBufferSize2[r1429] = this.encodeTable[((int) (context.lbitWorkArea >> 17)) & 31];
                int r1430 = context.pos;
                context.pos = r1430 + 1;
                ensureBufferSize2[r1430] = this.encodeTable[((int) (context.lbitWorkArea >> 12)) & 31];
                int r1431 = context.pos;
                context.pos = r1431 + 1;
                ensureBufferSize2[r1431] = this.encodeTable[((int) (context.lbitWorkArea >> 7)) & 31];
                int r1432 = context.pos;
                context.pos = r1432 + 1;
                ensureBufferSize2[r1432] = this.encodeTable[((int) (context.lbitWorkArea >> 2)) & 31];
                int r1433 = context.pos;
                context.pos = r1433 + 1;
                ensureBufferSize2[r1433] = this.encodeTable[((int) (context.lbitWorkArea << 3)) & 31];
                int r1434 = context.pos;
                context.pos = r1434 + 1;
                ensureBufferSize2[r1434] = this.pad;
            } else {
                throw new IllegalStateException("Impossible modulus " + context.modulus);
            }
        }
        context.currentLinePos += context.pos - r1311;
        if (this.lineLength <= 0 || context.currentLinePos <= 0) {
            return;
        }
        System.arraycopy(this.lineSeparator, 0, ensureBufferSize2, context.pos, this.lineSeparator.length);
        context.pos += this.lineSeparator.length;
    }

    @Override // org.apache.commons.codec.binary.BaseNCodec
    public boolean isInAlphabet(byte b) {
        if (b >= 0) {
            byte[] bArr = this.decodeTable;
            if (b < bArr.length && bArr[b] != -1) {
                return true;
            }
        }
        return false;
    }
}
