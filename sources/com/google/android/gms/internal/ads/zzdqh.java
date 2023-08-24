package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdqh {
    private final zzduw zza;
    private final zzdtl zzb;
    private ViewTreeObserver.OnScrollChangedListener zzc = null;

    public zzdqh(zzduw zzduwVar, zzdtl zzdtlVar) {
        this.zza = zzduwVar;
        this.zzb = zzdtlVar;
    }

    private static final int zzf(Context context, String str, int r2) {
        try {
            r2 = Integer.parseInt(str);
        } catch (NumberFormatException unused) {
        }
        com.google.android.gms.ads.internal.client.zzaw.zzb();
        return zzcgg.zzw(context, r2);
    }

    public final View zza(final View view, final WindowManager windowManager) throws zzcmy {
        zzcmn zza = this.zza.zza(com.google.android.gms.ads.internal.client.zzq.zzc(), null, null);
        View view2 = (View) zza;
        view2.setVisibility(4);
        view2.setContentDescription("policy_validator");
        zza.zzaf("/sendMessageToSdk", new zzbpq() { // from class: com.google.android.gms.internal.ads.zzdqd
            @Override // com.google.android.gms.internal.ads.zzbpq
            public final void zza(Object obj, Map map) {
                zzdqh.this.zzb((zzcmn) obj, map);
            }
        });
        zza.zzaf("/hideValidatorOverlay", new zzbpq() { // from class: com.google.android.gms.internal.ads.zzdqe
            @Override // com.google.android.gms.internal.ads.zzbpq
            public final void zza(Object obj, Map map) {
                zzdqh.this.zzc(windowManager, view, (zzcmn) obj, map);
            }
        });
        zza.zzaf("/open", new zzbqb(null, null, null, null, null));
        this.zzb.zzj(new WeakReference(zza), "/loadNativeAdPolicyViolations", new zzbpq() { // from class: com.google.android.gms.internal.ads.zzdqf
            @Override // com.google.android.gms.internal.ads.zzbpq
            public final void zza(Object obj, Map map) {
                zzdqh.this.zze(view, windowManager, (zzcmn) obj, map);
            }
        });
        this.zzb.zzj(new WeakReference(zza), "/showValidatorOverlay", new zzbpq() { // from class: com.google.android.gms.internal.ads.zzdqg
            @Override // com.google.android.gms.internal.ads.zzbpq
            public final void zza(Object obj, Map map) {
                com.google.android.gms.ads.internal.util.zze.zze("Show native ad policy validator overlay.");
                ((zzcmn) obj).zzH().setVisibility(0);
            }
        });
        return view2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzb(zzcmn zzcmnVar, Map map) {
        this.zzb.zzg("sendMessageToNativeJs", map);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzc(WindowManager windowManager, View view, zzcmn zzcmnVar, Map map) {
        com.google.android.gms.ads.internal.util.zze.zze("Hide native ad policy validator overlay.");
        zzcmnVar.zzH().setVisibility(8);
        if (zzcmnVar.zzH().getWindowToken() != null) {
            windowManager.removeView(zzcmnVar.zzH());
        }
        zzcmnVar.destroy();
        ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
        if (this.zzc == null || viewTreeObserver == null || !viewTreeObserver.isAlive()) {
            return;
        }
        viewTreeObserver.removeOnScrollChangedListener(this.zzc);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzd(Map map, boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put("messageType", "validatorHtmlLoaded");
        hashMap.put("id", (String) map.get("id"));
        this.zzb.zzg("sendMessageToNativeJs", hashMap);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zze(final View view, final WindowManager windowManager, final zzcmn zzcmnVar, final Map map) {
        int r1;
        zzcmnVar.zzP().zzz(new zzcny() { // from class: com.google.android.gms.internal.ads.zzdqb
            @Override // com.google.android.gms.internal.ads.zzcny
            public final void zza(boolean z) {
                zzdqh.this.zzd(map, z);
            }
        });
        if (map == null) {
            return;
        }
        Context context = view.getContext();
        int zzf = zzf(context, (String) map.get("validator_width"), ((Integer) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzgP)).intValue());
        int zzf2 = zzf(context, (String) map.get("validator_height"), ((Integer) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzgQ)).intValue());
        int zzf3 = zzf(context, (String) map.get("validator_x"), 0);
        int zzf4 = zzf(context, (String) map.get("validator_y"), 0);
        zzcmnVar.zzai(zzcoc.zzb(zzf, zzf2));
        try {
            zzcmnVar.zzI().getSettings().setUseWideViewPort(((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzgR)).booleanValue());
            zzcmnVar.zzI().getSettings().setLoadWithOverviewMode(((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzgS)).booleanValue());
        } catch (NullPointerException unused) {
        }
        final WindowManager.LayoutParams zzb = com.google.android.gms.ads.internal.util.zzbx.zzb();
        zzb.x = zzf3;
        zzb.y = zzf4;
        windowManager.updateViewLayout(zzcmnVar.zzH(), zzb);
        final String str = (String) map.get("orientation");
        Rect rect = new Rect();
        if (view.getGlobalVisibleRect(rect)) {
            if (IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE.equals(str) || "2".equals(str)) {
                r1 = rect.bottom;
            } else {
                r1 = rect.top;
            }
            final int r12 = r1 - zzf4;
            this.zzc = new ViewTreeObserver.OnScrollChangedListener() { // from class: com.google.android.gms.internal.ads.zzdqc
                @Override // android.view.ViewTreeObserver.OnScrollChangedListener
                public final void onScrollChanged() {
                    View view2 = view;
                    zzcmn zzcmnVar2 = zzcmnVar;
                    String str2 = str;
                    WindowManager.LayoutParams layoutParams = zzb;
                    int r4 = r12;
                    WindowManager windowManager2 = windowManager;
                    Rect rect2 = new Rect();
                    if (!view2.getGlobalVisibleRect(rect2) || zzcmnVar2.zzH().getWindowToken() == null) {
                        return;
                    }
                    if (IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE.equals(str2) || "2".equals(str2)) {
                        layoutParams.y = rect2.bottom - r4;
                    } else {
                        layoutParams.y = rect2.top - r4;
                    }
                    windowManager2.updateViewLayout(zzcmnVar2.zzH(), layoutParams);
                }
            };
            ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
            if (viewTreeObserver != null && viewTreeObserver.isAlive()) {
                viewTreeObserver.addOnScrollChangedListener(this.zzc);
            }
        }
        String str2 = (String) map.get("overlay_url");
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        zzcmnVar.loadUrl(str2);
    }
}
