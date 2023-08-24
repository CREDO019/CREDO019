package org.bouncycastle.jcajce.provider.asymmetric.p037ec;

import java.io.ByteArrayOutputStream;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.BadPaddingException;
import javax.crypto.CipherSpi;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.digests.Blake2bDigest;
import org.bouncycastle.crypto.digests.Blake2sDigest;
import org.bouncycastle.crypto.digests.MD5Digest;
import org.bouncycastle.crypto.digests.RIPEMD160Digest;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.digests.SHA224Digest;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.digests.SHA384Digest;
import org.bouncycastle.crypto.digests.SHA512Digest;
import org.bouncycastle.crypto.digests.WhirlpoolDigest;
import org.bouncycastle.crypto.engines.SM2Engine;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.jcajce.provider.asymmetric.util.ECUtil;
import org.bouncycastle.jcajce.provider.util.BadBlockException;
import org.bouncycastle.jcajce.util.BCJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jce.interfaces.ECKey;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Strings;

/* renamed from: org.bouncycastle.jcajce.provider.asymmetric.ec.GMCipherSpi */
/* loaded from: classes5.dex */
public class GMCipherSpi extends CipherSpi {
    private SM2Engine engine;
    private AsymmetricKeyParameter key;
    private SecureRandom random;
    private final JcaJceHelper helper = new BCJcaJceHelper();
    private int state = -1;
    private ErasableOutputStream buffer = new ErasableOutputStream();

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: org.bouncycastle.jcajce.provider.asymmetric.ec.GMCipherSpi$ErasableOutputStream */
    /* loaded from: classes5.dex */
    public static final class ErasableOutputStream extends ByteArrayOutputStream {
        public void erase() {
            Arrays.fill(this.buf, (byte) 0);
            reset();
        }

        public byte[] getBuf() {
            return this.buf;
        }
    }

    /* renamed from: org.bouncycastle.jcajce.provider.asymmetric.ec.GMCipherSpi$SM2 */
    /* loaded from: classes5.dex */
    public static class SM2 extends GMCipherSpi {
        public SM2() {
            super(new SM2Engine());
        }
    }

    /* renamed from: org.bouncycastle.jcajce.provider.asymmetric.ec.GMCipherSpi$SM2withBlake2b */
    /* loaded from: classes5.dex */
    public static class SM2withBlake2b extends GMCipherSpi {
        public SM2withBlake2b() {
            super(new SM2Engine(new Blake2bDigest(512)));
        }
    }

    /* renamed from: org.bouncycastle.jcajce.provider.asymmetric.ec.GMCipherSpi$SM2withBlake2s */
    /* loaded from: classes5.dex */
    public static class SM2withBlake2s extends GMCipherSpi {
        public SM2withBlake2s() {
            super(new SM2Engine(new Blake2sDigest(256)));
        }
    }

    /* renamed from: org.bouncycastle.jcajce.provider.asymmetric.ec.GMCipherSpi$SM2withMD5 */
    /* loaded from: classes5.dex */
    public static class SM2withMD5 extends GMCipherSpi {
        public SM2withMD5() {
            super(new SM2Engine(new MD5Digest()));
        }
    }

    /* renamed from: org.bouncycastle.jcajce.provider.asymmetric.ec.GMCipherSpi$SM2withRMD */
    /* loaded from: classes5.dex */
    public static class SM2withRMD extends GMCipherSpi {
        public SM2withRMD() {
            super(new SM2Engine(new RIPEMD160Digest()));
        }
    }

    /* renamed from: org.bouncycastle.jcajce.provider.asymmetric.ec.GMCipherSpi$SM2withSha1 */
    /* loaded from: classes5.dex */
    public static class SM2withSha1 extends GMCipherSpi {
        public SM2withSha1() {
            super(new SM2Engine(new SHA1Digest()));
        }
    }

    /* renamed from: org.bouncycastle.jcajce.provider.asymmetric.ec.GMCipherSpi$SM2withSha224 */
    /* loaded from: classes5.dex */
    public static class SM2withSha224 extends GMCipherSpi {
        public SM2withSha224() {
            super(new SM2Engine(new SHA224Digest()));
        }
    }

    /* renamed from: org.bouncycastle.jcajce.provider.asymmetric.ec.GMCipherSpi$SM2withSha256 */
    /* loaded from: classes5.dex */
    public static class SM2withSha256 extends GMCipherSpi {
        public SM2withSha256() {
            super(new SM2Engine(new SHA256Digest()));
        }
    }

    /* renamed from: org.bouncycastle.jcajce.provider.asymmetric.ec.GMCipherSpi$SM2withSha384 */
    /* loaded from: classes5.dex */
    public static class SM2withSha384 extends GMCipherSpi {
        public SM2withSha384() {
            super(new SM2Engine(new SHA384Digest()));
        }
    }

    /* renamed from: org.bouncycastle.jcajce.provider.asymmetric.ec.GMCipherSpi$SM2withSha512 */
    /* loaded from: classes5.dex */
    public static class SM2withSha512 extends GMCipherSpi {
        public SM2withSha512() {
            super(new SM2Engine(new SHA512Digest()));
        }
    }

    /* renamed from: org.bouncycastle.jcajce.provider.asymmetric.ec.GMCipherSpi$SM2withWhirlpool */
    /* loaded from: classes5.dex */
    public static class SM2withWhirlpool extends GMCipherSpi {
        public SM2withWhirlpool() {
            super(new SM2Engine(new WhirlpoolDigest()));
        }
    }

    public GMCipherSpi(SM2Engine sM2Engine) {
        this.engine = sM2Engine;
    }

    @Override // javax.crypto.CipherSpi
    public int engineDoFinal(byte[] bArr, int r2, int r3, byte[] bArr2, int r5) throws ShortBufferException, IllegalBlockSizeException, BadPaddingException {
        byte[] engineDoFinal = engineDoFinal(bArr, r2, r3);
        System.arraycopy(engineDoFinal, 0, bArr2, r5, engineDoFinal.length);
        return engineDoFinal.length;
    }

    @Override // javax.crypto.CipherSpi
    public byte[] engineDoFinal(byte[] bArr, int r6, int r7) throws IllegalBlockSizeException, BadPaddingException {
        byte[] processBlock;
        if (r7 != 0) {
            this.buffer.write(bArr, r6, r7);
        }
        try {
            int r5 = this.state;
            if (r5 == 1 || r5 == 3) {
                try {
                    this.engine.init(true, new ParametersWithRandom(this.key, this.random));
                    processBlock = this.engine.processBlock(this.buffer.getBuf(), 0, this.buffer.size());
                    return processBlock;
                } catch (Exception e) {
                    throw new BadBlockException("unable to process block", e);
                }
            } else if (r5 == 2 || r5 == 4) {
                try {
                    this.engine.init(false, this.key);
                    processBlock = this.engine.processBlock(this.buffer.getBuf(), 0, this.buffer.size());
                    return processBlock;
                } catch (Exception e2) {
                    throw new BadBlockException("unable to process block", e2);
                }
            } else {
                throw new IllegalStateException("cipher not initialised");
            }
        } finally {
            this.buffer.erase();
        }
    }

    @Override // javax.crypto.CipherSpi
    public int engineGetBlockSize() {
        return 0;
    }

    @Override // javax.crypto.CipherSpi
    public byte[] engineGetIV() {
        return null;
    }

    @Override // javax.crypto.CipherSpi
    public int engineGetKeySize(Key key) {
        if (key instanceof ECKey) {
            return ((ECKey) key).getParameters().getCurve().getFieldSize();
        }
        throw new IllegalArgumentException("not an EC key");
    }

    @Override // javax.crypto.CipherSpi
    public int engineGetOutputSize(int r3) {
        int r0 = this.state;
        if (r0 == 1 || r0 == 3 || r0 == 2 || r0 == 4) {
            return this.engine.getOutputSize(r3);
        }
        throw new IllegalStateException("cipher not initialised");
    }

    @Override // javax.crypto.CipherSpi
    public AlgorithmParameters engineGetParameters() {
        return null;
    }

    @Override // javax.crypto.CipherSpi
    public void engineInit(int r1, Key key, AlgorithmParameters algorithmParameters, SecureRandom secureRandom) throws InvalidKeyException, InvalidAlgorithmParameterException {
        if (algorithmParameters == null) {
            engineInit(r1, key, (AlgorithmParameterSpec) null, secureRandom);
            return;
        }
        throw new InvalidAlgorithmParameterException("cannot recognise parameters: " + algorithmParameters.getClass().getName());
    }

    @Override // javax.crypto.CipherSpi
    public void engineInit(int r3, Key key, SecureRandom secureRandom) throws InvalidKeyException {
        try {
            AlgorithmParameterSpec algorithmParameterSpec = null;
            engineInit(r3, key, (AlgorithmParameterSpec) null, secureRandom);
        } catch (InvalidAlgorithmParameterException e) {
            throw new IllegalArgumentException("cannot handle supplied parameter spec: " + e.getMessage());
        }
    }

    @Override // javax.crypto.CipherSpi
    public void engineInit(int r1, Key key, AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidAlgorithmParameterException, InvalidKeyException {
        AsymmetricKeyParameter generatePublicKeyParameter;
        if (r1 == 1 || r1 == 3) {
            if (!(key instanceof PublicKey)) {
                throw new InvalidKeyException("must be passed public EC key for encryption");
            }
            generatePublicKeyParameter = ECUtils.generatePublicKeyParameter((PublicKey) key);
        } else if (r1 != 2 && r1 != 4) {
            throw new InvalidKeyException("must be passed EC key");
        } else {
            if (!(key instanceof PrivateKey)) {
                throw new InvalidKeyException("must be passed private EC key for decryption");
            }
            generatePublicKeyParameter = ECUtil.generatePrivateKeyParameter((PrivateKey) key);
        }
        this.key = generatePublicKeyParameter;
        if (secureRandom != null) {
            this.random = secureRandom;
        } else {
            this.random = CryptoServicesRegistrar.getSecureRandom();
        }
        this.state = r1;
        this.buffer.reset();
    }

    @Override // javax.crypto.CipherSpi
    public void engineSetMode(String str) throws NoSuchAlgorithmException {
        if (Strings.toUpperCase(str).equals("NONE")) {
            return;
        }
        throw new IllegalArgumentException("can't support mode " + str);
    }

    @Override // javax.crypto.CipherSpi
    public void engineSetPadding(String str) throws NoSuchPaddingException {
        if (!Strings.toUpperCase(str).equals("NOPADDING")) {
            throw new NoSuchPaddingException("padding not available with IESCipher");
        }
    }

    @Override // javax.crypto.CipherSpi
    public int engineUpdate(byte[] bArr, int r2, int r3, byte[] bArr2, int r5) {
        this.buffer.write(bArr, r2, r3);
        return 0;
    }

    @Override // javax.crypto.CipherSpi
    public byte[] engineUpdate(byte[] bArr, int r3, int r4) {
        this.buffer.write(bArr, r3, r4);
        return null;
    }
}
