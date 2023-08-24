package com.google.zxing.oned.rss.expanded;

import com.canhub.cropper.CropImage;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imageutils.JfifUtil;
import com.google.android.exoplayer2.extractor.p011ts.PsExtractor;
import com.google.android.exoplayer2.extractor.p011ts.TsExtractor;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import com.google.zxing.common.detector.MathUtils;
import com.google.zxing.oned.rss.AbstractRSSReader;
import com.google.zxing.oned.rss.DataCharacter;
import com.google.zxing.oned.rss.FinderPattern;
import com.google.zxing.oned.rss.RSSUtils;
import com.google.zxing.oned.rss.expanded.decoders.AbstractExpandedDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.bouncycastle.math.Primes;

/* loaded from: classes3.dex */
public final class RSSExpandedReader extends AbstractRSSReader {
    private static final int FINDER_PAT_A = 0;
    private static final int FINDER_PAT_B = 1;
    private static final int FINDER_PAT_C = 2;
    private static final int FINDER_PAT_D = 3;
    private static final int FINDER_PAT_E = 4;
    private static final int FINDER_PAT_F = 5;
    private static final int MAX_PAIRS = 11;
    private final List<ExpandedPair> pairs = new ArrayList(11);
    private final List<ExpandedRow> rows = new ArrayList();
    private final int[] startEnd = new int[2];
    private boolean startFromEven;
    private static final int[] SYMBOL_WIDEST = {7, 5, 4, 3, 1};
    private static final int[] EVEN_TOTAL_SUBSET = {4, 20, 52, 104, CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE};
    private static final int[] GSUM = {0, 348, 1388, 2948, 3988};
    private static final int[][] FINDER_PATTERNS = {new int[]{1, 8, 4, 1}, new int[]{3, 6, 4, 1}, new int[]{3, 4, 6, 1}, new int[]{3, 2, 8, 1}, new int[]{2, 6, 5, 1}, new int[]{2, 2, 9, 1}};
    private static final int[][] WEIGHTS = {new int[]{1, 3, 9, 27, 81, 32, 96, 77}, new int[]{20, 60, RotationOptions.ROTATE_180, 118, 143, 7, 21, 63}, new int[]{PsExtractor.PRIVATE_STREAM_1, 145, 13, 39, 117, 140, 209, 205}, new int[]{193, 157, 49, 147, 19, 57, 171, 91}, new int[]{62, 186, 136, 197, 169, 85, 44, 132}, new int[]{185, 133, 188, 142, 4, 12, 36, 108}, new int[]{113, 128, 173, 97, 80, 29, 87, 50}, new int[]{150, 28, 84, 41, 123, 158, 52, 156}, new int[]{46, TsExtractor.TS_STREAM_TYPE_DTS, CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE, 187, 139, 206, 196, 166}, new int[]{76, 17, 51, 153, 37, 111, 122, 155}, new int[]{43, TsExtractor.TS_STREAM_TYPE_AC3, 176, 106, 107, 110, 119, 146}, new int[]{16, 48, 144, 10, 30, 90, 59, 177}, new int[]{109, 116, 137, 200, 178, 112, 125, 164}, new int[]{70, 210, JfifUtil.MARKER_RST0, 202, 184, TsExtractor.TS_STREAM_TYPE_HDMV_DTS, 179, 115}, new int[]{TsExtractor.TS_STREAM_TYPE_SPLICE_INFO, 191, 151, 31, 93, 68, CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE, 190}, new int[]{148, 22, 66, 198, TsExtractor.TS_STREAM_TYPE_AC4, 94, 71, 2}, new int[]{6, 18, 54, 162, 64, 192, 154, 40}, new int[]{120, 149, 25, 75, 14, 42, 126, 167}, new int[]{79, 26, 78, 23, 69, 207, 199, 175}, new int[]{103, 98, 83, 38, 114, 131, 182, 124}, new int[]{161, 61, 183, 127, 170, 88, 53, 159}, new int[]{55, 165, 73, 8, 24, 72, 5, 15}, new int[]{45, TsExtractor.TS_STREAM_TYPE_E_AC3, 194, 160, 58, 174, 100, 89}};
    private static final int[][] FINDER_PATTERN_SEQUENCES = {new int[]{0, 0}, new int[]{0, 1, 1}, new int[]{0, 2, 1, 3}, new int[]{0, 4, 1, 3, 2}, new int[]{0, 4, 1, 3, 3, 5}, new int[]{0, 4, 1, 3, 4, 5, 5}, new int[]{0, 0, 1, 1, 2, 2, 3, 3}, new int[]{0, 0, 1, 1, 2, 2, 3, 4, 4}, new int[]{0, 0, 1, 1, 2, 2, 3, 4, 5, 5}, new int[]{0, 0, 1, 1, 2, 3, 3, 4, 4, 5, 5}};

    @Override // com.google.zxing.oned.OneDReader
    public Result decodeRow(int r1, BitArray bitArray, Map<DecodeHintType, ?> map) throws NotFoundException, FormatException {
        this.pairs.clear();
        this.startFromEven = false;
        try {
            return constructResult(decodeRow2pairs(r1, bitArray));
        } catch (NotFoundException unused) {
            this.pairs.clear();
            this.startFromEven = true;
            return constructResult(decodeRow2pairs(r1, bitArray));
        }
    }

    @Override // com.google.zxing.oned.OneDReader, com.google.zxing.Reader
    public void reset() {
        this.pairs.clear();
        this.rows.clear();
    }

    List<ExpandedPair> decodeRow2pairs(int r6, BitArray bitArray) throws NotFoundException {
        boolean z = false;
        while (!z) {
            try {
                List<ExpandedPair> list = this.pairs;
                list.add(retrieveNextPair(bitArray, list, r6));
            } catch (NotFoundException e) {
                if (this.pairs.isEmpty()) {
                    throw e;
                }
                z = true;
            }
        }
        if (checkChecksum()) {
            return this.pairs;
        }
        boolean z2 = !this.rows.isEmpty();
        storeRow(r6, false);
        if (z2) {
            List<ExpandedPair> checkRows = checkRows(false);
            if (checkRows != null) {
                return checkRows;
            }
            List<ExpandedPair> checkRows2 = checkRows(true);
            if (checkRows2 != null) {
                return checkRows2;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private List<ExpandedPair> checkRows(boolean z) {
        List<ExpandedPair> list = null;
        if (this.rows.size() > 25) {
            this.rows.clear();
            return null;
        }
        this.pairs.clear();
        if (z) {
            Collections.reverse(this.rows);
        }
        try {
            list = checkRows(new ArrayList(), 0);
        } catch (NotFoundException unused) {
        }
        if (z) {
            Collections.reverse(this.rows);
        }
        return list;
    }

    private List<ExpandedPair> checkRows(List<ExpandedRow> list, int r6) throws NotFoundException {
        while (r6 < this.rows.size()) {
            ExpandedRow expandedRow = this.rows.get(r6);
            this.pairs.clear();
            for (ExpandedRow expandedRow2 : list) {
                this.pairs.addAll(expandedRow2.getPairs());
            }
            this.pairs.addAll(expandedRow.getPairs());
            if (isValidSequence(this.pairs)) {
                if (checkChecksum()) {
                    return this.pairs;
                }
                ArrayList arrayList = new ArrayList(list);
                arrayList.add(expandedRow);
                try {
                    return checkRows(arrayList, r6 + 1);
                } catch (NotFoundException unused) {
                    continue;
                }
            }
            r6++;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static boolean isValidSequence(List<ExpandedPair> list) {
        int[][] r0;
        boolean z;
        for (int[] r4 : FINDER_PATTERN_SEQUENCES) {
            if (list.size() <= r4.length) {
                int r5 = 0;
                while (true) {
                    if (r5 >= list.size()) {
                        z = true;
                        break;
                    } else if (list.get(r5).getFinderPattern().getValue() != r4[r5]) {
                        z = false;
                        break;
                    } else {
                        r5++;
                    }
                }
                if (z) {
                    return true;
                }
            }
        }
        return false;
    }

    private void storeRow(int r6, boolean z) {
        boolean z2 = false;
        int r1 = 0;
        boolean z3 = false;
        while (true) {
            if (r1 >= this.rows.size()) {
                break;
            }
            ExpandedRow expandedRow = this.rows.get(r1);
            if (expandedRow.getRowNumber() > r6) {
                z2 = expandedRow.isEquivalent(this.pairs);
                break;
            } else {
                z3 = expandedRow.isEquivalent(this.pairs);
                r1++;
            }
        }
        if (z2 || z3 || isPartialRow(this.pairs, this.rows)) {
            return;
        }
        this.rows.add(r1, new ExpandedRow(this.pairs, r6, z));
        removePartialRows(this.pairs, this.rows);
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x004c, code lost:
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void removePartialRows(java.util.List<com.google.zxing.oned.rss.expanded.ExpandedPair> r6, java.util.List<com.google.zxing.oned.rss.expanded.ExpandedRow> r7) {
        /*
            java.util.Iterator r7 = r7.iterator()
        L4:
            boolean r0 = r7.hasNext()
            if (r0 == 0) goto L56
            java.lang.Object r0 = r7.next()
            com.google.zxing.oned.rss.expanded.ExpandedRow r0 = (com.google.zxing.oned.rss.expanded.ExpandedRow) r0
            java.util.List r1 = r0.getPairs()
            int r1 = r1.size()
            int r2 = r6.size()
            if (r1 == r2) goto L4
            java.util.List r0 = r0.getPairs()
            java.util.Iterator r0 = r0.iterator()
        L26:
            boolean r1 = r0.hasNext()
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L4f
            java.lang.Object r1 = r0.next()
            com.google.zxing.oned.rss.expanded.ExpandedPair r1 = (com.google.zxing.oned.rss.expanded.ExpandedPair) r1
            java.util.Iterator r4 = r6.iterator()
        L38:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L4b
            java.lang.Object r5 = r4.next()
            com.google.zxing.oned.rss.expanded.ExpandedPair r5 = (com.google.zxing.oned.rss.expanded.ExpandedPair) r5
            boolean r5 = r1.equals(r5)
            if (r5 == 0) goto L38
            goto L4c
        L4b:
            r3 = 0
        L4c:
            if (r3 != 0) goto L26
            goto L50
        L4f:
            r2 = 1
        L50:
            if (r2 == 0) goto L4
            r7.remove()
            goto L4
        L56:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.rss.expanded.RSSExpandedReader.removePartialRows(java.util.List, java.util.List):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0043, code lost:
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static boolean isPartialRow(java.lang.Iterable<com.google.zxing.oned.rss.expanded.ExpandedPair> r7, java.lang.Iterable<com.google.zxing.oned.rss.expanded.ExpandedRow> r8) {
        /*
            java.util.Iterator r8 = r8.iterator()
        L4:
            boolean r0 = r8.hasNext()
            r1 = 0
            if (r0 == 0) goto L46
            java.lang.Object r0 = r8.next()
            com.google.zxing.oned.rss.expanded.ExpandedRow r0 = (com.google.zxing.oned.rss.expanded.ExpandedRow) r0
            java.util.Iterator r2 = r7.iterator()
        L15:
            boolean r3 = r2.hasNext()
            r4 = 1
            if (r3 == 0) goto L42
            java.lang.Object r3 = r2.next()
            com.google.zxing.oned.rss.expanded.ExpandedPair r3 = (com.google.zxing.oned.rss.expanded.ExpandedPair) r3
            java.util.List r5 = r0.getPairs()
            java.util.Iterator r5 = r5.iterator()
        L2a:
            boolean r6 = r5.hasNext()
            if (r6 == 0) goto L3e
            java.lang.Object r6 = r5.next()
            com.google.zxing.oned.rss.expanded.ExpandedPair r6 = (com.google.zxing.oned.rss.expanded.ExpandedPair) r6
            boolean r6 = r3.equals(r6)
            if (r6 == 0) goto L2a
            r3 = 1
            goto L3f
        L3e:
            r3 = 0
        L3f:
            if (r3 != 0) goto L15
            goto L43
        L42:
            r1 = 1
        L43:
            if (r1 == 0) goto L4
            return r4
        L46:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.rss.expanded.RSSExpandedReader.isPartialRow(java.lang.Iterable, java.lang.Iterable):boolean");
    }

    List<ExpandedRow> getRows() {
        return this.rows;
    }

    static Result constructResult(List<ExpandedPair> list) throws NotFoundException, FormatException {
        String parseInformation = AbstractExpandedDecoder.createDecoder(BitArrayBuilder.buildBitArray(list)).parseInformation();
        ResultPoint[] resultPoints = list.get(0).getFinderPattern().getResultPoints();
        ResultPoint[] resultPoints2 = list.get(list.size() - 1).getFinderPattern().getResultPoints();
        return new Result(parseInformation, null, new ResultPoint[]{resultPoints[0], resultPoints[1], resultPoints2[0], resultPoints2[1]}, BarcodeFormat.RSS_EXPANDED);
    }

    private boolean checkChecksum() {
        ExpandedPair expandedPair = this.pairs.get(0);
        DataCharacter leftChar = expandedPair.getLeftChar();
        DataCharacter rightChar = expandedPair.getRightChar();
        if (rightChar == null) {
            return false;
        }
        int checksumPortion = rightChar.getChecksumPortion();
        int r3 = 2;
        for (int r5 = 1; r5 < this.pairs.size(); r5++) {
            ExpandedPair expandedPair2 = this.pairs.get(r5);
            checksumPortion += expandedPair2.getLeftChar().getChecksumPortion();
            r3++;
            DataCharacter rightChar2 = expandedPair2.getRightChar();
            if (rightChar2 != null) {
                checksumPortion += rightChar2.getChecksumPortion();
                r3++;
            }
        }
        return ((r3 + (-4)) * Primes.SMALL_FACTOR_LIMIT) + (checksumPortion % Primes.SMALL_FACTOR_LIMIT) == leftChar.getValue();
    }

    private static int getNextSecondBar(BitArray bitArray, int r2) {
        if (bitArray.get(r2)) {
            return bitArray.getNextSet(bitArray.getNextUnset(r2));
        }
        return bitArray.getNextUnset(bitArray.getNextSet(r2));
    }

    ExpandedPair retrieveNextPair(BitArray bitArray, List<ExpandedPair> list, int r9) throws NotFoundException {
        FinderPattern parseFoundFinderPattern;
        DataCharacter dataCharacter;
        boolean z = list.size() % 2 == 0;
        if (this.startFromEven) {
            z = !z;
        }
        int r3 = -1;
        boolean z2 = true;
        do {
            findNextPair(bitArray, list, r3);
            parseFoundFinderPattern = parseFoundFinderPattern(bitArray, r9, z);
            if (parseFoundFinderPattern == null) {
                r3 = getNextSecondBar(bitArray, this.startEnd[0]);
                continue;
            } else {
                z2 = false;
                continue;
            }
        } while (z2);
        DataCharacter decodeDataCharacter = decodeDataCharacter(bitArray, parseFoundFinderPattern, z, true);
        if (!list.isEmpty() && list.get(list.size() - 1).mustBeLast()) {
            throw NotFoundException.getNotFoundInstance();
        }
        try {
            dataCharacter = decodeDataCharacter(bitArray, parseFoundFinderPattern, z, false);
        } catch (NotFoundException unused) {
            dataCharacter = null;
        }
        return new ExpandedPair(decodeDataCharacter, dataCharacter, parseFoundFinderPattern, true);
    }

    private void findNextPair(BitArray bitArray, List<ExpandedPair> list, int r14) throws NotFoundException {
        int[] decodeFinderCounters = getDecodeFinderCounters();
        decodeFinderCounters[0] = 0;
        decodeFinderCounters[1] = 0;
        decodeFinderCounters[2] = 0;
        decodeFinderCounters[3] = 0;
        int size = bitArray.getSize();
        if (r14 < 0) {
            r14 = list.isEmpty() ? 0 : list.get(list.size() - 1).getFinderPattern().getStartEnd()[1];
        }
        boolean z = list.size() % 2 != 0;
        if (this.startFromEven) {
            z = !z;
        }
        boolean z2 = false;
        while (r14 < size) {
            z2 = !bitArray.get(r14);
            if (!z2) {
                break;
            }
            r14++;
        }
        boolean z3 = z2;
        int r8 = 0;
        int r6 = r14;
        while (r14 < size) {
            if (bitArray.get(r14) != z3) {
                decodeFinderCounters[r8] = decodeFinderCounters[r8] + 1;
            } else {
                if (r8 == 3) {
                    if (z) {
                        reverseCounters(decodeFinderCounters);
                    }
                    if (isFinderPattern(decodeFinderCounters)) {
                        int[] r12 = this.startEnd;
                        r12[0] = r6;
                        r12[1] = r14;
                        return;
                    }
                    if (z) {
                        reverseCounters(decodeFinderCounters);
                    }
                    r6 += decodeFinderCounters[0] + decodeFinderCounters[1];
                    decodeFinderCounters[0] = decodeFinderCounters[2];
                    decodeFinderCounters[1] = decodeFinderCounters[3];
                    decodeFinderCounters[2] = 0;
                    decodeFinderCounters[3] = 0;
                    r8--;
                } else {
                    r8++;
                }
                decodeFinderCounters[r8] = 1;
                z3 = !z3;
            }
            r14++;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static void reverseCounters(int[] r5) {
        int length = r5.length;
        for (int r1 = 0; r1 < length / 2; r1++) {
            int r2 = r5[r1];
            int r3 = (length - r1) - 1;
            r5[r1] = r5[r3];
            r5[r3] = r2;
        }
    }

    private FinderPattern parseFoundFinderPattern(BitArray bitArray, int r11, boolean z) {
        int r7;
        int r6;
        int r2;
        if (z) {
            int r12 = this.startEnd[0] - 1;
            while (r12 >= 0 && !bitArray.get(r12)) {
                r12--;
            }
            int r122 = r12 + 1;
            int[] r10 = this.startEnd;
            r2 = r10[0] - r122;
            r7 = r10[1];
            r6 = r122;
        } else {
            int[] r123 = this.startEnd;
            int r22 = r123[0];
            int nextUnset = bitArray.getNextUnset(r123[1] + 1);
            r7 = nextUnset;
            r6 = r22;
            r2 = nextUnset - this.startEnd[1];
        }
        int[] decodeFinderCounters = getDecodeFinderCounters();
        System.arraycopy(decodeFinderCounters, 0, decodeFinderCounters, 1, decodeFinderCounters.length - 1);
        decodeFinderCounters[0] = r2;
        try {
            return new FinderPattern(parseFinderValue(decodeFinderCounters, FINDER_PATTERNS), new int[]{r6, r7}, r6, r7, r11);
        } catch (NotFoundException unused) {
            return null;
        }
    }

    DataCharacter decodeDataCharacter(BitArray bitArray, FinderPattern finderPattern, boolean z, boolean z2) throws NotFoundException {
        int[] dataCharacterCounters = getDataCharacterCounters();
        for (int r3 = 0; r3 < dataCharacterCounters.length; r3++) {
            dataCharacterCounters[r3] = 0;
        }
        if (z2) {
            recordPatternInReverse(bitArray, finderPattern.getStartEnd()[0], dataCharacterCounters);
        } else {
            recordPattern(bitArray, finderPattern.getStartEnd()[1], dataCharacterCounters);
            int r4 = 0;
            for (int length = dataCharacterCounters.length - 1; r4 < length; length--) {
                int r5 = dataCharacterCounters[r4];
                dataCharacterCounters[r4] = dataCharacterCounters[length];
                dataCharacterCounters[length] = r5;
                r4++;
            }
        }
        float sum = MathUtils.sum(dataCharacterCounters) / 17.0f;
        float f = (finderPattern.getStartEnd()[1] - finderPattern.getStartEnd()[0]) / 15.0f;
        if (Math.abs(sum - f) / f > 0.3f) {
            throw NotFoundException.getNotFoundInstance();
        }
        int[] oddCounts = getOddCounts();
        int[] evenCounts = getEvenCounts();
        float[] oddRoundingErrors = getOddRoundingErrors();
        float[] evenRoundingErrors = getEvenRoundingErrors();
        for (int r9 = 0; r9 < dataCharacterCounters.length; r9++) {
            float f2 = (dataCharacterCounters[r9] * 1.0f) / sum;
            int r10 = (int) (0.5f + f2);
            if (r10 <= 0) {
                if (f2 < 0.3f) {
                    throw NotFoundException.getNotFoundInstance();
                }
                r10 = 1;
            } else if (r10 > 8) {
                if (f2 > 8.7f) {
                    throw NotFoundException.getNotFoundInstance();
                }
                r10 = 8;
            }
            int r12 = r9 / 2;
            if ((r9 & 1) == 0) {
                oddCounts[r12] = r10;
                oddRoundingErrors[r12] = f2 - r10;
            } else {
                evenCounts[r12] = r10;
                evenRoundingErrors[r12] = f2 - r10;
            }
        }
        adjustOddEvenCounts(17);
        int value = (((finderPattern.getValue() * 4) + (z ? 0 : 2)) + (!z2 ? 1 : 0)) - 1;
        int r92 = 0;
        int r102 = 0;
        for (int length2 = oddCounts.length - 1; length2 >= 0; length2--) {
            if (isNotA1left(finderPattern, z, z2)) {
                r92 += oddCounts[length2] * WEIGHTS[value][length2 * 2];
            }
            r102 += oddCounts[length2];
        }
        int r11 = 0;
        for (int length3 = evenCounts.length - 1; length3 >= 0; length3--) {
            if (isNotA1left(finderPattern, z, z2)) {
                r11 += evenCounts[length3] * WEIGHTS[value][(length3 * 2) + 1];
            }
        }
        int r93 = r92 + r11;
        if ((r102 & 1) != 0 || r102 > 13 || r102 < 4) {
            throw NotFoundException.getNotFoundInstance();
        }
        int r0 = (13 - r102) / 2;
        int r42 = SYMBOL_WIDEST[r0];
        return new DataCharacter((RSSUtils.getRSSvalue(oddCounts, r42, true) * EVEN_TOTAL_SUBSET[r0]) + RSSUtils.getRSSvalue(evenCounts, 9 - r42, false) + GSUM[r0], r93);
    }

    private static boolean isNotA1left(FinderPattern finderPattern, boolean z, boolean z2) {
        return (finderPattern.getValue() == 0 && z && z2) ? false : true;
    }

    private void adjustOddEvenCounts(int r11) throws NotFoundException {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        int sum = MathUtils.sum(getOddCounts());
        int sum2 = MathUtils.sum(getEvenCounts());
        boolean z5 = true;
        if (sum > 13) {
            z = false;
            z2 = true;
        } else {
            z = sum < 4;
            z2 = false;
        }
        if (sum2 > 13) {
            z3 = false;
            z4 = true;
        } else {
            z3 = sum2 < 4;
            z4 = false;
        }
        int r8 = (sum + sum2) - r11;
        boolean z6 = (sum & 1) == 1;
        boolean z7 = (sum2 & 1) == 0;
        if (r8 == 1) {
            if (z6) {
                if (z7) {
                    throw NotFoundException.getNotFoundInstance();
                }
                z5 = z;
                z2 = true;
            } else if (!z7) {
                throw NotFoundException.getNotFoundInstance();
            } else {
                z5 = z;
                z4 = true;
            }
        } else if (r8 == -1) {
            if (z6) {
                if (z7) {
                    throw NotFoundException.getNotFoundInstance();
                }
            } else if (!z7) {
                throw NotFoundException.getNotFoundInstance();
            } else {
                z5 = z;
                z3 = true;
            }
        } else if (r8 != 0) {
            throw NotFoundException.getNotFoundInstance();
        } else {
            if (z6) {
                if (!z7) {
                    throw NotFoundException.getNotFoundInstance();
                }
                if (sum >= sum2) {
                    z5 = z;
                    z3 = true;
                    z2 = true;
                }
                z4 = true;
            } else if (z7) {
                throw NotFoundException.getNotFoundInstance();
            } else {
                z5 = z;
            }
        }
        if (z5) {
            if (z2) {
                throw NotFoundException.getNotFoundInstance();
            }
            increment(getOddCounts(), getOddRoundingErrors());
        }
        if (z2) {
            decrement(getOddCounts(), getOddRoundingErrors());
        }
        if (z3) {
            if (z4) {
                throw NotFoundException.getNotFoundInstance();
            }
            increment(getEvenCounts(), getOddRoundingErrors());
        }
        if (z4) {
            decrement(getEvenCounts(), getEvenRoundingErrors());
        }
    }
}
