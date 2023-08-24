package org.bouncycastle.crypto.agreement.kdf;

import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.DerivationFunction;
import org.bouncycastle.crypto.DerivationParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KDFParameters;

/* loaded from: classes5.dex */
public class ConcatenationKDFGenerator implements DerivationFunction {
    private Digest digest;
    private int hLen;
    private byte[] otherInfo;
    private byte[] shared;

    public ConcatenationKDFGenerator(Digest digest) {
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
    public int generateBytes(byte[] bArr, int r11, int r12) throws DataLengthException, IllegalArgumentException {
        int r3;
        int r7;
        if (bArr.length - r12 >= r11) {
            byte[] bArr2 = new byte[this.hLen];
            byte[] bArr3 = new byte[4];
            this.digest.reset();
            int r4 = 1;
            if (r12 > this.hLen) {
                r3 = 0;
                while (true) {
                    ItoOSP(r4, bArr3);
                    this.digest.update(bArr3, 0, 4);
                    Digest digest = this.digest;
                    byte[] bArr4 = this.shared;
                    digest.update(bArr4, 0, bArr4.length);
                    Digest digest2 = this.digest;
                    byte[] bArr5 = this.otherInfo;
                    digest2.update(bArr5, 0, bArr5.length);
                    this.digest.doFinal(bArr2, 0);
                    System.arraycopy(bArr2, 0, bArr, r11 + r3, this.hLen);
                    int r6 = this.hLen;
                    r3 += r6;
                    r7 = r4 + 1;
                    if (r4 >= r12 / r6) {
                        break;
                    }
                    r4 = r7;
                }
                r4 = r7;
            } else {
                r3 = 0;
            }
            if (r3 < r12) {
                ItoOSP(r4, bArr3);
                this.digest.update(bArr3, 0, 4);
                Digest digest3 = this.digest;
                byte[] bArr6 = this.shared;
                digest3.update(bArr6, 0, bArr6.length);
                Digest digest4 = this.digest;
                byte[] bArr7 = this.otherInfo;
                digest4.update(bArr7, 0, bArr7.length);
                this.digest.doFinal(bArr2, 0);
                System.arraycopy(bArr2, 0, bArr, r11 + r3, r12 - r3);
            }
            return r12;
        }
        throw new OutputLengthException("output buffer too small");
    }

    public Digest getDigest() {
        return this.digest;
    }

    @Override // org.bouncycastle.crypto.DerivationFunction
    public void init(DerivationParameters derivationParameters) {
        if (!(derivationParameters instanceof KDFParameters)) {
            throw new IllegalArgumentException("KDF parameters required for generator");
        }
        KDFParameters kDFParameters = (KDFParameters) derivationParameters;
        this.shared = kDFParameters.getSharedSecret();
        this.otherInfo = kDFParameters.getIV();
    }
}
