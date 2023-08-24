package com.google.android.gms.internal.ads;

import java.util.NoSuchElementException;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgmv extends zzgmx {
    final /* synthetic */ zzgnf zza;
    private int zzb = 0;
    private final int zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgmv(zzgnf zzgnfVar) {
        this.zza = zzgnfVar;
        this.zzc = zzgnfVar.zzd();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zzb < this.zzc;
    }

    @Override // com.google.android.gms.internal.ads.zzgmz
    public final byte zza() {
        int r0 = this.zzb;
        if (r0 >= this.zzc) {
            throw new NoSuchElementException();
        }
        this.zzb = r0 + 1;
        return this.zza.zzb(r0);
    }
}
