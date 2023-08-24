package org.bouncycastle.math.p039ec.rfc7748;

import com.google.common.base.Ascii;
import com.google.common.primitives.SignedBytes;
import java.security.SecureRandom;
import org.bouncycastle.math.p039ec.rfc8032.Ed25519;
import org.bouncycastle.util.Arrays;

/* renamed from: org.bouncycastle.math.ec.rfc7748.X25519 */
/* loaded from: classes5.dex */
public abstract class X25519 {
    private static final int C_A = 486662;
    private static final int C_A24 = 121666;
    public static final int POINT_SIZE = 32;
    public static final int SCALAR_SIZE = 32;

    /* renamed from: org.bouncycastle.math.ec.rfc7748.X25519$F */
    /* loaded from: classes5.dex */
    private static class C5377F extends X25519Field {
        private C5377F() {
        }
    }

    /* renamed from: org.bouncycastle.math.ec.rfc7748.X25519$Friend */
    /* loaded from: classes5.dex */
    public static class Friend {
        private static final Friend INSTANCE = new Friend();

        private Friend() {
        }
    }

    public static boolean calculateAgreement(byte[] bArr, int r1, byte[] bArr2, int r3, byte[] bArr3, int r5) {
        scalarMult(bArr, r1, bArr2, r3, bArr3, r5);
        return !Arrays.areAllZeroes(bArr3, r5, 32);
    }

    private static int decode32(byte[] bArr, int r3) {
        int r32 = r3 + 1;
        int r33 = r32 + 1;
        return (bArr[r33 + 1] << Ascii.CAN) | (bArr[r3] & 255) | ((bArr[r32] & 255) << 8) | ((bArr[r33] & 255) << 16);
    }

    private static void decodeScalar(byte[] bArr, int r4, int[] r5) {
        for (int r1 = 0; r1 < 8; r1++) {
            r5[r1] = decode32(bArr, (r1 * 4) + r4);
        }
        r5[0] = r5[0] & (-8);
        r5[7] = r5[7] & Integer.MAX_VALUE;
        r5[7] = r5[7] | 1073741824;
    }

    public static void generatePrivateKey(SecureRandom secureRandom, byte[] bArr) {
        secureRandom.nextBytes(bArr);
        bArr[0] = (byte) (bArr[0] & 248);
        bArr[31] = (byte) (bArr[31] & Byte.MAX_VALUE);
        bArr[31] = (byte) (bArr[31] | SignedBytes.MAX_POWER_OF_TWO);
    }

    public static void generatePublicKey(byte[] bArr, int r1, byte[] bArr2, int r3) {
        scalarMultBase(bArr, r1, bArr2, r3);
    }

    private static void pointDouble(int[] r2, int[] r3) {
        int[] create = C5377F.create();
        int[] create2 = C5377F.create();
        C5377F.apm(r2, r3, create, create2);
        C5377F.sqr(create, create);
        C5377F.sqr(create2, create2);
        C5377F.mul(create, create2, r2);
        C5377F.sub(create, create2, create);
        C5377F.mul(create, (int) C_A24, r3);
        C5377F.add(r3, create2, r3);
        C5377F.mul(r3, create, r3);
    }

    public static void precompute() {
        Ed25519.precompute();
    }

    public static void scalarMult(byte[] bArr, int r11, byte[] bArr2, int r13, byte[] bArr3, int r15) {
        int[] r0 = new int[8];
        decodeScalar(bArr, r11, r0);
        int[] create = C5377F.create();
        C5377F.decode(bArr2, r13, create);
        int[] create2 = C5377F.create();
        C5377F.copy(create, 0, create2, 0);
        int[] create3 = C5377F.create();
        create3[0] = 1;
        int[] create4 = C5377F.create();
        create4[0] = 1;
        int[] create5 = C5377F.create();
        int[] create6 = C5377F.create();
        int[] create7 = C5377F.create();
        int r6 = 254;
        int r7 = 1;
        while (true) {
            C5377F.apm(create4, create5, create6, create4);
            C5377F.apm(create2, create3, create5, create2);
            C5377F.mul(create6, create2, create6);
            C5377F.mul(create4, create5, create4);
            C5377F.sqr(create5, create5);
            C5377F.sqr(create2, create2);
            C5377F.sub(create5, create2, create7);
            C5377F.mul(create7, (int) C_A24, create3);
            C5377F.add(create3, create2, create3);
            C5377F.mul(create3, create7, create3);
            C5377F.mul(create2, create5, create2);
            C5377F.apm(create6, create4, create4, create5);
            C5377F.sqr(create4, create4);
            C5377F.sqr(create5, create5);
            C5377F.mul(create5, create, create5);
            r6--;
            int r8 = (r0[r6 >>> 5] >>> (r6 & 31)) & 1;
            int r72 = r7 ^ r8;
            C5377F.cswap(r72, create2, create4);
            C5377F.cswap(r72, create3, create5);
            if (r6 < 3) {
                break;
            }
            r7 = r8;
        }
        for (int r12 = 0; r12 < 3; r12++) {
            pointDouble(create2, create3);
        }
        C5377F.inv(create3, create3);
        C5377F.mul(create2, create3, create2);
        C5377F.normalize(create2);
        C5377F.encode(create2, bArr3, r15);
    }

    public static void scalarMultBase(byte[] bArr, int r4, byte[] bArr2, int r6) {
        int[] create = C5377F.create();
        int[] create2 = C5377F.create();
        Ed25519.scalarMultBaseYZ(Friend.INSTANCE, bArr, r4, create, create2);
        C5377F.apm(create2, create, create, create2);
        C5377F.inv(create2, create2);
        C5377F.mul(create, create2, create);
        C5377F.normalize(create);
        C5377F.encode(create, bArr2, r6);
    }
}
