package com.google.android.exoplayer2.extractor.mp4;

import com.google.android.exoplayer2.C1856C;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;

/* loaded from: classes2.dex */
public final class TrackEncryptionBox {
    private static final String TAG = "TrackEncryptionBox";
    public final TrackOutput.CryptoData cryptoData;
    public final byte[] defaultInitializationVector;
    public final boolean isEncrypted;
    public final int perSampleIvSize;
    public final String schemeType;

    public TrackEncryptionBox(boolean z, String str, int r6, byte[] bArr, int r8, int r9, byte[] bArr2) {
        Assertions.checkArgument((bArr2 == null) ^ (r6 == 0));
        this.isEncrypted = z;
        this.schemeType = str;
        this.perSampleIvSize = r6;
        this.defaultInitializationVector = bArr2;
        this.cryptoData = new TrackOutput.CryptoData(schemeToCryptoMode(str), bArr, r8, r9);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private static int schemeToCryptoMode(String str) {
        if (str == null) {
            return 1;
        }
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case 3046605:
                if (str.equals(C1856C.CENC_TYPE_cbc1)) {
                    c = 0;
                    break;
                }
                break;
            case 3046671:
                if (str.equals(C1856C.CENC_TYPE_cbcs)) {
                    c = 1;
                    break;
                }
                break;
            case 3049879:
                if (str.equals(C1856C.CENC_TYPE_cenc)) {
                    c = 2;
                    break;
                }
                break;
            case 3049895:
                if (str.equals(C1856C.CENC_TYPE_cens)) {
                    c = 3;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
            case 1:
                return 2;
            case 2:
            case 3:
                break;
            default:
                Log.m1132w(TAG, "Unsupported protection scheme type '" + str + "'. Assuming AES-CTR crypto mode.");
                break;
        }
        return 1;
    }
}
