package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.ConnectionResult;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzecd extends zzecf {
    public zzecd(Context context) {
        this.zzf = new zzcal(context, com.google.android.gms.ads.internal.zzt.zzu().zzb(), this, this);
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks
    public final void onConnected(Bundle bundle) {
        synchronized (this.zzb) {
            if (!this.zzd) {
                this.zzd = true;
                try {
                    this.zzf.zzp().zzg(this.zze, new zzece(this));
                } catch (RemoteException | IllegalArgumentException unused) {
                    this.zza.zze(new zzecu(1));
                } catch (Throwable th) {
                    com.google.android.gms.ads.internal.zzt.zzp().zzt(th, "RemoteAdRequestClientTask.onConnected");
                    this.zza.zze(new zzecu(1));
                }
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzecf, com.google.android.gms.common.internal.BaseGmsClient.BaseOnConnectionFailedListener
    public final void onConnectionFailed(ConnectionResult connectionResult) {
        com.google.android.gms.ads.internal.util.zze.zze("Cannot connect to remote service, fallback to local instance.");
        this.zza.zze(new zzecu(1));
    }
}
