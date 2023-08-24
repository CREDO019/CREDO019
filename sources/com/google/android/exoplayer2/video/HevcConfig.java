package com.google.android.exoplayer2.video;

import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.util.CodecSpecificDataUtil;
import com.google.android.exoplayer2.util.NalUnitUtil;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.util.Collections;
import java.util.List;

/* loaded from: classes2.dex */
public final class HevcConfig {
    private static final int SPS_NAL_UNIT_TYPE = 33;
    public final String codecs;
    public final int height;
    public final List<byte[]> initializationData;
    public final int nalUnitLengthFieldLength;
    public final float pixelWidthHeightRatio;
    public final int width;

    public static HevcConfig parse(ParsableByteArray parsableByteArray) throws ParserException {
        int r24;
        int r25;
        try {
            parsableByteArray.skipBytes(21);
            int readUnsignedByte = parsableByteArray.readUnsignedByte() & 3;
            int readUnsignedByte2 = parsableByteArray.readUnsignedByte();
            int position = parsableByteArray.getPosition();
            int r6 = 0;
            for (int r5 = 0; r5 < readUnsignedByte2; r5++) {
                parsableByteArray.skipBytes(1);
                int readUnsignedShort = parsableByteArray.readUnsignedShort();
                for (int r8 = 0; r8 < readUnsignedShort; r8++) {
                    int readUnsignedShort2 = parsableByteArray.readUnsignedShort();
                    r6 += readUnsignedShort2 + 4;
                    parsableByteArray.skipBytes(readUnsignedShort2);
                }
            }
            parsableByteArray.setPosition(position);
            byte[] bArr = new byte[r6];
            String str = null;
            int r82 = 0;
            int r13 = -1;
            int r14 = -1;
            float f = 1.0f;
            for (int r52 = 0; r52 < readUnsignedByte2; r52++) {
                int readUnsignedByte3 = parsableByteArray.readUnsignedByte() & 127;
                int readUnsignedShort3 = parsableByteArray.readUnsignedShort();
                int r11 = 0;
                while (r11 < readUnsignedShort3) {
                    int readUnsignedShort4 = parsableByteArray.readUnsignedShort();
                    int r17 = readUnsignedByte2;
                    System.arraycopy(NalUnitUtil.NAL_START_CODE, 0, bArr, r82, NalUnitUtil.NAL_START_CODE.length);
                    int length = r82 + NalUnitUtil.NAL_START_CODE.length;
                    System.arraycopy(parsableByteArray.getData(), parsableByteArray.getPosition(), bArr, length, readUnsignedShort4);
                    if (readUnsignedByte3 == 33 && r11 == 0) {
                        NalUnitUtil.H265SpsData parseH265SpsNalUnit = NalUnitUtil.parseH265SpsNalUnit(bArr, length, length + readUnsignedShort4);
                        int r132 = parseH265SpsNalUnit.width;
                        r14 = parseH265SpsNalUnit.height;
                        f = parseH265SpsNalUnit.pixelWidthHeightRatio;
                        r24 = readUnsignedByte3;
                        r25 = readUnsignedShort3;
                        r13 = r132;
                        str = CodecSpecificDataUtil.buildHevcCodecString(parseH265SpsNalUnit.generalProfileSpace, parseH265SpsNalUnit.generalTierFlag, parseH265SpsNalUnit.generalProfileIdc, parseH265SpsNalUnit.generalProfileCompatibilityFlags, parseH265SpsNalUnit.constraintBytes, parseH265SpsNalUnit.generalLevelIdc);
                    } else {
                        r24 = readUnsignedByte3;
                        r25 = readUnsignedShort3;
                    }
                    r82 = length + readUnsignedShort4;
                    parsableByteArray.skipBytes(readUnsignedShort4);
                    r11++;
                    readUnsignedByte2 = r17;
                    readUnsignedByte3 = r24;
                    readUnsignedShort3 = r25;
                }
            }
            return new HevcConfig(r6 == 0 ? Collections.emptyList() : Collections.singletonList(bArr), readUnsignedByte + 1, r13, r14, f, str);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw ParserException.createForMalformedContainer("Error parsing HEVC config", e);
        }
    }

    private HevcConfig(List<byte[]> list, int r2, int r3, int r4, float f, String str) {
        this.initializationData = list;
        this.nalUnitLengthFieldLength = r2;
        this.width = r3;
        this.height = r4;
        this.pixelWidthHeightRatio = f;
        this.codecs = str;
    }
}
