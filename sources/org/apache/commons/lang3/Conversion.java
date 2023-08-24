package org.apache.commons.lang3;

import java.util.UUID;
import kotlin.UShort;
import okhttp3.internal.p026ws.WebSocketProtocol;
import org.bouncycastle.asn1.cmc.BodyPartID;

/* loaded from: classes5.dex */
public class Conversion {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final boolean[] TTTT = {true, true, true, true};
    private static final boolean[] FTTT = {false, true, true, true};
    private static final boolean[] TFTT = {true, false, true, true};
    private static final boolean[] FFTT = {false, false, true, true};
    private static final boolean[] TTFT = {true, true, false, true};
    private static final boolean[] FTFT = {false, true, false, true};
    private static final boolean[] TFFT = {true, false, false, true};
    private static final boolean[] FFFT = {false, false, false, true};
    private static final boolean[] TTTF = {true, true, true, false};
    private static final boolean[] FTTF = {false, true, true, false};
    private static final boolean[] TFTF = {true, false, true, false};
    private static final boolean[] FFTF = {false, false, true, false};
    private static final boolean[] TTFF = {true, true, false, false};
    private static final boolean[] FTFF = {false, true, false, false};
    private static final boolean[] TFFF = {true, false, false, false};
    private static final boolean[] FFFF = {false, false, false, false};

    public static int hexDigitToInt(char c) {
        int digit = Character.digit(c, 16);
        if (digit >= 0) {
            return digit;
        }
        throw new IllegalArgumentException("Cannot interpret '" + c + "' as a hexadecimal digit");
    }

    public static int hexDigitMsb0ToInt(char c) {
        switch (c) {
            case '0':
                return 0;
            case '1':
                return 8;
            case '2':
                return 4;
            case '3':
                return 12;
            case '4':
                return 2;
            case '5':
                return 10;
            case '6':
                return 6;
            case '7':
                return 14;
            case '8':
                return 1;
            case '9':
                return 9;
            default:
                switch (c) {
                    case 'A':
                        return 5;
                    case 'B':
                        return 13;
                    case 'C':
                        return 3;
                    case 'D':
                        return 11;
                    case 'E':
                        return 7;
                    case 'F':
                        return 15;
                    default:
                        switch (c) {
                            case 'a':
                                return 5;
                            case 'b':
                                return 13;
                            case 'c':
                                return 3;
                            case 'd':
                                return 11;
                            case 'e':
                                return 7;
                            case 'f':
                                return 15;
                            default:
                                throw new IllegalArgumentException("Cannot interpret '" + c + "' as a hexadecimal digit");
                        }
                }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x002e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean[] hexDigitToBinary(char r3) {
        /*
            switch(r3) {
                case 48: goto Lac;
                case 49: goto La3;
                case 50: goto L9a;
                case 51: goto L91;
                case 52: goto L88;
                case 53: goto L7f;
                case 54: goto L76;
                case 55: goto L6d;
                case 56: goto L64;
                case 57: goto L5b;
                default: goto L3;
            }
        L3:
            switch(r3) {
                case 65: goto L52;
                case 66: goto L49;
                case 67: goto L40;
                case 68: goto L37;
                case 69: goto L2e;
                case 70: goto L25;
                default: goto L6;
            }
        L6:
            switch(r3) {
                case 97: goto L52;
                case 98: goto L49;
                case 99: goto L40;
                case 100: goto L37;
                case 101: goto L2e;
                case 102: goto L25;
                default: goto L9;
            }
        L9:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Cannot interpret '"
            r1.append(r2)
            r1.append(r3)
            java.lang.String r3 = "' as a hexadecimal digit"
            r1.append(r3)
            java.lang.String r3 = r1.toString()
            r0.<init>(r3)
            throw r0
        L25:
            boolean[] r3 = org.apache.commons.lang3.Conversion.TTTT
            java.lang.Object r3 = r3.clone()
            boolean[] r3 = (boolean[]) r3
            return r3
        L2e:
            boolean[] r3 = org.apache.commons.lang3.Conversion.FTTT
            java.lang.Object r3 = r3.clone()
            boolean[] r3 = (boolean[]) r3
            return r3
        L37:
            boolean[] r3 = org.apache.commons.lang3.Conversion.TFTT
            java.lang.Object r3 = r3.clone()
            boolean[] r3 = (boolean[]) r3
            return r3
        L40:
            boolean[] r3 = org.apache.commons.lang3.Conversion.FFTT
            java.lang.Object r3 = r3.clone()
            boolean[] r3 = (boolean[]) r3
            return r3
        L49:
            boolean[] r3 = org.apache.commons.lang3.Conversion.TTFT
            java.lang.Object r3 = r3.clone()
            boolean[] r3 = (boolean[]) r3
            return r3
        L52:
            boolean[] r3 = org.apache.commons.lang3.Conversion.FTFT
            java.lang.Object r3 = r3.clone()
            boolean[] r3 = (boolean[]) r3
            return r3
        L5b:
            boolean[] r3 = org.apache.commons.lang3.Conversion.TFFT
            java.lang.Object r3 = r3.clone()
            boolean[] r3 = (boolean[]) r3
            return r3
        L64:
            boolean[] r3 = org.apache.commons.lang3.Conversion.FFFT
            java.lang.Object r3 = r3.clone()
            boolean[] r3 = (boolean[]) r3
            return r3
        L6d:
            boolean[] r3 = org.apache.commons.lang3.Conversion.TTTF
            java.lang.Object r3 = r3.clone()
            boolean[] r3 = (boolean[]) r3
            return r3
        L76:
            boolean[] r3 = org.apache.commons.lang3.Conversion.FTTF
            java.lang.Object r3 = r3.clone()
            boolean[] r3 = (boolean[]) r3
            return r3
        L7f:
            boolean[] r3 = org.apache.commons.lang3.Conversion.TFTF
            java.lang.Object r3 = r3.clone()
            boolean[] r3 = (boolean[]) r3
            return r3
        L88:
            boolean[] r3 = org.apache.commons.lang3.Conversion.FFTF
            java.lang.Object r3 = r3.clone()
            boolean[] r3 = (boolean[]) r3
            return r3
        L91:
            boolean[] r3 = org.apache.commons.lang3.Conversion.TTFF
            java.lang.Object r3 = r3.clone()
            boolean[] r3 = (boolean[]) r3
            return r3
        L9a:
            boolean[] r3 = org.apache.commons.lang3.Conversion.FTFF
            java.lang.Object r3 = r3.clone()
            boolean[] r3 = (boolean[]) r3
            return r3
        La3:
            boolean[] r3 = org.apache.commons.lang3.Conversion.TFFF
            java.lang.Object r3 = r3.clone()
            boolean[] r3 = (boolean[]) r3
            return r3
        Lac:
            boolean[] r3 = org.apache.commons.lang3.Conversion.FFFF
            java.lang.Object r3 = r3.clone()
            boolean[] r3 = (boolean[]) r3
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang3.Conversion.hexDigitToBinary(char):boolean[]");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x002e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean[] hexDigitMsb0ToBinary(char r3) {
        /*
            switch(r3) {
                case 48: goto Lac;
                case 49: goto La3;
                case 50: goto L9a;
                case 51: goto L91;
                case 52: goto L88;
                case 53: goto L7f;
                case 54: goto L76;
                case 55: goto L6d;
                case 56: goto L64;
                case 57: goto L5b;
                default: goto L3;
            }
        L3:
            switch(r3) {
                case 65: goto L52;
                case 66: goto L49;
                case 67: goto L40;
                case 68: goto L37;
                case 69: goto L2e;
                case 70: goto L25;
                default: goto L6;
            }
        L6:
            switch(r3) {
                case 97: goto L52;
                case 98: goto L49;
                case 99: goto L40;
                case 100: goto L37;
                case 101: goto L2e;
                case 102: goto L25;
                default: goto L9;
            }
        L9:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Cannot interpret '"
            r1.append(r2)
            r1.append(r3)
            java.lang.String r3 = "' as a hexadecimal digit"
            r1.append(r3)
            java.lang.String r3 = r1.toString()
            r0.<init>(r3)
            throw r0
        L25:
            boolean[] r3 = org.apache.commons.lang3.Conversion.TTTT
            java.lang.Object r3 = r3.clone()
            boolean[] r3 = (boolean[]) r3
            return r3
        L2e:
            boolean[] r3 = org.apache.commons.lang3.Conversion.TTTF
            java.lang.Object r3 = r3.clone()
            boolean[] r3 = (boolean[]) r3
            return r3
        L37:
            boolean[] r3 = org.apache.commons.lang3.Conversion.TTFT
            java.lang.Object r3 = r3.clone()
            boolean[] r3 = (boolean[]) r3
            return r3
        L40:
            boolean[] r3 = org.apache.commons.lang3.Conversion.TTFF
            java.lang.Object r3 = r3.clone()
            boolean[] r3 = (boolean[]) r3
            return r3
        L49:
            boolean[] r3 = org.apache.commons.lang3.Conversion.TFTT
            java.lang.Object r3 = r3.clone()
            boolean[] r3 = (boolean[]) r3
            return r3
        L52:
            boolean[] r3 = org.apache.commons.lang3.Conversion.TFTF
            java.lang.Object r3 = r3.clone()
            boolean[] r3 = (boolean[]) r3
            return r3
        L5b:
            boolean[] r3 = org.apache.commons.lang3.Conversion.TFFT
            java.lang.Object r3 = r3.clone()
            boolean[] r3 = (boolean[]) r3
            return r3
        L64:
            boolean[] r3 = org.apache.commons.lang3.Conversion.TFFF
            java.lang.Object r3 = r3.clone()
            boolean[] r3 = (boolean[]) r3
            return r3
        L6d:
            boolean[] r3 = org.apache.commons.lang3.Conversion.FTTT
            java.lang.Object r3 = r3.clone()
            boolean[] r3 = (boolean[]) r3
            return r3
        L76:
            boolean[] r3 = org.apache.commons.lang3.Conversion.FTTF
            java.lang.Object r3 = r3.clone()
            boolean[] r3 = (boolean[]) r3
            return r3
        L7f:
            boolean[] r3 = org.apache.commons.lang3.Conversion.FTFT
            java.lang.Object r3 = r3.clone()
            boolean[] r3 = (boolean[]) r3
            return r3
        L88:
            boolean[] r3 = org.apache.commons.lang3.Conversion.FTFF
            java.lang.Object r3 = r3.clone()
            boolean[] r3 = (boolean[]) r3
            return r3
        L91:
            boolean[] r3 = org.apache.commons.lang3.Conversion.FFTT
            java.lang.Object r3 = r3.clone()
            boolean[] r3 = (boolean[]) r3
            return r3
        L9a:
            boolean[] r3 = org.apache.commons.lang3.Conversion.FFTF
            java.lang.Object r3 = r3.clone()
            boolean[] r3 = (boolean[]) r3
            return r3
        La3:
            boolean[] r3 = org.apache.commons.lang3.Conversion.FFFT
            java.lang.Object r3 = r3.clone()
            boolean[] r3 = (boolean[]) r3
            return r3
        Lac:
            boolean[] r3 = org.apache.commons.lang3.Conversion.FFFF
            java.lang.Object r3 = r3.clone()
            boolean[] r3 = (boolean[]) r3
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang3.Conversion.hexDigitMsb0ToBinary(char):boolean[]");
    }

    public static char binaryToHexDigit(boolean[] zArr) {
        return binaryToHexDigit(zArr, 0);
    }

    public static char binaryToHexDigit(boolean[] zArr, int r3) {
        if (zArr.length == 0) {
            throw new IllegalArgumentException("Cannot convert an empty array.");
        }
        int r1 = r3 + 3;
        if (zArr.length > r1 && zArr[r1]) {
            int r12 = r3 + 2;
            if (zArr.length <= r12 || !zArr[r12]) {
                int r13 = r3 + 1;
                return (zArr.length <= r13 || !zArr[r13]) ? zArr[r3] ? '9' : '8' : zArr[r3] ? 'b' : 'a';
            }
            int r14 = r3 + 1;
            return (zArr.length <= r14 || !zArr[r14]) ? zArr[r3] ? 'd' : 'c' : zArr[r3] ? 'f' : 'e';
        }
        int r15 = r3 + 2;
        if (zArr.length <= r15 || !zArr[r15]) {
            int r16 = r3 + 1;
            return (zArr.length <= r16 || !zArr[r16]) ? zArr[r3] ? '1' : '0' : zArr[r3] ? '3' : '2';
        }
        int r17 = r3 + 1;
        return (zArr.length <= r17 || !zArr[r17]) ? zArr[r3] ? '5' : '4' : zArr[r3] ? '7' : '6';
    }

    public static char binaryToHexDigitMsb0_4bits(boolean[] zArr) {
        return binaryToHexDigitMsb0_4bits(zArr, 0);
    }

    public static char binaryToHexDigitMsb0_4bits(boolean[] zArr, int r4) {
        if (zArr.length > 8) {
            throw new IllegalArgumentException("src.length>8: src.length=" + zArr.length);
        } else if (zArr.length - r4 >= 4) {
            return zArr[r4 + 3] ? zArr[r4 + 2] ? zArr[r4 + 1] ? zArr[r4] ? 'f' : '7' : zArr[r4] ? 'b' : '3' : zArr[r4 + 1] ? zArr[r4] ? 'd' : '5' : zArr[r4] ? '9' : '1' : zArr[r4 + 2] ? zArr[r4 + 1] ? zArr[r4] ? 'e' : '6' : zArr[r4] ? 'a' : '2' : zArr[r4 + 1] ? zArr[r4] ? 'c' : '4' : zArr[r4] ? '8' : '0';
        } else {
            throw new IllegalArgumentException("src.length-srcPos<4: src.length=" + zArr.length + ", srcPos=" + r4);
        }
    }

    public static char binaryBeMsb0ToHexDigit(boolean[] zArr) {
        return binaryBeMsb0ToHexDigit(zArr, 0);
    }

    public static char binaryBeMsb0ToHexDigit(boolean[] zArr, int r5) {
        if (zArr.length == 0) {
            throw new IllegalArgumentException("Cannot convert an empty array.");
        }
        int length = ((zArr.length - 1) - r5) + 1;
        int min = Math.min(4, length);
        boolean[] zArr2 = new boolean[4];
        System.arraycopy(zArr, length - min, zArr2, 4 - min, min);
        return zArr2[0] ? zArr2[1] ? zArr2[2] ? zArr2[3] ? 'f' : 'e' : zArr2[3] ? 'd' : 'c' : zArr2[2] ? zArr2[3] ? 'b' : 'a' : zArr2[3] ? '9' : '8' : zArr2[1] ? zArr2[2] ? zArr2[3] ? '7' : '6' : zArr2[3] ? '5' : '4' : zArr2[2] ? zArr2[3] ? '3' : '2' : zArr2[3] ? '1' : '0';
    }

    public static char intToHexDigit(int r3) {
        char forDigit = Character.forDigit(r3, 16);
        if (forDigit != 0) {
            return forDigit;
        }
        throw new IllegalArgumentException("nibble value not between 0 and 15: " + r3);
    }

    public static char intToHexDigitMsb0(int r3) {
        switch (r3) {
            case 0:
                return '0';
            case 1:
                return '8';
            case 2:
                return '4';
            case 3:
                return 'c';
            case 4:
                return '2';
            case 5:
                return 'a';
            case 6:
                return '6';
            case 7:
                return 'e';
            case 8:
                return '1';
            case 9:
                return '9';
            case 10:
                return '5';
            case 11:
                return 'd';
            case 12:
                return '3';
            case 13:
                return 'b';
            case 14:
                return '7';
            case 15:
                return 'f';
            default:
                throw new IllegalArgumentException("nibble value not between 0 and 15: " + r3);
        }
    }

    public static long intArrayToLong(int[] r6, int r7, long j, int r10, int r11) {
        if ((r6.length == 0 && r7 == 0) || r11 == 0) {
            return j;
        }
        if (((r11 - 1) * 32) + r10 < 64) {
            for (int r0 = 0; r0 < r11; r0++) {
                int r1 = (r0 * 32) + r10;
                j = (j & (~(BodyPartID.bodyIdMax << r1))) | ((r6[r0 + r7] & BodyPartID.bodyIdMax) << r1);
            }
            return j;
        }
        throw new IllegalArgumentException("(nInts-1)*32+dstPos is greater or equal to than 64");
    }

    public static long shortArrayToLong(short[] sArr, int r7, long j, int r10, int r11) {
        if ((sArr.length == 0 && r7 == 0) || r11 == 0) {
            return j;
        }
        if (((r11 - 1) * 16) + r10 < 64) {
            for (int r0 = 0; r0 < r11; r0++) {
                int r1 = (r0 * 16) + r10;
                j = (j & (~(WebSocketProtocol.PAYLOAD_SHORT_MAX << r1))) | ((sArr[r0 + r7] & WebSocketProtocol.PAYLOAD_SHORT_MAX) << r1);
            }
            return j;
        }
        throw new IllegalArgumentException("(nShorts-1)*16+dstPos is greater or equal to than 64");
    }

    public static int shortArrayToInt(short[] sArr, int r5, int r6, int r7, int r8) {
        if ((sArr.length == 0 && r5 == 0) || r8 == 0) {
            return r6;
        }
        if (((r8 - 1) * 16) + r7 < 32) {
            for (int r0 = 0; r0 < r8; r0++) {
                int r1 = (r0 * 16) + r7;
                r6 = (r6 & (~(65535 << r1))) | ((sArr[r0 + r5] & UShort.MAX_VALUE) << r1);
            }
            return r6;
        }
        throw new IllegalArgumentException("(nShorts-1)*16+dstPos is greater or equal to than 32");
    }

    public static long byteArrayToLong(byte[] bArr, int r7, long j, int r10, int r11) {
        if ((bArr.length == 0 && r7 == 0) || r11 == 0) {
            return j;
        }
        if (((r11 - 1) * 8) + r10 < 64) {
            for (int r0 = 0; r0 < r11; r0++) {
                int r1 = (r0 * 8) + r10;
                j = (j & (~(255 << r1))) | ((bArr[r0 + r7] & 255) << r1);
            }
            return j;
        }
        throw new IllegalArgumentException("(nBytes-1)*8+dstPos is greater or equal to than 64");
    }

    public static int byteArrayToInt(byte[] bArr, int r5, int r6, int r7, int r8) {
        if ((bArr.length == 0 && r5 == 0) || r8 == 0) {
            return r6;
        }
        if (((r8 - 1) * 8) + r7 < 32) {
            for (int r0 = 0; r0 < r8; r0++) {
                int r1 = (r0 * 8) + r7;
                r6 = (r6 & (~(255 << r1))) | ((bArr[r0 + r5] & 255) << r1);
            }
            return r6;
        }
        throw new IllegalArgumentException("(nBytes-1)*8+dstPos is greater or equal to than 32");
    }

    public static short byteArrayToShort(byte[] bArr, int r5, short s, int r7, int r8) {
        if ((bArr.length == 0 && r5 == 0) || r8 == 0) {
            return s;
        }
        if (((r8 - 1) * 8) + r7 < 16) {
            for (int r0 = 0; r0 < r8; r0++) {
                int r1 = (r0 * 8) + r7;
                s = (short) ((s & (~(255 << r1))) | ((bArr[r0 + r5] & 255) << r1));
            }
            return s;
        }
        throw new IllegalArgumentException("(nBytes-1)*8+dstPos is greater or equal to than 16");
    }

    public static long hexToLong(String str, int r7, long j, int r10, int r11) {
        if (r11 == 0) {
            return j;
        }
        if (((r11 - 1) * 4) + r10 < 64) {
            for (int r0 = 0; r0 < r11; r0++) {
                int r1 = (r0 * 4) + r10;
                j = (j & (~(15 << r1))) | ((hexDigitToInt(str.charAt(r0 + r7)) & 15) << r1);
            }
            return j;
        }
        throw new IllegalArgumentException("(nHexs-1)*4+dstPos is greater or equal to than 64");
    }

    public static int hexToInt(String str, int r5, int r6, int r7, int r8) {
        if (r8 == 0) {
            return r6;
        }
        if (((r8 - 1) * 4) + r7 < 32) {
            for (int r0 = 0; r0 < r8; r0++) {
                int r1 = (r0 * 4) + r7;
                r6 = (r6 & (~(15 << r1))) | ((hexDigitToInt(str.charAt(r0 + r5)) & 15) << r1);
            }
            return r6;
        }
        throw new IllegalArgumentException("(nHexs-1)*4+dstPos is greater or equal to than 32");
    }

    public static short hexToShort(String str, int r5, short s, int r7, int r8) {
        if (r8 == 0) {
            return s;
        }
        if (((r8 - 1) * 4) + r7 < 16) {
            for (int r0 = 0; r0 < r8; r0++) {
                int r1 = (r0 * 4) + r7;
                s = (short) ((s & (~(15 << r1))) | ((hexDigitToInt(str.charAt(r0 + r5)) & 15) << r1));
            }
            return s;
        }
        throw new IllegalArgumentException("(nHexs-1)*4+dstPos is greater or equal to than 16");
    }

    public static byte hexToByte(String str, int r5, byte b, int r7, int r8) {
        if (r8 == 0) {
            return b;
        }
        if (((r8 - 1) * 4) + r7 < 8) {
            for (int r0 = 0; r0 < r8; r0++) {
                int r1 = (r0 * 4) + r7;
                b = (byte) ((b & (~(15 << r1))) | ((hexDigitToInt(str.charAt(r0 + r5)) & 15) << r1));
            }
            return b;
        }
        throw new IllegalArgumentException("(nHexs-1)*4+dstPos is greater or equal to than 8");
    }

    public static long binaryToLong(boolean[] zArr, int r8, long j, int r11, int r12) {
        if ((zArr.length == 0 && r8 == 0) || r12 == 0) {
            return j;
        }
        if ((r12 - 1) + r11 < 64) {
            for (int r0 = 0; r0 < r12; r0++) {
                int r1 = r0 + r11;
                j = (j & (~(1 << r1))) | ((zArr[r0 + r8] ? 1L : 0L) << r1);
            }
            return j;
        }
        throw new IllegalArgumentException("nBools-1+dstPos is greater or equal to than 64");
    }

    public static int binaryToInt(boolean[] zArr, int r5, int r6, int r7, int r8) {
        if ((zArr.length == 0 && r5 == 0) || r8 == 0) {
            return r6;
        }
        if ((r8 - 1) + r7 < 32) {
            for (int r0 = 0; r0 < r8; r0++) {
                int r1 = r0 + r7;
                r6 = (r6 & (~(1 << r1))) | ((zArr[r0 + r5] ? 1 : 0) << r1);
            }
            return r6;
        }
        throw new IllegalArgumentException("nBools-1+dstPos is greater or equal to than 32");
    }

    public static short binaryToShort(boolean[] zArr, int r5, short s, int r7, int r8) {
        if ((zArr.length == 0 && r5 == 0) || r8 == 0) {
            return s;
        }
        if ((r8 - 1) + r7 < 16) {
            for (int r0 = 0; r0 < r8; r0++) {
                int r1 = r0 + r7;
                s = (short) ((s & (~(1 << r1))) | ((zArr[r0 + r5] ? 1 : 0) << r1));
            }
            return s;
        }
        throw new IllegalArgumentException("nBools-1+dstPos is greater or equal to than 16");
    }

    public static byte binaryToByte(boolean[] zArr, int r5, byte b, int r7, int r8) {
        if ((zArr.length == 0 && r5 == 0) || r8 == 0) {
            return b;
        }
        if ((r8 - 1) + r7 < 8) {
            for (int r0 = 0; r0 < r8; r0++) {
                int r1 = r0 + r7;
                b = (byte) ((b & (~(1 << r1))) | ((zArr[r0 + r5] ? 1 : 0) << r1));
            }
            return b;
        }
        throw new IllegalArgumentException("nBools-1+dstPos is greater or equal to than 8");
    }

    public static int[] longToIntArray(long j, int r9, int[] r10, int r11, int r12) {
        if (r12 == 0) {
            return r10;
        }
        if (((r12 - 1) * 32) + r9 < 64) {
            for (int r0 = 0; r0 < r12; r0++) {
                r10[r11 + r0] = (int) ((-1) & (j >> ((r0 * 32) + r9)));
            }
            return r10;
        }
        throw new IllegalArgumentException("(nInts-1)*32+srcPos is greater or equal to than 64");
    }

    public static short[] longToShortArray(long j, int r9, short[] sArr, int r11, int r12) {
        if (r12 == 0) {
            return sArr;
        }
        if (((r12 - 1) * 16) + r9 < 64) {
            for (int r0 = 0; r0 < r12; r0++) {
                sArr[r11 + r0] = (short) (WebSocketProtocol.PAYLOAD_SHORT_MAX & (j >> ((r0 * 16) + r9)));
            }
            return sArr;
        }
        throw new IllegalArgumentException("(nShorts-1)*16+srcPos is greater or equal to than 64");
    }

    public static short[] intToShortArray(int r4, int r5, short[] sArr, int r7, int r8) {
        if (r8 == 0) {
            return sArr;
        }
        if (((r8 - 1) * 16) + r5 < 32) {
            for (int r0 = 0; r0 < r8; r0++) {
                sArr[r7 + r0] = (short) ((r4 >> ((r0 * 16) + r5)) & 65535);
            }
            return sArr;
        }
        throw new IllegalArgumentException("(nShorts-1)*16+srcPos is greater or equal to than 32");
    }

    public static byte[] longToByteArray(long j, int r9, byte[] bArr, int r11, int r12) {
        if (r12 == 0) {
            return bArr;
        }
        if (((r12 - 1) * 8) + r9 < 64) {
            for (int r0 = 0; r0 < r12; r0++) {
                bArr[r11 + r0] = (byte) (255 & (j >> ((r0 * 8) + r9)));
            }
            return bArr;
        }
        throw new IllegalArgumentException("(nBytes-1)*8+srcPos is greater or equal to than 64");
    }

    public static byte[] intToByteArray(int r3, int r4, byte[] bArr, int r6, int r7) {
        if (r7 == 0) {
            return bArr;
        }
        if (((r7 - 1) * 8) + r4 < 32) {
            for (int r0 = 0; r0 < r7; r0++) {
                bArr[r6 + r0] = (byte) ((r3 >> ((r0 * 8) + r4)) & 255);
            }
            return bArr;
        }
        throw new IllegalArgumentException("(nBytes-1)*8+srcPos is greater or equal to than 32");
    }

    public static byte[] shortToByteArray(short s, int r4, byte[] bArr, int r6, int r7) {
        if (r7 == 0) {
            return bArr;
        }
        if (((r7 - 1) * 8) + r4 < 16) {
            for (int r0 = 0; r0 < r7; r0++) {
                bArr[r6 + r0] = (byte) ((s >> ((r0 * 8) + r4)) & 255);
            }
            return bArr;
        }
        throw new IllegalArgumentException("(nBytes-1)*8+srcPos is greater or equal to than 16");
    }

    public static String longToHex(long j, int r9, String str, int r11, int r12) {
        if (r12 == 0) {
            return str;
        }
        if (((r12 - 1) * 4) + r9 >= 64) {
            throw new IllegalArgumentException("(nHexs-1)*4+srcPos is greater or equal to than 64");
        }
        StringBuilder sb = new StringBuilder(str);
        int length = sb.length();
        for (int r1 = 0; r1 < r12; r1++) {
            int r3 = (int) ((j >> ((r1 * 4) + r9)) & 15);
            int r2 = r11 + r1;
            if (r2 == length) {
                length++;
                sb.append(intToHexDigit(r3));
            } else {
                sb.setCharAt(r2, intToHexDigit(r3));
            }
        }
        return sb.toString();
    }

    public static String intToHex(int r4, int r5, String str, int r7, int r8) {
        if (r8 == 0) {
            return str;
        }
        if (((r8 - 1) * 4) + r5 >= 32) {
            throw new IllegalArgumentException("(nHexs-1)*4+srcPos is greater or equal to than 32");
        }
        StringBuilder sb = new StringBuilder(str);
        int length = sb.length();
        for (int r1 = 0; r1 < r8; r1++) {
            int r2 = (r4 >> ((r1 * 4) + r5)) & 15;
            int r3 = r7 + r1;
            if (r3 == length) {
                length++;
                sb.append(intToHexDigit(r2));
            } else {
                sb.setCharAt(r3, intToHexDigit(r2));
            }
        }
        return sb.toString();
    }

    public static String shortToHex(short s, int r5, String str, int r7, int r8) {
        if (r8 == 0) {
            return str;
        }
        if (((r8 - 1) * 4) + r5 >= 16) {
            throw new IllegalArgumentException("(nHexs-1)*4+srcPos is greater or equal to than 16");
        }
        StringBuilder sb = new StringBuilder(str);
        int length = sb.length();
        for (int r1 = 0; r1 < r8; r1++) {
            int r2 = (s >> ((r1 * 4) + r5)) & 15;
            int r3 = r7 + r1;
            if (r3 == length) {
                length++;
                sb.append(intToHexDigit(r2));
            } else {
                sb.setCharAt(r3, intToHexDigit(r2));
            }
        }
        return sb.toString();
    }

    public static String byteToHex(byte b, int r5, String str, int r7, int r8) {
        if (r8 == 0) {
            return str;
        }
        if (((r8 - 1) * 4) + r5 >= 8) {
            throw new IllegalArgumentException("(nHexs-1)*4+srcPos is greater or equal to than 8");
        }
        StringBuilder sb = new StringBuilder(str);
        int length = sb.length();
        for (int r1 = 0; r1 < r8; r1++) {
            int r2 = (b >> ((r1 * 4) + r5)) & 15;
            int r3 = r7 + r1;
            if (r3 == length) {
                length++;
                sb.append(intToHexDigit(r2));
            } else {
                sb.setCharAt(r3, intToHexDigit(r2));
            }
        }
        return sb.toString();
    }

    public static boolean[] longToBinary(long j, int r10, boolean[] zArr, int r12, int r13) {
        if (r13 == 0) {
            return zArr;
        }
        if ((r13 - 1) + r10 < 64) {
            for (int r1 = 0; r1 < r13; r1++) {
                zArr[r12 + r1] = (1 & (j >> (r1 + r10))) != 0;
            }
            return zArr;
        }
        throw new IllegalArgumentException("nBools-1+srcPos is greater or equal to than 64");
    }

    public static boolean[] intToBinary(int r5, int r6, boolean[] zArr, int r8, int r9) {
        if (r9 == 0) {
            return zArr;
        }
        if ((r9 - 1) + r6 < 32) {
            for (int r1 = 0; r1 < r9; r1++) {
                int r3 = r8 + r1;
                boolean z = true;
                if (((r5 >> (r1 + r6)) & 1) == 0) {
                    z = false;
                }
                zArr[r3] = z;
            }
            return zArr;
        }
        throw new IllegalArgumentException("nBools-1+srcPos is greater or equal to than 32");
    }

    public static boolean[] shortToBinary(short s, int r6, boolean[] zArr, int r8, int r9) {
        if (r9 == 0) {
            return zArr;
        }
        if ((r9 - 1) + r6 < 16) {
            for (int r1 = 0; r1 < r9; r1++) {
                int r3 = r8 + r1;
                boolean z = true;
                if (((s >> (r1 + r6)) & 1) == 0) {
                    z = false;
                }
                zArr[r3] = z;
            }
            return zArr;
        }
        throw new IllegalArgumentException("nBools-1+srcPos is greater or equal to than 16");
    }

    public static boolean[] byteToBinary(byte b, int r6, boolean[] zArr, int r8, int r9) {
        if (r9 == 0) {
            return zArr;
        }
        if ((r9 - 1) + r6 < 8) {
            for (int r1 = 0; r1 < r9; r1++) {
                int r3 = r8 + r1;
                boolean z = true;
                if (((b >> (r1 + r6)) & 1) == 0) {
                    z = false;
                }
                zArr[r3] = z;
            }
            return zArr;
        }
        throw new IllegalArgumentException("nBools-1+srcPos is greater or equal to than 8");
    }

    public static byte[] uuidToByteArray(UUID r7, byte[] bArr, int r9, int r10) {
        if (r10 == 0) {
            return bArr;
        }
        if (r10 > 16) {
            throw new IllegalArgumentException("nBytes is greater than 16");
        }
        longToByteArray(r7.getMostSignificantBits(), 0, bArr, r9, r10 > 8 ? 8 : r10);
        if (r10 >= 8) {
            longToByteArray(r7.getLeastSignificantBits(), 0, bArr, r9 + 8, r10 - 8);
        }
        return bArr;
    }

    public static UUID byteArrayToUuid(byte[] bArr, int r10) {
        if (bArr.length - r10 < 16) {
            throw new IllegalArgumentException("Need at least 16 bytes for UUID");
        }
        return new UUID(byteArrayToLong(bArr, r10, 0L, 0, 8), byteArrayToLong(bArr, r10 + 8, 0L, 0, 8));
    }
}
