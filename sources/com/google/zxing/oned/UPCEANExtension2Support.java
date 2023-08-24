package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import java.util.EnumMap;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class UPCEANExtension2Support {
    private final int[] decodeMiddleCounters = new int[4];
    private final StringBuilder decodeRowStringBuffer = new StringBuilder();

    /* JADX INFO: Access modifiers changed from: package-private */
    public Result decodeRow(int r9, BitArray bitArray, int[] r11) throws NotFoundException {
        StringBuilder sb = this.decodeRowStringBuffer;
        sb.setLength(0);
        int decodeMiddle = decodeMiddle(bitArray, r11, sb);
        String sb2 = sb.toString();
        Map<ResultMetadataType, Object> parseExtensionString = parseExtensionString(sb2);
        float f = r9;
        Result result = new Result(sb2, null, new ResultPoint[]{new ResultPoint((r11[0] + r11[1]) / 2.0f, f), new ResultPoint(decodeMiddle, f)}, BarcodeFormat.UPC_EAN_EXTENSION);
        if (parseExtensionString != null) {
            result.putAllMetadata(parseExtensionString);
        }
        return result;
    }

    private int decodeMiddle(BitArray bitArray, int[] r13, StringBuilder sb) throws NotFoundException {
        int[] r0 = this.decodeMiddleCounters;
        r0[0] = 0;
        r0[1] = 0;
        r0[2] = 0;
        r0[3] = 0;
        int size = bitArray.getSize();
        int r132 = r13[1];
        int r6 = 0;
        for (int r5 = 0; r5 < 2 && r132 < size; r5++) {
            int decodeDigit = UPCEANReader.decodeDigit(bitArray, r0, r132, UPCEANReader.L_AND_G_PATTERNS);
            sb.append((char) ((decodeDigit % 10) + 48));
            for (int r10 : r0) {
                r132 += r10;
            }
            if (decodeDigit >= 10) {
                r6 |= 1 << (1 - r5);
            }
            if (r5 != 1) {
                r132 = bitArray.getNextUnset(bitArray.getNextSet(r132));
            }
        }
        if (sb.length() != 2) {
            throw NotFoundException.getNotFoundInstance();
        }
        if (Integer.parseInt(sb.toString()) % 4 == r6) {
            return r132;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static Map<ResultMetadataType, Object> parseExtensionString(String str) {
        if (str.length() != 2) {
            return null;
        }
        EnumMap enumMap = new EnumMap(ResultMetadataType.class);
        enumMap.put((EnumMap) ResultMetadataType.ISSUE_NUMBER, (ResultMetadataType) Integer.valueOf(str));
        return enumMap;
    }
}
