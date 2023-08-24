package com.google.android.exoplayer2.util;

import android.net.Uri;
import android.text.TextUtils;
import org.apache.commons.p028io.IOUtils;

/* loaded from: classes2.dex */
public final class UriUtil {
    private static final int FRAGMENT = 3;
    private static final int INDEX_COUNT = 4;
    private static final int PATH = 1;
    private static final int QUERY = 2;
    private static final int SCHEME_COLON = 0;

    private UriUtil() {
    }

    public static Uri resolveToUri(String str, String str2) {
        return Uri.parse(resolve(str, str2));
    }

    public static String resolve(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "";
        }
        int[] uriIndices = getUriIndices(str2);
        if (uriIndices[0] != -1) {
            sb.append(str2);
            removeDotSegments(sb, uriIndices[1], uriIndices[2]);
            return sb.toString();
        }
        int[] uriIndices2 = getUriIndices(str);
        if (uriIndices[3] == 0) {
            sb.append((CharSequence) str, 0, uriIndices2[3]);
            sb.append(str2);
            return sb.toString();
        } else if (uriIndices[2] == 0) {
            sb.append((CharSequence) str, 0, uriIndices2[2]);
            sb.append(str2);
            return sb.toString();
        } else if (uriIndices[1] != 0) {
            int r3 = uriIndices2[0] + 1;
            sb.append((CharSequence) str, 0, r3);
            sb.append(str2);
            return removeDotSegments(sb, uriIndices[1] + r3, r3 + uriIndices[2]);
        } else if (str2.charAt(uriIndices[1]) == '/') {
            sb.append((CharSequence) str, 0, uriIndices2[1]);
            sb.append(str2);
            return removeDotSegments(sb, uriIndices2[1], uriIndices2[1] + uriIndices[2]);
        } else if (uriIndices2[0] + 2 < uriIndices2[1] && uriIndices2[1] == uriIndices2[2]) {
            sb.append((CharSequence) str, 0, uriIndices2[1]);
            sb.append(IOUtils.DIR_SEPARATOR_UNIX);
            sb.append(str2);
            return removeDotSegments(sb, uriIndices2[1], uriIndices2[1] + uriIndices[2] + 1);
        } else {
            int lastIndexOf = str.lastIndexOf(47, uriIndices2[2] - 1);
            int r4 = lastIndexOf == -1 ? uriIndices2[1] : lastIndexOf + 1;
            sb.append((CharSequence) str, 0, r4);
            sb.append(str2);
            return removeDotSegments(sb, uriIndices2[1], r4 + uriIndices[2]);
        }
    }

    public static boolean isAbsolute(String str) {
        return (str == null || getUriIndices(str)[0] == -1) ? false : true;
    }

    public static Uri removeQueryParameter(Uri uri, String str) {
        Uri.Builder buildUpon = uri.buildUpon();
        buildUpon.clearQuery();
        for (String str2 : uri.getQueryParameterNames()) {
            if (!str2.equals(str)) {
                for (String str3 : uri.getQueryParameters(str2)) {
                    buildUpon.appendQueryParameter(str2, str3);
                }
            }
        }
        return buildUpon.build();
    }

    private static String removeDotSegments(StringBuilder sb, int r8, int r9) {
        int r3;
        int r0;
        if (r8 >= r9) {
            return sb.toString();
        }
        if (sb.charAt(r8) == '/') {
            r8++;
        }
        int r02 = r8;
        int r2 = r02;
        while (r02 <= r9) {
            if (r02 == r9) {
                r3 = r02;
            } else if (sb.charAt(r02) == '/') {
                r3 = r02 + 1;
            } else {
                r02++;
            }
            int r4 = r2 + 1;
            if (r02 == r4 && sb.charAt(r2) == '.') {
                sb.delete(r2, r3);
                r9 -= r3 - r2;
            } else {
                if (r02 == r2 + 2 && sb.charAt(r2) == '.' && sb.charAt(r4) == '.') {
                    r0 = sb.lastIndexOf("/", r2 - 2) + 1;
                    int r22 = r0 > r8 ? r0 : r8;
                    sb.delete(r22, r3);
                    r9 -= r3 - r22;
                } else {
                    r0 = r02 + 1;
                }
                r2 = r0;
            }
            r02 = r2;
        }
        return sb.toString();
    }

    private static int[] getUriIndices(String str) {
        int r10;
        int[] r0 = new int[4];
        if (TextUtils.isEmpty(str)) {
            r0[0] = -1;
            return r0;
        }
        int length = str.length();
        int indexOf = str.indexOf(35);
        if (indexOf != -1) {
            length = indexOf;
        }
        int indexOf2 = str.indexOf(63);
        if (indexOf2 == -1 || indexOf2 > length) {
            indexOf2 = length;
        }
        int indexOf3 = str.indexOf(47);
        if (indexOf3 == -1 || indexOf3 > indexOf2) {
            indexOf3 = indexOf2;
        }
        int indexOf4 = str.indexOf(58);
        if (indexOf4 > indexOf3) {
            indexOf4 = -1;
        }
        int r6 = indexOf4 + 2;
        if (r6 < indexOf2 && str.charAt(indexOf4 + 1) == '/' && str.charAt(r6) == '/') {
            r10 = str.indexOf(47, indexOf4 + 3);
            if (r10 == -1 || r10 > indexOf2) {
                r10 = indexOf2;
            }
        } else {
            r10 = indexOf4 + 1;
        }
        r0[0] = indexOf4;
        r0[1] = r10;
        r0[2] = indexOf2;
        r0[3] = length;
        return r0;
    }
}
