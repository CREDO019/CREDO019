package com.google.zxing.oned.rss;

import com.facebook.device.yearclass.YearClass;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitArray;
import com.google.zxing.common.detector.MathUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public final class RSS14Reader extends AbstractRSSReader {
    private final List<Pair> possibleLeftPairs = new ArrayList();
    private final List<Pair> possibleRightPairs = new ArrayList();
    private static final int[] OUTSIDE_EVEN_TOTAL_SUBSET = {1, 10, 34, 70, 126};
    private static final int[] INSIDE_ODD_TOTAL_SUBSET = {4, 20, 48, 81};
    private static final int[] OUTSIDE_GSUM = {0, 161, 961, YearClass.CLASS_2015, 2715};
    private static final int[] INSIDE_GSUM = {0, 336, 1036, 1516};
    private static final int[] OUTSIDE_ODD_WIDEST = {8, 6, 4, 3, 1};
    private static final int[] INSIDE_ODD_WIDEST = {2, 4, 6, 8};
    private static final int[][] FINDER_PATTERNS = {new int[]{3, 8, 2, 1}, new int[]{3, 5, 5, 1}, new int[]{3, 3, 7, 1}, new int[]{3, 1, 9, 1}, new int[]{2, 7, 4, 1}, new int[]{2, 5, 6, 1}, new int[]{2, 3, 8, 1}, new int[]{1, 5, 7, 1}, new int[]{1, 3, 9, 1}};

    @Override // com.google.zxing.oned.OneDReader
    public Result decodeRow(int r4, BitArray bitArray, Map<DecodeHintType, ?> map) throws NotFoundException {
        addOrTally(this.possibleLeftPairs, decodePair(bitArray, false, r4, map));
        bitArray.reverse();
        addOrTally(this.possibleRightPairs, decodePair(bitArray, true, r4, map));
        bitArray.reverse();
        for (Pair pair : this.possibleLeftPairs) {
            if (pair.getCount() > 1) {
                for (Pair pair2 : this.possibleRightPairs) {
                    if (pair2.getCount() > 1 && checkChecksum(pair, pair2)) {
                        return constructResult(pair, pair2);
                    }
                }
                continue;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static void addOrTally(Collection<Pair> collection, Pair pair) {
        if (pair == null) {
            return;
        }
        boolean z = false;
        Iterator<Pair> it = collection.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Pair next = it.next();
            if (next.getValue() == pair.getValue()) {
                next.incrementCount();
                z = true;
                break;
            }
        }
        if (z) {
            return;
        }
        collection.add(pair);
    }

    @Override // com.google.zxing.oned.OneDReader, com.google.zxing.Reader
    public void reset() {
        this.possibleLeftPairs.clear();
        this.possibleRightPairs.clear();
    }

    private static Result constructResult(Pair pair, Pair pair2) {
        String valueOf = String.valueOf((pair.getValue() * 4537077) + pair2.getValue());
        StringBuilder sb = new StringBuilder(14);
        for (int length = 13 - valueOf.length(); length > 0; length--) {
            sb.append('0');
        }
        sb.append(valueOf);
        int r5 = 0;
        for (int r2 = 0; r2 < 13; r2++) {
            int charAt = sb.charAt(r2) - '0';
            if ((r2 & 1) == 0) {
                charAt *= 3;
            }
            r5 += charAt;
        }
        int r3 = 10 - (r5 % 10);
        if (r3 == 10) {
            r3 = 0;
        }
        sb.append(r3);
        ResultPoint[] resultPoints = pair.getFinderPattern().getResultPoints();
        ResultPoint[] resultPoints2 = pair2.getFinderPattern().getResultPoints();
        return new Result(sb.toString(), null, new ResultPoint[]{resultPoints[0], resultPoints[1], resultPoints2[0], resultPoints2[1]}, BarcodeFormat.RSS_14);
    }

    private static boolean checkChecksum(Pair pair, Pair pair2) {
        int checksumPortion = (pair.getChecksumPortion() + (pair2.getChecksumPortion() * 16)) % 79;
        int value = (pair.getFinderPattern().getValue() * 9) + pair2.getFinderPattern().getValue();
        if (value > 72) {
            value--;
        }
        if (value > 8) {
            value--;
        }
        return checksumPortion == value;
    }

    private Pair decodePair(BitArray bitArray, boolean z, int r9, Map<DecodeHintType, ?> map) {
        try {
            int[] findFinderPattern = findFinderPattern(bitArray, z);
            FinderPattern parseFoundFinderPattern = parseFoundFinderPattern(bitArray, r9, z, findFinderPattern);
            ResultPointCallback resultPointCallback = map == null ? null : (ResultPointCallback) map.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
            if (resultPointCallback != null) {
                float f = (findFinderPattern[0] + findFinderPattern[1]) / 2.0f;
                if (z) {
                    f = (bitArray.getSize() - 1) - f;
                }
                resultPointCallback.foundPossibleResultPoint(new ResultPoint(f, r9));
            }
            DataCharacter decodeDataCharacter = decodeDataCharacter(bitArray, parseFoundFinderPattern, true);
            DataCharacter decodeDataCharacter2 = decodeDataCharacter(bitArray, parseFoundFinderPattern, false);
            return new Pair((decodeDataCharacter.getValue() * 1597) + decodeDataCharacter2.getValue(), decodeDataCharacter.getChecksumPortion() + (decodeDataCharacter2.getChecksumPortion() * 4), parseFoundFinderPattern);
        } catch (NotFoundException unused) {
            return null;
        }
    }

    private DataCharacter decodeDataCharacter(BitArray bitArray, FinderPattern finderPattern, boolean z) throws NotFoundException {
        int[] dataCharacterCounters = getDataCharacterCounters();
        for (int r2 = 0; r2 < dataCharacterCounters.length; r2++) {
            dataCharacterCounters[r2] = 0;
        }
        if (z) {
            recordPatternInReverse(bitArray, finderPattern.getStartEnd()[0], dataCharacterCounters);
        } else {
            recordPattern(bitArray, finderPattern.getStartEnd()[1] + 1, dataCharacterCounters);
            int r14 = 0;
            for (int length = dataCharacterCounters.length - 1; r14 < length; length--) {
                int r3 = dataCharacterCounters[r14];
                dataCharacterCounters[r14] = dataCharacterCounters[length];
                dataCharacterCounters[length] = r3;
                r14++;
            }
        }
        int r13 = z ? 16 : 15;
        float sum = MathUtils.sum(dataCharacterCounters) / r13;
        int[] oddCounts = getOddCounts();
        int[] evenCounts = getEvenCounts();
        float[] oddRoundingErrors = getOddRoundingErrors();
        float[] evenRoundingErrors = getEvenRoundingErrors();
        for (int r7 = 0; r7 < dataCharacterCounters.length; r7++) {
            float f = dataCharacterCounters[r7] / sum;
            int r9 = (int) (0.5f + f);
            if (r9 <= 0) {
                r9 = 1;
            } else if (r9 > 8) {
                r9 = 8;
            }
            int r10 = r7 / 2;
            if ((r7 & 1) == 0) {
                oddCounts[r10] = r9;
                oddRoundingErrors[r10] = f - r9;
            } else {
                evenCounts[r10] = r9;
                evenRoundingErrors[r10] = f - r9;
            }
        }
        adjustOddEvenCounts(z, r13);
        int r142 = 0;
        int r0 = 0;
        for (int length2 = oddCounts.length - 1; length2 >= 0; length2--) {
            r142 = (r142 * 9) + oddCounts[length2];
            r0 += oddCounts[length2];
        }
        int r5 = 0;
        int r6 = 0;
        for (int length3 = evenCounts.length - 1; length3 >= 0; length3--) {
            r5 = (r5 * 9) + evenCounts[length3];
            r6 += evenCounts[length3];
        }
        int r143 = r142 + (r5 * 3);
        if (!z) {
            if ((r6 & 1) != 0 || r6 > 10 || r6 < 4) {
                throw NotFoundException.getNotFoundInstance();
            }
            int r15 = (10 - r6) / 2;
            int r132 = INSIDE_ODD_WIDEST[r15];
            return new DataCharacter((RSSUtils.getRSSvalue(evenCounts, 9 - r132, false) * INSIDE_ODD_TOTAL_SUBSET[r15]) + RSSUtils.getRSSvalue(oddCounts, r132, true) + INSIDE_GSUM[r15], r143);
        } else if ((r0 & 1) != 0 || r0 > 12 || r0 < 4) {
            throw NotFoundException.getNotFoundInstance();
        } else {
            int r152 = (12 - r0) / 2;
            int r133 = OUTSIDE_ODD_WIDEST[r152];
            return new DataCharacter((RSSUtils.getRSSvalue(oddCounts, r133, false) * OUTSIDE_EVEN_TOTAL_SUBSET[r152]) + RSSUtils.getRSSvalue(evenCounts, 9 - r133, true) + OUTSIDE_GSUM[r152], r143);
        }
    }

    private int[] findFinderPattern(BitArray bitArray, boolean z) throws NotFoundException {
        int[] decodeFinderCounters = getDecodeFinderCounters();
        decodeFinderCounters[0] = 0;
        decodeFinderCounters[1] = 0;
        decodeFinderCounters[2] = 0;
        decodeFinderCounters[3] = 0;
        int size = bitArray.getSize();
        int r6 = 0;
        boolean z2 = false;
        while (r6 < size) {
            z2 = !bitArray.get(r6);
            if (z == z2) {
                break;
            }
            r6++;
        }
        int r13 = r6;
        int r8 = 0;
        while (r6 < size) {
            if (bitArray.get(r6) != z2) {
                decodeFinderCounters[r8] = decodeFinderCounters[r8] + 1;
            } else {
                if (r8 != 3) {
                    r8++;
                } else if (isFinderPattern(decodeFinderCounters)) {
                    return new int[]{r13, r6};
                } else {
                    r13 += decodeFinderCounters[0] + decodeFinderCounters[1];
                    decodeFinderCounters[0] = decodeFinderCounters[2];
                    decodeFinderCounters[1] = decodeFinderCounters[3];
                    decodeFinderCounters[2] = 0;
                    decodeFinderCounters[3] = 0;
                    r8--;
                }
                decodeFinderCounters[r8] = 1;
                z2 = !z2;
            }
            r6++;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private FinderPattern parseFoundFinderPattern(BitArray bitArray, int r13, boolean z, int[] r15) throws NotFoundException {
        int r9;
        int r8;
        boolean z2 = bitArray.get(r15[0]);
        int r2 = r15[0] - 1;
        while (r2 >= 0 && z2 != bitArray.get(r2)) {
            r2--;
        }
        int r22 = r2 + 1;
        int[] decodeFinderCounters = getDecodeFinderCounters();
        System.arraycopy(decodeFinderCounters, 0, decodeFinderCounters, 1, decodeFinderCounters.length - 1);
        decodeFinderCounters[0] = r15[0] - r22;
        int parseFinderValue = parseFinderValue(decodeFinderCounters, FINDER_PATTERNS);
        int r1 = r15[1];
        if (z) {
            r9 = (bitArray.getSize() - 1) - r1;
            r8 = (bitArray.getSize() - 1) - r22;
        } else {
            r9 = r1;
            r8 = r22;
        }
        return new FinderPattern(parseFinderValue, new int[]{r22, r15[1]}, r8, r9, r13);
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0025, code lost:
        if (r1 < 4) goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x003d, code lost:
        if (r1 < 4) goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x003f, code lost:
        r2 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0041, code lost:
        r2 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0042, code lost:
        r5 = false;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void adjustOddEvenCounts(boolean r10, int r11) throws com.google.zxing.NotFoundException {
        /*
            Method dump skipped, instructions count: 228
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.rss.RSS14Reader.adjustOddEvenCounts(boolean, int):void");
    }
}
