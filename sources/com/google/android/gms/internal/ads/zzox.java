package com.google.android.gms.internal.ads;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzox implements zznt {
    final /* synthetic */ zzoy zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzox(zzoy zzoyVar, zzow zzowVar) {
        this.zza = zzoyVar;
    }

    @Override // com.google.android.gms.internal.ads.zznt
    public final void zza(Exception exc) {
        zzdu.zza("MediaCodecAudioRenderer", "Audio sink error", exc);
        zzoy.zzU(this.zza).zzb(exc);
    }

    @Override // com.google.android.gms.internal.ads.zznt
    public final void zzb() {
        zzoy zzoyVar = this.zza;
        if (zzoy.zzT(zzoyVar) != null) {
            zzoy.zzT(zzoyVar).zzb();
        }
    }
}
