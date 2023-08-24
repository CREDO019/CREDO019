package com.google.android.gms.internal.ads;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzegp {
    private zzfcv zzc = null;
    private zzfcs zzd = null;
    private com.google.android.gms.ads.internal.client.zzu zze = null;
    private final Map zzb = Collections.synchronizedMap(new HashMap());
    private final List zza = Collections.synchronizedList(new ArrayList());

    private final void zzh(zzfcs zzfcsVar, long j, com.google.android.gms.ads.internal.client.zze zzeVar, boolean z) {
        String str = zzfcsVar.zzx;
        if (this.zzb.containsKey(str)) {
            if (this.zzd == null) {
                this.zzd = zzfcsVar;
            }
            com.google.android.gms.ads.internal.client.zzu zzuVar = (com.google.android.gms.ads.internal.client.zzu) this.zzb.get(str);
            zzuVar.zzb = j;
            zzuVar.zzc = zzeVar;
            if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzfP)).booleanValue() && z) {
                this.zze = zzuVar;
            }
        }
    }

    public final com.google.android.gms.ads.internal.client.zzu zza() {
        return this.zze;
    }

    public final zzdcr zzb() {
        return new zzdcr(this.zzd, "", this, this.zzc);
    }

    public final List zzc() {
        return this.zza;
    }

    public final void zzd(zzfcs zzfcsVar) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5 = zzfcsVar.zzx;
        if (this.zzb.containsKey(str5)) {
            return;
        }
        Bundle bundle = new Bundle();
        Iterator<String> keys = zzfcsVar.zzw.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            try {
                bundle.putString(next, zzfcsVar.zzw.getString(next));
            } catch (JSONException unused) {
            }
        }
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzfO)).booleanValue()) {
            String str6 = zzfcsVar.zzG;
            String str7 = zzfcsVar.zzH;
            str = str6;
            str2 = str7;
            str3 = zzfcsVar.zzI;
            str4 = zzfcsVar.zzJ;
        } else {
            str = "";
            str2 = str;
            str3 = str2;
            str4 = str3;
        }
        com.google.android.gms.ads.internal.client.zzu zzuVar = new com.google.android.gms.ads.internal.client.zzu(zzfcsVar.zzF, 0L, null, bundle, str, str2, str3, str4);
        this.zza.add(zzuVar);
        this.zzb.put(str5, zzuVar);
    }

    public final void zze(zzfcs zzfcsVar, long j, com.google.android.gms.ads.internal.client.zze zzeVar) {
        zzh(zzfcsVar, j, zzeVar, false);
    }

    public final void zzf(zzfcs zzfcsVar, long j, com.google.android.gms.ads.internal.client.zze zzeVar) {
        zzh(zzfcsVar, j, null, true);
    }

    public final void zzg(zzfcv zzfcvVar) {
        this.zzc = zzfcvVar;
    }
}
