package com.google.zxing.aztec.encoder;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/* loaded from: classes3.dex */
public final class HighLevelEncoder {
    private static final int[][] CHAR_MAP;
    static final int MODE_DIGIT = 2;
    static final int MODE_LOWER = 1;
    static final int MODE_MIXED = 3;
    static final int MODE_PUNCT = 4;
    static final int MODE_UPPER = 0;
    static final int[][] SHIFT_TABLE;
    private final byte[] text;
    static final String[] MODE_NAMES = {"UPPER", "LOWER", "DIGIT", "MIXED", "PUNCT"};
    static final int[][] LATCH_TABLE = {new int[]{0, 327708, 327710, 327709, 656318}, new int[]{590318, 0, 327710, 327709, 656318}, new int[]{262158, 590300, 0, 590301, 932798}, new int[]{327709, 327708, 656318, 0, 327710}, new int[]{327711, 656380, 656382, 656381, 0}};

    static {
        int[][] r1 = (int[][]) Array.newInstance(int.class, 5, 256);
        CHAR_MAP = r1;
        r1[0][32] = 1;
        for (int r12 = 65; r12 <= 90; r12++) {
            CHAR_MAP[0][r12] = (r12 - 65) + 2;
        }
        CHAR_MAP[1][32] = 1;
        for (int r13 = 97; r13 <= 122; r13++) {
            CHAR_MAP[1][r13] = (r13 - 97) + 2;
        }
        CHAR_MAP[2][32] = 1;
        for (int r14 = 48; r14 <= 57; r14++) {
            CHAR_MAP[2][r14] = (r14 - 48) + 2;
        }
        int[][] r15 = CHAR_MAP;
        r15[2][44] = 12;
        r15[2][46] = 13;
        int[] r2 = {0, 32, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 27, 28, 29, 30, 31, 64, 92, 94, 95, 96, 124, 126, 127};
        for (int r8 = 0; r8 < 28; r8++) {
            CHAR_MAP[3][r2[r8]] = r8;
        }
        int[] r82 = {0, 13, 0, 0, 0, 0, 33, 39, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 58, 59, 60, 61, 62, 63, 91, 93, 123, 125};
        for (int r9 = 0; r9 < 31; r9++) {
            if (r82[r9] > 0) {
                CHAR_MAP[4][r82[r9]] = r9;
            }
        }
        int[][] r0 = (int[][]) Array.newInstance(int.class, 6, 6);
        SHIFT_TABLE = r0;
        for (int[] r92 : r0) {
            Arrays.fill(r92, -1);
        }
        int[][] r02 = SHIFT_TABLE;
        r02[0][4] = 0;
        r02[1][4] = 0;
        r02[1][0] = 28;
        r02[3][4] = 0;
        r02[2][4] = 0;
        r02[2][0] = 15;
    }

    public HighLevelEncoder(byte[] bArr) {
        this.text = bArr;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0045  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.google.zxing.common.BitArray encode() {
        /*
            r8 = this;
            com.google.zxing.aztec.encoder.State r0 = com.google.zxing.aztec.encoder.State.INITIAL_STATE
            java.util.List r0 = java.util.Collections.singletonList(r0)
            r1 = 0
            r2 = 0
        L8:
            byte[] r3 = r8.text
            int r4 = r3.length
            if (r2 >= r4) goto L4c
            int r4 = r2 + 1
            int r5 = r3.length
            if (r4 >= r5) goto L15
            r5 = r3[r4]
            goto L16
        L15:
            r5 = 0
        L16:
            r3 = r3[r2]
            r6 = 13
            if (r3 == r6) goto L38
            r6 = 44
            r7 = 32
            if (r3 == r6) goto L34
            r6 = 46
            if (r3 == r6) goto L30
            r6 = 58
            if (r3 == r6) goto L2c
        L2a:
            r3 = 0
            goto L3d
        L2c:
            if (r5 != r7) goto L2a
            r3 = 5
            goto L3d
        L30:
            if (r5 != r7) goto L2a
            r3 = 3
            goto L3d
        L34:
            if (r5 != r7) goto L2a
            r3 = 4
            goto L3d
        L38:
            r3 = 10
            if (r5 != r3) goto L2a
            r3 = 2
        L3d:
            if (r3 <= 0) goto L45
            java.util.Collection r0 = updateStateListForPair(r0, r2, r3)
            r2 = r4
            goto L49
        L45:
            java.util.Collection r0 = r8.updateStateListForChar(r0, r2)
        L49:
            int r2 = r2 + 1
            goto L8
        L4c:
            com.google.zxing.aztec.encoder.HighLevelEncoder$1 r1 = new com.google.zxing.aztec.encoder.HighLevelEncoder$1
            r1.<init>()
            java.lang.Object r0 = java.util.Collections.min(r0, r1)
            com.google.zxing.aztec.encoder.State r0 = (com.google.zxing.aztec.encoder.State) r0
            byte[] r1 = r8.text
            com.google.zxing.common.BitArray r0 = r0.toBitArray(r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.aztec.encoder.HighLevelEncoder.encode():com.google.zxing.common.BitArray");
    }

    private Collection<State> updateStateListForChar(Iterable<State> iterable, int r4) {
        LinkedList linkedList = new LinkedList();
        for (State state : iterable) {
            updateStateForChar(state, r4, linkedList);
        }
        return simplifyStates(linkedList);
    }

    private void updateStateForChar(State state, int r9, Collection<State> collection) {
        char c = (char) (this.text[r9] & 255);
        boolean z = CHAR_MAP[state.getMode()][c] > 0;
        State state2 = null;
        for (int r2 = 0; r2 <= 4; r2++) {
            int r4 = CHAR_MAP[r2][c];
            if (r4 > 0) {
                if (state2 == null) {
                    state2 = state.endBinaryShift(r9);
                }
                if (!z || r2 == state.getMode() || r2 == 2) {
                    collection.add(state2.latchAndAppend(r2, r4));
                }
                if (!z && SHIFT_TABLE[state.getMode()][r2] >= 0) {
                    collection.add(state2.shiftAndAppend(r2, r4));
                }
            }
        }
        if (state.getBinaryShiftByteCount() > 0 || CHAR_MAP[state.getMode()][c] == 0) {
            collection.add(state.addBinaryShiftChar(r9));
        }
    }

    private static Collection<State> updateStateListForPair(Iterable<State> iterable, int r3, int r4) {
        LinkedList linkedList = new LinkedList();
        for (State state : iterable) {
            updateStateForPair(state, r3, r4, linkedList);
        }
        return simplifyStates(linkedList);
    }

    private static void updateStateForPair(State state, int r5, int r6, Collection<State> collection) {
        State endBinaryShift = state.endBinaryShift(r5);
        collection.add(endBinaryShift.latchAndAppend(4, r6));
        if (state.getMode() != 4) {
            collection.add(endBinaryShift.shiftAndAppend(4, r6));
        }
        if (r6 == 3 || r6 == 4) {
            collection.add(endBinaryShift.latchAndAppend(2, 16 - r6).latchAndAppend(2, 1));
        }
        if (state.getBinaryShiftByteCount() > 0) {
            collection.add(state.addBinaryShiftChar(r5).addBinaryShiftChar(r5 + 1));
        }
    }

    private static Collection<State> simplifyStates(Iterable<State> iterable) {
        LinkedList linkedList = new LinkedList();
        for (State state : iterable) {
            boolean z = true;
            Iterator it = linkedList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                State state2 = (State) it.next();
                if (state2.isBetterThanOrEqualTo(state)) {
                    z = false;
                    break;
                } else if (state.isBetterThanOrEqualTo(state2)) {
                    it.remove();
                }
            }
            if (z) {
                linkedList.add(state);
            }
        }
        return linkedList;
    }
}
