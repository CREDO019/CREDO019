package com.google.android.gms.internal.ads;

import android.os.SystemClock;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzakl {
    public static final boolean zza = zzakm.zzb;
    private final List zzb = new ArrayList();
    private boolean zzc = false;

    protected final void finalize() throws Throwable {
        if (this.zzc) {
            return;
        }
        zzb("Request on the loose");
        zzakm.zzb("Marker log finalized without finish() - uncaught exit point for request", new Object[0]);
    }

    public final synchronized void zza(String str, long j) {
        if (this.zzc) {
            throw new IllegalStateException("Marker added to finished log");
        }
        this.zzb.add(new zzakk(str, j, SystemClock.elapsedRealtime()));
    }

    public final synchronized void zzb(String str) {
        List list;
        long j;
        this.zzc = true;
        if (this.zzb.size() == 0) {
            j = 0;
        } else {
            j = ((zzakk) this.zzb.get(list.size() - 1)).zzc - ((zzakk) this.zzb.get(0)).zzc;
        }
        if (j <= 0) {
            return;
        }
        long j2 = ((zzakk) this.zzb.get(0)).zzc;
        zzakm.zza("(%-4d ms) %s", Long.valueOf(j), str);
        for (zzakk zzakkVar : this.zzb) {
            long j3 = zzakkVar.zzc;
            zzakm.zza("(+%-4d) [%2d] %s", Long.valueOf(j3 - j2), Long.valueOf(zzakkVar.zzb), zzakkVar.zza);
            j2 = j3;
        }
    }
}
