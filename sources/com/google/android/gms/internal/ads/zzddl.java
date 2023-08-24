package com.google.android.gms.internal.ads;

import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzddl extends zzdih implements zzddc {
    private final ScheduledExecutorService zzb;
    private ScheduledFuture zzc;
    private boolean zzd;

    public zzddl(zzddk zzddkVar, Set set, Executor executor, ScheduledExecutorService scheduledExecutorService) {
        super(set);
        this.zzd = false;
        this.zzb = scheduledExecutorService;
        zzj(zzddkVar, executor);
    }

    @Override // com.google.android.gms.internal.ads.zzddc
    public final void zza(final com.google.android.gms.ads.internal.client.zze zzeVar) {
        zzo(new zzdig() { // from class: com.google.android.gms.internal.ads.zzdde
            @Override // com.google.android.gms.internal.ads.zzdig
            public final void zza(Object obj) {
                ((zzddc) obj).zza(com.google.android.gms.ads.internal.client.zze.this);
            }
        });
    }

    @Override // com.google.android.gms.internal.ads.zzddc
    public final void zzb() {
        zzo(new zzdig() { // from class: com.google.android.gms.internal.ads.zzddg
            @Override // com.google.android.gms.internal.ads.zzdig
            public final void zza(Object obj) {
                ((zzddc) obj).zzb();
            }
        });
    }

    @Override // com.google.android.gms.internal.ads.zzddc
    public final void zzc(final zzdmm zzdmmVar) {
        if (this.zzd) {
            return;
        }
        ScheduledFuture scheduledFuture = this.zzc;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
        }
        zzo(new zzdig() { // from class: com.google.android.gms.internal.ads.zzddd
            @Override // com.google.android.gms.internal.ads.zzdig
            public final void zza(Object obj) {
                ((zzddc) obj).zzc(zzdmm.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzd() {
        synchronized (this) {
            com.google.android.gms.ads.internal.util.zze.zzg("Timeout waiting for show call succeed to be called.");
            zzc(new zzdmm("Timeout for show call succeed."));
            this.zzd = true;
        }
    }

    public final synchronized void zze() {
        ScheduledFuture scheduledFuture = this.zzc;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
        }
    }

    public final void zzf() {
        this.zzc = this.zzb.schedule(new Runnable() { // from class: com.google.android.gms.internal.ads.zzddf
            @Override // java.lang.Runnable
            public final void run() {
                zzddl.this.zzd();
            }
        }, ((Integer) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzin)).intValue(), TimeUnit.MILLISECONDS);
    }
}
