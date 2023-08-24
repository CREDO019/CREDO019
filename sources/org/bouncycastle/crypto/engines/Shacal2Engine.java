package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;

/* loaded from: classes5.dex */
public class Shacal2Engine implements BlockCipher {
    private static final int BLOCK_SIZE = 32;

    /* renamed from: K */
    private static final int[] f1967K = {1116352408, 1899447441, -1245643825, -373957723, 961987163, 1508970993, -1841331548, -1424204075, -670586216, 310598401, 607225278, 1426881987, 1925078388, -2132889090, -1680079193, -1046744716, -459576895, -272742522, 264347078, 604807628, 770255983, 1249150122, 1555081692, 1996064986, -1740746414, -1473132947, -1341970488, -1084653625, -958395405, -710438585, 113926993, 338241895, 666307205, 773529912, 1294757372, 1396182291, 1695183700, 1986661051, -2117940946, -1838011259, -1564481375, -1474664885, -1035236496, -949202525, -778901479, -694614492, -200395387, 275423344, 430227734, 506948616, 659060556, 883997877, 958139571, 1322822218, 1537002063, 1747873779, 1955562222, 2024104815, -2067236844, -1933114872, -1866530822, -1538233109, -1090935817, -965641998};
    private static final int ROUNDS = 64;
    private boolean forEncryption = false;
    private int[] workingKey = null;

    private void byteBlockToInts(byte[] bArr, int[] r5, int r6, int r7) {
        while (r7 < 8) {
            int r1 = r6 + 1;
            int r2 = r1 + 1;
            int r62 = ((bArr[r6] & 255) << 24) | ((bArr[r1] & 255) << 16);
            int r12 = r2 + 1;
            r5[r7] = r62 | ((bArr[r2] & 255) << 8) | (bArr[r12] & 255);
            r7++;
            r6 = r12 + 1;
        }
    }

    private void bytes2ints(byte[] bArr, int[] r4, int r5, int r6) {
        while (r6 < bArr.length / 4) {
            int r0 = r5 + 1;
            int r1 = r0 + 1;
            int r52 = ((bArr[r5] & 255) << 24) | ((bArr[r0] & 255) << 16);
            int r02 = r1 + 1;
            int r53 = r52 | ((bArr[r1] & 255) << 8);
            r4[r6] = r53 | (bArr[r02] & 255);
            r6++;
            r5 = r02 + 1;
        }
    }

    private void decryptBlock(byte[] bArr, int r11, byte[] bArr2, int r13) {
        int[] r0 = new int[8];
        byteBlockToInts(bArr, r0, r11, 0);
        for (int r10 = 63; r10 > -1; r10--) {
            int r112 = (r0[0] - ((((r0[1] >>> 2) | (r0[1] << (-2))) ^ ((r0[1] >>> 13) | (r0[1] << (-13)))) ^ ((r0[1] >>> 22) | (r0[1] << (-22))))) - (((r0[1] & r0[2]) ^ (r0[1] & r0[3])) ^ (r0[2] & r0[3]));
            r0[0] = r0[1];
            r0[1] = r0[2];
            r0[2] = r0[3];
            r0[3] = r0[4] - r112;
            r0[4] = r0[5];
            r0[5] = r0[6];
            r0[6] = r0[7];
            r0[7] = (((r112 - f1967K[r10]) - this.workingKey[r10]) - ((((r0[4] >>> 6) | (r0[4] << (-6))) ^ ((r0[4] >>> 11) | (r0[4] << (-11)))) ^ ((r0[4] >>> 25) | (r0[4] << (-25))))) - (((~r0[4]) & r0[6]) ^ (r0[5] & r0[4]));
        }
        ints2bytes(r0, bArr2, r13);
    }

    private void encryptBlock(byte[] bArr, int r10, byte[] bArr2, int r12) {
        int[] r0 = new int[8];
        byteBlockToInts(bArr, r0, r10, 0);
        for (int r9 = 0; r9 < 64; r9++) {
            int r2 = ((((r0[4] >>> 6) | (r0[4] << (-6))) ^ ((r0[4] >>> 11) | (r0[4] << (-11)))) ^ ((r0[4] >>> 25) | (r0[4] << (-25)))) + ((r0[4] & r0[5]) ^ ((~r0[4]) & r0[6])) + r0[7] + f1967K[r9] + this.workingKey[r9];
            r0[7] = r0[6];
            r0[6] = r0[5];
            r0[5] = r0[4];
            r0[4] = r0[3] + r2;
            r0[3] = r0[2];
            r0[2] = r0[1];
            r0[1] = r0[0];
            r0[0] = r2 + ((((r0[0] >>> 2) | (r0[0] << (-2))) ^ ((r0[0] >>> 13) | (r0[0] << (-13)))) ^ ((r0[0] >>> 22) | (r0[0] << (-22)))) + ((r0[2] & r0[3]) ^ ((r0[0] & r0[2]) ^ (r0[0] & r0[3])));
        }
        ints2bytes(r0, bArr2, r12);
    }

    private void ints2bytes(int[] r4, byte[] bArr, int r6) {
        for (int r0 = 0; r0 < r4.length; r0++) {
            int r1 = r6 + 1;
            bArr[r6] = (byte) (r4[r0] >>> 24);
            int r62 = r1 + 1;
            bArr[r1] = (byte) (r4[r0] >>> 16);
            int r12 = r62 + 1;
            bArr[r62] = (byte) (r4[r0] >>> 8);
            r6 = r12 + 1;
            bArr[r12] = (byte) r4[r0];
        }
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "Shacal2";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 32;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("only simple KeyParameter expected.");
        }
        this.forEncryption = z;
        this.workingKey = new int[64];
        setKey(((KeyParameter) cipherParameters).getKey());
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int r4, byte[] bArr2, int r6) throws DataLengthException, IllegalStateException {
        if (this.workingKey != null) {
            if (r4 + 32 <= bArr.length) {
                if (r6 + 32 <= bArr2.length) {
                    if (this.forEncryption) {
                        encryptBlock(bArr, r4, bArr2, r6);
                        return 32;
                    }
                    decryptBlock(bArr, r4, bArr2, r6);
                    return 32;
                }
                throw new OutputLengthException("output buffer too short");
            }
            throw new DataLengthException("input buffer too short");
        }
        throw new IllegalStateException("Shacal2 not initialised");
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }

    public void setKey(byte[] bArr) {
        if (bArr.length != 0 && bArr.length <= 64) {
            if (bArr.length >= 16 && bArr.length % 8 == 0) {
                bytes2ints(bArr, this.workingKey, 0, 0);
                for (int r2 = 16; r2 < 64; r2++) {
                    int[] r8 = this.workingKey;
                    int r0 = r2 - 2;
                    int r3 = r2 - 15;
                    r8[r2] = ((r8[r0] >>> 10) ^ (((r8[r0] >>> 17) | (r8[r0] << (-17))) ^ ((r8[r0] >>> 19) | (r8[r0] << (-19))))) + r8[r2 - 7] + ((r8[r3] >>> 3) ^ (((r8[r3] >>> 7) | (r8[r3] << (-7))) ^ ((r8[r3] >>> 18) | (r8[r3] << (-18))))) + r8[r2 - 16];
                }
                return;
            }
        }
        throw new IllegalArgumentException("Shacal2-key must be 16 - 64 bytes and multiple of 8");
    }
}
