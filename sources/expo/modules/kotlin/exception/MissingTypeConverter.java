package expo.modules.kotlin.exception;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KType;

/* compiled from: CodedException.kt */
@Metadata(m184d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, m183d2 = {"Lexpo/modules/kotlin/exception/MissingTypeConverter;", "Lexpo/modules/kotlin/exception/CodedException;", "forType", "Lkotlin/reflect/KType;", "(Lkotlin/reflect/KType;)V", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class MissingTypeConverter extends CodedException {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MissingTypeConverter(KType forType) {
        super("Cannot find type converter for '" + forType + "'.", null, 2, null);
        Intrinsics.checkNotNullParameter(forType, "forType");
    }
}
