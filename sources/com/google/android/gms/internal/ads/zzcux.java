package com.google.android.gms.internal.ads;

import android.content.Context;
import android.view.View;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcux implements zzdcy, zzdem, zzdds, com.google.android.gms.ads.internal.client.zza, zzddo {
    private final Context zza;
    private final Executor zzb;
    private final Executor zzc;
    private final ScheduledExecutorService zzd;
    private final zzfde zze;
    private final zzfcs zzf;
    private final zzfjq zzg;
    private final zzfdw zzh;
    private final zzapb zzi;
    private final zzbjx zzj;
    private final zzfjc zzk;
    private final WeakReference zzl;
    private final WeakReference zzm;
    private boolean zzn;
    private final AtomicBoolean zzo = new AtomicBoolean();
    private final zzbjz zzp;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzcux(Context context, Executor executor, Executor executor2, ScheduledExecutorService scheduledExecutorService, zzfde zzfdeVar, zzfcs zzfcsVar, zzfjq zzfjqVar, zzfdw zzfdwVar, View view, zzcmn zzcmnVar, zzapb zzapbVar, zzbjx zzbjxVar, zzbjz zzbjzVar, zzfjc zzfjcVar, byte[] bArr) {
        this.zza = context;
        this.zzb = executor;
        this.zzc = executor2;
        this.zzd = scheduledExecutorService;
        this.zze = zzfdeVar;
        this.zzf = zzfcsVar;
        this.zzg = zzfjqVar;
        this.zzh = zzfdwVar;
        this.zzi = zzapbVar;
        this.zzl = new WeakReference(view);
        this.zzm = new WeakReference(zzcmnVar);
        this.zzj = zzbjxVar;
        this.zzp = zzbjzVar;
        this.zzk = zzfjcVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzs() {
        int r0;
        String zzh = ((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzcF)).booleanValue() ? this.zzi.zzc().zzh(this.zza, (View) this.zzl.get(), null) : null;
        if ((!((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzal)).booleanValue() || !this.zze.zzb.zzb.zzg) && ((Boolean) zzbkn.zzh.zze()).booleanValue()) {
            if (((Boolean) zzbkn.zzg.zze()).booleanValue() && ((r0 = this.zzf.zzb) == 1 || r0 == 2 || r0 == 5)) {
                zzcmn zzcmnVar = (zzcmn) this.zzm.get();
            }
            zzfyo.zzr((zzfyf) zzfyo.zzo(zzfyf.zzv(zzfyo.zzi(null)), ((Long) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzaP)).longValue(), TimeUnit.MILLISECONDS, this.zzd), new zzcuw(this, zzh), this.zzb);
            return;
        }
        zzfdw zzfdwVar = this.zzh;
        zzfjq zzfjqVar = this.zzg;
        zzfde zzfdeVar = this.zze;
        zzfcs zzfcsVar = this.zzf;
        zzfdwVar.zza(zzfjqVar.zzd(zzfdeVar, zzfcsVar, false, zzh, null, zzfcsVar.zzd));
    }

    private final void zzt(final int r4, final int r5) {
        View view;
        if (r4 > 0 && ((view = (View) this.zzl.get()) == null || view.getHeight() == 0 || view.getWidth() == 0)) {
            this.zzd.schedule(new Runnable() { // from class: com.google.android.gms.internal.ads.zzcuq
                @Override // java.lang.Runnable
                public final void run() {
                    zzcux.this.zzi(r4, r5);
                }
            }, r5, TimeUnit.MILLISECONDS);
        } else {
            zzs();
        }
    }

    @Override // com.google.android.gms.ads.internal.client.zza
    public final void onAdClicked() {
        if ((((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzal)).booleanValue() && this.zze.zzb.zzb.zzg) || !((Boolean) zzbkn.zzd.zze()).booleanValue()) {
            zzfdw zzfdwVar = this.zzh;
            zzfjq zzfjqVar = this.zzg;
            zzfde zzfdeVar = this.zze;
            zzfcs zzfcsVar = this.zzf;
            zzfdwVar.zzc(zzfjqVar.zzc(zzfdeVar, zzfcsVar, zzfcsVar.zzc), true == com.google.android.gms.ads.internal.zzt.zzp().zzv(this.zza) ? 2 : 1);
            return;
        }
        zzfyo.zzr(zzfyo.zzf(zzfyf.zzv(this.zzj.zza()), Throwable.class, new zzfru() { // from class: com.google.android.gms.internal.ads.zzcur
            @Override // com.google.android.gms.internal.ads.zzfru
            public final Object apply(Object obj) {
                Throwable th = (Throwable) obj;
                return "failure_click_attok";
            }
        }, zzcha.zzf), new zzcuv(this), this.zzb);
    }

    @Override // com.google.android.gms.internal.ads.zzdcy
    public final void zzbv() {
        zzfdw zzfdwVar = this.zzh;
        zzfjq zzfjqVar = this.zzg;
        zzfde zzfdeVar = this.zze;
        zzfcs zzfcsVar = this.zzf;
        zzfdwVar.zza(zzfjqVar.zzc(zzfdeVar, zzfcsVar, zzfcsVar.zzj));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzg() {
        this.zzb.execute(new Runnable() { // from class: com.google.android.gms.internal.ads.zzcuu
            @Override // java.lang.Runnable
            public final void run() {
                zzcux.this.zzs();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzh(int r1, int r2) {
        zzt(r1 - 1, r2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzi(final int r3, final int r4) {
        this.zzb.execute(new Runnable() { // from class: com.google.android.gms.internal.ads.zzcus
            @Override // java.lang.Runnable
            public final void run() {
                zzcux.this.zzh(r3, r4);
            }
        });
    }

    @Override // com.google.android.gms.internal.ads.zzdcy
    public final void zzj() {
    }

    @Override // com.google.android.gms.internal.ads.zzddo
    public final void zzk(com.google.android.gms.ads.internal.client.zze zzeVar) {
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzbo)).booleanValue()) {
            this.zzh.zza(this.zzg.zzc(this.zze, this.zzf, zzfjq.zzf(2, zzeVar.zza, this.zzf.zzp)));
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdds
    public final void zzl() {
        if (this.zzo.compareAndSet(false, true)) {
            int intValue = ((Integer) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzcJ)).intValue();
            if (intValue > 0) {
                zzt(intValue, ((Integer) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzcK)).intValue());
                return;
            }
            if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzcI)).booleanValue()) {
                this.zzc.execute(new Runnable() { // from class: com.google.android.gms.internal.ads.zzcut
                    @Override // java.lang.Runnable
                    public final void run() {
                        zzcux.this.zzg();
                    }
                });
            } else {
                zzs();
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdcy
    public final void zzm() {
    }

    @Override // com.google.android.gms.internal.ads.zzdem
    public final synchronized void zzn() {
        if (this.zzn) {
            ArrayList arrayList = new ArrayList(this.zzf.zzd);
            arrayList.addAll(this.zzf.zzg);
            this.zzh.zza(this.zzg.zzd(this.zze, this.zzf, true, null, null, arrayList));
        } else {
            zzfdw zzfdwVar = this.zzh;
            zzfjq zzfjqVar = this.zzg;
            zzfde zzfdeVar = this.zze;
            zzfcs zzfcsVar = this.zzf;
            zzfdwVar.zza(zzfjqVar.zzc(zzfdeVar, zzfcsVar, zzfcsVar.zzn));
            zzfdw zzfdwVar2 = this.zzh;
            zzfjq zzfjqVar2 = this.zzg;
            zzfde zzfdeVar2 = this.zze;
            zzfcs zzfcsVar2 = this.zzf;
            zzfdwVar2.zza(zzfjqVar2.zzc(zzfdeVar2, zzfcsVar2, zzfcsVar2.zzg));
        }
        this.zzn = true;
    }

    @Override // com.google.android.gms.internal.ads.zzdcy
    public final void zzo() {
    }

    @Override // com.google.android.gms.internal.ads.zzdcy
    public final void zzp(zzcbq zzcbqVar, String str, String str2) {
        zzfdw zzfdwVar = this.zzh;
        zzfjq zzfjqVar = this.zzg;
        zzfcs zzfcsVar = this.zzf;
        zzfdwVar.zza(zzfjqVar.zze(zzfcsVar, zzfcsVar.zzi, zzcbqVar));
    }

    @Override // com.google.android.gms.internal.ads.zzdcy
    public final void zzr() {
        zzfdw zzfdwVar = this.zzh;
        zzfjq zzfjqVar = this.zzg;
        zzfde zzfdeVar = this.zze;
        zzfcs zzfcsVar = this.zzf;
        zzfdwVar.zza(zzfjqVar.zzc(zzfdeVar, zzfcsVar, zzfcsVar.zzh));
    }
}
