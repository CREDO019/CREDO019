package org.bouncycastle.crypto.macs;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.Pack;

/* loaded from: classes5.dex */
public class SipHash implements Mac {

    /* renamed from: c */
    protected final int f2026c;

    /* renamed from: d */
    protected final int f2027d;

    /* renamed from: k0 */
    protected long f2028k0;

    /* renamed from: k1 */
    protected long f2029k1;

    /* renamed from: m */
    protected long f2030m;

    /* renamed from: v0 */
    protected long f2031v0;

    /* renamed from: v1 */
    protected long f2032v1;

    /* renamed from: v2 */
    protected long f2033v2;

    /* renamed from: v3 */
    protected long f2034v3;
    protected int wordCount;
    protected int wordPos;

    public SipHash() {
        this.f2030m = 0L;
        this.wordPos = 0;
        this.wordCount = 0;
        this.f2026c = 2;
        this.f2027d = 4;
    }

    public SipHash(int r3, int r4) {
        this.f2030m = 0L;
        this.wordPos = 0;
        this.wordCount = 0;
        this.f2026c = r3;
        this.f2027d = r4;
    }

    protected static long rotateLeft(long j, int r4) {
        return (j >>> (-r4)) | (j << r4);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void applySipRounds(int r12) {
        long j = this.f2031v0;
        long j2 = this.f2032v1;
        long j3 = this.f2033v2;
        long j4 = this.f2034v3;
        for (int r8 = 0; r8 < r12; r8++) {
            long j5 = j + j2;
            long j6 = j3 + j4;
            long rotateLeft = rotateLeft(j2, 13) ^ j5;
            long rotateLeft2 = rotateLeft(j4, 16) ^ j6;
            long j7 = j6 + rotateLeft;
            j = rotateLeft(j5, 32) + rotateLeft2;
            j2 = rotateLeft(rotateLeft, 17) ^ j7;
            j4 = rotateLeft(rotateLeft2, 21) ^ j;
            j3 = rotateLeft(j7, 32);
        }
        this.f2031v0 = j;
        this.f2032v1 = j2;
        this.f2033v2 = j3;
        this.f2034v3 = j4;
    }

    @Override // org.bouncycastle.crypto.Mac
    public int doFinal(byte[] bArr, int r4) throws DataLengthException, IllegalStateException {
        Pack.longToLittleEndian(doFinal(), bArr, r4);
        return 8;
    }

    public long doFinal() throws DataLengthException, IllegalStateException {
        int r2;
        this.f2030m = ((this.f2030m >>> ((7 - this.wordPos) << 3)) >>> 8) | ((((this.wordCount << 3) + r2) & 255) << 56);
        processMessageWord();
        this.f2033v2 ^= 255;
        applySipRounds(this.f2027d);
        long j = ((this.f2031v0 ^ this.f2032v1) ^ this.f2033v2) ^ this.f2034v3;
        reset();
        return j;
    }

    @Override // org.bouncycastle.crypto.Mac
    public String getAlgorithmName() {
        return "SipHash-" + this.f2026c + "-" + this.f2027d;
    }

    @Override // org.bouncycastle.crypto.Mac
    public int getMacSize() {
        return 8;
    }

    @Override // org.bouncycastle.crypto.Mac
    public void init(CipherParameters cipherParameters) throws IllegalArgumentException {
        if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("'params' must be an instance of KeyParameter");
        }
        byte[] key = ((KeyParameter) cipherParameters).getKey();
        if (key.length != 16) {
            throw new IllegalArgumentException("'params' must be a 128-bit key");
        }
        this.f2028k0 = Pack.littleEndianToLong(key, 0);
        this.f2029k1 = Pack.littleEndianToLong(key, 8);
        reset();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void processMessageWord() {
        this.wordCount++;
        this.f2034v3 ^= this.f2030m;
        applySipRounds(this.f2026c);
        this.f2031v0 ^= this.f2030m;
    }

    @Override // org.bouncycastle.crypto.Mac
    public void reset() {
        long j = this.f2028k0;
        this.f2031v0 = 8317987319222330741L ^ j;
        long j2 = this.f2029k1;
        this.f2032v1 = 7237128888997146477L ^ j2;
        this.f2033v2 = j ^ 7816392313619706465L;
        this.f2034v3 = 8387220255154660723L ^ j2;
        this.f2030m = 0L;
        this.wordPos = 0;
        this.wordCount = 0;
    }

    @Override // org.bouncycastle.crypto.Mac
    public void update(byte b) throws IllegalStateException {
        this.f2030m = (this.f2030m >>> 8) | ((b & 255) << 56);
        int r8 = this.wordPos + 1;
        this.wordPos = r8;
        if (r8 == 8) {
            processMessageWord();
            this.wordPos = 0;
        }
    }

    @Override // org.bouncycastle.crypto.Mac
    public void update(byte[] bArr, int r17, int r18) throws DataLengthException, IllegalStateException {
        int r3 = r18 & (-8);
        int r4 = this.wordPos;
        int r8 = 0;
        if (r4 == 0) {
            while (r8 < r3) {
                this.f2030m = Pack.littleEndianToLong(bArr, r17 + r8);
                processMessageWord();
                r8 += 8;
            }
            while (r8 < r18) {
                long j = this.f2030m >>> 8;
                this.f2030m = j;
                this.f2030m = j | ((bArr[r17 + r8] & 255) << 56);
                r8++;
            }
            this.wordPos = r18 - r3;
            return;
        }
        int r42 = r4 << 3;
        int r10 = 0;
        while (r10 < r3) {
            long littleEndianToLong = Pack.littleEndianToLong(bArr, r17 + r10);
            this.f2030m = (this.f2030m >>> (-r42)) | (littleEndianToLong << r42);
            processMessageWord();
            this.f2030m = littleEndianToLong;
            r10 += 8;
        }
        while (r10 < r18) {
            long j2 = this.f2030m >>> 8;
            this.f2030m = j2;
            this.f2030m = j2 | ((bArr[r17 + r10] & 255) << 56);
            int r32 = this.wordPos + 1;
            this.wordPos = r32;
            if (r32 == 8) {
                processMessageWord();
                this.wordPos = 0;
            }
            r10++;
        }
    }
}
