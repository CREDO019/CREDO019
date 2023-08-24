package com.google.android.gms.common.util;

/* compiled from: com.google.android.gms:play-services-basement@@18.1.0 */
/* loaded from: classes2.dex */
public final class HexDumpUtils {
    public static String dump(byte[] bArr, int r11, int r12, boolean z) {
        int length;
        if (bArr == null || (length = bArr.length) == 0 || r11 < 0 || r12 <= 0 || r11 + r12 > length) {
            return null;
        }
        StringBuilder sb = new StringBuilder((z ? 75 : 57) * ((r12 + 15) / 16));
        int r2 = r12;
        int r4 = 0;
        int r5 = 0;
        while (r2 > 0) {
            if (r4 == 0) {
                if (r12 < 65536) {
                    sb.append(String.format("%04X:", Integer.valueOf(r11)));
                } else {
                    sb.append(String.format("%08X:", Integer.valueOf(r11)));
                }
                r5 = r11;
            } else if (r4 == 8) {
                sb.append(" -");
            }
            sb.append(String.format(" %02X", Integer.valueOf(bArr[r11] & 255)));
            r2--;
            r4++;
            if (z && (r4 == 16 || r2 == 0)) {
                int r7 = 16 - r4;
                if (r7 > 0) {
                    for (int r8 = 0; r8 < r7; r8++) {
                        sb.append("   ");
                    }
                }
                if (r7 >= 8) {
                    sb.append("  ");
                }
                sb.append("  ");
                for (int r6 = 0; r6 < r4; r6++) {
                    char c = (char) bArr[r5 + r6];
                    if (c < ' ' || c > '~') {
                        c = '.';
                    }
                    sb.append(c);
                }
            }
            if (r4 == 16 || r2 == 0) {
                sb.append('\n');
                r4 = 0;
            }
            r11++;
        }
        return sb.toString();
    }
}
