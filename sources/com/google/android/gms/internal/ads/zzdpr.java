package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.view.View;
import com.google.android.gms.common.util.Clock;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdpr implements View.OnClickListener {
    String zza;
    Long zzb;
    WeakReference zzc;
    private final zzdtl zzd;
    private final Clock zze;
    private zzbnu zzf;
    private zzbpq zzg;

    public zzdpr(zzdtl zzdtlVar, Clock clock) {
        this.zzd = zzdtlVar;
        this.zze = clock;
    }

    private final void zzd() {
        View view;
        this.zza = null;
        this.zzb = null;
        WeakReference weakReference = this.zzc;
        if (weakReference == null || (view = (View) weakReference.get()) == null) {
            return;
        }
        view.setClickable(false);
        view.setOnClickListener(null);
        this.zzc = null;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        WeakReference weakReference = this.zzc;
        if (weakReference == null || weakReference.get() != view) {
            return;
        }
        if (this.zza != null && this.zzb != null) {
            HashMap hashMap = new HashMap();
            hashMap.put("id", this.zza);
            hashMap.put("time_interval", String.valueOf(this.zze.currentTimeMillis() - this.zzb.longValue()));
            hashMap.put("messageType", "onePointFiveClick");
            this.zzd.zzg("sendMessageToNativeJs", hashMap);
        }
        zzd();
    }

    public final zzbnu zza() {
        return this.zzf;
    }

    public final void zzb() {
        if (this.zzf == null || this.zzb == null) {
            return;
        }
        zzd();
        try {
            this.zzf.zze();
        } catch (RemoteException e) {
            zzcgn.zzl("#007 Could not call remote method.", e);
        }
    }

    public final void zzc(final zzbnu zzbnuVar) {
        this.zzf = zzbnuVar;
        zzbpq zzbpqVar = this.zzg;
        if (zzbpqVar != null) {
            this.zzd.zzk("/unconfirmedClick", zzbpqVar);
        }
        zzbpq zzbpqVar2 = new zzbpq() { // from class: com.google.android.gms.internal.ads.zzdpq
            @Override // com.google.android.gms.internal.ads.zzbpq
            public final void zza(Object obj, Map map) {
                zzdpr zzdprVar = zzdpr.this;
                zzbnu zzbnuVar2 = zzbnuVar;
                try {
                    zzdprVar.zzb = Long.valueOf(Long.parseLong((String) map.get("timestamp")));
                } catch (NumberFormatException unused) {
                    com.google.android.gms.ads.internal.util.zze.zzg("Failed to call parse unconfirmedClickTimestamp.");
                }
                zzdprVar.zza = (String) map.get("id");
                String str = (String) map.get("asset_id");
                if (zzbnuVar2 == null) {
                    com.google.android.gms.ads.internal.util.zze.zze("Received unconfirmed click but UnconfirmedClickListener is null.");
                    return;
                }
                try {
                    zzbnuVar2.zzf(str);
                } catch (RemoteException e) {
                    zzcgn.zzl("#007 Could not call remote method.", e);
                }
            }
        };
        this.zzg = zzbpqVar2;
        this.zzd.zzi("/unconfirmedClick", zzbpqVar2);
    }
}
