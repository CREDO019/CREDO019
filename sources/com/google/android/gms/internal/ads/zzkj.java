package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Handler;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzkj {
    private final Context zza;
    private final Handler zzb;
    private final zzkf zzc;
    private final AudioManager zzd;
    private zzki zze;
    private int zzf;
    private int zzg;
    private boolean zzh;

    public zzkj(Context context, Handler handler, zzkf zzkfVar) {
        Context applicationContext = context.getApplicationContext();
        this.zza = applicationContext;
        this.zzb = handler;
        this.zzc = zzkfVar;
        AudioManager audioManager = (AudioManager) applicationContext.getSystemService("audio");
        zzdd.zzb(audioManager);
        this.zzd = audioManager;
        this.zzf = 3;
        this.zzg = zzg(audioManager, 3);
        this.zzh = zzi(audioManager, this.zzf);
        zzki zzkiVar = new zzki(this, null);
        try {
            zzel.zzA(applicationContext, zzkiVar, new IntentFilter("android.media.VOLUME_CHANGED_ACTION"));
            this.zze = zzkiVar;
        } catch (RuntimeException e) {
            zzdu.zzb("StreamVolumeManager", "Error registering stream volume receiver", e);
        }
    }

    public static /* bridge */ /* synthetic */ void zzd(zzkj zzkjVar) {
        zzkjVar.zzh();
    }

    private static int zzg(AudioManager audioManager, int r4) {
        try {
            return audioManager.getStreamVolume(r4);
        } catch (RuntimeException e) {
            zzdu.zzb("StreamVolumeManager", "Could not retrieve stream volume for stream type " + r4, e);
            return audioManager.getStreamMaxVolume(r4);
        }
    }

    public final void zzh() {
        zzdt zzdtVar;
        final int zzg = zzg(this.zzd, this.zzf);
        final boolean zzi = zzi(this.zzd, this.zzf);
        if (this.zzg == zzg && this.zzh == zzi) {
            return;
        }
        this.zzg = zzg;
        this.zzh = zzi;
        zzdtVar = ((zzin) this.zzc).zza.zzl;
        zzdtVar.zzd(30, new zzdq() { // from class: com.google.android.gms.internal.ads.zzii
            @Override // com.google.android.gms.internal.ads.zzdq
            public final void zza(Object obj) {
                int r0 = zzg;
                boolean z = zzi;
                int r2 = zzin.zzb;
                ((zzcd) obj).zzc(r0, z);
            }
        });
        zzdtVar.zzc();
    }

    private static boolean zzi(AudioManager audioManager, int r3) {
        if (zzel.zza >= 23) {
            return audioManager.isStreamMute(r3);
        }
        return zzg(audioManager, r3) == 0;
    }

    public final int zza() {
        return this.zzd.getStreamMaxVolume(this.zzf);
    }

    public final int zzb() {
        if (zzel.zza >= 28) {
            return this.zzd.getStreamMinVolume(this.zzf);
        }
        return 0;
    }

    public final void zze() {
        zzki zzkiVar = this.zze;
        if (zzkiVar != null) {
            try {
                this.zza.unregisterReceiver(zzkiVar);
            } catch (RuntimeException e) {
                zzdu.zzb("StreamVolumeManager", "Error unregistering stream volume receiver", e);
            }
            this.zze = null;
        }
    }

    public final void zzf(int r3) {
        zzkj zzkjVar;
        final zzt zzam;
        zzt zztVar;
        zzdt zzdtVar;
        if (this.zzf == 3) {
            return;
        }
        this.zzf = 3;
        zzh();
        zzin zzinVar = (zzin) this.zzc;
        zzkjVar = zzinVar.zza.zzz;
        zzam = zzir.zzam(zzkjVar);
        zztVar = zzinVar.zza.zzac;
        if (zzam.equals(zztVar)) {
            return;
        }
        zzinVar.zza.zzac = zzam;
        zzdtVar = zzinVar.zza.zzl;
        zzdtVar.zzd(29, new zzdq() { // from class: com.google.android.gms.internal.ads.zzij
            @Override // com.google.android.gms.internal.ads.zzdq
            public final void zza(Object obj) {
                zzt zztVar2 = zzt.this;
                int r1 = zzin.zzb;
                ((zzcd) obj).zzb(zztVar2);
            }
        });
        zzdtVar.zzc();
    }
}
