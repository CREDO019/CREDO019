package com.google.android.gms.internal.ads;

import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzeyo implements zzddb, zzdex, zzfae, com.google.android.gms.ads.internal.overlay.zzo, zzdfj, zzddo, zzdkl {
    private final zzfeu zza;
    private final AtomicReference zzb = new AtomicReference();
    private final AtomicReference zzc = new AtomicReference();
    private final AtomicReference zzd = new AtomicReference();
    private final AtomicReference zze = new AtomicReference();
    private final AtomicReference zzf = new AtomicReference();
    private final AtomicReference zzg = new AtomicReference();
    private zzeyo zzh = null;

    public zzeyo(zzfeu zzfeuVar) {
        this.zza = zzfeuVar;
    }

    public static zzeyo zzi(zzeyo zzeyoVar) {
        zzeyo zzeyoVar2 = new zzeyo(zzeyoVar.zza);
        zzeyoVar2.zzh = zzeyoVar;
        return zzeyoVar2;
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzo
    public final void zzb() {
        zzeyo zzeyoVar = this.zzh;
        if (zzeyoVar != null) {
            zzeyoVar.zzb();
            return;
        }
        zzezv.zza(this.zzf, new zzezu() { // from class: com.google.android.gms.internal.ads.zzeyh
            @Override // com.google.android.gms.internal.ads.zzezu
            public final void zza(Object obj) {
                ((com.google.android.gms.ads.internal.overlay.zzo) obj).zzb();
            }
        });
        zzezv.zza(this.zzd, new zzezu() { // from class: com.google.android.gms.internal.ads.zzeyi
            @Override // com.google.android.gms.internal.ads.zzezu
            public final void zza(Object obj) {
                ((zzbdm) obj).zzf();
            }
        });
        zzezv.zza(this.zzd, new zzezu() { // from class: com.google.android.gms.internal.ads.zzeyj
            @Override // com.google.android.gms.internal.ads.zzezu
            public final void zza(Object obj) {
                ((zzbdm) obj).zze();
            }
        });
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzo
    public final void zzbC() {
        zzeyo zzeyoVar = this.zzh;
        if (zzeyoVar != null) {
            zzeyoVar.zzbC();
        } else {
            zzezv.zza(this.zzf, new zzezu() { // from class: com.google.android.gms.internal.ads.zzeye
                @Override // com.google.android.gms.internal.ads.zzezu
                public final void zza(Object obj) {
                    ((com.google.android.gms.ads.internal.overlay.zzo) obj).zzbC();
                }
            });
        }
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzo
    public final void zzbK() {
    }

    @Override // com.google.android.gms.internal.ads.zzfae
    public final void zzbL(zzfae zzfaeVar) {
        this.zzh = (zzeyo) zzfaeVar;
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzo
    public final void zzbr() {
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzo
    public final void zze() {
        zzeyo zzeyoVar = this.zzh;
        if (zzeyoVar != null) {
            zzeyoVar.zze();
        } else {
            zzezv.zza(this.zzf, new zzezu() { // from class: com.google.android.gms.internal.ads.zzeyl
                @Override // com.google.android.gms.internal.ads.zzezu
                public final void zza(Object obj) {
                    ((com.google.android.gms.ads.internal.overlay.zzo) obj).zze();
                }
            });
        }
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzo
    public final void zzf(final int r3) {
        zzeyo zzeyoVar = this.zzh;
        if (zzeyoVar != null) {
            zzeyoVar.zzf(r3);
        } else {
            zzezv.zza(this.zzf, new zzezu() { // from class: com.google.android.gms.internal.ads.zzeyd
                @Override // com.google.android.gms.internal.ads.zzezu
                public final void zza(Object obj) {
                    ((com.google.android.gms.ads.internal.overlay.zzo) obj).zzf(r3);
                }
            });
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdfj
    public final void zzg(final com.google.android.gms.ads.internal.client.zzs zzsVar) {
        zzeyo zzeyoVar = this.zzh;
        if (zzeyoVar != null) {
            zzeyoVar.zzg(zzsVar);
        } else {
            zzezv.zza(this.zzg, new zzezu() { // from class: com.google.android.gms.internal.ads.zzeyc
                @Override // com.google.android.gms.internal.ads.zzezu
                public final void zza(Object obj) {
                    ((com.google.android.gms.ads.internal.client.zzde) obj).zze(com.google.android.gms.ads.internal.client.zzs.this);
                }
            });
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdex
    public final void zzh() {
        zzeyo zzeyoVar = this.zzh;
        if (zzeyoVar != null) {
            zzeyoVar.zzh();
        } else {
            zzezv.zza(this.zze, new zzezu() { // from class: com.google.android.gms.internal.ads.zzeyn
                @Override // com.google.android.gms.internal.ads.zzezu
                public final void zza(Object obj) {
                    ((zzdex) obj).zzh();
                }
            });
        }
    }

    public final void zzj() {
        zzeyo zzeyoVar = this.zzh;
        if (zzeyoVar != null) {
            zzeyoVar.zzj();
            return;
        }
        this.zza.zza();
        zzezv.zza(this.zzc, new zzezu() { // from class: com.google.android.gms.internal.ads.zzeya
            @Override // com.google.android.gms.internal.ads.zzezu
            public final void zza(Object obj) {
                ((zzbdj) obj).zze();
            }
        });
        zzezv.zza(this.zzd, new zzezu() { // from class: com.google.android.gms.internal.ads.zzeyb
            @Override // com.google.android.gms.internal.ads.zzezu
            public final void zza(Object obj) {
                ((zzbdm) obj).zzc();
            }
        });
    }

    @Override // com.google.android.gms.internal.ads.zzddo
    public final void zzk(final com.google.android.gms.ads.internal.client.zze zzeVar) {
        zzeyo zzeyoVar = this.zzh;
        if (zzeyoVar != null) {
            zzeyoVar.zzk(zzeVar);
        } else {
            zzezv.zza(this.zzd, new zzezu() { // from class: com.google.android.gms.internal.ads.zzeyk
                @Override // com.google.android.gms.internal.ads.zzezu
                public final void zza(Object obj) {
                    ((zzbdm) obj).zzd(com.google.android.gms.ads.internal.client.zze.this);
                }
            });
        }
    }

    public final void zzn(zzdex zzdexVar) {
        this.zze.set(zzdexVar);
    }

    public final void zzo(com.google.android.gms.ads.internal.overlay.zzo zzoVar) {
        this.zzf.set(zzoVar);
    }

    public final void zzp(com.google.android.gms.ads.internal.client.zzde zzdeVar) {
        this.zzg.set(zzdeVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdkl
    public final void zzq() {
        zzeyo zzeyoVar = this.zzh;
        if (zzeyoVar != null) {
            zzeyoVar.zzq();
        } else {
            zzezv.zza(this.zzd, new zzezu() { // from class: com.google.android.gms.internal.ads.zzeym
                @Override // com.google.android.gms.internal.ads.zzezu
                public final void zza(Object obj) {
                    ((zzbdm) obj).zzb();
                }
            });
        }
    }

    public final void zzr(zzbdi zzbdiVar) {
        this.zzb.set(zzbdiVar);
    }

    public final void zzs(zzbdm zzbdmVar) {
        this.zzd.set(zzbdmVar);
    }

    public final void zzt(zzbdj zzbdjVar) {
        this.zzc.set(zzbdjVar);
    }

    public final void zzl(final zzbdf zzbdfVar) {
        zzeyo zzeyoVar = this.zzh;
        if (zzeyoVar != null) {
            zzeyoVar.zzl(zzbdfVar);
        } else {
            zzezv.zza(this.zzb, new zzezu() { // from class: com.google.android.gms.internal.ads.zzeyg
                @Override // com.google.android.gms.internal.ads.zzezu
                public final void zza(Object obj) {
                    ((zzbdi) obj).zzd(zzbdf.this);
                }
            });
        }
    }

    @Override // com.google.android.gms.internal.ads.zzddb
    public final void zza(final com.google.android.gms.ads.internal.client.zze zzeVar) {
        zzeyo zzeyoVar = this.zzh;
        if (zzeyoVar != null) {
            zzeyoVar.zza(zzeVar);
            return;
        }
        zzezv.zza(this.zzb, new zzezu() { // from class: com.google.android.gms.internal.ads.zzexz
            @Override // com.google.android.gms.internal.ads.zzezu
            public final void zza(Object obj) {
                ((zzbdi) obj).zzc(com.google.android.gms.ads.internal.client.zze.this);
            }
        });
        zzezv.zza(this.zzb, new zzezu() { // from class: com.google.android.gms.internal.ads.zzeyf
            @Override // com.google.android.gms.internal.ads.zzezu
            public final void zza(Object obj) {
                ((zzbdi) obj).zzb(com.google.android.gms.ads.internal.client.zze.this.zza);
            }
        });
    }
}
