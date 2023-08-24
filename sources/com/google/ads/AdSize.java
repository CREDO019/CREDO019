package com.google.ads;

import ai.api.util.VoiceActivityDetector;
import android.content.Context;
import androidx.recyclerview.widget.ItemTouchHelper;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
@Deprecated
/* loaded from: classes2.dex */
public final class AdSize {
    public static final int AUTO_HEIGHT = -2;
    public static final int FULL_WIDTH = -1;
    public static final int LANDSCAPE_AD_HEIGHT = 32;
    public static final int LARGE_AD_HEIGHT = 90;
    public static final int PORTRAIT_AD_HEIGHT = 50;
    private final com.google.android.gms.ads.AdSize zza;
    public static final AdSize SMART_BANNER = new AdSize(-1, -2, "mb");
    public static final AdSize BANNER = new AdSize(VoiceActivityDetector.FRAME_SIZE_IN_BYTES, 50, "mb");
    public static final AdSize IAB_MRECT = new AdSize(300, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, "as");
    public static final AdSize IAB_BANNER = new AdSize(468, 60, "as");
    public static final AdSize IAB_LEADERBOARD = new AdSize(728, 90, "as");
    public static final AdSize IAB_WIDE_SKYSCRAPER = new AdSize(160, 600, "as");

    public AdSize(int r2, int r3) {
        this(new com.google.android.gms.ads.AdSize(r2, r3));
    }

    public AdSize(com.google.android.gms.ads.AdSize adSize) {
        this.zza = adSize;
    }

    public boolean equals(Object obj) {
        if (obj instanceof AdSize) {
            return this.zza.equals(((AdSize) obj).zza);
        }
        return false;
    }

    public AdSize findBestSize(AdSize... adSizeArr) {
        int width;
        int height;
        AdSize adSize = null;
        if (adSizeArr == null) {
            return null;
        }
        int width2 = getWidth();
        int height2 = getHeight();
        float f = 0.0f;
        for (AdSize adSize2 : adSizeArr) {
            if (isSizeAppropriate(adSize2.getWidth(), adSize2.getHeight())) {
                float f2 = (width * height) / (width2 * height2);
                if (f2 > 1.0f) {
                    f2 = 1.0f / f2;
                }
                if (f2 > f) {
                    adSize = adSize2;
                    f = f2;
                }
            }
        }
        return adSize;
    }

    public int getHeight() {
        return this.zza.getHeight();
    }

    public int getHeightInPixels(Context context) {
        return this.zza.getHeightInPixels(context);
    }

    public int getWidth() {
        return this.zza.getWidth();
    }

    public int getWidthInPixels(Context context) {
        return this.zza.getWidthInPixels(context);
    }

    public int hashCode() {
        return this.zza.hashCode();
    }

    public boolean isAutoHeight() {
        return this.zza.isAutoHeight();
    }

    public boolean isCustomAdSize() {
        return false;
    }

    public boolean isFullWidth() {
        return this.zza.isFullWidth();
    }

    public boolean isSizeAppropriate(int r5, int r6) {
        int width = getWidth();
        int height = getHeight();
        float f = r5;
        float f2 = width;
        if (f > f2 * 1.25f || f < f2 * 0.8f) {
            return false;
        }
        float f3 = r6;
        float f4 = height;
        return f3 <= 1.25f * f4 && f3 >= f4 * 0.8f;
    }

    public String toString() {
        return this.zza.toString();
    }

    private AdSize(int r1, int r2, String str) {
        this(new com.google.android.gms.ads.AdSize(r1, r2));
    }
}
