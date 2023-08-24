package org.apache.commons.lang3.text.translate;

import java.io.IOException;
import java.io.Writer;

@Deprecated
/* loaded from: classes5.dex */
public class UnicodeEscaper extends CodePointTranslator {
    private final int above;
    private final int below;
    private final boolean between;

    public UnicodeEscaper() {
        this(0, Integer.MAX_VALUE, true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public UnicodeEscaper(int r1, int r2, boolean z) {
        this.below = r1;
        this.above = r2;
        this.between = z;
    }

    public static UnicodeEscaper below(int r1) {
        return outsideOf(r1, Integer.MAX_VALUE);
    }

    public static UnicodeEscaper above(int r1) {
        return outsideOf(0, r1);
    }

    public static UnicodeEscaper outsideOf(int r2, int r3) {
        return new UnicodeEscaper(r2, r3, false);
    }

    public static UnicodeEscaper between(int r2, int r3) {
        return new UnicodeEscaper(r2, r3, true);
    }

    @Override // org.apache.commons.lang3.text.translate.CodePointTranslator
    public boolean translate(int r3, Writer writer) throws IOException {
        if (this.between) {
            if (r3 < this.below || r3 > this.above) {
                return false;
            }
        } else if (r3 >= this.below && r3 <= this.above) {
            return false;
        }
        if (r3 > 65535) {
            writer.write(toUtf16Escape(r3));
            return true;
        }
        writer.write("\\u");
        writer.write(HEX_DIGITS[(r3 >> 12) & 15]);
        writer.write(HEX_DIGITS[(r3 >> 8) & 15]);
        writer.write(HEX_DIGITS[(r3 >> 4) & 15]);
        writer.write(HEX_DIGITS[r3 & 15]);
        return true;
    }

    protected String toUtf16Escape(int r3) {
        return "\\u" + hex(r3);
    }
}
