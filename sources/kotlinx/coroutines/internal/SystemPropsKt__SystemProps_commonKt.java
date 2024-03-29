package kotlinx.coroutines.internal;

import kotlin.Metadata;
import kotlin.text.StringsKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SystemProps.common.kt */
@Metadata(m184d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0001H\u0000\u001a,\u0010\u0000\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u0005H\u0000\u001a,\u0010\u0000\u001a\u00020\b2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\b2\b\b\u0002\u0010\u0006\u001a\u00020\b2\b\b\u0002\u0010\u0007\u001a\u00020\bH\u0000¨\u0006\t"}, m183d2 = {"systemProp", "", "propertyName", "", "defaultValue", "", "minValue", "maxValue", "", "kotlinx-coroutines-core"}, m182k = 5, m181mv = {1, 6, 0}, m179xi = 48, m178xs = "kotlinx/coroutines/internal/SystemPropsKt")
/* loaded from: classes5.dex */
public final /* synthetic */ class SystemPropsKt__SystemProps_commonKt {
    public static final boolean systemProp(String str, boolean z) {
        String systemProp = SystemPropsKt.systemProp(str);
        return systemProp != null ? Boolean.parseBoolean(systemProp) : z;
    }

    public static /* synthetic */ int systemProp$default(String str, int r1, int r2, int r3, int r4, Object obj) {
        if ((r4 & 4) != 0) {
            r2 = 1;
        }
        if ((r4 & 8) != 0) {
            r3 = Integer.MAX_VALUE;
        }
        return SystemPropsKt.systemProp(str, r1, r2, r3);
    }

    public static final int systemProp(String str, int r8, int r9, int r10) {
        return (int) SystemPropsKt.systemProp(str, r8, r9, r10);
    }

    public static /* synthetic */ long systemProp$default(String str, long j, long j2, long j3, int r14, Object obj) {
        if ((r14 & 4) != 0) {
            j2 = 1;
        }
        long j4 = j2;
        if ((r14 & 8) != 0) {
            j3 = Long.MAX_VALUE;
        }
        return SystemPropsKt.systemProp(str, j, j4, j3);
    }

    public static final long systemProp(String str, long j, long j2, long j3) {
        String systemProp = SystemPropsKt.systemProp(str);
        if (systemProp == null) {
            return j;
        }
        Long longOrNull = StringsKt.toLongOrNull(systemProp);
        if (longOrNull == null) {
            throw new IllegalStateException(("System property '" + str + "' has unrecognized value '" + systemProp + '\'').toString());
        }
        long longValue = longOrNull.longValue();
        boolean z = false;
        if (j2 <= longValue && longValue <= j3) {
            z = true;
        }
        if (z) {
            return longValue;
        }
        throw new IllegalStateException(("System property '" + str + "' should be in range " + j2 + ".." + j3 + ", but is '" + longValue + '\'').toString());
    }
}
