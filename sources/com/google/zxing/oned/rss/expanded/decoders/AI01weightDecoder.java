package com.google.zxing.oned.rss.expanded.decoders;

import com.google.android.exoplayer2.audio.AacUtil;
import com.google.zxing.common.BitArray;

/* loaded from: classes3.dex */
abstract class AI01weightDecoder extends AI01decoder {
    protected abstract void addWeightCode(StringBuilder sb, int r2);

    protected abstract int checkWeight(int r1);

    /* JADX INFO: Access modifiers changed from: package-private */
    public AI01weightDecoder(BitArray bitArray) {
        super(bitArray);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void encodeCompressedWeight(StringBuilder sb, int r4, int r5) {
        int extractNumericValueFromBitArray = getGeneralDecoder().extractNumericValueFromBitArray(r4, r5);
        addWeightCode(sb, extractNumericValueFromBitArray);
        int checkWeight = checkWeight(extractNumericValueFromBitArray);
        int r52 = AacUtil.AAC_LC_MAX_RATE_BYTES_PER_SECOND;
        for (int r0 = 0; r0 < 5; r0++) {
            if (checkWeight / r52 == 0) {
                sb.append('0');
            }
            r52 /= 10;
        }
        sb.append(checkWeight);
    }
}
