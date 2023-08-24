package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdby implements zzdcy, zzdjv, zzdhp, zzddo, zzbbm {
    private final zzddq zza;
    private final zzfcs zzb;
    private final ScheduledExecutorService zzc;
    private final Executor zzd;
    private ScheduledFuture zzf;
    private final zzfzg zze = zzfzg.zzf();
    private final AtomicBoolean zzg = new AtomicBoolean();

    public zzdby(zzddq zzddqVar, zzfcs zzfcsVar, ScheduledExecutorService scheduledExecutorService, Executor executor) {
        this.zza = zzddqVar;
        this.zzb = zzfcsVar;
        this.zzc = scheduledExecutorService;
        this.zzd = executor;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzb() {
        synchronized (this) {
            if (this.zze.isDone()) {
                return;
            }
            this.zze.zzd(true);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdcy
    public final void zzbv() {
    }

    @Override // com.google.android.gms.internal.ads.zzbbm
    public final void zzc(zzbbl zzbblVar) {
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zziL)).booleanValue() && this.zzb.zzZ != 2 && zzbblVar.zzj && this.zzg.compareAndSet(false, true)) {
            com.google.android.gms.ads.internal.util.zze.zza("Full screen 1px impression occurred");
            this.zza.zza();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdhp
    public final void zzd() {
    }

    @Override // com.google.android.gms.internal.ads.zzdhp
    public final synchronized void zze() {
        if (this.zze.isDone()) {
            return;
        }
        ScheduledFuture scheduledFuture = this.zzf;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
        }
        this.zze.zzd(true);
    }

    @Override // com.google.android.gms.internal.ads.zzdjv
    public final void zzf() {
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzbp)).booleanValue()) {
            zzfcs zzfcsVar = this.zzb;
            if (zzfcsVar.zzZ == 2) {
                if (zzfcsVar.zzr == 0) {
                    this.zza.zza();
                    return;
                }
                zzfyo.zzr(this.zze, new zzdbx(this), this.zzd);
                this.zzf = this.zzc.schedule(new Runnable() { // from class: com.google.android.gms.internal.ads.zzdbw
                    @Override // java.lang.Runnable
                    public final void run() {
                        zzdby.this.zzb();
                    }
                }, this.zzb.zzr, TimeUnit.MILLISECONDS);
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdjv
    public final void zzg() {
    }

    @Override // com.google.android.gms.internal.ads.zzdcy
    public final void zzj() {
    }

    @Override // com.google.android.gms.internal.ads.zzddo
    public final synchronized void zzk(com.google.android.gms.ads.internal.client.zze zzeVar) {
        if (this.zze.isDone()) {
            return;
        }
        ScheduledFuture scheduledFuture = this.zzf;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
        }
        this.zze.zze(new Exception());
    }

    @Override // com.google.android.gms.internal.ads.zzdcy
    public final void zzm() {
    }

    @Override // com.google.android.gms.internal.ads.zzdcy
    public final void zzo() {
        int r0 = this.zzb.zzZ;
        if (r0 == 0 || r0 == 1) {
            if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zziL)).booleanValue()) {
                return;
            }
            this.zza.zza();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdcy
    public final void zzp(zzcbq zzcbqVar, String str, String str2) {
    }

    @Override // com.google.android.gms.internal.ads.zzdcy
    public final void zzr() {
    }
}
