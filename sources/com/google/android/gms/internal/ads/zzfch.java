package com.google.android.gms.internal.ads;

import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzfch extends zzflz {
    final /* synthetic */ com.google.android.gms.ads.internal.client.zzdb zza;
    final /* synthetic */ zzfcj zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfch(zzfcj zzfcjVar, com.google.android.gms.ads.internal.client.zzdb zzdbVar) {
        this.zzb = zzfcjVar;
        this.zza = zzdbVar;
    }

    @Override // com.google.android.gms.internal.ads.zzflz
    public final void zzv() {
        zzdua zzduaVar;
        zzduaVar = this.zzb.zzg;
        if (zzduaVar != null) {
            try {
                this.zza.zze();
            } catch (RemoteException e) {
                com.google.android.gms.ads.internal.util.zze.zzl("#007 Could not call remote method.", e);
            }
        }
    }
}
