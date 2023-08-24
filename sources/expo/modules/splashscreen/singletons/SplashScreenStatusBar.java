package expo.modules.splashscreen.singletons;

import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.WindowInsets;
import androidx.core.view.ViewCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SplashScreenStatusBar.kt */
@Metadata(m184d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001d\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\t¨\u0006\n"}, m183d2 = {"Lexpo/modules/splashscreen/singletons/SplashScreenStatusBar;", "", "()V", "configureTranslucent", "", "activity", "Landroid/app/Activity;", "translucent", "", "(Landroid/app/Activity;Ljava/lang/Boolean;)V", "expo-splash-screen_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class SplashScreenStatusBar {
    public static final SplashScreenStatusBar INSTANCE = new SplashScreenStatusBar();

    private SplashScreenStatusBar() {
    }

    public final void configureTranslucent(final Activity activity, Boolean bool) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (Build.VERSION.SDK_INT >= 21 && bool != null) {
            final boolean booleanValue = bool.booleanValue();
            activity.runOnUiThread(new Runnable() { // from class: expo.modules.splashscreen.singletons.SplashScreenStatusBar$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    SplashScreenStatusBar.m1708configureTranslucent$lambda2$lambda1(activity, booleanValue);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: configureTranslucent$lambda-2$lambda-1  reason: not valid java name */
    public static final void m1708configureTranslucent$lambda2$lambda1(Activity activity, boolean z) {
        Intrinsics.checkNotNullParameter(activity, "$activity");
        View decorView = activity.getWindow().getDecorView();
        Intrinsics.checkNotNullExpressionValue(decorView, "activity.window.decorView");
        if (z) {
            decorView.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() { // from class: expo.modules.splashscreen.singletons.SplashScreenStatusBar$$ExternalSyntheticLambda0
                @Override // android.view.View.OnApplyWindowInsetsListener
                public final WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
                    WindowInsets m1709configureTranslucent$lambda2$lambda1$lambda0;
                    m1709configureTranslucent$lambda2$lambda1$lambda0 = SplashScreenStatusBar.m1709configureTranslucent$lambda2$lambda1$lambda0(view, windowInsets);
                    return m1709configureTranslucent$lambda2$lambda1$lambda0;
                }
            });
        } else {
            decorView.setOnApplyWindowInsetsListener(null);
        }
        ViewCompat.requestApplyInsets(decorView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: configureTranslucent$lambda-2$lambda-1$lambda-0  reason: not valid java name */
    public static final WindowInsets m1709configureTranslucent$lambda2$lambda1$lambda0(View v, WindowInsets insets) {
        Intrinsics.checkNotNullParameter(v, "v");
        Intrinsics.checkNotNullParameter(insets, "insets");
        WindowInsets onApplyWindowInsets = v.onApplyWindowInsets(insets);
        return onApplyWindowInsets.replaceSystemWindowInsets(onApplyWindowInsets.getSystemWindowInsetLeft(), 0, onApplyWindowInsets.getSystemWindowInsetRight(), onApplyWindowInsets.getSystemWindowInsetBottom());
    }
}
