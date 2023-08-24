package com.google.android.gms.internal.ads;

import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzgrl implements Iterator {
    final Iterator zza;
    final /* synthetic */ zzgrm zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgrl(zzgrm zzgrmVar) {
        zzgpf zzgpfVar;
        this.zzb = zzgrmVar;
        zzgpfVar = zzgrmVar.zza;
        this.zza = zzgpfVar.iterator();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zza.hasNext();
    }

    @Override // java.util.Iterator
    public final /* bridge */ /* synthetic */ Object next() {
        return (String) this.zza.next();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
