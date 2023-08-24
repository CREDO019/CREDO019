package org.apache.commons.codec.digest;

import androidx.core.view.ViewCompat;
import java.util.Random;

/* loaded from: classes5.dex */
class B64 {
    static final String B64T = "./0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    B64() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void b64from24bit(byte b, byte b2, byte b3, int r4, StringBuilder sb) {
        int r1 = ((b << 16) & ViewCompat.MEASURED_SIZE_MASK) | ((b2 << 8) & 65535) | (b3 & 255);
        while (true) {
            int r2 = r4 - 1;
            if (r4 <= 0) {
                return;
            }
            sb.append(B64T.charAt(r1 & 63));
            r1 >>= 6;
            r4 = r2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getRandomSalt(int r4) {
        StringBuilder sb = new StringBuilder();
        for (int r1 = 1; r1 <= r4; r1++) {
            sb.append(B64T.charAt(new Random().nextInt(64)));
        }
        return sb.toString();
    }
}
