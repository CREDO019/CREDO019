package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzezn implements zzfah {
    private final zzfah zza;
    private final zzfah zzb;
    private final zzffu zzc;
    private final String zzd;
    private zzdch zze;
    private final Executor zzf;

    public zzezn(zzfah zzfahVar, zzfah zzfahVar2, zzffu zzffuVar, String str, Executor executor) {
        this.zza = zzfahVar;
        this.zzb = zzfahVar2;
        this.zzc = zzffuVar;
        this.zzd = str;
        this.zzf = executor;
    }

    private final zzfyx zzg(zzffh zzffhVar, zzfai zzfaiVar) {
        zzdch zzdchVar = zzffhVar.zza;
        this.zze = zzdchVar;
        if (zzffhVar.zzc != null) {
            if (zzdchVar.zzf() != null) {
                zzffhVar.zzc.zzo().zzbL(zzffhVar.zza.zzf());
            }
            return zzfyo.zzi(zzffhVar.zzc);
        }
        zzdchVar.zzb().zzk(zzffhVar.zzb);
        return ((zzezx) this.zza).zzb(zzfaiVar, null, zzffhVar.zza);
    }

    @Override // com.google.android.gms.internal.ads.zzfah
    /* renamed from: zza */
    public final synchronized zzdch zzd() {
        return this.zze;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzfyx zzb(zzfai zzfaiVar, zzezm zzezmVar, zzfag zzfagVar, zzdch zzdchVar, zzezs zzezsVar) throws Exception {
        if (zzezsVar != null) {
            zzezm zzezmVar2 = new zzezm(zzezmVar.zza, zzezmVar.zzb, zzezmVar.zzc, zzezmVar.zzd, zzezmVar.zze, zzezmVar.zzf, zzezsVar.zza);
            if (zzezsVar.zzc != null) {
                this.zze = null;
                this.zzc.zze(zzezmVar2);
                return zzg(zzezsVar.zzc, zzfaiVar);
            }
            zzfyx zza = this.zzc.zza(zzezmVar2);
            if (zza != null) {
                this.zze = null;
                return zzfyo.zzn(zza, new zzfxv() { // from class: com.google.android.gms.internal.ads.zzezj
                    @Override // com.google.android.gms.internal.ads.zzfxv
                    public final zzfyx zza(Object obj) {
                        return zzezn.this.zze((zzffr) obj);
                    }
                }, this.zzf);
            }
            this.zzc.zze(zzezmVar2);
            zzfaiVar = new zzfai(zzfaiVar.zzb, zzezsVar.zzb);
        }
        zzfyx zzb = ((zzezx) this.zza).zzb(zzfaiVar, zzfagVar, zzdchVar);
        this.zze = zzdchVar;
        return zzb;
    }

    @Override // com.google.android.gms.internal.ads.zzfah
    public final /* bridge */ /* synthetic */ zzfyx zzc(zzfai zzfaiVar, zzfag zzfagVar, Object obj) {
        return zzf(zzfaiVar, zzfagVar, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzfyx zze(zzffr zzffrVar) throws Exception {
        zzfft zzfftVar;
        if (zzffrVar == null || zzffrVar.zza == null || (zzfftVar = zzffrVar.zzb) == null) {
            throw new zzeas(1, "Empty prefetch");
        }
        zzbfa zza = zzbfg.zza();
        zzbey zza2 = zzbez.zza();
        zza2.zzd(2);
        zza2.zzb(zzbfd.zzd());
        zza.zza(zza2);
        zzffrVar.zza.zza.zzb().zzc().zzi((zzbfg) zza.zzal());
        return zzg(zzffrVar.zza, ((zzezm) zzfftVar).zzb);
    }

    public final synchronized zzfyx zzf(final zzfai zzfaiVar, final zzfag zzfagVar, zzdch zzdchVar) {
        zzdcg zza = zzfagVar.zza(zzfaiVar.zzb);
        zza.zza(new zzezo(this.zzd));
        final zzdch zzdchVar2 = (zzdch) zza.zzh();
        zzdchVar2.zzg();
        zzdchVar2.zzg();
        com.google.android.gms.ads.internal.client.zzl zzlVar = zzdchVar2.zzg().zzd;
        if (zzlVar.zzs == null && zzlVar.zzx == null) {
            zzfdn zzg = zzdchVar2.zzg();
            final zzezm zzezmVar = new zzezm(zzfagVar, zzfaiVar, zzg.zzd, zzg.zzf, this.zzf, zzg.zzj, null);
            return zzfyo.zzn(zzfyf.zzv(((zzezt) this.zzb).zzb(zzfaiVar, zzfagVar, zzdchVar2)), new zzfxv() { // from class: com.google.android.gms.internal.ads.zzezk
                @Override // com.google.android.gms.internal.ads.zzfxv
                public final zzfyx zza(Object obj) {
                    return zzezn.this.zzb(zzfaiVar, zzezmVar, zzfagVar, zzdchVar2, (zzezs) obj);
                }
            }, this.zzf);
        }
        this.zze = zzdchVar2;
        return ((zzezx) this.zza).zzb(zzfaiVar, zzfagVar, zzdchVar2);
    }
}
