package com.google.android.gms.internal.ads;

import android.media.MediaPlayer;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzchs implements Runnable {
    final /* synthetic */ MediaPlayer zza;
    final /* synthetic */ zzcia zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzchs(zzcia zzciaVar, MediaPlayer mediaPlayer) {
        this.zzb = zzciaVar;
        this.zza = mediaPlayer;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzcib zzcibVar;
        zzcib zzcibVar2;
        zzcia.zzl(this.zzb, this.zza);
        zzcia zzciaVar = this.zzb;
        zzcibVar = zzciaVar.zzq;
        if (zzcibVar != null) {
            zzcibVar2 = zzciaVar.zzq;
            zzcibVar2.zzf();
        }
    }
}
