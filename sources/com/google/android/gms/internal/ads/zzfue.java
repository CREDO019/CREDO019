package com.google.android.gms.internal.ads;

import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzfue extends AbstractCollection {
    final /* synthetic */ zzfuf zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfue(zzfuf zzfufVar) {
        this.zza = zzfufVar;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final void clear() {
        this.zza.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        zzfuf zzfufVar = this.zza;
        Map zzl = zzfufVar.zzl();
        if (zzl != null) {
            return zzl.values().iterator();
        }
        return new zzftz(zzfufVar);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final int size() {
        return this.zza.size();
    }
}
