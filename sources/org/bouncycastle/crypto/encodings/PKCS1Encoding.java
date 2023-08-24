package org.bouncycastle.crypto.encodings;

import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Properties;

/* loaded from: classes5.dex */
public class PKCS1Encoding implements AsymmetricBlockCipher {
    private static final int HEADER_LENGTH = 10;
    public static final String NOT_STRICT_LENGTH_ENABLED_PROPERTY = "org.bouncycastle.pkcs1.not_strict";
    public static final String STRICT_LENGTH_ENABLED_PROPERTY = "org.bouncycastle.pkcs1.strict";
    private byte[] blockBuffer;
    private AsymmetricBlockCipher engine;
    private byte[] fallback;
    private boolean forEncryption;
    private boolean forPrivateKey;
    private int pLen;
    private SecureRandom random;
    private boolean useStrictLength;

    public PKCS1Encoding(AsymmetricBlockCipher asymmetricBlockCipher) {
        this.pLen = -1;
        this.fallback = null;
        this.engine = asymmetricBlockCipher;
        this.useStrictLength = useStrict();
    }

    public PKCS1Encoding(AsymmetricBlockCipher asymmetricBlockCipher, int r3) {
        this.pLen = -1;
        this.fallback = null;
        this.engine = asymmetricBlockCipher;
        this.useStrictLength = useStrict();
        this.pLen = r3;
    }

    public PKCS1Encoding(AsymmetricBlockCipher asymmetricBlockCipher, byte[] bArr) {
        this.pLen = -1;
        this.fallback = null;
        this.engine = asymmetricBlockCipher;
        this.useStrictLength = useStrict();
        this.fallback = bArr;
        this.pLen = bArr.length;
    }

    private static int checkPkcs1Encoding(byte[] bArr, int r7) {
        int r0 = 0 | (bArr[0] ^ 2);
        int r72 = r7 + 1;
        int length = bArr.length - r72;
        for (int r3 = 1; r3 < length; r3++) {
            byte b = bArr[r3];
            int r4 = b | (b >> 1);
            int r42 = r4 | (r4 >> 2);
            r0 |= ((r42 | (r42 >> 4)) & 1) - 1;
        }
        int r6 = bArr[bArr.length - r72] | r0;
        int r62 = r6 | (r6 >> 1);
        int r63 = r62 | (r62 >> 2);
        return ~(((r63 | (r63 >> 4)) & 1) - 1);
    }

    private byte[] decodeBlock(byte[] bArr, int r6, int r7) throws InvalidCipherTextException {
        if (this.pLen != -1) {
            return decodeBlockOrRandom(bArr, r6, r7);
        }
        byte[] processBlock = this.engine.processBlock(bArr, r6, r7);
        boolean z = this.useStrictLength & (processBlock.length != this.engine.getOutputBlockSize());
        if (processBlock.length < getOutputBlockSize()) {
            processBlock = this.blockBuffer;
        }
        byte b = processBlock[0];
        boolean z2 = !this.forPrivateKey ? b == 1 : b == 2;
        int findStart = findStart(b, processBlock) + 1;
        if (z2 || (findStart < 10)) {
            Arrays.fill(processBlock, (byte) 0);
            throw new InvalidCipherTextException("block incorrect");
        } else if (z) {
            Arrays.fill(processBlock, (byte) 0);
            throw new InvalidCipherTextException("block incorrect size");
        } else {
            int length = processBlock.length - findStart;
            byte[] bArr2 = new byte[length];
            System.arraycopy(processBlock, findStart, bArr2, 0, length);
            return bArr2;
        }
    }

    private byte[] decodeBlockOrRandom(byte[] bArr, int r7, int r8) throws InvalidCipherTextException {
        if (!this.forPrivateKey) {
            throw new InvalidCipherTextException("sorry, this method is only for decryption, not for signing");
        }
        byte[] processBlock = this.engine.processBlock(bArr, r7, r8);
        byte[] bArr2 = this.fallback;
        if (bArr2 == null) {
            bArr2 = new byte[this.pLen];
            this.random.nextBytes(bArr2);
        }
        if (this.useStrictLength & (processBlock.length != this.engine.getOutputBlockSize())) {
            processBlock = this.blockBuffer;
        }
        int checkPkcs1Encoding = checkPkcs1Encoding(processBlock, this.pLen);
        byte[] bArr3 = new byte[this.pLen];
        int r1 = 0;
        while (true) {
            int r3 = this.pLen;
            if (r1 >= r3) {
                Arrays.fill(processBlock, (byte) 0);
                return bArr3;
            }
            bArr3[r1] = (byte) ((processBlock[(processBlock.length - r3) + r1] & (~checkPkcs1Encoding)) | (bArr2[r1] & checkPkcs1Encoding));
            r1++;
        }
    }

    private byte[] encodeBlock(byte[] bArr, int r8, int r9) throws InvalidCipherTextException {
        if (r9 <= getInputBlockSize()) {
            int inputBlockSize = this.engine.getInputBlockSize();
            byte[] bArr2 = new byte[inputBlockSize];
            if (this.forPrivateKey) {
                bArr2[0] = 1;
                for (int r2 = 1; r2 != (inputBlockSize - r9) - 1; r2++) {
                    bArr2[r2] = -1;
                }
            } else {
                this.random.nextBytes(bArr2);
                bArr2[0] = 2;
                for (int r22 = 1; r22 != (inputBlockSize - r9) - 1; r22++) {
                    while (bArr2[r22] == 0) {
                        bArr2[r22] = (byte) this.random.nextInt();
                    }
                }
            }
            int r23 = inputBlockSize - r9;
            bArr2[r23 - 1] = 0;
            System.arraycopy(bArr, r8, bArr2, r23, r9);
            return this.engine.processBlock(bArr2, 0, inputBlockSize);
        }
        throw new IllegalArgumentException("input data too large");
    }

    private int findStart(byte b, byte[] bArr) throws InvalidCipherTextException {
        boolean z = false;
        int r5 = -1;
        for (int r3 = 1; r3 != bArr.length; r3++) {
            byte b2 = bArr[r3];
            if ((b2 == 0) & (r5 < 0)) {
                r5 = r3;
            }
            z |= (b2 != -1) & (b == 1) & (r5 < 0);
        }
        if (z) {
            return -1;
        }
        return r5;
    }

    private boolean useStrict() {
        if (Properties.isOverrideSetTo(NOT_STRICT_LENGTH_ENABLED_PROPERTY, true)) {
            return false;
        }
        return !Properties.isOverrideSetTo(STRICT_LENGTH_ENABLED_PROPERTY, false);
    }

    @Override // org.bouncycastle.crypto.AsymmetricBlockCipher
    public int getInputBlockSize() {
        int inputBlockSize = this.engine.getInputBlockSize();
        return this.forEncryption ? inputBlockSize - 10 : inputBlockSize;
    }

    @Override // org.bouncycastle.crypto.AsymmetricBlockCipher
    public int getOutputBlockSize() {
        int outputBlockSize = this.engine.getOutputBlockSize();
        return this.forEncryption ? outputBlockSize : outputBlockSize - 10;
    }

    public AsymmetricBlockCipher getUnderlyingCipher() {
        return this.engine;
    }

    @Override // org.bouncycastle.crypto.AsymmetricBlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        AsymmetricKeyParameter asymmetricKeyParameter;
        if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
            this.random = parametersWithRandom.getRandom();
            asymmetricKeyParameter = (AsymmetricKeyParameter) parametersWithRandom.getParameters();
        } else {
            asymmetricKeyParameter = (AsymmetricKeyParameter) cipherParameters;
            if (!asymmetricKeyParameter.isPrivate() && z) {
                this.random = CryptoServicesRegistrar.getSecureRandom();
            }
        }
        this.engine.init(z, cipherParameters);
        this.forPrivateKey = asymmetricKeyParameter.isPrivate();
        this.forEncryption = z;
        this.blockBuffer = new byte[this.engine.getOutputBlockSize()];
        if (this.pLen > 0 && this.fallback == null && this.random == null) {
            throw new IllegalArgumentException("encoder requires random");
        }
    }

    @Override // org.bouncycastle.crypto.AsymmetricBlockCipher
    public byte[] processBlock(byte[] bArr, int r3, int r4) throws InvalidCipherTextException {
        return this.forEncryption ? encodeBlock(bArr, r3, r4) : decodeBlock(bArr, r3, r4);
    }
}
