package expo.modules.imagepicker;

import expo.modules.kotlin.exception.CodedException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ImagePickerExceptions.kt */
@Metadata(m184d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, m183d2 = {"Lexpo/modules/imagepicker/MissingModuleException;", "Lexpo/modules/kotlin/exception/CodedException;", "moduleName", "", "(Ljava/lang/String;)V", "expo-image-picker_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class MissingModuleException extends CodedException {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MissingModuleException(String moduleName) {
        super("Module '" + moduleName + "' not found. Are you sure all modules are linked correctly?", null, 2, null);
        Intrinsics.checkNotNullParameter(moduleName, "moduleName");
    }
}
