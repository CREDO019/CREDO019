package com.swmansion.rnscreens;

import android.app.Activity;
import android.view.View;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.facebook.react.bridge.GuardedRunnable;
import com.facebook.react.bridge.ReactContext;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ScreenWindowTraits.kt */
@Metadata(m184d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0017¨\u0006\u0004"}, m183d2 = {"com/swmansion/rnscreens/ScreenWindowTraits$setTranslucent$1", "Lcom/facebook/react/bridge/GuardedRunnable;", "runGuarded", "", "react-native-screens_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class ScreenWindowTraits$setTranslucent$1 extends GuardedRunnable {
    final /* synthetic */ Activity $activity;
    final /* synthetic */ boolean $translucent;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ScreenWindowTraits$setTranslucent$1(ReactContext reactContext, Activity activity, boolean z) {
        super(reactContext);
        this.$activity = activity;
        this.$translucent = z;
    }

    @Override // com.facebook.react.bridge.GuardedRunnable
    public void runGuarded() {
        View decorView = this.$activity.getWindow().getDecorView();
        Intrinsics.checkNotNullExpressionValue(decorView, "activity.window.decorView");
        if (this.$translucent) {
            ViewCompat.setOnApplyWindowInsetsListener(decorView, new OnApplyWindowInsetsListener() { // from class: com.swmansion.rnscreens.ScreenWindowTraits$setTranslucent$1$$ExternalSyntheticLambda0
                @Override // androidx.core.view.OnApplyWindowInsetsListener
                public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                    WindowInsetsCompat m1555runGuarded$lambda0;
                    m1555runGuarded$lambda0 = ScreenWindowTraits$setTranslucent$1.m1555runGuarded$lambda0(view, windowInsetsCompat);
                    return m1555runGuarded$lambda0;
                }
            });
        } else {
            ViewCompat.setOnApplyWindowInsetsListener(decorView, null);
        }
        ViewCompat.requestApplyInsets(decorView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: runGuarded$lambda-0  reason: not valid java name */
    public static final WindowInsetsCompat m1555runGuarded$lambda0(View view, WindowInsetsCompat windowInsetsCompat) {
        WindowInsetsCompat onApplyWindowInsets = ViewCompat.onApplyWindowInsets(view, windowInsetsCompat);
        Intrinsics.checkNotNullExpressionValue(onApplyWindowInsets, "onApplyWindowInsets(v, insets)");
        return onApplyWindowInsets.replaceSystemWindowInsets(onApplyWindowInsets.getSystemWindowInsetLeft(), 0, onApplyWindowInsets.getSystemWindowInsetRight(), onApplyWindowInsets.getSystemWindowInsetBottom());
    }
}
