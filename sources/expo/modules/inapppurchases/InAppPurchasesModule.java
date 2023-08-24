package expo.modules.inapppurchases;

import android.app.Activity;
import android.content.Context;
import expo.modules.core.ExportedModule;
import expo.modules.core.ModuleRegistry;
import expo.modules.core.Promise;
import expo.modules.core.arguments.ReadableArguments;
import expo.modules.core.interfaces.ActivityProvider;
import expo.modules.core.interfaces.ExpoMethod;
import expo.modules.core.interfaces.RegistryLifecycleListener;
import expo.modules.core.interfaces.services.EventEmitter;
import java.util.List;

/* loaded from: classes4.dex */
public class InAppPurchasesModule extends ExportedModule implements RegistryLifecycleListener {
    private static final String NAME = "ExpoInAppPurchases";
    private static final String TAG = "InAppPurchasesModule";
    private final String USE_GOOGLE_PLAY_CACHE_KEY;
    private BillingManager mBillingManager;
    private EventEmitter mEventEmitter;
    private ModuleRegistry mModuleRegistry;

    @Override // expo.modules.core.ExportedModule
    public String getName() {
        return NAME;
    }

    public InAppPurchasesModule(Context context) {
        super(context);
        this.USE_GOOGLE_PLAY_CACHE_KEY = "useGooglePlayCache";
    }

    @Override // expo.modules.core.ExportedModule, expo.modules.core.interfaces.RegistryLifecycleListener
    public void onCreate(ModuleRegistry moduleRegistry) {
        this.mModuleRegistry = moduleRegistry;
    }

    @ExpoMethod
    public void connectAsync(Promise promise) {
        Activity currentActivity = getCurrentActivity();
        if (currentActivity == null) {
            promise.reject("E_ACTIVITY_UNAVAILABLE", "Activity is not available");
        }
        this.mEventEmitter = (EventEmitter) this.mModuleRegistry.getModule(EventEmitter.class);
        BillingManager billingManager = new BillingManager(currentActivity, this.mEventEmitter);
        this.mBillingManager = billingManager;
        billingManager.startConnection(promise);
    }

    @ExpoMethod
    public void getProductsAsync(List<String> list, Promise promise) {
        this.mBillingManager.queryPurchasableItems(list, promise);
    }

    @ExpoMethod
    public void getPurchaseHistoryAsync(ReadableArguments readableArguments, Promise promise) {
        if (readableArguments.getBoolean("useGooglePlayCache", true)) {
            this.mBillingManager.queryPurchases(promise);
        } else {
            this.mBillingManager.queryPurchaseHistoryAsync(promise);
        }
    }

    @ExpoMethod
    public void purchaseItemAsync(String str, ReadableArguments readableArguments, Promise promise) {
        this.mBillingManager.purchaseItemAsync(str, readableArguments, promise);
    }

    @ExpoMethod
    public void getBillingResponseCodeAsync(Promise promise) {
        promise.resolve(Integer.valueOf(this.mBillingManager.getBillingClientResponseCode()));
    }

    @ExpoMethod
    public void finishTransactionAsync(String str, Boolean bool, Promise promise) {
        if (bool != null && bool.booleanValue()) {
            this.mBillingManager.consumeAsync(str, promise);
        } else {
            this.mBillingManager.acknowledgePurchaseAsync(str, promise);
        }
    }

    @ExpoMethod
    public void disconnectAsync(Promise promise) {
        BillingManager billingManager = this.mBillingManager;
        if (billingManager != null) {
            billingManager.destroy();
            this.mBillingManager = null;
        }
        promise.resolve(null);
    }

    private Activity getCurrentActivity() {
        ActivityProvider activityProvider = (ActivityProvider) this.mModuleRegistry.getModule(ActivityProvider.class);
        if (activityProvider != null) {
            return activityProvider.getCurrentActivity();
        }
        return null;
    }
}
