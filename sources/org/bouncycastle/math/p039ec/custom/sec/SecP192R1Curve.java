package org.bouncycastle.math.p039ec.custom.sec;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.math.p039ec.AbstractECLookupTable;
import org.bouncycastle.math.p039ec.ECConstants;
import org.bouncycastle.math.p039ec.ECCurve;
import org.bouncycastle.math.p039ec.ECFieldElement;
import org.bouncycastle.math.p039ec.ECLookupTable;
import org.bouncycastle.math.p039ec.ECPoint;
import org.bouncycastle.math.raw.Nat192;
import org.bouncycastle.util.encoders.Hex;

/* renamed from: org.bouncycastle.math.ec.custom.sec.SecP192R1Curve */
/* loaded from: classes5.dex */
public class SecP192R1Curve extends ECCurve.AbstractFp {
    private static final int SECP192R1_DEFAULT_COORDS = 2;
    protected SecP192R1Point infinity;

    /* renamed from: q */
    public static final BigInteger f2305q = SecP192R1FieldElement.f2309Q;
    private static final ECFieldElement[] SECP192R1_AFFINE_ZS = {new SecP192R1FieldElement(ECConstants.ONE)};

    public SecP192R1Curve() {
        super(f2305q);
        this.infinity = new SecP192R1Point(this, null, null);
        this.f2252a = fromBigInteger(new BigInteger(1, Hex.decodeStrict("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEFFFFFFFFFFFFFFFC")));
        this.f2253b = fromBigInteger(new BigInteger(1, Hex.decodeStrict("64210519E59C80E70FA7E9AB72243049FEB8DEECC146B9B1")));
        this.order = new BigInteger(1, Hex.decodeStrict("FFFFFFFFFFFFFFFFFFFFFFFF99DEF836146BC9B1B4D22831"));
        this.cofactor = BigInteger.valueOf(1L);
        this.coord = 2;
    }

    @Override // org.bouncycastle.math.p039ec.ECCurve
    protected ECCurve cloneCurve() {
        return new SecP192R1Curve();
    }

    @Override // org.bouncycastle.math.p039ec.ECCurve
    public ECLookupTable createCacheSafeLookupTable(ECPoint[] eCPointArr, int r8, final int r9) {
        final int[] r0 = new int[r9 * 6 * 2];
        int r3 = 0;
        for (int r2 = 0; r2 < r9; r2++) {
            ECPoint eCPoint = eCPointArr[r8 + r2];
            Nat192.copy(((SecP192R1FieldElement) eCPoint.getRawXCoord()).f2310x, 0, r0, r3);
            int r32 = r3 + 6;
            Nat192.copy(((SecP192R1FieldElement) eCPoint.getRawYCoord()).f2310x, 0, r0, r32);
            r3 = r32 + 6;
        }
        return new AbstractECLookupTable() { // from class: org.bouncycastle.math.ec.custom.sec.SecP192R1Curve.1
            private ECPoint createPoint(int[] r33, int[] r4) {
                return SecP192R1Curve.this.createRawPoint(new SecP192R1FieldElement(r33), new SecP192R1FieldElement(r4), SecP192R1Curve.SECP192R1_AFFINE_ZS);
            }

            @Override // org.bouncycastle.math.p039ec.ECLookupTable
            public int getSize() {
                return r9;
            }

            @Override // org.bouncycastle.math.p039ec.ECLookupTable
            public ECPoint lookup(int r11) {
                int[] create = Nat192.create();
                int[] create2 = Nat192.create();
                int r4 = 0;
                for (int r33 = 0; r33 < r9; r33++) {
                    int r5 = ((r33 ^ r11) - 1) >> 31;
                    for (int r6 = 0; r6 < 6; r6++) {
                        int r7 = create[r6];
                        int[] r82 = r0;
                        create[r6] = r7 ^ (r82[r4 + r6] & r5);
                        create2[r6] = create2[r6] ^ (r82[(r4 + 6) + r6] & r5);
                    }
                    r4 += 12;
                }
                return createPoint(create, create2);
            }

            @Override // org.bouncycastle.math.p039ec.AbstractECLookupTable, org.bouncycastle.math.p039ec.ECLookupTable
            public ECPoint lookupVar(int r7) {
                int[] create = Nat192.create();
                int[] create2 = Nat192.create();
                int r72 = r7 * 6 * 2;
                for (int r33 = 0; r33 < 6; r33++) {
                    int[] r4 = r0;
                    create[r33] = r4[r72 + r33];
                    create2[r33] = r4[r72 + 6 + r33];
                }
                return createPoint(create, create2);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.bouncycastle.math.p039ec.ECCurve
    public ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        return new SecP192R1Point(this, eCFieldElement, eCFieldElement2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.bouncycastle.math.p039ec.ECCurve
    public ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr) {
        return new SecP192R1Point(this, eCFieldElement, eCFieldElement2, eCFieldElementArr);
    }

    @Override // org.bouncycastle.math.p039ec.ECCurve
    public ECFieldElement fromBigInteger(BigInteger bigInteger) {
        return new SecP192R1FieldElement(bigInteger);
    }

    @Override // org.bouncycastle.math.p039ec.ECCurve
    public int getFieldSize() {
        return f2305q.bitLength();
    }

    @Override // org.bouncycastle.math.p039ec.ECCurve
    public ECPoint getInfinity() {
        return this.infinity;
    }

    public BigInteger getQ() {
        return f2305q;
    }

    @Override // org.bouncycastle.math.p039ec.ECCurve.AbstractFp, org.bouncycastle.math.p039ec.ECCurve
    public ECFieldElement randomFieldElement(SecureRandom secureRandom) {
        int[] create = Nat192.create();
        SecP192R1Field.random(secureRandom, create);
        return new SecP192R1FieldElement(create);
    }

    @Override // org.bouncycastle.math.p039ec.ECCurve.AbstractFp, org.bouncycastle.math.p039ec.ECCurve
    public ECFieldElement randomFieldElementMult(SecureRandom secureRandom) {
        int[] create = Nat192.create();
        SecP192R1Field.randomMult(secureRandom, create);
        return new SecP192R1FieldElement(create);
    }

    @Override // org.bouncycastle.math.p039ec.ECCurve
    public boolean supportsCoordinateSystem(int r2) {
        return r2 == 2;
    }
}
