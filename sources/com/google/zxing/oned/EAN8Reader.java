package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

/* loaded from: classes3.dex */
public final class EAN8Reader extends UPCEANReader {
    private final int[] decodeMiddleCounters = new int[4];

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.zxing.oned.UPCEANReader
    public int decodeMiddle(BitArray bitArray, int[] r10, StringBuilder sb) throws NotFoundException {
        int[] r0 = this.decodeMiddleCounters;
        r0[0] = 0;
        r0[1] = 0;
        r0[2] = 0;
        r0[3] = 0;
        int size = bitArray.getSize();
        int r102 = r10[1];
        for (int r4 = 0; r4 < 4 && r102 < size; r4++) {
            sb.append((char) (decodeDigit(bitArray, r0, r102, L_PATTERNS) + 48));
            for (int r7 : r0) {
                r102 += r7;
            }
        }
        int r103 = findGuardPattern(bitArray, r102, true, MIDDLE_PATTERN)[1];
        for (int r2 = 0; r2 < 4 && r103 < size; r2++) {
            sb.append((char) (decodeDigit(bitArray, r0, r103, L_PATTERNS) + 48));
            for (int r72 : r0) {
                r103 += r72;
            }
        }
        return r103;
    }

    @Override // com.google.zxing.oned.UPCEANReader
    BarcodeFormat getBarcodeFormat() {
        return BarcodeFormat.EAN_8;
    }
}
