package com.google.zxing.datamatrix.decoder;

import com.google.zxing.datamatrix.decoder.Version;

/* loaded from: classes3.dex */
final class DataBlock {
    private final byte[] codewords;
    private final int numDataCodewords;

    private DataBlock(int r1, byte[] bArr) {
        this.numDataCodewords = r1;
        this.codewords = bArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static DataBlock[] getDataBlocks(byte[] bArr, Version version) {
        Version.ECBlocks eCBlocks = version.getECBlocks();
        Version.ECB[] eCBlocks2 = eCBlocks.getECBlocks();
        int r5 = 0;
        for (Version.ECB ecb : eCBlocks2) {
            r5 += ecb.getCount();
        }
        DataBlock[] dataBlockArr = new DataBlock[r5];
        int r6 = 0;
        for (Version.ECB ecb2 : eCBlocks2) {
            int r8 = 0;
            while (r8 < ecb2.getCount()) {
                int dataCodewords = ecb2.getDataCodewords();
                dataBlockArr[r6] = new DataBlock(dataCodewords, new byte[eCBlocks.getECCodewords() + dataCodewords]);
                r8++;
                r6++;
            }
        }
        int length = dataBlockArr[0].codewords.length - eCBlocks.getECCodewords();
        int r0 = length - 1;
        int r52 = 0;
        for (int r4 = 0; r4 < r0; r4++) {
            int r7 = 0;
            while (r7 < r6) {
                dataBlockArr[r7].codewords[r4] = bArr[r52];
                r7++;
                r52++;
            }
        }
        boolean z = version.getVersionNumber() == 24;
        int r42 = z ? 8 : r6;
        int r72 = 0;
        while (r72 < r42) {
            dataBlockArr[r72].codewords[r0] = bArr[r52];
            r72++;
            r52++;
        }
        int length2 = dataBlockArr[0].codewords.length;
        while (length < length2) {
            int r43 = 0;
            while (r43 < r6) {
                int r73 = z ? (r43 + 8) % r6 : r43;
                dataBlockArr[r73].codewords[(!z || r73 <= 7) ? length : length - 1] = bArr[r52];
                r43++;
                r52++;
            }
            length++;
        }
        if (r52 == bArr.length) {
            return dataBlockArr;
        }
        throw new IllegalArgumentException();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getNumDataCodewords() {
        return this.numDataCodewords;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] getCodewords() {
        return this.codewords;
    }
}
