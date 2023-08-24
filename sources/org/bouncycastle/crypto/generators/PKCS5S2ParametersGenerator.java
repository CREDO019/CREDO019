package org.bouncycastle.crypto.generators;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.PBEParametersGenerator;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.crypto.util.DigestFactory;

/* loaded from: classes5.dex */
public class PKCS5S2ParametersGenerator extends PBEParametersGenerator {
    private Mac hMac;
    private byte[] state;

    public PKCS5S2ParametersGenerator() {
        this(DigestFactory.createSHA1());
    }

    public PKCS5S2ParametersGenerator(Digest digest) {
        HMac hMac = new HMac(digest);
        this.hMac = hMac;
        this.state = new byte[hMac.getMacSize()];
    }

    /* renamed from: F */
    private void m30F(byte[] bArr, int r6, byte[] bArr2, byte[] bArr3, int r9) {
        if (r6 == 0) {
            throw new IllegalArgumentException("iteration count must be at least 1.");
        }
        if (bArr != null) {
            this.hMac.update(bArr, 0, bArr.length);
        }
        this.hMac.update(bArr2, 0, bArr2.length);
        this.hMac.doFinal(this.state, 0);
        byte[] bArr4 = this.state;
        System.arraycopy(bArr4, 0, bArr3, r9, bArr4.length);
        for (int r5 = 1; r5 < r6; r5++) {
            Mac mac = this.hMac;
            byte[] bArr5 = this.state;
            mac.update(bArr5, 0, bArr5.length);
            this.hMac.doFinal(this.state, 0);
            int r7 = 0;
            while (true) {
                byte[] bArr6 = this.state;
                if (r7 != bArr6.length) {
                    int r2 = r9 + r7;
                    bArr3[r2] = (byte) (bArr6[r7] ^ bArr3[r2]);
                    r7++;
                }
            }
        }
    }

    private byte[] generateDerivedKey(int r13) {
        int r3;
        int macSize = this.hMac.getMacSize();
        int r132 = ((r13 + macSize) - 1) / macSize;
        byte[] bArr = new byte[4];
        byte[] bArr2 = new byte[r132 * macSize];
        this.hMac.init(new KeyParameter(this.password));
        int r10 = 0;
        for (int r11 = 1; r11 <= r132; r11++) {
            while (true) {
                byte b = (byte) (bArr[r3] + 1);
                bArr[r3] = b;
                r3 = b == 0 ? r3 - 1 : 3;
            }
            m30F(this.salt, this.iterationCount, bArr, bArr2, r10);
            r10 += macSize;
        }
        return bArr2;
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
}
