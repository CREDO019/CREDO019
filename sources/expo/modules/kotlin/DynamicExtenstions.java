package expo.modules.kotlin;

import com.facebook.react.bridge.Dynamic;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m184d1 = {"\u0000\u0016\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a4\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\u0017\u0010\u0003\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u0002H\u00010\u0004¢\u0006\u0002\b\u0005H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\u0006\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0007"}, m183d2 = {"recycle", "T", "Lcom/facebook/react/bridge/Dynamic;", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "(Lcom/facebook/react/bridge/Dynamic;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "expo-modules-core_release"}, m182k = 2, m181mv = {1, 6, 0}, m179xi = 48)
/* renamed from: expo.modules.kotlin.DynamicExtenstionsKt */
/* loaded from: classes4.dex */
public final class DynamicExtenstions {
    public static final <T> T recycle(Dynamic dynamic, Function1<? super Dynamic, ? extends T> block) {
        Intrinsics.checkNotNullParameter(dynamic, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        try {
            return block.invoke(dynamic);
        } finally {
            InlineMarker.finallyStart(1);
            dynamic.recycle();
            InlineMarker.finallyEnd(1);
        }
    }
}
