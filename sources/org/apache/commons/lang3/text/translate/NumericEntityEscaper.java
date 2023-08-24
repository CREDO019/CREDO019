package org.apache.commons.lang3.text.translate;

import java.io.IOException;
import java.io.Writer;

@Deprecated
/* loaded from: classes5.dex */
public class NumericEntityEscaper extends CodePointTranslator {
    private final int above;
    private final int below;
    private final boolean between;

    private NumericEntityEscaper(int r1, int r2, boolean z) {
        this.below = r1;
        this.above = r2;
        this.between = z;
    }

    public NumericEntityEscaper() {
        this(0, Integer.MAX_VALUE, true);
    }

    public static NumericEntityEscaper below(int r1) {
        return outsideOf(r1, Integer.MAX_VALUE);
    }

    public static NumericEntityEscaper above(int r1) {
        return outsideOf(0, r1);
    }

    public static NumericEntityEscaper between(int r2, int r3) {
        return new NumericEntityEscaper(r2, r3, true);
    }

    public static NumericEntityEscaper outsideOf(int r2, int r3) {
        return new NumericEntityEscaper(r2, r3, false);
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
        writer.write("&#");
        writer.write(Integer.toString(r3, 10));
        writer.write(59);
        return true;
    }
}
