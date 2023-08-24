package com.google.android.gms.internal.ads;

import java.util.NoSuchElementException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzgqj extends zzgmx {
    final zzgqn zza;
    zzgmz zzb = zzb();
    final /* synthetic */ zzgqp zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgqj(zzgqp zzgqpVar) {
        this.zzc = zzgqpVar;
        this.zza = new zzgqn(zzgqpVar, null);
    }

    private final zzgmz zzb() {
        zzgqn zzgqnVar = this.zza;
        if (zzgqnVar.hasNext()) {
            return zzgqnVar.next().iterator();
        }
        return null;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zzb != null;
    }

    @Override // com.google.android.gms.internal.ads.zzgmz
    public final byte zza() {
        zzgmz zzgmzVar = this.zzb;
        if (zzgmzVar == null) {
            throw new NoSuchElementException();
        }
        byte zza = zzgmzVar.zza();
        if (!this.zzb.hasNext()) {
            this.zzb = zzb();
        }
        return zza;
    }
}
