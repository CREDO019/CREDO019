package com.google.android.gms.internal.ads;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
@ParametersAreNonnullByDefault
/* loaded from: classes2.dex */
public final class zzbcc {
    int zza;
    private final Object zzb = new Object();
    private final List zzc = new LinkedList();

    public final zzbcb zza(boolean z) {
        synchronized (this.zzb) {
            zzbcb zzbcbVar = null;
            if (this.zzc.isEmpty()) {
                com.google.android.gms.ads.internal.util.zze.zze("Queue empty");
                return null;
            }
            int r4 = 0;
            if (this.zzc.size() >= 2) {
                int r1 = Integer.MIN_VALUE;
                int r3 = 0;
                for (zzbcb zzbcbVar2 : this.zzc) {
                    int zzb = zzbcbVar2.zzb();
                    if (zzb > r1) {
                        r4 = r3;
                    }
                    int r7 = zzb > r1 ? zzb : r1;
                    if (zzb > r1) {
                        zzbcbVar = zzbcbVar2;
                    }
                    r3++;
                    r1 = r7;
                }
                this.zzc.remove(r4);
                return zzbcbVar;
            }
            zzbcb zzbcbVar3 = (zzbcb) this.zzc.get(0);
            if (z) {
                this.zzc.remove(0);
            } else {
                zzbcbVar3.zzi();
            }
            return zzbcbVar3;
        }
    }

    public final void zzb(zzbcb zzbcbVar) {
        synchronized (this.zzb) {
            if (this.zzc.size() >= 10) {
                int size = this.zzc.size();
                com.google.android.gms.ads.internal.util.zze.zze("Queue is full, current size = " + size);
                this.zzc.remove(0);
            }
            int r1 = this.zza;
            this.zza = r1 + 1;
            zzbcbVar.zzj(r1);
            zzbcbVar.zzn();
            this.zzc.add(zzbcbVar);
        }
    }

    public final boolean zzc(zzbcb zzbcbVar) {
        synchronized (this.zzb) {
            Iterator it = this.zzc.iterator();
            while (it.hasNext()) {
                zzbcb zzbcbVar2 = (zzbcb) it.next();
                if (!com.google.android.gms.ads.internal.zzt.zzp().zzh().zzM()) {
                    if (zzbcbVar != zzbcbVar2 && zzbcbVar2.zzd().equals(zzbcbVar.zzd())) {
                        it.remove();
                        return true;
                    }
                } else if (!com.google.android.gms.ads.internal.zzt.zzp().zzh().zzN() && zzbcbVar != zzbcbVar2 && zzbcbVar2.zzf().equals(zzbcbVar.zzf())) {
                    it.remove();
                    return true;
                }
            }
            return false;
        }
    }

    public final boolean zzd(zzbcb zzbcbVar) {
        synchronized (this.zzb) {
            return this.zzc.contains(zzbcbVar);
        }
    }
}
