package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzemt extends com.google.android.gms.ads.internal.client.zzbk {
    private final zzenz zza;

    public zzemt(Context context, zzcok zzcokVar, zzfdl zzfdlVar, zzdoz zzdozVar, com.google.android.gms.ads.internal.client.zzbf zzbfVar) {
        zzeob zzeobVar = new zzeob(zzdozVar, zzcokVar.zzx());
        zzeobVar.zze(zzbfVar);
        this.zza = new zzenz(new zzeol(zzcokVar, context, zzeobVar, zzfdlVar), zzfdlVar.zzI());
    }

    @Override // com.google.android.gms.ads.internal.client.zzbl
    public final synchronized String zze() {
        return this.zza.zza();
    }

    @Override // com.google.android.gms.ads.internal.client.zzbl
    public final synchronized String zzf() {
        return this.zza.zzb();
    }

    @Override // com.google.android.gms.ads.internal.client.zzbl
    public final void zzg(com.google.android.gms.ads.internal.client.zzl zzlVar) throws RemoteException {
        this.zza.zzd(zzlVar, 1);
    }

    @Override // com.google.android.gms.ads.internal.client.zzbl
    public final synchronized void zzh(com.google.android.gms.ads.internal.client.zzl zzlVar, int r3) throws RemoteException {
        this.zza.zzd(zzlVar, r3);
    }

    @Override // com.google.android.gms.ads.internal.client.zzbl
    public final synchronized boolean zzi() throws RemoteException {
        return this.zza.zze();
    }
}
