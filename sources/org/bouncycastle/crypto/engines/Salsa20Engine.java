package org.bouncycastle.crypto.engines;

import org.bouncycastle.asn1.cmc.BodyPartID;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.MaxBytesExceededException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.SkippingStreamCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Integers;
import org.bouncycastle.util.Pack;
import org.bouncycastle.util.Strings;

/* loaded from: classes5.dex */
public class Salsa20Engine implements SkippingStreamCipher {
    public static final int DEFAULT_ROUNDS = 20;
    private static final int STATE_SIZE = 16;
    private static final int[] TAU_SIGMA = Pack.littleEndianToInt(Strings.toByteArray("expand 16-byte kexpand 32-byte k"), 0, 8);
    protected static final byte[] sigma = Strings.toByteArray("expand 32-byte k");
    protected static final byte[] tau = Strings.toByteArray("expand 16-byte k");
    private int cW0;
    private int cW1;
    private int cW2;
    protected int[] engineState;
    private int index;
    private boolean initialised;
    private byte[] keyStream;
    protected int rounds;

    /* renamed from: x */
    protected int[] f1962x;

    public Salsa20Engine() {
        this(20);
    }

    public Salsa20Engine(int r4) {
        this.index = 0;
        this.engineState = new int[16];
        this.f1962x = new int[16];
        this.keyStream = new byte[64];
        this.initialised = false;
        if (r4 <= 0 || (r4 & 1) != 0) {
            throw new IllegalArgumentException("'rounds' must be a positive, even number");
        }
        this.rounds = r4;
    }

    private boolean limitExceeded() {
        int r0 = this.cW0 + 1;
        this.cW0 = r0;
        if (r0 == 0) {
            int r02 = this.cW1 + 1;
            this.cW1 = r02;
            if (r02 == 0) {
                int r03 = this.cW2 + 1;
                this.cW2 = r03;
                return (r03 & 32) != 0;
            }
        }
        return false;
    }

    private boolean limitExceeded(int r3) {
        int r0 = this.cW0 + r3;
        this.cW0 = r0;
        if (r0 >= r3 || r0 < 0) {
            return false;
        }
        int r32 = this.cW1 + 1;
        this.cW1 = r32;
        if (r32 == 0) {
            int r33 = this.cW2 + 1;
            this.cW2 = r33;
            return (r33 & 32) != 0;
        }
        return false;
    }

    private void resetLimitCounter() {
        this.cW0 = 0;
        this.cW1 = 0;
        this.cW2 = 0;
    }

    public static void salsaCore(int r33, int[] r34, int[] r35) {
        if (r34.length != 16) {
            throw new IllegalArgumentException();
        }
        if (r35.length != 16) {
            throw new IllegalArgumentException();
        }
        if (r33 % 2 != 0) {
            throw new IllegalArgumentException("Number of rounds must be even");
        }
        int r3 = r34[0];
        int r5 = r34[1];
        int r7 = r34[2];
        int r9 = r34[3];
        int r11 = r34[4];
        int r13 = r34[5];
        int r15 = r34[6];
        int r14 = 7;
        int r16 = r34[7];
        int r18 = r34[8];
        int r12 = 9;
        int r19 = r34[9];
        int r21 = r34[10];
        int r23 = r34[11];
        int r25 = r34[12];
        int r10 = 13;
        int r26 = r34[13];
        int r28 = r34[14];
        int r31 = r34[15];
        int r30 = r28;
        int r282 = r26;
        int r262 = r25;
        int r252 = r23;
        int r232 = r21;
        int r212 = r19;
        int r192 = r18;
        int r182 = r16;
        int r162 = r15;
        int r152 = r13;
        int r132 = r11;
        int r112 = r9;
        int r92 = r7;
        int r72 = r5;
        int r52 = r3;
        int r32 = r33;
        while (r32 > 0) {
            int rotateLeft = Integers.rotateLeft(r52 + r262, r14) ^ r132;
            int rotateLeft2 = r192 ^ Integers.rotateLeft(rotateLeft + r52, r12);
            int rotateLeft3 = r262 ^ Integers.rotateLeft(rotateLeft2 + rotateLeft, r10);
            int rotateLeft4 = Integers.rotateLeft(rotateLeft3 + rotateLeft2, 18) ^ r52;
            int rotateLeft5 = r212 ^ Integers.rotateLeft(r152 + r72, r14);
            int rotateLeft6 = r282 ^ Integers.rotateLeft(rotateLeft5 + r152, r12);
            int rotateLeft7 = r72 ^ Integers.rotateLeft(rotateLeft6 + rotateLeft5, r10);
            int rotateLeft8 = Integers.rotateLeft(rotateLeft7 + rotateLeft6, 18) ^ r152;
            int rotateLeft9 = r30 ^ Integers.rotateLeft(r232 + r162, 7);
            int rotateLeft10 = r92 ^ Integers.rotateLeft(rotateLeft9 + r232, 9);
            int rotateLeft11 = r162 ^ Integers.rotateLeft(rotateLeft10 + rotateLeft9, 13);
            int rotateLeft12 = r232 ^ Integers.rotateLeft(rotateLeft11 + rotateLeft10, 18);
            int rotateLeft13 = r112 ^ Integers.rotateLeft(r31 + r252, 7);
            int rotateLeft14 = r182 ^ Integers.rotateLeft(rotateLeft13 + r31, 9);
            int r322 = r32;
            int rotateLeft15 = r252 ^ Integers.rotateLeft(rotateLeft14 + rotateLeft13, 13);
            int rotateLeft16 = r31 ^ Integers.rotateLeft(rotateLeft15 + rotateLeft14, 18);
            r72 = rotateLeft7 ^ Integers.rotateLeft(rotateLeft4 + rotateLeft13, 7);
            r92 = rotateLeft10 ^ Integers.rotateLeft(r72 + rotateLeft4, 9);
            int rotateLeft17 = rotateLeft13 ^ Integers.rotateLeft(r92 + r72, 13);
            int rotateLeft18 = rotateLeft4 ^ Integers.rotateLeft(rotateLeft17 + r92, 18);
            r162 = rotateLeft11 ^ Integers.rotateLeft(rotateLeft8 + rotateLeft, 7);
            r182 = rotateLeft14 ^ Integers.rotateLeft(r162 + rotateLeft8, 9);
            int rotateLeft19 = Integers.rotateLeft(r182 + r162, 13) ^ rotateLeft;
            r152 = rotateLeft8 ^ Integers.rotateLeft(rotateLeft19 + r182, 18);
            r252 = rotateLeft15 ^ Integers.rotateLeft(rotateLeft12 + rotateLeft5, 7);
            int rotateLeft20 = Integers.rotateLeft(r252 + rotateLeft12, 9) ^ rotateLeft2;
            r212 = rotateLeft5 ^ Integers.rotateLeft(rotateLeft20 + r252, 13);
            r232 = rotateLeft12 ^ Integers.rotateLeft(r212 + rotateLeft20, 18);
            r262 = rotateLeft3 ^ Integers.rotateLeft(rotateLeft16 + rotateLeft9, 7);
            r282 = rotateLeft6 ^ Integers.rotateLeft(r262 + rotateLeft16, 9);
            r30 = rotateLeft9 ^ Integers.rotateLeft(r282 + r262, 13);
            r31 = rotateLeft16 ^ Integers.rotateLeft(r30 + r282, 18);
            r112 = rotateLeft17;
            r192 = rotateLeft20;
            r52 = rotateLeft18;
            r132 = rotateLeft19;
            r10 = 13;
            r12 = 9;
            r14 = 7;
            r32 = r322 - 2;
        }
        r35[0] = r52 + r34[0];
        r35[1] = r72 + r34[1];
        r35[2] = r92 + r34[2];
        r35[3] = r112 + r34[3];
        r35[4] = r132 + r34[4];
        r35[5] = r152 + r34[5];
        r35[6] = r162 + r34[6];
        r35[7] = r182 + r34[7];
        r35[8] = r192 + r34[8];
        r35[9] = r212 + r34[9];
        r35[10] = r232 + r34[10];
        r35[11] = r252 + r34[11];
        r35[12] = r262 + r34[12];
        r35[13] = r282 + r34[13];
        r35[14] = r30 + r34[14];
        r35[15] = r31 + r34[15];
    }

    protected void advanceCounter() {
        int[] r0 = this.engineState;
        int r2 = r0[8] + 1;
        r0[8] = r2;
        if (r2 == 0) {
            r0[9] = r0[9] + 1;
        }
    }

    protected void advanceCounter(long j) {
        int r1 = (int) (j >>> 32);
        int r6 = (int) j;
        if (r1 > 0) {
            int[] r0 = this.engineState;
            r0[9] = r0[9] + r1;
        }
        int[] r02 = this.engineState;
        int r2 = r02[8];
        r02[8] = r02[8] + r6;
        if (r2 == 0 || r02[8] >= r2) {
            return;
        }
        r02[9] = r02[9] + 1;
    }

    protected void generateKeyStream(byte[] bArr) {
        salsaCore(this.rounds, this.engineState, this.f1962x);
        Pack.intToLittleEndian(this.f1962x, bArr, 0);
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public String getAlgorithmName() {
        if (this.rounds != 20) {
            return "Salsa20/" + this.rounds;
        }
        return "Salsa20";
    }

    protected long getCounter() {
        int[] r0 = this.engineState;
        return (r0[9] << 32) | (r0[8] & BodyPartID.bodyIdMax);
    }

    protected int getNonceSize() {
        return 8;
    }

    @Override // org.bouncycastle.crypto.SkippingCipher
    public long getPosition() {
        return (getCounter() * 64) + this.index;
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (!(cipherParameters instanceof ParametersWithIV)) {
            throw new IllegalArgumentException(getAlgorithmName() + " Init parameters must include an IV");
        }
        ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
        byte[] bArr = parametersWithIV.getIV();
        if (bArr == null || bArr.length != getNonceSize()) {
            throw new IllegalArgumentException(getAlgorithmName() + " requires exactly " + getNonceSize() + " bytes of IV");
        }
        CipherParameters parameters = parametersWithIV.getParameters();
        if (parameters == null) {
            if (!this.initialised) {
                throw new IllegalStateException(getAlgorithmName() + " KeyParameter can not be null for first initialisation");
            }
            setKey(null, bArr);
        } else if (!(parameters instanceof KeyParameter)) {
            throw new IllegalArgumentException(getAlgorithmName() + " Init parameters must contain a KeyParameter (or null for re-init)");
        } else {
            setKey(((KeyParameter) parameters).getKey(), bArr);
        }
        reset();
        this.initialised = true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void packTauOrSigma(int r4, int[] r5, int r6) {
        int r42 = (r4 - 16) / 4;
        int[] r0 = TAU_SIGMA;
        r5[r6] = r0[r42];
        r5[r6 + 1] = r0[r42 + 1];
        r5[r6 + 2] = r0[r42 + 2];
        r5[r6 + 3] = r0[r42 + 3];
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public int processBytes(byte[] bArr, int r7, int r8, byte[] bArr2, int r10) {
        if (!this.initialised) {
            throw new IllegalStateException(getAlgorithmName() + " not initialised");
        } else if (r7 + r8 <= bArr.length) {
            if (r10 + r8 <= bArr2.length) {
                if (limitExceeded(r8)) {
                    throw new MaxBytesExceededException("2^70 byte limit per IV would be exceeded; Change IV");
                }
                for (int r0 = 0; r0 < r8; r0++) {
                    byte[] bArr3 = this.keyStream;
                    int r3 = this.index;
                    bArr2[r0 + r10] = (byte) (bArr3[r3] ^ bArr[r0 + r7]);
                    int r1 = (r3 + 1) & 63;
                    this.index = r1;
                    if (r1 == 0) {
                        advanceCounter();
                        generateKeyStream(this.keyStream);
                    }
                }
                return r8;
            }
            throw new OutputLengthException("output buffer too short");
        } else {
            throw new DataLengthException("input buffer too short");
        }
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public void reset() {
        this.index = 0;
        resetLimitCounter();
        resetCounter();
        generateKeyStream(this.keyStream);
    }

    protected void resetCounter() {
        int[] r0 = this.engineState;
        r0[9] = 0;
        r0[8] = 0;
    }

    protected void retreatCounter() {
        int[] r0 = this.engineState;
        if (r0[8] == 0 && r0[9] == 0) {
            throw new IllegalStateException("attempt to reduce counter past zero.");
        }
        int r2 = r0[8] - 1;
        r0[8] = r2;
        if (r2 == -1) {
            r0[9] = r0[9] - 1;
        }
    }

    protected void retreatCounter(long j) {
        int r1 = (int) (j >>> 32);
        int r12 = (int) j;
        if (r1 != 0) {
            int[] r4 = this.engineState;
            if ((r4[9] & BodyPartID.bodyIdMax) < (r1 & BodyPartID.bodyIdMax)) {
                throw new IllegalStateException("attempt to reduce counter past zero.");
            }
            r4[9] = r4[9] - r1;
        }
        int[] r13 = this.engineState;
        if ((r13[8] & BodyPartID.bodyIdMax) >= (BodyPartID.bodyIdMax & r12)) {
            r13[8] = r13[8] - r12;
        } else if (r13[9] == 0) {
            throw new IllegalStateException("attempt to reduce counter past zero.");
        } else {
            r13[9] = r13[9] - 1;
            r13[8] = r13[8] - r12;
        }
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public byte returnByte(byte b) {
        if (limitExceeded()) {
            throw new MaxBytesExceededException("2^70 byte limit per IV; Change IV");
        }
        byte[] bArr = this.keyStream;
        int r1 = this.index;
        byte b2 = (byte) (b ^ bArr[r1]);
        int r0 = (r1 + 1) & 63;
        this.index = r0;
        if (r0 == 0) {
            advanceCounter();
            generateKeyStream(this.keyStream);
        }
        return b2;
    }

    @Override // org.bouncycastle.crypto.SkippingCipher
    public long seekTo(long j) {
        reset();
        return skip(j);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setKey(byte[] bArr, byte[] bArr2) {
        if (bArr != null) {
            if (bArr.length != 16 && bArr.length != 32) {
                throw new IllegalArgumentException(getAlgorithmName() + " requires 128 bit or 256 bit key");
            }
            int length = (bArr.length - 16) / 4;
            int[] r4 = this.engineState;
            int[] r5 = TAU_SIGMA;
            r4[0] = r5[length];
            r4[5] = r5[length + 1];
            r4[10] = r5[length + 2];
            r4[15] = r5[length + 3];
            Pack.littleEndianToInt(bArr, 0, r4, 1, 4);
            Pack.littleEndianToInt(bArr, bArr.length - 16, this.engineState, 11, 4);
        }
        Pack.littleEndianToInt(bArr2, 0, this.engineState, 6, 2);
    }

    @Override // org.bouncycastle.crypto.SkippingCipher
    public long skip(long j) {
        long j2;
        if (j >= 0) {
            if (j >= 64) {
                long j3 = j / 64;
                advanceCounter(j3);
                j2 = j - (j3 * 64);
            } else {
                j2 = j;
            }
            int r2 = this.index;
            int r0 = (((int) j2) + r2) & 63;
            this.index = r0;
            if (r0 < r2) {
                advanceCounter();
            }
        } else {
            long j4 = -j;
            if (j4 >= 64) {
                long j5 = j4 / 64;
                retreatCounter(j5);
                j4 -= j5 * 64;
            }
            for (long j6 = 0; j6 < j4; j6++) {
                if (this.index == 0) {
                    retreatCounter();
                }
                this.index = (this.index - 1) & 63;
            }
        }
        generateKeyStream(this.keyStream);
        return j;
    }
}
