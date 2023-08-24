package com.google.android.exoplayer2.video;

import com.google.android.exoplayer2.util.ParsableByteArray;

/* loaded from: classes2.dex */
public final class DolbyVisionConfig {
    public final String codecs;
    public final int level;
    public final int profile;

    public static DolbyVisionConfig parse(ParsableByteArray parsableByteArray) {
        String str;
        parsableByteArray.skipBytes(2);
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        int r1 = readUnsignedByte >> 1;
        int readUnsignedByte2 = ((parsableByteArray.readUnsignedByte() >> 3) & 31) | ((readUnsignedByte & 1) << 5);
        if (r1 == 4 || r1 == 5 || r1 == 7) {
            str = "dvhe";
        } else if (r1 == 8) {
            str = "hev1";
        } else if (r1 != 9) {
            return null;
        } else {
            str = "avc3";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(".0");
        sb.append(r1);
        sb.append(readUnsignedByte2 >= 10 ? "." : ".0");
        sb.append(readUnsignedByte2);
        return new DolbyVisionConfig(r1, readUnsignedByte2, sb.toString());
    }

    private DolbyVisionConfig(int r1, int r2, String str) {
        this.profile = r1;
        this.level = r2;
        this.codecs = str;
    }
}
