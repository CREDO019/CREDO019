package com.google.common.escape;

import com.google.common.base.Preconditions;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
/* loaded from: classes3.dex */
public abstract class UnicodeEscaper extends Escaper {
    private static final int DEST_PAD = 32;

    /* JADX INFO: Access modifiers changed from: protected */
    @CheckForNull
    public abstract char[] escape(int r1);

    @Override // com.google.common.escape.Escaper
    public String escape(String str) {
        Preconditions.checkNotNull(str);
        int length = str.length();
        int nextEscapeIndex = nextEscapeIndex(str, 0, length);
        return nextEscapeIndex == length ? str : escapeSlow(str, nextEscapeIndex);
    }

    protected int nextEscapeIndex(CharSequence charSequence, int r4, int r5) {
        while (r4 < r5) {
            int codePointAt = codePointAt(charSequence, r4, r5);
            if (codePointAt < 0 || escape(codePointAt) != null) {
                break;
            }
            r4 += Character.isSupplementaryCodePoint(codePointAt) ? 2 : 1;
        }
        return r4;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final String escapeSlow(String str, int r13) {
        int length = str.length();
        char[] charBufferFromThreadLocal = Platform.charBufferFromThreadLocal();
        int r3 = 0;
        int r4 = 0;
        while (r13 < length) {
            int codePointAt = codePointAt(str, r13, length);
            if (codePointAt < 0) {
                throw new IllegalArgumentException("Trailing high surrogate at end of input");
            }
            char[] escape = escape(codePointAt);
            int r5 = (Character.isSupplementaryCodePoint(codePointAt) ? 2 : 1) + r13;
            if (escape != null) {
                int r7 = r13 - r3;
                int r8 = r4 + r7;
                int length2 = escape.length + r8;
                if (charBufferFromThreadLocal.length < length2) {
                    charBufferFromThreadLocal = growBuffer(charBufferFromThreadLocal, r4, length2 + (length - r13) + 32);
                }
                if (r7 > 0) {
                    str.getChars(r3, r13, charBufferFromThreadLocal, r4);
                    r4 = r8;
                }
                if (escape.length > 0) {
                    System.arraycopy(escape, 0, charBufferFromThreadLocal, r4, escape.length);
                    r4 += escape.length;
                }
                r3 = r5;
            }
            r13 = nextEscapeIndex(str, r5, length);
        }
        int r132 = length - r3;
        if (r132 > 0) {
            int r133 = r132 + r4;
            if (charBufferFromThreadLocal.length < r133) {
                charBufferFromThreadLocal = growBuffer(charBufferFromThreadLocal, r4, r133);
            }
            str.getChars(r3, length, charBufferFromThreadLocal, r4);
            r4 = r133;
        }
        return new String(charBufferFromThreadLocal, 0, r4);
    }

    protected static int codePointAt(CharSequence charSequence, int r8, int r9) {
        Preconditions.checkNotNull(charSequence);
        if (r8 < r9) {
            int r0 = r8 + 1;
            char charAt = charSequence.charAt(r8);
            if (charAt < 55296 || charAt > 57343) {
                return charAt;
            }
            if (charAt > 56319) {
                String valueOf = String.valueOf(charSequence);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 88);
                sb.append("Unexpected low surrogate character '");
                sb.append(charAt);
                sb.append("' with value ");
                sb.append((int) charAt);
                sb.append(" at index ");
                sb.append(r0 - 1);
                sb.append(" in '");
                sb.append(valueOf);
                sb.append("'");
                throw new IllegalArgumentException(sb.toString());
            } else if (r0 == r9) {
                return -charAt;
            } else {
                char charAt2 = charSequence.charAt(r0);
                if (Character.isLowSurrogate(charAt2)) {
                    return Character.toCodePoint(charAt, charAt2);
                }
                String valueOf2 = String.valueOf(charSequence);
                StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf2).length() + 89);
                sb2.append("Expected low surrogate but got char '");
                sb2.append(charAt2);
                sb2.append("' with value ");
                sb2.append((int) charAt2);
                sb2.append(" at index ");
                sb2.append(r0);
                sb2.append(" in '");
                sb2.append(valueOf2);
                sb2.append("'");
                throw new IllegalArgumentException(sb2.toString());
            }
        }
        throw new IndexOutOfBoundsException("Index exceeds specified range");
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
