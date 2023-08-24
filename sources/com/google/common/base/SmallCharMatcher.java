package com.google.common.base;

import com.google.common.base.CharMatcher;
import java.util.BitSet;

@ElementTypesAreNonnullByDefault
/* loaded from: classes3.dex */
final class SmallCharMatcher extends CharMatcher.NamedFastMatcher {

    /* renamed from: C1 */
    private static final int f1137C1 = -862048943;

    /* renamed from: C2 */
    private static final int f1138C2 = 461845907;
    private static final double DESIRED_LOAD_FACTOR = 0.5d;
    static final int MAX_SIZE = 1023;
    private final boolean containsZero;
    private final long filter;
    private final char[] table;

    private SmallCharMatcher(char[] cArr, long j, boolean z, String str) {
        super(str);
        this.table = cArr;
        this.filter = j;
        this.containsZero = z;
    }

    static int smear(int r1) {
        return Integer.rotateLeft(r1 * f1137C1, 15) * f1138C2;
    }

    private boolean checkFilter(int r5) {
        return 1 == ((this.filter >> r5) & 1);
    }

    static int chooseTableSize(int r6) {
        if (r6 == 1) {
            return 2;
        }
        int highestOneBit = Integer.highestOneBit(r6 - 1) << 1;
        while (highestOneBit * DESIRED_LOAD_FACTOR < r6) {
            highestOneBit <<= 1;
        }
        return highestOneBit;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static CharMatcher from(BitSet bitSet, String str) {
        int r2;
        int cardinality = bitSet.cardinality();
        boolean z = bitSet.get(0);
        int chooseTableSize = chooseTableSize(cardinality);
        char[] cArr = new char[chooseTableSize];
        int r0 = chooseTableSize - 1;
        int nextSetBit = bitSet.nextSetBit(0);
        long j = 0;
        while (nextSetBit != -1) {
            long j2 = (1 << nextSetBit) | j;
            int smear = smear(nextSetBit);
            while (true) {
                r2 = smear & r0;
                if (cArr[r2] == 0) {
                    break;
                }
                smear = r2 + 1;
            }
            cArr[r2] = (char) nextSetBit;
            nextSetBit = bitSet.nextSetBit(nextSetBit + 1);
            j = j2;
        }
        return new SmallCharMatcher(cArr, j, z, str);
    }

    @Override // com.google.common.base.CharMatcher
    public boolean matches(char c) {
        if (c == 0) {
            return this.containsZero;
        }
        if (checkFilter(c)) {
            int length = this.table.length - 1;
            int smear = smear(c) & length;
            int r4 = smear;
            do {
                char[] cArr = this.table;
                if (cArr[r4] == 0) {
                    return false;
                }
                if (cArr[r4] == c) {
                    return true;
                }
                r4 = (r4 + 1) & length;
            } while (r4 != smear);
            return false;
        }
        return false;
    }

    @Override // com.google.common.base.CharMatcher
    void setBits(BitSet bitSet) {
        char[] cArr;
        if (this.containsZero) {
            bitSet.set(0);
        }
        for (char c : this.table) {
            if (c != 0) {
                bitSet.set(c);
            }
        }
    }
}
