package com.google.android.gms.internal.ads;

import android.os.Handler;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzpi {
    public final int zza;
    public final zzsg zzb;
    private final CopyOnWriteArrayList zzc;

    public zzpi() {
        this(new CopyOnWriteArrayList(), 0, null);
    }

    private zzpi(CopyOnWriteArrayList copyOnWriteArrayList, int r2, zzsg zzsgVar) {
        this.zzc = copyOnWriteArrayList;
        this.zza = r2;
        this.zzb = zzsgVar;
    }

    public final zzpi zza(int r3, zzsg zzsgVar) {
        return new zzpi(this.zzc, r3, zzsgVar);
    }

    public final void zzc(zzpj zzpjVar) {
        Iterator it = this.zzc.iterator();
        while (it.hasNext()) {
            zzph zzphVar = (zzph) it.next();
            if (zzphVar.zzb == zzpjVar) {
                this.zzc.remove(zzphVar);
            }
        }
    }

    public final void zzb(Handler handler, zzpj zzpjVar) {
        Objects.requireNonNull(zzpjVar);
        this.zzc.add(new zzph(handler, zzpjVar));
    }
}
