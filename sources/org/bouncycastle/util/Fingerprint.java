package org.bouncycastle.util;

import com.google.common.base.Ascii;
import org.apache.logging.log4j.message.ParameterizedMessage;
import org.bouncycastle.crypto.digests.SHA512tDigest;
import org.bouncycastle.crypto.digests.SHAKEDigest;

/* loaded from: classes4.dex */
public class Fingerprint {
    private static char[] encodingTable = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private final byte[] fingerprint;

    public Fingerprint(byte[] bArr) {
        this(bArr, 160);
    }

    public Fingerprint(byte[] bArr, int r2) {
        this.fingerprint = calculateFingerprint(bArr, r2);
    }

    public Fingerprint(byte[] bArr, boolean z) {
        if (z) {
            this.fingerprint = calculateFingerprintSHA512_160(bArr);
        } else {
            this.fingerprint = calculateFingerprint(bArr);
        }
    }

    public static byte[] calculateFingerprint(byte[] bArr) {
        return calculateFingerprint(bArr, 160);
    }

    public static byte[] calculateFingerprint(byte[] bArr, int r4) {
        if (r4 % 8 == 0) {
            SHAKEDigest sHAKEDigest = new SHAKEDigest(256);
            sHAKEDigest.update(bArr, 0, bArr.length);
            int r42 = r4 / 8;
            byte[] bArr2 = new byte[r42];
            sHAKEDigest.doFinal(bArr2, 0, r42);
            return bArr2;
        }
        throw new IllegalArgumentException("bitLength must be a multiple of 8");
    }

    public static byte[] calculateFingerprintSHA512_160(byte[] bArr) {
        SHA512tDigest sHA512tDigest = new SHA512tDigest(160);
        sHA512tDigest.update(bArr, 0, bArr.length);
        byte[] bArr2 = new byte[sHA512tDigest.getDigestSize()];
        sHA512tDigest.doFinal(bArr2, 0);
        return bArr2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Fingerprint) {
            return Arrays.areEqual(((Fingerprint) obj).fingerprint, this.fingerprint);
        }
        return false;
    }

    public byte[] getFingerprint() {
        return Arrays.clone(this.fingerprint);
    }

    public int hashCode() {
        return Arrays.hashCode(this.fingerprint);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        for (int r1 = 0; r1 != this.fingerprint.length; r1++) {
            if (r1 > 0) {
                stringBuffer.append(ParameterizedMessage.ERROR_MSG_SEPARATOR);
            }
            stringBuffer.append(encodingTable[(this.fingerprint[r1] >>> 4) & 15]);
            stringBuffer.append(encodingTable[this.fingerprint[r1] & Ascii.f1128SI]);
        }
        return stringBuffer.toString();
    }
}
