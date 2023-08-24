package com.google.android.exoplayer2.util;

import android.util.Pair;
import androidx.exifinterface.media.ExifInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;

/* loaded from: classes2.dex */
public final class CodecSpecificDataUtil {
    private static final int EXTENDED_PAR = 15;
    private static final int RECTANGULAR = 0;
    private static final int VISUAL_OBJECT_LAYER = 1;
    private static final int VISUAL_OBJECT_LAYER_START = 32;
    private static final byte[] NAL_START_CODE = {0, 0, 0, 1};
    private static final String[] HEVC_GENERAL_PROFILE_SPACE_STRINGS = {"", ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, "B", "C"};

    public static Pair<Integer, Integer> parseAlacAudioSpecificConfig(byte[] bArr) {
        ParsableByteArray parsableByteArray = new ParsableByteArray(bArr);
        parsableByteArray.setPosition(9);
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        parsableByteArray.setPosition(20);
        return Pair.create(Integer.valueOf(parsableByteArray.readUnsignedIntToInt()), Integer.valueOf(readUnsignedByte));
    }

    public static List<byte[]> buildCea708InitializationData(boolean z) {
        return Collections.singletonList(z ? new byte[]{1} : new byte[]{0});
    }

    public static boolean parseCea708InitializationData(List<byte[]> list) {
        return list.size() == 1 && list.get(0).length == 1 && list.get(0)[0] == 1;
    }

    public static Pair<Integer, Integer> getVideoResolutionFromMpeg4VideoConfig(byte[] bArr) {
        boolean z;
        ParsableByteArray parsableByteArray = new ParsableByteArray(bArr);
        int r1 = 0;
        int r2 = 0;
        while (true) {
            int r3 = r2 + 3;
            if (r3 >= bArr.length) {
                z = false;
                break;
            } else if (parsableByteArray.readUnsignedInt24() == 1 && (bArr[r3] & 240) == 32) {
                z = true;
                break;
            } else {
                parsableByteArray.setPosition(parsableByteArray.getPosition() - 2);
                r2++;
            }
        }
        Assertions.checkArgument(z, "Invalid input: VOL not found.");
        ParsableBitArray parsableBitArray = new ParsableBitArray(bArr);
        parsableBitArray.skipBits((r2 + 4) * 8);
        parsableBitArray.skipBits(1);
        parsableBitArray.skipBits(8);
        if (parsableBitArray.readBit()) {
            parsableBitArray.skipBits(4);
            parsableBitArray.skipBits(3);
        }
        if (parsableBitArray.readBits(4) == 15) {
            parsableBitArray.skipBits(8);
            parsableBitArray.skipBits(8);
        }
        if (parsableBitArray.readBit()) {
            parsableBitArray.skipBits(2);
            parsableBitArray.skipBits(1);
            if (parsableBitArray.readBit()) {
                parsableBitArray.skipBits(79);
            }
        }
        Assertions.checkArgument(parsableBitArray.readBits(2) == 0, "Only supports rectangular video object layer shape.");
        Assertions.checkArgument(parsableBitArray.readBit());
        int readBits = parsableBitArray.readBits(16);
        Assertions.checkArgument(parsableBitArray.readBit());
        if (parsableBitArray.readBit()) {
            Assertions.checkArgument(readBits > 0);
            for (int r7 = readBits - 1; r7 > 0; r7 >>= 1) {
                r1++;
            }
            parsableBitArray.skipBits(r1);
        }
        Assertions.checkArgument(parsableBitArray.readBit());
        int readBits2 = parsableBitArray.readBits(13);
        Assertions.checkArgument(parsableBitArray.readBit());
        int readBits3 = parsableBitArray.readBits(13);
        Assertions.checkArgument(parsableBitArray.readBit());
        parsableBitArray.skipBits(1);
        return Pair.create(Integer.valueOf(readBits2), Integer.valueOf(readBits3));
    }

    public static String buildAvcCodecString(int r2, int r3, int r4) {
        return String.format("avc1.%02X%02X%02X", Integer.valueOf(r2), Integer.valueOf(r3), Integer.valueOf(r4));
    }

    public static String buildHevcCodecString(int r3, boolean z, int r5, int r6, int[] r7, int r8) {
        Object[] objArr = new Object[5];
        objArr[0] = HEVC_GENERAL_PROFILE_SPACE_STRINGS[r3];
        objArr[1] = Integer.valueOf(r5);
        objArr[2] = Integer.valueOf(r6);
        objArr[3] = Character.valueOf(z ? 'H' : Matrix.MATRIX_TYPE_RANDOM_LT);
        objArr[4] = Integer.valueOf(r8);
        StringBuilder sb = new StringBuilder(Util.formatInvariant("hvc1.%s%d.%X.%c%d", objArr));
        int length = r7.length;
        while (length > 0 && r7[length - 1] == 0) {
            length--;
        }
        for (int r4 = 0; r4 < length; r4++) {
            sb.append(String.format(".%02X", Integer.valueOf(r7[r4])));
        }
        return sb.toString();
    }

    public static byte[] buildNalUnit(byte[] bArr, int r5, int r6) {
        byte[] bArr2 = NAL_START_CODE;
        byte[] bArr3 = new byte[bArr2.length + r6];
        System.arraycopy(bArr2, 0, bArr3, 0, bArr2.length);
        System.arraycopy(bArr, r5, bArr3, bArr2.length, r6);
        return bArr3;
    }

    public static byte[][] splitNalUnits(byte[] bArr) {
        if (isNalStartCode(bArr, 0)) {
            ArrayList arrayList = new ArrayList();
            int r2 = 0;
            do {
                arrayList.add(Integer.valueOf(r2));
                r2 = findNalStartCode(bArr, r2 + NAL_START_CODE.length);
            } while (r2 != -1);
            byte[][] bArr2 = new byte[arrayList.size()];
            int r3 = 0;
            while (r3 < arrayList.size()) {
                int intValue = ((Integer) arrayList.get(r3)).intValue();
                int intValue2 = (r3 < arrayList.size() + (-1) ? ((Integer) arrayList.get(r3 + 1)).intValue() : bArr.length) - intValue;
                byte[] bArr3 = new byte[intValue2];
                System.arraycopy(bArr, intValue, bArr3, 0, intValue2);
                bArr2[r3] = bArr3;
                r3++;
            }
            return bArr2;
        }
        return null;
    }

    private static int findNalStartCode(byte[] bArr, int r3) {
        int length = bArr.length - NAL_START_CODE.length;
        while (r3 <= length) {
            if (isNalStartCode(bArr, r3)) {
                return r3;
            }
            r3++;
        }
        return -1;
    }

    private static boolean isNalStartCode(byte[] bArr, int r5) {
        if (bArr.length - r5 <= NAL_START_CODE.length) {
            return false;
        }
        int r0 = 0;
        while (true) {
            byte[] bArr2 = NAL_START_CODE;
            if (r0 >= bArr2.length) {
                return true;
            }
            if (bArr[r5 + r0] != bArr2[r0]) {
                return false;
            }
            r0++;
        }
    }

    private CodecSpecificDataUtil() {
    }
}
