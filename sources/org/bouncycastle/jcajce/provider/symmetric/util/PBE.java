package org.bouncycastle.jcajce.provider.symmetric.util;

import java.security.InvalidAlgorithmParameterException;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.SecretKey;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.PBEParametersGenerator;
import org.bouncycastle.crypto.digests.GOST3411Digest;
import org.bouncycastle.crypto.digests.MD2Digest;
import org.bouncycastle.crypto.digests.RIPEMD160Digest;
import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.crypto.digests.TigerDigest;
import org.bouncycastle.crypto.generators.OpenSSLPBEParametersGenerator;
import org.bouncycastle.crypto.generators.PKCS12ParametersGenerator;
import org.bouncycastle.crypto.generators.PKCS5S1ParametersGenerator;
import org.bouncycastle.crypto.generators.PKCS5S2ParametersGenerator;
import org.bouncycastle.crypto.params.DESParameters;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.crypto.util.DigestFactory;

/* loaded from: classes5.dex */
public interface PBE {
    public static final int GOST3411 = 6;
    public static final int MD2 = 5;
    public static final int MD5 = 0;
    public static final int OPENSSL = 3;
    public static final int PKCS12 = 2;
    public static final int PKCS5S1 = 0;
    public static final int PKCS5S1_UTF8 = 4;
    public static final int PKCS5S2 = 1;
    public static final int PKCS5S2_UTF8 = 5;
    public static final int RIPEMD160 = 2;
    public static final int SHA1 = 1;
    public static final int SHA224 = 7;
    public static final int SHA256 = 4;
    public static final int SHA384 = 8;
    public static final int SHA3_224 = 10;
    public static final int SHA3_256 = 11;
    public static final int SHA3_384 = 12;
    public static final int SHA3_512 = 13;
    public static final int SHA512 = 9;
    public static final int SM3 = 14;
    public static final int TIGER = 3;

    /* loaded from: classes5.dex */
    public static class Util {
        private static byte[] convertPassword(int r1, PBEKeySpec pBEKeySpec) {
            return r1 == 2 ? PBEParametersGenerator.PKCS12PasswordToBytes(pBEKeySpec.getPassword()) : (r1 == 5 || r1 == 4) ? PBEParametersGenerator.PKCS5PasswordToUTF8Bytes(pBEKeySpec.getPassword()) : PBEParametersGenerator.PKCS5PasswordToBytes(pBEKeySpec.getPassword());
        }

        private static PBEParametersGenerator makePBEGenerator(int r3, int r4) {
            if (r3 == 0 || r3 == 4) {
                if (r4 != 0) {
                    if (r4 != 1) {
                        if (r4 == 5) {
                            return new PKCS5S1ParametersGenerator(new MD2Digest());
                        }
                        throw new IllegalStateException("PKCS5 scheme 1 only supports MD2, MD5 and SHA1.");
                    }
                    return new PKCS5S1ParametersGenerator(DigestFactory.createSHA1());
                }
                return new PKCS5S1ParametersGenerator(DigestFactory.createMD5());
            } else if (r3 != 1 && r3 != 5) {
                if (r3 == 2) {
                    switch (r4) {
                        case 0:
                            return new PKCS12ParametersGenerator(DigestFactory.createMD5());
                        case 1:
                            return new PKCS12ParametersGenerator(DigestFactory.createSHA1());
                        case 2:
                            return new PKCS12ParametersGenerator(new RIPEMD160Digest());
                        case 3:
                            return new PKCS12ParametersGenerator(new TigerDigest());
                        case 4:
                            return new PKCS12ParametersGenerator(DigestFactory.createSHA256());
                        case 5:
                            return new PKCS12ParametersGenerator(new MD2Digest());
                        case 6:
                            return new PKCS12ParametersGenerator(new GOST3411Digest());
                        case 7:
                            return new PKCS12ParametersGenerator(DigestFactory.createSHA224());
                        case 8:
                            return new PKCS12ParametersGenerator(DigestFactory.createSHA384());
                        case 9:
                            return new PKCS12ParametersGenerator(DigestFactory.createSHA512());
                        default:
                            throw new IllegalStateException("unknown digest scheme for PBE encryption.");
                    }
                }
                return new OpenSSLPBEParametersGenerator();
            } else {
                switch (r4) {
                    case 0:
                        return new PKCS5S2ParametersGenerator(DigestFactory.createMD5());
                    case 1:
                        return new PKCS5S2ParametersGenerator(DigestFactory.createSHA1());
                    case 2:
                        return new PKCS5S2ParametersGenerator(new RIPEMD160Digest());
                    case 3:
                        return new PKCS5S2ParametersGenerator(new TigerDigest());
                    case 4:
                        return new PKCS5S2ParametersGenerator(DigestFactory.createSHA256());
                    case 5:
                        return new PKCS5S2ParametersGenerator(new MD2Digest());
                    case 6:
                        return new PKCS5S2ParametersGenerator(new GOST3411Digest());
                    case 7:
                        return new PKCS5S2ParametersGenerator(DigestFactory.createSHA224());
                    case 8:
                        return new PKCS5S2ParametersGenerator(DigestFactory.createSHA384());
                    case 9:
                        return new PKCS5S2ParametersGenerator(DigestFactory.createSHA512());
                    case 10:
                        return new PKCS5S2ParametersGenerator(DigestFactory.createSHA3_224());
                    case 11:
                        return new PKCS5S2ParametersGenerator(DigestFactory.createSHA3_256());
                    case 12:
                        return new PKCS5S2ParametersGenerator(DigestFactory.createSHA3_384());
                    case 13:
                        return new PKCS5S2ParametersGenerator(DigestFactory.createSHA3_512());
                    case 14:
                        return new PKCS5S2ParametersGenerator(new SM3Digest());
                    default:
                        throw new IllegalStateException("unknown digest scheme for PBE PKCS5S2 encryption.");
                }
            }
        }

        public static CipherParameters makePBEMacParameters(SecretKey secretKey, int r2, int r3, int r4, PBEParameterSpec pBEParameterSpec) {
            PBEParametersGenerator makePBEGenerator = makePBEGenerator(r2, r3);
            byte[] encoded = secretKey.getEncoded();
            makePBEGenerator.init(secretKey.getEncoded(), pBEParameterSpec.getSalt(), pBEParameterSpec.getIterationCount());
            CipherParameters generateDerivedMacParameters = makePBEGenerator.generateDerivedMacParameters(r4);
            for (int r42 = 0; r42 != encoded.length; r42++) {
                encoded[r42] = 0;
            }
            return generateDerivedMacParameters;
        }

        public static CipherParameters makePBEMacParameters(PBEKeySpec pBEKeySpec, int r2, int r3, int r4) {
            PBEParametersGenerator makePBEGenerator = makePBEGenerator(r2, r3);
            byte[] convertPassword = convertPassword(r2, pBEKeySpec);
            makePBEGenerator.init(convertPassword, pBEKeySpec.getSalt(), pBEKeySpec.getIterationCount());
            CipherParameters generateDerivedMacParameters = makePBEGenerator.generateDerivedMacParameters(r4);
            for (int r42 = 0; r42 != convertPassword.length; r42++) {
                convertPassword[r42] = 0;
            }
            return generateDerivedMacParameters;
        }

        public static CipherParameters makePBEMacParameters(BCPBEKey bCPBEKey, AlgorithmParameterSpec algorithmParameterSpec) {
            if (algorithmParameterSpec == null || !(algorithmParameterSpec instanceof PBEParameterSpec)) {
                throw new IllegalArgumentException("Need a PBEParameter spec with a PBE key.");
            }
            PBEParameterSpec pBEParameterSpec = (PBEParameterSpec) algorithmParameterSpec;
            PBEParametersGenerator makePBEGenerator = makePBEGenerator(bCPBEKey.getType(), bCPBEKey.getDigest());
            makePBEGenerator.init(bCPBEKey.getEncoded(), pBEParameterSpec.getSalt(), pBEParameterSpec.getIterationCount());
            return makePBEGenerator.generateDerivedMacParameters(bCPBEKey.getKeySize());
        }

        public static CipherParameters makePBEParameters(PBEKeySpec pBEKeySpec, int r2, int r3, int r4, int r5) {
            PBEParametersGenerator makePBEGenerator = makePBEGenerator(r2, r3);
            byte[] convertPassword = convertPassword(r2, pBEKeySpec);
            makePBEGenerator.init(convertPassword, pBEKeySpec.getSalt(), pBEKeySpec.getIterationCount());
            CipherParameters generateDerivedParameters = r5 != 0 ? makePBEGenerator.generateDerivedParameters(r4, r5) : makePBEGenerator.generateDerivedParameters(r4);
            for (int r42 = 0; r42 != convertPassword.length; r42++) {
                convertPassword[r42] = 0;
            }
            return generateDerivedParameters;
        }

        public static CipherParameters makePBEParameters(BCPBEKey bCPBEKey, AlgorithmParameterSpec algorithmParameterSpec, String str) {
            if (algorithmParameterSpec == null || !(algorithmParameterSpec instanceof PBEParameterSpec)) {
                throw new IllegalArgumentException("Need a PBEParameter spec with a PBE key.");
            }
            PBEParameterSpec pBEParameterSpec = (PBEParameterSpec) algorithmParameterSpec;
            PBEParametersGenerator makePBEGenerator = makePBEGenerator(bCPBEKey.getType(), bCPBEKey.getDigest());
            byte[] encoded = bCPBEKey.getEncoded();
            if (bCPBEKey.shouldTryWrongPKCS12()) {
                encoded = new byte[2];
            }
            makePBEGenerator.init(encoded, pBEParameterSpec.getSalt(), pBEParameterSpec.getIterationCount());
            CipherParameters generateDerivedParameters = bCPBEKey.getIvSize() != 0 ? makePBEGenerator.generateDerivedParameters(bCPBEKey.getKeySize(), bCPBEKey.getIvSize()) : makePBEGenerator.generateDerivedParameters(bCPBEKey.getKeySize());
            if (str.startsWith("DES")) {
                if (generateDerivedParameters instanceof ParametersWithIV) {
                    DESParameters.setOddParity(((KeyParameter) ((ParametersWithIV) generateDerivedParameters).getParameters()).getKey());
                } else {
                    DESParameters.setOddParity(((KeyParameter) generateDerivedParameters).getKey());
                }
            }
            return generateDerivedParameters;
        }

        public static CipherParameters makePBEParameters(byte[] bArr, int r2, int r3, int r4, int r5, AlgorithmParameterSpec algorithmParameterSpec, String str) throws InvalidAlgorithmParameterException {
            if (algorithmParameterSpec == null || !(algorithmParameterSpec instanceof PBEParameterSpec)) {
                throw new InvalidAlgorithmParameterException("Need a PBEParameter spec with a PBE key.");
            }
            PBEParameterSpec pBEParameterSpec = (PBEParameterSpec) algorithmParameterSpec;
            PBEParametersGenerator makePBEGenerator = makePBEGenerator(r2, r3);
            makePBEGenerator.init(bArr, pBEParameterSpec.getSalt(), pBEParameterSpec.getIterationCount());
            CipherParameters generateDerivedParameters = r5 != 0 ? makePBEGenerator.generateDerivedParameters(r4, r5) : makePBEGenerator.generateDerivedParameters(r4);
            if (str.startsWith("DES")) {
                if (generateDerivedParameters instanceof ParametersWithIV) {
                    DESParameters.setOddParity(((KeyParameter) ((ParametersWithIV) generateDerivedParameters).getParameters()).getKey());
                } else {
                    DESParameters.setOddParity(((KeyParameter) generateDerivedParameters).getKey());
                }
            }
            return generateDerivedParameters;
        }
    }
}
