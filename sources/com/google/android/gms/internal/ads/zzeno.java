package com.google.android.gms.internal.ads;

import android.util.Pair;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzeno implements zzdft, zzdem, zzddb, zzdds, com.google.android.gms.ads.internal.client.zza, zzdcy, zzdfj, zzasb, zzddo, zzdkl {
    private final zzfhz zzj;
    private final AtomicReference zzb = new AtomicReference();
    private final AtomicReference zzc = new AtomicReference();
    private final AtomicReference zzd = new AtomicReference();
    private final AtomicReference zze = new AtomicReference();
    private final AtomicReference zzf = new AtomicReference();
    private final AtomicBoolean zzg = new AtomicBoolean(true);
    private final AtomicBoolean zzh = new AtomicBoolean(false);
    private final AtomicBoolean zzi = new AtomicBoolean(false);
    final BlockingQueue zza = new ArrayBlockingQueue(((Integer) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzhw)).intValue());

    public zzeno(zzfhz zzfhzVar) {
        this.zzj = zzfhzVar;
    }

    private final void zzt() {
        if (this.zzh.get() && this.zzi.get()) {
            for (final Pair pair : this.zza) {
                zzezv.zza(this.zzc, new zzezu() { // from class: com.google.android.gms.internal.ads.zzenf
                    @Override // com.google.android.gms.internal.ads.zzezu
                    public final void zza(Object obj) {
                        Pair pair2 = pair;
                        ((com.google.android.gms.ads.internal.client.zzbz) obj).zzc((String) pair2.first, (String) pair2.second);
                    }
                });
            }
            this.zza.clear();
            this.zzg.set(false);
        }
    }

    @Override // com.google.android.gms.ads.internal.client.zza
    public final void onAdClicked() {
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zziq)).booleanValue()) {
            return;
        }
        zzezv.zza(this.zzb, zzeng.zza);
    }

    @Override // com.google.android.gms.internal.ads.zzddb
    public final void zza(final com.google.android.gms.ads.internal.client.zze zzeVar) {
        zzezv.zza(this.zzb, new zzezu() { // from class: com.google.android.gms.internal.ads.zzeni
            @Override // com.google.android.gms.internal.ads.zzezu
            public final void zza(Object obj) {
                ((com.google.android.gms.ads.internal.client.zzbf) obj).zzf(com.google.android.gms.ads.internal.client.zze.this);
            }
        });
        zzezv.zza(this.zzb, new zzezu() { // from class: com.google.android.gms.internal.ads.zzenj
            @Override // com.google.android.gms.internal.ads.zzezu
            public final void zza(Object obj) {
                ((com.google.android.gms.ads.internal.client.zzbf) obj).zze(com.google.android.gms.ads.internal.client.zze.this.zza);
            }
        });
        zzezv.zza(this.zze, new zzezu() { // from class: com.google.android.gms.internal.ads.zzenk
            @Override // com.google.android.gms.internal.ads.zzezu
            public final void zza(Object obj) {
                ((com.google.android.gms.ads.internal.client.zzbi) obj).zzb(com.google.android.gms.ads.internal.client.zze.this);
            }
        });
        this.zzg.set(false);
        this.zza.clear();
    }

    @Override // com.google.android.gms.internal.ads.zzdft
    public final void zzb(zzfde zzfdeVar) {
        this.zzg.set(true);
        this.zzi.set(false);
    }

    @Override // com.google.android.gms.internal.ads.zzdft
    public final void zzbE(zzcba zzcbaVar) {
    }

    @Override // com.google.android.gms.internal.ads.zzasb
    public final synchronized void zzbu(final String str, final String str2) {
        if (this.zzg.get()) {
            if (!this.zza.offer(new Pair(str, str2))) {
                com.google.android.gms.ads.internal.util.zze.zze("The queue for app events is full, dropping the new event.");
                zzfhz zzfhzVar = this.zzj;
                if (zzfhzVar != null) {
                    zzfhy zzb = zzfhy.zzb("dae_action");
                    zzb.zza("dae_name", str);
                    zzb.zza("dae_data", str2);
                    zzfhzVar.zzb(zzb);
                    return;
                }
            }
            return;
        }
        zzezv.zza(this.zzc, new zzezu() { // from class: com.google.android.gms.internal.ads.zzenb
            @Override // com.google.android.gms.internal.ads.zzezu
            public final void zza(Object obj) {
                ((com.google.android.gms.ads.internal.client.zzbz) obj).zzc(str, str2);
            }
        });
    }

    @Override // com.google.android.gms.internal.ads.zzdcy
    public final void zzbv() {
    }

    public final synchronized com.google.android.gms.ads.internal.client.zzbf zzc() {
        return (com.google.android.gms.ads.internal.client.zzbf) this.zzb.get();
    }

    public final synchronized com.google.android.gms.ads.internal.client.zzbz zzd() {
        return (com.google.android.gms.ads.internal.client.zzbz) this.zzc.get();
    }

    public final void zze(com.google.android.gms.ads.internal.client.zzbf zzbfVar) {
        this.zzb.set(zzbfVar);
    }

    public final void zzf(com.google.android.gms.ads.internal.client.zzbi zzbiVar) {
        this.zze.set(zzbiVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdfj
    public final void zzg(final com.google.android.gms.ads.internal.client.zzs zzsVar) {
        zzezv.zza(this.zzd, new zzezu() { // from class: com.google.android.gms.internal.ads.zzend
            @Override // com.google.android.gms.internal.ads.zzezu
            public final void zza(Object obj) {
                ((com.google.android.gms.ads.internal.client.zzde) obj).zze(com.google.android.gms.ads.internal.client.zzs.this);
            }
        });
    }

    public final void zzh(com.google.android.gms.ads.internal.client.zzde zzdeVar) {
        this.zzd.set(zzdeVar);
    }

    public final void zzi(com.google.android.gms.ads.internal.client.zzbz zzbzVar) {
        this.zzc.set(zzbzVar);
        this.zzh.set(true);
        zzt();
    }

    @Override // com.google.android.gms.internal.ads.zzdcy
    public final void zzj() {
        zzezv.zza(this.zzb, new zzezu() { // from class: com.google.android.gms.internal.ads.zzenn
            @Override // com.google.android.gms.internal.ads.zzezu
            public final void zza(Object obj) {
                ((com.google.android.gms.ads.internal.client.zzbf) obj).zzd();
            }
        });
        zzezv.zza(this.zzf, new zzezu() { // from class: com.google.android.gms.internal.ads.zzemx
            @Override // com.google.android.gms.internal.ads.zzezu
            public final void zza(Object obj) {
                ((com.google.android.gms.ads.internal.client.zzcg) obj).zzc();
            }
        });
    }

    @Override // com.google.android.gms.internal.ads.zzddo
    public final void zzk(final com.google.android.gms.ads.internal.client.zze zzeVar) {
        zzezv.zza(this.zzf, new zzezu() { // from class: com.google.android.gms.internal.ads.zzenc
            @Override // com.google.android.gms.internal.ads.zzezu
            public final void zza(Object obj) {
                ((com.google.android.gms.ads.internal.client.zzcg) obj).zzd(com.google.android.gms.ads.internal.client.zze.this);
            }
        });
    }

    @Override // com.google.android.gms.internal.ads.zzdds
    public final void zzl() {
        zzezv.zza(this.zzb, new zzezu() { // from class: com.google.android.gms.internal.ads.zzemw
            @Override // com.google.android.gms.internal.ads.zzezu
            public final void zza(Object obj) {
                ((com.google.android.gms.ads.internal.client.zzbf) obj).zzg();
            }
        });
    }

    @Override // com.google.android.gms.internal.ads.zzdcy
    public final void zzm() {
        zzezv.zza(this.zzb, new zzezu() { // from class: com.google.android.gms.internal.ads.zzene
            @Override // com.google.android.gms.internal.ads.zzezu
            public final void zza(Object obj) {
                ((com.google.android.gms.ads.internal.client.zzbf) obj).zzh();
            }
        });
    }

    @Override // com.google.android.gms.internal.ads.zzdem
    public final synchronized void zzn() {
        zzezv.zza(this.zzb, new zzezu() { // from class: com.google.android.gms.internal.ads.zzenl
            @Override // com.google.android.gms.internal.ads.zzezu
            public final void zza(Object obj) {
                ((com.google.android.gms.ads.internal.client.zzbf) obj).zzi();
            }
        });
        zzezv.zza(this.zze, new zzezu() { // from class: com.google.android.gms.internal.ads.zzenm
            @Override // com.google.android.gms.internal.ads.zzezu
            public final void zza(Object obj) {
                ((com.google.android.gms.ads.internal.client.zzbi) obj).zzc();
            }
        });
        this.zzi.set(true);
        zzt();
    }

    @Override // com.google.android.gms.internal.ads.zzdcy
    public final void zzo() {
        zzezv.zza(this.zzb, new zzezu() { // from class: com.google.android.gms.internal.ads.zzemy
            @Override // com.google.android.gms.internal.ads.zzezu
            public final void zza(Object obj) {
                ((com.google.android.gms.ads.internal.client.zzbf) obj).zzj();
            }
        });
        zzezv.zza(this.zzf, new zzezu() { // from class: com.google.android.gms.internal.ads.zzemz
            @Override // com.google.android.gms.internal.ads.zzezu
            public final void zza(Object obj) {
                ((com.google.android.gms.ads.internal.client.zzcg) obj).zzf();
            }
        });
        zzezv.zza(this.zzf, new zzezu() { // from class: com.google.android.gms.internal.ads.zzena
            @Override // com.google.android.gms.internal.ads.zzezu
            public final void zza(Object obj) {
                ((com.google.android.gms.ads.internal.client.zzcg) obj).zze();
            }
        });
    }

    @Override // com.google.android.gms.internal.ads.zzdcy
    public final void zzp(zzcbq zzcbqVar, String str, String str2) {
    }

    @Override // com.google.android.gms.internal.ads.zzdkl
    public final void zzq() {
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zziq)).booleanValue()) {
            zzezv.zza(this.zzb, zzeng.zza);
        }
        zzezv.zza(this.zzf, new zzezu() { // from class: com.google.android.gms.internal.ads.zzenh
            @Override // com.google.android.gms.internal.ads.zzezu
            public final void zza(Object obj) {
                ((com.google.android.gms.ads.internal.client.zzcg) obj).zzb();
            }
        });
    }

    @Override // com.google.android.gms.internal.ads.zzdcy
    public final void zzr() {
    }

    public final void zzs(com.google.android.gms.ads.internal.client.zzcg zzcgVar) {
        this.zzf.set(zzcgVar);
    }
}
