package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.ConnectionResult;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzecl extends zzecf {
    private String zzg;
    private int zzh = 1;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzecl(Context context) {
        this.zzf = new zzcal(context, com.google.android.gms.ads.internal.zzt.zzu().zzb(), this, this);
    }

    @Override // com.google.android.gms.internal.ads.zzecf, com.google.android.gms.common.internal.BaseGmsClient.BaseOnConnectionFailedListener
    public final void onConnectionFailed(ConnectionResult connectionResult) {
        com.google.android.gms.ads.internal.util.zze.zze("Cannot connect to remote service, fallback to local instance.");
        this.zza.zze(new zzecu(1));
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks
    public final void onConnected(Bundle bundle) {
        synchronized (this.zzb) {
            if (!this.zzd) {
                this.zzd = true;
                try {
                    int r1 = this.zzh;
                    if (r1 == 2) {
                        this.zzf.zzp().zze(this.zze, new zzece(this));
                    } else if (r1 == 3) {
                        this.zzf.zzp().zzh(this.zzg, new zzece(this));
                    } else {
                        this.zza.zze(new zzecu(1));
                    }
                } catch (RemoteException | IllegalArgumentException unused) {
                    this.zza.zze(new zzecu(1));
                }
            }
        }
    }

    public final zzfyx zzb(zzcba zzcbaVar) {
        synchronized (this.zzb) {
            int r1 = this.zzh;
            if (r1 != 1 && r1 != 2) {
                return zzfyo.zzh(new zzecu(2));
            } else if (this.zzc) {
                return this.zza;
            } else {
                this.zzh = 2;
                this.zzc = true;
                this.zze = zzcbaVar;
                this.zzf.checkAvailabilityAndConnect();
                this.zza.zzc(new Runnable() { // from class: com.google.android.gms.internal.ads.zzeck
                    @Override // java.lang.Runnable
                    public final void run() {
                        zzecl.this.zza();
                    }
                }, zzcha.zzf);
                return this.zza;
            }
        }
    }

    public final zzfyx zzc(String str) {
        synchronized (this.zzb) {
            int r1 = this.zzh;
            if (r1 != 1 && r1 != 3) {
                return zzfyo.zzh(new zzecu(2));
            } else if (this.zzc) {
                return this.zza;
            } else {
                this.zzh = 3;
                this.zzc = true;
                this.zzg = str;
                this.zzf.checkAvailabilityAndConnect();
                this.zza.zzc(new Runnable() { // from class: com.google.android.gms.internal.ads.zzecj
                    @Override // java.lang.Runnable
                    public final void run() {
                        zzecl.this.zza();
                    }
                }, zzcha.zzf);
                return this.zza;
            }
        }
    }
}
