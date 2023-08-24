package org.bouncycastle.crypto.generators;

import java.math.BigInteger;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.DerivationParameters;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.MacDerivationFunction;
import org.bouncycastle.crypto.params.KDFDoublePipelineIterationParameters;
import org.bouncycastle.crypto.params.KeyParameter;

/* loaded from: classes5.dex */
public class KDFDoublePipelineIterationBytesGenerator implements MacDerivationFunction {
    private static final BigInteger INTEGER_MAX = BigInteger.valueOf(2147483647L);
    private static final BigInteger TWO = BigInteger.valueOf(2);

    /* renamed from: a */
    private byte[] f1996a;
    private byte[] fixedInputData;
    private int generatedBytes;

    /* renamed from: h */
    private final int f1997h;
    private byte[] ios;

    /* renamed from: k */
    private byte[] f1998k;
    private int maxSizeExcl;
    private final Mac prf;
    private boolean useCounter;

    public KDFDoublePipelineIterationBytesGenerator(Mac mac) {
        this.prf = mac;
        int macSize = mac.getMacSize();
        this.f1997h = macSize;
        this.f1996a = new byte[macSize];
        this.f1998k = new byte[macSize];
    }

    private void generateNext() {
        if (this.generatedBytes == 0) {
            Mac mac = this.prf;
            byte[] bArr = this.fixedInputData;
            mac.update(bArr, 0, bArr.length);
            this.prf.doFinal(this.f1996a, 0);
        } else {
            Mac mac2 = this.prf;
            byte[] bArr2 = this.f1996a;
            mac2.update(bArr2, 0, bArr2.length);
            this.prf.doFinal(this.f1996a, 0);
        }
        Mac mac3 = this.prf;
        byte[] bArr3 = this.f1996a;
        mac3.update(bArr3, 0, bArr3.length);
        if (this.useCounter) {
            int r0 = (this.generatedBytes / this.f1997h) + 1;
            byte[] bArr4 = this.ios;
            int length = bArr4.length;
            if (length != 1) {
                if (length != 2) {
                    if (length != 3) {
                        if (length != 4) {
                            throw new IllegalStateException("Unsupported size of counter i");
                        }
                        bArr4[0] = (byte) (r0 >>> 24);
                    }
                    bArr4[bArr4.length - 3] = (byte) (r0 >>> 16);
                }
                bArr4[bArr4.length - 2] = (byte) (r0 >>> 8);
            }
            bArr4[bArr4.length - 1] = (byte) r0;
            this.prf.update(bArr4, 0, bArr4.length);
        }
        Mac mac4 = this.prf;
        byte[] bArr5 = this.fixedInputData;
        mac4.update(bArr5, 0, bArr5.length);
        this.prf.doFinal(this.f1998k, 0);
    }

    @Override // org.bouncycastle.crypto.DerivationFunction
    public int generateBytes(byte[] bArr, int r6, int r7) throws DataLengthException, IllegalArgumentException {
        int r0 = this.generatedBytes;
        int r1 = r0 + r7;
        if (r1 < 0 || r1 >= this.maxSizeExcl) {
            throw new DataLengthException("Current KDFCTR may only be used for " + this.maxSizeExcl + " bytes");
        }
        if (r0 % this.f1997h == 0) {
            generateNext();
        }
        int r02 = this.generatedBytes;
        int r12 = this.f1997h;
        int r2 = r02 % r12;
        int min = Math.min(r12 - (r02 % r12), r7);
        System.arraycopy(this.f1998k, r2, bArr, r6, min);
        this.generatedBytes += min;
        int r13 = r7 - min;
        while (true) {
            r6 += min;
            if (r13 <= 0) {
                return r7;
            }
            generateNext();
            min = Math.min(this.f1997h, r13);
            System.arraycopy(this.f1998k, 0, bArr, r6, min);
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
        if (!(derivationParameters instanceof KDFDoublePipelineIterationParameters)) {
            throw new IllegalArgumentException("Wrong type of arguments given");
        }
        KDFDoublePipelineIterationParameters kDFDoublePipelineIterationParameters = (KDFDoublePipelineIterationParameters) derivationParameters;
        this.prf.init(new KeyParameter(kDFDoublePipelineIterationParameters.getKI()));
        this.fixedInputData = kDFDoublePipelineIterationParameters.getFixedInputData();
        int r = kDFDoublePipelineIterationParameters.getR();
        this.ios = new byte[r / 8];
        int r2 = Integer.MAX_VALUE;
        if (kDFDoublePipelineIterationParameters.useCounter()) {
            BigInteger multiply = TWO.pow(r).multiply(BigInteger.valueOf(this.f1997h));
            if (multiply.compareTo(INTEGER_MAX) != 1) {
                r2 = multiply.intValue();
            }
        }
        this.maxSizeExcl = r2;
        this.useCounter = kDFDoublePipelineIterationParameters.useCounter();
        this.generatedBytes = 0;
    }
}
