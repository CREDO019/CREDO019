package com.canhub.cropper;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.net.Uri;
import com.canhub.cropper.CropImageView;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CropImageContractOptions.kt */
@Metadata(m184d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\r\n\u0002\b\u0011\n\u0002\u0010\u0007\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\b\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001f\u0010\r\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\u000e\u0010\u0013\u001a\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u0012J\u000e\u0010\u0015\u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u000fJ\u000e\u0010\u001a\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u000fJ\u000e\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u000fJ\u0016\u0010\u001e\u001a\u00020\u00002\u0006\u0010\u001f\u001a\u00020\u00122\u0006\u0010 \u001a\u00020\u0012J\u000e\u0010!\u001a\u00020\u00002\u0006\u0010\"\u001a\u00020\u000fJ\u000e\u0010#\u001a\u00020\u00002\u0006\u0010$\u001a\u00020\u0012J\u000e\u0010%\u001a\u00020\u00002\u0006\u0010&\u001a\u00020\u0012J\u000e\u0010'\u001a\u00020\u00002\u0006\u0010(\u001a\u00020)J\u000e\u0010*\u001a\u00020\u00002\u0006\u0010+\u001a\u00020)J\u000e\u0010,\u001a\u00020\u00002\u0006\u0010-\u001a\u00020)J\u000e\u0010.\u001a\u00020\u00002\u0006\u0010/\u001a\u00020\u0012J\u000e\u00100\u001a\u00020\u00002\u0006\u00101\u001a\u00020)J\u000e\u00102\u001a\u00020\u00002\u0006\u00103\u001a\u00020\u000fJ\u000e\u00104\u001a\u00020\u00002\u0006\u00105\u001a\u00020\u0012J\u000e\u00106\u001a\u00020\u00002\u0006\u00107\u001a\u00020)J\u000e\u00108\u001a\u00020\u00002\u0006\u00109\u001a\u00020:J\u0010\u0010;\u001a\u00020\u00002\b\b\u0001\u0010<\u001a\u00020\u0012J\u0010\u0010=\u001a\u00020\u00002\b\u0010>\u001a\u0004\u0018\u00010\u0017J\u000e\u0010?\u001a\u00020\u00002\u0006\u0010@\u001a\u00020AJ\u000e\u0010B\u001a\u00020\u00002\u0006\u0010C\u001a\u00020\u000fJ\u000e\u0010D\u001a\u00020\u00002\u0006\u0010E\u001a\u00020\u000fJ\u000e\u0010F\u001a\u00020\u00002\u0006\u0010G\u001a\u00020\u000fJ\u000e\u0010H\u001a\u00020\u00002\u0006\u0010I\u001a\u00020JJ\u000e\u0010K\u001a\u00020\u00002\u0006\u0010L\u001a\u00020\u0012J\u000e\u0010M\u001a\u00020\u00002\u0006\u0010N\u001a\u00020)J\u0016\u0010O\u001a\u00020\u00002\u0006\u0010P\u001a\u00020\u000f2\u0006\u0010Q\u001a\u00020\u000fJ\u000e\u0010R\u001a\u00020\u00002\u0006\u0010S\u001a\u00020)J\u0010\u0010T\u001a\u00020\u00002\b\u0010U\u001a\u0004\u0018\u00010VJ\u000e\u0010W\u001a\u00020\u00002\u0006\u0010X\u001a\u00020\u0012J\u0014\u0010Y\u001a\u00020\u00052\f\u0010Z\u001a\b\u0012\u0004\u0012\u00020\\0[J\u000e\u0010]\u001a\u00020\u00052\u0006\u0010^\u001a\u00020\\J\u0016\u0010_\u001a\u00020\u00002\u0006\u0010`\u001a\u00020\u00122\u0006\u0010a\u001a\u00020\u0012J\u000e\u0010b\u001a\u00020\u00002\u0006\u0010c\u001a\u00020\u0012J\u0016\u0010d\u001a\u00020\u00002\u0006\u0010e\u001a\u00020\u00122\u0006\u0010f\u001a\u00020\u0012J\u0016\u0010g\u001a\u00020\u00002\u0006\u0010h\u001a\u00020\u00122\u0006\u0010i\u001a\u00020\u0012J\u000e\u0010j\u001a\u00020\u00002\u0006\u0010k\u001a\u00020\u000fJ\u000e\u0010l\u001a\u00020\u00002\u0006\u0010m\u001a\u00020\u000fJ\u000e\u0010n\u001a\u00020\u00002\u0006\u0010o\u001a\u00020pJ\u000e\u0010q\u001a\u00020\u00002\u0006\u0010r\u001a\u00020\u0012J\u0010\u0010s\u001a\u00020\u00002\b\u0010t\u001a\u0004\u0018\u00010\u0003J\u0016\u0010u\u001a\u00020\u00002\u0006\u0010v\u001a\u00020\u00122\u0006\u0010w\u001a\u00020\u0012J\u001e\u0010u\u001a\u00020\u00002\u0006\u0010v\u001a\u00020\u00122\u0006\u0010w\u001a\u00020\u00122\u0006\u0010x\u001a\u00020yJ\u000e\u0010z\u001a\u00020\u00002\u0006\u0010{\u001a\u00020\u0012J\u000e\u0010|\u001a\u00020\u00002\u0006\u0010}\u001a\u00020~J\u000f\u0010\u007f\u001a\u00020\u00002\u0007\u0010\u0080\u0001\u001a\u00020\u000fJ\u0010\u0010\u0081\u0001\u001a\u00020\u00002\u0007\u0010\u0082\u0001\u001a\u00020\u000fJ\u0010\u0010\u0083\u0001\u001a\u00020\u00052\u0007\u0010\u0084\u0001\u001a\u00020\u000fJ\u0010\u0010\u0085\u0001\u001a\u00020\u00002\u0007\u0010\u0086\u0001\u001a\u00020\u000fJ\u0010\u0010\u0087\u0001\u001a\u00020\u00002\u0007\u0010\u0088\u0001\u001a\u00020)J\u0010\u0010\u0089\u0001\u001a\u00020\u00002\u0007\u0010\u008a\u0001\u001a\u00020)J\n\u0010\u008b\u0001\u001a\u00020\\HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u008c\u0001"}, m183d2 = {"Lcom/canhub/cropper/CropImageContractOptions;", "", "uri", "Landroid/net/Uri;", "cropImageOptions", "Lcom/canhub/cropper/CropImageOptions;", "(Landroid/net/Uri;Lcom/canhub/cropper/CropImageOptions;)V", "getCropImageOptions", "()Lcom/canhub/cropper/CropImageOptions;", "getUri", "()Landroid/net/Uri;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "setActivityMenuIconColor", "activityMenuIconColor", "setActivityTitle", "activityTitle", "", "setAllowCounterRotation", "allowCounterRotation", "setAllowFlipping", "allowFlipping", "setAllowRotation", "allowRotation", "setAspectRatio", "aspectRatioX", "aspectRatioY", "setAutoZoomEnabled", "autoZoomEnabled", "setBackgroundColor", "backgroundColor", "setBorderCornerColor", "borderCornerColor", "setBorderCornerLength", "borderCornerLength", "", "setBorderCornerOffset", "borderCornerOffset", "setBorderCornerThickness", "borderCornerThickness", "setBorderLineColor", "borderLineColor", "setBorderLineThickness", "borderLineThickness", "setCenterMoveEnabled", "centerMoveEnabled", "setCircleCornerFillColor", "circleFillColorHexValue", "setCropCornerRadius", "cornerRadius", "setCropCornerShape", "cornerShape", "Lcom/canhub/cropper/CropImageView$CropCornerShape;", "setCropMenuCropButtonIcon", "drawableResource", "setCropMenuCropButtonTitle", "title", "setCropShape", "cropShape", "Lcom/canhub/cropper/CropImageView$CropShape;", "setFixAspectRatio", "fixAspectRatio", "setFlipHorizontally", "flipHorizontally", "setFlipVertically", "flipVertically", "setGuidelines", "guidelines", "Lcom/canhub/cropper/CropImageView$Guidelines;", "setGuidelinesColor", "guidelinesColor", "setGuidelinesThickness", "guidelinesThickness", "setImageSource", "includeGallery", "includeCamera", "setInitialCropWindowPaddingRatio", "initialCropWindowPaddingRatio", "setInitialCropWindowRectangle", "initialCropWindowRectangle", "Landroid/graphics/Rect;", "setInitialRotation", "initialRotation", "setIntentChooserPriorityList", "priorityAppPackages", "", "", "setIntentChooserTitle", "intentChooserTitle", "setMaxCropResultSize", "maxCropResultWidth", "maxCropResultHeight", "setMaxZoom", "maxZoom", "setMinCropResultSize", "minCropResultWidth", "minCropResultHeight", "setMinCropWindowSize", "minCropWindowWidth", "minCropWindowHeight", "setMultiTouchEnabled", "multiTouchEnabled", "setNoOutputImage", "noOutputImage", "setOutputCompressFormat", "outputCompressFormat", "Landroid/graphics/Bitmap$CompressFormat;", "setOutputCompressQuality", "outputCompressQuality", "setOutputUri", "outputUri", "setRequestedSize", "reqWidth", "reqHeight", "reqSizeOptions", "Lcom/canhub/cropper/CropImageView$RequestSizeOptions;", "setRotationDegrees", "rotationDegrees", "setScaleType", "scaleType", "Lcom/canhub/cropper/CropImageView$ScaleType;", "setShowCropLabel", "showCropLabel", "setShowCropOverlay", "showCropOverlay", "setShowIntentChooser", "showIntentChooser", "setSkipEditing", "skipEditing", "setSnapRadius", "snapRadius", "setTouchRadius", "touchRadius", "toString", "cropper_release"}, m182k = 1, m181mv = {1, 5, 1}, m179xi = 48)
/* loaded from: classes.dex */
public final class CropImageContractOptions {
    private final CropImageOptions cropImageOptions;
    private final Uri uri;

    public static /* synthetic */ CropImageContractOptions copy$default(CropImageContractOptions cropImageContractOptions, Uri uri, CropImageOptions cropImageOptions, int r3, Object obj) {
        if ((r3 & 1) != 0) {
            uri = cropImageContractOptions.uri;
        }
        if ((r3 & 2) != 0) {
            cropImageOptions = cropImageContractOptions.cropImageOptions;
        }
        return cropImageContractOptions.copy(uri, cropImageOptions);
    }

    public final Uri component1() {
        return this.uri;
    }

    public final CropImageOptions component2() {
        return this.cropImageOptions;
    }

    public final CropImageContractOptions copy(Uri uri, CropImageOptions cropImageOptions) {
        Intrinsics.checkNotNullParameter(cropImageOptions, "cropImageOptions");
        return new CropImageContractOptions(uri, cropImageOptions);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof CropImageContractOptions) {
            CropImageContractOptions cropImageContractOptions = (CropImageContractOptions) obj;
            return Intrinsics.areEqual(this.uri, cropImageContractOptions.uri) && Intrinsics.areEqual(this.cropImageOptions, cropImageContractOptions.cropImageOptions);
        }
        return false;
    }

    public int hashCode() {
        Uri uri = this.uri;
        return ((uri == null ? 0 : uri.hashCode()) * 31) + this.cropImageOptions.hashCode();
    }

    public String toString() {
        Uri uri = this.uri;
        CropImageOptions cropImageOptions = this.cropImageOptions;
        return "CropImageContractOptions(uri=" + uri + ", cropImageOptions=" + cropImageOptions + ")";
    }

    public CropImageContractOptions(Uri uri, CropImageOptions cropImageOptions) {
        Intrinsics.checkNotNullParameter(cropImageOptions, "cropImageOptions");
        this.uri = uri;
        this.cropImageOptions = cropImageOptions;
    }

    public final Uri getUri() {
        return this.uri;
    }

    public final CropImageOptions getCropImageOptions() {
        return this.cropImageOptions;
    }

    public final CropImageContractOptions setImageSource(boolean z, boolean z2) {
        this.cropImageOptions.imageSourceIncludeGallery = z;
        this.cropImageOptions.imageSourceIncludeCamera = z2;
        return this;
    }

    public final CropImageContractOptions setCropShape(CropImageView.CropShape cropShape) {
        Intrinsics.checkNotNullParameter(cropShape, "cropShape");
        this.cropImageOptions.cropShape = cropShape;
        return this;
    }

    public final CropImageContractOptions setCropCornerShape(CropImageView.CropCornerShape cornerShape) {
        Intrinsics.checkNotNullParameter(cornerShape, "cornerShape");
        this.cropImageOptions.cornerShape = cornerShape;
        return this;
    }

    public final CropImageContractOptions setCircleCornerFillColor(int r2) {
        this.cropImageOptions.circleCornerFillColorHexValue = r2;
        return this;
    }

    public final CropImageContractOptions setCropCornerRadius(float f) {
        this.cropImageOptions.cropCornerRadius = f;
        return this;
    }

    public final CropImageContractOptions setSnapRadius(float f) {
        this.cropImageOptions.snapRadius = f;
        return this;
    }

    public final CropImageContractOptions setTouchRadius(float f) {
        this.cropImageOptions.touchRadius = f;
        return this;
    }

    public final CropImageContractOptions setGuidelines(CropImageView.Guidelines guidelines) {
        Intrinsics.checkNotNullParameter(guidelines, "guidelines");
        this.cropImageOptions.guidelines = guidelines;
        return this;
    }

    public final CropImageContractOptions setScaleType(CropImageView.ScaleType scaleType) {
        Intrinsics.checkNotNullParameter(scaleType, "scaleType");
        this.cropImageOptions.scaleType = scaleType;
        return this;
    }

    public final CropImageContractOptions setShowCropOverlay(boolean z) {
        this.cropImageOptions.showCropOverlay = z;
        return this;
    }

    public final CropImageContractOptions setShowCropLabel(boolean z) {
        this.cropImageOptions.showCropLabel = z;
        return this;
    }

    public final CropImageContractOptions setAutoZoomEnabled(boolean z) {
        this.cropImageOptions.autoZoomEnabled = z;
        return this;
    }

    public final CropImageContractOptions setMultiTouchEnabled(boolean z) {
        this.cropImageOptions.multiTouchEnabled = z;
        return this;
    }

    public final CropImageContractOptions setCenterMoveEnabled(boolean z) {
        this.cropImageOptions.centerMoveEnabled = z;
        return this;
    }

    public final CropImageContractOptions setMaxZoom(int r2) {
        this.cropImageOptions.maxZoom = r2;
        return this;
    }

    public final CropImageContractOptions setInitialCropWindowPaddingRatio(float f) {
        this.cropImageOptions.initialCropWindowPaddingRatio = f;
        return this;
    }

    public final CropImageContractOptions setFixAspectRatio(boolean z) {
        this.cropImageOptions.fixAspectRatio = z;
        return this;
    }

    public final CropImageContractOptions setAspectRatio(int r2, int r3) {
        this.cropImageOptions.aspectRatioX = r2;
        this.cropImageOptions.aspectRatioY = r3;
        this.cropImageOptions.fixAspectRatio = true;
        return this;
    }

    public final CropImageContractOptions setBorderLineThickness(float f) {
        this.cropImageOptions.borderLineThickness = f;
        return this;
    }

    public final CropImageContractOptions setBorderLineColor(int r2) {
        this.cropImageOptions.borderLineColor = r2;
        return this;
    }

    public final CropImageContractOptions setBorderCornerThickness(float f) {
        this.cropImageOptions.borderCornerThickness = f;
        return this;
    }

    public final CropImageContractOptions setBorderCornerOffset(float f) {
        this.cropImageOptions.borderCornerOffset = f;
        return this;
    }

    public final CropImageContractOptions setBorderCornerLength(float f) {
        this.cropImageOptions.borderCornerLength = f;
        return this;
    }

    public final CropImageContractOptions setBorderCornerColor(int r2) {
        this.cropImageOptions.borderCornerColor = r2;
        return this;
    }

    public final CropImageContractOptions setGuidelinesThickness(float f) {
        this.cropImageOptions.guidelinesThickness = f;
        return this;
    }

    public final CropImageContractOptions setGuidelinesColor(int r2) {
        this.cropImageOptions.guidelinesColor = r2;
        return this;
    }

    public final CropImageContractOptions setBackgroundColor(int r2) {
        this.cropImageOptions.backgroundColor = r2;
        return this;
    }

    public final CropImageContractOptions setMinCropWindowSize(int r2, int r3) {
        this.cropImageOptions.minCropWindowWidth = r2;
        this.cropImageOptions.minCropWindowHeight = r3;
        return this;
    }

    public final CropImageContractOptions setMinCropResultSize(int r2, int r3) {
        this.cropImageOptions.minCropResultWidth = r2;
        this.cropImageOptions.minCropResultHeight = r3;
        return this;
    }

    public final CropImageContractOptions setMaxCropResultSize(int r2, int r3) {
        this.cropImageOptions.maxCropResultWidth = r2;
        this.cropImageOptions.maxCropResultHeight = r3;
        return this;
    }

    public final CropImageContractOptions setActivityTitle(CharSequence activityTitle) {
        Intrinsics.checkNotNullParameter(activityTitle, "activityTitle");
        this.cropImageOptions.activityTitle = activityTitle;
        return this;
    }

    public final CropImageContractOptions setActivityMenuIconColor(int r2) {
        this.cropImageOptions.activityMenuIconColor = r2;
        return this;
    }

    public final CropImageContractOptions setOutputUri(Uri uri) {
        this.cropImageOptions.customOutputUri = uri;
        return this;
    }

    public final CropImageContractOptions setOutputCompressFormat(Bitmap.CompressFormat outputCompressFormat) {
        Intrinsics.checkNotNullParameter(outputCompressFormat, "outputCompressFormat");
        this.cropImageOptions.outputCompressFormat = outputCompressFormat;
        return this;
    }

    public final CropImageContractOptions setOutputCompressQuality(int r2) {
        this.cropImageOptions.outputCompressQuality = r2;
        return this;
    }

    public final CropImageContractOptions setRequestedSize(int r2, int r3) {
        return setRequestedSize(r2, r3, CropImageView.RequestSizeOptions.RESIZE_INSIDE);
    }

    public final CropImageContractOptions setRequestedSize(int r2, int r3, CropImageView.RequestSizeOptions reqSizeOptions) {
        Intrinsics.checkNotNullParameter(reqSizeOptions, "reqSizeOptions");
        this.cropImageOptions.outputRequestWidth = r2;
        this.cropImageOptions.outputRequestHeight = r3;
        this.cropImageOptions.outputRequestSizeOptions = reqSizeOptions;
        return this;
    }

    public final CropImageContractOptions setNoOutputImage(boolean z) {
        this.cropImageOptions.noOutputImage = z;
        return this;
    }

    public final CropImageContractOptions setInitialCropWindowRectangle(Rect rect) {
        this.cropImageOptions.initialCropWindowRectangle = rect;
        return this;
    }

    public final CropImageContractOptions setInitialRotation(int r2) {
        this.cropImageOptions.initialRotation = (r2 + CropImageOptions.DEGREES_360) % CropImageOptions.DEGREES_360;
        return this;
    }

    public final CropImageContractOptions setAllowRotation(boolean z) {
        this.cropImageOptions.allowRotation = z;
        return this;
    }

    public final CropImageContractOptions setAllowFlipping(boolean z) {
        this.cropImageOptions.allowFlipping = z;
        return this;
    }

    public final CropImageContractOptions setAllowCounterRotation(boolean z) {
        this.cropImageOptions.allowCounterRotation = z;
        return this;
    }

    public final CropImageContractOptions setRotationDegrees(int r2) {
        this.cropImageOptions.rotationDegrees = (r2 + CropImageOptions.DEGREES_360) % CropImageOptions.DEGREES_360;
        return this;
    }

    public final CropImageContractOptions setFlipHorizontally(boolean z) {
        this.cropImageOptions.flipHorizontally = z;
        return this;
    }

    public final CropImageContractOptions setFlipVertically(boolean z) {
        this.cropImageOptions.flipVertically = z;
        return this;
    }

    public final CropImageContractOptions setCropMenuCropButtonTitle(CharSequence charSequence) {
        this.cropImageOptions.cropMenuCropButtonTitle = charSequence;
        return this;
    }

    public final CropImageContractOptions setCropMenuCropButtonIcon(int r2) {
        this.cropImageOptions.cropMenuCropButtonIcon = r2;
        return this;
    }

    public final CropImageContractOptions setSkipEditing(boolean z) {
        this.cropImageOptions.skipEditing = z;
        this.cropImageOptions.showCropOverlay = !z;
        return this;
    }

    public final CropImageOptions setShowIntentChooser(boolean z) {
        CropImageOptions cropImageOptions = this.cropImageOptions;
        cropImageOptions.showIntentChooser = z;
        return cropImageOptions;
    }

    public final CropImageOptions setIntentChooserTitle(String intentChooserTitle) {
        Intrinsics.checkNotNullParameter(intentChooserTitle, "intentChooserTitle");
        CropImageOptions cropImageOptions = this.cropImageOptions;
        cropImageOptions.intentChooserTitle = intentChooserTitle;
        return cropImageOptions;
    }

    public final CropImageOptions setIntentChooserPriorityList(List<String> priorityAppPackages) {
        Intrinsics.checkNotNullParameter(priorityAppPackages, "priorityAppPackages");
        CropImageOptions cropImageOptions = this.cropImageOptions;
        cropImageOptions.intentChooserPriorityList = priorityAppPackages;
        return cropImageOptions;
    }
}
