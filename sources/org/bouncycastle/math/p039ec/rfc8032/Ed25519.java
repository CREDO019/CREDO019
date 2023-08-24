package org.bouncycastle.math.p039ec.rfc8032;

import com.google.common.base.Ascii;
import com.google.common.primitives.SignedBytes;
import java.security.SecureRandom;
import java.util.Objects;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA512Digest;
import org.bouncycastle.math.p039ec.rfc7748.X25519;
import org.bouncycastle.math.p039ec.rfc7748.X25519Field;
import org.bouncycastle.math.raw.Interleave;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.math.raw.Nat256;
import org.bouncycastle.util.Arrays;

/* renamed from: org.bouncycastle.math.ec.rfc8032.Ed25519 */
/* loaded from: classes5.dex */
public abstract class Ed25519 {
    private static final int COORD_INTS = 8;

    /* renamed from: L0 */
    private static final int f2355L0 = -50998291;

    /* renamed from: L1 */
    private static final int f2356L1 = 19280294;

    /* renamed from: L2 */
    private static final int f2357L2 = 127719000;

    /* renamed from: L3 */
    private static final int f2358L3 = -6428113;

    /* renamed from: L4 */
    private static final int f2359L4 = 5343;
    private static final long M08L = 255;
    private static final long M28L = 268435455;
    private static final long M32L = 4294967295L;
    private static final int POINT_BYTES = 32;
    private static final int PRECOMP_BLOCKS = 8;
    private static final int PRECOMP_MASK = 7;
    private static final int PRECOMP_POINTS = 8;
    private static final int PRECOMP_SPACING = 8;
    private static final int PRECOMP_TEETH = 4;
    public static final int PREHASH_SIZE = 64;
    public static final int PUBLIC_KEY_SIZE = 32;
    private static final int SCALAR_BYTES = 32;
    private static final int SCALAR_INTS = 8;
    public static final int SECRET_KEY_SIZE = 32;
    public static final int SIGNATURE_SIZE = 64;
    private static final int WNAF_WIDTH_BASE = 7;
    private static final byte[] DOM2_PREFIX = {83, 105, 103, 69, 100, 50, 53, 53, 49, 57, 32, 110, 111, 32, 69, 100, 50, 53, 53, 49, 57, 32, 99, 111, 108, 108, 105, 115, 105, 111, 110, 115};

    /* renamed from: P */
    private static final int[] f2360P = {-19, -1, -1, -1, -1, -1, -1, Integer.MAX_VALUE};

    /* renamed from: L */
    private static final int[] f2354L = {1559614445, 1477600026, -1560830762, 350157278, 0, 0, 0, 268435456};
    private static final int[] B_x = {52811034, 25909283, 8072341, 50637101, 13785486, 30858332, 20483199, 20966410, 43936626, 4379245};
    private static final int[] B_y = {40265304, 26843545, 6710886, 53687091, 13421772, 40265318, 26843545, 6710886, 53687091, 13421772};
    private static final int[] C_d = {56195235, 47411844, 25868126, 40503822, 57364, 58321048, 30416477, 31930572, 57760639, 10749657};
    private static final int[] C_d2 = {45281625, 27714825, 18181821, 13898781, 114729, 49533232, 60832955, 30306712, 48412415, 4722099};
    private static final int[] C_d4 = {23454386, 55429651, 2809210, 27797563, 229458, 31957600, 54557047, 27058993, 29715967, 9444199};
    private static final Object precompLock = new Object();
    private static PointExt[] precompBaseTable = null;
    private static int[] precompBase = null;

    /* renamed from: org.bouncycastle.math.ec.rfc8032.Ed25519$Algorithm */
    /* loaded from: classes5.dex */
    public static final class Algorithm {
        public static final int Ed25519 = 0;
        public static final int Ed25519ctx = 1;
        public static final int Ed25519ph = 2;
    }

    /* renamed from: org.bouncycastle.math.ec.rfc8032.Ed25519$F */
    /* loaded from: classes5.dex */
    private static class C5380F extends X25519Field {
        private C5380F() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: org.bouncycastle.math.ec.rfc8032.Ed25519$PointAccum */
    /* loaded from: classes5.dex */
    public static class PointAccum {

        /* renamed from: u */
        int[] f2361u;

        /* renamed from: v */
        int[] f2362v;

        /* renamed from: x */
        int[] f2363x;

        /* renamed from: y */
        int[] f2364y;

        /* renamed from: z */
        int[] f2365z;

        private PointAccum() {
            this.f2363x = C5380F.create();
            this.f2364y = C5380F.create();
            this.f2365z = C5380F.create();
            this.f2361u = C5380F.create();
            this.f2362v = C5380F.create();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: org.bouncycastle.math.ec.rfc8032.Ed25519$PointAffine */
    /* loaded from: classes5.dex */
    public static class PointAffine {

        /* renamed from: x */
        int[] f2366x;

        /* renamed from: y */
        int[] f2367y;

        private PointAffine() {
            this.f2366x = C5380F.create();
            this.f2367y = C5380F.create();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: org.bouncycastle.math.ec.rfc8032.Ed25519$PointExt */
    /* loaded from: classes5.dex */
    public static class PointExt {

        /* renamed from: t */
        int[] f2368t;

        /* renamed from: x */
        int[] f2369x;

        /* renamed from: y */
        int[] f2370y;

        /* renamed from: z */
        int[] f2371z;

        private PointExt() {
            this.f2369x = C5380F.create();
            this.f2370y = C5380F.create();
            this.f2371z = C5380F.create();
            this.f2368t = C5380F.create();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: org.bouncycastle.math.ec.rfc8032.Ed25519$PointPrecomp */
    /* loaded from: classes5.dex */
    public static class PointPrecomp {
        int[] xyd;
        int[] ymx_h;
        int[] ypx_h;

        private PointPrecomp() {
            this.ypx_h = C5380F.create();
            this.ymx_h = C5380F.create();
            this.xyd = C5380F.create();
        }
    }

    private static byte[] calculateS(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        int[] r1 = new int[16];
        decodeScalar(bArr, 0, r1);
        int[] r3 = new int[8];
        decodeScalar(bArr2, 0, r3);
        int[] r4 = new int[8];
        decodeScalar(bArr3, 0, r4);
        Nat256.mulAddTo(r3, r4, r1);
        byte[] bArr4 = new byte[64];
        for (int r2 = 0; r2 < 16; r2++) {
            encode32(r1[r2], bArr4, r2 * 4);
        }
        return reduceScalar(bArr4);
    }

    private static boolean checkContextVar(byte[] bArr, byte b) {
        return (bArr == null && b == 0) || (bArr != null && bArr.length < 256);
    }

    private static int checkPoint(int[] r3, int[] r4) {
        int[] create = C5380F.create();
        int[] create2 = C5380F.create();
        int[] create3 = C5380F.create();
        C5380F.sqr(r3, create2);
        C5380F.sqr(r4, create3);
        C5380F.mul(create2, create3, create);
        C5380F.sub(create3, create2, create3);
        C5380F.mul(create, C_d, create);
        C5380F.addOne(create);
        C5380F.sub(create, create3, create);
        C5380F.normalize(create);
        return C5380F.isZero(create);
    }

    private static int checkPoint(int[] r4, int[] r5, int[] r6) {
        int[] create = C5380F.create();
        int[] create2 = C5380F.create();
        int[] create3 = C5380F.create();
        int[] create4 = C5380F.create();
        C5380F.sqr(r4, create2);
        C5380F.sqr(r5, create3);
        C5380F.sqr(r6, create4);
        C5380F.mul(create2, create3, create);
        C5380F.sub(create3, create2, create3);
        C5380F.mul(create3, create4, create3);
        C5380F.sqr(create4, create4);
        C5380F.mul(create, C_d, create);
        C5380F.add(create, create4, create);
        C5380F.sub(create, create3, create);
        C5380F.normalize(create);
        return C5380F.isZero(create);
    }

    private static boolean checkPointVar(byte[] bArr) {
        int[] r1 = new int[8];
        decode32(bArr, 0, r1, 0, 8);
        r1[7] = r1[7] & Integer.MAX_VALUE;
        return !Nat256.gte(r1, f2360P);
    }

    private static boolean checkScalarVar(byte[] bArr, int[] r2) {
        decodeScalar(bArr, 0, r2);
        return !Nat256.gte(r2, f2354L);
    }

    private static byte[] copy(byte[] bArr, int r3, int r4) {
        byte[] bArr2 = new byte[r4];
        System.arraycopy(bArr, r3, bArr2, 0, r4);
        return bArr2;
    }

    private static Digest createDigest() {
        return new SHA512Digest();
    }

    public static Digest createPrehash() {
        return createDigest();
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

    private static boolean decodePointVar(byte[] bArr, int r4, boolean z, PointAffine pointAffine) {
        byte[] copy = copy(bArr, r4, 32);
        if (checkPointVar(copy)) {
            int r1 = (copy[31] & 128) >>> 7;
            copy[31] = (byte) (copy[31] & Byte.MAX_VALUE);
            C5380F.decode(copy, 0, pointAffine.f2367y);
            int[] create = C5380F.create();
            int[] create2 = C5380F.create();
            C5380F.sqr(pointAffine.f2367y, create);
            C5380F.mul(C_d, create, create2);
            C5380F.subOne(create);
            C5380F.addOne(create2);
            if (C5380F.sqrtRatioVar(create, create2, pointAffine.f2366x)) {
                C5380F.normalize(pointAffine.f2366x);
                if (r1 == 1 && C5380F.isZeroVar(pointAffine.f2366x)) {
                    return false;
                }
                if (z ^ (r1 != (pointAffine.f2366x[0] & 1))) {
                    C5380F.negate(pointAffine.f2366x, pointAffine.f2366x);
                }
                return true;
            }
            return false;
        }
        return false;
    }

    private static void decodeScalar(byte[] bArr, int r3, int[] r4) {
        decode32(bArr, r3, r4, 0, 8);
    }

    private static void dom2(Digest digest, byte b, byte[] bArr) {
        if (bArr != null) {
            byte[] bArr2 = DOM2_PREFIX;
            int length = bArr2.length;
            int r2 = length + 2;
            int length2 = bArr.length + r2;
            byte[] bArr3 = new byte[length2];
            System.arraycopy(bArr2, 0, bArr3, 0, length);
            bArr3[length] = b;
            bArr3[length + 1] = (byte) bArr.length;
            System.arraycopy(bArr, 0, bArr3, r2, bArr.length);
            digest.update(bArr3, 0, length2);
        }
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

    private static int encodePoint(PointAccum pointAccum, byte[] bArr, int r5) {
        int[] create = C5380F.create();
        int[] create2 = C5380F.create();
        C5380F.inv(pointAccum.f2365z, create2);
        C5380F.mul(pointAccum.f2363x, create2, create);
        C5380F.mul(pointAccum.f2364y, create2, create2);
        C5380F.normalize(create);
        C5380F.normalize(create2);
        int checkPoint = checkPoint(create, create2);
        C5380F.encode(create2, bArr, r5);
        int r52 = (r5 + 32) - 1;
        bArr[r52] = (byte) (((create[0] & 1) << 7) | bArr[r52]);
        return checkPoint;
    }

    public static void generatePrivateKey(SecureRandom secureRandom, byte[] bArr) {
        secureRandom.nextBytes(bArr);
    }

    public static void generatePublicKey(byte[] bArr, int r4, byte[] bArr2, int r6) {
        Digest createDigest = createDigest();
        byte[] bArr3 = new byte[createDigest.getDigestSize()];
        createDigest.update(bArr, r4, 32);
        createDigest.doFinal(bArr3, 0);
        byte[] bArr4 = new byte[32];
        pruneScalar(bArr3, 0, bArr4);
        scalarMultBaseEncoded(bArr4, bArr2, r6);
    }

    private static int getWindow4(int[] r1, int r2) {
        return (r1[r2 >>> 3] >>> ((r2 & 7) << 2)) & 15;
    }

    private static byte[] getWnafVar(int[] r9, int r10) {
        int[] r1 = new int[16];
        int r2 = 0;
        int r3 = 8;
        int r4 = 16;
        int r5 = 0;
        while (true) {
            r3--;
            if (r3 < 0) {
                break;
            }
            int r6 = r9[r3];
            int r42 = r4 - 1;
            r1[r42] = (r5 << 16) | (r6 >>> 16);
            r4 = r42 - 1;
            r1[r4] = r6;
            r5 = r6;
        }
        byte[] bArr = new byte[253];
        int r32 = 32 - r10;
        int r43 = 0;
        int r52 = 0;
        while (r2 < 16) {
            int r62 = r1[r2];
            while (r43 < 16) {
                int r7 = r62 >>> r43;
                if ((r7 & 1) == r52) {
                    r43++;
                } else {
                    int r53 = (r7 | 1) << r32;
                    bArr[(r2 << 4) + r43] = (byte) (r53 >> r32);
                    r43 += r10;
                    r52 = r53 >>> 31;
                }
            }
            r2++;
            r43 -= 16;
        }
        return bArr;
    }

    private static void implSign(Digest digest, byte[] bArr, byte[] bArr2, byte[] bArr3, int r8, byte[] bArr4, byte b, byte[] bArr5, int r12, int r13, byte[] bArr6, int r15) {
        dom2(digest, b, bArr4);
        digest.update(bArr, 32, 32);
        digest.update(bArr5, r12, r13);
        digest.doFinal(bArr, 0);
        byte[] reduceScalar = reduceScalar(bArr);
        byte[] bArr7 = new byte[32];
        scalarMultBaseEncoded(reduceScalar, bArr7, 0);
        dom2(digest, b, bArr4);
        digest.update(bArr7, 0, 32);
        digest.update(bArr3, r8, 32);
        digest.update(bArr5, r12, r13);
        digest.doFinal(bArr, 0);
        byte[] calculateS = calculateS(reduceScalar, reduceScalar(bArr), bArr2);
        System.arraycopy(bArr7, 0, bArr6, r15, 32);
        System.arraycopy(calculateS, 0, bArr6, r15 + 32, 32);
    }

    private static void implSign(byte[] bArr, int r14, byte[] bArr2, byte b, byte[] bArr3, int r18, int r19, byte[] bArr4, int r21) {
        if (!checkContextVar(bArr2, b)) {
            throw new IllegalArgumentException("ctx");
        }
        Digest createDigest = createDigest();
        byte[] bArr5 = new byte[createDigest.getDigestSize()];
        createDigest.update(bArr, r14, 32);
        createDigest.doFinal(bArr5, 0);
        byte[] bArr6 = new byte[32];
        pruneScalar(bArr5, 0, bArr6);
        byte[] bArr7 = new byte[32];
        scalarMultBaseEncoded(bArr6, bArr7, 0);
        implSign(createDigest, bArr5, bArr6, bArr7, 0, bArr2, b, bArr3, r18, r19, bArr4, r21);
    }

    private static void implSign(byte[] bArr, int r14, byte[] bArr2, int r16, byte[] bArr3, byte b, byte[] bArr4, int r20, int r21, byte[] bArr5, int r23) {
        if (!checkContextVar(bArr3, b)) {
            throw new IllegalArgumentException("ctx");
        }
        Digest createDigest = createDigest();
        byte[] bArr6 = new byte[createDigest.getDigestSize()];
        createDigest.update(bArr, r14, 32);
        createDigest.doFinal(bArr6, 0);
        byte[] bArr7 = new byte[32];
        pruneScalar(bArr6, 0, bArr7);
        implSign(createDigest, bArr6, bArr7, bArr2, r16, bArr3, b, bArr4, r20, r21, bArr5, r23);
    }

    private static boolean implVerify(byte[] bArr, int r15, byte[] bArr2, int r17, byte[] bArr3, byte b, byte[] bArr4, int r21, int r22) {
        if (checkContextVar(bArr3, b)) {
            byte[] copy = copy(bArr, r15, 32);
            byte[] copy2 = copy(bArr, r15 + 32, 32);
            if (checkPointVar(copy)) {
                int[] r7 = new int[8];
                if (checkScalarVar(copy2, r7)) {
                    PointAffine pointAffine = new PointAffine();
                    if (decodePointVar(bArr2, r17, true, pointAffine)) {
                        Digest createDigest = createDigest();
                        byte[] bArr5 = new byte[createDigest.getDigestSize()];
                        dom2(createDigest, b, bArr3);
                        createDigest.update(copy, 0, 32);
                        createDigest.update(bArr2, r17, 32);
                        createDigest.update(bArr4, r21, r22);
                        createDigest.doFinal(bArr5, 0);
                        int[] r1 = new int[8];
                        decodeScalar(reduceScalar(bArr5), 0, r1);
                        PointAccum pointAccum = new PointAccum();
                        scalarMultStrausVar(r7, r1, pointAffine, pointAccum);
                        byte[] bArr6 = new byte[32];
                        return encodePoint(pointAccum, bArr6, 0) != 0 && Arrays.areEqual(bArr6, copy);
                    }
                    return false;
                }
                return false;
            }
            return false;
        }
        throw new IllegalArgumentException("ctx");
    }

    private static boolean isNeutralElementVar(int[] r0, int[] r1) {
        return C5380F.isZeroVar(r0) && C5380F.isOneVar(r1);
    }

    private static boolean isNeutralElementVar(int[] r0, int[] r1, int[] r2) {
        return C5380F.isZeroVar(r0) && C5380F.areEqualVar(r1, r2);
    }

    private static void pointAdd(PointExt pointExt, PointAccum pointAccum) {
        int[] create = C5380F.create();
        int[] create2 = C5380F.create();
        int[] create3 = C5380F.create();
        int[] create4 = C5380F.create();
        int[] r4 = pointAccum.f2361u;
        int[] create5 = C5380F.create();
        int[] create6 = C5380F.create();
        int[] r7 = pointAccum.f2362v;
        C5380F.apm(pointAccum.f2364y, pointAccum.f2363x, create2, create);
        C5380F.apm(pointExt.f2370y, pointExt.f2369x, create4, create3);
        C5380F.mul(create, create3, create);
        C5380F.mul(create2, create4, create2);
        C5380F.mul(pointAccum.f2361u, pointAccum.f2362v, create3);
        C5380F.mul(create3, pointExt.f2368t, create3);
        C5380F.mul(create3, C_d2, create3);
        C5380F.mul(pointAccum.f2365z, pointExt.f2371z, create4);
        C5380F.add(create4, create4, create4);
        C5380F.apm(create2, create, r7, r4);
        C5380F.apm(create4, create3, create6, create5);
        C5380F.carry(create6);
        C5380F.mul(r4, create5, pointAccum.f2363x);
        C5380F.mul(create6, r7, pointAccum.f2364y);
        C5380F.mul(create5, create6, pointAccum.f2365z);
    }

    private static void pointAdd(PointExt pointExt, PointExt pointExt2) {
        int[] create = C5380F.create();
        int[] create2 = C5380F.create();
        int[] create3 = C5380F.create();
        int[] create4 = C5380F.create();
        int[] create5 = C5380F.create();
        int[] create6 = C5380F.create();
        int[] create7 = C5380F.create();
        int[] create8 = C5380F.create();
        C5380F.apm(pointExt.f2370y, pointExt.f2369x, create2, create);
        C5380F.apm(pointExt2.f2370y, pointExt2.f2369x, create4, create3);
        C5380F.mul(create, create3, create);
        C5380F.mul(create2, create4, create2);
        C5380F.mul(pointExt.f2368t, pointExt2.f2368t, create3);
        C5380F.mul(create3, C_d2, create3);
        C5380F.mul(pointExt.f2371z, pointExt2.f2371z, create4);
        C5380F.add(create4, create4, create4);
        C5380F.apm(create2, create, create8, create5);
        C5380F.apm(create4, create3, create7, create6);
        C5380F.carry(create7);
        C5380F.mul(create5, create6, pointExt2.f2369x);
        C5380F.mul(create7, create8, pointExt2.f2370y);
        C5380F.mul(create6, create7, pointExt2.f2371z);
        C5380F.mul(create5, create8, pointExt2.f2368t);
    }

    private static void pointAddPrecomp(PointPrecomp pointPrecomp, PointAccum pointAccum) {
        int[] create = C5380F.create();
        int[] create2 = C5380F.create();
        int[] create3 = C5380F.create();
        int[] r3 = pointAccum.f2361u;
        int[] create4 = C5380F.create();
        int[] create5 = C5380F.create();
        int[] r6 = pointAccum.f2362v;
        C5380F.apm(pointAccum.f2364y, pointAccum.f2363x, create2, create);
        C5380F.mul(create, pointPrecomp.ymx_h, create);
        C5380F.mul(create2, pointPrecomp.ypx_h, create2);
        C5380F.mul(pointAccum.f2361u, pointAccum.f2362v, create3);
        C5380F.mul(create3, pointPrecomp.xyd, create3);
        C5380F.apm(create2, create, r6, r3);
        C5380F.apm(pointAccum.f2365z, create3, create5, create4);
        C5380F.carry(create5);
        C5380F.mul(r3, create4, pointAccum.f2363x);
        C5380F.mul(create5, r6, pointAccum.f2364y);
        C5380F.mul(create4, create5, pointAccum.f2365z);
    }

    private static void pointAddVar(boolean z, PointExt pointExt, PointAccum pointAccum) {
        int[] r8;
        int[] r13;
        int[] r10;
        int[] r9;
        int[] create = C5380F.create();
        int[] create2 = C5380F.create();
        int[] create3 = C5380F.create();
        int[] create4 = C5380F.create();
        int[] r4 = pointAccum.f2361u;
        int[] create5 = C5380F.create();
        int[] create6 = C5380F.create();
        int[] r7 = pointAccum.f2362v;
        if (z) {
            r13 = create3;
            r8 = create4;
            r9 = create5;
            r10 = create6;
        } else {
            r8 = create3;
            r13 = create4;
            r10 = create5;
            r9 = create6;
        }
        C5380F.apm(pointAccum.f2364y, pointAccum.f2363x, create2, create);
        C5380F.apm(pointExt.f2370y, pointExt.f2369x, r13, r8);
        C5380F.mul(create, create3, create);
        C5380F.mul(create2, create4, create2);
        C5380F.mul(pointAccum.f2361u, pointAccum.f2362v, create3);
        C5380F.mul(create3, pointExt.f2368t, create3);
        C5380F.mul(create3, C_d2, create3);
        C5380F.mul(pointAccum.f2365z, pointExt.f2371z, create4);
        C5380F.add(create4, create4, create4);
        C5380F.apm(create2, create, r7, r4);
        C5380F.apm(create4, create3, r9, r10);
        C5380F.carry(r9);
        C5380F.mul(r4, create5, pointAccum.f2363x);
        C5380F.mul(create6, r7, pointAccum.f2364y);
        C5380F.mul(create5, create6, pointAccum.f2365z);
    }

    private static void pointAddVar(boolean z, PointExt pointExt, PointExt pointExt2, PointExt pointExt3) {
        int[] r12;
        int[] r11;
        int[] r14;
        int[] r13;
        int[] create = C5380F.create();
        int[] create2 = C5380F.create();
        int[] create3 = C5380F.create();
        int[] create4 = C5380F.create();
        int[] create5 = C5380F.create();
        int[] create6 = C5380F.create();
        int[] create7 = C5380F.create();
        int[] create8 = C5380F.create();
        if (z) {
            r11 = create3;
            r12 = create4;
            r13 = create6;
            r14 = create7;
        } else {
            r12 = create3;
            r11 = create4;
            r14 = create6;
            r13 = create7;
        }
        C5380F.apm(pointExt.f2370y, pointExt.f2369x, create2, create);
        C5380F.apm(pointExt2.f2370y, pointExt2.f2369x, r11, r12);
        C5380F.mul(create, create3, create);
        C5380F.mul(create2, create4, create2);
        C5380F.mul(pointExt.f2368t, pointExt2.f2368t, create3);
        C5380F.mul(create3, C_d2, create3);
        C5380F.mul(pointExt.f2371z, pointExt2.f2371z, create4);
        C5380F.add(create4, create4, create4);
        C5380F.apm(create2, create, create8, create5);
        C5380F.apm(create4, create3, r13, r14);
        C5380F.carry(r13);
        C5380F.mul(create5, create6, pointExt3.f2369x);
        C5380F.mul(create7, create8, pointExt3.f2370y);
        C5380F.mul(create6, create7, pointExt3.f2371z);
        C5380F.mul(create5, create8, pointExt3.f2368t);
    }

    private static PointExt pointCopy(PointAccum pointAccum) {
        PointExt pointExt = new PointExt();
        C5380F.copy(pointAccum.f2363x, 0, pointExt.f2369x, 0);
        C5380F.copy(pointAccum.f2364y, 0, pointExt.f2370y, 0);
        C5380F.copy(pointAccum.f2365z, 0, pointExt.f2371z, 0);
        C5380F.mul(pointAccum.f2361u, pointAccum.f2362v, pointExt.f2368t);
        return pointExt;
    }

    private static PointExt pointCopy(PointAffine pointAffine) {
        PointExt pointExt = new PointExt();
        C5380F.copy(pointAffine.f2366x, 0, pointExt.f2369x, 0);
        C5380F.copy(pointAffine.f2367y, 0, pointExt.f2370y, 0);
        pointExtendXY(pointExt);
        return pointExt;
    }

    private static PointExt pointCopy(PointExt pointExt) {
        PointExt pointExt2 = new PointExt();
        pointCopy(pointExt, pointExt2);
        return pointExt2;
    }

    private static void pointCopy(PointAffine pointAffine, PointAccum pointAccum) {
        C5380F.copy(pointAffine.f2366x, 0, pointAccum.f2363x, 0);
        C5380F.copy(pointAffine.f2367y, 0, pointAccum.f2364y, 0);
        pointExtendXY(pointAccum);
    }

    private static void pointCopy(PointExt pointExt, PointExt pointExt2) {
        C5380F.copy(pointExt.f2369x, 0, pointExt2.f2369x, 0);
        C5380F.copy(pointExt.f2370y, 0, pointExt2.f2370y, 0);
        C5380F.copy(pointExt.f2371z, 0, pointExt2.f2371z, 0);
        C5380F.copy(pointExt.f2368t, 0, pointExt2.f2368t, 0);
    }

    private static void pointDouble(PointAccum pointAccum) {
        int[] create = C5380F.create();
        int[] create2 = C5380F.create();
        int[] create3 = C5380F.create();
        int[] r3 = pointAccum.f2361u;
        int[] create4 = C5380F.create();
        int[] create5 = C5380F.create();
        int[] r6 = pointAccum.f2362v;
        C5380F.sqr(pointAccum.f2363x, create);
        C5380F.sqr(pointAccum.f2364y, create2);
        C5380F.sqr(pointAccum.f2365z, create3);
        C5380F.add(create3, create3, create3);
        C5380F.apm(create, create2, r6, create5);
        C5380F.add(pointAccum.f2363x, pointAccum.f2364y, r3);
        C5380F.sqr(r3, r3);
        C5380F.sub(r6, r3, r3);
        C5380F.add(create3, create5, create4);
        C5380F.carry(create4);
        C5380F.mul(r3, create4, pointAccum.f2363x);
        C5380F.mul(create5, r6, pointAccum.f2364y);
        C5380F.mul(create4, create5, pointAccum.f2365z);
    }

    private static void pointExtendXY(PointAccum pointAccum) {
        C5380F.one(pointAccum.f2365z);
        C5380F.copy(pointAccum.f2363x, 0, pointAccum.f2361u, 0);
        C5380F.copy(pointAccum.f2364y, 0, pointAccum.f2362v, 0);
    }

    private static void pointExtendXY(PointExt pointExt) {
        C5380F.one(pointExt.f2371z);
        C5380F.mul(pointExt.f2369x, pointExt.f2370y, pointExt.f2368t);
    }

    private static void pointLookup(int r6, int r7, PointPrecomp pointPrecomp) {
        int r62 = r6 * 8 * 3 * 10;
        for (int r2 = 0; r2 < 8; r2++) {
            int r3 = ((r2 ^ r7) - 1) >> 31;
            C5380F.cmov(r3, precompBase, r62, pointPrecomp.ypx_h, 0);
            int r63 = r62 + 10;
            C5380F.cmov(r3, precompBase, r63, pointPrecomp.ymx_h, 0);
            int r64 = r63 + 10;
            C5380F.cmov(r3, precompBase, r64, pointPrecomp.xyd, 0);
            r62 = r64 + 10;
        }
    }

    private static void pointLookup(int[] r2, int r3, PointExt pointExt) {
        int r32 = r3 * 40;
        C5380F.copy(r2, r32, pointExt.f2369x, 0);
        int r33 = r32 + 10;
        C5380F.copy(r2, r33, pointExt.f2370y, 0);
        int r34 = r33 + 10;
        C5380F.copy(r2, r34, pointExt.f2371z, 0);
        C5380F.copy(r2, r34 + 10, pointExt.f2368t, 0);
    }

    private static void pointLookup(int[] r5, int r6, int[] r7, PointExt pointExt) {
        int window4 = getWindow4(r5, r6);
        int r62 = (window4 >>> 3) ^ 1;
        int r52 = (window4 ^ (-r62)) & 7;
        int r2 = 0;
        for (int r1 = 0; r1 < 8; r1++) {
            int r3 = ((r1 ^ r52) - 1) >> 31;
            C5380F.cmov(r3, r7, r2, pointExt.f2369x, 0);
            int r22 = r2 + 10;
            C5380F.cmov(r3, r7, r22, pointExt.f2370y, 0);
            int r23 = r22 + 10;
            C5380F.cmov(r3, r7, r23, pointExt.f2371z, 0);
            int r24 = r23 + 10;
            C5380F.cmov(r3, r7, r24, pointExt.f2368t, 0);
            r2 = r24 + 10;
        }
        C5380F.cnegate(r62, pointExt.f2369x);
        C5380F.cnegate(r62, pointExt.f2368t);
    }

    private static int[] pointPrecompute(PointAffine pointAffine, int r7) {
        PointExt pointCopy = pointCopy(pointAffine);
        PointExt pointCopy2 = pointCopy(pointCopy);
        pointAdd(pointCopy, pointCopy2);
        int[] createTable = C5380F.createTable(r7 * 4);
        int r3 = 0;
        int r4 = 0;
        while (true) {
            C5380F.copy(pointCopy.f2369x, 0, createTable, r3);
            int r32 = r3 + 10;
            C5380F.copy(pointCopy.f2370y, 0, createTable, r32);
            int r33 = r32 + 10;
            C5380F.copy(pointCopy.f2371z, 0, createTable, r33);
            int r34 = r33 + 10;
            C5380F.copy(pointCopy.f2368t, 0, createTable, r34);
            r3 = r34 + 10;
            r4++;
            if (r4 == r7) {
                return createTable;
            }
            pointAdd(pointCopy2, pointCopy);
        }
    }

    private static PointExt[] pointPrecomputeVar(PointExt pointExt, int r7) {
        PointExt pointExt2 = new PointExt();
        pointAddVar(false, pointExt, pointExt, pointExt2);
        PointExt[] pointExtArr = new PointExt[r7];
        pointExtArr[0] = pointCopy(pointExt);
        for (int r6 = 1; r6 < r7; r6++) {
            PointExt pointExt3 = pointExtArr[r6 - 1];
            PointExt pointExt4 = new PointExt();
            pointExtArr[r6] = pointExt4;
            pointAddVar(false, pointExt3, pointExt2, pointExt4);
        }
        return pointExtArr;
    }

    private static void pointSetNeutral(PointAccum pointAccum) {
        C5380F.zero(pointAccum.f2363x);
        C5380F.one(pointAccum.f2364y);
        C5380F.one(pointAccum.f2365z);
        C5380F.zero(pointAccum.f2361u);
        C5380F.one(pointAccum.f2362v);
    }

    private static void pointSetNeutral(PointExt pointExt) {
        C5380F.zero(pointExt.f2369x);
        C5380F.one(pointExt.f2370y);
        C5380F.one(pointExt.f2371z);
        C5380F.zero(pointExt.f2368t);
    }

    public static void precompute() {
        int r11;
        synchronized (precompLock) {
            if (precompBase != null) {
                return;
            }
            PointExt pointExt = new PointExt();
            int[] r3 = B_x;
            C5380F.copy(r3, 0, pointExt.f2369x, 0);
            int[] r4 = B_y;
            C5380F.copy(r4, 0, pointExt.f2370y, 0);
            pointExtendXY(pointExt);
            precompBaseTable = pointPrecomputeVar(pointExt, 32);
            PointAccum pointAccum = new PointAccum();
            C5380F.copy(r3, 0, pointAccum.f2363x, 0);
            C5380F.copy(r4, 0, pointAccum.f2364y, 0);
            pointExtendXY(pointAccum);
            precompBase = C5380F.createTable(192);
            int r42 = 0;
            for (int r32 = 0; r32 < 8; r32++) {
                PointExt[] pointExtArr = new PointExt[4];
                PointExt pointExt2 = new PointExt();
                pointSetNeutral(pointExt2);
                int r10 = 0;
                while (true) {
                    r11 = 1;
                    if (r10 >= 4) {
                        break;
                    }
                    pointAddVar(true, pointExt2, pointCopy(pointAccum), pointExt2);
                    pointDouble(pointAccum);
                    pointExtArr[r10] = pointCopy(pointAccum);
                    if (r32 + r10 != 10) {
                        while (r11 < 8) {
                            pointDouble(pointAccum);
                            r11++;
                        }
                    }
                    r10++;
                }
                PointExt[] pointExtArr2 = new PointExt[8];
                pointExtArr2[0] = pointExt2;
                int r9 = 0;
                int r102 = 1;
                while (r9 < 3) {
                    int r12 = r11 << r9;
                    int r13 = 0;
                    while (r13 < r12) {
                        PointExt pointExt3 = pointExtArr2[r102 - r12];
                        PointExt pointExt4 = pointExtArr[r9];
                        PointExt pointExt5 = new PointExt();
                        pointExtArr2[r102] = pointExt5;
                        pointAddVar(false, pointExt3, pointExt4, pointExt5);
                        r13++;
                        r102++;
                    }
                    r9++;
                    r11 = 1;
                }
                int[] createTable = C5380F.createTable(8);
                int[] create = C5380F.create();
                C5380F.copy(pointExtArr2[0].f2371z, 0, create, 0);
                C5380F.copy(create, 0, createTable, 0);
                int r103 = 0;
                while (true) {
                    r103++;
                    if (r103 >= 8) {
                        break;
                    }
                    C5380F.mul(create, pointExtArr2[r103].f2371z, create);
                    C5380F.copy(create, 0, createTable, r103 * 10);
                }
                C5380F.add(create, create, create);
                C5380F.invVar(create, create);
                int r104 = r103 - 1;
                int[] create2 = C5380F.create();
                while (r104 > 0) {
                    int r122 = r104 - 1;
                    C5380F.copy(createTable, r122 * 10, create2, 0);
                    C5380F.mul(create2, create, create2);
                    C5380F.copy(create2, 0, createTable, r104 * 10);
                    C5380F.mul(create, pointExtArr2[r104].f2371z, create);
                    r104 = r122;
                }
                C5380F.copy(create, 0, createTable, 0);
                for (int r92 = 0; r92 < 8; r92++) {
                    PointExt pointExt6 = pointExtArr2[r92];
                    int[] create3 = C5380F.create();
                    int[] create4 = C5380F.create();
                    C5380F.copy(createTable, r92 * 10, create4, 0);
                    C5380F.mul(pointExt6.f2369x, create4, create3);
                    C5380F.mul(pointExt6.f2370y, create4, create4);
                    PointPrecomp pointPrecomp = new PointPrecomp();
                    C5380F.apm(create4, create3, pointPrecomp.ypx_h, pointPrecomp.ymx_h);
                    C5380F.mul(create3, create4, pointPrecomp.xyd);
                    C5380F.mul(pointPrecomp.xyd, C_d4, pointPrecomp.xyd);
                    C5380F.normalize(pointPrecomp.ypx_h);
                    C5380F.normalize(pointPrecomp.ymx_h);
                    C5380F.copy(pointPrecomp.ypx_h, 0, precompBase, r42);
                    int r43 = r42 + 10;
                    C5380F.copy(pointPrecomp.ymx_h, 0, precompBase, r43);
                    int r44 = r43 + 10;
                    C5380F.copy(pointPrecomp.xyd, 0, precompBase, r44);
                    r42 = r44 + 10;
                }
            }
        }
    }

    private static void pruneScalar(byte[] bArr, int r3, byte[] bArr2) {
        System.arraycopy(bArr, r3, bArr2, 0, 32);
        bArr2[0] = (byte) (bArr2[0] & 248);
        bArr2[31] = (byte) (bArr2[31] & Byte.MAX_VALUE);
        bArr2[31] = (byte) (bArr2[31] | SignedBytes.MAX_POWER_OF_TWO);
    }

    private static byte[] reduceScalar(byte[] bArr) {
        long decode24 = (decode24(bArr, 4) << 4) & 4294967295L;
        long decode32 = decode32(bArr, 7) & 4294967295L;
        long decode242 = (decode24(bArr, 11) << 4) & 4294967295L;
        long decode322 = decode32(bArr, 14) & 4294967295L;
        long decode243 = (decode24(bArr, 18) << 4) & 4294967295L;
        long decode323 = decode32(bArr, 21) & 4294967295L;
        long decode324 = decode32(bArr, 28) & 4294967295L;
        long decode325 = decode32(bArr, 49) & 4294967295L;
        long decode244 = (decode24(bArr, 53) << 4) & 4294967295L;
        long decode326 = decode32(bArr, 56) & 4294967295L;
        long decode245 = (decode24(bArr, 60) << 4) & 4294967295L;
        long j = bArr[63] & M08L;
        long decode246 = ((decode24(bArr, 46) << 4) & 4294967295L) - (j * 5343);
        long j2 = decode245 + (decode326 >> 28);
        long j3 = decode326 & M28L;
        long decode247 = (((decode24(bArr, 32) << 4) & 4294967295L) - (j * (-50998291))) - (j2 * 19280294);
        long decode327 = ((decode32(bArr, 35) & 4294967295L) - (j * 19280294)) - (j2 * 127719000);
        long decode328 = ((decode32(bArr, 42) & 4294967295L) - (j * (-6428113))) - (j2 * 5343);
        long decode248 = ((((decode24(bArr, 39) << 4) & 4294967295L) - (j * 127719000)) - (j2 * (-6428113))) - (j3 * 5343);
        long j4 = decode244 + (decode325 >> 28);
        long j5 = decode325 & M28L;
        long j6 = (decode327 - (j3 * (-6428113))) - (j4 * 5343);
        long j7 = ((decode247 - (j3 * 127719000)) - (j4 * (-6428113))) - (j5 * 5343);
        long j8 = decode246 + (decode328 >> 28);
        long j9 = (decode328 & M28L) + (decode248 >> 28);
        long j10 = (decode322 - (j8 * (-50998291))) - (j9 * 19280294);
        long j11 = ((decode243 - (j5 * (-50998291))) - (j8 * 19280294)) - (j9 * 127719000);
        long decode249 = ((((((decode24(bArr, 25) << 4) & 4294967295L) - (j3 * (-50998291))) - (j4 * 19280294)) - (j5 * 127719000)) - (j8 * (-6428113))) - (j9 * 5343);
        long j12 = (decode248 & M28L) + (j6 >> 28);
        long j13 = (decode242 - (j9 * (-50998291))) - (j12 * 19280294);
        long j14 = j10 - (j12 * 127719000);
        long j15 = ((((decode323 - (j4 * (-50998291))) - (j5 * 19280294)) - (j8 * 127719000)) - (j9 * (-6428113))) - (j12 * 5343);
        long j16 = (j6 & M28L) + (j7 >> 28);
        long j17 = j7 & M28L;
        long j18 = decode24 - (j16 * (-50998291));
        long j19 = (decode32 - (j12 * (-50998291))) - (j16 * 19280294);
        long j20 = j13 - (j16 * 127719000);
        long j21 = j14 - (j16 * (-6428113));
        long j22 = (j11 - (j12 * (-6428113))) - (j16 * 5343);
        long j23 = (((((decode324 - (j2 * (-50998291))) - (j3 * 19280294)) - (j4 * 127719000)) - (j5 * (-6428113))) - (j8 * 5343)) + (decode249 >> 28);
        long j24 = decode249 & M28L;
        long j25 = j23 & M28L;
        long j26 = j25 >>> 27;
        long j27 = j17 + (j23 >> 28) + j26;
        long decode329 = (decode32(bArr, 0) & 4294967295L) - (j27 * (-50998291));
        long j28 = (j18 - (j27 * 19280294)) + (decode329 >> 28);
        long j29 = decode329 & M28L;
        long j30 = (j19 - (j27 * 127719000)) + (j28 >> 28);
        long j31 = j28 & M28L;
        long j32 = (j20 - (j27 * (-6428113))) + (j30 >> 28);
        long j33 = j30 & M28L;
        long j34 = (j21 - (j27 * 5343)) + (j32 >> 28);
        long j35 = j32 & M28L;
        long j36 = j22 + (j34 >> 28);
        long j37 = j34 & M28L;
        long j38 = j15 + (j36 >> 28);
        long j39 = j36 & M28L;
        long j40 = j24 + (j38 >> 28);
        long j41 = j38 & M28L;
        long j42 = j25 + (j40 >> 28);
        long j43 = j40 & M28L;
        long j44 = j42 >> 28;
        long j45 = j42 & M28L;
        long j46 = j44 - j26;
        long j47 = j29 + (j46 & (-50998291));
        long j48 = j31 + (j46 & 19280294) + (j47 >> 28);
        long j49 = j47 & M28L;
        long j50 = j33 + (j46 & 127719000) + (j48 >> 28);
        long j51 = j48 & M28L;
        long j52 = j35 + (j46 & (-6428113)) + (j50 >> 28);
        long j53 = j50 & M28L;
        long j54 = j37 + (j46 & 5343) + (j52 >> 28);
        long j55 = j52 & M28L;
        long j56 = j39 + (j54 >> 28);
        long j57 = j54 & M28L;
        long j58 = j41 + (j56 >> 28);
        long j59 = j56 & M28L;
        long j60 = j43 + (j58 >> 28);
        long j61 = j58 & M28L;
        long j62 = j60 & M28L;
        byte[] bArr2 = new byte[32];
        encode56(j49 | (j51 << 28), bArr2, 0);
        encode56((j55 << 28) | j53, bArr2, 7);
        encode56(j57 | (j59 << 28), bArr2, 14);
        encode56(j61 | (j62 << 28), bArr2, 21);
        encode32((int) (j45 + (j60 >> 28)), bArr2, 28);
        return bArr2;
    }

    private static void scalarMult(byte[] bArr, PointAffine pointAffine, PointAccum pointAccum) {
        int[] r1 = new int[8];
        decodeScalar(bArr, 0, r1);
        Nat.shiftDownBits(8, r1, 3, 1);
        Nat.cadd(8, (~r1[0]) & 1, r1, f2354L, r1);
        Nat.shiftDownBit(8, r1, 0);
        int[] pointPrecompute = pointPrecompute(pointAffine, 8);
        PointExt pointExt = new PointExt();
        pointCopy(pointAffine, pointAccum);
        pointLookup(pointPrecompute, 7, pointExt);
        pointAdd(pointExt, pointAccum);
        int r5 = 62;
        while (true) {
            pointLookup(r1, r5, pointPrecompute, pointExt);
            pointAdd(pointExt, pointAccum);
            pointDouble(pointAccum);
            pointDouble(pointAccum);
            pointDouble(pointAccum);
            r5--;
            if (r5 < 0) {
                return;
            }
            pointDouble(pointAccum);
        }
    }

    private static void scalarMultBase(byte[] bArr, PointAccum pointAccum) {
        precompute();
        int[] r1 = new int[8];
        decodeScalar(bArr, 0, r1);
        Nat.cadd(8, (~r1[0]) & 1, r1, f2354L, r1);
        Nat.shiftDownBit(8, r1, 1);
        for (int r9 = 0; r9 < 8; r9++) {
            r1[r9] = Interleave.shuffle2(r1[r9]);
        }
        PointPrecomp pointPrecomp = new PointPrecomp();
        pointSetNeutral(pointAccum);
        int r4 = 28;
        while (true) {
            for (int r5 = 0; r5 < 8; r5++) {
                int r6 = r1[r5] >>> r4;
                int r7 = (r6 >>> 3) & 1;
                pointLookup(r5, (r6 ^ (-r7)) & 7, pointPrecomp);
                C5380F.cswap(r7, pointPrecomp.ypx_h, pointPrecomp.ymx_h);
                C5380F.cnegate(r7, pointPrecomp.xyd);
                pointAddPrecomp(pointPrecomp, pointAccum);
            }
            r4 -= 4;
            if (r4 < 0) {
                return;
            }
            pointDouble(pointAccum);
        }
    }

    private static void scalarMultBaseEncoded(byte[] bArr, byte[] bArr2, int r4) {
        PointAccum pointAccum = new PointAccum();
        scalarMultBase(bArr, pointAccum);
        if (encodePoint(pointAccum, bArr2, r4) == 0) {
            throw new IllegalStateException();
        }
    }

    public static void scalarMultBaseYZ(X25519.Friend friend, byte[] bArr, int r3, int[] r4, int[] r5) {
        Objects.requireNonNull(friend, "This method is only for use by X25519");
        byte[] bArr2 = new byte[32];
        pruneScalar(bArr, r3, bArr2);
        PointAccum pointAccum = new PointAccum();
        scalarMultBase(bArr2, pointAccum);
        if (checkPoint(pointAccum.f2363x, pointAccum.f2364y, pointAccum.f2365z) == 0) {
            throw new IllegalStateException();
        }
        C5380F.copy(pointAccum.f2364y, 0, r4, 0);
        C5380F.copy(pointAccum.f2365z, 0, r5, 0);
    }

    private static void scalarMultOrderVar(PointAffine pointAffine, PointAccum pointAccum) {
        byte[] wnafVar = getWnafVar(f2354L, 5);
        PointExt[] pointPrecomputeVar = pointPrecomputeVar(pointCopy(pointAffine), 8);
        pointSetNeutral(pointAccum);
        int r1 = 252;
        while (true) {
            byte b = wnafVar[r1];
            if (b != 0) {
                int r3 = b >> Ascii.f1131US;
                pointAddVar(r3 != 0, pointPrecomputeVar[(b ^ r3) >>> 1], pointAccum);
            }
            r1--;
            if (r1 < 0) {
                return;
            }
            pointDouble(pointAccum);
        }
    }

    private static void scalarMultStrausVar(int[] r6, int[] r7, PointAffine pointAffine, PointAccum pointAccum) {
        precompute();
        byte[] wnafVar = getWnafVar(r6, 7);
        byte[] wnafVar2 = getWnafVar(r7, 5);
        PointExt[] pointPrecomputeVar = pointPrecomputeVar(pointCopy(pointAffine), 8);
        pointSetNeutral(pointAccum);
        int r0 = 252;
        while (true) {
            byte b = wnafVar[r0];
            if (b != 0) {
                int r4 = b >> Ascii.f1131US;
                pointAddVar(r4 != 0, precompBaseTable[(b ^ r4) >>> 1], pointAccum);
            }
            byte b2 = wnafVar2[r0];
            if (b2 != 0) {
                int r42 = b2 >> Ascii.f1131US;
                pointAddVar(r42 != 0, pointPrecomputeVar[(b2 ^ r42) >>> 1], pointAccum);
            }
            r0--;
            if (r0 < 0) {
                return;
            }
            pointDouble(pointAccum);
        }
    }

    public static void sign(byte[] bArr, int r10, byte[] bArr2, int r12, int r13, byte[] bArr3, int r15) {
        implSign(bArr, r10, null, (byte) 0, bArr2, r12, r13, bArr3, r15);
    }

    public static void sign(byte[] bArr, int r12, byte[] bArr2, int r14, byte[] bArr3, int r16, int r17, byte[] bArr4, int r19) {
        implSign(bArr, r12, bArr2, r14, null, (byte) 0, bArr3, r16, r17, bArr4, r19);
    }

    public static void sign(byte[] bArr, int r12, byte[] bArr2, int r14, byte[] bArr3, byte[] bArr4, int r17, int r18, byte[] bArr5, int r20) {
        implSign(bArr, r12, bArr2, r14, bArr3, (byte) 0, bArr4, r17, r18, bArr5, r20);
    }

    public static void sign(byte[] bArr, int r10, byte[] bArr2, byte[] bArr3, int r13, int r14, byte[] bArr4, int r16) {
        implSign(bArr, r10, bArr2, (byte) 0, bArr3, r13, r14, bArr4, r16);
    }

    public static void signPrehash(byte[] bArr, int r13, byte[] bArr2, int r15, byte[] bArr3, Digest digest, byte[] bArr4, int r19) {
        byte[] bArr5 = new byte[64];
        if (64 != digest.doFinal(bArr5, 0)) {
            throw new IllegalArgumentException("ph");
        }
        implSign(bArr, r13, bArr2, r15, bArr3, (byte) 1, bArr5, 0, 64, bArr4, r19);
    }

    public static void signPrehash(byte[] bArr, int r12, byte[] bArr2, int r14, byte[] bArr3, byte[] bArr4, int r17, byte[] bArr5, int r19) {
        implSign(bArr, r12, bArr2, r14, bArr3, (byte) 1, bArr4, r17, 64, bArr5, r19);
    }

    public static void signPrehash(byte[] bArr, int r11, byte[] bArr2, Digest digest, byte[] bArr3, int r15) {
        byte[] bArr4 = new byte[64];
        if (64 != digest.doFinal(bArr4, 0)) {
            throw new IllegalArgumentException("ph");
        }
        implSign(bArr, r11, bArr2, (byte) 1, bArr4, 0, 64, bArr3, r15);
    }

    public static void signPrehash(byte[] bArr, int r10, byte[] bArr2, byte[] bArr3, int r13, byte[] bArr4, int r15) {
        implSign(bArr, r10, bArr2, (byte) 1, bArr3, r13, 64, bArr4, r15);
    }

    public static boolean validatePublicKeyFull(byte[] bArr, int r4) {
        PointAffine pointAffine = new PointAffine();
        if (decodePointVar(bArr, r4, false, pointAffine)) {
            C5380F.normalize(pointAffine.f2366x);
            C5380F.normalize(pointAffine.f2367y);
            if (isNeutralElementVar(pointAffine.f2366x, pointAffine.f2367y)) {
                return false;
            }
            PointAccum pointAccum = new PointAccum();
            scalarMultOrderVar(pointAffine, pointAccum);
            C5380F.normalize(pointAccum.f2363x);
            C5380F.normalize(pointAccum.f2364y);
            C5380F.normalize(pointAccum.f2365z);
            return isNeutralElementVar(pointAccum.f2363x, pointAccum.f2364y, pointAccum.f2365z);
        }
        return false;
    }

    public static boolean validatePublicKeyPartial(byte[] bArr, int r3) {
        return decodePointVar(bArr, r3, false, new PointAffine());
    }

    public static boolean verify(byte[] bArr, int r10, byte[] bArr2, int r12, byte[] bArr3, int r14, int r15) {
        return implVerify(bArr, r10, bArr2, r12, null, (byte) 0, bArr3, r14, r15);
    }

    public static boolean verify(byte[] bArr, int r10, byte[] bArr2, int r12, byte[] bArr3, byte[] bArr4, int r15, int r16) {
        return implVerify(bArr, r10, bArr2, r12, bArr3, (byte) 0, bArr4, r15, r16);
    }

    public static boolean verifyPrehash(byte[] bArr, int r11, byte[] bArr2, int r13, byte[] bArr3, Digest digest) {
        byte[] bArr4 = new byte[64];
        if (64 == digest.doFinal(bArr4, 0)) {
            return implVerify(bArr, r11, bArr2, r13, bArr3, (byte) 1, bArr4, 0, 64);
        }
        throw new IllegalArgumentException("ph");
    }

    public static boolean verifyPrehash(byte[] bArr, int r10, byte[] bArr2, int r12, byte[] bArr3, byte[] bArr4, int r15) {
        return implVerify(bArr, r10, bArr2, r12, bArr3, (byte) 1, bArr4, r15, 64);
    }
}
