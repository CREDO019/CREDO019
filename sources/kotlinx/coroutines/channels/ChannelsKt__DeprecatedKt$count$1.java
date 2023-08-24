package kotlinx.coroutines.channels;

import com.onesignal.shortcutbadger.impl.NewHtcHomeBadger;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Deprecated.kt */
@Metadata(m182k = 3, m181mv = {1, 6, 0}, m179xi = 48)
@DebugMetadata(m175c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", m174f = "Deprecated.kt", m173i = {0, 0}, m172l = {487}, m171m = NewHtcHomeBadger.COUNT, m170n = {NewHtcHomeBadger.COUNT, "$this$consume$iv$iv"}, m169s = {"L$0", "L$1"})
/* loaded from: classes5.dex */
public final class ChannelsKt__DeprecatedKt$count$1<E> extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ChannelsKt__DeprecatedKt$count$1(Continuation<? super ChannelsKt__DeprecatedKt$count$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object count;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        count = Deprecated.count(null, this);
        return count;
    }
}
