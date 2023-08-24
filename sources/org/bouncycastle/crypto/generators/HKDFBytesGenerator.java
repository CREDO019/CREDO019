package org.bouncycastle.crypto.generators;

import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.DerivationFunction;
import org.bouncycastle.crypto.DerivationParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.HKDFParameters;
import org.bouncycastle.crypto.params.KeyParameter;

/* loaded from: classes5.dex */
public class HKDFBytesGenerator implements DerivationFunction {
    private byte[] currentT;
    private int generatedBytes;
    private HMac hMacHash;
    private int hashLen;
    private byte[] info;

    public HKDFBytesGenerator(Digest digest) {
        this.hMacHash = new HMac(digest);
        this.hashLen = digest.getDigestSize();
    }

    private void expandNext() throws DataLengthException {
        int r0 = this.generatedBytes;
        int r1 = this.hashLen;
        int r2 = (r0 / r1) + 1;
        if (r2 >= 256) {
            throw new DataLengthException("HKDF cannot generate more than 255 blocks of HashLen size");
        }
        if (r0 != 0) {
            this.hMacHash.update(this.currentT, 0, r1);
        }
        HMac hMac = this.hMacHash;
        byte[] bArr = this.info;
        hMac.update(bArr, 0, bArr.length);
        this.hMacHash.update((byte) r2);
        this.hMacHash.doFinal(this.currentT, 0);
    }

    private KeyParameter extract(byte[] bArr, byte[] bArr2) {
        if (bArr == null) {
            this.hMacHash.init(new KeyParameter(new byte[this.hashLen]));
        } else {
            this.hMacHash.init(new KeyParameter(bArr));
        }
        this.hMacHash.update(bArr2, 0, bArr2.length);
        byte[] bArr3 = new byte[this.hashLen];
        this.hMacHash.doFinal(bArr3, 0);
        return new KeyParameter(bArr3);
    }

    @Override // org.bouncycastle.crypto.DerivationFunction
    public int generateBytes(byte[] bArr, int r6, int r7) throws DataLengthException, IllegalArgumentException {
        int r0 = this.generatedBytes;
        int r1 = r0 + r7;
        int r2 = this.hashLen;
        if (r1 > r2 * 255) {
            throw new DataLengthException("HKDF may only be used for 255 * HashLen bytes of output");
        }
        if (r0 % r2 == 0) {
            expandNext();
        }
        int r02 = this.generatedBytes;
        int r12 = this.hashLen;
        int r22 = r02 % r12;
        int min = Math.min(r12 - (r02 % r12), r7);
        System.arraycopy(this.currentT, r22, bArr, r6, min);
        this.generatedBytes += min;
        int r13 = r7 - min;
        while (true) {
            r6 += min;
            if (r13 <= 0) {
                return r7;
            }
            expandNext();
            min = Math.min(this.hashLen, r13);
            System.arraycopy(this.currentT, 0, bArr, r6, min);
            this.generatedBytes += min;
            r13 -= min;
        }
    }

    public Digest getDigest() {
        return this.hMacHash.getUnderlyingDigest();
    }

    @Override // org.bouncycastle.crypto.DerivationFunction
    public void init(DerivationParameters derivationParameters) {
        HMac hMac;
        KeyParameter extract;
        if (!(derivationParameters instanceof HKDFParameters)) {
            throw new IllegalArgumentException("HKDF parameters required for HKDFBytesGenerator");
        }
        HKDFParameters hKDFParameters = (HKDFParameters) derivationParameters;
        if (hKDFParameters.skipExtract()) {
            hMac = this.hMacHash;
            extract = new KeyParameter(hKDFParameters.getIKM());
        } else {
            hMac = this.hMacHash;
            extract = extract(hKDFParameters.getSalt(), hKDFParameters.getIKM());
        }
        hMac.init(extract);
        this.info = hKDFParameters.getInfo();
        this.generatedBytes = 0;
        this.currentT = new byte[this.hashLen];
    }
}
