package com.google.android.gms.ads.internal;

import android.os.Build;
import com.google.android.gms.ads.internal.overlay.zzw;
import com.google.android.gms.ads.internal.util.zzaa;
import com.google.android.gms.ads.internal.util.zzab;
import com.google.android.gms.ads.internal.util.zzaw;
import com.google.android.gms.ads.internal.util.zzbv;
import com.google.android.gms.ads.internal.util.zzbw;
import com.google.android.gms.ads.internal.util.zzcg;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.internal.ads.zzbcg;
import com.google.android.gms.internal.ads.zzbdt;
import com.google.android.gms.internal.ads.zzbei;
import com.google.android.gms.internal.ads.zzbje;
import com.google.android.gms.internal.ads.zzbsl;
import com.google.android.gms.internal.ads.zzbtw;
import com.google.android.gms.internal.ads.zzbvb;
import com.google.android.gms.internal.ads.zzbyu;
import com.google.android.gms.internal.ads.zzcbi;
import com.google.android.gms.internal.ads.zzces;
import com.google.android.gms.internal.ads.zzcfw;
import com.google.android.gms.internal.ads.zzchg;
import com.google.android.gms.internal.ads.zzchn;
import com.google.android.gms.internal.ads.zzcks;
import com.google.android.gms.internal.ads.zzcmz;
import com.google.android.gms.internal.ads.zzegj;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzt {
    private static final zzt zza = new zzt();
    private final zzces zzA;
    private final zzcg zzB;
    private final zzcks zzC;
    private final zzchn zzD;
    private final com.google.android.gms.ads.internal.overlay.zza zzb;
    private final com.google.android.gms.ads.internal.overlay.zzm zzc;
    private final com.google.android.gms.ads.internal.util.zzs zzd;
    private final zzcmz zze;
    private final zzaa zzf;
    private final zzbcg zzg;
    private final zzcfw zzh;
    private final zzab zzi;
    private final zzbdt zzj;
    private final Clock zzk;
    private final zze zzl;
    private final zzbje zzm;
    private final zzaw zzn;
    private final zzcbi zzo;
    private final zzbsl zzp;
    private final zzchg zzq;
    private final zzbtw zzr;
    private final zzw zzs;
    private final zzbv zzt;
    private final com.google.android.gms.ads.internal.overlay.zzaa zzu;
    private final com.google.android.gms.ads.internal.overlay.zzab zzv;
    private final zzbvb zzw;
    private final zzbw zzx;
    private final zzbyu zzy;
    private final zzbei zzz;

    protected zzt() {
        com.google.android.gms.ads.internal.overlay.zza zzaVar = new com.google.android.gms.ads.internal.overlay.zza();
        com.google.android.gms.ads.internal.overlay.zzm zzmVar = new com.google.android.gms.ads.internal.overlay.zzm();
        com.google.android.gms.ads.internal.util.zzs zzsVar = new com.google.android.gms.ads.internal.util.zzs();
        zzcmz zzcmzVar = new zzcmz();
        zzaa zzm = zzaa.zzm(Build.VERSION.SDK_INT);
        zzbcg zzbcgVar = new zzbcg();
        zzcfw zzcfwVar = new zzcfw();
        zzab zzabVar = new zzab();
        zzbdt zzbdtVar = new zzbdt();
        Clock defaultClock = DefaultClock.getInstance();
        zze zzeVar = new zze();
        zzbje zzbjeVar = new zzbje();
        zzaw zzawVar = new zzaw();
        zzcbi zzcbiVar = new zzcbi();
        zzbsl zzbslVar = new zzbsl();
        zzchg zzchgVar = new zzchg();
        zzbtw zzbtwVar = new zzbtw();
        zzw zzwVar = new zzw();
        zzbv zzbvVar = new zzbv();
        com.google.android.gms.ads.internal.overlay.zzaa zzaaVar = new com.google.android.gms.ads.internal.overlay.zzaa();
        com.google.android.gms.ads.internal.overlay.zzab zzabVar2 = new com.google.android.gms.ads.internal.overlay.zzab();
        zzbvb zzbvbVar = new zzbvb();
        zzbw zzbwVar = new zzbw();
        zzegj zzegjVar = new zzegj();
        zzbei zzbeiVar = new zzbei();
        zzces zzcesVar = new zzces();
        zzcg zzcgVar = new zzcg();
        zzcks zzcksVar = new zzcks();
        zzchn zzchnVar = new zzchn();
        this.zzb = zzaVar;
        this.zzc = zzmVar;
        this.zzd = zzsVar;
        this.zze = zzcmzVar;
        this.zzf = zzm;
        this.zzg = zzbcgVar;
        this.zzh = zzcfwVar;
        this.zzi = zzabVar;
        this.zzj = zzbdtVar;
        this.zzk = defaultClock;
        this.zzl = zzeVar;
        this.zzm = zzbjeVar;
        this.zzn = zzawVar;
        this.zzo = zzcbiVar;
        this.zzp = zzbslVar;
        this.zzq = zzchgVar;
        this.zzr = zzbtwVar;
        this.zzt = zzbvVar;
        this.zzs = zzwVar;
        this.zzu = zzaaVar;
        this.zzv = zzabVar2;
        this.zzw = zzbvbVar;
        this.zzx = zzbwVar;
        this.zzy = zzegjVar;
        this.zzz = zzbeiVar;
        this.zzA = zzcesVar;
        this.zzB = zzcgVar;
        this.zzC = zzcksVar;
        this.zzD = zzchnVar;
    }

    public static zzcmz zzA() {
        return zza.zze;
    }

    public static Clock zzB() {
        return zza.zzk;
    }

    public static zze zza() {
        return zza.zzl;
    }

    public static zzbcg zzb() {
        return zza.zzg;
    }

    public static zzbdt zzc() {
        return zza.zzj;
    }

    public static zzbei zzd() {
        return zza.zzz;
    }

    public static zzbje zze() {
        return zza.zzm;
    }

    public static zzbtw zzf() {
        return zza.zzr;
    }

    public static zzbvb zzg() {
        return zza.zzw;
    }

    public static zzbyu zzh() {
        return zza.zzy;
    }

    public static com.google.android.gms.ads.internal.overlay.zza zzi() {
        return zza.zzb;
    }

    public static com.google.android.gms.ads.internal.overlay.zzm zzj() {
        return zza.zzc;
    }

    public static zzw zzk() {
        return zza.zzs;
    }

    public static com.google.android.gms.ads.internal.overlay.zzaa zzl() {
        return zza.zzu;
    }

    public static com.google.android.gms.ads.internal.overlay.zzab zzm() {
        return zza.zzv;
    }

    public static zzcbi zzn() {
        return zza.zzo;
    }

    public static zzces zzo() {
        return zza.zzA;
    }

    public static zzcfw zzp() {
        return zza.zzh;
    }

    public static com.google.android.gms.ads.internal.util.zzs zzq() {
        return zza.zzd;
    }

    public static zzaa zzr() {
        return zza.zzf;
    }

    public static zzab zzs() {
        return zza.zzi;
    }

    public static zzaw zzt() {
        return zza.zzn;
    }

    public static zzbv zzu() {
        return zza.zzt;
    }

    public static zzbw zzv() {
        return zza.zzx;
    }

    public static zzcg zzw() {
        return zza.zzB;
    }

    public static zzchg zzx() {
        return zza.zzq;
    }

    public static zzchn zzy() {
        return zza.zzD;
    }

    public static zzcks zzz() {
        return zza.zzC;
    }
}
