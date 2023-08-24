package expo.modules.kotlin.modules;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Module.kt */
@Metadata(m184d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u001a)\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0017\u0010\u0003\u001a\u0013\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\u0086\bø\u0001\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\b"}, m183d2 = {"ModuleDefinition", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "Lexpo/modules/kotlin/modules/Module;", "block", "Lkotlin/Function1;", "Lexpo/modules/kotlin/modules/ModuleDefinitionBuilder;", "", "Lkotlin/ExtensionFunctionType;", "expo-modules-core_release"}, m182k = 2, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class ModuleKt {
    public static final ModuleDefinitionData ModuleDefinition(Module module, Function1<? super ModuleDefinitionBuilder, Unit> block) {
        Intrinsics.checkNotNullParameter(module, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        ModuleDefinitionBuilder moduleDefinitionBuilder = new ModuleDefinitionBuilder(module);
        block.invoke(moduleDefinitionBuilder);
        return moduleDefinitionBuilder.buildModule();
    }
}
