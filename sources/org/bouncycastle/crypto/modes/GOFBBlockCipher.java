package org.bouncycastle.crypto.modes;

import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewCompat;
import com.google.common.base.Ascii;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.StreamBlockCipher;
import org.bouncycastle.crypto.params.ParametersWithIV;

/* loaded from: classes5.dex */
public class GOFBBlockCipher extends StreamBlockCipher {

    /* renamed from: C1 */
    static final int f2060C1 = 16843012;

    /* renamed from: C2 */
    static final int f2061C2 = 16843009;

    /* renamed from: IV */
    private byte[] f2062IV;

    /* renamed from: N3 */
    int f2063N3;

    /* renamed from: N4 */
    int f2064N4;
    private final int blockSize;
    private int byteCount;
    private final BlockCipher cipher;
    boolean firstStep;
    private byte[] ofbOutV;
    private byte[] ofbV;

    public GOFBBlockCipher(BlockCipher blockCipher) {
        super(blockCipher);
        this.firstStep = true;
        this.cipher = blockCipher;
        int blockSize = blockCipher.getBlockSize();
        this.blockSize = blockSize;
        if (blockSize != 8) {
            throw new IllegalArgumentException("GCTR only for 64 bit block ciphers");
        }
        this.f2062IV = new byte[blockCipher.getBlockSize()];
        this.ofbV = new byte[blockCipher.getBlockSize()];
        this.ofbOutV = new byte[blockCipher.getBlockSize()];
    }

    private int bytesToint(byte[] bArr, int r5) {
        return ((bArr[r5 + 3] << Ascii.CAN) & ViewCompat.MEASURED_STATE_MASK) + ((bArr[r5 + 2] << 16) & 16711680) + ((bArr[r5 + 1] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) + (bArr[r5] & 255);
    }

    private void intTobytes(int r3, byte[] bArr, int r5) {
        bArr[r5 + 3] = (byte) (r3 >>> 24);
        bArr[r5 + 2] = (byte) (r3 >>> 16);
        bArr[r5 + 1] = (byte) (r3 >>> 8);
        bArr[r5] = (byte) r3;
    }

    @Override // org.bouncycastle.crypto.StreamBlockCipher
    protected byte calculateByte(byte b) {
        if (this.byteCount == 0) {
            if (this.firstStep) {
                this.firstStep = false;
                this.cipher.processBlock(this.ofbV, 0, this.ofbOutV, 0);
                this.f2063N3 = bytesToint(this.ofbOutV, 0);
                this.f2064N4 = bytesToint(this.ofbOutV, 4);
            }
            int r0 = this.f2063N3 + f2061C2;
            this.f2063N3 = r0;
            int r3 = this.f2064N4 + f2060C1;
            this.f2064N4 = r3;
            if (r3 < f2060C1 && r3 > 0) {
                this.f2064N4 = r3 + 1;
            }
            intTobytes(r0, this.ofbV, 0);
            intTobytes(this.f2064N4, this.ofbV, 4);
            this.cipher.processBlock(this.ofbV, 0, this.ofbOutV, 0);
        }
        byte[] bArr = this.ofbOutV;
        int r2 = this.byteCount;
        int r32 = r2 + 1;
        this.byteCount = r32;
        byte b2 = (byte) (b ^ bArr[r2]);
        int r02 = this.blockSize;
        if (r32 == r02) {
            this.byteCount = 0;
            byte[] bArr2 = this.ofbV;
            System.arraycopy(bArr2, r02, bArr2, 0, bArr2.length - r02);
            byte[] bArr3 = this.ofbOutV;
            byte[] bArr4 = this.ofbV;
            int length = bArr4.length;
            int r4 = this.blockSize;
            System.arraycopy(bArr3, 0, bArr4, length - r4, r4);
        }
        return b2;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return this.cipher.getAlgorithmName() + "/GCTR";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return this.blockSize;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        BlockCipher blockCipher;
        this.firstStep = true;
        this.f2063N3 = 0;
        this.f2064N4 = 0;
        if (cipherParameters instanceof ParametersWithIV) {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            byte[] bArr = parametersWithIV.getIV();
            int length = bArr.length;
            byte[] bArr2 = this.f2062IV;
            if (length < bArr2.length) {
                System.arraycopy(bArr, 0, bArr2, bArr2.length - bArr.length, bArr.length);
                int r2 = 0;
                while (true) {
                    byte[] bArr3 = this.f2062IV;
                    if (r2 >= bArr3.length - bArr.length) {
                        break;
                    }
                    bArr3[r2] = 0;
                    r2++;
                }
            } else {
                System.arraycopy(bArr, 0, bArr2, 0, bArr2.length);
            }
            reset();
            if (parametersWithIV.getParameters() == null) {
                return;
            }
            blockCipher = this.cipher;
            cipherParameters = parametersWithIV.getParameters();
        } else {
            reset();
            if (cipherParameters == null) {
                return;
            }
            blockCipher = this.cipher;
        }
        blockCipher.init(true, cipherParameters);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int r8, byte[] bArr2, int r10) throws DataLengthException, IllegalStateException {
        processBytes(bArr, r8, this.blockSize, bArr2, r10);
        return this.blockSize;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
        this.firstStep = true;
        this.f2063N3 = 0;
        this.f2064N4 = 0;
        byte[] bArr = this.f2062IV;
        System.arraycopy(bArr, 0, this.ofbV, 0, bArr.length);
        this.byteCount = 0;
        this.cipher.reset();
    }
}
