package expo.modules.adapters.react.permissions;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.modules.core.PermissionAwareActivity;
import com.facebook.react.modules.core.PermissionListener;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import expo.modules.core.ModuleRegistry;
import expo.modules.core.Promise;
import expo.modules.core.interfaces.ActivityProvider;
import expo.modules.core.interfaces.InternalModule;
import expo.modules.core.interfaces.LifecycleEventListener;
import expo.modules.core.interfaces.RegistryLifecycleListener;
import expo.modules.core.interfaces.services.UIManager;
import expo.modules.interfaces.permissions.Permissions;
import expo.modules.interfaces.permissions.PermissionsResponse;
import expo.modules.interfaces.permissions.PermissionsResponseListener;
import expo.modules.interfaces.permissions.PermissionsStatus;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import kotlin.Metadata;
import kotlin.Tuples;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PermissionsService.kt */
@Metadata(m184d1 = {"\u0000\u0098\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001d\u0010\u0019\u001a\u00020\u001a2\u000e\u0010\u001b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000f0\u000eH\u0002¢\u0006\u0002\u0010\u001cJ%\u0010\u001d\u001a\u00020\u001a2\u000e\u0010\u001b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000f0\u000e2\u0006\u0010\u001e\u001a\u00020\fH\u0014¢\u0006\u0002\u0010\u001fJ)\u0010 \u001a\u00020\u001a2\u0006\u0010!\u001a\u00020\f2\u0012\u0010\u001b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000f0\u000e\"\u00020\u000fH\u0016¢\u0006\u0002\u0010\"J)\u0010#\u001a\u00020\u001a2\u0006\u0010$\u001a\u00020%2\u0012\u0010\u001b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000f0\u000e\"\u00020\u000fH\u0016¢\u0006\u0002\u0010&J\b\u0010'\u001a\u00020\u001aH\u0003J\u0010\u0010(\u001a\u00020\u00182\u0006\u0010)\u001a\u00020\u000fH\u0002J\b\u0010*\u001a\u00020+H\u0002J%\u0010,\u001a\u00020\u001a2\u000e\u0010\u001b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000f0\u000e2\u0006\u0010\u001e\u001a\u00020\fH\u0004¢\u0006\u0002\u0010\u001fJ\u0010\u0010-\u001a\u00020\u00182\u0006\u0010)\u001a\u00020\u000fH\u0002J\u0016\u0010.\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u000201000/H\u0016J\u0010\u00102\u001a\u0002032\u0006\u0010)\u001a\u00020\u000fH\u0002J\u0010\u00104\u001a\u0002032\u0006\u0010)\u001a\u00020\u000fH\u0014J\u0018\u00105\u001a\u0002062\u0006\u0010)\u001a\u00020\u000f2\u0006\u00107\u001a\u000203H\u0002J)\u00108\u001a\u00020\u001a2\u0006\u0010!\u001a\u00020\f2\u0012\u0010\u001b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000f0\u000e\"\u00020\u000fH\u0016¢\u0006\u0002\u0010\"J)\u00109\u001a\u00020\u001a2\u0006\u0010$\u001a\u00020%2\u0012\u0010\u001b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000f0\u000e\"\u00020\u000fH\u0016¢\u0006\u0002\u0010&J!\u0010:\u001a\u00020\u00182\u0012\u0010\u001b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000f0\u000e\"\u00020\u000fH\u0016¢\u0006\u0002\u0010;J\b\u0010<\u001a\u00020\u0018H\u0002J\u0010\u0010=\u001a\u00020\u00182\u0006\u0010)\u001a\u00020\u000fH\u0002J\u0010\u0010>\u001a\u00020\u00182\u0006\u0010)\u001a\u00020\u000fH\u0016J\b\u0010?\u001a\u00020\u0018H\u0002J\u0010\u0010@\u001a\u00020\u001a2\u0006\u0010A\u001a\u00020BH\u0016J\b\u0010C\u001a\u00020\u001aH\u0016J\b\u0010D\u001a\u00020\u001aH\u0016J\b\u0010E\u001a\u00020\u001aH\u0016J1\u0010F\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u0002060G2\u000e\u0010H\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000f0\u000e2\u0006\u0010I\u001a\u00020JH\u0002¢\u0006\u0002\u0010KR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u000f\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R(\u0010\u0014\u001a\u001c\u0012\u0018\u0012\u0016\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u000f0\u000e\u0012\u0004\u0012\u00020\f0\u00160\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006L"}, m183d2 = {"Lexpo/modules/adapters/react/permissions/PermissionsService;", "Lexpo/modules/core/interfaces/InternalModule;", "Lexpo/modules/interfaces/permissions/Permissions;", "Lexpo/modules/core/interfaces/LifecycleEventListener;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "mActivityProvider", "Lexpo/modules/core/interfaces/ActivityProvider;", "mAskAsyncListener", "Lexpo/modules/interfaces/permissions/PermissionsResponseListener;", "mAskAsyncRequestedPermissions", "", "", "[Ljava/lang/String;", "mAskedPermissionsCache", "Landroid/content/SharedPreferences;", "mCurrentPermissionListener", "mPendingPermissionCalls", "Ljava/util/Queue;", "Lkotlin/Pair;", "mWriteSettingsPermissionBeingAsked", "", "addToAskedPermissionsCache", "", "permissions", "([Ljava/lang/String;)V", "askForManifestPermissions", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "([Ljava/lang/String;Lexpo/modules/interfaces/permissions/PermissionsResponseListener;)V", "askForPermissions", "responseListener", "(Lexpo/modules/interfaces/permissions/PermissionsResponseListener;[Ljava/lang/String;)V", "askForPermissionsWithPromise", BaseJavaModule.METHOD_TYPE_PROMISE, "Lexpo/modules/core/Promise;", "(Lexpo/modules/core/Promise;[Ljava/lang/String;)V", "askForWriteSettingsPermissionFirst", PermissionsResponse.CAN_ASK_AGAIN_KEY, "permission", "createListenerWithPendingPermissionsRequest", "Lcom/facebook/react/modules/core/PermissionListener;", "delegateRequestToActivity", "didAsk", "getExportedInterfaces", "", "Ljava/lang/Class;", "", "getManifestPermission", "", "getManifestPermissionFromContext", "getPermissionResponseFromNativeResponse", "Lexpo/modules/interfaces/permissions/PermissionsResponse;", "result", "getPermissions", "getPermissionsWithPromise", "hasGrantedPermissions", "([Ljava/lang/String;)Z", "hasWriteSettingsPermission", "isPermissionGranted", "isPermissionPresentInManifest", "isRuntimePermissionsAvailable", "onCreate", "moduleRegistry", "Lexpo/modules/core/ModuleRegistry;", "onHostDestroy", "onHostPause", "onHostResume", "parseNativeResult", "", "permissionsString", "grantResults", "", "([Ljava/lang/String;[I)Ljava/util/Map;", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public class PermissionsService implements InternalModule, Permissions, LifecycleEventListener {
    private final Context context;
    private ActivityProvider mActivityProvider;
    private PermissionsResponseListener mAskAsyncListener;
    private String[] mAskAsyncRequestedPermissions;
    private SharedPreferences mAskedPermissionsCache;
    private PermissionsResponseListener mCurrentPermissionListener;
    private final Queue<Tuples<String[], PermissionsResponseListener>> mPendingPermissionCalls;
    private boolean mWriteSettingsPermissionBeingAsked;

    @Override // expo.modules.core.interfaces.RegistryLifecycleListener
    public /* synthetic */ void onDestroy() {
        RegistryLifecycleListener.CC.$default$onDestroy(this);
    }

    @Override // expo.modules.core.interfaces.LifecycleEventListener
    public void onHostDestroy() {
    }

    @Override // expo.modules.core.interfaces.LifecycleEventListener
    public void onHostPause() {
    }

    public PermissionsService(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.mPendingPermissionCalls = new LinkedList();
    }

    public final Context getContext() {
        return this.context;
    }

    private final boolean didAsk(String str) {
        SharedPreferences sharedPreferences = this.mAskedPermissionsCache;
        if (sharedPreferences == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAskedPermissionsCache");
            sharedPreferences = null;
        }
        return sharedPreferences.getBoolean(str, false);
    }

    private final void addToAskedPermissionsCache(String[] strArr) {
        SharedPreferences sharedPreferences = this.mAskedPermissionsCache;
        if (sharedPreferences == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAskedPermissionsCache");
            sharedPreferences = null;
        }
        SharedPreferences.Editor edit = sharedPreferences.edit();
        int r1 = 0;
        int length = strArr.length;
        while (r1 < length) {
            String str = strArr[r1];
            r1++;
            edit.putBoolean(str, true);
        }
        edit.apply();
    }

    @Override // expo.modules.core.interfaces.InternalModule
    public List<Class<? extends Object>> getExportedInterfaces() {
        return CollectionsKt.listOf(Permissions.class);
    }

    @Override // expo.modules.core.interfaces.RegistryLifecycleListener
    public void onCreate(ModuleRegistry moduleRegistry) throws IllegalStateException {
        Intrinsics.checkNotNullParameter(moduleRegistry, "moduleRegistry");
        ActivityProvider activityProvider = (ActivityProvider) moduleRegistry.getModule(ActivityProvider.class);
        if (activityProvider == null) {
            throw new IllegalStateException("Couldn't find implementation for ActivityProvider.");
        }
        this.mActivityProvider = activityProvider;
        ((UIManager) moduleRegistry.getModule(UIManager.class)).registerLifecycleEventListener(this);
        SharedPreferences sharedPreferences = this.context.getApplicationContext().getSharedPreferences("expo.modules.permissions.asked", 0);
        Intrinsics.checkNotNullExpressionValue(sharedPreferences, "context.applicationConte…ME, Context.MODE_PRIVATE)");
        this.mAskedPermissionsCache = sharedPreferences;
    }

    @Override // expo.modules.interfaces.permissions.Permissions
    public void getPermissionsWithPromise(final Promise promise, String... permissions) {
        Intrinsics.checkNotNullParameter(promise, "promise");
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        getPermissions(new PermissionsResponseListener() { // from class: expo.modules.adapters.react.permissions.PermissionsService$$ExternalSyntheticLambda3
            @Override // expo.modules.interfaces.permissions.PermissionsResponseListener
            public final void onResult(Map map) {
                PermissionsService.m1574getPermissionsWithPromise$lambda6(Promise.this, map);
            }
        }, (String[]) Arrays.copyOf(permissions, permissions.length));
    }

    @Override // expo.modules.interfaces.permissions.Permissions
    public void askForPermissionsWithPromise(final Promise promise, final String... permissions) {
        Intrinsics.checkNotNullParameter(promise, "promise");
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        askForPermissions(new PermissionsResponseListener() { // from class: expo.modules.adapters.react.permissions.PermissionsService$$ExternalSyntheticLambda1
            @Override // expo.modules.interfaces.permissions.PermissionsResponseListener
            public final void onResult(Map map) {
                PermissionsService.m1572askForPermissionsWithPromise$lambda7(PermissionsService.this, promise, permissions, map);
            }
        }, (String[]) Arrays.copyOf(permissions, permissions.length));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: askForPermissionsWithPromise$lambda-7  reason: not valid java name */
    public static final void m1572askForPermissionsWithPromise$lambda7(PermissionsService this$0, Promise promise, String[] permissions, Map map) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(promise, "$promise");
        Intrinsics.checkNotNullParameter(permissions, "$permissions");
        this$0.getPermissionsWithPromise(promise, (String[]) Arrays.copyOf(permissions, permissions.length));
    }

    @Override // expo.modules.interfaces.permissions.Permissions
    public void askForPermissions(final PermissionsResponseListener responseListener, String... permissions) throws IllegalStateException {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        if (ArraysKt.contains(permissions, "android.permission.WRITE_SETTINGS") && isRuntimePermissionsAvailable()) {
            List mutableList = ArraysKt.toMutableList(permissions);
            mutableList.remove("android.permission.WRITE_SETTINGS");
            Object[] array = mutableList.toArray(new String[0]);
            Objects.requireNonNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
            String[] strArr = (String[]) array;
            PermissionsResponseListener permissionsResponseListener = new PermissionsResponseListener() { // from class: expo.modules.adapters.react.permissions.PermissionsService$$ExternalSyntheticLambda2
                @Override // expo.modules.interfaces.permissions.PermissionsResponseListener
                public final void onResult(Map map) {
                    PermissionsService.m1571askForPermissions$lambda10(PermissionsService.this, responseListener, map);
                }
            };
            if (!hasWriteSettingsPermission()) {
                if (this.mAskAsyncListener != null) {
                    throw new IllegalStateException("Another permissions request is in progress. Await the old request and then try again.");
                }
                this.mAskAsyncListener = permissionsResponseListener;
                this.mAskAsyncRequestedPermissions = strArr;
                addToAskedPermissionsCache(new String[]{"android.permission.WRITE_SETTINGS"});
                askForWriteSettingsPermissionFirst();
                return;
            }
            askForManifestPermissions(strArr, permissionsResponseListener);
            return;
        }
        askForManifestPermissions(permissions, responseListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: askForPermissions$lambda-10  reason: not valid java name */
    public static final void m1571askForPermissions$lambda10(PermissionsService this$0, PermissionsResponseListener responseListener, Map it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(responseListener, "$responseListener");
        int r0 = this$0.hasWriteSettingsPermission() ? 0 : -1;
        Intrinsics.checkNotNullExpressionValue(it, "it");
        it.put("android.permission.WRITE_SETTINGS", this$0.getPermissionResponseFromNativeResponse("android.permission.WRITE_SETTINGS", r0));
        responseListener.onResult(it);
    }

    @Override // expo.modules.interfaces.permissions.Permissions
    public boolean isPermissionPresentInManifest(String permission) {
        Intrinsics.checkNotNullParameter(permission, "permission");
        try {
            PackageInfo packageInfo = this.context.getPackageManager().getPackageInfo(this.context.getPackageName(), 4096);
            if (packageInfo == null) {
                return false;
            }
            String[] requestedPermissions = packageInfo.requestedPermissions;
            Intrinsics.checkNotNullExpressionValue(requestedPermissions, "requestedPermissions");
            return ArraysKt.contains(requestedPermissions, permission);
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    private final boolean isPermissionGranted(String str) {
        return Intrinsics.areEqual(str, "android.permission.WRITE_SETTINGS") ? hasWriteSettingsPermission() : getManifestPermission(str) == 0;
    }

    private final int getManifestPermission(String str) {
        Activity currentActivity;
        ActivityProvider activityProvider = this.mActivityProvider;
        if (activityProvider != null && (currentActivity = activityProvider.getCurrentActivity()) != null && (currentActivity instanceof PermissionAwareActivity)) {
            return ContextCompat.checkSelfPermission(currentActivity, str);
        }
        return getManifestPermissionFromContext(str);
    }

    protected int getManifestPermissionFromContext(String permission) {
        Intrinsics.checkNotNullParameter(permission, "permission");
        return ContextCompat.checkSelfPermission(this.context, permission);
    }

    private final boolean canAskAgain(String str) {
        Activity currentActivity;
        ActivityProvider activityProvider = this.mActivityProvider;
        if (activityProvider == null || (currentActivity = activityProvider.getCurrentActivity()) == null) {
            return false;
        }
        return ActivityCompat.shouldShowRequestPermissionRationale(currentActivity, str);
    }

    private final Map<String, PermissionsResponse> parseNativeResult(String[] strArr, int[] r5) {
        HashMap hashMap = new HashMap();
        for (Tuples tuples : ArraysKt.zip(r5, (Object[]) strArr)) {
            int intValue = ((Number) tuples.component1()).intValue();
            String str = (String) tuples.component2();
            hashMap.put(str, getPermissionResponseFromNativeResponse(str, intValue));
        }
        return hashMap;
    }

    private final PermissionsResponse getPermissionResponseFromNativeResponse(String str, int r4) {
        PermissionsStatus permissionsStatus;
        if (r4 == 0) {
            permissionsStatus = PermissionsStatus.GRANTED;
        } else {
            permissionsStatus = didAsk(str) ? PermissionsStatus.DENIED : PermissionsStatus.UNDETERMINED;
        }
        return new PermissionsResponse(permissionsStatus, permissionsStatus == PermissionsStatus.DENIED ? canAskAgain(str) : true);
    }

    protected void askForManifestPermissions(String[] permissions, PermissionsResponseListener listener) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(listener, "listener");
        if (!isRuntimePermissionsAvailable()) {
            addToAskedPermissionsCache(permissions);
            ArrayList arrayList = new ArrayList(permissions.length);
            int r1 = 0;
            int length = permissions.length;
            while (r1 < length) {
                String str = permissions[r1];
                r1++;
                arrayList.add(Integer.valueOf(getManifestPermission(str)));
            }
            listener.onResult(parseNativeResult(permissions, CollectionsKt.toIntArray(arrayList)));
            return;
        }
        delegateRequestToActivity(permissions, listener);
    }

    protected final void delegateRequestToActivity(String[] permissions, PermissionsResponseListener listener) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(listener, "listener");
        addToAskedPermissionsCache(permissions);
        ActivityProvider activityProvider = this.mActivityProvider;
        Activity currentActivity = activityProvider == null ? null : activityProvider.getCurrentActivity();
        if (currentActivity instanceof PermissionAwareActivity) {
            synchronized (this) {
                if (this.mCurrentPermissionListener != null) {
                    Boolean.valueOf(this.mPendingPermissionCalls.add(TuplesKt.m176to(permissions, listener)));
                } else {
                    this.mCurrentPermissionListener = listener;
                    ((PermissionAwareActivity) currentActivity).requestPermissions(permissions, 13, createListenerWithPendingPermissionsRequest());
                    Unit unit = Unit.INSTANCE;
                }
            }
            return;
        }
        int length = permissions.length;
        int[] r2 = new int[length];
        for (int r0 = 0; r0 < length; r0++) {
            r2[r0] = -1;
        }
        listener.onResult(parseNativeResult(permissions, r2));
    }

    private final PermissionListener createListenerWithPendingPermissionsRequest() {
        return new PermissionListener() { // from class: expo.modules.adapters.react.permissions.PermissionsService$$ExternalSyntheticLambda0
            @Override // com.facebook.react.modules.core.PermissionListener
            public final boolean onRequestPermissionsResult(int r2, String[] strArr, int[] r4) {
                boolean m1573createListenerWithPendingPermissionsRequest$lambda22;
                m1573createListenerWithPendingPermissionsRequest$lambda22 = PermissionsService.m1573createListenerWithPendingPermissionsRequest$lambda22(PermissionsService.this, r2, strArr, r4);
                return m1573createListenerWithPendingPermissionsRequest$lambda22;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: createListenerWithPendingPermissionsRequest$lambda-22  reason: not valid java name */
    public static final boolean m1573createListenerWithPendingPermissionsRequest$lambda22(PermissionsService this$0, int r6, String[] receivePermissions, int[] grantResults) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (r6 == 13) {
            synchronized (this$0) {
                PermissionsResponseListener permissionsResponseListener = this$0.mCurrentPermissionListener;
                if (permissionsResponseListener == null) {
                    throw new IllegalArgumentException("Required value was null.".toString());
                }
                Intrinsics.checkNotNullExpressionValue(receivePermissions, "receivePermissions");
                Intrinsics.checkNotNullExpressionValue(grantResults, "grantResults");
                permissionsResponseListener.onResult(this$0.parseNativeResult(receivePermissions, grantResults));
                this$0.mCurrentPermissionListener = null;
                Tuples<String[], PermissionsResponseListener> poll = this$0.mPendingPermissionCalls.poll();
                if (poll != null) {
                    ActivityProvider activityProvider = this$0.mActivityProvider;
                    Activity currentActivity = activityProvider == null ? null : activityProvider.getCurrentActivity();
                    PermissionAwareActivity permissionAwareActivity = currentActivity instanceof PermissionAwareActivity ? (PermissionAwareActivity) currentActivity : null;
                    if (permissionAwareActivity == null) {
                        PermissionsResponseListener second = poll.getSecond();
                        String[] first = poll.getFirst();
                        int length = poll.getFirst().length;
                        int[] r0 = new int[length];
                        for (int r2 = 0; r2 < length; r2++) {
                            r0[r2] = -1;
                        }
                        second.onResult(this$0.parseNativeResult(first, r0));
                        Iterator<T> it = this$0.mPendingPermissionCalls.iterator();
                        while (it.hasNext()) {
                            Tuples tuples = (Tuples) it.next();
                            PermissionsResponseListener permissionsResponseListener2 = (PermissionsResponseListener) tuples.getSecond();
                            String[] strArr = (String[]) tuples.getFirst();
                            int length2 = ((Object[]) tuples.getFirst()).length;
                            int[] r22 = new int[length2];
                            for (int r4 = 0; r4 < length2; r4++) {
                                r22[r4] = -1;
                            }
                            permissionsResponseListener2.onResult(this$0.parseNativeResult(strArr, r22));
                        }
                        this$0.mPendingPermissionCalls.clear();
                    } else {
                        this$0.mCurrentPermissionListener = poll.getSecond();
                        permissionAwareActivity.requestPermissions(poll.getFirst(), 13, this$0.createListenerWithPendingPermissionsRequest());
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    private final void askForWriteSettingsPermissionFirst() {
        Intent intent = new Intent("android.settings.action.MANAGE_WRITE_SETTINGS");
        String packageName = getContext().getPackageName();
        intent.setData(Uri.parse("package:" + packageName));
        intent.addFlags(268435456);
        this.mWriteSettingsPermissionBeingAsked = true;
        getContext().startActivity(intent);
    }

    private final boolean hasWriteSettingsPermission() {
        if (isRuntimePermissionsAvailable()) {
            return Settings.System.canWrite(this.context.getApplicationContext());
        }
        return true;
    }

    private final boolean isRuntimePermissionsAvailable() {
        return Build.VERSION.SDK_INT >= 23;
    }

    @Override // expo.modules.core.interfaces.LifecycleEventListener
    public void onHostResume() {
        if (this.mWriteSettingsPermissionBeingAsked) {
            this.mWriteSettingsPermissionBeingAsked = false;
            PermissionsResponseListener permissionsResponseListener = this.mAskAsyncListener;
            Intrinsics.checkNotNull(permissionsResponseListener);
            String[] strArr = this.mAskAsyncRequestedPermissions;
            Intrinsics.checkNotNull(strArr);
            this.mAskAsyncListener = null;
            this.mAskAsyncRequestedPermissions = null;
            if (!(strArr.length == 0)) {
                askForManifestPermissions(strArr, permissionsResponseListener);
            } else {
                permissionsResponseListener.onResult(new LinkedHashMap());
            }
        }
    }

    @Override // expo.modules.interfaces.permissions.Permissions
    public void getPermissions(PermissionsResponseListener responseListener, String... permissions) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        ArrayList arrayList = new ArrayList(permissions.length);
        int length = permissions.length;
        int r3 = 0;
        while (r3 < length) {
            String str = permissions[r3];
            r3++;
            arrayList.add(Integer.valueOf(isPermissionGranted(str) ? 0 : -1));
        }
        responseListener.onResult(parseNativeResult(permissions, CollectionsKt.toIntArray(arrayList)));
    }

    @Override // expo.modules.interfaces.permissions.Permissions
    public boolean hasGrantedPermissions(String... permissions) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        int length = permissions.length;
        int r2 = 0;
        while (r2 < length) {
            String str = permissions[r2];
            r2++;
            if (!isPermissionGranted(str)) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: getPermissionsWithPromise$lambda-6  reason: not valid java name */
    public static final void m1574getPermissionsWithPromise$lambda6(Promise promise, Map permissionsMap) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        String status;
        Intrinsics.checkNotNullParameter(promise, "$promise");
        Intrinsics.checkNotNullParameter(permissionsMap, "permissionsMap");
        boolean z5 = false;
        if (!permissionsMap.isEmpty()) {
            for (Map.Entry entry : permissionsMap.entrySet()) {
                if (((PermissionsResponse) entry.getValue()).getStatus() == PermissionsStatus.GRANTED) {
                    z = true;
                    continue;
                } else {
                    z = false;
                    continue;
                }
                if (!z) {
                    z2 = false;
                    break;
                }
            }
        }
        z2 = true;
        if (!permissionsMap.isEmpty()) {
            for (Map.Entry entry2 : permissionsMap.entrySet()) {
                if (((PermissionsResponse) entry2.getValue()).getStatus() == PermissionsStatus.DENIED) {
                    z3 = true;
                    continue;
                } else {
                    z3 = false;
                    continue;
                }
                if (!z3) {
                    z4 = false;
                    break;
                }
            }
        }
        z4 = true;
        if (!permissionsMap.isEmpty()) {
            for (Map.Entry entry3 : permissionsMap.entrySet()) {
                if (!((PermissionsResponse) entry3.getValue()).getCanAskAgain()) {
                    break;
                }
            }
        }
        z5 = true;
        Bundle bundle = new Bundle();
        bundle.putString(PermissionsResponse.EXPIRES_KEY, "never");
        if (z2) {
            status = PermissionsStatus.GRANTED.getStatus();
        } else if (z4) {
            status = PermissionsStatus.DENIED.getStatus();
        } else {
            status = PermissionsStatus.UNDETERMINED.getStatus();
        }
        bundle.putString("status", status);
        bundle.putBoolean(PermissionsResponse.CAN_ASK_AGAIN_KEY, z5);
        bundle.putBoolean(PermissionsResponse.GRANTED_KEY, z2);
        promise.resolve(bundle);
    }
}
