package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzarm implements Callable {
    protected final String zza = getClass().getSimpleName();
    protected final zzaqb zzb;
    protected final String zzc;
    protected final String zzd;
    protected final zzamh zze;
    protected Method zzf;
    protected final int zzg;
    protected final int zzh;

    public zzarm(zzaqb zzaqbVar, String str, String str2, zzamh zzamhVar, int r6, int r7) {
        this.zzb = zzaqbVar;
        this.zzc = str;
        this.zzd = str2;
        this.zze = zzamhVar;
        this.zzg = r6;
        this.zzh = r7;
    }

    @Override // java.util.concurrent.Callable
    public /* bridge */ /* synthetic */ Object call() throws Exception {
        zzk();
        return null;
    }

    protected abstract void zza() throws IllegalAccessException, InvocationTargetException;

    public Void zzk() throws Exception {
        long nanoTime;
        Method zzj;
        int r6;
        try {
            nanoTime = System.nanoTime();
            zzj = this.zzb.zzj(this.zzc, this.zzd);
            this.zzf = zzj;
        } catch (IllegalAccessException | InvocationTargetException unused) {
        }
        if (zzj == null) {
            return null;
        }
        zza();
        zzaow zzd = this.zzb.zzd();
        if (zzd != null && (r6 = this.zzg) != Integer.MIN_VALUE) {
            zzd.zzc(this.zzh, r6, (System.nanoTime() - nanoTime) / 1000, null, null);
        }
        return null;
    }
}
