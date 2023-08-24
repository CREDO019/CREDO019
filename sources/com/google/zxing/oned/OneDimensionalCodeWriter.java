package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.util.Map;

/* loaded from: classes3.dex */
public abstract class OneDimensionalCodeWriter implements Writer {
    public abstract boolean[] encode(String str);

    public int getDefaultMargin() {
        return 10;
    }

    @Override // com.google.zxing.Writer
    public final BitMatrix encode(String str, BarcodeFormat barcodeFormat, int r9, int r10) throws WriterException {
        return encode(str, barcodeFormat, r9, r10, null);
    }

    @Override // com.google.zxing.Writer
    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int r4, int r5, Map<EncodeHintType, ?> map) throws WriterException {
        if (str.isEmpty()) {
            throw new IllegalArgumentException("Found empty contents");
        }
        if (r4 < 0 || r5 < 0) {
            throw new IllegalArgumentException("Negative size is not allowed. Input: " + r4 + 'x' + r5);
        }
        int defaultMargin = getDefaultMargin();
        if (map != null && map.containsKey(EncodeHintType.MARGIN)) {
            defaultMargin = Integer.parseInt(map.get(EncodeHintType.MARGIN).toString());
        }
        return renderResult(encode(str), r4, r5, defaultMargin);
    }

    private static BitMatrix renderResult(boolean[] zArr, int r6, int r7, int r8) {
        int length = zArr.length;
        int r82 = r8 + length;
        int max = Math.max(r6, r82);
        int max2 = Math.max(1, r7);
        int r83 = max / r82;
        int r1 = (max - (length * r83)) / 2;
        BitMatrix bitMatrix = new BitMatrix(max, max2);
        int r3 = 0;
        while (r3 < length) {
            if (zArr[r3]) {
                bitMatrix.setRegion(r1, 0, r83, max2);
            }
            r3++;
            r1 += r83;
        }
        return bitMatrix;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static int appendPattern(boolean[] zArr, int r8, int[] r9, boolean z) {
        int r3 = 0;
        for (int r4 : r9) {
            int r5 = 0;
            while (r5 < r4) {
                zArr[r8] = z;
                r5++;
                r8++;
            }
            r3 += r4;
            z = !z;
        }
        return r3;
    }
}
