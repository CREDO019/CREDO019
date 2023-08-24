package expo.modules.keepawake;

import android.app.Activity;
import com.onesignal.UserState;
import expo.modules.core.ModuleRegistry;
import expo.modules.core.ModuleRegistryDelegate;
import expo.modules.core.errors.CurrentActivityNotFoundException;
import expo.modules.core.interfaces.ActivityProvider;
import expo.modules.core.interfaces.InternalModule;
import expo.modules.core.interfaces.RegistryLifecycleListener;
import expo.modules.core.interfaces.services.KeepAwakeManager;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Functions;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ExpoKeepAwakeManager.kt */
@Metadata(m184d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010#\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0018\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0014\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u00150\u0014H\u0016J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\u001f\u0010\u0018\u001a\u0010\u0012\f\u0012\n \u001b*\u0004\u0018\u0001H\u001aH\u001a0\u0019\"\u0006\b\u0000\u0010\u001a\u0018\u0001H\u0082\bJ\u0010\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\u001dH\u0016R\u0014\u0010\u0006\u001a\u00020\u00078BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e²\u0006\n\u0010\u001f\u001a\u00020 X\u008a\u0084\u0002"}, m183d2 = {"Lexpo/modules/keepawake/ExpoKeepAwakeManager;", "Lexpo/modules/core/interfaces/services/KeepAwakeManager;", "Lexpo/modules/core/interfaces/InternalModule;", "moduleRegistryDelegate", "Lexpo/modules/core/ModuleRegistryDelegate;", "(Lexpo/modules/core/ModuleRegistryDelegate;)V", "currentActivity", "Landroid/app/Activity;", "getCurrentActivity", "()Landroid/app/Activity;", UserState.TAGS, "", "", "activate", "", "tag", "done", "Ljava/lang/Runnable;", "deactivate", "getExportedInterfaces", "", "Ljava/lang/Class;", "isActivated", "", "moduleRegistry", "Lkotlin/Lazy;", "T", "kotlin.jvm.PlatformType", "onCreate", "Lexpo/modules/core/ModuleRegistry;", "expo-keep-awake_release", "activityProvider", "Lexpo/modules/core/interfaces/ActivityProvider;"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class ExpoKeepAwakeManager implements KeepAwakeManager, InternalModule {
    private final ModuleRegistryDelegate moduleRegistryDelegate;
    private final Set<String> tags;

    public ExpoKeepAwakeManager() {
        this(null, 1, null);
    }

    @Override // expo.modules.core.interfaces.RegistryLifecycleListener
    public /* synthetic */ void onDestroy() {
        RegistryLifecycleListener.CC.$default$onDestroy(this);
    }

    public ExpoKeepAwakeManager(ModuleRegistryDelegate moduleRegistryDelegate) {
        Intrinsics.checkNotNullParameter(moduleRegistryDelegate, "moduleRegistryDelegate");
        this.moduleRegistryDelegate = moduleRegistryDelegate;
        this.tags = new HashSet();
    }

    public /* synthetic */ ExpoKeepAwakeManager(ModuleRegistryDelegate moduleRegistryDelegate, int r2, DefaultConstructorMarker defaultConstructorMarker) {
        this((r2 & 1) != 0 ? new ModuleRegistryDelegate() : moduleRegistryDelegate);
    }

    private final /* synthetic */ <T> Lazy<T> moduleRegistry() {
        final ModuleRegistryDelegate moduleRegistryDelegate = this.moduleRegistryDelegate;
        Intrinsics.needClassReification();
        return LazyKt.lazy(new Functions<T>() { // from class: expo.modules.keepawake.ExpoKeepAwakeManager$moduleRegistry$$inlined$getFromModuleRegistry$1
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

    @Override // expo.modules.core.interfaces.RegistryLifecycleListener
    public void onCreate(ModuleRegistry moduleRegistry) {
        Intrinsics.checkNotNullParameter(moduleRegistry, "moduleRegistry");
        this.moduleRegistryDelegate.onCreate(moduleRegistry);
    }

    /* renamed from: _get_currentActivity_$lambda-0  reason: not valid java name */
    private static final ActivityProvider m1655_get_currentActivity_$lambda0(Lazy<? extends ActivityProvider> lazy) {
        ActivityProvider value = lazy.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "_get_currentActivity_$lambda-0(...)");
        return value;
    }

    @Override // expo.modules.core.interfaces.services.KeepAwakeManager
    public void activate(String tag, Runnable done) throws CurrentActivityNotFoundException {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(done, "done");
        final Activity currentActivity = getCurrentActivity();
        if (!isActivated()) {
            currentActivity.runOnUiThread(new Runnable() { // from class: expo.modules.keepawake.ExpoKeepAwakeManager$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    ExpoKeepAwakeManager.m1656activate$lambda1(currentActivity);
                }
            });
        }
        this.tags.add(tag);
        done.run();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: activate$lambda-1  reason: not valid java name */
    public static final void m1656activate$lambda1(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "$activity");
        activity.getWindow().addFlags(128);
    }

    @Override // expo.modules.core.interfaces.services.KeepAwakeManager
    public void deactivate(String tag, Runnable done) throws CurrentActivityNotFoundException {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(done, "done");
        final Activity currentActivity = getCurrentActivity();
        if (this.tags.size() == 1 && this.tags.contains(tag)) {
            currentActivity.runOnUiThread(new Runnable() { // from class: expo.modules.keepawake.ExpoKeepAwakeManager$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    ExpoKeepAwakeManager.m1657deactivate$lambda2(currentActivity);
                }
            });
        }
        this.tags.remove(tag);
        done.run();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: deactivate$lambda-2  reason: not valid java name */
    public static final void m1657deactivate$lambda2(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "$activity");
        activity.getWindow().clearFlags(128);
    }

    @Override // expo.modules.core.interfaces.services.KeepAwakeManager
    public boolean isActivated() {
        return !this.tags.isEmpty();
    }

    @Override // expo.modules.core.interfaces.InternalModule
    public List<Class<?>> getExportedInterfaces() {
        return CollectionsKt.listOf(KeepAwakeManager.class);
    }

    private final Activity getCurrentActivity() throws CurrentActivityNotFoundException {
        final ModuleRegistryDelegate moduleRegistryDelegate = this.moduleRegistryDelegate;
        Lazy lazy = LazyKt.lazy(new Functions<ActivityProvider>() { // from class: expo.modules.keepawake.ExpoKeepAwakeManager$special$$inlined$moduleRegistry$1
            {
                super(0);
            }

            /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Object, expo.modules.core.interfaces.ActivityProvider] */
            @Override // kotlin.jvm.functions.Functions
            public final ActivityProvider invoke() {
                ModuleRegistry moduleRegistry = ModuleRegistryDelegate.this.getModuleRegistry();
                Intrinsics.checkNotNull(moduleRegistry);
                return moduleRegistry.getModule(ActivityProvider.class);
            }
        });
        if (m1655_get_currentActivity_$lambda0(lazy).getCurrentActivity() != null) {
            Activity currentActivity = m1655_get_currentActivity_$lambda0(lazy).getCurrentActivity();
            Intrinsics.checkNotNullExpressionValue(currentActivity, "{\n        activityProvider.currentActivity\n      }");
            return currentActivity;
        }
        throw new CurrentActivityNotFoundException();
    }
}
