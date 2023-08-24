package com.google.android.gms.internal.ads;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzgqn implements Iterator {
    private final ArrayDeque zza;
    private zzgna zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzgqn(zzgnf zzgnfVar, zzgqm zzgqmVar) {
        zzgnf zzgnfVar2;
        if (zzgnfVar instanceof zzgqp) {
            zzgqp zzgqpVar = (zzgqp) zzgnfVar;
            ArrayDeque arrayDeque = new ArrayDeque(zzgqpVar.zzf());
            this.zza = arrayDeque;
            arrayDeque.push(zzgqpVar);
            zzgnfVar2 = zzgqpVar.zzd;
            this.zzb = zzb(zzgnfVar2);
            return;
        }
        this.zza = null;
        this.zzb = (zzgna) zzgnfVar;
    }

    private final zzgna zzb(zzgnf zzgnfVar) {
        while (zzgnfVar instanceof zzgqp) {
            zzgqp zzgqpVar = (zzgqp) zzgnfVar;
            this.zza.push(zzgqpVar);
            zzgnfVar = zzgqpVar.zzd;
        }
        return (zzgna) zzgnfVar;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zzb != null;
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Iterator
    /* renamed from: zza */
    public final zzgna next() {
        zzgna zzgnaVar;
        zzgnf zzgnfVar;
        zzgna zzgnaVar2 = this.zzb;
        if (zzgnaVar2 == null) {
            throw new NoSuchElementException();
        }
        do {
            ArrayDeque arrayDeque = this.zza;
            zzgnaVar = null;
            if (arrayDeque == null || arrayDeque.isEmpty()) {
                break;
            }
            zzgnfVar = ((zzgqp) this.zza.pop()).zze;
            zzgnaVar = zzb(zzgnfVar);
        } while (zzgnaVar.zzD());
        this.zzb = zzgnaVar;
        return zzgnaVar2;
    }
}
