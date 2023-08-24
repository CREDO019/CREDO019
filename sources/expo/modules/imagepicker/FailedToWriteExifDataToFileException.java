package expo.modules.imagepicker;

import expo.modules.kotlin.exception.CodedException;
import kotlin.Metadata;

/* compiled from: ImagePickerExceptions.kt */
@Metadata(m184d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, m183d2 = {"Lexpo/modules/imagepicker/FailedToWriteExifDataToFileException;", "Lexpo/modules/kotlin/exception/CodedException;", "file", "Ljava/io/File;", "cause", "", "(Ljava/io/File;Ljava/lang/Throwable;)V", "expo-image-picker_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class FailedToWriteExifDataToFileException extends CodedException {
    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public FailedToWriteExifDataToFileException(java.io.File r3, java.lang.Throwable r4) {
        /*
            r2 = this;
            java.lang.String r0 = "file"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.lang.String r0 = "cause"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            android.net.Uri r3 = android.net.Uri.fromFile(r3)
            java.lang.String r0 = "fromFile(this)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Failed to write EXIF data to file '"
            r0.append(r1)
            r0.append(r3)
            java.lang.String r3 = r0.toString()
            r2.<init>(r3, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.imagepicker.FailedToWriteExifDataToFileException.<init>(java.io.File, java.lang.Throwable):void");
    }
}
