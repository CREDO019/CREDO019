package com.google.zxing.datamatrix.decoder;

import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonDecoder;
import com.google.zxing.common.reedsolomon.ReedSolomonException;

/* loaded from: classes3.dex */
public final class Decoder {
    private final ReedSolomonDecoder rsDecoder = new ReedSolomonDecoder(GenericGF.DATA_MATRIX_FIELD_256);

    public DecoderResult decode(boolean[][] zArr) throws FormatException, ChecksumException {
        return decode(BitMatrix.parse(zArr));
    }

    public DecoderResult decode(BitMatrix bitMatrix) throws FormatException, ChecksumException {
        BitMatrixParser bitMatrixParser = new BitMatrixParser(bitMatrix);
        DataBlock[] dataBlocks = DataBlock.getDataBlocks(bitMatrixParser.readCodewords(), bitMatrixParser.getVersion());
        int r3 = 0;
        for (DataBlock dataBlock : dataBlocks) {
            r3 += dataBlock.getNumDataCodewords();
        }
        byte[] bArr = new byte[r3];
        int length = dataBlocks.length;
        for (int r32 = 0; r32 < length; r32++) {
            DataBlock dataBlock2 = dataBlocks[r32];
            byte[] codewords = dataBlock2.getCodewords();
            int numDataCodewords = dataBlock2.getNumDataCodewords();
            correctErrors(codewords, numDataCodewords);
            for (int r6 = 0; r6 < numDataCodewords; r6++) {
                bArr[(r6 * length) + r32] = codewords[r6];
            }
        }
        return DecodedBitStreamParser.decode(bArr);
    }

    private void correctErrors(byte[] bArr, int r7) throws ChecksumException {
        int length = bArr.length;
        int[] r1 = new int[length];
        for (int r3 = 0; r3 < length; r3++) {
            r1[r3] = bArr[r3] & 255;
        }
        try {
            this.rsDecoder.decode(r1, bArr.length - r7);
            for (int r2 = 0; r2 < r7; r2++) {
                bArr[r2] = (byte) r1[r2];
            }
        } catch (ReedSolomonException unused) {
            throw ChecksumException.getChecksumInstance();
        }
    }
}
