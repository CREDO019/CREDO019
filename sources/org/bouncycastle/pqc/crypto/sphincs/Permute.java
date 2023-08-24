package org.bouncycastle.pqc.crypto.sphincs;

import org.bouncycastle.util.Pack;

/* loaded from: classes3.dex */
class Permute {
    private static final int CHACHA_ROUNDS = 12;

    public static void permute(int r32, int[] r33) {
        int r2 = 16;
        if (r33.length != 16) {
            throw new IllegalArgumentException();
        }
        if (r32 % 2 != 0) {
            throw new IllegalArgumentException("Number of rounds must be even");
        }
        char c = 0;
        int r3 = r33[0];
        int r5 = r33[1];
        int r7 = r33[2];
        int r9 = r33[3];
        int r11 = r33[4];
        int r13 = r33[5];
        int r15 = r33[6];
        int r14 = 7;
        int r16 = r33[7];
        int r12 = 8;
        int r17 = r33[8];
        int r19 = r33[9];
        int r21 = r33[10];
        int r23 = r33[11];
        int r24 = r33[12];
        int r26 = r33[13];
        int r28 = r33[14];
        int r31 = r33[15];
        int r30 = r28;
        int r282 = r26;
        int r262 = r24;
        int r242 = r23;
        int r232 = r21;
        int r212 = r19;
        int r192 = r17;
        int r172 = r16;
        int r162 = r15;
        int r152 = r13;
        int r132 = r11;
        int r112 = r9;
        int r92 = r7;
        int r72 = r5;
        int r52 = r3;
        int r34 = r32;
        while (r34 > 0) {
            int r53 = r52 + r132;
            int rotl = rotl(r262 ^ r53, r2);
            int r193 = r192 + rotl;
            int rotl2 = rotl(r132 ^ r193, 12);
            int r54 = r53 + rotl2;
            int rotl3 = rotl(rotl ^ r54, r12);
            int r194 = r193 + rotl3;
            int rotl4 = rotl(rotl2 ^ r194, r14);
            int r73 = r72 + r152;
            int rotl5 = rotl(r282 ^ r73, r2);
            int r213 = r212 + rotl5;
            int rotl6 = rotl(r152 ^ r213, 12);
            int r74 = r73 + rotl6;
            int rotl7 = rotl(rotl5 ^ r74, r12);
            int r214 = r213 + rotl7;
            int rotl8 = rotl(rotl6 ^ r214, r14);
            int r93 = r92 + r162;
            int rotl9 = rotl(r30 ^ r93, r2);
            int r233 = r232 + rotl9;
            int rotl10 = rotl(r162 ^ r233, 12);
            int r94 = r93 + rotl10;
            int rotl11 = rotl(rotl9 ^ r94, r12);
            int r234 = r233 + rotl11;
            int rotl12 = rotl(rotl10 ^ r234, r14);
            int r113 = r112 + r172;
            int rotl13 = rotl(r31 ^ r113, r2);
            int r243 = r242 + rotl13;
            int rotl14 = rotl(r172 ^ r243, 12);
            int r114 = r113 + rotl14;
            int rotl15 = rotl(rotl13 ^ r114, r12);
            int r244 = r243 + rotl15;
            int rotl16 = rotl(rotl14 ^ r244, 7);
            int r55 = r54 + rotl8;
            int rotl17 = rotl(rotl15 ^ r55, 16);
            int r235 = r234 + rotl17;
            int rotl18 = rotl(rotl8 ^ r235, 12);
            r52 = r55 + rotl18;
            r31 = rotl(rotl17 ^ r52, 8);
            r232 = r235 + r31;
            r152 = rotl(rotl18 ^ r232, 7);
            int r75 = r74 + rotl12;
            int rotl19 = rotl(rotl3 ^ r75, 16);
            int r245 = r244 + rotl19;
            int rotl20 = rotl(rotl12 ^ r245, 12);
            r72 = r75 + rotl20;
            r262 = rotl(rotl19 ^ r72, 8);
            r242 = r245 + r262;
            r162 = rotl(rotl20 ^ r242, 7);
            int r95 = r94 + rotl16;
            int rotl21 = rotl(rotl7 ^ r95, 16);
            int r195 = r194 + rotl21;
            int rotl22 = rotl(rotl16 ^ r195, 12);
            r92 = r95 + rotl22;
            r282 = rotl(rotl21 ^ r92, 8);
            r192 = r195 + r282;
            r172 = rotl(rotl22 ^ r192, 7);
            int r115 = r114 + rotl4;
            r2 = 16;
            int rotl23 = rotl(rotl11 ^ r115, 16);
            int r215 = r214 + rotl23;
            int rotl24 = rotl(rotl4 ^ r215, 12);
            r112 = r115 + rotl24;
            r30 = rotl(rotl23 ^ r112, 8);
            r212 = r215 + r30;
            r132 = rotl(rotl24 ^ r212, 7);
            r34 -= 2;
            c = 0;
            r12 = 8;
            r14 = 7;
        }
        r33[c] = r52;
        r33[1] = r72;
        r33[2] = r92;
        r33[3] = r112;
        r33[4] = r132;
        r33[5] = r152;
        r33[6] = r162;
        r33[7] = r172;
        r33[8] = r192;
        r33[9] = r212;
        r33[10] = r232;
        r33[11] = r242;
        r33[12] = r262;
        r33[13] = r282;
        r33[14] = r30;
        r33[15] = r31;
    }

    protected static int rotl(int r1, int r2) {
        return (r1 >>> (-r2)) | (r1 << r2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void chacha_permute(byte[] bArr, byte[] bArr2) {
        int[] r1 = new int[16];
        for (int r3 = 0; r3 < 16; r3++) {
            r1[r3] = Pack.littleEndianToInt(bArr2, r3 * 4);
        }
        permute(12, r1);
        for (int r2 = 0; r2 < 16; r2++) {
            Pack.intToLittleEndian(r1[r2], bArr, r2 * 4);
        }
    }
}
