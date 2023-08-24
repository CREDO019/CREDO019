package org.bouncycastle.pqc.jcajce.spec;

import java.security.InvalidParameterException;
import java.security.spec.AlgorithmParameterSpec;
import org.bouncycastle.pqc.math.linearalgebra.PolynomialRingGF2;

/* loaded from: classes4.dex */
public class McElieceKeyGenParameterSpec implements AlgorithmParameterSpec {
    public static final int DEFAULT_M = 11;
    public static final int DEFAULT_T = 50;
    private int fieldPoly;

    /* renamed from: m */
    private int f2521m;

    /* renamed from: n */
    private int f2522n;

    /* renamed from: t */
    private int f2523t;

    public McElieceKeyGenParameterSpec() {
        this(11, 50);
    }

    public McElieceKeyGenParameterSpec(int r3) {
        if (r3 < 1) {
            throw new IllegalArgumentException("key size must be positive");
        }
        this.f2521m = 0;
        this.f2522n = 1;
        while (true) {
            int r1 = this.f2522n;
            if (r1 >= r3) {
                int r32 = r1 >>> 1;
                this.f2523t = r32;
                int r0 = this.f2521m;
                this.f2523t = r32 / r0;
                this.fieldPoly = PolynomialRingGF2.getIrreduciblePolynomial(r0);
                return;
            }
            this.f2522n = r1 << 1;
            this.f2521m++;
        }
    }

    public McElieceKeyGenParameterSpec(int r3, int r4) throws InvalidParameterException {
        if (r3 < 1) {
            throw new IllegalArgumentException("m must be positive");
        }
        if (r3 > 32) {
            throw new IllegalArgumentException("m is too large");
        }
        this.f2521m = r3;
        int r0 = 1 << r3;
        this.f2522n = r0;
        if (r4 < 0) {
            throw new IllegalArgumentException("t must be positive");
        }
        if (r4 > r0) {
            throw new IllegalArgumentException("t must be less than n = 2^m");
        }
        this.f2523t = r4;
        this.fieldPoly = PolynomialRingGF2.getIrreduciblePolynomial(r3);
    }

    public McElieceKeyGenParameterSpec(int r3, int r4, int r5) {
        this.f2521m = r3;
        if (r3 < 1) {
            throw new IllegalArgumentException("m must be positive");
        }
        if (r3 > 32) {
            throw new IllegalArgumentException(" m is too large");
        }
        int r0 = 1 << r3;
        this.f2522n = r0;
        this.f2523t = r4;
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
    }

    public int getFieldPoly() {
        return this.fieldPoly;
    }

    public int getM() {
        return this.f2521m;
    }

    public int getN() {
        return this.f2522n;
    }

    public int getT() {
        return this.f2523t;
    }
}
