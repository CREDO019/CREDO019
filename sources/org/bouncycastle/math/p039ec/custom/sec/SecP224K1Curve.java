package org.bouncycastle.math.p039ec.custom.sec;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.math.p039ec.AbstractECLookupTable;
import org.bouncycastle.math.p039ec.ECConstants;
import org.bouncycastle.math.p039ec.ECCurve;
import org.bouncycastle.math.p039ec.ECFieldElement;
import org.bouncycastle.math.p039ec.ECLookupTable;
import org.bouncycastle.math.p039ec.ECPoint;
import org.bouncycastle.math.raw.Nat224;
import org.bouncycastle.util.encoders.Hex;

/* renamed from: org.bouncycastle.math.ec.custom.sec.SecP224K1Curve */
/* loaded from: classes5.dex */
public class SecP224K1Curve extends ECCurve.AbstractFp {
    private static final int SECP224K1_DEFAULT_COORDS = 2;
    protected SecP224K1Point infinity;

    /* renamed from: q */
    public static final BigInteger f2311q = SecP224K1FieldElement.f2314Q;
    private static final ECFieldElement[] SECP224K1_AFFINE_ZS = {new SecP224K1FieldElement(ECConstants.ONE)};

    public SecP224K1Curve() {
        super(f2311q);
        this.infinity = new SecP224K1Point(this, null, null);
        this.f2252a = fromBigInteger(ECConstants.ZERO);
        this.f2253b = fromBigInteger(BigInteger.valueOf(5L));
        this.order = new BigInteger(1, Hex.decodeStrict("010000000000000000000000000001DCE8D2EC6184CAF0A971769FB1F7"));
        this.cofactor = BigInteger.valueOf(1L);
        this.coord = 2;
    }

    @Override // org.bouncycastle.math.p039ec.ECCurve
    protected ECCurve cloneCurve() {
        return new SecP224K1Curve();
    }

    @Override // org.bouncycastle.math.p039ec.ECCurve
    public ECLookupTable createCacheSafeLookupTable(ECPoint[] eCPointArr, int r8, final int r9) {
        final int[] r0 = new int[r9 * 7 * 2];
        int r3 = 0;
        for (int r2 = 0; r2 < r9; r2++) {
            ECPoint eCPoint = eCPointArr[r8 + r2];
            Nat224.copy(((SecP224K1FieldElement) eCPoint.getRawXCoord()).f2315x, 0, r0, r3);
            int r32 = r3 + 7;
            Nat224.copy(((SecP224K1FieldElement) eCPoint.getRawYCoord()).f2315x, 0, r0, r32);
            r3 = r32 + 7;
        }
        return new AbstractECLookupTable() { // from class: org.bouncycastle.math.ec.custom.sec.SecP224K1Curve.1
            private ECPoint createPoint(int[] r33, int[] r4) {
                return SecP224K1Curve.this.createRawPoint(new SecP224K1FieldElement(r33), new SecP224K1FieldElement(r4), SecP224K1Curve.SECP224K1_AFFINE_ZS);
            }

            @Override // org.bouncycastle.math.p039ec.ECLookupTable
            public int getSize() {
                return r9;
            }

            @Override // org.bouncycastle.math.p039ec.ECLookupTable
            public ECPoint lookup(int r11) {
                int[] create = Nat224.create();
                int[] create2 = Nat224.create();
                int r4 = 0;
                for (int r33 = 0; r33 < r9; r33++) {
                    int r5 = ((r33 ^ r11) - 1) >> 31;
                    for (int r6 = 0; r6 < 7; r6++) {
                        int r7 = create[r6];
                        int[] r82 = r0;
                        create[r6] = r7 ^ (r82[r4 + r6] & r5);
                        create2[r6] = create2[r6] ^ (r82[(r4 + 7) + r6] & r5);
                    }
                    r4 += 14;
                }
                return createPoint(create, create2);
            }

            @Override // org.bouncycastle.math.p039ec.AbstractECLookupTable, org.bouncycastle.math.p039ec.ECLookupTable
            public ECPoint lookupVar(int r11) {
                int[] create = Nat224.create();
                int[] create2 = Nat224.create();
                int r4 = 0;
                for (int r33 = 0; r33 < r9; r33++) {
                    int r5 = ((r33 ^ r11) - 1) >> 31;
                    for (int r6 = 0; r6 < 7; r6++) {
                        int r7 = create[r6];
                        int[] r82 = r0;
                        create[r6] = r7 ^ (r82[r4 + r6] & r5);
                        create2[r6] = create2[r6] ^ (r82[(r4 + 7) + r6] & r5);
                    }
                    r4 += 14;
                }
                return createPoint(create, create2);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.bouncycastle.math.p039ec.ECCurve
    public ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        return new SecP224K1Point(this, eCFieldElement, eCFieldElement2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.bouncycastle.math.p039ec.ECCurve
    public ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr) {
        return new SecP224K1Point(this, eCFieldElement, eCFieldElement2, eCFieldElementArr);
    }

    @Override // org.bouncycastle.math.p039ec.ECCurve
    public ECFieldElement fromBigInteger(BigInteger bigInteger) {
        return new SecP224K1FieldElement(bigInteger);
    }

    @Override // org.bouncycastle.math.p039ec.ECCurve
    public int getFieldSize() {
        return f2311q.bitLength();
    }

    @Override // org.bouncycastle.math.p039ec.ECCurve
    public ECPoint getInfinity() {
        return this.infinity;
    }

    public BigInteger getQ() {
        return f2311q;
    }

    @Override // org.bouncycastle.math.p039ec.ECCurve.AbstractFp, org.bouncycastle.math.p039ec.ECCurve
    public ECFieldElement randomFieldElement(SecureRandom secureRandom) {
        int[] create = Nat224.create();
        SecP224K1Field.random(secureRandom, create);
        return new SecP224K1FieldElement(create);
    }

    @Override // org.bouncycastle.math.p039ec.ECCurve.AbstractFp, org.bouncycastle.math.p039ec.ECCurve
    public ECFieldElement randomFieldElementMult(SecureRandom secureRandom) {
        int[] create = Nat224.create();
        SecP224K1Field.randomMult(secureRandom, create);
        return new SecP224K1FieldElement(create);
    }

    @Override // org.bouncycastle.math.p039ec.ECCurve
    public boolean supportsCoordinateSystem(int r2) {
        return r2 == 2;
    }
}
