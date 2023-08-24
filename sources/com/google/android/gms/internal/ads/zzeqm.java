package com.google.android.gms.internal.ads;

import android.content.Context;
import android.media.AudioManager;
import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzeqm implements zzeun {
    private final zzfyy zza;
    private final Context zzb;

    public zzeqm(zzfyy zzfyyVar, Context context) {
        this.zza = zzfyyVar;
        this.zzb = context;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final int zza() {
        return 13;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final zzfyx zzb() {
        return this.zza.zzb(new Callable() { // from class: com.google.android.gms.internal.ads.zzeql
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return zzeqm.this.zzc();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzeqn zzc() throws Exception {
        int r6;
        AudioManager audioManager = (AudioManager) this.zzb.getSystemService("audio");
        int mode = audioManager.getMode();
        boolean isMusicActive = audioManager.isMusicActive();
        boolean isSpeakerphoneOn = audioManager.isSpeakerphoneOn();
        int streamVolume = audioManager.getStreamVolume(3);
        int r7 = -1;
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzix)).booleanValue()) {
            r6 = com.google.android.gms.ads.internal.zzt.zzr().zzi(audioManager);
            r7 = audioManager.getStreamMaxVolume(3);
        } else {
            r6 = -1;
        }
        return new zzeqn(mode, isMusicActive, isSpeakerphoneOn, streamVolume, r6, r7, audioManager.getRingerMode(), audioManager.getStreamVolume(2), com.google.android.gms.ads.internal.zzt.zzs().zza(), com.google.android.gms.ads.internal.zzt.zzs().zze());
    }
}
