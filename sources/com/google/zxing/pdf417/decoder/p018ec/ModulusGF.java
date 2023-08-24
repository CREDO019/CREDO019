package com.google.zxing.pdf417.decoder.p018ec;

import com.google.zxing.pdf417.PDF417Common;

/* renamed from: com.google.zxing.pdf417.decoder.ec.ModulusGF */
/* loaded from: classes3.dex */
public final class ModulusGF {
    public static final ModulusGF PDF417_GF = new ModulusGF(PDF417Common.NUMBER_OF_CODEWORDS, 3);
    private final int[] expTable;
    private final int[] logTable;
    private final int modulus;
    private final ModulusPoly one;
    private final ModulusPoly zero;

    private ModulusGF(int r6, int r7) {
        this.modulus = r6;
        this.expTable = new int[r6];
        this.logTable = new int[r6];
        int r3 = 1;
        for (int r2 = 0; r2 < r6; r2++) {
            this.expTable[r2] = r3;
            r3 = (r3 * r7) % r6;
        }
        for (int r72 = 0; r72 < r6 - 1; r72++) {
            this.logTable[this.expTable[r72]] = r72;
        }
        this.zero = new ModulusPoly(this, new int[]{0});
        this.one = new ModulusPoly(this, new int[]{1});
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ModulusPoly getZero() {
        return this.zero;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ModulusPoly getOne() {
        return this.one;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ModulusPoly buildMonomial(int r2, int r3) {
        if (r2 >= 0) {
            if (r3 == 0) {
                return this.zero;
            }
            int[] r22 = new int[r2 + 1];
            r22[0] = r3;
            return new ModulusPoly(this, r22);
        }
        throw new IllegalArgumentException();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int add(int r1, int r2) {
        return (r1 + r2) % this.modulus;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int subtract(int r2, int r3) {
        int r0 = this.modulus;
        return ((r2 + r0) - r3) % r0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int exp(int r2) {
        return this.expTable[r2];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int log(int r2) {
        if (r2 == 0) {
            throw new IllegalArgumentException();
        }
        return this.logTable[r2];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int inverse(int r4) {
        if (r4 == 0) {
            throw new ArithmeticException();
        }
        return this.expTable[(this.modulus - this.logTable[r4]) - 1];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int multiply(int r3, int r4) {
        if (r3 == 0 || r4 == 0) {
            return 0;
        }
        int[] r0 = this.expTable;
        int[] r1 = this.logTable;
        return r0[(r1[r3] + r1[r4]) % (this.modulus - 1)];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getSize() {
        return this.modulus;
    }
}
