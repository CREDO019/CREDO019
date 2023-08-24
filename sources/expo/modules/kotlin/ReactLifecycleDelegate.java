package expo.modules.kotlin;

import android.app.Activity;
import android.content.Intent;
import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.LifecycleEventListener;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ReactLifecycleDelegate.kt */
@Metadata(m184d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J*\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J\b\u0010\u0012\u001a\u00020\nH\u0016J\b\u0010\u0013\u001a\u00020\nH\u0016J\b\u0010\u0014\u001a\u00020\nH\u0016J\u0012\u0010\u0015\u001a\u00020\n2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0011H\u0016R\u001c\u0010\u0006\u001a\u0010\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00040\u00040\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, m183d2 = {"Lexpo/modules/kotlin/ReactLifecycleDelegate;", "Lcom/facebook/react/bridge/LifecycleEventListener;", "Lcom/facebook/react/bridge/ActivityEventListener;", "appContext", "Lexpo/modules/kotlin/AppContext;", "(Lexpo/modules/kotlin/AppContext;)V", "appContextHolder", "Ljava/lang/ref/WeakReference;", "kotlin.jvm.PlatformType", "onActivityResult", "", "activity", "Landroid/app/Activity;", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onHostDestroy", "onHostPause", "onHostResume", "onNewIntent", "intent", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class ReactLifecycleDelegate implements LifecycleEventListener, ActivityEventListener {
    private final WeakReference<AppContext> appContextHolder;

    public ReactLifecycleDelegate(AppContext appContext) {
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        this.appContextHolder = new WeakReference<>(appContext);
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() {
        AppContext appContext = this.appContextHolder.get();
        if (appContext == null) {
            return;
        }
        appContext.onHostResume$expo_modules_core_release();
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
        AppContext appContext = this.appContextHolder.get();
        if (appContext == null) {
            return;
        }
        appContext.onHostPause$expo_modules_core_release();
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
        AppContext appContext = this.appContextHolder.get();
        if (appContext == null) {
            return;
        }
        appContext.onHostDestroy$expo_modules_core_release();
    }

    @Override // com.facebook.react.bridge.ActivityEventListener
    public void onActivityResult(Activity activity, int r3, int r4, Intent intent) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        AppContext appContext = this.appContextHolder.get();
        if (appContext == null) {
            return;
        }
        appContext.onActivityResult$expo_modules_core_release(activity, r3, r4, intent);
    }

    @Override // com.facebook.react.bridge.ActivityEventListener
    public void onNewIntent(Intent intent) {
        AppContext appContext = this.appContextHolder.get();
        if (appContext == null) {
            return;
        }
        appContext.onNewIntent$expo_modules_core_release(intent);
    }
}
