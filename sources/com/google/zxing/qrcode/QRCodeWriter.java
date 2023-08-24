package com.google.zxing.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.encoder.ByteMatrix;
import com.google.zxing.qrcode.encoder.Encoder;
import com.google.zxing.qrcode.encoder.QRCode;
import java.util.Map;

/* loaded from: classes3.dex */
public final class QRCodeWriter implements Writer {
    private static final int QUIET_ZONE_SIZE = 4;

    @Override // com.google.zxing.Writer
    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int r9, int r10) throws WriterException {
        return encode(str, barcodeFormat, r9, r10, null);
    }

    @Override // com.google.zxing.Writer
    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int r5, int r6, Map<EncodeHintType, ?> map) throws WriterException {
        if (str.isEmpty()) {
            throw new IllegalArgumentException("Found empty contents");
        }
        if (barcodeFormat == BarcodeFormat.QR_CODE) {
            if (r5 < 0 || r6 < 0) {
                throw new IllegalArgumentException("Requested dimensions are too small: " + r5 + 'x' + r6);
            }
            ErrorCorrectionLevel errorCorrectionLevel = ErrorCorrectionLevel.L;
            int r0 = 4;
            if (map != null) {
                if (map.containsKey(EncodeHintType.ERROR_CORRECTION)) {
                    errorCorrectionLevel = ErrorCorrectionLevel.valueOf(map.get(EncodeHintType.ERROR_CORRECTION).toString());
                }
                if (map.containsKey(EncodeHintType.MARGIN)) {
                    r0 = Integer.parseInt(map.get(EncodeHintType.MARGIN).toString());
                }
            }
            return renderResult(Encoder.encode(str, errorCorrectionLevel, map), r5, r6, r0);
        }
        throw new IllegalArgumentException("Can only encode QR_CODE, but got ".concat(String.valueOf(barcodeFormat)));
    }

    private static BitMatrix renderResult(QRCode qRCode, int r10, int r11, int r12) {
        ByteMatrix matrix = qRCode.getMatrix();
        if (matrix == null) {
            throw new IllegalStateException();
        }
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        int r122 = r12 << 1;
        int r3 = width + r122;
        int r123 = r122 + height;
        int max = Math.max(r10, r3);
        int max2 = Math.max(r11, r123);
        int min = Math.min(max / r3, max2 / r123);
        int r32 = (max - (width * min)) / 2;
        int r4 = (max2 - (height * min)) / 2;
        BitMatrix bitMatrix = new BitMatrix(max, max2);
        int r112 = 0;
        while (r112 < height) {
            int r7 = r32;
            int r6 = 0;
            while (r6 < width) {
                if (matrix.get(r6, r112) == 1) {
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
