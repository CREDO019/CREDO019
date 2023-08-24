package com.google.common.escape;

import com.google.common.base.Preconditions;
import java.util.Map;
import javax.annotation.CheckForNull;
import kotlin.jvm.internal.CharCompanionObject;

@ElementTypesAreNonnullByDefault
/* loaded from: classes3.dex */
public abstract class ArrayBasedUnicodeEscaper extends UnicodeEscaper {
    private final char[][] replacements;
    private final int replacementsLength;
    private final int safeMax;
    private final char safeMaxChar;
    private final int safeMin;
    private final char safeMinChar;

    @CheckForNull
    protected abstract char[] escapeUnsafe(int r1);

    protected ArrayBasedUnicodeEscaper(Map<Character, String> map, int r2, int r3, String str) {
        this(ArrayBasedEscaperMap.create(map), r2, r3, str);
    }

    protected ArrayBasedUnicodeEscaper(ArrayBasedEscaperMap arrayBasedEscaperMap, int r2, int r3, String str) {
        Preconditions.checkNotNull(arrayBasedEscaperMap);
        char[][] replacementArray = arrayBasedEscaperMap.getReplacementArray();
        this.replacements = replacementArray;
        this.replacementsLength = replacementArray.length;
        if (r3 < r2) {
            r3 = -1;
            r2 = Integer.MAX_VALUE;
        }
        this.safeMin = r2;
        this.safeMax = r3;
        if (r2 >= 55296) {
            this.safeMinChar = CharCompanionObject.MAX_VALUE;
            this.safeMaxChar = (char) 0;
            return;
        }
        this.safeMinChar = (char) r2;
        this.safeMaxChar = (char) Math.min(r3, 55295);
    }

    @Override // com.google.common.escape.UnicodeEscaper, com.google.common.escape.Escaper
    public final String escape(String str) {
        Preconditions.checkNotNull(str);
        for (int r0 = 0; r0 < str.length(); r0++) {
            char charAt = str.charAt(r0);
            if ((charAt < this.replacementsLength && this.replacements[charAt] != null) || charAt > this.safeMaxChar || charAt < this.safeMinChar) {
                return escapeSlow(str, r0);
            }
        }
        return str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.common.escape.UnicodeEscaper
    @CheckForNull
    public final char[] escape(int r2) {
        char[] cArr;
        if (r2 >= this.replacementsLength || (cArr = this.replacements[r2]) == null) {
            if (r2 < this.safeMin || r2 > this.safeMax) {
                return escapeUnsafe(r2);
            }
            return null;
        }
        return cArr;
    }

    @Override // com.google.common.escape.UnicodeEscaper
    protected final int nextEscapeIndex(CharSequence charSequence, int r4, int r5) {
        while (r4 < r5) {
            char charAt = charSequence.charAt(r4);
            if ((charAt < this.replacementsLength && this.replacements[charAt] != null) || charAt > this.safeMaxChar || charAt < this.safeMinChar) {
                break;
            }
            r4++;
        }
        return r4;
    }
}
