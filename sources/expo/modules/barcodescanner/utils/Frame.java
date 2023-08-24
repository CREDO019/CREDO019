package expo.modules.barcodescanner.utils;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Frame.kt */
@Metadata(m184d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, m183d2 = {"Lexpo/modules/barcodescanner/utils/Frame;", "", "frame", "Lcom/google/android/gms/vision/Frame;", "dimensions", "Lexpo/modules/barcodescanner/utils/ImageDimensions;", "(Lcom/google/android/gms/vision/Frame;Lexpo/modules/barcodescanner/utils/ImageDimensions;)V", "getDimensions", "()Lexpo/modules/barcodescanner/utils/ImageDimensions;", "getFrame", "()Lcom/google/android/gms/vision/Frame;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "expo-barcode-scanner_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class Frame {
    private final ImageDimensions dimensions;
    private final com.google.android.gms.vision.Frame frame;

    public static /* synthetic */ Frame copy$default(Frame frame, com.google.android.gms.vision.Frame frame2, ImageDimensions imageDimensions, int r3, Object obj) {
        if ((r3 & 1) != 0) {
            frame2 = frame.frame;
        }
        if ((r3 & 2) != 0) {
            imageDimensions = frame.dimensions;
        }
        return frame.copy(frame2, imageDimensions);
    }

    public final com.google.android.gms.vision.Frame component1() {
        return this.frame;
    }

    public final ImageDimensions component2() {
        return this.dimensions;
    }

    public final Frame copy(com.google.android.gms.vision.Frame frame, ImageDimensions dimensions) {
        Intrinsics.checkNotNullParameter(frame, "frame");
        Intrinsics.checkNotNullParameter(dimensions, "dimensions");
        return new Frame(frame, dimensions);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Frame) {
            Frame frame = (Frame) obj;
            return Intrinsics.areEqual(this.frame, frame.frame) && Intrinsics.areEqual(this.dimensions, frame.dimensions);
        }
        return false;
    }

    public int hashCode() {
        return (this.frame.hashCode() * 31) + this.dimensions.hashCode();
    }

    public String toString() {
        com.google.android.gms.vision.Frame frame = this.frame;
        ImageDimensions imageDimensions = this.dimensions;
        return "Frame(frame=" + frame + ", dimensions=" + imageDimensions + ")";
    }

    public Frame(com.google.android.gms.vision.Frame frame, ImageDimensions dimensions) {
        Intrinsics.checkNotNullParameter(frame, "frame");
        Intrinsics.checkNotNullParameter(dimensions, "dimensions");
        this.frame = frame;
        this.dimensions = dimensions;
    }

    public final com.google.android.gms.vision.Frame getFrame() {
        return this.frame;
    }

    public final ImageDimensions getDimensions() {
        return this.dimensions;
    }
}
