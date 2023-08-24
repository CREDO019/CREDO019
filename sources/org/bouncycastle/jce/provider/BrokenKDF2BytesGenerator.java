package org.bouncycastle.jce.provider;

import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.DerivationFunction;
import org.bouncycastle.crypto.DerivationParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KDFParameters;

/* loaded from: classes5.dex */
public class BrokenKDF2BytesGenerator implements DerivationFunction {
    private Digest digest;

    /* renamed from: iv */
    private byte[] f2218iv;
    private byte[] shared;

    public BrokenKDF2BytesGenerator(Digest digest) {
        this.digest = digest;
    }

    @Override // org.bouncycastle.crypto.DerivationFunction
    public int generateBytes(byte[] bArr, int r10, int r11) throws DataLengthException, IllegalArgumentException {
        if (bArr.length - r11 >= r10) {
            long j = r11 * 8;
            if (j <= this.digest.getDigestSize() * 8 * 2147483648L) {
                int digestSize = (int) (j / this.digest.getDigestSize());
                int digestSize2 = this.digest.getDigestSize();
                byte[] bArr2 = new byte[digestSize2];
                for (int r3 = 1; r3 <= digestSize; r3++) {
                    Digest digest = this.digest;
                    byte[] bArr3 = this.shared;
                    digest.update(bArr3, 0, bArr3.length);
                    this.digest.update((byte) (r3 & 255));
                    this.digest.update((byte) ((r3 >> 8) & 255));
                    this.digest.update((byte) ((r3 >> 16) & 255));
                    this.digest.update((byte) ((r3 >> 24) & 255));
                    Digest digest2 = this.digest;
                    byte[] bArr4 = this.f2218iv;
                    digest2.update(bArr4, 0, bArr4.length);
                    this.digest.doFinal(bArr2, 0);
                    int r4 = r11 - r10;
                    if (r4 > digestSize2) {
                        System.arraycopy(bArr2, 0, bArr, r10, digestSize2);
                        r10 += digestSize2;
                    } else {
                        System.arraycopy(bArr2, 0, bArr, r10, r4);
                    }
                }
                this.digest.reset();
                return r11;
            }
            throw new IllegalArgumentException("Output length too large");
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
        this.f2218iv = kDFParameters.getIV();
    }
}
