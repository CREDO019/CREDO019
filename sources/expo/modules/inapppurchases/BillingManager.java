package expo.modules.inapppurchases;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import com.android.billingclient.api.AcknowledgePurchaseParams;
import com.android.billingclient.api.AcknowledgePurchaseResponseListener;
import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.ConsumeParams;
import com.android.billingclient.api.ConsumeResponseListener;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchaseHistoryRecord;
import com.android.billingclient.api.PurchaseHistoryResponseListener;
import com.android.billingclient.api.PurchasesResponseListener;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.SkuDetails;
import com.android.billingclient.api.SkuDetailsParams;
import com.android.billingclient.api.SkuDetailsResponseListener;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import expo.modules.core.Promise;
import expo.modules.core.arguments.MapArguments;
import expo.modules.core.arguments.ReadableArguments;
import expo.modules.core.interfaces.services.EventEmitter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* loaded from: classes4.dex */
public class BillingManager implements PurchasesUpdatedListener {
    public static final String ACKNOWLEDGING_PURCHASE = "Acknowledging Item";
    public static final int BILLING_MANAGER_NOT_INITIALIZED = -1;
    public static final int ERROR = 2;
    public static final String INAPP_SUB_PERIOD = "P0D";

    /* renamed from: OK */
    public static final int f1443OK = 0;
    public static final String PURCHASES_UPDATED_EVENT = "Expo.purchasesUpdated";
    private static final String TAG = "BillingManager";
    public static final int USER_CANCELED = 1;
    protected static final HashMap<String, Promise> promises = new HashMap<>();
    private final Activity mActivity;
    private BillingClient mBillingClient;
    private BillingUpdatesListener mBillingUpdatesListener;
    private EventEmitter mEventEmitter;
    private boolean mIsServiceConnected;
    private Set<String> mTokensToBeConsumed;
    private int mBillingClientResponseCode = -1;
    private final List<Purchase> mPurchases = new ArrayList();
    private final HashMap<String, SkuDetails> mSkuDetailsMap = new HashMap<>();

    /* loaded from: classes4.dex */
    public interface BillingUpdatesListener {
        void onBillingClientSetupFinished();

        void onConsumeFinished(String str, BillingResult billingResult);

        void onPurchasesUpdated(List<Purchase> list);
    }

    /* loaded from: classes4.dex */
    public interface ServiceConnectedListener {
        void onServiceConnected(BillingResult billingResult);
    }

    private static int errorCodeNativeToJS(int r3) {
        if (r3 != -3) {
            if (r3 != -2) {
                if (r3 != -1) {
                    if (r3 != 2) {
                        if (r3 != 3) {
                            if (r3 != 4) {
                                if (r3 != 5) {
                                    if (r3 != 7) {
                                        return r3 != 8 ? 0 : 9;
                                    }
                                    return 8;
                                }
                                return 7;
                            }
                            return 6;
                        }
                        return 5;
                    }
                    return 3;
                }
                return 2;
            }
            return 1;
        }
        return 4;
    }

    private static int purchaseStateNativeToJS(int r1) {
        if (r1 != 0) {
            return r1 != 1 ? 0 : 1;
        }
        return 2;
    }

    public BillingManager(Activity activity, EventEmitter eventEmitter) {
        this.mActivity = activity;
        this.mEventEmitter = eventEmitter;
        this.mBillingUpdatesListener = new UpdateListener(eventEmitter);
        this.mBillingClient = BillingClient.newBuilder(activity).enablePendingPurchases().setListener(this).build();
    }

    public void startConnection(final Promise promise) {
        startServiceConnection(new Runnable() { // from class: expo.modules.inapppurchases.BillingManager.1
            @Override // java.lang.Runnable
            public void run() {
                BillingManager.this.mBillingUpdatesListener.onBillingClientSetupFinished();
                promise.resolve(null);
            }
        });
    }

    public void startServiceConnection(final Runnable runnable) {
        this.mBillingClient.startConnection(new BillingClientStateListener() { // from class: expo.modules.inapppurchases.BillingManager.2
            @Override // com.android.billingclient.api.BillingClientStateListener
            public void onBillingSetupFinished(BillingResult billingResult) {
                int responseCode = billingResult.getResponseCode();
                if (responseCode == 0) {
                    BillingManager.this.mIsServiceConnected = true;
                    Runnable runnable2 = runnable;
                    if (runnable2 != null) {
                        runnable2.run();
                    }
                }
                BillingManager.this.mBillingClientResponseCode = responseCode;
            }

            @Override // com.android.billingclient.api.BillingClientStateListener
            public void onBillingServiceDisconnected() {
                BillingManager.this.mIsServiceConnected = false;
            }
        });
    }

    public void purchaseItemAsync(final String str, ReadableArguments readableArguments, final Promise promise) {
        if (readableArguments == null) {
            readableArguments = new MapArguments();
        }
        final String string = readableArguments.getString("oldPurchaseToken");
        final ReadableArguments arguments = readableArguments.getArguments("accountIdentifiers");
        final Boolean valueOf = Boolean.valueOf(readableArguments.getBoolean("isVrPurchaseFlow"));
        executeServiceRequest(new Runnable() { // from class: expo.modules.inapppurchases.BillingManager.3
            @Override // java.lang.Runnable
            public void run() {
                SkuDetails skuDetails = (SkuDetails) BillingManager.this.mSkuDetailsMap.get(str);
                if (skuDetails == null) {
                    promise.reject("E_ITEM_NOT_QUERIED", "Must query item from store before calling purchase");
                    return;
                }
                BillingFlowParams.Builder skuDetails2 = BillingFlowParams.newBuilder().setSkuDetails(skuDetails);
                if (string != null) {
                    skuDetails2.setSubscriptionUpdateParams(BillingFlowParams.SubscriptionUpdateParams.newBuilder().setOldSkuPurchaseToken(string).build());
                }
                ReadableArguments readableArguments2 = arguments;
                if (readableArguments2 != null) {
                    String string2 = readableArguments2.getString("obfuscatedAccountId");
                    String string3 = arguments.getString("obfuscatedProfileId");
                    if (string2 != null && string3 != null) {
                        skuDetails2.setObfuscatedAccountId(string2);
                        skuDetails2.setObfuscatedProfileId(string3);
                    }
                }
                skuDetails2.setVrPurchaseFlow(valueOf.booleanValue());
                BillingManager.this.mBillingClient.launchBillingFlow(BillingManager.this.mActivity, skuDetails2.build());
            }
        });
    }

    public Context getContext() {
        return this.mActivity;
    }

    @Override // com.android.billingclient.api.PurchasesUpdatedListener
    public void onPurchasesUpdated(BillingResult billingResult, List<Purchase> list) {
        if (billingResult.getResponseCode() == 0 && list != null) {
            for (Purchase purchase : list) {
                handlePurchase(purchase);
            }
            this.mBillingUpdatesListener.onPurchasesUpdated(this.mPurchases);
            return;
        }
        this.mEventEmitter.emit(PURCHASES_UPDATED_EVENT, formatResponse(billingResult, null));
    }

    public void acknowledgePurchaseAsync(String str, final Promise promise) {
        AcknowledgePurchaseResponseListener acknowledgePurchaseResponseListener = new AcknowledgePurchaseResponseListener() { // from class: expo.modules.inapppurchases.BillingManager.4
            @Override // com.android.billingclient.api.AcknowledgePurchaseResponseListener
            public void onAcknowledgePurchaseResponse(BillingResult billingResult) {
                promise.resolve(BillingManager.formatResponse(billingResult, null));
            }
        };
        this.mBillingClient.acknowledgePurchase(AcknowledgePurchaseParams.newBuilder().setPurchaseToken(str).build(), acknowledgePurchaseResponseListener);
    }

    public void consumeAsync(final String str, Promise promise) {
        Set<String> set = this.mTokensToBeConsumed;
        if (set == null) {
            this.mTokensToBeConsumed = new HashSet();
        } else if (set.contains(str)) {
            Bundle bundle = new Bundle();
            bundle.putInt("responseCode", 0);
            promise.resolve(bundle);
            return;
        }
        HashMap<String, Promise> hashMap = promises;
        if (hashMap.get(ACKNOWLEDGING_PURCHASE) != null) {
            promise.reject("E_UNFINISHED_PROMISE", "Must wait for promise to resolve before recalling function.");
            return;
        }
        hashMap.put(ACKNOWLEDGING_PURCHASE, promise);
        this.mTokensToBeConsumed.add(str);
        final ConsumeResponseListener consumeResponseListener = new ConsumeResponseListener() { // from class: expo.modules.inapppurchases.BillingManager.5
            @Override // com.android.billingclient.api.ConsumeResponseListener
            public void onConsumeResponse(BillingResult billingResult, String str2) {
                BillingManager.this.mBillingUpdatesListener.onConsumeFinished(str2, billingResult);
            }
        };
        executeServiceRequest(new Runnable() { // from class: expo.modules.inapppurchases.BillingManager.6
            @Override // java.lang.Runnable
            public void run() {
                BillingManager.this.mBillingClient.consumeAsync(ConsumeParams.newBuilder().setPurchaseToken(str).build(), consumeResponseListener);
            }
        });
    }

    private void handlePurchase(Purchase purchase) {
        this.mPurchases.add(purchase);
    }

    public int getBillingClientResponseCode() {
        return this.mBillingClientResponseCode;
    }

    public void queryPurchases(final Promise promise) {
        executeServiceRequest(new Runnable() { // from class: expo.modules.inapppurchases.BillingManager.7
            @Override // java.lang.Runnable
            public void run() {
                final HashSet hashSet = new HashSet(Arrays.asList(BillingClient.SkuType.INAPP, BillingClient.SkuType.SUBS));
                final ArrayList arrayList = new ArrayList();
                final HashSet hashSet2 = new HashSet();
                final HashSet hashSet3 = new HashSet();
                BillingManager.this.mBillingClient.queryPurchasesAsync(BillingClient.SkuType.INAPP, new PurchasesResponseListener() { // from class: expo.modules.inapppurchases.BillingManager.7.1
                    @Override // com.android.billingclient.api.PurchasesResponseListener
                    public void onQueryPurchasesResponse(BillingResult billingResult, List<Purchase> list) {
                        if (billingResult.getResponseCode() == 0) {
                            arrayList.addAll(list);
                        }
                        hashSet3.add(billingResult);
                        hashSet2.add(BillingClient.SkuType.INAPP);
                        if (hashSet2.containsAll(hashSet) || !BillingManager.this.areSubscriptionsSupported()) {
                            BillingManager.this.onQueryPurchasesFinished(BillingManager.this.aggregateBillingResults(hashSet3), arrayList, promise);
                        }
                    }
                });
                if (BillingManager.this.areSubscriptionsSupported()) {
                    BillingManager.this.mBillingClient.queryPurchasesAsync(BillingClient.SkuType.SUBS, new PurchasesResponseListener() { // from class: expo.modules.inapppurchases.BillingManager.7.2
                        @Override // com.android.billingclient.api.PurchasesResponseListener
                        public void onQueryPurchasesResponse(BillingResult billingResult, List<Purchase> list) {
                            if (billingResult.getResponseCode() == 0) {
                                arrayList.addAll(list);
                            }
                            hashSet3.add(billingResult);
                            hashSet2.add(BillingClient.SkuType.SUBS);
                            if (hashSet2.containsAll(hashSet)) {
                                BillingManager.this.onQueryPurchasesFinished(BillingManager.this.aggregateBillingResults(hashSet3), arrayList, promise);
                            }
                        }
                    });
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BillingResult aggregateBillingResults(Set<BillingResult> set) {
        for (BillingResult billingResult : set) {
            if (billingResult.getResponseCode() != 0) {
                return billingResult;
            }
        }
        return set.iterator().next();
    }

    /* renamed from: expo.modules.inapppurchases.BillingManager$8 */
    /* loaded from: classes4.dex */
    class RunnableC44288 implements Runnable {
        final /* synthetic */ Promise val$promise;

        RunnableC44288(Promise promise) {
            this.val$promise = promise;
        }

        @Override // java.lang.Runnable
        public void run() {
            final ArrayList arrayList = new ArrayList();
            BillingManager.this.mBillingClient.queryPurchaseHistoryAsync(BillingClient.SkuType.INAPP, new PurchaseHistoryResponseListener() { // from class: expo.modules.inapppurchases.BillingManager.8.1
                @Override // com.android.billingclient.api.PurchaseHistoryResponseListener
                public void onPurchaseHistoryResponse(final BillingResult billingResult, List<PurchaseHistoryRecord> list) {
                    if (billingResult.getResponseCode() == 0 && list != null) {
                        for (PurchaseHistoryRecord purchaseHistoryRecord : list) {
                            arrayList.add(BillingManager.purchaseHistoryToBundle(purchaseHistoryRecord));
                        }
                    }
                    BillingManager.this.mBillingClient.queryPurchaseHistoryAsync(BillingClient.SkuType.SUBS, new PurchaseHistoryResponseListener() { // from class: expo.modules.inapppurchases.BillingManager.8.1.1
                        @Override // com.android.billingclient.api.PurchaseHistoryResponseListener
                        public void onPurchaseHistoryResponse(BillingResult billingResult2, List<PurchaseHistoryRecord> list2) {
                            if (billingResult2.getResponseCode() == 0 && list2 != null) {
                                for (PurchaseHistoryRecord purchaseHistoryRecord2 : list2) {
                                    arrayList.add(BillingManager.purchaseHistoryToBundle(purchaseHistoryRecord2));
                                }
                            }
                            RunnableC44288.this.val$promise.resolve(BillingManager.formatResponse(billingResult, arrayList));
                        }
                    });
                }
            });
        }
    }

    public void queryPurchaseHistoryAsync(Promise promise) {
        executeServiceRequest(new RunnableC44288(promise));
    }

    public static Bundle formatResponse(BillingResult billingResult, ArrayList<? extends Parcelable> arrayList) {
        Bundle bundle = new Bundle();
        int responseCode = billingResult.getResponseCode();
        if (responseCode == 0) {
            bundle.putInt("responseCode", 0);
            if (arrayList == null) {
                arrayList = new ArrayList<>();
            }
            bundle.putParcelableArrayList("results", arrayList);
        } else if (responseCode == 1) {
            bundle.putInt("responseCode", 1);
        } else {
            bundle.putInt("responseCode", 2);
            bundle.putInt("errorCode", errorCodeNativeToJS(responseCode));
        }
        return bundle;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Bundle skuToBundle(SkuDetails skuDetails) {
        Bundle bundle = new Bundle();
        String subscriptionPeriod = skuDetails.getType().equals(BillingClient.SkuType.SUBS) ? skuDetails.getSubscriptionPeriod() : INAPP_SUB_PERIOD;
        bundle.putString("description", skuDetails.getDescription());
        bundle.putString("price", skuDetails.getPrice());
        bundle.putLong("priceAmountMicros", skuDetails.getPriceAmountMicros());
        bundle.putString("priceCurrencyCode", skuDetails.getPriceCurrencyCode());
        bundle.putString("productId", skuDetails.getSku());
        bundle.putString("title", skuDetails.getTitle());
        bundle.putInt(SessionDescription.ATTR_TYPE, !skuDetails.getType().equals(BillingClient.SkuType.INAPP));
        bundle.putString("subscriptionPeriod", subscriptionPeriod);
        return bundle;
    }

    public static Bundle purchaseToBundle(Purchase purchase) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("acknowledged", purchase.isAcknowledged());
        bundle.putString("orderId", purchase.getOrderId());
        bundle.putString("productId", purchase.getSkus().get(0));
        bundle.putInt("purchaseState", purchaseStateNativeToJS(purchase.getPurchaseState()));
        bundle.putLong("purchaseTime", purchase.getPurchaseTime());
        bundle.putString("packageName", purchase.getPackageName());
        bundle.putString("purchaseToken", purchase.getPurchaseToken());
        return bundle;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Bundle purchaseHistoryToBundle(PurchaseHistoryRecord purchaseHistoryRecord) {
        Bundle bundle = new Bundle();
        bundle.putString("productId", purchaseHistoryRecord.getSkus().get(0));
        bundle.putLong("purchaseTime", purchaseHistoryRecord.getPurchaseTime());
        bundle.putString("purchaseToken", purchaseHistoryRecord.getPurchaseToken());
        return bundle;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onQueryPurchasesFinished(BillingResult billingResult, List<Purchase> list, Promise promise) {
        if (this.mBillingClient == null || billingResult.getResponseCode() != 0) {
            promise.reject("E_QUERY_FAILED", "Billing client was null or query was unsuccessful");
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (Purchase purchase : list) {
            arrayList.add(purchaseToBundle(purchase));
        }
        this.mPurchases.clear();
        onPurchasesUpdated(billingResult, list);
        promise.resolve(formatResponse(billingResult, arrayList));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: expo.modules.inapppurchases.BillingManager$9 */
    /* loaded from: classes4.dex */
    public class RunnableC44319 implements Runnable {
        final /* synthetic */ SkuDetailsResponseListener val$listener;
        final /* synthetic */ List val$skuList;

        RunnableC44319(List list, SkuDetailsResponseListener skuDetailsResponseListener) {
            this.val$skuList = list;
            this.val$listener = skuDetailsResponseListener;
        }

        @Override // java.lang.Runnable
        public void run() {
            SkuDetailsParams.Builder newBuilder = SkuDetailsParams.newBuilder();
            newBuilder.setSkusList(this.val$skuList).setType(BillingClient.SkuType.INAPP);
            BillingManager.this.mBillingClient.querySkuDetailsAsync(newBuilder.build(), new SkuDetailsResponseListener() { // from class: expo.modules.inapppurchases.BillingManager.9.1
                @Override // com.android.billingclient.api.SkuDetailsResponseListener
                public void onSkuDetailsResponse(BillingResult billingResult, final List<SkuDetails> list) {
                    SkuDetailsParams.Builder newBuilder2 = SkuDetailsParams.newBuilder();
                    newBuilder2.setSkusList(RunnableC44319.this.val$skuList).setType(BillingClient.SkuType.SUBS);
                    BillingManager.this.mBillingClient.querySkuDetailsAsync(newBuilder2.build(), new SkuDetailsResponseListener() { // from class: expo.modules.inapppurchases.BillingManager.9.1.1
                        @Override // com.android.billingclient.api.SkuDetailsResponseListener
                        public void onSkuDetailsResponse(BillingResult billingResult2, List<SkuDetails> list2) {
                            List list3 = list;
                            if (list3 != null && list2 != null) {
                                list3.addAll(list2);
                            }
                            RunnableC44319.this.val$listener.onSkuDetailsResponse(billingResult2, list);
                        }
                    });
                }
            });
        }
    }

    public void querySkuDetailsAsync(List<String> list, SkuDetailsResponseListener skuDetailsResponseListener) {
        executeServiceRequest(new RunnableC44319(list, skuDetailsResponseListener));
    }

    public void queryPurchasableItems(List<String> list, final Promise promise) {
        querySkuDetailsAsync(list, new SkuDetailsResponseListener() { // from class: expo.modules.inapppurchases.BillingManager.10
            @Override // com.android.billingclient.api.SkuDetailsResponseListener
            public void onSkuDetailsResponse(BillingResult billingResult, List<SkuDetails> list2) {
                ArrayList arrayList = new ArrayList();
                if (list2 != null) {
                    for (SkuDetails skuDetails : list2) {
                        BillingManager.this.mSkuDetailsMap.put(skuDetails.getSku(), skuDetails);
                        arrayList.add(BillingManager.skuToBundle(skuDetails));
                    }
                }
                promise.resolve(BillingManager.formatResponse(billingResult, arrayList));
            }
        });
    }

    private void executeServiceRequest(Runnable runnable) {
        if (this.mIsServiceConnected) {
            runnable.run();
        } else {
            startServiceConnection(runnable);
        }
    }

    public boolean areSubscriptionsSupported() {
        return this.mBillingClient.isFeatureSupported(BillingClient.FeatureType.SUBSCRIPTIONS).getResponseCode() == 0;
    }

    public void destroy() {
        BillingClient billingClient = this.mBillingClient;
        if (billingClient == null || !billingClient.isReady()) {
            return;
        }
        this.mBillingClient.endConnection();
        this.mBillingClient = null;
    }
}
