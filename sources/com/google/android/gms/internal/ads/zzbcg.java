package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import javax.annotation.ParametersAreNonnullByDefault;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
@ParametersAreNonnullByDefault
/* loaded from: classes2.dex */
public final class zzbcg {
    private final Object zza = new Object();
    private zzbce zzb = null;
    private boolean zzc = false;

    public final Activity zza() {
        synchronized (this.zza) {
            zzbce zzbceVar = this.zzb;
            if (zzbceVar != null) {
                return zzbceVar.zza();
            }
            return null;
        }
    }

    public final Context zzb() {
        synchronized (this.zza) {
            zzbce zzbceVar = this.zzb;
            if (zzbceVar != null) {
                return zzbceVar.zzb();
            }
            return null;
        }
    }

    public final void zzc(zzbcf zzbcfVar) {
        synchronized (this.zza) {
            if (this.zzb == null) {
                this.zzb = new zzbce();
            }
            this.zzb.zzf(zzbcfVar);
        }
    }

    public final void zzd(Context context) {
        synchronized (this.zza) {
            if (!this.zzc) {
                Context applicationContext = context.getApplicationContext();
                if (applicationContext == null) {
                    applicationContext = context;
                }
                Application application = applicationContext instanceof Application ? (Application) applicationContext : null;
                if (application == null) {
                    com.google.android.gms.ads.internal.util.zze.zzj("Can not cast Context to Application");
                    return;
                }
                if (this.zzb == null) {
                    this.zzb = new zzbce();
                }
                this.zzb.zzg(application, context);
                this.zzc = true;
            }
        }
    }

    public final void zze(zzbcf zzbcfVar) {
        synchronized (this.zza) {
            zzbce zzbceVar = this.zzb;
            if (zzbceVar == null) {
                return;
            }
            zzbceVar.zzh(zzbcfVar);
        }
    }
}
