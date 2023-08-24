package com.google.android.gms.internal.ads;

import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfbv extends zzflz implements zzdem, zzddb, zzdcy, zzddo, zzdfj, zzfae, zzdkl {
    private final zzfeu zza;
    private final AtomicReference zzb = new AtomicReference();
    private final AtomicReference zzc = new AtomicReference();
    private final AtomicReference zzd = new AtomicReference();
    private final AtomicReference zze = new AtomicReference();
    private final AtomicReference zzf = new AtomicReference();
    private final AtomicReference zzg = new AtomicReference();
    private final AtomicReference zzh = new AtomicReference();

    public zzfbv(zzfeu zzfeuVar) {
        this.zza = zzfeuVar;
    }

    @Override // com.google.android.gms.internal.ads.zzddb
    public final void zza(final com.google.android.gms.ads.internal.client.zze zzeVar) {
        final int r0 = zzeVar.zza;
        zzezv.zza(this.zzc, new zzezu() { // from class: com.google.android.gms.internal.ads.zzfbb
            @Override // com.google.android.gms.internal.ads.zzezu
            public final void zza(Object obj) {
                ((zzccq) obj).zzf(com.google.android.gms.ads.internal.client.zze.this);
            }
        });
        zzezv.zza(this.zzc, new zzezu() { // from class: com.google.android.gms.internal.ads.zzfbc
            @Override // com.google.android.gms.internal.ads.zzezu
            public final void zza(Object obj) {
                ((zzccq) obj).zze(r0);
            }
        });
        zzezv.zza(this.zze, new zzezu() { // from class: com.google.android.gms.internal.ads.zzfbd
            @Override // com.google.android.gms.internal.ads.zzezu
            public final void zza(Object obj) {
                ((zzcbw) obj).zzg(r0);
            }
        });
    }

    public final void zzb(zzflz zzflzVar) {
        this.zzb.set(zzflzVar);
    }

    @Override // com.google.android.gms.internal.ads.zzfae
    public final void zzbL(zzfae zzfaeVar) {
        throw null;
    }

    @Override // com.google.android.gms.internal.ads.zzdcy
    public final void zzbv() {
        zzezv.zza(this.zze, new zzezu() { // from class: com.google.android.gms.internal.ads.zzfbg
            @Override // com.google.android.gms.internal.ads.zzezu
            public final void zza(Object obj) {
                ((zzcbw) obj).zzk();
            }
        });
    }

    public final void zzc(com.google.android.gms.ads.internal.client.zzde zzdeVar) {
        this.zzh.set(zzdeVar);
    }

    public final void zzd(zzccm zzccmVar) {
        this.zzd.set(zzccmVar);
    }

    public final void zze(zzccq zzccqVar) {
        this.zzc.set(zzccqVar);
    }

    @Deprecated
    public final void zzf(zzcbw zzcbwVar) {
        this.zze.set(zzcbwVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdfj
    public final void zzg(final com.google.android.gms.ads.internal.client.zzs zzsVar) {
        zzezv.zza(this.zzh, new zzezu() { // from class: com.google.android.gms.internal.ads.zzfba
            @Override // com.google.android.gms.internal.ads.zzezu
            public final void zza(Object obj) {
                ((com.google.android.gms.ads.internal.client.zzde) obj).zze(com.google.android.gms.ads.internal.client.zzs.this);
            }
        });
    }

    @Deprecated
    public final void zzh(zzcbr zzcbrVar) {
        this.zzg.set(zzcbrVar);
    }

    public final void zzi(zzccr zzccrVar) {
        this.zzf.set(zzccrVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdcy
    public final void zzj() {
        this.zza.zza();
        zzezv.zza(this.zzd, new zzezu() { // from class: com.google.android.gms.internal.ads.zzfbh
            @Override // com.google.android.gms.internal.ads.zzezu
            public final void zza(Object obj) {
                ((zzccm) obj).zzg();
            }
        });
        zzezv.zza(this.zze, new zzezu() { // from class: com.google.android.gms.internal.ads.zzfbi
            @Override // com.google.android.gms.internal.ads.zzezu
            public final void zza(Object obj) {
                ((zzcbw) obj).zzf();
            }
        });
    }

    @Override // com.google.android.gms.internal.ads.zzddo
    public final void zzk(final com.google.android.gms.ads.internal.client.zze zzeVar) {
        zzezv.zza(this.zzd, new zzezu() { // from class: com.google.android.gms.internal.ads.zzfbe
            @Override // com.google.android.gms.internal.ads.zzezu
            public final void zza(Object obj) {
                ((zzccm) obj).zzi(com.google.android.gms.ads.internal.client.zze.this);
            }
        });
        zzezv.zza(this.zzd, new zzezu() { // from class: com.google.android.gms.internal.ads.zzfbf
            @Override // com.google.android.gms.internal.ads.zzezu
            public final void zza(Object obj) {
                ((zzccm) obj).zzh(com.google.android.gms.ads.internal.client.zze.this.zza);
            }
        });
    }

    @Override // com.google.android.gms.internal.ads.zzdcy
    public final void zzm() {
        zzezv.zza(this.zze, new zzezu() { // from class: com.google.android.gms.internal.ads.zzfbq
            @Override // com.google.android.gms.internal.ads.zzezu
            public final void zza(Object obj) {
                ((zzcbw) obj).zzh();
            }
        });
    }

    @Override // com.google.android.gms.internal.ads.zzdem
    public final void zzn() {
        zzezv.zza(this.zzc, new zzezu() { // from class: com.google.android.gms.internal.ads.zzfbn
            @Override // com.google.android.gms.internal.ads.zzezu
            public final void zza(Object obj) {
                ((zzccq) obj).zzg();
            }
        });
        zzezv.zza(this.zze, new zzezu() { // from class: com.google.android.gms.internal.ads.zzfbo
            @Override // com.google.android.gms.internal.ads.zzezu
            public final void zza(Object obj) {
                ((zzcbw) obj).zzi();
            }
        });
    }

    @Override // com.google.android.gms.internal.ads.zzdcy
    public final void zzo() {
        zzezv.zza(this.zzd, new zzezu() { // from class: com.google.android.gms.internal.ads.zzfbj
            @Override // com.google.android.gms.internal.ads.zzezu
            public final void zza(Object obj) {
                ((zzccm) obj).zzj();
            }
        });
        zzezv.zza(this.zze, new zzezu() { // from class: com.google.android.gms.internal.ads.zzfbl
            @Override // com.google.android.gms.internal.ads.zzezu
            public final void zza(Object obj) {
                ((zzcbw) obj).zzj();
            }
        });
        zzezv.zza(this.zzd, new zzezu() { // from class: com.google.android.gms.internal.ads.zzfbm
            @Override // com.google.android.gms.internal.ads.zzezu
            public final void zza(Object obj) {
                ((zzccm) obj).zzf();
            }
        });
    }

    @Override // com.google.android.gms.internal.ads.zzdcy
    public final void zzp(final zzcbq zzcbqVar, final String str, final String str2) {
        zzezv.zza(this.zzd, new zzezu() { // from class: com.google.android.gms.internal.ads.zzfbr
            @Override // com.google.android.gms.internal.ads.zzezu
            public final void zza(Object obj) {
                zzcbq zzcbqVar2 = zzcbq.this;
                ((zzccm) obj).zzk(new zzcda(zzcbqVar2.zzc(), zzcbqVar2.zzb()));
            }
        });
        zzezv.zza(this.zzf, new zzezu() { // from class: com.google.android.gms.internal.ads.zzfbs
            @Override // com.google.android.gms.internal.ads.zzezu
            public final void zza(Object obj) {
                zzcbq zzcbqVar2 = zzcbq.this;
                ((zzccr) obj).zze(new zzcda(zzcbqVar2.zzc(), zzcbqVar2.zzb()), str, str2);
            }
        });
        zzezv.zza(this.zze, new zzezu() { // from class: com.google.android.gms.internal.ads.zzfbt
            @Override // com.google.android.gms.internal.ads.zzezu
            public final void zza(Object obj) {
                ((zzcbw) obj).zze(zzcbq.this);
            }
        });
        zzezv.zza(this.zzg, new zzezu() { // from class: com.google.android.gms.internal.ads.zzfbu
            @Override // com.google.android.gms.internal.ads.zzezu
            public final void zza(Object obj) {
                ((zzcbr) obj).zze(zzcbq.this, str, str2);
            }
        });
    }

    @Override // com.google.android.gms.internal.ads.zzdkl
    public final void zzq() {
        zzezv.zza(this.zzd, new zzezu() { // from class: com.google.android.gms.internal.ads.zzfaz
            @Override // com.google.android.gms.internal.ads.zzezu
            public final void zza(Object obj) {
                ((zzccm) obj).zze();
            }
        });
    }

    @Override // com.google.android.gms.internal.ads.zzdcy
    public final void zzr() {
        zzezv.zza(this.zze, new zzezu() { // from class: com.google.android.gms.internal.ads.zzfbk
            @Override // com.google.android.gms.internal.ads.zzezu
            public final void zza(Object obj) {
                ((zzcbw) obj).zzl();
            }
        });
    }

    @Override // com.google.android.gms.internal.ads.zzflz
    public final void zzv() {
        zzezv.zza(this.zzb, new zzezu() { // from class: com.google.android.gms.internal.ads.zzfbp
            @Override // com.google.android.gms.internal.ads.zzezu
            public final void zza(Object obj) {
                ((zzflz) obj).zzv();
            }
        });
    }
}
