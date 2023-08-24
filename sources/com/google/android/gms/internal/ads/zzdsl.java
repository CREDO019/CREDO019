package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.Collections;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdsl extends zzbrz implements ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener, zzbll {
    private View zza;
    private com.google.android.gms.ads.internal.client.zzdk zzb;
    private zzdoj zzc;
    private boolean zzd = false;
    private boolean zze = false;

    public zzdsl(zzdoj zzdojVar, zzdoo zzdooVar) {
        this.zza = zzdooVar.zzf();
        this.zzb = zzdooVar.zzj();
        this.zzc = zzdojVar;
        if (zzdooVar.zzr() != null) {
            zzdooVar.zzr().zzao(this);
        }
    }

    private final void zzg() {
        View view;
        zzdoj zzdojVar = this.zzc;
        if (zzdojVar == null || (view = this.zza) == null) {
            return;
        }
        zzdojVar.zzv(view, Collections.emptyMap(), Collections.emptyMap(), zzdoj.zzP(this.zza));
    }

    private final void zzh() {
        View view = this.zza;
        if (view == null) {
            return;
        }
        ViewParent parent = view.getParent();
        if (parent instanceof ViewGroup) {
            ((ViewGroup) parent).removeView(this.zza);
        }
    }

    private static final void zzi(zzbsd zzbsdVar, int r1) {
        try {
            zzbsdVar.zze(r1);
        } catch (RemoteException e) {
            com.google.android.gms.ads.internal.util.zze.zzl("#007 Could not call remote method.", e);
        }
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public final void onGlobalLayout() {
        zzg();
    }

    @Override // android.view.ViewTreeObserver.OnScrollChangedListener
    public final void onScrollChanged() {
        zzg();
    }

    @Override // com.google.android.gms.internal.ads.zzbsa
    public final com.google.android.gms.ads.internal.client.zzdk zzb() throws RemoteException {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        if (this.zzd) {
            com.google.android.gms.ads.internal.util.zze.zzg("getVideoController: Instream ad should not be used after destroyed");
            return null;
        }
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.ads.zzbsa
    public final zzblx zzc() {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        if (this.zzd) {
            com.google.android.gms.ads.internal.util.zze.zzg("getVideoController: Instream ad should not be used after destroyed");
            return null;
        }
        zzdoj zzdojVar = this.zzc;
        if (zzdojVar == null || zzdojVar.zza() == null) {
            return null;
        }
        return zzdojVar.zza().zza();
    }

    @Override // com.google.android.gms.internal.ads.zzbsa
    public final void zzd() throws RemoteException {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzh();
        zzdoj zzdojVar = this.zzc;
        if (zzdojVar != null) {
            zzdojVar.zzV();
        }
        this.zzc = null;
        this.zza = null;
        this.zzb = null;
        this.zzd = true;
    }

    @Override // com.google.android.gms.internal.ads.zzbsa
    public final void zze(IObjectWrapper iObjectWrapper) throws RemoteException {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzf(iObjectWrapper, new zzdsk(this));
    }

    @Override // com.google.android.gms.internal.ads.zzbsa
    public final void zzf(IObjectWrapper iObjectWrapper, zzbsd zzbsdVar) throws RemoteException {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        if (this.zzd) {
            com.google.android.gms.ads.internal.util.zze.zzg("Instream ad can not be shown after destroy().");
            zzi(zzbsdVar, 2);
            return;
        }
        View view = this.zza;
        if (view == null || this.zzb == null) {
            com.google.android.gms.ads.internal.util.zze.zzg("Instream internal error: ".concat(view == null ? "can not get video view." : "can not get video controller."));
            zzi(zzbsdVar, 0);
        } else if (this.zze) {
            com.google.android.gms.ads.internal.util.zze.zzg("Instream ad should not be used again.");
            zzi(zzbsdVar, 1);
        } else {
            this.zze = true;
            zzh();
            ((ViewGroup) ObjectWrapper.unwrap(iObjectWrapper)).addView(this.zza, new ViewGroup.LayoutParams(-1, -1));
            com.google.android.gms.ads.internal.zzt.zzy();
            zzchn.zza(this.zza, this);
            com.google.android.gms.ads.internal.zzt.zzy();
            zzchn.zzb(this.zza, this);
            zzg();
            try {
                zzbsdVar.zzf();
            } catch (RemoteException e) {
                com.google.android.gms.ads.internal.util.zze.zzl("#007 Could not call remote method.", e);
            }
        }
    }
}
