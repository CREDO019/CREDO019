package org.bouncycastle.crypto.engines;

import androidx.core.app.FrameMetricsAggregator;
import com.google.android.exoplayer2.analytics.AnalyticsListener;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.StreamCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;

/* loaded from: classes5.dex */
public class HC128Engine implements StreamCipher {
    private boolean initialised;

    /* renamed from: iv */
    private byte[] f1930iv;
    private byte[] key;

    /* renamed from: p */
    private int[] f1931p = new int[512];

    /* renamed from: q */
    private int[] f1932q = new int[512];
    private int cnt = 0;
    private byte[] buf = new byte[4];
    private int idx = 0;

    private static int dim(int r0, int r1) {
        return mod512(r0 - r1);
    }

    /* renamed from: f1 */
    private static int m56f1(int r2) {
        return (r2 >>> 3) ^ (rotateRight(r2, 7) ^ rotateRight(r2, 18));
    }

    /* renamed from: f2 */
    private static int m55f2(int r2) {
        return (r2 >>> 10) ^ (rotateRight(r2, 17) ^ rotateRight(r2, 19));
    }

    /* renamed from: g1 */
    private int m54g1(int r2, int r3, int r4) {
        return (rotateRight(r2, 10) ^ rotateRight(r4, 23)) + rotateRight(r3, 8);
    }

    /* renamed from: g2 */
    private int m53g2(int r2, int r3, int r4) {
        return (rotateLeft(r2, 10) ^ rotateLeft(r4, 23)) + rotateLeft(r3, 8);
    }

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

    /* renamed from: h1 */
    private int m52h1(int r3) {
        int[] r0 = this.f1932q;
        return r0[r3 & 255] + r0[((r3 >> 16) & 255) + 256];
    }

    /* renamed from: h2 */
    private int m51h2(int r3) {
        int[] r0 = this.f1931p;
        return r0[r3 & 255] + r0[((r3 >> 16) & 255) + 256];
    }

    private void init() {
        if (this.key.length != 16) {
            throw new IllegalArgumentException("The key must be 128 bits long");
        }
        this.idx = 0;
        this.cnt = 0;
        int[] r3 = new int[1280];
        for (int r4 = 0; r4 < 16; r4++) {
            int r6 = r4 >> 2;
            r3[r6] = ((this.key[r4] & 255) << ((r4 & 3) * 8)) | r3[r6];
        }
        System.arraycopy(r3, 0, r3, 4, 4);
        int r62 = 0;
        while (true) {
            byte[] bArr = this.f1930iv;
            if (r62 >= bArr.length || r62 >= 16) {
                break;
            }
            int r8 = (r62 >> 2) + 8;
            r3[r8] = ((bArr[r62] & 255) << ((r62 & 3) * 8)) | r3[r8];
            r62++;
        }
        System.arraycopy(r3, 8, r3, 12, 4);
        for (int r1 = 16; r1 < 1280; r1++) {
            r3[r1] = m55f2(r3[r1 - 2]) + r3[r1 - 7] + m56f1(r3[r1 - 15]) + r3[r1 - 16] + r1;
        }
        System.arraycopy(r3, 256, this.f1931p, 0, 512);
        System.arraycopy(r3, 768, this.f1932q, 0, 512);
        for (int r12 = 0; r12 < 512; r12++) {
            this.f1931p[r12] = step();
        }
        for (int r13 = 0; r13 < 512; r13++) {
            this.f1932q[r13] = step();
        }
        this.cnt = 0;
    }

    private static int mod1024(int r0) {
        return r0 & AnalyticsListener.EVENT_DRM_KEYS_LOADED;
    }

    private static int mod512(int r0) {
        return r0 & FrameMetricsAggregator.EVERY_DURATION;
    }

    private static int rotateLeft(int r1, int r2) {
        return (r1 >>> (-r2)) | (r1 << r2);
    }

    private static int rotateRight(int r1, int r2) {
        return (r1 << (-r2)) | (r1 >>> r2);
    }

    private int step() {
        int m51h2;
        int r0;
        int mod512 = mod512(this.cnt);
        if (this.cnt < 512) {
            int[] r1 = this.f1931p;
            r1[mod512] = r1[mod512] + m54g1(r1[dim(mod512, 3)], this.f1931p[dim(mod512, 10)], this.f1931p[dim(mod512, FrameMetricsAggregator.EVERY_DURATION)]);
            m51h2 = m52h1(this.f1931p[dim(mod512, 12)]);
            r0 = this.f1931p[mod512];
        } else {
            int[] r12 = this.f1932q;
            r12[mod512] = r12[mod512] + m53g2(r12[dim(mod512, 3)], this.f1932q[dim(mod512, 10)], this.f1932q[dim(mod512, FrameMetricsAggregator.EVERY_DURATION)]);
            m51h2 = m51h2(this.f1932q[dim(mod512, 12)]);
            r0 = this.f1932q[mod512];
        }
        int r02 = r0 ^ m51h2;
        this.cnt = mod1024(this.cnt + 1);
        return r02;
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public String getAlgorithmName() {
        return "HC-128";
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        CipherParameters cipherParameters2;
        if (cipherParameters instanceof ParametersWithIV) {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            this.f1930iv = parametersWithIV.getIV();
            cipherParameters2 = parametersWithIV.getParameters();
        } else {
            this.f1930iv = new byte[0];
            cipherParameters2 = cipherParameters;
        }
        if (cipherParameters2 instanceof KeyParameter) {
            this.key = ((KeyParameter) cipherParameters2).getKey();
            init();
            this.initialised = true;
            return;
        }
        throw new IllegalArgumentException("Invalid parameter passed to HC128 init - " + cipherParameters.getClass().getName());
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
