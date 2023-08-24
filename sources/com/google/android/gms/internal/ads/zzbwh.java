package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.mediation.Adapter;
import com.google.android.gms.dynamic.ObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbwh extends zzbvk {
    private final Adapter zza;
    private final zzccb zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbwh(Adapter adapter, zzccb zzccbVar) {
        this.zza = adapter;
        this.zzb = zzccbVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbvl
    public final void zze() throws RemoteException {
        zzccb zzccbVar = this.zzb;
        if (zzccbVar != null) {
            zzccbVar.zze(ObjectWrapper.wrap(this.zza));
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbvl
    public final void zzf() throws RemoteException {
        zzccb zzccbVar = this.zzb;
        if (zzccbVar != null) {
            zzccbVar.zzf(ObjectWrapper.wrap(this.zza));
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbvl
    public final void zzg(int r3) throws RemoteException {
        zzccb zzccbVar = this.zzb;
        if (zzccbVar != null) {
            zzccbVar.zzg(ObjectWrapper.wrap(this.zza), r3);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbvl
    public final void zzh(com.google.android.gms.ads.internal.client.zze zzeVar) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzbvl
    public final void zzi(int r1, String str) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzbvl
    public final void zzj(int r1) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzbvl
    public final void zzk(com.google.android.gms.ads.internal.client.zze zzeVar) {
    }

    @Override // com.google.android.gms.internal.ads.zzbvl
    public final void zzl(String str) {
    }

    @Override // com.google.android.gms.internal.ads.zzbvl
    public final void zzm() throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzbvl
    public final void zzn() throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzbvl
    public final void zzo() throws RemoteException {
        zzccb zzccbVar = this.zzb;
        if (zzccbVar != null) {
            zzccbVar.zzi(ObjectWrapper.wrap(this.zza));
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbvl
    public final void zzp() throws RemoteException {
        zzccb zzccbVar = this.zzb;
        if (zzccbVar != null) {
            zzccbVar.zzj(ObjectWrapper.wrap(this.zza));
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbvl
    public final void zzq(String str, String str2) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzbvl
    public final void zzr(zzbmu zzbmuVar, String str) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzbvl
    public final void zzs(zzccc zzcccVar) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzbvl
    public final void zzt(zzccg zzccgVar) throws RemoteException {
        zzccb zzccbVar = this.zzb;
        if (zzccbVar != null) {
            zzccbVar.zzm(ObjectWrapper.wrap(this.zza), new zzccc(zzccgVar.zzf(), zzccgVar.zze()));
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbvl
    public final void zzu() throws RemoteException {
        zzccb zzccbVar = this.zzb;
        if (zzccbVar != null) {
            zzccbVar.zzn(ObjectWrapper.wrap(this.zza));
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbvl
    public final void zzv() throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzbvl
    public final void zzw() throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzbvl
    public final void zzx() throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzbvl
    public final void zzy() throws RemoteException {
        zzccb zzccbVar = this.zzb;
        if (zzccbVar != null) {
            zzccbVar.zzo(ObjectWrapper.wrap(this.zza));
        }
    }
}
