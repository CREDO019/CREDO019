package com.google.android.gms.internal.ads;

import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzecr implements zzect {
    private final Map zza;
    private final zzfyy zzb;
    private final zzdfr zzc;

    public zzecr(Map map, zzfyy zzfyyVar, zzdfr zzdfrVar) {
        this.zza = map;
        this.zzb = zzfyyVar;
        this.zzc = zzdfrVar;
    }

    @Override // com.google.android.gms.internal.ads.zzect
    public final zzfyx zzb(final zzcba zzcbaVar) {
        this.zzc.zzbE(zzcbaVar);
        zzfyx zzh = zzfyo.zzh(new zzeas(3));
        for (String str : ((String) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzgW)).split(",")) {
            final zzgve zzgveVar = (zzgve) this.zza.get(str.trim());
            if (zzgveVar != null) {
                zzh = zzfyo.zzg(zzh, zzeas.class, new zzfxv() { // from class: com.google.android.gms.internal.ads.zzecp
                    @Override // com.google.android.gms.internal.ads.zzfxv
                    public final zzfyx zza(Object obj) {
                        zzgve zzgveVar2 = zzgve.this;
                        zzeas zzeasVar = (zzeas) obj;
                        return ((zzect) zzgveVar2.zzb()).zzb(zzcbaVar);
                    }
                }, this.zzb);
            }
        }
        zzfyo.zzr(zzh, new zzecq(this), zzcha.zzf);
        return zzh;
    }
}
