package com.google.common.base;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
/* loaded from: classes3.dex */
public final class Strings {
    private Strings() {
    }

    public static String nullToEmpty(@CheckForNull String str) {
        return Platform.nullToEmpty(str);
    }

    @CheckForNull
    public static String emptyToNull(@CheckForNull String str) {
        return Platform.emptyToNull(str);
    }

    public static boolean isNullOrEmpty(@CheckForNull String str) {
        return Platform.stringIsNullOrEmpty(str);
    }

    public static String padStart(String str, int r3, char c) {
        Preconditions.checkNotNull(str);
        if (str.length() >= r3) {
            return str;
        }
        StringBuilder sb = new StringBuilder(r3);
        for (int length = str.length(); length < r3; length++) {
            sb.append(c);
        }
        sb.append(str);
        return sb.toString();
    }

    public static String padEnd(String str, int r2, char c) {
        Preconditions.checkNotNull(str);
        if (str.length() >= r2) {
            return str;
        }
        StringBuilder sb = new StringBuilder(r2);
        sb.append(str);
        for (int length = str.length(); length < r2; length++) {
            sb.append(c);
        }
        return sb.toString();
    }

    public static String repeat(String str, int r8) {
        Preconditions.checkNotNull(str);
        if (r8 <= 1) {
            Preconditions.checkArgument(r8 >= 0, "invalid count: %s", r8);
            return r8 == 0 ? "" : str;
        }
        int length = str.length();
        long j = length * r8;
        int r82 = (int) j;
        if (r82 != j) {
            StringBuilder sb = new StringBuilder(51);
            sb.append("Required array size too large: ");
            sb.append(j);
            throw new ArrayIndexOutOfBoundsException(sb.toString());
        }
        char[] cArr = new char[r82];
        str.getChars(0, length, cArr, 0);
        while (true) {
            int r7 = r82 - length;
            if (length < r7) {
                System.arraycopy(cArr, 0, cArr, length, length);
                length <<= 1;
            } else {
                System.arraycopy(cArr, 0, cArr, length, r7);
                return new String(cArr);
            }
        }
    }

    public static String commonPrefix(CharSequence charSequence, CharSequence charSequence2) {
        Preconditions.checkNotNull(charSequence);
        Preconditions.checkNotNull(charSequence2);
        int min = Math.min(charSequence.length(), charSequence2.length());
        int r2 = 0;
        while (r2 < min && charSequence.charAt(r2) == charSequence2.charAt(r2)) {
            r2++;
        }
        int r0 = r2 - 1;
        if (validSurrogatePairAt(charSequence, r0) || validSurrogatePairAt(charSequence2, r0)) {
            r2--;
        }
        return charSequence.subSequence(0, r2).toString();
    }

    public static String commonSuffix(CharSequence charSequence, CharSequence charSequence2) {
        Preconditions.checkNotNull(charSequence);
        Preconditions.checkNotNull(charSequence2);
        int min = Math.min(charSequence.length(), charSequence2.length());
        int r1 = 0;
        while (r1 < min && charSequence.charAt((charSequence.length() - r1) - 1) == charSequence2.charAt((charSequence2.length() - r1) - 1)) {
            r1++;
        }
        if (validSurrogatePairAt(charSequence, (charSequence.length() - r1) - 1) || validSurrogatePairAt(charSequence2, (charSequence2.length() - r1) - 1)) {
            r1--;
        }
        return charSequence.subSequence(charSequence.length() - r1, charSequence.length()).toString();
    }

    static boolean validSurrogatePairAt(CharSequence charSequence, int r3) {
        return r3 >= 0 && r3 <= charSequence.length() + (-2) && Character.isHighSurrogate(charSequence.charAt(r3)) && Character.isLowSurrogate(charSequence.charAt(r3 + 1));
    }

    public static String lenientFormat(@CheckForNull String str, @CheckForNull Object... objArr) {
        int indexOf;
        String valueOf = String.valueOf(str);
        int r0 = 0;
        if (objArr == null) {
            objArr = new Object[]{"(Object[])null"};
        } else {
            for (int r1 = 0; r1 < objArr.length; r1++) {
                objArr[r1] = lenientToString(objArr[r1]);
            }
        }
        StringBuilder sb = new StringBuilder(valueOf.length() + (objArr.length * 16));
        int r2 = 0;
        while (r0 < objArr.length && (indexOf = valueOf.indexOf("%s", r2)) != -1) {
            sb.append((CharSequence) valueOf, r2, indexOf);
            sb.append(objArr[r0]);
            r2 = indexOf + 2;
            r0++;
        }
        sb.append((CharSequence) valueOf, r2, valueOf.length());
        if (r0 < objArr.length) {
            sb.append(" [");
            sb.append(objArr[r0]);
            for (int r6 = r0 + 1; r6 < objArr.length; r6++) {
                sb.append(", ");
                sb.append(objArr[r6]);
            }
            sb.append(']');
        }
        return sb.toString();
    }

    private static String lenientToString(@CheckForNull Object obj) {
        if (obj == null) {
            return "null";
        }
        try {
            return obj.toString();
        } catch (Exception e) {
            String name = obj.getClass().getName();
            String hexString = Integer.toHexString(System.identityHashCode(obj));
            StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 1 + String.valueOf(hexString).length());
            sb.append(name);
            sb.append('@');
            sb.append(hexString);
            String sb2 = sb.toString();
            Logger logger = Logger.getLogger("com.google.common.base.Strings");
            Level level = Level.WARNING;
            String valueOf = String.valueOf(sb2);
            logger.log(level, valueOf.length() != 0 ? "Exception during lenientFormat for ".concat(valueOf) : new String("Exception during lenientFormat for "), (Throwable) e);
            String name2 = e.getClass().getName();
            StringBuilder sb3 = new StringBuilder(String.valueOf(sb2).length() + 9 + String.valueOf(name2).length());
            sb3.append("<");
            sb3.append(sb2);
            sb3.append(" threw ");
            sb3.append(name2);
            sb3.append(">");
            return sb3.toString();
        }
    }
}
