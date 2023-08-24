package com.google.android.gms.internal.ads;

import android.os.Handler;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zznp {
    private final Handler zza;
    private final zznq zzb;

    public zznp(Handler handler, zznq zznqVar) {
        this.zza = zznqVar == null ? null : handler;
        this.zzb = zznqVar;
    }

    public final void zza(final Exception exc) {
        Handler handler = this.zza;
        if (handler != null) {
            handler.post(new Runnable() { // from class: com.google.android.gms.internal.ads.zznf
                @Override // java.lang.Runnable
                public final void run() {
                    zznp.this.zzh(exc);
                }
            });
        }
    }

    public final void zzb(final Exception exc) {
        Handler handler = this.zza;
        if (handler != null) {
            handler.post(new Runnable() { // from class: com.google.android.gms.internal.ads.zznl
                @Override // java.lang.Runnable
                public final void run() {
                    zznp.this.zzi(exc);
                }
            });
        }
    }

    public final void zzc(final String str, final long j, final long j2) {
        Handler handler = this.zza;
        if (handler != null) {
            handler.post(new Runnable() { // from class: com.google.android.gms.internal.ads.zznk
                @Override // java.lang.Runnable
                public final void run() {
                    zznp.this.zzj(str, j, j2);
                }
            });
        }
    }

    public final void zzd(final String str) {
        Handler handler = this.zza;
        if (handler != null) {
            handler.post(new Runnable() { // from class: com.google.android.gms.internal.ads.zznm
                @Override // java.lang.Runnable
                public final void run() {
                    zznp.this.zzk(str);
                }
            });
        }
    }

    public final void zze(final zzgq zzgqVar) {
        zzgqVar.zza();
        Handler handler = this.zza;
        if (handler != null) {
            handler.post(new Runnable() { // from class: com.google.android.gms.internal.ads.zzng
                @Override // java.lang.Runnable
                public final void run() {
                    zznp.this.zzl(zzgqVar);
                }
            });
        }
    }

    public final void zzf(final zzgq zzgqVar) {
        Handler handler = this.zza;
        if (handler != null) {
            handler.post(new Runnable() { // from class: com.google.android.gms.internal.ads.zznh
                @Override // java.lang.Runnable
                public final void run() {
                    zznp.this.zzm(zzgqVar);
                }
            });
        }
    }

    public final void zzg(final zzaf zzafVar, final zzgr zzgrVar) {
        Handler handler = this.zza;
        if (handler != null) {
            handler.post(new Runnable() { // from class: com.google.android.gms.internal.ads.zznn
                @Override // java.lang.Runnable
                public final void run() {
                    zznp.this.zzn(zzafVar, zzgrVar);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzh(Exception exc) {
        zznq zznqVar = this.zzb;
        int r1 = zzel.zza;
        zznqVar.zzc(exc);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzi(Exception exc) {
        zznq zznqVar = this.zzb;
        int r1 = zzel.zza;
        zznqVar.zzj(exc);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzj(String str, long j, long j2) {
        zznq zznqVar = this.zzb;
        int r1 = zzel.zza;
        zznqVar.zzd(str, j, j2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzk(String str) {
        zznq zznqVar = this.zzb;
        int r1 = zzel.zza;
        zznqVar.zze(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzl(zzgq zzgqVar) {
        zzgqVar.zza();
        zznq zznqVar = this.zzb;
        int r1 = zzel.zza;
        zznqVar.zzf(zzgqVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzm(zzgq zzgqVar) {
        zznq zznqVar = this.zzb;
        int r1 = zzel.zza;
        zznqVar.zzg(zzgqVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzn(zzaf zzafVar, zzgr zzgrVar) {
        int r0 = zzel.zza;
        this.zzb.zzh(zzafVar, zzgrVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzo(long j) {
        zznq zznqVar = this.zzb;
        int r1 = zzel.zza;
        zznqVar.zzi(j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzp(boolean z) {
        zznq zznqVar = this.zzb;
        int r1 = zzel.zza;
        zznqVar.zzn(z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzq(int r7, long j, long j2) {
        zznq zznqVar = this.zzb;
        int r1 = zzel.zza;
        zznqVar.zzk(r7, j, j2);
    }

    public final void zzr(final long j) {
        Handler handler = this.zza;
        if (handler != null) {
            handler.post(new Runnable() { // from class: com.google.android.gms.internal.ads.zzni
                @Override // java.lang.Runnable
                public final void run() {
                    zznp.this.zzo(j);
                }
            });
        }
    }

    public final void zzs(final boolean z) {
        Handler handler = this.zza;
        if (handler != null) {
            handler.post(new Runnable() { // from class: com.google.android.gms.internal.ads.zznj
                @Override // java.lang.Runnable
                public final void run() {
                    zznp.this.zzp(z);
                }
            });
        }
    }

    public final void zzt(final int r10, final long j, final long j2) {
        Handler handler = this.zza;
        if (handler != null) {
            handler.post(new Runnable() { // from class: com.google.android.gms.internal.ads.zzno
                @Override // java.lang.Runnable
                public final void run() {
                    zznp.this.zzq(r10, j, j2);
                }
            });
        }
    }
}
