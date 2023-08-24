package com.google.zxing.pdf417.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.pdf417.PDF417ResultMetadata;
import java.math.BigInteger;
import java.util.Arrays;

/* loaded from: classes3.dex */
final class DecodedBitStreamParser {

    /* renamed from: AL */
    private static final int f1229AL = 28;

    /* renamed from: AS */
    private static final int f1230AS = 27;
    private static final int BEGIN_MACRO_PDF417_CONTROL_BLOCK = 928;
    private static final int BEGIN_MACRO_PDF417_OPTIONAL_FIELD = 923;
    private static final int BYTE_COMPACTION_MODE_LATCH = 901;
    private static final int BYTE_COMPACTION_MODE_LATCH_6 = 924;
    private static final int ECI_CHARSET = 927;
    private static final int ECI_GENERAL_PURPOSE = 926;
    private static final int ECI_USER_DEFINED = 925;
    private static final BigInteger[] EXP900;

    /* renamed from: LL */
    private static final int f1231LL = 27;
    private static final int MACRO_PDF417_OPTIONAL_FIELD_ADDRESSEE = 4;
    private static final int MACRO_PDF417_OPTIONAL_FIELD_CHECKSUM = 6;
    private static final int MACRO_PDF417_OPTIONAL_FIELD_FILE_NAME = 0;
    private static final int MACRO_PDF417_OPTIONAL_FIELD_FILE_SIZE = 5;
    private static final int MACRO_PDF417_OPTIONAL_FIELD_SEGMENT_COUNT = 1;
    private static final int MACRO_PDF417_OPTIONAL_FIELD_SENDER = 3;
    private static final int MACRO_PDF417_OPTIONAL_FIELD_TIME_STAMP = 2;
    private static final int MACRO_PDF417_TERMINATOR = 922;
    private static final int MAX_NUMERIC_CODEWORDS = 15;

    /* renamed from: ML */
    private static final int f1232ML = 28;
    private static final int MODE_SHIFT_TO_BYTE_COMPACTION_MODE = 913;
    private static final int NUMBER_OF_SEQUENCE_CODEWORDS = 2;
    private static final int NUMERIC_COMPACTION_MODE_LATCH = 902;
    private static final int PAL = 29;

    /* renamed from: PL */
    private static final int f1233PL = 25;

    /* renamed from: PS */
    private static final int f1234PS = 29;
    private static final int TEXT_COMPACTION_MODE_LATCH = 900;
    private static final char[] PUNCT_CHARS = ";<>@[\\]_`~!\r\t,:\n-.$/\"|*()?{}'".toCharArray();
    private static final char[] MIXED_CHARS = "0123456789&\r\t,:#-.$/+%*=^".toCharArray();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public enum Mode {
        ALPHA,
        LOWER,
        MIXED,
        PUNCT,
        ALPHA_SHIFT,
        PUNCT_SHIFT
    }

    static {
        BigInteger[] bigIntegerArr = new BigInteger[16];
        EXP900 = bigIntegerArr;
        bigIntegerArr[0] = BigInteger.ONE;
        BigInteger valueOf = BigInteger.valueOf(900L);
        bigIntegerArr[1] = valueOf;
        int r0 = 2;
        while (true) {
            BigInteger[] bigIntegerArr2 = EXP900;
            if (r0 >= bigIntegerArr2.length) {
                return;
            }
            bigIntegerArr2[r0] = bigIntegerArr2[r0 - 1].multiply(valueOf);
            r0++;
        }
    }

    private DecodedBitStreamParser() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:17:0x004e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.google.zxing.common.DecoderResult decode(int[] r6, java.lang.String r7) throws com.google.zxing.FormatException {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            int r1 = r6.length
            r2 = 1
            int r1 = r1 << r2
            r0.<init>(r1)
            java.nio.charset.Charset r1 = java.nio.charset.StandardCharsets.ISO_8859_1
            r2 = r6[r2]
            com.google.zxing.pdf417.PDF417ResultMetadata r3 = new com.google.zxing.pdf417.PDF417ResultMetadata
            r3.<init>()
            r4 = 2
        L12:
            r5 = 0
            r5 = r6[r5]
            if (r4 >= r5) goto L6d
            r5 = 913(0x391, float:1.28E-42)
            if (r2 == r5) goto L58
            switch(r2) {
                case 900: goto L53;
                case 901: goto L4e;
                case 902: goto L49;
                default: goto L1e;
            }
        L1e:
            switch(r2) {
                case 922: goto L44;
                case 923: goto L44;
                case 924: goto L4e;
                case 925: goto L41;
                case 926: goto L3e;
                case 927: goto L2d;
                case 928: goto L28;
                default: goto L21;
            }
        L21:
            int r4 = r4 + (-1)
            int r2 = textCompaction(r6, r4, r0)
            goto L60
        L28:
            int r2 = decodeMacroBlock(r6, r4, r3)
            goto L60
        L2d:
            int r2 = r4 + 1
            r1 = r6[r4]
            com.google.zxing.common.CharacterSetECI r1 = com.google.zxing.common.CharacterSetECI.getCharacterSetECIByValue(r1)
            java.lang.String r1 = r1.name()
            java.nio.charset.Charset r1 = java.nio.charset.Charset.forName(r1)
            goto L60
        L3e:
            int r2 = r4 + 2
            goto L60
        L41:
            int r2 = r4 + 1
            goto L60
        L44:
            com.google.zxing.FormatException r6 = com.google.zxing.FormatException.getFormatInstance()
            throw r6
        L49:
            int r2 = numericCompaction(r6, r4, r0)
            goto L60
        L4e:
            int r2 = byteCompaction(r2, r6, r1, r4, r0)
            goto L60
        L53:
            int r2 = textCompaction(r6, r4, r0)
            goto L60
        L58:
            int r2 = r4 + 1
            r4 = r6[r4]
            char r4 = (char) r4
            r0.append(r4)
        L60:
            int r4 = r6.length
            if (r2 >= r4) goto L68
            int r4 = r2 + 1
            r2 = r6[r2]
            goto L12
        L68:
            com.google.zxing.FormatException r6 = com.google.zxing.FormatException.getFormatInstance()
            throw r6
        L6d:
            int r6 = r0.length()
            if (r6 == 0) goto L81
            com.google.zxing.common.DecoderResult r6 = new com.google.zxing.common.DecoderResult
            java.lang.String r0 = r0.toString()
            r1 = 0
            r6.<init>(r1, r0, r1, r7)
            r6.setOther(r3)
            return r6
        L81:
            com.google.zxing.FormatException r6 = com.google.zxing.FormatException.getFormatInstance()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.decoder.DecodedBitStreamParser.decode(int[], java.lang.String):com.google.zxing.common.DecoderResult");
    }

    static int decodeMacroBlock(int[] r6, int r7, PDF417ResultMetadata pDF417ResultMetadata) throws FormatException {
        if (r7 + 2 > r6[0]) {
            throw FormatException.getFormatInstance();
        }
        int[] r2 = new int[2];
        int r3 = 0;
        while (r3 < 2) {
            r2[r3] = r6[r7];
            r3++;
            r7++;
        }
        pDF417ResultMetadata.setSegmentIndex(Integer.parseInt(decodeBase900toBase10(r2, 2)));
        StringBuilder sb = new StringBuilder();
        int textCompaction = textCompaction(r6, r7, sb);
        pDF417ResultMetadata.setFileId(sb.toString());
        int r0 = r6[textCompaction] == BEGIN_MACRO_PDF417_OPTIONAL_FIELD ? textCompaction + 1 : -1;
        while (textCompaction < r6[0]) {
            int r4 = r6[textCompaction];
            if (r4 == MACRO_PDF417_TERMINATOR) {
                textCompaction++;
                pDF417ResultMetadata.setLastSegment(true);
            } else if (r4 == BEGIN_MACRO_PDF417_OPTIONAL_FIELD) {
                int r72 = textCompaction + 1;
                switch (r6[r72]) {
                    case 0:
                        StringBuilder sb2 = new StringBuilder();
                        textCompaction = textCompaction(r6, r72 + 1, sb2);
                        pDF417ResultMetadata.setFileName(sb2.toString());
                        continue;
                    case 1:
                        StringBuilder sb3 = new StringBuilder();
                        textCompaction = numericCompaction(r6, r72 + 1, sb3);
                        pDF417ResultMetadata.setSegmentCount(Integer.parseInt(sb3.toString()));
                        continue;
                    case 2:
                        StringBuilder sb4 = new StringBuilder();
                        textCompaction = numericCompaction(r6, r72 + 1, sb4);
                        pDF417ResultMetadata.setTimestamp(Long.parseLong(sb4.toString()));
                        continue;
                    case 3:
                        StringBuilder sb5 = new StringBuilder();
                        textCompaction = textCompaction(r6, r72 + 1, sb5);
                        pDF417ResultMetadata.setSender(sb5.toString());
                        continue;
                    case 4:
                        StringBuilder sb6 = new StringBuilder();
                        textCompaction = textCompaction(r6, r72 + 1, sb6);
                        pDF417ResultMetadata.setAddressee(sb6.toString());
                        continue;
                    case 5:
                        StringBuilder sb7 = new StringBuilder();
                        textCompaction = numericCompaction(r6, r72 + 1, sb7);
                        pDF417ResultMetadata.setFileSize(Long.parseLong(sb7.toString()));
                        continue;
                    case 6:
                        StringBuilder sb8 = new StringBuilder();
                        textCompaction = numericCompaction(r6, r72 + 1, sb8);
                        pDF417ResultMetadata.setChecksum(Integer.parseInt(sb8.toString()));
                        continue;
                    default:
                        throw FormatException.getFormatInstance();
                }
            } else {
                throw FormatException.getFormatInstance();
            }
        }
        if (r0 != -1) {
            int r1 = textCompaction - r0;
            if (pDF417ResultMetadata.isLastSegment()) {
                r1--;
            }
            pDF417ResultMetadata.setOptionalData(Arrays.copyOfRange(r6, r0, r1 + r0));
        }
        return textCompaction;
    }

    private static int textCompaction(int[] r9, int r10, StringBuilder sb) {
        int[] r1 = new int[(r9[0] - r10) << 1];
        int[] r3 = new int[(r9[0] - r10) << 1];
        boolean z = false;
        int r5 = 0;
        while (r10 < r9[0] && !z) {
            int r6 = r10 + 1;
            int r102 = r9[r10];
            if (r102 < TEXT_COMPACTION_MODE_LATCH) {
                r1[r5] = r102 / 30;
                r1[r5 + 1] = r102 % 30;
                r5 += 2;
            } else if (r102 != MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                if (r102 != 928) {
                    switch (r102) {
                        case TEXT_COMPACTION_MODE_LATCH /* 900 */:
                            r1[r5] = TEXT_COMPACTION_MODE_LATCH;
                            r5++;
                            break;
                        case BYTE_COMPACTION_MODE_LATCH /* 901 */:
                        case NUMERIC_COMPACTION_MODE_LATCH /* 902 */:
                            break;
                        default:
                            switch (r102) {
                            }
                    }
                }
                r10 = r6 - 1;
                z = true;
            } else {
                r1[r5] = MODE_SHIFT_TO_BYTE_COMPACTION_MODE;
                r10 = r6 + 1;
                r3[r5] = r9[r6];
                r5++;
            }
            r10 = r6;
        }
        decodeTextCompaction(r1, r3, r5, sb);
        return r10;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private static void decodeTextCompaction(int[] r12, int[] r13, int r14, StringBuilder sb) {
        Mode mode;
        int r4;
        Mode mode2 = Mode.ALPHA;
        Mode mode3 = Mode.ALPHA;
        for (int r3 = 0; r3 < r14; r3++) {
            int r42 = r12[r3];
            char c = ' ';
            switch (C33731.f1235x45bba1d[mode2.ordinal()]) {
                case 1:
                    if (r42 < 26) {
                        r4 = r42 + 65;
                        c = (char) r4;
                        break;
                    } else {
                        if (r42 == TEXT_COMPACTION_MODE_LATCH) {
                            mode2 = Mode.ALPHA;
                        } else if (r42 != MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                            switch (r42) {
                                case 27:
                                    mode2 = Mode.LOWER;
                                    break;
                                case 28:
                                    mode2 = Mode.MIXED;
                                    break;
                                case 29:
                                    mode = Mode.PUNCT_SHIFT;
                                    c = 0;
                                    Mode mode4 = mode;
                                    mode3 = mode2;
                                    mode2 = mode4;
                                    break;
                            }
                        } else {
                            sb.append((char) r13[r3]);
                        }
                        c = 0;
                        break;
                    }
                case 2:
                    if (r42 < 26) {
                        r4 = r42 + 97;
                        c = (char) r4;
                        break;
                    } else {
                        if (r42 == TEXT_COMPACTION_MODE_LATCH) {
                            mode2 = Mode.ALPHA;
                        } else if (r42 != MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                            switch (r42) {
                                case 27:
                                    mode = Mode.ALPHA_SHIFT;
                                    c = 0;
                                    Mode mode42 = mode;
                                    mode3 = mode2;
                                    mode2 = mode42;
                                    break;
                                case 28:
                                    mode2 = Mode.MIXED;
                                    break;
                                case 29:
                                    mode = Mode.PUNCT_SHIFT;
                                    c = 0;
                                    Mode mode422 = mode;
                                    mode3 = mode2;
                                    mode2 = mode422;
                                    break;
                            }
                        } else {
                            sb.append((char) r13[r3]);
                        }
                        c = 0;
                        break;
                    }
                case 3:
                    if (r42 < 25) {
                        c = MIXED_CHARS[r42];
                        break;
                    } else {
                        if (r42 == TEXT_COMPACTION_MODE_LATCH) {
                            mode2 = Mode.ALPHA;
                        } else if (r42 != MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                            switch (r42) {
                                case 25:
                                    mode2 = Mode.PUNCT;
                                    break;
                                case 27:
                                    mode2 = Mode.LOWER;
                                    break;
                                case 28:
                                    mode2 = Mode.ALPHA;
                                    break;
                                case 29:
                                    mode = Mode.PUNCT_SHIFT;
                                    c = 0;
                                    Mode mode4222 = mode;
                                    mode3 = mode2;
                                    mode2 = mode4222;
                                    break;
                            }
                        } else {
                            sb.append((char) r13[r3]);
                        }
                        c = 0;
                        break;
                    }
                case 4:
                    if (r42 < 29) {
                        c = PUNCT_CHARS[r42];
                        break;
                    } else {
                        if (r42 == 29) {
                            mode2 = Mode.ALPHA;
                        } else if (r42 == TEXT_COMPACTION_MODE_LATCH) {
                            mode2 = Mode.ALPHA;
                        } else if (r42 == MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                            sb.append((char) r13[r3]);
                        }
                        c = 0;
                        break;
                    }
                case 5:
                    if (r42 < 26) {
                        c = (char) (r42 + 65);
                    } else if (r42 != 26) {
                        if (r42 == TEXT_COMPACTION_MODE_LATCH) {
                            mode2 = Mode.ALPHA;
                            c = 0;
                            break;
                        }
                        mode2 = mode3;
                        c = 0;
                    }
                    mode2 = mode3;
                    break;
                case 6:
                    if (r42 < 29) {
                        c = PUNCT_CHARS[r42];
                        mode2 = mode3;
                        break;
                    } else {
                        if (r42 == 29) {
                            mode2 = Mode.ALPHA;
                        } else if (r42 != TEXT_COMPACTION_MODE_LATCH) {
                            if (r42 == MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                                sb.append((char) r13[r3]);
                            }
                            mode2 = mode3;
                        } else {
                            mode2 = Mode.ALPHA;
                        }
                        c = 0;
                        break;
                    }
                default:
                    c = 0;
                    break;
            }
            if (c != 0) {
                sb.append(c);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.zxing.pdf417.decoder.DecodedBitStreamParser$1 */
    /* loaded from: classes3.dex */
    public static /* synthetic */ class C33731 {

        /* renamed from: $SwitchMap$com$google$zxing$pdf417$decoder$DecodedBitStreamParser$Mode */
        static final /* synthetic */ int[] f1235x45bba1d;

        static {
            int[] r0 = new int[Mode.values().length];
            f1235x45bba1d = r0;
            try {
                r0[Mode.ALPHA.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1235x45bba1d[Mode.LOWER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1235x45bba1d[Mode.MIXED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1235x45bba1d[Mode.PUNCT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1235x45bba1d[Mode.ALPHA_SHIFT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1235x45bba1d[Mode.PUNCT_SHIFT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:57:0x0046 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0021 A[ADDED_TO_REGION, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static int byteCompaction(int r16, int[] r17, java.nio.charset.Charset r18, int r19, java.lang.StringBuilder r20) {
        /*
            Method dump skipped, instructions count: 262
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.decoder.DecodedBitStreamParser.byteCompaction(int, int[], java.nio.charset.Charset, int, java.lang.StringBuilder):int");
    }

    private static int numericCompaction(int[] r7, int r8, StringBuilder sb) throws FormatException {
        int[] r0 = new int[15];
        boolean z = false;
        int r3 = 0;
        while (r8 < r7[0] && !z) {
            int r4 = r8 + 1;
            int r82 = r7[r8];
            if (r4 == r7[0]) {
                z = true;
            }
            if (r82 < TEXT_COMPACTION_MODE_LATCH) {
                r0[r3] = r82;
                r3++;
            } else {
                if (r82 != TEXT_COMPACTION_MODE_LATCH && r82 != BYTE_COMPACTION_MODE_LATCH && r82 != 928) {
                    switch (r82) {
                    }
                }
                r4--;
                z = true;
            }
            if ((r3 % 15 == 0 || r82 == NUMERIC_COMPACTION_MODE_LATCH || z) && r3 > 0) {
                sb.append(decodeBase900toBase10(r0, r3));
                r3 = 0;
            }
            r8 = r4;
        }
        return r8;
    }

    private static String decodeBase900toBase10(int[] r6, int r7) throws FormatException {
        BigInteger bigInteger = BigInteger.ZERO;
        for (int r2 = 0; r2 < r7; r2++) {
            bigInteger = bigInteger.add(EXP900[(r7 - r2) - 1].multiply(BigInteger.valueOf(r6[r2])));
        }
        String bigInteger2 = bigInteger.toString();
        if (bigInteger2.charAt(0) != '1') {
            throw FormatException.getFormatInstance();
        }
        return bigInteger2.substring(1);
    }
}
