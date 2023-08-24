package org.bouncycastle.pqc.crypto.sphincs;

import android.support.p001v4.media.session.PlaybackStateCompat;
import com.facebook.device.yearclass.YearClass;

/* loaded from: classes3.dex */
class Horst {
    static final int HORST_K = 32;
    static final int HORST_LOGT = 16;
    static final int HORST_SIGBYTES = 13312;
    static final int HORST_SKBYTES = 32;
    static final int HORST_T = 65536;
    static final int N_MASKS = 32;

    static void expand_seed(byte[] bArr, byte[] bArr2) {
        Seed.prg(bArr, 0, PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE, bArr2, 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int horst_sign(HashFunctions hashFunctions, byte[] bArr, int r23, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5) {
        byte[] bArr6 = new byte[2097152];
        byte[] bArr7 = new byte[4194272];
        expand_seed(bArr6, bArr3);
        for (int r2 = 0; r2 < 65536; r2++) {
            hashFunctions.hash_n_n(bArr7, (65535 + r2) * 32, bArr6, r2 * 32);
        }
        for (int r13 = 0; r13 < 16; r13++) {
            int r22 = 16 - r13;
            long j = (1 << r22) - 1;
            int r8 = 1 << (r22 - 1);
            long j2 = r8 - 1;
            int r5 = 0;
            while (r5 < r8) {
                hashFunctions.hash_2n_n_mask(bArr7, (int) ((r5 + j2) * 32), bArr7, (int) (((r5 * 2) + j) * 32), bArr4, r13 * 2 * 32);
                r5++;
                r8 = r8;
                j2 = j2;
            }
        }
        int r24 = r23;
        int r4 = YearClass.CLASS_2016;
        while (r4 < 4064) {
            bArr[r24] = bArr7[r4];
            r4++;
            r24++;
        }
        for (int r42 = 0; r42 < 32; r42++) {
            int r52 = r42 * 2;
            int r6 = (bArr5[r52] & 255) + ((bArr5[r52 + 1] & 255) << 8);
            int r53 = 0;
            while (r53 < 32) {
                bArr[r24] = bArr6[(r6 * 32) + r53];
                r53++;
                r24++;
            }
            int r62 = r6 + 65535;
            for (int r54 = 0; r54 < 10; r54++) {
                int r63 = (r62 & 1) != 0 ? r62 + 1 : r62 - 1;
                int r7 = 0;
                while (r7 < 32) {
                    bArr[r24] = bArr7[(r63 * 32) + r7];
                    r7++;
                    r24++;
                }
                r62 = (r63 - 1) / 2;
            }
        }
        for (int r9 = 0; r9 < 32; r9++) {
            bArr2[r9] = bArr7[r9];
        }
        return HORST_SIGBYTES;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int horst_verify(HashFunctions hashFunctions, byte[] bArr, byte[] bArr2, int r22, byte[] bArr3, byte[] bArr4) {
        int r18;
        byte[] bArr5 = new byte[1024];
        int r0 = r22 + 2048;
        int r12 = 0;
        while (r12 < 32) {
            int r1 = r12 * 2;
            int r2 = (bArr4[r1] & 255) + ((bArr4[r1 + 1] & 255) << 8);
            if ((r2 & 1) == 0) {
                hashFunctions.hash_n_n(bArr5, 0, bArr2, r0);
                for (int r13 = 0; r13 < 32; r13++) {
                    bArr5[r13 + 32] = bArr2[r0 + 32 + r13];
                }
            } else {
                hashFunctions.hash_n_n(bArr5, 32, bArr2, r0);
                for (int r14 = 0; r14 < 32; r14++) {
                    bArr5[r14] = bArr2[r0 + 32 + r14];
                }
            }
            int r132 = r0 + 64;
            int r6 = 1;
            while (r6 < 10) {
                int r16 = r2 >>> 1;
                if ((r16 & 1) == 0) {
                    r18 = r6;
                    hashFunctions.hash_2n_n_mask(bArr5, 0, bArr5, 0, bArr3, (r6 - 1) * 2 * 32);
                    for (int r02 = 0; r02 < 32; r02++) {
                        bArr5[r02 + 32] = bArr2[r132 + r02];
                    }
                } else {
                    r18 = r6;
                    hashFunctions.hash_2n_n_mask(bArr5, 32, bArr5, 0, bArr3, (r18 - 1) * 2 * 32);
                    for (int r03 = 0; r03 < 32; r03++) {
                        bArr5[r03] = bArr2[r132 + r03];
                    }
                }
                r132 += 32;
                r6 = r18 + 1;
                r2 = r16;
            }
            int r142 = r2 >>> 1;
            hashFunctions.hash_2n_n_mask(bArr5, 0, bArr5, 0, bArr3, 576);
            for (int r04 = 0; r04 < 32; r04++) {
                if (bArr2[(r142 * 32) + r22 + r04] != bArr5[r04]) {
                    for (int r05 = 0; r05 < 32; r05++) {
                        bArr[r05] = 0;
                    }
                    return -1;
                }
            }
            r12++;
            r0 = r132;
        }
        for (int r122 = 0; r122 < 32; r122++) {
            hashFunctions.hash_2n_n_mask(bArr5, r122 * 32, bArr2, r22 + (r122 * 2 * 32), bArr3, 640);
        }
        for (int r8 = 0; r8 < 16; r8++) {
            hashFunctions.hash_2n_n_mask(bArr5, r8 * 32, bArr5, r8 * 2 * 32, bArr3, 704);
        }
        for (int r82 = 0; r82 < 8; r82++) {
            hashFunctions.hash_2n_n_mask(bArr5, r82 * 32, bArr5, r82 * 2 * 32, bArr3, 768);
        }
        for (int r83 = 0; r83 < 4; r83++) {
            hashFunctions.hash_2n_n_mask(bArr5, r83 * 32, bArr5, r83 * 2 * 32, bArr3, 832);
        }
        for (int r84 = 0; r84 < 2; r84++) {
            hashFunctions.hash_2n_n_mask(bArr5, r84 * 32, bArr5, r84 * 2 * 32, bArr3, 896);
        }
        hashFunctions.hash_2n_n_mask(bArr, 0, bArr5, 0, bArr3, 960);
        return 0;
    }
}
