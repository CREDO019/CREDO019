package com.fasterxml.jackson.core.p009io;

import com.google.android.exoplayer2.extractor.p011ts.PsExtractor;
import java.util.Arrays;
import org.apache.commons.p028io.IOUtils;

/* renamed from: com.fasterxml.jackson.core.io.CharTypes */
/* loaded from: classes.dex */
public final class CharTypes {

    /* renamed from: HB */
    private static final byte[] f178HB;

    /* renamed from: HC */
    private static final char[] f179HC;
    private static final int[] sHexValues;
    private static final int[] sInputCodes;
    private static final int[] sInputCodesComment;
    private static final int[] sInputCodesJsNames;
    private static final int[] sInputCodesUTF8;
    private static final int[] sInputCodesUtf8JsNames;
    private static final int[] sInputCodesWS;
    private static final int[] sOutputEscapes128;

    static {
        char[] charArray = "0123456789ABCDEF".toCharArray();
        f179HC = charArray;
        int length = charArray.length;
        f178HB = new byte[length];
        for (int r2 = 0; r2 < length; r2++) {
            f178HB[r2] = (byte) f179HC[r2];
        }
        int[] r22 = new int[256];
        for (int r3 = 0; r3 < 32; r3++) {
            r22[r3] = -1;
        }
        r22[34] = 1;
        r22[92] = 1;
        sInputCodes = r22;
        int length2 = r22.length;
        int[] r9 = new int[length2];
        System.arraycopy(r22, 0, r9, 0, length2);
        for (int r8 = 128; r8 < 256; r8++) {
            r9[r8] = (r8 & 224) == 192 ? 2 : (r8 & PsExtractor.VIDEO_STREAM_MASK) == 224 ? 3 : (r8 & 248) == 240 ? 4 : -1;
        }
        sInputCodesUTF8 = r9;
        int[] r82 = new int[256];
        Arrays.fill(r82, -1);
        for (int r92 = 33; r92 < 256; r92++) {
            if (Character.isJavaIdentifierPart((char) r92)) {
                r82[r92] = 0;
            }
        }
        r82[64] = 0;
        r82[35] = 0;
        r82[42] = 0;
        r82[45] = 0;
        r82[43] = 0;
        sInputCodesJsNames = r82;
        int[] r11 = new int[256];
        System.arraycopy(r82, 0, r11, 0, 256);
        Arrays.fill(r11, 128, 128, 0);
        sInputCodesUtf8JsNames = r11;
        int[] r83 = new int[256];
        int[] r112 = sInputCodesUTF8;
        System.arraycopy(r112, 128, r83, 128, 128);
        Arrays.fill(r83, 0, 32, -1);
        r83[9] = 0;
        r83[10] = 10;
        r83[13] = 13;
        r83[42] = 42;
        sInputCodesComment = r83;
        int[] r0 = new int[256];
        System.arraycopy(r112, 128, r0, 128, 128);
        Arrays.fill(r0, 0, 32, -1);
        r0[32] = 1;
        r0[9] = 1;
        r0[10] = 10;
        r0[13] = 13;
        r0[47] = 47;
        r0[35] = 35;
        sInputCodesWS = r0;
        int[] r02 = new int[128];
        for (int r6 = 0; r6 < 32; r6++) {
            r02[r6] = -1;
        }
        r02[34] = 34;
        r02[92] = 92;
        r02[8] = 98;
        r02[9] = 116;
        r02[12] = 102;
        r02[10] = 110;
        r02[13] = 114;
        sOutputEscapes128 = r02;
        int[] r03 = new int[128];
        sHexValues = r03;
        Arrays.fill(r03, -1);
        for (int r04 = 0; r04 < 10; r04++) {
            sHexValues[r04 + 48] = r04;
        }
        for (int r1 = 0; r1 < 6; r1++) {
            int[] r05 = sHexValues;
            int r32 = r1 + 10;
            r05[r1 + 97] = r32;
            r05[r1 + 65] = r32;
        }
    }

    public static int[] getInputCodeLatin1() {
        return sInputCodes;
    }

    public static int[] getInputCodeUtf8() {
        return sInputCodesUTF8;
    }

    public static int[] getInputCodeLatin1JsNames() {
        return sInputCodesJsNames;
    }

    public static int[] getInputCodeUtf8JsNames() {
        return sInputCodesUtf8JsNames;
    }

    public static int[] getInputCodeComment() {
        return sInputCodesComment;
    }

    public static int[] getInputCodeWS() {
        return sInputCodesWS;
    }

    public static int[] get7BitOutputEscapes() {
        return sOutputEscapes128;
    }

    public static int charToHex(int r1) {
        if (r1 > 127) {
            return -1;
        }
        return sHexValues[r1];
    }

    public static void appendQuoted(StringBuilder sb, String str) {
        int[] r0 = sOutputEscapes128;
        int length = r0.length;
        int length2 = str.length();
        for (int r3 = 0; r3 < length2; r3++) {
            char charAt = str.charAt(r3);
            if (charAt >= length || r0[charAt] == 0) {
                sb.append(charAt);
            } else {
                sb.append(IOUtils.DIR_SEPARATOR_WINDOWS);
                int r5 = r0[charAt];
                if (r5 < 0) {
                    sb.append('u');
                    sb.append('0');
                    sb.append('0');
                    char[] cArr = f179HC;
                    sb.append(cArr[charAt >> 4]);
                    sb.append(cArr[charAt & 15]);
                } else {
                    sb.append((char) r5);
                }
            }
        }
    }

    public static char[] copyHexChars() {
        return (char[]) f179HC.clone();
    }

    public static byte[] copyHexBytes() {
        return (byte[]) f178HB.clone();
    }
}
