package expo.modules.inapppurchases;

import android.os.Bundle;
import android.os.Parcelable;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.Purchase;
import expo.modules.core.Promise;
import expo.modules.core.interfaces.services.EventEmitter;
import expo.modules.inapppurchases.BillingManager;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class UpdateListener implements BillingManager.BillingUpdatesListener {
    private static final String TAG = "UpdateListener";
    private EventEmitter mEventEmitter;

    @Override // expo.modules.inapppurchases.BillingManager.BillingUpdatesListener
    public void onBillingClientSetupFinished() {
    }

    public UpdateListener(EventEmitter eventEmitter) {
        this.mEventEmitter = eventEmitter;
    }

    @Override // expo.modules.inapppurchases.BillingManager.BillingUpdatesListener
    public void onConsumeFinished(String str, BillingResult billingResult) {
        Bundle bundle = new Bundle();
        bundle.putInt("responseCode", billingResult.getResponseCode());
        bundle.putString("token", str);
        Promise promise = BillingManager.promises.get(BillingManager.ACKNOWLEDGING_PURCHASE);
        if (promise != null) {
            BillingManager.promises.put(BillingManager.ACKNOWLEDGING_PURCHASE, null);
            promise.resolve(bundle);
        }
    }

    @Override // expo.modules.inapppurchases.BillingManager.BillingUpdatesListener
    public void onPurchasesUpdated(List<Purchase> list) {
        Bundle bundle = new Bundle();
        ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
        for (Purchase purchase : list) {
            arrayList.add(BillingManager.purchaseToBundle(purchase));
        }
        bundle.putParcelableArrayList("results", arrayList);
        bundle.putInt("responseCode", 0);
        this.mEventEmitter.emit(BillingManager.PURCHASES_UPDATED_EVENT, bundle);
    }
}
