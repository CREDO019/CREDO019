package expo.modules.imagepicker;

import expo.modules.kotlin.exception.CodedException;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: ImagePickerExceptions.kt */
@Metadata(m184d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, m183d2 = {"Lexpo/modules/imagepicker/FailedToWriteFileException;", "Lexpo/modules/kotlin/exception/CodedException;", "file", "Ljava/io/File;", "cause", "", "(Ljava/io/File;Ljava/lang/Throwable;)V", "expo-image-picker_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class FailedToWriteFileException extends CodedException {
    public FailedToWriteFileException() {
        this(null, null, 3, null);
    }

    public /* synthetic */ FailedToWriteFileException(File file, Throwable th, int r4, DefaultConstructorMarker defaultConstructorMarker) {
        this((r4 & 1) != 0 ? null : file, (r4 & 2) != 0 ? null : th);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public FailedToWriteFileException(java.io.File r3, java.lang.Throwable r4) {
        /*
            r2 = this;
            java.lang.String r0 = ""
            if (r3 != 0) goto L5
            goto L16
        L5:
            android.net.Uri r3 = android.net.Uri.fromFile(r3)
            java.lang.String r1 = "fromFile(this)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r1)
            java.lang.String r3 = r3.toString()
            if (r3 != 0) goto L15
            goto L16
        L15:
            r0 = r3
        L16:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r1 = "Failed to write a file '"
            r3.append(r1)
            r3.append(r0)
            java.lang.String r0 = "'"
            r3.append(r0)
            java.lang.String r3 = r3.toString()
            r2.<init>(r3, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.imagepicker.FailedToWriteFileException.<init>(java.io.File, java.lang.Throwable):void");
    }
}
