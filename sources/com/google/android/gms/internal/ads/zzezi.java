package com.google.android.gms.internal.ads;

import java.util.Iterator;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzezi implements zzfah {
    private zzdch zza;
    private final Executor zzb = zzfze.zzb();

    public final zzdch zza() {
        return this.zza;
    }

    public final zzfyx zzb(zzfai zzfaiVar, zzfag zzfagVar, zzdch zzdchVar) {
        zzdcg zza = zzfagVar.zza(zzfaiVar.zzb);
        zza.zzb(new zzfan(true));
        zzdch zzdchVar2 = (zzdch) zza.zzh();
        this.zza = zzdchVar2;
        final zzdaf zzb = zzdchVar2.zzb();
        final zzffh zzffhVar = new zzffh();
        return zzfyo.zzm(zzfyo.zzn(zzfyf.zzv(zzb.zzi()), new zzfxv() { // from class: com.google.android.gms.internal.ads.zzezg
            @Override // com.google.android.gms.internal.ads.zzfxv
            public final zzfyx zza(Object obj) {
                zzffh zzffhVar2 = zzffhVar;
                zzdaf zzdafVar = zzb;
                zzfde zzfdeVar = (zzfde) obj;
                zzffhVar2.zzb = zzfdeVar;
                Iterator it = zzfdeVar.zzb.zza.iterator();
                boolean z = false;
                loop0: while (true) {
                    if (it.hasNext()) {
                        for (String str : ((zzfcs) it.next()).zza) {
                            if (!str.contains("FirstPartyRenderer")) {
                                break loop0;
                            }
                            z = true;
                        }
                    } else if (z) {
                        return zzdafVar.zzh(zzfyo.zzi(zzfdeVar));
                    }
                }
                return zzfyo.zzi(null);
            }
        }, this.zzb), new zzfru() { // from class: com.google.android.gms.internal.ads.zzezh
            @Override // com.google.android.gms.internal.ads.zzfru
            public final Object apply(Object obj) {
                zzffh zzffhVar2 = zzffh.this;
                zzffhVar2.zzc = (zzczc) obj;
                return zzffhVar2;
            }
        }, this.zzb);
    }

    @Override // com.google.android.gms.internal.ads.zzfah
    public final /* bridge */ /* synthetic */ zzfyx zzc(zzfai zzfaiVar, zzfag zzfagVar, Object obj) {
        return zzb(zzfaiVar, zzfagVar, null);
    }

    @Override // com.google.android.gms.internal.ads.zzfah
    public final /* synthetic */ Object zzd() {
        return this.zza;
    }
}
