package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.text.TextUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdzw implements zzddb, zzdft, zzdeq {
    private final zzeai zza;
    private final String zzb;
    private int zzc = 0;
    private zzdzv zzd = zzdzv.AD_REQUESTED;
    private zzdcr zze;
    private com.google.android.gms.ads.internal.client.zze zzf;
    private String zzg;
    private String zzh;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzdzw(zzeai zzeaiVar, zzfdn zzfdnVar) {
        this.zza = zzeaiVar;
        this.zzb = zzfdnVar.zzf;
    }

    private static JSONObject zze(com.google.android.gms.ads.internal.client.zze zzeVar) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("errorDomain", zzeVar.zzc);
        jSONObject.put("errorCode", zzeVar.zza);
        jSONObject.put("errorDescription", zzeVar.zzb);
        com.google.android.gms.ads.internal.client.zze zzeVar2 = zzeVar.zzd;
        jSONObject.put("underlyingError", zzeVar2 == null ? null : zze(zzeVar2));
        return jSONObject;
    }

    private final JSONObject zzf(zzdcr zzdcrVar) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("winningAdapterClassName", zzdcrVar.zzg());
        jSONObject.put("responseSecsSinceEpoch", zzdcrVar.zzc());
        jSONObject.put("responseId", zzdcrVar.zzh());
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzhQ)).booleanValue()) {
            String zzd = zzdcrVar.zzd();
            if (!TextUtils.isEmpty(zzd)) {
                com.google.android.gms.ads.internal.util.zze.zze("Bidding data: ".concat(String.valueOf(zzd)));
                jSONObject.put("biddingData", new JSONObject(zzd));
            }
        }
        if (!TextUtils.isEmpty(this.zzg)) {
            jSONObject.put("adRequestUrl", this.zzg);
        }
        if (!TextUtils.isEmpty(this.zzh)) {
            jSONObject.put("postBody", this.zzh);
        }
        JSONArray jSONArray = new JSONArray();
        for (com.google.android.gms.ads.internal.client.zzu zzuVar : zzdcrVar.zzi()) {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("adapterClassName", zzuVar.zza);
            jSONObject2.put("latencyMillis", zzuVar.zzb);
            if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzhR)).booleanValue()) {
                jSONObject2.put("credentials", com.google.android.gms.ads.internal.client.zzaw.zzb().zzh(zzuVar.zzd));
            }
            com.google.android.gms.ads.internal.client.zze zzeVar = zzuVar.zzc;
            jSONObject2.put("error", zzeVar == null ? null : zze(zzeVar));
            jSONArray.put(jSONObject2);
        }
        jSONObject.put("adNetworks", jSONArray);
        return jSONObject;
    }

    @Override // com.google.android.gms.internal.ads.zzddb
    public final void zza(com.google.android.gms.ads.internal.client.zze zzeVar) {
        this.zzd = zzdzv.AD_LOAD_FAILED;
        this.zzf = zzeVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdft
    public final void zzb(zzfde zzfdeVar) {
        if (!zzfdeVar.zzb.zza.isEmpty()) {
            this.zzc = ((zzfcs) zzfdeVar.zzb.zza.get(0)).zzb;
        }
        if (!TextUtils.isEmpty(zzfdeVar.zzb.zzb.zzk)) {
            this.zzg = zzfdeVar.zzb.zzb.zzk;
        }
        if (TextUtils.isEmpty(zzfdeVar.zzb.zzb.zzl)) {
            return;
        }
        this.zzh = zzfdeVar.zzb.zzb.zzl;
    }

    @Override // com.google.android.gms.internal.ads.zzdft
    public final void zzbE(zzcba zzcbaVar) {
        this.zza.zze(this.zzb, this);
    }

    @Override // com.google.android.gms.internal.ads.zzdeq
    public final void zzbH(zzczc zzczcVar) {
        this.zze = zzczcVar.zzl();
        this.zzd = zzdzv.AD_LOADED;
    }

    public final JSONObject zzc() throws JSONException {
        IBinder iBinder;
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("state", this.zzd);
        jSONObject.put("format", zzfcs.zza(this.zzc));
        zzdcr zzdcrVar = this.zze;
        JSONObject jSONObject2 = null;
        if (zzdcrVar != null) {
            jSONObject2 = zzf(zzdcrVar);
        } else {
            com.google.android.gms.ads.internal.client.zze zzeVar = this.zzf;
            if (zzeVar != null && (iBinder = zzeVar.zze) != null) {
                zzdcr zzdcrVar2 = (zzdcr) iBinder;
                jSONObject2 = zzf(zzdcrVar2);
                if (zzdcrVar2.zzi().isEmpty()) {
                    JSONArray jSONArray = new JSONArray();
                    jSONArray.put(zze(this.zzf));
                    jSONObject2.put("errors", jSONArray);
                }
            }
        }
        jSONObject.put("responseInfo", jSONObject2);
        return jSONObject;
    }

    public final boolean zzd() {
        return this.zzd != zzdzv.AD_REQUESTED;
    }
}
