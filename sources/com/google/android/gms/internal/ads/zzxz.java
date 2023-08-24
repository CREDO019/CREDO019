package com.google.android.gms.internal.ads;

import android.os.Handler;
import android.os.SystemClock;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzxz {
    private final Handler zza;
    private final zzya zzb;

    public zzxz(Handler handler, zzya zzyaVar) {
        this.zza = zzyaVar == null ? null : handler;
        this.zzb = zzyaVar;
    }

    public final void zza(final String str, final long j, final long j2) {
        Handler handler = this.zza;
        if (handler != null) {
            handler.post(new Runnable() { // from class: com.google.android.gms.internal.ads.zzxv
                @Override // java.lang.Runnable
                public final void run() {
                    zzxz.this.zzg(str, j, j2);
                }
            });
        }
    }

    public final void zzb(final String str) {
        Handler handler = this.zza;
        if (handler != null) {
            handler.post(new Runnable() { // from class: com.google.android.gms.internal.ads.zzxy
                @Override // java.lang.Runnable
                public final void run() {
                    zzxz.this.zzh(str);
                }
            });
        }
    }

    public final void zzc(final zzgq zzgqVar) {
        zzgqVar.zza();
        Handler handler = this.zza;
        if (handler != null) {
            handler.post(new Runnable() { // from class: com.google.android.gms.internal.ads.zzxu
                @Override // java.lang.Runnable
                public final void run() {
                    zzxz.this.zzi(zzgqVar);
                }
            });
        }
    }

    public final void zzd(final int r3, final long j) {
        Handler handler = this.zza;
        if (handler != null) {
            handler.post(new Runnable() { // from class: com.google.android.gms.internal.ads.zzxp
                @Override // java.lang.Runnable
                public final void run() {
                    zzxz.this.zzj(r3, j);
                }
            });
        }
    }

    public final void zze(final zzgq zzgqVar) {
        Handler handler = this.zza;
        if (handler != null) {
            handler.post(new Runnable() { // from class: com.google.android.gms.internal.ads.zzxt
                @Override // java.lang.Runnable
                public final void run() {
                    zzxz.this.zzk(zzgqVar);
                }
            });
        }
    }

    public final void zzf(final zzaf zzafVar, final zzgr zzgrVar) {
        Handler handler = this.zza;
        if (handler != null) {
            handler.post(new Runnable() { // from class: com.google.android.gms.internal.ads.zzxw
                @Override // java.lang.Runnable
                public final void run() {
                    zzxz.this.zzl(zzafVar, zzgrVar);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzg(String str, long j, long j2) {
        zzya zzyaVar = this.zzb;
        int r1 = zzel.zza;
        zzyaVar.zzp(str, j, j2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzh(String str) {
        zzya zzyaVar = this.zzb;
        int r1 = zzel.zza;
        zzyaVar.zzq(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzi(zzgq zzgqVar) {
        zzgqVar.zza();
        zzya zzyaVar = this.zzb;
        int r1 = zzel.zza;
        zzyaVar.zzr(zzgqVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzj(int r3, long j) {
        zzya zzyaVar = this.zzb;
        int r1 = zzel.zza;
        zzyaVar.zzl(r3, j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzk(zzgq zzgqVar) {
        zzya zzyaVar = this.zzb;
        int r1 = zzel.zza;
        zzyaVar.zzs(zzgqVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzl(zzaf zzafVar, zzgr zzgrVar) {
        int r0 = zzel.zza;
        this.zzb.zzu(zzafVar, zzgrVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzm(Object obj, long j) {
        zzya zzyaVar = this.zzb;
        int r1 = zzel.zza;
        zzyaVar.zzm(obj, j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzn(long j, int r5) {
        zzya zzyaVar = this.zzb;
        int r1 = zzel.zza;
        zzyaVar.zzt(j, r5);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzo(Exception exc) {
        zzya zzyaVar = this.zzb;
        int r1 = zzel.zza;
        zzyaVar.zzo(exc);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzp(zzda zzdaVar) {
        zzya zzyaVar = this.zzb;
        int r1 = zzel.zza;
        zzyaVar.zzv(zzdaVar);
    }

    public final void zzq(final Object obj) {
        if (this.zza != null) {
            final long elapsedRealtime = SystemClock.elapsedRealtime();
            this.zza.post(new Runnable() { // from class: com.google.android.gms.internal.ads.zzxq
                @Override // java.lang.Runnable
                public final void run() {
                    zzxz.this.zzm(obj, elapsedRealtime);
                }
            });
        }
    }

    public final void zzr(final long j, final int r5) {
        Handler handler = this.zza;
        if (handler != null) {
            handler.post(new Runnable() { // from class: com.google.android.gms.internal.ads.zzxs
                @Override // java.lang.Runnable
                public final void run() {
                    zzxz.this.zzn(j, r5);
                }
            });
        }
    }

    public final void zzs(final Exception exc) {
        Handler handler = this.zza;
        if (handler != null) {
            handler.post(new Runnable() { // from class: com.google.android.gms.internal.ads.zzxr
                @Override // java.lang.Runnable
                public final void run() {
                    zzxz.this.zzo(exc);
                }
            });
        }
    }

    public final void zzt(final zzda zzdaVar) {
        Handler handler = this.zza;
        if (handler != null) {
            handler.post(new Runnable() { // from class: com.google.android.gms.internal.ads.zzxx
                @Override // java.lang.Runnable
                public final void run() {
                    zzxz.this.zzp(zzdaVar);
                }
            });
        }
    }
}
