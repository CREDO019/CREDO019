package expo.modules.kotlin.objects;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Functions;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: ObjectDefinitionBuilder.kt */
@Metadata(m184d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0010\u0010\u0002\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, m183d2 = {"<anonymous>", "", "it", "", "invoke", "([Ljava/lang/Object;)Ljava/lang/Object;"}, m182k = 3, m181mv = {1, 6, 0}, m179xi = 176)
/* loaded from: classes4.dex */
public final class ObjectDefinitionBuilder$AsyncFunction$1 extends Lambda implements Function1<Object[], Object> {
    final /* synthetic */ Functions<Object> $body;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ObjectDefinitionBuilder$AsyncFunction$1(Functions<? extends Object> functions) {
        super(1);
        this.$body = functions;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object[] it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return this.$body.invoke();
    }
}
