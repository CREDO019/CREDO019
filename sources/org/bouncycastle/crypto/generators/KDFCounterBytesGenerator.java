package org.bouncycastle.crypto.generators;

import java.math.BigInteger;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.DerivationParameters;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.MacDerivationFunction;
import org.bouncycastle.crypto.params.KDFCounterParameters;
import org.bouncycastle.crypto.params.KeyParameter;

/* loaded from: classes5.dex */
public class KDFCounterBytesGenerator implements MacDerivationFunction {
    private static final BigInteger INTEGER_MAX = BigInteger.valueOf(2147483647L);
    private static final BigInteger TWO = BigInteger.valueOf(2);
    private byte[] fixedInputDataCtrPrefix;
    private byte[] fixedInputData_afterCtr;
    private int generatedBytes;

    /* renamed from: h */
    private final int f1994h;
    private byte[] ios;

    /* renamed from: k */
    private byte[] f1995k;
    private int maxSizeExcl;
    private final Mac prf;

    public KDFCounterBytesGenerator(Mac mac) {
        this.prf = mac;
        int macSize = mac.getMacSize();
        this.f1994h = macSize;
        this.f1995k = new byte[macSize];
    }

    private void generateNext() {
        int r0 = (this.generatedBytes / this.f1994h) + 1;
        byte[] bArr = this.ios;
        int length = bArr.length;
        if (length != 1) {
            if (length != 2) {
                if (length != 3) {
                    if (length != 4) {
                        throw new IllegalStateException("Unsupported size of counter i");
                    }
                    bArr[0] = (byte) (r0 >>> 24);
                }
                bArr[bArr.length - 3] = (byte) (r0 >>> 16);
            }
            bArr[bArr.length - 2] = (byte) (r0 >>> 8);
        }
        bArr[bArr.length - 1] = (byte) r0;
        Mac mac = this.prf;
        byte[] bArr2 = this.fixedInputDataCtrPrefix;
        mac.update(bArr2, 0, bArr2.length);
        Mac mac2 = this.prf;
        byte[] bArr3 = this.ios;
        mac2.update(bArr3, 0, bArr3.length);
        Mac mac3 = this.prf;
        byte[] bArr4 = this.fixedInputData_afterCtr;
        mac3.update(bArr4, 0, bArr4.length);
        this.prf.doFinal(this.f1995k, 0);
    }

    @Override // org.bouncycastle.crypto.DerivationFunction
    public int generateBytes(byte[] bArr, int r6, int r7) throws DataLengthException, IllegalArgumentException {
        int r0 = this.generatedBytes;
        int r1 = r0 + r7;
        if (r1 < 0 || r1 >= this.maxSizeExcl) {
            throw new DataLengthException("Current KDFCTR may only be used for " + this.maxSizeExcl + " bytes");
        }
        if (r0 % this.f1994h == 0) {
            generateNext();
        }
        int r02 = this.generatedBytes;
        int r12 = this.f1994h;
        int r2 = r02 % r12;
        int min = Math.min(r12 - (r02 % r12), r7);
        System.arraycopy(this.f1995k, r2, bArr, r6, min);
        this.generatedBytes += min;
        int r13 = r7 - min;
        while (true) {
            r6 += min;
            if (r13 <= 0) {
                return r7;
            }
            generateNext();
            min = Math.min(this.f1994h, r13);
            System.arraycopy(this.f1995k, 0, bArr, r6, min);
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
        if (!(derivationParameters instanceof KDFCounterParameters)) {
            throw new IllegalArgumentException("Wrong type of arguments given");
        }
        KDFCounterParameters kDFCounterParameters = (KDFCounterParameters) derivationParameters;
        this.prf.init(new KeyParameter(kDFCounterParameters.getKI()));
        this.fixedInputDataCtrPrefix = kDFCounterParameters.getFixedInputDataCounterPrefix();
        this.fixedInputData_afterCtr = kDFCounterParameters.getFixedInputDataCounterSuffix();
        int r = kDFCounterParameters.getR();
        this.ios = new byte[r / 8];
        BigInteger multiply = TWO.pow(r).multiply(BigInteger.valueOf(this.f1994h));
        this.maxSizeExcl = multiply.compareTo(INTEGER_MAX) == 1 ? Integer.MAX_VALUE : multiply.intValue();
        this.generatedBytes = 0;
    }
}
