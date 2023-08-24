package org.bouncycastle.pqc.crypto.mceliece;

import org.bouncycastle.pqc.math.linearalgebra.GF2Matrix;

/* loaded from: classes3.dex */
public class McElieceCCA2PublicKeyParameters extends McElieceCCA2KeyParameters {
    private GF2Matrix matrixG;

    /* renamed from: n */
    private int f2450n;

    /* renamed from: t */
    private int f2451t;

    public McElieceCCA2PublicKeyParameters(int r2, int r3, GF2Matrix gF2Matrix, String str) {
        super(false, str);
        this.f2450n = r2;
        this.f2451t = r3;
        this.matrixG = new GF2Matrix(gF2Matrix);
    }

    public GF2Matrix getG() {
        return this.matrixG;
    }

    public int getK() {
        return this.matrixG.getNumRows();
    }

    public int getN() {
        return this.f2450n;
    }

    public int getT() {
        return this.f2451t;
    }
}
