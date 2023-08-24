package org.bouncycastle.crypto.modes;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.macs.CMac;
import org.bouncycastle.crypto.params.AEADParameters;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Arrays;

/* loaded from: classes5.dex */
public class EAXBlockCipher implements AEADBlockCipher {
    private static final byte cTAG = 2;
    private static final byte hTAG = 1;
    private static final byte nTAG = 0;
    private byte[] associatedTextMac;
    private int blockSize;
    private byte[] bufBlock;
    private int bufOff;
    private SICBlockCipher cipher;
    private boolean cipherInitialized;
    private boolean forEncryption;
    private byte[] initialAssociatedText;
    private Mac mac;
    private byte[] macBlock;
    private int macSize;
    private byte[] nonceMac;

    public EAXBlockCipher(BlockCipher blockCipher) {
        this.blockSize = blockCipher.getBlockSize();
        CMac cMac = new CMac(blockCipher);
        this.mac = cMac;
        this.macBlock = new byte[this.blockSize];
        this.associatedTextMac = new byte[cMac.getMacSize()];
        this.nonceMac = new byte[this.mac.getMacSize()];
        this.cipher = new SICBlockCipher(blockCipher);
    }

    private void calculateMac() {
        byte[] bArr = new byte[this.blockSize];
        int r2 = 0;
        this.mac.doFinal(bArr, 0);
        while (true) {
            byte[] bArr2 = this.macBlock;
            if (r2 >= bArr2.length) {
                return;
            }
            bArr2[r2] = (byte) ((this.nonceMac[r2] ^ this.associatedTextMac[r2]) ^ bArr[r2]);
            r2++;
        }
    }

    private void initCipher() {
        if (this.cipherInitialized) {
            return;
        }
        this.cipherInitialized = true;
        this.mac.doFinal(this.associatedTextMac, 0);
        int r0 = this.blockSize;
        byte[] bArr = new byte[r0];
        bArr[r0 - 1] = 2;
        this.mac.update(bArr, 0, r0);
    }

    private int process(byte b, byte[] bArr, int r7) {
        int processBlock;
        byte[] bArr2 = this.bufBlock;
        int r1 = this.bufOff;
        int r2 = r1 + 1;
        this.bufOff = r2;
        bArr2[r1] = b;
        if (r2 == bArr2.length) {
            int length = bArr.length;
            int r22 = this.blockSize;
            if (length >= r7 + r22) {
                if (this.forEncryption) {
                    processBlock = this.cipher.processBlock(bArr2, 0, bArr, r7);
                    this.mac.update(bArr, r7, this.blockSize);
                } else {
                    this.mac.update(bArr2, 0, r22);
                    processBlock = this.cipher.processBlock(this.bufBlock, 0, bArr, r7);
                }
                this.bufOff = 0;
                if (!this.forEncryption) {
                    byte[] bArr3 = this.bufBlock;
                    System.arraycopy(bArr3, this.blockSize, bArr3, 0, this.macSize);
                    this.bufOff = this.macSize;
                }
                return processBlock;
            }
            throw new OutputLengthException("Output buffer is too short");
        }
        return 0;
    }

    private void reset(boolean z) {
        this.cipher.reset();
        this.mac.reset();
        this.bufOff = 0;
        Arrays.fill(this.bufBlock, (byte) 0);
        if (z) {
            Arrays.fill(this.macBlock, (byte) 0);
        }
        int r5 = this.blockSize;
        byte[] bArr = new byte[r5];
        bArr[r5 - 1] = 1;
        this.mac.update(bArr, 0, r5);
        this.cipherInitialized = false;
        byte[] bArr2 = this.initialAssociatedText;
        if (bArr2 != null) {
            processAADBytes(bArr2, 0, bArr2.length);
        }
    }

    private boolean verifyMac(byte[] bArr, int r7) {
        int r2 = 0;
        for (int r1 = 0; r1 < this.macSize; r1++) {
            r2 |= this.macBlock[r1] ^ bArr[r7 + r1];
        }
        return r2 == 0;
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int doFinal(byte[] bArr, int r10) throws IllegalStateException, InvalidCipherTextException {
        initCipher();
        int r0 = this.bufOff;
        byte[] bArr2 = this.bufBlock;
        byte[] bArr3 = new byte[bArr2.length];
        this.bufOff = 0;
        if (this.forEncryption) {
            int r6 = r10 + r0;
            if (bArr.length >= this.macSize + r6) {
                this.cipher.processBlock(bArr2, 0, bArr3, 0);
                System.arraycopy(bArr3, 0, bArr, r10, r0);
                this.mac.update(bArr3, 0, r0);
                calculateMac();
                System.arraycopy(this.macBlock, 0, bArr, r6, this.macSize);
                reset(false);
                return r0 + this.macSize;
            }
            throw new OutputLengthException("Output buffer too short");
        }
        int r4 = this.macSize;
        if (r0 >= r4) {
            if (bArr.length >= (r10 + r0) - r4) {
                if (r0 > r4) {
                    this.mac.update(bArr2, 0, r0 - r4);
                    this.cipher.processBlock(this.bufBlock, 0, bArr3, 0);
                    System.arraycopy(bArr3, 0, bArr, r10, r0 - this.macSize);
                }
                calculateMac();
                if (verifyMac(this.bufBlock, r0 - this.macSize)) {
                    reset(false);
                    return r0 - this.macSize;
                }
                throw new InvalidCipherTextException("mac check in EAX failed");
            }
            throw new OutputLengthException("Output buffer too short");
        }
        throw new InvalidCipherTextException("data too short");
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public String getAlgorithmName() {
        return this.cipher.getUnderlyingCipher().getAlgorithmName() + "/EAX";
    }

    public int getBlockSize() {
        return this.cipher.getBlockSize();
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public byte[] getMac() {
        int r0 = this.macSize;
        byte[] bArr = new byte[r0];
        System.arraycopy(this.macBlock, 0, bArr, 0, r0);
        return bArr;
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int getOutputSize(int r2) {
        int r22 = r2 + this.bufOff;
        if (this.forEncryption) {
            return r22 + this.macSize;
        }
        int r0 = this.macSize;
        if (r22 < r0) {
            return 0;
        }
        return r22 - r0;
    }

    @Override // org.bouncycastle.crypto.modes.AEADBlockCipher
    public BlockCipher getUnderlyingCipher() {
        return this.cipher.getUnderlyingCipher();
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int getUpdateOutputSize(int r2) {
        int r22 = r2 + this.bufOff;
        if (!this.forEncryption) {
            int r0 = this.macSize;
            if (r22 < r0) {
                return 0;
            }
            r22 -= r0;
        }
        return r22 - (r22 % this.blockSize);
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        byte[] bArr;
        CipherParameters parameters;
        this.forEncryption = z;
        if (cipherParameters instanceof AEADParameters) {
            AEADParameters aEADParameters = (AEADParameters) cipherParameters;
            bArr = aEADParameters.getNonce();
            this.initialAssociatedText = aEADParameters.getAssociatedText();
            this.macSize = aEADParameters.getMacSize() / 8;
            parameters = aEADParameters.getKey();
        } else if (!(cipherParameters instanceof ParametersWithIV)) {
            throw new IllegalArgumentException("invalid parameters passed to EAX");
        } else {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            bArr = parametersWithIV.getIV();
            this.initialAssociatedText = null;
            this.macSize = this.mac.getMacSize() / 2;
            parameters = parametersWithIV.getParameters();
        }
        this.bufBlock = new byte[z ? this.blockSize : this.blockSize + this.macSize];
        byte[] bArr2 = new byte[this.blockSize];
        this.mac.init(parameters);
        int r6 = this.blockSize;
        bArr2[r6 - 1] = 0;
        this.mac.update(bArr2, 0, r6);
        this.mac.update(bArr, 0, bArr.length);
        this.mac.doFinal(this.nonceMac, 0);
        this.cipher.init(true, new ParametersWithIV(null, this.nonceMac));
        reset();
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public void processAADByte(byte b) {
        if (this.cipherInitialized) {
            throw new IllegalStateException("AAD data cannot be added after encryption/decryption processing has begun.");
        }
        this.mac.update(b);
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public void processAADBytes(byte[] bArr, int r3, int r4) {
        if (this.cipherInitialized) {
            throw new IllegalStateException("AAD data cannot be added after encryption/decryption processing has begun.");
        }
        this.mac.update(bArr, r3, r4);
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int processByte(byte b, byte[] bArr, int r3) throws DataLengthException {
        initCipher();
        return process(b, bArr, r3);
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int processBytes(byte[] bArr, int r6, int r7, byte[] bArr2, int r9) throws DataLengthException {
        initCipher();
        if (bArr.length >= r6 + r7) {
            int r1 = 0;
            for (int r0 = 0; r0 != r7; r0++) {
                r1 += process(bArr[r6 + r0], bArr2, r9 + r1);
            }
            return r1;
        }
        throw new DataLengthException("Input buffer too short");
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public void reset() {
        reset(true);
    }
}
