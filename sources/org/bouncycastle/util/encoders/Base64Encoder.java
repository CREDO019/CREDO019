package org.bouncycastle.util.encoders;

import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes4.dex */
public class Base64Encoder implements Encoder {
    protected final byte[] encodingTable = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};
    protected byte padding = 61;
    protected final byte[] decodingTable = new byte[128];

    public Base64Encoder() {
        initialiseDecodingTable();
    }

    private int decodeLastBlock(OutputStream outputStream, char c, char c2, char c3, char c4) throws IOException {
        byte b = this.padding;
        if (c3 == b) {
            if (c4 == b) {
                byte[] bArr = this.decodingTable;
                byte b2 = bArr[c];
                byte b3 = bArr[c2];
                if ((b2 | b3) >= 0) {
                    outputStream.write((b2 << 2) | (b3 >> 4));
                    return 1;
                }
                throw new IOException("invalid characters encountered at end of base64 data");
            }
            throw new IOException("invalid characters encountered at end of base64 data");
        } else if (c4 == b) {
            byte[] bArr2 = this.decodingTable;
            byte b4 = bArr2[c];
            byte b5 = bArr2[c2];
            byte b6 = bArr2[c3];
            if ((b4 | b5 | b6) >= 0) {
                outputStream.write((b4 << 2) | (b5 >> 4));
                outputStream.write((b5 << 4) | (b6 >> 2));
                return 2;
            }
            throw new IOException("invalid characters encountered at end of base64 data");
        } else {
            byte[] bArr3 = this.decodingTable;
            byte b7 = bArr3[c];
            byte b8 = bArr3[c2];
            byte b9 = bArr3[c3];
            byte b10 = bArr3[c4];
            if ((b7 | b8 | b9 | b10) >= 0) {
                outputStream.write((b7 << 2) | (b8 >> 4));
                outputStream.write((b8 << 4) | (b9 >> 2));
                outputStream.write((b9 << 6) | b10);
                return 3;
            }
            throw new IOException("invalid characters encountered at end of base64 data");
        }
    }

    private boolean ignore(char c) {
        return c == '\n' || c == '\r' || c == '\t' || c == ' ';
    }

    private int nextI(String str, int r3, int r4) {
        while (r3 < r4 && ignore(str.charAt(r3))) {
            r3++;
        }
        return r3;
    }

    private int nextI(byte[] bArr, int r3, int r4) {
        while (r3 < r4 && ignore((char) bArr[r3])) {
            r3++;
        }
        return r3;
    }

    @Override // org.bouncycastle.util.encoders.Encoder
    public int decode(String str, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[54];
        int length = str.length();
        while (length > 0 && ignore(str.charAt(length - 1))) {
            length--;
        }
        if (length == 0) {
            return 0;
        }
        int r7 = length;
        int r8 = 0;
        while (r7 > 0 && r8 != 4) {
            if (!ignore(str.charAt(r7 - 1))) {
                r8++;
            }
            r7--;
        }
        int nextI = nextI(str, 0, r7);
        int r9 = 0;
        int r10 = 0;
        while (nextI < r7) {
            int r12 = nextI + 1;
            byte b = this.decodingTable[str.charAt(nextI)];
            int nextI2 = nextI(str, r12, r7);
            int r13 = nextI2 + 1;
            byte b2 = this.decodingTable[str.charAt(nextI2)];
            int nextI3 = nextI(str, r13, r7);
            int r14 = nextI3 + 1;
            byte b3 = this.decodingTable[str.charAt(nextI3)];
            int nextI4 = nextI(str, r14, r7);
            int r15 = nextI4 + 1;
            byte b4 = this.decodingTable[str.charAt(nextI4)];
            if ((b | b2 | b3 | b4) < 0) {
                throw new IOException("invalid characters encountered in base64 data");
            }
            int r142 = r9 + 1;
            bArr[r9] = (byte) ((b << 2) | (b2 >> 4));
            int r82 = r142 + 1;
            bArr[r142] = (byte) ((b2 << 4) | (b3 >> 2));
            r9 = r82 + 1;
            bArr[r82] = (byte) ((b3 << 6) | b4);
            r10 += 3;
            if (r9 == 54) {
                outputStream.write(bArr);
                r9 = 0;
            }
            nextI = nextI(str, r15, r7);
        }
        if (r9 > 0) {
            outputStream.write(bArr, 0, r9);
        }
        int nextI5 = nextI(str, nextI, length);
        int nextI6 = nextI(str, nextI5 + 1, length);
        int nextI7 = nextI(str, nextI6 + 1, length);
        return r10 + decodeLastBlock(outputStream, str.charAt(nextI5), str.charAt(nextI6), str.charAt(nextI7), str.charAt(nextI(str, nextI7 + 1, length)));
    }

    @Override // org.bouncycastle.util.encoders.Encoder
    public int decode(byte[] bArr, int r19, int r20, OutputStream outputStream) throws IOException {
        byte[] bArr2 = new byte[54];
        int r5 = r19 + r20;
        while (r5 > r19 && ignore((char) bArr[r5 - 1])) {
            r5--;
        }
        if (r5 == 0) {
            return 0;
        }
        int r8 = r5;
        int r9 = 0;
        while (r8 > r19 && r9 != 4) {
            if (!ignore((char) bArr[r8 - 1])) {
                r9++;
            }
            r8--;
        }
        int nextI = nextI(bArr, r19, r8);
        int r92 = 0;
        int r10 = 0;
        while (nextI < r8) {
            int r12 = nextI + 1;
            byte b = this.decodingTable[bArr[nextI]];
            int nextI2 = nextI(bArr, r12, r8);
            int r13 = nextI2 + 1;
            byte b2 = this.decodingTable[bArr[nextI2]];
            int nextI3 = nextI(bArr, r13, r8);
            int r14 = nextI3 + 1;
            byte b3 = this.decodingTable[bArr[nextI3]];
            int nextI4 = nextI(bArr, r14, r8);
            int r15 = nextI4 + 1;
            byte b4 = this.decodingTable[bArr[nextI4]];
            if ((b | b2 | b3 | b4) < 0) {
                throw new IOException("invalid characters encountered in base64 data");
            }
            int r142 = r92 + 1;
            bArr2[r92] = (byte) ((b << 2) | (b2 >> 4));
            int r1 = r142 + 1;
            bArr2[r142] = (byte) ((b2 << 4) | (b3 >> 2));
            r92 = r1 + 1;
            bArr2[r1] = (byte) ((b3 << 6) | b4);
            if (r92 == 54) {
                outputStream.write(bArr2);
                r92 = 0;
            }
            r10 += 3;
            nextI = nextI(bArr, r15, r8);
        }
        if (r92 > 0) {
            outputStream.write(bArr2, 0, r92);
        }
        int nextI5 = nextI(bArr, nextI, r5);
        int nextI6 = nextI(bArr, nextI5 + 1, r5);
        int nextI7 = nextI(bArr, nextI6 + 1, r5);
        return r10 + decodeLastBlock(outputStream, (char) bArr[nextI5], (char) bArr[nextI6], (char) bArr[nextI7], (char) bArr[nextI(bArr, nextI7 + 1, r5)]);
    }

    @Override // org.bouncycastle.util.encoders.Encoder
    public int encode(byte[] bArr, int r12, int r13, OutputStream outputStream) throws IOException {
        if (r13 < 0) {
            return 0;
        }
        byte[] bArr2 = new byte[72];
        int r8 = r13;
        while (r8 > 0) {
            int min = Math.min(54, r8);
            outputStream.write(bArr2, 0, encode(bArr, r12, min, bArr2, 0));
            r12 += min;
            r8 -= min;
        }
        return ((r13 + 2) / 3) * 4;
    }

    public int encode(byte[] bArr, int r12, int r13, byte[] bArr2, int r15) throws IOException {
        int r0 = (r12 + r13) - 2;
        int r2 = r12;
        int r3 = r15;
        while (r2 < r0) {
            int r4 = r2 + 1;
            byte b = bArr[r2];
            int r5 = r4 + 1;
            int r42 = bArr[r4] & 255;
            int r6 = r5 + 1;
            int r52 = bArr[r5] & 255;
            int r7 = r3 + 1;
            byte[] bArr3 = this.encodingTable;
            bArr2[r3] = bArr3[(b >>> 2) & 63];
            int r32 = r7 + 1;
            bArr2[r7] = bArr3[((b << 4) | (r42 >>> 4)) & 63];
            int r22 = r32 + 1;
            bArr2[r32] = bArr3[((r42 << 2) | (r52 >>> 6)) & 63];
            r3 = r22 + 1;
            bArr2[r22] = bArr3[r52 & 63];
            r2 = r6;
        }
        int r132 = r13 - (r2 - r12);
        if (r132 == 1) {
            int r11 = bArr[r2] & 255;
            int r122 = r3 + 1;
            byte[] bArr4 = this.encodingTable;
            bArr2[r3] = bArr4[(r11 >>> 2) & 63];
            int r02 = r122 + 1;
            bArr2[r122] = bArr4[(r11 << 4) & 63];
            int r112 = r02 + 1;
            byte b2 = this.padding;
            bArr2[r02] = b2;
            r3 = r112 + 1;
            bArr2[r112] = b2;
        } else if (r132 == 2) {
            int r133 = bArr[r2] & 255;
            int r113 = bArr[r2 + 1] & 255;
            int r123 = r3 + 1;
            byte[] bArr5 = this.encodingTable;
            bArr2[r3] = bArr5[(r133 >>> 2) & 63];
            int r23 = r123 + 1;
            bArr2[r123] = bArr5[((r133 << 4) | (r113 >>> 4)) & 63];
            int r124 = r23 + 1;
            bArr2[r23] = bArr5[(r113 << 2) & 63];
            r3 = r124 + 1;
            bArr2[r124] = this.padding;
        }
        return r3 - r15;
    }

    @Override // org.bouncycastle.util.encoders.Encoder
    public int getEncodedLength(int r1) {
        return ((r1 + 2) / 3) * 4;
    }

    @Override // org.bouncycastle.util.encoders.Encoder
    public int getMaxDecodedLength(int r1) {
        return (r1 / 4) * 3;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void initialiseDecodingTable() {
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
