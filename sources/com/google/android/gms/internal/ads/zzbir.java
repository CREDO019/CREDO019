package com.google.android.gms.internal.ads;

import android.content.SharedPreferences;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbir {
    private final Collection zza = new ArrayList();
    private final Collection zzb = new ArrayList();
    private final Collection zzc = new ArrayList();

    public final List zza() {
        ArrayList arrayList = new ArrayList();
        for (zzbiq zzbiqVar : this.zzb) {
            String str = (String) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiqVar);
            if (!TextUtils.isEmpty(str)) {
                arrayList.add(str);
            }
        }
        arrayList.addAll(zzbiz.zza());
        return arrayList;
    }

    public final List zzb() {
        List zza = zza();
        for (zzbiq zzbiqVar : this.zzc) {
            String str = (String) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiqVar);
            if (!TextUtils.isEmpty(str)) {
                zza.add(str);
            }
        }
        zza.addAll(zzbiz.zzb());
        return zza;
    }

    public final void zzc(zzbiq zzbiqVar) {
        this.zzb.add(zzbiqVar);
    }

    public final void zzd(zzbiq zzbiqVar) {
        this.zza.add(zzbiqVar);
    }

    public final void zze(SharedPreferences.Editor editor, int r5, JSONObject jSONObject) {
        for (zzbiq zzbiqVar : this.zza) {
            if (zzbiqVar.zze() == 1) {
                zzbiqVar.zzd(editor, zzbiqVar.zza(jSONObject));
            }
        }
        if (jSONObject != null) {
            editor.putString("flag_configuration", jSONObject.toString());
        } else {
            zzcgn.zzg("Flag Json is null.");
        }
    }
}
