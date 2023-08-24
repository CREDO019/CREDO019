package expo.modules;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.ViewGroup;
import androidx.collection.ArrayMap;
import androidx.core.app.NotificationCompat;
import androidx.exifinterface.media.ExifInterface;
import com.facebook.react.ReactActivity;
import com.facebook.react.ReactActivityDelegate;
import com.facebook.react.ReactDelegate;
import com.facebook.react.ReactInstanceEventListener;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactRootView;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.modules.core.PermissionListener;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import expo.modules.core.interfaces.Package;
import expo.modules.core.interfaces.ReactActivityHandler;
import expo.modules.core.interfaces.ReactActivityLifecycleListener;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;

/* compiled from: ReactActivityDelegateWrapper.kt */
@Metadata(m184d1 = {"\u0000\u009e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0015\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0001¢\u0006\u0002\u0010\u0005B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\u0004\u001a\u00020\u0001¢\u0006\u0002\u0010\bJ\b\u0010\u0013\u001a\u00020\u0014H\u0014J\b\u0010\u0015\u001a\u00020\u0016H\u0014J\n\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0014J\n\u0010\u0019\u001a\u0004\u0018\u00010\u000bH\u0016J\b\u0010\u001a\u001a\u00020\u001bH\u0014J\b\u0010\u001c\u001a\u00020\u001dH\u0016J\b\u0010\u001e\u001a\u00020\u001fH\u0014J\u001b\u0010 \u001a\u0002H!\"\u0004\b\u0000\u0010!2\u0006\u0010\"\u001a\u00020\u000bH\u0002¢\u0006\u0002\u0010#JA\u0010 \u001a\u0002H!\"\u0004\b\u0000\u0010!\"\u0004\b\u0001\u0010$2\u0006\u0010\"\u001a\u00020\u000b2\u0010\u0010%\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030'0&2\f\u0010(\u001a\b\u0012\u0004\u0012\u0002H$0&H\u0002¢\u0006\u0002\u0010)J\u0012\u0010*\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010\u000bH\u0014J\"\u0010-\u001a\u00020+2\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020/2\b\u00101\u001a\u0004\u0018\u000102H\u0016J\b\u00103\u001a\u00020\u0007H\u0016J\u0012\u00104\u001a\u00020+2\b\u00105\u001a\u0004\u0018\u00010\u0018H\u0014J\b\u00106\u001a\u00020+H\u0014J\u001a\u00107\u001a\u00020\u00072\u0006\u00108\u001a\u00020/2\b\u00109\u001a\u0004\u0018\u00010:H\u0016J\u001a\u0010;\u001a\u00020\u00072\u0006\u00108\u001a\u00020/2\b\u00109\u001a\u0004\u0018\u00010:H\u0016J\u001a\u0010<\u001a\u00020\u00072\u0006\u00108\u001a\u00020/2\b\u00109\u001a\u0004\u0018\u00010:H\u0016J\u0012\u0010=\u001a\u00020\u00072\b\u0010>\u001a\u0004\u0018\u000102H\u0016J\b\u0010?\u001a\u00020+H\u0014J1\u0010@\u001a\u00020+2\u0006\u0010.\u001a\u00020/2\u0010\u0010A\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u000b\u0018\u00010&2\b\u0010B\u001a\u0004\u0018\u00010CH\u0016¢\u0006\u0002\u0010DJ\b\u0010E\u001a\u00020+H\u0014J\u0010\u0010F\u001a\u00020+2\u0006\u0010G\u001a\u00020\u0007H\u0016J1\u0010H\u001a\u00020+2\u0010\u0010A\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u000b\u0018\u00010&2\u0006\u0010.\u001a\u00020/2\b\u0010I\u001a\u0004\u0018\u00010JH\u0016¢\u0006\u0002\u0010KR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\r\u001a\u0010\u0012\f\u0012\n \u0010*\u0004\u0018\u00010\u000f0\u000f0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0011\u001a\u0010\u0012\f\u0012\n \u0010*\u0004\u0018\u00010\u00120\u00120\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006L"}, m183d2 = {"Lexpo/modules/ReactActivityDelegateWrapper;", "Lcom/facebook/react/ReactActivityDelegate;", "activity", "Lcom/facebook/react/ReactActivity;", "delegate", "(Lcom/facebook/react/ReactActivity;Lcom/facebook/react/ReactActivityDelegate;)V", "isNewArchitectureEnabled", "", "(Lcom/facebook/react/ReactActivity;ZLcom/facebook/react/ReactActivityDelegate;)V", "methodMap", "Landroidx/collection/ArrayMap;", "", "Ljava/lang/reflect/Method;", "reactActivityHandlers", "", "Lexpo/modules/core/interfaces/ReactActivityHandler;", "kotlin.jvm.PlatformType", "reactActivityLifecycleListeners", "Lexpo/modules/core/interfaces/ReactActivityLifecycleListener;", "createRootView", "Lcom/facebook/react/ReactRootView;", "getContext", "Landroid/content/Context;", "getLaunchOptions", "Landroid/os/Bundle;", "getMainComponentName", "getPlainActivity", "Landroid/app/Activity;", "getReactInstanceManager", "Lcom/facebook/react/ReactInstanceManager;", "getReactNativeHost", "Lcom/facebook/react/ReactNativeHost;", "invokeDelegateMethod", "T", "name", "(Ljava/lang/String;)Ljava/lang/Object;", ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, "argTypes", "", "Ljava/lang/Class;", "args", "(Ljava/lang/String;[Ljava/lang/Class;[Ljava/lang/Object;)Ljava/lang/Object;", "loadApp", "", "appKey", "onActivityResult", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onBackPressed", "onCreate", "savedInstanceState", "onDestroy", "onKeyDown", "keyCode", NotificationCompat.CATEGORY_EVENT, "Landroid/view/KeyEvent;", "onKeyLongPress", "onKeyUp", "onNewIntent", "intent", "onPause", "onRequestPermissionsResult", "permissions", "grantResults", "", "(I[Ljava/lang/String;[I)V", "onResume", "onWindowFocusChanged", "hasFocus", "requestPermissions", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/facebook/react/modules/core/PermissionListener;", "([Ljava/lang/String;ILcom/facebook/react/modules/core/PermissionListener;)V", "expo_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class ReactActivityDelegateWrapper extends ReactActivityDelegate {
    private final ReactActivity activity;
    private ReactActivityDelegate delegate;
    private final boolean isNewArchitectureEnabled;
    private final ArrayMap<String, Method> methodMap;
    private final List<ReactActivityHandler> reactActivityHandlers;
    private final List<ReactActivityLifecycleListener> reactActivityLifecycleListeners;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ReactActivityDelegateWrapper(ReactActivity activity, boolean z, ReactActivityDelegate delegate) {
        super(activity, (String) null);
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        this.activity = activity;
        this.isNewArchitectureEnabled = z;
        this.delegate = delegate;
        ArrayList arrayList = new ArrayList();
        for (Package r4 : ExpoModulesPackage.Companion.getPackageList()) {
            List<? extends ReactActivityLifecycleListener> createReactActivityLifecycleListeners = r4.createReactActivityLifecycleListeners(this.activity);
            Intrinsics.checkNotNullExpressionValue(createReactActivityLifecycleListeners, "it.createReactActivityLifecycleListeners(activity)");
            CollectionsKt.addAll(arrayList, createReactActivityLifecycleListeners);
        }
        this.reactActivityLifecycleListeners = arrayList;
        ArrayList arrayList2 = new ArrayList();
        for (Package r42 : ExpoModulesPackage.Companion.getPackageList()) {
            List<? extends ReactActivityHandler> createReactActivityHandlers = r42.createReactActivityHandlers(this.activity);
            Intrinsics.checkNotNullExpressionValue(createReactActivityHandlers, "it.createReactActivityHandlers(activity)");
            CollectionsKt.addAll(arrayList2, createReactActivityHandlers);
        }
        this.reactActivityHandlers = arrayList2;
        this.methodMap = new ArrayMap<>();
    }

    /* JADX WARN: 'thıs' call moved to the top of the method (can break code semantics) */
    public ReactActivityDelegateWrapper(ReactActivity activity, ReactActivityDelegate delegate) {
        this(activity, false, delegate);
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(delegate, "delegate");
    }

    @Override // com.facebook.react.ReactActivityDelegate
    protected Bundle getLaunchOptions() {
        return (Bundle) invokeDelegateMethod("getLaunchOptions");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.ReactActivityDelegate
    public ReactRootView createRootView() {
        ReactRootView reactRootView = (ReactRootView) SequencesKt.firstOrNull(SequencesKt.mapNotNull(CollectionsKt.asSequence(this.reactActivityHandlers), new Function1<ReactActivityHandler, ReactRootView>() { // from class: expo.modules.ReactActivityDelegateWrapper$createRootView$rootView$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final ReactRootView invoke(ReactActivityHandler reactActivityHandler) {
                ReactActivity reactActivity;
                reactActivity = ReactActivityDelegateWrapper.this.activity;
                return reactActivityHandler.createReactRootView(reactActivity);
            }
        }));
        if (reactRootView == null) {
            reactRootView = (ReactRootView) invokeDelegateMethod("createRootView");
        }
        Intrinsics.checkNotNullExpressionValue(reactRootView, "override fun createRootV…)\n    return rootView\n  }");
        reactRootView.setIsFabric(this.isNewArchitectureEnabled);
        return reactRootView;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.ReactActivityDelegate
    public ReactNativeHost getReactNativeHost() {
        return (ReactNativeHost) invokeDelegateMethod("getReactNativeHost");
    }

    @Override // com.facebook.react.ReactActivityDelegate
    public ReactInstanceManager getReactInstanceManager() {
        ReactInstanceManager reactInstanceManager = this.delegate.getReactInstanceManager();
        Intrinsics.checkNotNullExpressionValue(reactInstanceManager, "delegate.reactInstanceManager");
        return reactInstanceManager;
    }

    @Override // com.facebook.react.ReactActivityDelegate
    public String getMainComponentName() {
        return this.delegate.getMainComponentName();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.ReactActivityDelegate
    public void loadApp(String str) {
        ViewGroup viewGroup = (ViewGroup) SequencesKt.firstOrNull(SequencesKt.mapNotNull(CollectionsKt.asSequence(this.reactActivityHandlers), new Function1<ReactActivityHandler, ViewGroup>() { // from class: expo.modules.ReactActivityDelegateWrapper$loadApp$rootViewContainer$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final ViewGroup invoke(ReactActivityHandler reactActivityHandler) {
                ReactActivity reactActivity;
                reactActivity = ReactActivityDelegateWrapper.this.activity;
                return reactActivityHandler.createReactRootViewContainer(reactActivity);
            }
        }));
        if (viewGroup == null) {
            invokeDelegateMethod("loadApp", new Class[]{String.class}, new String[]{str});
            return;
        }
        Field declaredField = ReactActivityDelegate.class.getDeclaredField("mReactDelegate");
        declaredField.setAccessible(true);
        Object obj = declaredField.get(this.delegate);
        Objects.requireNonNull(obj, "null cannot be cast to non-null type com.facebook.react.ReactDelegate");
        ReactDelegate reactDelegate = (ReactDelegate) obj;
        reactDelegate.loadApp(str);
        viewGroup.addView(reactDelegate.getReactRootView(), -1);
        this.activity.setContentView(viewGroup);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.ReactActivityDelegate
    public void onCreate(Bundle bundle) {
        ReactActivityDelegate reactActivityDelegate = (ReactActivityDelegate) SequencesKt.firstOrNull(SequencesKt.mapNotNull(CollectionsKt.asSequence(this.reactActivityHandlers), new Function1<ReactActivityHandler, ReactActivityDelegate>() { // from class: expo.modules.ReactActivityDelegateWrapper$onCreate$newDelegate$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final ReactActivityDelegate invoke(ReactActivityHandler reactActivityHandler) {
                ReactActivity reactActivity;
                reactActivity = ReactActivityDelegateWrapper.this.activity;
                return reactActivityHandler.onDidCreateReactActivityDelegate(reactActivity, ReactActivityDelegateWrapper.this);
            }
        }));
        if (reactActivityDelegate != null && !Intrinsics.areEqual(reactActivityDelegate, this)) {
            Field declaredField = ReactActivity.class.getDeclaredField("mDelegate");
            declaredField.setAccessible(true);
            Field declaredField2 = Field.class.getDeclaredField("accessFlags");
            declaredField2.setAccessible(true);
            declaredField2.setInt(declaredField, declaredField.getModifiers() & (-17));
            declaredField.set(this.activity, reactActivityDelegate);
            this.delegate = reactActivityDelegate;
            invokeDelegateMethod("onCreate", new Class[]{Bundle.class}, new Bundle[]{bundle});
        } else {
            final Activity plainActivity = getPlainActivity();
            final ReactNativeHost reactNativeHost = getReactNativeHost();
            final String mainComponentName = getMainComponentName();
            final Bundle launchOptions = getLaunchOptions();
            ReactDelegate reactDelegate = new ReactDelegate(plainActivity, reactNativeHost, mainComponentName, launchOptions) { // from class: expo.modules.ReactActivityDelegateWrapper$onCreate$reactDelegate$1
                @Override // com.facebook.react.ReactDelegate
                protected ReactRootView createRootView() {
                    return ReactActivityDelegateWrapper.this.createRootView();
                }
            };
            Field declaredField3 = ReactActivityDelegate.class.getDeclaredField("mReactDelegate");
            declaredField3.setAccessible(true);
            declaredField3.set(this.delegate, reactDelegate);
            if (getMainComponentName() != null) {
                loadApp(getMainComponentName());
            }
        }
        for (ReactActivityLifecycleListener reactActivityLifecycleListener : this.reactActivityLifecycleListeners) {
            reactActivityLifecycleListener.onCreate(this.activity, bundle);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.ReactActivityDelegate
    public void onResume() {
        invokeDelegateMethod("onResume");
        for (ReactActivityLifecycleListener reactActivityLifecycleListener : this.reactActivityLifecycleListeners) {
            reactActivityLifecycleListener.onResume(this.activity);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.ReactActivityDelegate
    public void onPause() {
        for (ReactActivityLifecycleListener reactActivityLifecycleListener : this.reactActivityLifecycleListeners) {
            reactActivityLifecycleListener.onPause(this.activity);
        }
        invokeDelegateMethod("onPause");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.ReactActivityDelegate
    public void onDestroy() {
        for (ReactActivityLifecycleListener reactActivityLifecycleListener : this.reactActivityLifecycleListeners) {
            reactActivityLifecycleListener.onDestroy(this.activity);
        }
        invokeDelegateMethod("onDestroy");
    }

    @Override // com.facebook.react.ReactActivityDelegate
    public void onActivityResult(final int r2, final int r3, final Intent intent) {
        if (this.delegate.getReactInstanceManager().getCurrentReactContext() == null) {
            this.delegate.getReactInstanceManager().addReactInstanceEventListener(new ReactInstanceEventListener() { // from class: expo.modules.ReactActivityDelegateWrapper$onActivityResult$reactContextListener$1
                @Override // com.facebook.react.ReactInstanceEventListener
                public void onReactContextInitialized(ReactContext reactContext) {
                    ReactActivityDelegate reactActivityDelegate;
                    ReactActivityDelegate reactActivityDelegate2;
                    reactActivityDelegate = ReactActivityDelegateWrapper.this.delegate;
                    reactActivityDelegate.getReactInstanceManager().removeReactInstanceEventListener(this);
                    reactActivityDelegate2 = ReactActivityDelegateWrapper.this.delegate;
                    reactActivityDelegate2.onActivityResult(r2, r3, intent);
                }
            });
            return;
        }
        this.delegate.onActivityResult(r2, r3, intent);
    }

    @Override // com.facebook.react.ReactActivityDelegate
    public boolean onKeyDown(int r2, KeyEvent keyEvent) {
        return this.delegate.onKeyDown(r2, keyEvent);
    }

    @Override // com.facebook.react.ReactActivityDelegate
    public boolean onKeyUp(int r6, KeyEvent keyEvent) {
        boolean z;
        List<ReactActivityHandler> list = this.reactActivityHandlers;
        ArrayList<Boolean> arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (ReactActivityHandler reactActivityHandler : list) {
            arrayList.add(Boolean.valueOf(reactActivityHandler.onKeyUp(r6, keyEvent)));
        }
        loop1: while (true) {
            for (Boolean bool : arrayList) {
                z = z || bool.booleanValue();
            }
        }
        return z || this.delegate.onKeyUp(r6, keyEvent);
    }

    @Override // com.facebook.react.ReactActivityDelegate
    public boolean onKeyLongPress(int r2, KeyEvent keyEvent) {
        return this.delegate.onKeyLongPress(r2, keyEvent);
    }

    @Override // com.facebook.react.ReactActivityDelegate
    public boolean onBackPressed() {
        boolean z;
        List<ReactActivityLifecycleListener> list = this.reactActivityLifecycleListeners;
        ArrayList<Boolean> arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (ReactActivityLifecycleListener reactActivityLifecycleListener : list) {
            arrayList.add(Boolean.valueOf(reactActivityLifecycleListener.onBackPressed()));
        }
        loop1: while (true) {
            for (Boolean bool : arrayList) {
                z = z || bool.booleanValue();
            }
        }
        return z || this.delegate.onBackPressed();
    }

    @Override // com.facebook.react.ReactActivityDelegate
    public boolean onNewIntent(Intent intent) {
        boolean z;
        List<ReactActivityLifecycleListener> list = this.reactActivityLifecycleListeners;
        ArrayList<Boolean> arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (ReactActivityLifecycleListener reactActivityLifecycleListener : list) {
            arrayList.add(Boolean.valueOf(reactActivityLifecycleListener.onNewIntent(intent)));
        }
        loop1: while (true) {
            for (Boolean bool : arrayList) {
                z = z || bool.booleanValue();
            }
        }
        return z || this.delegate.onNewIntent(intent);
    }

    @Override // com.facebook.react.ReactActivityDelegate
    public void onWindowFocusChanged(boolean z) {
        this.delegate.onWindowFocusChanged(z);
    }

    @Override // com.facebook.react.ReactActivityDelegate
    public void requestPermissions(String[] strArr, int r3, PermissionListener permissionListener) {
        this.delegate.requestPermissions(strArr, r3, permissionListener);
    }

    @Override // com.facebook.react.ReactActivityDelegate
    public void onRequestPermissionsResult(int r2, String[] strArr, int[] r4) {
        this.delegate.onRequestPermissionsResult(r2, strArr, r4);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.ReactActivityDelegate
    public Context getContext() {
        return (Context) invokeDelegateMethod("getContext");
    }

    @Override // com.facebook.react.ReactActivityDelegate
    protected Activity getPlainActivity() {
        return (Activity) invokeDelegateMethod("getPlainActivity");
    }

    private final <T> T invokeDelegateMethod(String str) {
        Method method = this.methodMap.get(str);
        if (method == null) {
            method = ReactActivityDelegate.class.getDeclaredMethod(str, new Class[0]);
            method.setAccessible(true);
            this.methodMap.put(str, method);
        }
        Intrinsics.checkNotNull(method);
        return (T) method.invoke(this.delegate, new Object[0]);
    }

    private final <T, A> T invokeDelegateMethod(String str, Class<?>[] clsArr, A[] aArr) {
        Method method = this.methodMap.get(str);
        if (method == null) {
            method = ReactActivityDelegate.class.getDeclaredMethod(str, (Class[]) Arrays.copyOf(clsArr, clsArr.length));
            method.setAccessible(true);
            this.methodMap.put(str, method);
        }
        Intrinsics.checkNotNull(method);
        return (T) method.invoke(this.delegate, Arrays.copyOf(aArr, aArr.length));
    }
}
