package com.google.android.gms.internal.ads;

import android.content.Context;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcfs extends com.google.android.gms.ads.internal.util.zzb {
    final /* synthetic */ zzcfw zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzcfs(zzcfw zzcfwVar) {
        this.zza = zzcfwVar;
    }

    @Override // com.google.android.gms.ads.internal.util.zzb
    public final void zza() {
        Context context;
        zzcgt zzcgtVar;
        Object obj;
        zzbjd zzbjdVar;
        zzcfw zzcfwVar = this.zza;
        context = zzcfwVar.zze;
        zzcgtVar = zzcfwVar.zzf;
        zzbjb zzbjbVar = new zzbjb(context, zzcgtVar.zza);
        obj = this.zza.zza;
        synchronized (obj) {
            try {
                com.google.android.gms.ads.internal.zzt.zze();
                zzbjdVar = this.zza.zzg;
                zzbje.zza(zzbjdVar, zzbjbVar);
            } catch (IllegalArgumentException e) {
                com.google.android.gms.ads.internal.util.zze.zzk("Cannot config CSI reporter.", e);
            }
        }
    }
}
