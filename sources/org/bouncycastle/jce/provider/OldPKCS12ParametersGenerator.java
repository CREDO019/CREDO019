package org.bouncycastle.jce.provider;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.PBEParametersGenerator;
import org.bouncycastle.crypto.digests.MD5Digest;
import org.bouncycastle.crypto.digests.RIPEMD160Digest;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;

/* loaded from: classes5.dex */
class OldPKCS12ParametersGenerator extends PBEParametersGenerator {
    public static final int IV_MATERIAL = 2;
    public static final int KEY_MATERIAL = 1;
    public static final int MAC_MATERIAL = 3;
    private Digest digest;

    /* renamed from: u */
    private int f2227u;

    /* renamed from: v */
    private int f2228v;

    public OldPKCS12ParametersGenerator(Digest digest) {
        this.digest = digest;
        if (digest instanceof MD5Digest) {
            this.f2227u = 16;
        } else if (!(digest instanceof SHA1Digest) && !(digest instanceof RIPEMD160Digest)) {
            throw new IllegalArgumentException("Digest " + digest.getAlgorithmName() + " unsupported");
        } else {
            this.f2227u = 20;
        }
        this.f2228v = 64;
    }

    private void adjust(byte[] bArr, int r7, byte[] bArr2) {
        int r0 = (bArr2[bArr2.length - 1] & 255) + (bArr[(bArr2.length + r7) - 1] & 255) + 1;
        bArr[(bArr2.length + r7) - 1] = (byte) r0;
        int r02 = r0 >>> 8;
        for (int length = bArr2.length - 2; length >= 0; length--) {
            int r3 = r7 + length;
            int r03 = r02 + (bArr2[length] & 255) + (bArr[r3] & 255);
            bArr[r3] = (byte) r03;
            r02 = r03 >>> 8;
        }
    }

    private byte[] generateDerivedKey(int r17, int r18) {
        byte[] bArr;
        byte[] bArr2;
        int r2 = this.f2228v;
        byte[] bArr3 = new byte[r2];
        byte[] bArr4 = new byte[r18];
        int r5 = 0;
        for (int r6 = 0; r6 != r2; r6++) {
            bArr3[r6] = (byte) r17;
        }
        if (this.salt == null || this.salt.length == 0) {
            bArr = new byte[0];
        } else {
            int r62 = this.f2228v;
            int length = this.salt.length;
            int r9 = this.f2228v;
            int r63 = r62 * (((length + r9) - 1) / r9);
            bArr = new byte[r63];
            for (int r92 = 0; r92 != r63; r92++) {
                bArr[r92] = this.salt[r92 % this.salt.length];
            }
        }
        if (this.password == null || this.password.length == 0) {
            bArr2 = new byte[0];
        } else {
            int r64 = this.f2228v;
            int length2 = this.password.length;
            int r10 = this.f2228v;
            int r65 = r64 * (((length2 + r10) - 1) / r10);
            bArr2 = new byte[r65];
            for (int r102 = 0; r102 != r65; r102++) {
                bArr2[r102] = this.password[r102 % this.password.length];
            }
        }
        int length3 = bArr.length + bArr2.length;
        byte[] bArr5 = new byte[length3];
        System.arraycopy(bArr, 0, bArr5, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr5, bArr.length, bArr2.length);
        int r8 = this.f2228v;
        byte[] bArr6 = new byte[r8];
        int r11 = this.f2227u;
        int r12 = ((r18 + r11) - 1) / r11;
        int r112 = 1;
        while (r112 <= r12) {
            int r13 = this.f2227u;
            byte[] bArr7 = new byte[r13];
            this.digest.update(bArr3, r5, r2);
            this.digest.update(bArr5, r5, length3);
            this.digest.doFinal(bArr7, r5);
            for (int r15 = 1; r15 != this.iterationCount; r15++) {
                this.digest.update(bArr7, r5, r13);
                this.digest.doFinal(bArr7, r5);
            }
            for (int r7 = 0; r7 != r8; r7++) {
                bArr6[r112] = bArr7[r7 % r13];
            }
            int r72 = 0;
            while (true) {
                int r152 = this.f2228v;
                if (r72 == length3 / r152) {
                    break;
                }
                adjust(bArr5, r152 * r72, bArr6);
                r72++;
            }
            if (r112 == r12) {
                int r52 = r112 - 1;
                int r73 = this.f2227u;
                System.arraycopy(bArr7, 0, bArr4, r52 * r73, r18 - (r52 * r73));
            } else {
                System.arraycopy(bArr7, 0, bArr4, (r112 - 1) * this.f2227u, r13);
            }
            r112++;
            r5 = 0;
        }
        return bArr4;
    }

    @Override // org.bouncycastle.crypto.PBEParametersGenerator
    public CipherParameters generateDerivedMacParameters(int r4) {
        int r42 = r4 / 8;
        return new KeyParameter(generateDerivedKey(3, r42), 0, r42);
    }

    @Override // org.bouncycastle.crypto.PBEParametersGenerator
    public CipherParameters generateDerivedParameters(int r4) {
        int r42 = r4 / 8;
        return new KeyParameter(generateDerivedKey(1, r42), 0, r42);
    }

    @Override // org.bouncycastle.crypto.PBEParametersGenerator
    public CipherParameters generateDerivedParameters(int r6, int r7) {
        int r62 = r6 / 8;
        int r72 = r7 / 8;
        byte[] generateDerivedKey = generateDerivedKey(1, r62);
        return new ParametersWithIV(new KeyParameter(generateDerivedKey, 0, r62), generateDerivedKey(2, r72), 0, r72);
    }
}
