package com.facebook.imageutils;

import com.canhub.cropper.CropImage;
import com.facebook.common.internal.Preconditions;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes.dex */
public class JfifUtil {
    public static final int APP1_EXIF_MAGIC = 1165519206;
    public static final int MARKER_APP1 = 225;
    public static final int MARKER_EOI = 217;
    public static final int MARKER_ESCAPE_BYTE = 0;
    public static final int MARKER_FIRST_BYTE = 255;
    public static final int MARKER_RST0 = 208;
    public static final int MARKER_RST7 = 215;
    public static final int MARKER_SOFn = 192;
    public static final int MARKER_SOI = 216;
    public static final int MARKER_SOS = 218;
    public static final int MARKER_TEM = 1;

    private static boolean isSOFn(int marker) {
        switch (marker) {
            case 192:
            case 193:
            case 194:
            case 195:
            case 197:
            case 198:
            case 199:
            case CropImage.PICK_IMAGE_PERMISSIONS_REQUEST_CODE /* 201 */:
            case 202:
            case CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE /* 203 */:
            case 205:
            case 206:
            case 207:
                return true;
            case 196:
            case 200:
            case CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE /* 204 */:
            default:
                return false;
        }
    }

    private JfifUtil() {
    }

    public static int getAutoRotateAngleFromOrientation(int orientation) {
        return TiffUtil.getAutoRotateAngleFromOrientation(orientation);
    }

    public static int getOrientation(byte[] jpeg) {
        return getOrientation(new ByteArrayInputStream(jpeg));
    }

    public static int getOrientation(InputStream is) {
        try {
            int moveToAPP1EXIF = moveToAPP1EXIF(is);
            if (moveToAPP1EXIF == 0) {
                return 0;
            }
            return TiffUtil.readOrientationFromTIFF(is, moveToAPP1EXIF);
        } catch (IOException unused) {
            return 0;
        }
    }

    public static boolean moveToMarker(InputStream is, int markerToFind) throws IOException {
        Preconditions.checkNotNull(is);
        while (StreamProcessor.readPackedInt(is, 1, false) == 255) {
            int r2 = 255;
            while (r2 == 255) {
                r2 = StreamProcessor.readPackedInt(is, 1, false);
            }
            if ((markerToFind != 192 || !isSOFn(r2)) && r2 != markerToFind) {
                if (r2 != 216 && r2 != 1) {
                    if (r2 == 217 || r2 == 218) {
                        break;
                    }
                    is.skip(StreamProcessor.readPackedInt(is, 2, false) - 2);
                }
            } else {
                return true;
            }
        }
        return false;
    }

    private static int moveToAPP1EXIF(InputStream is) throws IOException {
        int readPackedInt;
        if (moveToMarker(is, MARKER_APP1) && (readPackedInt = StreamProcessor.readPackedInt(is, 2, false) - 2) > 6) {
            int readPackedInt2 = StreamProcessor.readPackedInt(is, 4, false);
            int readPackedInt3 = StreamProcessor.readPackedInt(is, 2, false);
            int r2 = (readPackedInt - 4) - 2;
            if (readPackedInt2 == 1165519206 && readPackedInt3 == 0) {
                return r2;
            }
        }
        return 0;
    }
}
