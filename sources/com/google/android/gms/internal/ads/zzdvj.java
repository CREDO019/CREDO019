package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.ads.mediation.admob.AdMobAdapter;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdvj {
    private final zzfeg zza;
    private final zzdvg zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzdvj(zzfeg zzfegVar, zzdvg zzdvgVar) {
        this.zza = zzfegVar;
        this.zzb = zzdvgVar;
    }

    final zzbvf zza() throws RemoteException {
        zzbvf zzb = this.zza.zzb();
        if (zzb != null) {
            return zzb;
        }
        com.google.android.gms.ads.internal.util.zze.zzj("Unexpected call to adapter creator.");
        throw new RemoteException();
    }

    public final zzbwy zzb(String str) throws RemoteException {
        zzbwy zzc = zza().zzc(str);
        this.zzb.zze(str, zzc);
        return zzc;
    }

    public final zzfei zzc(String str, JSONObject jSONObject) throws zzfds {
        zzbvi zzb;
        try {
            if ("com.google.ads.mediation.admob.AdMobAdapter".equals(str)) {
                zzb = new zzbwe(new AdMobAdapter());
            } else if (!"com.google.ads.mediation.admob.AdMobCustomTabsAdapter".equals(str)) {
                zzbvf zza = zza();
                if ("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter".equals(str) || "com.google.ads.mediation.customevent.CustomEventAdapter".equals(str)) {
                    try {
                        String string = jSONObject.getString("class_name");
                        if (zza.zze(string)) {
                            zzb = zza.zzb("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter");
                        } else if (zza.zzd(string)) {
                            zzb = zza.zzb(string);
                        } else {
                            zzb = zza.zzb("com.google.ads.mediation.customevent.CustomEventAdapter");
                        }
                    } catch (JSONException e) {
                        com.google.android.gms.ads.internal.util.zze.zzh("Invalid custom event.", e);
                    }
                }
                zzb = zza.zzb(str);
            } else {
                zzb = new zzbwe(new zzbxp());
            }
            zzfei zzfeiVar = new zzfei(zzb);
            this.zzb.zzd(str, zzfeiVar);
            return zzfeiVar;
        } catch (Throwable th) {
            if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzhU)).booleanValue()) {
                this.zzb.zzd(str, null);
            }
            throw new zzfds(th);
        }
    }

    public final boolean zzd() {
        return this.zza.zzb() != null;
    }
}
