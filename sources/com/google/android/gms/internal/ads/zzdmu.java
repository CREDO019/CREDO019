package com.google.android.gms.internal.ads;

import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzdmu implements zzczf {
    private final Map zza;
    private final Map zzb;
    private final Map zzc;
    private final zzgve zzd;
    private final zzdoz zze;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzdmu(Map map, Map map2, Map map3, zzgve zzgveVar, zzdoz zzdozVar) {
        this.zza = map;
        this.zzb = map2;
        this.zzc = map3;
        this.zzd = zzgveVar;
        this.zze = zzdozVar;
    }

    @Override // com.google.android.gms.internal.ads.zzczf
    public final zzegk zza(int r3, String str) {
        zzegk zza;
        zzegk zzegkVar = (zzegk) this.zza.get(str);
        if (zzegkVar != null) {
            return zzegkVar;
        }
        if (r3 == 1) {
            if (this.zze.zze() == null || (zza = ((zzczf) this.zzd.zzb()).zza(r3, str)) == null) {
                return null;
            }
            return zzczj.zza(zza);
        } else if (r3 != 4) {
            return null;
        } else {
            zzeiy zzeiyVar = (zzeiy) this.zzc.get(str);
            if (zzeiyVar != null) {
                return new zzegl(zzeiyVar, new zzfru() { // from class: com.google.android.gms.internal.ads.zzczh
                    @Override // com.google.android.gms.internal.ads.zzfru
                    public final Object apply(Object obj) {
                        return new zzczj((List) obj);
                    }
                });
            }
            zzegk zzegkVar2 = (zzegk) this.zzb.get(str);
            if (zzegkVar2 == null) {
                return null;
            }
            return zzczj.zza(zzegkVar2);
        }
    }
}
