package expo.modules.imagepicker;

import expo.modules.kotlin.exception.CodedException;
import kotlin.Metadata;

/* compiled from: ImagePickerExceptions.kt */
@Metadata(m184d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, m183d2 = {"Lexpo/modules/imagepicker/MissingActivityToHandleIntent;", "Lexpo/modules/kotlin/exception/CodedException;", "intentType", "", "(Ljava/lang/String;)V", "expo-image-picker_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class MissingActivityToHandleIntent extends CodedException {
    public MissingActivityToHandleIntent(String str) {
        super("Failed to resolve activity to handle the intent of type '" + str + "'", null, 2, null);
    }
}
