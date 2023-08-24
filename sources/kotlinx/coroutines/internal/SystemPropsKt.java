package kotlinx.coroutines.internal;

import kotlin.Metadata;

@Metadata(m184d1 = {"kotlinx/coroutines/internal/SystemPropsKt__SystemPropsKt", "kotlinx/coroutines/internal/SystemPropsKt__SystemProps_commonKt"}, m182k = 4, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes5.dex */
public final class SystemPropsKt {
    public static final int getAVAILABLE_PROCESSORS() {
        return SystemProps.getAVAILABLE_PROCESSORS();
    }

    public static final int systemProp(String str, int r1, int r2, int r3) {
        return SystemPropsKt__SystemProps_commonKt.systemProp(str, r1, r2, r3);
    }

    public static final long systemProp(String str, long j, long j2, long j3) {
        return SystemPropsKt__SystemProps_commonKt.systemProp(str, j, j2, j3);
    }

    public static final String systemProp(String str) {
        return SystemProps.systemProp(str);
    }

    public static final boolean systemProp(String str, boolean z) {
        return SystemPropsKt__SystemProps_commonKt.systemProp(str, z);
    }
}
