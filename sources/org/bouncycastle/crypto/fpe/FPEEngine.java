package org.bouncycastle.crypto.fpe;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.FPEParameters;
import org.bouncycastle.util.Pack;

/* loaded from: classes5.dex */
public abstract class FPEEngine {
    protected final BlockCipher baseCipher;
    protected boolean forEncryption;
    protected FPEParameters fpeParameters;

    /* JADX INFO: Access modifiers changed from: protected */
    public FPEEngine(BlockCipher blockCipher) {
        this.baseCipher = blockCipher;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static byte[] toByteArray(short[] sArr) {
        byte[] bArr = new byte[sArr.length * 2];
        for (int r1 = 0; r1 != sArr.length; r1++) {
            Pack.shortToBigEndian(sArr[r1], bArr, r1 * 2);
        }
        return bArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static short[] toShortArray(byte[] bArr) {
        if ((bArr.length & 1) == 0) {
            int length = bArr.length / 2;
            short[] sArr = new short[length];
            for (int r2 = 0; r2 != length; r2++) {
                sArr[r2] = Pack.bigEndianToShort(bArr, r2 * 2);
            }
            return sArr;
        }
        throw new IllegalArgumentException("data must be an even number of bytes for a wide radix");
    }

    protected abstract int decryptBlock(byte[] bArr, int r2, int r3, byte[] bArr2, int r5);

    protected abstract int encryptBlock(byte[] bArr, int r2, int r3, byte[] bArr2, int r5);

    public abstract String getAlgorithmName();

    public abstract void init(boolean z, CipherParameters cipherParameters);

    public int processBlock(byte[] bArr, int r4, int r5, byte[] bArr2, int r7) {
        if (this.fpeParameters != null) {
            if (r5 >= 0) {
                if (bArr == null || bArr2 == null) {
                    throw new NullPointerException("buffer value is null");
                }
                if (bArr.length >= r4 + r5) {
                    if (bArr2.length >= r7 + r5) {
                        return this.forEncryption ? encryptBlock(bArr, r4, r5, bArr2, r7) : decryptBlock(bArr, r4, r5, bArr2, r7);
                    }
                    throw new OutputLengthException("output buffer too short");
                }
                throw new DataLengthException("input buffer too short");
            }
            throw new IllegalArgumentException("input length cannot be negative");
        }
        throw new IllegalStateException("FPE engine not initialized");
    }
}
