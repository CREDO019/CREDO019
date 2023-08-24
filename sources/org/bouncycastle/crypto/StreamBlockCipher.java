package org.bouncycastle.crypto;

/* loaded from: classes5.dex */
public abstract class StreamBlockCipher implements BlockCipher, StreamCipher {
    private final BlockCipher cipher;

    /* JADX INFO: Access modifiers changed from: protected */
    public StreamBlockCipher(BlockCipher blockCipher) {
        this.cipher = blockCipher;
    }

    protected abstract byte calculateByte(byte b);

    public BlockCipher getUnderlyingCipher() {
        return this.cipher;
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public int processBytes(byte[] bArr, int r5, int r6, byte[] bArr2, int r8) throws DataLengthException {
        int r0 = r5 + r6;
        if (r0 <= bArr.length) {
            if (r8 + r6 <= bArr2.length) {
                while (r5 < r0) {
                    bArr2[r8] = calculateByte(bArr[r5]);
                    r8++;
                    r5++;
                }
                return r6;
            }
            throw new OutputLengthException("output buffer too short");
        }
        throw new DataLengthException("input buffer too small");
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public final byte returnByte(byte b) {
        return calculateByte(b);
    }
}
