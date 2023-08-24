package com.google.android.gms.internal.ads;

import android.media.AudioTrack;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzop extends AudioTrack.StreamEventCallback {
    final /* synthetic */ zzos zza;
    final /* synthetic */ zzoq zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzop(zzoq zzoqVar, zzos zzosVar) {
        this.zzb = zzoqVar;
        this.zza = zzosVar;
    }

    @Override // android.media.AudioTrack.StreamEventCallback
    public final void onDataRequest(AudioTrack audioTrack, int r2) {
        AudioTrack audioTrack2;
        zznt zzntVar;
        boolean z;
        zznt zzntVar2;
        audioTrack2 = this.zzb.zza.zzq;
        zzdd.zzf(audioTrack == audioTrack2);
        zzos zzosVar = this.zzb.zza;
        zzntVar = zzosVar.zzn;
        if (zzntVar != null) {
            z = zzosVar.zzO;
            if (z) {
                zzntVar2 = zzosVar.zzn;
                zzntVar2.zzb();
            }
        }
    }

    @Override // android.media.AudioTrack.StreamEventCallback
    public final void onTearDown(AudioTrack audioTrack) {
        AudioTrack audioTrack2;
        zznt zzntVar;
        boolean z;
        zznt zzntVar2;
        audioTrack2 = this.zzb.zza.zzq;
        zzdd.zzf(audioTrack == audioTrack2);
        zzos zzosVar = this.zzb.zza;
        zzntVar = zzosVar.zzn;
        if (zzntVar != null) {
            z = zzosVar.zzO;
            if (z) {
                zzntVar2 = zzosVar.zzn;
                zzntVar2.zzb();
            }
        }
    }
}
