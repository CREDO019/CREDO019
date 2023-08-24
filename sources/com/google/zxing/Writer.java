package com.google.zxing;

import com.google.zxing.common.BitMatrix;
import java.util.Map;

/* loaded from: classes3.dex */
public interface Writer {
    BitMatrix encode(String str, BarcodeFormat barcodeFormat, int r3, int r4) throws WriterException;

    BitMatrix encode(String str, BarcodeFormat barcodeFormat, int r3, int r4, Map<EncodeHintType, ?> map) throws WriterException;
}
