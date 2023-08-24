package org.bouncycastle.crypto.params;

import com.google.android.exoplayer2.analytics.AnalyticsListener;
import com.onesignal.NotificationBundleProcessor;
import java.math.BigInteger;
import java.util.Objects;
import org.bouncycastle.asn1.p032x9.X9ECParameters;
import org.bouncycastle.math.p039ec.ECAlgorithms;
import org.bouncycastle.math.p039ec.ECConstants;
import org.bouncycastle.math.p039ec.ECCurve;
import org.bouncycastle.math.p039ec.ECPoint;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.BigIntegers;

/* loaded from: classes5.dex */
public class ECDomainParameters implements ECConstants {

    /* renamed from: G */
    private final ECPoint f2120G;
    private final ECCurve curve;

    /* renamed from: h */
    private final BigInteger f2121h;
    private BigInteger hInv;

    /* renamed from: n */
    private final BigInteger f2122n;
    private final byte[] seed;

    public ECDomainParameters(X9ECParameters x9ECParameters) {
        this(x9ECParameters.getCurve(), x9ECParameters.getG(), x9ECParameters.getN(), x9ECParameters.getH(), x9ECParameters.getSeed());
    }

    public ECDomainParameters(ECCurve eCCurve, ECPoint eCPoint, BigInteger bigInteger) {
        this(eCCurve, eCPoint, bigInteger, ONE, null);
    }

    public ECDomainParameters(ECCurve eCCurve, ECPoint eCPoint, BigInteger bigInteger, BigInteger bigInteger2) {
        this(eCCurve, eCPoint, bigInteger, bigInteger2, null);
    }

    public ECDomainParameters(ECCurve eCCurve, ECPoint eCPoint, BigInteger bigInteger, BigInteger bigInteger2, byte[] bArr) {
        this.hInv = null;
        Objects.requireNonNull(eCCurve, "curve");
        Objects.requireNonNull(bigInteger, NotificationBundleProcessor.PUSH_MINIFIED_BUTTON_TEXT);
        this.curve = eCCurve;
        this.f2120G = validatePublicPoint(eCCurve, eCPoint);
        this.f2122n = bigInteger;
        this.f2121h = bigInteger2;
        this.seed = Arrays.clone(bArr);
    }

    static ECPoint validatePublicPoint(ECCurve eCCurve, ECPoint eCPoint) {
        Objects.requireNonNull(eCPoint, "Point cannot be null");
        ECPoint normalize = ECAlgorithms.importPoint(eCCurve, eCPoint).normalize();
        if (normalize.isInfinity()) {
            throw new IllegalArgumentException("Point at infinity");
        }
        if (normalize.isValid()) {
            return normalize;
        }
        throw new IllegalArgumentException("Point not on curve");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ECDomainParameters) {
            ECDomainParameters eCDomainParameters = (ECDomainParameters) obj;
            return this.curve.equals(eCDomainParameters.curve) && this.f2120G.equals(eCDomainParameters.f2120G) && this.f2122n.equals(eCDomainParameters.f2122n);
        }
        return false;
    }

    public ECCurve getCurve() {
        return this.curve;
    }

    public ECPoint getG() {
        return this.f2120G;
    }

    public BigInteger getH() {
        return this.f2121h;
    }

    public synchronized BigInteger getHInv() {
        if (this.hInv == null) {
            this.hInv = BigIntegers.modOddInverseVar(this.f2122n, this.f2121h);
        }
        return this.hInv;
    }

    public BigInteger getN() {
        return this.f2122n;
    }

    public byte[] getSeed() {
        return Arrays.clone(this.seed);
    }

    public int hashCode() {
        return ((((this.curve.hashCode() ^ AnalyticsListener.EVENT_PLAYER_RELEASED) * 257) ^ this.f2120G.hashCode()) * 257) ^ this.f2122n.hashCode();
    }

    public BigInteger validatePrivateScalar(BigInteger bigInteger) {
        Objects.requireNonNull(bigInteger, "Scalar cannot be null");
        if (bigInteger.compareTo(ECConstants.ONE) < 0 || bigInteger.compareTo(getN()) >= 0) {
            throw new IllegalArgumentException("Scalar is not in the interval [1, n - 1]");
        }
        return bigInteger;
    }

    public ECPoint validatePublicPoint(ECPoint eCPoint) {
        return validatePublicPoint(getCurve(), eCPoint);
    }
}
