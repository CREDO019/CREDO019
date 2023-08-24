package com.google.zxing.datamatrix;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.DetectorResult;
import com.google.zxing.datamatrix.decoder.Decoder;
import com.google.zxing.datamatrix.detector.Detector;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public final class DataMatrixReader implements Reader {
    private static final ResultPoint[] NO_POINTS = new ResultPoint[0];
    private final Decoder decoder = new Decoder();

    @Override // com.google.zxing.Reader
    public void reset() {
    }

    @Override // com.google.zxing.Reader
    public Result decode(BinaryBitmap binaryBitmap) throws NotFoundException, ChecksumException, FormatException {
        return decode(binaryBitmap, null);
    }

    @Override // com.google.zxing.Reader
    public Result decode(BinaryBitmap binaryBitmap, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException {
        ResultPoint[] points;
        DecoderResult decoderResult;
        if (map != null && map.containsKey(DecodeHintType.PURE_BARCODE)) {
            decoderResult = this.decoder.decode(extractPureBits(binaryBitmap.getBlackMatrix()));
            points = NO_POINTS;
        } else {
            DetectorResult detect = new Detector(binaryBitmap.getBlackMatrix()).detect();
            DecoderResult decode = this.decoder.decode(detect.getBits());
            points = detect.getPoints();
            decoderResult = decode;
        }
        Result result = new Result(decoderResult.getText(), decoderResult.getRawBytes(), points, BarcodeFormat.DATA_MATRIX);
        List<byte[]> byteSegments = decoderResult.getByteSegments();
        if (byteSegments != null) {
            result.putMetadata(ResultMetadataType.BYTE_SEGMENTS, byteSegments);
        }
        String eCLevel = decoderResult.getECLevel();
        if (eCLevel != null) {
            result.putMetadata(ResultMetadataType.ERROR_CORRECTION_LEVEL, eCLevel);
        }
        return result;
    }

    private static BitMatrix extractPureBits(BitMatrix bitMatrix) throws NotFoundException {
        int[] topLeftOnBit = bitMatrix.getTopLeftOnBit();
        int[] bottomRightOnBit = bitMatrix.getBottomRightOnBit();
        if (topLeftOnBit == null || bottomRightOnBit == null) {
            throw NotFoundException.getNotFoundInstance();
        }
        int moduleSize = moduleSize(topLeftOnBit, bitMatrix);
        int r4 = topLeftOnBit[1];
        int r5 = bottomRightOnBit[1];
        int r0 = topLeftOnBit[0];
        int r1 = ((bottomRightOnBit[0] - r0) + 1) / moduleSize;
        int r52 = ((r5 - r4) + 1) / moduleSize;
        if (r1 <= 0 || r52 <= 0) {
            throw NotFoundException.getNotFoundInstance();
        }
        int r3 = moduleSize / 2;
        int r42 = r4 + r3;
        int r02 = r0 + r3;
        BitMatrix bitMatrix2 = new BitMatrix(r1, r52);
        for (int r7 = 0; r7 < r52; r7++) {
            int r8 = (r7 * moduleSize) + r42;
            for (int r9 = 0; r9 < r1; r9++) {
                if (bitMatrix.get((r9 * moduleSize) + r02, r8)) {
                    bitMatrix2.set(r9, r7);
                }
            }
        }
        return bitMatrix2;
    }

    private static int moduleSize(int[] r5, BitMatrix bitMatrix) throws NotFoundException {
        int width = bitMatrix.getWidth();
        int r2 = r5[0];
        int r3 = r5[1];
        while (r2 < width && bitMatrix.get(r2, r3)) {
            r2++;
        }
        if (r2 == width) {
            throw NotFoundException.getNotFoundInstance();
        }
        int r22 = r2 - r5[0];
        if (r22 != 0) {
            return r22;
        }
        throw NotFoundException.getNotFoundInstance();
    }
}
