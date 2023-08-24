package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import com.google.android.exoplayer2.PlaybackException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzaou implements zzaox {
    private static zzaou zzb;
    private final Context zzc;
    private final zzfnu zzd;
    private final zzfob zze;
    private final zzfod zzf;
    private final zzapv zzg;
    private final zzfmf zzh;
    private final Executor zzi;
    private final zzfoa zzj;
    private final zzaqk zzl;
    private volatile boolean zzn;
    private final int zzp;
    volatile long zza = 0;
    private final Object zzm = new Object();
    private volatile boolean zzo = false;
    private final CountDownLatch zzk = new CountDownLatch(1);

    zzaou(Context context, zzfmf zzfmfVar, zzfnu zzfnuVar, zzfob zzfobVar, zzfod zzfodVar, zzapv zzapvVar, Executor executor, zzfma zzfmaVar, int r11, zzaqk zzaqkVar) {
        this.zzc = context;
        this.zzh = zzfmfVar;
        this.zzd = zzfnuVar;
        this.zze = zzfobVar;
        this.zzf = zzfodVar;
        this.zzg = zzapvVar;
        this.zzi = executor;
        this.zzp = r11;
        this.zzl = zzaqkVar;
        this.zzj = new zzaos(this, zzfmaVar);
    }

    public static synchronized zzaou zza(String str, Context context, boolean z, boolean z2) {
        zzaou zzb2;
        synchronized (zzaou.class) {
            zzb2 = zzb(str, context, Executors.newCachedThreadPool(), z, z2);
        }
        return zzb2;
    }

    @Deprecated
    public static synchronized zzaou zzb(String str, Context context, Executor executor, boolean z, boolean z2) {
        zzaou zzaouVar;
        synchronized (zzaou.class) {
            if (zzb == null) {
                zzfmg zza = zzfmh.zza();
                zza.zza(str);
                zza.zzc(z);
                zzfmh zzd = zza.zzd();
                zzfmf zza2 = zzfmf.zza(context, executor, z2);
                zzapf zzc = ((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzcz)).booleanValue() ? zzapf.zzc(context) : null;
                zzaqk zzd2 = ((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzcA)).booleanValue() ? zzaqk.zzd(context, executor) : null;
                zzfmy zze = zzfmy.zze(context, executor, zza2, zzd);
                zzapu zzapuVar = new zzapu(context);
                zzapv zzapvVar = new zzapv(zzd, zze, new zzaqi(context, zzapuVar), zzapuVar, zzc, zzd2);
                int zzb2 = zzfnh.zzb(context, zza2);
                zzfma zzfmaVar = new zzfma();
                zzaou zzaouVar2 = new zzaou(context, zza2, new zzfnu(context, zzb2), new zzfob(context, zzb2, new zzaor(zza2), ((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzbQ)).booleanValue()), new zzfod(context, zzapvVar, zza2, zzfmaVar), zzapvVar, executor, zzfmaVar, zzb2, zzd2);
                zzb = zzaouVar2;
                zzaouVar2.zzm();
                zzb.zzo();
            }
            zzaouVar = zzb;
        }
        return zzaouVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0099, code lost:
        if (r4.zzd().zzj().equals(r5.zzj()) != false) goto L47;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static /* bridge */ /* synthetic */ void zzj(com.google.android.gms.internal.ads.zzaou r12) {
        /*
            Method dump skipped, instructions count: 320
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaou.zzj(com.google.android.gms.internal.ads.zzaou):void");
    }

    private final void zzr() {
        zzaqk zzaqkVar = this.zzl;
        if (zzaqkVar != null) {
            zzaqkVar.zzh();
        }
    }

    private final zzfnt zzs(int r2) {
        if (zzfnh.zza(this.zzp)) {
            if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzbO)).booleanValue()) {
                return this.zze.zzc(1);
            }
            return this.zzd.zzd(1);
        }
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzaox
    public final String zze(Context context, String str, View view) {
        return zzf(context, str, view, null);
    }

    @Override // com.google.android.gms.internal.ads.zzaox
    public final String zzf(Context context, String str, View view, Activity activity) {
        zzr();
        zzo();
        zzfmi zza = this.zzf.zza();
        if (zza != null) {
            long currentTimeMillis = System.currentTimeMillis();
            String zza2 = zza.zza(context, null, str, view, activity);
            this.zzh.zzf(5000, System.currentTimeMillis() - currentTimeMillis, zza2, null);
            return zza2;
        }
        return "";
    }

    @Override // com.google.android.gms.internal.ads.zzaox
    public final String zzg(Context context) {
        zzr();
        zzo();
        zzfmi zza = this.zzf.zza();
        if (zza != null) {
            long currentTimeMillis = System.currentTimeMillis();
            String zzc = zza.zzc(context, null);
            this.zzh.zzf(PlaybackException.ERROR_CODE_AUDIO_TRACK_INIT_FAILED, System.currentTimeMillis() - currentTimeMillis, zzc, null);
            return zzc;
        }
        return "";
    }

    @Override // com.google.android.gms.internal.ads.zzaox
    public final String zzh(Context context, View view, Activity activity) {
        zzr();
        zzo();
        zzfmi zza = this.zzf.zza();
        if (zza != null) {
            long currentTimeMillis = System.currentTimeMillis();
            String zzb2 = zza.zzb(context, null, view, activity);
            this.zzh.zzf(PlaybackException.ERROR_CODE_AUDIO_TRACK_WRITE_FAILED, System.currentTimeMillis() - currentTimeMillis, zzb2, null);
            return zzb2;
        }
        return "";
    }

    @Override // com.google.android.gms.internal.ads.zzaox
    public final void zzk(MotionEvent motionEvent) {
        zzfmi zza = this.zzf.zza();
        if (zza != null) {
            try {
                zza.zzd(null, motionEvent);
            } catch (zzfoc e) {
                this.zzh.zzc(e.zza(), -1L, e);
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaox
    public final void zzl(int r1, int r2, int r3) {
    }

    final synchronized void zzm() {
        long currentTimeMillis = System.currentTimeMillis();
        zzfnt zzs = zzs(1);
        if (zzs != null) {
            if (this.zzf.zzc(zzs)) {
                this.zzo = true;
                this.zzk.countDown();
                return;
            }
            return;
        }
        this.zzh.zzd(4013, System.currentTimeMillis() - currentTimeMillis);
    }

    @Override // com.google.android.gms.internal.ads.zzaox
    public final void zzn(View view) {
        this.zzg.zzd(view);
    }

    public final void zzo() {
        if (this.zzn) {
            return;
        }
        synchronized (this.zzm) {
            if (!this.zzn) {
                if ((System.currentTimeMillis() / 1000) - this.zza < 3600) {
                    return;
                }
                zzfnt zzb2 = this.zzf.zzb();
                if ((zzb2 == null || zzb2.zzd(3600L)) && zzfnh.zza(this.zzp)) {
                    this.zzi.execute(new zzaot(this));
                }
            }
        }
    }

    public final synchronized boolean zzq() {
        return this.zzo;
    }
}
