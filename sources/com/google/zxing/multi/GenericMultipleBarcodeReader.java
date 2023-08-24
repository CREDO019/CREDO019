package com.google.zxing.multi;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public final class GenericMultipleBarcodeReader implements MultipleBarcodeReader {
    private static final int MAX_DEPTH = 4;
    private static final int MIN_DIMENSION_TO_RECUR = 100;
    private final Reader delegate;

    public GenericMultipleBarcodeReader(Reader reader) {
        this.delegate = reader;
    }

    @Override // com.google.zxing.multi.MultipleBarcodeReader
    public Result[] decodeMultiple(BinaryBitmap binaryBitmap) throws NotFoundException {
        return decodeMultiple(binaryBitmap, null);
    }

    @Override // com.google.zxing.multi.MultipleBarcodeReader
    public Result[] decodeMultiple(BinaryBitmap binaryBitmap, Map<DecodeHintType, ?> map) throws NotFoundException {
        ArrayList arrayList = new ArrayList();
        doDecodeMultiple(binaryBitmap, map, arrayList, 0, 0, 0);
        if (arrayList.isEmpty()) {
            throw NotFoundException.getNotFoundInstance();
        }
        return (Result[]) arrayList.toArray(new Result[arrayList.size()]);
    }

    private void doDecodeMultiple(BinaryBitmap binaryBitmap, Map<DecodeHintType, ?> map, List<Result> list, int r26, int r27, int r28) {
        boolean z;
        float f;
        float f2;
        int r20;
        int r21;
        int r11;
        int r14;
        if (r28 > 4) {
            return;
        }
        try {
            Result decode = this.delegate.decode(binaryBitmap, map);
            Iterator<Result> it = list.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (it.next().getText().equals(decode.getText())) {
                        z = true;
                        break;
                    }
                } else {
                    z = false;
                    break;
                }
            }
            if (!z) {
                list.add(translateResultPoints(decode, r26, r27));
            }
            ResultPoint[] resultPoints = decode.getResultPoints();
            if (resultPoints == null || resultPoints.length == 0) {
                return;
            }
            int width = binaryBitmap.getWidth();
            int height = binaryBitmap.getHeight();
            float f3 = width;
            float f4 = height;
            float f5 = 0.0f;
            float f6 = 0.0f;
            for (ResultPoint resultPoint : resultPoints) {
                if (resultPoint != null) {
                    float x = resultPoint.getX();
                    float y = resultPoint.getY();
                    if (x < f3) {
                        f3 = x;
                    }
                    if (y < f4) {
                        f4 = y;
                    }
                    if (x > f5) {
                        f5 = x;
                    }
                    if (y > f6) {
                        f6 = y;
                    }
                }
            }
            if (f3 > 100.0f) {
                f = f5;
                f2 = f4;
                r20 = height;
                r21 = width;
                doDecodeMultiple(binaryBitmap.crop(0, 0, (int) f3, height), map, list, r26, r27, r28 + 1);
            } else {
                f = f5;
                f2 = f4;
                r20 = height;
                r21 = width;
            }
            if (f2 > 100.0f) {
                int r1 = (int) f2;
                r11 = r21;
                doDecodeMultiple(binaryBitmap.crop(0, 0, r11, r1), map, list, r26, r27, r28 + 1);
            } else {
                r11 = r21;
            }
            float f7 = f;
            if (f7 < r11 - 100) {
                int r12 = (int) f7;
                r14 = r20;
                doDecodeMultiple(binaryBitmap.crop(r12, 0, r11 - r12, r14), map, list, r26 + r12, r27, r28 + 1);
            } else {
                r14 = r20;
            }
            if (f6 < r14 - 100) {
                int r13 = (int) f6;
                doDecodeMultiple(binaryBitmap.crop(0, r13, r11, r14 - r13), map, list, r26, r27 + r13, r28 + 1);
            }
        } catch (ReaderException unused) {
        }
    }

    private static Result translateResultPoints(Result result, int r11, int r12) {
        ResultPoint[] resultPoints = result.getResultPoints();
        if (resultPoints == null) {
            return result;
        }
        ResultPoint[] resultPointArr = new ResultPoint[resultPoints.length];
        for (int r1 = 0; r1 < resultPoints.length; r1++) {
            ResultPoint resultPoint = resultPoints[r1];
            if (resultPoint != null) {
                resultPointArr[r1] = new ResultPoint(resultPoint.getX() + r11, resultPoint.getY() + r12);
            }
        }
        Result result2 = new Result(result.getText(), result.getRawBytes(), result.getNumBits(), resultPointArr, result.getBarcodeFormat(), result.getTimestamp());
        result2.putAllMetadata(result.getResultMetadata());
        return result2;
    }
}
