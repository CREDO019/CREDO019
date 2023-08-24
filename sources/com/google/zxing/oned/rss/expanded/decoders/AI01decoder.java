package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.common.BitArray;

/* loaded from: classes3.dex */
abstract class AI01decoder extends AbstractExpandedDecoder {
    static final int GTIN_SIZE = 40;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AI01decoder(BitArray bitArray) {
        super(bitArray);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void encodeCompressedGtin(StringBuilder sb, int r4) {
        sb.append("(01)");
        int length = sb.length();
        sb.append('9');
        encodeCompressedGtinWithoutAI(sb, r4, length);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void encodeCompressedGtinWithoutAI(StringBuilder sb, int r6, int r7) {
        for (int r0 = 0; r0 < 4; r0++) {
            int extractNumericValueFromBitArray = getGeneralDecoder().extractNumericValueFromBitArray((r0 * 10) + r6, 10);
            if (extractNumericValueFromBitArray / 100 == 0) {
                sb.append('0');
            }
            if (extractNumericValueFromBitArray / 10 == 0) {
                sb.append('0');
            }
            sb.append(extractNumericValueFromBitArray);
        }
        appendCheckDigit(sb, r7);
    }

    private static void appendCheckDigit(StringBuilder sb, int r6) {
        int r2 = 0;
        for (int r1 = 0; r1 < 13; r1++) {
            int charAt = sb.charAt(r1 + r6) - '0';
            if ((r1 & 1) == 0) {
                charAt *= 3;
            }
            r2 += charAt;
        }
        int r12 = 10 - (r2 % 10);
        sb.append(r12 != 10 ? r12 : 0);
    }
}
