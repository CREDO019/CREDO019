package expo.modules.screenorientation;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.uimanager.ViewProps;
import expo.modules.core.ExportedModule;
import expo.modules.core.ModuleRegistry;
import expo.modules.core.Promise;
import expo.modules.core.errors.InvalidArgumentException;
import expo.modules.core.interfaces.ActivityProvider;
import expo.modules.core.interfaces.ExpoMethod;
import expo.modules.core.interfaces.LifecycleEventListener;
import expo.modules.core.interfaces.services.UIManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ScreenOrientationModule.kt */
@Metadata(m184d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\tH\u0002J\b\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0007J\u0010\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0007J\u0010\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0007J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0010\u0010\u0019\u001a\u00020\t2\u0006\u0010\u001a\u001a\u00020\tH\u0002J \u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\t2\u0006\u0010\u001e\u001a\u00020\t2\u0006\u0010\u001f\u001a\u00020\tH\u0002J\u0018\u0010 \u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u0012H\u0007J\u0018\u0010!\u001a\u00020\u00102\u0006\u0010\"\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u0012H\u0007J\u0010\u0010#\u001a\u00020\u00102\u0006\u0010$\u001a\u00020%H\u0016J\b\u0010&\u001a\u00020\u0010H\u0016J\b\u0010'\u001a\u00020\u0010H\u0016J\b\u0010(\u001a\u00020\u0010H\u0016J\b\u0010)\u001a\u00020\u0010H\u0016J\u0018\u0010*\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u0012H\u0007R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u0012\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\n¨\u0006+"}, m183d2 = {"Lexpo/modules/screenorientation/ScreenOrientationModule;", "Lexpo/modules/core/ExportedModule;", "Lexpo/modules/core/interfaces/LifecycleEventListener;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "mActivityProvider", "Lexpo/modules/core/interfaces/ActivityProvider;", "mInitialOrientation", "", "Ljava/lang/Integer;", "exportOrientationLock", "nativeOrientationLock", "getName", "", "getOrientationAsync", "", BaseJavaModule.METHOD_TYPE_PROMISE, "Lexpo/modules/core/Promise;", "getOrientationLockAsync", "getPlatformOrientationLockAsync", "getScreenOrientation", "Lexpo/modules/screenorientation/Orientation;", "activity", "Landroid/app/Activity;", "importOrientationLock", "orientationLock", "isPortraitNaturalOrientation", "", ViewProps.ROTATION, "width", "height", "lockAsync", "lockPlatformAsync", "orientationAttr", "onCreate", "moduleRegistry", "Lexpo/modules/core/ModuleRegistry;", "onDestroy", "onHostDestroy", "onHostPause", "onHostResume", "supportsOrientationLockAsync", "expo-screen-orientation_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class ScreenOrientationModule extends ExportedModule implements LifecycleEventListener {
    private ActivityProvider mActivityProvider;
    private Integer mInitialOrientation;

    private final int exportOrientationLock(int r3) {
        if (r3 != -1) {
            if (r3 != 0) {
                if (r3 != 1) {
                    switch (r3) {
                        case 6:
                            return 5;
                        case 7:
                            return 2;
                        case 8:
                            return 6;
                        case 9:
                            return 4;
                        case 10:
                            return 1;
                        default:
                            return 8;
                    }
                }
                return 3;
            }
            return 7;
        }
        return 0;
    }

    private final boolean isPortraitNaturalOrientation(int r3, int r4, int r5) {
        if ((r3 == 0 || r3 == 2) && r5 > r4) {
            return true;
        }
        return (r3 == 1 || r3 == 3) && r4 > r5;
    }

    @Override // expo.modules.core.ExportedModule
    public String getName() {
        return "ExpoScreenOrientation";
    }

    @Override // expo.modules.core.interfaces.LifecycleEventListener
    public void onHostDestroy() {
    }

    @Override // expo.modules.core.interfaces.LifecycleEventListener
    public void onHostPause() {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ScreenOrientationModule(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    @Override // expo.modules.core.ExportedModule, expo.modules.core.interfaces.RegistryLifecycleListener
    public void onCreate(ModuleRegistry moduleRegistry) {
        Intrinsics.checkNotNullParameter(moduleRegistry, "moduleRegistry");
        ActivityProvider activityProvider = (ActivityProvider) moduleRegistry.getModule(ActivityProvider.class);
        if (activityProvider == null) {
            throw new IllegalStateException("Could not find implementation for ActivityProvider.");
        }
        this.mActivityProvider = activityProvider;
        UIManager uIManager = (UIManager) moduleRegistry.getModule(UIManager.class);
        if (uIManager == null) {
            throw new IllegalStateException("Could not find implementation for UIManager.");
        }
        uIManager.registerLifecycleEventListener(this);
    }

    @Override // expo.modules.core.interfaces.LifecycleEventListener
    public void onHostResume() {
        ActivityProvider activityProvider = this.mActivityProvider;
        if (activityProvider == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mActivityProvider");
            activityProvider = null;
        }
        Activity currentActivity = activityProvider.getCurrentActivity();
        if (currentActivity != null && this.mInitialOrientation == null) {
            this.mInitialOrientation = Integer.valueOf(currentActivity.getRequestedOrientation());
        }
    }

    @Override // expo.modules.core.ExportedModule, expo.modules.core.interfaces.RegistryLifecycleListener
    public void onDestroy() {
        Integer num;
        ActivityProvider activityProvider = this.mActivityProvider;
        if (activityProvider == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mActivityProvider");
            activityProvider = null;
        }
        Activity currentActivity = activityProvider.getCurrentActivity();
        if (currentActivity == null || (num = this.mInitialOrientation) == null) {
            return;
        }
        currentActivity.setRequestedOrientation(num.intValue());
    }

    @ExpoMethod
    public final void lockAsync(int r4, Promise promise) {
        Intrinsics.checkNotNullParameter(promise, "promise");
        ActivityProvider activityProvider = this.mActivityProvider;
        if (activityProvider == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mActivityProvider");
            activityProvider = null;
        }
        Activity currentActivity = activityProvider.getCurrentActivity();
        if (currentActivity != null) {
            try {
                currentActivity.setRequestedOrientation(importOrientationLock(r4));
                promise.resolve(null);
                return;
            } catch (InvalidArgumentException e) {
                promise.reject("ERR_SCREEN_ORIENTATION_INVALID_ORIENTATION_LOCK", "An invalid OrientationLock was passed in: " + r4, e);
                return;
            } catch (Exception e2) {
                promise.reject("ERR_SCREEN_ORIENTATION_UNSUPPORTED_ORIENTATION_LOCK", "Could not apply the ScreenOrientation lock: " + r4, e2);
                return;
            }
        }
        promise.reject("ERR_SCREEN_ORIENTATION_MISSING_ACTIVITY", "Could not find activity.", null);
    }

    @ExpoMethod
    public final void lockPlatformAsync(int r4, Promise promise) {
        Intrinsics.checkNotNullParameter(promise, "promise");
        ActivityProvider activityProvider = this.mActivityProvider;
        if (activityProvider == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mActivityProvider");
            activityProvider = null;
        }
        Activity currentActivity = activityProvider.getCurrentActivity();
        if (currentActivity != null) {
            try {
                currentActivity.setRequestedOrientation(r4);
                promise.resolve(null);
                return;
            } catch (Exception e) {
                promise.reject("ERR_SCREEN_ORIENTATION_UNSUPPORTED_ORIENTATION_LOCK", "Could not apply the ScreenOrientation platform lock: " + r4, e);
                return;
            }
        }
        promise.reject("ERR_SCREEN_ORIENTATION_MISSING_ACTIVITY", "Could not find activity.", null);
    }

    @ExpoMethod
    public final void getOrientationAsync(Promise promise) {
        Intrinsics.checkNotNullParameter(promise, "promise");
        ActivityProvider activityProvider = this.mActivityProvider;
        if (activityProvider == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mActivityProvider");
            activityProvider = null;
        }
        Activity currentActivity = activityProvider.getCurrentActivity();
        if (currentActivity != null) {
            promise.resolve(Integer.valueOf(getScreenOrientation(currentActivity).getValue()));
        } else {
            promise.reject("ERR_SCREEN_ORIENTATION_MISSING_ACTIVITY", "Could not find activity.", null);
        }
    }

    @ExpoMethod
    public final void getOrientationLockAsync(Promise promise) {
        Intrinsics.checkNotNullParameter(promise, "promise");
        ActivityProvider activityProvider = this.mActivityProvider;
        if (activityProvider == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mActivityProvider");
            activityProvider = null;
        }
        Activity currentActivity = activityProvider.getCurrentActivity();
        if (currentActivity != null) {
            try {
                promise.resolve(Integer.valueOf(exportOrientationLock(currentActivity.getRequestedOrientation())));
                return;
            } catch (Exception e) {
                promise.reject("ERR_SCREEN_ORIENTATION_GET_ORIENTATION_LOCK", "Could not get the current screen orientation lock", e);
                return;
            }
        }
        promise.reject("ERR_SCREEN_ORIENTATION_MISSING_ACTIVITY", "Could not find activity.", null);
    }

    @ExpoMethod
    public final void getPlatformOrientationLockAsync(Promise promise) {
        Intrinsics.checkNotNullParameter(promise, "promise");
        ActivityProvider activityProvider = this.mActivityProvider;
        if (activityProvider == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mActivityProvider");
            activityProvider = null;
        }
        Activity currentActivity = activityProvider.getCurrentActivity();
        if (currentActivity != null) {
            try {
                promise.resolve(Integer.valueOf(currentActivity.getRequestedOrientation()));
                return;
            } catch (Exception e) {
                promise.reject("ERR_SCREEN_ORIENTATION_GET_PLATFORM_ORIENTATION_LOCK", "Could not get the current screen orientation platform lock", e);
                return;
            }
        }
        promise.reject("ERR_SCREEN_ORIENTATION_MISSING_ACTIVITY", "Could not find activity.", null);
    }

    @ExpoMethod
    public final void supportsOrientationLockAsync(int r2, Promise promise) {
        Intrinsics.checkNotNullParameter(promise, "promise");
        try {
            importOrientationLock(r2);
            promise.resolve(true);
        } catch (Exception unused) {
            promise.resolve(false);
        }
    }

    private final Orientation getScreenOrientation(Activity activity) {
        WindowManager windowManager = activity.getWindowManager();
        if (windowManager == null) {
            return Orientation.UNKNOWN;
        }
        int rotation = windowManager.getDefaultDisplay().getRotation();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        Display defaultDisplay = windowManager.getDefaultDisplay();
        Intrinsics.checkNotNullExpressionValue(defaultDisplay, "windowManager.defaultDisplay");
        defaultDisplay.getMetrics(displayMetrics);
        if (isPortraitNaturalOrientation(rotation, displayMetrics.widthPixels, displayMetrics.heightPixels)) {
            if (rotation != 0) {
                if (rotation != 1) {
                    if (rotation != 2) {
                        if (rotation == 3) {
                            return Orientation.LANDSCAPE_LEFT;
                        }
                        return Orientation.UNKNOWN;
                    }
                    return Orientation.PORTRAIT_DOWN;
                }
                return Orientation.LANDSCAPE_RIGHT;
            }
            return Orientation.PORTRAIT_UP;
        } else if (rotation != 0) {
            if (rotation != 1) {
                if (rotation != 2) {
                    if (rotation == 3) {
                        return Orientation.PORTRAIT_UP;
                    }
                    return Orientation.UNKNOWN;
                }
                return Orientation.LANDSCAPE_LEFT;
            }
            return Orientation.PORTRAIT_DOWN;
        } else {
            return Orientation.LANDSCAPE_RIGHT;
        }
    }

    private final int importOrientationLock(int r4) throws InvalidArgumentException {
        switch (r4) {
            case 0:
                return -1;
            case 1:
                return 10;
            case 2:
                return 7;
            case 3:
                return 1;
            case 4:
                return 9;
            case 5:
                return 6;
            case 6:
                return 8;
            case 7:
                return 0;
            default:
                throw new InvalidArgumentException("OrientationLock " + r4 + " is not mappable to a native Android orientation attr");
        }
    }
}
