package org.bouncycastle.math.p039ec.custom.sec;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.math.p039ec.AbstractECLookupTable;
import org.bouncycastle.math.p039ec.ECConstants;
import org.bouncycastle.math.p039ec.ECCurve;
import org.bouncycastle.math.p039ec.ECFieldElement;
import org.bouncycastle.math.p039ec.ECLookupTable;
import org.bouncycastle.math.p039ec.ECPoint;
import org.bouncycastle.math.raw.Nat160;
import org.bouncycastle.util.encoders.Hex;

/* renamed from: org.bouncycastle.math.ec.custom.sec.SecP160R2Curve */
/* loaded from: classes5.dex */
public class SecP160R2Curve extends ECCurve.AbstractFp {
    private static final int SECP160R2_DEFAULT_COORDS = 2;
    protected SecP160R2Point infinity;

    /* renamed from: q */
    public static final BigInteger f2295q = SecP160R2FieldElement.f2298Q;
    private static final ECFieldElement[] SECP160R2_AFFINE_ZS = {new SecP160R2FieldElement(ECConstants.ONE)};

    public SecP160R2Curve() {
        super(f2295q);
        this.infinity = new SecP160R2Point(this, null, null);
        this.f2252a = fromBigInteger(new BigInteger(1, Hex.decodeStrict("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEFFFFAC70")));
        this.f2253b = fromBigInteger(new BigInteger(1, Hex.decodeStrict("B4E134D3FB59EB8BAB57274904664D5AF50388BA")));
        this.order = new BigInteger(1, Hex.decodeStrict("0100000000000000000000351EE786A818F3A1A16B"));
        this.cofactor = BigInteger.valueOf(1L);
        this.coord = 2;
    }

    @Override // org.bouncycastle.math.p039ec.ECCurve
    protected ECCurve cloneCurve() {
        return new SecP160R2Curve();
    }

    @Override // org.bouncycastle.math.p039ec.ECCurve
    public ECLookupTable createCacheSafeLookupTable(ECPoint[] eCPointArr, int r8, final int r9) {
        final int[] r0 = new int[r9 * 5 * 2];
        int r3 = 0;
        for (int r2 = 0; r2 < r9; r2++) {
            ECPoint eCPoint = eCPointArr[r8 + r2];
            Nat160.copy(((SecP160R2FieldElement) eCPoint.getRawXCoord()).f2299x, 0, r0, r3);
            int r32 = r3 + 5;
            Nat160.copy(((SecP160R2FieldElement) eCPoint.getRawYCoord()).f2299x, 0, r0, r32);
            r3 = r32 + 5;
        }
        return new AbstractECLookupTable() { // from class: org.bouncycastle.math.ec.custom.sec.SecP160R2Curve.1
            private ECPoint createPoint(int[] r33, int[] r4) {
                return SecP160R2Curve.this.createRawPoint(new SecP160R2FieldElement(r33), new SecP160R2FieldElement(r4), SecP160R2Curve.SECP160R2_AFFINE_ZS);
            }

            @Override // org.bouncycastle.math.p039ec.ECLookupTable
            public int getSize() {
                return r9;
            }

            @Override // org.bouncycastle.math.p039ec.ECLookupTable
            public ECPoint lookup(int r11) {
                int[] create = Nat160.create();
                int[] create2 = Nat160.create();
                int r4 = 0;
                for (int r33 = 0; r33 < r9; r33++) {
                    int r5 = ((r33 ^ r11) - 1) >> 31;
                    for (int r6 = 0; r6 < 5; r6++) {
                        int r7 = create[r6];
                        int[] r82 = r0;
                        create[r6] = r7 ^ (r82[r4 + r6] & r5);
                        create2[r6] = create2[r6] ^ (r82[(r4 + 5) + r6] & r5);
                    }
                    r4 += 10;
                }
                return createPoint(create, create2);
            }

            @Override // org.bouncycastle.math.p039ec.AbstractECLookupTable, org.bouncycastle.math.p039ec.ECLookupTable
            public ECPoint lookupVar(int r7) {
                int[] create = Nat160.create();
                int[] create2 = Nat160.create();
                int r72 = r7 * 5 * 2;
                for (int r33 = 0; r33 < 5; r33++) {
                    int[] r4 = r0;
                    create[r33] = r4[r72 + r33];
                    create2[r33] = r4[r72 + 5 + r33];
                }
                return createPoint(create, create2);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.bouncycastle.math.p039ec.ECCurve
    public ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        return new SecP160R2Point(this, eCFieldElement, eCFieldElement2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.bouncycastle.math.p039ec.ECCurve
    public ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr) {
        return new SecP160R2Point(this, eCFieldElement, eCFieldElement2, eCFieldElementArr);
    }

    @Override // org.bouncycastle.math.p039ec.ECCurve
    public ECFieldElement fromBigInteger(BigInteger bigInteger) {
        return new SecP160R2FieldElement(bigInteger);
    }

    @Override // org.bouncycastle.math.p039ec.ECCurve
    public int getFieldSize() {
        return f2295q.bitLength();
    }

    @Override // org.bouncycastle.math.p039ec.ECCurve
    public ECPoint getInfinity() {
        return this.infinity;
    }

    public BigInteger getQ() {
        return f2295q;
    }

    @Override // org.bouncycastle.math.p039ec.ECCurve.AbstractFp, org.bouncycastle.math.p039ec.ECCurve
    public ECFieldElement randomFieldElement(SecureRandom secureRandom) {
        int[] create = Nat160.create();
        SecP160R2Field.random(secureRandom, create);
        return new SecP160R2FieldElement(create);
    }

    @Override // org.bouncycastle.math.p039ec.ECCurve.AbstractFp, org.bouncycastle.math.p039ec.ECCurve
    public ECFieldElement randomFieldElementMult(SecureRandom secureRandom) {
        int[] create = Nat160.create();
        SecP160R2Field.randomMult(secureRandom, create);
        return new SecP160R2FieldElement(create);
    }

    @Override // org.bouncycastle.math.p039ec.ECCurve
    public boolean supportsCoordinateSystem(int r2) {
        return r2 == 2;
    }
}