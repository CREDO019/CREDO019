package com.google.zxing.oned;

import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.util.Map;

/* loaded from: classes3.dex */
public final class UPCAWriter implements Writer {
    private final EAN13Writer subWriter = new EAN13Writer();

    @Override // com.google.zxing.Writer
    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int r9, int r10) throws WriterException {
        return encode(str, barcodeFormat, r9, r10, null);
    }

    @Override // com.google.zxing.Writer
    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int r10, int r11, Map<EncodeHintType, ?> map) throws WriterException {
        if (barcodeFormat != BarcodeFormat.UPC_A) {
            throw new IllegalArgumentException("Can only encode UPC-A, but got ".concat(String.valueOf(barcodeFormat)));
        }
        return this.subWriter.encode(SessionDescription.SUPPORTED_SDP_VERSION.concat(String.valueOf(str)), BarcodeFormat.EAN_13, r10, r11, map);
    }
}
