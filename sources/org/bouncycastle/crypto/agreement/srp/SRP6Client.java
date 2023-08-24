package org.bouncycastle.crypto.agreement.srp;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.params.SRP6GroupParameters;

/* loaded from: classes5.dex */
public class SRP6Client {

    /* renamed from: A */
    protected BigInteger f1700A;

    /* renamed from: B */
    protected BigInteger f1701B;
    protected BigInteger Key;

    /* renamed from: M1 */
    protected BigInteger f1702M1;

    /* renamed from: M2 */
    protected BigInteger f1703M2;

    /* renamed from: N */
    protected BigInteger f1704N;

    /* renamed from: S */
    protected BigInteger f1705S;

    /* renamed from: a */
    protected BigInteger f1706a;
    protected Digest digest;

    /* renamed from: g */
    protected BigInteger f1707g;
    protected SecureRandom random;

    /* renamed from: u */
    protected BigInteger f1708u;

    /* renamed from: x */
    protected BigInteger f1709x;

    private BigInteger calculateS() {
        BigInteger calculateK = SRP6Util.calculateK(this.digest, this.f1704N, this.f1707g);
        return this.f1701B.subtract(this.f1707g.modPow(this.f1709x, this.f1704N).multiply(calculateK).mod(this.f1704N)).mod(this.f1704N).modPow(this.f1708u.multiply(this.f1709x).add(this.f1706a), this.f1704N);
    }

    public BigInteger calculateClientEvidenceMessage() throws CryptoException {
        BigInteger bigInteger;
        BigInteger bigInteger2;
        BigInteger bigInteger3 = this.f1700A;
        if (bigInteger3 == null || (bigInteger = this.f1701B) == null || (bigInteger2 = this.f1705S) == null) {
            throw new CryptoException("Impossible to compute M1: some data are missing from the previous operations (A,B,S)");
        }
        BigInteger calculateM1 = SRP6Util.calculateM1(this.digest, this.f1704N, bigInteger3, bigInteger, bigInteger2);
        this.f1702M1 = calculateM1;
        return calculateM1;
    }

    public BigInteger calculateSecret(BigInteger bigInteger) throws CryptoException {
        BigInteger validatePublicValue = SRP6Util.validatePublicValue(this.f1704N, bigInteger);
        this.f1701B = validatePublicValue;
        this.f1708u = SRP6Util.calculateU(this.digest, this.f1704N, this.f1700A, validatePublicValue);
        BigInteger calculateS = calculateS();
        this.f1705S = calculateS;
        return calculateS;
    }

    public BigInteger calculateSessionKey() throws CryptoException {
        BigInteger bigInteger = this.f1705S;
        if (bigInteger == null || this.f1702M1 == null || this.f1703M2 == null) {
            throw new CryptoException("Impossible to compute Key: some data are missing from the previous operations (S,M1,M2)");
        }
        BigInteger calculateKey = SRP6Util.calculateKey(this.digest, this.f1704N, bigInteger);
        this.Key = calculateKey;
        return calculateKey;
    }

    public BigInteger generateClientCredentials(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        this.f1709x = SRP6Util.calculateX(this.digest, this.f1704N, bArr, bArr2, bArr3);
        BigInteger selectPrivateValue = selectPrivateValue();
        this.f1706a = selectPrivateValue;
        BigInteger modPow = this.f1707g.modPow(selectPrivateValue, this.f1704N);
        this.f1700A = modPow;
        return modPow;
    }

    public void init(BigInteger bigInteger, BigInteger bigInteger2, Digest digest, SecureRandom secureRandom) {
        this.f1704N = bigInteger;
        this.f1707g = bigInteger2;
        this.digest = digest;
        this.random = secureRandom;
    }

    public void init(SRP6GroupParameters sRP6GroupParameters, Digest digest, SecureRandom secureRandom) {
        init(sRP6GroupParameters.getN(), sRP6GroupParameters.getG(), digest, secureRandom);
    }

    protected BigInteger selectPrivateValue() {
        return SRP6Util.generatePrivateValue(this.digest, this.f1704N, this.f1707g, this.random);
    }

    public boolean verifyServerEvidenceMessage(BigInteger bigInteger) throws CryptoException {
        BigInteger bigInteger2;
        BigInteger bigInteger3;
        BigInteger bigInteger4 = this.f1700A;
        if (bigInteger4 == null || (bigInteger2 = this.f1702M1) == null || (bigInteger3 = this.f1705S) == null) {
            throw new CryptoException("Impossible to compute and verify M2: some data are missing from the previous operations (A,M1,S)");
        }
        if (SRP6Util.calculateM2(this.digest, this.f1704N, bigInteger4, bigInteger2, bigInteger3).equals(bigInteger)) {
            this.f1703M2 = bigInteger;
            return true;
        }
        return false;
    }
}
