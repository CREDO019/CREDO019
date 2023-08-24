package com.google.common.escape;

import com.google.common.base.Preconditions;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
/* loaded from: classes3.dex */
public abstract class CharEscaper extends Escaper {
    private static final int DEST_PAD_MULTIPLIER = 2;

    /* JADX INFO: Access modifiers changed from: protected */
    @CheckForNull
    public abstract char[] escape(char c);

    @Override // com.google.common.escape.Escaper
    public String escape(String str) {
        Preconditions.checkNotNull(str);
        int length = str.length();
        for (int r1 = 0; r1 < length; r1++) {
            if (escape(str.charAt(r1)) != null) {
                return escapeSlow(str, r1);
            }
        }
        return str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final String escapeSlow(String str, int r13) {
        int length = str.length();
        char[] charBufferFromThreadLocal = Platform.charBufferFromThreadLocal();
        int length2 = charBufferFromThreadLocal.length;
        int r4 = 0;
        int r5 = 0;
        while (r13 < length) {
            char[] escape = escape(str.charAt(r13));
            if (escape != null) {
                int length3 = escape.length;
                int r8 = r13 - r4;
                int r9 = r5 + r8;
                int r10 = r9 + length3;
                if (length2 < r10) {
                    length2 = ((length - r13) * 2) + r10;
                    charBufferFromThreadLocal = growBuffer(charBufferFromThreadLocal, r5, length2);
                }
                if (r8 > 0) {
                    str.getChars(r4, r13, charBufferFromThreadLocal, r5);
                    r5 = r9;
                }
                if (length3 > 0) {
                    System.arraycopy(escape, 0, charBufferFromThreadLocal, r5, length3);
                    r5 += length3;
                }
                r4 = r13 + 1;
            }
            r13++;
        }
        int r132 = length - r4;
        if (r132 > 0) {
            int r133 = r132 + r5;
            if (length2 < r133) {
                charBufferFromThreadLocal = growBuffer(charBufferFromThreadLocal, r5, r133);
            }
            str.getChars(r4, length, charBufferFromThreadLocal, r5);
            r5 = r133;
        }
        return new String(charBufferFromThreadLocal, 0, r5);
    }

    private static char[] growBuffer(char[] cArr, int r2, int r3) {
        if (r3 < 0) {
            throw new AssertionError("Cannot increase internal buffer any further");
        }
        char[] cArr2 = new char[r3];
        if (r2 > 0) {
            System.arraycopy(cArr, 0, cArr2, 0, r2);
        }
        return cArr2;
    }
}
