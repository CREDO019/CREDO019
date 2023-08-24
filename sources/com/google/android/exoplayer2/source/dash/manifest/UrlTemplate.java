package com.google.android.exoplayer2.source.dash.manifest;

import java.util.Locale;

/* loaded from: classes2.dex */
public final class UrlTemplate {
    private static final String BANDWIDTH = "Bandwidth";
    private static final int BANDWIDTH_ID = 3;
    private static final String DEFAULT_FORMAT_TAG = "%01d";
    private static final String ESCAPED_DOLLAR = "$$";
    private static final String NUMBER = "Number";
    private static final int NUMBER_ID = 2;
    private static final String REPRESENTATION = "RepresentationID";
    private static final int REPRESENTATION_ID = 1;
    private static final String TIME = "Time";
    private static final int TIME_ID = 4;
    private final int identifierCount;
    private final String[] identifierFormatTags;
    private final int[] identifiers;
    private final String[] urlPieces;

    public static UrlTemplate compile(String str) {
        String[] strArr = new String[5];
        int[] r2 = new int[4];
        String[] strArr2 = new String[4];
        return new UrlTemplate(strArr, r2, strArr2, parseTemplate(str, strArr, r2, strArr2));
    }

    private UrlTemplate(String[] strArr, int[] r2, String[] strArr2, int r4) {
        this.urlPieces = strArr;
        this.identifiers = r2;
        this.identifierFormatTags = strArr2;
        this.identifierCount = r4;
    }

    public String buildUri(String str, long j, int r11, long j2) {
        StringBuilder sb = new StringBuilder();
        int r2 = 0;
        while (true) {
            int r3 = this.identifierCount;
            if (r2 < r3) {
                sb.append(this.urlPieces[r2]);
                int[] r32 = this.identifiers;
                if (r32[r2] == 1) {
                    sb.append(str);
                } else if (r32[r2] == 2) {
                    sb.append(String.format(Locale.US, this.identifierFormatTags[r2], Long.valueOf(j)));
                } else if (r32[r2] == 3) {
                    sb.append(String.format(Locale.US, this.identifierFormatTags[r2], Integer.valueOf(r11)));
                } else if (r32[r2] == 4) {
                    sb.append(String.format(Locale.US, this.identifierFormatTags[r2], Long.valueOf(j2)));
                }
                r2++;
            } else {
                sb.append(this.urlPieces[r3]);
                return sb.toString();
            }
        }
    }

    private static int parseTemplate(String str, String[] strArr, int[] r13, String[] strArr2) {
        String str2;
        strArr[0] = "";
        int r2 = 0;
        int r3 = 0;
        while (r2 < str.length()) {
            int indexOf = str.indexOf("$", r2);
            char c = 65535;
            if (indexOf == -1) {
                strArr[r3] = strArr[r3] + str.substring(r2);
                r2 = str.length();
            } else if (indexOf != r2) {
                strArr[r3] = strArr[r3] + str.substring(r2, indexOf);
                r2 = indexOf;
            } else if (str.startsWith(ESCAPED_DOLLAR, r2)) {
                strArr[r3] = strArr[r3] + "$";
                r2 += 2;
            } else {
                int r22 = r2 + 1;
                int indexOf2 = str.indexOf("$", r22);
                String substring = str.substring(r22, indexOf2);
                if (substring.equals(REPRESENTATION)) {
                    r13[r3] = 1;
                } else {
                    int indexOf3 = substring.indexOf("%0");
                    if (indexOf3 != -1) {
                        str2 = substring.substring(indexOf3);
                        if (!str2.endsWith("d") && !str2.endsWith("x") && !str2.endsWith("X")) {
                            str2 = str2 + "d";
                        }
                        substring = substring.substring(0, indexOf3);
                    } else {
                        str2 = DEFAULT_FORMAT_TAG;
                    }
                    substring.hashCode();
                    switch (substring.hashCode()) {
                        case -1950496919:
                            if (substring.equals(NUMBER)) {
                                c = 0;
                                break;
                            }
                            break;
                        case 2606829:
                            if (substring.equals(TIME)) {
                                c = 1;
                                break;
                            }
                            break;
                        case 38199441:
                            if (substring.equals("Bandwidth")) {
                                c = 2;
                                break;
                            }
                            break;
                    }
                    switch (c) {
                        case 0:
                            r13[r3] = 2;
                            break;
                        case 1:
                            r13[r3] = 4;
                            break;
                        case 2:
                            r13[r3] = 3;
                            break;
                        default:
                            throw new IllegalArgumentException("Invalid template: " + str);
                    }
                    strArr2[r3] = str2;
                }
                r3++;
                strArr[r3] = "";
                r2 = indexOf2 + 1;
            }
        }
        return r3;
    }
}
