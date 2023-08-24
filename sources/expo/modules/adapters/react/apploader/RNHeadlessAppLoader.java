package expo.modules.adapters.react.apploader;

import android.content.Context;
import android.os.Handler;
import com.facebook.react.ReactApplication;
import com.facebook.react.ReactInstanceEventListener;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.common.LifecycleState;
import com.onesignal.outcomes.data.OutcomeEventsTable;
import expo.modules.apploader.HeadlessAppLoader;
import expo.modules.core.interfaces.Consumer;
import java.util.Map;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RNHeadlessAppLoader.kt */
@Metadata(m184d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J\u0012\u0010\t\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J4\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u000e\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0011H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, m183d2 = {"Lexpo/modules/adapters/react/apploader/RNHeadlessAppLoader;", "Lexpo/modules/apploader/HeadlessAppLoader;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "invalidateApp", "", "appScopeKey", "", "isRunning", "loadApp", "", OutcomeEventsTable.COLUMN_NAME_PARAMS, "Lexpo/modules/apploader/HeadlessAppLoader$Params;", "alreadyRunning", "Ljava/lang/Runnable;", "callback", "Lexpo/modules/core/interfaces/Consumer;", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class RNHeadlessAppLoader implements HeadlessAppLoader {
    private final Context context;

    public RNHeadlessAppLoader(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    @Override // expo.modules.apploader.HeadlessAppLoader
    public void loadApp(Context context, final HeadlessAppLoader.Params params, Runnable runnable, final Consumer<Boolean> consumer) {
        Map map;
        Map map2;
        Intrinsics.checkNotNullParameter(context, "context");
        if (params == null || params.getAppScopeKey() == null) {
            throw new IllegalArgumentException("Params must be set with appScopeKey!");
        }
        if (context.getApplicationContext() instanceof ReactApplication) {
            Context applicationContext = context.getApplicationContext();
            Objects.requireNonNull(applicationContext, "null cannot be cast to non-null type com.facebook.react.ReactApplication");
            ReactInstanceManager reactInstanceManager = ((ReactApplication) applicationContext).getReactNativeHost().getReactInstanceManager();
            map = RNHeadlessAppLoaderKt.appRecords;
            if (map.containsKey(params.getAppScopeKey())) {
                if (runnable == null) {
                    return;
                }
                runnable.run();
                return;
            }
            reactInstanceManager.addReactInstanceEventListener(new ReactInstanceEventListener() { // from class: expo.modules.adapters.react.apploader.RNHeadlessAppLoader$$ExternalSyntheticLambda0
                @Override // com.facebook.react.ReactInstanceEventListener
                public final void onReactContextInitialized(ReactContext reactContext) {
                    RNHeadlessAppLoader.m1569loadApp$lambda0(HeadlessAppLoader.Params.this, consumer, reactContext);
                }
            });
            map2 = RNHeadlessAppLoaderKt.appRecords;
            String appScopeKey = params.getAppScopeKey();
            Intrinsics.checkNotNullExpressionValue(appScopeKey, "params.appScopeKey");
            Intrinsics.checkNotNullExpressionValue(reactInstanceManager, "reactInstanceManager");
            map2.put(appScopeKey, reactInstanceManager);
            if (reactInstanceManager.hasStartedCreatingInitialContext()) {
                reactInstanceManager.recreateReactContextInBackground();
                return;
            } else {
                reactInstanceManager.createReactContextInBackground();
                return;
            }
        }
        throw new IllegalStateException("Your application must implement ReactApplication");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadApp$lambda-0  reason: not valid java name */
    public static final void m1569loadApp$lambda0(HeadlessAppLoader.Params params, Consumer consumer, ReactContext reactContext) {
        HeadlessAppLoaderNotifier.INSTANCE.notifyAppLoaded(params.getAppScopeKey());
        if (consumer == null) {
            return;
        }
        consumer.apply(true);
    }

    @Override // expo.modules.apploader.HeadlessAppLoader
    public boolean invalidateApp(final String str) {
        Map map;
        Map map2;
        Map map3;
        map = RNHeadlessAppLoaderKt.appRecords;
        if (map.containsKey(str)) {
            map2 = RNHeadlessAppLoaderKt.appRecords;
            if (map2.get(str) != null) {
                map3 = RNHeadlessAppLoaderKt.appRecords;
                Object obj = map3.get(str);
                Intrinsics.checkNotNull(obj);
                final ReactInstanceManager reactInstanceManager = (ReactInstanceManager) obj;
                new Handler(this.context.getMainLooper()).post(new Runnable() { // from class: expo.modules.adapters.react.apploader.RNHeadlessAppLoader$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        RNHeadlessAppLoader.m1568invalidateApp$lambda1(ReactInstanceManager.this, str);
                    }
                });
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: invalidateApp$lambda-1  reason: not valid java name */
    public static final void m1568invalidateApp$lambda1(ReactInstanceManager appRecord, String str) {
        Map map;
        Intrinsics.checkNotNullParameter(appRecord, "$appRecord");
        if (appRecord.getLifecycleState() == LifecycleState.BEFORE_CREATE) {
            appRecord.destroy();
        }
        HeadlessAppLoaderNotifier.INSTANCE.notifyAppDestroyed(str);
        map = RNHeadlessAppLoaderKt.appRecords;
        map.remove(str);
    }

    @Override // expo.modules.apploader.HeadlessAppLoader
    public boolean isRunning(String str) {
        Map map;
        Map map2;
        map = RNHeadlessAppLoaderKt.appRecords;
        if (map.containsKey(str)) {
            map2 = RNHeadlessAppLoaderKt.appRecords;
            Object obj = map2.get(str);
            Intrinsics.checkNotNull(obj);
            if (((ReactInstanceManager) obj).hasStartedCreatingInitialContext()) {
                return true;
            }
        }
        return false;
    }
}
