package androidx.lifecycle;

import kotlin.Metadata;
import kotlin.jvm.functions.Functions;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: Add missing generic type declarations: [R] */
/* compiled from: WithLifecycleState.kt */
@Metadata(m185bv = {1, 0, 3}, m184d1 = {"\u0000\u0004\n\u0002\b\u0004\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, m183d2 = {"<anonymous>", "R", "invoke", "()Ljava/lang/Object;"}, m182k = 3, m181mv = {1, 4, 1})
/* loaded from: classes.dex */
public final class WithLifecycleStateKt$withStateAtLeastUnchecked$2<R> extends Lambda implements Functions<R> {
    final /* synthetic */ Functions $block;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WithLifecycleStateKt$withStateAtLeastUnchecked$2(Functions functions) {
        super(0);
        this.$block = functions;
    }

    @Override // kotlin.jvm.functions.Functions
    public final R invoke() {
        return (R) this.$block.invoke();
    }
}
