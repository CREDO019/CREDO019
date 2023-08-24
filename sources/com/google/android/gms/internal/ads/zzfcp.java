package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.regex.Pattern;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfcp extends zzcbs {
    private final zzfcf zza;
    private final zzfbv zzb;
    private final zzfdf zzc;
    private zzdua zzd;
    private boolean zze = false;

    public zzfcp(zzfcf zzfcfVar, zzfbv zzfbvVar, zzfdf zzfdfVar) {
        this.zza = zzfcfVar;
        this.zzb = zzfbvVar;
        this.zzc = zzfdfVar;
    }

    private final synchronized boolean zzy() {
        boolean z;
        zzdua zzduaVar = this.zzd;
        if (zzduaVar != null) {
            z = zzduaVar.zze() ? false : true;
        }
        return z;
    }

    @Override // com.google.android.gms.internal.ads.zzcbt
    public final Bundle zzb() {
        Preconditions.checkMainThread("getAdMetadata can only be called from the UI thread.");
        zzdua zzduaVar = this.zzd;
        return zzduaVar != null ? zzduaVar.zza() : new Bundle();
    }

    @Override // com.google.android.gms.internal.ads.zzcbt
    public final synchronized com.google.android.gms.ads.internal.client.zzdh zzc() throws RemoteException {
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzfN)).booleanValue()) {
            zzdua zzduaVar = this.zzd;
            if (zzduaVar != null) {
                return zzduaVar.zzl();
            }
            return null;
        }
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzcbt
    public final synchronized String zzd() throws RemoteException {
        zzdua zzduaVar = this.zzd;
        if (zzduaVar == null || zzduaVar.zzl() == null) {
            return null;
        }
        return zzduaVar.zzl().zzg();
    }

    @Override // com.google.android.gms.internal.ads.zzcbt
    public final void zze() throws RemoteException {
        zzf(null);
    }

    @Override // com.google.android.gms.internal.ads.zzcbt
    public final synchronized void zzf(IObjectWrapper iObjectWrapper) {
        Preconditions.checkMainThread("destroy must be called on the main UI thread.");
        Context context = null;
        this.zzb.zzb(null);
        if (this.zzd != null) {
            if (iObjectWrapper != null) {
                context = (Context) ObjectWrapper.unwrap(iObjectWrapper);
            }
            this.zzd.zzm().zza(context);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzcbt
    public final synchronized void zzg(zzcbx zzcbxVar) throws RemoteException {
        Preconditions.checkMainThread("loadAd must be called on the main UI thread.");
        String str = zzcbxVar.zzb;
        String str2 = (String) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzev);
        if (str2 != null && str != null) {
            try {
                if (Pattern.matches(str2, str)) {
                    return;
                }
            } catch (RuntimeException e) {
                com.google.android.gms.ads.internal.zzt.zzp().zzt(e, "NonagonUtil.isPatternMatched");
            }
        }
        if (zzy()) {
            if (!((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzex)).booleanValue()) {
                return;
            }
        }
        zzfbx zzfbxVar = new zzfbx(null);
        this.zzd = null;
        this.zza.zzj(1);
        this.zza.zzb(zzcbxVar.zza, zzcbxVar.zzb, zzfbxVar, new zzfcn(this));
    }

    @Override // com.google.android.gms.internal.ads.zzcbt
    public final void zzh() {
        zzi(null);
    }

    @Override // com.google.android.gms.internal.ads.zzcbt
    public final synchronized void zzi(IObjectWrapper iObjectWrapper) {
        Preconditions.checkMainThread("pause must be called on the main UI thread.");
        if (this.zzd != null) {
            this.zzd.zzm().zzb(iObjectWrapper == null ? null : (Context) ObjectWrapper.unwrap(iObjectWrapper));
        }
    }

    @Override // com.google.android.gms.internal.ads.zzcbt
    public final void zzj() {
        zzk(null);
    }

    @Override // com.google.android.gms.internal.ads.zzcbt
    public final synchronized void zzk(IObjectWrapper iObjectWrapper) {
        Preconditions.checkMainThread("resume must be called on the main UI thread.");
        if (this.zzd != null) {
            this.zzd.zzm().zzc(iObjectWrapper == null ? null : (Context) ObjectWrapper.unwrap(iObjectWrapper));
        }
    }

    @Override // com.google.android.gms.internal.ads.zzcbt
    public final void zzl(com.google.android.gms.ads.internal.client.zzbw zzbwVar) {
        Preconditions.checkMainThread("setAdMetadataListener can only be called from the UI thread.");
        if (zzbwVar == null) {
            this.zzb.zzb(null);
        } else {
            this.zzb.zzb(new zzfco(this, zzbwVar));
        }
    }

    @Override // com.google.android.gms.internal.ads.zzcbt
    public final synchronized void zzm(String str) throws RemoteException {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: setCustomData");
        this.zzc.zzb = str;
    }

    @Override // com.google.android.gms.internal.ads.zzcbt
    public final synchronized void zzn(boolean z) {
        Preconditions.checkMainThread("setImmersiveMode must be called on the main UI thread.");
        this.zze = z;
    }

    @Override // com.google.android.gms.internal.ads.zzcbt
    public final void zzo(zzcbw zzcbwVar) throws RemoteException {
        Preconditions.checkMainThread("setRewardedVideoAdListener can only be called from the UI thread.");
        this.zzb.zzf(zzcbwVar);
    }

    @Override // com.google.android.gms.internal.ads.zzcbt
    public final synchronized void zzp(String str) throws RemoteException {
        Preconditions.checkMainThread("setUserId must be called on the main UI thread.");
        this.zzc.zza = str;
    }

    @Override // com.google.android.gms.internal.ads.zzcbt
    public final synchronized void zzq() throws RemoteException {
        zzr(null);
    }

    @Override // com.google.android.gms.internal.ads.zzcbt
    public final synchronized void zzr(IObjectWrapper iObjectWrapper) throws RemoteException {
        Preconditions.checkMainThread("showAd must be called on the main UI thread.");
        if (this.zzd != null) {
            Activity activity = null;
            if (iObjectWrapper != null) {
                Object unwrap = ObjectWrapper.unwrap(iObjectWrapper);
                if (unwrap instanceof Activity) {
                    activity = (Activity) unwrap;
                }
            }
            this.zzd.zzh(this.zze, activity);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzcbt
    public final boolean zzs() throws RemoteException {
        Preconditions.checkMainThread("isLoaded must be called on the main UI thread.");
        return zzy();
    }

    @Override // com.google.android.gms.internal.ads.zzcbt
    public final boolean zzt() {
        zzdua zzduaVar = this.zzd;
        return zzduaVar != null && zzduaVar.zzg();
    }

    @Override // com.google.android.gms.internal.ads.zzcbt
    public final void zzu(zzcbr zzcbrVar) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: setRewardedAdSkuListener");
        this.zzb.zzh(zzcbrVar);
    }
}
