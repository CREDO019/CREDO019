package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzfxf;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
abstract class zzfxt extends zzfxf.zzi {
    private static final zzfxq zzaX;
    private static final Logger zzaY = Logger.getLogger(zzfxt.class.getName());
    private volatile int remaining;
    @CheckForNull
    private volatile Set<Throwable> seenExceptions = null;

    static {
        zzfxq zzfxsVar;
        Throwable th;
        try {
            zzfxsVar = new zzfxr(AtomicReferenceFieldUpdater.newUpdater(zzfxt.class, Set.class, "seenExceptions"), AtomicIntegerFieldUpdater.newUpdater(zzfxt.class, "remaining"));
            th = null;
        } catch (Error | RuntimeException e) {
            zzfxsVar = new zzfxs(null);
            th = e;
        }
        zzaX = zzfxsVar;
        if (th != null) {
            zzaY.logp(Level.SEVERE, "com.google.common.util.concurrent.AggregateFutureState", "<clinit>", "SafeAtomicHelper is broken!", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfxt(int r2) {
        this.remaining = r2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int zzA(zzfxt zzfxtVar) {
        int r0 = zzfxtVar.remaining - 1;
        zzfxtVar.remaining = r0;
        return r0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int zzB() {
        return zzaX.zza(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Set zzE() {
        Set<Throwable> set = this.seenExceptions;
        if (set == null) {
            Set newSetFromMap = Collections.newSetFromMap(new ConcurrentHashMap());
            zzf(newSetFromMap);
            zzaX.zzb(this, null, newSetFromMap);
            Set<Throwable> set2 = this.seenExceptions;
            set2.getClass();
            return set2;
        }
        return set;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzF() {
        this.seenExceptions = null;
    }

    abstract void zzf(Set set);
}
