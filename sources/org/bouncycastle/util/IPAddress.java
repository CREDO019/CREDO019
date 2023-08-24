package org.bouncycastle.util;

import org.apache.logging.log4j.message.ParameterizedMessage;

/* loaded from: classes4.dex */
public class IPAddress {
    private static boolean isMaskValue(String str, int r2) {
        try {
            int parseInt = Integer.parseInt(str);
            return parseInt >= 0 && parseInt <= r2;
        } catch (NumberFormatException unused) {
            return false;
        }
    }

    public static boolean isValid(String str) {
        return isValidIPv4(str) || isValidIPv6(str);
    }

    public static boolean isValidIPv4(String str) {
        int indexOf;
        if (str.length() == 0) {
            return false;
        }
        String str2 = str + ".";
        int r0 = 0;
        int r2 = 0;
        while (r0 < str2.length() && (indexOf = str2.indexOf(46, r0)) > r0) {
            if (r2 == 4) {
                return false;
            }
            try {
                int parseInt = Integer.parseInt(str2.substring(r0, indexOf));
                if (parseInt >= 0 && parseInt <= 255) {
                    r0 = indexOf + 1;
                    r2++;
                }
            } catch (NumberFormatException unused) {
            }
            return false;
        }
        return r2 == 4;
    }

    public static boolean isValidIPv4WithNetmask(String str) {
        int indexOf = str.indexOf("/");
        String substring = str.substring(indexOf + 1);
        if (indexOf <= 0 || !isValidIPv4(str.substring(0, indexOf))) {
            return false;
        }
        return isValidIPv4(substring) || isMaskValue(substring, 32);
    }

    public static boolean isValidIPv6(String str) {
        int indexOf;
        if (str.length() == 0) {
            return false;
        }
        String str2 = str + ParameterizedMessage.ERROR_MSG_SEPARATOR;
        int r0 = 0;
        int r2 = 0;
        boolean z = false;
        while (r0 < str2.length() && (indexOf = str2.indexOf(58, r0)) >= r0) {
            if (r2 == 8) {
                return false;
            }
            if (r0 != indexOf) {
                String substring = str2.substring(r0, indexOf);
                if (indexOf != str2.length() - 1 || substring.indexOf(46) <= 0) {
                    try {
                        int parseInt = Integer.parseInt(str2.substring(r0, indexOf), 16);
                        if (parseInt >= 0 && parseInt <= 65535) {
                        }
                    } catch (NumberFormatException unused) {
                    }
                    return false;
                } else if (!isValidIPv4(substring)) {
                    return false;
                } else {
                    r2++;
                }
            } else if (indexOf != 1 && indexOf != str2.length() - 1 && z) {
                return false;
            } else {
                z = true;
            }
            r0 = indexOf + 1;
            r2++;
        }
        return r2 == 8 || z;
    }

    public static boolean isValidIPv6WithNetmask(String str) {
        int indexOf = str.indexOf("/");
        String substring = str.substring(indexOf + 1);
        if (indexOf <= 0 || !isValidIPv6(str.substring(0, indexOf))) {
            return false;
        }
        return isValidIPv6(substring) || isMaskValue(substring, 128);
    }

    public static boolean isValidWithNetMask(String str) {
        return isValidIPv4WithNetmask(str) || isValidIPv6WithNetmask(str);
    }
}
