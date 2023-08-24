package org.bouncycastle.math.p039ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.math.p039ec.AbstractECLookupTable;
import org.bouncycastle.math.p039ec.ECConstants;
import org.bouncycastle.math.p039ec.ECCurve;
import org.bouncycastle.math.p039ec.ECFieldElement;
import org.bouncycastle.math.p039ec.ECLookupTable;
import org.bouncycastle.math.p039ec.ECPoint;
import org.bouncycastle.math.raw.Nat128;
import org.bouncycastle.util.encoders.Hex;

/* renamed from: org.bouncycastle.math.ec.custom.sec.SecT113R1Curve */
/* loaded from: classes5.dex */
public class SecT113R1Curve extends ECCurve.AbstractF2m {
    private static final ECFieldElement[] SECT113R1_AFFINE_ZS = {new SecT113FieldElement(ECConstants.ONE)};
    private static final int SECT113R1_DEFAULT_COORDS = 6;
    protected SecT113R1Point infinity;

    public SecT113R1Curve() {
        super(113, 9, 0, 0);
        this.infinity = new SecT113R1Point(this, null, null);
        this.f2252a = fromBigInteger(new BigInteger(1, Hex.decodeStrict("003088250CA6E7C7FE649CE85820F7")));
        this.f2253b = fromBigInteger(new BigInteger(1, Hex.decodeStrict("00E8BEE4D3E2260744188BE0E9C723")));
        this.order = new BigInteger(1, Hex.decodeStrict("0100000000000000D9CCEC8A39E56F"));
        this.cofactor = BigInteger.valueOf(2L);
        this.coord = 6;
    }

    @Override // org.bouncycastle.math.p039ec.ECCurve
    protected ECCurve cloneCurve() {
        return new SecT113R1Curve();
    }

    @Override // org.bouncycastle.math.p039ec.ECCurve
    public ECLookupTable createCacheSafeLookupTable(ECPoint[] eCPointArr, int r8, final int r9) {
        final long[] jArr = new long[r9 * 2 * 2];
        int r3 = 0;
        for (int r2 = 0; r2 < r9; r2++) {
            ECPoint eCPoint = eCPointArr[r8 + r2];
            Nat128.copy64(((SecT113FieldElement) eCPoint.getRawXCoord()).f2342x, 0, jArr, r3);
            int r32 = r3 + 2;
            Nat128.copy64(((SecT113FieldElement) eCPoint.getRawYCoord()).f2342x, 0, jArr, r32);
            r3 = r32 + 2;
        }
        return new AbstractECLookupTable() { // from class: org.bouncycastle.math.ec.custom.sec.SecT113R1Curve.1
            private ECPoint createPoint(long[] jArr2, long[] jArr3) {
                return SecT113R1Curve.this.createRawPoint(new SecT113FieldElement(jArr2), new SecT113FieldElement(jArr3), SecT113R1Curve.SECT113R1_AFFINE_ZS);
            }

            @Override // org.bouncycastle.math.p039ec.ECLookupTable
            public int getSize() {
                return r9;
            }

            @Override // org.bouncycastle.math.p039ec.ECLookupTable
            public ECPoint lookup(int r14) {
                long[] create64 = Nat128.create64();
                long[] create642 = Nat128.create64();
                int r4 = 0;
                for (int r33 = 0; r33 < r9; r33++) {
                    long j = ((r33 ^ r14) - 1) >> 31;
                    for (int r7 = 0; r7 < 2; r7++) {
                        long j2 = create64[r7];
                        long[] jArr2 = jArr;
                        create64[r7] = j2 ^ (jArr2[r4 + r7] & j);
                        create642[r7] = create642[r7] ^ (jArr2[(r4 + 2) + r7] & j);
                    }
                    r4 += 4;
                }
                return createPoint(create64, create642);
            }

            @Override // org.bouncycastle.math.p039ec.AbstractECLookupTable, org.bouncycastle.math.p039ec.ECLookupTable
            public ECPoint lookupVar(int r82) {
                long[] create64 = Nat128.create64();
                long[] create642 = Nat128.create64();
                int r83 = r82 * 2 * 2;
                for (int r33 = 0; r33 < 2; r33++) {
                    long[] jArr2 = jArr;
                    create64[r33] = jArr2[r83 + r33];
                    create642[r33] = jArr2[r83 + 2 + r33];
                }
                return createPoint(create64, create642);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.bouncycastle.math.p039ec.ECCurve
    public ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        return new SecT113R1Point(this, eCFieldElement, eCFieldElement2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.bouncycastle.math.p039ec.ECCurve
    public ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr) {
        return new SecT113R1Point(this, eCFieldElement, eCFieldElement2, eCFieldElementArr);
    }

    @Override // org.bouncycastle.math.p039ec.ECCurve
    public ECFieldElement fromBigInteger(BigInteger bigInteger) {
        return new SecT113FieldElement(bigInteger);
    }

    @Override // org.bouncycastle.math.p039ec.ECCurve
    public int getFieldSize() {
        return 113;
    }

    @Override // org.bouncycastle.math.p039ec.ECCurve
    public ECPoint getInfinity() {
        return this.infinity;
    }

    public int getK1() {
        return 9;
    }

    public int getK2() {
        return 0;
    }

    public int getK3() {
        return 0;
    }

    public int getM() {
        return 113;
    }

    @Override // org.bouncycastle.math.p039ec.ECCurve.AbstractF2m
    public boolean isKoblitz() {
        return false;
    }

    public boolean isTrinomial() {
        return true;
    }

    @Override // org.bouncycastle.math.p039ec.ECCurve
    public boolean supportsCoordinateSystem(int r2) {
        return r2 == 6;
    }
}
