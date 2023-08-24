package com.google.android.gms.ads.internal;

import android.app.Activity;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import com.google.android.gms.ads.internal.client.zzaw;
import com.google.android.gms.ads.internal.client.zzay;
import com.google.android.gms.internal.ads.zzaou;
import com.google.android.gms.internal.ads.zzaox;
import com.google.android.gms.internal.ads.zzapa;
import com.google.android.gms.internal.ads.zzbiy;
import com.google.android.gms.internal.ads.zzcgg;
import com.google.android.gms.internal.ads.zzcgt;
import com.google.android.gms.internal.ads.zzcha;
import com.google.android.gms.internal.ads.zzfmf;
import com.google.android.gms.internal.ads.zzfnh;
import com.google.android.gms.internal.ads.zzfob;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzi implements Runnable, zzaox {
    protected boolean zza;
    private final boolean zzf;
    private final boolean zzg;
    private final Executor zzh;
    private final zzfmf zzi;
    private Context zzj;
    private final Context zzk;
    private zzcgt zzl;
    private final zzcgt zzm;
    private final boolean zzn;
    private int zzo;
    private final List zzc = new Vector();
    private final AtomicReference zzd = new AtomicReference();
    private final AtomicReference zze = new AtomicReference();
    final CountDownLatch zzb = new CountDownLatch(1);

    public zzi(Context context, zzcgt zzcgtVar) {
        this.zzj = context;
        this.zzk = context;
        this.zzl = zzcgtVar;
        this.zzm = zzcgtVar;
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        this.zzh = newCachedThreadPool;
        boolean booleanValue = ((Boolean) zzay.zzc().zzb(zzbiy.zzbT)).booleanValue();
        this.zzn = booleanValue;
        this.zzi = zzfmf.zza(context, newCachedThreadPool, booleanValue);
        this.zzf = ((Boolean) zzay.zzc().zzb(zzbiy.zzbP)).booleanValue();
        this.zzg = ((Boolean) zzay.zzc().zzb(zzbiy.zzbU)).booleanValue();
        if (((Boolean) zzay.zzc().zzb(zzbiy.zzbS)).booleanValue()) {
            this.zzo = 2;
        } else {
            this.zzo = 1;
        }
        if (!((Boolean) zzay.zzc().zzb(zzbiy.zzcC)).booleanValue()) {
            this.zza = zzc();
        }
        if (((Boolean) zzay.zzc().zzb(zzbiy.zzcw)).booleanValue()) {
            zzcha.zza.execute(this);
            return;
        }
        zzaw.zzb();
        if (zzcgg.zzt()) {
            zzcha.zza.execute(this);
        } else {
            run();
        }
    }

    private final zzaox zzj() {
        return zzi() == 2 ? (zzaox) this.zze.get() : (zzaox) this.zzd.get();
    }

    private final void zzm() {
        zzaox zzj = zzj();
        if (this.zzc.isEmpty() || zzj == null) {
            return;
        }
        for (Object[] objArr : this.zzc) {
            int length = objArr.length;
            if (length == 1) {
                zzj.zzk((MotionEvent) objArr[0]);
            } else if (length == 3) {
                zzj.zzl(((Integer) objArr[0]).intValue(), ((Integer) objArr[1]).intValue(), ((Integer) objArr[2]).intValue());
            }
        }
        this.zzc.clear();
    }

    private final void zzo(boolean z) {
        this.zzd.set(zzapa.zzt(this.zzl.zza, zzp(this.zzj), z, this.zzo));
    }

    private static final Context zzp(Context context) {
        Context applicationContext = context.getApplicationContext();
        return applicationContext == null ? context : applicationContext;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [android.content.Context, com.google.android.gms.internal.ads.zzcgt] */
    @Override // java.lang.Runnable
    public final void run() {
        try {
            if (((Boolean) zzay.zzc().zzb(zzbiy.zzcC)).booleanValue()) {
                this.zza = zzc();
            }
            boolean z = this.zzl.zzd;
            final boolean z2 = false;
            if (!((Boolean) zzay.zzc().zzb(zzbiy.zzaQ)).booleanValue() && z) {
                z2 = true;
            }
            if (zzi() == 1) {
                zzo(z2);
                if (this.zzo == 2) {
                    this.zzh.execute(new Runnable() { // from class: com.google.android.gms.ads.internal.zzg
                        @Override // java.lang.Runnable
                        public final void run() {
                            zzi.this.zzb(z2);
                        }
                    });
                }
            } else {
                long currentTimeMillis = System.currentTimeMillis();
                try {
                    zzaou zza = zzaou.zza(this.zzl.zza, zzp(this.zzj), z2, this.zzn);
                    this.zze.set(zza);
                    if (this.zzg && !zza.zzq()) {
                        this.zzo = 1;
                        zzo(z2);
                    }
                } catch (NullPointerException e) {
                    this.zzo = 1;
                    zzo(z2);
                    this.zzi.zzc(2031, System.currentTimeMillis() - currentTimeMillis, e);
                }
            }
        } finally {
            this.zzb.countDown();
            this.zzj = null;
            this.zzl = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzb(boolean z) {
        long currentTimeMillis = System.currentTimeMillis();
        try {
            zzaou.zza(this.zzm.zza, zzp(this.zzk), z, this.zzn).zzo();
        } catch (NullPointerException e) {
            this.zzi.zzc(2027, System.currentTimeMillis() - currentTimeMillis, e);
        }
    }

    protected final boolean zzc() {
        Context context = this.zzj;
        zzfmf zzfmfVar = this.zzi;
        zzh zzhVar = new zzh(this);
        return new zzfob(this.zzj, zzfnh.zzb(context, zzfmfVar), zzhVar, ((Boolean) zzay.zzc().zzb(zzbiy.zzbQ)).booleanValue()).zzd(1);
    }

    public final boolean zzd() {
        try {
            this.zzb.await();
            return true;
        } catch (InterruptedException e) {
            com.google.android.gms.ads.internal.util.zze.zzk("Interrupted during GADSignals creation.", e);
            return false;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaox
    public final String zze(Context context, String str, View view) {
        return zzf(context, str, view, null);
    }

    @Override // com.google.android.gms.internal.ads.zzaox
    public final String zzf(Context context, String str, View view, Activity activity) {
        if (zzd()) {
            zzaox zzj = zzj();
            if (((Boolean) zzay.zzc().zzb(zzbiy.zzil)).booleanValue()) {
                zzt.zzq();
                com.google.android.gms.ads.internal.util.zzs.zzF(view, 4, null);
            }
            if (zzj != null) {
                zzm();
                return zzj.zzf(zzp(context), str, view, activity);
            }
            return "";
        }
        return "";
    }

    @Override // com.google.android.gms.internal.ads.zzaox
    public final String zzg(Context context) {
        zzaox zzj;
        if (!zzd() || (zzj = zzj()) == null) {
            return "";
        }
        zzm();
        return zzj.zzg(zzp(context));
    }

    @Override // com.google.android.gms.internal.ads.zzaox
    public final String zzh(Context context, View view, Activity activity) {
        if (((Boolean) zzay.zzc().zzb(zzbiy.zzik)).booleanValue()) {
            if (zzd()) {
                zzaox zzj = zzj();
                if (((Boolean) zzay.zzc().zzb(zzbiy.zzil)).booleanValue()) {
                    zzt.zzq();
                    com.google.android.gms.ads.internal.util.zzs.zzF(view, 2, null);
                }
                return zzj != null ? zzj.zzh(context, view, activity) : "";
            }
            return "";
        }
        zzaox zzj2 = zzj();
        if (((Boolean) zzay.zzc().zzb(zzbiy.zzil)).booleanValue()) {
            zzt.zzq();
            com.google.android.gms.ads.internal.util.zzs.zzF(view, 2, null);
        }
        return zzj2 != null ? zzj2.zzh(context, view, activity) : "";
    }

    protected final int zzi() {
        if (!this.zzf || this.zza) {
            return this.zzo;
        }
        return 1;
    }

    @Override // com.google.android.gms.internal.ads.zzaox
    public final void zzk(MotionEvent motionEvent) {
        zzaox zzj = zzj();
        if (zzj != null) {
            zzm();
            zzj.zzk(motionEvent);
            return;
        }
        this.zzc.add(new Object[]{motionEvent});
    }

    @Override // com.google.android.gms.internal.ads.zzaox
    public final void zzl(int r4, int r5, int r6) {
        zzaox zzj = zzj();
        if (zzj == null) {
            this.zzc.add(new Object[]{Integer.valueOf(r4), Integer.valueOf(r5), Integer.valueOf(r6)});
            return;
        }
        zzm();
        zzj.zzl(r4, r5, r6);
    }

    @Override // com.google.android.gms.internal.ads.zzaox
    public final void zzn(View view) {
        zzaox zzj = zzj();
        if (zzj != null) {
            zzj.zzn(view);
        }
    }
}
