package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfcj extends zzcci {
    private final zzfcf zza;
    private final zzfbv zzb;
    private final String zzc;
    private final zzfdf zzd;
    private final Context zze;
    private final zzcgt zzf;
    private zzdua zzg;
    private boolean zzh = ((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzaA)).booleanValue();

    public zzfcj(String str, zzfcf zzfcfVar, Context context, zzfbv zzfbvVar, zzfdf zzfdfVar, zzcgt zzcgtVar) {
        this.zzc = str;
        this.zza = zzfcfVar;
        this.zzb = zzfbvVar;
        this.zzd = zzfdfVar;
        this.zze = context;
        this.zzf = zzcgtVar;
    }

    private final synchronized void zzu(com.google.android.gms.ads.internal.client.zzl zzlVar, zzccq zzccqVar, int r7) throws RemoteException {
        boolean z = false;
        if (((Boolean) zzbkm.zzl.zze()).booleanValue()) {
            if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zziG)).booleanValue()) {
                z = true;
            }
        }
        if (this.zzf.zzc < ((Integer) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zziH)).intValue() || !z) {
            Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        }
        this.zzb.zze(zzccqVar);
        com.google.android.gms.ads.internal.zzt.zzq();
        if (com.google.android.gms.ads.internal.util.zzs.zzD(this.zze) && zzlVar.zzs == null) {
            com.google.android.gms.ads.internal.util.zze.zzg("Failed to load the ad because app ID is missing.");
            this.zzb.zza(zzfem.zzd(4, null, null));
            return;
        }
        if (this.zzg != null) {
            return;
        }
        zzfbx zzfbxVar = new zzfbx(null);
        this.zza.zzj(r7);
        this.zza.zzb(zzlVar, this.zzc, zzfbxVar, new zzfci(this));
    }

    @Override // com.google.android.gms.internal.ads.zzccj
    public final Bundle zzb() {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzdua zzduaVar = this.zzg;
        return zzduaVar != null ? zzduaVar.zza() : new Bundle();
    }

    @Override // com.google.android.gms.internal.ads.zzccj
    public final com.google.android.gms.ads.internal.client.zzdh zzc() {
        zzdua zzduaVar;
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzfN)).booleanValue() && (zzduaVar = this.zzg) != null) {
            return zzduaVar.zzl();
        }
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzccj
    public final zzccg zzd() {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzdua zzduaVar = this.zzg;
        if (zzduaVar != null) {
            return zzduaVar.zzc();
        }
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzccj
    public final synchronized String zze() throws RemoteException {
        zzdua zzduaVar = this.zzg;
        if (zzduaVar == null || zzduaVar.zzl() == null) {
            return null;
        }
        return zzduaVar.zzl().zzg();
    }

    @Override // com.google.android.gms.internal.ads.zzccj
    public final synchronized void zzf(com.google.android.gms.ads.internal.client.zzl zzlVar, zzccq zzccqVar) throws RemoteException {
        zzu(zzlVar, zzccqVar, 2);
    }

    @Override // com.google.android.gms.internal.ads.zzccj
    public final synchronized void zzg(com.google.android.gms.ads.internal.client.zzl zzlVar, zzccq zzccqVar) throws RemoteException {
        zzu(zzlVar, zzccqVar, 3);
    }

    @Override // com.google.android.gms.internal.ads.zzccj
    public final synchronized void zzh(boolean z) {
        Preconditions.checkMainThread("setImmersiveMode must be called on the main UI thread.");
        this.zzh = z;
    }

    @Override // com.google.android.gms.internal.ads.zzccj
    public final void zzi(com.google.android.gms.ads.internal.client.zzdb zzdbVar) {
        if (zzdbVar == null) {
            this.zzb.zzb(null);
        } else {
            this.zzb.zzb(new zzfch(this, zzdbVar));
        }
    }

    @Override // com.google.android.gms.internal.ads.zzccj
    public final void zzj(com.google.android.gms.ads.internal.client.zzde zzdeVar) {
        Preconditions.checkMainThread("setOnPaidEventListener must be called on the main UI thread.");
        this.zzb.zzc(zzdeVar);
    }

    @Override // com.google.android.gms.internal.ads.zzccj
    public final void zzk(zzccm zzccmVar) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        this.zzb.zzd(zzccmVar);
    }

    @Override // com.google.android.gms.internal.ads.zzccj
    public final synchronized void zzl(zzccx zzccxVar) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzfdf zzfdfVar = this.zzd;
        zzfdfVar.zza = zzccxVar.zza;
        zzfdfVar.zzb = zzccxVar.zzb;
    }

    @Override // com.google.android.gms.internal.ads.zzccj
    public final synchronized void zzm(IObjectWrapper iObjectWrapper) throws RemoteException {
        zzn(iObjectWrapper, this.zzh);
    }

    @Override // com.google.android.gms.internal.ads.zzccj
    public final synchronized void zzn(IObjectWrapper iObjectWrapper, boolean z) throws RemoteException {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        if (this.zzg == null) {
            com.google.android.gms.ads.internal.util.zze.zzj("Rewarded can not be shown before loaded");
            this.zzb.zzk(zzfem.zzd(9, null, null));
            return;
        }
        this.zzg.zzh(z, (Activity) ObjectWrapper.unwrap(iObjectWrapper));
    }

    @Override // com.google.android.gms.internal.ads.zzccj
    public final boolean zzo() {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzdua zzduaVar = this.zzg;
        return (zzduaVar == null || zzduaVar.zzf()) ? false : true;
    }

    @Override // com.google.android.gms.internal.ads.zzccj
    public final void zzp(zzccr zzccrVar) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        this.zzb.zzi(zzccrVar);
    }
}
