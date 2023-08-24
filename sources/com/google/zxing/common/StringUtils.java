package com.google.zxing.common;

import com.google.zxing.DecodeHintType;
import java.nio.charset.Charset;
import java.util.Map;

/* loaded from: classes3.dex */
public final class StringUtils {
    private static final boolean ASSUME_SHIFT_JIS;
    private static final String EUC_JP = "EUC_JP";
    public static final String GB2312 = "GB2312";
    private static final String ISO88591 = "ISO8859_1";
    private static final String PLATFORM_DEFAULT_ENCODING;
    public static final String SHIFT_JIS = "SJIS";
    private static final String UTF8 = "UTF8";

    static {
        String name = Charset.defaultCharset().name();
        PLATFORM_DEFAULT_ENCODING = name;
        ASSUME_SHIFT_JIS = SHIFT_JIS.equalsIgnoreCase(name) || EUC_JP.equalsIgnoreCase(name);
    }

    private StringUtils() {
    }

    public static String guessEncoding(byte[] bArr, Map<DecodeHintType, ?> map) {
        byte[] bArr2 = bArr;
        if (map != null && map.containsKey(DecodeHintType.CHARACTER_SET)) {
            return map.get(DecodeHintType.CHARACTER_SET).toString();
        }
        int length = bArr2.length;
        boolean z = true;
        int r6 = 0;
        boolean z2 = bArr2.length > 3 && bArr2[0] == -17 && bArr2[1] == -69 && bArr2[2] == -65;
        int r3 = 0;
        boolean z3 = true;
        boolean z4 = true;
        int r9 = 0;
        int r10 = 0;
        int r11 = 0;
        int r12 = 0;
        int r13 = 0;
        int r14 = 0;
        int r15 = 0;
        int r16 = 0;
        int r17 = 0;
        int r18 = 0;
        while (r9 < length && (z || z3 || z4)) {
            int r4 = bArr2[r9] & 255;
            if (z4) {
                if (r10 > 0) {
                    if ((r4 & 128) != 0) {
                        r10--;
                    }
                    z4 = false;
                } else if ((r4 & 128) != 0) {
                    if ((r4 & 64) != 0) {
                        r10++;
                        if ((r4 & 32) == 0) {
                            r12++;
                        } else {
                            r10++;
                            if ((r4 & 16) == 0) {
                                r13++;
                            } else {
                                r10++;
                                if ((r4 & 8) == 0) {
                                    r14++;
                                }
                            }
                        }
                    }
                    z4 = false;
                }
            }
            if (z) {
                if (r4 > 127 && r4 < 160) {
                    z = false;
                } else if (r4 > 159 && (r4 < 192 || r4 == 215 || r4 == 247)) {
                    r16++;
                }
            }
            if (z3) {
                if (r11 > 0) {
                    if (r4 >= 64 && r4 != 127 && r4 <= 252) {
                        r11--;
                    }
                    z3 = false;
                } else {
                    if (r4 != 128 && r4 != 160 && r4 <= 239) {
                        if (r4 <= 160 || r4 >= 224) {
                            if (r4 > 127) {
                                r11++;
                                int r0 = r17 + 1;
                                if (r0 > r6) {
                                    r6 = r0;
                                    r17 = r6;
                                } else {
                                    r17 = r0;
                                }
                            } else {
                                r17 = 0;
                            }
                            r18 = 0;
                        } else {
                            r3++;
                            int r02 = r18 + 1;
                            if (r02 > r15) {
                                r15 = r02;
                                r18 = r15;
                            } else {
                                r18 = r02;
                            }
                            r17 = 0;
                        }
                    }
                    z3 = false;
                }
            }
            r9++;
            bArr2 = bArr;
        }
        if (z4 && r10 > 0) {
            z4 = false;
        }
        if (z3 && r11 > 0) {
            z3 = false;
        }
        return (!z4 || (!z2 && (r12 + r13) + r14 <= 0)) ? (!z3 || (!ASSUME_SHIFT_JIS && r15 < 3 && r6 < 3)) ? (z && z3) ? (!(r15 == 2 && r3 == 2) && r16 * 10 < length) ? ISO88591 : SHIFT_JIS : z ? ISO88591 : z3 ? SHIFT_JIS : z4 ? UTF8 : PLATFORM_DEFAULT_ENCODING : SHIFT_JIS : UTF8;
    }
}
