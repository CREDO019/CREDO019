package org.apache.commons.lang3.text;

import java.util.Formattable;
import java.util.Formatter;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.Validate;

@Deprecated
/* loaded from: classes5.dex */
public class FormattableUtils {
    private static final String SIMPLEST_FORMAT = "%s";

    public static String toString(Formattable formattable) {
        return String.format(SIMPLEST_FORMAT, formattable);
    }

    public static Formatter append(CharSequence charSequence, Formatter formatter, int r9, int r10, int r11) {
        return append(charSequence, formatter, r9, r10, r11, ' ', null);
    }

    public static Formatter append(CharSequence charSequence, Formatter formatter, int r9, int r10, int r11, char c) {
        return append(charSequence, formatter, r9, r10, r11, c, null);
    }

    public static Formatter append(CharSequence charSequence, Formatter formatter, int r9, int r10, int r11, CharSequence charSequence2) {
        return append(charSequence, formatter, r9, r10, r11, ' ', charSequence2);
    }

    public static Formatter append(CharSequence charSequence, Formatter formatter, int r7, int r8, int r9, char c, CharSequence charSequence2) {
        Validate.isTrue(charSequence2 == null || r9 < 0 || charSequence2.length() <= r9, "Specified ellipsis '%1$s' exceeds precision of %2$s", charSequence2, Integer.valueOf(r9));
        StringBuilder sb = new StringBuilder(charSequence);
        if (r9 >= 0 && r9 < charSequence.length()) {
            CharSequence charSequence3 = (CharSequence) ObjectUtils.defaultIfNull(charSequence2, "");
            sb.replace(r9 - charSequence3.length(), charSequence.length(), charSequence3.toString());
        }
        boolean z = (r7 & 1) == 1;
        for (int length = sb.length(); length < r8; length++) {
            sb.insert(z ? length : 0, c);
        }
        formatter.format(sb.toString(), new Object[0]);
        return formatter;
    }
}
