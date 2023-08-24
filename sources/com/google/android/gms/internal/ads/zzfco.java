package com.google.android.gms.internal.ads;

import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzfco extends zzflz {
    final /* synthetic */ com.google.android.gms.ads.internal.client.zzbw zza;
    final /* synthetic */ zzfcp zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfco(zzfcp zzfcpVar, com.google.android.gms.ads.internal.client.zzbw zzbwVar) {
        this.zzb = zzfcpVar;
        this.zza = zzbwVar;
    }

    @Override // com.google.android.gms.internal.ads.zzflz
    public final void zzv() {
        zzdua zzduaVar;
        zzduaVar = this.zzb.zzd;
        if (zzduaVar != null) {
            try {
                this.zza.zze();
            } catch (RemoteException e) {
                com.google.android.gms.ads.internal.util.zze.zzl("#007 Could not call remote method.", e);
            }
        }
    }
}
