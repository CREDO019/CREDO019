package org.apache.commons.lang3.text.translate;

import java.io.IOException;
import java.io.Writer;

@Deprecated
/* loaded from: classes5.dex */
public class OctalUnescaper extends CharSequenceTranslator {
    private boolean isOctalDigit(char c) {
        return c >= '0' && c <= '7';
    }

    private boolean isZeroToThree(char c) {
        return c >= '0' && c <= '3';
    }

    @Override // org.apache.commons.lang3.text.translate.CharSequenceTranslator
    public int translate(CharSequence charSequence, int r8, Writer writer) throws IOException {
        int length = (charSequence.length() - r8) - 1;
        StringBuilder sb = new StringBuilder();
        if (charSequence.charAt(r8) != '\\' || length <= 0) {
            return 0;
        }
        int r3 = r8 + 1;
        if (isOctalDigit(charSequence.charAt(r3))) {
            int r4 = r8 + 2;
            int r82 = r8 + 3;
            sb.append(charSequence.charAt(r3));
            if (length > 1 && isOctalDigit(charSequence.charAt(r4))) {
                sb.append(charSequence.charAt(r4));
                if (length > 2 && isZeroToThree(charSequence.charAt(r3)) && isOctalDigit(charSequence.charAt(r82))) {
                    sb.append(charSequence.charAt(r82));
                }
            }
            writer.write(Integer.parseInt(sb.toString(), 8));
            return sb.length() + 1;
        }
        return 0;
    }
}
