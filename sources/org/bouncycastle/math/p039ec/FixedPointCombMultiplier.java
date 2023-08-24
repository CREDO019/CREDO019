package org.bouncycastle.math.p039ec;

import java.math.BigInteger;
import org.bouncycastle.math.raw.Nat;

/* renamed from: org.bouncycastle.math.ec.FixedPointCombMultiplier */
/* loaded from: classes5.dex */
public class FixedPointCombMultiplier extends AbstractECMultiplier {
    @Override // org.bouncycastle.math.p039ec.AbstractECMultiplier
    protected ECPoint multiplyPositive(ECPoint eCPoint, BigInteger bigInteger) {
        int combSize;
        ECCurve curve = eCPoint.getCurve();
        if (bigInteger.bitLength() <= FixedPointUtil.getCombSize(curve)) {
            FixedPointPreCompInfo precompute = FixedPointUtil.precompute(eCPoint);
            ECLookupTable lookupTable = precompute.getLookupTable();
            int width = precompute.getWidth();
            int r1 = ((combSize + width) - 1) / width;
            ECPoint infinity = curve.getInfinity();
            int r3 = width * r1;
            int[] fromBigInteger = Nat.fromBigInteger(r3, bigInteger);
            int r32 = r3 - 1;
            for (int r5 = 0; r5 < r1; r5++) {
                int r7 = 0;
                for (int r6 = r32 - r5; r6 >= 0; r6 -= r1) {
                    int r8 = fromBigInteger[r6 >>> 5] >>> (r6 & 31);
                    r7 = ((r7 ^ (r8 >>> 1)) << 1) ^ r8;
                }
                infinity = infinity.twicePlus(lookupTable.lookup(r7));
            }
            return infinity.add(precompute.getOffset());
        }
        throw new IllegalStateException("fixed-point comb doesn't support scalars larger than the curve order");
    }
}
