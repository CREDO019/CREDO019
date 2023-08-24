package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Binder;
import android.os.RemoteException;
import com.google.android.gms.common.internal.BaseGmsClient;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbdt {
    private ScheduledFuture zza = null;
    private final Runnable zzb = new zzbdp(this);
    private final Object zzc = new Object();
    private zzbdw zzd;
    private Context zze;
    private zzbdz zzf;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ void zzh(zzbdt zzbdtVar) {
        synchronized (zzbdtVar.zzc) {
            zzbdw zzbdwVar = zzbdtVar.zzd;
            if (zzbdwVar == null) {
                return;
            }
            if (zzbdwVar.isConnected() || zzbdtVar.zzd.isConnecting()) {
                zzbdtVar.zzd.disconnect();
            }
            zzbdtVar.zzd = null;
            zzbdtVar.zzf = null;
            Binder.flushPendingCommands();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzl() {
        synchronized (this.zzc) {
            if (this.zze != null && this.zzd == null) {
                zzbdw zzd = zzd(new zzbdr(this), new zzbds(this));
                this.zzd = zzd;
                zzd.checkAvailabilityAndConnect();
            }
        }
    }

    public final long zza(zzbdx zzbdxVar) {
        synchronized (this.zzc) {
            if (this.zzf == null) {
                return -2L;
            }
            if (this.zzd.zzp()) {
                try {
                    return this.zzf.zze(zzbdxVar);
                } catch (RemoteException e) {
                    com.google.android.gms.ads.internal.util.zze.zzh("Unable to call into cache service.", e);
                }
            }
            return -2L;
        }
    }

    public final zzbdu zzb(zzbdx zzbdxVar) {
        synchronized (this.zzc) {
            if (this.zzf == null) {
                return new zzbdu();
            }
            try {
                if (this.zzd.zzp()) {
                    return this.zzf.zzg(zzbdxVar);
                }
                return this.zzf.zzf(zzbdxVar);
            } catch (RemoteException e) {
                com.google.android.gms.ads.internal.util.zze.zzh("Unable to call into cache service.", e);
                return new zzbdu();
            }
        }
    }

    protected final synchronized zzbdw zzd(BaseGmsClient.BaseConnectionCallbacks baseConnectionCallbacks, BaseGmsClient.BaseOnConnectionFailedListener baseOnConnectionFailedListener) {
        return new zzbdw(this.zze, com.google.android.gms.ads.internal.zzt.zzu().zzb(), baseConnectionCallbacks, baseOnConnectionFailedListener);
    }

    public final void zzi(Context context) {
        if (context == null) {
            return;
        }
        synchronized (this.zzc) {
            if (this.zze != null) {
                return;
            }
            this.zze = context.getApplicationContext();
            if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzdm)).booleanValue()) {
                zzl();
            } else {
                if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzdl)).booleanValue()) {
                    com.google.android.gms.ads.internal.zzt.zzb().zzc(new zzbdq(this));
                }
            }
        }
    }

    public final void zzj() {
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzdn)).booleanValue()) {
            synchronized (this.zzc) {
                zzl();
                if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzdp)).booleanValue()) {
                    ScheduledFuture scheduledFuture = this.zza;
                    if (scheduledFuture != null) {
                        scheduledFuture.cancel(false);
                    }
                    this.zza = zzcha.zzd.schedule(this.zzb, ((Long) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzdo)).longValue(), TimeUnit.MILLISECONDS);
                } else {
                    com.google.android.gms.ads.internal.util.zzs.zza.removeCallbacks(this.zzb);
                    com.google.android.gms.ads.internal.util.zzs.zza.postDelayed(this.zzb, ((Long) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzdo)).longValue());
                }
            }
        }
    }
}
