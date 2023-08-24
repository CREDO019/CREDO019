package expo.modules.keepawake;

import android.content.Context;
import com.facebook.react.bridge.BaseJavaModule;
import expo.modules.core.ExportedModule;
import expo.modules.core.ModuleRegistry;
import expo.modules.core.ModuleRegistryDelegate;
import expo.modules.core.Promise;
import expo.modules.core.errors.CurrentActivityNotFoundException;
import expo.modules.core.interfaces.ExpoMethod;
import expo.modules.core.interfaces.services.KeepAwakeManager;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Functions;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: KeepAwakeModule.kt */
@Metadata(m184d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0007J\u0018\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0007J\b\u0010\u0017\u001a\u00020\u0013H\u0016J\u001f\u0010\u0018\u001a\u0010\u0012\f\u0012\n \u001b*\u0004\u0018\u0001H\u001aH\u001a0\u0019\"\u0006\b\u0000\u0010\u001a\u0018\u0001H\u0082\bJ\u0010\u0010\u001c\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u001dH\u0016R\u0011\u0010\u0007\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\tR\u001b\u0010\n\u001a\u00020\u000b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\rR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, m183d2 = {"Lexpo/modules/keepawake/KeepAwakeModule;", "Lexpo/modules/core/ExportedModule;", "context", "Landroid/content/Context;", "moduleRegistryDelegate", "Lexpo/modules/core/ModuleRegistryDelegate;", "(Landroid/content/Context;Lexpo/modules/core/ModuleRegistryDelegate;)V", "isActivated", "", "()Z", "keepAwakeManager", "Lexpo/modules/core/interfaces/services/KeepAwakeManager;", "getKeepAwakeManager", "()Lexpo/modules/core/interfaces/services/KeepAwakeManager;", "keepAwakeManager$delegate", "Lkotlin/Lazy;", "activate", "", "tag", "", BaseJavaModule.METHOD_TYPE_PROMISE, "Lexpo/modules/core/Promise;", "deactivate", "getName", "moduleRegistry", "Lkotlin/Lazy;", "T", "kotlin.jvm.PlatformType", "onCreate", "Lexpo/modules/core/ModuleRegistry;", "expo-keep-awake_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class KeepAwakeModule extends ExportedModule {
    private final Lazy keepAwakeManager$delegate;
    private final ModuleRegistryDelegate moduleRegistryDelegate;

    @Override // expo.modules.core.ExportedModule
    public String getName() {
        return "ExpoKeepAwake";
    }

    public /* synthetic */ KeepAwakeModule(Context context, ModuleRegistryDelegate moduleRegistryDelegate, int r3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (r3 & 2) != 0 ? new ModuleRegistryDelegate() : moduleRegistryDelegate);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KeepAwakeModule(Context context, final ModuleRegistryDelegate moduleRegistryDelegate) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(moduleRegistryDelegate, "moduleRegistryDelegate");
        this.moduleRegistryDelegate = moduleRegistryDelegate;
        this.keepAwakeManager$delegate = LazyKt.lazy(new Functions<KeepAwakeManager>() { // from class: expo.modules.keepawake.KeepAwakeModule$special$$inlined$moduleRegistry$1
            {
                super(0);
            }

            /* JADX WARN: Type inference failed for: r0v2, types: [expo.modules.core.interfaces.services.KeepAwakeManager, java.lang.Object] */
            @Override // kotlin.jvm.functions.Functions
            public final KeepAwakeManager invoke() {
                ModuleRegistry moduleRegistry = ModuleRegistryDelegate.this.getModuleRegistry();
                Intrinsics.checkNotNull(moduleRegistry);
                return moduleRegistry.getModule(KeepAwakeManager.class);
            }
        });
    }

    private final KeepAwakeManager getKeepAwakeManager() {
        Object value = this.keepAwakeManager$delegate.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-keepAwakeManager>(...)");
        return (KeepAwakeManager) value;
    }

    private final /* synthetic */ <T> Lazy<T> moduleRegistry() {
        final ModuleRegistryDelegate moduleRegistryDelegate = this.moduleRegistryDelegate;
        Intrinsics.needClassReification();
        return LazyKt.lazy(new Functions<T>() { // from class: expo.modules.keepawake.KeepAwakeModule$moduleRegistry$$inlined$getFromModuleRegistry$1
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

    @Override // expo.modules.core.ExportedModule, expo.modules.core.interfaces.RegistryLifecycleListener
    public void onCreate(ModuleRegistry moduleRegistry) {
        Intrinsics.checkNotNullParameter(moduleRegistry, "moduleRegistry");
        this.moduleRegistryDelegate.onCreate(moduleRegistry);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: activate$lambda-0  reason: not valid java name */
    public static final void m1659activate$lambda0(Promise promise) {
        Intrinsics.checkNotNullParameter(promise, "$promise");
        promise.resolve(true);
    }

    @ExpoMethod
    public final void activate(String tag, final Promise promise) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(promise, "promise");
        try {
            getKeepAwakeManager().activate(tag, new Runnable() { // from class: expo.modules.keepawake.KeepAwakeModule$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    KeepAwakeModule.m1659activate$lambda0(Promise.this);
                }
            });
        } catch (CurrentActivityNotFoundException unused) {
            promise.reject("NO_CURRENT_ACTIVITY", "Unable to activate keep awake");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: deactivate$lambda-1  reason: not valid java name */
    public static final void m1660deactivate$lambda1(Promise promise) {
        Intrinsics.checkNotNullParameter(promise, "$promise");
        promise.resolve(true);
    }

    @ExpoMethod
    public final void deactivate(String tag, final Promise promise) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(promise, "promise");
        try {
            getKeepAwakeManager().deactivate(tag, new Runnable() { // from class: expo.modules.keepawake.KeepAwakeModule$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    KeepAwakeModule.m1660deactivate$lambda1(Promise.this);
                }
            });
        } catch (CurrentActivityNotFoundException unused) {
            promise.reject("NO_CURRENT_ACTIVITY", "Unable to deactivate keep awake. However, it probably is deactivated already.");
        }
    }

    public final boolean isActivated() {
        return getKeepAwakeManager().isActivated();
    }
}
