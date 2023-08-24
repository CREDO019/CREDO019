package org.bouncycastle.crypto.digests;

import com.google.common.base.Ascii;
import com.google.common.primitives.SignedBytes;
import okio.Utf8;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.apache.commons.fileupload.MultipartStream;
import org.bouncycastle.crypto.ExtendedDigest;
import org.bouncycastle.crypto.signers.PSSSigner;
import org.bouncycastle.util.Memoable;

/* loaded from: classes5.dex */
public class MD2Digest implements ExtendedDigest, Memoable {
    private static final int DIGEST_LENGTH = 16;

    /* renamed from: S */
    private static final byte[] f1768S = {41, 46, 67, -55, -94, -40, 124, 1, 61, 54, 84, -95, -20, -16, 6, 19, 98, -89, 5, -13, -64, -57, 115, -116, -104, -109, 43, -39, PSSSigner.TRAILER_IMPLICIT, 76, -126, -54, Ascii.f1127RS, -101, 87, 60, -3, -44, -32, Ascii.SYN, 103, 66, 111, Ascii.CAN, -118, Ascii.ETB, -27, Ascii.DC2, -66, 78, -60, -42, -38, -98, -34, 73, -96, -5, -11, -114, -69, 47, -18, 122, -87, 104, 121, -111, Ascii.NAK, -78, 7, Utf8.REPLACEMENT_BYTE, -108, -62, 16, -119, Ascii.f1132VT, 34, 95, 33, Byte.MIN_VALUE, Byte.MAX_VALUE, 93, -102, 90, -112, 50, 39, 53, 62, -52, -25, -65, -9, -105, 3, -1, Ascii.f1120EM, 48, -77, 72, -91, -75, -47, -41, 94, -110, 42, -84, 86, -86, -58, 79, -72, 56, -46, -106, -92, 125, -74, 118, -4, 107, -30, -100, 116, 4, -15, 69, -99, 112, 89, 100, 113, -121, 32, -122, 91, -49, 101, -26, MultipartStream.DASH, -88, 2, Ascii.ESC, 96, 37, -83, -82, -80, -71, -10, Ascii.f1122FS, 70, 97, 105, 52, SignedBytes.MAX_POWER_OF_TWO, 126, Ascii.f1128SI, 85, 71, -93, 35, -35, 81, -81, 58, -61, 92, -7, -50, -70, -59, -22, 38, 44, 83, 13, 110, -123, 40, -124, 9, -45, -33, -51, -12, 65, -127, 77, 82, 106, -36, 55, -56, 108, -63, -85, -6, 36, -31, 123, 8, Ascii.f1121FF, -67, -79, 74, 120, -120, -107, -117, -29, 99, -24, 109, -23, -53, -43, -2, 59, 0, Ascii.f1123GS, 57, -14, -17, -73, Ascii.f1129SO, 102, 88, -48, -28, -90, 119, 114, -8, -21, 117, 75, 10, 49, 68, 80, -76, -113, -19, Ascii.f1131US, Ascii.SUB, -37, -103, -115, 51, -97, 17, -125, Ascii.DC4};

    /* renamed from: C */
    private byte[] f1769C;
    private int COff;

    /* renamed from: M */
    private byte[] f1770M;

    /* renamed from: X */
    private byte[] f1771X;
    private int mOff;
    private int xOff;

    public MD2Digest() {
        this.f1771X = new byte[48];
        this.f1770M = new byte[16];
        this.f1769C = new byte[16];
        reset();
    }

    public MD2Digest(MD2Digest mD2Digest) {
        this.f1771X = new byte[48];
        this.f1770M = new byte[16];
        this.f1769C = new byte[16];
        copyIn(mD2Digest);
    }

    private void copyIn(MD2Digest mD2Digest) {
        byte[] bArr = mD2Digest.f1771X;
        System.arraycopy(bArr, 0, this.f1771X, 0, bArr.length);
        this.xOff = mD2Digest.xOff;
        byte[] bArr2 = mD2Digest.f1770M;
        System.arraycopy(bArr2, 0, this.f1770M, 0, bArr2.length);
        this.mOff = mD2Digest.mOff;
        byte[] bArr3 = mD2Digest.f1769C;
        System.arraycopy(bArr3, 0, this.f1769C, 0, bArr3.length);
        this.COff = mD2Digest.COff;
    }

    @Override // org.bouncycastle.util.Memoable
    public Memoable copy() {
        return new MD2Digest(this);
    }

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int r6) {
        int length = this.f1770M.length;
        int r1 = this.mOff;
        byte b = (byte) (length - r1);
        while (true) {
            byte[] bArr2 = this.f1770M;
            if (r1 >= bArr2.length) {
                processCheckSum(bArr2);
                processBlock(this.f1770M);
                processBlock(this.f1769C);
                System.arraycopy(this.f1771X, this.xOff, bArr, r6, 16);
                reset();
                return 16;
            }
            bArr2[r1] = b;
            r1++;
        }
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return MessageDigestAlgorithms.MD2;
    }

    @Override // org.bouncycastle.crypto.ExtendedDigest
    public int getByteLength() {
        return 16;
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return 16;
    }

    protected void processBlock(byte[] bArr) {
        for (int r1 = 0; r1 < 16; r1++) {
            byte[] bArr2 = this.f1771X;
            bArr2[r1 + 16] = bArr[r1];
            bArr2[r1 + 32] = (byte) (bArr[r1] ^ bArr2[r1]);
        }
        int r12 = 0;
        for (int r7 = 0; r7 < 18; r7++) {
            for (int r2 = 0; r2 < 48; r2++) {
                byte[] bArr3 = this.f1771X;
                byte b = (byte) (f1768S[r12] ^ bArr3[r2]);
                bArr3[r2] = b;
                r12 = b & 255;
            }
            r12 = (r12 + r7) % 256;
        }
    }

    protected void processCheckSum(byte[] bArr) {
        byte b = this.f1769C[15];
        for (int r1 = 0; r1 < 16; r1++) {
            byte[] bArr2 = this.f1769C;
            bArr2[r1] = (byte) (f1768S[(b ^ bArr[r1]) & 255] ^ bArr2[r1]);
            b = bArr2[r1];
        }
    }

    @Override // org.bouncycastle.crypto.Digest
    public void reset() {
        this.xOff = 0;
        int r1 = 0;
        while (true) {
            byte[] bArr = this.f1771X;
            if (r1 == bArr.length) {
                break;
            }
            bArr[r1] = 0;
            r1++;
        }
        this.mOff = 0;
        int r12 = 0;
        while (true) {
            byte[] bArr2 = this.f1770M;
            if (r12 == bArr2.length) {
                break;
            }
            bArr2[r12] = 0;
            r12++;
        }
        this.COff = 0;
        int r13 = 0;
        while (true) {
            byte[] bArr3 = this.f1769C;
            if (r13 == bArr3.length) {
                return;
            }
            bArr3[r13] = 0;
            r13++;
        }
    }

    @Override // org.bouncycastle.util.Memoable
    public void reset(Memoable memoable) {
        copyIn((MD2Digest) memoable);
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte b) {
        byte[] bArr = this.f1770M;
        int r1 = this.mOff;
        int r2 = r1 + 1;
        this.mOff = r2;
        bArr[r1] = b;
        if (r2 == 16) {
            processCheckSum(bArr);
            processBlock(this.f1770M);
            this.mOff = 0;
        }
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte[] bArr, int r5, int r6) {
        while (this.mOff != 0 && r6 > 0) {
            update(bArr[r5]);
            r5++;
            r6--;
        }
        while (r6 > 16) {
            System.arraycopy(bArr, r5, this.f1770M, 0, 16);
            processCheckSum(this.f1770M);
            processBlock(this.f1770M);
            r6 -= 16;
            r5 += 16;
        }
        while (r6 > 0) {
            update(bArr[r5]);
            r5++;
            r6--;
        }
    }
}
