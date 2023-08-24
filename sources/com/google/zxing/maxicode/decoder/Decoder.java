package com.google.zxing.maxicode.decoder;

import com.google.common.base.Ascii;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonDecoder;
import com.google.zxing.common.reedsolomon.ReedSolomonException;
import java.util.Map;

/* loaded from: classes3.dex */
public final class Decoder {
    private static final int ALL = 0;
    private static final int EVEN = 1;
    private static final int ODD = 2;
    private final ReedSolomonDecoder rsDecoder = new ReedSolomonDecoder(GenericGF.MAXICODE_FIELD_64);

    public DecoderResult decode(BitMatrix bitMatrix) throws ChecksumException, FormatException {
        return decode(bitMatrix, null);
    }

    public DecoderResult decode(BitMatrix bitMatrix, Map<DecodeHintType, ?> map) throws FormatException, ChecksumException {
        byte[] bArr;
        byte[] readCodewords = new BitMatrixParser(bitMatrix).readCodewords();
        correctErrors(readCodewords, 0, 10, 10, 0);
        int r6 = readCodewords[0] & Ascii.f1128SI;
        if (r6 == 2 || r6 == 3 || r6 == 4) {
            correctErrors(readCodewords, 20, 84, 40, 1);
            correctErrors(readCodewords, 20, 84, 40, 2);
            bArr = new byte[94];
        } else if (r6 == 5) {
            correctErrors(readCodewords, 20, 68, 56, 1);
            correctErrors(readCodewords, 20, 68, 56, 2);
            bArr = new byte[78];
        } else {
            throw FormatException.getFormatInstance();
        }
        System.arraycopy(readCodewords, 0, bArr, 0, 10);
        System.arraycopy(readCodewords, 20, bArr, 10, bArr.length - 10);
        return DecodedBitStreamParser.decode(bArr, r6);
    }

    private void correctErrors(byte[] bArr, int r9, int r10, int r11, int r12) throws ChecksumException {
        int r0 = r10 + r11;
        int r1 = r12 == 0 ? 1 : 2;
        int[] r2 = new int[r0 / r1];
        for (int r4 = 0; r4 < r0; r4++) {
            if (r12 == 0 || r4 % 2 == r12 - 1) {
                r2[r4 / r1] = bArr[r4 + r9] & 255;
            }
        }
        try {
            this.rsDecoder.decode(r2, r11 / r1);
            for (int r3 = 0; r3 < r10; r3++) {
                if (r12 == 0 || r3 % 2 == r12 - 1) {
                    bArr[r3 + r9] = (byte) r2[r3 / r1];
                }
            }
        } catch (ReedSolomonException unused) {
            throw ChecksumException.getChecksumInstance();
        }
    }
}
