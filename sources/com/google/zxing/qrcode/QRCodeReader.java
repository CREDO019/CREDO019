package com.google.zxing.qrcode;

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
import com.google.zxing.qrcode.decoder.Decoder;
import com.google.zxing.qrcode.decoder.QRCodeDecoderMetaData;
import com.google.zxing.qrcode.detector.Detector;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class QRCodeReader implements Reader {
    private static final ResultPoint[] NO_POINTS = new ResultPoint[0];
    private final Decoder decoder = new Decoder();

    @Override // com.google.zxing.Reader
    public void reset() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Decoder getDecoder() {
        return this.decoder;
    }

    @Override // com.google.zxing.Reader
    public Result decode(BinaryBitmap binaryBitmap) throws NotFoundException, ChecksumException, FormatException {
        return decode(binaryBitmap, null);
    }

    @Override // com.google.zxing.Reader
    public final Result decode(BinaryBitmap binaryBitmap, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException {
        ResultPoint[] points;
        DecoderResult decoderResult;
        if (map != null && map.containsKey(DecodeHintType.PURE_BARCODE)) {
            decoderResult = this.decoder.decode(extractPureBits(binaryBitmap.getBlackMatrix()), map);
            points = NO_POINTS;
        } else {
            DetectorResult detect = new Detector(binaryBitmap.getBlackMatrix()).detect(map);
            DecoderResult decode = this.decoder.decode(detect.getBits(), map);
            points = detect.getPoints();
            decoderResult = decode;
        }
        if (decoderResult.getOther() instanceof QRCodeDecoderMetaData) {
            ((QRCodeDecoderMetaData) decoderResult.getOther()).applyMirroredCorrection(points);
        }
        Result result = new Result(decoderResult.getText(), decoderResult.getRawBytes(), points, BarcodeFormat.QR_CODE);
        List<byte[]> byteSegments = decoderResult.getByteSegments();
        if (byteSegments != null) {
            result.putMetadata(ResultMetadataType.BYTE_SEGMENTS, byteSegments);
        }
        String eCLevel = decoderResult.getECLevel();
        if (eCLevel != null) {
            result.putMetadata(ResultMetadataType.ERROR_CORRECTION_LEVEL, eCLevel);
        }
        if (decoderResult.hasStructuredAppend()) {
            result.putMetadata(ResultMetadataType.STRUCTURED_APPEND_SEQUENCE, Integer.valueOf(decoderResult.getStructuredAppendSequenceNumber()));
            result.putMetadata(ResultMetadataType.STRUCTURED_APPEND_PARITY, Integer.valueOf(decoderResult.getStructuredAppendParity()));
        }
        return result;
    }

    private static BitMatrix extractPureBits(BitMatrix bitMatrix) throws NotFoundException {
        int[] topLeftOnBit = bitMatrix.getTopLeftOnBit();
        int[] bottomRightOnBit = bitMatrix.getBottomRightOnBit();
        if (topLeftOnBit == null || bottomRightOnBit == null) {
            throw NotFoundException.getNotFoundInstance();
        }
        float moduleSize = moduleSize(topLeftOnBit, bitMatrix);
        int r4 = topLeftOnBit[1];
        int r5 = bottomRightOnBit[1];
        int r0 = topLeftOnBit[0];
        int r1 = bottomRightOnBit[0];
        if (r0 >= r1 || r4 >= r5) {
            throw NotFoundException.getNotFoundInstance();
        }
        int r7 = r5 - r4;
        if (r7 != r1 - r0 && (r1 = r0 + r7) >= bitMatrix.getWidth()) {
            throw NotFoundException.getNotFoundInstance();
        }
        int round = Math.round(((r1 - r0) + 1) / moduleSize);
        int round2 = Math.round((r7 + 1) / moduleSize);
        if (round <= 0 || round2 <= 0) {
            throw NotFoundException.getNotFoundInstance();
        }
        if (round2 != round) {
            throw NotFoundException.getNotFoundInstance();
        }
        int r72 = (int) (moduleSize / 2.0f);
        int r42 = r4 + r72;
        int r02 = r0 + r72;
        int r9 = (((int) ((round - 1) * moduleSize)) + r02) - r1;
        if (r9 > 0) {
            if (r9 > r72) {
                throw NotFoundException.getNotFoundInstance();
            }
            r02 -= r9;
        }
        int r12 = (((int) ((round2 - 1) * moduleSize)) + r42) - r5;
        if (r12 > 0) {
            if (r12 > r72) {
                throw NotFoundException.getNotFoundInstance();
            }
            r42 -= r12;
        }
        BitMatrix bitMatrix2 = new BitMatrix(round, round2);
        for (int r52 = 0; r52 < round2; r52++) {
            int r73 = ((int) (r52 * moduleSize)) + r42;
            for (int r92 = 0; r92 < round; r92++) {
                if (bitMatrix.get(((int) (r92 * moduleSize)) + r02, r73)) {
                    bitMatrix2.set(r92, r52);
                }
            }
        }
        return bitMatrix2;
    }

    private static float moduleSize(int[] r8, BitMatrix bitMatrix) throws NotFoundException {
        int height = bitMatrix.getHeight();
        int width = bitMatrix.getWidth();
        int r3 = r8[0];
        boolean z = true;
        int r5 = r8[1];
        int r6 = 0;
        while (r3 < width && r5 < height) {
            if (z != bitMatrix.get(r3, r5)) {
                r6++;
                if (r6 == 5) {
                    break;
                }
                z = !z;
            }
            r3++;
            r5++;
        }
        if (r3 == width || r5 == height) {
            throw NotFoundException.getNotFoundInstance();
        }
        return (r3 - r8[0]) / 7.0f;
    }
}
