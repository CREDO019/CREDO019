package org.bouncycastle.math.p039ec;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Hashtable;
import java.util.Random;
import org.bouncycastle.math.field.FiniteField;
import org.bouncycastle.math.field.FiniteFields;
import org.bouncycastle.math.p039ec.ECFieldElement;
import org.bouncycastle.math.p039ec.ECPoint;
import org.bouncycastle.math.p039ec.endo.ECEndomorphism;
import org.bouncycastle.math.p039ec.endo.GLVEndomorphism;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.util.BigIntegers;
import org.bouncycastle.util.Integers;

/* renamed from: org.bouncycastle.math.ec.ECCurve */
/* loaded from: classes5.dex */
public abstract class ECCurve {
    public static final int COORD_AFFINE = 0;
    public static final int COORD_HOMOGENEOUS = 1;
    public static final int COORD_JACOBIAN = 2;
    public static final int COORD_JACOBIAN_CHUDNOVSKY = 3;
    public static final int COORD_JACOBIAN_MODIFIED = 4;
    public static final int COORD_LAMBDA_AFFINE = 5;
    public static final int COORD_LAMBDA_PROJECTIVE = 6;
    public static final int COORD_SKEWED = 7;

    /* renamed from: a */
    protected ECFieldElement f2252a;

    /* renamed from: b */
    protected ECFieldElement f2253b;
    protected BigInteger cofactor;
    protected FiniteField field;
    protected BigInteger order;
    protected int coord = 0;
    protected ECEndomorphism endomorphism = null;
    protected ECMultiplier multiplier = null;

    /* renamed from: org.bouncycastle.math.ec.ECCurve$AbstractF2m */
    /* loaded from: classes5.dex */
    public static abstract class AbstractF2m extends ECCurve {

        /* renamed from: si */
        private BigInteger[] f2254si;

        /* JADX INFO: Access modifiers changed from: protected */
        public AbstractF2m(int r1, int r2, int r3, int r4) {
            super(buildField(r1, r2, r3, r4));
            this.f2254si = null;
        }

        private static FiniteField buildField(int r5, int r6, int r7, int r8) {
            if (r6 != 0) {
                if (r7 == 0) {
                    if (r8 == 0) {
                        return FiniteFields.getBinaryExtensionField(new int[]{0, r6, r5});
                    }
                    throw new IllegalArgumentException("k3 must be 0 if k2 == 0");
                } else if (r7 > r6) {
                    if (r8 > r7) {
                        return FiniteFields.getBinaryExtensionField(new int[]{0, r6, r7, r8, r5});
                    }
                    throw new IllegalArgumentException("k3 must be > k2");
                } else {
                    throw new IllegalArgumentException("k2 must be > k1");
                }
            }
            throw new IllegalArgumentException("k1 must be > 0");
        }

        private static BigInteger implRandomFieldElementMult(SecureRandom secureRandom, int r3) {
            BigInteger createRandomBigInteger;
            do {
                createRandomBigInteger = BigIntegers.createRandomBigInteger(r3, secureRandom);
            } while (createRandomBigInteger.signum() <= 0);
            return createRandomBigInteger;
        }

        public static BigInteger inverse(int r1, int[] r2, BigInteger bigInteger) {
            return new LongArray(bigInteger).modInverse(r1, r2).toBigInteger();
        }

        @Override // org.bouncycastle.math.p039ec.ECCurve
        public ECPoint createPoint(BigInteger bigInteger, BigInteger bigInteger2) {
            ECFieldElement fromBigInteger = fromBigInteger(bigInteger);
            ECFieldElement fromBigInteger2 = fromBigInteger(bigInteger2);
            int coordinateSystem = getCoordinateSystem();
            if (coordinateSystem == 5 || coordinateSystem == 6) {
                if (!fromBigInteger.isZero()) {
                    fromBigInteger2 = fromBigInteger2.divide(fromBigInteger).add(fromBigInteger);
                } else if (!fromBigInteger2.square().equals(getB())) {
                    throw new IllegalArgumentException();
                }
            }
            return createRawPoint(fromBigInteger, fromBigInteger2);
        }

        @Override // org.bouncycastle.math.p039ec.ECCurve
        protected ECPoint decompressPoint(int r4, BigInteger bigInteger) {
            ECFieldElement eCFieldElement;
            ECFieldElement fromBigInteger = fromBigInteger(bigInteger);
            if (fromBigInteger.isZero()) {
                eCFieldElement = getB().sqrt();
            } else {
                ECFieldElement solveQuadraticEquation = solveQuadraticEquation(fromBigInteger.square().invert().multiply(getB()).add(getA()).add(fromBigInteger));
                if (solveQuadraticEquation != null) {
                    if (solveQuadraticEquation.testBitZero() != (r4 == 1)) {
                        solveQuadraticEquation = solveQuadraticEquation.addOne();
                    }
                    int coordinateSystem = getCoordinateSystem();
                    eCFieldElement = (coordinateSystem == 5 || coordinateSystem == 6) ? solveQuadraticEquation.add(fromBigInteger) : solveQuadraticEquation.multiply(fromBigInteger);
                } else {
                    eCFieldElement = null;
                }
            }
            if (eCFieldElement != null) {
                return createRawPoint(fromBigInteger, eCFieldElement);
            }
            throw new IllegalArgumentException("Invalid point compression");
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public synchronized BigInteger[] getSi() {
            if (this.f2254si == null) {
                this.f2254si = Tnaf.getSi(this);
            }
            return this.f2254si;
        }

        public boolean isKoblitz() {
            return this.order != null && this.cofactor != null && this.f2253b.isOne() && (this.f2252a.isZero() || this.f2252a.isOne());
        }

        @Override // org.bouncycastle.math.p039ec.ECCurve
        public boolean isValidFieldElement(BigInteger bigInteger) {
            return bigInteger != null && bigInteger.signum() >= 0 && bigInteger.bitLength() <= getFieldSize();
        }

        @Override // org.bouncycastle.math.p039ec.ECCurve
        public ECFieldElement randomFieldElement(SecureRandom secureRandom) {
            return fromBigInteger(BigIntegers.createRandomBigInteger(getFieldSize(), secureRandom));
        }

        @Override // org.bouncycastle.math.p039ec.ECCurve
        public ECFieldElement randomFieldElementMult(SecureRandom secureRandom) {
            int fieldSize = getFieldSize();
            return fromBigInteger(implRandomFieldElementMult(secureRandom, fieldSize)).multiply(fromBigInteger(implRandomFieldElementMult(secureRandom, fieldSize)));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public ECFieldElement solveQuadraticEquation(ECFieldElement eCFieldElement) {
            ECFieldElement eCFieldElement2;
            ECFieldElement.AbstractF2m abstractF2m = (ECFieldElement.AbstractF2m) eCFieldElement;
            boolean hasFastTrace = abstractF2m.hasFastTrace();
            if (!hasFastTrace || abstractF2m.trace() == 0) {
                int fieldSize = getFieldSize();
                if ((fieldSize & 1) != 0) {
                    ECFieldElement halfTrace = abstractF2m.halfTrace();
                    if (hasFastTrace || halfTrace.square().add(halfTrace).add(eCFieldElement).isZero()) {
                        return halfTrace;
                    }
                    return null;
                } else if (eCFieldElement.isZero()) {
                    return eCFieldElement;
                } else {
                    ECFieldElement fromBigInteger = fromBigInteger(ECConstants.ZERO);
                    Random random = new Random();
                    do {
                        ECFieldElement fromBigInteger2 = fromBigInteger(new BigInteger(fieldSize, random));
                        ECFieldElement eCFieldElement3 = eCFieldElement;
                        eCFieldElement2 = fromBigInteger;
                        for (int r5 = 1; r5 < fieldSize; r5++) {
                            ECFieldElement square = eCFieldElement3.square();
                            eCFieldElement2 = eCFieldElement2.square().add(square.multiply(fromBigInteger2));
                            eCFieldElement3 = square.add(eCFieldElement);
                        }
                        if (!eCFieldElement3.isZero()) {
                            return null;
                        }
                    } while (eCFieldElement2.square().add(eCFieldElement2).isZero());
                    return eCFieldElement2;
                }
            }
            return null;
        }
    }

    /* renamed from: org.bouncycastle.math.ec.ECCurve$AbstractFp */
    /* loaded from: classes5.dex */
    public static abstract class AbstractFp extends ECCurve {
        /* JADX INFO: Access modifiers changed from: protected */
        public AbstractFp(BigInteger bigInteger) {
            super(FiniteFields.getPrimeField(bigInteger));
        }

        private static BigInteger implRandomFieldElement(SecureRandom secureRandom, BigInteger bigInteger) {
            BigInteger createRandomBigInteger;
            do {
                createRandomBigInteger = BigIntegers.createRandomBigInteger(bigInteger.bitLength(), secureRandom);
            } while (createRandomBigInteger.compareTo(bigInteger) >= 0);
            return createRandomBigInteger;
        }

        private static BigInteger implRandomFieldElementMult(SecureRandom secureRandom, BigInteger bigInteger) {
            while (true) {
                BigInteger createRandomBigInteger = BigIntegers.createRandomBigInteger(bigInteger.bitLength(), secureRandom);
                if (createRandomBigInteger.signum() > 0 && createRandomBigInteger.compareTo(bigInteger) < 0) {
                    return createRandomBigInteger;
                }
            }
        }

        @Override // org.bouncycastle.math.p039ec.ECCurve
        protected ECPoint decompressPoint(int r4, BigInteger bigInteger) {
            ECFieldElement fromBigInteger = fromBigInteger(bigInteger);
            ECFieldElement sqrt = fromBigInteger.square().add(this.f2252a).multiply(fromBigInteger).add(this.f2253b).sqrt();
            if (sqrt != null) {
                if (sqrt.testBitZero() != (r4 == 1)) {
                    sqrt = sqrt.negate();
                }
                return createRawPoint(fromBigInteger, sqrt);
            }
            throw new IllegalArgumentException("Invalid point compression");
        }

        @Override // org.bouncycastle.math.p039ec.ECCurve
        public boolean isValidFieldElement(BigInteger bigInteger) {
            return bigInteger != null && bigInteger.signum() >= 0 && bigInteger.compareTo(getField().getCharacteristic()) < 0;
        }

        @Override // org.bouncycastle.math.p039ec.ECCurve
        public ECFieldElement randomFieldElement(SecureRandom secureRandom) {
            BigInteger characteristic = getField().getCharacteristic();
            return fromBigInteger(implRandomFieldElement(secureRandom, characteristic)).multiply(fromBigInteger(implRandomFieldElement(secureRandom, characteristic)));
        }

        @Override // org.bouncycastle.math.p039ec.ECCurve
        public ECFieldElement randomFieldElementMult(SecureRandom secureRandom) {
            BigInteger characteristic = getField().getCharacteristic();
            return fromBigInteger(implRandomFieldElementMult(secureRandom, characteristic)).multiply(fromBigInteger(implRandomFieldElementMult(secureRandom, characteristic)));
        }
    }

    /* renamed from: org.bouncycastle.math.ec.ECCurve$Config */
    /* loaded from: classes5.dex */
    public class Config {
        protected int coord;
        protected ECEndomorphism endomorphism;
        protected ECMultiplier multiplier;

        Config(int r2, ECEndomorphism eCEndomorphism, ECMultiplier eCMultiplier) {
            this.coord = r2;
            this.endomorphism = eCEndomorphism;
            this.multiplier = eCMultiplier;
        }

        public ECCurve create() {
            if (ECCurve.this.supportsCoordinateSystem(this.coord)) {
                ECCurve cloneCurve = ECCurve.this.cloneCurve();
                if (cloneCurve != ECCurve.this) {
                    synchronized (cloneCurve) {
                        cloneCurve.coord = this.coord;
                        cloneCurve.endomorphism = this.endomorphism;
                        cloneCurve.multiplier = this.multiplier;
                    }
                    return cloneCurve;
                }
                throw new IllegalStateException("implementation returned current curve");
            }
            throw new IllegalStateException("unsupported coordinate system");
        }

        public Config setCoordinateSystem(int r1) {
            this.coord = r1;
            return this;
        }

        public Config setEndomorphism(ECEndomorphism eCEndomorphism) {
            this.endomorphism = eCEndomorphism;
            return this;
        }

        public Config setMultiplier(ECMultiplier eCMultiplier) {
            this.multiplier = eCMultiplier;
            return this;
        }
    }

    /* renamed from: org.bouncycastle.math.ec.ECCurve$F2m */
    /* loaded from: classes5.dex */
    public static class F2m extends AbstractF2m {
        private static final int F2M_DEFAULT_COORDS = 6;
        private ECPoint.F2m infinity;

        /* renamed from: k1 */
        private int f2255k1;

        /* renamed from: k2 */
        private int f2256k2;

        /* renamed from: k3 */
        private int f2257k3;

        /* renamed from: m */
        private int f2258m;

        public F2m(int r10, int r11, int r12, int r13, BigInteger bigInteger, BigInteger bigInteger2) {
            this(r10, r11, r12, r13, bigInteger, bigInteger2, (BigInteger) null, (BigInteger) null);
        }

        public F2m(int r1, int r2, int r3, int r4, BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4) {
            super(r1, r2, r3, r4);
            this.f2258m = r1;
            this.f2255k1 = r2;
            this.f2256k2 = r3;
            this.f2257k3 = r4;
            this.order = bigInteger3;
            this.cofactor = bigInteger4;
            this.infinity = new ECPoint.F2m(this, null, null);
            this.f2252a = fromBigInteger(bigInteger);
            this.f2253b = fromBigInteger(bigInteger2);
            this.coord = 6;
        }

        protected F2m(int r1, int r2, int r3, int r4, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, BigInteger bigInteger, BigInteger bigInteger2) {
            super(r1, r2, r3, r4);
            this.f2258m = r1;
            this.f2255k1 = r2;
            this.f2256k2 = r3;
            this.f2257k3 = r4;
            this.order = bigInteger;
            this.cofactor = bigInteger2;
            this.infinity = new ECPoint.F2m(this, null, null);
            this.f2252a = eCFieldElement;
            this.f2253b = eCFieldElement2;
            this.coord = 6;
        }

        public F2m(int r10, int r11, BigInteger bigInteger, BigInteger bigInteger2) {
            this(r10, r11, 0, 0, bigInteger, bigInteger2, (BigInteger) null, (BigInteger) null);
        }

        public F2m(int r10, int r11, BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4) {
            this(r10, r11, 0, 0, bigInteger, bigInteger2, bigInteger3, bigInteger4);
        }

        @Override // org.bouncycastle.math.p039ec.ECCurve
        protected ECCurve cloneCurve() {
            return new F2m(this.f2258m, this.f2255k1, this.f2256k2, this.f2257k3, this.f2252a, this.f2253b, this.order, this.cofactor);
        }

        @Override // org.bouncycastle.math.p039ec.ECCurve
        public ECLookupTable createCacheSafeLookupTable(ECPoint[] eCPointArr, int r9, final int r10) {
            final int r4 = (this.f2258m + 63) >>> 6;
            final int[] r6 = isTrinomial() ? new int[]{this.f2255k1} : new int[]{this.f2255k1, this.f2256k2, this.f2257k3};
            final long[] jArr = new long[r10 * r4 * 2];
            int r0 = 0;
            for (int r3 = 0; r3 < r10; r3++) {
                ECPoint eCPoint = eCPointArr[r9 + r3];
                ((ECFieldElement.F2m) eCPoint.getRawXCoord()).f2263x.copyTo(jArr, r0);
                int r02 = r0 + r4;
                ((ECFieldElement.F2m) eCPoint.getRawYCoord()).f2263x.copyTo(jArr, r02);
                r0 = r02 + r4;
            }
            return new AbstractECLookupTable() { // from class: org.bouncycastle.math.ec.ECCurve.F2m.1
                private ECPoint createPoint(long[] jArr2, long[] jArr3) {
                    return F2m.this.createRawPoint(new ECFieldElement.F2m(F2m.this.f2258m, r6, new LongArray(jArr2)), new ECFieldElement.F2m(F2m.this.f2258m, r6, new LongArray(jArr3)));
                }

                @Override // org.bouncycastle.math.p039ec.ECLookupTable
                public int getSize() {
                    return r10;
                }

                @Override // org.bouncycastle.math.p039ec.ECLookupTable
                public ECPoint lookup(int r15) {
                    int r8;
                    long[] create64 = Nat.create64(r4);
                    long[] create642 = Nat.create64(r4);
                    int r42 = 0;
                    for (int r32 = 0; r32 < r10; r32++) {
                        long j = ((r32 ^ r15) - 1) >> 31;
                        int r7 = 0;
                        while (true) {
                            r8 = r4;
                            if (r7 < r8) {
                                long j2 = create64[r7];
                                long[] jArr2 = jArr;
                                create64[r7] = j2 ^ (jArr2[r42 + r7] & j);
                                create642[r7] = create642[r7] ^ (jArr2[(r8 + r42) + r7] & j);
                                r7++;
                            }
                        }
                        r42 += r8 * 2;
                    }
                    return createPoint(create64, create642);
                }

                @Override // org.bouncycastle.math.p039ec.AbstractECLookupTable, org.bouncycastle.math.p039ec.ECLookupTable
                public ECPoint lookupVar(int r8) {
                    long[] create64 = Nat.create64(r4);
                    long[] create642 = Nat.create64(r4);
                    int r82 = r8 * r4 * 2;
                    int r2 = 0;
                    while (true) {
                        int r32 = r4;
                        if (r2 >= r32) {
                            return createPoint(create64, create642);
                        }
                        long[] jArr2 = jArr;
                        create64[r2] = jArr2[r82 + r2];
                        create642[r2] = jArr2[r32 + r82 + r2];
                        r2++;
                    }
                }
            };
        }

        @Override // org.bouncycastle.math.p039ec.ECCurve
        protected ECMultiplier createDefaultMultiplier() {
            return isKoblitz() ? new WTauNafMultiplier() : super.createDefaultMultiplier();
        }

        @Override // org.bouncycastle.math.p039ec.ECCurve
        protected ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            return new ECPoint.F2m(this, eCFieldElement, eCFieldElement2);
        }

        @Override // org.bouncycastle.math.p039ec.ECCurve
        protected ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr) {
            return new ECPoint.F2m(this, eCFieldElement, eCFieldElement2, eCFieldElementArr);
        }

        @Override // org.bouncycastle.math.p039ec.ECCurve
        public ECFieldElement fromBigInteger(BigInteger bigInteger) {
            return new ECFieldElement.F2m(this.f2258m, this.f2255k1, this.f2256k2, this.f2257k3, bigInteger);
        }

        @Override // org.bouncycastle.math.p039ec.ECCurve
        public int getFieldSize() {
            return this.f2258m;
        }

        @Override // org.bouncycastle.math.p039ec.ECCurve
        public ECPoint getInfinity() {
            return this.infinity;
        }

        public int getK1() {
            return this.f2255k1;
        }

        public int getK2() {
            return this.f2256k2;
        }

        public int getK3() {
            return this.f2257k3;
        }

        public int getM() {
            return this.f2258m;
        }

        public boolean isTrinomial() {
            return this.f2256k2 == 0 && this.f2257k3 == 0;
        }

        @Override // org.bouncycastle.math.p039ec.ECCurve
        public boolean supportsCoordinateSystem(int r3) {
            return r3 == 0 || r3 == 1 || r3 == 6;
        }
    }

    /* renamed from: org.bouncycastle.math.ec.ECCurve$Fp */
    /* loaded from: classes5.dex */
    public static class C5335Fp extends AbstractFp {
        private static final int FP_DEFAULT_COORDS = 4;
        ECPoint.C5338Fp infinity;

        /* renamed from: q */
        BigInteger f2259q;

        /* renamed from: r */
        BigInteger f2260r;

        public C5335Fp(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
            this(bigInteger, bigInteger2, bigInteger3, null, null);
        }

        public C5335Fp(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, BigInteger bigInteger5) {
            super(bigInteger);
            this.f2259q = bigInteger;
            this.f2260r = ECFieldElement.C5336Fp.calculateResidue(bigInteger);
            this.infinity = new ECPoint.C5338Fp(this, null, null);
            this.f2252a = fromBigInteger(bigInteger2);
            this.f2253b = fromBigInteger(bigInteger3);
            this.order = bigInteger4;
            this.cofactor = bigInteger5;
            this.coord = 4;
        }

        protected C5335Fp(BigInteger bigInteger, BigInteger bigInteger2, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, BigInteger bigInteger3, BigInteger bigInteger4) {
            super(bigInteger);
            this.f2259q = bigInteger;
            this.f2260r = bigInteger2;
            this.infinity = new ECPoint.C5338Fp(this, null, null);
            this.f2252a = eCFieldElement;
            this.f2253b = eCFieldElement2;
            this.order = bigInteger3;
            this.cofactor = bigInteger4;
            this.coord = 4;
        }

        @Override // org.bouncycastle.math.p039ec.ECCurve
        protected ECCurve cloneCurve() {
            return new C5335Fp(this.f2259q, this.f2260r, this.f2252a, this.f2253b, this.order, this.cofactor);
        }

        @Override // org.bouncycastle.math.p039ec.ECCurve
        protected ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            return new ECPoint.C5338Fp(this, eCFieldElement, eCFieldElement2);
        }

        @Override // org.bouncycastle.math.p039ec.ECCurve
        protected ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr) {
            return new ECPoint.C5338Fp(this, eCFieldElement, eCFieldElement2, eCFieldElementArr);
        }

        @Override // org.bouncycastle.math.p039ec.ECCurve
        public ECFieldElement fromBigInteger(BigInteger bigInteger) {
            return new ECFieldElement.C5336Fp(this.f2259q, this.f2260r, bigInteger);
        }

        @Override // org.bouncycastle.math.p039ec.ECCurve
        public int getFieldSize() {
            return this.f2259q.bitLength();
        }

        @Override // org.bouncycastle.math.p039ec.ECCurve
        public ECPoint getInfinity() {
            return this.infinity;
        }

        public BigInteger getQ() {
            return this.f2259q;
        }

        @Override // org.bouncycastle.math.p039ec.ECCurve
        public ECPoint importPoint(ECPoint eCPoint) {
            int coordinateSystem;
            return (this == eCPoint.getCurve() || getCoordinateSystem() != 2 || eCPoint.isInfinity() || !((coordinateSystem = eCPoint.getCurve().getCoordinateSystem()) == 2 || coordinateSystem == 3 || coordinateSystem == 4)) ? super.importPoint(eCPoint) : new ECPoint.C5338Fp(this, fromBigInteger(eCPoint.f2267x.toBigInteger()), fromBigInteger(eCPoint.f2268y.toBigInteger()), new ECFieldElement[]{fromBigInteger(eCPoint.f2269zs[0].toBigInteger())});
        }

        @Override // org.bouncycastle.math.p039ec.ECCurve
        public boolean supportsCoordinateSystem(int r3) {
            return r3 == 0 || r3 == 1 || r3 == 2 || r3 == 4;
        }
    }

    protected ECCurve(FiniteField finiteField) {
        this.field = finiteField;
    }

    public static int[] getAllCoordinateSystems() {
        return new int[]{0, 1, 2, 3, 4, 5, 6, 7};
    }

    protected void checkPoint(ECPoint eCPoint) {
        if (eCPoint == null || this != eCPoint.getCurve()) {
            throw new IllegalArgumentException("'point' must be non-null and on this curve");
        }
    }

    protected void checkPoints(ECPoint[] eCPointArr) {
        checkPoints(eCPointArr, 0, eCPointArr.length);
    }

    protected void checkPoints(ECPoint[] eCPointArr, int r4, int r5) {
        if (eCPointArr == null) {
            throw new IllegalArgumentException("'points' cannot be null");
        }
        if (r4 < 0 || r5 < 0 || r4 > eCPointArr.length - r5) {
            throw new IllegalArgumentException("invalid range specified for 'points'");
        }
        for (int r0 = 0; r0 < r5; r0++) {
            ECPoint eCPoint = eCPointArr[r4 + r0];
            if (eCPoint != null && this != eCPoint.getCurve()) {
                throw new IllegalArgumentException("'points' entries must be null or on this curve");
            }
        }
    }

    protected abstract ECCurve cloneCurve();

    public synchronized Config configure() {
        return new Config(this.coord, this.endomorphism, this.multiplier);
    }

    public ECLookupTable createCacheSafeLookupTable(ECPoint[] eCPointArr, int r14, final int r15) {
        final int fieldSize = (getFieldSize() + 7) >>> 3;
        final byte[] bArr = new byte[r15 * fieldSize * 2];
        int r4 = 0;
        for (int r3 = 0; r3 < r15; r3++) {
            ECPoint eCPoint = eCPointArr[r14 + r3];
            byte[] byteArray = eCPoint.getRawXCoord().toBigInteger().toByteArray();
            byte[] byteArray2 = eCPoint.getRawYCoord().toBigInteger().toByteArray();
            int r8 = 1;
            int r7 = byteArray.length > fieldSize ? 1 : 0;
            int length = byteArray.length - r7;
            if (byteArray2.length <= fieldSize) {
                r8 = 0;
            }
            int length2 = byteArray2.length - r8;
            int r42 = r4 + fieldSize;
            System.arraycopy(byteArray, r7, bArr, r42 - length, length);
            r4 = r42 + fieldSize;
            System.arraycopy(byteArray2, r8, bArr, r4 - length2, length2);
        }
        return new AbstractECLookupTable() { // from class: org.bouncycastle.math.ec.ECCurve.1
            private ECPoint createPoint(byte[] bArr2, byte[] bArr3) {
                ECCurve eCCurve = ECCurve.this;
                return eCCurve.createRawPoint(eCCurve.fromBigInteger(new BigInteger(1, bArr2)), ECCurve.this.fromBigInteger(new BigInteger(1, bArr3)));
            }

            @Override // org.bouncycastle.math.p039ec.ECLookupTable
            public int getSize() {
                return r15;
            }

            @Override // org.bouncycastle.math.p039ec.ECLookupTable
            public ECPoint lookup(int r12) {
                int r72;
                int r0 = fieldSize;
                byte[] bArr2 = new byte[r0];
                byte[] bArr3 = new byte[r0];
                int r43 = 0;
                for (int r32 = 0; r32 < r15; r32++) {
                    int r5 = ((r32 ^ r12) - 1) >> 31;
                    int r6 = 0;
                    while (true) {
                        r72 = fieldSize;
                        if (r6 < r72) {
                            byte b = bArr2[r6];
                            byte[] bArr4 = bArr;
                            bArr2[r6] = (byte) (b ^ (bArr4[r43 + r6] & r5));
                            bArr3[r6] = (byte) ((bArr4[(r72 + r43) + r6] & r5) ^ bArr3[r6]);
                            r6++;
                        }
                    }
                    r43 += r72 * 2;
                }
                return createPoint(bArr2, bArr3);
            }

            @Override // org.bouncycastle.math.p039ec.AbstractECLookupTable, org.bouncycastle.math.p039ec.ECLookupTable
            public ECPoint lookupVar(int r72) {
                int r0 = fieldSize;
                byte[] bArr2 = new byte[r0];
                byte[] bArr3 = new byte[r0];
                int r73 = r72 * r0 * 2;
                int r02 = 0;
                while (true) {
                    int r32 = fieldSize;
                    if (r02 >= r32) {
                        return createPoint(bArr2, bArr3);
                    }
                    byte[] bArr4 = bArr;
                    bArr2[r02] = bArr4[r73 + r02];
                    bArr3[r02] = bArr4[r32 + r73 + r02];
                    r02++;
                }
            }
        };
    }

    protected ECMultiplier createDefaultMultiplier() {
        ECEndomorphism eCEndomorphism = this.endomorphism;
        return eCEndomorphism instanceof GLVEndomorphism ? new GLVMultiplier(this, (GLVEndomorphism) eCEndomorphism) : new WNafL2RMultiplier();
    }

    public ECPoint createPoint(BigInteger bigInteger, BigInteger bigInteger2) {
        return createRawPoint(fromBigInteger(bigInteger), fromBigInteger(bigInteger2));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr);

    public ECPoint decodePoint(byte[] bArr) {
        ECPoint infinity;
        int fieldSize = (getFieldSize() + 7) / 8;
        byte b = bArr[0];
        if (b != 0) {
            if (b == 2 || b == 3) {
                if (bArr.length != fieldSize + 1) {
                    throw new IllegalArgumentException("Incorrect length for compressed encoding");
                }
                infinity = decompressPoint(b & 1, BigIntegers.fromUnsignedByteArray(bArr, 1, fieldSize));
                if (!infinity.implIsValid(true, true)) {
                    throw new IllegalArgumentException("Invalid point");
                }
            } else if (b != 4) {
                if (b != 6 && b != 7) {
                    throw new IllegalArgumentException("Invalid point encoding 0x" + Integer.toString(b, 16));
                } else if (bArr.length != (fieldSize * 2) + 1) {
                    throw new IllegalArgumentException("Incorrect length for hybrid encoding");
                } else {
                    BigInteger fromUnsignedByteArray = BigIntegers.fromUnsignedByteArray(bArr, 1, fieldSize);
                    BigInteger fromUnsignedByteArray2 = BigIntegers.fromUnsignedByteArray(bArr, fieldSize + 1, fieldSize);
                    if (fromUnsignedByteArray2.testBit(0) != (b == 7)) {
                        throw new IllegalArgumentException("Inconsistent Y coordinate in hybrid encoding");
                    }
                    infinity = validatePoint(fromUnsignedByteArray, fromUnsignedByteArray2);
                }
            } else if (bArr.length != (fieldSize * 2) + 1) {
                throw new IllegalArgumentException("Incorrect length for uncompressed encoding");
            } else {
                infinity = validatePoint(BigIntegers.fromUnsignedByteArray(bArr, 1, fieldSize), BigIntegers.fromUnsignedByteArray(bArr, fieldSize + 1, fieldSize));
            }
        } else if (bArr.length != 1) {
            throw new IllegalArgumentException("Incorrect length for infinity encoding");
        } else {
            infinity = getInfinity();
        }
        if (b == 0 || !infinity.isInfinity()) {
            return infinity;
        }
        throw new IllegalArgumentException("Invalid infinity encoding");
    }

    protected abstract ECPoint decompressPoint(int r1, BigInteger bigInteger);

    public boolean equals(Object obj) {
        return this == obj || ((obj instanceof ECCurve) && equals((ECCurve) obj));
    }

    public boolean equals(ECCurve eCCurve) {
        return this == eCCurve || (eCCurve != null && getField().equals(eCCurve.getField()) && getA().toBigInteger().equals(eCCurve.getA().toBigInteger()) && getB().toBigInteger().equals(eCCurve.getB().toBigInteger()));
    }

    public abstract ECFieldElement fromBigInteger(BigInteger bigInteger);

    public ECFieldElement getA() {
        return this.f2252a;
    }

    public ECFieldElement getB() {
        return this.f2253b;
    }

    public BigInteger getCofactor() {
        return this.cofactor;
    }

    public int getCoordinateSystem() {
        return this.coord;
    }

    public ECEndomorphism getEndomorphism() {
        return this.endomorphism;
    }

    public FiniteField getField() {
        return this.field;
    }

    public abstract int getFieldSize();

    public abstract ECPoint getInfinity();

    public ECMultiplier getMultiplier() {
        if (this.multiplier == null) {
            this.multiplier = createDefaultMultiplier();
        }
        return this.multiplier;
    }

    public BigInteger getOrder() {
        return this.order;
    }

    public PreCompInfo getPreCompInfo(ECPoint eCPoint, String str) {
        Hashtable hashtable;
        PreCompInfo preCompInfo;
        checkPoint(eCPoint);
        synchronized (eCPoint) {
            hashtable = eCPoint.preCompTable;
        }
        if (hashtable == null) {
            return null;
        }
        synchronized (hashtable) {
            preCompInfo = (PreCompInfo) hashtable.get(str);
        }
        return preCompInfo;
    }

    public int hashCode() {
        return (getField().hashCode() ^ Integers.rotateLeft(getA().toBigInteger().hashCode(), 8)) ^ Integers.rotateLeft(getB().toBigInteger().hashCode(), 16);
    }

    public ECPoint importPoint(ECPoint eCPoint) {
        if (this == eCPoint.getCurve()) {
            return eCPoint;
        }
        if (eCPoint.isInfinity()) {
            return getInfinity();
        }
        ECPoint normalize = eCPoint.normalize();
        return createPoint(normalize.getXCoord().toBigInteger(), normalize.getYCoord().toBigInteger());
    }

    public abstract boolean isValidFieldElement(BigInteger bigInteger);

    public void normalizeAll(ECPoint[] eCPointArr) {
        normalizeAll(eCPointArr, 0, eCPointArr.length, null);
    }

    public void normalizeAll(ECPoint[] eCPointArr, int r10, int r11, ECFieldElement eCFieldElement) {
        checkPoints(eCPointArr, r10, r11);
        int coordinateSystem = getCoordinateSystem();
        if (coordinateSystem == 0 || coordinateSystem == 5) {
            if (eCFieldElement != null) {
                throw new IllegalArgumentException("'iso' not valid for affine coordinates");
            }
            return;
        }
        ECFieldElement[] eCFieldElementArr = new ECFieldElement[r11];
        int[] r1 = new int[r11];
        int r4 = 0;
        for (int r3 = 0; r3 < r11; r3++) {
            int r5 = r10 + r3;
            ECPoint eCPoint = eCPointArr[r5];
            if (eCPoint != null && (eCFieldElement != null || !eCPoint.isNormalized())) {
                eCFieldElementArr[r4] = eCPoint.getZCoord(0);
                r1[r4] = r5;
                r4++;
            }
        }
        if (r4 == 0) {
            return;
        }
        ECAlgorithms.montgomeryTrick(eCFieldElementArr, 0, r4, eCFieldElement);
        for (int r2 = 0; r2 < r4; r2++) {
            int r102 = r1[r2];
            eCPointArr[r102] = eCPointArr[r102].normalize(eCFieldElementArr[r2]);
        }
    }

    public PreCompInfo precompute(ECPoint eCPoint, String str, PreCompCallback preCompCallback) {
        Hashtable hashtable;
        PreCompInfo precompute;
        checkPoint(eCPoint);
        synchronized (eCPoint) {
            hashtable = eCPoint.preCompTable;
            if (hashtable == null) {
                hashtable = new Hashtable(4);
                eCPoint.preCompTable = hashtable;
            }
        }
        synchronized (hashtable) {
            PreCompInfo preCompInfo = (PreCompInfo) hashtable.get(str);
            precompute = preCompCallback.precompute(preCompInfo);
            if (precompute != preCompInfo) {
                hashtable.put(str, precompute);
            }
        }
        return precompute;
    }

    public abstract ECFieldElement randomFieldElement(SecureRandom secureRandom);

    public abstract ECFieldElement randomFieldElementMult(SecureRandom secureRandom);

    public boolean supportsCoordinateSystem(int r1) {
        return r1 == 0;
    }

    public ECPoint validatePoint(BigInteger bigInteger, BigInteger bigInteger2) {
        ECPoint createPoint = createPoint(bigInteger, bigInteger2);
        if (createPoint.isValid()) {
            return createPoint;
        }
        throw new IllegalArgumentException("Invalid point coordinates");
    }
}
