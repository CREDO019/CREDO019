package org.bouncycastle.crypto.signers;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.BigIntegers;

/* loaded from: classes5.dex */
public class HMacDSAKCalculator implements DSAKCalculator {
    private static final BigInteger ZERO = BigInteger.valueOf(0);

    /* renamed from: K */
    private final byte[] f2174K;

    /* renamed from: V */
    private final byte[] f2175V;
    private final HMac hMac;

    /* renamed from: n */
    private BigInteger f2176n;

    public HMacDSAKCalculator(Digest digest) {
        HMac hMac = new HMac(digest);
        this.hMac = hMac;
        this.f2175V = new byte[hMac.getMacSize()];
        this.f2174K = new byte[hMac.getMacSize()];
    }

    private BigInteger bitsToInt(byte[] bArr) {
        BigInteger bigInteger = new BigInteger(1, bArr);
        return bArr.length * 8 > this.f2176n.bitLength() ? bigInteger.shiftRight((bArr.length * 8) - this.f2176n.bitLength()) : bigInteger;
    }

    @Override // org.bouncycastle.crypto.signers.DSAKCalculator
    public void init(BigInteger bigInteger, BigInteger bigInteger2, byte[] bArr) {
        this.f2176n = bigInteger;
        Arrays.fill(this.f2175V, (byte) 1);
        Arrays.fill(this.f2174K, (byte) 0);
        int unsignedByteLength = BigIntegers.getUnsignedByteLength(bigInteger);
        byte[] bArr2 = new byte[unsignedByteLength];
        byte[] asUnsignedByteArray = BigIntegers.asUnsignedByteArray(bigInteger2);
        System.arraycopy(asUnsignedByteArray, 0, bArr2, unsignedByteLength - asUnsignedByteArray.length, asUnsignedByteArray.length);
        byte[] bArr3 = new byte[unsignedByteLength];
        BigInteger bitsToInt = bitsToInt(bArr);
        if (bitsToInt.compareTo(bigInteger) >= 0) {
            bitsToInt = bitsToInt.subtract(bigInteger);
        }
        byte[] asUnsignedByteArray2 = BigIntegers.asUnsignedByteArray(bitsToInt);
        System.arraycopy(asUnsignedByteArray2, 0, bArr3, unsignedByteLength - asUnsignedByteArray2.length, asUnsignedByteArray2.length);
        this.hMac.init(new KeyParameter(this.f2174K));
        HMac hMac = this.hMac;
        byte[] bArr4 = this.f2175V;
        hMac.update(bArr4, 0, bArr4.length);
        this.hMac.update((byte) 0);
        this.hMac.update(bArr2, 0, unsignedByteLength);
        this.hMac.update(bArr3, 0, unsignedByteLength);
        this.hMac.doFinal(this.f2174K, 0);
        this.hMac.init(new KeyParameter(this.f2174K));
        HMac hMac2 = this.hMac;
        byte[] bArr5 = this.f2175V;
        hMac2.update(bArr5, 0, bArr5.length);
        this.hMac.doFinal(this.f2175V, 0);
        HMac hMac3 = this.hMac;
        byte[] bArr6 = this.f2175V;
        hMac3.update(bArr6, 0, bArr6.length);
        this.hMac.update((byte) 1);
        this.hMac.update(bArr2, 0, unsignedByteLength);
        this.hMac.update(bArr3, 0, unsignedByteLength);
        this.hMac.doFinal(this.f2174K, 0);
        this.hMac.init(new KeyParameter(this.f2174K));
        HMac hMac4 = this.hMac;
        byte[] bArr7 = this.f2175V;
        hMac4.update(bArr7, 0, bArr7.length);
        this.hMac.doFinal(this.f2175V, 0);
    }

    @Override // org.bouncycastle.crypto.signers.DSAKCalculator
    public void init(BigInteger bigInteger, SecureRandom secureRandom) {
        throw new IllegalStateException("Operation not supported");
    }

    @Override // org.bouncycastle.crypto.signers.DSAKCalculator
    public boolean isDeterministic() {
        return true;
    }

    @Override // org.bouncycastle.crypto.signers.DSAKCalculator
    public BigInteger nextK() {
        int unsignedByteLength = BigIntegers.getUnsignedByteLength(this.f2176n);
        byte[] bArr = new byte[unsignedByteLength];
        while (true) {
            int r3 = 0;
            while (r3 < unsignedByteLength) {
                HMac hMac = this.hMac;
                byte[] bArr2 = this.f2175V;
                hMac.update(bArr2, 0, bArr2.length);
                this.hMac.doFinal(this.f2175V, 0);
                int min = Math.min(unsignedByteLength - r3, this.f2175V.length);
                System.arraycopy(this.f2175V, 0, bArr, r3, min);
                r3 += min;
            }
            BigInteger bitsToInt = bitsToInt(bArr);
            if (bitsToInt.compareTo(ZERO) > 0 && bitsToInt.compareTo(this.f2176n) < 0) {
                return bitsToInt;
            }
            HMac hMac2 = this.hMac;
            byte[] bArr3 = this.f2175V;
            hMac2.update(bArr3, 0, bArr3.length);
            this.hMac.update((byte) 0);
            this.hMac.doFinal(this.f2174K, 0);
            this.hMac.init(new KeyParameter(this.f2174K));
            HMac hMac3 = this.hMac;
            byte[] bArr4 = this.f2175V;
            hMac3.update(bArr4, 0, bArr4.length);
            this.hMac.doFinal(this.f2175V, 0);
        }
    }
}
