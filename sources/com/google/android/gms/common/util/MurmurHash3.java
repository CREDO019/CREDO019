package com.google.android.gms.common.util;

/* compiled from: com.google.android.gms:play-services-basement@@18.1.0 */
/* loaded from: classes2.dex */
public class MurmurHash3 {
    private MurmurHash3() {
    }

    public static int murmurhash3_x86_32(byte[] bArr, int r6, int r7, int r8) {
        int r0 = (r7 & (-4)) + r6;
        while (r6 < r0) {
            int r3 = ((bArr[r6] & 255) | ((bArr[r6 + 1] & 255) << 8) | ((bArr[r6 + 2] & 255) << 16) | (bArr[r6 + 3] << 24)) * (-862048943);
            int r82 = r8 ^ (((r3 << 15) | (r3 >>> 17)) * 461845907);
            r8 = (((r82 >>> 19) | (r82 << 13)) * 5) - 430675100;
            r6 += 4;
        }
        int r62 = r7 & 3;
        if (r62 != 1) {
            if (r62 != 2) {
                r3 = r62 == 3 ? (bArr[r0 + 2] & 255) << 16 : 0;
                int r5 = r8 ^ r7;
                int r52 = (r5 ^ (r5 >>> 16)) * (-2048144789);
                int r53 = (r52 ^ (r52 >>> 13)) * (-1028477387);
                return r53 ^ (r53 >>> 16);
            }
            r3 |= (bArr[r0 + 1] & 255) << 8;
        }
        int r54 = ((bArr[r0] & 255) | r3) * (-862048943);
        r8 ^= ((r54 >>> 17) | (r54 << 15)) * 461845907;
        int r55 = r8 ^ r7;
        int r522 = (r55 ^ (r55 >>> 16)) * (-2048144789);
        int r532 = (r522 ^ (r522 >>> 13)) * (-1028477387);
        return r532 ^ (r532 >>> 16);
    }
}
