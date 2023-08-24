package expo.modules.kotlin.exception;

import expo.modules.kotlin.exception.CodedException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CodedException.kt */
@Metadata(m184d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0015\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003H\u0086\b¨\u0006\u0004"}, m183d2 = {"errorCodeOf", "", "T", "Lexpo/modules/kotlin/exception/CodedException;", "expo-modules-core_release"}, m182k = 2, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class CodedExceptionKt {
    public static final /* synthetic */ <T extends CodedException> String errorCodeOf() {
        CodedException.Companion companion = CodedException.Companion;
        Intrinsics.reifiedOperationMarker(4, "T");
        return companion.inferCode(CodedException.class);
    }
}
