package org.bouncycastle.pqc.crypto.newhope;

import kotlin.UShort;
import org.bouncycastle.crypto.digests.SHAKEDigest;
import org.bouncycastle.util.Pack;

/* loaded from: classes3.dex */
class Poly {
    Poly() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void add(short[] sArr, short[] sArr2, short[] sArr3) {
        for (int r0 = 0; r0 < 1024; r0++) {
            sArr3[r0] = Reduce.barrett((short) (sArr[r0] + sArr2[r0]));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void fromBytes(short[] sArr, byte[] bArr) {
        for (int r0 = 0; r0 < 256; r0++) {
            int r1 = r0 * 7;
            int r3 = bArr[r1 + 1] & 255;
            int r5 = bArr[r1 + 3] & 255;
            int r7 = bArr[r1 + 5] & 255;
            int r8 = r0 * 4;
            sArr[r8 + 0] = (short) ((bArr[r1 + 0] & 255) | ((r3 & 63) << 8));
            sArr[r8 + 1] = (short) ((r3 >>> 6) | ((bArr[r1 + 2] & 255) << 2) | ((r5 & 15) << 10));
            sArr[r8 + 2] = (short) ((r5 >>> 4) | ((bArr[r1 + 4] & 255) << 4) | ((r7 & 3) << 12));
            sArr[r8 + 3] = (short) (((bArr[r1 + 6] & 255) << 6) | (r7 >>> 2));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void fromNTT(short[] sArr) {
        NTT.bitReverse(sArr);
        NTT.core(sArr, Precomp.OMEGAS_INV_MONTGOMERY);
        NTT.mulCoefficients(sArr, Precomp.PSIS_INV_MONTGOMERY);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void getNoise(short[] sArr, byte[] bArr, byte b) {
        byte[] bArr2 = new byte[8];
        bArr2[0] = b;
        byte[] bArr3 = new byte[4096];
        ChaCha20.process(bArr, bArr2, bArr3, 0, 4096);
        for (int r8 = 0; r8 < 1024; r8++) {
            int bigEndianToInt = Pack.bigEndianToInt(bArr3, r8 * 4);
            int r4 = 0;
            for (int r1 = 0; r1 < 8; r1++) {
                r4 += (bigEndianToInt >> r1) & 16843009;
            }
            sArr[r8] = (short) (((((r4 >>> 24) + (r4 >>> 0)) & 255) + 12289) - (((r4 >>> 16) + (r4 >>> 8)) & 255));
        }
    }

    private static short normalize(short s) {
        short barrett = Reduce.barrett(s);
        int r0 = barrett - 12289;
        return (short) (((barrett ^ r0) & (r0 >> 31)) ^ r0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void pointWise(short[] sArr, short[] sArr2, short[] sArr3) {
        for (int r0 = 0; r0 < 1024; r0++) {
            sArr3[r0] = Reduce.montgomery((sArr[r0] & UShort.MAX_VALUE) * (65535 & Reduce.montgomery((sArr2[r0] & UShort.MAX_VALUE) * 3186)));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void toBytes(byte[] bArr, short[] sArr) {
        for (int r0 = 0; r0 < 256; r0++) {
            int r1 = r0 * 4;
            short normalize = normalize(sArr[r1 + 0]);
            short normalize2 = normalize(sArr[r1 + 1]);
            short normalize3 = normalize(sArr[r1 + 2]);
            short normalize4 = normalize(sArr[r1 + 3]);
            int r5 = r0 * 7;
            bArr[r5 + 0] = (byte) normalize;
            bArr[r5 + 1] = (byte) ((normalize >> 8) | (normalize2 << 6));
            bArr[r5 + 2] = (byte) (normalize2 >> 2);
            bArr[r5 + 3] = (byte) ((normalize2 >> 10) | (normalize3 << 4));
            bArr[r5 + 4] = (byte) (normalize3 >> 4);
            bArr[r5 + 5] = (byte) ((normalize3 >> 12) | (normalize4 << 2));
            bArr[r5 + 6] = (byte) (normalize4 >> 6);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void toNTT(short[] sArr) {
        NTT.mulCoefficients(sArr, Precomp.PSIS_BITREV_MONTGOMERY);
        NTT.core(sArr, Precomp.OMEGAS_MONTGOMERY);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void uniform(short[] sArr, byte[] bArr) {
        SHAKEDigest sHAKEDigest = new SHAKEDigest(128);
        sHAKEDigest.update(bArr, 0, bArr.length);
        int r8 = 0;
        while (true) {
            byte[] bArr2 = new byte[256];
            sHAKEDigest.doOutput(bArr2, 0, 256);
            for (int r4 = 0; r4 < 256; r4 += 2) {
                int r5 = (bArr2[r4] & 255) | ((bArr2[r4 + 1] & 255) << 8);
                if (r5 < 61445) {
                    int r6 = r8 + 1;
                    sArr[r8] = (short) r5;
                    if (r6 == 1024) {
                        return;
                    }
                    r8 = r6;
                }
            }
        }
    }
}
