package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.text.TextUtils;
import java.util.List;
import org.json.JSONException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdcr extends com.google.android.gms.ads.internal.client.zzdg {
    private final String zza;
    private final String zzb;
    private final String zzc;
    private final List zzd;
    private final long zze;
    private final String zzf;
    private final zzegp zzg;
    private final Bundle zzh;

    public zzdcr(zzfcs zzfcsVar, String str, zzegp zzegpVar, zzfcv zzfcvVar) {
        String str2 = null;
        this.zzb = zzfcsVar == null ? null : zzfcsVar.zzac;
        this.zzc = zzfcvVar == null ? null : zzfcvVar.zzb;
        if ("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter".equals(str) || "com.google.ads.mediation.customevent.CustomEventAdapter".equals(str)) {
            try {
                str2 = zzfcsVar.zzw.getString("class_name");
            } catch (JSONException unused) {
            }
        }
        this.zza = str2 != null ? str2 : str;
        this.zzd = zzegpVar.zzc();
        this.zzg = zzegpVar;
        this.zze = com.google.android.gms.ads.internal.zzt.zzB().currentTimeMillis() / 1000;
        if (!((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzfQ)).booleanValue() || zzfcvVar == null) {
            this.zzh = new Bundle();
        } else {
            this.zzh = zzfcvVar.zzj;
        }
        this.zzf = (!((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzhQ)).booleanValue() || zzfcvVar == null || TextUtils.isEmpty(zzfcvVar.zzh)) ? "" : zzfcvVar.zzh;
    }

    public final long zzc() {
        return this.zze;
    }

    public final String zzd() {
        return this.zzf;
    }

    @Override // com.google.android.gms.ads.internal.client.zzdh
    public final Bundle zze() {
        return this.zzh;
    }

    @Override // com.google.android.gms.ads.internal.client.zzdh
    public final com.google.android.gms.ads.internal.client.zzu zzf() {
        zzegp zzegpVar = this.zzg;
        if (zzegpVar != null) {
            return zzegpVar.zza();
        }
        return null;
    }

    @Override // com.google.android.gms.ads.internal.client.zzdh
    public final String zzg() {
        return this.zza;
    }

    @Override // com.google.android.gms.ads.internal.client.zzdh
    public final String zzh() {
        return this.zzb;
    }

    @Override // com.google.android.gms.ads.internal.client.zzdh
    public final List zzi() {
        return this.zzd;
    }

    public final String zzj() {
        return this.zzc;
    }
}
