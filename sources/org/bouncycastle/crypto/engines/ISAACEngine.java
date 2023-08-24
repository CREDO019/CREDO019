package org.bouncycastle.crypto.engines;

import com.google.android.exoplayer2.analytics.AnalyticsListener;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.StreamCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.Pack;

/* loaded from: classes5.dex */
public class ISAACEngine implements StreamCipher {
    private final int sizeL = 8;
    private final int stateArraySize = 256;
    private int[] engineState = null;
    private int[] results = null;

    /* renamed from: a */
    private int f1938a = 0;

    /* renamed from: b */
    private int f1939b = 0;

    /* renamed from: c */
    private int f1940c = 0;
    private int index = 0;
    private byte[] keyStream = new byte[1024];
    private byte[] workingKey = null;
    private boolean initialised = false;

    private void isaac() {
        int r4;
        int r5;
        int r0 = this.f1939b;
        int r1 = this.f1940c + 1;
        this.f1940c = r1;
        this.f1939b = r0 + r1;
        for (int r02 = 0; r02 < 256; r02++) {
            int[] r12 = this.engineState;
            int r3 = r12[r02];
            int r42 = r02 & 3;
            if (r42 == 0) {
                r4 = this.f1938a;
                r5 = r4 << 13;
            } else if (r42 == 1) {
                r4 = this.f1938a;
                r5 = r4 >>> 6;
            } else if (r42 == 2) {
                r4 = this.f1938a;
                r5 = r4 << 2;
            } else if (r42 != 3) {
                int r43 = this.f1938a + r12[(r02 + 128) & 255];
                this.f1938a = r43;
                int r52 = r12[(r3 >>> 2) & 255] + r43 + this.f1939b;
                r12[r02] = r52;
                int[] r44 = this.results;
                int r13 = r12[(r52 >>> 10) & 255] + r3;
                this.f1939b = r13;
                r44[r02] = r13;
            } else {
                r4 = this.f1938a;
                r5 = r4 >>> 16;
            }
            this.f1938a = r4 ^ r5;
            int r432 = this.f1938a + r12[(r02 + 128) & 255];
            this.f1938a = r432;
            int r522 = r12[(r3 >>> 2) & 255] + r432 + this.f1939b;
            r12[r02] = r522;
            int[] r442 = this.results;
            int r132 = r12[(r522 >>> 10) & 255] + r3;
            this.f1939b = r132;
            r442[r02] = r132;
        }
    }

    private void mix(int[] r10) {
        r10[0] = r10[0] ^ (r10[1] << 11);
        r10[3] = r10[3] + r10[0];
        r10[1] = r10[1] + r10[2];
        r10[1] = r10[1] ^ (r10[2] >>> 2);
        r10[4] = r10[4] + r10[1];
        r10[2] = r10[2] + r10[3];
        r10[2] = r10[2] ^ (r10[3] << 8);
        r10[5] = r10[5] + r10[2];
        r10[3] = r10[3] + r10[4];
        r10[3] = r10[3] ^ (r10[4] >>> 16);
        r10[6] = r10[6] + r10[3];
        r10[4] = r10[4] + r10[5];
        r10[4] = r10[4] ^ (r10[5] << 10);
        r10[7] = r10[7] + r10[4];
        r10[5] = r10[5] + r10[6];
        r10[5] = (r10[6] >>> 4) ^ r10[5];
        r10[0] = r10[0] + r10[5];
        r10[6] = r10[6] + r10[7];
        r10[6] = r10[6] ^ (r10[7] << 8);
        r10[1] = r10[1] + r10[6];
        r10[7] = r10[7] + r10[0];
        r10[7] = r10[7] ^ (r10[0] >>> 9);
        r10[2] = r10[2] + r10[7];
        r10[0] = r10[0] + r10[1];
    }

    private void setKey(byte[] bArr) {
        this.workingKey = bArr;
        if (this.engineState == null) {
            this.engineState = new int[256];
        }
        if (this.results == null) {
            this.results = new int[256];
        }
        for (int r2 = 0; r2 < 256; r2++) {
            int[] r3 = this.engineState;
            this.results[r2] = 0;
            r3[r2] = 0;
        }
        this.f1940c = 0;
        this.f1939b = 0;
        this.f1938a = 0;
        this.index = 0;
        int length = bArr.length + (bArr.length & 3);
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        for (int r11 = 0; r11 < length; r11 += 4) {
            this.results[r11 >>> 2] = Pack.littleEndianToInt(bArr2, r11);
        }
        int[] r22 = new int[8];
        for (int r32 = 0; r32 < 8; r32++) {
            r22[r32] = -1640531527;
        }
        for (int r33 = 0; r33 < 4; r33++) {
            mix(r22);
        }
        int r34 = 0;
        while (r34 < 2) {
            for (int r4 = 0; r4 < 256; r4 += 8) {
                for (int r6 = 0; r6 < 8; r6++) {
                    r22[r6] = r22[r6] + (r34 < 1 ? this.results[r4 + r6] : this.engineState[r4 + r6]);
                }
                mix(r22);
                for (int r62 = 0; r62 < 8; r62++) {
                    this.engineState[r4 + r62] = r22[r62];
                }
            }
            r34++;
        }
        isaac();
        this.initialised = true;
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public String getAlgorithmName() {
        return "ISAAC";
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof KeyParameter) {
            setKey(((KeyParameter) cipherParameters).getKey());
            return;
        }
        throw new IllegalArgumentException("invalid parameter passed to ISAAC init - " + cipherParameters.getClass().getName());
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public int processBytes(byte[] bArr, int r7, int r8, byte[] bArr2, int r10) {
        if (!this.initialised) {
            throw new IllegalStateException(getAlgorithmName() + " not initialised");
        } else if (r7 + r8 <= bArr.length) {
            if (r10 + r8 <= bArr2.length) {
                for (int r0 = 0; r0 < r8; r0++) {
                    if (this.index == 0) {
                        isaac();
                        this.keyStream = Pack.intToBigEndian(this.results);
                    }
                    byte[] bArr3 = this.keyStream;
                    int r3 = this.index;
                    bArr2[r0 + r10] = (byte) (bArr3[r3] ^ bArr[r0 + r7]);
                    this.index = (r3 + 1) & AnalyticsListener.EVENT_DRM_KEYS_LOADED;
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
        setKey(this.workingKey);
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public byte returnByte(byte b) {
        if (this.index == 0) {
            isaac();
            this.keyStream = Pack.intToBigEndian(this.results);
        }
        byte[] bArr = this.keyStream;
        int r1 = this.index;
        byte b2 = (byte) (b ^ bArr[r1]);
        this.index = (r1 + 1) & AnalyticsListener.EVENT_DRM_KEYS_LOADED;
        return b2;
    }
}
