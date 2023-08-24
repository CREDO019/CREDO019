package com.google.android.gms.internal.ads;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
abstract class zzfub implements Iterator {
    int zzb;
    int zzc;
    int zzd;
    final /* synthetic */ zzfuf zze;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzfub(zzfuf zzfufVar, zzftx zzftxVar) {
        int r2;
        this.zze = zzfufVar;
        r2 = zzfufVar.zzf;
        this.zzb = r2;
        this.zzc = zzfufVar.zze();
        this.zzd = -1;
    }

    private final void zzb() {
        int r0;
        r0 = this.zze.zzf;
        if (r0 != this.zzb) {
            throw new ConcurrentModificationException();
        }
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zzc >= 0;
    }

    @Override // java.util.Iterator
    public final Object next() {
        zzb();
        if (hasNext()) {
            int r0 = this.zzc;
            this.zzd = r0;
            Object zza = zza(r0);
            this.zzc = this.zze.zzf(this.zzc);
            return zza;
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.Iterator
    public final void remove() {
        zzb();
        zzfsf.zzi(this.zzd >= 0, "no calls to next() since the last call to remove()");
        this.zzb += 32;
        zzfuf zzfufVar = this.zze;
        zzfufVar.remove(zzfuf.zzg(zzfufVar, this.zzd));
        this.zzc--;
        this.zzd = -1;
    }

    abstract Object zza(int r1);
}
