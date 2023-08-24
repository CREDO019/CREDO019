package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzemo extends zzbxa {
    private final String zza;
    private final zzbwy zzb;
    private final zzchf zzc;
    private final JSONObject zzd;
    private boolean zze;

    public zzemo(String str, zzbwy zzbwyVar, zzchf zzchfVar) {
        JSONObject jSONObject = new JSONObject();
        this.zzd = jSONObject;
        this.zze = false;
        this.zzc = zzchfVar;
        this.zza = str;
        this.zzb = zzbwyVar;
        try {
            jSONObject.put("adapter_version", zzbwyVar.zzf().toString());
            jSONObject.put("sdk_version", zzbwyVar.zzg().toString());
            jSONObject.put("name", str);
        } catch (RemoteException | NullPointerException | JSONException unused) {
        }
    }

    public static synchronized void zzb(String str, zzchf zzchfVar) {
        synchronized (zzemo.class) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("name", str);
                jSONObject.put("signal_error", "Adapter failed to instantiate");
                zzchfVar.zzd(jSONObject);
            } catch (JSONException unused) {
            }
        }
    }

    public final synchronized void zzc() {
        try {
            zzf("Signal collection timeout.");
        } catch (RemoteException unused) {
        }
    }

    public final synchronized void zzd() {
        if (this.zze) {
            return;
        }
        this.zzc.zzd(this.zzd);
        this.zze = true;
    }

    @Override // com.google.android.gms.internal.ads.zzbxb
    public final synchronized void zze(String str) throws RemoteException {
        if (this.zze) {
            return;
        }
        if (str == null) {
            zzf("Adapter returned null signals");
            return;
        }
        try {
            this.zzd.put("signals", str);
        } catch (JSONException unused) {
        }
        this.zzc.zzd(this.zzd);
        this.zze = true;
    }

    @Override // com.google.android.gms.internal.ads.zzbxb
    public final synchronized void zzf(String str) throws RemoteException {
        if (this.zze) {
            return;
        }
        try {
            this.zzd.put("signal_error", str);
        } catch (JSONException unused) {
        }
        this.zzc.zzd(this.zzd);
        this.zze = true;
    }

    @Override // com.google.android.gms.internal.ads.zzbxb
    public final synchronized void zzg(com.google.android.gms.ads.internal.client.zze zzeVar) throws RemoteException {
        if (this.zze) {
            return;
        }
        try {
            this.zzd.put("signal_error", zzeVar.zzb);
        } catch (JSONException unused) {
        }
        this.zzc.zzd(this.zzd);
        this.zze = true;
    }
}
