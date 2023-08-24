package org.bouncycastle.math.p039ec;

import java.math.BigInteger;

/* renamed from: org.bouncycastle.math.ec.WNafUtil */
/* loaded from: classes5.dex */
public abstract class WNafUtil {
    private static final int[] DEFAULT_WINDOW_SIZE_CUTOFFS = {13, 41, 121, 337, 897, 2305};
    private static final byte[] EMPTY_BYTES = new byte[0];
    private static final int[] EMPTY_INTS = new int[0];
    private static final ECPoint[] EMPTY_POINTS = new ECPoint[0];
    private static final int MAX_WIDTH = 16;
    public static final String PRECOMP_NAME = "bc_wnaf";

    public static void configureBasepoint(ECPoint eCPoint) {
        ECCurve curve = eCPoint.getCurve();
        if (curve == null) {
            return;
        }
        BigInteger order = curve.getOrder();
        final int min = Math.min(16, getWindowSize(order == null ? curve.getFieldSize() + 1 : order.bitLength()) + 3);
        curve.precompute(eCPoint, PRECOMP_NAME, new PreCompCallback() { // from class: org.bouncycastle.math.ec.WNafUtil.1
            @Override // org.bouncycastle.math.p039ec.PreCompCallback
            public PreCompInfo precompute(PreCompInfo preCompInfo) {
                WNafPreCompInfo wNafPreCompInfo = preCompInfo instanceof WNafPreCompInfo ? (WNafPreCompInfo) preCompInfo : null;
                if (wNafPreCompInfo != null && wNafPreCompInfo.getConfWidth() == min) {
                    wNafPreCompInfo.setPromotionCountdown(0);
                    return wNafPreCompInfo;
                }
                WNafPreCompInfo wNafPreCompInfo2 = new WNafPreCompInfo();
                wNafPreCompInfo2.setPromotionCountdown(0);
                wNafPreCompInfo2.setConfWidth(min);
                if (wNafPreCompInfo != null) {
                    wNafPreCompInfo2.setPreComp(wNafPreCompInfo.getPreComp());
                    wNafPreCompInfo2.setPreCompNeg(wNafPreCompInfo.getPreCompNeg());
                    wNafPreCompInfo2.setTwice(wNafPreCompInfo.getTwice());
                    wNafPreCompInfo2.setWidth(wNafPreCompInfo.getWidth());
                }
                return wNafPreCompInfo2;
            }
        });
    }

    public static int[] generateCompactNaf(BigInteger bigInteger) {
        if ((bigInteger.bitLength() >>> 16) == 0) {
            if (bigInteger.signum() == 0) {
                return EMPTY_INTS;
            }
            BigInteger add = bigInteger.shiftLeft(1).add(bigInteger);
            int bitLength = add.bitLength();
            int r3 = bitLength >> 1;
            int[] r4 = new int[r3];
            BigInteger xor = add.xor(bigInteger);
            int r2 = bitLength - 1;
            int r5 = 0;
            int r6 = 0;
            int r7 = 1;
            while (r7 < r2) {
                if (xor.testBit(r7)) {
                    r4[r5] = r6 | ((bigInteger.testBit(r7) ? -1 : 1) << 16);
                    r7++;
                    r5++;
                    r6 = 1;
                } else {
                    r6++;
                }
                r7++;
            }
            int r10 = r5 + 1;
            r4[r5] = 65536 | r6;
            return r3 > r10 ? trim(r4, r10) : r4;
        }
        throw new IllegalArgumentException("'k' must have bitlength < 2^16");
    }

    public static int[] generateCompactWindowNaf(int r13, BigInteger bigInteger) {
        if (r13 == 2) {
            return generateCompactNaf(bigInteger);
        }
        if (r13 < 2 || r13 > 16) {
            throw new IllegalArgumentException("'width' must be in the range [2, 16]");
        }
        if ((bigInteger.bitLength() >>> 16) == 0) {
            if (bigInteger.signum() == 0) {
                return EMPTY_INTS;
            }
            int bitLength = (bigInteger.bitLength() / r13) + 1;
            int[] r3 = new int[bitLength];
            int r4 = 1 << r13;
            int r5 = r4 - 1;
            int r6 = r4 >>> 1;
            int r8 = 0;
            int r9 = 0;
            boolean z = false;
            while (r8 <= bigInteger.bitLength()) {
                if (bigInteger.testBit(r8) == z) {
                    r8++;
                } else {
                    bigInteger = bigInteger.shiftRight(r8);
                    int intValue = bigInteger.intValue() & r5;
                    if (z) {
                        intValue++;
                    }
                    z = (intValue & r6) != 0;
                    if (z) {
                        intValue -= r4;
                    }
                    if (r9 > 0) {
                        r8--;
                    }
                    r3[r9] = r8 | (intValue << 16);
                    r8 = r13;
                    r9++;
                }
            }
            return bitLength > r9 ? trim(r3, r9) : r3;
        }
        throw new IllegalArgumentException("'k' must have bitlength < 2^16");
    }

    public static byte[] generateJSF(BigInteger bigInteger, BigInteger bigInteger2) {
        int max = Math.max(bigInteger.bitLength(), bigInteger2.bitLength()) + 1;
        byte[] bArr = new byte[max];
        int r3 = 0;
        int r4 = 0;
        int r5 = 0;
        int r6 = 0;
        while (true) {
            if ((r3 | r4) == 0 && bigInteger.bitLength() <= r5 && bigInteger2.bitLength() <= r5) {
                break;
            }
            int intValue = ((bigInteger.intValue() >>> r5) + r3) & 7;
            int intValue2 = ((bigInteger2.intValue() >>> r5) + r4) & 7;
            int r9 = intValue & 1;
            if (r9 != 0) {
                r9 -= intValue & 2;
                if (intValue + r9 == 4 && (intValue2 & 3) == 2) {
                    r9 = -r9;
                }
            }
            int r12 = intValue2 & 1;
            if (r12 != 0) {
                r12 -= intValue2 & 2;
                if (intValue2 + r12 == 4 && (intValue & 3) == 2) {
                    r12 = -r12;
                }
            }
            if ((r3 << 1) == r9 + 1) {
                r3 ^= 1;
            }
            if ((r4 << 1) == r12 + 1) {
                r4 ^= 1;
            }
            r5++;
            if (r5 == 30) {
                bigInteger = bigInteger.shiftRight(30);
                bigInteger2 = bigInteger2.shiftRight(30);
                r5 = 0;
            }
            bArr[r6] = (byte) ((r9 << 4) | (r12 & 15));
            r6++;
        }
        return max > r6 ? trim(bArr, r6) : bArr;
    }

    public static byte[] generateNaf(BigInteger bigInteger) {
        if (bigInteger.signum() == 0) {
            return EMPTY_BYTES;
        }
        BigInteger add = bigInteger.shiftLeft(1).add(bigInteger);
        int bitLength = add.bitLength() - 1;
        byte[] bArr = new byte[bitLength];
        BigInteger xor = add.xor(bigInteger);
        int r4 = 1;
        while (r4 < bitLength) {
            if (xor.testBit(r4)) {
                bArr[r4 - 1] = (byte) (bigInteger.testBit(r4) ? -1 : 1);
                r4++;
            }
            r4++;
        }
        bArr[bitLength - 1] = 1;
        return bArr;
    }

    public static byte[] generateWindowNaf(int r11, BigInteger bigInteger) {
        if (r11 == 2) {
            return generateNaf(bigInteger);
        }
        if (r11 < 2 || r11 > 8) {
            throw new IllegalArgumentException("'width' must be in the range [2, 8]");
        }
        if (bigInteger.signum() == 0) {
            return EMPTY_BYTES;
        }
        int bitLength = bigInteger.bitLength() + 1;
        byte[] bArr = new byte[bitLength];
        int r3 = 1 << r11;
        int r4 = r3 - 1;
        int r5 = r3 >>> 1;
        int r7 = 0;
        int r8 = 0;
        boolean z = false;
        while (r7 <= bigInteger.bitLength()) {
            if (bigInteger.testBit(r7) == z) {
                r7++;
            } else {
                bigInteger = bigInteger.shiftRight(r7);
                int intValue = bigInteger.intValue() & r4;
                if (z) {
                    intValue++;
                }
                z = (intValue & r5) != 0;
                if (z) {
                    intValue -= r3;
                }
                if (r8 > 0) {
                    r7--;
                }
                int r82 = r8 + r7;
                bArr[r82] = (byte) intValue;
                r8 = r82 + 1;
                r7 = r11;
            }
        }
        return bitLength > r8 ? trim(bArr, r8) : bArr;
    }

    public static int getNafWeight(BigInteger bigInteger) {
        if (bigInteger.signum() == 0) {
            return 0;
        }
        return bigInteger.shiftLeft(1).add(bigInteger).xor(bigInteger).bitCount();
    }

    public static WNafPreCompInfo getWNafPreCompInfo(ECPoint eCPoint) {
        return getWNafPreCompInfo(eCPoint.getCurve().getPreCompInfo(eCPoint, PRECOMP_NAME));
    }

    public static WNafPreCompInfo getWNafPreCompInfo(PreCompInfo preCompInfo) {
        if (preCompInfo instanceof WNafPreCompInfo) {
            return (WNafPreCompInfo) preCompInfo;
        }
        return null;
    }

    public static int getWindowSize(int r2) {
        return getWindowSize(r2, DEFAULT_WINDOW_SIZE_CUTOFFS, 16);
    }

    public static int getWindowSize(int r1, int r2) {
        return getWindowSize(r1, DEFAULT_WINDOW_SIZE_CUTOFFS, r2);
    }

    public static int getWindowSize(int r1, int[] r2) {
        return getWindowSize(r1, r2, 16);
    }

    public static int getWindowSize(int r2, int[] r3, int r4) {
        int r0 = 0;
        while (r0 < r3.length && r2 >= r3[r0]) {
            r0++;
        }
        return Math.max(2, Math.min(r4, r0 + 2));
    }

    public static WNafPreCompInfo precompute(final ECPoint eCPoint, final int r3, final boolean z) {
        final ECCurve curve = eCPoint.getCurve();
        return (WNafPreCompInfo) curve.precompute(eCPoint, PRECOMP_NAME, new PreCompCallback() { // from class: org.bouncycastle.math.ec.WNafUtil.2
            private boolean checkExisting(WNafPreCompInfo wNafPreCompInfo, int r4, int r5, boolean z2) {
                return wNafPreCompInfo != null && wNafPreCompInfo.getWidth() >= Math.max(wNafPreCompInfo.getConfWidth(), r4) && checkTable(wNafPreCompInfo.getPreComp(), r5) && (!z2 || checkTable(wNafPreCompInfo.getPreCompNeg(), r5));
            }

            private boolean checkTable(ECPoint[] eCPointArr, int r2) {
                return eCPointArr != null && eCPointArr.length >= r2;
            }

            /* JADX WARN: Removed duplicated region for block: B:47:0x00f2 A[LOOP:0: B:46:0x00f0->B:47:0x00f2, LOOP_END] */
            /* JADX WARN: Removed duplicated region for block: B:58:0x0117 A[LOOP:1: B:57:0x0115->B:58:0x0117, LOOP_END] */
            @Override // org.bouncycastle.math.p039ec.PreCompCallback
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public org.bouncycastle.math.p039ec.PreCompInfo precompute(org.bouncycastle.math.p039ec.PreCompInfo r14) {
                /*
                    Method dump skipped, instructions count: 303
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.math.p039ec.WNafUtil.C53412.precompute(org.bouncycastle.math.ec.PreCompInfo):org.bouncycastle.math.ec.PreCompInfo");
            }
        });
    }

    public static WNafPreCompInfo precomputeWithPointMap(ECPoint eCPoint, final ECPointMap eCPointMap, final WNafPreCompInfo wNafPreCompInfo, final boolean z) {
        return (WNafPreCompInfo) eCPoint.getCurve().precompute(eCPoint, PRECOMP_NAME, new PreCompCallback() { // from class: org.bouncycastle.math.ec.WNafUtil.3
            private boolean checkExisting(WNafPreCompInfo wNafPreCompInfo2, int r3, int r4, boolean z2) {
                return wNafPreCompInfo2 != null && wNafPreCompInfo2.getWidth() >= r3 && checkTable(wNafPreCompInfo2.getPreComp(), r4) && (!z2 || checkTable(wNafPreCompInfo2.getPreCompNeg(), r4));
            }

            private boolean checkTable(ECPoint[] eCPointArr, int r2) {
                return eCPointArr != null && eCPointArr.length >= r2;
            }

            @Override // org.bouncycastle.math.p039ec.PreCompCallback
            public PreCompInfo precompute(PreCompInfo preCompInfo) {
                WNafPreCompInfo wNafPreCompInfo2 = preCompInfo instanceof WNafPreCompInfo ? (WNafPreCompInfo) preCompInfo : null;
                int width = WNafPreCompInfo.this.getWidth();
                if (checkExisting(wNafPreCompInfo2, width, WNafPreCompInfo.this.getPreComp().length, z)) {
                    wNafPreCompInfo2.decrementPromotionCountdown();
                    return wNafPreCompInfo2;
                }
                WNafPreCompInfo wNafPreCompInfo3 = new WNafPreCompInfo();
                wNafPreCompInfo3.setPromotionCountdown(WNafPreCompInfo.this.getPromotionCountdown());
                ECPoint twice = WNafPreCompInfo.this.getTwice();
                if (twice != null) {
                    wNafPreCompInfo3.setTwice(eCPointMap.map(twice));
                }
                ECPoint[] preComp = WNafPreCompInfo.this.getPreComp();
                int length = preComp.length;
                ECPoint[] eCPointArr = new ECPoint[length];
                for (int r5 = 0; r5 < preComp.length; r5++) {
                    eCPointArr[r5] = eCPointMap.map(preComp[r5]);
                }
                wNafPreCompInfo3.setPreComp(eCPointArr);
                wNafPreCompInfo3.setWidth(width);
                if (z) {
                    ECPoint[] eCPointArr2 = new ECPoint[length];
                    for (int r4 = 0; r4 < length; r4++) {
                        eCPointArr2[r4] = eCPointArr[r4].negate();
                    }
                    wNafPreCompInfo3.setPreCompNeg(eCPointArr2);
                }
                return wNafPreCompInfo3;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ECPoint[] resizeTable(ECPoint[] eCPointArr, int r3) {
        ECPoint[] eCPointArr2 = new ECPoint[r3];
        System.arraycopy(eCPointArr, 0, eCPointArr2, 0, eCPointArr.length);
        return eCPointArr2;
    }

    private static byte[] trim(byte[] bArr, int r3) {
        byte[] bArr2 = new byte[r3];
        System.arraycopy(bArr, 0, bArr2, 0, r3);
        return bArr2;
    }

    private static int[] trim(int[] r2, int r3) {
        int[] r0 = new int[r3];
        System.arraycopy(r2, 0, r0, 0, r3);
        return r0;
    }
}
