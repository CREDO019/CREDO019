package com.google.android.gms.ads;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.client.zzaw;
import com.google.android.gms.ads.internal.client.zzdh;
import com.google.android.gms.ads.internal.client.zzu;
import com.google.android.gms.internal.ads.zzcgn;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class ResponseInfo {
    private final zzdh zza;
    private final List zzb = new ArrayList();
    private AdapterResponseInfo zzc;

    private ResponseInfo(zzdh zzdhVar) {
        this.zza = zzdhVar;
        if (zzdhVar != null) {
            try {
                List<zzu> zzi = zzdhVar.zzi();
                if (zzi != null) {
                    for (zzu zzuVar : zzi) {
                        AdapterResponseInfo zza = AdapterResponseInfo.zza(zzuVar);
                        if (zza != null) {
                            this.zzb.add(zza);
                        }
                    }
                }
            } catch (RemoteException e) {
                zzcgn.zzh("Could not forward getAdapterResponseInfo to ResponseInfo.", e);
            }
        }
        zzdh zzdhVar2 = this.zza;
        if (zzdhVar2 == null) {
            return;
        }
        try {
            zzu zzf = zzdhVar2.zzf();
            if (zzf != null) {
                this.zzc = AdapterResponseInfo.zza(zzf);
            }
        } catch (RemoteException e2) {
            zzcgn.zzh("Could not forward getLoadedAdapterResponse to ResponseInfo.", e2);
        }
    }

    public static ResponseInfo zza(zzdh zzdhVar) {
        if (zzdhVar != null) {
            return new ResponseInfo(zzdhVar);
        }
        return null;
    }

    public static ResponseInfo zzb(zzdh zzdhVar) {
        return new ResponseInfo(zzdhVar);
    }

    public List<AdapterResponseInfo> getAdapterResponses() {
        return this.zzb;
    }

    public AdapterResponseInfo getLoadedAdapterResponseInfo() {
        return this.zzc;
    }

    public String getMediationAdapterClassName() {
        try {
            zzdh zzdhVar = this.zza;
            if (zzdhVar != null) {
                return zzdhVar.zzg();
            }
            return null;
        } catch (RemoteException e) {
            zzcgn.zzh("Could not forward getMediationAdapterClassName to ResponseInfo.", e);
            return null;
        }
    }

    public Bundle getResponseExtras() {
        try {
            zzdh zzdhVar = this.zza;
            if (zzdhVar != null) {
                return zzdhVar.zze();
            }
        } catch (RemoteException e) {
            zzcgn.zzh("Could not forward getResponseExtras to ResponseInfo.", e);
        }
        return new Bundle();
    }

    public String getResponseId() {
        try {
            zzdh zzdhVar = this.zza;
            if (zzdhVar != null) {
                return zzdhVar.zzh();
            }
            return null;
        } catch (RemoteException e) {
            zzcgn.zzh("Could not forward getResponseId to ResponseInfo.", e);
            return null;
        }
    }

    public String toString() {
        try {
            return zzc().toString(2);
        } catch (JSONException unused) {
            return "Error forming toString output.";
        }
    }

    public final JSONObject zzc() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        String responseId = getResponseId();
        if (responseId == null) {
            jSONObject.put("Response ID", "null");
        } else {
            jSONObject.put("Response ID", responseId);
        }
        String mediationAdapterClassName = getMediationAdapterClassName();
        if (mediationAdapterClassName == null) {
            jSONObject.put("Mediation Adapter Class Name", "null");
        } else {
            jSONObject.put("Mediation Adapter Class Name", mediationAdapterClassName);
        }
        JSONArray jSONArray = new JSONArray();
        for (AdapterResponseInfo adapterResponseInfo : this.zzb) {
            jSONArray.put(adapterResponseInfo.zzb());
        }
        jSONObject.put("Adapter Responses", jSONArray);
        AdapterResponseInfo adapterResponseInfo2 = this.zzc;
        if (adapterResponseInfo2 != null) {
            jSONObject.put("Loaded Adapter Response", adapterResponseInfo2.zzb());
        }
        Bundle responseExtras = getResponseExtras();
        if (responseExtras != null) {
            jSONObject.put("Response Extras", zzaw.zzb().zzh(responseExtras));
        }
        return jSONObject;
    }
}
