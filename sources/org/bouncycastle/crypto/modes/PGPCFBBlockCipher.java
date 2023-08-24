package org.bouncycastle.crypto.modes;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.ParametersWithIV;

/* loaded from: classes5.dex */
public class PGPCFBBlockCipher implements BlockCipher {

    /* renamed from: FR */
    private byte[] f2074FR;
    private byte[] FRE;

    /* renamed from: IV */
    private byte[] f2075IV;
    private int blockSize;
    private BlockCipher cipher;
    private int count;
    private boolean forEncryption;
    private boolean inlineIv;
    private byte[] tmp;

    public PGPCFBBlockCipher(BlockCipher blockCipher, boolean z) {
        this.cipher = blockCipher;
        this.inlineIv = z;
        int blockSize = blockCipher.getBlockSize();
        this.blockSize = blockSize;
        this.f2075IV = new byte[blockSize];
        this.f2074FR = new byte[blockSize];
        this.FRE = new byte[blockSize];
        this.tmp = new byte[blockSize];
    }

    private int decryptBlock(byte[] bArr, int r6, byte[] bArr2, int r8) throws DataLengthException, IllegalStateException {
        int r0 = this.blockSize;
        if (r6 + r0 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (r0 + r8 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        int r3 = 0;
        this.cipher.processBlock(this.f2074FR, 0, this.FRE, 0);
        for (int r02 = 0; r02 < this.blockSize; r02++) {
            bArr2[r8 + r02] = encryptByte(bArr[r6 + r02], r02);
        }
        while (true) {
            int r7 = this.blockSize;
            if (r3 >= r7) {
                return r7;
            }
            this.f2074FR[r3] = bArr[r6 + r3];
            r3++;
        }
    }

    private int decryptBlockWithIV(byte[] bArr, int r8, byte[] bArr2, int r10) throws DataLengthException, IllegalStateException {
        int r82;
        int r0 = this.blockSize;
        if (r8 + r0 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (r10 + r0 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        int r1 = this.count;
        if (r1 == 0) {
            for (int r9 = 0; r9 < this.blockSize; r9++) {
                this.f2074FR[r9] = bArr[r8 + r9];
            }
            this.cipher.processBlock(this.f2074FR, 0, this.FRE, 0);
            this.count += this.blockSize;
            return 0;
        } else if (r1 != r0) {
            if (r1 >= r0 + 2) {
                System.arraycopy(bArr, r8, this.tmp, 0, r0);
                bArr2[r10 + 0] = encryptByte(this.tmp[0], this.blockSize - 2);
                bArr2[r10 + 1] = encryptByte(this.tmp[1], this.blockSize - 1);
                System.arraycopy(this.tmp, 0, this.f2074FR, this.blockSize - 2, 2);
                this.cipher.processBlock(this.f2074FR, 0, this.FRE, 0);
                int r7 = 0;
                while (true) {
                    r82 = this.blockSize;
                    if (r7 >= r82 - 2) {
                        break;
                    }
                    bArr2[r10 + r7 + 2] = encryptByte(this.tmp[r7 + 2], r7);
                    r7++;
                }
                System.arraycopy(this.tmp, 2, this.f2074FR, 0, r82 - 2);
            }
            return this.blockSize;
        } else {
            System.arraycopy(bArr, r8, this.tmp, 0, r0);
            byte[] bArr3 = this.f2074FR;
            System.arraycopy(bArr3, 2, bArr3, 0, this.blockSize - 2);
            byte[] bArr4 = this.f2074FR;
            int r83 = this.blockSize;
            byte[] bArr5 = this.tmp;
            bArr4[r83 - 2] = bArr5[0];
            bArr4[r83 - 1] = bArr5[1];
            this.cipher.processBlock(bArr4, 0, this.FRE, 0);
            int r72 = 0;
            while (true) {
                int r84 = this.blockSize;
                if (r72 >= r84 - 2) {
                    System.arraycopy(this.tmp, 2, this.f2074FR, 0, r84 - 2);
                    this.count += 2;
                    return this.blockSize - 2;
                }
                bArr2[r10 + r72] = encryptByte(this.tmp[r72 + 2], r72);
                r72++;
            }
        }
    }

    private int encryptBlock(byte[] bArr, int r6, byte[] bArr2, int r8) throws DataLengthException, IllegalStateException {
        int r0 = this.blockSize;
        if (r6 + r0 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (r0 + r8 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        int r3 = 0;
        this.cipher.processBlock(this.f2074FR, 0, this.FRE, 0);
        for (int r02 = 0; r02 < this.blockSize; r02++) {
            bArr2[r8 + r02] = encryptByte(bArr[r6 + r02], r02);
        }
        while (true) {
            int r5 = this.blockSize;
            if (r3 >= r5) {
                return r5;
            }
            this.f2074FR[r3] = bArr2[r8 + r3];
            r3++;
        }
    }

    private int encryptBlockWithIV(byte[] bArr, int r7, byte[] bArr2, int r9) throws DataLengthException, IllegalStateException {
        int r1;
        int r12;
        int r0 = this.blockSize;
        if (r7 + r0 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        int r13 = this.count;
        if (r13 != 0) {
            if (r13 >= r0 + 2) {
                if (r0 + r9 > bArr2.length) {
                    throw new OutputLengthException("output buffer too short");
                }
                this.cipher.processBlock(this.f2074FR, 0, this.FRE, 0);
                int r02 = 0;
                while (true) {
                    r1 = this.blockSize;
                    if (r02 >= r1) {
                        break;
                    }
                    bArr2[r9 + r02] = encryptByte(bArr[r7 + r02], r02);
                    r02++;
                }
                System.arraycopy(bArr2, r9, this.f2074FR, 0, r1);
            }
            return this.blockSize;
        } else if ((r0 * 2) + r9 + 2 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        } else {
            this.cipher.processBlock(this.f2074FR, 0, this.FRE, 0);
            int r03 = 0;
            while (true) {
                r12 = this.blockSize;
                if (r03 >= r12) {
                    break;
                }
                bArr2[r9 + r03] = encryptByte(this.f2075IV[r03], r03);
                r03++;
            }
            System.arraycopy(bArr2, r9, this.f2074FR, 0, r12);
            this.cipher.processBlock(this.f2074FR, 0, this.FRE, 0);
            int r04 = this.blockSize;
            bArr2[r9 + r04] = encryptByte(this.f2075IV[r04 - 2], 0);
            int r05 = this.blockSize;
            bArr2[r9 + r05 + 1] = encryptByte(this.f2075IV[r05 - 1], 1);
            System.arraycopy(bArr2, r9 + 2, this.f2074FR, 0, this.blockSize);
            this.cipher.processBlock(this.f2074FR, 0, this.FRE, 0);
            int r06 = 0;
            while (true) {
                int r14 = this.blockSize;
                if (r06 >= r14) {
                    System.arraycopy(bArr2, r9 + r14 + 2, this.f2074FR, 0, r14);
                    int r6 = this.count;
                    int r72 = this.blockSize;
                    this.count = r6 + (r72 * 2) + 2;
                    return (r72 * 2) + 2;
                }
                bArr2[r14 + r9 + 2 + r06] = encryptByte(bArr[r7 + r06], r06);
                r06++;
            }
        }
    }

    private byte encryptByte(byte b, int r3) {
        return (byte) (b ^ this.FRE[r3]);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        StringBuilder sb;
        String str;
        if (this.inlineIv) {
            sb = new StringBuilder();
            sb.append(this.cipher.getAlgorithmName());
            str = "/PGPCFBwithIV";
        } else {
            sb = new StringBuilder();
            sb.append(this.cipher.getAlgorithmName());
            str = "/PGPCFB";
        }
        sb.append(str);
        return sb.toString();
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return this.cipher.getBlockSize();
    }

    public BlockCipher getUnderlyingCipher() {
        return this.cipher;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        BlockCipher blockCipher;
        this.forEncryption = z;
        if (cipherParameters instanceof ParametersWithIV) {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            byte[] bArr = parametersWithIV.getIV();
            int length = bArr.length;
            byte[] bArr2 = this.f2075IV;
            if (length < bArr2.length) {
                System.arraycopy(bArr, 0, bArr2, bArr2.length - bArr.length, bArr.length);
                int r1 = 0;
                while (true) {
                    byte[] bArr3 = this.f2075IV;
                    if (r1 >= bArr3.length - bArr.length) {
                        break;
                    }
                    bArr3[r1] = 0;
                    r1++;
                }
            } else {
                System.arraycopy(bArr, 0, bArr2, 0, bArr2.length);
            }
            reset();
            blockCipher = this.cipher;
            cipherParameters = parametersWithIV.getParameters();
        } else {
            reset();
            blockCipher = this.cipher;
        }
        blockCipher.init(true, cipherParameters);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int r3, byte[] bArr2, int r5) throws DataLengthException, IllegalStateException {
        return this.inlineIv ? this.forEncryption ? encryptBlockWithIV(bArr, r3, bArr2, r5) : decryptBlockWithIV(bArr, r3, bArr2, r5) : this.forEncryption ? encryptBlock(bArr, r3, bArr2, r5) : decryptBlock(bArr, r3, bArr2, r5);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
        this.count = 0;
        int r1 = 0;
        while (true) {
            byte[] bArr = this.f2074FR;
            if (r1 == bArr.length) {
                this.cipher.reset();
                return;
            }
            if (this.inlineIv) {
                bArr[r1] = 0;
            } else {
                bArr[r1] = this.f2075IV[r1];
            }
            r1++;
        }
    }
}
