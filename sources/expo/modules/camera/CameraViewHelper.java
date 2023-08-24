package expo.modules.camera;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.CamcorderProfile;
import android.os.Bundle;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.ViewCompat;
import androidx.exifinterface.media.ExifInterface;
import com.canhub.cropper.CropImageOptions;
import com.facebook.react.uimanager.ViewProps;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CameraViewHelper.kt */
@Metadata(m184d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0007J\u0016\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bJ\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u000bH\u0007J\u0018\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u000bH\u0007J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0006H\u0007J$\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0012\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u00010\u0019H\u0007¨\u0006\u001b"}, m183d2 = {"Lexpo/modules/camera/CameraViewHelper;", "", "()V", "addExifData", "", "baseExif", "Landroidx/exifinterface/media/ExifInterface;", "additionalExif", "generateSimulatorPhoto", "", "width", "", "height", "getCamcorderProfile", "Landroid/media/CamcorderProfile;", "cameraId", "quality", "getCorrectCameraRotation", ViewProps.ROTATION, "facing", "getExifData", "Landroid/os/Bundle;", "exifInterface", "setExifData", "exifMap", "", "", "expo-camera_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class CameraViewHelper {
    public static final CameraViewHelper INSTANCE = new CameraViewHelper();

    private CameraViewHelper() {
    }

    @JvmStatic
    public static final int getCorrectCameraRotation(int r1, int r2) {
        if (r2 == 1) {
            return ((r1 - 90) + CropImageOptions.DEGREES_360) % CropImageOptions.DEGREES_360;
        }
        return (((-r1) + 90) + CropImageOptions.DEGREES_360) % CropImageOptions.DEGREES_360;
    }

    @JvmStatic
    public static final CamcorderProfile getCamcorderProfile(int r3, int r4) {
        CamcorderProfile profile = CamcorderProfile.get(r3, 1);
        if (r4 == 0) {
            profile = CamcorderProfile.get(r3, 8);
        } else if (r4 == 1) {
            profile = CamcorderProfile.get(r3, 6);
        } else if (r4 == 2) {
            profile = CamcorderProfile.get(r3, 5);
        } else if (r4 == 3) {
            profile = CamcorderProfile.get(r3, 4);
        } else if (r4 == 4) {
            profile = CamcorderProfile.get(r3, 4);
            profile.videoFrameWidth = 640;
        }
        Intrinsics.checkNotNullExpressionValue(profile, "profile");
        return profile;
    }

    @JvmStatic
    public static final Bundle getExifData(ExifInterface exifInterface) {
        Intrinsics.checkNotNullParameter(exifInterface, "exifInterface");
        Bundle bundle = new Bundle();
        String[][] exifTags = ExifTags.getExifTags();
        int length = exifTags.length;
        int r4 = 0;
        while (r4 < length) {
            String[] strArr = exifTags[r4];
            r4++;
            String str = strArr[0];
            String str2 = strArr[1];
            if (exifInterface.getAttribute(str2) != null) {
                int hashCode = str.hashCode();
                if (hashCode != -1325958191) {
                    if (hashCode != -891985903) {
                        if (hashCode == 104431 && str.equals("int")) {
                            bundle.putInt(str2, exifInterface.getAttributeInt(str2, 0));
                        }
                    } else if (str.equals("string")) {
                        bundle.putString(str2, exifInterface.getAttribute(str2));
                    }
                } else if (str.equals("double")) {
                    bundle.putDouble(str2, exifInterface.getAttributeDouble(str2, 0.0d));
                }
            }
        }
        double[] latLong = exifInterface.getLatLong();
        if (latLong != null) {
            bundle.putDouble(ExifInterface.TAG_GPS_LATITUDE, latLong[0]);
            bundle.putDouble(ExifInterface.TAG_GPS_LONGITUDE, latLong[1]);
            bundle.putDouble(ExifInterface.TAG_GPS_ALTITUDE, exifInterface.getAltitude(0.0d));
        }
        return bundle;
    }

    @JvmStatic
    public static final void setExifData(ExifInterface baseExif, Map<String, ? extends Object> exifMap) throws IllegalArgumentException {
        Intrinsics.checkNotNullParameter(baseExif, "baseExif");
        Intrinsics.checkNotNullParameter(exifMap, "exifMap");
        String[][] exifTags = ExifTags.getExifTags();
        int length = exifTags.length;
        int r2 = 0;
        while (r2 < length) {
            String[] strArr = exifTags[r2];
            r2++;
            String str = strArr[1];
            Object obj = exifMap.get(str);
            if (obj != null) {
                if (obj instanceof String) {
                    baseExif.setAttribute(str, (String) obj);
                } else if (obj instanceof Number) {
                    baseExif.setAttribute(str, new BigDecimal(String.valueOf(((Number) obj).doubleValue())).toPlainString());
                } else if (obj instanceof Boolean) {
                    baseExif.setAttribute(str, obj.toString());
                }
            }
        }
        if (exifMap.containsKey(ExifInterface.TAG_GPS_LATITUDE) && exifMap.containsKey(ExifInterface.TAG_GPS_LONGITUDE) && (exifMap.get(ExifInterface.TAG_GPS_LATITUDE) instanceof Number) && (exifMap.get(ExifInterface.TAG_GPS_LONGITUDE) instanceof Number)) {
            Object obj2 = exifMap.get(ExifInterface.TAG_GPS_LATITUDE);
            Objects.requireNonNull(obj2, "null cannot be cast to non-null type kotlin.Double");
            double doubleValue = ((Double) obj2).doubleValue();
            Object obj3 = exifMap.get(ExifInterface.TAG_GPS_LONGITUDE);
            Objects.requireNonNull(obj3, "null cannot be cast to non-null type kotlin.Double");
            baseExif.setLatLong(doubleValue, ((Double) obj3).doubleValue());
        }
        if (exifMap.containsKey(ExifInterface.TAG_GPS_ALTITUDE) && (exifMap.get(ExifInterface.TAG_GPS_ALTITUDE) instanceof Number)) {
            Object obj4 = exifMap.get(ExifInterface.TAG_GPS_ALTITUDE);
            Objects.requireNonNull(obj4, "null cannot be cast to non-null type kotlin.Double");
            baseExif.setAltitude(((Double) obj4).doubleValue());
        }
    }

    @JvmStatic
    public static final void addExifData(ExifInterface baseExif, ExifInterface additionalExif) throws IOException {
        Intrinsics.checkNotNullParameter(baseExif, "baseExif");
        Intrinsics.checkNotNullParameter(additionalExif, "additionalExif");
        String[][] exifTags = ExifTags.getExifTags();
        int length = exifTags.length;
        int r2 = 0;
        while (r2 < length) {
            String[] strArr = exifTags[r2];
            r2++;
            String str = strArr[1];
            String attribute = additionalExif.getAttribute(str);
            if (attribute != null) {
                baseExif.setAttribute(str, attribute);
            }
        }
        baseExif.saveAttributes();
    }

    public final byte[] generateSimulatorPhoto(int r9, int r10) {
        Bitmap createBitmap = Bitmap.createBitmap(r9, r10, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        paint.setColor(ViewCompat.MEASURED_STATE_MASK);
        float f = r9;
        float f2 = r10;
        canvas.drawRect(0.0f, 0.0f, f, f2, paint);
        Paint paint2 = new Paint();
        paint2.setColor(InputDeviceCompat.SOURCE_ANY);
        paint2.setTextSize(35.0f);
        canvas.drawText(new SimpleDateFormat("dd.MM.yy HH:mm:ss", Locale.US).format(Calendar.getInstance().getTime()), f * 0.1f, f2 * 0.9f, paint2);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        createBitmap.compress(Bitmap.CompressFormat.PNG, 90, byteArrayOutputStream);
        byte[] fakePhotoByteArray = byteArrayOutputStream.toByteArray();
        Intrinsics.checkNotNullExpressionValue(fakePhotoByteArray, "fakePhotoByteArray");
        return fakePhotoByteArray;
    }
}
