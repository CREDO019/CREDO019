package com.google.zxing.qrcode.decoder;

import com.google.zxing.common.BitMatrix;

/* loaded from: classes3.dex */
enum DataMask {
    DATA_MASK_000 { // from class: com.google.zxing.qrcode.decoder.DataMask.1
        @Override // com.google.zxing.qrcode.decoder.DataMask
        boolean isMasked(int r1, int r2) {
            return ((r1 + r2) & 1) == 0;
        }
    },
    DATA_MASK_001 { // from class: com.google.zxing.qrcode.decoder.DataMask.2
        @Override // com.google.zxing.qrcode.decoder.DataMask
        boolean isMasked(int r1, int r2) {
            return (r1 & 1) == 0;
        }
    },
    DATA_MASK_010 { // from class: com.google.zxing.qrcode.decoder.DataMask.3
        @Override // com.google.zxing.qrcode.decoder.DataMask
        boolean isMasked(int r1, int r2) {
            return r2 % 3 == 0;
        }
    },
    DATA_MASK_011 { // from class: com.google.zxing.qrcode.decoder.DataMask.4
        @Override // com.google.zxing.qrcode.decoder.DataMask
        boolean isMasked(int r1, int r2) {
            return (r1 + r2) % 3 == 0;
        }
    },
    DATA_MASK_100 { // from class: com.google.zxing.qrcode.decoder.DataMask.5
        @Override // com.google.zxing.qrcode.decoder.DataMask
        boolean isMasked(int r1, int r2) {
            return (((r1 / 2) + (r2 / 3)) & 1) == 0;
        }
    },
    DATA_MASK_101 { // from class: com.google.zxing.qrcode.decoder.DataMask.6
        @Override // com.google.zxing.qrcode.decoder.DataMask
        boolean isMasked(int r1, int r2) {
            return (r1 * r2) % 6 == 0;
        }
    },
    DATA_MASK_110 { // from class: com.google.zxing.qrcode.decoder.DataMask.7
        @Override // com.google.zxing.qrcode.decoder.DataMask
        boolean isMasked(int r1, int r2) {
            return (r1 * r2) % 6 < 3;
        }
    },
    DATA_MASK_111 { // from class: com.google.zxing.qrcode.decoder.DataMask.8
        @Override // com.google.zxing.qrcode.decoder.DataMask
        boolean isMasked(int r2, int r3) {
            return (((r2 + r3) + ((r2 * r3) % 3)) & 1) == 0;
        }
    };

    abstract boolean isMasked(int r1, int r2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void unmaskBitMatrix(BitMatrix bitMatrix, int r6) {
        for (int r1 = 0; r1 < r6; r1++) {
            for (int r2 = 0; r2 < r6; r2++) {
                if (isMasked(r1, r2)) {
                    bitMatrix.flip(r2, r1);
                }
            }
        }
    }
}
