package com.google.android.exoplayer2.decoder;

import android.media.MediaCodec;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;

/* loaded from: classes2.dex */
public final class CryptoInfo {
    public int clearBlocks;
    public int encryptedBlocks;
    private final MediaCodec.CryptoInfo frameworkCryptoInfo;

    /* renamed from: iv */
    public byte[] f219iv;
    public byte[] key;
    public int mode;
    public int[] numBytesOfClearData;
    public int[] numBytesOfEncryptedData;
    public int numSubSamples;
    private final PatternHolderV24 patternHolder;

    public CryptoInfo() {
        MediaCodec.CryptoInfo cryptoInfo = new MediaCodec.CryptoInfo();
        this.frameworkCryptoInfo = cryptoInfo;
        this.patternHolder = Util.SDK_INT >= 24 ? new PatternHolderV24(cryptoInfo) : null;
    }

    public void set(int r2, int[] r3, int[] r4, byte[] bArr, byte[] bArr2, int r7, int r8, int r9) {
        this.numSubSamples = r2;
        this.numBytesOfClearData = r3;
        this.numBytesOfEncryptedData = r4;
        this.key = bArr;
        this.f219iv = bArr2;
        this.mode = r7;
        this.encryptedBlocks = r8;
        this.clearBlocks = r9;
        this.frameworkCryptoInfo.numSubSamples = r2;
        this.frameworkCryptoInfo.numBytesOfClearData = r3;
        this.frameworkCryptoInfo.numBytesOfEncryptedData = r4;
        this.frameworkCryptoInfo.key = bArr;
        this.frameworkCryptoInfo.iv = bArr2;
        this.frameworkCryptoInfo.mode = r7;
        if (Util.SDK_INT >= 24) {
            ((PatternHolderV24) Assertions.checkNotNull(this.patternHolder)).set(r8, r9);
        }
    }

    public MediaCodec.CryptoInfo getFrameworkCryptoInfo() {
        return this.frameworkCryptoInfo;
    }

    public void increaseClearDataFirstSubSampleBy(int r4) {
        if (r4 == 0) {
            return;
        }
        if (this.numBytesOfClearData == null) {
            int[] r0 = new int[1];
            this.numBytesOfClearData = r0;
            this.frameworkCryptoInfo.numBytesOfClearData = r0;
        }
        int[] r02 = this.numBytesOfClearData;
        r02[0] = r02[0] + r4;
    }

    /* loaded from: classes2.dex */
    private static final class PatternHolderV24 {
        private final MediaCodec.CryptoInfo frameworkCryptoInfo;
        private final MediaCodec.CryptoInfo.Pattern pattern;

        private PatternHolderV24(MediaCodec.CryptoInfo cryptoInfo) {
            this.frameworkCryptoInfo = cryptoInfo;
            this.pattern = new MediaCodec.CryptoInfo.Pattern(0, 0);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void set(int r2, int r3) {
            this.pattern.set(r2, r3);
            this.frameworkCryptoInfo.setPattern(this.pattern);
        }
    }
}
