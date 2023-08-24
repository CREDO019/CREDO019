package expo.modules.imagepicker;

import com.RNFetchBlob.RNFetchBlobConst;
import expo.modules.kotlin.exception.CodedException;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m184d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, m183d2 = {"Lexpo/modules/imagepicker/FailedToCreateFileException;", "Lexpo/modules/kotlin/exception/CodedException;", RNFetchBlobConst.RNFB_RESPONSE_PATH, "", "cause", "", "(Ljava/lang/String;Ljava/lang/Throwable;)V", "expo-image-picker_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* renamed from: expo.modules.imagepicker.FailedToCreateFileException */
/* loaded from: classes4.dex */
public final class ImagePickerExceptions extends CodedException {
    public /* synthetic */ ImagePickerExceptions(String str, Throwable th, int r3, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (r3 & 2) != 0 ? null : th);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ImagePickerExceptions(String path, Throwable th) {
        super("Failed to create the file '" + path + "'", th);
        Intrinsics.checkNotNullParameter(path, "path");
    }
}
