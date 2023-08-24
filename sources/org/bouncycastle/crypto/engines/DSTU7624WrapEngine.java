package org.bouncycastle.crypto.engines;

import java.util.ArrayList;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.Wrapper;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.util.Arrays;

/* loaded from: classes5.dex */
public class DSTU7624WrapEngine implements Wrapper {
    private static final int BYTES_IN_INTEGER = 4;

    /* renamed from: B */
    private byte[] f1924B;
    private ArrayList<byte[]> Btemp;
    private byte[] checkSumArray;
    private DSTU7624Engine engine;
    private boolean forWrapping;
    private byte[] intArray;
    private byte[] zeroArray;

    public DSTU7624WrapEngine(int r2) {
        DSTU7624Engine dSTU7624Engine = new DSTU7624Engine(r2);
        this.engine = dSTU7624Engine;
        this.f1924B = new byte[dSTU7624Engine.getBlockSize() / 2];
        this.checkSumArray = new byte[this.engine.getBlockSize()];
        this.zeroArray = new byte[this.engine.getBlockSize()];
        this.Btemp = new ArrayList<>();
        this.intArray = new byte[4];
    }

    private void intToBytes(int r3, byte[] bArr, int r5) {
        bArr[r5 + 3] = (byte) (r3 >> 24);
        bArr[r5 + 2] = (byte) (r3 >> 16);
        bArr[r5 + 1] = (byte) (r3 >> 8);
        bArr[r5] = (byte) r3;
    }

    @Override // org.bouncycastle.crypto.Wrapper
    public String getAlgorithmName() {
        return "DSTU7624WrapEngine";
    }

    @Override // org.bouncycastle.crypto.Wrapper
    public void init(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof ParametersWithRandom) {
            cipherParameters = ((ParametersWithRandom) cipherParameters).getParameters();
        }
        this.forWrapping = z;
        if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("invalid parameters passed to DSTU7624WrapEngine");
        }
        this.engine.init(z, cipherParameters);
    }

    @Override // org.bouncycastle.crypto.Wrapper
    public byte[] unwrap(byte[] bArr, int r12, int r13) throws InvalidCipherTextException {
        if (this.forWrapping) {
            throw new IllegalStateException("not set for unwrapping");
        }
        if (r13 % this.engine.getBlockSize() != 0) {
            throw new DataLengthException("unwrap data must be a multiple of " + this.engine.getBlockSize() + " bytes");
        }
        int blockSize = (r13 * 2) / this.engine.getBlockSize();
        int r1 = blockSize - 1;
        int r2 = r1 * 6;
        byte[] bArr2 = new byte[r13];
        System.arraycopy(bArr, r12, bArr2, 0, r13);
        byte[] bArr3 = new byte[this.engine.getBlockSize() / 2];
        System.arraycopy(bArr2, 0, bArr3, 0, this.engine.getBlockSize() / 2);
        this.Btemp.clear();
        int blockSize2 = r13 - (this.engine.getBlockSize() / 2);
        int blockSize3 = this.engine.getBlockSize() / 2;
        while (blockSize2 != 0) {
            byte[] bArr4 = new byte[this.engine.getBlockSize() / 2];
            System.arraycopy(bArr2, blockSize3, bArr4, 0, this.engine.getBlockSize() / 2);
            this.Btemp.add(bArr4);
            blockSize2 -= this.engine.getBlockSize() / 2;
            blockSize3 += this.engine.getBlockSize() / 2;
        }
        for (int r5 = 0; r5 < r2; r5++) {
            System.arraycopy(this.Btemp.get(blockSize - 2), 0, bArr2, 0, this.engine.getBlockSize() / 2);
            System.arraycopy(bArr3, 0, bArr2, this.engine.getBlockSize() / 2, this.engine.getBlockSize() / 2);
            intToBytes(r2 - r5, this.intArray, 0);
            for (int r6 = 0; r6 < 4; r6++) {
                int blockSize4 = (this.engine.getBlockSize() / 2) + r6;
                bArr2[blockSize4] = (byte) (bArr2[blockSize4] ^ this.intArray[r6]);
            }
            this.engine.processBlock(bArr2, 0, bArr2, 0);
            System.arraycopy(bArr2, 0, bArr3, 0, this.engine.getBlockSize() / 2);
            for (int r62 = 2; r62 < blockSize; r62++) {
                int r8 = blockSize - r62;
                System.arraycopy(this.Btemp.get(r8 - 1), 0, this.Btemp.get(r8), 0, this.engine.getBlockSize() / 2);
            }
            System.arraycopy(bArr2, this.engine.getBlockSize() / 2, this.Btemp.get(0), 0, this.engine.getBlockSize() / 2);
        }
        System.arraycopy(bArr3, 0, bArr2, 0, this.engine.getBlockSize() / 2);
        int blockSize5 = this.engine.getBlockSize() / 2;
        for (int r0 = 0; r0 < r1; r0++) {
            System.arraycopy(this.Btemp.get(r0), 0, bArr2, blockSize5, this.engine.getBlockSize() / 2);
            blockSize5 += this.engine.getBlockSize() / 2;
        }
        System.arraycopy(bArr2, r13 - this.engine.getBlockSize(), this.checkSumArray, 0, this.engine.getBlockSize());
        byte[] bArr5 = new byte[r13 - this.engine.getBlockSize()];
        if (Arrays.areEqual(this.checkSumArray, this.zeroArray)) {
            System.arraycopy(bArr2, 0, bArr5, 0, r13 - this.engine.getBlockSize());
            return bArr5;
        }
        throw new InvalidCipherTextException("checksum failed");
    }

    @Override // org.bouncycastle.crypto.Wrapper
    public byte[] wrap(byte[] bArr, int r10, int r11) {
        if (this.forWrapping) {
            if (r11 % this.engine.getBlockSize() != 0) {
                throw new DataLengthException("wrap data must be a multiple of " + this.engine.getBlockSize() + " bytes");
            } else if (r10 + r11 <= bArr.length) {
                int blockSize = ((r11 / this.engine.getBlockSize()) + 1) * 2;
                int r2 = blockSize - 1;
                int r3 = r2 * 6;
                int blockSize2 = this.engine.getBlockSize() + r11;
                byte[] bArr2 = new byte[blockSize2];
                System.arraycopy(bArr, r10, bArr2, 0, r11);
                System.arraycopy(bArr2, 0, this.f1924B, 0, this.engine.getBlockSize() / 2);
                this.Btemp.clear();
                int blockSize3 = blockSize2 - (this.engine.getBlockSize() / 2);
                int blockSize4 = this.engine.getBlockSize() / 2;
                while (blockSize3 != 0) {
                    byte[] bArr3 = new byte[this.engine.getBlockSize() / 2];
                    System.arraycopy(bArr2, blockSize4, bArr3, 0, this.engine.getBlockSize() / 2);
                    this.Btemp.add(bArr3);
                    blockSize3 -= this.engine.getBlockSize() / 2;
                    blockSize4 += this.engine.getBlockSize() / 2;
                }
                int r9 = 0;
                while (r9 < r3) {
                    System.arraycopy(this.f1924B, 0, bArr2, 0, this.engine.getBlockSize() / 2);
                    System.arraycopy(this.Btemp.get(0), 0, bArr2, this.engine.getBlockSize() / 2, this.engine.getBlockSize() / 2);
                    this.engine.processBlock(bArr2, 0, bArr2, 0);
                    r9++;
                    intToBytes(r9, this.intArray, 0);
                    for (int r102 = 0; r102 < 4; r102++) {
                        int blockSize5 = (this.engine.getBlockSize() / 2) + r102;
                        bArr2[blockSize5] = (byte) (bArr2[blockSize5] ^ this.intArray[r102]);
                    }
                    System.arraycopy(bArr2, this.engine.getBlockSize() / 2, this.f1924B, 0, this.engine.getBlockSize() / 2);
                    for (int r103 = 2; r103 < blockSize; r103++) {
                        System.arraycopy(this.Btemp.get(r103 - 1), 0, this.Btemp.get(r103 - 2), 0, this.engine.getBlockSize() / 2);
                    }
                    System.arraycopy(bArr2, 0, this.Btemp.get(blockSize - 2), 0, this.engine.getBlockSize() / 2);
                }
                System.arraycopy(this.f1924B, 0, bArr2, 0, this.engine.getBlockSize() / 2);
                int blockSize6 = this.engine.getBlockSize() / 2;
                for (int r104 = 0; r104 < r2; r104++) {
                    System.arraycopy(this.Btemp.get(r104), 0, bArr2, blockSize6, this.engine.getBlockSize() / 2);
                    blockSize6 += this.engine.getBlockSize() / 2;
                }
                return bArr2;
            } else {
                throw new DataLengthException("input buffer too short");
            }
        }
        throw new IllegalStateException("not set for wrapping");
    }
}
