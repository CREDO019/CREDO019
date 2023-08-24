package org.bouncycastle.crypto.generators;

import java.math.BigInteger;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.DerivationParameters;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.MacDerivationFunction;
import org.bouncycastle.crypto.params.KDFFeedbackParameters;
import org.bouncycastle.crypto.params.KeyParameter;

/* loaded from: classes5.dex */
public class KDFFeedbackBytesGenerator implements MacDerivationFunction {
    private static final BigInteger INTEGER_MAX = BigInteger.valueOf(2147483647L);
    private static final BigInteger TWO = BigInteger.valueOf(2);
    private byte[] fixedInputData;
    private int generatedBytes;

    /* renamed from: h */
    private final int f1999h;
    private byte[] ios;

    /* renamed from: iv */
    private byte[] f2000iv;

    /* renamed from: k */
    private byte[] f2001k;
    private int maxSizeExcl;
    private final Mac prf;
    private boolean useCounter;

    public KDFFeedbackBytesGenerator(Mac mac) {
        this.prf = mac;
        int macSize = mac.getMacSize();
        this.f1999h = macSize;
        this.f2001k = new byte[macSize];
    }

    private void generateNext() {
        if (this.generatedBytes == 0) {
            Mac mac = this.prf;
            byte[] bArr = this.f2000iv;
            mac.update(bArr, 0, bArr.length);
        } else {
            Mac mac2 = this.prf;
            byte[] bArr2 = this.f2001k;
            mac2.update(bArr2, 0, bArr2.length);
        }
        if (this.useCounter) {
            int r0 = (this.generatedBytes / this.f1999h) + 1;
            byte[] bArr3 = this.ios;
            int length = bArr3.length;
            if (length != 1) {
                if (length != 2) {
                    if (length != 3) {
                        if (length != 4) {
                            throw new IllegalStateException("Unsupported size of counter i");
                        }
                        bArr3[0] = (byte) (r0 >>> 24);
                    }
                    bArr3[bArr3.length - 3] = (byte) (r0 >>> 16);
                }
                bArr3[bArr3.length - 2] = (byte) (r0 >>> 8);
            }
            bArr3[bArr3.length - 1] = (byte) r0;
            this.prf.update(bArr3, 0, bArr3.length);
        }
        Mac mac3 = this.prf;
        byte[] bArr4 = this.fixedInputData;
        mac3.update(bArr4, 0, bArr4.length);
        this.prf.doFinal(this.f2001k, 0);
    }

    @Override // org.bouncycastle.crypto.DerivationFunction
    public int generateBytes(byte[] bArr, int r6, int r7) throws DataLengthException, IllegalArgumentException {
        int r0 = this.generatedBytes;
        int r1 = r0 + r7;
        if (r1 < 0 || r1 >= this.maxSizeExcl) {
            throw new DataLengthException("Current KDFCTR may only be used for " + this.maxSizeExcl + " bytes");
        }
        if (r0 % this.f1999h == 0) {
            generateNext();
        }
        int r02 = this.generatedBytes;
        int r12 = this.f1999h;
        int r2 = r02 % r12;
        int min = Math.min(r12 - (r02 % r12), r7);
        System.arraycopy(this.f2001k, r2, bArr, r6, min);
        this.generatedBytes += min;
        int r13 = r7 - min;
        while (true) {
            r6 += min;
            if (r13 <= 0) {
                return r7;
            }
            generateNext();
            min = Math.min(this.f1999h, r13);
            System.arraycopy(this.f2001k, 0, bArr, r6, min);
            this.generatedBytes += min;
            r13 -= min;
        }
    }

    @Override // org.bouncycastle.crypto.MacDerivationFunction
    public Mac getMac() {
        return this.prf;
    }

    @Override // org.bouncycastle.crypto.DerivationFunction
    public void init(DerivationParameters derivationParameters) {
        if (!(derivationParameters instanceof KDFFeedbackParameters)) {
            throw new IllegalArgumentException("Wrong type of arguments given");
        }
        KDFFeedbackParameters kDFFeedbackParameters = (KDFFeedbackParameters) derivationParameters;
        this.prf.init(new KeyParameter(kDFFeedbackParameters.getKI()));
        this.fixedInputData = kDFFeedbackParameters.getFixedInputData();
        int r = kDFFeedbackParameters.getR();
        this.ios = new byte[r / 8];
        int r2 = Integer.MAX_VALUE;
        if (kDFFeedbackParameters.useCounter()) {
            BigInteger multiply = TWO.pow(r).multiply(BigInteger.valueOf(this.f1999h));
            if (multiply.compareTo(INTEGER_MAX) != 1) {
                r2 = multiply.intValue();
            }
        }
        this.maxSizeExcl = r2;
        this.f2000iv = kDFFeedbackParameters.getIV();
        this.useCounter = kDFFeedbackParameters.useCounter();
        this.generatedBytes = 0;
    }
}
