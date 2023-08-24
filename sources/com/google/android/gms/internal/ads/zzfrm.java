package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfrm {
    public static String zza(String str) {
        int length = str.length();
        int r1 = 0;
        while (r1 < length) {
            if (zze(str.charAt(r1))) {
                char[] charArray = str.toCharArray();
                while (r1 < length) {
                    char c = charArray[r1];
                    if (zze(c)) {
                        charArray[r1] = (char) (c ^ ' ');
                    }
                    r1++;
                }
                return String.valueOf(charArray);
            }
            r1++;
        }
        return str;
    }

    public static String zzb(String str) {
        int length = str.length();
        int r1 = 0;
        while (r1 < length) {
            if (zzd(str.charAt(r1))) {
                char[] charArray = str.toCharArray();
                while (r1 < length) {
                    char c = charArray[r1];
                    if (zzd(c)) {
                        charArray[r1] = (char) (c ^ ' ');
                    }
                    r1++;
                }
                return String.valueOf(charArray);
            }
            r1++;
        }
        return str;
    }

    public static boolean zzc(CharSequence charSequence, CharSequence charSequence2) {
        int zzf;
        int length = charSequence.length();
        if (charSequence == charSequence2) {
            return true;
        }
        if (length == charSequence2.length()) {
            for (int r2 = 0; r2 < length; r2++) {
                char charAt = charSequence.charAt(r2);
                char charAt2 = charSequence2.charAt(r2);
                if (charAt != charAt2 && ((zzf = zzf(charAt)) >= 26 || zzf != zzf(charAt2))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static boolean zzd(char c) {
        return c >= 'a' && c <= 'z';
    }

    public static boolean zze(char c) {
        return c >= 'A' && c <= 'Z';
    }

    private static int zzf(char c) {
        return (char) ((c | ' ') - 97);
    }
}
