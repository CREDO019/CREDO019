package org.bouncycastle.math.p039ec.custom.sec;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.math.p039ec.AbstractECLookupTable;
import org.bouncycastle.math.p039ec.ECConstants;
import org.bouncycastle.math.p039ec.ECCurve;
import org.bouncycastle.math.p039ec.ECFieldElement;
import org.bouncycastle.math.p039ec.ECLookupTable;
import org.bouncycastle.math.p039ec.ECPoint;
import org.bouncycastle.math.raw.Nat256;
import org.bouncycastle.util.encoders.Hex;

/* renamed from: org.bouncycastle.math.ec.custom.sec.SecP256K1Curve */
/* loaded from: classes5.dex */
public class SecP256K1Curve extends ECCurve.AbstractFp {
    private static final int SECP256K1_DEFAULT_COORDS = 2;
    protected SecP256K1Point infinity;

    /* renamed from: q */
    public static final BigInteger f2322q = SecP256K1FieldElement.f2325Q;
    private static final ECFieldElement[] SECP256K1_AFFINE_ZS = {new SecP256K1FieldElement(ECConstants.ONE)};

    public SecP256K1Curve() {
        super(f2322q);
        this.infinity = new SecP256K1Point(this, null, null);
        this.f2252a = fromBigInteger(ECConstants.ZERO);
        this.f2253b = fromBigInteger(BigInteger.valueOf(7L));
        this.order = new BigInteger(1, Hex.decodeStrict("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEBAAEDCE6AF48A03BBFD25E8CD0364141"));
        this.cofactor = BigInteger.valueOf(1L);
        this.coord = 2;
    }

    @Override // org.bouncycastle.math.p039ec.ECCurve
    protected ECCurve cloneCurve() {
        return new SecP256K1Curve();
    }

    @Override // org.bouncycastle.math.p039ec.ECCurve
    public ECLookupTable createCacheSafeLookupTable(ECPoint[] eCPointArr, int r8, final int r9) {
        final int[] r0 = new int[r9 * 8 * 2];
        int r3 = 0;
        for (int r2 = 0; r2 < r9; r2++) {
            ECPoint eCPoint = eCPointArr[r8 + r2];
            Nat256.copy(((SecP256K1FieldElement) eCPoint.getRawXCoord()).f2326x, 0, r0, r3);
            int r32 = r3 + 8;
            Nat256.copy(((SecP256K1FieldElement) eCPoint.getRawYCoord()).f2326x, 0, r0, r32);
            r3 = r32 + 8;
        }
        return new AbstractECLookupTable() { // from class: org.bouncycastle.math.ec.custom.sec.SecP256K1Curve.1
            private ECPoint createPoint(int[] r33, int[] r4) {
                return SecP256K1Curve.this.createRawPoint(new SecP256K1FieldElement(r33), new SecP256K1FieldElement(r4), SecP256K1Curve.SECP256K1_AFFINE_ZS);
            }

            @Override // org.bouncycastle.math.p039ec.ECLookupTable
            public int getSize() {
                return r9;
            }

            @Override // org.bouncycastle.math.p039ec.ECLookupTable
            public ECPoint lookup(int r11) {
                int[] create = Nat256.create();
                int[] create2 = Nat256.create();
                int r4 = 0;
                for (int r33 = 0; r33 < r9; r33++) {
                    int r5 = ((r33 ^ r11) - 1) >> 31;
                    for (int r6 = 0; r6 < 8; r6++) {
                        int r7 = create[r6];
                        int[] r82 = r0;
                        create[r6] = r7 ^ (r82[r4 + r6] & r5);
                        create2[r6] = create2[r6] ^ (r82[(r4 + 8) + r6] & r5);
                    }
                    r4 += 16;
                }
                return createPoint(create, create2);
            }

            @Override // org.bouncycastle.math.p039ec.AbstractECLookupTable, org.bouncycastle.math.p039ec.ECLookupTable
            public ECPoint lookupVar(int r7) {
                int[] create = Nat256.create();
                int[] create2 = Nat256.create();
                int r72 = r7 * 8 * 2;
                for (int r33 = 0; r33 < 8; r33++) {
                    int[] r4 = r0;
                    create[r33] = r4[r72 + r33];
                    create2[r33] = r4[r72 + 8 + r33];
                }
                return createPoint(create, create2);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.bouncycastle.math.p039ec.ECCurve
    public ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        return new SecP256K1Point(this, eCFieldElement, eCFieldElement2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.bouncycastle.math.p039ec.ECCurve
    public ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr) {
        return new SecP256K1Point(this, eCFieldElement, eCFieldElement2, eCFieldElementArr);
    }

    @Override // org.bouncycastle.math.p039ec.ECCurve
    public ECFieldElement fromBigInteger(BigInteger bigInteger) {
        return new SecP256K1FieldElement(bigInteger);
    }

    @Override // org.bouncycastle.math.p039ec.ECCurve
    public int getFieldSize() {
        return f2322q.bitLength();
    }

    @Override // org.bouncycastle.math.p039ec.ECCurve
    public ECPoint getInfinity() {
        return this.infinity;
    }

    public BigInteger getQ() {
        return f2322q;
    }

    @Override // org.bouncycastle.math.p039ec.ECCurve.AbstractFp, org.bouncycastle.math.p039ec.ECCurve
    public ECFieldElement randomFieldElement(SecureRandom secureRandom) {
        int[] create = Nat256.create();
        SecP256K1Field.random(secureRandom, create);
        return new SecP256K1FieldElement(create);
    }

    @Override // org.bouncycastle.math.p039ec.ECCurve.AbstractFp, org.bouncycastle.math.p039ec.ECCurve
    public ECFieldElement randomFieldElementMult(SecureRandom secureRandom) {
        int[] create = Nat256.create();
        SecP256K1Field.randomMult(secureRandom, create);
        return new SecP256K1FieldElement(create);
    }

    @Override // org.bouncycastle.math.p039ec.ECCurve
    public boolean supportsCoordinateSystem(int r2) {
        return r2 == 2;
    }
}
