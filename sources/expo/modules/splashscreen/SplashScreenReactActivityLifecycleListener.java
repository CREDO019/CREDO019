package expo.modules.splashscreen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.facebook.react.ReactRootView;
import expo.modules.core.interfaces.ReactActivityLifecycleListener;
import expo.modules.splashscreen.SplashScreenImageResizeMode;
import expo.modules.splashscreen.singletons.SplashScreen;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SplashScreenReactActivityLifecycleListener.kt */
@Metadata(m184d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0003H\u0002J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\u0007\u001a\u00020\u0003H\u0002J\u001a\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016¨\u0006\u0010"}, m183d2 = {"Lexpo/modules/splashscreen/SplashScreenReactActivityLifecycleListener;", "Lexpo/modules/core/interfaces/ReactActivityLifecycleListener;", "activityContext", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getResizeMode", "Lexpo/modules/splashscreen/SplashScreenImageResizeMode;", "context", "getStatusBarTranslucent", "", "onCreate", "", "activity", "Landroid/app/Activity;", "savedInstanceState", "Landroid/os/Bundle;", "expo-splash-screen_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class SplashScreenReactActivityLifecycleListener implements ReactActivityLifecycleListener {
    @Override // expo.modules.core.interfaces.ReactActivityLifecycleListener
    public /* synthetic */ boolean onBackPressed() {
        return ReactActivityLifecycleListener.CC.$default$onBackPressed(this);
    }

    @Override // expo.modules.core.interfaces.ReactActivityLifecycleListener
    public /* synthetic */ void onDestroy(Activity activity) {
        ReactActivityLifecycleListener.CC.$default$onDestroy(this, activity);
    }

    @Override // expo.modules.core.interfaces.ReactActivityLifecycleListener
    public /* synthetic */ boolean onNewIntent(Intent intent) {
        return ReactActivityLifecycleListener.CC.$default$onNewIntent(this, intent);
    }

    @Override // expo.modules.core.interfaces.ReactActivityLifecycleListener
    public /* synthetic */ void onPause(Activity activity) {
        ReactActivityLifecycleListener.CC.$default$onPause(this, activity);
    }

    @Override // expo.modules.core.interfaces.ReactActivityLifecycleListener
    public /* synthetic */ void onResume(Activity activity) {
        ReactActivityLifecycleListener.CC.$default$onResume(this, activity);
    }

    public SplashScreenReactActivityLifecycleListener(Context activityContext) {
        Intrinsics.checkNotNullParameter(activityContext, "activityContext");
    }

    @Override // expo.modules.core.interfaces.ReactActivityLifecycleListener
    public void onCreate(final Activity activity, Bundle bundle) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        new Handler(activity.getMainLooper()).post(new Runnable() { // from class: expo.modules.splashscreen.SplashScreenReactActivityLifecycleListener$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                SplashScreenReactActivityLifecycleListener.m1702onCreate$lambda0(activity, this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onCreate$lambda-0  reason: not valid java name */
    public static final void m1702onCreate$lambda0(Activity activity, SplashScreenReactActivityLifecycleListener this$0) {
        Intrinsics.checkNotNullParameter(activity, "$activity");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Activity activity2 = activity;
        SplashScreen.show$default(activity, this$0.getResizeMode(activity2), ReactRootView.class, this$0.getStatusBarTranslucent(activity2), null, null, null, 112, null);
    }

    private final SplashScreenImageResizeMode getResizeMode(Context context) {
        SplashScreenImageResizeMode.Companion companion = SplashScreenImageResizeMode.Companion;
        String string = context.getString(C4545R.string.expo_splash_screen_resize_mode);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.stri…plash_screen_resize_mode)");
        String lowerCase = string.toLowerCase();
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
        SplashScreenImageResizeMode fromString = companion.fromString(lowerCase);
        return fromString == null ? SplashScreenImageResizeMode.CONTAIN : fromString;
    }

    private final boolean getStatusBarTranslucent(Context context) {
        return Boolean.parseBoolean(context.getString(C4545R.string.expo_splash_screen_status_bar_translucent));
    }
}
