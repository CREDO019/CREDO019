package org.bouncycastle.util.encoders;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;

/* loaded from: classes4.dex */
public class HexEncoder implements Encoder {
    protected final byte[] encodingTable = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102};
    protected final byte[] decodingTable = new byte[128];

    public HexEncoder() {
        initialiseDecodingTable();
    }

    private static boolean ignore(char c) {
        return c == '\n' || c == '\r' || c == '\t' || c == ' ';
    }

    @Override // org.bouncycastle.util.encoders.Encoder
    public int decode(String str, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[36];
        int length = str.length();
        while (length > 0 && ignore(str.charAt(length - 1))) {
            length--;
        }
        int r4 = 0;
        int r5 = 0;
        int r6 = 0;
        while (r4 < length) {
            while (r4 < length && ignore(str.charAt(r4))) {
                r4++;
            }
            int r8 = r4 + 1;
            byte b = this.decodingTable[str.charAt(r4)];
            while (r8 < length && ignore(str.charAt(r8))) {
                r8++;
            }
            int r9 = r8 + 1;
            byte b2 = this.decodingTable[str.charAt(r8)];
            if ((b | b2) < 0) {
                throw new IOException("invalid characters encountered in Hex string");
            }
            int r82 = r5 + 1;
            bArr[r5] = (byte) ((b << 4) | b2);
            if (r82 == 36) {
                outputStream.write(bArr);
                r5 = 0;
            } else {
                r5 = r82;
            }
            r6++;
            r4 = r9;
        }
        if (r5 > 0) {
            outputStream.write(bArr, 0, r5);
        }
        return r6;
    }

    @Override // org.bouncycastle.util.encoders.Encoder
    public int decode(byte[] bArr, int r10, int r11, OutputStream outputStream) throws IOException {
        byte[] bArr2 = new byte[36];
        int r112 = r11 + r10;
        while (r112 > r10 && ignore((char) bArr[r112 - 1])) {
            r112--;
        }
        int r3 = 0;
        int r4 = 0;
        while (r10 < r112) {
            while (r10 < r112 && ignore((char) bArr[r10])) {
                r10++;
            }
            int r6 = r10 + 1;
            byte b = this.decodingTable[bArr[r10]];
            while (r6 < r112 && ignore((char) bArr[r6])) {
                r6++;
            }
            int r7 = r6 + 1;
            byte b2 = this.decodingTable[bArr[r6]];
            if ((b | b2) < 0) {
                throw new IOException("invalid characters encountered in Hex data");
            }
            int r62 = r3 + 1;
            bArr2[r3] = (byte) ((b << 4) | b2);
            if (r62 == 36) {
                outputStream.write(bArr2);
                r3 = 0;
            } else {
                r3 = r62;
            }
            r4++;
            r10 = r7;
        }
        if (r3 > 0) {
            outputStream.write(bArr2, 0, r3);
        }
        return r4;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] decodeStrict(String str, int r7, int r8) throws IOException {
        Objects.requireNonNull(str, "'str' cannot be null");
        if (r7 < 0 || r8 < 0 || r7 > str.length() - r8) {
            throw new IndexOutOfBoundsException("invalid offset and/or length specified");
        }
        if ((r8 & 1) == 0) {
            int r82 = r8 >>> 1;
            byte[] bArr = new byte[r82];
            int r1 = 0;
            while (r1 < r82) {
                int r3 = r7 + 1;
                int r4 = r3 + 1;
                int r72 = (this.decodingTable[str.charAt(r7)] << 4) | this.decodingTable[str.charAt(r3)];
                if (r72 < 0) {
                    throw new IOException("invalid characters encountered in Hex string");
                }
                bArr[r1] = (byte) r72;
                r1++;
                r7 = r4;
            }
            return bArr;
        }
        throw new IOException("a hexadecimal encoding must have an even number of characters");
    }

    @Override // org.bouncycastle.util.encoders.Encoder
    public int encode(byte[] bArr, int r12, int r13, OutputStream outputStream) throws IOException {
        if (r13 < 0) {
            return 0;
        }
        byte[] bArr2 = new byte[72];
        int r8 = r13;
        while (r8 > 0) {
            int min = Math.min(36, r8);
            outputStream.write(bArr2, 0, encode(bArr, r12, min, bArr2, 0));
            r12 += min;
            r8 -= min;
        }
        return r13 * 2;
    }

    public int encode(byte[] bArr, int r7, int r8, byte[] bArr2, int r10) throws IOException {
        int r82 = r8 + r7;
        int r0 = r10;
        while (r7 < r82) {
            int r1 = r7 + 1;
            int r72 = bArr[r7] & 255;
            int r2 = r0 + 1;
            byte[] bArr3 = this.encodingTable;
            bArr2[r0] = bArr3[r72 >>> 4];
            r0 = r2 + 1;
            bArr2[r2] = bArr3[r72 & 15];
            r7 = r1;
        }
        return r0 - r10;
    }

    @Override // org.bouncycastle.util.encoders.Encoder
    public int getEncodedLength(int r1) {
        return r1 * 2;
    }

    @Override // org.bouncycastle.util.encoders.Encoder
    public int getMaxDecodedLength(int r1) {
        return r1 / 2;
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
                byte[] bArr3 = this.decodingTable;
                bArr3[65] = bArr3[97];
                bArr3[66] = bArr3[98];
                bArr3[67] = bArr3[99];
                bArr3[68] = bArr3[100];
                bArr3[69] = bArr3[101];
                bArr3[70] = bArr3[102];
                return;
            }
            this.decodingTable[bArr2[r0]] = (byte) r0;
            r0++;
        }
    }
}
