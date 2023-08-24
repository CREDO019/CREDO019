package org.bouncycastle.math.p039ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.math.p039ec.AbstractECLookupTable;
import org.bouncycastle.math.p039ec.ECConstants;
import org.bouncycastle.math.p039ec.ECCurve;
import org.bouncycastle.math.p039ec.ECFieldElement;
import org.bouncycastle.math.p039ec.ECLookupTable;
import org.bouncycastle.math.p039ec.ECPoint;
import org.bouncycastle.math.raw.Nat192;
import org.bouncycastle.util.encoders.Hex;

/* renamed from: org.bouncycastle.math.ec.custom.sec.SecT131R1Curve */
/* loaded from: classes5.dex */
public class SecT131R1Curve extends ECCurve.AbstractF2m {
    private static final ECFieldElement[] SECT131R1_AFFINE_ZS = {new SecT131FieldElement(ECConstants.ONE)};
    private static final int SECT131R1_DEFAULT_COORDS = 6;
    protected SecT131R1Point infinity;

    public SecT131R1Curve() {
        super(131, 2, 3, 8);
        this.infinity = new SecT131R1Point(this, null, null);
        this.f2252a = fromBigInteger(new BigInteger(1, Hex.decodeStrict("07A11B09A76B562144418FF3FF8C2570B8")));
        this.f2253b = fromBigInteger(new BigInteger(1, Hex.decodeStrict("0217C05610884B63B9C6C7291678F9D341")));
        this.order = new BigInteger(1, Hex.decodeStrict("0400000000000000023123953A9464B54D"));
        this.cofactor = BigInteger.valueOf(2L);
        this.coord = 6;
    }

    @Override // org.bouncycastle.math.p039ec.ECCurve
    protected ECCurve cloneCurve() {
        return new SecT131R1Curve();
    }

    @Override // org.bouncycastle.math.p039ec.ECCurve
    public ECLookupTable createCacheSafeLookupTable(ECPoint[] eCPointArr, int r8, final int r9) {
        final long[] jArr = new long[r9 * 3 * 2];
        int r3 = 0;
        for (int r2 = 0; r2 < r9; r2++) {
            ECPoint eCPoint = eCPointArr[r8 + r2];
            Nat192.copy64(((SecT131FieldElement) eCPoint.getRawXCoord()).f2343x, 0, jArr, r3);
            int r32 = r3 + 3;
            Nat192.copy64(((SecT131FieldElement) eCPoint.getRawYCoord()).f2343x, 0, jArr, r32);
            r3 = r32 + 3;
        }
        return new AbstractECLookupTable() { // from class: org.bouncycastle.math.ec.custom.sec.SecT131R1Curve.1
            private ECPoint createPoint(long[] jArr2, long[] jArr3) {
                return SecT131R1Curve.this.createRawPoint(new SecT131FieldElement(jArr2), new SecT131FieldElement(jArr3), SecT131R1Curve.SECT131R1_AFFINE_ZS);
            }

            @Override // org.bouncycastle.math.p039ec.ECLookupTable
            public int getSize() {
                return r9;
            }

            @Override // org.bouncycastle.math.p039ec.ECLookupTable
            public ECPoint lookup(int r14) {
                long[] create64 = Nat192.create64();
                long[] create642 = Nat192.create64();
                int r4 = 0;
                for (int r33 = 0; r33 < r9; r33++) {
                    long j = ((r33 ^ r14) - 1) >> 31;
                    for (int r7 = 0; r7 < 3; r7++) {
                        long j2 = create64[r7];
                        long[] jArr2 = jArr;
                        create64[r7] = j2 ^ (jArr2[r4 + r7] & j);
                        create642[r7] = create642[r7] ^ (jArr2[(r4 + 3) + r7] & j);
                    }
                    r4 += 6;
                }
                return createPoint(create64, create642);
            }

            @Override // org.bouncycastle.math.p039ec.AbstractECLookupTable, org.bouncycastle.math.p039ec.ECLookupTable
            public ECPoint lookupVar(int r82) {
                long[] create64 = Nat192.create64();
                long[] create642 = Nat192.create64();
                int r83 = r82 * 3 * 2;
                for (int r33 = 0; r33 < 3; r33++) {
                    long[] jArr2 = jArr;
                    create64[r33] = jArr2[r83 + r33];
                    create642[r33] = jArr2[r83 + 3 + r33];
                }
                return createPoint(create64, create642);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.bouncycastle.math.p039ec.ECCurve
    public ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        return new SecT131R1Point(this, eCFieldElement, eCFieldElement2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.bouncycastle.math.p039ec.ECCurve
    public ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr) {
        return new SecT131R1Point(this, eCFieldElement, eCFieldElement2, eCFieldElementArr);
    }

    @Override // org.bouncycastle.math.p039ec.ECCurve
    public ECFieldElement fromBigInteger(BigInteger bigInteger) {
        return new SecT131FieldElement(bigInteger);
    }

    @Override // org.bouncycastle.math.p039ec.ECCurve
    public int getFieldSize() {
        return 131;
    }

    @Override // org.bouncycastle.math.p039ec.ECCurve
    public ECPoint getInfinity() {
        return this.infinity;
    }

    public int getK1() {
        return 2;
    }

    public int getK2() {
        return 3;
    }

    public int getK3() {
        return 8;
    }

    public int getM() {
        return 131;
    }

    @Override // org.bouncycastle.math.p039ec.ECCurve.AbstractF2m
    public boolean isKoblitz() {
        return false;
    }

    public boolean isTrinomial() {
        return false;
    }

    @Override // org.bouncycastle.math.p039ec.ECCurve
    public boolean supportsCoordinateSystem(int r2) {
        return r2 == 6;
    }
}
