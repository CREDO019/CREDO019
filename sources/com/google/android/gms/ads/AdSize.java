package com.google.android.gms.ads;

import ai.api.util.VoiceActivityDetector;
import android.content.Context;
import android.os.Parcelable;
import android.util.DisplayMetrics;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.facebook.hermes.intl.Constants;
import com.google.android.gms.ads.internal.client.zzaw;
import com.google.android.gms.ads.internal.client.zzq;
import com.google.android.gms.internal.ads.zzcgg;
import com.google.android.gms.internal.ads.zzcgn;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class AdSize {
    public static final int AUTO_HEIGHT = -2;
    public static final int FULL_WIDTH = -1;
    private final int zzb;
    private final int zzc;
    private final String zzd;
    private boolean zze;
    private boolean zzf;
    private int zzg;
    private boolean zzh;
    private int zzi;
    public static final AdSize BANNER = new AdSize(VoiceActivityDetector.FRAME_SIZE_IN_BYTES, 50, "320x50_mb");
    public static final AdSize FULL_BANNER = new AdSize(468, 60, "468x60_as");
    public static final AdSize LARGE_BANNER = new AdSize(VoiceActivityDetector.FRAME_SIZE_IN_BYTES, 100, "320x100_as");
    public static final AdSize LEADERBOARD = new AdSize(728, 90, "728x90_as");
    public static final AdSize MEDIUM_RECTANGLE = new AdSize(300, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, "300x250_as");
    public static final AdSize WIDE_SKYSCRAPER = new AdSize(160, 600, "160x600_as");
    @Deprecated
    public static final AdSize SMART_BANNER = new AdSize(-1, -2, "smart_banner");
    public static final AdSize FLUID = new AdSize(-3, -4, "fluid");
    public static final AdSize INVALID = new AdSize(0, 0, Constants.COLLATION_INVALID);
    public static final AdSize zza = new AdSize(50, 50, "50x50_mb");
    public static final AdSize SEARCH = new AdSize(-3, 0, "search_v2");

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public AdSize(int r4, int r5) {
        /*
            r3 = this;
            r0 = -1
            if (r4 != r0) goto L6
            java.lang.String r0 = "FULL"
            goto La
        L6:
            java.lang.String r0 = java.lang.String.valueOf(r4)
        La:
            r1 = -2
            if (r5 != r1) goto L10
            java.lang.String r1 = "AUTO"
            goto L14
        L10:
            java.lang.String r1 = java.lang.String.valueOf(r5)
        L14:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r0)
            java.lang.String r0 = "x"
            r2.append(r0)
            r2.append(r1)
            java.lang.String r0 = "_as"
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            r3.<init>(r4, r5, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.AdSize.<init>(int, int):void");
    }

    public static AdSize getCurrentOrientationAnchoredAdaptiveBannerAdSize(Context context, int r3) {
        AdSize zzc = zzcgg.zzc(context, r3, 50, 0);
        zzc.zze = true;
        return zzc;
    }

    public static AdSize getCurrentOrientationInlineAdaptiveBannerAdSize(Context context, int r3) {
        int zza2 = zzcgg.zza(context, 0);
        if (zza2 == -1) {
            return INVALID;
        }
        AdSize adSize = new AdSize(r3, 0);
        adSize.zzg = zza2;
        adSize.zzf = true;
        return adSize;
    }

    public static AdSize getCurrentOrientationInterscrollerAdSize(Context context, int r2) {
        return zzj(r2, zzcgg.zza(context, 0));
    }

    public static AdSize getInlineAdaptiveBannerAdSize(int r2, int r3) {
        AdSize adSize = new AdSize(r2, 0);
        adSize.zzg = r3;
        adSize.zzf = true;
        if (r3 < 32) {
            zzcgn.zzj("The maximum height set for the inline adaptive ad size was " + r3 + " dp, which is below the minimum recommended value of 32 dp.");
        }
        return adSize;
    }

    public static AdSize getLandscapeAnchoredAdaptiveBannerAdSize(Context context, int r3) {
        AdSize zzc = zzcgg.zzc(context, r3, 50, 2);
        zzc.zze = true;
        return zzc;
    }

    public static AdSize getLandscapeInlineAdaptiveBannerAdSize(Context context, int r3) {
        int zza2 = zzcgg.zza(context, 2);
        AdSize adSize = new AdSize(r3, 0);
        if (zza2 == -1) {
            return INVALID;
        }
        adSize.zzg = zza2;
        adSize.zzf = true;
        return adSize;
    }

    public static AdSize getLandscapeInterscrollerAdSize(Context context, int r2) {
        return zzj(r2, zzcgg.zza(context, 2));
    }

    public static AdSize getPortraitAnchoredAdaptiveBannerAdSize(Context context, int r3) {
        AdSize zzc = zzcgg.zzc(context, r3, 50, 1);
        zzc.zze = true;
        return zzc;
    }

    public static AdSize getPortraitInlineAdaptiveBannerAdSize(Context context, int r4) {
        int zza2 = zzcgg.zza(context, 1);
        AdSize adSize = new AdSize(r4, 0);
        if (zza2 == -1) {
            return INVALID;
        }
        adSize.zzg = zza2;
        adSize.zzf = true;
        return adSize;
    }

    public static AdSize getPortraitInterscrollerAdSize(Context context, int r2) {
        return zzj(r2, zzcgg.zza(context, 1));
    }

    private static AdSize zzj(int r2, int r3) {
        if (r3 == -1) {
            return INVALID;
        }
        AdSize adSize = new AdSize(r2, 0);
        adSize.zzi = r3;
        adSize.zzh = true;
        return adSize;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj instanceof AdSize) {
            AdSize adSize = (AdSize) obj;
            return this.zzb == adSize.zzb && this.zzc == adSize.zzc && this.zzd.equals(adSize.zzd);
        }
        return false;
    }

    public int getHeight() {
        return this.zzc;
    }

    public int getWidth() {
        return this.zzb;
    }

    public int hashCode() {
        return this.zzd.hashCode();
    }

    public boolean isAutoHeight() {
        return this.zzc == -2;
    }

    public boolean isFluid() {
        return this.zzb == -3 && this.zzc == -4;
    }

    public boolean isFullWidth() {
        return this.zzb == -1;
    }

    public String toString() {
        return this.zzd;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int zza() {
        return this.zzi;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int zzb() {
        return this.zzg;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzc(int r1) {
        this.zzg = r1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzd(int r1) {
        this.zzi = r1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zze(boolean z) {
        this.zzf = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzf(boolean z) {
        this.zzh = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zzg() {
        return this.zze;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zzh() {
        return this.zzf;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zzi() {
        return this.zzh;
    }

    public int getHeightInPixels(Context context) {
        int r0 = this.zzc;
        if (r0 == -4 || r0 == -3) {
            return -1;
        }
        if (r0 != -2) {
            zzaw.zzb();
            return zzcgg.zzw(context, this.zzc);
        }
        return zzq.zza(context.getResources().getDisplayMetrics());
    }

    public int getWidthInPixels(Context context) {
        int r0 = this.zzb;
        if (r0 != -3) {
            if (r0 != -1) {
                zzaw.zzb();
                return zzcgg.zzw(context, this.zzb);
            }
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            Parcelable.Creator<zzq> creator = zzq.CREATOR;
            return displayMetrics.widthPixels;
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AdSize(int r2, int r3, String str) {
        if (r2 < 0 && r2 != -1 && r2 != -3) {
            throw new IllegalArgumentException("Invalid width for AdSize: " + r2);
        } else if (r3 < 0 && r3 != -2 && r3 != -4) {
            throw new IllegalArgumentException("Invalid height for AdSize: " + r3);
        } else {
            this.zzb = r2;
            this.zzc = r3;
            this.zzd = str;
        }
    }
}
