package org.bouncycastle.pqc.crypto.mceliece;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.pqc.math.linearalgebra.PolynomialRingGF2;

/* loaded from: classes3.dex */
public class McElieceParameters implements CipherParameters {
    public static final int DEFAULT_M = 11;
    public static final int DEFAULT_T = 50;
    private Digest digest;
    private int fieldPoly;

    /* renamed from: m */
    private int f2467m;

    /* renamed from: n */
    private int f2468n;

    /* renamed from: t */
    private int f2469t;

    public McElieceParameters() {
        this(11, 50);
    }

    public McElieceParameters(int r2) {
        this(r2, (Digest) null);
    }

    public McElieceParameters(int r2, int r3) {
        this(r2, r3, (Digest) null);
    }

    public McElieceParameters(int r2, int r3, int r4) {
        this(r2, r3, r4, null);
    }

    public McElieceParameters(int r3, int r4, int r5, Digest digest) {
        this.f2467m = r3;
        if (r3 < 1) {
            throw new IllegalArgumentException("m must be positive");
        }
        if (r3 > 32) {
            throw new IllegalArgumentException(" m is too large");
        }
        int r0 = 1 << r3;
        this.f2468n = r0;
        this.f2469t = r4;
        if (r4 < 0) {
            throw new IllegalArgumentException("t must be positive");
        }
        if (r4 > r0) {
            throw new IllegalArgumentException("t must be less than n = 2^m");
        }
        if (PolynomialRingGF2.degree(r5) != r3 || !PolynomialRingGF2.isIrreducible(r5)) {
            throw new IllegalArgumentException("polynomial is not a field polynomial for GF(2^m)");
        }
        this.fieldPoly = r5;
        this.digest = digest;
    }

    public McElieceParameters(int r3, int r4, Digest digest) {
        if (r3 < 1) {
            throw new IllegalArgumentException("m must be positive");
        }
        if (r3 > 32) {
            throw new IllegalArgumentException("m is too large");
        }
        this.f2467m = r3;
        int r0 = 1 << r3;
        this.f2468n = r0;
        if (r4 < 0) {
            throw new IllegalArgumentException("t must be positive");
        }
        if (r4 > r0) {
            throw new IllegalArgumentException("t must be less than n = 2^m");
        }
        this.f2469t = r4;
        this.fieldPoly = PolynomialRingGF2.getIrreduciblePolynomial(r3);
        this.digest = digest;
    }

    public McElieceParameters(int r3, Digest digest) {
        if (r3 < 1) {
            throw new IllegalArgumentException("key size must be positive");
        }
        this.f2467m = 0;
        this.f2468n = 1;
        while (true) {
            int r1 = this.f2468n;
            if (r1 >= r3) {
                int r32 = r1 >>> 1;
                this.f2469t = r32;
                int r0 = this.f2467m;
                this.f2469t = r32 / r0;
                this.fieldPoly = PolynomialRingGF2.getIrreduciblePolynomial(r0);
                this.digest = digest;
                return;
            }
            this.f2468n = r1 << 1;
            this.f2467m++;
        }
    }

    public McElieceParameters(Digest digest) {
        this(11, 50, digest);
    }

    public int getFieldPoly() {
        return this.fieldPoly;
    }

    public int getM() {
        return this.f2467m;
    }

    public int getN() {
        return this.f2468n;
    }

    public int getT() {
        return this.f2469t;
    }
}
