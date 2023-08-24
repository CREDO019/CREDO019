package org.bouncycastle.util.encoders;

import java.io.IOException;
import java.io.OutputStream;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Strings;

/* loaded from: classes4.dex */
public class Base32Encoder implements Encoder {
    private static final byte[] DEAULT_ENCODING_TABLE = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 50, 51, 52, 53, 54, 55};
    private static final byte DEFAULT_PADDING = 61;
    private final byte[] decodingTable;
    private final byte[] encodingTable;
    private final byte padding;

    public Base32Encoder() {
        this.decodingTable = new byte[128];
        this.encodingTable = DEAULT_ENCODING_TABLE;
        this.padding = DEFAULT_PADDING;
        initialiseDecodingTable();
    }

    public Base32Encoder(byte[] bArr, byte b) {
        this.decodingTable = new byte[128];
        if (bArr.length != 32) {
            throw new IllegalArgumentException("encoding table needs to be length 32");
        }
        this.encodingTable = Arrays.clone(bArr);
        this.padding = b;
        initialiseDecodingTable();
    }

    private int decodeLastBlock(OutputStream outputStream, char c, char c2, char c3, char c4, char c5, char c6, char c7, char c8) throws IOException {
        byte b = this.padding;
        if (c8 != b) {
            byte[] bArr = this.decodingTable;
            byte b2 = bArr[c];
            byte b3 = bArr[c2];
            byte b4 = bArr[c3];
            byte b5 = bArr[c4];
            byte b6 = bArr[c5];
            byte b7 = bArr[c6];
            byte b8 = bArr[c7];
            byte b9 = bArr[c8];
            if ((b2 | b3 | b4 | b5 | b6 | b7 | b8 | b9) >= 0) {
                outputStream.write((b2 << 3) | (b3 >> 2));
                outputStream.write((b3 << 6) | (b4 << 1) | (b5 >> 4));
                outputStream.write((b5 << 4) | (b6 >> 1));
                outputStream.write((b6 << 7) | (b7 << 2) | (b8 >> 3));
                outputStream.write((b8 << 5) | b9);
                return 5;
            }
            throw new IOException("invalid characters encountered at end of base32 data");
        } else if (c7 != b) {
            byte[] bArr2 = this.decodingTable;
            byte b10 = bArr2[c];
            byte b11 = bArr2[c2];
            byte b12 = bArr2[c3];
            byte b13 = bArr2[c4];
            byte b14 = bArr2[c5];
            byte b15 = bArr2[c6];
            byte b16 = bArr2[c7];
            if ((b10 | b11 | b12 | b13 | b14 | b15 | b16) >= 0) {
                outputStream.write((b10 << 3) | (b11 >> 2));
                outputStream.write((b11 << 6) | (b12 << 1) | (b13 >> 4));
                outputStream.write((b13 << 4) | (b14 >> 1));
                outputStream.write((b14 << 7) | (b15 << 2) | (b16 >> 3));
                return 4;
            }
            throw new IOException("invalid characters encountered at end of base32 data");
        } else if (c6 == b) {
            if (c5 != b) {
                byte[] bArr3 = this.decodingTable;
                byte b17 = bArr3[c];
                byte b18 = bArr3[c2];
                byte b19 = bArr3[c3];
                byte b20 = bArr3[c4];
                byte b21 = bArr3[c5];
                if ((b17 | b18 | b19 | b20 | b21) >= 0) {
                    outputStream.write((b17 << 3) | (b18 >> 2));
                    outputStream.write((b18 << 6) | (b19 << 1) | (b20 >> 4));
                    outputStream.write((b20 << 4) | (b21 >> 1));
                    return 3;
                }
                throw new IOException("invalid characters encountered at end of base32 data");
            } else if (c4 == b) {
                if (c3 == b) {
                    byte[] bArr4 = this.decodingTable;
                    byte b22 = bArr4[c];
                    byte b23 = bArr4[c2];
                    if ((b22 | b23) >= 0) {
                        outputStream.write((b22 << 3) | (b23 >> 2));
                        return 1;
                    }
                    throw new IOException("invalid characters encountered at end of base32 data");
                }
                throw new IOException("invalid characters encountered at end of base32 data");
            } else {
                byte[] bArr5 = this.decodingTable;
                byte b24 = bArr5[c];
                byte b25 = bArr5[c2];
                byte b26 = bArr5[c3];
                byte b27 = bArr5[c4];
                if ((b24 | b25 | b26 | b27) >= 0) {
                    outputStream.write((b24 << 3) | (b25 >> 2));
                    outputStream.write((b25 << 6) | (b26 << 1) | (b27 >> 4));
                    return 2;
                }
                throw new IOException("invalid characters encountered at end of base32 data");
            }
        } else {
            throw new IOException("invalid characters encountered at end of base32 data");
        }
    }

    private void encodeBlock(byte[] bArr, int r8, byte[] bArr2, int r10) {
        int r0 = r8 + 1;
        byte b = bArr[r8];
        int r1 = r0 + 1;
        int r02 = bArr[r0] & 255;
        int r2 = r1 + 1;
        int r12 = bArr[r1] & 255;
        int r3 = r2 + 1;
        int r22 = bArr[r2] & 255;
        int r7 = bArr[r3] & 255;
        int r32 = r10 + 1;
        byte[] bArr3 = this.encodingTable;
        bArr2[r10] = bArr3[(b >>> 3) & 31];
        int r102 = r32 + 1;
        bArr2[r32] = bArr3[((b << 2) | (r02 >>> 6)) & 31];
        int r82 = r102 + 1;
        bArr2[r102] = bArr3[(r02 >>> 1) & 31];
        int r103 = r82 + 1;
        bArr2[r82] = bArr3[((r02 << 4) | (r12 >>> 4)) & 31];
        int r83 = r103 + 1;
        bArr2[r103] = bArr3[((r12 << 1) | (r22 >>> 7)) & 31];
        int r104 = r83 + 1;
        bArr2[r83] = bArr3[(r22 >>> 2) & 31];
        bArr2[r104] = bArr3[((r22 << 3) | (r7 >>> 5)) & 31];
        bArr2[r104 + 1] = bArr3[r7 & 31];
    }

    private boolean ignore(char c) {
        return c == '\n' || c == '\r' || c == '\t' || c == ' ';
    }

    private int nextI(byte[] bArr, int r3, int r4) {
        while (r3 < r4 && ignore((char) bArr[r3])) {
            r3++;
        }
        return r3;
    }

    @Override // org.bouncycastle.util.encoders.Encoder
    public int decode(String str, OutputStream outputStream) throws IOException {
        byte[] byteArray = Strings.toByteArray(str);
        return decode(byteArray, 0, byteArray.length, outputStream);
    }

    @Override // org.bouncycastle.util.encoders.Encoder
    public int decode(byte[] bArr, int r21, int r22, OutputStream outputStream) throws IOException {
        byte[] bArr2 = new byte[55];
        int r5 = r21 + r22;
        while (r5 > r21 && ignore((char) bArr[r5 - 1])) {
            r5--;
        }
        if (r5 == 0) {
            return 0;
        }
        int r7 = r5;
        int r8 = 0;
        while (r7 > r21 && r8 != 8) {
            if (!ignore((char) bArr[r7 - 1])) {
                r8++;
            }
            r7--;
        }
        int nextI = nextI(bArr, r21, r7);
        int r82 = 0;
        int r11 = 0;
        while (nextI < r7) {
            int r12 = nextI + 1;
            byte b = this.decodingTable[bArr[nextI]];
            int nextI2 = nextI(bArr, r12, r7);
            int r13 = nextI2 + 1;
            byte b2 = this.decodingTable[bArr[nextI2]];
            int nextI3 = nextI(bArr, r13, r7);
            int r14 = nextI3 + 1;
            byte b3 = this.decodingTable[bArr[nextI3]];
            int nextI4 = nextI(bArr, r14, r7);
            int r15 = nextI4 + 1;
            byte b4 = this.decodingTable[bArr[nextI4]];
            int nextI5 = nextI(bArr, r15, r7);
            int r6 = nextI5 + 1;
            byte b5 = this.decodingTable[bArr[nextI5]];
            int nextI6 = nextI(bArr, r6, r7);
            int r3 = nextI6 + 1;
            byte b6 = this.decodingTable[bArr[nextI6]];
            int nextI7 = nextI(bArr, r3, r7);
            int r16 = r5;
            int r52 = nextI7 + 1;
            byte b7 = this.decodingTable[bArr[nextI7]];
            int nextI8 = nextI(bArr, r52, r7);
            int r17 = r7;
            int r72 = nextI8 + 1;
            byte b8 = this.decodingTable[bArr[nextI8]];
            if ((b | b2 | b3 | b4 | b5 | b6 | b7 | b8) < 0) {
                throw new IOException("invalid characters encountered in base32 data");
            }
            int r152 = r82 + 1;
            bArr2[r82] = (byte) ((b << 3) | (b2 >> 2));
            int r1 = r152 + 1;
            bArr2[r152] = (byte) ((b2 << 6) | (b3 << 1) | (b4 >> 4));
            int r83 = r1 + 1;
            bArr2[r1] = (byte) ((b4 << 4) | (b5 >> 1));
            int r18 = r83 + 1;
            bArr2[r83] = (byte) ((b6 << 2) | (b5 << 7) | (b7 >> 3));
            int r62 = r18 + 1;
            bArr2[r18] = (byte) ((b7 << 5) | b8);
            if (r62 == 55) {
                outputStream.write(bArr2);
                r82 = 0;
            } else {
                r82 = r62;
            }
            r11 += 5;
            nextI = nextI(bArr, r72, r17);
            r7 = r17;
            r5 = r16;
        }
        int r162 = r5;
        if (r82 > 0) {
            outputStream.write(bArr2, 0, r82);
        }
        int nextI9 = nextI(bArr, nextI, r162);
        int nextI10 = nextI(bArr, nextI9 + 1, r162);
        int nextI11 = nextI(bArr, nextI10 + 1, r162);
        int nextI12 = nextI(bArr, nextI11 + 1, r162);
        int nextI13 = nextI(bArr, nextI12 + 1, r162);
        int nextI14 = nextI(bArr, nextI13 + 1, r162);
        int nextI15 = nextI(bArr, nextI14 + 1, r162);
        return r11 + decodeLastBlock(outputStream, (char) bArr[nextI9], (char) bArr[nextI10], (char) bArr[nextI11], (char) bArr[nextI12], (char) bArr[nextI13], (char) bArr[nextI14], (char) bArr[nextI15], (char) bArr[nextI(bArr, nextI15 + 1, r162)]);
    }

    @Override // org.bouncycastle.util.encoders.Encoder
    public int encode(byte[] bArr, int r12, int r13, OutputStream outputStream) throws IOException {
        if (r13 < 0) {
            return 0;
        }
        byte[] bArr2 = new byte[72];
        int r8 = r13;
        while (r8 > 0) {
            int min = Math.min(45, r8);
            outputStream.write(bArr2, 0, encode(bArr, r12, min, bArr2, 0));
            r12 += min;
            r8 -= min;
        }
        return ((r13 + 2) / 3) * 4;
    }

    public int encode(byte[] bArr, int r6, int r7, byte[] bArr2, int r9) throws IOException {
        int r0 = (r6 + r7) - 4;
        int r2 = r6;
        int r3 = r9;
        while (r2 < r0) {
            encodeBlock(bArr, r2, bArr2, r3);
            r2 += 5;
            r3 += 8;
        }
        int r72 = r7 - (r2 - r6);
        if (r72 > 0) {
            byte[] bArr3 = new byte[5];
            System.arraycopy(bArr, r2, bArr3, 0, r72);
            encodeBlock(bArr3, 0, bArr2, r3);
            if (r72 == 1) {
                byte b = this.padding;
                bArr2[r3 + 2] = b;
                bArr2[r3 + 3] = b;
                bArr2[r3 + 4] = b;
                bArr2[r3 + 5] = b;
                bArr2[r3 + 6] = b;
                bArr2[r3 + 7] = b;
            } else if (r72 == 2) {
                byte b2 = this.padding;
                bArr2[r3 + 4] = b2;
                bArr2[r3 + 5] = b2;
                bArr2[r3 + 6] = b2;
                bArr2[r3 + 7] = b2;
            } else if (r72 == 3) {
                byte b3 = this.padding;
                bArr2[r3 + 5] = b3;
                bArr2[r3 + 6] = b3;
                bArr2[r3 + 7] = b3;
            } else if (r72 == 4) {
                bArr2[r3 + 7] = this.padding;
            }
            r3 += 8;
        }
        return r3 - r9;
    }

    @Override // org.bouncycastle.util.encoders.Encoder
    public int getEncodedLength(int r1) {
        return ((r1 + 4) / 5) * 8;
    }

    @Override // org.bouncycastle.util.encoders.Encoder
    public int getMaxDecodedLength(int r1) {
        return (r1 / 8) * 5;
    }

    protected void initialiseDecodingTable() {
        int r0 = 0;
        int r1 = 0;
        while (true) {
            byte[] bArr = this.decodingTable;
            if (r1 >= bArr.length) {
                break;
            }
            bArr[r1] = -1;
            r1++;
        }
        while (true) {
            byte[] bArr2 = this.encodingTable;
            if (r0 >= bArr2.length) {
                return;
            }
            this.decodingTable[bArr2[r0]] = (byte) r0;
            r0++;
        }
    }
}
