package expo.modules.core;

import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Functions;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ModuleRegistryDelegate.kt */
@Metadata(m184d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001f\u0010\b\u001a\u0010\u0012\f\u0012\n \u000b*\u0004\u0018\u0001H\nH\n0\t\"\u0006\b\u0000\u0010\n\u0018\u0001H\u0086\bJ\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u0004R\"\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000e"}, m183d2 = {"Lexpo/modules/core/ModuleRegistryDelegate;", "", "()V", "<set-?>", "Lexpo/modules/core/ModuleRegistry;", "moduleRegistry", "getModuleRegistry", "()Lexpo/modules/core/ModuleRegistry;", "getFromModuleRegistry", "Lkotlin/Lazy;", "T", "kotlin.jvm.PlatformType", "onCreate", "", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class ModuleRegistryDelegate {
    private ModuleRegistry moduleRegistry;

    public final ModuleRegistry getModuleRegistry() {
        return this.moduleRegistry;
    }

    public final void onCreate(ModuleRegistry moduleRegistry) {
        Intrinsics.checkNotNullParameter(moduleRegistry, "moduleRegistry");
        this.moduleRegistry = moduleRegistry;
    }

    public final /* synthetic */ <T> Lazy<T> getFromModuleRegistry() {
        Intrinsics.needClassReification();
        return LazyKt.lazy(new Functions<T>() { // from class: expo.modules.core.ModuleRegistryDelegate$getFromModuleRegistry$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Functions
            public final T invoke() {
                ModuleRegistry moduleRegistry = ModuleRegistryDelegate.this.getModuleRegistry();
                Intrinsics.checkNotNull(moduleRegistry);
                Intrinsics.reifiedOperationMarker(4, "T");
                return (T) moduleRegistry.getModule(Object.class);
            }
        });
    }
}
