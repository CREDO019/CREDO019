package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcwu extends zzbde {
    private final zzcwt zza;
    private final com.google.android.gms.ads.internal.client.zzbs zzb;
    private final zzeyo zzc;
    private boolean zzd = false;

    public zzcwu(zzcwt zzcwtVar, com.google.android.gms.ads.internal.client.zzbs zzbsVar, zzeyo zzeyoVar) {
        this.zza = zzcwtVar;
        this.zzb = zzbsVar;
        this.zzc = zzeyoVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbdf
    public final com.google.android.gms.ads.internal.client.zzbs zze() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.ads.zzbdf
    public final com.google.android.gms.ads.internal.client.zzdh zzf() {
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzfN)).booleanValue()) {
            return this.zza.zzl();
        }
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzbdf
    public final void zzg(boolean z) {
        this.zzd = z;
    }

    @Override // com.google.android.gms.internal.ads.zzbdf
    public final void zzh(com.google.android.gms.ads.internal.client.zzde zzdeVar) {
        Preconditions.checkMainThread("setOnPaidEventListener must be called on the main UI thread.");
        zzeyo zzeyoVar = this.zzc;
        if (zzeyoVar != null) {
            zzeyoVar.zzp(zzdeVar);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbdf
    public final void zzi(IObjectWrapper iObjectWrapper, zzbdm zzbdmVar) {
        try {
            this.zzc.zzs(zzbdmVar);
            this.zza.zzd((Activity) ObjectWrapper.unwrap(iObjectWrapper), zzbdmVar, this.zzd);
        } catch (RemoteException e) {
            com.google.android.gms.ads.internal.util.zze.zzl("#007 Could not call remote method.", e);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbdf
    public final void zzj(zzbdj zzbdjVar) {
    }
}
