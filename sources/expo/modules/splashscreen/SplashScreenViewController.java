package expo.modules.splashscreen;

import android.app.Activity;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import expo.modules.splashscreen.exceptions.NoContentViewException;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Functions;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SplashScreenViewController.kt */
@Metadata(m184d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0012\u0010\u0014\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0015\u001a\u00020\bH\u0002J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0015\u001a\u00020\u0006H\u0002JR\u0010\u0018\u001a\u00020\u00172#\b\u0002\u0010\u0019\u001a\u001d\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u00020\u00170\u001a2#\b\u0002\u0010\u001e\u001a\u001d\u0012\u0013\u0012\u00110\u001f¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b( \u0012\u0004\u0012\u00020\u00170\u001aH\u0016JL\u0010!\u001a\u00020\u00172!\u0010\u0019\u001a\u001d\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u00020\u00170\u001a2!\u0010\u001e\u001a\u001d\u0012\u0013\u0012\u00110\u001f¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b( \u0012\u0004\u0012\u00020\u00170\u001aJ\b\u0010\"\u001a\u00020\u0017H\u0002J\u0018\u0010#\u001a\u00020\u00172\u000e\b\u0002\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00170$H\u0016R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0011\u001a\u0010\u0012\f\u0012\n \u0013*\u0004\u0018\u00010\u00030\u00030\u0012X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006%"}, m183d2 = {"Lexpo/modules/splashscreen/SplashScreenViewController;", "", "activity", "Landroid/app/Activity;", "rootViewClass", "Ljava/lang/Class;", "Landroid/view/ViewGroup;", "splashScreenView", "Landroid/view/View;", "(Landroid/app/Activity;Ljava/lang/Class;Landroid/view/View;)V", "autoHideEnabled", "", "contentView", "handler", "Landroid/os/Handler;", "rootView", "splashScreenShown", "weakActivity", "Ljava/lang/ref/WeakReference;", "kotlin.jvm.PlatformType", "findRootView", "view", "handleRootView", "", "hideSplashScreen", "successCallback", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "hasEffect", "failureCallback", "", "reason", "preventAutoHide", "searchForRootView", "showSplashScreen", "Lkotlin/Function0;", "expo-splash-screen_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public class SplashScreenViewController {
    private boolean autoHideEnabled;
    private final ViewGroup contentView;
    private final Handler handler;
    private ViewGroup rootView;
    private final Class<? extends ViewGroup> rootViewClass;
    private boolean splashScreenShown;
    private final View splashScreenView;
    private final WeakReference<Activity> weakActivity;

    public SplashScreenViewController(Activity activity, Class<? extends ViewGroup> rootViewClass, View splashScreenView) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(rootViewClass, "rootViewClass");
        Intrinsics.checkNotNullParameter(splashScreenView, "splashScreenView");
        this.rootViewClass = rootViewClass;
        this.splashScreenView = splashScreenView;
        this.weakActivity = new WeakReference<>(activity);
        ViewGroup viewGroup = (ViewGroup) activity.findViewById(16908290);
        if (viewGroup == null) {
            throw new NoContentViewException();
        }
        this.contentView = viewGroup;
        this.handler = new Handler();
        this.autoHideEnabled = true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void showSplashScreen$default(SplashScreenViewController splashScreenViewController, Functions functions, int r2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: showSplashScreen");
        }
        if ((r2 & 1) != 0) {
            functions = new Functions<Unit>() { // from class: expo.modules.splashscreen.SplashScreenViewController$showSplashScreen$1
                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2() {
                }

                @Override // kotlin.jvm.functions.Functions
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }
            };
        }
        splashScreenViewController.showSplashScreen(functions);
    }

    public void showSplashScreen(final Functions<Unit> successCallback) {
        Intrinsics.checkNotNullParameter(successCallback, "successCallback");
        Activity activity = this.weakActivity.get();
        if (activity == null) {
            return;
        }
        activity.runOnUiThread(new Runnable() { // from class: expo.modules.splashscreen.SplashScreenViewController$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                SplashScreenViewController.m1707showSplashScreen$lambda0(SplashScreenViewController.this, successCallback);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showSplashScreen$lambda-0  reason: not valid java name */
    public static final void m1707showSplashScreen$lambda0(SplashScreenViewController this$0, Functions successCallback) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(successCallback, "$successCallback");
        ViewParent parent = this$0.splashScreenView.getParent();
        ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
        if (viewGroup != null) {
            viewGroup.removeView(this$0.splashScreenView);
        }
        this$0.contentView.addView(this$0.splashScreenView);
        this$0.splashScreenShown = true;
        successCallback.invoke();
        this$0.searchForRootView();
    }

    public final void preventAutoHide(Function1<? super Boolean, Unit> successCallback, Function1<? super String, Unit> failureCallback) {
        Intrinsics.checkNotNullParameter(successCallback, "successCallback");
        Intrinsics.checkNotNullParameter(failureCallback, "failureCallback");
        if (!this.autoHideEnabled || !this.splashScreenShown) {
            successCallback.invoke(false);
            return;
        }
        this.autoHideEnabled = false;
        successCallback.invoke(true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void hideSplashScreen$default(SplashScreenViewController splashScreenViewController, Function1 function1, Function1 function12, int r3, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: hideSplashScreen");
        }
        if ((r3 & 1) != 0) {
            function1 = new Function1<Boolean, Unit>() { // from class: expo.modules.splashscreen.SplashScreenViewController$hideSplashScreen$1
                public final void invoke(boolean z) {
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                    invoke(bool.booleanValue());
                    return Unit.INSTANCE;
                }
            };
        }
        if ((r3 & 2) != 0) {
            function12 = new Function1<String, Unit>() { // from class: expo.modules.splashscreen.SplashScreenViewController$hideSplashScreen$2
                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2(String it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(String str) {
                    invoke2(str);
                    return Unit.INSTANCE;
                }
            };
        }
        splashScreenViewController.hideSplashScreen(function1, function12);
    }

    public void hideSplashScreen(final Function1<? super Boolean, Unit> successCallback, Function1<? super String, Unit> failureCallback) {
        Intrinsics.checkNotNullParameter(successCallback, "successCallback");
        Intrinsics.checkNotNullParameter(failureCallback, "failureCallback");
        if (!this.splashScreenShown) {
            successCallback.invoke(false);
            return;
        }
        Activity activity = this.weakActivity.get();
        if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
            failureCallback.invoke("Cannot hide native splash screen on activity that is already destroyed (application is already closed).");
        } else {
            new Handler(activity.getMainLooper()).post(new Runnable() { // from class: expo.modules.splashscreen.SplashScreenViewController$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    SplashScreenViewController.m1705hideSplashScreen$lambda1(SplashScreenViewController.this, successCallback);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: hideSplashScreen$lambda-1  reason: not valid java name */
    public static final void m1705hideSplashScreen$lambda1(SplashScreenViewController this$0, Function1 successCallback) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(successCallback, "$successCallback");
        this$0.contentView.removeView(this$0.splashScreenView);
        this$0.autoHideEnabled = true;
        this$0.splashScreenShown = false;
        successCallback.invoke(true);
    }

    private final void searchForRootView() {
        if (this.rootView != null) {
            return;
        }
        ViewGroup findRootView = findRootView(this.contentView);
        if (findRootView != null) {
            handleRootView(findRootView);
        } else {
            this.handler.postDelayed(new Runnable() { // from class: expo.modules.splashscreen.SplashScreenViewController$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    SplashScreenViewController.m1706searchForRootView$lambda3(SplashScreenViewController.this);
                }
            }, 20L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: searchForRootView$lambda-3  reason: not valid java name */
    public static final void m1706searchForRootView$lambda3(SplashScreenViewController this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.searchForRootView();
    }

    private final ViewGroup findRootView(View view) {
        if (this.rootViewClass.isInstance(view)) {
            return (ViewGroup) view;
        }
        if (Intrinsics.areEqual(view, this.splashScreenView) || !(view instanceof ViewGroup)) {
            return null;
        }
        int r0 = 0;
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        while (r0 < childCount) {
            int r2 = r0 + 1;
            View childAt = viewGroup.getChildAt(r0);
            Intrinsics.checkNotNullExpressionValue(childAt, "view.getChildAt(idx)");
            ViewGroup findRootView = findRootView(childAt);
            if (findRootView != null) {
                return findRootView;
            }
            r0 = r2;
        }
        return null;
    }

    private final void handleRootView(ViewGroup viewGroup) {
        this.rootView = viewGroup;
        if ((viewGroup == null ? 0 : viewGroup.getChildCount()) > 0 && this.autoHideEnabled) {
            hideSplashScreen$default(this, null, null, 3, null);
        }
        viewGroup.setOnHierarchyChangeListener(new ViewGroup.OnHierarchyChangeListener() { // from class: expo.modules.splashscreen.SplashScreenViewController$handleRootView$1
            @Override // android.view.ViewGroup.OnHierarchyChangeListener
            public void onChildViewRemoved(View parent, View child) {
                ViewGroup viewGroup2;
                Intrinsics.checkNotNullParameter(parent, "parent");
                Intrinsics.checkNotNullParameter(child, "child");
                viewGroup2 = SplashScreenViewController.this.rootView;
                boolean z = false;
                if (viewGroup2 != null && viewGroup2.getChildCount() == 0) {
                    z = true;
                }
                if (z) {
                    SplashScreenViewController.showSplashScreen$default(SplashScreenViewController.this, null, 1, null);
                }
            }

            @Override // android.view.ViewGroup.OnHierarchyChangeListener
            public void onChildViewAdded(View parent, View child) {
                ViewGroup viewGroup2;
                boolean z;
                Intrinsics.checkNotNullParameter(parent, "parent");
                Intrinsics.checkNotNullParameter(child, "child");
                viewGroup2 = SplashScreenViewController.this.rootView;
                boolean z2 = false;
                if (viewGroup2 != null && viewGroup2.getChildCount() == 1) {
                    z2 = true;
                }
                if (z2) {
                    z = SplashScreenViewController.this.autoHideEnabled;
                    if (z) {
                        SplashScreenViewController.hideSplashScreen$default(SplashScreenViewController.this, null, null, 3, null);
                    }
                }
            }
        });
    }
}
