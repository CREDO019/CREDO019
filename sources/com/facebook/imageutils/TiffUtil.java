package com.facebook.imageutils;

import com.facebook.common.logging.FLog;
import com.facebook.imagepipeline.common.RotationOptions;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes.dex */
class TiffUtil {
    private static final Class<?> TAG = TiffUtil.class;
    public static final int TIFF_BYTE_ORDER_BIG_END = 1296891946;
    public static final int TIFF_BYTE_ORDER_LITTLE_END = 1229531648;
    public static final int TIFF_TAG_ORIENTATION = 274;
    public static final int TIFF_TYPE_SHORT = 3;

    public static int getAutoRotateAngleFromOrientation(int orientation) {
        if (orientation != 3) {
            if (orientation != 6) {
                return orientation != 8 ? 0 : 270;
            }
            return 90;
        }
        return RotationOptions.ROTATE_180;
    }

    TiffUtil() {
    }

    public static int readOrientationFromTIFF(InputStream is, int length) throws IOException {
        TiffHeader tiffHeader = new TiffHeader();
        int readTiffHeader = readTiffHeader(is, length, tiffHeader);
        int r1 = tiffHeader.firstIfdOffset - 8;
        if (readTiffHeader == 0 || r1 > readTiffHeader) {
            return 0;
        }
        is.skip(r1);
        return getOrientationFromTiffEntry(is, moveToTiffEntryWithTag(is, readTiffHeader - r1, tiffHeader.isLittleEndian, TIFF_TAG_ORIENTATION), tiffHeader.isLittleEndian);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class TiffHeader {
        int byteOrder;
        int firstIfdOffset;
        boolean isLittleEndian;

        private TiffHeader() {
        }
    }

    private static int readTiffHeader(InputStream is, int length, TiffHeader tiffHeader) throws IOException {
        if (length <= 8) {
            return 0;
        }
        tiffHeader.byteOrder = StreamProcessor.readPackedInt(is, 4, false);
        int r7 = length - 4;
        if (tiffHeader.byteOrder != 1229531648 && tiffHeader.byteOrder != 1296891946) {
            FLog.m1332e(TAG, "Invalid TIFF header");
            return 0;
        }
        tiffHeader.isLittleEndian = tiffHeader.byteOrder == 1229531648;
        tiffHeader.firstIfdOffset = StreamProcessor.readPackedInt(is, 4, tiffHeader.isLittleEndian);
        int r72 = r7 - 4;
        if (tiffHeader.firstIfdOffset < 8 || tiffHeader.firstIfdOffset - 8 > r72) {
            FLog.m1332e(TAG, "Invalid offset");
            return 0;
        }
        return r72;
    }

    private static int moveToTiffEntryWithTag(InputStream is, int length, boolean isLittleEndian, int tagToFind) throws IOException {
        if (length < 14) {
            return 0;
        }
        int readPackedInt = StreamProcessor.readPackedInt(is, 2, isLittleEndian);
        int r7 = length - 2;
        while (true) {
            int r3 = readPackedInt - 1;
            if (readPackedInt <= 0 || r7 < 12) {
                break;
            }
            int r72 = r7 - 2;
            if (StreamProcessor.readPackedInt(is, 2, isLittleEndian) == tagToFind) {
                return r72;
            }
            is.skip(10L);
            r7 = r72 - 10;
            readPackedInt = r3;
        }
        return 0;
    }

    private static int getOrientationFromTiffEntry(InputStream is, int length, boolean isLittleEndian) throws IOException {
        if (length >= 10 && StreamProcessor.readPackedInt(is, 2, isLittleEndian) == 3 && StreamProcessor.readPackedInt(is, 4, isLittleEndian) == 1) {
            return StreamProcessor.readPackedInt(is, 2, isLittleEndian);
        }
        return 0;
    }
}
