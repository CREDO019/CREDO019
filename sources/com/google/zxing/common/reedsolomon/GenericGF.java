package com.google.zxing.common.reedsolomon;

/* loaded from: classes3.dex */
public final class GenericGF {
    public static final GenericGF AZTEC_DATA_6;
    public static final GenericGF AZTEC_DATA_8;
    public static final GenericGF AZTEC_PARAM;
    public static final GenericGF DATA_MATRIX_FIELD_256;
    public static final GenericGF MAXICODE_FIELD_64;
    public static final GenericGF QR_CODE_FIELD_256;
    private final int[] expTable;
    private final int generatorBase;
    private final int[] logTable;
    private final GenericGFPoly one;
    private final int primitive;
    private final int size;
    private final GenericGFPoly zero;
    public static final GenericGF AZTEC_DATA_12 = new GenericGF(4201, 4096, 1);
    public static final GenericGF AZTEC_DATA_10 = new GenericGF(1033, 1024, 1);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int addOrSubtract(int r0, int r1) {
        return r0 ^ r1;
    }

    static {
        GenericGF genericGF = new GenericGF(67, 64, 1);
        AZTEC_DATA_6 = genericGF;
        AZTEC_PARAM = new GenericGF(19, 16, 1);
        QR_CODE_FIELD_256 = new GenericGF(285, 256, 0);
        GenericGF genericGF2 = new GenericGF(301, 256, 1);
        DATA_MATRIX_FIELD_256 = genericGF2;
        AZTEC_DATA_8 = genericGF2;
        MAXICODE_FIELD_64 = genericGF;
    }

    public GenericGF(int r5, int r6, int r7) {
        this.primitive = r5;
        this.size = r6;
        this.generatorBase = r7;
        this.expTable = new int[r6];
        this.logTable = new int[r6];
        int r2 = 1;
        for (int r1 = 0; r1 < r6; r1++) {
            this.expTable[r1] = r2;
            r2 <<= 1;
            if (r2 >= r6) {
                r2 = (r2 ^ r5) & (r6 - 1);
            }
        }
        for (int r52 = 0; r52 < r6 - 1; r52++) {
            this.logTable[this.expTable[r52]] = r52;
        }
        this.zero = new GenericGFPoly(this, new int[]{0});
        this.one = new GenericGFPoly(this, new int[]{1});
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GenericGFPoly getZero() {
        return this.zero;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GenericGFPoly getOne() {
        return this.one;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GenericGFPoly buildMonomial(int r2, int r3) {
        if (r2 >= 0) {
            if (r3 == 0) {
                return this.zero;
            }
            int[] r22 = new int[r2 + 1];
            r22[0] = r3;
            return new GenericGFPoly(this, r22);
        }
        throw new IllegalArgumentException();
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
        return this.expTable[(this.size - this.logTable[r4]) - 1];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int multiply(int r3, int r4) {
        if (r3 == 0 || r4 == 0) {
            return 0;
        }
        int[] r0 = this.expTable;
        int[] r1 = this.logTable;
        return r0[(r1[r3] + r1[r4]) % (this.size - 1)];
    }

    public int getSize() {
        return this.size;
    }

    public int getGeneratorBase() {
        return this.generatorBase;
    }

    public String toString() {
        return "GF(0x" + Integer.toHexString(this.primitive) + ',' + this.size + ')';
    }
}
