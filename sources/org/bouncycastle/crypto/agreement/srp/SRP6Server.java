package org.bouncycastle.crypto.agreement.srp;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.params.SRP6GroupParameters;

/* loaded from: classes5.dex */
public class SRP6Server {

    /* renamed from: A */
    protected BigInteger f1710A;

    /* renamed from: B */
    protected BigInteger f1711B;
    protected BigInteger Key;

    /* renamed from: M1 */
    protected BigInteger f1712M1;

    /* renamed from: M2 */
    protected BigInteger f1713M2;

    /* renamed from: N */
    protected BigInteger f1714N;

    /* renamed from: S */
    protected BigInteger f1715S;

    /* renamed from: b */
    protected BigInteger f1716b;
    protected Digest digest;

    /* renamed from: g */
    protected BigInteger f1717g;
    protected SecureRandom random;

    /* renamed from: u */
    protected BigInteger f1718u;

    /* renamed from: v */
    protected BigInteger f1719v;

    private BigInteger calculateS() {
        return this.f1719v.modPow(this.f1718u, this.f1714N).multiply(this.f1710A).mod(this.f1714N).modPow(this.f1716b, this.f1714N);
    }

    public BigInteger calculateSecret(BigInteger bigInteger) throws CryptoException {
        BigInteger validatePublicValue = SRP6Util.validatePublicValue(this.f1714N, bigInteger);
        this.f1710A = validatePublicValue;
        this.f1718u = SRP6Util.calculateU(this.digest, this.f1714N, validatePublicValue, this.f1711B);
        BigInteger calculateS = calculateS();
        this.f1715S = calculateS;
        return calculateS;
    }

    public BigInteger calculateServerEvidenceMessage() throws CryptoException {
        BigInteger bigInteger;
        BigInteger bigInteger2;
        BigInteger bigInteger3 = this.f1710A;
        if (bigInteger3 == null || (bigInteger = this.f1712M1) == null || (bigInteger2 = this.f1715S) == null) {
            throw new CryptoException("Impossible to compute M2: some data are missing from the previous operations (A,M1,S)");
        }
        BigInteger calculateM2 = SRP6Util.calculateM2(this.digest, this.f1714N, bigInteger3, bigInteger, bigInteger2);
        this.f1713M2 = calculateM2;
        return calculateM2;
    }

    public BigInteger calculateSessionKey() throws CryptoException {
        BigInteger bigInteger = this.f1715S;
        if (bigInteger == null || this.f1712M1 == null || this.f1713M2 == null) {
            throw new CryptoException("Impossible to compute Key: some data are missing from the previous operations (S,M1,M2)");
        }
        BigInteger calculateKey = SRP6Util.calculateKey(this.digest, this.f1714N, bigInteger);
        this.Key = calculateKey;
        return calculateKey;
    }

    public BigInteger generateServerCredentials() {
        BigInteger calculateK = SRP6Util.calculateK(this.digest, this.f1714N, this.f1717g);
        this.f1716b = selectPrivateValue();
        BigInteger mod = calculateK.multiply(this.f1719v).mod(this.f1714N).add(this.f1717g.modPow(this.f1716b, this.f1714N)).mod(this.f1714N);
        this.f1711B = mod;
        return mod;
    }

    public void init(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, Digest digest, SecureRandom secureRandom) {
        this.f1714N = bigInteger;
        this.f1717g = bigInteger2;
        this.f1719v = bigInteger3;
        this.random = secureRandom;
        this.digest = digest;
    }

    public void init(SRP6GroupParameters sRP6GroupParameters, BigInteger bigInteger, Digest digest, SecureRandom secureRandom) {
        init(sRP6GroupParameters.getN(), sRP6GroupParameters.getG(), bigInteger, digest, secureRandom);
    }

    protected BigInteger selectPrivateValue() {
        return SRP6Util.generatePrivateValue(this.digest, this.f1714N, this.f1717g, this.random);
    }

    public boolean verifyClientEvidenceMessage(BigInteger bigInteger) throws CryptoException {
        BigInteger bigInteger2;
        BigInteger bigInteger3;
        BigInteger bigInteger4 = this.f1710A;
        if (bigInteger4 == null || (bigInteger2 = this.f1711B) == null || (bigInteger3 = this.f1715S) == null) {
            throw new CryptoException("Impossible to compute and verify M1: some data are missing from the previous operations (A,B,S)");
        }
        if (SRP6Util.calculateM1(this.digest, this.f1714N, bigInteger4, bigInteger2, bigInteger3).equals(bigInteger)) {
            this.f1712M1 = bigInteger;
            return true;
        }
        return false;
    }
}
