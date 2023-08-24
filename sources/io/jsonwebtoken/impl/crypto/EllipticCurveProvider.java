package io.jsonwebtoken.impl.crypto;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.lang.Assert;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/* loaded from: classes5.dex */
public abstract class EllipticCurveProvider extends SignatureProvider {
    private static final Map<SignatureAlgorithm, String> EC_CURVE_NAMES = createEcCurveNames();

    private static Map<SignatureAlgorithm, String> createEcCurveNames() {
        HashMap hashMap = new HashMap();
        hashMap.put(SignatureAlgorithm.ES256, "secp256r1");
        hashMap.put(SignatureAlgorithm.ES384, "secp384r1");
        hashMap.put(SignatureAlgorithm.ES512, "secp521r1");
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public EllipticCurveProvider(SignatureAlgorithm signatureAlgorithm, Key key) {
        super(signatureAlgorithm, key);
        Assert.isTrue(signatureAlgorithm.isEllipticCurve(), "SignatureAlgorithm must be an Elliptic Curve algorithm.");
    }

    public static KeyPair generateKeyPair() {
        return generateKeyPair(SignatureAlgorithm.ES512);
    }

    public static KeyPair generateKeyPair(SignatureAlgorithm signatureAlgorithm) {
        return generateKeyPair(signatureAlgorithm, SignatureProvider.DEFAULT_SECURE_RANDOM);
    }

    public static KeyPair generateKeyPair(SignatureAlgorithm signatureAlgorithm, SecureRandom secureRandom) {
        return generateKeyPair("ECDSA", BouncyCastleProvider.PROVIDER_NAME, signatureAlgorithm, secureRandom);
    }

    public static KeyPair generateKeyPair(String str, String str2, SignatureAlgorithm signatureAlgorithm, SecureRandom secureRandom) {
        Assert.notNull(signatureAlgorithm, "SignatureAlgorithm argument cannot be null.");
        Assert.isTrue(signatureAlgorithm.isEllipticCurve(), "SignatureAlgorithm argument must represent an Elliptic Curve algorithm.");
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(str, str2);
            keyPairGenerator.initialize(ECNamedCurveTable.getParameterSpec(EC_CURVE_NAMES.get(signatureAlgorithm)), secureRandom);
            return keyPairGenerator.generateKeyPair();
        } catch (Exception e) {
            throw new IllegalStateException("Unable to generate Elliptic Curve KeyPair: " + e.getMessage(), e);
        }
    }

    /* renamed from: io.jsonwebtoken.impl.crypto.EllipticCurveProvider$1 */
    /* loaded from: classes5.dex */
    static /* synthetic */ class C46341 {
        static final /* synthetic */ int[] $SwitchMap$io$jsonwebtoken$SignatureAlgorithm;

        static {
            int[] r0 = new int[SignatureAlgorithm.values().length];
            $SwitchMap$io$jsonwebtoken$SignatureAlgorithm = r0;
            try {
                r0[SignatureAlgorithm.ES256.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$jsonwebtoken$SignatureAlgorithm[SignatureAlgorithm.ES384.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$jsonwebtoken$SignatureAlgorithm[SignatureAlgorithm.ES512.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public static int getSignatureByteArrayLength(SignatureAlgorithm signatureAlgorithm) throws JwtException {
        int r0 = C46341.$SwitchMap$io$jsonwebtoken$SignatureAlgorithm[signatureAlgorithm.ordinal()];
        if (r0 != 1) {
            if (r0 != 2) {
                if (r0 == 3) {
                    return 132;
                }
                throw new JwtException("Unsupported Algorithm: " + signatureAlgorithm.name());
            }
            return 96;
        }
        return 64;
    }

    public static byte[] transcodeSignatureToConcat(byte[] bArr, int r12) throws JwtException {
        int r0;
        if (bArr.length < 8 || bArr[0] != 48) {
            throw new JwtException("Invalid ECDSA signature format");
        }
        if (bArr[1] > 0) {
            r0 = 2;
        } else if (bArr[1] != -127) {
            throw new JwtException("Invalid ECDSA signature format");
        } else {
            r0 = 3;
        }
        int r2 = bArr[r0 + 1];
        int r4 = r2;
        while (r4 > 0 && bArr[((r0 + 2) + r2) - r4] == 0) {
            r4--;
        }
        int r5 = r0 + 2 + r2;
        int r6 = bArr[r5 + 1];
        int r7 = r6;
        while (r7 > 0 && bArr[((r5 + 2) + r6) - r7] == 0) {
            r7--;
        }
        int max = Math.max(Math.max(r4, r7), r12 / 2);
        int r8 = r0 - 1;
        if ((bArr[r8] & 255) != bArr.length - r0 || (bArr[r8] & 255) != r2 + 2 + 2 + r6 || bArr[r0] != 2 || bArr[r5] != 2) {
            throw new JwtException("Invalid ECDSA signature format");
        }
        int r02 = max * 2;
        byte[] bArr2 = new byte[r02];
        System.arraycopy(bArr, r5 - r4, bArr2, max - r4, r4);
        System.arraycopy(bArr, ((r5 + 2) + r6) - r7, bArr2, r02 - r7, r7);
        return bArr2;
    }

    public static byte[] transcodeSignatureToDER(byte[] bArr) throws JwtException {
        byte[] bArr2;
        int length = bArr.length / 2;
        int r2 = length;
        while (r2 > 0 && bArr[length - r2] == 0) {
            r2--;
        }
        int r3 = length - r2;
        int r4 = bArr[r3] < 0 ? r2 + 1 : r2;
        int r5 = length;
        while (r5 > 0 && bArr[(length * 2) - r5] == 0) {
            r5--;
        }
        int r0 = (length * 2) - r5;
        int r6 = bArr[r0] < 0 ? r5 + 1 : r5;
        int r7 = r4 + 2 + 2 + r6;
        if (r7 > 255) {
            throw new JwtException("Invalid ECDSA signature format");
        }
        int r9 = 1;
        if (r7 < 128) {
            bArr2 = new byte[r4 + 4 + 2 + r6];
        } else {
            bArr2 = new byte[r4 + 5 + 2 + r6];
            bArr2[1] = -127;
            r9 = 2;
        }
        bArr2[0] = 48;
        int r10 = r9 + 1;
        bArr2[r9] = (byte) r7;
        int r72 = r10 + 1;
        bArr2[r10] = 2;
        bArr2[r72] = (byte) r4;
        int r92 = r72 + 1 + r4;
        System.arraycopy(bArr, r3, bArr2, r92 - r2, r2);
        int r22 = r92 + 1;
        bArr2[r92] = 2;
        bArr2[r22] = (byte) r6;
        System.arraycopy(bArr, r0, bArr2, ((r22 + 1) + r6) - r5, r5);
        return bArr2;
    }
}
