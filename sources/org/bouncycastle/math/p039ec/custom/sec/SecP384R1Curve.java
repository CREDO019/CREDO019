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

/* renamed from: org.bouncycastle.math.ec.custom.sec.SecP384R1Curve */
/* loaded from: classes5.dex */
public class SecP384R1Curve extends ECCurve.AbstractFp {
    private static final int SECP384R1_DEFAULT_COORDS = 2;
    protected SecP384R1Point infinity;

    /* renamed from: q */
    public static final BigInteger f2333q = SecP384R1FieldElement.f2336Q;
    private static final ECFieldElement[] SECP384R1_AFFINE_ZS = {new SecP384R1FieldElement(ECConstants.ONE)};

    public SecP384R1Curve() {
        super(f2333q);
        this.infinity = new SecP384R1Point(this, null, null);
        this.f2252a = fromBigInteger(new BigInteger(1, Hex.decodeStrict("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEFFFFFFFF0000000000000000FFFFFFFC")));
        this.f2253b = fromBigInteger(new BigInteger(1, Hex.decodeStrict("B3312FA7E23EE7E4988E056BE3F82D19181D9C6EFE8141120314088F5013875AC656398D8A2ED19D2A85C8EDD3EC2AEF")));
        this.order = new BigInteger(1, Hex.decodeStrict("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFC7634D81F4372DDF581A0DB248B0A77AECEC196ACCC52973"));
        this.cofactor = BigInteger.valueOf(1L);
        this.coord = 2;
    }

    @Override // org.bouncycastle.math.p039ec.ECCurve
    protected ECCurve cloneCurve() {
        return new SecP384R1Curve();
    }

    @Override // org.bouncycastle.math.p039ec.ECCurve
    public ECLookupTable createCacheSafeLookupTable(ECPoint[] eCPointArr, int r9, final int r10) {
        final int[] r0 = new int[r10 * 12 * 2];
        int r3 = 0;
        for (int r2 = 0; r2 < r10; r2++) {
            ECPoint eCPoint = eCPointArr[r9 + r2];
            Nat.copy(12, ((SecP384R1FieldElement) eCPoint.getRawXCoord()).f2337x, 0, r0, r3);
            int r32 = r3 + 12;
            Nat.copy(12, ((SecP384R1FieldElement) eCPoint.getRawYCoord()).f2337x, 0, r0, r32);
            r3 = r32 + 12;
        }
        return new AbstractECLookupTable() { // from class: org.bouncycastle.math.ec.custom.sec.SecP384R1Curve.1
            private ECPoint createPoint(int[] r33, int[] r4) {
                return SecP384R1Curve.this.createRawPoint(new SecP384R1FieldElement(r33), new SecP384R1FieldElement(r4), SecP384R1Curve.SECP384R1_AFFINE_ZS);
            }

            @Override // org.bouncycastle.math.p039ec.ECLookupTable
            public int getSize() {
                return r10;
            }

            @Override // org.bouncycastle.math.p039ec.ECLookupTable
            public ECPoint lookup(int r12) {
                int[] create = Nat.create(12);
                int[] create2 = Nat.create(12);
                int r5 = 0;
                for (int r4 = 0; r4 < r10; r4++) {
                    int r6 = ((r4 ^ r12) - 1) >> 31;
                    for (int r7 = 0; r7 < 12; r7++) {
                        int r8 = create[r7];
                        int[] r92 = r0;
                        create[r7] = r8 ^ (r92[r5 + r7] & r6);
                        create2[r7] = create2[r7] ^ (r92[(r5 + 12) + r7] & r6);
                    }
                    r5 += 24;
                }
                return createPoint(create, create2);
            }

            @Override // org.bouncycastle.math.p039ec.AbstractECLookupTable, org.bouncycastle.math.p039ec.ECLookupTable
            public ECPoint lookupVar(int r7) {
                int[] create = Nat.create(12);
                int[] create2 = Nat.create(12);
                int r72 = r7 * 12 * 2;
                for (int r33 = 0; r33 < 12; r33++) {
                    int[] r4 = r0;
                    create[r33] = r4[r72 + r33];
                    create2[r33] = r4[r72 + 12 + r33];
                }
                return createPoint(create, create2);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.bouncycastle.math.p039ec.ECCurve
    public ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        return new SecP384R1Point(this, eCFieldElement, eCFieldElement2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.bouncycastle.math.p039ec.ECCurve
    public ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr) {
        return new SecP384R1Point(this, eCFieldElement, eCFieldElement2, eCFieldElementArr);
    }

    @Override // org.bouncycastle.math.p039ec.ECCurve
    public ECFieldElement fromBigInteger(BigInteger bigInteger) {
        return new SecP384R1FieldElement(bigInteger);
    }

    @Override // org.bouncycastle.math.p039ec.ECCurve
    public int getFieldSize() {
        return f2333q.bitLength();
    }

    @Override // org.bouncycastle.math.p039ec.ECCurve
    public ECPoint getInfinity() {
        return this.infinity;
    }

    public BigInteger getQ() {
        return f2333q;
    }

    @Override // org.bouncycastle.math.p039ec.ECCurve.AbstractFp, org.bouncycastle.math.p039ec.ECCurve
    public ECFieldElement randomFieldElement(SecureRandom secureRandom) {
        int[] create = Nat.create(12);
        SecP384R1Field.random(secureRandom, create);
        return new SecP384R1FieldElement(create);
    }

    @Override // org.bouncycastle.math.p039ec.ECCurve.AbstractFp, org.bouncycastle.math.p039ec.ECCurve
    public ECFieldElement randomFieldElementMult(SecureRandom secureRandom) {
        int[] create = Nat.create(12);
        SecP384R1Field.randomMult(secureRandom, create);
        return new SecP384R1FieldElement(create);
    }

    @Override // org.bouncycastle.math.p039ec.ECCurve
    public boolean supportsCoordinateSystem(int r2) {
        return r2 == 2;
    }
}
