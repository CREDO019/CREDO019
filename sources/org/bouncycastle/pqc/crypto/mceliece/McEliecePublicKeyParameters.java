package org.bouncycastle.pqc.crypto.mceliece;

import org.bouncycastle.pqc.math.linearalgebra.GF2Matrix;

/* loaded from: classes3.dex */
public class McEliecePublicKeyParameters extends McElieceKeyParameters {

    /* renamed from: g */
    private GF2Matrix f2479g;

    /* renamed from: n */
    private int f2480n;

    /* renamed from: t */
    private int f2481t;

    public McEliecePublicKeyParameters(int r3, int r4, GF2Matrix gF2Matrix) {
        super(false, null);
        this.f2480n = r3;
        this.f2481t = r4;
        this.f2479g = new GF2Matrix(gF2Matrix);
    }

    public GF2Matrix getG() {
        return this.f2479g;
    }

    public int getK() {
        return this.f2479g.getNumRows();
    }

    public int getN() {
        return this.f2480n;
    }

    public int getT() {
        return this.f2481t;
    }
}
