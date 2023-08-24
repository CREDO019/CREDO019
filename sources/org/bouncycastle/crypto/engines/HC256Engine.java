package org.bouncycastle.crypto.engines;

import com.google.android.exoplayer2.analytics.AnalyticsListener;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.StreamCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;

/* loaded from: classes5.dex */
public class HC256Engine implements StreamCipher {
    private boolean initialised;

    /* renamed from: iv */
    private byte[] f1933iv;
    private byte[] key;

    /* renamed from: p */
    private int[] f1934p = new int[1024];

    /* renamed from: q */
    private int[] f1935q = new int[1024];
    private int cnt = 0;
    private byte[] buf = new byte[4];
    private int idx = 0;

    private byte getByte() {
        if (this.idx == 0) {
            int step = step();
            byte[] bArr = this.buf;
            bArr[0] = (byte) (step & 255);
            int r0 = step >> 8;
            bArr[1] = (byte) (r0 & 255);
            int r02 = r0 >> 8;
            bArr[2] = (byte) (r02 & 255);
            bArr[3] = (byte) ((r02 >> 8) & 255);
        }
        byte[] bArr2 = this.buf;
        int r3 = this.idx;
        byte b = bArr2[r3];
        this.idx = 3 & (r3 + 1);
        return b;
    }

    private void init() {
        byte[] bArr = this.key;
        if (bArr.length != 32 && bArr.length != 16) {
            throw new IllegalArgumentException("The key must be 128/256 bits long");
        }
        if (this.f1933iv.length < 16) {
            throw new IllegalArgumentException("The IV must be at least 128 bits long");
        }
        if (bArr.length != 32) {
            byte[] bArr2 = new byte[32];
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            byte[] bArr3 = this.key;
            System.arraycopy(bArr3, 0, bArr2, 16, bArr3.length);
            this.key = bArr2;
        }
        byte[] bArr4 = this.f1933iv;
        if (bArr4.length < 32) {
            byte[] bArr5 = new byte[32];
            System.arraycopy(bArr4, 0, bArr5, 0, bArr4.length);
            byte[] bArr6 = this.f1933iv;
            System.arraycopy(bArr6, 0, bArr5, bArr6.length, 32 - bArr6.length);
            this.f1933iv = bArr5;
        }
        this.idx = 0;
        this.cnt = 0;
        int[] r1 = new int[2560];
        for (int r5 = 0; r5 < 32; r5++) {
            int r6 = r5 >> 2;
            r1[r6] = r1[r6] | ((this.key[r5] & 255) << ((r5 & 3) * 8));
        }
        for (int r52 = 0; r52 < 32; r52++) {
            int r62 = (r52 >> 2) + 8;
            r1[r62] = r1[r62] | ((this.f1933iv[r52] & 255) << ((r52 & 3) * 8));
        }
        for (int r2 = 16; r2 < 2560; r2++) {
            int r3 = r1[r2 - 2];
            int r53 = r1[r2 - 15];
            r1[r2] = ((r3 >>> 10) ^ (rotateRight(r3, 17) ^ rotateRight(r3, 19))) + r1[r2 - 7] + ((r53 >>> 3) ^ (rotateRight(r53, 7) ^ rotateRight(r53, 18))) + r1[r2 - 16] + r2;
        }
        System.arraycopy(r1, 512, this.f1934p, 0, 1024);
        System.arraycopy(r1, 1536, this.f1935q, 0, 1024);
        for (int r0 = 0; r0 < 4096; r0++) {
            step();
        }
        this.cnt = 0;
    }

    private static int rotateRight(int r1, int r2) {
        return (r1 << (-r2)) | (r1 >>> r2);
    }

    private int step() {
        int r4;
        int r0;
        int r02 = this.cnt;
        int r1 = r02 & AnalyticsListener.EVENT_DRM_KEYS_LOADED;
        if (r02 < 1024) {
            int[] r03 = this.f1934p;
            int r42 = r03[(r1 - 3) & AnalyticsListener.EVENT_DRM_KEYS_LOADED];
            int r5 = r03[(r1 - 1023) & AnalyticsListener.EVENT_DRM_KEYS_LOADED];
            int r6 = r03[r1];
            int rotateRight = r03[(r1 - 10) & AnalyticsListener.EVENT_DRM_KEYS_LOADED] + (rotateRight(r5, 23) ^ rotateRight(r42, 10));
            int[] r2 = this.f1935q;
            r03[r1] = r6 + rotateRight + r2[(r42 ^ r5) & AnalyticsListener.EVENT_DRM_KEYS_LOADED];
            int[] r04 = this.f1934p;
            int r3 = r04[(r1 - 12) & AnalyticsListener.EVENT_DRM_KEYS_LOADED];
            r4 = r2[r3 & 255] + r2[((r3 >> 8) & 255) + 256] + r2[((r3 >> 16) & 255) + 512] + r2[((r3 >> 24) & 255) + 768];
            r0 = r04[r1];
        } else {
            int[] r05 = this.f1935q;
            int r43 = r05[(r1 - 3) & AnalyticsListener.EVENT_DRM_KEYS_LOADED];
            int r52 = r05[(r1 - 1023) & AnalyticsListener.EVENT_DRM_KEYS_LOADED];
            int r62 = r05[r1];
            int rotateRight2 = r05[(r1 - 10) & AnalyticsListener.EVENT_DRM_KEYS_LOADED] + (rotateRight(r52, 23) ^ rotateRight(r43, 10));
            int[] r22 = this.f1934p;
            r05[r1] = r62 + rotateRight2 + r22[(r43 ^ r52) & AnalyticsListener.EVENT_DRM_KEYS_LOADED];
            int[] r06 = this.f1935q;
            int r32 = r06[(r1 - 12) & AnalyticsListener.EVENT_DRM_KEYS_LOADED];
            r4 = r22[r32 & 255] + r22[((r32 >> 8) & 255) + 256] + r22[((r32 >> 16) & 255) + 512] + r22[((r32 >> 24) & 255) + 768];
            r0 = r06[r1];
        }
        int r07 = r0 ^ r4;
        this.cnt = (this.cnt + 1) & 2047;
        return r07;
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public String getAlgorithmName() {
        return "HC-256";
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        CipherParameters cipherParameters2;
        if (cipherParameters instanceof ParametersWithIV) {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            this.f1933iv = parametersWithIV.getIV();
            cipherParameters2 = parametersWithIV.getParameters();
        } else {
            this.f1933iv = new byte[0];
            cipherParameters2 = cipherParameters;
        }
        if (cipherParameters2 instanceof KeyParameter) {
            this.key = ((KeyParameter) cipherParameters2).getKey();
            init();
            this.initialised = true;
            return;
        }
        throw new IllegalArgumentException("Invalid parameter passed to HC256 init - " + cipherParameters.getClass().getName());
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public int processBytes(byte[] bArr, int r6, int r7, byte[] bArr2, int r9) throws DataLengthException {
        if (!this.initialised) {
            throw new IllegalStateException(getAlgorithmName() + " not initialised");
        } else if (r6 + r7 <= bArr.length) {
            if (r9 + r7 <= bArr2.length) {
                for (int r0 = 0; r0 < r7; r0++) {
                    bArr2[r9 + r0] = (byte) (bArr[r6 + r0] ^ getByte());
                }
                return r7;
            }
            throw new OutputLengthException("output buffer too short");
        } else {
            throw new DataLengthException("input buffer too short");
        }
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public void reset() {
        init();
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public byte returnByte(byte b) {
        return (byte) (b ^ getByte());
    }
}
