package com.google.android.gms.internal.measurement;

import java.util.NoSuchElementException;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.2 */
/* loaded from: classes3.dex */
final class zzis extends zziu {
    final /* synthetic */ zzjb zza;
    private int zzb = 0;
    private final int zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzis(zzjb zzjbVar) {
        this.zza = zzjbVar;
        this.zzc = zzjbVar.zzd();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zzb < this.zzc;
    }

    @Override // com.google.android.gms.internal.measurement.zziw
    public final byte zza() {
        int r0 = this.zzb;
        if (r0 >= this.zzc) {
            throw new NoSuchElementException();
        }
        this.zzb = r0 + 1;
        return this.zza.zzb(r0);
    }
}
