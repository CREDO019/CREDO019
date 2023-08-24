package com.google.android.gms.ads;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.ads.admanager.AppEventListener;
import com.google.android.gms.ads.internal.client.zzay;
import com.google.android.gms.ads.internal.client.zzdu;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.ads.zzbiy;
import com.google.android.gms.internal.ads.zzbkm;
import com.google.android.gms.internal.ads.zzcad;
import com.google.android.gms.internal.ads.zzcgc;
import com.google.android.gms.internal.ads.zzcgn;
import org.checkerframework.checker.initialization.qual.NotOnlyInitialized;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class BaseAdView extends ViewGroup {
    @NotOnlyInitialized
    protected final zzdu zza;

    /* JADX INFO: Access modifiers changed from: protected */
    public BaseAdView(Context context, int r2) {
        super(context);
        this.zza = new zzdu(this, r2);
    }

    public void destroy() {
        zzbiy.zzc(getContext());
        if (((Boolean) zzbkm.zze.zze()).booleanValue()) {
            if (((Boolean) zzay.zzc().zzb(zzbiy.zziD)).booleanValue()) {
                zzcgc.zzb.execute(new Runnable() { // from class: com.google.android.gms.ads.zze
                    @Override // java.lang.Runnable
                    public final void run() {
                        BaseAdView baseAdView = BaseAdView.this;
                        try {
                            baseAdView.zza.zzk();
                        } catch (IllegalStateException e) {
                            zzcad.zza(baseAdView.getContext()).zzd(e, "BaseAdView.destroy");
                        }
                    }
                });
                return;
            }
        }
        this.zza.zzk();
    }

    public AdListener getAdListener() {
        return this.zza.zza();
    }

    public AdSize getAdSize() {
        return this.zza.zzb();
    }

    public String getAdUnitId() {
        return this.zza.zzj();
    }

    public OnPaidEventListener getOnPaidEventListener() {
        return this.zza.zzc();
    }

    public ResponseInfo getResponseInfo() {
        return this.zza.zzd();
    }

    public boolean isLoading() {
        return this.zza.zzA();
    }

    public void loadAd(final AdRequest adRequest) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzbiy.zzc(getContext());
        if (((Boolean) zzbkm.zzf.zze()).booleanValue()) {
            if (((Boolean) zzay.zzc().zzb(zzbiy.zziG)).booleanValue()) {
                zzcgc.zzb.execute(new Runnable() { // from class: com.google.android.gms.ads.zzc
                    @Override // java.lang.Runnable
                    public final void run() {
                        BaseAdView baseAdView = BaseAdView.this;
                        try {
                            baseAdView.zza.zzm(adRequest.zza());
                        } catch (IllegalStateException e) {
                            zzcad.zza(baseAdView.getContext()).zzd(e, "BaseAdView.loadAd");
                        }
                    }
                });
                return;
            }
        }
        this.zza.zzm(adRequest.zza());
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int r4, int r5, int r6, int r7) {
        View childAt = getChildAt(0);
        if (childAt == null || childAt.getVisibility() == 8) {
            return;
        }
        int measuredWidth = childAt.getMeasuredWidth();
        int measuredHeight = childAt.getMeasuredHeight();
        int r62 = ((r6 - r4) - measuredWidth) / 2;
        int r72 = ((r7 - r5) - measuredHeight) / 2;
        childAt.layout(r62, r72, measuredWidth + r62, measuredHeight + r72);
    }

    @Override // android.view.View
    protected void onMeasure(int r5, int r6) {
        AdSize adSize;
        int r1;
        int r0 = 0;
        View childAt = getChildAt(0);
        if (childAt == null || childAt.getVisibility() == 8) {
            try {
                adSize = getAdSize();
            } catch (NullPointerException e) {
                zzcgn.zzh("Unable to retrieve ad size.", e);
                adSize = null;
            }
            if (adSize != null) {
                Context context = getContext();
                int widthInPixels = adSize.getWidthInPixels(context);
                r1 = adSize.getHeightInPixels(context);
                r0 = widthInPixels;
            } else {
                r1 = 0;
            }
        } else {
            measureChild(childAt, r5, r6);
            r0 = childAt.getMeasuredWidth();
            r1 = childAt.getMeasuredHeight();
        }
        setMeasuredDimension(View.resolveSize(Math.max(r0, getSuggestedMinimumWidth()), r5), View.resolveSize(Math.max(r1, getSuggestedMinimumHeight()), r6));
    }

    public void pause() {
        zzbiy.zzc(getContext());
        if (((Boolean) zzbkm.zzg.zze()).booleanValue()) {
            if (((Boolean) zzay.zzc().zzb(zzbiy.zziE)).booleanValue()) {
                zzcgc.zzb.execute(new Runnable() { // from class: com.google.android.gms.ads.zzd
                    @Override // java.lang.Runnable
                    public final void run() {
                        BaseAdView baseAdView = BaseAdView.this;
                        try {
                            baseAdView.zza.zzn();
                        } catch (IllegalStateException e) {
                            zzcad.zza(baseAdView.getContext()).zzd(e, "BaseAdView.pause");
                        }
                    }
                });
                return;
            }
        }
        this.zza.zzn();
    }

    public void resume() {
        zzbiy.zzc(getContext());
        if (((Boolean) zzbkm.zzh.zze()).booleanValue()) {
            if (((Boolean) zzay.zzc().zzb(zzbiy.zziC)).booleanValue()) {
                zzcgc.zzb.execute(new Runnable() { // from class: com.google.android.gms.ads.zzf
                    @Override // java.lang.Runnable
                    public final void run() {
                        BaseAdView baseAdView = BaseAdView.this;
                        try {
                            baseAdView.zza.zzp();
                        } catch (IllegalStateException e) {
                            zzcad.zza(baseAdView.getContext()).zzd(e, "BaseAdView.resume");
                        }
                    }
                });
                return;
            }
        }
        this.zza.zzp();
    }

    public void setAdListener(AdListener adListener) {
        this.zza.zzr(adListener);
        if (adListener == null) {
            this.zza.zzq(null);
            return;
        }
        if (adListener instanceof com.google.android.gms.ads.internal.client.zza) {
            this.zza.zzq((com.google.android.gms.ads.internal.client.zza) adListener);
        }
        if (adListener instanceof AppEventListener) {
            this.zza.zzv((AppEventListener) adListener);
        }
    }

    public void setAdSize(AdSize adSize) {
        this.zza.zzs(adSize);
    }

    public void setAdUnitId(String str) {
        this.zza.zzu(str);
    }

    public void setOnPaidEventListener(OnPaidEventListener onPaidEventListener) {
        this.zza.zzx(onPaidEventListener);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BaseAdView(Context context, AttributeSet attributeSet, int r4) {
        super(context, attributeSet);
        this.zza = new zzdu(this, attributeSet, false, r4);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BaseAdView(Context context, AttributeSet attributeSet, int r3, int r4) {
        super(context, attributeSet, r3);
        this.zza = new zzdu(this, attributeSet, false, r4);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BaseAdView(Context context, AttributeSet attributeSet, int r3, int r4, boolean z) {
        super(context, attributeSet, r3);
        this.zza = new zzdu(this, attributeSet, z, r4);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BaseAdView(Context context, AttributeSet attributeSet, boolean z) {
        super(context, attributeSet);
        this.zza = new zzdu(this, attributeSet, z);
    }
}
