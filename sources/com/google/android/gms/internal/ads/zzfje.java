package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Binder;
import android.os.Build;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfje implements Runnable {
    public static Boolean zza;
    private final Context zzb;
    private final zzcgt zzc;
    private String zze;
    private int zzf;
    private final zzdvg zzg;
    private final zzeea zzi;
    private final zzcbm zzj;
    private final zzfjj zzd = zzfjm.zzc();
    private boolean zzh = false;

    public zzfje(Context context, zzcgt zzcgtVar, zzdvg zzdvgVar, zzeea zzeeaVar, zzcbm zzcbmVar, byte[] bArr) {
        this.zzb = context;
        this.zzc = zzcgtVar;
        this.zzg = zzdvgVar;
        this.zzi = zzeeaVar;
        this.zzj = zzcbmVar;
    }

    public static synchronized boolean zza() {
        boolean booleanValue;
        synchronized (zzfje.class) {
            if (zza == null) {
                if (((Boolean) zzbkh.zzb.zze()).booleanValue()) {
                    zza = Boolean.valueOf(Math.random() < ((Double) zzbkh.zza.zze()).doubleValue());
                } else {
                    zza = false;
                }
            }
            booleanValue = zza.booleanValue();
        }
        return booleanValue;
    }

    private final synchronized void zzc() {
        if (this.zzh) {
            return;
        }
        this.zzh = true;
        if (zza()) {
            com.google.android.gms.ads.internal.zzt.zzq();
            this.zze = com.google.android.gms.ads.internal.util.zzs.zzo(this.zzb);
            this.zzf = GoogleApiAvailabilityLight.getInstance().getApkVersion(this.zzb);
            long intValue = ((Integer) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzhs)).intValue();
            zzcha.zzd.scheduleAtFixedRate(this, intValue, intValue, TimeUnit.MILLISECONDS);
        }
    }

    private final synchronized void zzd() {
        try {
            new zzedz(this.zzb, this.zzc.zza, this.zzj, Binder.getCallingUid(), null).zza(new zzedx((String) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzhr), 60000, new HashMap(), ((zzfjm) this.zzd.zzal()).zzaw(), "application/x-protobuf"));
            this.zzd.zzc();
        } catch (Exception e) {
            if (!(e instanceof zzeas) || ((zzeas) e).zza() != 3) {
                com.google.android.gms.ads.internal.zzt.zzp().zzs(e, "CuiMonitor.sendCuiPing");
            } else {
                this.zzd.zzc();
            }
        }
    }

    @Override // java.lang.Runnable
    public final synchronized void run() {
        if (zza()) {
            if (this.zzd.zza() == 0) {
                return;
            }
            zzd();
        }
    }

    public final synchronized void zzb(zzfiv zzfivVar) {
        if (!this.zzh) {
            zzc();
        }
        if (zza()) {
            if (zzfivVar == null) {
                return;
            }
            if (this.zzd.zza() >= ((Integer) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzht)).intValue()) {
                return;
            }
            zzfjj zzfjjVar = this.zzd;
            zzfjk zza2 = zzfjl.zza();
            zzfjg zza3 = zzfjh.zza();
            zza3.zzo(zzfivVar.zzh());
            zza3.zzl(zzfivVar.zzg());
            zza3.zze(zzfivVar.zzb());
            zza3.zzq(3);
            zza3.zzk(this.zzc.zza);
            zza3.zza(this.zze);
            zza3.zzi(Build.VERSION.RELEASE);
            zza3.zzm(Build.VERSION.SDK_INT);
            zza3.zzp(zzfivVar.zzj());
            zza3.zzh(zzfivVar.zza());
            zza3.zzc(this.zzf);
            zza3.zzn(zzfivVar.zzi());
            zza3.zzb(zzfivVar.zzc());
            zza3.zzd(zzfivVar.zzd());
            zza3.zzf(zzfivVar.zze());
            zza3.zzg(this.zzg.zzc(zzfivVar.zze()));
            zza3.zzj(zzfivVar.zzf());
            zza2.zza(zza3);
            zzfjjVar.zzb(zza2);
        }
    }
}
