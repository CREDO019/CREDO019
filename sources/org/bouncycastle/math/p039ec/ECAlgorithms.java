package org.bouncycastle.math.p039ec;

import com.google.common.base.Ascii;
import java.math.BigInteger;
import org.bouncycastle.math.field.FiniteField;
import org.bouncycastle.math.field.PolynomialExtensionField;
import org.bouncycastle.math.p039ec.ECCurve;
import org.bouncycastle.math.p039ec.endo.ECEndomorphism;
import org.bouncycastle.math.p039ec.endo.EndoUtil;
import org.bouncycastle.math.p039ec.endo.GLVEndomorphism;
import org.bouncycastle.math.raw.Nat;

/* renamed from: org.bouncycastle.math.ec.ECAlgorithms */
/* loaded from: classes5.dex */
public class ECAlgorithms {
    public static ECPoint cleanPoint(ECCurve eCCurve, ECPoint eCPoint) {
        if (eCCurve.equals(eCPoint.getCurve())) {
            return eCCurve.decodePoint(eCPoint.getEncoded(false));
        }
        throw new IllegalArgumentException("Point must be on the same curve");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ECPoint implCheckResult(ECPoint eCPoint) {
        if (eCPoint.isValidPartial()) {
            return eCPoint;
        }
        throw new IllegalStateException("Invalid result");
    }

    private static ECPoint implShamirsTrickFixedPoint(ECPoint eCPoint, BigInteger bigInteger, ECPoint eCPoint2, BigInteger bigInteger2) {
        ECPoint add;
        ECPoint offset;
        ECCurve curve = eCPoint.getCurve();
        int combSize = FixedPointUtil.getCombSize(curve);
        if (bigInteger.bitLength() > combSize || bigInteger2.bitLength() > combSize) {
            throw new IllegalStateException("fixed-point comb doesn't support scalars larger than the curve order");
        }
        FixedPointPreCompInfo precompute = FixedPointUtil.precompute(eCPoint);
        FixedPointPreCompInfo precompute2 = FixedPointUtil.precompute(eCPoint2);
        ECLookupTable lookupTable = precompute.getLookupTable();
        ECLookupTable lookupTable2 = precompute2.getLookupTable();
        int width = precompute.getWidth();
        if (width != precompute2.getWidth()) {
            FixedPointCombMultiplier fixedPointCombMultiplier = new FixedPointCombMultiplier();
            add = fixedPointCombMultiplier.multiply(eCPoint, bigInteger);
            offset = fixedPointCombMultiplier.multiply(eCPoint2, bigInteger2);
        } else {
            int r3 = ((combSize + width) - 1) / width;
            ECPoint infinity = curve.getInfinity();
            int r8 = width * r3;
            int[] fromBigInteger = Nat.fromBigInteger(r8, bigInteger);
            int[] fromBigInteger2 = Nat.fromBigInteger(r8, bigInteger2);
            int r82 = r8 - 1;
            for (int r10 = 0; r10 < r3; r10++) {
                int r12 = 0;
                int r13 = 0;
                for (int r11 = r82 - r10; r11 >= 0; r11 -= r3) {
                    int r14 = r11 >>> 5;
                    int r16 = r11 & 31;
                    int r15 = fromBigInteger[r14] >>> r16;
                    r12 = ((r12 ^ (r15 >>> 1)) << 1) ^ r15;
                    int r142 = fromBigInteger2[r14] >>> r16;
                    r13 = ((r13 ^ (r142 >>> 1)) << 1) ^ r142;
                }
                infinity = infinity.twicePlus(lookupTable.lookupVar(r12).add(lookupTable2.lookupVar(r13)));
            }
            add = infinity.add(precompute.getOffset());
            offset = precompute2.getOffset();
        }
        return add.add(offset);
    }

    static ECPoint implShamirsTrickJsf(ECPoint eCPoint, BigInteger bigInteger, ECPoint eCPoint2, BigInteger bigInteger2) {
        ECCurve curve = eCPoint.getCurve();
        ECPoint infinity = curve.getInfinity();
        ECPoint[] eCPointArr = {eCPoint2, eCPoint.subtract(eCPoint2), eCPoint, eCPoint.add(eCPoint2)};
        curve.normalizeAll(eCPointArr);
        ECPoint[] eCPointArr2 = {eCPointArr[3].negate(), eCPointArr[2].negate(), eCPointArr[1].negate(), eCPointArr[0].negate(), infinity, eCPointArr[0], eCPointArr[1], eCPointArr[2], eCPointArr[3]};
        byte[] generateJSF = WNafUtil.generateJSF(bigInteger, bigInteger2);
        int length = generateJSF.length;
        while (true) {
            length--;
            if (length < 0) {
                return infinity;
            }
            byte b = generateJSF[length];
            infinity = infinity.twicePlus(eCPointArr2[(((b << Ascii.CAN) >> 28) * 3) + 4 + ((b << Ascii.f1122FS) >> 28)]);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ECPoint implShamirsTrickWNaf(ECPoint eCPoint, BigInteger bigInteger, ECPoint eCPoint2, BigInteger bigInteger2) {
        boolean z = bigInteger.signum() < 0;
        boolean z2 = bigInteger2.signum() < 0;
        BigInteger abs = bigInteger.abs();
        BigInteger abs2 = bigInteger2.abs();
        int windowSize = WNafUtil.getWindowSize(abs.bitLength(), 8);
        int windowSize2 = WNafUtil.getWindowSize(abs2.bitLength(), 8);
        WNafPreCompInfo precompute = WNafUtil.precompute(eCPoint, windowSize, true);
        WNafPreCompInfo precompute2 = WNafUtil.precompute(eCPoint2, windowSize2, true);
        int combSize = FixedPointUtil.getCombSize(eCPoint.getCurve());
        if (z || z2 || bigInteger.bitLength() > combSize || bigInteger2.bitLength() > combSize || !precompute.isPromoted() || !precompute2.isPromoted()) {
            int min = Math.min(8, precompute.getWidth());
            int min2 = Math.min(8, precompute2.getWidth());
            return implShamirsTrickWNaf(z ? precompute.getPreCompNeg() : precompute.getPreComp(), z ? precompute.getPreComp() : precompute.getPreCompNeg(), WNafUtil.generateWindowNaf(min, abs), z2 ? precompute2.getPreCompNeg() : precompute2.getPreComp(), z2 ? precompute2.getPreComp() : precompute2.getPreCompNeg(), WNafUtil.generateWindowNaf(min2, abs2));
        }
        return implShamirsTrickFixedPoint(eCPoint, bigInteger, eCPoint2, bigInteger2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ECPoint implShamirsTrickWNaf(ECEndomorphism eCEndomorphism, ECPoint eCPoint, BigInteger bigInteger, BigInteger bigInteger2) {
        boolean z = bigInteger.signum() < 0;
        boolean z2 = bigInteger2.signum() < 0;
        BigInteger abs = bigInteger.abs();
        BigInteger abs2 = bigInteger2.abs();
        WNafPreCompInfo precompute = WNafUtil.precompute(eCPoint, WNafUtil.getWindowSize(Math.max(abs.bitLength(), abs2.bitLength()), 8), true);
        WNafPreCompInfo precomputeWithPointMap = WNafUtil.precomputeWithPointMap(EndoUtil.mapPoint(eCEndomorphism, eCPoint), eCEndomorphism.getPointMap(), precompute, true);
        int min = Math.min(8, precompute.getWidth());
        int min2 = Math.min(8, precomputeWithPointMap.getWidth());
        return implShamirsTrickWNaf(z ? precompute.getPreCompNeg() : precompute.getPreComp(), z ? precompute.getPreComp() : precompute.getPreCompNeg(), WNafUtil.generateWindowNaf(min, abs), z2 ? precomputeWithPointMap.getPreCompNeg() : precomputeWithPointMap.getPreComp(), z2 ? precomputeWithPointMap.getPreComp() : precomputeWithPointMap.getPreCompNeg(), WNafUtil.generateWindowNaf(min2, abs2));
    }

    private static ECPoint implShamirsTrickWNaf(ECPoint[] eCPointArr, ECPoint[] eCPointArr2, byte[] bArr, ECPoint[] eCPointArr3, ECPoint[] eCPointArr4, byte[] bArr2) {
        ECPoint eCPoint;
        int max = Math.max(bArr.length, bArr2.length);
        ECPoint infinity = eCPointArr[0].getCurve().getInfinity();
        int r0 = max - 1;
        ECPoint eCPoint2 = infinity;
        int r3 = 0;
        while (r0 >= 0) {
            byte b = r0 < bArr.length ? bArr[r0] : (byte) 0;
            byte b2 = r0 < bArr2.length ? bArr2[r0] : (byte) 0;
            if ((b | b2) == 0) {
                r3++;
            } else {
                if (b != 0) {
                    eCPoint = infinity.add((b < 0 ? eCPointArr2 : eCPointArr)[Math.abs((int) b) >>> 1]);
                } else {
                    eCPoint = infinity;
                }
                if (b2 != 0) {
                    eCPoint = eCPoint.add((b2 < 0 ? eCPointArr4 : eCPointArr3)[Math.abs((int) b2) >>> 1]);
                }
                if (r3 > 0) {
                    eCPoint2 = eCPoint2.timesPow2(r3);
                    r3 = 0;
                }
                eCPoint2 = eCPoint2.twicePlus(eCPoint);
            }
            r0--;
        }
        return r3 > 0 ? eCPoint2.timesPow2(r3) : eCPoint2;
    }

    static ECPoint implSumOfMultiplies(ECEndomorphism eCEndomorphism, ECPoint[] eCPointArr, BigInteger[] bigIntegerArr) {
        ECPoint[] eCPointArr2 = eCPointArr;
        int length = eCPointArr2.length;
        int r2 = length << 1;
        boolean[] zArr = new boolean[r2];
        WNafPreCompInfo[] wNafPreCompInfoArr = new WNafPreCompInfo[r2];
        byte[][] bArr = new byte[r2];
        ECPointMap pointMap = eCEndomorphism.getPointMap();
        int r7 = 0;
        while (r7 < length) {
            int r8 = r7 << 1;
            int r9 = r8 + 1;
            BigInteger bigInteger = bigIntegerArr[r8];
            zArr[r8] = bigInteger.signum() < 0;
            BigInteger abs = bigInteger.abs();
            BigInteger bigInteger2 = bigIntegerArr[r9];
            zArr[r9] = bigInteger2.signum() < 0;
            BigInteger abs2 = bigInteger2.abs();
            int windowSize = WNafUtil.getWindowSize(Math.max(abs.bitLength(), abs2.bitLength()), 8);
            ECPoint eCPoint = eCPointArr2[r7];
            WNafPreCompInfo precompute = WNafUtil.precompute(eCPoint, windowSize, true);
            WNafPreCompInfo precomputeWithPointMap = WNafUtil.precomputeWithPointMap(EndoUtil.mapPoint(eCEndomorphism, eCPoint), pointMap, precompute, true);
            int min = Math.min(8, precompute.getWidth());
            int min2 = Math.min(8, precomputeWithPointMap.getWidth());
            wNafPreCompInfoArr[r8] = precompute;
            wNafPreCompInfoArr[r9] = precomputeWithPointMap;
            bArr[r8] = WNafUtil.generateWindowNaf(min, abs);
            bArr[r9] = WNafUtil.generateWindowNaf(min2, abs2);
            r7++;
            eCPointArr2 = eCPointArr;
        }
        return implSumOfMultiplies(zArr, wNafPreCompInfoArr, bArr);
    }

    static ECPoint implSumOfMultiplies(ECPoint[] eCPointArr, BigInteger[] bigIntegerArr) {
        int length = eCPointArr.length;
        boolean[] zArr = new boolean[length];
        WNafPreCompInfo[] wNafPreCompInfoArr = new WNafPreCompInfo[length];
        byte[][] bArr = new byte[length];
        for (int r5 = 0; r5 < length; r5++) {
            BigInteger bigInteger = bigIntegerArr[r5];
            zArr[r5] = bigInteger.signum() < 0;
            BigInteger abs = bigInteger.abs();
            WNafPreCompInfo precompute = WNafUtil.precompute(eCPointArr[r5], WNafUtil.getWindowSize(abs.bitLength(), 8), true);
            int min = Math.min(8, precompute.getWidth());
            wNafPreCompInfoArr[r5] = precompute;
            bArr[r5] = WNafUtil.generateWindowNaf(min, abs);
        }
        return implSumOfMultiplies(zArr, wNafPreCompInfoArr, bArr);
    }

    private static ECPoint implSumOfMultiplies(boolean[] zArr, WNafPreCompInfo[] wNafPreCompInfoArr, byte[][] bArr) {
        int length = bArr.length;
        int r3 = 0;
        for (byte[] bArr2 : bArr) {
            r3 = Math.max(r3, bArr2.length);
        }
        ECPoint infinity = wNafPreCompInfoArr[0].getPreComp()[0].getCurve().getInfinity();
        int r32 = r3 - 1;
        ECPoint eCPoint = infinity;
        int r5 = 0;
        while (r32 >= 0) {
            ECPoint eCPoint2 = infinity;
            for (int r7 = 0; r7 < length; r7++) {
                byte[] bArr3 = bArr[r7];
                byte b = r32 < bArr3.length ? bArr3[r32] : (byte) 0;
                if (b != 0) {
                    int abs = Math.abs((int) b);
                    WNafPreCompInfo wNafPreCompInfo = wNafPreCompInfoArr[r7];
                    eCPoint2 = eCPoint2.add(((b < 0) == zArr[r7] ? wNafPreCompInfo.getPreComp() : wNafPreCompInfo.getPreCompNeg())[abs >>> 1]);
                }
            }
            if (eCPoint2 == infinity) {
                r5++;
            } else {
                if (r5 > 0) {
                    eCPoint = eCPoint.timesPow2(r5);
                    r5 = 0;
                }
                eCPoint = eCPoint.twicePlus(eCPoint2);
            }
            r32--;
        }
        return r5 > 0 ? eCPoint.timesPow2(r5) : eCPoint;
    }

    static ECPoint implSumOfMultipliesGLV(ECPoint[] eCPointArr, BigInteger[] bigIntegerArr, GLVEndomorphism gLVEndomorphism) {
        BigInteger order = eCPointArr[0].getCurve().getOrder();
        int length = eCPointArr.length;
        int r3 = length << 1;
        BigInteger[] bigIntegerArr2 = new BigInteger[r3];
        int r6 = 0;
        for (int r5 = 0; r5 < length; r5++) {
            BigInteger[] decomposeScalar = gLVEndomorphism.decomposeScalar(bigIntegerArr[r5].mod(order));
            int r8 = r6 + 1;
            bigIntegerArr2[r6] = decomposeScalar[0];
            r6 = r8 + 1;
            bigIntegerArr2[r8] = decomposeScalar[1];
        }
        if (gLVEndomorphism.hasEfficientPointMap()) {
            return implSumOfMultiplies(gLVEndomorphism, eCPointArr, bigIntegerArr2);
        }
        ECPoint[] eCPointArr2 = new ECPoint[r3];
        int r1 = 0;
        for (ECPoint eCPoint : eCPointArr) {
            ECPoint mapPoint = EndoUtil.mapPoint(gLVEndomorphism, eCPoint);
            int r62 = r1 + 1;
            eCPointArr2[r1] = eCPoint;
            r1 = r62 + 1;
            eCPointArr2[r62] = mapPoint;
        }
        return implSumOfMultiplies(eCPointArr2, bigIntegerArr2);
    }

    public static ECPoint importPoint(ECCurve eCCurve, ECPoint eCPoint) {
        if (eCCurve.equals(eCPoint.getCurve())) {
            return eCCurve.importPoint(eCPoint);
        }
        throw new IllegalArgumentException("Point must be on the same curve");
    }

    public static boolean isF2mCurve(ECCurve eCCurve) {
        return isF2mField(eCCurve.getField());
    }

    public static boolean isF2mField(FiniteField finiteField) {
        return finiteField.getDimension() > 1 && finiteField.getCharacteristic().equals(ECConstants.TWO) && (finiteField instanceof PolynomialExtensionField);
    }

    public static boolean isFpCurve(ECCurve eCCurve) {
        return isFpField(eCCurve.getField());
    }

    public static boolean isFpField(FiniteField finiteField) {
        return finiteField.getDimension() == 1;
    }

    public static void montgomeryTrick(ECFieldElement[] eCFieldElementArr, int r2, int r3) {
        montgomeryTrick(eCFieldElementArr, r2, r3, null);
    }

    public static void montgomeryTrick(ECFieldElement[] eCFieldElementArr, int r5, int r6, ECFieldElement eCFieldElement) {
        ECFieldElement[] eCFieldElementArr2 = new ECFieldElement[r6];
        int r2 = 0;
        eCFieldElementArr2[0] = eCFieldElementArr[r5];
        while (true) {
            r2++;
            if (r2 >= r6) {
                break;
            }
            eCFieldElementArr2[r2] = eCFieldElementArr2[r2 - 1].multiply(eCFieldElementArr[r5 + r2]);
        }
        int r22 = r2 - 1;
        if (eCFieldElement != null) {
            eCFieldElementArr2[r22] = eCFieldElementArr2[r22].multiply(eCFieldElement);
        }
        ECFieldElement invert = eCFieldElementArr2[r22].invert();
        while (r22 > 0) {
            int r7 = r22 - 1;
            int r23 = r22 + r5;
            ECFieldElement eCFieldElement2 = eCFieldElementArr[r23];
            eCFieldElementArr[r23] = eCFieldElementArr2[r7].multiply(invert);
            invert = invert.multiply(eCFieldElement2);
            r22 = r7;
        }
        eCFieldElementArr[r5] = invert;
    }

    public static ECPoint referenceMultiply(ECPoint eCPoint, BigInteger bigInteger) {
        BigInteger abs = bigInteger.abs();
        ECPoint infinity = eCPoint.getCurve().getInfinity();
        int bitLength = abs.bitLength();
        if (bitLength > 0) {
            if (abs.testBit(0)) {
                infinity = eCPoint;
            }
            for (int r3 = 1; r3 < bitLength; r3++) {
                eCPoint = eCPoint.twice();
                if (abs.testBit(r3)) {
                    infinity = infinity.add(eCPoint);
                }
            }
        }
        return bigInteger.signum() < 0 ? infinity.negate() : infinity;
    }

    public static ECPoint shamirsTrick(ECPoint eCPoint, BigInteger bigInteger, ECPoint eCPoint2, BigInteger bigInteger2) {
        return implCheckResult(implShamirsTrickJsf(eCPoint, bigInteger, importPoint(eCPoint.getCurve(), eCPoint2), bigInteger2));
    }

    public static ECPoint sumOfMultiplies(ECPoint[] eCPointArr, BigInteger[] bigIntegerArr) {
        if (eCPointArr != null && bigIntegerArr != null && eCPointArr.length == bigIntegerArr.length) {
            if (eCPointArr.length >= 1) {
                int length = eCPointArr.length;
                if (length != 1) {
                    if (length != 2) {
                        ECPoint eCPoint = eCPointArr[0];
                        ECCurve curve = eCPoint.getCurve();
                        ECPoint[] eCPointArr2 = new ECPoint[length];
                        eCPointArr2[0] = eCPoint;
                        for (int r1 = 1; r1 < length; r1++) {
                            eCPointArr2[r1] = importPoint(curve, eCPointArr[r1]);
                        }
                        ECEndomorphism endomorphism = curve.getEndomorphism();
                        return endomorphism instanceof GLVEndomorphism ? implCheckResult(implSumOfMultipliesGLV(eCPointArr2, bigIntegerArr, (GLVEndomorphism) endomorphism)) : implCheckResult(implSumOfMultiplies(eCPointArr2, bigIntegerArr));
                    }
                    return sumOfTwoMultiplies(eCPointArr[0], bigIntegerArr[0], eCPointArr[1], bigIntegerArr[1]);
                }
                return eCPointArr[0].multiply(bigIntegerArr[0]);
            }
        }
        throw new IllegalArgumentException("point and scalar arrays should be non-null, and of equal, non-zero, length");
    }

    public static ECPoint sumOfTwoMultiplies(ECPoint eCPoint, BigInteger bigInteger, ECPoint eCPoint2, BigInteger bigInteger2) {
        ECPoint implSumOfMultipliesGLV;
        ECCurve curve = eCPoint.getCurve();
        ECPoint importPoint = importPoint(curve, eCPoint2);
        if ((curve instanceof ECCurve.AbstractF2m) && ((ECCurve.AbstractF2m) curve).isKoblitz()) {
            implSumOfMultipliesGLV = eCPoint.multiply(bigInteger).add(importPoint.multiply(bigInteger2));
        } else {
            ECEndomorphism endomorphism = curve.getEndomorphism();
            implSumOfMultipliesGLV = endomorphism instanceof GLVEndomorphism ? implSumOfMultipliesGLV(new ECPoint[]{eCPoint, importPoint}, new BigInteger[]{bigInteger, bigInteger2}, (GLVEndomorphism) endomorphism) : implShamirsTrickWNaf(eCPoint, bigInteger, importPoint, bigInteger2);
        }
        return implCheckResult(implSumOfMultipliesGLV);
    }

    public static ECPoint validatePoint(ECPoint eCPoint) {
        if (eCPoint.isValid()) {
            return eCPoint;
        }
        throw new IllegalStateException("Invalid point");
    }
}
