package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;

/* loaded from: classes5.dex */
public abstract class SerpentEngineBase implements BlockCipher {
    protected static final int BLOCK_SIZE = 16;
    static final int PHI = -1640531527;
    static final int ROUNDS = 32;

    /* renamed from: X0 */
    protected int f1963X0;

    /* renamed from: X1 */
    protected int f1964X1;

    /* renamed from: X2 */
    protected int f1965X2;

    /* renamed from: X3 */
    protected int f1966X3;
    protected boolean encrypting;
    protected int[] wKey;

    /* JADX INFO: Access modifiers changed from: protected */
    public static int rotateLeft(int r1, int r2) {
        return (r1 >>> (-r2)) | (r1 << r2);
    }

    protected static int rotateRight(int r1, int r2) {
        return (r1 << (-r2)) | (r1 >>> r2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: LT */
    public final void m42LT() {
        int rotateLeft = rotateLeft(this.f1963X0, 13);
        int rotateLeft2 = rotateLeft(this.f1965X2, 3);
        this.f1964X1 = rotateLeft((this.f1964X1 ^ rotateLeft) ^ rotateLeft2, 1);
        int rotateLeft3 = rotateLeft((this.f1966X3 ^ rotateLeft2) ^ (rotateLeft << 3), 7);
        this.f1966X3 = rotateLeft3;
        this.f1963X0 = rotateLeft((rotateLeft ^ this.f1964X1) ^ rotateLeft3, 5);
        this.f1965X2 = rotateLeft((this.f1966X3 ^ rotateLeft2) ^ (this.f1964X1 << 7), 22);
    }

    protected abstract void decryptBlock(byte[] bArr, int r2, byte[] bArr2, int r4);

    protected abstract void encryptBlock(byte[] bArr, int r2, byte[] bArr2, int r4);

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "Serpent";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 16;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void ib0(int r4, int r5, int r6, int r7) {
        int r0 = ~r4;
        int r52 = r5 ^ r4;
        int r1 = (r0 | r52) ^ r7;
        int r62 = r6 ^ r1;
        int r2 = r52 ^ r62;
        this.f1965X2 = r2;
        int r53 = (r52 & r7) ^ r0;
        int r72 = (r2 & r53) ^ r1;
        this.f1964X1 = r72;
        int r42 = (r4 & r1) ^ (r72 | r62);
        this.f1966X3 = r42;
        this.f1963X0 = r42 ^ (r53 ^ r62);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void ib1(int r2, int r3, int r4, int r5) {
        int r52 = r5 ^ r3;
        int r22 = r2 ^ (r3 & r52);
        int r0 = r52 ^ r22;
        int r42 = r4 ^ r0;
        this.f1966X3 = r42;
        int r32 = r3 ^ (r52 & r22);
        int r23 = r22 ^ (r42 | r32);
        this.f1964X1 = r23;
        int r24 = ~r23;
        int r33 = r32 ^ r42;
        this.f1963X0 = r24 ^ r33;
        this.f1965X2 = (r24 | r33) ^ r0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void ib2(int r4, int r5, int r6, int r7) {
        int r0 = r5 ^ r7;
        int r1 = ~r0;
        int r2 = r4 ^ r6;
        int r62 = r6 ^ r0;
        int r52 = (r5 & r62) ^ r2;
        this.f1963X0 = r52;
        int r42 = (((r4 | r1) ^ r7) | r2) ^ r0;
        this.f1966X3 = r42;
        int r63 = ~r62;
        int r43 = r42 | r52;
        this.f1964X1 = r63 ^ r43;
        this.f1965X2 = (r43 ^ r2) ^ (r7 & r63);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void ib3(int r4, int r5, int r6, int r7) {
        int r0 = r4 | r5;
        int r1 = r5 ^ r6;
        int r42 = r4 ^ (r5 & r1);
        int r52 = r6 ^ r42;
        int r62 = r7 | r42;
        int r2 = r1 ^ r62;
        this.f1963X0 = r2;
        int r63 = (r62 | r1) ^ r7;
        this.f1965X2 = r52 ^ r63;
        int r53 = r0 ^ r63;
        int r43 = r42 ^ (r2 & r53);
        this.f1966X3 = r43;
        this.f1964X1 = r43 ^ (r53 ^ r2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void ib4(int r3, int r4, int r5, int r6) {
        int r42 = r4 ^ ((r5 | r6) & r3);
        int r52 = r5 ^ (r3 & r42);
        int r0 = r6 ^ r52;
        this.f1964X1 = r0;
        int r32 = ~r3;
        int r53 = (r52 & r0) ^ r42;
        this.f1966X3 = r53;
        int r62 = r6 ^ (r0 | r32);
        this.f1963X0 = r53 ^ r62;
        this.f1965X2 = (r32 ^ r0) ^ (r42 & r62);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void ib5(int r6, int r7, int r8, int r9) {
        int r0 = ~r8;
        int r1 = (r7 & r0) ^ r9;
        int r2 = r6 & r1;
        int r3 = (r7 ^ r0) ^ r2;
        this.f1966X3 = r3;
        int r32 = r3 | r7;
        this.f1964X1 = r1 ^ (r6 & r32);
        int r92 = r9 | r6;
        this.f1963X0 = (r0 ^ r32) ^ r92;
        this.f1965X2 = ((r6 ^ r8) | r2) ^ (r7 & r92);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void ib6(int r4, int r5, int r6, int r7) {
        int r0 = ~r4;
        int r42 = r4 ^ r5;
        int r1 = r6 ^ r42;
        int r62 = (r6 | r0) ^ r7;
        this.f1964X1 = r1 ^ r62;
        int r43 = r42 ^ (r1 & r62);
        int r63 = r62 ^ (r5 | r43);
        this.f1966X3 = r63;
        int r52 = r5 | r63;
        this.f1963X0 = r43 ^ r52;
        this.f1965X2 = (r7 & r0) ^ (r52 ^ r1);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void ib7(int r5, int r6, int r7, int r8) {
        int r0 = (r5 & r6) | r7;
        int r1 = (r5 | r6) & r8;
        int r2 = r0 ^ r1;
        this.f1966X3 = r2;
        int r62 = r6 ^ r1;
        int r12 = ((r2 ^ (~r8)) | r62) ^ r5;
        this.f1964X1 = r12;
        int r63 = (r62 ^ r7) ^ (r8 | r12);
        this.f1963X0 = r63;
        this.f1965X2 = ((r5 & r2) ^ r63) ^ (r0 ^ r12);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof KeyParameter) {
            this.encrypting = z;
            this.wKey = makeWorkingKey(((KeyParameter) cipherParameters).getKey());
            return;
        }
        throw new IllegalArgumentException("invalid parameter passed to " + getAlgorithmName() + " init - " + cipherParameters.getClass().getName());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void inverseLT() {
        int rotateRight = (rotateRight(this.f1965X2, 22) ^ this.f1966X3) ^ (this.f1964X1 << 7);
        int rotateRight2 = rotateRight(this.f1963X0, 5) ^ this.f1964X1;
        int r3 = this.f1966X3;
        int r1 = rotateRight2 ^ r3;
        int rotateRight3 = rotateRight(r3, 7);
        int rotateRight4 = rotateRight(this.f1964X1, 1);
        this.f1966X3 = (rotateRight3 ^ rotateRight) ^ (r1 << 3);
        this.f1964X1 = (rotateRight4 ^ r1) ^ rotateRight;
        this.f1965X2 = rotateRight(rotateRight, 3);
        this.f1963X0 = rotateRight(r1, 13);
    }

    protected abstract int[] makeWorkingKey(byte[] bArr);

    @Override // org.bouncycastle.crypto.BlockCipher
    public final int processBlock(byte[] bArr, int r4, byte[] bArr2, int r6) {
        if (this.wKey == null) {
            throw new IllegalStateException(getAlgorithmName() + " not initialised");
        } else if (r4 + 16 <= bArr.length) {
            if (r6 + 16 <= bArr2.length) {
                if (this.encrypting) {
                    encryptBlock(bArr, r4, bArr2, r6);
                    return 16;
                }
                decryptBlock(bArr, r4, bArr2, r6);
                return 16;
            }
            throw new OutputLengthException("output buffer too short");
        } else {
            throw new DataLengthException("input buffer too short");
        }
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void sb0(int r4, int r5, int r6, int r7) {
        int r0 = r4 ^ r7;
        int r1 = r6 ^ r0;
        int r2 = r5 ^ r1;
        int r72 = (r7 & r4) ^ r2;
        this.f1966X3 = r72;
        int r42 = r4 ^ (r5 & r0);
        this.f1965X2 = (r6 | r42) ^ r2;
        int r52 = (r1 ^ r42) & r72;
        this.f1964X1 = (~r1) ^ r52;
        this.f1963X0 = (~r42) ^ r52;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void sb1(int r2, int r3, int r4, int r5) {
        int r0 = (~r2) ^ r3;
        int r22 = (r2 | r0) ^ r4;
        int r42 = r5 ^ r22;
        this.f1965X2 = r42;
        int r32 = r3 ^ (r5 | r0);
        int r43 = r42 ^ r0;
        int r52 = (r22 & r32) ^ r43;
        this.f1966X3 = r52;
        int r33 = r32 ^ r22;
        this.f1964X1 = r52 ^ r33;
        this.f1963X0 = r22 ^ (r33 & r43);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void sb2(int r5, int r6, int r7, int r8) {
        int r0 = ~r5;
        int r1 = r6 ^ r8;
        int r2 = (r7 & r0) ^ r1;
        this.f1963X0 = r2;
        int r3 = r7 ^ r0;
        int r62 = r6 & (r7 ^ r2);
        int r72 = r3 ^ r62;
        this.f1966X3 = r72;
        int r52 = r5 ^ ((r62 | r8) & (r2 | r3));
        this.f1965X2 = r52;
        this.f1964X1 = (r52 ^ (r8 | r0)) ^ (r1 ^ r72);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void sb3(int r4, int r5, int r6, int r7) {
        int r0 = r4 ^ r5;
        int r1 = r4 & r6;
        int r42 = r4 | r7;
        int r62 = r6 ^ r7;
        int r12 = r1 | (r0 & r42);
        int r2 = r62 ^ r12;
        this.f1965X2 = r2;
        int r43 = (r42 ^ r5) ^ r12;
        int r02 = r0 ^ (r62 & r43);
        this.f1963X0 = r02;
        int r03 = r02 & r2;
        this.f1964X1 = r43 ^ r03;
        this.f1966X3 = (r5 | r7) ^ (r62 ^ r03);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void sb4(int r3, int r4, int r5, int r6) {
        int r0 = r3 ^ r6;
        int r52 = r5 ^ (r6 & r0);
        int r62 = r4 | r52;
        this.f1966X3 = r0 ^ r62;
        int r42 = ~r4;
        int r1 = (r0 | r42) ^ r52;
        this.f1963X0 = r1;
        int r43 = r42 ^ r0;
        int r63 = (r62 & r43) ^ (r1 & r3);
        this.f1965X2 = r63;
        this.f1964X1 = (r3 ^ r52) ^ (r43 & r63);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void sb5(int r4, int r5, int r6, int r7) {
        int r0 = ~r4;
        int r1 = r4 ^ r5;
        int r42 = r4 ^ r7;
        int r62 = (r6 ^ r0) ^ (r1 | r42);
        this.f1963X0 = r62;
        int r72 = r7 & r62;
        int r2 = (r1 ^ r62) ^ r72;
        this.f1964X1 = r2;
        int r43 = r42 ^ (r62 | r0);
        this.f1965X2 = (r1 | r72) ^ r43;
        this.f1966X3 = (r43 & r2) ^ (r5 ^ r72);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void sb6(int r3, int r4, int r5, int r6) {
        int r0 = ~r3;
        int r32 = r3 ^ r6;
        int r1 = r4 ^ r32;
        int r52 = r5 ^ (r0 | r32);
        int r42 = r4 ^ r52;
        this.f1964X1 = r42;
        int r33 = (r32 | r42) ^ r6;
        int r43 = (r52 & r33) ^ r1;
        this.f1965X2 = r43;
        int r34 = r33 ^ r52;
        this.f1963X0 = r43 ^ r34;
        this.f1966X3 = (r34 & r1) ^ (~r52);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void sb7(int r3, int r4, int r5, int r6) {
        int r0 = r4 ^ r5;
        int r52 = (r5 & r0) ^ r6;
        int r1 = r3 ^ r52;
        int r42 = r4 ^ ((r6 | r0) & r1);
        this.f1964X1 = r42;
        int r32 = (r3 & r1) ^ r0;
        this.f1966X3 = r32;
        int r43 = (r42 | r52) ^ r1;
        int r53 = r52 ^ (r32 & r43);
        this.f1965X2 = r53;
        this.f1963X0 = (r32 & r53) ^ (~r43);
    }
}
