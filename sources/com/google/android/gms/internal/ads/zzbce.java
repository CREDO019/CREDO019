package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.ads.MobileAds;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbce implements Application.ActivityLifecycleCallbacks {
    private Activity zza;
    private Context zzb;
    private Runnable zzh;
    private long zzj;
    private final Object zzc = new Object();
    private boolean zzd = true;
    private boolean zze = false;
    private final List zzf = new ArrayList();
    private final List zzg = new ArrayList();
    private boolean zzi = false;

    private final void zzk(Activity activity) {
        synchronized (this.zzc) {
            if (!activity.getClass().getName().startsWith(MobileAds.ERROR_DOMAIN)) {
                this.zza = activity;
            }
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity) {
        synchronized (this.zzc) {
            Activity activity2 = this.zza;
            if (activity2 != null) {
                if (activity2.equals(activity)) {
                    this.zza = null;
                }
                Iterator it = this.zzg.iterator();
                while (it.hasNext()) {
                    try {
                        if (((zzbct) it.next()).zza()) {
                            it.remove();
                        }
                    } catch (Exception e) {
                        com.google.android.gms.ads.internal.zzt.zzp().zzt(e, "AppActivityTracker.ActivityListener.onActivityDestroyed");
                        zzcgn.zzh("", e);
                    }
                }
            }
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPaused(Activity activity) {
        zzk(activity);
        synchronized (this.zzc) {
            for (zzbct zzbctVar : this.zzg) {
                try {
                    zzbctVar.zzb();
                } catch (Exception e) {
                    com.google.android.gms.ads.internal.zzt.zzp().zzt(e, "AppActivityTracker.ActivityListener.onActivityPaused");
                    zzcgn.zzh("", e);
                }
            }
        }
        this.zze = true;
        if (this.zzh != null) {
            com.google.android.gms.ads.internal.util.zzs.zza.removeCallbacks(this.zzh);
        }
        zzfph zzfphVar = com.google.android.gms.ads.internal.util.zzs.zza;
        zzbcd zzbcdVar = new zzbcd(this);
        this.zzh = zzbcdVar;
        zzfphVar.postDelayed(zzbcdVar, this.zzj);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityResumed(Activity activity) {
        zzk(activity);
        this.zze = false;
        boolean z = !this.zzd;
        this.zzd = true;
        if (this.zzh != null) {
            com.google.android.gms.ads.internal.util.zzs.zza.removeCallbacks(this.zzh);
        }
        synchronized (this.zzc) {
            for (zzbct zzbctVar : this.zzg) {
                try {
                    zzbctVar.zzc();
                } catch (Exception e) {
                    com.google.android.gms.ads.internal.zzt.zzp().zzt(e, "AppActivityTracker.ActivityListener.onActivityResumed");
                    zzcgn.zzh("", e);
                }
            }
            if (z) {
                for (zzbcf zzbcfVar : this.zzf) {
                    try {
                        zzbcfVar.zza(true);
                    } catch (Exception e2) {
                        zzcgn.zzh("", e2);
                    }
                }
            } else {
                com.google.android.gms.ads.internal.util.zze.zze("App is still foreground.");
            }
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity activity) {
        zzk(activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity activity) {
    }

    public final Activity zza() {
        return this.zza;
    }

    public final Context zzb() {
        return this.zzb;
    }

    public final void zzf(zzbcf zzbcfVar) {
        synchronized (this.zzc) {
            this.zzf.add(zzbcfVar);
        }
    }

    public final void zzg(Application application, Context context) {
        if (this.zzi) {
            return;
        }
        application.registerActivityLifecycleCallbacks(this);
        if (context instanceof Activity) {
            zzk((Activity) context);
        }
        this.zzb = application;
        this.zzj = ((Long) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzaM)).longValue();
        this.zzi = true;
    }

    public final void zzh(zzbcf zzbcfVar) {
        synchronized (this.zzc) {
            this.zzf.remove(zzbcfVar);
        }
    }
}
