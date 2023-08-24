package org.bouncycastle.pqc.crypto.newhope;

import org.bouncycastle.util.Arrays;

/* loaded from: classes3.dex */
class ErrorCorrection {
    ErrorCorrection() {
    }

    static short LDDecode(int r0, int r1, int r2, int r3) {
        return (short) (((((m16g(r0) + m16g(r1)) + m16g(r2)) + m16g(r3)) - 98312) >>> 31);
    }

    static int abs(int r1) {
        int r0 = r1 >> 31;
        return (r1 ^ r0) - r0;
    }

    /* renamed from: f */
    static int m17f(int[] r3, int r4, int r5, int r6) {
        int r0 = (r6 * 2730) >> 25;
        int r02 = r0 - ((12288 - (r6 - (r0 * 12289))) >> 31);
        r3[r4] = (r02 >> 1) + (r02 & 1);
        int r03 = r02 - 1;
        r3[r5] = (r03 >> 1) + (r03 & 1);
        return abs(r6 - ((r3[r4] * 2) * 12289));
    }

    /* renamed from: g */
    static int m16g(int r3) {
        int r0 = (r3 * 2730) >> 27;
        int r02 = r0 - ((49155 - (r3 - (49156 * r0))) >> 31);
        return abs((((r02 >> 1) + (r02 & 1)) * 98312) - r3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void helpRec(short[] sArr, short[] sArr2, byte[] bArr, byte b) {
        byte[] bArr2 = new byte[8];
        bArr2[0] = b;
        byte[] bArr3 = new byte[32];
        ChaCha20.process(bArr, bArr2, bArr3, 0, 32);
        int[] r1 = new int[8];
        int[] r5 = new int[4];
        for (int r6 = 0; r6 < 256; r6++) {
            int r9 = r6 + 0;
            int r7 = ((bArr3[r6 >>> 3] >>> (r6 & 7)) & 1) * 4;
            int r11 = r6 + 256;
            int r12 = r6 + 512;
            int r14 = r6 + 768;
            int m17f = (24577 - (((m17f(r1, 0, 4, (sArr2[r9] * 8) + r7) + m17f(r1, 1, 5, (sArr2[r11] * 8) + r7)) + m17f(r1, 2, 6, (sArr2[r12] * 8) + r7)) + m17f(r1, 3, 7, (sArr2[r14] * 8) + r7))) >> 31;
            int r10 = ~m17f;
            r5[0] = (r10 & r1[0]) ^ (m17f & r1[4]);
            r5[1] = (r10 & r1[1]) ^ (m17f & r1[5]);
            r5[2] = (r10 & r1[2]) ^ (m17f & r1[6]);
            r5[3] = (r10 & r1[3]) ^ (r1[7] & m17f);
            sArr[r9] = (short) ((r5[0] - r5[3]) & 3);
            sArr[r11] = (short) ((r5[1] - r5[3]) & 3);
            sArr[r12] = (short) ((r5[2] - r5[3]) & 3);
            sArr[r14] = (short) (3 & ((-m17f) + (r5[3] * 2)));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void rec(byte[] bArr, short[] sArr, short[] sArr2) {
        Arrays.fill(bArr, (byte) 0);
        int[] r1 = new int[4];
        for (int r2 = 0; r2 < 256; r2++) {
            int r3 = r2 + 0;
            int r7 = r2 + 768;
            r1[0] = ((sArr[r3] * 8) + 196624) - (((sArr2[r3] * 2) + sArr2[r7]) * 12289);
            int r32 = r2 + 256;
            r1[1] = ((sArr[r32] * 8) + 196624) - (((sArr2[r32] * 2) + sArr2[r7]) * 12289);
            int r4 = r2 + 512;
            r1[2] = ((sArr[r4] * 8) + 196624) - (((sArr2[r4] * 2) + sArr2[r7]) * 12289);
            r1[3] = ((sArr[r7] * 8) + 196624) - (sArr2[r7] * 12289);
            int r42 = r2 >>> 3;
            bArr[r42] = (byte) ((LDDecode(r1[0], r1[1], r1[2], r1[3]) << (r2 & 7)) | bArr[r42]);
        }
    }
}
