package com.google.android.gms.common.util;

import android.text.TextUtils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: com.google.android.gms:play-services-basement@@18.1.0 */
/* loaded from: classes2.dex */
final class zzc {
    private static final Pattern zza = Pattern.compile("\\\\u[0-9a-fA-F]{4}");

    public static String zza(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        Matcher matcher = zza.matcher(str);
        int r1 = 0;
        StringBuilder sb = null;
        while (matcher.find()) {
            if (sb == null) {
                sb = new StringBuilder();
            }
            int start = matcher.start();
            int r4 = start;
            while (r4 >= 0 && str.charAt(r4) == '\\') {
                r4--;
            }
            if ((start - r4) % 2 != 0) {
                int parseInt = Integer.parseInt(matcher.group().substring(2), 16);
                sb.append((CharSequence) str, r1, matcher.start());
                if (parseInt == 92) {
                    sb.append("\\\\");
                } else {
                    sb.append(Character.toChars(parseInt));
                }
                r1 = matcher.end();
            }
        }
        if (sb == null) {
            return str;
        }
        if (r1 < matcher.regionEnd()) {
            sb.append((CharSequence) str, r1, matcher.regionEnd());
        }
        return sb.toString();
    }
}
