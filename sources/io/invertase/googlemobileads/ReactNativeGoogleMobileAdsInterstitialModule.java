package io.invertase.googlemobileads;

import android.app.Activity;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.google.android.gms.ads.AdLoadCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.admanager.AdManagerAdRequest;
import com.google.android.gms.ads.admanager.AdManagerInterstitialAd;
import com.google.android.gms.ads.admanager.AdManagerInterstitialAdLoadCallback;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ReactNativeGoogleMobileAdsInterstitialModule.kt */
@Metadata(m184d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u001a2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001aB\u000f\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0007H\u0016J \u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000eH\u0007J(\u0010\u000f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0012H\u0007J.\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u00172\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00020\u0019H\u0016¨\u0006\u001b"}, m183d2 = {"Lio/invertase/googlemobileads/ReactNativeGoogleMobileAdsInterstitialModule;", "Lio/invertase/googlemobileads/ReactNativeGoogleMobileAdsFullScreenAdModule;", "Lcom/google/android/gms/ads/admanager/AdManagerInterstitialAd;", "reactContext", "Lcom/facebook/react/bridge/ReactApplicationContext;", "(Lcom/facebook/react/bridge/ReactApplicationContext;)V", "getAdEventName", "", "interstitialLoad", "", "requestId", "", "adUnitId", "adRequestOptions", "Lcom/facebook/react/bridge/ReadableMap;", "interstitialShow", "showOptions", BaseJavaModule.METHOD_TYPE_PROMISE, "Lcom/facebook/react/bridge/Promise;", "loadAd", "activity", "Landroid/app/Activity;", "adRequest", "Lcom/google/android/gms/ads/admanager/AdManagerAdRequest;", "adLoadCallback", "Lcom/google/android/gms/ads/AdLoadCallback;", "Companion", "react-native-google-mobile-ads_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes5.dex */
public final class ReactNativeGoogleMobileAdsInterstitialModule extends ReactNativeGoogleMobileAdsFullScreenAdModule<AdManagerInterstitialAd> {
    public static final Companion Companion = new Companion(null);
    public static final String NAME = "RNGoogleMobileAdsInterstitialModule";

    @Override // io.invertase.googlemobileads.ReactNativeGoogleMobileAdsFullScreenAdModule
    public String getAdEventName() {
        return ReactNativeGoogleMobileAdsEvent.GOOGLE_MOBILE_ADS_EVENT_INTERSTITIAL;
    }

    public ReactNativeGoogleMobileAdsInterstitialModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext, NAME);
    }

    @ReactMethod
    public final void interstitialLoad(int r2, String adUnitId, ReadableMap adRequestOptions) {
        Intrinsics.checkNotNullParameter(adUnitId, "adUnitId");
        Intrinsics.checkNotNullParameter(adRequestOptions, "adRequestOptions");
        load(r2, adUnitId, adRequestOptions);
    }

    @ReactMethod
    public final void interstitialShow(int r2, String adUnitId, ReadableMap showOptions, Promise promise) {
        Intrinsics.checkNotNullParameter(adUnitId, "adUnitId");
        Intrinsics.checkNotNullParameter(showOptions, "showOptions");
        Intrinsics.checkNotNullParameter(promise, "promise");
        show(r2, adUnitId, showOptions, promise);
    }

    @Override // io.invertase.googlemobileads.ReactNativeGoogleMobileAdsFullScreenAdModule
    public void loadAd(Activity activity, String adUnitId, AdManagerAdRequest adRequest, final AdLoadCallback<AdManagerInterstitialAd> adLoadCallback) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(adUnitId, "adUnitId");
        Intrinsics.checkNotNullParameter(adRequest, "adRequest");
        Intrinsics.checkNotNullParameter(adLoadCallback, "adLoadCallback");
        AdManagerInterstitialAd.load(activity, adUnitId, adRequest, new AdManagerInterstitialAdLoadCallback() { // from class: io.invertase.googlemobileads.ReactNativeGoogleMobileAdsInterstitialModule$loadAd$1
            @Override // com.google.android.gms.ads.AdLoadCallback
            public void onAdLoaded(AdManagerInterstitialAd ad) {
                Intrinsics.checkNotNullParameter(ad, "ad");
                adLoadCallback.onAdLoaded(ad);
            }

            @Override // com.google.android.gms.ads.AdLoadCallback
            public void onAdFailedToLoad(LoadAdError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                adLoadCallback.onAdFailedToLoad(error);
            }
        });
    }

    /* compiled from: ReactNativeGoogleMobileAdsInterstitialModule.kt */
    @Metadata(m184d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, m183d2 = {"Lio/invertase/googlemobileads/ReactNativeGoogleMobileAdsInterstitialModule$Companion;", "", "()V", "NAME", "", "react-native-google-mobile-ads_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes5.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
