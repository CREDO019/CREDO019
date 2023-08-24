package com.google.common.collect;

import com.google.common.base.Objects;
import java.util.Arrays;
import javax.annotation.CheckForNull;
import kotlin.UShort;

@ElementTypesAreNonnullByDefault
/* loaded from: classes3.dex */
final class CompactHashing {
    private static final int BYTE_MASK = 255;
    private static final int BYTE_MAX_SIZE = 256;
    static final int DEFAULT_SIZE = 3;
    static final int HASH_TABLE_BITS_MASK = 31;
    private static final int HASH_TABLE_BITS_MAX_BITS = 5;
    static final int MAX_SIZE = 1073741823;
    private static final int MIN_HASH_TABLE_SIZE = 4;
    static final int MODIFICATION_COUNT_INCREMENT = 32;
    private static final int SHORT_MASK = 65535;
    private static final int SHORT_MAX_SIZE = 65536;
    static final byte UNSET = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getHashPrefix(int r0, int r1) {
        return r0 & (~r1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getNext(int r0, int r1) {
        return r0 & r1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int maskCombine(int r1, int r2, int r3) {
        return (r1 & (~r3)) | (r2 & r3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int newCapacity(int r1) {
        return (r1 < 32 ? 4 : 2) * (r1 + 1);
    }

    private CompactHashing() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int tableSize(int r2) {
        return Math.max(4, Hashing.closedTableSize(r2 + 1, 1.0d));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object createTable(int r3) {
        if (r3 < 2 || r3 > 1073741824 || Integer.highestOneBit(r3) != r3) {
            StringBuilder sb = new StringBuilder(52);
            sb.append("must be power of 2 between 2^1 and 2^30: ");
            sb.append(r3);
            throw new IllegalArgumentException(sb.toString());
        } else if (r3 <= 256) {
            return new byte[r3];
        } else {
            if (r3 <= 65536) {
                return new short[r3];
            }
            return new int[r3];
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void tableClear(Object obj) {
        if (obj instanceof byte[]) {
            Arrays.fill((byte[]) obj, (byte) 0);
        } else if (obj instanceof short[]) {
            Arrays.fill((short[]) obj, (short) 0);
        } else {
            Arrays.fill((int[]) obj, 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int tableGet(Object obj, int r2) {
        if (obj instanceof byte[]) {
            return ((byte[]) obj)[r2] & 255;
        }
        if (obj instanceof short[]) {
            return ((short[]) obj)[r2] & UShort.MAX_VALUE;
        }
        return ((int[]) obj)[r2];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void tableSet(Object obj, int r2, int r3) {
        if (obj instanceof byte[]) {
            ((byte[]) obj)[r2] = (byte) r3;
        } else if (obj instanceof short[]) {
            ((short[]) obj)[r2] = (short) r3;
        } else {
            ((int[]) obj)[r2] = r3;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int remove(@CheckForNull Object obj, @CheckForNull Object obj2, int r10, Object obj3, int[] r12, Object[] objArr, @CheckForNull Object[] objArr2) {
        int r2;
        int r5;
        int smearedHash = Hashing.smearedHash(obj);
        int r1 = smearedHash & r10;
        int tableGet = tableGet(obj3, r1);
        if (tableGet == 0) {
            return -1;
        }
        int hashPrefix = getHashPrefix(smearedHash, r10);
        int r4 = -1;
        while (true) {
            r2 = tableGet - 1;
            r5 = r12[r2];
            if (getHashPrefix(r5, r10) != hashPrefix || !Objects.equal(obj, objArr[r2]) || (objArr2 != null && !Objects.equal(obj2, objArr2[r2]))) {
                int next = getNext(r5, r10);
                if (next == 0) {
                    return -1;
                }
                r4 = r2;
                tableGet = next;
            }
        }
        int next2 = getNext(r5, r10);
        if (r4 == -1) {
            tableSet(obj3, r1, next2);
        } else {
            r12[r4] = maskCombine(r12[r4], next2, r10);
        }
        return r2;
    }
}
