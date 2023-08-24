package expo.modules.kotlin.views;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CallbacksDefinition.kt */
@Metadata(m184d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0015\u0012\u000e\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005R\u001b\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007¨\u0006\t"}, m183d2 = {"Lexpo/modules/kotlin/views/CallbacksDefinition;", "", "names", "", "", "([Ljava/lang/String;)V", "getNames", "()[Ljava/lang/String;", "[Ljava/lang/String;", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class CallbacksDefinition {
    private final String[] names;

    public CallbacksDefinition(String[] names) {
        Intrinsics.checkNotNullParameter(names, "names");
        this.names = names;
    }

    public final String[] getNames() {
        return this.names;
    }
}
