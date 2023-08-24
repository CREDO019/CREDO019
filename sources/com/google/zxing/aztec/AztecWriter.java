package com.google.zxing.aztec;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Writer;
import com.google.zxing.aztec.encoder.AztecCode;
import com.google.zxing.aztec.encoder.Encoder;
import com.google.zxing.common.BitMatrix;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/* loaded from: classes3.dex */
public final class AztecWriter implements Writer {
    @Override // com.google.zxing.Writer
    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int r9, int r10) {
        return encode(str, barcodeFormat, r9, r10, null);
    }

    @Override // com.google.zxing.Writer
    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int r13, int r14, Map<EncodeHintType, ?> map) {
        Charset charset;
        int r8;
        int r9;
        Charset charset2 = StandardCharsets.ISO_8859_1;
        if (map != null) {
            if (map.containsKey(EncodeHintType.CHARACTER_SET)) {
                charset2 = Charset.forName(map.get(EncodeHintType.CHARACTER_SET).toString());
            }
            int parseInt = map.containsKey(EncodeHintType.ERROR_CORRECTION) ? Integer.parseInt(map.get(EncodeHintType.ERROR_CORRECTION).toString()) : 33;
            if (map.containsKey(EncodeHintType.AZTEC_LAYERS)) {
                charset = charset2;
                r8 = parseInt;
                r9 = Integer.parseInt(map.get(EncodeHintType.AZTEC_LAYERS).toString());
                return encode(str, barcodeFormat, r13, r14, charset, r8, r9);
            }
            charset = charset2;
            r8 = parseInt;
        } else {
            charset = charset2;
            r8 = 33;
        }
        r9 = 0;
        return encode(str, barcodeFormat, r13, r14, charset, r8, r9);
    }

    private static BitMatrix encode(String str, BarcodeFormat barcodeFormat, int r3, int r4, Charset charset, int r6, int r7) {
        if (barcodeFormat != BarcodeFormat.AZTEC) {
            throw new IllegalArgumentException("Can only encode AZTEC, but got ".concat(String.valueOf(barcodeFormat)));
        }
        return renderResult(Encoder.encode(str.getBytes(charset), r6, r7), r3, r4);
    }

    private static BitMatrix renderResult(AztecCode aztecCode, int r10, int r11) {
        BitMatrix matrix = aztecCode.getMatrix();
        if (matrix == null) {
            throw new IllegalStateException();
        }
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        int max = Math.max(r10, width);
        int max2 = Math.max(r11, height);
        int min = Math.min(max / width, max2 / height);
        int r3 = (max - (width * min)) / 2;
        int r4 = (max2 - (height * min)) / 2;
        BitMatrix bitMatrix = new BitMatrix(max, max2);
        int r112 = 0;
        while (r112 < height) {
            int r7 = r3;
            int r6 = 0;
            while (r6 < width) {
                if (matrix.get(r6, r112)) {
                    bitMatrix.setRegion(r7, r4, min, min);
                }
                r6++;
                r7 += min;
            }
            r112++;
            r4 += min;
        }
        return bitMatrix;
    }
}
