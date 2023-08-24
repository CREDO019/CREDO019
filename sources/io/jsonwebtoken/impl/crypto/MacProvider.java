package io.jsonwebtoken.impl.crypto;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.lang.Assert;
import java.security.Key;
import java.security.SecureRandom;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes5.dex */
public abstract class MacProvider extends SignatureProvider {
    /* JADX INFO: Access modifiers changed from: protected */
    public MacProvider(SignatureAlgorithm signatureAlgorithm, Key key) {
        super(signatureAlgorithm, key);
        Assert.isTrue(signatureAlgorithm.isHmac(), "SignatureAlgorithm must be a HMAC SHA algorithm.");
    }

    public static SecretKey generateKey() {
        return generateKey(SignatureAlgorithm.HS512);
    }

    public static SecretKey generateKey(SignatureAlgorithm signatureAlgorithm) {
        return generateKey(signatureAlgorithm, SignatureProvider.DEFAULT_SECURE_RANDOM);
    }

    public static SecretKey generateKey(SignatureAlgorithm signatureAlgorithm, SecureRandom secureRandom) {
        Assert.isTrue(signatureAlgorithm.isHmac(), "SignatureAlgorithm argument must represent an HMAC algorithm.");
        int r0 = C46351.$SwitchMap$io$jsonwebtoken$SignatureAlgorithm[signatureAlgorithm.ordinal()];
        byte[] bArr = r0 != 1 ? r0 != 2 ? new byte[64] : new byte[48] : new byte[32];
        secureRandom.nextBytes(bArr);
        return new SecretKeySpec(bArr, signatureAlgorithm.getJcaName());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: io.jsonwebtoken.impl.crypto.MacProvider$1 */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class C46351 {
        static final /* synthetic */ int[] $SwitchMap$io$jsonwebtoken$SignatureAlgorithm;

        static {
            int[] r0 = new int[SignatureAlgorithm.values().length];
            $SwitchMap$io$jsonwebtoken$SignatureAlgorithm = r0;
            try {
                r0[SignatureAlgorithm.HS256.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$jsonwebtoken$SignatureAlgorithm[SignatureAlgorithm.HS384.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }
}
