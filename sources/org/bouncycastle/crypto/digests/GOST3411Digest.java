package org.bouncycastle.crypto.digests;

import androidx.core.view.MotionEventCompat;
import java.lang.reflect.Array;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.ExtendedDigest;
import org.bouncycastle.crypto.engines.GOST28147Engine;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithSBox;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Memoable;
import org.bouncycastle.util.Pack;

/* loaded from: classes5.dex */
public class GOST3411Digest implements ExtendedDigest, Memoable {

    /* renamed from: C2 */
    private static final byte[] f1734C2 = {0, -1, 0, -1, 0, -1, 0, -1, -1, 0, -1, 0, -1, 0, -1, 0, 0, -1, -1, 0, -1, 0, 0, -1, -1, 0, 0, 0, -1, -1, 0, -1};
    private static final int DIGEST_LENGTH = 32;

    /* renamed from: C */
    private byte[][] f1735C;

    /* renamed from: H */
    private byte[] f1736H;

    /* renamed from: K */
    private byte[] f1737K;

    /* renamed from: L */
    private byte[] f1738L;

    /* renamed from: M */
    private byte[] f1739M;

    /* renamed from: S */
    byte[] f1740S;
    private byte[] Sum;

    /* renamed from: U */
    byte[] f1741U;

    /* renamed from: V */
    byte[] f1742V;

    /* renamed from: W */
    byte[] f1743W;

    /* renamed from: a */
    byte[] f1744a;
    private long byteCount;
    private BlockCipher cipher;
    private byte[] sBox;

    /* renamed from: wS */
    short[] f1745wS;
    short[] w_S;
    private byte[] xBuf;
    private int xBufOff;

    public GOST3411Digest() {
        this.f1736H = new byte[32];
        this.f1738L = new byte[32];
        this.f1739M = new byte[32];
        this.Sum = new byte[32];
        this.f1735C = (byte[][]) Array.newInstance(byte.class, 4, 32);
        this.xBuf = new byte[32];
        this.cipher = new GOST28147Engine();
        this.f1737K = new byte[32];
        this.f1744a = new byte[8];
        this.f1745wS = new short[16];
        this.w_S = new short[16];
        this.f1740S = new byte[32];
        this.f1741U = new byte[32];
        this.f1742V = new byte[32];
        this.f1743W = new byte[32];
        byte[] sBox = GOST28147Engine.getSBox("D-A");
        this.sBox = sBox;
        this.cipher.init(true, new ParametersWithSBox(null, sBox));
        reset();
    }

    public GOST3411Digest(GOST3411Digest gOST3411Digest) {
        this.f1736H = new byte[32];
        this.f1738L = new byte[32];
        this.f1739M = new byte[32];
        this.Sum = new byte[32];
        this.f1735C = (byte[][]) Array.newInstance(byte.class, 4, 32);
        this.xBuf = new byte[32];
        this.cipher = new GOST28147Engine();
        this.f1737K = new byte[32];
        this.f1744a = new byte[8];
        this.f1745wS = new short[16];
        this.w_S = new short[16];
        this.f1740S = new byte[32];
        this.f1741U = new byte[32];
        this.f1742V = new byte[32];
        this.f1743W = new byte[32];
        reset(gOST3411Digest);
    }

    public GOST3411Digest(byte[] bArr) {
        this.f1736H = new byte[32];
        this.f1738L = new byte[32];
        this.f1739M = new byte[32];
        this.Sum = new byte[32];
        this.f1735C = (byte[][]) Array.newInstance(byte.class, 4, 32);
        this.xBuf = new byte[32];
        this.cipher = new GOST28147Engine();
        this.f1737K = new byte[32];
        this.f1744a = new byte[8];
        this.f1745wS = new short[16];
        this.w_S = new short[16];
        this.f1740S = new byte[32];
        this.f1741U = new byte[32];
        this.f1742V = new byte[32];
        this.f1743W = new byte[32];
        byte[] clone = Arrays.clone(bArr);
        this.sBox = clone;
        this.cipher.init(true, new ParametersWithSBox(null, clone));
        reset();
    }

    /* renamed from: A */
    private byte[] m121A(byte[] bArr) {
        for (int r1 = 0; r1 < 8; r1++) {
            this.f1744a[r1] = (byte) (bArr[r1] ^ bArr[r1 + 8]);
        }
        System.arraycopy(bArr, 8, bArr, 0, 24);
        System.arraycopy(this.f1744a, 0, bArr, 24, 8);
        return bArr;
    }

    /* renamed from: E */
    private void m120E(byte[] bArr, byte[] bArr2, int r5, byte[] bArr3, int r7) {
        this.cipher.init(true, new KeyParameter(bArr));
        this.cipher.processBlock(bArr3, r7, bArr2, r5);
    }

    /* renamed from: P */
    private byte[] m119P(byte[] bArr) {
        for (int r0 = 0; r0 < 8; r0++) {
            byte[] bArr2 = this.f1737K;
            int r2 = r0 * 4;
            bArr2[r2] = bArr[r0];
            bArr2[r2 + 1] = bArr[r0 + 8];
            bArr2[r2 + 2] = bArr[r0 + 16];
            bArr2[r2 + 3] = bArr[r0 + 24];
        }
        return this.f1737K;
    }

    private void cpyBytesToShort(byte[] bArr, short[] sArr) {
        for (int r0 = 0; r0 < bArr.length / 2; r0++) {
            int r1 = r0 * 2;
            sArr[r0] = (short) ((bArr[r1] & 255) | ((bArr[r1 + 1] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK));
        }
    }

    private void cpyShortToBytes(short[] sArr, byte[] bArr) {
        for (int r0 = 0; r0 < bArr.length / 2; r0++) {
            int r1 = r0 * 2;
            bArr[r1 + 1] = (byte) (sArr[r0] >> 8);
            bArr[r1] = (byte) sArr[r0];
        }
    }

    private void finish() {
        Pack.longToLittleEndian(this.byteCount * 8, this.f1738L, 0);
        while (this.xBufOff != 0) {
            update((byte) 0);
        }
        processBlock(this.f1738L, 0);
        processBlock(this.Sum, 0);
    }

    /* renamed from: fw */
    private void m118fw(byte[] bArr) {
        cpyBytesToShort(bArr, this.f1745wS);
        short[] sArr = this.w_S;
        short[] sArr2 = this.f1745wS;
        sArr[15] = (short) (((((sArr2[0] ^ sArr2[1]) ^ sArr2[2]) ^ sArr2[3]) ^ sArr2[12]) ^ sArr2[15]);
        System.arraycopy(sArr2, 1, sArr, 0, 15);
        cpyShortToBytes(this.w_S, bArr);
    }

    private void sumByteArray(byte[] bArr) {
        int r0 = 0;
        int r1 = 0;
        while (true) {
            byte[] bArr2 = this.Sum;
            if (r0 == bArr2.length) {
                return;
            }
            int r3 = (bArr2[r0] & 255) + (bArr[r0] & 255) + r1;
            bArr2[r0] = (byte) r3;
            r1 = r3 >>> 8;
            r0++;
        }
    }

    @Override // org.bouncycastle.util.Memoable
    public Memoable copy() {
        return new GOST3411Digest(this);
    }

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int r5) {
        finish();
        byte[] bArr2 = this.f1736H;
        System.arraycopy(bArr2, 0, bArr, r5, bArr2.length);
        reset();
        return 32;
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return "GOST3411";
    }

    @Override // org.bouncycastle.crypto.ExtendedDigest
    public int getByteLength() {
        return 32;
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return 32;
    }

    protected void processBlock(byte[] bArr, int r11) {
        System.arraycopy(bArr, r11, this.f1739M, 0, 32);
        System.arraycopy(this.f1736H, 0, this.f1741U, 0, 32);
        System.arraycopy(this.f1739M, 0, this.f1742V, 0, 32);
        for (int r10 = 0; r10 < 32; r10++) {
            this.f1743W[r10] = (byte) (this.f1741U[r10] ^ this.f1742V[r10]);
        }
        m120E(m119P(this.f1743W), this.f1740S, 0, this.f1736H, 0);
        for (int r102 = 1; r102 < 4; r102++) {
            byte[] m121A = m121A(this.f1741U);
            for (int r0 = 0; r0 < 32; r0++) {
                this.f1741U[r0] = (byte) (m121A[r0] ^ this.f1735C[r102][r0]);
            }
            this.f1742V = m121A(m121A(this.f1742V));
            for (int r112 = 0; r112 < 32; r112++) {
                this.f1743W[r112] = (byte) (this.f1741U[r112] ^ this.f1742V[r112]);
            }
            int r8 = r102 * 8;
            m120E(m119P(this.f1743W), this.f1740S, r8, this.f1736H, r8);
        }
        for (int r103 = 0; r103 < 12; r103++) {
            m118fw(this.f1740S);
        }
        for (int r104 = 0; r104 < 32; r104++) {
            byte[] bArr2 = this.f1740S;
            bArr2[r104] = (byte) (bArr2[r104] ^ this.f1739M[r104]);
        }
        m118fw(this.f1740S);
        for (int r105 = 0; r105 < 32; r105++) {
            byte[] bArr3 = this.f1740S;
            bArr3[r105] = (byte) (this.f1736H[r105] ^ bArr3[r105]);
        }
        for (int r106 = 0; r106 < 61; r106++) {
            m118fw(this.f1740S);
        }
        byte[] bArr4 = this.f1740S;
        byte[] bArr5 = this.f1736H;
        System.arraycopy(bArr4, 0, bArr5, 0, bArr5.length);
    }

    @Override // org.bouncycastle.crypto.Digest
    public void reset() {
        this.byteCount = 0L;
        this.xBufOff = 0;
        int r1 = 0;
        while (true) {
            byte[] bArr = this.f1736H;
            if (r1 >= bArr.length) {
                break;
            }
            bArr[r1] = 0;
            r1++;
        }
        int r12 = 0;
        while (true) {
            byte[] bArr2 = this.f1738L;
            if (r12 >= bArr2.length) {
                break;
            }
            bArr2[r12] = 0;
            r12++;
        }
        int r13 = 0;
        while (true) {
            byte[] bArr3 = this.f1739M;
            if (r13 >= bArr3.length) {
                break;
            }
            bArr3[r13] = 0;
            r13++;
        }
        int r14 = 0;
        while (true) {
            byte[][] bArr4 = this.f1735C;
            if (r14 >= bArr4[1].length) {
                break;
            }
            bArr4[1][r14] = 0;
            r14++;
        }
        int r15 = 0;
        while (true) {
            byte[][] bArr5 = this.f1735C;
            if (r15 >= bArr5[3].length) {
                break;
            }
            bArr5[3][r15] = 0;
            r15++;
        }
        int r16 = 0;
        while (true) {
            byte[] bArr6 = this.Sum;
            if (r16 >= bArr6.length) {
                break;
            }
            bArr6[r16] = 0;
            r16++;
        }
        int r17 = 0;
        while (true) {
            byte[] bArr7 = this.xBuf;
            if (r17 >= bArr7.length) {
                byte[] bArr8 = f1734C2;
                System.arraycopy(bArr8, 0, this.f1735C[2], 0, bArr8.length);
                return;
            }
            bArr7[r17] = 0;
            r17++;
        }
    }

    @Override // org.bouncycastle.util.Memoable
    public void reset(Memoable memoable) {
        GOST3411Digest gOST3411Digest = (GOST3411Digest) memoable;
        byte[] bArr = gOST3411Digest.sBox;
        this.sBox = bArr;
        this.cipher.init(true, new ParametersWithSBox(null, bArr));
        reset();
        byte[] bArr2 = gOST3411Digest.f1736H;
        System.arraycopy(bArr2, 0, this.f1736H, 0, bArr2.length);
        byte[] bArr3 = gOST3411Digest.f1738L;
        System.arraycopy(bArr3, 0, this.f1738L, 0, bArr3.length);
        byte[] bArr4 = gOST3411Digest.f1739M;
        System.arraycopy(bArr4, 0, this.f1739M, 0, bArr4.length);
        byte[] bArr5 = gOST3411Digest.Sum;
        System.arraycopy(bArr5, 0, this.Sum, 0, bArr5.length);
        byte[][] bArr6 = gOST3411Digest.f1735C;
        System.arraycopy(bArr6[1], 0, this.f1735C[1], 0, bArr6[1].length);
        byte[][] bArr7 = gOST3411Digest.f1735C;
        System.arraycopy(bArr7[2], 0, this.f1735C[2], 0, bArr7[2].length);
        byte[][] bArr8 = gOST3411Digest.f1735C;
        System.arraycopy(bArr8[3], 0, this.f1735C[3], 0, bArr8[3].length);
        byte[] bArr9 = gOST3411Digest.xBuf;
        System.arraycopy(bArr9, 0, this.xBuf, 0, bArr9.length);
        this.xBufOff = gOST3411Digest.xBufOff;
        this.byteCount = gOST3411Digest.byteCount;
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte b) {
        byte[] bArr = this.xBuf;
        int r1 = this.xBufOff;
        int r2 = r1 + 1;
        this.xBufOff = r2;
        bArr[r1] = b;
        if (r2 == bArr.length) {
            sumByteArray(bArr);
            processBlock(this.xBuf, 0);
            this.xBufOff = 0;
        }
        this.byteCount++;
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte[] bArr, int r7, int r8) {
        while (this.xBufOff != 0 && r8 > 0) {
            update(bArr[r7]);
            r7++;
            r8--;
        }
        while (true) {
            byte[] bArr2 = this.xBuf;
            if (r8 <= bArr2.length) {
                break;
            }
            System.arraycopy(bArr, r7, bArr2, 0, bArr2.length);
            sumByteArray(this.xBuf);
            processBlock(this.xBuf, 0);
            byte[] bArr3 = this.xBuf;
            r7 += bArr3.length;
            r8 -= bArr3.length;
            this.byteCount += bArr3.length;
        }
        while (r8 > 0) {
            update(bArr[r7]);
            r7++;
            r8--;
        }
    }
}
