package org.apache.commons.lang3.text.translate;

import java.io.IOException;
import java.io.Writer;

@Deprecated
/* loaded from: classes5.dex */
public class UnicodeUnescaper extends CharSequenceTranslator {
    @Override // org.apache.commons.lang3.text.translate.CharSequenceTranslator
    public int translate(CharSequence charSequence, int r6, Writer writer) throws IOException {
        int r0;
        int r2;
        if (charSequence.charAt(r6) == '\\' && (r0 = r6 + 1) < charSequence.length() && charSequence.charAt(r0) == 'u') {
            int r02 = 2;
            while (true) {
                r2 = r6 + r02;
                if (r2 >= charSequence.length() || charSequence.charAt(r2) != 'u') {
                    break;
                }
                r02++;
            }
            if (r2 < charSequence.length() && charSequence.charAt(r2) == '+') {
                r02++;
            }
            int r1 = r6 + r02;
            int r22 = r1 + 4;
            if (r22 <= charSequence.length()) {
                CharSequence subSequence = charSequence.subSequence(r1, r22);
                try {
                    writer.write((char) Integer.parseInt(subSequence.toString(), 16));
                    return r02 + 4;
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Unable to parse unicode value: " + ((Object) subSequence), e);
                }
            }
            throw new IllegalArgumentException("Less than 4 hex digits in unicode value: '" + ((Object) charSequence.subSequence(r6, charSequence.length())) + "' due to end of CharSequence");
        }
        return 0;
    }
}
