package expo.modules.imagepicker.contracts;

import android.net.Uri;
import expo.modules.imagepicker.ImagePickerOptions;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CropImageContract.kt */
@Metadata(m184d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0016"}, m183d2 = {"Lexpo/modules/imagepicker/contracts/CropImageContractOptions;", "Ljava/io/Serializable;", "sourceUri", "Landroid/net/Uri;", "options", "Lexpo/modules/imagepicker/ImagePickerOptions;", "(Landroid/net/Uri;Lexpo/modules/imagepicker/ImagePickerOptions;)V", "getOptions", "()Lexpo/modules/imagepicker/ImagePickerOptions;", "getSourceUri", "()Landroid/net/Uri;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "expo-image-picker_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class CropImageContractOptions implements Serializable {
    private final ImagePickerOptions options;
    private final Uri sourceUri;

    public static /* synthetic */ CropImageContractOptions copy$default(CropImageContractOptions cropImageContractOptions, Uri uri, ImagePickerOptions imagePickerOptions, int r3, Object obj) {
        if ((r3 & 1) != 0) {
            uri = cropImageContractOptions.sourceUri;
        }
        if ((r3 & 2) != 0) {
            imagePickerOptions = cropImageContractOptions.options;
        }
        return cropImageContractOptions.copy(uri, imagePickerOptions);
    }

    public final Uri component1() {
        return this.sourceUri;
    }

    public final ImagePickerOptions component2() {
        return this.options;
    }

    public final CropImageContractOptions copy(Uri sourceUri, ImagePickerOptions options) {
        Intrinsics.checkNotNullParameter(sourceUri, "sourceUri");
        Intrinsics.checkNotNullParameter(options, "options");
        return new CropImageContractOptions(sourceUri, options);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof CropImageContractOptions) {
            CropImageContractOptions cropImageContractOptions = (CropImageContractOptions) obj;
            return Intrinsics.areEqual(this.sourceUri, cropImageContractOptions.sourceUri) && Intrinsics.areEqual(this.options, cropImageContractOptions.options);
        }
        return false;
    }

    public int hashCode() {
        return (this.sourceUri.hashCode() * 31) + this.options.hashCode();
    }

    public String toString() {
        Uri uri = this.sourceUri;
        ImagePickerOptions imagePickerOptions = this.options;
        return "CropImageContractOptions(sourceUri=" + uri + ", options=" + imagePickerOptions + ")";
    }

    public CropImageContractOptions(Uri sourceUri, ImagePickerOptions options) {
        Intrinsics.checkNotNullParameter(sourceUri, "sourceUri");
        Intrinsics.checkNotNullParameter(options, "options");
        this.sourceUri = sourceUri;
        this.options = options;
    }

    public final Uri getSourceUri() {
        return this.sourceUri;
    }

    public final ImagePickerOptions getOptions() {
        return this.options;
    }
}
