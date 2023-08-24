package org.bouncycastle.crypto.generators;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.PBEParametersGenerator;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.crypto.util.DigestFactory;

/* loaded from: classes5.dex */
public class OpenSSLPBEParametersGenerator extends PBEParametersGenerator {
    private final Digest digest;

    public OpenSSLPBEParametersGenerator() {
        this(DigestFactory.createMD5());
    }

    public OpenSSLPBEParametersGenerator(Digest digest) {
        this.digest = digest;
    }

    private byte[] generateDerivedKey(int r9) {
        int digestSize = this.digest.getDigestSize();
        byte[] bArr = new byte[digestSize];
        byte[] bArr2 = new byte[r9];
        int r4 = 0;
        while (true) {
            this.digest.update(this.password, 0, this.password.length);
            this.digest.update(this.salt, 0, this.salt.length);
            this.digest.doFinal(bArr, 0);
            int r5 = r9 > digestSize ? digestSize : r9;
            System.arraycopy(bArr, 0, bArr2, r4, r5);
            r4 += r5;
            r9 -= r5;
            if (r9 == 0) {
                return bArr2;
            }
            this.digest.reset();
            this.digest.update(bArr, 0, digestSize);
        }
    }

    @Override // org.bouncycastle.crypto.PBEParametersGenerator
    public CipherParameters generateDerivedMacParameters(int r1) {
        return generateDerivedParameters(r1);
    }

    @Override // org.bouncycastle.crypto.PBEParametersGenerator
    public CipherParameters generateDerivedParameters(int r4) {
        int r42 = r4 / 8;
        return new KeyParameter(generateDerivedKey(r42), 0, r42);
    }

    @Override // org.bouncycastle.crypto.PBEParametersGenerator
    public CipherParameters generateDerivedParameters(int r5, int r6) {
        int r52 = r5 / 8;
        int r62 = r6 / 8;
        byte[] generateDerivedKey = generateDerivedKey(r52 + r62);
        return new ParametersWithIV(new KeyParameter(generateDerivedKey, 0, r52), generateDerivedKey, r52, r62);
    }

    public void init(byte[] bArr, byte[] bArr2) {
        super.init(bArr, bArr2, 1);
    }
}
