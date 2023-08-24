package com.google.zxing.oned;

import com.canhub.cropper.CropImageOptions;
import com.facebook.imagepipeline.common.RotationOptions;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

/* loaded from: classes3.dex */
public abstract class OneDReader implements Reader {
    public abstract Result decodeRow(int r1, BitArray bitArray, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException;

    @Override // com.google.zxing.Reader
    public void reset() {
    }

    @Override // com.google.zxing.Reader
    public Result decode(BinaryBitmap binaryBitmap) throws NotFoundException, FormatException {
        return decode(binaryBitmap, null);
    }

    @Override // com.google.zxing.Reader
    public Result decode(BinaryBitmap binaryBitmap, Map<DecodeHintType, ?> map) throws NotFoundException, FormatException {
        try {
            return doDecode(binaryBitmap, map);
        } catch (NotFoundException e) {
            if ((map != null && map.containsKey(DecodeHintType.TRY_HARDER)) && binaryBitmap.isRotateSupported()) {
                BinaryBitmap rotateCounterClockwise = binaryBitmap.rotateCounterClockwise();
                Result doDecode = doDecode(rotateCounterClockwise, map);
                Map<ResultMetadataType, Object> resultMetadata = doDecode.getResultMetadata();
                int r2 = 270;
                if (resultMetadata != null && resultMetadata.containsKey(ResultMetadataType.ORIENTATION)) {
                    r2 = (((Integer) resultMetadata.get(ResultMetadataType.ORIENTATION)).intValue() + 270) % CropImageOptions.DEGREES_360;
                }
                doDecode.putMetadata(ResultMetadataType.ORIENTATION, Integer.valueOf(r2));
                ResultPoint[] resultPoints = doDecode.getResultPoints();
                if (resultPoints != null) {
                    int height = rotateCounterClockwise.getHeight();
                    for (int r1 = 0; r1 < resultPoints.length; r1++) {
                        resultPoints[r1] = new ResultPoint((height - resultPoints[r1].getY()) - 1.0f, resultPoints[r1].getX());
                    }
                }
                return doDecode;
            }
            throw e;
        }
    }

    private Result doDecode(BinaryBitmap binaryBitmap, Map<DecodeHintType, ?> map) throws NotFoundException {
        Map<DecodeHintType, ?> map2;
        int r20;
        EnumMap enumMap = map;
        int width = binaryBitmap.getWidth();
        int height = binaryBitmap.getHeight();
        BitArray bitArray = new BitArray(width);
        char c = 0;
        int r5 = 1;
        boolean z = enumMap != null && enumMap.containsKey(DecodeHintType.TRY_HARDER);
        int max = Math.max(1, height >> (z ? 8 : 5));
        int r6 = z ? height : 15;
        int r8 = height / 2;
        int r9 = 0;
        while (r9 < r6) {
            int r10 = r9 + 1;
            int r11 = r10 / 2;
            if (!((r9 & 1) == 0)) {
                r11 = -r11;
            }
            int r112 = (r11 * max) + r8;
            if (r112 < 0 || r112 >= height) {
                break;
            }
            try {
                bitArray = binaryBitmap.getBlackRow(r112, bitArray);
                int r12 = 0;
                while (r12 < 2) {
                    if (r12 == r5) {
                        bitArray.reverse();
                        if (enumMap != null && enumMap.containsKey(DecodeHintType.NEED_RESULT_POINT_CALLBACK)) {
                            EnumMap enumMap2 = new EnumMap(DecodeHintType.class);
                            enumMap2.putAll(enumMap);
                            enumMap2.remove(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
                            enumMap = enumMap2;
                        }
                    }
                    try {
                        Result decodeRow = decodeRow(r112, bitArray, enumMap);
                        if (r12 == r5) {
                            decodeRow.putMetadata(ResultMetadataType.ORIENTATION, Integer.valueOf((int) RotationOptions.ROTATE_180));
                            ResultPoint[] resultPoints = decodeRow.getResultPoints();
                            if (resultPoints != null) {
                                map2 = enumMap;
                                float f = width;
                                try {
                                    r20 = width;
                                    try {
                                        resultPoints[0] = new ResultPoint((f - resultPoints[c].getX()) - 1.0f, resultPoints[c].getY());
                                    } catch (ReaderException unused) {
                                        r12++;
                                        enumMap = map2;
                                        width = r20;
                                        c = 0;
                                        r5 = 1;
                                    }
                                    try {
                                        resultPoints[1] = new ResultPoint((f - resultPoints[1].getX()) - 1.0f, resultPoints[1].getY());
                                    } catch (ReaderException unused2) {
                                        continue;
                                        r12++;
                                        enumMap = map2;
                                        width = r20;
                                        c = 0;
                                        r5 = 1;
                                    }
                                } catch (ReaderException unused3) {
                                    r20 = width;
                                    r12++;
                                    enumMap = map2;
                                    width = r20;
                                    c = 0;
                                    r5 = 1;
                                }
                            }
                        }
                        return decodeRow;
                    } catch (ReaderException unused4) {
                        map2 = enumMap;
                    }
                }
                continue;
            } catch (NotFoundException unused5) {
            }
            r9 = r10;
            width = width;
            c = 0;
            r5 = 1;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void recordPattern(BitArray bitArray, int r7, int[] r8) throws NotFoundException {
        int length = r8.length;
        int r1 = 0;
        Arrays.fill(r8, 0, length, 0);
        int size = bitArray.getSize();
        if (r7 >= size) {
            throw NotFoundException.getNotFoundInstance();
        }
        boolean z = !bitArray.get(r7);
        while (r7 < size) {
            if (bitArray.get(r7) == z) {
                r1++;
                if (r1 == length) {
                    break;
                }
                r8[r1] = 1;
                z = !z;
            } else {
                r8[r1] = r8[r1] + 1;
            }
            r7++;
        }
        if (r1 != length) {
            if (r1 != length - 1 || r7 != size) {
                throw NotFoundException.getNotFoundInstance();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void recordPatternInReverse(BitArray bitArray, int r4, int[] r5) throws NotFoundException {
        int length = r5.length;
        boolean z = bitArray.get(r4);
        while (r4 > 0 && length >= 0) {
            r4--;
            if (bitArray.get(r4) != z) {
                length--;
                z = !z;
            }
        }
        if (length >= 0) {
            throw NotFoundException.getNotFoundInstance();
        }
        recordPattern(bitArray, r4 + 1, r5);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static float patternMatchVariance(int[] r9, int[] r10, float f) {
        int length = r9.length;
        int r3 = 0;
        int r4 = 0;
        for (int r2 = 0; r2 < length; r2++) {
            r3 += r9[r2];
            r4 += r10[r2];
        }
        if (r3 < r4) {
            return Float.POSITIVE_INFINITY;
        }
        float f2 = r3;
        float f3 = f2 / r4;
        float f4 = f * f3;
        float f5 = 0.0f;
        for (int r1 = 0; r1 < length; r1++) {
            float f6 = r10[r1] * f3;
            float f7 = r9[r1];
            float f8 = f7 > f6 ? f7 - f6 : f6 - f7;
            if (f8 > f4) {
                return Float.POSITIVE_INFINITY;
            }
            f5 += f8;
        }
        return f5 / f2;
    }
}
