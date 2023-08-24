package com.google.android.gms.internal.ads;

import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.CheckForNull;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzfxo extends zzfxt {
    private static final Logger zza = Logger.getLogger(zzfxo.class.getName());
    @CheckForNull
    private zzfuq zzb;
    private final boolean zzc;
    private final boolean zze;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfxo(zzfuq zzfuqVar, boolean z, boolean z2) {
        super(zzfuqVar.size());
        Objects.requireNonNull(zzfuqVar);
        this.zzb = zzfuqVar;
        this.zzc = z;
        this.zze = z2;
    }

    private final void zzG(int r1, Future future) {
        try {
            zzg(r1, zzfyo.zzp(future));
        } catch (Error e) {
            e = e;
            zzI(e);
        } catch (RuntimeException e2) {
            e = e2;
            zzI(e);
        } catch (ExecutionException e3) {
            zzI(e3.getCause());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: zzH */
    public final void zzy(@CheckForNull zzfuq zzfuqVar) {
        int zzB = zzB();
        int r1 = 0;
        zzfsf.zzi(zzB >= 0, "Less than 0 remaining futures");
        if (zzB == 0) {
            if (zzfuqVar != null) {
                zzfwu it = zzfuqVar.iterator();
                while (it.hasNext()) {
                    Future future = (Future) it.next();
                    if (!future.isCancelled()) {
                        zzG(r1, future);
                    }
                    r1++;
                }
            }
            zzF();
            zzv();
            zzz(2);
        }
    }

    private static void zzJ(Throwable th) {
        zza.logp(Level.SEVERE, "com.google.common.util.concurrent.AggregateFuture", "log", true != (th instanceof Error) ? "Got more than one input Future failure. Logging failures after the first" : "Input Future failed with Error", th);
    }

    private static boolean zzK(Set set, Throwable th) {
        while (th != null) {
            if (!set.add(th)) {
                return false;
            }
            th = th.getCause();
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzfxf
    @CheckForNull
    public final String zza() {
        zzfuq zzfuqVar = this.zzb;
        if (zzfuqVar != null) {
            Objects.toString(zzfuqVar);
            return "futures=".concat(zzfuqVar.toString());
        }
        return super.zza();
    }

    @Override // com.google.android.gms.internal.ads.zzfxf
    protected final void zzb() {
        zzfuq zzfuqVar = this.zzb;
        zzz(1);
        if ((zzfuqVar != null) && isCancelled()) {
            boolean zzu = zzu();
            zzfwu it = zzfuqVar.iterator();
            while (it.hasNext()) {
                ((Future) it.next()).cancel(zzu);
            }
        }
    }

    abstract void zzg(int r1, Object obj);

    abstract void zzv();

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzw() {
        zzfuq zzfuqVar = this.zzb;
        zzfuqVar.getClass();
        if (zzfuqVar.isEmpty()) {
            zzv();
        } else if (this.zzc) {
            zzfwu it = this.zzb.iterator();
            final int r1 = 0;
            while (it.hasNext()) {
                final zzfyx zzfyxVar = (zzfyx) it.next();
                zzfyxVar.zzc(new Runnable() { // from class: com.google.android.gms.internal.ads.zzfxm
                    @Override // java.lang.Runnable
                    public final void run() {
                        zzfxo.this.zzx(zzfyxVar, r1);
                    }
                }, zzfyc.INSTANCE);
                r1++;
            }
        } else {
            final zzfuq zzfuqVar2 = this.zze ? this.zzb : null;
            Runnable runnable = new Runnable() { // from class: com.google.android.gms.internal.ads.zzfxn
                @Override // java.lang.Runnable
                public final void run() {
                    zzfxo.this.zzy(zzfuqVar2);
                }
            };
            zzfwu it2 = this.zzb.iterator();
            while (it2.hasNext()) {
                ((zzfyx) it2.next()).zzc(runnable, zzfyc.INSTANCE);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzx(zzfyx zzfyxVar, int r4) {
        try {
            if (zzfyxVar.isCancelled()) {
                this.zzb = null;
                cancel(false);
            } else {
                zzG(r4, zzfyxVar);
            }
        } finally {
            zzy(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void zzz(int r1) {
        this.zzb = null;
    }

    @Override // com.google.android.gms.internal.ads.zzfxt
    final void zzf(Set set) {
        Objects.requireNonNull(set);
        if (isCancelled()) {
            return;
        }
        Throwable zzp = zzp();
        zzp.getClass();
        zzK(set, zzp);
    }

    private final void zzI(Throwable th) {
        Objects.requireNonNull(th);
        if (!this.zzc || zze(th) || !zzK(zzE(), th)) {
            if (th instanceof Error) {
                zzJ(th);
                return;
            }
            return;
        }
        zzJ(th);
    }
}
