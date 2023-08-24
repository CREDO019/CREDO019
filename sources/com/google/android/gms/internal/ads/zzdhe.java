package com.google.android.gms.internal.ads;

import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdhe extends zzflz implements zzdcy, com.google.android.gms.ads.internal.client.zza, zzasb, zzdfj, zzdds, zzdex, com.google.android.gms.ads.internal.overlay.zzo, zzddo, zzdkl {
    private final zzdhc zza = new zzdhc(this, null);
    @Nullable
    private zzeno zzb;
    @Nullable
    private zzens zzc;
    @Nullable
    private zzeyo zzd;
    @Nullable
    private zzfbv zze;

    public static /* bridge */ /* synthetic */ void zzn(zzdhe zzdheVar, zzeno zzenoVar) {
        zzdheVar.zzb = zzenoVar;
    }

    public static /* bridge */ /* synthetic */ void zzs(zzdhe zzdheVar, zzeyo zzeyoVar) {
        zzdheVar.zzd = zzeyoVar;
    }

    public static /* bridge */ /* synthetic */ void zzt(zzdhe zzdheVar, zzens zzensVar) {
        zzdheVar.zzc = zzensVar;
    }

    public static /* bridge */ /* synthetic */ void zzu(zzdhe zzdheVar, zzfbv zzfbvVar) {
        zzdheVar.zze = zzfbvVar;
    }

    private static void zzw(Object obj, zzdhd zzdhdVar) {
        if (obj != null) {
            zzdhdVar.zza(obj);
        }
    }

    @Override // com.google.android.gms.ads.internal.client.zza
    public final void onAdClicked() {
        zzw(this.zzb, new zzdhd() { // from class: com.google.android.gms.internal.ads.zzdgs
            @Override // com.google.android.gms.internal.ads.zzdhd
            public final void zza(Object obj) {
                ((zzeno) obj).onAdClicked();
            }
        });
        zzw(this.zzc, new zzdhd() { // from class: com.google.android.gms.internal.ads.zzdgt
            @Override // com.google.android.gms.internal.ads.zzdhd
            public final void zza(Object obj) {
                ((zzens) obj).onAdClicked();
            }
        });
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzo
    public final void zzb() {
        zzw(this.zzd, new zzdhd() { // from class: com.google.android.gms.internal.ads.zzdgy
            @Override // com.google.android.gms.internal.ads.zzdhd
            public final void zza(Object obj) {
                ((zzeyo) obj).zzb();
            }
        });
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzo
    public final void zzbC() {
        zzw(this.zzd, new zzdhd() { // from class: com.google.android.gms.internal.ads.zzdgb
            @Override // com.google.android.gms.internal.ads.zzdhd
            public final void zza(Object obj) {
                ((zzeyo) obj).zzbC();
            }
        });
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzo
    public final void zzbK() {
        zzw(this.zzd, new zzdhd() { // from class: com.google.android.gms.internal.ads.zzdgr
            @Override // com.google.android.gms.internal.ads.zzdhd
            public final void zza(Object obj) {
                zzeyo zzeyoVar = (zzeyo) obj;
            }
        });
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzo
    public final void zzbr() {
        zzw(this.zzd, new zzdhd() { // from class: com.google.android.gms.internal.ads.zzdfy
            @Override // com.google.android.gms.internal.ads.zzdhd
            public final void zza(Object obj) {
                zzeyo zzeyoVar = (zzeyo) obj;
            }
        });
    }

    @Override // com.google.android.gms.internal.ads.zzasb
    public final void zzbu(final String str, final String str2) {
        zzw(this.zzb, new zzdhd() { // from class: com.google.android.gms.internal.ads.zzdfv
            @Override // com.google.android.gms.internal.ads.zzdhd
            public final void zza(Object obj) {
                ((zzeno) obj).zzbu(str, str2);
            }
        });
    }

    @Override // com.google.android.gms.internal.ads.zzdcy
    public final void zzbv() {
        zzw(this.zzb, new zzdhd() { // from class: com.google.android.gms.internal.ads.zzdfw
            @Override // com.google.android.gms.internal.ads.zzdhd
            public final void zza(Object obj) {
                zzeno zzenoVar = (zzeno) obj;
            }
        });
        zzw(this.zze, new zzdhd() { // from class: com.google.android.gms.internal.ads.zzdfx
            @Override // com.google.android.gms.internal.ads.zzdhd
            public final void zza(Object obj) {
                ((zzfbv) obj).zzbv();
            }
        });
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzo
    public final void zze() {
        zzw(this.zzd, new zzdhd() { // from class: com.google.android.gms.internal.ads.zzdfu
            @Override // com.google.android.gms.internal.ads.zzdhd
            public final void zza(Object obj) {
                ((zzeyo) obj).zze();
            }
        });
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzo
    public final void zzf(final int r3) {
        zzw(this.zzd, new zzdhd() { // from class: com.google.android.gms.internal.ads.zzdgp
            @Override // com.google.android.gms.internal.ads.zzdhd
            public final void zza(Object obj) {
                ((zzeyo) obj).zzf(r3);
            }
        });
    }

    @Override // com.google.android.gms.internal.ads.zzdfj
    public final void zzg(final com.google.android.gms.ads.internal.client.zzs zzsVar) {
        zzw(this.zzb, new zzdhd() { // from class: com.google.android.gms.internal.ads.zzdgv
            @Override // com.google.android.gms.internal.ads.zzdhd
            public final void zza(Object obj) {
                ((zzeno) obj).zzg(com.google.android.gms.ads.internal.client.zzs.this);
            }
        });
        zzw(this.zze, new zzdhd() { // from class: com.google.android.gms.internal.ads.zzdgw
            @Override // com.google.android.gms.internal.ads.zzdhd
            public final void zza(Object obj) {
                ((zzfbv) obj).zzg(com.google.android.gms.ads.internal.client.zzs.this);
            }
        });
        zzw(this.zzd, new zzdhd() { // from class: com.google.android.gms.internal.ads.zzdgx
            @Override // com.google.android.gms.internal.ads.zzdhd
            public final void zza(Object obj) {
                ((zzeyo) obj).zzg(com.google.android.gms.ads.internal.client.zzs.this);
            }
        });
    }

    @Override // com.google.android.gms.internal.ads.zzdex
    public final void zzh() {
        zzw(this.zzd, new zzdhd() { // from class: com.google.android.gms.internal.ads.zzdgh
            @Override // com.google.android.gms.internal.ads.zzdhd
            public final void zza(Object obj) {
                ((zzeyo) obj).zzh();
            }
        });
    }

    public final zzdhc zzi() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.ads.zzdcy
    public final void zzj() {
        zzw(this.zzb, new zzdhd() { // from class: com.google.android.gms.internal.ads.zzdgn
            @Override // com.google.android.gms.internal.ads.zzdhd
            public final void zza(Object obj) {
                ((zzeno) obj).zzj();
            }
        });
        zzw(this.zze, new zzdhd() { // from class: com.google.android.gms.internal.ads.zzdgo
            @Override // com.google.android.gms.internal.ads.zzdhd
            public final void zza(Object obj) {
                ((zzfbv) obj).zzj();
            }
        });
    }

    @Override // com.google.android.gms.internal.ads.zzddo
    public final void zzk(final com.google.android.gms.ads.internal.client.zze zzeVar) {
        zzw(this.zze, new zzdhd() { // from class: com.google.android.gms.internal.ads.zzdgc
            @Override // com.google.android.gms.internal.ads.zzdhd
            public final void zza(Object obj) {
                ((zzfbv) obj).zzk(com.google.android.gms.ads.internal.client.zze.this);
            }
        });
        zzw(this.zzb, new zzdhd() { // from class: com.google.android.gms.internal.ads.zzdgd
            @Override // com.google.android.gms.internal.ads.zzdhd
            public final void zza(Object obj) {
                ((zzeno) obj).zzk(com.google.android.gms.ads.internal.client.zze.this);
            }
        });
    }

    @Override // com.google.android.gms.internal.ads.zzdds
    public final void zzl() {
        zzw(this.zzb, new zzdhd() { // from class: com.google.android.gms.internal.ads.zzdgf
            @Override // com.google.android.gms.internal.ads.zzdhd
            public final void zza(Object obj) {
                ((zzeno) obj).zzl();
            }
        });
    }

    @Override // com.google.android.gms.internal.ads.zzdcy
    public final void zzm() {
        zzw(this.zzb, new zzdhd() { // from class: com.google.android.gms.internal.ads.zzdgq
            @Override // com.google.android.gms.internal.ads.zzdhd
            public final void zza(Object obj) {
                ((zzeno) obj).zzm();
            }
        });
        zzw(this.zze, new zzdhd() { // from class: com.google.android.gms.internal.ads.zzdgu
            @Override // com.google.android.gms.internal.ads.zzdhd
            public final void zza(Object obj) {
                ((zzfbv) obj).zzm();
            }
        });
    }

    @Override // com.google.android.gms.internal.ads.zzdcy
    public final void zzo() {
        zzw(this.zzb, new zzdhd() { // from class: com.google.android.gms.internal.ads.zzdgz
            @Override // com.google.android.gms.internal.ads.zzdhd
            public final void zza(Object obj) {
                ((zzeno) obj).zzo();
            }
        });
        zzw(this.zze, new zzdhd() { // from class: com.google.android.gms.internal.ads.zzdha
            @Override // com.google.android.gms.internal.ads.zzdhd
            public final void zza(Object obj) {
                ((zzfbv) obj).zzo();
            }
        });
    }

    @Override // com.google.android.gms.internal.ads.zzdcy
    public final void zzp(final zzcbq zzcbqVar, final String str, final String str2) {
        zzw(this.zzb, new zzdhd() { // from class: com.google.android.gms.internal.ads.zzdge
            @Override // com.google.android.gms.internal.ads.zzdhd
            public final void zza(Object obj) {
                zzeno zzenoVar = (zzeno) obj;
            }
        });
        zzw(this.zze, new zzdhd() { // from class: com.google.android.gms.internal.ads.zzdgg
            @Override // com.google.android.gms.internal.ads.zzdhd
            public final void zza(Object obj) {
                ((zzfbv) obj).zzp(zzcbq.this, str, str2);
            }
        });
    }

    @Override // com.google.android.gms.internal.ads.zzdkl
    public final void zzq() {
        zzw(this.zzb, new zzdhd() { // from class: com.google.android.gms.internal.ads.zzdgi
            @Override // com.google.android.gms.internal.ads.zzdhd
            public final void zza(Object obj) {
                ((zzeno) obj).zzq();
            }
        });
        zzw(this.zzc, new zzdhd() { // from class: com.google.android.gms.internal.ads.zzdgj
            @Override // com.google.android.gms.internal.ads.zzdhd
            public final void zza(Object obj) {
                ((zzens) obj).zzq();
            }
        });
        zzw(this.zze, new zzdhd() { // from class: com.google.android.gms.internal.ads.zzdgk
            @Override // com.google.android.gms.internal.ads.zzdhd
            public final void zza(Object obj) {
                ((zzfbv) obj).zzq();
            }
        });
        zzw(this.zzd, new zzdhd() { // from class: com.google.android.gms.internal.ads.zzdgl
            @Override // com.google.android.gms.internal.ads.zzdhd
            public final void zza(Object obj) {
                ((zzeyo) obj).zzq();
            }
        });
    }

    @Override // com.google.android.gms.internal.ads.zzdcy
    public final void zzr() {
        zzw(this.zzb, new zzdhd() { // from class: com.google.android.gms.internal.ads.zzdfz
            @Override // com.google.android.gms.internal.ads.zzdhd
            public final void zza(Object obj) {
                zzeno zzenoVar = (zzeno) obj;
            }
        });
        zzw(this.zze, new zzdhd() { // from class: com.google.android.gms.internal.ads.zzdga
            @Override // com.google.android.gms.internal.ads.zzdhd
            public final void zza(Object obj) {
                ((zzfbv) obj).zzr();
            }
        });
    }

    @Override // com.google.android.gms.internal.ads.zzflz
    public final void zzv() {
        zzw(this.zze, new zzdhd() { // from class: com.google.android.gms.internal.ads.zzdgm
            @Override // com.google.android.gms.internal.ads.zzdhd
            public final void zza(Object obj) {
                ((zzfbv) obj).zzv();
            }
        });
    }
}
