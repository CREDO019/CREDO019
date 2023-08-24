package com.google.android.gms.internal.ads;

import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzeob {
    private final zzdoz zza;
    private final zzeno zzb;
    private final zzddb zzc;

    public zzeob(zzdoz zzdozVar, zzfhz zzfhzVar) {
        this.zza = zzdozVar;
        final zzeno zzenoVar = new zzeno(zzfhzVar);
        this.zzb = zzenoVar;
        final zzbsg zzg = zzdozVar.zzg();
        this.zzc = new zzddb() { // from class: com.google.android.gms.internal.ads.zzeoa
            @Override // com.google.android.gms.internal.ads.zzddb
            public final void zza(com.google.android.gms.ads.internal.client.zze zzeVar) {
                zzeno zzenoVar2 = zzeno.this;
                zzbsg zzbsgVar = zzg;
                zzenoVar2.zza(zzeVar);
                if (zzbsgVar != null) {
                    try {
                        zzbsgVar.zzf(zzeVar);
                    } catch (RemoteException e) {
                        zzcgn.zzl("#007 Could not call remote method.", e);
                    }
                }
                if (zzbsgVar != null) {
                    try {
                        zzbsgVar.zze(zzeVar.zza);
                    } catch (RemoteException e2) {
                        zzcgn.zzl("#007 Could not call remote method.", e2);
                    }
                }
            }
        };
    }

    public final zzddb zza() {
        return this.zzc;
    }

    public final zzdem zzb() {
        return this.zzb;
    }

    public final zzdmw zzc() {
        return new zzdmw(this.zza, this.zzb.zzc());
    }

    public final zzeno zzd() {
        return this.zzb;
    }

    public final void zze(com.google.android.gms.ads.internal.client.zzbf zzbfVar) {
        this.zzb.zze(zzbfVar);
    }
}
