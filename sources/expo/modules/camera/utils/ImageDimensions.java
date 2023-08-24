package expo.modules.camera.utils;

import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.react.uimanager.ViewProps;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: ImageDimensions.kt */
@Metadata(m184d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B+\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\u0012\u001a\u00020\u0003HÂ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÂ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J1\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\r2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\tR\u0014\u0010\f\u001a\u00020\r8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\u000eR\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\tR\u0011\u0010\u0010\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\t¨\u0006\u001c"}, m183d2 = {"Lexpo/modules/camera/utils/ImageDimensions;", "", "mWidth", "", "mHeight", ViewProps.ROTATION, "facing", "(IIII)V", "getFacing", "()I", "height", "getHeight", "isLandscape", "", "()Z", "getRotation", "width", "getWidth", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "toString", "", "expo-camera_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class ImageDimensions {
    private final int facing;
    private final int mHeight;
    private final int mWidth;
    private final int rotation;

    public ImageDimensions(int r8, int r9) {
        this(r8, r9, 0, 0, 12, null);
    }

    public ImageDimensions(int r8, int r9, int r10) {
        this(r8, r9, r10, 0, 8, null);
    }

    private final int component1() {
        return this.mWidth;
    }

    private final int component2() {
        return this.mHeight;
    }

    public static /* synthetic */ ImageDimensions copy$default(ImageDimensions imageDimensions, int r1, int r2, int r3, int r4, int r5, Object obj) {
        if ((r5 & 1) != 0) {
            r1 = imageDimensions.mWidth;
        }
        if ((r5 & 2) != 0) {
            r2 = imageDimensions.mHeight;
        }
        if ((r5 & 4) != 0) {
            r3 = imageDimensions.rotation;
        }
        if ((r5 & 8) != 0) {
            r4 = imageDimensions.facing;
        }
        return imageDimensions.copy(r1, r2, r3, r4);
    }

    public final int component3() {
        return this.rotation;
    }

    public final int component4() {
        return this.facing;
    }

    public final ImageDimensions copy(int r2, int r3, int r4, int r5) {
        return new ImageDimensions(r2, r3, r4, r5);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ImageDimensions) {
            ImageDimensions imageDimensions = (ImageDimensions) obj;
            return this.mWidth == imageDimensions.mWidth && this.mHeight == imageDimensions.mHeight && this.rotation == imageDimensions.rotation && this.facing == imageDimensions.facing;
        }
        return false;
    }

    public int hashCode() {
        return (((((this.mWidth * 31) + this.mHeight) * 31) + this.rotation) * 31) + this.facing;
    }

    public String toString() {
        int r0 = this.mWidth;
        int r1 = this.mHeight;
        int r2 = this.rotation;
        int r3 = this.facing;
        return "ImageDimensions(mWidth=" + r0 + ", mHeight=" + r1 + ", rotation=" + r2 + ", facing=" + r3 + ")";
    }

    public ImageDimensions(int r1, int r2, int r3, int r4) {
        this.mWidth = r1;
        this.mHeight = r2;
        this.rotation = r3;
        this.facing = r4;
    }

    public /* synthetic */ ImageDimensions(int r1, int r2, int r3, int r4, int r5, DefaultConstructorMarker defaultConstructorMarker) {
        this(r1, r2, (r5 & 4) != 0 ? 0 : r3, (r5 & 8) != 0 ? -1 : r4);
    }

    public final int getFacing() {
        return this.facing;
    }

    public final int getRotation() {
        return this.rotation;
    }

    private final boolean isLandscape() {
        return this.rotation % RotationOptions.ROTATE_180 == 90;
    }

    public final int getWidth() {
        if (isLandscape()) {
            return this.mHeight;
        }
        return this.mWidth;
    }

    public final int getHeight() {
        if (isLandscape()) {
            return this.mWidth;
        }
        return this.mHeight;
    }
}
