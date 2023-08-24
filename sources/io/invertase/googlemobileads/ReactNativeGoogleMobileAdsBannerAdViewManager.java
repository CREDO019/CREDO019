package io.invertase.googlemobileads;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.views.view.ReactViewGroup;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.BaseAdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.admanager.AdManagerAdView;
import com.google.android.gms.ads.admanager.AppEventListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* loaded from: classes5.dex */
public class ReactNativeGoogleMobileAdsBannerAdViewManager extends SimpleViewManager<ReactViewGroup> {
    private static final String REACT_CLASS = "RNGoogleMobileAdsBannerView";
    private boolean isFluid;
    private Boolean manualImpressionsEnabled;
    private boolean propsChanged;
    private AdRequest request;
    private List<AdSize> sizes;
    private String unitId;
    private final String EVENT_AD_LOADED = "onAdLoaded";
    private final String EVENT_AD_FAILED_TO_LOAD = "onAdFailedToLoad";
    private final String EVENT_AD_OPENED = "onAdOpened";
    private final String EVENT_AD_CLOSED = "onAdClosed";
    private final String EVENT_SIZE_CHANGE = "onSizeChange";
    private final String EVENT_APP_EVENT = "onAppEvent";
    private final int COMMAND_ID_RECORD_MANUAL_IMPRESSION = 1;

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    @Nonnull
    public String getName() {
        return REACT_CLASS;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    @Nonnull
    public ReactViewGroup createViewInstance(@Nonnull ThemedReactContext themedReactContext) {
        return new ReactViewGroup(themedReactContext);
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        MapBuilder.Builder builder = MapBuilder.builder();
        builder.put("onNativeEvent", MapBuilder.m1261of("registrationName", "onNativeEvent"));
        return builder.build();
    }

    @Override // com.facebook.react.uimanager.ViewManager
    @Nullable
    public Map<String, Integer> getCommandsMap() {
        return MapBuilder.m1261of("recordManualImpression", 1);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void receiveCommand(ReactViewGroup reactViewGroup, String str, @Nullable ReadableArray readableArray) {
        super.receiveCommand((ReactNativeGoogleMobileAdsBannerAdViewManager) reactViewGroup, str, readableArray);
        if (Integer.parseInt(str) == 1) {
            BaseAdView adView = getAdView(reactViewGroup);
            if (adView instanceof AdManagerAdView) {
                ((AdManagerAdView) adView).recordManualImpression();
            }
        }
    }

    @ReactProp(name = "unitId")
    public void setUnitId(ReactViewGroup reactViewGroup, String str) {
        this.unitId = str;
        this.propsChanged = true;
    }

    @ReactProp(name = "request")
    public void setRequest(ReactViewGroup reactViewGroup, ReadableMap readableMap) {
        this.request = ReactNativeGoogleMobileAdsCommon.buildAdRequest(readableMap);
        this.propsChanged = true;
    }

    @ReactProp(name = "sizes")
    public void setSizes(ReactViewGroup reactViewGroup, ReadableArray readableArray) {
        ArrayList arrayList = new ArrayList();
        Iterator<Object> it = readableArray.toArrayList().iterator();
        while (it.hasNext()) {
            Object next = it.next();
            if (next instanceof String) {
                arrayList.add(ReactNativeGoogleMobileAdsCommon.getAdSize((String) next, reactViewGroup));
            }
        }
        if (arrayList.size() > 0) {
            AdSize adSize = (AdSize) arrayList.get(0);
            WritableMap createMap = Arguments.createMap();
            createMap.putDouble("width", adSize.getWidth());
            createMap.putDouble("height", adSize.getHeight());
            sendEvent(reactViewGroup, "onSizeChange", createMap);
        }
        this.sizes = arrayList;
        this.propsChanged = true;
    }

    @ReactProp(name = "manualImpressionsEnabled")
    public void setManualImpressionsEnabled(ReactViewGroup reactViewGroup, boolean z) {
        this.manualImpressionsEnabled = Boolean.valueOf(z);
        this.propsChanged = true;
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    public void onAfterUpdateTransaction(ReactViewGroup reactViewGroup) {
        super.onAfterUpdateTransaction((ReactNativeGoogleMobileAdsBannerAdViewManager) reactViewGroup);
        if (this.propsChanged) {
            requestAd(reactViewGroup);
        }
        this.propsChanged = false;
    }

    private BaseAdView initAdView(final ReactViewGroup reactViewGroup) {
        final BaseAdView adView;
        BaseAdView adView2 = getAdView(reactViewGroup);
        if (adView2 != null) {
            adView2.destroy();
            reactViewGroup.removeView(adView2);
        }
        if (ReactNativeGoogleMobileAdsCommon.isAdManagerUnit(this.unitId)) {
            adView = new AdManagerAdView(reactViewGroup.getContext());
        } else {
            adView = new AdView(reactViewGroup.getContext());
        }
        adView.setDescendantFocusability(393216);
        adView.setAdListener(new AdListener() { // from class: io.invertase.googlemobileads.ReactNativeGoogleMobileAdsBannerAdViewManager.1
            @Override // com.google.android.gms.ads.AdListener
            public void onAdLoaded() {
                int heightInPixels;
                int r0;
                int r3;
                AdSize adSize = adView.getAdSize();
                int r2 = 0;
                if (ReactNativeGoogleMobileAdsBannerAdViewManager.this.isFluid) {
                    r0 = reactViewGroup.getWidth();
                    heightInPixels = reactViewGroup.getHeight();
                    r3 = 0;
                } else {
                    r2 = adView.getLeft();
                    int top = adView.getTop();
                    int widthInPixels = adSize.getWidthInPixels(reactViewGroup.getContext());
                    heightInPixels = adSize.getHeightInPixels(reactViewGroup.getContext());
                    r0 = widthInPixels;
                    r3 = top;
                }
                adView.measure(r0, heightInPixels);
                adView.layout(r2, r3, r2 + r0, r3 + heightInPixels);
                WritableMap createMap = Arguments.createMap();
                createMap.putDouble("width", PixelUtil.toDIPFromPixel(r0));
                createMap.putDouble("height", PixelUtil.toDIPFromPixel(heightInPixels));
                ReactNativeGoogleMobileAdsBannerAdViewManager.this.sendEvent(reactViewGroup, "onAdLoaded", createMap);
            }

            @Override // com.google.android.gms.ads.AdListener
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                ReactNativeGoogleMobileAdsBannerAdViewManager.this.sendEvent(reactViewGroup, "onAdFailedToLoad", ReactNativeGoogleMobileAdsCommon.errorCodeToMap(loadAdError.getCode()));
            }

            @Override // com.google.android.gms.ads.AdListener
            public void onAdOpened() {
                ReactNativeGoogleMobileAdsBannerAdViewManager.this.sendEvent(reactViewGroup, "onAdOpened", null);
            }

            @Override // com.google.android.gms.ads.AdListener
            public void onAdClosed() {
                ReactNativeGoogleMobileAdsBannerAdViewManager.this.sendEvent(reactViewGroup, "onAdClosed", null);
            }
        });
        if (adView instanceof AdManagerAdView) {
            ((AdManagerAdView) adView).setAppEventListener(new AppEventListener() { // from class: io.invertase.googlemobileads.ReactNativeGoogleMobileAdsBannerAdViewManager.2
                @Override // com.google.android.gms.ads.admanager.AppEventListener
                public void onAppEvent(String str, @Nullable String str2) {
                    WritableMap createMap = Arguments.createMap();
                    createMap.putString("name", str);
                    createMap.putString("data", str2);
                    ReactNativeGoogleMobileAdsBannerAdViewManager.this.sendEvent(reactViewGroup, "onAppEvent", createMap);
                }
            });
        }
        reactViewGroup.addView(adView);
        return adView;
    }

    @Nullable
    private BaseAdView getAdView(ReactViewGroup reactViewGroup) {
        return (BaseAdView) reactViewGroup.getChildAt(0);
    }

    private void requestAd(ReactViewGroup reactViewGroup) {
        if (this.sizes == null || this.unitId == null || this.request == null || this.manualImpressionsEnabled == null) {
            return;
        }
        BaseAdView initAdView = initAdView(reactViewGroup);
        initAdView.setAdUnitId(this.unitId);
        this.isFluid = false;
        if (initAdView instanceof AdManagerAdView) {
            if (this.sizes.contains(AdSize.FLUID)) {
                this.isFluid = true;
                ((AdManagerAdView) initAdView).setAdSizes(AdSize.FLUID);
            } else {
                ((AdManagerAdView) initAdView).setAdSizes((AdSize[]) this.sizes.toArray(new AdSize[0]));
            }
            if (this.manualImpressionsEnabled.booleanValue()) {
                ((AdManagerAdView) initAdView).setManualImpressionsEnabled(true);
            }
        } else {
            initAdView.setAdSize(this.sizes.get(0));
        }
        initAdView.loadAd(this.request);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendEvent(ReactViewGroup reactViewGroup, String str, WritableMap writableMap) {
        WritableMap createMap = Arguments.createMap();
        createMap.putString(SessionDescription.ATTR_TYPE, str);
        if (writableMap != null) {
            createMap.merge(writableMap);
        }
        ((RCTEventEmitter) ((ThemedReactContext) reactViewGroup.getContext()).getJSModule(RCTEventEmitter.class)).receiveEvent(reactViewGroup.getId(), "onNativeEvent", createMap);
    }
}
