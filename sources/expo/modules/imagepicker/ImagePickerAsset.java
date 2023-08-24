package expo.modules.imagepicker;

import android.os.Bundle;
import androidx.core.app.FrameMetricsAggregator;
import com.RNFetchBlob.RNFetchBlobConst;
import com.facebook.react.uimanager.ViewProps;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import expo.modules.kotlin.records.Field;
import expo.modules.kotlin.records.Record;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ImagePickerResponse.kt */
@Metadata(m184d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u001d\b\u0000\u0018\u00002\u00020\u0001Bi\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\u000fR\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u001e\u0010\n\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0014\u0010\u0011\u001a\u0004\b\u0015\u0010\u0013R \u0010\r\u001a\u0004\u0018\u00010\b8\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u0019\u0012\u0004\b\u0016\u0010\u0011\u001a\u0004\b\u0017\u0010\u0018R\u001e\u0010\u000b\u001a\u0004\u0018\u00010\f8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001a\u0010\u0011\u001a\u0004\b\u001b\u0010\u001cR\u001c\u0010\t\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001d\u0010\u0011\u001a\u0004\b\u001e\u0010\u001fR \u0010\u000e\u001a\u0004\u0018\u00010\b8\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u0019\u0012\u0004\b \u0010\u0011\u001a\u0004\b!\u0010\u0018R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\"\u0010\u0011\u001a\u0004\b#\u0010$R\u001c\u0010\u0006\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b%\u0010\u0011\u001a\u0004\b&\u0010\u0013R\u001c\u0010\u0007\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b'\u0010\u0011\u001a\u0004\b(\u0010\u001f¨\u0006)"}, m183d2 = {"Lexpo/modules/imagepicker/ImagePickerAsset;", "Lexpo/modules/kotlin/records/Record;", "assetId", "", SessionDescription.ATTR_TYPE, "Lexpo/modules/imagepicker/MediaType;", "uri", "width", "", "height", RNFetchBlobConst.RNFB_RESPONSE_BASE64, "exif", "Landroid/os/Bundle;", "duration", ViewProps.ROTATION, "(Ljava/lang/String;Lexpo/modules/imagepicker/MediaType;Ljava/lang/String;IILjava/lang/String;Landroid/os/Bundle;Ljava/lang/Integer;Ljava/lang/Integer;)V", "getAssetId$annotations", "()V", "getAssetId", "()Ljava/lang/String;", "getBase64$annotations", "getBase64", "getDuration$annotations", "getDuration", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getExif$annotations", "getExif", "()Landroid/os/Bundle;", "getHeight$annotations", "getHeight", "()I", "getRotation$annotations", "getRotation", "getType$annotations", "getType", "()Lexpo/modules/imagepicker/MediaType;", "getUri$annotations", "getUri", "getWidth$annotations", "getWidth", "expo-image-picker_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class ImagePickerAsset implements Record {
    private final String assetId;
    private final String base64;
    private final Integer duration;
    private final Bundle exif;
    private final int height;
    private final Integer rotation;
    private final MediaType type;
    private final String uri;
    private final int width;

    public ImagePickerAsset() {
        this(null, null, null, 0, 0, null, null, null, null, FrameMetricsAggregator.EVERY_DURATION, null);
    }

    @Field
    public static /* synthetic */ void getAssetId$annotations() {
    }

    @Field
    public static /* synthetic */ void getBase64$annotations() {
    }

    @Field
    public static /* synthetic */ void getDuration$annotations() {
    }

    @Field
    public static /* synthetic */ void getExif$annotations() {
    }

    @Field
    public static /* synthetic */ void getHeight$annotations() {
    }

    @Field
    public static /* synthetic */ void getRotation$annotations() {
    }

    @Field
    public static /* synthetic */ void getType$annotations() {
    }

    @Field
    public static /* synthetic */ void getUri$annotations() {
    }

    @Field
    public static /* synthetic */ void getWidth$annotations() {
    }

    public ImagePickerAsset(String str, MediaType type, String uri, int r5, int r6, String str2, Bundle bundle, Integer num, Integer num2) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(uri, "uri");
        this.assetId = str;
        this.type = type;
        this.uri = uri;
        this.width = r5;
        this.height = r6;
        this.base64 = str2;
        this.exif = bundle;
        this.duration = num;
        this.rotation = num2;
    }

    public final String getAssetId() {
        return this.assetId;
    }

    public /* synthetic */ ImagePickerAsset(String str, MediaType mediaType, String str2, int r14, int r15, String str3, Bundle bundle, Integer num, Integer num2, int r20, DefaultConstructorMarker defaultConstructorMarker) {
        this((r20 & 1) != 0 ? null : str, (r20 & 2) != 0 ? MediaType.IMAGE : mediaType, (r20 & 4) != 0 ? "" : str2, (r20 & 8) != 0 ? 0 : r14, (r20 & 16) == 0 ? r15 : 0, (r20 & 32) != 0 ? null : str3, (r20 & 64) != 0 ? null : bundle, (r20 & 128) != 0 ? null : num, (r20 & 256) == 0 ? num2 : null);
    }

    public final MediaType getType() {
        return this.type;
    }

    public final String getUri() {
        return this.uri;
    }

    public final int getWidth() {
        return this.width;
    }

    public final int getHeight() {
        return this.height;
    }

    public final String getBase64() {
        return this.base64;
    }

    public final Bundle getExif() {
        return this.exif;
    }

    public final Integer getDuration() {
        return this.duration;
    }

    public final Integer getRotation() {
        return this.rotation;
    }
}
