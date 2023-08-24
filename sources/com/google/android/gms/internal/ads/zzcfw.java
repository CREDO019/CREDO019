package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.wrappers.Wrappers;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.ParametersAreNonnullByDefault;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
@ParametersAreNonnullByDefault
/* loaded from: classes2.dex */
public final class zzcfw {
    private final Object zza = new Object();
    private final com.google.android.gms.ads.internal.util.zzj zzb;
    private final zzcga zzc;
    private boolean zzd;
    private Context zze;
    private zzcgt zzf;
    private zzbjd zzg;
    private Boolean zzh;
    private final AtomicInteger zzi;
    private final zzcfv zzj;
    private final Object zzk;
    private zzfyx zzl;
    private final AtomicBoolean zzm;

    public zzcfw() {
        com.google.android.gms.ads.internal.util.zzj zzjVar = new com.google.android.gms.ads.internal.util.zzj();
        this.zzb = zzjVar;
        this.zzc = new zzcga(com.google.android.gms.ads.internal.client.zzaw.zzd(), zzjVar);
        this.zzd = false;
        this.zzg = null;
        this.zzh = null;
        this.zzi = new AtomicInteger(0);
        this.zzj = new zzcfv(null);
        this.zzk = new Object();
        this.zzm = new AtomicBoolean();
    }

    public final int zza() {
        return this.zzi.get();
    }

    public final Context zzc() {
        return this.zze;
    }

    public final Resources zzd() {
        if (this.zzf.zzd) {
            return this.zze.getResources();
        }
        try {
            if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzis)).booleanValue()) {
                return zzcgr.zza(this.zze).getResources();
            }
            zzcgr.zza(this.zze).getResources();
            return null;
        } catch (zzcgq e) {
            com.google.android.gms.ads.internal.util.zze.zzk("Cannot load resource from dynamite apk or local jar", e);
            return null;
        }
    }

    public final zzbjd zzf() {
        zzbjd zzbjdVar;
        synchronized (this.zza) {
            zzbjdVar = this.zzg;
        }
        return zzbjdVar;
    }

    public final zzcga zzg() {
        return this.zzc;
    }

    public final com.google.android.gms.ads.internal.util.zzg zzh() {
        com.google.android.gms.ads.internal.util.zzj zzjVar;
        synchronized (this.zza) {
            zzjVar = this.zzb;
        }
        return zzjVar;
    }

    public final zzfyx zzj() {
        if (this.zze != null) {
            if (!((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzcj)).booleanValue()) {
                synchronized (this.zzk) {
                    zzfyx zzfyxVar = this.zzl;
                    if (zzfyxVar != null) {
                        return zzfyxVar;
                    }
                    zzfyx zzb = zzcha.zza.zzb(new Callable() { // from class: com.google.android.gms.internal.ads.zzcfr
                        @Override // java.util.concurrent.Callable
                        public final Object call() {
                            return zzcfw.this.zzm();
                        }
                    });
                    this.zzl = zzb;
                    return zzb;
                }
            }
        }
        return zzfyo.zzi(new ArrayList());
    }

    public final Boolean zzk() {
        Boolean bool;
        synchronized (this.zza) {
            bool = this.zzh;
        }
        return bool;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ ArrayList zzm() throws Exception {
        Context zza = zzcbo.zza(this.zze);
        ArrayList arrayList = new ArrayList();
        try {
            PackageInfo packageInfo = Wrappers.packageManager(zza).getPackageInfo(zza.getApplicationInfo().packageName, 4096);
            if (packageInfo.requestedPermissions != null && packageInfo.requestedPermissionsFlags != null) {
                for (int r2 = 0; r2 < packageInfo.requestedPermissions.length; r2++) {
                    if ((packageInfo.requestedPermissionsFlags[r2] & 2) != 0) {
                        arrayList.add(packageInfo.requestedPermissions[r2]);
                    }
                }
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        return arrayList;
    }

    public final void zzo() {
        this.zzj.zza();
    }

    public final void zzp() {
        this.zzi.decrementAndGet();
    }

    public final void zzq() {
        this.zzi.incrementAndGet();
    }

    public final void zzr(Context context, zzcgt zzcgtVar) {
        zzbjd zzbjdVar;
        synchronized (this.zza) {
            if (!this.zzd) {
                this.zze = context.getApplicationContext();
                this.zzf = zzcgtVar;
                com.google.android.gms.ads.internal.zzt.zzb().zzc(this.zzc);
                this.zzb.zzr(this.zze);
                zzcad.zzb(this.zze, this.zzf);
                com.google.android.gms.ads.internal.zzt.zze();
                if (!((Boolean) zzbki.zzc.zze()).booleanValue()) {
                    com.google.android.gms.ads.internal.util.zze.zza("CsiReporterFactory: CSI is not enabled. No CSI reporter created.");
                    zzbjdVar = null;
                } else {
                    zzbjdVar = new zzbjd();
                }
                this.zzg = zzbjdVar;
                if (zzbjdVar != null) {
                    zzchd.zza(new zzcfs(this).zzb(), "AppState.registerCsiReporter");
                }
                if (PlatformVersion.isAtLeastO()) {
                    if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzhg)).booleanValue()) {
                        ((ConnectivityManager) context.getSystemService("connectivity")).registerDefaultNetworkCallback(new zzcft(this));
                    }
                }
                this.zzd = true;
                zzj();
            }
        }
        com.google.android.gms.ads.internal.zzt.zzq().zzc(context, zzcgtVar.zza);
    }

    public final void zzs(Throwable th, String str) {
        zzcad.zzb(this.zze, this.zzf).zze(th, str, ((Double) zzbkw.zzg.zze()).floatValue());
    }

    public final void zzt(Throwable th, String str) {
        zzcad.zzb(this.zze, this.zzf).zzd(th, str);
    }

    public final void zzu(Boolean bool) {
        synchronized (this.zza) {
            this.zzh = bool;
        }
    }

    public final boolean zzv(Context context) {
        if (PlatformVersion.isAtLeastO()) {
            if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzhg)).booleanValue()) {
                return this.zzm.get();
            }
        }
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
