package expo.modules.constants;

import android.content.Context;
import com.facebook.react.bridge.BaseJavaModule;
import expo.modules.core.ExportedModule;
import expo.modules.core.ModuleRegistry;
import expo.modules.core.ModuleRegistryDelegate;
import expo.modules.core.Promise;
import expo.modules.core.interfaces.ExpoMethod;
import expo.modules.interfaces.constants.ConstantsInterface;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Functions;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ConstantsModule.kt */
@Metadata(m184d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0014\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000eH\u0016J\b\u0010\u0011\u001a\u00020\u000fH\u0016J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0007J\u001f\u0010\u0016\u001a\u0010\u0012\f\u0012\n \u0019*\u0004\u0018\u0001H\u0018H\u00180\u0017\"\u0006\b\u0000\u0010\u0018\u0018\u0001H\u0082\bJ\u0010\u0010\u001a\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u001bH\u0016R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, m183d2 = {"Lexpo/modules/constants/ConstantsModule;", "Lexpo/modules/core/ExportedModule;", "context", "Landroid/content/Context;", "moduleRegistryDelegate", "Lexpo/modules/core/ModuleRegistryDelegate;", "(Landroid/content/Context;Lexpo/modules/core/ModuleRegistryDelegate;)V", "constantsService", "Lexpo/modules/interfaces/constants/ConstantsInterface;", "getConstantsService", "()Lexpo/modules/interfaces/constants/ConstantsInterface;", "constantsService$delegate", "Lkotlin/Lazy;", "getConstants", "", "", "", "getName", "getWebViewUserAgentAsync", "", BaseJavaModule.METHOD_TYPE_PROMISE, "Lexpo/modules/core/Promise;", "moduleRegistry", "Lkotlin/Lazy;", "T", "kotlin.jvm.PlatformType", "onCreate", "Lexpo/modules/core/ModuleRegistry;", "expo-constants_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class ConstantsModule extends ExportedModule {
    private final Lazy constantsService$delegate;
    private final ModuleRegistryDelegate moduleRegistryDelegate;

    @Override // expo.modules.core.ExportedModule
    public String getName() {
        return "ExponentConstants";
    }

    public /* synthetic */ ConstantsModule(Context context, ModuleRegistryDelegate moduleRegistryDelegate, int r3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (r3 & 2) != 0 ? new ModuleRegistryDelegate() : moduleRegistryDelegate);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConstantsModule(Context context, final ModuleRegistryDelegate moduleRegistryDelegate) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(moduleRegistryDelegate, "moduleRegistryDelegate");
        this.moduleRegistryDelegate = moduleRegistryDelegate;
        this.constantsService$delegate = LazyKt.lazy(new Functions<ConstantsInterface>() { // from class: expo.modules.constants.ConstantsModule$special$$inlined$moduleRegistry$1
            {
                super(0);
            }

            /* JADX WARN: Type inference failed for: r0v2, types: [expo.modules.interfaces.constants.ConstantsInterface, java.lang.Object] */
            @Override // kotlin.jvm.functions.Functions
            public final ConstantsInterface invoke() {
                ModuleRegistry moduleRegistry = ModuleRegistryDelegate.this.getModuleRegistry();
                Intrinsics.checkNotNull(moduleRegistry);
                return moduleRegistry.getModule(ConstantsInterface.class);
            }
        });
    }

    private final /* synthetic */ <T> Lazy<T> moduleRegistry() {
        final ModuleRegistryDelegate moduleRegistryDelegate = this.moduleRegistryDelegate;
        Intrinsics.needClassReification();
        return LazyKt.lazy(new Functions<T>() { // from class: expo.modules.constants.ConstantsModule$moduleRegistry$$inlined$getFromModuleRegistry$1
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

    private final ConstantsInterface getConstantsService() {
        Object value = this.constantsService$delegate.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-constantsService>(...)");
        return (ConstantsInterface) value;
    }

    @Override // expo.modules.core.ExportedModule
    public Map<String, Object> getConstants() {
        Map<String, Object> constants = getConstantsService().getConstants();
        Intrinsics.checkNotNullExpressionValue(constants, "constantsService.constants");
        return constants;
    }

    @Override // expo.modules.core.ExportedModule, expo.modules.core.interfaces.RegistryLifecycleListener
    public void onCreate(ModuleRegistry moduleRegistry) {
        Intrinsics.checkNotNullParameter(moduleRegistry, "moduleRegistry");
        this.moduleRegistryDelegate.onCreate(moduleRegistry);
    }

    @ExpoMethod
    public final void getWebViewUserAgentAsync(Promise promise) {
        Intrinsics.checkNotNullParameter(promise, "promise");
        promise.resolve(System.getProperty("http.agent"));
    }
}
