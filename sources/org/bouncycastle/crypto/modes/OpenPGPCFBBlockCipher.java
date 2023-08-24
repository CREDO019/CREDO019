package org.bouncycastle.crypto.modes;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;

/* loaded from: classes5.dex */
public class OpenPGPCFBBlockCipher implements BlockCipher {

    /* renamed from: FR */
    private byte[] f2072FR;
    private byte[] FRE;

    /* renamed from: IV */
    private byte[] f2073IV;
    private int blockSize;
    private BlockCipher cipher;
    private int count;
    private boolean forEncryption;

    public OpenPGPCFBBlockCipher(BlockCipher blockCipher) {
        this.cipher = blockCipher;
        int blockSize = blockCipher.getBlockSize();
        this.blockSize = blockSize;
        this.f2073IV = new byte[blockSize];
        this.f2072FR = new byte[blockSize];
        this.FRE = new byte[blockSize];
    }

    private int decryptBlock(byte[] bArr, int r10, byte[] bArr2, int r12) throws DataLengthException, IllegalStateException {
        int r0;
        int r9;
        int r122;
        int r02 = this.blockSize;
        if (r10 + r02 <= bArr.length) {
            if (r12 + r02 <= bArr2.length) {
                int r1 = this.count;
                int r2 = 2;
                int r3 = 0;
                if (r1 > r02) {
                    byte b = bArr[r10];
                    this.f2072FR[r02 - 2] = b;
                    bArr2[r12] = encryptByte(b, r02 - 2);
                    byte b2 = bArr[r10 + 1];
                    byte[] bArr3 = this.f2072FR;
                    int r5 = this.blockSize;
                    bArr3[r5 - 1] = b2;
                    bArr2[r12 + 1] = encryptByte(b2, r5 - 1);
                    this.cipher.processBlock(this.f2072FR, 0, this.FRE, 0);
                    while (r2 < this.blockSize) {
                        byte b3 = bArr[r10 + r2];
                        int r32 = r2 - 2;
                        this.f2072FR[r32] = b3;
                        bArr2[r12 + r2] = encryptByte(b3, r32);
                        r2++;
                    }
                } else {
                    if (r1 == 0) {
                        this.cipher.processBlock(this.f2072FR, 0, this.FRE, 0);
                        while (true) {
                            r122 = this.blockSize;
                            if (r3 >= r122) {
                                break;
                            }
                            int r03 = r10 + r3;
                            this.f2072FR[r3] = bArr[r03];
                            bArr2[r3] = encryptByte(bArr[r03], r3);
                            r3++;
                        }
                        r9 = this.count + r122;
                    } else if (r1 == r02) {
                        this.cipher.processBlock(this.f2072FR, 0, this.FRE, 0);
                        byte b4 = bArr[r10];
                        byte b5 = bArr[r10 + 1];
                        bArr2[r12] = encryptByte(b4, 0);
                        bArr2[r12 + 1] = encryptByte(b5, 1);
                        byte[] bArr4 = this.f2072FR;
                        System.arraycopy(bArr4, 2, bArr4, 0, this.blockSize - 2);
                        byte[] bArr5 = this.f2072FR;
                        int r6 = this.blockSize;
                        bArr5[r6 - 2] = b4;
                        bArr5[r6 - 1] = b5;
                        this.cipher.processBlock(bArr5, 0, this.FRE, 0);
                        while (true) {
                            r0 = this.blockSize;
                            if (r2 >= r0) {
                                break;
                            }
                            byte b6 = bArr[r10 + r2];
                            int r33 = r2 - 2;
                            this.f2072FR[r33] = b6;
                            bArr2[r12 + r2] = encryptByte(b6, r33);
                            r2++;
                        }
                        r9 = this.count + r0;
                    }
                    this.count = r9;
                }
                return this.blockSize;
            }
            throw new OutputLengthException("output buffer too short");
        }
        throw new DataLengthException("input buffer too short");
    }

    private int encryptBlock(byte[] bArr, int r10, byte[] bArr2, int r12) throws DataLengthException, IllegalStateException {
        int r0;
        int r02 = this.blockSize;
        if (r10 + r02 <= bArr.length) {
            if (r12 + r02 <= bArr2.length) {
                int r1 = this.count;
                int r3 = 2;
                int r4 = 0;
                if (r1 > r02) {
                    byte[] bArr3 = this.f2072FR;
                    int r5 = r02 - 2;
                    byte encryptByte = encryptByte(bArr[r10], r02 - 2);
                    bArr2[r12] = encryptByte;
                    bArr3[r5] = encryptByte;
                    byte[] bArr4 = this.f2072FR;
                    int r13 = this.blockSize;
                    int r52 = r13 - 1;
                    byte encryptByte2 = encryptByte(bArr[r10 + 1], r13 - 1);
                    bArr2[r12 + 1] = encryptByte2;
                    bArr4[r52] = encryptByte2;
                    this.cipher.processBlock(this.f2072FR, 0, this.FRE, 0);
                    while (r3 < this.blockSize) {
                        byte[] bArr5 = this.f2072FR;
                        int r14 = r3 - 2;
                        byte encryptByte3 = encryptByte(bArr[r10 + r3], r14);
                        bArr2[r12 + r3] = encryptByte3;
                        bArr5[r14] = encryptByte3;
                        r3++;
                    }
                } else {
                    if (r1 != 0) {
                        if (r1 == r02) {
                            this.cipher.processBlock(this.f2072FR, 0, this.FRE, 0);
                            bArr2[r12] = encryptByte(bArr[r10], 0);
                            bArr2[r12 + 1] = encryptByte(bArr[r10 + 1], 1);
                            byte[] bArr6 = this.f2072FR;
                            System.arraycopy(bArr6, 2, bArr6, 0, this.blockSize - 2);
                            System.arraycopy(bArr2, r12, this.f2072FR, this.blockSize - 2, 2);
                            this.cipher.processBlock(this.f2072FR, 0, this.FRE, 0);
                            while (true) {
                                r0 = this.blockSize;
                                if (r3 >= r0) {
                                    break;
                                }
                                byte[] bArr7 = this.f2072FR;
                                int r15 = r3 - 2;
                                byte encryptByte4 = encryptByte(bArr[r10 + r3], r15);
                                bArr2[r12 + r3] = encryptByte4;
                                bArr7[r15] = encryptByte4;
                                r3++;
                            }
                        }
                    } else {
                        this.cipher.processBlock(this.f2072FR, 0, this.FRE, 0);
                        while (true) {
                            r0 = this.blockSize;
                            if (r4 >= r0) {
                                break;
                            }
                            byte[] bArr8 = this.f2072FR;
                            byte encryptByte5 = encryptByte(bArr[r10 + r4], r4);
                            bArr2[r12 + r4] = encryptByte5;
                            bArr8[r4] = encryptByte5;
                            r4++;
                        }
                    }
                    this.count += r0;
                }
                return this.blockSize;
            }
            throw new OutputLengthException("output buffer too short");
        }
        throw new DataLengthException("input buffer too short");
    }

    private byte encryptByte(byte b, int r3) {
        return (byte) (b ^ this.FRE[r3]);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return this.cipher.getAlgorithmName() + "/OpenPGPCFB";
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
        this.forEncryption = z;
        reset();
        this.cipher.init(true, cipherParameters);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int r3, byte[] bArr2, int r5) throws DataLengthException, IllegalStateException {
        return this.forEncryption ? encryptBlock(bArr, r3, bArr2, r5) : decryptBlock(bArr, r3, bArr2, r5);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
        this.count = 0;
        byte[] bArr = this.f2073IV;
        byte[] bArr2 = this.f2072FR;
        System.arraycopy(bArr, 0, bArr2, 0, bArr2.length);
        this.cipher.reset();
    }
}
