package com.google.android.gms.internal.ads;

import android.media.AudioManager;
import android.os.Handler;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgm implements AudioManager.OnAudioFocusChangeListener {
    final /* synthetic */ zzgo zza;
    private final Handler zzb;

    public zzgm(zzgo zzgoVar, Handler handler) {
        this.zza = zzgoVar;
        this.zzb = handler;
    }

    @Override // android.media.AudioManager.OnAudioFocusChangeListener
    public final void onAudioFocusChange(final int r3) {
        this.zzb.post(new Runnable() { // from class: com.google.android.gms.internal.ads.zzgl
            @Override // java.lang.Runnable
            public final void run() {
                zzgm zzgmVar = zzgm.this;
                zzgo.zzc(zzgmVar.zza, r3);
            }
        });
    }
}
