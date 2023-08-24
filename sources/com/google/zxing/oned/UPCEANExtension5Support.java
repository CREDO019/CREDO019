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
public final class UPCEANExtension5Support {
    private static final int[] CHECK_DIGIT_ENCODINGS = {24, 20, 18, 17, 12, 6, 3, 10, 9, 5};
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

    private int decodeMiddle(BitArray bitArray, int[] r12, StringBuilder sb) throws NotFoundException {
        int[] r0 = this.decodeMiddleCounters;
        r0[0] = 0;
        r0[1] = 0;
        r0[2] = 0;
        r0[3] = 0;
        int size = bitArray.getSize();
        int r122 = r12[1];
        int r5 = 0;
        for (int r4 = 0; r4 < 5 && r122 < size; r4++) {
            int decodeDigit = UPCEANReader.decodeDigit(bitArray, r0, r122, UPCEANReader.L_AND_G_PATTERNS);
            sb.append((char) ((decodeDigit % 10) + 48));
            for (int r9 : r0) {
                r122 += r9;
            }
            if (decodeDigit >= 10) {
                r5 |= 1 << (4 - r4);
            }
            if (r4 != 4) {
                r122 = bitArray.getNextUnset(bitArray.getNextSet(r122));
            }
        }
        if (sb.length() != 5) {
            throw NotFoundException.getNotFoundInstance();
        }
        if (extensionChecksum(sb.toString()) == determineCheckDigit(r5)) {
            return r122;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static int extensionChecksum(CharSequence charSequence) {
        int length = charSequence.length();
        int r2 = 0;
        for (int r1 = length - 2; r1 >= 0; r1 -= 2) {
            r2 += charSequence.charAt(r1) - '0';
        }
        int r22 = r2 * 3;
        for (int r0 = length - 1; r0 >= 0; r0 -= 2) {
            r22 += charSequence.charAt(r0) - '0';
        }
        return (r22 * 3) % 10;
    }

    private static int determineCheckDigit(int r2) throws NotFoundException {
        for (int r0 = 0; r0 < 10; r0++) {
            if (r2 == CHECK_DIGIT_ENCODINGS[r0]) {
                return r0;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static Map<ResultMetadataType, Object> parseExtensionString(String str) {
        String parseExtension5String;
        if (str.length() == 5 && (parseExtension5String = parseExtension5String(str)) != null) {
            EnumMap enumMap = new EnumMap(ResultMetadataType.class);
            enumMap.put((EnumMap) ResultMetadataType.SUGGESTED_PRICE, (ResultMetadataType) parseExtension5String);
            return enumMap;
        }
        return null;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x003e, code lost:
        if (r5.equals("90000") == false) goto L9;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String parseExtension5String(java.lang.String r5) {
        /*
            r0 = 0
            char r1 = r5.charAt(r0)
            r2 = 48
            java.lang.String r3 = ""
            r4 = 1
            if (r1 == r2) goto L50
            r2 = 53
            if (r1 == r2) goto L4d
            r2 = 57
            if (r1 == r2) goto L15
            goto L52
        L15:
            r5.hashCode()
            r1 = -1
            int r2 = r5.hashCode()
            switch(r2) {
                case 54118329: goto L38;
                case 54395376: goto L2d;
                case 54395377: goto L22;
                default: goto L20;
            }
        L20:
            r0 = -1
            goto L41
        L22:
            java.lang.String r0 = "99991"
            boolean r0 = r5.equals(r0)
            if (r0 != 0) goto L2b
            goto L20
        L2b:
            r0 = 2
            goto L41
        L2d:
            java.lang.String r0 = "99990"
            boolean r0 = r5.equals(r0)
            if (r0 != 0) goto L36
            goto L20
        L36:
            r0 = 1
            goto L41
        L38:
            java.lang.String r2 = "90000"
            boolean r2 = r5.equals(r2)
            if (r2 != 0) goto L41
            goto L20
        L41:
            switch(r0) {
                case 0: goto L4b;
                case 1: goto L48;
                case 2: goto L45;
                default: goto L44;
            }
        L44:
            goto L52
        L45:
            java.lang.String r5 = "0.00"
            return r5
        L48:
            java.lang.String r5 = "Used"
            return r5
        L4b:
            r5 = 0
            return r5
        L4d:
            java.lang.String r3 = "$"
            goto L52
        L50:
            java.lang.String r3 = "£"
        L52:
            java.lang.String r5 = r5.substring(r4)
            int r5 = java.lang.Integer.parseInt(r5)
            int r0 = r5 / 100
            java.lang.String r0 = java.lang.String.valueOf(r0)
            int r5 = r5 % 100
            r1 = 10
            if (r5 >= r1) goto L71
            java.lang.String r5 = java.lang.String.valueOf(r5)
            java.lang.String r1 = "0"
            java.lang.String r5 = r1.concat(r5)
            goto L75
        L71:
            java.lang.String r5 = java.lang.String.valueOf(r5)
        L75:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r3)
            r1.append(r0)
            r0 = 46
            r1.append(r0)
            r1.append(r5)
            java.lang.String r5 = r1.toString()
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.UPCEANExtension5Support.parseExtension5String(java.lang.String):java.lang.String");
    }
}
