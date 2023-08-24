package io.invertase.googlemobileads;

import android.app.Activity;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.admanager.AdManagerInterstitialAd;
import com.google.android.gms.ads.admanager.AppEventListener;
import com.google.android.gms.ads.appopen.AppOpenAd;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.ServerSideVerificationOptions;
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAd;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ReactNativeGoogleMobileAdsAdHelper.kt */
@Metadata(m184d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00028\u0000¢\u0006\u0002\u0010\u0004J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u0016J\u0018\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bR\u0010\u0010\u0003\u001a\u00028\u0000X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0005R\u0011\u0010\u0006\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u001c"}, m183d2 = {"Lio/invertase/googlemobileads/ReactNativeGoogleMobileAdsAdHelper;", "T", "", "ad", "(Ljava/lang/Object;)V", "Ljava/lang/Object;", "rewardItem", "Lcom/google/android/gms/ads/rewarded/RewardItem;", "getRewardItem", "()Lcom/google/android/gms/ads/rewarded/RewardItem;", "setAppEventListener", "", "appEventListener", "Lcom/google/android/gms/ads/admanager/AppEventListener;", "setFullScreenContentCallback", "fullScreenContentCallback", "Lcom/google/android/gms/ads/FullScreenContentCallback;", "setImmersiveMode", "enabled", "", "setServerSideVerificationOptions", "serverSideVerificationOptions", "Lcom/google/android/gms/ads/rewarded/ServerSideVerificationOptions;", "show", "activity", "Landroid/app/Activity;", "onUserEarnedRewardListener", "Lcom/google/android/gms/ads/OnUserEarnedRewardListener;", "react-native-google-mobile-ads_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes5.dex */
public final class ReactNativeGoogleMobileAdsAdHelper<T> {

    /* renamed from: ad */
    private final T f1485ad;

    public ReactNativeGoogleMobileAdsAdHelper(T t) {
        this.f1485ad = t;
    }

    public final void show(Activity activity, OnUserEarnedRewardListener onUserEarnedRewardListener) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        T t = this.f1485ad;
        if (t instanceof AppOpenAd) {
            ((AppOpenAd) t).show(activity);
        } else if (t instanceof InterstitialAd) {
            ((InterstitialAd) t).show(activity);
        } else if (t instanceof RewardedAd) {
            if (onUserEarnedRewardListener == null) {
                return;
            }
            ((RewardedAd) t).show(activity, onUserEarnedRewardListener);
        } else if (!(t instanceof RewardedInterstitialAd) || onUserEarnedRewardListener == null) {
        } else {
            ((RewardedInterstitialAd) t).show(activity, onUserEarnedRewardListener);
        }
    }

    public final void setFullScreenContentCallback(FullScreenContentCallback fullScreenContentCallback) {
        Intrinsics.checkNotNullParameter(fullScreenContentCallback, "fullScreenContentCallback");
        T t = this.f1485ad;
        if (t instanceof AppOpenAd) {
            ((AppOpenAd) t).setFullScreenContentCallback(fullScreenContentCallback);
        } else if (t instanceof InterstitialAd) {
            ((InterstitialAd) t).setFullScreenContentCallback(fullScreenContentCallback);
        } else if (t instanceof RewardedAd) {
            ((RewardedAd) t).setFullScreenContentCallback(fullScreenContentCallback);
        } else if (t instanceof RewardedInterstitialAd) {
            ((RewardedInterstitialAd) t).setFullScreenContentCallback(fullScreenContentCallback);
        }
    }

    public final void setAppEventListener(AppEventListener appEventListener) {
        Intrinsics.checkNotNullParameter(appEventListener, "appEventListener");
        T t = this.f1485ad;
        if (t instanceof AdManagerInterstitialAd) {
            ((AdManagerInterstitialAd) t).setAppEventListener(appEventListener);
        }
    }

    public final void setImmersiveMode(boolean z) {
        T t = this.f1485ad;
        if (t instanceof AppOpenAd) {
            ((AppOpenAd) t).setImmersiveMode(z);
        } else if (t instanceof InterstitialAd) {
            ((InterstitialAd) t).setImmersiveMode(z);
        } else if (t instanceof RewardedAd) {
            ((RewardedAd) t).setImmersiveMode(z);
        } else if (t instanceof RewardedInterstitialAd) {
            ((RewardedInterstitialAd) t).setImmersiveMode(z);
        }
    }

    public final void setServerSideVerificationOptions(ServerSideVerificationOptions serverSideVerificationOptions) {
        Intrinsics.checkNotNullParameter(serverSideVerificationOptions, "serverSideVerificationOptions");
        T t = this.f1485ad;
        if (t instanceof RewardedAd) {
            ((RewardedAd) t).setServerSideVerificationOptions(serverSideVerificationOptions);
        } else if (t instanceof RewardedInterstitialAd) {
            ((RewardedInterstitialAd) t).setServerSideVerificationOptions(serverSideVerificationOptions);
        }
    }

    public final RewardItem getRewardItem() {
        T t = this.f1485ad;
        if (t instanceof RewardedAd) {
            RewardItem rewardItem = ((RewardedAd) t).getRewardItem();
            Intrinsics.checkNotNullExpressionValue(rewardItem, "ad.rewardItem");
            return rewardItem;
        } else if (t instanceof RewardedInterstitialAd) {
            RewardItem rewardItem2 = ((RewardedInterstitialAd) t).getRewardItem();
            Intrinsics.checkNotNullExpressionValue(rewardItem2, "ad.rewardItem");
            return rewardItem2;
        } else {
            throw new IllegalStateException("Ad type not supported");
        }
    }
}
