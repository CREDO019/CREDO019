package com.google.android.gms.internal.ads;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgui extends AbstractList {
    private static final zzguj zzc = zzguj.zzb(zzgui.class);
    final List zza;
    final Iterator zzb;

    public zzgui(List list, Iterator it) {
        this.zza = list;
        this.zzb = it;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int r3) {
        if (this.zza.size() > r3) {
            return this.zza.get(r3);
        }
        if (this.zzb.hasNext()) {
            this.zza.add(this.zzb.next());
            return get(r3);
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator iterator() {
        return new zzguh(this);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        zzguj zzgujVar = zzc;
        zzgujVar.zza("potentially expensive size() call");
        zzgujVar.zza("blowup running");
        while (this.zzb.hasNext()) {
            this.zza.add(this.zzb.next());
        }
        return this.zza.size();
    }
}
