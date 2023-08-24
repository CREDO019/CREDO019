package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcyi extends zzcxa {
    private final zzbnr zzc;
    private final Runnable zzd;
    private final Executor zze;

    public zzcyi(zzczb zzczbVar, zzbnr zzbnrVar, Runnable runnable, Executor executor) {
        super(zzczbVar);
        this.zzc = zzbnrVar;
        this.zzd = runnable;
        this.zze = executor;
    }

    public static /* synthetic */ void zzi(AtomicReference atomicReference) {
        Runnable runnable = (Runnable) atomicReference.getAndSet(null);
        if (runnable != null) {
            runnable.run();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzczc
    public final void zzW() {
        final zzcyg zzcygVar = new zzcyg(new AtomicReference(this.zzd));
        this.zze.execute(new Runnable() { // from class: com.google.android.gms.internal.ads.zzcyh
            @Override // java.lang.Runnable
            public final void run() {
                zzcyi.this.zzk(zzcygVar);
            }
        });
    }

    @Override // com.google.android.gms.internal.ads.zzcxa
    public final int zza() {
        return 0;
    }

    @Override // com.google.android.gms.internal.ads.zzcxa
    public final View zzc() {
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzcxa
    public final com.google.android.gms.ads.internal.client.zzdk zzd() {
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzcxa
    public final zzfct zze() {
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzcxa
    public final zzfct zzf() {
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzcxa
    public final void zzg() {
    }

    @Override // com.google.android.gms.internal.ads.zzcxa
    public final void zzh(ViewGroup viewGroup, com.google.android.gms.ads.internal.client.zzq zzqVar) {
    }

    public final /* synthetic */ void zzk(Runnable runnable) {
        try {
            if (this.zzc.zzb(ObjectWrapper.wrap(runnable))) {
                return;
            }
            zzi(((zzcyg) runnable).zza);
        } catch (RemoteException unused) {
            zzi(((zzcyg) runnable).zza);
        }
    }
}
