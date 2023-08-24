package com.google.android.gms.ads.nativead;

import android.view.View;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.nativead.NativeAd;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public interface NativeCustomFormatAd {
    public static final String ASSET_NAME_VIDEO = "_videoMediaView";

    /* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
    /* loaded from: classes2.dex */
    public interface DisplayOpenMeasurement {
        void setView(View view);

        boolean start();
    }

    /* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
    /* loaded from: classes2.dex */
    public interface OnCustomClickListener {
        void onCustomClick(NativeCustomFormatAd nativeCustomFormatAd, String str);
    }

    /* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
    /* loaded from: classes2.dex */
    public interface OnCustomFormatAdLoadedListener {
        void onCustomFormatAdLoaded(NativeCustomFormatAd nativeCustomFormatAd);
    }

    void destroy();

    List<String> getAvailableAssetNames();

    String getCustomFormatId();

    DisplayOpenMeasurement getDisplayOpenMeasurement();

    NativeAd.Image getImage(String str);

    CharSequence getText(String str);

    VideoController getVideoController();

    MediaView getVideoMediaView();

    void performClick(String str);

    void recordImpression();
}
