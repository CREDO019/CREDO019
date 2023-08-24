package org.bouncycastle.math.p039ec.rfc8032;

import com.google.common.base.Ascii;
import java.security.SecureRandom;
import java.util.Objects;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
import org.bouncycastle.crypto.Xof;
import org.bouncycastle.crypto.digests.SHAKEDigest;
import org.bouncycastle.math.p039ec.rfc7748.X448;
import org.bouncycastle.math.p039ec.rfc7748.X448Field;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.util.Arrays;

/* renamed from: org.bouncycastle.math.ec.rfc8032.Ed448 */
/* loaded from: classes5.dex */
public abstract class Ed448 {
    private static final int COORD_INTS = 14;
    private static final int C_d = -39081;
    private static final int L4_0 = 43969588;
    private static final int L4_1 = 30366549;
    private static final int L4_2 = 163752818;
    private static final int L4_3 = 258169998;
    private static final int L4_4 = 96434764;
    private static final int L4_5 = 227822194;
    private static final int L4_6 = 149865618;
    private static final int L4_7 = 550336261;
    private static final int L_0 = 78101261;
    private static final int L_1 = 141809365;
    private static final int L_2 = 175155932;
    private static final int L_3 = 64542499;
    private static final int L_4 = 158326419;
    private static final int L_5 = 191173276;
    private static final int L_6 = 104575268;
    private static final int L_7 = 137584065;
    private static final long M26L = 67108863;
    private static final long M28L = 268435455;
    private static final long M32L = 4294967295L;
    private static final int POINT_BYTES = 57;
    private static final int PRECOMP_BLOCKS = 5;
    private static final int PRECOMP_MASK = 15;
    private static final int PRECOMP_POINTS = 16;
    private static final int PRECOMP_SPACING = 18;
    private static final int PRECOMP_TEETH = 5;
    public static final int PREHASH_SIZE = 64;
    public static final int PUBLIC_KEY_SIZE = 57;
    private static final int SCALAR_BYTES = 57;
    private static final int SCALAR_INTS = 14;
    public static final int SECRET_KEY_SIZE = 57;
    public static final int SIGNATURE_SIZE = 114;
    private static final int WNAF_WIDTH_BASE = 7;
    private static final byte[] DOM4_PREFIX = {83, 105, 103, 69, 100, 52, 52, 56};

    /* renamed from: P */
    private static final int[] f2373P = {-1, -1, -1, -1, -1, -1, -1, -2, -1, -1, -1, -1, -1, -1};

    /* renamed from: L */
    private static final int[] f2372L = {-1420278541, 595116690, -1916432555, 560775794, -1361693040, -1001465015, 2093622249, -1, -1, -1, -1, -1, -1, LockFreeTaskQueueCore.MAX_CAPACITY_MASK};
    private static final int[] B_x = {118276190, 40534716, 9670182, 135141552, 85017403, 259173222, 68333082, 171784774, 174973732, 15824510, 73756743, 57518561, 94773951, 248652241, 107736333, 82941708};
    private static final int[] B_y = {36764180, 8885695, 130592152, 20104429, 163904957, 30304195, 121295871, 5901357, 125344798, 171541512, 175338348, 209069246, 3626697, 38307682, 24032956, 110359655};
    private static final Object precompLock = new Object();
    private static PointExt[] precompBaseTable = null;
    private static int[] precompBase = null;

    /* renamed from: org.bouncycastle.math.ec.rfc8032.Ed448$Algorithm */
    /* loaded from: classes5.dex */
    public static final class Algorithm {
        public static final int Ed448 = 0;
        public static final int Ed448ph = 1;
    }

    /* renamed from: org.bouncycastle.math.ec.rfc8032.Ed448$F */
    /* loaded from: classes5.dex */
    private static class C5382F extends X448Field {
        private C5382F() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: org.bouncycastle.math.ec.rfc8032.Ed448$PointExt */
    /* loaded from: classes5.dex */
    public static class PointExt {

        /* renamed from: x */
        int[] f2374x;

        /* renamed from: y */
        int[] f2375y;

        /* renamed from: z */
        int[] f2376z;

        private PointExt() {
            this.f2374x = C5382F.create();
            this.f2375y = C5382F.create();
            this.f2376z = C5382F.create();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: org.bouncycastle.math.ec.rfc8032.Ed448$PointPrecomp */
    /* loaded from: classes5.dex */
    public static class PointPrecomp {

        /* renamed from: x */
        int[] f2377x;

        /* renamed from: y */
        int[] f2378y;

        private PointPrecomp() {
            this.f2377x = C5382F.create();
            this.f2378y = C5382F.create();
        }
    }

    private static byte[] calculateS(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        int[] r1 = new int[28];
        decodeScalar(bArr, 0, r1);
        int[] r3 = new int[14];
        decodeScalar(bArr2, 0, r3);
        int[] r5 = new int[14];
        decodeScalar(bArr3, 0, r5);
        Nat.mulAddTo(14, r3, r5, r1);
        byte[] bArr4 = new byte[114];
        for (int r2 = 0; r2 < 28; r2++) {
            encode32(r1[r2], bArr4, r2 * 4);
        }
        return reduceScalar(bArr4);
    }

    private static boolean checkContextVar(byte[] bArr) {
        return bArr != null && bArr.length < 256;
    }

    private static int checkPoint(int[] r3, int[] r4) {
        int[] create = C5382F.create();
        int[] create2 = C5382F.create();
        int[] create3 = C5382F.create();
        C5382F.sqr(r3, create2);
        C5382F.sqr(r4, create3);
        C5382F.mul(create2, create3, create);
        C5382F.add(create2, create3, create2);
        C5382F.mul(create, 39081, create);
        C5382F.subOne(create);
        C5382F.add(create, create2, create);
        C5382F.normalize(create);
        return C5382F.isZero(create);
    }

    private static int checkPoint(int[] r4, int[] r5, int[] r6) {
        int[] create = C5382F.create();
        int[] create2 = C5382F.create();
        int[] create3 = C5382F.create();
        int[] create4 = C5382F.create();
        C5382F.sqr(r4, create2);
        C5382F.sqr(r5, create3);
        C5382F.sqr(r6, create4);
        C5382F.mul(create2, create3, create);
        C5382F.add(create2, create3, create2);
        C5382F.mul(create2, create4, create2);
        C5382F.sqr(create4, create4);
        C5382F.mul(create, 39081, create);
        C5382F.sub(create, create4, create);
        C5382F.add(create, create2, create);
        C5382F.normalize(create);
        return C5382F.isZero(create);
    }

    private static boolean checkPointVar(byte[] bArr) {
        if ((bArr[56] & Byte.MAX_VALUE) != 0) {
            return false;
        }
        int[] r2 = new int[14];
        decode32(bArr, 0, r2, 0, 14);
        return !Nat.gte(14, r2, f2373P);
    }

    private static boolean checkScalarVar(byte[] bArr, int[] r3) {
        if (bArr[56] != 0) {
            return false;
        }
        decodeScalar(bArr, 0, r3);
        return !Nat.gte(14, r3, f2372L);
    }

    private static byte[] copy(byte[] bArr, int r3, int r4) {
        byte[] bArr2 = new byte[r4];
        System.arraycopy(bArr, r3, bArr2, 0, r4);
        return bArr2;
    }

    public static Xof createPrehash() {
        return createXof();
    }

    private static Xof createXof() {
        return new SHAKEDigest(256);
    }

    private static int decode16(byte[] bArr, int r2) {
        return ((bArr[r2 + 1] & 255) << 8) | (bArr[r2] & 255);
    }

    private static int decode24(byte[] bArr, int r3) {
        int r32 = r3 + 1;
        return ((bArr[r32 + 1] & 255) << 16) | (bArr[r3] & 255) | ((bArr[r32] & 255) << 8);
    }

    private static int decode32(byte[] bArr, int r3) {
        int r32 = r3 + 1;
        int r33 = r32 + 1;
        return (bArr[r33 + 1] << Ascii.CAN) | (bArr[r3] & 255) | ((bArr[r32] & 255) << 8) | ((bArr[r33] & 255) << 16);
    }

    private static void decode32(byte[] bArr, int r4, int[] r5, int r6, int r7) {
        for (int r0 = 0; r0 < r7; r0++) {
            r5[r6 + r0] = decode32(bArr, (r0 * 4) + r4);
        }
    }

    private static boolean decodePointVar(byte[] bArr, int r4, boolean z, PointExt pointExt) {
        byte[] copy = copy(bArr, r4, 57);
        if (checkPointVar(copy)) {
            int r1 = (copy[56] & 128) >>> 7;
            copy[56] = (byte) (copy[56] & Byte.MAX_VALUE);
            C5382F.decode(copy, 0, pointExt.f2375y);
            int[] create = C5382F.create();
            int[] create2 = C5382F.create();
            C5382F.sqr(pointExt.f2375y, create);
            C5382F.mul(create, 39081, create2);
            C5382F.negate(create, create);
            C5382F.addOne(create);
            C5382F.addOne(create2);
            if (C5382F.sqrtRatioVar(create, create2, pointExt.f2374x)) {
                C5382F.normalize(pointExt.f2374x);
                if (r1 == 1 && C5382F.isZeroVar(pointExt.f2374x)) {
                    return false;
                }
                if (z ^ (r1 != (pointExt.f2374x[0] & 1))) {
                    C5382F.negate(pointExt.f2374x, pointExt.f2374x);
                }
                pointExtendXY(pointExt);
                return true;
            }
            return false;
        }
        return false;
    }

    private static void decodeScalar(byte[] bArr, int r3, int[] r4) {
        decode32(bArr, r3, r4, 0, 14);
    }

    private static void dom4(Xof xof, byte b, byte[] bArr) {
        byte[] bArr2 = DOM4_PREFIX;
        int length = bArr2.length;
        int r2 = length + 2;
        int length2 = bArr.length + r2;
        byte[] bArr3 = new byte[length2];
        System.arraycopy(bArr2, 0, bArr3, 0, length);
        bArr3[length] = b;
        bArr3[length + 1] = (byte) bArr.length;
        System.arraycopy(bArr, 0, bArr3, r2, bArr.length);
        xof.update(bArr3, 0, length2);
    }

    private static void encode24(int r1, byte[] bArr, int r3) {
        bArr[r3] = (byte) r1;
        int r32 = r3 + 1;
        bArr[r32] = (byte) (r1 >>> 8);
        bArr[r32 + 1] = (byte) (r1 >>> 16);
    }

    private static void encode32(int r1, byte[] bArr, int r3) {
        bArr[r3] = (byte) r1;
        int r32 = r3 + 1;
        bArr[r32] = (byte) (r1 >>> 8);
        int r33 = r32 + 1;
        bArr[r33] = (byte) (r1 >>> 16);
        bArr[r33 + 1] = (byte) (r1 >>> 24);
    }

    private static void encode56(long j, byte[] bArr, int r4) {
        encode32((int) j, bArr, r4);
        encode24((int) (j >>> 32), bArr, r4 + 4);
    }

    private static int encodePoint(PointExt pointExt, byte[] bArr, int r5) {
        int[] create = C5382F.create();
        int[] create2 = C5382F.create();
        C5382F.inv(pointExt.f2376z, create2);
        C5382F.mul(pointExt.f2374x, create2, create);
        C5382F.mul(pointExt.f2375y, create2, create2);
        C5382F.normalize(create);
        C5382F.normalize(create2);
        int checkPoint = checkPoint(create, create2);
        C5382F.encode(create2, bArr, r5);
        bArr[(r5 + 57) - 1] = (byte) ((create[0] & 1) << 7);
        return checkPoint;
    }

    public static void generatePrivateKey(SecureRandom secureRandom, byte[] bArr) {
        secureRandom.nextBytes(bArr);
    }

    public static void generatePublicKey(byte[] bArr, int r5, byte[] bArr2, int r7) {
        Xof createXof = createXof();
        byte[] bArr3 = new byte[114];
        createXof.update(bArr, r5, 57);
        createXof.doFinal(bArr3, 0, 114);
        byte[] bArr4 = new byte[57];
        pruneScalar(bArr3, 0, bArr4);
        scalarMultBaseEncoded(bArr4, bArr2, r7);
    }

    private static int getWindow4(int[] r1, int r2) {
        return (r1[r2 >>> 3] >>> ((r2 & 7) << 2)) & 15;
    }

    private static byte[] getWnafVar(int[] r10, int r11) {
        int[] r1 = new int[28];
        int r2 = 0;
        int r3 = 14;
        int r4 = 28;
        int r5 = 0;
        while (true) {
            r3--;
            if (r3 < 0) {
                break;
            }
            int r7 = r10[r3];
            int r42 = r4 - 1;
            r1[r42] = (r5 << 16) | (r7 >>> 16);
            r4 = r42 - 1;
            r1[r4] = r7;
            r5 = r7;
        }
        byte[] bArr = new byte[447];
        int r32 = 32 - r11;
        int r43 = 0;
        int r52 = 0;
        while (r2 < 28) {
            int r72 = r1[r2];
            while (r43 < 16) {
                int r8 = r72 >>> r43;
                if ((r8 & 1) == r52) {
                    r43++;
                } else {
                    int r53 = (r8 | 1) << r32;
                    bArr[(r2 << 4) + r43] = (byte) (r53 >> r32);
                    r43 += r11;
                    r52 = r53 >>> 31;
                }
            }
            r2++;
            r43 -= 16;
        }
        return bArr;
    }

    private static void implSign(Xof xof, byte[] bArr, byte[] bArr2, byte[] bArr3, int r8, byte[] bArr4, byte b, byte[] bArr5, int r12, int r13, byte[] bArr6, int r15) {
        dom4(xof, b, bArr4);
        xof.update(bArr, 57, 57);
        xof.update(bArr5, r12, r13);
        xof.doFinal(bArr, 0, bArr.length);
        byte[] reduceScalar = reduceScalar(bArr);
        byte[] bArr7 = new byte[57];
        scalarMultBaseEncoded(reduceScalar, bArr7, 0);
        dom4(xof, b, bArr4);
        xof.update(bArr7, 0, 57);
        xof.update(bArr3, r8, 57);
        xof.update(bArr5, r12, r13);
        xof.doFinal(bArr, 0, bArr.length);
        byte[] calculateS = calculateS(reduceScalar, reduceScalar(bArr), bArr2);
        System.arraycopy(bArr7, 0, bArr6, r15, 57);
        System.arraycopy(calculateS, 0, bArr6, r15 + 57, 57);
    }

    private static void implSign(byte[] bArr, int r14, byte[] bArr2, byte b, byte[] bArr3, int r18, int r19, byte[] bArr4, int r21) {
        if (!checkContextVar(bArr2)) {
            throw new IllegalArgumentException("ctx");
        }
        Xof createXof = createXof();
        byte[] bArr5 = new byte[114];
        createXof.update(bArr, r14, 57);
        createXof.doFinal(bArr5, 0, 114);
        byte[] bArr6 = new byte[57];
        pruneScalar(bArr5, 0, bArr6);
        byte[] bArr7 = new byte[57];
        scalarMultBaseEncoded(bArr6, bArr7, 0);
        implSign(createXof, bArr5, bArr6, bArr7, 0, bArr2, b, bArr3, r18, r19, bArr4, r21);
    }

    private static void implSign(byte[] bArr, int r14, byte[] bArr2, int r16, byte[] bArr3, byte b, byte[] bArr4, int r20, int r21, byte[] bArr5, int r23) {
        if (!checkContextVar(bArr3)) {
            throw new IllegalArgumentException("ctx");
        }
        Xof createXof = createXof();
        byte[] bArr6 = new byte[114];
        createXof.update(bArr, r14, 57);
        createXof.doFinal(bArr6, 0, 114);
        byte[] bArr7 = new byte[57];
        pruneScalar(bArr6, 0, bArr7);
        implSign(createXof, bArr6, bArr7, bArr2, r16, bArr3, b, bArr4, r20, r21, bArr5, r23);
    }

    private static boolean implVerify(byte[] bArr, int r16, byte[] bArr2, int r18, byte[] bArr3, byte b, byte[] bArr4, int r22, int r23) {
        if (checkContextVar(bArr3)) {
            byte[] copy = copy(bArr, r16, 57);
            byte[] copy2 = copy(bArr, r16 + 57, 57);
            if (checkPointVar(copy)) {
                int[] r7 = new int[14];
                if (checkScalarVar(copy2, r7)) {
                    PointExt pointExt = new PointExt();
                    if (decodePointVar(bArr2, r18, true, pointExt)) {
                        Xof createXof = createXof();
                        byte[] bArr5 = new byte[114];
                        dom4(createXof, b, bArr3);
                        createXof.update(copy, 0, 57);
                        createXof.update(bArr2, r18, 57);
                        createXof.update(bArr4, r22, r23);
                        createXof.doFinal(bArr5, 0, 114);
                        int[] r1 = new int[14];
                        decodeScalar(reduceScalar(bArr5), 0, r1);
                        PointExt pointExt2 = new PointExt();
                        scalarMultStrausVar(r7, r1, pointExt, pointExt2);
                        byte[] bArr6 = new byte[57];
                        return encodePoint(pointExt2, bArr6, 0) != 0 && Arrays.areEqual(bArr6, copy);
                    }
                    return false;
                }
                return false;
            }
            return false;
        }
        throw new IllegalArgumentException("ctx");
    }

    private static boolean isNeutralElementVar(int[] r0, int[] r1, int[] r2) {
        return C5382F.isZeroVar(r0) && C5382F.areEqualVar(r1, r2);
    }

    private static void pointAdd(PointExt pointExt, PointExt pointExt2) {
        int[] create = C5382F.create();
        int[] create2 = C5382F.create();
        int[] create3 = C5382F.create();
        int[] create4 = C5382F.create();
        int[] create5 = C5382F.create();
        int[] create6 = C5382F.create();
        int[] create7 = C5382F.create();
        int[] create8 = C5382F.create();
        C5382F.mul(pointExt.f2376z, pointExt2.f2376z, create);
        C5382F.sqr(create, create2);
        C5382F.mul(pointExt.f2374x, pointExt2.f2374x, create3);
        C5382F.mul(pointExt.f2375y, pointExt2.f2375y, create4);
        C5382F.mul(create3, create4, create5);
        C5382F.mul(create5, 39081, create5);
        C5382F.add(create2, create5, create6);
        C5382F.sub(create2, create5, create7);
        C5382F.add(pointExt.f2374x, pointExt.f2375y, create2);
        C5382F.add(pointExt2.f2374x, pointExt2.f2375y, create5);
        C5382F.mul(create2, create5, create8);
        C5382F.add(create4, create3, create2);
        C5382F.sub(create4, create3, create5);
        C5382F.carry(create2);
        C5382F.sub(create8, create2, create8);
        C5382F.mul(create8, create, create8);
        C5382F.mul(create5, create, create5);
        C5382F.mul(create6, create8, pointExt2.f2374x);
        C5382F.mul(create5, create7, pointExt2.f2375y);
        C5382F.mul(create6, create7, pointExt2.f2376z);
    }

    private static void pointAddPrecomp(PointPrecomp pointPrecomp, PointExt pointExt) {
        int[] create = C5382F.create();
        int[] create2 = C5382F.create();
        int[] create3 = C5382F.create();
        int[] create4 = C5382F.create();
        int[] create5 = C5382F.create();
        int[] create6 = C5382F.create();
        int[] create7 = C5382F.create();
        C5382F.sqr(pointExt.f2376z, create);
        C5382F.mul(pointPrecomp.f2377x, pointExt.f2374x, create2);
        C5382F.mul(pointPrecomp.f2378y, pointExt.f2375y, create3);
        C5382F.mul(create2, create3, create4);
        C5382F.mul(create4, 39081, create4);
        C5382F.add(create, create4, create5);
        C5382F.sub(create, create4, create6);
        C5382F.add(pointPrecomp.f2377x, pointPrecomp.f2378y, create);
        C5382F.add(pointExt.f2374x, pointExt.f2375y, create4);
        C5382F.mul(create, create4, create7);
        C5382F.add(create3, create2, create);
        C5382F.sub(create3, create2, create4);
        C5382F.carry(create);
        C5382F.sub(create7, create, create7);
        C5382F.mul(create7, pointExt.f2376z, create7);
        C5382F.mul(create4, pointExt.f2376z, create4);
        C5382F.mul(create5, create7, pointExt.f2374x);
        C5382F.mul(create4, create6, pointExt.f2375y);
        C5382F.mul(create5, create6, pointExt.f2376z);
    }

    private static void pointAddVar(boolean z, PointExt pointExt, PointExt pointExt2) {
        int[] r9;
        int[] r10;
        int[] r13;
        int[] r8;
        int[] create = C5382F.create();
        int[] create2 = C5382F.create();
        int[] create3 = C5382F.create();
        int[] create4 = C5382F.create();
        int[] create5 = C5382F.create();
        int[] create6 = C5382F.create();
        int[] create7 = C5382F.create();
        int[] create8 = C5382F.create();
        if (z) {
            C5382F.sub(pointExt.f2375y, pointExt.f2374x, create8);
            r10 = create2;
            r9 = create5;
            r8 = create6;
            r13 = create7;
        } else {
            C5382F.add(pointExt.f2375y, pointExt.f2374x, create8);
            r9 = create2;
            r10 = create5;
            r13 = create6;
            r8 = create7;
        }
        C5382F.mul(pointExt.f2376z, pointExt2.f2376z, create);
        C5382F.sqr(create, create2);
        C5382F.mul(pointExt.f2374x, pointExt2.f2374x, create3);
        C5382F.mul(pointExt.f2375y, pointExt2.f2375y, create4);
        C5382F.mul(create3, create4, create5);
        C5382F.mul(create5, 39081, create5);
        C5382F.add(create2, create5, r13);
        C5382F.sub(create2, create5, r8);
        C5382F.add(pointExt2.f2374x, pointExt2.f2375y, create5);
        C5382F.mul(create8, create5, create8);
        C5382F.add(create4, create3, r9);
        C5382F.sub(create4, create3, r10);
        C5382F.carry(r9);
        C5382F.sub(create8, create2, create8);
        C5382F.mul(create8, create, create8);
        C5382F.mul(create5, create, create5);
        C5382F.mul(create6, create8, pointExt2.f2374x);
        C5382F.mul(create5, create7, pointExt2.f2375y);
        C5382F.mul(create6, create7, pointExt2.f2376z);
    }

    private static PointExt pointCopy(PointExt pointExt) {
        PointExt pointExt2 = new PointExt();
        pointCopy(pointExt, pointExt2);
        return pointExt2;
    }

    private static void pointCopy(PointExt pointExt, PointExt pointExt2) {
        C5382F.copy(pointExt.f2374x, 0, pointExt2.f2374x, 0);
        C5382F.copy(pointExt.f2375y, 0, pointExt2.f2375y, 0);
        C5382F.copy(pointExt.f2376z, 0, pointExt2.f2376z, 0);
    }

    private static void pointDouble(PointExt pointExt) {
        int[] create = C5382F.create();
        int[] create2 = C5382F.create();
        int[] create3 = C5382F.create();
        int[] create4 = C5382F.create();
        int[] create5 = C5382F.create();
        int[] create6 = C5382F.create();
        C5382F.add(pointExt.f2374x, pointExt.f2375y, create);
        C5382F.sqr(create, create);
        C5382F.sqr(pointExt.f2374x, create2);
        C5382F.sqr(pointExt.f2375y, create3);
        C5382F.add(create2, create3, create4);
        C5382F.carry(create4);
        C5382F.sqr(pointExt.f2376z, create5);
        C5382F.add(create5, create5, create5);
        C5382F.carry(create5);
        C5382F.sub(create4, create5, create6);
        C5382F.sub(create, create4, create);
        C5382F.sub(create2, create3, create2);
        C5382F.mul(create, create6, pointExt.f2374x);
        C5382F.mul(create4, create2, pointExt.f2375y);
        C5382F.mul(create4, create6, pointExt.f2376z);
    }

    private static void pointExtendXY(PointExt pointExt) {
        C5382F.one(pointExt.f2376z);
    }

    private static void pointLookup(int r6, int r7, PointPrecomp pointPrecomp) {
        int r62 = r6 * 16 * 2 * 16;
        for (int r2 = 0; r2 < 16; r2++) {
            int r3 = ((r2 ^ r7) - 1) >> 31;
            C5382F.cmov(r3, precompBase, r62, pointPrecomp.f2377x, 0);
            int r63 = r62 + 16;
            C5382F.cmov(r3, precompBase, r63, pointPrecomp.f2378y, 0);
            r62 = r63 + 16;
        }
    }

    private static void pointLookup(int[] r5, int r6, int[] r7, PointExt pointExt) {
        int window4 = getWindow4(r5, r6);
        int r62 = (window4 >>> 3) ^ 1;
        int r52 = (window4 ^ (-r62)) & 7;
        int r2 = 0;
        for (int r1 = 0; r1 < 8; r1++) {
            int r3 = ((r1 ^ r52) - 1) >> 31;
            C5382F.cmov(r3, r7, r2, pointExt.f2374x, 0);
            int r22 = r2 + 16;
            C5382F.cmov(r3, r7, r22, pointExt.f2375y, 0);
            int r23 = r22 + 16;
            C5382F.cmov(r3, r7, r23, pointExt.f2376z, 0);
            r2 = r23 + 16;
        }
        C5382F.cnegate(r62, pointExt.f2374x);
    }

    private static int[] pointPrecompute(PointExt pointExt, int r7) {
        PointExt pointCopy = pointCopy(pointExt);
        PointExt pointCopy2 = pointCopy(pointCopy);
        pointDouble(pointCopy2);
        int[] createTable = C5382F.createTable(r7 * 3);
        int r3 = 0;
        int r4 = 0;
        while (true) {
            C5382F.copy(pointCopy.f2374x, 0, createTable, r3);
            int r32 = r3 + 16;
            C5382F.copy(pointCopy.f2375y, 0, createTable, r32);
            int r33 = r32 + 16;
            C5382F.copy(pointCopy.f2376z, 0, createTable, r33);
            r3 = r33 + 16;
            r4++;
            if (r4 == r7) {
                return createTable;
            }
            pointAdd(pointCopy2, pointCopy);
        }
    }

    private static PointExt[] pointPrecomputeVar(PointExt pointExt, int r5) {
        PointExt pointCopy = pointCopy(pointExt);
        pointDouble(pointCopy);
        PointExt[] pointExtArr = new PointExt[r5];
        pointExtArr[0] = pointCopy(pointExt);
        for (int r4 = 1; r4 < r5; r4++) {
            pointExtArr[r4] = pointCopy(pointExtArr[r4 - 1]);
            pointAddVar(false, pointCopy, pointExtArr[r4]);
        }
        return pointExtArr;
    }

    private static void pointSetNeutral(PointExt pointExt) {
        C5382F.zero(pointExt.f2374x);
        C5382F.one(pointExt.f2375y);
        C5382F.one(pointExt.f2376z);
    }

    public static void precompute() {
        synchronized (precompLock) {
            if (precompBase != null) {
                return;
            }
            PointExt pointExt = new PointExt();
            C5382F.copy(B_x, 0, pointExt.f2374x, 0);
            C5382F.copy(B_y, 0, pointExt.f2375y, 0);
            pointExtendXY(pointExt);
            precompBaseTable = pointPrecomputeVar(pointExt, 32);
            precompBase = C5382F.createTable(160);
            int r4 = 0;
            for (int r3 = 0; r3 < 5; r3++) {
                PointExt[] pointExtArr = new PointExt[5];
                PointExt pointExt2 = new PointExt();
                pointSetNeutral(pointExt2);
                int r9 = 0;
                while (true) {
                    if (r9 >= 5) {
                        break;
                    }
                    pointAddVar(true, pointExt, pointExt2);
                    pointDouble(pointExt);
                    pointExtArr[r9] = pointCopy(pointExt);
                    if (r3 + r9 != 8) {
                        for (int r10 = 1; r10 < 18; r10++) {
                            pointDouble(pointExt);
                        }
                    }
                    r9++;
                }
                PointExt[] pointExtArr2 = new PointExt[16];
                pointExtArr2[0] = pointExt2;
                int r11 = 1;
                for (int r8 = 0; r8 < 4; r8++) {
                    int r12 = 1 << r8;
                    int r13 = 0;
                    while (r13 < r12) {
                        pointExtArr2[r11] = pointCopy(pointExtArr2[r11 - r12]);
                        pointAddVar(false, pointExtArr[r8], pointExtArr2[r11]);
                        r13++;
                        r11++;
                    }
                }
                int[] createTable = C5382F.createTable(16);
                int[] create = C5382F.create();
                C5382F.copy(pointExtArr2[0].f2376z, 0, create, 0);
                C5382F.copy(create, 0, createTable, 0);
                int r112 = 0;
                while (true) {
                    r112++;
                    if (r112 >= 16) {
                        break;
                    }
                    C5382F.mul(create, pointExtArr2[r112].f2376z, create);
                    C5382F.copy(create, 0, createTable, r112 * 16);
                }
                C5382F.invVar(create, create);
                int r113 = r112 - 1;
                int[] create2 = C5382F.create();
                while (r113 > 0) {
                    int r122 = r113 - 1;
                    C5382F.copy(createTable, r122 * 16, create2, 0);
                    C5382F.mul(create2, create, create2);
                    C5382F.copy(create2, 0, createTable, r113 * 16);
                    C5382F.mul(create, pointExtArr2[r113].f2376z, create);
                    r113 = r122;
                }
                C5382F.copy(create, 0, createTable, 0);
                for (int r82 = 0; r82 < 16; r82++) {
                    PointExt pointExt3 = pointExtArr2[r82];
                    C5382F.copy(createTable, r82 * 16, pointExt3.f2376z, 0);
                    C5382F.mul(pointExt3.f2374x, pointExt3.f2376z, pointExt3.f2374x);
                    C5382F.mul(pointExt3.f2375y, pointExt3.f2376z, pointExt3.f2375y);
                    C5382F.copy(pointExt3.f2374x, 0, precompBase, r4);
                    int r42 = r4 + 16;
                    C5382F.copy(pointExt3.f2375y, 0, precompBase, r42);
                    r4 = r42 + 16;
                }
            }
        }
    }

    private static void pruneScalar(byte[] bArr, int r3, byte[] bArr2) {
        System.arraycopy(bArr, r3, bArr2, 0, 56);
        bArr2[0] = (byte) (bArr2[0] & 252);
        bArr2[55] = (byte) (bArr2[55] | 128);
        bArr2[56] = 0;
    }

    private static byte[] reduceScalar(byte[] bArr) {
        long decode24 = (decode24(bArr, 4) << 4) & 4294967295L;
        long decode32 = decode32(bArr, 7) & 4294967295L;
        long decode242 = (decode24(bArr, 11) << 4) & 4294967295L;
        long decode322 = decode32(bArr, 14) & 4294967295L;
        long decode243 = (decode24(bArr, 18) << 4) & 4294967295L;
        long decode323 = decode32(bArr, 21) & 4294967295L;
        long decode324 = decode32(bArr, 28) & 4294967295L;
        long decode244 = (decode24(bArr, 32) << 4) & 4294967295L;
        long decode325 = decode32(bArr, 35) & 4294967295L;
        long decode245 = (decode24(bArr, 39) << 4) & 4294967295L;
        long decode326 = decode32(bArr, 42) & 4294967295L;
        long decode246 = (decode24(bArr, 46) << 4) & 4294967295L;
        long decode327 = decode32(bArr, 49) & 4294967295L;
        long decode247 = (decode24(bArr, 53) << 4) & 4294967295L;
        long decode248 = (decode24(bArr, 74) << 4) & 4294967295L;
        long decode328 = decode32(bArr, 77) & 4294967295L;
        long decode249 = (decode24(bArr, 81) << 4) & 4294967295L;
        long decode329 = decode32(bArr, 84) & 4294967295L;
        long decode2410 = (decode24(bArr, 88) << 4) & 4294967295L;
        long decode3210 = decode32(bArr, 91) & 4294967295L;
        long decode2411 = (decode24(bArr, 95) << 4) & 4294967295L;
        long decode3211 = decode32(bArr, 98) & 4294967295L;
        long decode2412 = (decode24(bArr, 102) << 4) & 4294967295L;
        long decode3212 = decode32(bArr, 105) & 4294967295L;
        long decode2413 = (decode24(bArr, 109) << 4) & 4294967295L;
        long decode16 = decode16(bArr, 112) & 4294967295L;
        long j = decode2413 + (decode3212 >>> 28);
        long j2 = decode3212 & M28L;
        long decode3213 = (decode32(bArr, 56) & 4294967295L) + (decode16 * 43969588) + (j * 30366549);
        long decode2414 = ((decode24(bArr, 60) << 4) & 4294967295L) + (decode16 * 30366549) + (j * 163752818);
        long decode3214 = (decode32(bArr, 63) & 4294967295L) + (decode16 * 163752818) + (j * 258169998);
        long decode2415 = ((decode24(bArr, 67) << 4) & 4294967295L) + (decode16 * 258169998) + (j * 96434764);
        long j3 = decode328 + (decode16 * 149865618) + (j * 550336261);
        long j4 = decode327 + (j2 * 43969588);
        long j5 = decode2412 + (decode3211 >>> 28);
        long j6 = decode3211 & M28L;
        long decode3215 = (decode32(bArr, 70) & 4294967295L) + (decode16 * 96434764) + (j * 227822194) + (j2 * 149865618) + (j5 * 550336261);
        long j7 = decode326 + (j6 * 43969588);
        long j8 = decode2411 + (decode3210 >>> 28);
        long j9 = decode3210 & M28L;
        long j10 = decode3214 + (j2 * 96434764) + (j5 * 227822194) + (j6 * 149865618) + (j8 * 550336261);
        long j11 = decode325 + (j9 * 43969588);
        long j12 = decode2414 + (j2 * 258169998) + (j5 * 96434764) + (j6 * 227822194) + (j8 * 149865618) + (j9 * 550336261);
        long j13 = decode2410 + (decode329 >>> 28);
        long j14 = decode329 & M28L;
        long j15 = decode248 + (decode16 * 227822194) + (j * 149865618) + (j2 * 550336261) + (decode3215 >>> 28);
        long j16 = decode3215 & M28L;
        long j17 = j3 + (j15 >>> 28);
        long j18 = j15 & M28L;
        long j19 = decode249 + (decode16 * 550336261) + (j17 >>> 28);
        long j20 = j17 & M28L;
        long j21 = j14 + (j19 >>> 28);
        long j22 = j19 & M28L;
        long decode2416 = ((decode24(bArr, 25) << 4) & 4294967295L) + (j22 * 43969588);
        long j23 = decode324 + (j21 * 43969588) + (j22 * 30366549);
        long j24 = decode244 + (j13 * 43969588) + (j21 * 30366549) + (j22 * 163752818);
        long j25 = j11 + (j13 * 30366549) + (j21 * 163752818) + (j22 * 258169998);
        long j26 = decode245 + (j8 * 43969588) + (j9 * 30366549) + (j13 * 163752818) + (j21 * 258169998) + (j22 * 96434764);
        long j27 = j7 + (j8 * 30366549) + (j9 * 163752818) + (j13 * 258169998) + (j21 * 96434764) + (j22 * 227822194);
        long j28 = j4 + (j5 * 30366549) + (j6 * 163752818) + (j8 * 258169998) + (j9 * 96434764) + (j13 * 227822194) + (j21 * 149865618) + (j22 * 550336261);
        long j29 = decode323 + (j20 * 43969588);
        long j30 = j10 + (j12 >>> 28);
        long j31 = j12 & M28L;
        long j32 = decode2415 + (j2 * 227822194) + (j5 * 149865618) + (j6 * 550336261) + (j30 >>> 28);
        long j33 = j30 & M28L;
        long j34 = j16 + (j32 >>> 28);
        long j35 = j32 & M28L;
        long j36 = j18 + (j34 >>> 28);
        long j37 = j34 & M28L;
        long j38 = j26 + (j20 * 227822194) + (j36 * 149865618) + (j37 * 550336261);
        long j39 = decode322 + (j37 * 43969588) + (j35 * 30366549);
        long j40 = decode243 + (j36 * 43969588) + (j37 * 30366549) + (j35 * 163752818);
        long j41 = j29 + (j36 * 30366549) + (j37 * 163752818) + (j35 * 258169998);
        long j42 = decode2416 + (j20 * 30366549) + (j36 * 163752818) + (j37 * 258169998) + (j35 * 96434764);
        long j43 = j23 + (j20 * 163752818) + (j36 * 258169998) + (j37 * 96434764) + (j35 * 227822194);
        long j44 = j24 + (j20 * 258169998) + (j36 * 96434764) + (j37 * 227822194) + (j35 * 149865618);
        long j45 = j25 + (j20 * 96434764) + (j36 * 227822194) + (j37 * 149865618) + (j35 * 550336261);
        long j46 = decode247 + (j * 43969588) + (j2 * 30366549) + (j5 * 163752818) + (j6 * 258169998) + (j8 * 96434764) + (j9 * 227822194) + (j13 * 149865618) + (j21 * 550336261) + (j28 >>> 28);
        long j47 = j28 & M28L;
        long j48 = decode3213 + (j2 * 163752818) + (j5 * 258169998) + (j6 * 96434764) + (j8 * 227822194) + (j9 * 149865618) + (j13 * 550336261) + (j46 >>> 28);
        long j49 = j46 & M28L;
        long j50 = j31 + (j48 >>> 28);
        long j51 = j48 & M28L;
        long j52 = j33 + (j50 >>> 28);
        long j53 = j50 & M28L;
        long j54 = decode32 + (j52 * 43969588);
        long j55 = decode242 + (j35 * 43969588) + (j52 * 30366549);
        long j56 = j39 + (j52 * 163752818);
        long j57 = j40 + (j52 * 258169998);
        long j58 = j41 + (j52 * 96434764);
        long j59 = j42 + (j52 * 227822194);
        long j60 = j44 + (j52 * 550336261);
        long j61 = j49 & M26L;
        long j62 = (j51 * 4) + (j49 >>> 26) + 1;
        long decode3216 = (decode32(bArr, 0) & 4294967295L) + (78101261 * j62);
        long j63 = j54 + (30366549 * j53) + (175155932 * j62);
        long j64 = j55 + (163752818 * j53) + (64542499 * j62);
        long j65 = j56 + (258169998 * j53) + (158326419 * j62);
        long j66 = j57 + (96434764 * j53) + (191173276 * j62);
        long j67 = j59 + (149865618 * j53) + (j62 * 137584065);
        long j68 = decode24 + (43969588 * j53) + (141809365 * j62) + (decode3216 >>> 28);
        long j69 = decode3216 & M28L;
        long j70 = j63 + (j68 >>> 28);
        long j71 = j68 & M28L;
        long j72 = j64 + (j70 >>> 28);
        long j73 = j70 & M28L;
        long j74 = j65 + (j72 >>> 28);
        long j75 = j72 & M28L;
        long j76 = j66 + (j74 >>> 28);
        long j77 = j74 & M28L;
        long j78 = j58 + (227822194 * j53) + (104575268 * j62) + (j76 >>> 28);
        long j79 = j76 & M28L;
        long j80 = j67 + (j78 >>> 28);
        long j81 = j78 & M28L;
        long j82 = j43 + (j52 * 149865618) + (j53 * 550336261) + (j80 >>> 28);
        long j83 = j80 & M28L;
        long j84 = j60 + (j82 >>> 28);
        long j85 = j82 & M28L;
        long j86 = j45 + (j84 >>> 28);
        long j87 = j84 & M28L;
        long j88 = j38 + (j86 >>> 28);
        long j89 = j86 & M28L;
        long j90 = j27 + (j20 * 149865618) + (j36 * 550336261) + (j88 >>> 28);
        long j91 = j88 & M28L;
        long j92 = decode246 + (j5 * 43969588) + (j6 * 30366549) + (j8 * 163752818) + (j9 * 258169998) + (j13 * 96434764) + (j21 * 227822194) + (j22 * 149865618) + (j20 * 550336261) + (j90 >>> 28);
        long j93 = j90 & M28L;
        long j94 = j47 + (j92 >>> 28);
        long j95 = j92 & M28L;
        long j96 = j61 + (j94 >>> 28);
        long j97 = j94 & M28L;
        long j98 = j96 & M26L;
        long j99 = (j96 >>> 26) - 1;
        long j100 = j69 - (j99 & 78101261);
        long j101 = (j71 - (j99 & 141809365)) + (j100 >> 28);
        long j102 = j100 & M28L;
        long j103 = (j73 - (j99 & 175155932)) + (j101 >> 28);
        long j104 = j101 & M28L;
        long j105 = (j75 - (j99 & 64542499)) + (j103 >> 28);
        long j106 = j103 & M28L;
        long j107 = (j77 - (j99 & 158326419)) + (j105 >> 28);
        long j108 = j105 & M28L;
        long j109 = (j79 - (j99 & 191173276)) + (j107 >> 28);
        long j110 = j107 & M28L;
        long j111 = (j81 - (j99 & 104575268)) + (j109 >> 28);
        long j112 = j109 & M28L;
        long j113 = (j83 - (j99 & 137584065)) + (j111 >> 28);
        long j114 = j111 & M28L;
        long j115 = j85 + (j113 >> 28);
        long j116 = j113 & M28L;
        long j117 = j87 + (j115 >> 28);
        long j118 = j115 & M28L;
        long j119 = j89 + (j117 >> 28);
        long j120 = j117 & M28L;
        long j121 = j91 + (j119 >> 28);
        long j122 = j119 & M28L;
        long j123 = j93 + (j121 >> 28);
        long j124 = j121 & M28L;
        long j125 = j95 + (j123 >> 28);
        long j126 = j123 & M28L;
        long j127 = j97 + (j125 >> 28);
        long j128 = j125 & M28L;
        long j129 = j127 & M28L;
        byte[] bArr2 = new byte[57];
        encode56((j104 << 28) | j102, bArr2, 0);
        encode56((j108 << 28) | j106, bArr2, 7);
        encode56(j110 | (j112 << 28), bArr2, 14);
        encode56(j114 | (j116 << 28), bArr2, 21);
        encode56(j118 | (j120 << 28), bArr2, 28);
        encode56(j122 | (j124 << 28), bArr2, 35);
        encode56(j126 | (j128 << 28), bArr2, 42);
        encode56(((j98 + (j127 >> 28)) << 28) | j129, bArr2, 49);
        return bArr2;
    }

    private static void scalarMult(byte[] bArr, PointExt pointExt, PointExt pointExt2) {
        int[] r1 = new int[14];
        decodeScalar(bArr, 0, r1);
        Nat.shiftDownBits(14, r1, 2, 0);
        Nat.cadd(14, (~r1[0]) & 1, r1, f2372L, r1);
        Nat.shiftDownBit(14, r1, 1);
        int[] pointPrecompute = pointPrecompute(pointExt, 8);
        PointExt pointExt3 = new PointExt();
        pointLookup(r1, 111, pointPrecompute, pointExt2);
        for (int r3 = 110; r3 >= 0; r3--) {
            for (int r4 = 0; r4 < 4; r4++) {
                pointDouble(pointExt2);
            }
            pointLookup(r1, r3, pointPrecompute, pointExt3);
            pointAdd(pointExt3, pointExt2);
        }
        for (int r2 = 0; r2 < 2; r2++) {
            pointDouble(pointExt2);
        }
    }

    private static void scalarMultBase(byte[] bArr, PointExt pointExt) {
        precompute();
        int[] r1 = new int[15];
        decodeScalar(bArr, 0, r1);
        r1[14] = Nat.cadd(14, (~r1[0]) & 1, r1, f2372L, r1) + 4;
        Nat.shiftDownBit(15, r1, 0);
        PointPrecomp pointPrecomp = new PointPrecomp();
        pointSetNeutral(pointExt);
        int r4 = 17;
        while (true) {
            int r6 = r4;
            for (int r5 = 0; r5 < 5; r5++) {
                int r9 = 0;
                for (int r8 = 0; r8 < 5; r8++) {
                    r9 = (r9 & (~(1 << r8))) ^ ((r1[r6 >>> 5] >>> (r6 & 31)) << r8);
                    r6 += 18;
                }
                int r7 = (r9 >>> 4) & 1;
                pointLookup(r5, ((-r7) ^ r9) & 15, pointPrecomp);
                C5382F.cnegate(r7, pointPrecomp.f2377x);
                pointAddPrecomp(pointPrecomp, pointExt);
            }
            r4--;
            if (r4 < 0) {
                return;
            }
            pointDouble(pointExt);
        }
    }

    private static void scalarMultBaseEncoded(byte[] bArr, byte[] bArr2, int r4) {
        PointExt pointExt = new PointExt();
        scalarMultBase(bArr, pointExt);
        if (encodePoint(pointExt, bArr2, r4) == 0) {
            throw new IllegalStateException();
        }
    }

    public static void scalarMultBaseXY(X448.Friend friend, byte[] bArr, int r3, int[] r4, int[] r5) {
        Objects.requireNonNull(friend, "This method is only for use by X448");
        byte[] bArr2 = new byte[57];
        pruneScalar(bArr, r3, bArr2);
        PointExt pointExt = new PointExt();
        scalarMultBase(bArr2, pointExt);
        if (checkPoint(pointExt.f2374x, pointExt.f2375y, pointExt.f2376z) == 0) {
            throw new IllegalStateException();
        }
        C5382F.copy(pointExt.f2374x, 0, r4, 0);
        C5382F.copy(pointExt.f2375y, 0, r5, 0);
    }

    private static void scalarMultOrderVar(PointExt pointExt, PointExt pointExt2) {
        byte[] wnafVar = getWnafVar(f2372L, 5);
        PointExt[] pointPrecomputeVar = pointPrecomputeVar(pointExt, 8);
        pointSetNeutral(pointExt2);
        int r1 = 446;
        while (true) {
            byte b = wnafVar[r1];
            if (b != 0) {
                int r3 = b >> Ascii.f1131US;
                pointAddVar(r3 != 0, pointPrecomputeVar[(b ^ r3) >>> 1], pointExt2);
            }
            r1--;
            if (r1 < 0) {
                return;
            }
            pointDouble(pointExt2);
        }
    }

    private static void scalarMultStrausVar(int[] r6, int[] r7, PointExt pointExt, PointExt pointExt2) {
        precompute();
        byte[] wnafVar = getWnafVar(r6, 7);
        byte[] wnafVar2 = getWnafVar(r7, 5);
        PointExt[] pointPrecomputeVar = pointPrecomputeVar(pointExt, 8);
        pointSetNeutral(pointExt2);
        int r0 = 446;
        while (true) {
            byte b = wnafVar[r0];
            if (b != 0) {
                int r4 = b >> Ascii.f1131US;
                pointAddVar(r4 != 0, precompBaseTable[(b ^ r4) >>> 1], pointExt2);
            }
            byte b2 = wnafVar2[r0];
            if (b2 != 0) {
                int r42 = b2 >> Ascii.f1131US;
                pointAddVar(r42 != 0, pointPrecomputeVar[(b2 ^ r42) >>> 1], pointExt2);
            }
            r0--;
            if (r0 < 0) {
                return;
            }
            pointDouble(pointExt2);
        }
    }

    public static void sign(byte[] bArr, int r12, byte[] bArr2, int r14, byte[] bArr3, byte[] bArr4, int r17, int r18, byte[] bArr5, int r20) {
        implSign(bArr, r12, bArr2, r14, bArr3, (byte) 0, bArr4, r17, r18, bArr5, r20);
    }

    public static void sign(byte[] bArr, int r10, byte[] bArr2, byte[] bArr3, int r13, int r14, byte[] bArr4, int r16) {
        implSign(bArr, r10, bArr2, (byte) 0, bArr3, r13, r14, bArr4, r16);
    }

    public static void signPrehash(byte[] bArr, int r13, byte[] bArr2, int r15, byte[] bArr3, Xof xof, byte[] bArr4, int r19) {
        byte[] bArr5 = new byte[64];
        if (64 != xof.doFinal(bArr5, 0, 64)) {
            throw new IllegalArgumentException("ph");
        }
        implSign(bArr, r13, bArr2, r15, bArr3, (byte) 1, bArr5, 0, 64, bArr4, r19);
    }

    public static void signPrehash(byte[] bArr, int r12, byte[] bArr2, int r14, byte[] bArr3, byte[] bArr4, int r17, byte[] bArr5, int r19) {
        implSign(bArr, r12, bArr2, r14, bArr3, (byte) 1, bArr4, r17, 64, bArr5, r19);
    }

    public static void signPrehash(byte[] bArr, int r11, byte[] bArr2, Xof xof, byte[] bArr3, int r15) {
        byte[] bArr4 = new byte[64];
        if (64 != xof.doFinal(bArr4, 0, 64)) {
            throw new IllegalArgumentException("ph");
        }
        implSign(bArr, r11, bArr2, (byte) 1, bArr4, 0, 64, bArr3, r15);
    }

    public static void signPrehash(byte[] bArr, int r10, byte[] bArr2, byte[] bArr3, int r13, byte[] bArr4, int r15) {
        implSign(bArr, r10, bArr2, (byte) 1, bArr3, r13, 64, bArr4, r15);
    }

    public static boolean validatePublicKeyFull(byte[] bArr, int r5) {
        PointExt pointExt = new PointExt();
        if (decodePointVar(bArr, r5, false, pointExt)) {
            C5382F.normalize(pointExt.f2374x);
            C5382F.normalize(pointExt.f2375y);
            C5382F.normalize(pointExt.f2376z);
            if (isNeutralElementVar(pointExt.f2374x, pointExt.f2375y, pointExt.f2376z)) {
                return false;
            }
            PointExt pointExt2 = new PointExt();
            scalarMultOrderVar(pointExt, pointExt2);
            C5382F.normalize(pointExt2.f2374x);
            C5382F.normalize(pointExt2.f2375y);
            C5382F.normalize(pointExt2.f2376z);
            return isNeutralElementVar(pointExt2.f2374x, pointExt2.f2375y, pointExt2.f2376z);
        }
        return false;
    }

    public static boolean validatePublicKeyPartial(byte[] bArr, int r3) {
        return decodePointVar(bArr, r3, false, new PointExt());
    }

    public static boolean verify(byte[] bArr, int r10, byte[] bArr2, int r12, byte[] bArr3, byte[] bArr4, int r15, int r16) {
        return implVerify(bArr, r10, bArr2, r12, bArr3, (byte) 0, bArr4, r15, r16);
    }

    public static boolean verifyPrehash(byte[] bArr, int r11, byte[] bArr2, int r13, byte[] bArr3, Xof xof) {
        byte[] bArr4 = new byte[64];
        if (64 == xof.doFinal(bArr4, 0, 64)) {
            return implVerify(bArr, r11, bArr2, r13, bArr3, (byte) 1, bArr4, 0, 64);
        }
        throw new IllegalArgumentException("ph");
    }

    public static boolean verifyPrehash(byte[] bArr, int r10, byte[] bArr2, int r12, byte[] bArr3, byte[] bArr4, int r15) {
        return implVerify(bArr, r10, bArr2, r12, bArr3, (byte) 1, bArr4, r15, 64);
    }
}
