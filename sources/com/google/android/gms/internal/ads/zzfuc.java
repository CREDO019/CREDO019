package com.google.android.gms.internal.ads;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzfuc extends AbstractSet {
    final /* synthetic */ zzfuf zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfuc(zzfuf zzfufVar) {
        this.zza = zzfufVar;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        this.zza.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(@CheckForNull Object obj) {
        return this.zza.containsKey(obj);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        zzfuf zzfufVar = this.zza;
        Map zzl = zzfufVar.zzl();
        if (zzl != null) {
            return zzl.keySet().iterator();
        }
        return new zzftx(zzfufVar);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(@CheckForNull Object obj) {
        Object zzx;
        Object obj2;
        Map zzl = this.zza.zzl();
        if (zzl == null) {
            zzx = this.zza.zzx(obj);
            obj2 = zzfuf.zzd;
            return zzx != obj2;
        }
        return zzl.keySet().remove(obj);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.zza.size();
    }
}
