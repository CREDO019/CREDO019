package org.apache.commons.codec.language;

import java.util.Locale;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;

/* loaded from: classes5.dex */
final class SoundexUtils {
    SoundexUtils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String clean(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        int length = str.length();
        char[] cArr = new char[length];
        int r4 = 0;
        for (int r3 = 0; r3 < length; r3++) {
            if (Character.isLetter(str.charAt(r3))) {
                cArr[r4] = str.charAt(r3);
                r4++;
            }
        }
        if (r4 == length) {
            return str.toUpperCase(Locale.ENGLISH);
        }
        return new String(cArr, 0, r4).toUpperCase(Locale.ENGLISH);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int difference(StringEncoder stringEncoder, String str, String str2) throws EncoderException {
        return differenceEncoded(stringEncoder.encode(str), stringEncoder.encode(str2));
    }

    static int differenceEncoded(String str, String str2) {
        if (str == null || str2 == null) {
            return 0;
        }
        int min = Math.min(str.length(), str2.length());
        int r2 = 0;
        for (int r0 = 0; r0 < min; r0++) {
            if (str.charAt(r0) == str2.charAt(r0)) {
                r2++;
            }
        }
        return r2;
    }
}
