package com.google.android.gms.internal.ads;

import android.os.Handler;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzwc {
    private final CopyOnWriteArrayList zza = new CopyOnWriteArrayList();

    public final void zza(Handler handler, zzwd zzwdVar) {
        zzc(zzwdVar);
        this.zza.add(new zzwb(handler, zzwdVar));
    }

    public final void zzb(final int r11, final long j, final long j2) {
        boolean z;
        Handler handler;
        Iterator it = this.zza.iterator();
        while (it.hasNext()) {
            final zzwb zzwbVar = (zzwb) it.next();
            z = zzwbVar.zzc;
            if (!z) {
                handler = zzwbVar.zza;
                handler.post(new Runnable() { // from class: com.google.android.gms.internal.ads.zzwa
                    @Override // java.lang.Runnable
                    public final void run() {
                        zzwd zzwdVar;
                        zzwb zzwbVar2 = zzwb.this;
                        int r2 = r11;
                        long j3 = j;
                        long j4 = j2;
                        zzwdVar = zzwbVar2.zzb;
                        zzwdVar.zzY(r2, j3, j4);
                    }
                });
            }
        }
    }

    public final void zzc(zzwd zzwdVar) {
        zzwd zzwdVar2;
        Iterator it = this.zza.iterator();
        while (it.hasNext()) {
            zzwb zzwbVar = (zzwb) it.next();
            zzwdVar2 = zzwbVar.zzb;
            if (zzwdVar2 == zzwdVar) {
                zzwbVar.zzc();
                this.zza.remove(zzwbVar);
            }
        }
    }
}
