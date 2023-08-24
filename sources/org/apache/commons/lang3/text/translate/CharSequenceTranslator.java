package org.apache.commons.lang3.text.translate;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Locale;

@Deprecated
/* loaded from: classes5.dex */
public abstract class CharSequenceTranslator {
    static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public abstract int translate(CharSequence charSequence, int r2, Writer writer) throws IOException;

    public final String translate(CharSequence charSequence) {
        if (charSequence == null) {
            return null;
        }
        try {
            StringWriter stringWriter = new StringWriter(charSequence.length() * 2);
            translate(charSequence, stringWriter);
            return stringWriter.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public final void translate(CharSequence charSequence, Writer writer) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("The Writer must not be null");
        }
        if (charSequence == null) {
            return;
        }
        int length = charSequence.length();
        int r2 = 0;
        while (r2 < length) {
            int translate = translate(charSequence, r2, writer);
            if (translate == 0) {
                char charAt = charSequence.charAt(r2);
                writer.write(charAt);
                r2++;
                if (Character.isHighSurrogate(charAt) && r2 < length) {
                    char charAt2 = charSequence.charAt(r2);
                    if (Character.isLowSurrogate(charAt2)) {
                        writer.write(charAt2);
                        r2++;
                    }
                }
            } else {
                for (int r4 = 0; r4 < translate; r4++) {
                    r2 += Character.charCount(Character.codePointAt(charSequence, r2));
                }
            }
        }
    }

    public final CharSequenceTranslator with(CharSequenceTranslator... charSequenceTranslatorArr) {
        CharSequenceTranslator[] charSequenceTranslatorArr2 = new CharSequenceTranslator[charSequenceTranslatorArr.length + 1];
        charSequenceTranslatorArr2[0] = this;
        System.arraycopy(charSequenceTranslatorArr, 0, charSequenceTranslatorArr2, 1, charSequenceTranslatorArr.length);
        return new AggregateTranslator(charSequenceTranslatorArr2);
    }

    public static String hex(int r1) {
        return Integer.toHexString(r1).toUpperCase(Locale.ENGLISH);
    }
}