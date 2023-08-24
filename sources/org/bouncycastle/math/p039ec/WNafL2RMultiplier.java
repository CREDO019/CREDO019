package org.bouncycastle.math.p039ec;

import java.math.BigInteger;
import org.bouncycastle.util.Integers;

/* renamed from: org.bouncycastle.math.ec.WNafL2RMultiplier */
/* loaded from: classes5.dex */
public class WNafL2RMultiplier extends AbstractECMultiplier {
    @Override // org.bouncycastle.math.p039ec.AbstractECMultiplier
    protected ECPoint multiplyPositive(ECPoint eCPoint, BigInteger bigInteger) {
        ECPoint eCPoint2;
        WNafPreCompInfo precompute = WNafUtil.precompute(eCPoint, WNafUtil.getWindowSize(bigInteger.bitLength()), true);
        ECPoint[] preComp = precompute.getPreComp();
        ECPoint[] preCompNeg = precompute.getPreCompNeg();
        int width = precompute.getWidth();
        int[] generateCompactWindowNaf = WNafUtil.generateCompactWindowNaf(width, bigInteger);
        ECPoint infinity = eCPoint.getCurve().getInfinity();
        int length = generateCompactWindowNaf.length;
        if (length > 1) {
            length--;
            int r11 = generateCompactWindowNaf[length];
            int r6 = r11 >> 16;
            int r112 = r11 & 65535;
            int abs = Math.abs(r6);
            ECPoint[] eCPointArr = r6 < 0 ? preCompNeg : preComp;
            if ((abs << 2) < (1 << width)) {
                int numberOfLeadingZeros = 32 - Integers.numberOfLeadingZeros(abs);
                int r9 = width - numberOfLeadingZeros;
                eCPoint2 = eCPointArr[((1 << (width - 1)) - 1) >>> 1].add(eCPointArr[(((abs ^ (1 << (numberOfLeadingZeros - 1))) << r9) + 1) >>> 1]);
                r112 -= r9;
            } else {
                eCPoint2 = eCPointArr[abs >>> 1];
            }
            infinity = eCPoint2.timesPow2(r112);
        }
        while (length > 0) {
            length--;
            int r0 = generateCompactWindowNaf[length];
            int r62 = r0 >> 16;
            infinity = infinity.twicePlus((r62 < 0 ? preCompNeg : preComp)[Math.abs(r62) >>> 1]).timesPow2(r0 & 65535);
        }
        return infinity;
    }
}
