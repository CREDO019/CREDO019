package org.apache.commons.codec.binary;

/* loaded from: classes5.dex */
public class CharSequenceUtils {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean regionMatches(CharSequence charSequence, boolean z, int r9, CharSequence charSequence2, int r11, int r12) {
        if ((charSequence instanceof String) && (charSequence2 instanceof String)) {
            return ((String) charSequence).regionMatches(z, r9, (String) charSequence2, r11, r12);
        }
        while (true) {
            int r0 = r12 - 1;
            if (r12 <= 0) {
                return true;
            }
            int r122 = r9 + 1;
            char charAt = charSequence.charAt(r9);
            int r1 = r11 + 1;
            char charAt2 = charSequence2.charAt(r11);
            if (charAt != charAt2) {
                if (!z) {
                    return false;
                }
                if (Character.toUpperCase(charAt) != Character.toUpperCase(charAt2) && Character.toLowerCase(charAt) != Character.toLowerCase(charAt2)) {
                    return false;
                }
            }
            r9 = r122;
            r12 = r0;
            r11 = r1;
        }
    }
}
