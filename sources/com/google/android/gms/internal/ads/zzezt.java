package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzezt implements zzfah {
    private final zzfey zza;
    private final Executor zzb;
    private final zzfyk zzc = new zzezr(this);

    public zzezt(zzfey zzfeyVar, Executor executor) {
        this.zza = zzfeyVar;
        this.zzb = executor;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzfyx zza(zzdch zzdchVar, zzfac zzfacVar) throws Exception {
        zzffi zzffiVar = zzfacVar.zzb;
        zzcba zzcbaVar = zzfacVar.zza;
        zzffh zzb = zzffiVar != null ? this.zza.zzb(zzffiVar) : null;
        if (zzffiVar == null) {
            return zzfyo.zzi(null);
        }
        if (zzb != null && zzcbaVar != null) {
            zzfyo.zzr(zzdchVar.zzb().zzg(zzcbaVar), this.zzc, this.zzb);
        }
        return zzfyo.zzi(new zzezs(zzffiVar, zzcbaVar, zzb));
    }

    public final zzfyx zzb(zzfai zzfaiVar, zzfag zzfagVar, final zzdch zzdchVar) {
        return zzfyo.zzf(zzfyo.zzn(zzfyf.zzv(new zzfad(this.zza, zzdchVar, this.zzb).zzc()), new zzfxv() { // from class: com.google.android.gms.internal.ads.zzezp
            @Override // com.google.android.gms.internal.ads.zzfxv
            public final zzfyx zza(Object obj) {
                return zzezt.this.zza(zzdchVar, (zzfac) obj);
            }
        }, this.zzb), Exception.class, new zzezq(this), this.zzb);
    }

    @Override // com.google.android.gms.internal.ads.zzfah
    public final /* bridge */ /* synthetic */ zzfyx zzc(zzfai zzfaiVar, zzfag zzfagVar, Object obj) {
        return zzb(zzfaiVar, zzfagVar, null);
    }

    @Override // com.google.android.gms.internal.ads.zzfah
    public final /* bridge */ /* synthetic */ Object zzd() {
        return null;
    }
}
