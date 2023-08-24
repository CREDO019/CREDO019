package org.bouncycastle.pqc.jcajce.spec;

import java.security.spec.AlgorithmParameterSpec;
import org.bouncycastle.pqc.math.linearalgebra.PolynomialRingGF2;

/* loaded from: classes4.dex */
public class McElieceCCA2KeyGenParameterSpec implements AlgorithmParameterSpec {
    public static final int DEFAULT_M = 11;
    public static final int DEFAULT_T = 50;
    public static final String SHA1 = "SHA-1";
    public static final String SHA224 = "SHA-224";
    public static final String SHA256 = "SHA-256";
    public static final String SHA384 = "SHA-384";
    public static final String SHA512 = "SHA-512";
    private final String digest;
    private int fieldPoly;

    /* renamed from: m */
    private final int f2518m;

    /* renamed from: n */
    private final int f2519n;

    /* renamed from: t */
    private final int f2520t;

    public McElieceCCA2KeyGenParameterSpec() {
        this(11, 50, "SHA-256");
    }

    public McElieceCCA2KeyGenParameterSpec(int r2) {
        this(r2, "SHA-256");
    }

    public McElieceCCA2KeyGenParameterSpec(int r2, int r3) {
        this(r2, r3, "SHA-256");
    }

    public McElieceCCA2KeyGenParameterSpec(int r2, int r3, int r4) {
        this(r2, r3, r4, "SHA-256");
    }

    public McElieceCCA2KeyGenParameterSpec(int r3, int r4, int r5, String str) {
        this.f2518m = r3;
        if (r3 < 1) {
            throw new IllegalArgumentException("m must be positive");
        }
        if (r3 > 32) {
            throw new IllegalArgumentException(" m is too large");
        }
        int r0 = 1 << r3;
        this.f2519n = r0;
        this.f2520t = r4;
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
        this.digest = str;
    }

    public McElieceCCA2KeyGenParameterSpec(int r3, int r4, String str) {
        if (r3 < 1) {
            throw new IllegalArgumentException("m must be positive");
        }
        if (r3 > 32) {
            throw new IllegalArgumentException("m is too large");
        }
        this.f2518m = r3;
        int r0 = 1 << r3;
        this.f2519n = r0;
        if (r4 < 0) {
            throw new IllegalArgumentException("t must be positive");
        }
        if (r4 > r0) {
            throw new IllegalArgumentException("t must be less than n = 2^m");
        }
        this.f2520t = r4;
        this.fieldPoly = PolynomialRingGF2.getIrreduciblePolynomial(r3);
        this.digest = str;
    }

    public McElieceCCA2KeyGenParameterSpec(int r3, String str) {
        int r0 = 1;
        if (r3 < 1) {
            throw new IllegalArgumentException("key size must be positive");
        }
        int r1 = 0;
        while (r0 < r3) {
            r0 <<= 1;
            r1++;
        }
        this.f2520t = (r0 >>> 1) / r1;
        this.f2518m = r1;
        this.f2519n = r0;
        this.fieldPoly = PolynomialRingGF2.getIrreduciblePolynomial(r1);
        this.digest = str;
    }

    public String getDigest() {
        return this.digest;
    }

    public int getFieldPoly() {
        return this.fieldPoly;
    }

    public int getM() {
        return this.f2518m;
    }

    public int getN() {
        return this.f2519n;
    }

    public int getT() {
        return this.f2520t;
    }
}
