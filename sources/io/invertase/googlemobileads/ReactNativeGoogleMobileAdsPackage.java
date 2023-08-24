package io.invertase.googlemobileads;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Nonnull;

/* loaded from: classes5.dex */
public class ReactNativeGoogleMobileAdsPackage implements ReactPackage {
    @Override // com.facebook.react.ReactPackage
    public List<NativeModule> createNativeModules(ReactApplicationContext reactApplicationContext) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new ReactNativeAppModule(reactApplicationContext));
        arrayList.add(new ReactNativeGoogleMobileAdsModule(reactApplicationContext));
        arrayList.add(new ReactNativeGoogleMobileAdsConsentModule(reactApplicationContext));
        arrayList.add(new ReactNativeGoogleMobileAdsAppOpenModule(reactApplicationContext));
        arrayList.add(new ReactNativeGoogleMobileAdsInterstitialModule(reactApplicationContext));
        arrayList.add(new ReactNativeGoogleMobileAdsRewardedModule(reactApplicationContext));
        arrayList.add(new ReactNativeGoogleMobileAdsRewardedInterstitialModule(reactApplicationContext));
        return arrayList;
    }

    @Override // com.facebook.react.ReactPackage
    @Nonnull
    public List<ViewManager> createViewManagers(@Nonnull ReactApplicationContext reactApplicationContext) {
        return Arrays.asList(new ReactNativeGoogleMobileAdsBannerAdViewManager());
    }
}
