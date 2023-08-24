package com.fasterxml.jackson.core.p009io;

import com.fasterxml.jackson.core.SerializableString;
import java.io.Serializable;
import java.util.Arrays;

/* renamed from: com.fasterxml.jackson.core.io.CharacterEscapes */
/* loaded from: classes.dex */
public abstract class CharacterEscapes implements Serializable {
    public static final int ESCAPE_CUSTOM = -2;
    public static final int ESCAPE_NONE = 0;
    public static final int ESCAPE_STANDARD = -1;

    public abstract int[] getEscapeCodesForAscii();

    public abstract SerializableString getEscapeSequence(int r1);

    public static int[] standardAsciiEscapesForJSON() {
        int[] r0 = CharTypes.get7BitOutputEscapes();
        return Arrays.copyOf(r0, r0.length);
    }
}
