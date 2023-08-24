package expo.modules.barcodescanner.utils;

import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.react.uimanager.ViewProps;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: ImageDimensions.kt */
@Metadata(m184d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\u0011\u001a\u00020\u0003HÂ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÂ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J1\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\r2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\tR\u0011\u0010\u000f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\t¨\u0006\u001b"}, m183d2 = {"Lexpo/modules/barcodescanner/utils/ImageDimensions;", "", "innerWidth", "", "innerHeight", ViewProps.ROTATION, "facing", "(IIII)V", "getFacing", "()I", "height", "getHeight", "isLandscape", "", "getRotation", "width", "getWidth", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "toString", "", "expo-barcode-scanner_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class ImageDimensions {
    private final int facing;
    private final int height;
    private final int innerHeight;
    private final int innerWidth;
    private final boolean isLandscape;
    private final int rotation;
    private final int width;

    private final int component1() {
        return this.innerWidth;
    }

    private final int component2() {
        return this.innerHeight;
    }

    public static /* synthetic */ ImageDimensions copy$default(ImageDimensions imageDimensions, int r1, int r2, int r3, int r4, int r5, Object obj) {
        if ((r5 & 1) != 0) {
            r1 = imageDimensions.innerWidth;
        }
        if ((r5 & 2) != 0) {
            r2 = imageDimensions.innerHeight;
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
            return this.innerWidth == imageDimensions.innerWidth && this.innerHeight == imageDimensions.innerHeight && this.rotation == imageDimensions.rotation && this.facing == imageDimensions.facing;
        }
        return false;
    }

    public int hashCode() {
        return (((((this.innerWidth * 31) + this.innerHeight) * 31) + this.rotation) * 31) + this.facing;
    }

    public String toString() {
        int r0 = this.innerWidth;
        int r1 = this.innerHeight;
        int r2 = this.rotation;
        int r3 = this.facing;
        return "ImageDimensions(innerWidth=" + r0 + ", innerHeight=" + r1 + ", rotation=" + r2 + ", facing=" + r3 + ")";
    }

    public ImageDimensions(int r1, int r2, int r3, int r4) {
        this.innerWidth = r1;
        this.innerHeight = r2;
        this.rotation = r3;
        this.facing = r4;
        boolean z = r3 % RotationOptions.ROTATE_180 == 90;
        this.isLandscape = z;
        this.width = z ? r2 : r1;
        this.height = z ? r1 : r2;
    }

    public /* synthetic */ ImageDimensions(int r1, int r2, int r3, int r4, int r5, DefaultConstructorMarker defaultConstructorMarker) {
        this(r1, r2, (r5 & 4) != 0 ? 0 : r3, (r5 & 8) != 0 ? -1 : r4);
    }

    public final int getRotation() {
        return this.rotation;
    }

    public final int getFacing() {
        return this.facing;
    }

    public final int getWidth() {
        return this.width;
    }

    public final int getHeight() {
        return this.height;
    }
}
