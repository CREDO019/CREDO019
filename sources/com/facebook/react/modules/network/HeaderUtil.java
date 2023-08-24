package com.facebook.react.modules.network;

/* loaded from: classes.dex */
public class HeaderUtil {
    public static String stripHeaderName(String str) {
        StringBuilder sb = new StringBuilder(str.length());
        int length = str.length();
        boolean z = false;
        for (int r2 = 0; r2 < length; r2++) {
            char charAt = str.charAt(r2);
            if (charAt <= ' ' || charAt >= 127) {
                z = true;
            } else {
                sb.append(charAt);
            }
        }
        return z ? sb.toString() : str;
    }

    public static String stripHeaderValue(String str) {
        StringBuilder sb = new StringBuilder(str.length());
        int length = str.length();
        boolean z = false;
        for (int r2 = 0; r2 < length; r2++) {
            char charAt = str.charAt(r2);
            if ((charAt <= 31 || charAt >= 127) && charAt != '\t') {
                z = true;
            } else {
                sb.append(charAt);
            }
        }
        return z ? sb.toString() : str;
    }
}
