package okio;

import java.util.zip.Inflater;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: InflaterSource.kt */
@Metadata(m185bv = {1, 0, 3}, m184d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0017\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0086\b¨\u0006\u0005"}, m183d2 = {"inflate", "Lokio/InflaterSource;", "Lokio/Source;", "inflater", "Ljava/util/zip/Inflater;", "okio"}, m182k = 2, m181mv = {1, 4, 0})
/* renamed from: okio.-InflaterSourceExtensions  reason: invalid class name */
/* loaded from: classes5.dex */
public final class InflaterSourceExtensions {
    public static /* synthetic */ InflaterSource inflate$default(Source inflate, Inflater inflater, int r2, Object obj) {
        if ((r2 & 1) != 0) {
            inflater = new Inflater();
        }
        Intrinsics.checkNotNullParameter(inflate, "$this$inflate");
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return new InflaterSource(inflate, inflater);
    }

    public static final InflaterSource inflate(Source inflate, Inflater inflater) {
        Intrinsics.checkNotNullParameter(inflate, "$this$inflate");
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return new InflaterSource(inflate, inflater);
    }
}
