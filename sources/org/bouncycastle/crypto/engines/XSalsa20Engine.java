package org.bouncycastle.crypto.engines;

import org.bouncycastle.util.Pack;

/* loaded from: classes5.dex */
public class XSalsa20Engine extends Salsa20Engine {
    @Override // org.bouncycastle.crypto.engines.Salsa20Engine, org.bouncycastle.crypto.StreamCipher
    public String getAlgorithmName() {
        return "XSalsa20";
    }

    @Override // org.bouncycastle.crypto.engines.Salsa20Engine
    protected int getNonceSize() {
        return 24;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.bouncycastle.crypto.engines.Salsa20Engine
    public void setKey(byte[] bArr, byte[] bArr2) {
        if (bArr == null) {
            throw new IllegalArgumentException(getAlgorithmName() + " doesn't support re-init with null key");
        } else if (bArr.length != 32) {
            throw new IllegalArgumentException(getAlgorithmName() + " requires a 256 bit key");
        } else {
            super.setKey(bArr, bArr2);
            Pack.littleEndianToInt(bArr2, 8, this.engineState, 8, 2);
            int[] r9 = new int[this.engineState.length];
            salsaCore(20, this.engineState, r9);
            this.engineState[1] = r9[0] - this.engineState[0];
            this.engineState[2] = r9[5] - this.engineState[5];
            this.engineState[3] = r9[10] - this.engineState[10];
            this.engineState[4] = r9[15] - this.engineState[15];
            this.engineState[11] = r9[6] - this.engineState[6];
            this.engineState[12] = r9[7] - this.engineState[7];
            this.engineState[13] = r9[8] - this.engineState[8];
            this.engineState[14] = r9[9] - this.engineState[9];
            Pack.littleEndianToInt(bArr2, 16, this.engineState, 6, 2);
        }
    }
}
