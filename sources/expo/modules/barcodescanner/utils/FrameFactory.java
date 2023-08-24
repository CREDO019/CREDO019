package expo.modules.barcodescanner.utils;

import android.graphics.Bitmap;
import com.facebook.react.uimanager.ViewProps;
import com.google.android.gms.vision.Frame;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FrameFactory.kt */
@Metadata(m184d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J&\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\n¨\u0006\r"}, m183d2 = {"Lexpo/modules/barcodescanner/utils/FrameFactory;", "", "()V", "buildFrame", "Lexpo/modules/barcodescanner/utils/Frame;", "bitmap", "Landroid/graphics/Bitmap;", "bitmapData", "", "width", "", "height", ViewProps.ROTATION, "expo-barcode-scanner_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class FrameFactory {
    public static final FrameFactory INSTANCE = new FrameFactory();

    private FrameFactory() {
    }

    public final Frame buildFrame(byte[] bitmapData, int r10, int r11, int r12) {
        Intrinsics.checkNotNullParameter(bitmapData, "bitmapData");
        Frame.Builder builder = new Frame.Builder();
        builder.setImageData(ByteBuffer.wrap(bitmapData), r10, r11, 17);
        if (r12 == 90) {
            builder.setRotation(1);
        } else if (r12 == 180) {
            builder.setRotation(2);
        } else if (r12 == 270) {
            builder.setRotation(3);
        } else {
            builder.setRotation(0);
        }
        ImageDimensions imageDimensions = new ImageDimensions(r10, r11, r12, 0, 8, null);
        com.google.android.gms.vision.Frame build = builder.build();
        Intrinsics.checkNotNullExpressionValue(build, "builder.build()");
        return new Frame(build, imageDimensions);
    }

    public final Frame buildFrame(Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        Frame.Builder builder = new Frame.Builder();
        builder.setBitmap(bitmap);
        ImageDimensions imageDimensions = new ImageDimensions(bitmap.getWidth(), bitmap.getHeight(), 0, 0, 12, null);
        com.google.android.gms.vision.Frame build = builder.build();
        Intrinsics.checkNotNullExpressionValue(build, "builder.build()");
        return new Frame(build, imageDimensions);
    }
}
