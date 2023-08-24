package com.google.android.datatransport.cct;

/* loaded from: classes2.dex */
public final class StringMerger {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static String mergeStrings(String str, String str2) {
        int length = str.length() - str2.length();
        if (length < 0 || length > 1) {
            throw new IllegalArgumentException("Invalid input received");
        }
        StringBuilder sb = new StringBuilder(str.length() + str2.length());
        for (int r1 = 0; r1 < str.length(); r1++) {
            sb.append(str.charAt(r1));
            if (str2.length() > r1) {
                sb.append(str2.charAt(r1));
            }
        }
        return sb.toString();
    }
}
