package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzead {
    private final zzdzq zza;
    private final zzdvg zzb;
    private final Object zzc = new Object();
    private final List zzd = new ArrayList();
    private boolean zze;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzead(zzdzq zzdzqVar, zzdvg zzdvgVar) {
        this.zza = zzdzqVar;
        this.zzb = zzdvgVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzd(List list) {
        String str;
        boolean z;
        zzdvf zza;
        zzbxl zzbxlVar;
        synchronized (this.zzc) {
            if (this.zze) {
                return;
            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                zzbrl zzbrlVar = (zzbrl) it.next();
                if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzhT)).booleanValue()) {
                    zzdvf zza2 = this.zzb.zza(zzbrlVar.zza);
                    if (zza2 != null && (zzbxlVar = zza2.zzc) != null) {
                        str = zzbxlVar.toString();
                    }
                    str = "";
                } else {
                    str = "";
                }
                String str2 = str;
                if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzhU)).booleanValue() && (zza = this.zzb.zza(zzbrlVar.zza)) != null && zza.zzd) {
                    z = true;
                    List list2 = this.zzd;
                    String str3 = zzbrlVar.zza;
                    list2.add(new zzeac(str3, str2, this.zzb.zzc(str3), zzbrlVar.zzb ? 1 : 0, zzbrlVar.zzd, zzbrlVar.zzc, z));
                }
                z = false;
                List list22 = this.zzd;
                String str32 = zzbrlVar.zza;
                list22.add(new zzeac(str32, str2, this.zzb.zzc(str32), zzbrlVar.zzb ? 1 : 0, zzbrlVar.zzd, zzbrlVar.zzc, z));
            }
            this.zze = true;
        }
    }

    public final JSONArray zza() throws JSONException {
        JSONArray jSONArray = new JSONArray();
        synchronized (this.zzc) {
            if (!this.zze) {
                if (this.zza.zzt()) {
                    zzd(this.zza.zzg());
                } else {
                    zzc();
                    return jSONArray;
                }
            }
            for (zzeac zzeacVar : this.zzd) {
                jSONArray.put(zzeacVar.zza());
            }
            return jSONArray;
        }
    }

    public final void zzc() {
        this.zza.zzs(new zzeab(this));
    }
}
