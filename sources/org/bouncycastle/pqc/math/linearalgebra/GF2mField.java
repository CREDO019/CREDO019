package org.bouncycastle.pqc.math.linearalgebra;

import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import java.security.SecureRandom;
import org.bouncycastle.crypto.CryptoServicesRegistrar;

/* loaded from: classes4.dex */
public class GF2mField {
    private int degree;
    private int polynomial;

    public GF2mField(int r2) {
        this.degree = 0;
        if (r2 >= 32) {
            throw new IllegalArgumentException(" Error: the degree of field is too large ");
        }
        if (r2 < 1) {
            throw new IllegalArgumentException(" Error: the degree of field is non-positive ");
        }
        this.degree = r2;
        this.polynomial = PolynomialRingGF2.getIrreduciblePolynomial(r2);
    }

    public GF2mField(int r2, int r3) {
        this.degree = 0;
        if (r2 != PolynomialRingGF2.degree(r3)) {
            throw new IllegalArgumentException(" Error: the degree is not correct");
        }
        if (!PolynomialRingGF2.isIrreducible(r3)) {
            throw new IllegalArgumentException(" Error: given polynomial is reducible");
        }
        this.degree = r2;
        this.polynomial = r3;
    }

    public GF2mField(GF2mField gF2mField) {
        this.degree = 0;
        this.degree = gF2mField.degree;
        this.polynomial = gF2mField.polynomial;
    }

    public GF2mField(byte[] bArr) {
        this.degree = 0;
        if (bArr.length != 4) {
            throw new IllegalArgumentException("byte array is not an encoded finite field");
        }
        int OS2IP = LittleEndianConversions.OS2IP(bArr);
        this.polynomial = OS2IP;
        if (!PolynomialRingGF2.isIrreducible(OS2IP)) {
            throw new IllegalArgumentException("byte array is not an encoded finite field");
        }
        this.degree = PolynomialRingGF2.degree(this.polynomial);
    }

    private static String polyToString(int r4) {
        if (r4 == 0) {
            return SessionDescription.SUPPORTED_SDP_VERSION;
        }
        String str = ((byte) (r4 & 1)) == 1 ? IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE : "";
        int r42 = r4 >>> 1;
        int r2 = 1;
        while (r42 != 0) {
            if (((byte) (r42 & 1)) == 1) {
                str = str + "+x^" + r2;
            }
            r42 >>>= 1;
            r2++;
        }
        return str;
    }

    public int add(int r1, int r2) {
        return r1 ^ r2;
    }

    public String elementToStr(int r5) {
        StringBuilder sb;
        String str;
        String str2 = "";
        for (int r1 = 0; r1 < this.degree; r1++) {
            if ((((byte) r5) & 1) == 0) {
                sb = new StringBuilder();
                str = SessionDescription.SUPPORTED_SDP_VERSION;
            } else {
                sb = new StringBuilder();
                str = IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE;
            }
            sb.append(str);
            sb.append(str2);
            str2 = sb.toString();
            r5 >>>= 1;
        }
        return str2;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof GF2mField)) {
            GF2mField gF2mField = (GF2mField) obj;
            if (this.degree == gF2mField.degree && this.polynomial == gF2mField.polynomial) {
                return true;
            }
        }
        return false;
    }

    public int exp(int r4, int r5) {
        if (r5 == 0) {
            return 1;
        }
        if (r4 == 0) {
            return 0;
        }
        if (r4 == 1) {
            return 1;
        }
        if (r5 < 0) {
            r4 = inverse(r4);
            r5 = -r5;
        }
        int r1 = 1;
        while (r5 != 0) {
            if ((r5 & 1) == 1) {
                r1 = mult(r1, r4);
            }
            r4 = mult(r4, r4);
            r5 >>>= 1;
        }
        return r1;
    }

    public int getDegree() {
        return this.degree;
    }

    public byte[] getEncoded() {
        return LittleEndianConversions.I2OSP(this.polynomial);
    }

    public int getPolynomial() {
        return this.polynomial;
    }

    public int getRandomElement(SecureRandom secureRandom) {
        return RandUtils.nextInt(secureRandom, 1 << this.degree);
    }

    public int getRandomNonZeroElement() {
        return getRandomNonZeroElement(CryptoServicesRegistrar.getSecureRandom());
    }

    public int getRandomNonZeroElement(SecureRandom secureRandom) {
        int nextInt = RandUtils.nextInt(secureRandom, 1 << this.degree);
        int r2 = 0;
        while (nextInt == 0 && r2 < 1048576) {
            nextInt = RandUtils.nextInt(secureRandom, 1 << this.degree);
            r2++;
        }
        if (r2 == 1048576) {
            return 1;
        }
        return nextInt;
    }

    public int hashCode() {
        return this.polynomial;
    }

    public int inverse(int r3) {
        return exp(r3, (1 << this.degree) - 2);
    }

    public boolean isElementOfThisField(int r5) {
        int r0 = this.degree;
        return r0 == 31 ? r5 >= 0 : r5 >= 0 && r5 < (1 << r0);
    }

    public int mult(int r2, int r3) {
        return PolynomialRingGF2.modMultiply(r2, r3, this.polynomial);
    }

    public int sqRoot(int r3) {
        for (int r0 = 1; r0 < this.degree; r0++) {
            r3 = mult(r3, r3);
        }
        return r3;
    }

    public String toString() {
        return "Finite Field GF(2^" + this.degree + ") = GF(2)[X]/<" + polyToString(this.polynomial) + "> ";
    }
}
