package com.google.android.exoplayer2.text.ssa;

import android.text.TextUtils;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.exoplayer2.util.Assertions;
import com.google.common.base.Ascii;

/* loaded from: classes2.dex */
final class SsaDialogueFormat {
    public final int endTimeIndex;
    public final int length;
    public final int startTimeIndex;
    public final int styleIndex;
    public final int textIndex;

    private SsaDialogueFormat(int r1, int r2, int r3, int r4, int r5) {
        this.startTimeIndex = r1;
        this.endTimeIndex = r2;
        this.styleIndex = r3;
        this.textIndex = r4;
        this.length = r5;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static SsaDialogueFormat fromFormatLine(String str) {
        char c;
        Assertions.checkArgument(str.startsWith("Format:"));
        String[] split = TextUtils.split(str.substring(7), ",");
        int r4 = -1;
        int r5 = -1;
        int r6 = -1;
        int r7 = -1;
        for (int r2 = 0; r2 < split.length; r2++) {
            String lowerCase = Ascii.toLowerCase(split[r2].trim());
            lowerCase.hashCode();
            switch (lowerCase.hashCode()) {
                case 100571:
                    if (lowerCase.equals("end")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case 3556653:
                    if (lowerCase.equals("text")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case 109757538:
                    if (lowerCase.equals("start")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case 109780401:
                    if (lowerCase.equals(TtmlNode.TAG_STYLE)) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            switch (c) {
                case 0:
                    r5 = r2;
                    break;
                case 1:
                    r7 = r2;
                    break;
                case 2:
                    r4 = r2;
                    break;
                case 3:
                    r6 = r2;
                    break;
            }
        }
        if (r4 == -1 || r5 == -1 || r7 == -1) {
            return null;
        }
        return new SsaDialogueFormat(r4, r5, r6, r7, split.length);
    }
}
