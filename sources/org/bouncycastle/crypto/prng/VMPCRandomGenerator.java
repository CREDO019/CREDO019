package org.bouncycastle.crypto.prng;

import com.google.common.base.Ascii;
import com.google.common.primitives.SignedBytes;
import okio.Utf8;
import org.apache.commons.fileupload.MultipartStream;
import org.bouncycastle.crypto.signers.PSSSigner;
import org.bouncycastle.util.Pack;

/* loaded from: classes5.dex */
public class VMPCRandomGenerator implements RandomGenerator {

    /* renamed from: n */
    private byte f2158n = 0;

    /* renamed from: P */
    private byte[] f2157P = {-69, 44, 98, Byte.MAX_VALUE, -75, -86, -44, 13, -127, -2, -78, -126, -53, -96, -95, 8, Ascii.CAN, 113, 86, -24, 73, 2, 16, -60, -34, 53, -91, -20, Byte.MIN_VALUE, Ascii.DC2, -72, 105, -38, 47, 117, -52, -94, 9, 54, 3, 97, MultipartStream.DASH, -3, -32, -35, 5, 67, -112, -83, -56, -31, -81, 87, -101, 76, -40, 81, -82, 80, -123, 60, 10, -28, -13, -100, 38, 35, 83, -55, -125, -105, 70, -79, -103, 100, 49, 119, -43, Ascii.f1123GS, -42, 120, -67, 94, -80, -118, 34, 56, -8, 104, 43, 42, -59, -45, -9, PSSSigner.TRAILER_IMPLICIT, 111, -33, 4, -27, -107, 62, 37, -122, -90, Ascii.f1132VT, -113, -15, 36, Ascii.f1129SO, -41, SignedBytes.MAX_POWER_OF_TWO, -77, -49, 126, 6, Ascii.NAK, -102, 77, Ascii.f1122FS, -93, -37, 50, -110, 88, 17, 39, -12, 89, -48, 78, 106, Ascii.ETB, 91, -84, -1, 7, -64, 101, 121, -4, -57, -51, 118, 66, 93, -25, 58, 52, 122, 48, 40, Ascii.f1128SI, 115, 1, -7, -47, -46, Ascii.f1120EM, -23, -111, -71, 90, -19, 65, 109, -76, -61, -98, -65, 99, -6, Ascii.f1131US, 51, 96, 71, -119, -16, -106, Ascii.SUB, 95, -109, 61, 55, 75, -39, -88, -63, Ascii.ESC, -10, 57, -117, -73, Ascii.f1121FF, 32, -50, -120, 110, -74, 116, -114, -115, Ascii.SYN, 41, -14, -121, -11, -21, 112, -29, -5, 85, -97, -58, 68, 74, 69, 125, -30, 107, 92, 108, 102, -87, -116, -18, -124, 19, -89, Ascii.f1127RS, -99, -36, 103, 72, -70, 46, -26, -92, -85, 124, -108, 0, 33, -17, -22, -66, -54, 114, 79, 82, -104, Utf8.REPLACEMENT_BYTE, -62, Ascii.DC4, 123, 59, 84};

    /* renamed from: s */
    private byte f2159s = -66;

    @Override // org.bouncycastle.crypto.prng.RandomGenerator
    public void addSeedMaterial(long j) {
        addSeedMaterial(Pack.longToBigEndian(j));
    }

    @Override // org.bouncycastle.crypto.prng.RandomGenerator
    public void addSeedMaterial(byte[] bArr) {
        for (byte b : bArr) {
            byte[] bArr2 = this.f2157P;
            byte b2 = this.f2159s;
            byte b3 = this.f2158n;
            byte b4 = bArr2[(b2 + bArr2[b3 & 255] + b) & 255];
            this.f2159s = b4;
            byte b5 = bArr2[b3 & 255];
            bArr2[b3 & 255] = bArr2[b4 & 255];
            bArr2[b4 & 255] = b5;
            this.f2158n = (byte) ((b3 + 1) & 255);
        }
    }

    @Override // org.bouncycastle.crypto.prng.RandomGenerator
    public void nextBytes(byte[] bArr) {
        nextBytes(bArr, 0, bArr.length);
    }

    @Override // org.bouncycastle.crypto.prng.RandomGenerator
    public void nextBytes(byte[] bArr, int r9, int r10) {
        synchronized (this.f2157P) {
            int r102 = r10 + r9;
            while (r9 != r102) {
                byte[] bArr2 = this.f2157P;
                byte b = this.f2159s;
                byte b2 = this.f2158n;
                byte b3 = bArr2[(b + bArr2[b2 & 255]) & 255];
                this.f2159s = b3;
                bArr[r9] = bArr2[(bArr2[bArr2[b3 & 255] & 255] + 1) & 255];
                byte b4 = bArr2[b2 & 255];
                bArr2[b2 & 255] = bArr2[b3 & 255];
                bArr2[b3 & 255] = b4;
                this.f2158n = (byte) ((b2 + 1) & 255);
                r9++;
            }
        }
    }
}
