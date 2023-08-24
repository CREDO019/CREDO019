package com.google.zxing.oned.rss.expanded;

import com.google.zxing.common.BitArray;
import java.util.List;

/* loaded from: classes3.dex */
final class BitArrayBuilder {
    private BitArrayBuilder() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static BitArray buildBitArray(List<ExpandedPair> list) {
        int size = (list.size() << 1) - 1;
        if (list.get(list.size() - 1).getRightChar() == null) {
            size--;
        }
        BitArray bitArray = new BitArray(size * 12);
        int r0 = 0;
        int value = list.get(0).getRightChar().getValue();
        for (int r5 = 11; r5 >= 0; r5--) {
            if (((1 << r5) & value) != 0) {
                bitArray.set(r0);
            }
            r0++;
        }
        for (int r3 = 1; r3 < list.size(); r3++) {
            ExpandedPair expandedPair = list.get(r3);
            int value2 = expandedPair.getLeftChar().getValue();
            for (int r7 = 11; r7 >= 0; r7--) {
                if (((1 << r7) & value2) != 0) {
                    bitArray.set(r0);
                }
                r0++;
            }
            if (expandedPair.getRightChar() != null) {
                int value3 = expandedPair.getRightChar().getValue();
                for (int r6 = 11; r6 >= 0; r6--) {
                    if (((1 << r6) & value3) != 0) {
                        bitArray.set(r0);
                    }
                    r0++;
                }
            }
        }
        return bitArray;
    }
}
