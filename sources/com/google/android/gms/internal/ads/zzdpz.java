package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import android.view.View;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdpz {
    private final zzduw zza;
    private final zzdtl zzb;
    private final zzcvs zzc;
    private final zzdow zzd;

    public zzdpz(zzduw zzduwVar, zzdtl zzdtlVar, zzcvs zzcvsVar, zzdow zzdowVar) {
        this.zza = zzduwVar;
        this.zzb = zzdtlVar;
        this.zzc = zzcvsVar;
        this.zzd = zzdowVar;
    }

    public final View zza() throws zzcmy {
        zzcmn zza = this.zza.zza(com.google.android.gms.ads.internal.client.zzq.zzc(), null, null);
        View view = (View) zza;
        view.setVisibility(8);
        zza.zzaf("/sendMessageToSdk", new zzbpq() { // from class: com.google.android.gms.internal.ads.zzdpt
            @Override // com.google.android.gms.internal.ads.zzbpq
            public final void zza(Object obj, Map map) {
                zzdpz.this.zzb((zzcmn) obj, map);
            }
        });
        zza.zzaf("/adMuted", new zzbpq() { // from class: com.google.android.gms.internal.ads.zzdpu
            @Override // com.google.android.gms.internal.ads.zzbpq
            public final void zza(Object obj, Map map) {
                zzdpz.this.zzc((zzcmn) obj, map);
            }
        });
        this.zzb.zzj(new WeakReference(zza), "/loadHtml", new zzbpq() { // from class: com.google.android.gms.internal.ads.zzdpv
            @Override // com.google.android.gms.internal.ads.zzbpq
            public final void zza(Object obj, final Map map) {
                final zzdpz zzdpzVar = zzdpz.this;
                zzcmn zzcmnVar = (zzcmn) obj;
                zzcmnVar.zzP().zzz(new zzcny() { // from class: com.google.android.gms.internal.ads.zzdpy
                    @Override // com.google.android.gms.internal.ads.zzcny
                    public final void zza(boolean z) {
                        zzdpz.this.zzd(map, z);
                    }
                });
                String str = (String) map.get("overlayHtml");
                String str2 = (String) map.get("baseUrl");
                if (TextUtils.isEmpty(str2)) {
                    zzcmnVar.loadData(str, "text/html", "UTF-8");
                } else {
                    zzcmnVar.loadDataWithBaseURL(str2, str, "text/html", "UTF-8", null);
                }
            }
        });
        this.zzb.zzj(new WeakReference(zza), "/showOverlay", new zzbpq() { // from class: com.google.android.gms.internal.ads.zzdpw
            @Override // com.google.android.gms.internal.ads.zzbpq
            public final void zza(Object obj, Map map) {
                zzdpz.this.zze((zzcmn) obj, map);
            }
        });
        this.zzb.zzj(new WeakReference(zza), "/hideOverlay", new zzbpq() { // from class: com.google.android.gms.internal.ads.zzdpx
            @Override // com.google.android.gms.internal.ads.zzbpq
            public final void zza(Object obj, Map map) {
                zzdpz.this.zzf((zzcmn) obj, map);
            }
        });
        return view;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzb(zzcmn zzcmnVar, Map map) {
        this.zzb.zzg("sendMessageToNativeJs", map);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzc(zzcmn zzcmnVar, Map map) {
        this.zzd.zzf();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzd(Map map, boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put("messageType", "htmlLoaded");
        hashMap.put("id", (String) map.get("id"));
        this.zzb.zzg("sendMessageToNativeJs", hashMap);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zze(zzcmn zzcmnVar, Map map) {
        com.google.android.gms.ads.internal.util.zze.zzi("Showing native ads overlay.");
        zzcmnVar.zzH().setVisibility(0);
        this.zzc.zze(true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzf(zzcmn zzcmnVar, Map map) {
        com.google.android.gms.ads.internal.util.zze.zzi("Hiding native ads overlay.");
        zzcmnVar.zzH().setVisibility(8);
        this.zzc.zze(false);
    }
}
