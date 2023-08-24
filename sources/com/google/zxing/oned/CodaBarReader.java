package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import java.util.Arrays;
import java.util.Map;

/* loaded from: classes3.dex */
public final class CodaBarReader extends OneDReader {
    private static final float MAX_ACCEPTABLE = 2.0f;
    private static final int MIN_CHARACTER_LENGTH = 3;
    private static final float PADDING = 1.5f;
    private static final String ALPHABET_STRING = "0123456789-$:/.+ABCD";
    static final char[] ALPHABET = ALPHABET_STRING.toCharArray();
    static final int[] CHARACTER_ENCODINGS = {3, 6, 9, 96, 18, 66, 33, 36, 48, 72, 12, 24, 69, 81, 84, 21, 26, 41, 11, 14};
    private static final char[] STARTEND_ENCODING = {'A', 'B', 'C', 'D'};
    private final StringBuilder decodeRowResult = new StringBuilder(20);
    private int[] counters = new int[80];
    private int counterLength = 0;

    @Override // com.google.zxing.oned.OneDReader
    public Result decodeRow(int r11, BitArray bitArray, Map<DecodeHintType, ?> map) throws NotFoundException {
        Arrays.fill(this.counters, 0);
        setCounters(bitArray);
        int findStartPattern = findStartPattern();
        this.decodeRowResult.setLength(0);
        int r0 = findStartPattern;
        do {
            int narrowWidePattern = toNarrowWidePattern(r0);
            if (narrowWidePattern == -1) {
                throw NotFoundException.getNotFoundInstance();
            }
            this.decodeRowResult.append((char) narrowWidePattern);
            r0 += 8;
            if (this.decodeRowResult.length() > 1 && arrayContains(STARTEND_ENCODING, ALPHABET[narrowWidePattern])) {
                break;
            }
        } while (r0 < this.counterLength);
        int r4 = r0 - 1;
        int r2 = this.counters[r4];
        int r7 = 0;
        for (int r6 = -8; r6 < -1; r6++) {
            r7 += this.counters[r0 + r6];
        }
        if (r0 < this.counterLength && r2 < r7 / 2) {
            throw NotFoundException.getNotFoundInstance();
        }
        validatePattern(findStartPattern);
        for (int r02 = 0; r02 < this.decodeRowResult.length(); r02++) {
            StringBuilder sb = this.decodeRowResult;
            sb.setCharAt(r02, ALPHABET[sb.charAt(r02)]);
        }
        char charAt = this.decodeRowResult.charAt(0);
        char[] cArr = STARTEND_ENCODING;
        if (!arrayContains(cArr, charAt)) {
            throw NotFoundException.getNotFoundInstance();
        }
        StringBuilder sb2 = this.decodeRowResult;
        if (!arrayContains(cArr, sb2.charAt(sb2.length() - 1))) {
            throw NotFoundException.getNotFoundInstance();
        }
        if (this.decodeRowResult.length() <= 3) {
            throw NotFoundException.getNotFoundInstance();
        }
        if (map == null || !map.containsKey(DecodeHintType.RETURN_CODABAR_START_END)) {
            StringBuilder sb3 = this.decodeRowResult;
            sb3.deleteCharAt(sb3.length() - 1);
            this.decodeRowResult.deleteCharAt(0);
        }
        int r03 = 0;
        for (int r13 = 0; r13 < findStartPattern; r13++) {
            r03 += this.counters[r13];
        }
        float f = r03;
        while (findStartPattern < r4) {
            r03 += this.counters[findStartPattern];
            findStartPattern++;
        }
        float f2 = r11;
        return new Result(this.decodeRowResult.toString(), null, new ResultPoint[]{new ResultPoint(f, f2), new ResultPoint(r03, f2)}, BarcodeFormat.CODABAR);
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x00ae, code lost:
        throw com.google.zxing.NotFoundException.getNotFoundInstance();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void validatePattern(int r15) throws com.google.zxing.NotFoundException {
        /*
            Method dump skipped, instructions count: 208
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.CodaBarReader.validatePattern(int):void");
    }

    private void setCounters(BitArray bitArray) throws NotFoundException {
        int r0 = 0;
        this.counterLength = 0;
        int nextUnset = bitArray.getNextUnset(0);
        int size = bitArray.getSize();
        if (nextUnset >= size) {
            throw NotFoundException.getNotFoundInstance();
        }
        boolean z = true;
        while (nextUnset < size) {
            if (bitArray.get(nextUnset) != z) {
                r0++;
            } else {
                counterAppend(r0);
                z = !z;
                r0 = 1;
            }
            nextUnset++;
        }
        counterAppend(r0);
    }

    private void counterAppend(int r4) {
        int[] r0 = this.counters;
        int r1 = this.counterLength;
        r0[r1] = r4;
        int r12 = r1 + 1;
        this.counterLength = r12;
        if (r12 >= r0.length) {
            int[] r42 = new int[r12 << 1];
            System.arraycopy(r0, 0, r42, 0, r12);
            this.counters = r42;
        }
    }

    private int findStartPattern() throws NotFoundException {
        for (int r1 = 1; r1 < this.counterLength; r1 += 2) {
            int narrowWidePattern = toNarrowWidePattern(r1);
            if (narrowWidePattern != -1 && arrayContains(STARTEND_ENCODING, ALPHABET[narrowWidePattern])) {
                int r2 = 0;
                for (int r3 = r1; r3 < r1 + 7; r3++) {
                    r2 += this.counters[r3];
                }
                if (r1 == 1 || this.counters[r1 - 1] >= r2 / 2) {
                    return r1;
                }
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean arrayContains(char[] cArr, char c) {
        if (cArr != null) {
            for (char c2 : cArr) {
                if (c2 == c) {
                    return true;
                }
            }
        }
        return false;
    }

    private int toNarrowWidePattern(int r11) {
        int r0 = r11 + 7;
        if (r0 >= this.counterLength) {
            return -1;
        }
        int[] r1 = this.counters;
        int r3 = Integer.MAX_VALUE;
        int r4 = 0;
        int r6 = Integer.MAX_VALUE;
        int r7 = 0;
        for (int r5 = r11; r5 < r0; r5 += 2) {
            int r8 = r1[r5];
            if (r8 < r6) {
                r6 = r8;
            }
            if (r8 > r7) {
                r7 = r8;
            }
        }
        int r62 = (r6 + r7) / 2;
        int r72 = 0;
        for (int r52 = r11 + 1; r52 < r0; r52 += 2) {
            int r82 = r1[r52];
            if (r82 < r3) {
                r3 = r82;
            }
            if (r82 > r72) {
                r72 = r82;
            }
        }
        int r32 = (r3 + r72) / 2;
        int r02 = 128;
        int r73 = 0;
        for (int r53 = 0; r53 < 7; r53++) {
            r02 >>= 1;
            if (r1[r11 + r53] > ((r53 & 1) == 0 ? r62 : r32)) {
                r73 |= r02;
            }
        }
        while (true) {
            int[] r112 = CHARACTER_ENCODINGS;
            if (r4 >= r112.length) {
                return -1;
            }
            if (r112[r4] == r73) {
                return r4;
            }
            r4++;
        }
    }
}
