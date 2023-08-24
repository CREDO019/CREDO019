package com.facebook.imageformat;

import com.facebook.common.internal.Preconditions;
import java.io.UnsupportedEncodingException;

/* loaded from: classes.dex */
public class ImageFormatCheckerUtils {
    public static byte[] asciiBytes(String value) {
        Preconditions.checkNotNull(value);
        try {
            return value.getBytes("ASCII");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("ASCII not found!", e);
        }
    }

    public static boolean startsWithPattern(final byte[] byteArray, final byte[] pattern) {
        return hasPatternAt(byteArray, pattern, 0);
    }

    public static boolean hasPatternAt(final byte[] byteArray, final byte[] pattern, int offset) {
        Preconditions.checkNotNull(byteArray);
        Preconditions.checkNotNull(pattern);
        if (pattern.length + offset > byteArray.length) {
            return false;
        }
        for (int r0 = 0; r0 < pattern.length; r0++) {
            if (byteArray[offset + r0] != pattern[r0]) {
                return false;
            }
        }
        return true;
    }

    public static int indexOfPattern(final byte[] byteArray, final int byteArrayLen, final byte[] pattern, final int patternLen) {
        Preconditions.checkNotNull(byteArray);
        Preconditions.checkNotNull(pattern);
        if (patternLen > byteArrayLen) {
            return -1;
        }
        int r1 = 0;
        byte b = pattern[0];
        int r9 = byteArrayLen - patternLen;
        while (r1 <= r9) {
            if (byteArray[r1] != b) {
                do {
                    r1++;
                    if (r1 > r9) {
                        break;
                    }
                } while (byteArray[r1] != b);
            }
            if (r1 <= r9) {
                int r3 = r1 + 1;
                int r5 = (r3 + patternLen) - 1;
                for (int r4 = 1; r3 < r5 && byteArray[r3] == pattern[r4]; r4++) {
                    r3++;
                }
                if (r3 == r5) {
                    return r1;
                }
            }
            r1++;
        }
        return -1;
    }

    private ImageFormatCheckerUtils() {
    }
}
