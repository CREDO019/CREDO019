package org.bouncycastle.crypto.engines;

import com.google.common.base.Ascii;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.signers.PSSSigner;
import org.bouncycastle.util.Integers;
import org.bouncycastle.util.Pack;

/* loaded from: classes5.dex */
public class NoekeonEngine implements BlockCipher {
    private static final int SIZE = 16;
    private static final byte[] roundConstants = {Byte.MIN_VALUE, Ascii.ESC, 54, 108, -40, -85, 77, -102, 47, 94, PSSSigner.TRAILER_IMPLICIT, 99, -58, -105, 53, 106, -44};
    private boolean _forEncryption;

    /* renamed from: k */
    private final int[] f1941k = new int[4];
    private boolean _initialised = false;

    private int decryptBlock(byte[] bArr, int r21, byte[] bArr2, int r23) {
        int bigEndianToInt = Pack.bigEndianToInt(bArr, r21);
        int bigEndianToInt2 = Pack.bigEndianToInt(bArr, r21 + 4);
        int bigEndianToInt3 = Pack.bigEndianToInt(bArr, r21 + 8);
        int bigEndianToInt4 = Pack.bigEndianToInt(bArr, r21 + 12);
        int[] r7 = this.f1941k;
        int r8 = r7[0];
        int r10 = r7[1];
        int r12 = r7[2];
        int r72 = r7[3];
        int r14 = 16;
        while (true) {
            int r15 = bigEndianToInt ^ bigEndianToInt3;
            int rotateLeft = r15 ^ (Integers.rotateLeft(r15, 8) ^ Integers.rotateLeft(r15, 24));
            int r4 = bigEndianToInt2 ^ r10;
            int r0 = bigEndianToInt4 ^ r72;
            int r13 = r4 ^ r0;
            int rotateLeft2 = (Integers.rotateLeft(r13, 24) ^ Integers.rotateLeft(r13, 8)) ^ r13;
            int r42 = r4 ^ rotateLeft;
            int r5 = (bigEndianToInt3 ^ r12) ^ rotateLeft2;
            int r02 = r0 ^ rotateLeft;
            int r3 = ((bigEndianToInt ^ r8) ^ rotateLeft2) ^ (roundConstants[r14] & 255);
            r14--;
            if (r14 < 0) {
                Pack.intToBigEndian(r3, bArr2, r23);
                Pack.intToBigEndian(r42, bArr2, r23 + 4);
                Pack.intToBigEndian(r5, bArr2, r23 + 8);
                Pack.intToBigEndian(r02, bArr2, r23 + 12);
                return 16;
            }
            int rotateLeft3 = Integers.rotateLeft(r42, 1);
            int rotateLeft4 = Integers.rotateLeft(r5, 5);
            int rotateLeft5 = Integers.rotateLeft(r02, 2);
            int r43 = rotateLeft3 ^ (rotateLeft5 | rotateLeft4);
            int r152 = ~r43;
            int r32 = r3 ^ (rotateLeft4 & r152);
            int r52 = (rotateLeft4 ^ (r152 ^ rotateLeft5)) ^ r32;
            int r44 = r43 ^ (r32 | r52);
            bigEndianToInt2 = Integers.rotateLeft(r44, 31);
            bigEndianToInt3 = Integers.rotateLeft(r52, 27);
            int rotateLeft6 = Integers.rotateLeft(r32, 30);
            bigEndianToInt = rotateLeft5 ^ (r52 & r44);
            bigEndianToInt4 = rotateLeft6;
        }
    }

    private int encryptBlock(byte[] bArr, int r21, byte[] bArr2, int r23) {
        int bigEndianToInt = Pack.bigEndianToInt(bArr, r21);
        int bigEndianToInt2 = Pack.bigEndianToInt(bArr, r21 + 4);
        int bigEndianToInt3 = Pack.bigEndianToInt(bArr, r21 + 8);
        int bigEndianToInt4 = Pack.bigEndianToInt(bArr, r21 + 12);
        int[] r7 = this.f1941k;
        int r8 = 0;
        int r9 = r7[0];
        int r11 = r7[1];
        int r13 = r7[2];
        int r72 = r7[3];
        while (true) {
            int r3 = bigEndianToInt ^ (roundConstants[r8] & 255);
            int r14 = r3 ^ bigEndianToInt3;
            int rotateLeft = r14 ^ (Integers.rotateLeft(r14, 8) ^ Integers.rotateLeft(r14, 24));
            int r4 = bigEndianToInt2 ^ r11;
            int r0 = bigEndianToInt4 ^ r72;
            int r10 = r4 ^ r0;
            int rotateLeft2 = r10 ^ (Integers.rotateLeft(r10, 24) ^ Integers.rotateLeft(r10, 8));
            int r32 = (r3 ^ r9) ^ rotateLeft2;
            int r42 = r4 ^ rotateLeft;
            int r5 = (bigEndianToInt3 ^ r13) ^ rotateLeft2;
            int r02 = r0 ^ rotateLeft;
            r8++;
            if (r8 > 16) {
                Pack.intToBigEndian(r32, bArr2, r23);
                Pack.intToBigEndian(r42, bArr2, r23 + 4);
                Pack.intToBigEndian(r5, bArr2, r23 + 8);
                Pack.intToBigEndian(r02, bArr2, r23 + 12);
                return 16;
            }
            int rotateLeft3 = Integers.rotateLeft(r42, 1);
            int rotateLeft4 = Integers.rotateLeft(r5, 5);
            int rotateLeft5 = Integers.rotateLeft(r02, 2);
            int r43 = rotateLeft3 ^ (rotateLeft5 | rotateLeft4);
            int r142 = ~r43;
            int r33 = r32 ^ (rotateLeft4 & r142);
            int r52 = (rotateLeft4 ^ (r142 ^ rotateLeft5)) ^ r33;
            int r44 = r43 ^ (r33 | r52);
            bigEndianToInt2 = Integers.rotateLeft(r44, 31);
            bigEndianToInt3 = Integers.rotateLeft(r52, 27);
            int rotateLeft6 = Integers.rotateLeft(r33, 30);
            bigEndianToInt = rotateLeft5 ^ (r52 & r44);
            bigEndianToInt4 = rotateLeft6;
        }
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "Noekeon";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 16;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("invalid parameter passed to Noekeon init - " + cipherParameters.getClass().getName());
        }
        byte[] key = ((KeyParameter) cipherParameters).getKey();
        if (key.length != 16) {
            throw new IllegalArgumentException("Key length not 128 bits.");
        }
        Pack.bigEndianToInt(key, 0, this.f1941k, 0, 4);
        if (!z) {
            int[] r0 = this.f1941k;
            int r1 = r0[0];
            int r3 = r0[1];
            int r5 = r0[2];
            int r02 = r0[3];
            int r7 = r1 ^ r5;
            int rotateLeft = r7 ^ (Integers.rotateLeft(r7, 8) ^ Integers.rotateLeft(r7, 24));
            int r9 = r3 ^ r02;
            int rotateLeft2 = (Integers.rotateLeft(r9, 8) ^ Integers.rotateLeft(r9, 24)) ^ r9;
            int r32 = r3 ^ rotateLeft;
            int r03 = r02 ^ rotateLeft;
            int[] r72 = this.f1941k;
            r72[0] = r1 ^ rotateLeft2;
            r72[1] = r32;
            r72[2] = r5 ^ rotateLeft2;
            r72[3] = r03;
        }
        this._forEncryption = z;
        this._initialised = true;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int r3, byte[] bArr2, int r5) {
        if (!this._initialised) {
            throw new IllegalStateException(getAlgorithmName() + " not initialised");
        } else if (r3 <= bArr.length - 16) {
            if (r5 <= bArr2.length - 16) {
                return this._forEncryption ? encryptBlock(bArr, r3, bArr2, r5) : decryptBlock(bArr, r3, bArr2, r5);
            }
            throw new OutputLengthException("output buffer too short");
        } else {
            throw new DataLengthException("input buffer too short");
        }
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
