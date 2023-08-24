package org.bouncycastle.crypto.generators;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.PBEParametersGenerator;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;

/* loaded from: classes5.dex */
public class PKCS5S1ParametersGenerator extends PBEParametersGenerator {
    private Digest digest;

    public PKCS5S1ParametersGenerator(Digest digest) {
        this.digest = digest;
    }

    private byte[] generateDerivedKey() {
        int digestSize = this.digest.getDigestSize();
        byte[] bArr = new byte[digestSize];
        this.digest.update(this.password, 0, this.password.length);
        this.digest.update(this.salt, 0, this.salt.length);
        this.digest.doFinal(bArr, 0);
        for (int r2 = 1; r2 < this.iterationCount; r2++) {
            this.digest.update(bArr, 0, digestSize);
            this.digest.doFinal(bArr, 0);
        }
        return bArr;
    }

    @Override // org.bouncycastle.crypto.PBEParametersGenerator
    public CipherParameters generateDerivedMacParameters(int r1) {
        return generateDerivedParameters(r1);
    }

    @Override // org.bouncycastle.crypto.PBEParametersGenerator
    public CipherParameters generateDerivedParameters(int r4) {
        int r42 = r4 / 8;
        if (r42 <= this.digest.getDigestSize()) {
            return new KeyParameter(generateDerivedKey(), 0, r42);
        }
        throw new IllegalArgumentException("Can't generate a derived key " + r42 + " bytes long.");
    }

    @Override // org.bouncycastle.crypto.PBEParametersGenerator
    public CipherParameters generateDerivedParameters(int r5, int r6) {
        int r52 = r5 / 8;
        int r62 = r6 / 8;
        int r0 = r52 + r62;
        if (r0 <= this.digest.getDigestSize()) {
            byte[] generateDerivedKey = generateDerivedKey();
            return new ParametersWithIV(new KeyParameter(generateDerivedKey, 0, r52), generateDerivedKey, r52, r62);
        }
        throw new IllegalArgumentException("Can't generate a derived key " + r0 + " bytes long.");
    }
}
