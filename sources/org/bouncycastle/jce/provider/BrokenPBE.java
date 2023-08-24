package org.bouncycastle.jce.provider;

import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.spec.PBEParameterSpec;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.PBEParametersGenerator;
import org.bouncycastle.crypto.digests.MD5Digest;
import org.bouncycastle.crypto.digests.RIPEMD160Digest;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.generators.PKCS12ParametersGenerator;
import org.bouncycastle.crypto.generators.PKCS5S1ParametersGenerator;
import org.bouncycastle.crypto.generators.PKCS5S2ParametersGenerator;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.jcajce.provider.symmetric.util.BCPBEKey;

/* loaded from: classes5.dex */
public interface BrokenPBE {
    public static final int MD5 = 0;
    public static final int OLD_PKCS12 = 3;
    public static final int PKCS12 = 2;
    public static final int PKCS5S1 = 0;
    public static final int PKCS5S2 = 1;
    public static final int RIPEMD160 = 2;
    public static final int SHA1 = 1;

    /* loaded from: classes5.dex */
    public static class Util {
        private static PBEParametersGenerator makePBEGenerator(int r4, int r5) {
            if (r4 == 0) {
                if (r5 != 0) {
                    if (r5 == 1) {
                        return new PKCS5S1ParametersGenerator(new SHA1Digest());
                    }
                    throw new IllegalStateException("PKCS5 scheme 1 only supports only MD5 and SHA1.");
                }
                return new PKCS5S1ParametersGenerator(new MD5Digest());
            } else if (r4 == 1) {
                return new PKCS5S2ParametersGenerator();
            } else {
                if (r4 == 3) {
                    if (r5 != 0) {
                        if (r5 != 1) {
                            if (r5 == 2) {
                                return new OldPKCS12ParametersGenerator(new RIPEMD160Digest());
                            }
                            throw new IllegalStateException("unknown digest scheme for PBE encryption.");
                        }
                        return new OldPKCS12ParametersGenerator(new SHA1Digest());
                    }
                    return new OldPKCS12ParametersGenerator(new MD5Digest());
                } else if (r5 != 0) {
                    if (r5 != 1) {
                        if (r5 == 2) {
                            return new PKCS12ParametersGenerator(new RIPEMD160Digest());
                        }
                        throw new IllegalStateException("unknown digest scheme for PBE encryption.");
                    }
                    return new PKCS12ParametersGenerator(new SHA1Digest());
                } else {
                    return new PKCS12ParametersGenerator(new MD5Digest());
                }
            }
        }

        static CipherParameters makePBEMacParameters(BCPBEKey bCPBEKey, AlgorithmParameterSpec algorithmParameterSpec, int r3, int r4, int r5) {
            if (algorithmParameterSpec == null || !(algorithmParameterSpec instanceof PBEParameterSpec)) {
                throw new IllegalArgumentException("Need a PBEParameter spec with a PBE key.");
            }
            PBEParameterSpec pBEParameterSpec = (PBEParameterSpec) algorithmParameterSpec;
            PBEParametersGenerator makePBEGenerator = makePBEGenerator(r3, r4);
            byte[] encoded = bCPBEKey.getEncoded();
            makePBEGenerator.init(encoded, pBEParameterSpec.getSalt(), pBEParameterSpec.getIterationCount());
            CipherParameters generateDerivedMacParameters = makePBEGenerator.generateDerivedMacParameters(r5);
            for (int r42 = 0; r42 != encoded.length; r42++) {
                encoded[r42] = 0;
            }
            return generateDerivedMacParameters;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static CipherParameters makePBEParameters(BCPBEKey bCPBEKey, AlgorithmParameterSpec algorithmParameterSpec, int r3, int r4, String str, int r6, int r7) {
            if (algorithmParameterSpec == null || !(algorithmParameterSpec instanceof PBEParameterSpec)) {
                throw new IllegalArgumentException("Need a PBEParameter spec with a PBE key.");
            }
            PBEParameterSpec pBEParameterSpec = (PBEParameterSpec) algorithmParameterSpec;
            PBEParametersGenerator makePBEGenerator = makePBEGenerator(r3, r4);
            byte[] encoded = bCPBEKey.getEncoded();
            makePBEGenerator.init(encoded, pBEParameterSpec.getSalt(), pBEParameterSpec.getIterationCount());
            CipherParameters generateDerivedParameters = r7 != 0 ? makePBEGenerator.generateDerivedParameters(r6, r7) : makePBEGenerator.generateDerivedParameters(r6);
            if (str.startsWith("DES")) {
                if (generateDerivedParameters instanceof ParametersWithIV) {
                    setOddParity(((KeyParameter) ((ParametersWithIV) generateDerivedParameters).getParameters()).getKey());
                } else {
                    setOddParity(((KeyParameter) generateDerivedParameters).getKey());
                }
            }
            for (int r42 = 0; r42 != encoded.length; r42++) {
                encoded[r42] = 0;
            }
            return generateDerivedParameters;
        }

        private static void setOddParity(byte[] bArr) {
            for (int r0 = 0; r0 < bArr.length; r0++) {
                byte b = bArr[r0];
                bArr[r0] = (byte) ((((b >> 7) ^ ((((((b >> 1) ^ (b >> 2)) ^ (b >> 3)) ^ (b >> 4)) ^ (b >> 5)) ^ (b >> 6))) ^ 1) | (b & 254));
            }
        }
    }
}
