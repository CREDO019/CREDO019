package com.google.zxing.aztec.encoder;

import com.google.zxing.common.BitArray;
import java.util.LinkedList;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class State {
    static final State INITIAL_STATE = new State(Token.EMPTY, 0, 0, 0);
    private final int binaryShiftByteCount;
    private final int bitCount;
    private final int mode;
    private final Token token;

    private State(Token token, int r2, int r3, int r4) {
        this.token = token;
        this.mode = r2;
        this.binaryShiftByteCount = r3;
        this.bitCount = r4;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getMode() {
        return this.mode;
    }

    Token getToken() {
        return this.token;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getBinaryShiftByteCount() {
        return this.binaryShiftByteCount;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getBitCount() {
        return this.bitCount;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public State latchAndAppend(int r5, int r6) {
        int r0 = this.bitCount;
        Token token = this.token;
        if (r5 != this.mode) {
            int r2 = HighLevelEncoder.LATCH_TABLE[this.mode][r5];
            int r3 = 65535 & r2;
            int r22 = r2 >> 16;
            token = token.add(r3, r22);
            r0 += r22;
        }
        int r23 = r5 == 2 ? 4 : 5;
        return new State(token.add(r6, r23), r5, 0, r0 + r23);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public State shiftAndAppend(int r6, int r7) {
        Token token = this.token;
        int r1 = this.mode == 2 ? 4 : 5;
        return new State(token.add(HighLevelEncoder.SHIFT_TABLE[this.mode][r6], r1).add(r7, 5), this.mode, 0, this.bitCount + r1 + 5);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public State addBinaryShiftChar(int r7) {
        Token token = this.token;
        int r1 = this.mode;
        int r2 = this.bitCount;
        if (r1 == 4 || r1 == 2) {
            int r12 = HighLevelEncoder.LATCH_TABLE[r1][0];
            int r4 = 65535 & r12;
            int r13 = r12 >> 16;
            token = token.add(r4, r13);
            r2 += r13;
            r1 = 0;
        }
        int r3 = this.binaryShiftByteCount;
        State state = new State(token, r1, this.binaryShiftByteCount + 1, r2 + ((r3 == 0 || r3 == 31) ? 18 : r3 == 62 ? 9 : 8));
        return state.binaryShiftByteCount == 2078 ? state.endBinaryShift(r7 + 1) : state;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public State endBinaryShift(int r5) {
        int r0 = this.binaryShiftByteCount;
        return r0 == 0 ? this : new State(this.token.addBinaryShift(r5 - r0, r0), this.mode, 0, this.bitCount);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isBetterThanOrEqualTo(State state) {
        int r2;
        int r0 = this.bitCount + (HighLevelEncoder.LATCH_TABLE[this.mode][state.mode] >> 16);
        int r1 = state.binaryShiftByteCount;
        if (r1 > 0 && ((r2 = this.binaryShiftByteCount) == 0 || r2 > r1)) {
            r0 += 10;
        }
        return r0 <= state.bitCount;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BitArray toBitArray(byte[] bArr) {
        LinkedList<Token> linkedList = new LinkedList();
        for (Token token = endBinaryShift(bArr.length).token; token != null; token = token.getPrevious()) {
            linkedList.addFirst(token);
        }
        BitArray bitArray = new BitArray();
        for (Token token2 : linkedList) {
            token2.appendTo(bitArray, bArr);
        }
        return bitArray;
    }

    public String toString() {
        return String.format("%s bits=%d bytes=%d", HighLevelEncoder.MODE_NAMES[this.mode], Integer.valueOf(this.bitCount), Integer.valueOf(this.binaryShiftByteCount));
    }
}
