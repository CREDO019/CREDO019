package org.bouncycastle.crypto.engines;

import androidx.core.view.InputDeviceCompat;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.bouncycastle.crypto.BasicAgreement;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.DerivationFunction;
import org.bouncycastle.crypto.DerivationParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.DigestDerivationFunction;
import org.bouncycastle.crypto.EphemeralKeyPair;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.KeyParser;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.generators.EphemeralKeyPairGenerator;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.IESParameters;
import org.bouncycastle.crypto.params.IESWithCipherParameters;
import org.bouncycastle.crypto.params.ISO18033KDFParameters;
import org.bouncycastle.crypto.params.KDFParameters;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.BigIntegers;
import org.bouncycastle.util.Pack;

/* loaded from: classes5.dex */
public class EthereumIESEngine {

    /* renamed from: IV */
    private byte[] f1925IV;

    /* renamed from: V */
    byte[] f1926V;
    BasicAgreement agree;
    BufferedBlockCipher cipher;
    byte[] commonMac;
    boolean forEncryption;
    DerivationFunction kdf;
    private EphemeralKeyPairGenerator keyPairGenerator;
    private KeyParser keyParser;
    Mac mac;
    byte[] macBuf;
    IESParameters param;
    CipherParameters privParam;
    CipherParameters pubParam;

    /* loaded from: classes5.dex */
    public static class HandshakeKDFFunction implements DigestDerivationFunction {
        private int counterStart;
        private Digest digest;

        /* renamed from: iv */
        private byte[] f1927iv;
        private byte[] shared;

        public HandshakeKDFFunction(int r1, Digest digest) {
            this.counterStart = r1;
            this.digest = digest;
        }

        @Override // org.bouncycastle.crypto.DerivationFunction
        public int generateBytes(byte[] bArr, int r18, int r19) throws DataLengthException, IllegalArgumentException {
            int r2 = r19;
            int r4 = r18;
            if (bArr.length - r2 >= r4) {
                long j = r2;
                int digestSize = this.digest.getDigestSize();
                if (j <= 8589934591L) {
                    long j2 = digestSize;
                    int r7 = (int) (((j + j2) - 1) / j2);
                    byte[] bArr2 = new byte[this.digest.getDigestSize()];
                    int r9 = 4;
                    byte[] bArr3 = new byte[4];
                    Pack.intToBigEndian(this.counterStart, bArr3, 0);
                    int r11 = this.counterStart & InputDeviceCompat.SOURCE_ANY;
                    int r13 = 0;
                    while (r13 < r7) {
                        this.digest.update(bArr3, 0, r9);
                        Digest digest = this.digest;
                        byte[] bArr4 = this.shared;
                        digest.update(bArr4, 0, bArr4.length);
                        byte[] bArr5 = this.f1927iv;
                        if (bArr5 != null) {
                            this.digest.update(bArr5, 0, bArr5.length);
                        }
                        this.digest.doFinal(bArr2, 0);
                        if (r2 > digestSize) {
                            System.arraycopy(bArr2, 0, bArr, r4, digestSize);
                            r4 += digestSize;
                            r2 -= digestSize;
                        } else {
                            System.arraycopy(bArr2, 0, bArr, r4, r2);
                        }
                        byte b = (byte) (bArr3[3] + 1);
                        bArr3[3] = b;
                        if (b == 0) {
                            r11 += 256;
                            Pack.intToBigEndian(r11, bArr3, 0);
                        }
                        r13++;
                        r9 = 4;
                    }
                    this.digest.reset();
                    return (int) j;
                }
                throw new IllegalArgumentException("output length too large");
            }
            throw new OutputLengthException("output buffer too small");
        }

        @Override // org.bouncycastle.crypto.DigestDerivationFunction
        public Digest getDigest() {
            return this.digest;
        }

        @Override // org.bouncycastle.crypto.DerivationFunction
        public void init(DerivationParameters derivationParameters) {
            if (derivationParameters instanceof KDFParameters) {
                KDFParameters kDFParameters = (KDFParameters) derivationParameters;
                this.shared = kDFParameters.getSharedSecret();
                this.f1927iv = kDFParameters.getIV();
            } else if (!(derivationParameters instanceof ISO18033KDFParameters)) {
                throw new IllegalArgumentException("KDF parameters required for generator");
            } else {
                this.shared = ((ISO18033KDFParameters) derivationParameters).getSeed();
                this.f1927iv = null;
            }
        }
    }

    public EthereumIESEngine(BasicAgreement basicAgreement, DerivationFunction derivationFunction, Mac mac, byte[] bArr) {
        this.agree = basicAgreement;
        this.kdf = derivationFunction;
        this.mac = mac;
        this.macBuf = new byte[mac.getMacSize()];
        this.commonMac = bArr;
        this.cipher = null;
    }

    public EthereumIESEngine(BasicAgreement basicAgreement, DerivationFunction derivationFunction, Mac mac, byte[] bArr, BufferedBlockCipher bufferedBlockCipher) {
        this.agree = basicAgreement;
        this.kdf = derivationFunction;
        this.mac = mac;
        this.macBuf = new byte[mac.getMacSize()];
        this.commonMac = bArr;
        this.cipher = bufferedBlockCipher;
    }

    private byte[] decryptBlock(byte[] bArr, int r14, int r15) throws InvalidCipherTextException {
        byte[] bArr2;
        byte[] bArr3;
        int processBytes;
        if (r15 >= this.f1926V.length + this.mac.getMacSize()) {
            if (this.cipher == null) {
                int length = (r15 - this.f1926V.length) - this.mac.getMacSize();
                byte[] bArr4 = new byte[length];
                int macKeySize = this.param.getMacKeySize() / 8;
                bArr2 = new byte[macKeySize];
                int r5 = length + macKeySize;
                byte[] bArr5 = new byte[r5];
                this.kdf.generateBytes(bArr5, 0, r5);
                if (this.f1926V.length != 0) {
                    System.arraycopy(bArr5, 0, bArr2, 0, macKeySize);
                    System.arraycopy(bArr5, macKeySize, bArr4, 0, length);
                } else {
                    System.arraycopy(bArr5, 0, bArr4, 0, length);
                    System.arraycopy(bArr5, length, bArr2, 0, macKeySize);
                }
                bArr3 = new byte[length];
                for (int r52 = 0; r52 != length; r52++) {
                    bArr3[r52] = (byte) (bArr[(this.f1926V.length + r14) + r52] ^ bArr4[r52]);
                }
                processBytes = 0;
            } else {
                int cipherKeySize = ((IESWithCipherParameters) this.param).getCipherKeySize() / 8;
                byte[] bArr6 = new byte[cipherKeySize];
                int macKeySize2 = this.param.getMacKeySize() / 8;
                bArr2 = new byte[macKeySize2];
                int r53 = cipherKeySize + macKeySize2;
                byte[] bArr7 = new byte[r53];
                this.kdf.generateBytes(bArr7, 0, r53);
                System.arraycopy(bArr7, 0, bArr6, 0, cipherKeySize);
                System.arraycopy(bArr7, cipherKeySize, bArr2, 0, macKeySize2);
                CipherParameters keyParameter = new KeyParameter(bArr6);
                byte[] bArr8 = this.f1925IV;
                if (bArr8 != null) {
                    keyParameter = new ParametersWithIV(keyParameter, bArr8);
                }
                this.cipher.init(false, keyParameter);
                bArr3 = new byte[this.cipher.getOutputSize((r15 - this.f1926V.length) - this.mac.getMacSize())];
                BufferedBlockCipher bufferedBlockCipher = this.cipher;
                byte[] bArr9 = this.f1926V;
                processBytes = bufferedBlockCipher.processBytes(bArr, r14 + bArr9.length, (r15 - bArr9.length) - this.mac.getMacSize(), bArr3, 0);
            }
            byte[] encodingV = this.param.getEncodingV();
            byte[] lengthTag = this.f1926V.length != 0 ? getLengthTag(encodingV) : null;
            int r6 = r14 + r15;
            byte[] copyOfRange = Arrays.copyOfRange(bArr, r6 - this.mac.getMacSize(), r6);
            int length2 = copyOfRange.length;
            byte[] bArr10 = new byte[length2];
            SHA256Digest sHA256Digest = new SHA256Digest();
            byte[] bArr11 = new byte[sHA256Digest.getDigestSize()];
            sHA256Digest.reset();
            sHA256Digest.update(bArr2, 0, bArr2.length);
            sHA256Digest.doFinal(bArr11, 0);
            this.mac.init(new KeyParameter(bArr11));
            Mac mac = this.mac;
            byte[] bArr12 = this.f1925IV;
            mac.update(bArr12, 0, bArr12.length);
            Mac mac2 = this.mac;
            byte[] bArr13 = this.f1926V;
            mac2.update(bArr, r14 + bArr13.length, (r15 - bArr13.length) - length2);
            if (encodingV != null) {
                this.mac.update(encodingV, 0, encodingV.length);
            }
            if (this.f1926V.length != 0) {
                this.mac.update(lengthTag, 0, lengthTag.length);
            }
            Mac mac3 = this.mac;
            byte[] bArr14 = this.commonMac;
            mac3.update(bArr14, 0, bArr14.length);
            this.mac.doFinal(bArr10, 0);
            if (Arrays.constantTimeAreEqual(copyOfRange, bArr10)) {
                BufferedBlockCipher bufferedBlockCipher2 = this.cipher;
                return bufferedBlockCipher2 == null ? bArr3 : Arrays.copyOfRange(bArr3, 0, processBytes + bufferedBlockCipher2.doFinal(bArr3, processBytes));
            }
            throw new InvalidCipherTextException("invalid MAC");
        }
        throw new InvalidCipherTextException("length of input must be greater than the MAC and V combined");
    }

    private byte[] encryptBlock(byte[] bArr, int r13, int r14) throws InvalidCipherTextException {
        BufferedBlockCipher bufferedBlockCipher;
        CipherParameters keyParameter;
        byte[] bArr2;
        byte[] bArr3;
        if (this.cipher == null) {
            byte[] bArr4 = new byte[r14];
            int macKeySize = this.param.getMacKeySize() / 8;
            bArr3 = new byte[macKeySize];
            int r4 = r14 + macKeySize;
            byte[] bArr5 = new byte[r4];
            this.kdf.generateBytes(bArr5, 0, r4);
            if (this.f1926V.length != 0) {
                System.arraycopy(bArr5, 0, bArr3, 0, macKeySize);
                System.arraycopy(bArr5, macKeySize, bArr4, 0, r14);
            } else {
                System.arraycopy(bArr5, 0, bArr4, 0, r14);
                System.arraycopy(bArr5, r14, bArr3, 0, macKeySize);
            }
            bArr2 = new byte[r14];
            for (int r42 = 0; r42 != r14; r42++) {
                bArr2[r42] = (byte) (bArr[r13 + r42] ^ bArr4[r42]);
            }
        } else {
            int cipherKeySize = ((IESWithCipherParameters) this.param).getCipherKeySize() / 8;
            byte[] bArr6 = new byte[cipherKeySize];
            int macKeySize2 = this.param.getMacKeySize() / 8;
            byte[] bArr7 = new byte[macKeySize2];
            int r5 = cipherKeySize + macKeySize2;
            byte[] bArr8 = new byte[r5];
            this.kdf.generateBytes(bArr8, 0, r5);
            System.arraycopy(bArr8, 0, bArr6, 0, cipherKeySize);
            System.arraycopy(bArr8, cipherKeySize, bArr7, 0, macKeySize2);
            if (this.f1925IV != null) {
                bufferedBlockCipher = this.cipher;
                keyParameter = new ParametersWithIV(new KeyParameter(bArr6), this.f1925IV);
            } else {
                bufferedBlockCipher = this.cipher;
                keyParameter = new KeyParameter(bArr6);
            }
            bufferedBlockCipher.init(true, keyParameter);
            bArr2 = new byte[this.cipher.getOutputSize(r14)];
            int processBytes = this.cipher.processBytes(bArr, r13, r14, bArr2, 0);
            r14 = processBytes + this.cipher.doFinal(bArr2, processBytes);
            bArr3 = bArr7;
        }
        byte[] encodingV = this.param.getEncodingV();
        byte[] lengthTag = this.f1926V.length != 0 ? getLengthTag(encodingV) : null;
        int macSize = this.mac.getMacSize();
        byte[] bArr9 = new byte[macSize];
        SHA256Digest sHA256Digest = new SHA256Digest();
        byte[] bArr10 = new byte[sHA256Digest.getDigestSize()];
        sHA256Digest.reset();
        sHA256Digest.update(bArr3, 0, bArr3.length);
        sHA256Digest.doFinal(bArr10, 0);
        this.mac.init(new KeyParameter(bArr10));
        Mac mac = this.mac;
        byte[] bArr11 = this.f1925IV;
        mac.update(bArr11, 0, bArr11.length);
        this.mac.update(bArr2, 0, bArr2.length);
        if (encodingV != null) {
            this.mac.update(encodingV, 0, encodingV.length);
        }
        if (this.f1926V.length != 0) {
            this.mac.update(lengthTag, 0, lengthTag.length);
        }
        Mac mac2 = this.mac;
        byte[] bArr12 = this.commonMac;
        mac2.update(bArr12, 0, bArr12.length);
        this.mac.doFinal(bArr9, 0);
        byte[] bArr13 = this.f1926V;
        byte[] bArr14 = new byte[bArr13.length + r14 + macSize];
        System.arraycopy(bArr13, 0, bArr14, 0, bArr13.length);
        System.arraycopy(bArr2, 0, bArr14, this.f1926V.length, r14);
        System.arraycopy(bArr9, 0, bArr14, this.f1926V.length + r14, macSize);
        return bArr14;
    }

    private void extractParams(CipherParameters cipherParameters) {
        if (cipherParameters instanceof ParametersWithIV) {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            this.f1925IV = parametersWithIV.getIV();
            cipherParameters = parametersWithIV.getParameters();
        } else {
            this.f1925IV = null;
        }
        this.param = (IESParameters) cipherParameters;
    }

    public BufferedBlockCipher getCipher() {
        return this.cipher;
    }

    protected byte[] getLengthTag(byte[] bArr) {
        byte[] bArr2 = new byte[8];
        if (bArr != null) {
            Pack.longToBigEndian(bArr.length * 8, bArr2, 0);
        }
        return bArr2;
    }

    public Mac getMac() {
        return this.mac;
    }

    public void init(AsymmetricKeyParameter asymmetricKeyParameter, CipherParameters cipherParameters, KeyParser keyParser) {
        this.forEncryption = false;
        this.privParam = asymmetricKeyParameter;
        this.keyParser = keyParser;
        extractParams(cipherParameters);
    }

    public void init(AsymmetricKeyParameter asymmetricKeyParameter, CipherParameters cipherParameters, EphemeralKeyPairGenerator ephemeralKeyPairGenerator) {
        this.forEncryption = true;
        this.pubParam = asymmetricKeyParameter;
        this.keyPairGenerator = ephemeralKeyPairGenerator;
        extractParams(cipherParameters);
    }

    public void init(boolean z, CipherParameters cipherParameters, CipherParameters cipherParameters2, CipherParameters cipherParameters3) {
        this.forEncryption = z;
        this.privParam = cipherParameters;
        this.pubParam = cipherParameters2;
        this.f1926V = new byte[0];
        extractParams(cipherParameters3);
    }

    public byte[] processBlock(byte[] bArr, int r6, int r7) throws InvalidCipherTextException {
        if (this.forEncryption) {
            EphemeralKeyPairGenerator ephemeralKeyPairGenerator = this.keyPairGenerator;
            if (ephemeralKeyPairGenerator != null) {
                EphemeralKeyPair generate = ephemeralKeyPairGenerator.generate();
                this.privParam = generate.getKeyPair().getPrivate();
                this.f1926V = generate.getEncodedPublicKey();
            }
        } else if (this.keyParser != null) {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr, r6, r7);
            try {
                this.pubParam = this.keyParser.readKey(byteArrayInputStream);
                this.f1926V = Arrays.copyOfRange(bArr, r6, (r7 - byteArrayInputStream.available()) + r6);
            } catch (IOException e) {
                throw new InvalidCipherTextException("unable to recover ephemeral public key: " + e.getMessage(), e);
            } catch (IllegalArgumentException e2) {
                throw new InvalidCipherTextException("unable to recover ephemeral public key: " + e2.getMessage(), e2);
            }
        }
        this.agree.init(this.privParam);
        byte[] asUnsignedByteArray = BigIntegers.asUnsignedByteArray(this.agree.getFieldSize(), this.agree.calculateAgreement(this.pubParam));
        byte[] bArr2 = this.f1926V;
        if (bArr2.length != 0) {
            byte[] concatenate = Arrays.concatenate(bArr2, asUnsignedByteArray);
            Arrays.fill(asUnsignedByteArray, (byte) 0);
            asUnsignedByteArray = concatenate;
        }
        try {
            this.kdf.init(new KDFParameters(asUnsignedByteArray, this.param.getDerivationV()));
            return this.forEncryption ? encryptBlock(bArr, r6, r7) : decryptBlock(bArr, r6, r7);
        } finally {
            Arrays.fill(asUnsignedByteArray, (byte) 0);
        }
    }
}
