package expo.modules.imagepicker;

import expo.modules.kotlin.types.Enumerable;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.apache.commons.codec.language.p027bm.Rule;

/* compiled from: ImagePickerOptions.kt */
@Metadata(m184d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\b\u0080\u0001\u0018\u0000 \u000e2\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002:\u0001\u000eB\u000f\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0006\u0010\b\u001a\u00020\u0004J\u0006\u0010\t\u001a\u00020\u0004J\u0006\u0010\n\u001a\u00020\u0004R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\u000bj\u0002\b\fj\u0002\b\r¨\u0006\u000f"}, m183d2 = {"Lexpo/modules/imagepicker/MediaTypes;", "", "Lexpo/modules/kotlin/types/Enumerable;", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "toCameraIntentAction", "toFileExtension", "toMimeType", "IMAGES", "VIDEOS", Rule.ALL, "Companion", "expo-image-picker_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public enum MediaTypes implements Enumerable {
    IMAGES("Images"),
    VIDEOS("Videos"),
    ALL("All");
    
    @Deprecated
    public static final String AllMimeType = "*/*";
    private static final Companion Companion = new Companion(null);
    @Deprecated
    public static final String ImageAllMimeType = "image/*";
    @Deprecated
    public static final String VideoAllMimeType = "video/*";
    private final String value;

    /* compiled from: ImagePickerOptions.kt */
    @Metadata(m182k = 3, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes4.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] r0 = new int[MediaTypes.values().length];
            r0[MediaTypes.IMAGES.ordinal()] = 1;
            r0[MediaTypes.VIDEOS.ordinal()] = 2;
            r0[MediaTypes.ALL.ordinal()] = 3;
            $EnumSwitchMapping$0 = r0;
        }
    }

    MediaTypes(String str) {
        this.value = str;
    }

    public final String getValue() {
        return this.value;
    }

    public final String toMimeType() {
        int r0 = WhenMappings.$EnumSwitchMapping$0[ordinal()];
        if (r0 != 1) {
            if (r0 != 2) {
                if (r0 == 3) {
                    return AllMimeType;
                }
                throw new NoWhenBranchMatchedException();
            }
            return VideoAllMimeType;
        }
        return ImageAllMimeType;
    }

    public final String toFileExtension() {
        return WhenMappings.$EnumSwitchMapping$0[ordinal()] == 2 ? ".mp4" : ".jpeg";
    }

    public final String toCameraIntentAction() {
        return WhenMappings.$EnumSwitchMapping$0[ordinal()] == 2 ? "android.media.action.VIDEO_CAPTURE" : "android.media.action.IMAGE_CAPTURE";
    }

    /* compiled from: ImagePickerOptions.kt */
    @Metadata(m184d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, m183d2 = {"Lexpo/modules/imagepicker/MediaTypes$Companion;", "", "()V", "AllMimeType", "", "ImageAllMimeType", "VideoAllMimeType", "expo-image-picker_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes4.dex */
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
