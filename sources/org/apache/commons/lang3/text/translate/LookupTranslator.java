package org.apache.commons.lang3.text.translate;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.HashSet;

@Deprecated
/* loaded from: classes5.dex */
public class LookupTranslator extends CharSequenceTranslator {
    private final int longest;
    private final HashMap<String, String> lookupMap = new HashMap<>();
    private final HashSet<Character> prefixSet = new HashSet<>();
    private final int shortest;

    public LookupTranslator(CharSequence[]... charSequenceArr) {
        int r0 = 0;
        int r1 = Integer.MAX_VALUE;
        if (charSequenceArr != null) {
            int r4 = 0;
            for (CharSequence[] charSequenceArr2 : charSequenceArr) {
                this.lookupMap.put(charSequenceArr2[0].toString(), charSequenceArr2[1].toString());
                this.prefixSet.add(Character.valueOf(charSequenceArr2[0].charAt(0)));
                int length = charSequenceArr2[0].length();
                r1 = length < r1 ? length : r1;
                if (length > r4) {
                    r4 = length;
                }
            }
            r0 = r4;
        }
        this.shortest = r1;
        this.longest = r0;
    }

    @Override // org.apache.commons.lang3.text.translate.CharSequenceTranslator
    public int translate(CharSequence charSequence, int r5, Writer writer) throws IOException {
        if (this.prefixSet.contains(Character.valueOf(charSequence.charAt(r5)))) {
            int r0 = this.longest;
            if (r5 + r0 > charSequence.length()) {
                r0 = charSequence.length() - r5;
            }
            while (r0 >= this.shortest) {
                String str = this.lookupMap.get(charSequence.subSequence(r5, r5 + r0).toString());
                if (str != null) {
                    writer.write(str);
                    return r0;
                }
                r0--;
            }
            return 0;
        }
        return 0;
    }
}
