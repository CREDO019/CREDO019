package org.bouncycastle.crypto.modes;

import com.google.android.exoplayer2.extractor.p011ts.TsExtractor;
import java.util.Vector;
import okio.Utf8;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.AEADParameters;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Arrays;

/* loaded from: classes5.dex */
public class OCBBlockCipher implements AEADBlockCipher {
    private static final int BLOCK_SIZE = 16;
    private byte[] Checksum;

    /* renamed from: L */
    private Vector f2070L;
    private byte[] L_Asterisk;
    private byte[] L_Dollar;
    private byte[] OffsetHASH;
    private byte[] Sum;
    private boolean forEncryption;
    private byte[] hashBlock;
    private long hashBlockCount;
    private int hashBlockPos;
    private BlockCipher hashCipher;
    private byte[] initialAssociatedText;
    private byte[] macBlock;
    private int macSize;
    private byte[] mainBlock;
    private long mainBlockCount;
    private int mainBlockPos;
    private BlockCipher mainCipher;
    private byte[] KtopInput = null;
    private byte[] Stretch = new byte[24];
    private byte[] OffsetMAIN_0 = new byte[16];
    private byte[] OffsetMAIN = new byte[16];

    public OCBBlockCipher(BlockCipher blockCipher, BlockCipher blockCipher2) {
        if (blockCipher == null) {
            throw new IllegalArgumentException("'hashCipher' cannot be null");
        }
        if (blockCipher.getBlockSize() != 16) {
            throw new IllegalArgumentException("'hashCipher' must have a block size of 16");
        }
        if (blockCipher2 == null) {
            throw new IllegalArgumentException("'mainCipher' cannot be null");
        }
        if (blockCipher2.getBlockSize() != 16) {
            throw new IllegalArgumentException("'mainCipher' must have a block size of 16");
        }
        if (!blockCipher.getAlgorithmName().equals(blockCipher2.getAlgorithmName())) {
            throw new IllegalArgumentException("'hashCipher' and 'mainCipher' must be the same algorithm");
        }
        this.hashCipher = blockCipher;
        this.mainCipher = blockCipher2;
    }

    protected static byte[] OCB_double(byte[] bArr) {
        byte[] bArr2 = new byte[16];
        int shiftLeft = shiftLeft(bArr, bArr2);
        bArr2[15] = (byte) ((TsExtractor.TS_STREAM_TYPE_E_AC3 >>> ((1 - shiftLeft) << 3)) ^ bArr2[15]);
        return bArr2;
    }

    protected static void OCB_extend(byte[] bArr, int r2) {
        bArr[r2] = Byte.MIN_VALUE;
        while (true) {
            r2++;
            if (r2 >= 16) {
                return;
            }
            bArr[r2] = 0;
        }
    }

    protected static int OCB_ntz(long j) {
        if (j == 0) {
            return 64;
        }
        int r2 = 0;
        while ((1 & j) == 0) {
            r2++;
            j >>>= 1;
        }
        return r2;
    }

    protected static int shiftLeft(byte[] bArr, byte[] bArr2) {
        int r0 = 16;
        int r1 = 0;
        while (true) {
            r0--;
            if (r0 < 0) {
                return r1;
            }
            int r2 = bArr[r0] & 255;
            bArr2[r0] = (byte) (r1 | (r2 << 1));
            r1 = (r2 >>> 7) & 1;
        }
    }

    protected static void xor(byte[] bArr, byte[] bArr2) {
        for (int r0 = 15; r0 >= 0; r0--) {
            bArr[r0] = (byte) (bArr[r0] ^ bArr2[r0]);
        }
    }

    protected void clear(byte[] bArr) {
        if (bArr != null) {
            Arrays.fill(bArr, (byte) 0);
        }
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int doFinal(byte[] bArr, int r8) throws IllegalStateException, InvalidCipherTextException {
        byte[] bArr2;
        if (this.forEncryption) {
            bArr2 = null;
        } else {
            int r0 = this.mainBlockPos;
            int r2 = this.macSize;
            if (r0 < r2) {
                throw new InvalidCipherTextException("data too short");
            }
            int r02 = r0 - r2;
            this.mainBlockPos = r02;
            bArr2 = new byte[r2];
            System.arraycopy(this.mainBlock, r02, bArr2, 0, r2);
        }
        int r03 = this.hashBlockPos;
        if (r03 > 0) {
            OCB_extend(this.hashBlock, r03);
            updateHASH(this.L_Asterisk);
        }
        int r04 = this.mainBlockPos;
        if (r04 > 0) {
            if (this.forEncryption) {
                OCB_extend(this.mainBlock, r04);
                xor(this.Checksum, this.mainBlock);
            }
            xor(this.OffsetMAIN, this.L_Asterisk);
            byte[] bArr3 = new byte[16];
            this.hashCipher.processBlock(this.OffsetMAIN, 0, bArr3, 0);
            xor(this.mainBlock, bArr3);
            int length = bArr.length;
            int r4 = this.mainBlockPos;
            if (length < r8 + r4) {
                throw new OutputLengthException("Output buffer too short");
            }
            System.arraycopy(this.mainBlock, 0, bArr, r8, r4);
            if (!this.forEncryption) {
                OCB_extend(this.mainBlock, this.mainBlockPos);
                xor(this.Checksum, this.mainBlock);
            }
        }
        xor(this.Checksum, this.OffsetMAIN);
        xor(this.Checksum, this.L_Dollar);
        BlockCipher blockCipher = this.hashCipher;
        byte[] bArr4 = this.Checksum;
        blockCipher.processBlock(bArr4, 0, bArr4, 0);
        xor(this.Checksum, this.Sum);
        int r05 = this.macSize;
        byte[] bArr5 = new byte[r05];
        this.macBlock = bArr5;
        System.arraycopy(this.Checksum, 0, bArr5, 0, r05);
        int r06 = this.mainBlockPos;
        if (this.forEncryption) {
            int length2 = bArr.length;
            int r82 = r8 + r06;
            int r42 = this.macSize;
            if (length2 < r82 + r42) {
                throw new OutputLengthException("Output buffer too short");
            }
            System.arraycopy(this.macBlock, 0, bArr, r82, r42);
            r06 += this.macSize;
        } else if (!Arrays.constantTimeAreEqual(this.macBlock, bArr2)) {
            throw new InvalidCipherTextException("mac check in OCB failed");
        }
        reset(false);
        return r06;
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public String getAlgorithmName() {
        return this.mainCipher.getAlgorithmName() + "/OCB";
    }

    protected byte[] getLSub(int r3) {
        while (r3 >= this.f2070L.size()) {
            Vector vector = this.f2070L;
            vector.addElement(OCB_double((byte[]) vector.lastElement()));
        }
        return (byte[]) this.f2070L.elementAt(r3);
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public byte[] getMac() {
        byte[] bArr = this.macBlock;
        return bArr == null ? new byte[this.macSize] : Arrays.clone(bArr);
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int getOutputSize(int r2) {
        int r22 = r2 + this.mainBlockPos;
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
        return this.mainCipher;
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int getUpdateOutputSize(int r2) {
        int r22 = r2 + this.mainBlockPos;
        if (!this.forEncryption) {
            int r0 = this.macSize;
            if (r22 < r0) {
                return 0;
            }
            r22 -= r0;
        }
        return r22 - (r22 % 16);
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        byte[] bArr;
        KeyParameter keyParameter;
        boolean z2 = this.forEncryption;
        this.forEncryption = z;
        this.macBlock = null;
        if (cipherParameters instanceof AEADParameters) {
            AEADParameters aEADParameters = (AEADParameters) cipherParameters;
            bArr = aEADParameters.getNonce();
            this.initialAssociatedText = aEADParameters.getAssociatedText();
            int macSize = aEADParameters.getMacSize();
            if (macSize < 64 || macSize > 128 || macSize % 8 != 0) {
                throw new IllegalArgumentException("Invalid value for MAC size: " + macSize);
            }
            this.macSize = macSize / 8;
            keyParameter = aEADParameters.getKey();
        } else if (!(cipherParameters instanceof ParametersWithIV)) {
            throw new IllegalArgumentException("invalid parameters passed to OCB");
        } else {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            bArr = parametersWithIV.getIV();
            this.initialAssociatedText = null;
            this.macSize = 16;
            keyParameter = (KeyParameter) parametersWithIV.getParameters();
        }
        this.hashBlock = new byte[16];
        this.mainBlock = new byte[z ? 16 : this.macSize + 16];
        if (bArr == null) {
            bArr = new byte[0];
        }
        if (bArr.length > 15) {
            throw new IllegalArgumentException("IV must be no more than 15 bytes");
        }
        if (keyParameter != null) {
            this.hashCipher.init(true, keyParameter);
            this.mainCipher.init(z, keyParameter);
            this.KtopInput = null;
        } else if (z2 != z) {
            throw new IllegalArgumentException("cannot change encrypting state without providing key.");
        }
        byte[] bArr2 = new byte[16];
        this.L_Asterisk = bArr2;
        this.hashCipher.processBlock(bArr2, 0, bArr2, 0);
        this.L_Dollar = OCB_double(this.L_Asterisk);
        Vector vector = new Vector();
        this.f2070L = vector;
        vector.addElement(OCB_double(this.L_Dollar));
        int processNonce = processNonce(bArr);
        int r10 = processNonce % 8;
        int r9 = processNonce / 8;
        if (r10 == 0) {
            System.arraycopy(this.Stretch, r9, this.OffsetMAIN_0, 0, 16);
        } else {
            for (int r0 = 0; r0 < 16; r0++) {
                byte[] bArr3 = this.Stretch;
                r9++;
                this.OffsetMAIN_0[r0] = (byte) (((bArr3[r9] & 255) >>> (8 - r10)) | ((bArr3[r9] & 255) << r10));
            }
        }
        this.hashBlockPos = 0;
        this.mainBlockPos = 0;
        this.hashBlockCount = 0L;
        this.mainBlockCount = 0L;
        this.OffsetHASH = new byte[16];
        this.Sum = new byte[16];
        System.arraycopy(this.OffsetMAIN_0, 0, this.OffsetMAIN, 0, 16);
        this.Checksum = new byte[16];
        byte[] bArr4 = this.initialAssociatedText;
        if (bArr4 != null) {
            processAADBytes(bArr4, 0, bArr4.length);
        }
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public void processAADByte(byte b) {
        byte[] bArr = this.hashBlock;
        int r1 = this.hashBlockPos;
        bArr[r1] = b;
        int r12 = r1 + 1;
        this.hashBlockPos = r12;
        if (r12 == bArr.length) {
            processHashBlock();
        }
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public void processAADBytes(byte[] bArr, int r6, int r7) {
        for (int r0 = 0; r0 < r7; r0++) {
            byte[] bArr2 = this.hashBlock;
            int r2 = this.hashBlockPos;
            bArr2[r2] = bArr[r6 + r0];
            int r22 = r2 + 1;
            this.hashBlockPos = r22;
            if (r22 == bArr2.length) {
                processHashBlock();
            }
        }
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int processByte(byte b, byte[] bArr, int r5) throws DataLengthException {
        byte[] bArr2 = this.mainBlock;
        int r1 = this.mainBlockPos;
        bArr2[r1] = b;
        int r12 = r1 + 1;
        this.mainBlockPos = r12;
        if (r12 == bArr2.length) {
            processMainBlock(bArr, r5);
            return 16;
        }
        return 0;
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int processBytes(byte[] bArr, int r7, int r8, byte[] bArr2, int r10) throws DataLengthException {
        if (bArr.length >= r7 + r8) {
            int r1 = 0;
            for (int r0 = 0; r0 < r8; r0++) {
                byte[] bArr3 = this.mainBlock;
                int r3 = this.mainBlockPos;
                bArr3[r3] = bArr[r7 + r0];
                int r32 = r3 + 1;
                this.mainBlockPos = r32;
                if (r32 == bArr3.length) {
                    processMainBlock(bArr2, r10 + r1);
                    r1 += 16;
                }
            }
            return r1;
        }
        throw new DataLengthException("Input buffer too short");
    }

    protected void processHashBlock() {
        long j = this.hashBlockCount + 1;
        this.hashBlockCount = j;
        updateHASH(getLSub(OCB_ntz(j)));
        this.hashBlockPos = 0;
    }

    protected void processMainBlock(byte[] bArr, int r8) {
        if (bArr.length < r8 + 16) {
            throw new OutputLengthException("Output buffer too short");
        }
        if (this.forEncryption) {
            xor(this.Checksum, this.mainBlock);
            this.mainBlockPos = 0;
        }
        byte[] bArr2 = this.OffsetMAIN;
        long j = this.mainBlockCount + 1;
        this.mainBlockCount = j;
        xor(bArr2, getLSub(OCB_ntz(j)));
        xor(this.mainBlock, this.OffsetMAIN);
        BlockCipher blockCipher = this.mainCipher;
        byte[] bArr3 = this.mainBlock;
        blockCipher.processBlock(bArr3, 0, bArr3, 0);
        xor(this.mainBlock, this.OffsetMAIN);
        System.arraycopy(this.mainBlock, 0, bArr, r8, 16);
        if (this.forEncryption) {
            return;
        }
        xor(this.Checksum, this.mainBlock);
        byte[] bArr4 = this.mainBlock;
        System.arraycopy(bArr4, 16, bArr4, 0, this.macSize);
        this.mainBlockPos = this.macSize;
    }

    protected int processNonce(byte[] bArr) {
        byte[] bArr2 = new byte[16];
        int r4 = 0;
        System.arraycopy(bArr, 0, bArr2, 16 - bArr.length, bArr.length);
        bArr2[0] = (byte) (this.macSize << 4);
        int length = 15 - bArr.length;
        bArr2[length] = (byte) (bArr2[length] | 1);
        int r7 = bArr2[15] & Utf8.REPLACEMENT_BYTE;
        bArr2[15] = (byte) (bArr2[15] & 192);
        byte[] bArr3 = this.KtopInput;
        if (bArr3 == null || !Arrays.areEqual(bArr2, bArr3)) {
            byte[] bArr4 = new byte[16];
            this.KtopInput = bArr2;
            this.hashCipher.processBlock(bArr2, 0, bArr4, 0);
            System.arraycopy(bArr4, 0, this.Stretch, 0, 16);
            while (r4 < 8) {
                byte[] bArr5 = this.Stretch;
                int r1 = r4 + 16;
                byte b = bArr4[r4];
                r4++;
                bArr5[r1] = (byte) (b ^ bArr4[r4]);
            }
        }
        return r7;
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public void reset() {
        reset(true);
    }

    protected void reset(boolean z) {
        this.hashCipher.reset();
        this.mainCipher.reset();
        clear(this.hashBlock);
        clear(this.mainBlock);
        this.hashBlockPos = 0;
        this.mainBlockPos = 0;
        this.hashBlockCount = 0L;
        this.mainBlockCount = 0L;
        clear(this.OffsetHASH);
        clear(this.Sum);
        System.arraycopy(this.OffsetMAIN_0, 0, this.OffsetMAIN, 0, 16);
        clear(this.Checksum);
        if (z) {
            this.macBlock = null;
        }
        byte[] bArr = this.initialAssociatedText;
        if (bArr != null) {
            processAADBytes(bArr, 0, bArr.length);
        }
    }

    protected void updateHASH(byte[] bArr) {
        xor(this.OffsetHASH, bArr);
        xor(this.hashBlock, this.OffsetHASH);
        BlockCipher blockCipher = this.hashCipher;
        byte[] bArr2 = this.hashBlock;
        blockCipher.processBlock(bArr2, 0, bArr2, 0);
        xor(this.Sum, this.hashBlock);
    }
}
