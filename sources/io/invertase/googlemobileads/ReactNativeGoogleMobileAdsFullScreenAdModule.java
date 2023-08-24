package io.invertase.googlemobileads;

import android.app.Activity;
import android.util.SparseArray;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import com.google.android.gms.ads.AdLoadCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.admanager.AdManagerAdRequest;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.onesignal.OneSignalDbContract;
import io.invertase.googlemobileads.common.ReactNativeModule;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ReactNativeGoogleMobileAdsFullScreenAdModule.kt */
@Metadata(m184d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0001\"B\u0017\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\n\u001a\u00020\u0006H&J\u001e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u0011J.\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u00162\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00028\u00000\u0018H&J4\u0010\u0019\u001a\u00020\f2\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00062\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001cH\u0002J&\u0010\u001e\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020\u00112\u0006\u0010 \u001a\u00020!R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006#"}, m183d2 = {"Lio/invertase/googlemobileads/ReactNativeGoogleMobileAdsFullScreenAdModule;", "T", "Lio/invertase/googlemobileads/common/ReactNativeModule;", "reactContext", "Lcom/facebook/react/bridge/ReactApplicationContext;", "moduleName", "", "(Lcom/facebook/react/bridge/ReactApplicationContext;Ljava/lang/String;)V", "adArray", "Landroid/util/SparseArray;", "getAdEventName", "load", "", "requestId", "", "adUnitId", "adRequestOptions", "Lcom/facebook/react/bridge/ReadableMap;", "loadAd", "activity", "Landroid/app/Activity;", "adRequest", "Lcom/google/android/gms/ads/admanager/AdManagerAdRequest;", "adLoadCallback", "Lcom/google/android/gms/ads/AdLoadCallback;", "sendAdEvent", SessionDescription.ATTR_TYPE, "error", "Lcom/facebook/react/bridge/WritableMap;", "data", "show", "showOptions", BaseJavaModule.METHOD_TYPE_PROMISE, "Lcom/facebook/react/bridge/Promise;", "ReactNativeGoogleMobileAdsAdLoadCallback", "react-native-google-mobile-ads_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes5.dex */
public abstract class ReactNativeGoogleMobileAdsFullScreenAdModule<T> extends ReactNativeModule {
    private final SparseArray<T> adArray;

    public abstract String getAdEventName();

    public abstract void loadAd(Activity activity, String str, AdManagerAdRequest adManagerAdRequest, AdLoadCallback<T> adLoadCallback);

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ReactNativeGoogleMobileAdsFullScreenAdModule(ReactApplicationContext reactApplicationContext, String moduleName) {
        super(reactApplicationContext, moduleName);
        Intrinsics.checkNotNullParameter(moduleName, "moduleName");
        this.adArray = new SparseArray<>();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendAdEvent(String str, int r8, String str2, WritableMap writableMap, WritableMap writableMap2) {
        ReactNativeGoogleMobileAdsCommon.sendAdEvent(getAdEventName(), r8, str, str2, writableMap, writableMap2);
    }

    public final void load(int r8, final String adUnitId, ReadableMap adRequestOptions) {
        Intrinsics.checkNotNullParameter(adUnitId, "adUnitId");
        Intrinsics.checkNotNullParameter(adRequestOptions, "adRequestOptions");
        final Activity currentActivity = getCurrentActivity();
        if (currentActivity == null) {
            WritableMap createMap = Arguments.createMap();
            createMap.putString("code", "null-activity");
            createMap.putString(OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE, "Ad attempted to load but the current Activity was null.");
            sendAdEvent("error", r8, adUnitId, createMap, null);
            return;
        }
        final AdManagerAdRequest buildAdRequest = ReactNativeGoogleMobileAdsCommon.buildAdRequest(adRequestOptions);
        final ReactNativeGoogleMobileAdsAdLoadCallback reactNativeGoogleMobileAdsAdLoadCallback = new ReactNativeGoogleMobileAdsAdLoadCallback(this, r8, adUnitId, adRequestOptions);
        currentActivity.runOnUiThread(new Runnable() { // from class: io.invertase.googlemobileads.ReactNativeGoogleMobileAdsFullScreenAdModule$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                ReactNativeGoogleMobileAdsFullScreenAdModule.m1741load$lambda0(ReactNativeGoogleMobileAdsFullScreenAdModule.this, currentActivity, adUnitId, buildAdRequest, reactNativeGoogleMobileAdsAdLoadCallback);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: load$lambda-0  reason: not valid java name */
    public static final void m1741load$lambda0(ReactNativeGoogleMobileAdsFullScreenAdModule this$0, Activity activity, String adUnitId, AdManagerAdRequest adRequest, ReactNativeGoogleMobileAdsAdLoadCallback adLoadCallback) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(adUnitId, "$adUnitId");
        Intrinsics.checkNotNullParameter(adLoadCallback, "$adLoadCallback");
        Intrinsics.checkNotNullExpressionValue(adRequest, "adRequest");
        this$0.loadAd(activity, adUnitId, adRequest, adLoadCallback);
    }

    public final void show(final int r10, final String adUnitId, final ReadableMap showOptions, final Promise promise) {
        Intrinsics.checkNotNullParameter(adUnitId, "adUnitId");
        Intrinsics.checkNotNullParameter(showOptions, "showOptions");
        Intrinsics.checkNotNullParameter(promise, "promise");
        final Activity currentActivity = getCurrentActivity();
        if (currentActivity == null) {
            ReactNativeModule.rejectPromiseWithCodeAndMessage(promise, "null-activity", "Ad attempted to show but the current Activity was null.");
        } else {
            currentActivity.runOnUiThread(new Runnable() { // from class: io.invertase.googlemobileads.ReactNativeGoogleMobileAdsFullScreenAdModule$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    ReactNativeGoogleMobileAdsFullScreenAdModule.m1742show$lambda2(ReactNativeGoogleMobileAdsFullScreenAdModule.this, r10, showOptions, currentActivity, promise, adUnitId);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: show$lambda-2  reason: not valid java name */
    public static final void m1742show$lambda2(final ReactNativeGoogleMobileAdsFullScreenAdModule this$0, final int r4, ReadableMap showOptions, Activity activity, Promise promise, final String adUnitId) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(showOptions, "$showOptions");
        Intrinsics.checkNotNullParameter(promise, "$promise");
        Intrinsics.checkNotNullParameter(adUnitId, "$adUnitId");
        ReactNativeGoogleMobileAdsAdHelper reactNativeGoogleMobileAdsAdHelper = new ReactNativeGoogleMobileAdsAdHelper(this$0.adArray.get(r4));
        reactNativeGoogleMobileAdsAdHelper.setImmersiveMode(showOptions.hasKey("immersiveModeEnabled") ? showOptions.getBoolean("immersiveModeEnabled") : false);
        reactNativeGoogleMobileAdsAdHelper.show(activity, new OnUserEarnedRewardListener() { // from class: io.invertase.googlemobileads.ReactNativeGoogleMobileAdsFullScreenAdModule$$ExternalSyntheticLambda0
            @Override // com.google.android.gms.ads.OnUserEarnedRewardListener
            public final void onUserEarnedReward(RewardItem rewardItem) {
                ReactNativeGoogleMobileAdsFullScreenAdModule.m1743show$lambda2$lambda1(ReactNativeGoogleMobileAdsFullScreenAdModule.this, r4, adUnitId, rewardItem);
            }
        });
        promise.resolve(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: show$lambda-2$lambda-1  reason: not valid java name */
    public static final void m1743show$lambda2$lambda1(ReactNativeGoogleMobileAdsFullScreenAdModule this$0, int r8, String adUnitId, RewardItem rewardItem) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(adUnitId, "$adUnitId");
        Intrinsics.checkNotNullParameter(rewardItem, "rewardItem");
        WritableMap createMap = Arguments.createMap();
        createMap.putString(SessionDescription.ATTR_TYPE, rewardItem.getType());
        createMap.putInt("amount", rewardItem.getAmount());
        this$0.sendAdEvent(ReactNativeGoogleMobileAdsEvent.GOOGLE_MOBILE_ADS_EVENT_REWARDED_EARNED_REWARD, r8, adUnitId, null, createMap);
    }

    /* compiled from: ReactNativeGoogleMobileAdsFullScreenAdModule.kt */
    @Metadata(m184d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0004\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0015\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u000fR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, m183d2 = {"Lio/invertase/googlemobileads/ReactNativeGoogleMobileAdsFullScreenAdModule$ReactNativeGoogleMobileAdsAdLoadCallback;", "Lcom/google/android/gms/ads/AdLoadCallback;", "requestId", "", "adUnitId", "", "adRequestOptions", "Lcom/facebook/react/bridge/ReadableMap;", "(Lio/invertase/googlemobileads/ReactNativeGoogleMobileAdsFullScreenAdModule;ILjava/lang/String;Lcom/facebook/react/bridge/ReadableMap;)V", "onAdFailedToLoad", "", "loadAdError", "Lcom/google/android/gms/ads/LoadAdError;", "onAdLoaded", "ad", "(Ljava/lang/Object;)V", "react-native-google-mobile-ads_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes5.dex */
    public final class ReactNativeGoogleMobileAdsAdLoadCallback extends AdLoadCallback<T> {
        private final ReadableMap adRequestOptions;
        private final String adUnitId;
        private final int requestId;
        final /* synthetic */ ReactNativeGoogleMobileAdsFullScreenAdModule<T> this$0;

        public ReactNativeGoogleMobileAdsAdLoadCallback(ReactNativeGoogleMobileAdsFullScreenAdModule this$0, int r3, String adUnitId, ReadableMap adRequestOptions) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(adUnitId, "adUnitId");
            Intrinsics.checkNotNullParameter(adRequestOptions, "adRequestOptions");
            this.this$0 = this$0;
            this.requestId = r3;
            this.adUnitId = adUnitId;
            this.adRequestOptions = adRequestOptions;
        }

        /* JADX WARN: Removed duplicated region for block: B:23:0x006a A[Catch: Exception -> 0x0096, TryCatch #0 {Exception -> 0x0096, blocks: (B:2:0x0000, B:4:0x000c, B:21:0x0066, B:23:0x006a, B:24:0x0074, B:8:0x0014, B:11:0x003b, B:15:0x004c, B:19:0x0058, B:18:0x0055, B:14:0x0049), top: B:29:0x0000 }] */
        @Override // com.google.android.gms.ads.AdLoadCallback
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void onAdLoaded(T r12) {
            /*
                r11 = this;
                io.invertase.googlemobileads.ReactNativeGoogleMobileAdsAdHelper r0 = new io.invertase.googlemobileads.ReactNativeGoogleMobileAdsAdHelper     // Catch: java.lang.Exception -> L96
                r0.<init>(r12)     // Catch: java.lang.Exception -> L96
                java.lang.String r1 = "loaded"
                r2 = 0
                boolean r3 = r12 instanceof com.google.android.gms.ads.rewarded.RewardedAd     // Catch: java.lang.Exception -> L96
                if (r3 != 0) goto L14
                boolean r3 = r12 instanceof com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAd     // Catch: java.lang.Exception -> L96
                if (r3 == 0) goto L11
                goto L14
            L11:
                r6 = r1
                r10 = r2
                goto L66
            L14:
                java.lang.String r1 = "rewarded_loaded"
                com.google.android.gms.ads.rewarded.RewardItem r2 = r0.getRewardItem()     // Catch: java.lang.Exception -> L96
                com.facebook.react.bridge.WritableMap r3 = com.facebook.react.bridge.Arguments.createMap()     // Catch: java.lang.Exception -> L96
                java.lang.String r4 = "type"
                java.lang.String r5 = r2.getType()     // Catch: java.lang.Exception -> L96
                r3.putString(r4, r5)     // Catch: java.lang.Exception -> L96
                java.lang.String r4 = "amount"
                int r2 = r2.getAmount()     // Catch: java.lang.Exception -> L96
                r3.putInt(r4, r2)     // Catch: java.lang.Exception -> L96
                com.facebook.react.bridge.ReadableMap r2 = r11.adRequestOptions     // Catch: java.lang.Exception -> L96
                java.lang.String r4 = "serverSideVerificationOptions"
                com.facebook.react.bridge.ReadableMap r2 = r2.getMap(r4)     // Catch: java.lang.Exception -> L96
                if (r2 != 0) goto L3b
                goto L64
            L3b:
                com.google.android.gms.ads.rewarded.ServerSideVerificationOptions$Builder r4 = new com.google.android.gms.ads.rewarded.ServerSideVerificationOptions$Builder     // Catch: java.lang.Exception -> L96
                r4.<init>()     // Catch: java.lang.Exception -> L96
                java.lang.String r5 = "userId"
                java.lang.String r5 = r2.getString(r5)     // Catch: java.lang.Exception -> L96
                if (r5 != 0) goto L49
                goto L4c
            L49:
                r4.setUserId(r5)     // Catch: java.lang.Exception -> L96
            L4c:
                java.lang.String r5 = "customData"
                java.lang.String r2 = r2.getString(r5)     // Catch: java.lang.Exception -> L96
                if (r2 != 0) goto L55
                goto L58
            L55:
                r4.setCustomData(r2)     // Catch: java.lang.Exception -> L96
            L58:
                com.google.android.gms.ads.rewarded.ServerSideVerificationOptions r2 = r4.build()     // Catch: java.lang.Exception -> L96
                java.lang.String r4 = "options.build()"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r4)     // Catch: java.lang.Exception -> L96
                r0.setServerSideVerificationOptions(r2)     // Catch: java.lang.Exception -> L96
            L64:
                r6 = r1
                r10 = r3
            L66:
                boolean r1 = r12 instanceof com.google.android.gms.ads.admanager.AdManagerInterstitialAd     // Catch: java.lang.Exception -> L96
                if (r1 == 0) goto L74
                io.invertase.googlemobileads.ReactNativeGoogleMobileAdsFullScreenAdModule<T> r1 = r11.this$0     // Catch: java.lang.Exception -> L96
                io.invertase.googlemobileads.ReactNativeGoogleMobileAdsFullScreenAdModule$ReactNativeGoogleMobileAdsAdLoadCallback$$ExternalSyntheticLambda0 r2 = new io.invertase.googlemobileads.ReactNativeGoogleMobileAdsFullScreenAdModule$ReactNativeGoogleMobileAdsAdLoadCallback$$ExternalSyntheticLambda0     // Catch: java.lang.Exception -> L96
                r2.<init>()     // Catch: java.lang.Exception -> L96
                r0.setAppEventListener(r2)     // Catch: java.lang.Exception -> L96
            L74:
                io.invertase.googlemobileads.ReactNativeGoogleMobileAdsFullScreenAdModule$ReactNativeGoogleMobileAdsAdLoadCallback$onAdLoaded$fullScreenContentCallback$1 r1 = new io.invertase.googlemobileads.ReactNativeGoogleMobileAdsFullScreenAdModule$ReactNativeGoogleMobileAdsAdLoadCallback$onAdLoaded$fullScreenContentCallback$1     // Catch: java.lang.Exception -> L96
                io.invertase.googlemobileads.ReactNativeGoogleMobileAdsFullScreenAdModule<T> r2 = r11.this$0     // Catch: java.lang.Exception -> L96
                r1.<init>()     // Catch: java.lang.Exception -> L96
                com.google.android.gms.ads.FullScreenContentCallback r1 = (com.google.android.gms.ads.FullScreenContentCallback) r1     // Catch: java.lang.Exception -> L96
                r0.setFullScreenContentCallback(r1)     // Catch: java.lang.Exception -> L96
                io.invertase.googlemobileads.ReactNativeGoogleMobileAdsFullScreenAdModule<T> r0 = r11.this$0     // Catch: java.lang.Exception -> L96
                android.util.SparseArray r0 = io.invertase.googlemobileads.ReactNativeGoogleMobileAdsFullScreenAdModule.access$getAdArray$p(r0)     // Catch: java.lang.Exception -> L96
                int r1 = r11.requestId     // Catch: java.lang.Exception -> L96
                r0.put(r1, r12)     // Catch: java.lang.Exception -> L96
                io.invertase.googlemobileads.ReactNativeGoogleMobileAdsFullScreenAdModule<T> r5 = r11.this$0     // Catch: java.lang.Exception -> L96
                int r7 = r11.requestId     // Catch: java.lang.Exception -> L96
                java.lang.String r8 = r11.adUnitId     // Catch: java.lang.Exception -> L96
                r9 = 0
                io.invertase.googlemobileads.ReactNativeGoogleMobileAdsFullScreenAdModule.access$sendAdEvent(r5, r6, r7, r8, r9, r10)     // Catch: java.lang.Exception -> L96
                goto Lc4
            L96:
                r12 = move-exception
                java.lang.String r0 = "RNGoogleMobileAds"
                java.lang.String r1 = "Unknown error on load"
                android.util.Log.w(r0, r1)
                r1 = r12
                java.lang.Throwable r1 = (java.lang.Throwable) r1
                android.util.Log.w(r0, r1)
                com.facebook.react.bridge.WritableMap r6 = com.facebook.react.bridge.Arguments.createMap()
                java.lang.String r0 = "code"
                java.lang.String r1 = "internal"
                r6.putString(r0, r1)
                java.lang.String r12 = r12.getMessage()
                java.lang.String r0 = "message"
                r6.putString(r0, r12)
                io.invertase.googlemobileads.ReactNativeGoogleMobileAdsFullScreenAdModule<T> r2 = r11.this$0
                int r4 = r11.requestId
                java.lang.String r5 = r11.adUnitId
                r7 = 0
                java.lang.String r3 = "error"
                io.invertase.googlemobileads.ReactNativeGoogleMobileAdsFullScreenAdModule.access$sendAdEvent(r2, r3, r4, r5, r6, r7)
            Lc4:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.invertase.googlemobileads.ReactNativeGoogleMobileAdsFullScreenAdModule.ReactNativeGoogleMobileAdsAdLoadCallback.onAdLoaded(java.lang.Object):void");
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: onAdLoaded$lambda-3  reason: not valid java name */
        public static final void m1744onAdLoaded$lambda3(ReactNativeGoogleMobileAdsFullScreenAdModule this$0, ReactNativeGoogleMobileAdsAdLoadCallback this$1, String name, String eventData) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(this$1, "this$1");
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(eventData, "eventData");
            WritableMap createMap = Arguments.createMap();
            createMap.putString("name", name);
            createMap.putString("data", eventData);
            this$0.sendAdEvent(ReactNativeGoogleMobileAdsEvent.GOOGLE_MOBILE_ADS_EVENT_APP_EVENT, this$1.requestId, this$1.adUnitId, null, createMap);
        }

        @Override // com.google.android.gms.ads.AdLoadCallback
        public void onAdFailedToLoad(LoadAdError loadAdError) {
            Intrinsics.checkNotNullParameter(loadAdError, "loadAdError");
            WritableMap createMap = Arguments.createMap();
            String[] codeAndMessageFromAdError = ReactNativeGoogleMobileAdsCommon.getCodeAndMessageFromAdError(loadAdError);
            createMap.putString("code", codeAndMessageFromAdError[0]);
            createMap.putString(OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE, codeAndMessageFromAdError[1]);
            this.this$0.sendAdEvent("error", this.requestId, this.adUnitId, createMap, null);
        }
    }
}
