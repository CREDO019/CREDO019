package org.bouncycastle.crypto.generators;

import androidx.core.view.InputDeviceCompat;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.DerivationParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.DigestDerivationFunction;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.ISO18033KDFParameters;
import org.bouncycastle.crypto.params.KDFParameters;
import org.bouncycastle.util.Pack;

/* loaded from: classes5.dex */
public class BaseKDFBytesGenerator implements DigestDerivationFunction {
    private int counterStart;
    private Digest digest;

    /* renamed from: iv */
    private byte[] f1991iv;
    private byte[] shared;

    /* JADX INFO: Access modifiers changed from: protected */
    public BaseKDFBytesGenerator(int r1, Digest digest) {
        this.counterStart = r1;
        this.digest = digest;
    }

    @Override // org.bouncycastle.crypto.DerivationFunction
    public int generateBytes(byte[] bArr, int r18, int r19) throws DataLengthException, IllegalArgumentException {
        int r2 = r19;
        int r4 = r18;
        if (bArr.length - r2 >= r4) {
            long j = r2;
            int digestSize = this.digest.getDigestSize();
            if (j <= 8589934591L) {
                long j2 = digestSize;
                int r7 = (int) (((j + j2) - 1) / j2);
                byte[] bArr2 = new byte[this.digest.getDigestSize()];
                byte[] bArr3 = new byte[4];
                Pack.intToBigEndian(this.counterStart, bArr3, 0);
                int r11 = this.counterStart & InputDeviceCompat.SOURCE_ANY;
                for (int r13 = 0; r13 < r7; r13++) {
                    Digest digest = this.digest;
                    byte[] bArr4 = this.shared;
                    digest.update(bArr4, 0, bArr4.length);
                    this.digest.update(bArr3, 0, 4);
                    byte[] bArr5 = this.f1991iv;
                    if (bArr5 != null) {
                        this.digest.update(bArr5, 0, bArr5.length);
                    }
                    this.digest.doFinal(bArr2, 0);
                    if (r2 > digestSize) {
                        System.arraycopy(bArr2, 0, bArr, r4, digestSize);
                        r4 += digestSize;
                        r2 -= digestSize;
                    } else {
                        System.arraycopy(bArr2, 0, bArr, r4, r2);
                    }
                    byte b = (byte) (bArr3[3] + 1);
                    bArr3[3] = b;
                    if (b == 0) {
                        r11 += 256;
                        Pack.intToBigEndian(r11, bArr3, 0);
                    }
                }
                this.digest.reset();
                return (int) j;
            }
            throw new IllegalArgumentException("Output length too large");
        }
        throw new OutputLengthException("output buffer too small");
    }

    @Override // org.bouncycastle.crypto.DigestDerivationFunction
    public Digest getDigest() {
        return this.digest;
    }

    @Override // org.bouncycastle.crypto.DerivationFunction
    public void init(DerivationParameters derivationParameters) {
        if (derivationParameters instanceof KDFParameters) {
            KDFParameters kDFParameters = (KDFParameters) derivationParameters;
            this.shared = kDFParameters.getSharedSecret();
            this.f1991iv = kDFParameters.getIV();
        } else if (!(derivationParameters instanceof ISO18033KDFParameters)) {
            throw new IllegalArgumentException("KDF parameters required for generator");
        } else {
            this.shared = ((ISO18033KDFParameters) derivationParameters).getSeed();
            this.f1991iv = null;
        }
    }
}
