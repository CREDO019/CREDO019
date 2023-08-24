package com.google.common.net;

import com.google.common.base.Preconditions;
import com.google.common.escape.UnicodeEscaper;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
/* loaded from: classes3.dex */
public final class PercentEscaper extends UnicodeEscaper {
    private static final char[] PLUS_SIGN = {'+'};
    private static final char[] UPPER_HEX_DIGITS = "0123456789ABCDEF".toCharArray();
    private final boolean plusForSpace;
    private final boolean[] safeOctets;

    public PercentEscaper(String str, boolean z) {
        Preconditions.checkNotNull(str);
        if (str.matches(".*[0-9A-Za-z].*")) {
            throw new IllegalArgumentException("Alphanumeric characters are always 'safe' and should not be explicitly specified");
        }
        String concat = String.valueOf(str).concat("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789");
        if (z && concat.contains(" ")) {
            throw new IllegalArgumentException("plusForSpace cannot be specified when space is a 'safe' character");
        }
        this.plusForSpace = z;
        this.safeOctets = createSafeOctets(concat);
    }

    private static boolean[] createSafeOctets(String str) {
        char[] charArray = str.toCharArray();
        int r2 = -1;
        for (char c : charArray) {
            r2 = Math.max((int) c, r2);
        }
        boolean[] zArr = new boolean[r2 + 1];
        for (char c2 : charArray) {
            zArr[c2] = true;
        }
        return zArr;
    }

    @Override // com.google.common.escape.UnicodeEscaper
    protected int nextEscapeIndex(CharSequence charSequence, int r5, int r6) {
        Preconditions.checkNotNull(charSequence);
        while (r5 < r6) {
            char charAt = charSequence.charAt(r5);
            boolean[] zArr = this.safeOctets;
            if (charAt >= zArr.length || !zArr[charAt]) {
                break;
            }
            r5++;
        }
        return r5;
    }

    @Override // com.google.common.escape.UnicodeEscaper, com.google.common.escape.Escaper
    public String escape(String str) {
        Preconditions.checkNotNull(str);
        int length = str.length();
        for (int r1 = 0; r1 < length; r1++) {
            char charAt = str.charAt(r1);
            boolean[] zArr = this.safeOctets;
            if (charAt >= zArr.length || !zArr[charAt]) {
                return escapeSlow(str, r1);
            }
        }
        return str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.common.escape.UnicodeEscaper
    @CheckForNull
    public char[] escape(int r14) {
        boolean[] zArr = this.safeOctets;
        if (r14 >= zArr.length || !zArr[r14]) {
            if (r14 == 32 && this.plusForSpace) {
                return PLUS_SIGN;
            }
            if (r14 <= 127) {
                char[] cArr = UPPER_HEX_DIGITS;
                return new char[]{'%', cArr[r14 >>> 4], cArr[r14 & 15]};
            } else if (r14 <= 2047) {
                char[] cArr2 = UPPER_HEX_DIGITS;
                char[] cArr3 = {'%', cArr2[(r14 >>> 4) | 12], cArr2[r14 & 15], '%', cArr2[(r14 & 3) | 8], cArr2[r14 & 15]};
                int r142 = r14 >>> 4;
                int r143 = r142 >>> 2;
                return cArr3;
            } else if (r14 <= 65535) {
                char[] cArr4 = UPPER_HEX_DIGITS;
                char[] cArr5 = {'%', 'E', cArr4[r14 >>> 2], '%', cArr4[(r14 & 3) | 8], cArr4[r14 & 15], '%', cArr4[(r14 & 3) | 8], cArr4[r14 & 15]};
                int r144 = r14 >>> 4;
                int r145 = r144 >>> 2;
                int r146 = r145 >>> 4;
                return cArr5;
            } else if (r14 <= 1114111) {
                char[] cArr6 = UPPER_HEX_DIGITS;
                char[] cArr7 = {'%', 'F', cArr6[(r14 >>> 2) & 7], '%', cArr6[(r14 & 3) | 8], cArr6[r14 & 15], '%', cArr6[(r14 & 3) | 8], cArr6[r14 & 15], '%', cArr6[(r14 & 3) | 8], cArr6[r14 & 15]};
                int r147 = r14 >>> 4;
                int r148 = r147 >>> 2;
                int r149 = r148 >>> 4;
                int r1410 = r149 >>> 2;
                int r1411 = r1410 >>> 4;
                return cArr7;
            } else {
                StringBuilder sb = new StringBuilder(43);
                sb.append("Invalid unicode character value ");
                sb.append(r14);
                throw new IllegalArgumentException(sb.toString());
            }
        }
        return null;
    }
}
