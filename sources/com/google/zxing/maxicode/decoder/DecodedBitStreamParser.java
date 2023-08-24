package com.google.zxing.maxicode.decoder;

import com.google.common.base.Ascii;
import com.google.zxing.common.DecoderResult;
import java.text.DecimalFormat;
import org.apache.commons.fileupload.MultipartStream;

/* loaded from: classes3.dex */
final class DecodedBitStreamParser {
    private static final char ECI = 65530;

    /* renamed from: FS */
    private static final char f1220FS = 28;

    /* renamed from: GS */
    private static final char f1221GS = 29;
    private static final char LATCHA = 65527;
    private static final char LATCHB = 65528;
    private static final char LOCK = 65529;

    /* renamed from: NS */
    private static final char f1222NS = 65531;
    private static final char PAD = 65532;

    /* renamed from: RS */
    private static final char f1223RS = 30;
    private static final String[] SETS = {"\nABCDEFGHIJKLMNOPQRSTUVWXYZ\ufffa\u001c\u001d\u001e\ufffb ￼\"#$%&'()*+,-./0123456789:\ufff1\ufff2\ufff3\ufff4\ufff8", "`abcdefghijklmnopqrstuvwxyz\ufffa\u001c\u001d\u001e\ufffb{￼}~\u007f;<=>?[\\]^_ ,./:@!|￼\ufff5\ufff6￼\ufff0\ufff2\ufff3\ufff4\ufff7", "ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏÐÑÒÓÔÕÖ×ØÙÚ\ufffa\u001c\u001d\u001eÛÜÝÞßª¬±²³µ¹º¼½¾\u0080\u0081\u0082\u0083\u0084\u0085\u0086\u0087\u0088\u0089\ufff7 \ufff9\ufff3\ufff4\ufff8", "àáâãäåæçèéêëìíîïðñòóôõö÷øùú\ufffa\u001c\u001d\u001e\ufffbûüýþÿ¡¨«¯°´·¸»¿\u008a\u008b\u008c\u008d\u008e\u008f\u0090\u0091\u0092\u0093\u0094\ufff7 \ufff2\ufff9\ufff4\ufff8", "\u0000\u0001\u0002\u0003\u0004\u0005\u0006\u0007\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a\ufffa￼￼\u001b\ufffb\u001c\u001d\u001e\u001f\u009f ¢£¤¥¦§©\u00ad®¶\u0095\u0096\u0097\u0098\u0099\u009a\u009b\u009c\u009d\u009e\ufff7 \ufff2\ufff3\ufff9\ufff8", "\u0000\u0001\u0002\u0003\u0004\u0005\u0006\u0007\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a\u001b\u001c\u001d\u001e\u001f !\"#$%&'()*+,-./0123456789:;<=>?"};
    private static final char SHIFTA = 65520;
    private static final char SHIFTB = 65521;
    private static final char SHIFTC = 65522;
    private static final char SHIFTD = 65523;
    private static final char SHIFTE = 65524;
    private static final char THREESHIFTA = 65526;
    private static final char TWOSHIFTA = 65525;

    private DecodedBitStreamParser() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static DecoderResult decode(byte[] bArr, int r8) {
        String postCode3;
        StringBuilder sb = new StringBuilder(144);
        if (r8 == 2 || r8 == 3) {
            if (r8 == 2) {
                postCode3 = new DecimalFormat("0000000000".substring(0, getPostCode2Length(bArr))).format(getPostCode2(bArr));
            } else {
                postCode3 = getPostCode3(bArr);
            }
            DecimalFormat decimalFormat = new DecimalFormat("000");
            String format = decimalFormat.format(getCountry(bArr));
            String format2 = decimalFormat.format(getServiceClass(bArr));
            sb.append(getMessage(bArr, 10, 84));
            if (sb.toString().startsWith("[)>\u001e01\u001d")) {
                sb.insert(9, postCode3 + f1221GS + format + f1221GS + format2 + f1221GS);
            } else {
                sb.insert(0, postCode3 + f1221GS + format + f1221GS + format2 + f1221GS);
            }
        } else if (r8 == 4) {
            sb.append(getMessage(bArr, 1, 93));
        } else if (r8 == 5) {
            sb.append(getMessage(bArr, 1, 77));
        }
        return new DecoderResult(bArr, sb.toString(), null, String.valueOf(r8));
    }

    private static int getBit(int r1, byte[] bArr) {
        int r12 = r1 - 1;
        return ((1 << (5 - (r12 % 6))) & bArr[r12 / 6]) == 0 ? 0 : 1;
    }

    private static int getInt(byte[] bArr, byte[] bArr2) {
        if (bArr2.length == 0) {
            throw new IllegalArgumentException();
        }
        int r1 = 0;
        for (int r0 = 0; r0 < bArr2.length; r0++) {
            r1 += getBit(bArr2[r0], bArr) << ((bArr2.length - r0) - 1);
        }
        return r1;
    }

    private static int getCountry(byte[] bArr) {
        return getInt(bArr, new byte[]{53, 54, 43, 44, MultipartStream.DASH, 46, 47, 48, 37, 38});
    }

    private static int getServiceClass(byte[] bArr) {
        return getInt(bArr, new byte[]{55, 56, 57, 58, 59, 60, 49, 50, 51, 52});
    }

    private static int getPostCode2Length(byte[] bArr) {
        return getInt(bArr, new byte[]{39, 40, 41, 42, Ascii.f1131US, 32});
    }

    private static int getPostCode2(byte[] bArr) {
        return getInt(bArr, new byte[]{33, 34, 35, 36, Ascii.f1120EM, Ascii.SUB, Ascii.ESC, Ascii.f1122FS, Ascii.f1123GS, Ascii.f1127RS, 19, Ascii.DC4, Ascii.NAK, Ascii.SYN, Ascii.ETB, Ascii.CAN, 13, Ascii.f1129SO, Ascii.f1128SI, 16, 17, Ascii.DC2, 7, 8, 9, 10, Ascii.f1132VT, Ascii.f1121FF, 1, 2});
    }

    private static String getPostCode3(byte[] bArr) {
        String[] strArr = SETS;
        return String.valueOf(new char[]{strArr[0].charAt(getInt(bArr, new byte[]{39, 40, 41, 42, Ascii.f1131US, 32})), strArr[0].charAt(getInt(bArr, new byte[]{33, 34, 35, 36, Ascii.f1120EM, Ascii.SUB})), strArr[0].charAt(getInt(bArr, new byte[]{Ascii.ESC, Ascii.f1122FS, Ascii.f1123GS, Ascii.f1127RS, 19, Ascii.DC4})), strArr[0].charAt(getInt(bArr, new byte[]{Ascii.NAK, Ascii.SYN, Ascii.ETB, Ascii.CAN, 13, Ascii.f1129SO})), strArr[0].charAt(getInt(bArr, new byte[]{Ascii.f1128SI, 16, 17, Ascii.DC2, 7, 8})), strArr[0].charAt(getInt(bArr, new byte[]{9, 10, Ascii.f1132VT, Ascii.f1121FF, 1, 2}))});
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private static String getMessage(byte[] bArr, int r13, int r14) {
        int r3;
        int r32;
        int r33;
        int r34;
        StringBuilder sb = new StringBuilder();
        int r35 = r13;
        int r4 = 0;
        int r5 = -1;
        int r6 = 0;
        while (r35 < r13 + r14) {
            char charAt = SETS[r4].charAt(bArr[r35]);
            switch (charAt) {
                case 65520:
                case 65521:
                case 65522:
                case 65523:
                case 65524:
                    r6 = r4;
                    r4 = charAt - SHIFTA;
                    r5 = 1;
                    break;
                case 65525:
                    r5 = 2;
                    r6 = r4;
                    r4 = 0;
                    break;
                case 65526:
                    r5 = 3;
                    r6 = r4;
                    r4 = 0;
                    break;
                case 65527:
                    r4 = 0;
                    r5 = -1;
                    break;
                case 65528:
                    r4 = 1;
                    r5 = -1;
                    break;
                case 65529:
                    r5 = -1;
                    break;
                case 65530:
                default:
                    sb.append(charAt);
                    break;
                case 65531:
                    r35 = r35 + 1 + 1 + 1 + 1 + 1;
                    sb.append(new DecimalFormat("000000000").format((bArr[r3] << Ascii.CAN) + (bArr[r32] << Ascii.DC2) + (bArr[r33] << Ascii.f1121FF) + (bArr[r34] << 6) + bArr[r35]));
                    break;
            }
            int r7 = r5 - 1;
            if (r5 == 0) {
                r4 = r6;
            }
            r35++;
            r5 = r7;
        }
        while (sb.length() > 0 && sb.charAt(sb.length() - 1) == 65532) {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }
}
