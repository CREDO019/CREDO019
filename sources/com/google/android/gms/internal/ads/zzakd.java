package com.google.android.gms.internal.ads;

import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzakd {
    private final AtomicInteger zza;
    private final Set zzb;
    private final PriorityBlockingQueue zzc;
    private final PriorityBlockingQueue zzd;
    private final zzajk zze;
    private final zzajt zzf;
    private final zzaju[] zzg;
    private zzajm zzh;
    private final List zzi;
    private final List zzj;
    private final zzajr zzk;

    public zzakd(zzajk zzajkVar, zzajt zzajtVar, int r5) {
        zzajr zzajrVar = new zzajr(new Handler(Looper.getMainLooper()));
        this.zza = new AtomicInteger();
        this.zzb = new HashSet();
        this.zzc = new PriorityBlockingQueue();
        this.zzd = new PriorityBlockingQueue();
        this.zzi = new ArrayList();
        this.zzj = new ArrayList();
        this.zze = zzajkVar;
        this.zzf = zzajtVar;
        this.zzg = new zzaju[4];
        this.zzk = zzajrVar;
    }

    public final zzaka zza(zzaka zzakaVar) {
        zzakaVar.zzf(this);
        synchronized (this.zzb) {
            this.zzb.add(zzakaVar);
        }
        zzakaVar.zzg(this.zza.incrementAndGet());
        zzakaVar.zzm("add-to-queue");
        zzc(zzakaVar, 0);
        this.zzc.add(zzakaVar);
        return zzakaVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzb(zzaka zzakaVar) {
        synchronized (this.zzb) {
            this.zzb.remove(zzakaVar);
        }
        synchronized (this.zzi) {
            for (zzakc zzakcVar : this.zzi) {
                zzakcVar.zza();
            }
        }
        zzc(zzakaVar, 5);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzc(zzaka zzakaVar, int r3) {
        synchronized (this.zzj) {
            for (zzakb zzakbVar : this.zzj) {
                zzakbVar.zza();
            }
        }
    }

    public final void zzd() {
        zzajm zzajmVar = this.zzh;
        if (zzajmVar != null) {
            zzajmVar.zzb();
        }
        zzaju[] zzajuVarArr = this.zzg;
        for (int r2 = 0; r2 < 4; r2++) {
            zzaju zzajuVar = zzajuVarArr[r2];
            if (zzajuVar != null) {
                zzajuVar.zza();
            }
        }
        zzajm zzajmVar2 = new zzajm(this.zzc, this.zzd, this.zze, this.zzk, null);
        this.zzh = zzajmVar2;
        zzajmVar2.start();
        for (int r1 = 0; r1 < 4; r1++) {
            zzaju zzajuVar2 = new zzaju(this.zzd, this.zzf, this.zze, this.zzk, null);
            this.zzg[r1] = zzajuVar2;
            zzajuVar2.start();
        }
    }
}
