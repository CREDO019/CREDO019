package org.bouncycastle.crypto.signers;

import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricBlockCipher;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.SignerWithRecovery;
import org.bouncycastle.util.Arrays;

/* loaded from: classes5.dex */
public class ISO9796d2PSSSigner implements SignerWithRecovery {
    public static final int TRAILER_IMPLICIT = 188;
    public static final int TRAILER_RIPEMD128 = 13004;
    public static final int TRAILER_RIPEMD160 = 12748;
    public static final int TRAILER_SHA1 = 13260;
    public static final int TRAILER_SHA256 = 13516;
    public static final int TRAILER_SHA384 = 14028;
    public static final int TRAILER_SHA512 = 13772;
    public static final int TRAILER_WHIRLPOOL = 14284;
    private byte[] block;
    private AsymmetricBlockCipher cipher;
    private Digest digest;
    private boolean fullMessage;
    private int hLen;
    private int keyBits;
    private byte[] mBuf;
    private int messageLength;
    private byte[] preBlock;
    private int preMStart;
    private byte[] preSig;
    private int preTLength;
    private SecureRandom random;
    private byte[] recoveredMessage;
    private int saltLength;
    private byte[] standardSalt;
    private int trailer;

    public ISO9796d2PSSSigner(AsymmetricBlockCipher asymmetricBlockCipher, Digest digest, int r4) {
        this(asymmetricBlockCipher, digest, r4, false);
    }

    public ISO9796d2PSSSigner(AsymmetricBlockCipher asymmetricBlockCipher, Digest digest, int r3, boolean z) {
        int intValue;
        this.cipher = asymmetricBlockCipher;
        this.digest = digest;
        this.hLen = digest.getDigestSize();
        this.saltLength = r3;
        if (z) {
            intValue = 188;
        } else {
            Integer trailer = ISOTrailers.getTrailer(digest);
            if (trailer == null) {
                throw new IllegalArgumentException("no valid trailer for digest: " + digest.getAlgorithmName());
            }
            intValue = trailer.intValue();
        }
        this.trailer = intValue;
    }

    private void ItoOSP(int r4, byte[] bArr) {
        bArr[0] = (byte) (r4 >>> 24);
        bArr[1] = (byte) (r4 >>> 16);
        bArr[2] = (byte) (r4 >>> 8);
        bArr[3] = (byte) (r4 >>> 0);
    }

    private void LtoOSP(long j, byte[] bArr) {
        bArr[0] = (byte) (j >>> 56);
        bArr[1] = (byte) (j >>> 48);
        bArr[2] = (byte) (j >>> 40);
        bArr[3] = (byte) (j >>> 32);
        bArr[4] = (byte) (j >>> 24);
        bArr[5] = (byte) (j >>> 16);
        bArr[6] = (byte) (j >>> 8);
        bArr[7] = (byte) (j >>> 0);
    }

    private void clearBlock(byte[] bArr) {
        for (int r1 = 0; r1 != bArr.length; r1++) {
            bArr[r1] = 0;
        }
    }

    private boolean isSameAs(byte[] bArr, byte[] bArr2) {
        boolean z = this.messageLength == bArr2.length;
        for (int r1 = 0; r1 != bArr2.length; r1++) {
            if (bArr[r1] != bArr2[r1]) {
                z = false;
            }
        }
        return z;
    }

    private byte[] maskGeneratorFunction1(byte[] bArr, int r10, int r11, int r12) {
        int r6;
        byte[] bArr2 = new byte[r12];
        byte[] bArr3 = new byte[this.hLen];
        byte[] bArr4 = new byte[4];
        this.digest.reset();
        int r5 = 0;
        while (true) {
            r6 = this.hLen;
            if (r5 >= r12 / r6) {
                break;
            }
            ItoOSP(r5, bArr4);
            this.digest.update(bArr, r10, r11);
            this.digest.update(bArr4, 0, 4);
            this.digest.doFinal(bArr3, 0);
            int r62 = this.hLen;
            System.arraycopy(bArr3, 0, bArr2, r5 * r62, r62);
            r5++;
        }
        if (r6 * r5 < r12) {
            ItoOSP(r5, bArr4);
            this.digest.update(bArr, r10, r11);
            this.digest.update(bArr4, 0, 4);
            this.digest.doFinal(bArr3, 0);
            int r9 = this.hLen;
            System.arraycopy(bArr3, 0, bArr2, r5 * r9, r12 - (r5 * r9));
        }
        return bArr2;
    }

    @Override // org.bouncycastle.crypto.Signer
    public byte[] generateSignature() throws CryptoException {
        int digestSize = this.digest.getDigestSize();
        byte[] bArr = new byte[digestSize];
        this.digest.doFinal(bArr, 0);
        byte[] bArr2 = new byte[8];
        LtoOSP(this.messageLength * 8, bArr2);
        this.digest.update(bArr2, 0, 8);
        this.digest.update(this.mBuf, 0, this.messageLength);
        this.digest.update(bArr, 0, digestSize);
        byte[] bArr3 = this.standardSalt;
        if (bArr3 == null) {
            bArr3 = new byte[this.saltLength];
            this.random.nextBytes(bArr3);
        }
        this.digest.update(bArr3, 0, bArr3.length);
        int digestSize2 = this.digest.getDigestSize();
        byte[] bArr4 = new byte[digestSize2];
        this.digest.doFinal(bArr4, 0);
        int r4 = this.trailer == 188 ? 1 : 2;
        byte[] bArr5 = this.block;
        int length = bArr5.length;
        int r10 = this.messageLength;
        int length2 = ((((length - r10) - bArr3.length) - this.hLen) - r4) - 1;
        bArr5[length2] = 1;
        int r9 = length2 + 1;
        System.arraycopy(this.mBuf, 0, bArr5, r9, r10);
        System.arraycopy(bArr3, 0, this.block, r9 + this.messageLength, bArr3.length);
        byte[] maskGeneratorFunction1 = maskGeneratorFunction1(bArr4, 0, digestSize2, (this.block.length - this.hLen) - r4);
        for (int r1 = 0; r1 != maskGeneratorFunction1.length; r1++) {
            byte[] bArr6 = this.block;
            bArr6[r1] = (byte) (bArr6[r1] ^ maskGeneratorFunction1[r1]);
        }
        byte[] bArr7 = this.block;
        int length3 = bArr7.length;
        int r8 = this.hLen;
        System.arraycopy(bArr4, 0, bArr7, (length3 - r8) - r4, r8);
        int r0 = this.trailer;
        if (r0 == 188) {
            byte[] bArr8 = this.block;
            bArr8[bArr8.length - 1] = PSSSigner.TRAILER_IMPLICIT;
        } else {
            byte[] bArr9 = this.block;
            bArr9[bArr9.length - 2] = (byte) (r0 >>> 8);
            bArr9[bArr9.length - 1] = (byte) r0;
        }
        byte[] bArr10 = this.block;
        bArr10[0] = (byte) (bArr10[0] & Byte.MAX_VALUE);
        byte[] processBlock = this.cipher.processBlock(bArr10, 0, bArr10.length);
        int r12 = this.messageLength;
        byte[] bArr11 = new byte[r12];
        this.recoveredMessage = bArr11;
        byte[] bArr12 = this.mBuf;
        this.fullMessage = r12 <= bArr12.length;
        System.arraycopy(bArr12, 0, bArr11, 0, bArr11.length);
        clearBlock(this.mBuf);
        clearBlock(this.block);
        this.messageLength = 0;
        return processBlock;
    }

    @Override // org.bouncycastle.crypto.SignerWithRecovery
    public byte[] getRecoveredMessage() {
        return this.recoveredMessage;
    }

    @Override // org.bouncycastle.crypto.SignerWithRecovery
    public boolean hasFullMessage() {
        return this.fullMessage;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0072  */
    @Override // org.bouncycastle.crypto.Signer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void init(boolean r4, org.bouncycastle.crypto.CipherParameters r5) {
        /*
            r3 = this;
            int r0 = r3.saltLength
            boolean r1 = r5 instanceof org.bouncycastle.crypto.params.ParametersWithRandom
            if (r1 == 0) goto L17
            org.bouncycastle.crypto.params.ParametersWithRandom r5 = (org.bouncycastle.crypto.params.ParametersWithRandom) r5
            org.bouncycastle.crypto.CipherParameters r1 = r5.getParameters()
            org.bouncycastle.crypto.params.RSAKeyParameters r1 = (org.bouncycastle.crypto.params.RSAKeyParameters) r1
            if (r4 == 0) goto L43
            java.security.SecureRandom r5 = r5.getRandom()
        L14:
            r3.random = r5
            goto L43
        L17:
            boolean r1 = r5 instanceof org.bouncycastle.crypto.params.ParametersWithSalt
            if (r1 == 0) goto L39
            org.bouncycastle.crypto.params.ParametersWithSalt r5 = (org.bouncycastle.crypto.params.ParametersWithSalt) r5
            org.bouncycastle.crypto.CipherParameters r0 = r5.getParameters()
            r1 = r0
            org.bouncycastle.crypto.params.RSAKeyParameters r1 = (org.bouncycastle.crypto.params.RSAKeyParameters) r1
            byte[] r5 = r5.getSalt()
            r3.standardSalt = r5
            int r0 = r5.length
            int r5 = r5.length
            int r2 = r3.saltLength
            if (r5 != r2) goto L31
            goto L43
        L31:
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException
            java.lang.String r5 = "Fixed salt is of wrong length"
            r4.<init>(r5)
            throw r4
        L39:
            r1 = r5
            org.bouncycastle.crypto.params.RSAKeyParameters r1 = (org.bouncycastle.crypto.params.RSAKeyParameters) r1
            if (r4 == 0) goto L43
            java.security.SecureRandom r5 = org.bouncycastle.crypto.CryptoServicesRegistrar.getSecureRandom()
            goto L14
        L43:
            org.bouncycastle.crypto.AsymmetricBlockCipher r5 = r3.cipher
            r5.init(r4, r1)
            java.math.BigInteger r4 = r1.getModulus()
            int r4 = r4.bitLength()
            r3.keyBits = r4
            int r4 = r4 + 7
            int r4 = r4 / 8
            byte[] r4 = new byte[r4]
            r3.block = r4
            int r5 = r3.trailer
            r1 = 188(0xbc, float:2.63E-43)
            int r4 = r4.length
            if (r5 != r1) goto L72
            org.bouncycastle.crypto.Digest r5 = r3.digest
            int r5 = r5.getDigestSize()
            int r4 = r4 - r5
            int r4 = r4 - r0
            int r4 = r4 + (-1)
            int r4 = r4 + (-1)
            byte[] r4 = new byte[r4]
            r3.mBuf = r4
            goto L82
        L72:
            org.bouncycastle.crypto.Digest r5 = r3.digest
            int r5 = r5.getDigestSize()
            int r4 = r4 - r5
            int r4 = r4 - r0
            int r4 = r4 + (-1)
            int r4 = r4 + (-2)
            byte[] r4 = new byte[r4]
            r3.mBuf = r4
        L82:
            r3.reset()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.crypto.signers.ISO9796d2PSSSigner.init(boolean, org.bouncycastle.crypto.CipherParameters):void");
    }

    @Override // org.bouncycastle.crypto.Signer
    public void reset() {
        this.digest.reset();
        this.messageLength = 0;
        byte[] bArr = this.mBuf;
        if (bArr != null) {
            clearBlock(bArr);
        }
        byte[] bArr2 = this.recoveredMessage;
        if (bArr2 != null) {
            clearBlock(bArr2);
            this.recoveredMessage = null;
        }
        this.fullMessage = false;
        if (this.preSig != null) {
            this.preSig = null;
            clearBlock(this.preBlock);
            this.preBlock = null;
        }
    }

    @Override // org.bouncycastle.crypto.Signer
    public void update(byte b) {
        if (this.preSig == null) {
            int r0 = this.messageLength;
            byte[] bArr = this.mBuf;
            if (r0 < bArr.length) {
                this.messageLength = r0 + 1;
                bArr[r0] = b;
                return;
            }
        }
        this.digest.update(b);
    }

    @Override // org.bouncycastle.crypto.Signer
    public void update(byte[] bArr, int r4, int r5) {
        if (this.preSig == null) {
            while (r5 > 0 && this.messageLength < this.mBuf.length) {
                update(bArr[r4]);
                r4++;
                r5--;
            }
        }
        if (r5 > 0) {
            this.digest.update(bArr, r4, r5);
        }
    }

    @Override // org.bouncycastle.crypto.SignerWithRecovery
    public void updateWithRecoveredMessage(byte[] bArr) throws InvalidCipherTextException {
        byte[] processBlock = this.cipher.processBlock(bArr, 0, bArr.length);
        int length = processBlock.length;
        int r3 = this.keyBits;
        if (length < (r3 + 7) / 8) {
            int r32 = (r3 + 7) / 8;
            byte[] bArr2 = new byte[r32];
            System.arraycopy(processBlock, 0, bArr2, r32 - processBlock.length, processBlock.length);
            clearBlock(processBlock);
            processBlock = bArr2;
        }
        int r4 = 2;
        if (((processBlock[processBlock.length - 1] & 255) ^ 188) == 0) {
            r4 = 1;
        } else {
            int r1 = ((processBlock[processBlock.length - 2] & 255) << 8) | (processBlock[processBlock.length - 1] & 255);
            Integer trailer = ISOTrailers.getTrailer(this.digest);
            if (trailer == null) {
                throw new IllegalArgumentException("unrecognised hash in signature");
            }
            int intValue = trailer.intValue();
            if (r1 != intValue && (intValue != 15052 || r1 != 16588)) {
                throw new IllegalStateException("signer initialised with wrong digest for trailer " + r1);
            }
        }
        this.digest.doFinal(new byte[this.hLen], 0);
        int length2 = processBlock.length;
        int r5 = this.hLen;
        byte[] maskGeneratorFunction1 = maskGeneratorFunction1(processBlock, (length2 - r5) - r4, r5, (processBlock.length - r5) - r4);
        for (int r52 = 0; r52 != maskGeneratorFunction1.length; r52++) {
            processBlock[r52] = (byte) (processBlock[r52] ^ maskGeneratorFunction1[r52]);
        }
        processBlock[0] = (byte) (processBlock[0] & Byte.MAX_VALUE);
        int r53 = 0;
        while (r53 != processBlock.length && processBlock[r53] != 1) {
            r53++;
        }
        int r54 = r53 + 1;
        if (r54 >= processBlock.length) {
            clearBlock(processBlock);
        }
        this.fullMessage = r54 > 1;
        byte[] bArr3 = new byte[(maskGeneratorFunction1.length - r54) - this.saltLength];
        this.recoveredMessage = bArr3;
        System.arraycopy(processBlock, r54, bArr3, 0, bArr3.length);
        byte[] bArr4 = this.recoveredMessage;
        System.arraycopy(bArr4, 0, this.mBuf, 0, bArr4.length);
        this.preSig = bArr;
        this.preBlock = processBlock;
        this.preMStart = r54;
        this.preTLength = r4;
    }

    @Override // org.bouncycastle.crypto.Signer
    public boolean verifySignature(byte[] bArr) {
        int r0 = this.hLen;
        byte[] bArr2 = new byte[r0];
        this.digest.doFinal(bArr2, 0);
        byte[] bArr3 = this.preSig;
        if (bArr3 == null) {
            try {
                updateWithRecoveredMessage(bArr);
            } catch (Exception unused) {
                return false;
            }
        } else if (!Arrays.areEqual(bArr3, bArr)) {
            throw new IllegalStateException("updateWithRecoveredMessage called on different signature");
        }
        byte[] bArr4 = this.preBlock;
        int r2 = this.preMStart;
        int r4 = this.preTLength;
        this.preSig = null;
        this.preBlock = null;
        byte[] bArr5 = new byte[8];
        LtoOSP(this.recoveredMessage.length * 8, bArr5);
        this.digest.update(bArr5, 0, 8);
        byte[] bArr6 = this.recoveredMessage;
        if (bArr6.length != 0) {
            this.digest.update(bArr6, 0, bArr6.length);
        }
        this.digest.update(bArr2, 0, r0);
        byte[] bArr7 = this.standardSalt;
        if (bArr7 != null) {
            this.digest.update(bArr7, 0, bArr7.length);
        } else {
            this.digest.update(bArr4, r2 + this.recoveredMessage.length, this.saltLength);
        }
        int digestSize = this.digest.getDigestSize();
        byte[] bArr8 = new byte[digestSize];
        this.digest.doFinal(bArr8, 0);
        int length = (bArr4.length - r4) - digestSize;
        boolean z = true;
        for (int r5 = 0; r5 != digestSize; r5++) {
            if (bArr8[r5] != bArr4[length + r5]) {
                z = false;
            }
        }
        clearBlock(bArr4);
        clearBlock(bArr8);
        if (!z) {
            this.fullMessage = false;
            this.messageLength = 0;
            clearBlock(this.recoveredMessage);
            return false;
        } else if (this.messageLength == 0 || isSameAs(this.mBuf, this.recoveredMessage)) {
            this.messageLength = 0;
            clearBlock(this.mBuf);
            return true;
        } else {
            this.messageLength = 0;
            clearBlock(this.mBuf);
            return false;
        }
    }
}
