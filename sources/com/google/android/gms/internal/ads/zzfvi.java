package com.google.android.gms.internal.ads;

import java.io.Serializable;
import java.util.AbstractSequentialList;
import java.util.List;
import java.util.ListIterator;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzfvi extends AbstractSequentialList implements Serializable {
    final List zza;
    final zzfru zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfvi(List list, zzfru zzfruVar) {
        this.zza = list;
        this.zzb = zzfruVar;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        this.zza.clear();
    }

    @Override // java.util.AbstractSequentialList, java.util.AbstractList, java.util.List
    public final ListIterator listIterator(int r3) {
        return new zzfvh(this, this.zza.listIterator(r3));
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zza.size();
    }
}
