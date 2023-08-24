package org.bouncycastle.math.p039ec.custom.sec;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.math.p039ec.AbstractECLookupTable;
import org.bouncycastle.math.p039ec.ECConstants;
import org.bouncycastle.math.p039ec.ECCurve;
import org.bouncycastle.math.p039ec.ECFieldElement;
import org.bouncycastle.math.p039ec.ECLookupTable;
import org.bouncycastle.math.p039ec.ECPoint;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.util.encoders.Hex;

/* renamed from: org.bouncycastle.math.ec.custom.sec.SecP521R1Curve */
/* loaded from: classes5.dex */
public class SecP521R1Curve extends ECCurve.AbstractFp {
    private static final int SECP521R1_DEFAULT_COORDS = 2;
    protected SecP521R1Point infinity;

    /* renamed from: q */
    public static final BigInteger f2338q = SecP521R1FieldElement.f2340Q;
    private static final ECFieldElement[] SECP521R1_AFFINE_ZS = {new SecP521R1FieldElement(ECConstants.ONE)};

    public SecP521R1Curve() {
        super(f2338q);
        this.infinity = new SecP521R1Point(this, null, null);
        this.f2252a = fromBigInteger(new BigInteger(1, Hex.decodeStrict("01FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFC")));
        this.f2253b = fromBigInteger(new BigInteger(1, Hex.decodeStrict("0051953EB9618E1C9A1F929A21A0B68540EEA2DA725B99B315F3B8B489918EF109E156193951EC7E937B1652C0BD3BB1BF073573DF883D2C34F1EF451FD46B503F00")));
        this.order = new BigInteger(1, Hex.decodeStrict("01FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFA51868783BF2F966B7FCC0148F709A5D03BB5C9B8899C47AEBB6FB71E91386409"));
        this.cofactor = BigInteger.valueOf(1L);
        this.coord = 2;
    }

    @Override // org.bouncycastle.math.p039ec.ECCurve
    protected ECCurve cloneCurve() {
        return new SecP521R1Curve();
    }

    @Override // org.bouncycastle.math.p039ec.ECCurve
    public ECLookupTable createCacheSafeLookupTable(ECPoint[] eCPointArr, int r9, final int r10) {
        final int[] r0 = new int[r10 * 17 * 2];
        int r3 = 0;
        for (int r2 = 0; r2 < r10; r2++) {
            ECPoint eCPoint = eCPointArr[r9 + r2];
            Nat.copy(17, ((SecP521R1FieldElement) eCPoint.getRawXCoord()).f2341x, 0, r0, r3);
            int r32 = r3 + 17;
            Nat.copy(17, ((SecP521R1FieldElement) eCPoint.getRawYCoord()).f2341x, 0, r0, r32);
            r3 = r32 + 17;
        }
        return new AbstractECLookupTable() { // from class: org.bouncycastle.math.ec.custom.sec.SecP521R1Curve.1
            private ECPoint createPoint(int[] r33, int[] r4) {
                return SecP521R1Curve.this.createRawPoint(new SecP521R1FieldElement(r33), new SecP521R1FieldElement(r4), SecP521R1Curve.SECP521R1_AFFINE_ZS);
            }

            @Override // org.bouncycastle.math.p039ec.ECLookupTable
            public int getSize() {
                return r10;
            }

            @Override // org.bouncycastle.math.p039ec.ECLookupTable
            public ECPoint lookup(int r12) {
                int[] create = Nat.create(17);
                int[] create2 = Nat.create(17);
                int r5 = 0;
                for (int r4 = 0; r4 < r10; r4++) {
                    int r6 = ((r4 ^ r12) - 1) >> 31;
                    for (int r7 = 0; r7 < 17; r7++) {
                        int r8 = create[r7];
                        int[] r92 = r0;
                        create[r7] = r8 ^ (r92[r5 + r7] & r6);
                        create2[r7] = create2[r7] ^ (r92[(r5 + 17) + r7] & r6);
                    }
                    r5 += 34;
                }
                return createPoint(create, create2);
            }

            @Override // org.bouncycastle.math.p039ec.AbstractECLookupTable, org.bouncycastle.math.p039ec.ECLookupTable
            public ECPoint lookupVar(int r8) {
                int[] create = Nat.create(17);
                int[] create2 = Nat.create(17);
                int r82 = r8 * 17 * 2;
                for (int r33 = 0; r33 < 17; r33++) {
                    int r4 = create[r33];
                    int[] r5 = r0;
                    create[r33] = r4 ^ r5[r82 + r33];
                    create2[r33] = create2[r33] ^ r5[(r82 + 17) + r33];
                }
                return createPoint(create, create2);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.bouncycastle.math.p039ec.ECCurve
    public ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        return new SecP521R1Point(this, eCFieldElement, eCFieldElement2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.bouncycastle.math.p039ec.ECCurve
    public ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr) {
        return new SecP521R1Point(this, eCFieldElement, eCFieldElement2, eCFieldElementArr);
    }

    @Override // org.bouncycastle.math.p039ec.ECCurve
    public ECFieldElement fromBigInteger(BigInteger bigInteger) {
        return new SecP521R1FieldElement(bigInteger);
    }

    @Override // org.bouncycastle.math.p039ec.ECCurve
    public int getFieldSize() {
        return f2338q.bitLength();
    }

    @Override // org.bouncycastle.math.p039ec.ECCurve
    public ECPoint getInfinity() {
        return this.infinity;
    }

    public BigInteger getQ() {
        return f2338q;
    }

    @Override // org.bouncycastle.math.p039ec.ECCurve.AbstractFp, org.bouncycastle.math.p039ec.ECCurve
    public ECFieldElement randomFieldElement(SecureRandom secureRandom) {
        int[] create = Nat.create(17);
        SecP521R1Field.random(secureRandom, create);
        return new SecP521R1FieldElement(create);
    }

    @Override // org.bouncycastle.math.p039ec.ECCurve.AbstractFp, org.bouncycastle.math.p039ec.ECCurve
    public ECFieldElement randomFieldElementMult(SecureRandom secureRandom) {
        int[] create = Nat.create(17);
        SecP521R1Field.randomMult(secureRandom, create);
        return new SecP521R1FieldElement(create);
    }

    @Override // org.bouncycastle.math.p039ec.ECCurve
    public boolean supportsCoordinateSystem(int r2) {
        return r2 == 2;
    }
}
