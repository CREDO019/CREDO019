package org.bouncycastle.crypto.prng.drbg;

import java.util.Hashtable;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.prng.EntropySource;
import org.bouncycastle.pqc.crypto.sphincs.SPHINCSKeyParameters;
import org.bouncycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Integers;

/* loaded from: classes5.dex */
public class HashSP800DRBG implements SP80090DRBG {
    private static final int MAX_BITS_REQUEST = 262144;
    private static final byte[] ONE = {1};
    private static final long RESEED_MAX = 140737488355328L;
    private static final Hashtable seedlens;

    /* renamed from: _C */
    private byte[] f2172_C;

    /* renamed from: _V */
    private byte[] f2173_V;
    private Digest _digest;
    private EntropySource _entropySource;
    private long _reseedCounter;
    private int _securityStrength;
    private int _seedLength;

    static {
        Hashtable hashtable = new Hashtable();
        seedlens = hashtable;
        hashtable.put("SHA-1", Integers.valueOf(440));
        hashtable.put(McElieceCCA2KeyGenParameterSpec.SHA224, Integers.valueOf(440));
        hashtable.put("SHA-256", Integers.valueOf(440));
        hashtable.put(SPHINCSKeyParameters.SHA512_256, Integers.valueOf(440));
        hashtable.put("SHA-512/224", Integers.valueOf(440));
        hashtable.put("SHA-384", Integers.valueOf(888));
        hashtable.put("SHA-512", Integers.valueOf(888));
    }

    public HashSP800DRBG(Digest digest, int r3, EntropySource entropySource, byte[] bArr, byte[] bArr2) {
        if (r3 > C5246Utils.getMaxSecurityStrength(digest)) {
            throw new IllegalArgumentException("Requested security strength is not supported by the derivation function");
        }
        if (entropySource.entropySize() < r3) {
            throw new IllegalArgumentException("Not enough entropy for security strength required");
        }
        this._digest = digest;
        this._entropySource = entropySource;
        this._securityStrength = r3;
        this._seedLength = ((Integer) seedlens.get(digest.getAlgorithmName())).intValue();
        byte[] hash_df = C5246Utils.hash_df(this._digest, Arrays.concatenate(getEntropy(), bArr2, bArr), this._seedLength);
        this.f2173_V = hash_df;
        byte[] bArr3 = new byte[hash_df.length + 1];
        System.arraycopy(hash_df, 0, bArr3, 1, hash_df.length);
        this.f2172_C = C5246Utils.hash_df(this._digest, bArr3, this._seedLength);
        this._reseedCounter = 1L;
    }

    private void addTo(byte[] bArr, byte[] bArr2) {
        int r3 = 0;
        for (int r2 = 1; r2 <= bArr2.length; r2++) {
            int r4 = (bArr[bArr.length - r2] & 255) + (bArr2[bArr2.length - r2] & 255) + r3;
            r3 = r4 > 255 ? 1 : 0;
            bArr[bArr.length - r2] = (byte) r4;
        }
        for (int length = bArr2.length + 1; length <= bArr.length; length++) {
            int r22 = (bArr[bArr.length - length] & 255) + r3;
            r3 = r22 > 255 ? 1 : 0;
            bArr[bArr.length - length] = (byte) r22;
        }
    }

    private void doHash(byte[] bArr, byte[] bArr2) {
        this._digest.update(bArr, 0, bArr.length);
        this._digest.doFinal(bArr2, 0);
    }

    private byte[] getEntropy() {
        byte[] entropy = this._entropySource.getEntropy();
        if (entropy.length >= (this._securityStrength + 7) / 8) {
            return entropy;
        }
        throw new IllegalStateException("Insufficient entropy provided by entropy source");
    }

    private byte[] hash(byte[] bArr) {
        byte[] bArr2 = new byte[this._digest.getDigestSize()];
        doHash(bArr, bArr2);
        return bArr2;
    }

    private byte[] hashgen(byte[] bArr, int r10) {
        int r102 = r10 / 8;
        int digestSize = r102 / this._digest.getDigestSize();
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        byte[] bArr3 = new byte[r102];
        int digestSize2 = this._digest.getDigestSize();
        byte[] bArr4 = new byte[digestSize2];
        for (int r5 = 0; r5 <= digestSize; r5++) {
            doHash(bArr2, bArr4);
            int r6 = r5 * digestSize2;
            int r7 = r102 - r6;
            if (r7 > digestSize2) {
                r7 = digestSize2;
            }
            System.arraycopy(bArr4, 0, bArr3, r6, r7);
            addTo(bArr2, ONE);
        }
        return bArr3;
    }

    @Override // org.bouncycastle.crypto.prng.drbg.SP80090DRBG
    public int generate(byte[] bArr, byte[] bArr2, boolean z) {
        long j;
        int length = bArr.length * 8;
        if (length <= 262144) {
            if (this._reseedCounter > RESEED_MAX) {
                return -1;
            }
            if (z) {
                reseed(bArr2);
                bArr2 = null;
            }
            if (bArr2 != null) {
                byte[] bArr3 = this.f2173_V;
                byte[] bArr4 = new byte[bArr3.length + 1 + bArr2.length];
                bArr4[0] = 2;
                System.arraycopy(bArr3, 0, bArr4, 1, bArr3.length);
                System.arraycopy(bArr2, 0, bArr4, this.f2173_V.length + 1, bArr2.length);
                addTo(this.f2173_V, hash(bArr4));
            }
            byte[] hashgen = hashgen(this.f2173_V, length);
            byte[] bArr5 = this.f2173_V;
            byte[] bArr6 = new byte[bArr5.length + 1];
            System.arraycopy(bArr5, 0, bArr6, 1, bArr5.length);
            bArr6[0] = 3;
            addTo(this.f2173_V, hash(bArr6));
            addTo(this.f2173_V, this.f2172_C);
            addTo(this.f2173_V, new byte[]{(byte) (j >> 24), (byte) (j >> 16), (byte) (j >> 8), (byte) this._reseedCounter});
            this._reseedCounter++;
            System.arraycopy(hashgen, 0, bArr, 0, bArr.length);
            return length;
        }
        throw new IllegalArgumentException("Number of bits per request limited to 262144");
    }

    @Override // org.bouncycastle.crypto.prng.drbg.SP80090DRBG
    public int getBlockSize() {
        return this._digest.getDigestSize() * 8;
    }

    @Override // org.bouncycastle.crypto.prng.drbg.SP80090DRBG
    public void reseed(byte[] bArr) {
        byte[] hash_df = C5246Utils.hash_df(this._digest, Arrays.concatenate(ONE, this.f2173_V, getEntropy(), bArr), this._seedLength);
        this.f2173_V = hash_df;
        byte[] bArr2 = new byte[hash_df.length + 1];
        bArr2[0] = 0;
        System.arraycopy(hash_df, 0, bArr2, 1, hash_df.length);
        this.f2172_C = C5246Utils.hash_df(this._digest, bArr2, this._seedLength);
        this._reseedCounter = 1L;
    }
}
