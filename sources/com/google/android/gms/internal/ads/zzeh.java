package com.google.android.gms.internal.ads;

import android.os.Handler;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzeh implements zzdn {
    private static final List zza = new ArrayList(50);
    private final Handler zzb;

    public zzeh(Handler handler) {
        this.zzb = handler;
    }

    public static /* bridge */ /* synthetic */ void zzk(zzeg zzegVar) {
        List list = zza;
        synchronized (list) {
            if (list.size() < 50) {
                list.add(zzegVar);
            }
        }
    }

    private static zzeg zzl() {
        zzeg zzegVar;
        List list = zza;
        synchronized (list) {
            zzegVar = list.isEmpty() ? new zzeg(null) : (zzeg) list.remove(list.size() - 1);
        }
        return zzegVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdn
    public final zzdm zza(int r3) {
        zzeg zzl = zzl();
        zzl.zzb(this.zzb.obtainMessage(r3), this);
        return zzl;
    }

    @Override // com.google.android.gms.internal.ads.zzdn
    public final zzdm zzb(int r3, Object obj) {
        zzeg zzl = zzl();
        zzl.zzb(this.zzb.obtainMessage(r3, obj), this);
        return zzl;
    }

    @Override // com.google.android.gms.internal.ads.zzdn
    public final zzdm zzc(int r3, int r4, int r5) {
        zzeg zzl = zzl();
        zzl.zzb(this.zzb.obtainMessage(1, r4, r5), this);
        return zzl;
    }

    @Override // com.google.android.gms.internal.ads.zzdn
    public final void zzd(Object obj) {
        this.zzb.removeCallbacksAndMessages(null);
    }

    @Override // com.google.android.gms.internal.ads.zzdn
    public final void zze(int r2) {
        this.zzb.removeMessages(2);
    }

    @Override // com.google.android.gms.internal.ads.zzdn
    public final boolean zzf(int r2) {
        return this.zzb.hasMessages(0);
    }

    @Override // com.google.android.gms.internal.ads.zzdn
    public final boolean zzg(Runnable runnable) {
        return this.zzb.post(runnable);
    }

    @Override // com.google.android.gms.internal.ads.zzdn
    public final boolean zzh(int r2) {
        return this.zzb.sendEmptyMessage(r2);
    }

    @Override // com.google.android.gms.internal.ads.zzdn
    public final boolean zzi(int r2, long j) {
        return this.zzb.sendEmptyMessageAtTime(2, j);
    }

    @Override // com.google.android.gms.internal.ads.zzdn
    public final boolean zzj(zzdm zzdmVar) {
        return ((zzeg) zzdmVar).zzc(this.zzb);
    }
}
