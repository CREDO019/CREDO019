package org.bouncycastle.crypto.generators;

import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.DerivationFunction;
import org.bouncycastle.crypto.DerivationParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.MGFParameters;

/* loaded from: classes5.dex */
public class MGF1BytesGenerator implements DerivationFunction {
    private Digest digest;
    private int hLen;
    private byte[] seed;

    public MGF1BytesGenerator(Digest digest) {
        this.digest = digest;
        this.hLen = digest.getDigestSize();
    }

    private void ItoOSP(int r4, byte[] bArr) {
        bArr[0] = (byte) (r4 >>> 24);
        bArr[1] = (byte) (r4 >>> 16);
        bArr[2] = (byte) (r4 >>> 8);
        bArr[3] = (byte) (r4 >>> 0);
    }

    @Override // org.bouncycastle.crypto.DerivationFunction
    public int generateBytes(byte[] bArr, int r10, int r11) throws DataLengthException, IllegalArgumentException {
        int r3;
        if (bArr.length - r11 >= r10) {
            byte[] bArr2 = new byte[this.hLen];
            byte[] bArr3 = new byte[4];
            this.digest.reset();
            if (r11 > this.hLen) {
                r3 = 0;
                do {
                    ItoOSP(r3, bArr3);
                    Digest digest = this.digest;
                    byte[] bArr4 = this.seed;
                    digest.update(bArr4, 0, bArr4.length);
                    this.digest.update(bArr3, 0, 4);
                    this.digest.doFinal(bArr2, 0);
                    int r5 = this.hLen;
                    System.arraycopy(bArr2, 0, bArr, (r3 * r5) + r10, r5);
                    r3++;
                } while (r3 < r11 / this.hLen);
            } else {
                r3 = 0;
            }
            if (this.hLen * r3 < r11) {
                ItoOSP(r3, bArr3);
                Digest digest2 = this.digest;
                byte[] bArr5 = this.seed;
                digest2.update(bArr5, 0, bArr5.length);
                this.digest.update(bArr3, 0, 4);
                this.digest.doFinal(bArr2, 0);
                int r1 = this.hLen;
                System.arraycopy(bArr2, 0, bArr, r10 + (r3 * r1), r11 - (r3 * r1));
            }
            return r11;
        }
        throw new OutputLengthException("output buffer too small");
    }

    public Digest getDigest() {
        return this.digest;
    }

    @Override // org.bouncycastle.crypto.DerivationFunction
    public void init(DerivationParameters derivationParameters) {
        if (!(derivationParameters instanceof MGFParameters)) {
            throw new IllegalArgumentException("MGF parameters required for MGF1Generator");
        }
        this.seed = ((MGFParameters) derivationParameters).getSeed();
    }
}
