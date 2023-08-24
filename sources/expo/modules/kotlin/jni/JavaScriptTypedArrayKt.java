package expo.modules.kotlin.jni;

import kotlin.Metadata;

/* compiled from: JavaScriptTypedArray.kt */
@Metadata(m184d1 = {"\u0000\b\n\u0000\n\u0002\u0010\b\n\u0000\u001a\b\u0010\u0000\u001a\u00020\u0001H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0002"}, m183d2 = {"nextValue", "", "expo-modules-core_release"}, m182k = 2, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class JavaScriptTypedArrayKt {
    private static int nextValue = 1;

    /* JADX INFO: Access modifiers changed from: private */
    public static final int nextValue() {
        int r0 = nextValue;
        nextValue = r0 + 1;
        return r0;
    }
}
