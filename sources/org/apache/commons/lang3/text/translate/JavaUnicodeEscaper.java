package org.apache.commons.lang3.text.translate;

@Deprecated
/* loaded from: classes5.dex */
public class JavaUnicodeEscaper extends UnicodeEscaper {
    public static JavaUnicodeEscaper above(int r1) {
        return outsideOf(0, r1);
    }

    public static JavaUnicodeEscaper below(int r1) {
        return outsideOf(r1, Integer.MAX_VALUE);
    }

    public static JavaUnicodeEscaper between(int r2, int r3) {
        return new JavaUnicodeEscaper(r2, r3, true);
    }

    public static JavaUnicodeEscaper outsideOf(int r2, int r3) {
        return new JavaUnicodeEscaper(r2, r3, false);
    }

    public JavaUnicodeEscaper(int r1, int r2, boolean z) {
        super(r1, r2, z);
    }

    @Override // org.apache.commons.lang3.text.translate.UnicodeEscaper
    protected String toUtf16Escape(int r4) {
        char[] chars = Character.toChars(r4);
        return "\\u" + hex(chars[0]) + "\\u" + hex(chars[1]);
    }
}
