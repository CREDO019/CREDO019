package expo.modules.imagepicker;

import android.net.Uri;
import com.RNFetchBlob.RNFetchBlobConst;
import expo.modules.imagepicker.contracts.CameraContractOptions;
import expo.modules.imagepicker.contracts.ImageLibraryContractOptions;
import expo.modules.kotlin.records.Field;
import expo.modules.kotlin.records.Record;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.Tuples;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ImagePickerOptions.kt */
@Metadata(m184d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0006\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u000e\u00102\u001a\u0002032\u0006\u00104\u001a\u000205J\u0006\u00106\u001a\u000207R$\u0010\u0004\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0006\u0010\u0003\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR$\u0010\u000b\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\f\u0010\u0003\u001a\u0004\b\r\u0010\b\"\u0004\b\u000e\u0010\nR2\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u00108\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0012\u0010\u0003\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R$\u0010\u0017\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0018\u0010\u0003\u001a\u0004\b\u0019\u0010\b\"\u0004\b\u001a\u0010\nR$\u0010\u001b\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u001c\u0010\u0003\u001a\u0004\b\u001d\u0010\b\"\u0004\b\u001e\u0010\nR$\u0010\u001f\u001a\u00020 8\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b!\u0010\u0003\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R$\u0010&\u001a\u00020'8\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b(\u0010\u0003\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u001e\u0010-\u001a\u00020\u00118\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010/\"\u0004\b0\u00101¨\u00068"}, m183d2 = {"Lexpo/modules/imagepicker/ImagePickerOptions;", "Lexpo/modules/kotlin/records/Record;", "Ljava/io/Serializable;", "()V", "allowsEditing", "", "getAllowsEditing$annotations", "getAllowsEditing", "()Z", "setAllowsEditing", "(Z)V", "allowsMultipleSelection", "getAllowsMultipleSelection$annotations", "getAllowsMultipleSelection", "setAllowsMultipleSelection", "aspect", "Lkotlin/Pair;", "", "getAspect$annotations", "getAspect", "()Lkotlin/Pair;", "setAspect", "(Lkotlin/Pair;)V", RNFetchBlobConst.RNFB_RESPONSE_BASE64, "getBase64$annotations", "getBase64", "setBase64", "exif", "getExif$annotations", "getExif", "setExif", "mediaTypes", "Lexpo/modules/imagepicker/MediaTypes;", "getMediaTypes$annotations", "getMediaTypes", "()Lexpo/modules/imagepicker/MediaTypes;", "setMediaTypes", "(Lexpo/modules/imagepicker/MediaTypes;)V", "quality", "", "getQuality$annotations", "getQuality", "()D", "setQuality", "(D)V", "videoMaxDuration", "getVideoMaxDuration", "()I", "setVideoMaxDuration", "(I)V", "toCameraContractOptions", "Lexpo/modules/imagepicker/contracts/CameraContractOptions;", "uri", "Landroid/net/Uri;", "toImageLibraryContractOptions", "Lexpo/modules/imagepicker/contracts/ImageLibraryContractOptions;", "expo-image-picker_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class ImagePickerOptions implements Record, Serializable {
    private boolean allowsEditing;
    private boolean allowsMultipleSelection;
    private Tuples<Integer, Integer> aspect;
    private boolean base64;
    private boolean exif;
    private int videoMaxDuration;
    private double quality = 0.2d;
    private MediaTypes mediaTypes = MediaTypes.IMAGES;

    @Field
    public static /* synthetic */ void getAllowsEditing$annotations() {
    }

    @Field
    public static /* synthetic */ void getAllowsMultipleSelection$annotations() {
    }

    @Field
    public static /* synthetic */ void getAspect$annotations() {
    }

    @Field
    public static /* synthetic */ void getBase64$annotations() {
    }

    @Field
    public static /* synthetic */ void getExif$annotations() {
    }

    @Field
    public static /* synthetic */ void getMediaTypes$annotations() {
    }

    @Field
    public static /* synthetic */ void getQuality$annotations() {
    }

    public final boolean getAllowsEditing() {
        return this.allowsEditing;
    }

    public final void setAllowsEditing(boolean z) {
        this.allowsEditing = z;
    }

    public final boolean getAllowsMultipleSelection() {
        return this.allowsMultipleSelection;
    }

    public final void setAllowsMultipleSelection(boolean z) {
        this.allowsMultipleSelection = z;
    }

    public final double getQuality() {
        return this.quality;
    }

    public final void setQuality(double d) {
        this.quality = d;
    }

    public final boolean getBase64() {
        return this.base64;
    }

    public final void setBase64(boolean z) {
        this.base64 = z;
    }

    public final boolean getExif() {
        return this.exif;
    }

    public final void setExif(boolean z) {
        this.exif = z;
    }

    public final MediaTypes getMediaTypes() {
        return this.mediaTypes;
    }

    public final void setMediaTypes(MediaTypes mediaTypes) {
        Intrinsics.checkNotNullParameter(mediaTypes, "<set-?>");
        this.mediaTypes = mediaTypes;
    }

    public final int getVideoMaxDuration() {
        return this.videoMaxDuration;
    }

    public final void setVideoMaxDuration(int r1) {
        this.videoMaxDuration = r1;
    }

    public final Tuples<Integer, Integer> getAspect() {
        return this.aspect;
    }

    public final void setAspect(Tuples<Integer, Integer> tuples) {
        this.aspect = tuples;
    }

    public final CameraContractOptions toCameraContractOptions(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        return new CameraContractOptions(uri, this);
    }

    public final ImageLibraryContractOptions toImageLibraryContractOptions() {
        return new ImageLibraryContractOptions(this);
    }
}
