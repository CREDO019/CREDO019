package com.polidea.reactnativeble.utils;

import com.facebook.react.bridge.ReadableArray;
import java.util.UUID;

/* loaded from: classes3.dex */
public class UUIDConverter {
    private static String baseUUIDPrefix = "0000";
    private static String baseUUIDSuffix = "-0000-1000-8000-00805F9B34FB";

    public static UUID convert(String str) {
        if (str == null) {
            return null;
        }
        if (str.length() == 4) {
            str = baseUUIDPrefix + str + baseUUIDSuffix;
        } else if (str.length() == 8) {
            str = str + baseUUIDSuffix;
        }
        try {
            return UUID.fromString(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static UUID[] convert(String... strArr) {
        UUID[] r0 = new UUID[strArr.length];
        for (int r1 = 0; r1 < strArr.length; r1++) {
            if (strArr[r1] == null) {
                return null;
            }
            if (strArr[r1].length() == 4) {
                strArr[r1] = baseUUIDPrefix + strArr[r1] + baseUUIDSuffix;
            } else if (strArr[r1].length() == 8) {
                strArr[r1] = strArr[r1] + baseUUIDSuffix;
            }
            try {
                r0[r1] = UUID.fromString(strArr[r1]);
            } catch (Throwable unused) {
                return null;
            }
        }
        return r0;
    }

    public static UUID[] convert(ReadableArray readableArray) {
        UUID[] r0 = new UUID[readableArray.size()];
        for (int r1 = 0; r1 < readableArray.size(); r1++) {
            try {
                String string = readableArray.getString(r1);
                if (string.length() == 4) {
                    string = baseUUIDPrefix + string + baseUUIDSuffix;
                } else if (string.length() == 8) {
                    string = string + baseUUIDSuffix;
                }
                r0[r1] = UUID.fromString(string);
            } catch (Throwable unused) {
                return null;
            }
        }
        return r0;
    }

    public static String fromUUID(UUID r0) {
        return r0.toString().toLowerCase();
    }
}
