package expo.modules.splashscreen;

import android.app.Activity;
import android.content.Context;
import com.facebook.react.bridge.BaseJavaModule;
import expo.modules.core.ExportedModule;
import expo.modules.core.ModuleRegistry;
import expo.modules.core.Promise;
import expo.modules.core.errors.CurrentActivityNotFoundException;
import expo.modules.core.interfaces.ActivityProvider;
import expo.modules.core.interfaces.ExpoMethod;
import expo.modules.splashscreen.singletons.SplashScreen;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SplashScreenModule.kt */
@Metadata(m184d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u0010\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\u0010\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0007R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0012"}, m183d2 = {"Lexpo/modules/splashscreen/SplashScreenModule;", "Lexpo/modules/core/ExportedModule;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "activityProvider", "Lexpo/modules/core/interfaces/ActivityProvider;", "getName", "", "hideAsync", "", BaseJavaModule.METHOD_TYPE_PROMISE, "Lexpo/modules/core/Promise;", "onCreate", "moduleRegistry", "Lexpo/modules/core/ModuleRegistry;", "preventAutoHideAsync", "Companion", "expo-splash-screen_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class SplashScreenModule extends ExportedModule {
    public static final Companion Companion = new Companion(null);
    private static final String ERROR_TAG = "ERR_SPLASH_SCREEN";
    private static final String NAME = "ExpoSplashScreen";
    private ActivityProvider activityProvider;

    @Override // expo.modules.core.ExportedModule
    public String getName() {
        return NAME;
    }

    /* compiled from: SplashScreenModule.kt */
    @Metadata(m184d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, m183d2 = {"Lexpo/modules/splashscreen/SplashScreenModule$Companion;", "", "()V", "ERROR_TAG", "", "NAME", "expo-splash-screen_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SplashScreenModule(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    @Override // expo.modules.core.ExportedModule, expo.modules.core.interfaces.RegistryLifecycleListener
    public void onCreate(ModuleRegistry moduleRegistry) {
        Intrinsics.checkNotNullParameter(moduleRegistry, "moduleRegistry");
        Object module = moduleRegistry.getModule(ActivityProvider.class);
        Intrinsics.checkNotNullExpressionValue(module, "moduleRegistry.getModule…vityProvider::class.java)");
        this.activityProvider = (ActivityProvider) module;
    }

    @ExpoMethod
    public final void preventAutoHideAsync(final Promise promise) {
        Intrinsics.checkNotNullParameter(promise, "promise");
        ActivityProvider activityProvider = this.activityProvider;
        if (activityProvider == null) {
            Intrinsics.throwUninitializedPropertyAccessException("activityProvider");
            activityProvider = null;
        }
        Activity currentActivity = activityProvider.getCurrentActivity();
        if (currentActivity == null) {
            promise.reject(new CurrentActivityNotFoundException());
        } else {
            SplashScreen.INSTANCE.preventAutoHide(currentActivity, new Function1<Boolean, Unit>() { // from class: expo.modules.splashscreen.SplashScreenModule$preventAutoHideAsync$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                    invoke(bool.booleanValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(boolean z) {
                    Promise.this.resolve(Boolean.valueOf(z));
                }
            }, new Function1<String, Unit>() { // from class: expo.modules.splashscreen.SplashScreenModule$preventAutoHideAsync$2
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(String str) {
                    invoke2(str);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2(String m) {
                    Intrinsics.checkNotNullParameter(m, "m");
                    Promise.this.reject("ERR_SPLASH_SCREEN", m);
                }
            });
        }
    }

    @ExpoMethod
    public final void hideAsync(final Promise promise) {
        Intrinsics.checkNotNullParameter(promise, "promise");
        ActivityProvider activityProvider = this.activityProvider;
        if (activityProvider == null) {
            Intrinsics.throwUninitializedPropertyAccessException("activityProvider");
            activityProvider = null;
        }
        Activity currentActivity = activityProvider.getCurrentActivity();
        if (currentActivity == null) {
            promise.reject(new CurrentActivityNotFoundException());
        } else {
            SplashScreen.INSTANCE.hide(currentActivity, new Function1<Boolean, Unit>() { // from class: expo.modules.splashscreen.SplashScreenModule$hideAsync$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                    invoke(bool.booleanValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(boolean z) {
                    Promise.this.resolve(Boolean.valueOf(z));
                }
            }, new Function1<String, Unit>() { // from class: expo.modules.splashscreen.SplashScreenModule$hideAsync$2
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(String str) {
                    invoke2(str);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2(String m) {
                    Intrinsics.checkNotNullParameter(m, "m");
                    Promise.this.reject("ERR_SPLASH_SCREEN", m);
                }
            });
        }
    }
}
