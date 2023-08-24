package org.bouncycastle.math.p039ec;

import java.math.BigInteger;

/* renamed from: org.bouncycastle.math.ec.FixedPointUtil */
/* loaded from: classes5.dex */
public class FixedPointUtil {
    public static final String PRECOMP_NAME = "bc_fixed_point";

    public static int getCombSize(ECCurve eCCurve) {
        BigInteger order = eCCurve.getOrder();
        return order == null ? eCCurve.getFieldSize() + 1 : order.bitLength();
    }

    public static FixedPointPreCompInfo getFixedPointPreCompInfo(PreCompInfo preCompInfo) {
        if (preCompInfo instanceof FixedPointPreCompInfo) {
            return (FixedPointPreCompInfo) preCompInfo;
        }
        return null;
    }

    public static FixedPointPreCompInfo precompute(final ECPoint eCPoint) {
        final ECCurve curve = eCPoint.getCurve();
        return (FixedPointPreCompInfo) curve.precompute(eCPoint, PRECOMP_NAME, new PreCompCallback() { // from class: org.bouncycastle.math.ec.FixedPointUtil.1
            private boolean checkExisting(FixedPointPreCompInfo fixedPointPreCompInfo, int r2) {
                return fixedPointPreCompInfo != null && checkTable(fixedPointPreCompInfo.getLookupTable(), r2);
            }

            private boolean checkTable(ECLookupTable eCLookupTable, int r2) {
                return eCLookupTable != null && eCLookupTable.getSize() >= r2;
            }

            @Override // org.bouncycastle.math.p039ec.PreCompCallback
            public PreCompInfo precompute(PreCompInfo preCompInfo) {
                FixedPointPreCompInfo fixedPointPreCompInfo = preCompInfo instanceof FixedPointPreCompInfo ? (FixedPointPreCompInfo) preCompInfo : null;
                int combSize = FixedPointUtil.getCombSize(ECCurve.this);
                int r1 = combSize > 250 ? 6 : 5;
                int r3 = 1 << r1;
                if (checkExisting(fixedPointPreCompInfo, r3)) {
                    return fixedPointPreCompInfo;
                }
                int r0 = ((combSize + r1) - 1) / r1;
                ECPoint[] eCPointArr = new ECPoint[r1 + 1];
                eCPointArr[0] = eCPoint;
                for (int r4 = 1; r4 < r1; r4++) {
                    eCPointArr[r4] = eCPointArr[r4 - 1].timesPow2(r0);
                }
                eCPointArr[r1] = eCPointArr[0].subtract(eCPointArr[1]);
                ECCurve.this.normalizeAll(eCPointArr);
                ECPoint[] eCPointArr2 = new ECPoint[r3];
                eCPointArr2[0] = eCPointArr[0];
                for (int r42 = r1 - 1; r42 >= 0; r42--) {
                    ECPoint eCPoint2 = eCPointArr[r42];
                    int r7 = 1 << r42;
                    for (int r8 = r7; r8 < r3; r8 += r7 << 1) {
                        eCPointArr2[r8] = eCPointArr2[r8 - r7].add(eCPoint2);
                    }
                }
                ECCurve.this.normalizeAll(eCPointArr2);
                FixedPointPreCompInfo fixedPointPreCompInfo2 = new FixedPointPreCompInfo();
                fixedPointPreCompInfo2.setLookupTable(ECCurve.this.createCacheSafeLookupTable(eCPointArr2, 0, r3));
                fixedPointPreCompInfo2.setOffset(eCPointArr[r1]);
                fixedPointPreCompInfo2.setWidth(r1);
                return fixedPointPreCompInfo2;
            }
        });
    }
}
