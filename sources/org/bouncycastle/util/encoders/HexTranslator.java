package org.bouncycastle.util.encoders;

import com.google.common.base.Ascii;

/* loaded from: classes4.dex */
public class HexTranslator implements Translator {
    private static final byte[] hexTable = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102};

    @Override // org.bouncycastle.util.encoders.Translator
    public int decode(byte[] bArr, int r6, int r7, byte[] bArr2, int r9) {
        int r72 = r7 / 2;
        for (int r0 = 0; r0 < r72; r0++) {
            int r1 = (r0 * 2) + r6;
            byte b = bArr[r1];
            byte b2 = bArr[r1 + 1];
            if (b < 97) {
                bArr2[r9] = (byte) ((b - 48) << 4);
            } else {
                bArr2[r9] = (byte) (((b - 97) + 10) << 4);
            }
            if (b2 < 97) {
                bArr2[r9] = (byte) (bArr2[r9] + ((byte) (b2 - 48)));
            } else {
                bArr2[r9] = (byte) (bArr2[r9] + ((byte) ((b2 - 97) + 10)));
            }
            r9++;
        }
        return r72;
    }

    @Override // org.bouncycastle.util.encoders.Translator
    public int encode(byte[] bArr, int r7, int r8, byte[] bArr2, int r10) {
        int r0 = 0;
        int r1 = 0;
        while (r0 < r8) {
            int r2 = r10 + r1;
            byte[] bArr3 = hexTable;
            bArr2[r2] = bArr3[(bArr[r7] >> 4) & 15];
            bArr2[r2 + 1] = bArr3[bArr[r7] & Ascii.f1128SI];
            r7++;
            r0++;
            r1 += 2;
        }
        return r8 * 2;
    }

    @Override // org.bouncycastle.util.encoders.Translator
    public int getDecodedBlockSize() {
        return 1;
    }

    @Override // org.bouncycastle.util.encoders.Translator
    public int getEncodedBlockSize() {
        return 2;
    }
}
