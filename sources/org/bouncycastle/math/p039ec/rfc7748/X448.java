package org.bouncycastle.math.p039ec.rfc7748;

import com.google.common.base.Ascii;
import java.security.SecureRandom;
import org.bouncycastle.math.p039ec.rfc8032.Ed448;
import org.bouncycastle.util.Arrays;

/* renamed from: org.bouncycastle.math.ec.rfc7748.X448 */
/* loaded from: classes5.dex */
public abstract class X448 {
    private static final int C_A = 156326;
    private static final int C_A24 = 39082;
    public static final int POINT_SIZE = 56;
    public static final int SCALAR_SIZE = 56;

    /* renamed from: org.bouncycastle.math.ec.rfc7748.X448$F */
    /* loaded from: classes5.dex */
    private static class C5378F extends X448Field {
        private C5378F() {
        }
    }

    /* renamed from: org.bouncycastle.math.ec.rfc7748.X448$Friend */
    /* loaded from: classes5.dex */
    public static class Friend {
        private static final Friend INSTANCE = new Friend();

        private Friend() {
        }
    }

    public static boolean calculateAgreement(byte[] bArr, int r1, byte[] bArr2, int r3, byte[] bArr3, int r5) {
        scalarMult(bArr, r1, bArr2, r3, bArr3, r5);
        return !Arrays.areAllZeroes(bArr3, r5, 56);
    }

    private static int decode32(byte[] bArr, int r3) {
        int r32 = r3 + 1;
        int r33 = r32 + 1;
        return (bArr[r33 + 1] << Ascii.CAN) | (bArr[r3] & 255) | ((bArr[r32] & 255) << 8) | ((bArr[r33] & 255) << 16);
    }

    private static void decodeScalar(byte[] bArr, int r4, int[] r5) {
        for (int r1 = 0; r1 < 14; r1++) {
            r5[r1] = decode32(bArr, (r1 * 4) + r4);
        }
        r5[0] = r5[0] & (-4);
        r5[13] = r5[13] | Integer.MIN_VALUE;
    }

    public static void generatePrivateKey(SecureRandom secureRandom, byte[] bArr) {
        secureRandom.nextBytes(bArr);
        bArr[0] = (byte) (bArr[0] & 252);
        bArr[55] = (byte) (bArr[55] | 128);
    }

    public static void generatePublicKey(byte[] bArr, int r1, byte[] bArr2, int r3) {
        scalarMultBase(bArr, r1, bArr2, r3);
    }

    private static void pointDouble(int[] r2, int[] r3) {
        int[] create = C5378F.create();
        int[] create2 = C5378F.create();
        C5378F.add(r2, r3, create);
        C5378F.sub(r2, r3, create2);
        C5378F.sqr(create, create);
        C5378F.sqr(create2, create2);
        C5378F.mul(create, create2, r2);
        C5378F.sub(create, create2, create);
        C5378F.mul(create, (int) C_A24, r3);
        C5378F.add(r3, create2, r3);
        C5378F.mul(r3, create, r3);
    }

    public static void precompute() {
        Ed448.precompute();
    }

    public static void scalarMult(byte[] bArr, int r11, byte[] bArr2, int r13, byte[] bArr3, int r15) {
        int[] r0 = new int[14];
        decodeScalar(bArr, r11, r0);
        int[] create = C5378F.create();
        C5378F.decode(bArr2, r13, create);
        int[] create2 = C5378F.create();
        C5378F.copy(create, 0, create2, 0);
        int[] create3 = C5378F.create();
        create3[0] = 1;
        int[] create4 = C5378F.create();
        create4[0] = 1;
        int[] create5 = C5378F.create();
        int[] create6 = C5378F.create();
        int[] create7 = C5378F.create();
        int r6 = 447;
        int r7 = 1;
        while (true) {
            C5378F.add(create4, create5, create6);
            C5378F.sub(create4, create5, create4);
            C5378F.add(create2, create3, create5);
            C5378F.sub(create2, create3, create2);
            C5378F.mul(create6, create2, create6);
            C5378F.mul(create4, create5, create4);
            C5378F.sqr(create5, create5);
            C5378F.sqr(create2, create2);
            C5378F.sub(create5, create2, create7);
            C5378F.mul(create7, (int) C_A24, create3);
            C5378F.add(create3, create2, create3);
            C5378F.mul(create3, create7, create3);
            C5378F.mul(create2, create5, create2);
            C5378F.sub(create6, create4, create5);
            C5378F.add(create6, create4, create4);
            C5378F.sqr(create4, create4);
            C5378F.sqr(create5, create5);
            C5378F.mul(create5, create, create5);
            r6--;
            int r8 = (r0[r6 >>> 5] >>> (r6 & 31)) & 1;
            int r72 = r7 ^ r8;
            C5378F.cswap(r72, create2, create4);
            C5378F.cswap(r72, create3, create5);
            if (r6 < 2) {
                break;
            }
            r7 = r8;
        }
        for (int r12 = 0; r12 < 2; r12++) {
            pointDouble(create2, create3);
        }
        C5378F.inv(create3, create3);
        C5378F.mul(create2, create3, create2);
        C5378F.normalize(create2);
        C5378F.encode(create2, bArr3, r15);
    }

    public static void scalarMultBase(byte[] bArr, int r4, byte[] bArr2, int r6) {
        int[] create = C5378F.create();
        int[] create2 = C5378F.create();
        Ed448.scalarMultBaseXY(Friend.INSTANCE, bArr, r4, create, create2);
        C5378F.inv(create, create);
        C5378F.mul(create, create2, create);
        C5378F.sqr(create, create);
        C5378F.normalize(create);
        C5378F.encode(create, bArr2, r6);
    }
}
