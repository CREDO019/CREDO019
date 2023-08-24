package org.bouncycastle.math.p039ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.math.p039ec.AbstractECLookupTable;
import org.bouncycastle.math.p039ec.ECConstants;
import org.bouncycastle.math.p039ec.ECCurve;
import org.bouncycastle.math.p039ec.ECFieldElement;
import org.bouncycastle.math.p039ec.ECLookupTable;
import org.bouncycastle.math.p039ec.ECMultiplier;
import org.bouncycastle.math.p039ec.ECPoint;
import org.bouncycastle.math.p039ec.WTauNafMultiplier;
import org.bouncycastle.math.raw.Nat576;
import org.bouncycastle.util.encoders.Hex;

/* renamed from: org.bouncycastle.math.ec.custom.sec.SecT571K1Curve */
/* loaded from: classes5.dex */
public class SecT571K1Curve extends ECCurve.AbstractF2m {
    private static final ECFieldElement[] SECT571K1_AFFINE_ZS = {new SecT571FieldElement(ECConstants.ONE)};
    private static final int SECT571K1_DEFAULT_COORDS = 6;
    protected SecT571K1Point infinity;

    public SecT571K1Curve() {
        super(571, 2, 5, 10);
        this.infinity = new SecT571K1Point(this, null, null);
        this.f2252a = fromBigInteger(BigInteger.valueOf(0L));
        this.f2253b = fromBigInteger(BigInteger.valueOf(1L));
        this.order = new BigInteger(1, Hex.decodeStrict("020000000000000000000000000000000000000000000000000000000000000000000000131850E1F19A63E4B391A8DB917F4138B630D84BE5D639381E91DEB45CFE778F637C1001"));
        this.cofactor = BigInteger.valueOf(4L);
        this.coord = 6;
    }

    @Override // org.bouncycastle.math.p039ec.ECCurve
    protected ECCurve cloneCurve() {
        return new SecT571K1Curve();
    }

    @Override // org.bouncycastle.math.p039ec.ECCurve
    public ECLookupTable createCacheSafeLookupTable(ECPoint[] eCPointArr, int r8, final int r9) {
        final long[] jArr = new long[r9 * 9 * 2];
        int r3 = 0;
        for (int r2 = 0; r2 < r9; r2++) {
            ECPoint eCPoint = eCPointArr[r8 + r2];
            Nat576.copy64(((SecT571FieldElement) eCPoint.getRawXCoord()).f2350x, 0, jArr, r3);
            int r32 = r3 + 9;
            Nat576.copy64(((SecT571FieldElement) eCPoint.getRawYCoord()).f2350x, 0, jArr, r32);
            r3 = r32 + 9;
        }
        return new AbstractECLookupTable() { // from class: org.bouncycastle.math.ec.custom.sec.SecT571K1Curve.1
            private ECPoint createPoint(long[] jArr2, long[] jArr3) {
                return SecT571K1Curve.this.createRawPoint(new SecT571FieldElement(jArr2), new SecT571FieldElement(jArr3), SecT571K1Curve.SECT571K1_AFFINE_ZS);
            }

            @Override // org.bouncycastle.math.p039ec.ECLookupTable
            public int getSize() {
                return r9;
            }

            @Override // org.bouncycastle.math.p039ec.ECLookupTable
            public ECPoint lookup(int r14) {
                long[] create64 = Nat576.create64();
                long[] create642 = Nat576.create64();
                int r4 = 0;
                for (int r33 = 0; r33 < r9; r33++) {
                    long j = ((r33 ^ r14) - 1) >> 31;
                    for (int r7 = 0; r7 < 9; r7++) {
                        long j2 = create64[r7];
                        long[] jArr2 = jArr;
                        create64[r7] = j2 ^ (jArr2[r4 + r7] & j);
                        create642[r7] = create642[r7] ^ (jArr2[(r4 + 9) + r7] & j);
                    }
                    r4 += 18;
                }
                return createPoint(create64, create642);
            }

            @Override // org.bouncycastle.math.p039ec.AbstractECLookupTable, org.bouncycastle.math.p039ec.ECLookupTable
            public ECPoint lookupVar(int r82) {
                long[] create64 = Nat576.create64();
                long[] create642 = Nat576.create64();
                int r83 = r82 * 9 * 2;
                for (int r33 = 0; r33 < 9; r33++) {
                    long[] jArr2 = jArr;
                    create64[r33] = jArr2[r83 + r33];
                    create642[r33] = jArr2[r83 + 9 + r33];
                }
                return createPoint(create64, create642);
            }
        };
    }

    @Override // org.bouncycastle.math.p039ec.ECCurve
    protected ECMultiplier createDefaultMultiplier() {
        return new WTauNafMultiplier();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.bouncycastle.math.p039ec.ECCurve
    public ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        return new SecT571K1Point(this, eCFieldElement, eCFieldElement2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.bouncycastle.math.p039ec.ECCurve
    public ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr) {
        return new SecT571K1Point(this, eCFieldElement, eCFieldElement2, eCFieldElementArr);
    }

    @Override // org.bouncycastle.math.p039ec.ECCurve
    public ECFieldElement fromBigInteger(BigInteger bigInteger) {
        return new SecT571FieldElement(bigInteger);
    }

    @Override // org.bouncycastle.math.p039ec.ECCurve
    public int getFieldSize() {
        return 571;
    }

    @Override // org.bouncycastle.math.p039ec.ECCurve
    public ECPoint getInfinity() {
        return this.infinity;
    }

    public int getK1() {
        return 2;
    }

    public int getK2() {
        return 5;
    }

    public int getK3() {
        return 10;
    }

    public int getM() {
        return 571;
    }

    @Override // org.bouncycastle.math.p039ec.ECCurve.AbstractF2m
    public boolean isKoblitz() {
        return true;
    }

    public boolean isTrinomial() {
        return false;
    }

    @Override // org.bouncycastle.math.p039ec.ECCurve
    public boolean supportsCoordinateSystem(int r2) {
        return r2 == 6;
    }
}
