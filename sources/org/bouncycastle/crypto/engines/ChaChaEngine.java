package org.bouncycastle.crypto.engines;

import org.bouncycastle.asn1.cmc.BodyPartID;
import org.bouncycastle.util.Integers;
import org.bouncycastle.util.Pack;

/* loaded from: classes5.dex */
public class ChaChaEngine extends Salsa20Engine {
    public ChaChaEngine() {
    }

    public ChaChaEngine(int r1) {
        super(r1);
    }

    public static void chachaCore(int r33, int[] r34, int[] r35) {
        int r3 = 16;
        if (r34.length != 16) {
            throw new IllegalArgumentException();
        }
        if (r35.length != 16) {
            throw new IllegalArgumentException();
        }
        if (r33 % 2 != 0) {
            throw new IllegalArgumentException("Number of rounds must be even");
        }
        char c = 0;
        int r4 = r34[0];
        int r6 = r34[1];
        int r8 = r34[2];
        int r10 = r34[3];
        int r12 = r34[4];
        int r14 = r34[5];
        int r16 = r34[6];
        int r15 = 7;
        int r17 = r34[7];
        int r13 = 8;
        int r18 = r34[8];
        int r20 = r34[9];
        int r22 = r34[10];
        int r24 = r34[11];
        int r11 = 12;
        int r25 = r34[12];
        int r27 = r34[13];
        int r29 = r34[14];
        int r32 = r34[15];
        int r31 = r29;
        int r292 = r27;
        int r272 = r25;
        int r252 = r24;
        int r242 = r22;
        int r222 = r20;
        int r202 = r18;
        int r182 = r17;
        int r172 = r16;
        int r162 = r14;
        int r142 = r12;
        int r122 = r10;
        int r102 = r8;
        int r82 = r6;
        int r62 = r4;
        int r42 = r33;
        while (r42 > 0) {
            int r63 = r62 + r142;
            int rotateLeft = Integers.rotateLeft(r272 ^ r63, r3);
            int r203 = r202 + rotateLeft;
            int rotateLeft2 = Integers.rotateLeft(r142 ^ r203, r11);
            int r64 = r63 + rotateLeft2;
            int rotateLeft3 = Integers.rotateLeft(rotateLeft ^ r64, r13);
            int r204 = r203 + rotateLeft3;
            int rotateLeft4 = Integers.rotateLeft(rotateLeft2 ^ r204, r15);
            int r83 = r82 + r162;
            int rotateLeft5 = Integers.rotateLeft(r292 ^ r83, r3);
            int r223 = r222 + rotateLeft5;
            int rotateLeft6 = Integers.rotateLeft(r162 ^ r223, r11);
            int r84 = r83 + rotateLeft6;
            int rotateLeft7 = Integers.rotateLeft(rotateLeft5 ^ r84, r13);
            int r224 = r223 + rotateLeft7;
            int rotateLeft8 = Integers.rotateLeft(rotateLeft6 ^ r224, r15);
            int r103 = r102 + r172;
            int rotateLeft9 = Integers.rotateLeft(r31 ^ r103, r3);
            int r243 = r242 + rotateLeft9;
            int rotateLeft10 = Integers.rotateLeft(r172 ^ r243, r11);
            int r104 = r103 + rotateLeft10;
            int rotateLeft11 = Integers.rotateLeft(rotateLeft9 ^ r104, r13);
            int r244 = r243 + rotateLeft11;
            int rotateLeft12 = Integers.rotateLeft(rotateLeft10 ^ r244, r15);
            int r123 = r122 + r182;
            int rotateLeft13 = Integers.rotateLeft(r32 ^ r123, 16);
            int r253 = r252 + rotateLeft13;
            int rotateLeft14 = Integers.rotateLeft(r182 ^ r253, r11);
            int r124 = r123 + rotateLeft14;
            int rotateLeft15 = Integers.rotateLeft(rotateLeft13 ^ r124, 8);
            int r254 = r253 + rotateLeft15;
            int rotateLeft16 = Integers.rotateLeft(rotateLeft14 ^ r254, 7);
            int r65 = r64 + rotateLeft8;
            int rotateLeft17 = Integers.rotateLeft(rotateLeft15 ^ r65, 16);
            int r245 = r244 + rotateLeft17;
            int rotateLeft18 = Integers.rotateLeft(rotateLeft8 ^ r245, 12);
            r62 = r65 + rotateLeft18;
            r32 = Integers.rotateLeft(rotateLeft17 ^ r62, 8);
            r242 = r245 + r32;
            r162 = Integers.rotateLeft(rotateLeft18 ^ r242, 7);
            int r85 = r84 + rotateLeft12;
            int rotateLeft19 = Integers.rotateLeft(rotateLeft3 ^ r85, 16);
            int r255 = r254 + rotateLeft19;
            int rotateLeft20 = Integers.rotateLeft(rotateLeft12 ^ r255, 12);
            r82 = r85 + rotateLeft20;
            r272 = Integers.rotateLeft(rotateLeft19 ^ r82, 8);
            r252 = r255 + r272;
            r172 = Integers.rotateLeft(rotateLeft20 ^ r252, 7);
            int r105 = r104 + rotateLeft16;
            int rotateLeft21 = Integers.rotateLeft(rotateLeft7 ^ r105, 16);
            int r205 = r204 + rotateLeft21;
            int rotateLeft22 = Integers.rotateLeft(rotateLeft16 ^ r205, 12);
            r102 = r105 + rotateLeft22;
            r292 = Integers.rotateLeft(rotateLeft21 ^ r102, 8);
            r202 = r205 + r292;
            r182 = Integers.rotateLeft(rotateLeft22 ^ r202, 7);
            int r125 = r124 + rotateLeft4;
            r3 = 16;
            int rotateLeft23 = Integers.rotateLeft(rotateLeft11 ^ r125, 16);
            int r225 = r224 + rotateLeft23;
            int rotateLeft24 = Integers.rotateLeft(rotateLeft4 ^ r225, 12);
            r122 = r125 + rotateLeft24;
            r31 = Integers.rotateLeft(rotateLeft23 ^ r122, 8);
            r222 = r225 + r31;
            r142 = Integers.rotateLeft(rotateLeft24 ^ r222, 7);
            r42 -= 2;
            c = 0;
            r11 = 12;
            r13 = 8;
            r15 = 7;
        }
        r35[c] = r62 + r34[c];
        r35[1] = r82 + r34[1];
        r35[2] = r102 + r34[2];
        r35[3] = r122 + r34[3];
        r35[4] = r142 + r34[4];
        r35[5] = r162 + r34[5];
        r35[6] = r172 + r34[6];
        r35[7] = r182 + r34[7];
        r35[8] = r202 + r34[8];
        r35[9] = r222 + r34[9];
        r35[10] = r242 + r34[10];
        r35[11] = r252 + r34[11];
        r35[12] = r272 + r34[12];
        r35[13] = r292 + r34[13];
        r35[14] = r31 + r34[14];
        r35[15] = r32 + r34[15];
    }

    @Override // org.bouncycastle.crypto.engines.Salsa20Engine
    protected void advanceCounter() {
        int[] r0 = this.engineState;
        int r2 = r0[12] + 1;
        r0[12] = r2;
        if (r2 == 0) {
            int[] r02 = this.engineState;
            r02[13] = r02[13] + 1;
        }
    }

    @Override // org.bouncycastle.crypto.engines.Salsa20Engine
    protected void advanceCounter(long j) {
        int r1 = (int) (j >>> 32);
        int r6 = (int) j;
        if (r1 > 0) {
            int[] r0 = this.engineState;
            r0[13] = r0[13] + r1;
        }
        int r02 = this.engineState[12];
        int[] r2 = this.engineState;
        r2[12] = r2[12] + r6;
        if (r02 == 0 || this.engineState[12] >= r02) {
            return;
        }
        int[] r62 = this.engineState;
        r62[13] = r62[13] + 1;
    }

    @Override // org.bouncycastle.crypto.engines.Salsa20Engine
    protected void generateKeyStream(byte[] bArr) {
        chachaCore(this.rounds, this.engineState, this.f1962x);
        Pack.intToLittleEndian(this.f1962x, bArr, 0);
    }

    @Override // org.bouncycastle.crypto.engines.Salsa20Engine, org.bouncycastle.crypto.StreamCipher
    public String getAlgorithmName() {
        return "ChaCha" + this.rounds;
    }

    @Override // org.bouncycastle.crypto.engines.Salsa20Engine
    protected long getCounter() {
        return (this.engineState[13] << 32) | (this.engineState[12] & BodyPartID.bodyIdMax);
    }

    @Override // org.bouncycastle.crypto.engines.Salsa20Engine
    protected void resetCounter() {
        int[] r0 = this.engineState;
        this.engineState[13] = 0;
        r0[12] = 0;
    }

    @Override // org.bouncycastle.crypto.engines.Salsa20Engine
    protected void retreatCounter() {
        int[] r0;
        if (this.engineState[12] == 0 && this.engineState[13] == 0) {
            throw new IllegalStateException("attempt to reduce counter past zero.");
        }
        int[] r02 = this.engineState;
        int r3 = r02[12] - 1;
        r02[12] = r3;
        if (r3 == -1) {
            this.engineState[13] = r0[13] - 1;
        }
    }

    @Override // org.bouncycastle.crypto.engines.Salsa20Engine
    protected void retreatCounter(long j) {
        int r1 = (int) (j >>> 32);
        int r11 = (int) j;
        if (r1 != 0) {
            if ((this.engineState[13] & BodyPartID.bodyIdMax) < (r1 & BodyPartID.bodyIdMax)) {
                throw new IllegalStateException("attempt to reduce counter past zero.");
            }
            int[] r4 = this.engineState;
            r4[13] = r4[13] - r1;
        }
        if ((this.engineState[12] & BodyPartID.bodyIdMax) >= (r11 & BodyPartID.bodyIdMax)) {
            int[] r10 = this.engineState;
            r10[12] = r10[12] - r11;
        } else if (this.engineState[13] == 0) {
            throw new IllegalStateException("attempt to reduce counter past zero.");
        } else {
            int[] r102 = this.engineState;
            r102[13] = r102[13] - 1;
            int[] r103 = this.engineState;
            r103[12] = r103[12] - r11;
        }
    }

    @Override // org.bouncycastle.crypto.engines.Salsa20Engine
    protected void setKey(byte[] bArr, byte[] bArr2) {
        if (bArr != null) {
            if (bArr.length != 16 && bArr.length != 32) {
                throw new IllegalArgumentException(getAlgorithmName() + " requires 128 bit or 256 bit key");
            }
            packTauOrSigma(bArr.length, this.engineState, 0);
            Pack.littleEndianToInt(bArr, 0, this.engineState, 4, 4);
            Pack.littleEndianToInt(bArr, bArr.length - 16, this.engineState, 8, 4);
        }
        Pack.littleEndianToInt(bArr2, 0, this.engineState, 14, 2);
    }
}
