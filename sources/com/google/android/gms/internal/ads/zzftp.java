package com.google.android.gms.internal.ads;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import javax.annotation.CheckForNull;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public class zzftp extends zzftn implements List {
    final /* synthetic */ zzftq zzf;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzftp(zzftq zzftqVar, Object obj, @CheckForNull List list, zzftn zzftnVar) {
        super(zzftqVar, obj, list, zzftnVar);
        this.zzf = zzftqVar;
    }

    @Override // java.util.List
    public final void add(int r3, Object obj) {
        zzb();
        boolean isEmpty = this.zzb.isEmpty();
        ((List) this.zzb).add(r3, obj);
        zzftq.zzd(this.zzf);
        if (isEmpty) {
            zza();
        }
    }

    @Override // java.util.List
    public final boolean addAll(int r3, Collection collection) {
        if (collection.isEmpty()) {
            return false;
        }
        int size = size();
        boolean addAll = ((List) this.zzb).addAll(r3, collection);
        if (addAll) {
            zzftq.zzf(this.zzf, this.zzb.size() - size);
            if (size == 0) {
                zza();
                return true;
            }
            return addAll;
        }
        return addAll;
    }

    @Override // java.util.List
    public final Object get(int r2) {
        zzb();
        return ((List) this.zzb).get(r2);
    }

    @Override // java.util.List
    public final int indexOf(@CheckForNull Object obj) {
        zzb();
        return ((List) this.zzb).indexOf(obj);
    }

    @Override // java.util.List
    public final int lastIndexOf(@CheckForNull Object obj) {
        zzb();
        return ((List) this.zzb).lastIndexOf(obj);
    }

    @Override // java.util.List
    public final ListIterator listIterator() {
        zzb();
        return new zzfto(this);
    }

    @Override // java.util.List
    public final Object remove(int r2) {
        zzb();
        Object remove = ((List) this.zzb).remove(r2);
        zzftq.zze(this.zzf);
        zzc();
        return remove;
    }

    @Override // java.util.List
    public final Object set(int r2, Object obj) {
        zzb();
        return ((List) this.zzb).set(r2, obj);
    }

    @Override // java.util.List
    public final List subList(int r4, int r5) {
        zzb();
        zzftq zzftqVar = this.zzf;
        Object obj = this.zza;
        List subList = ((List) this.zzb).subList(r4, r5);
        zzftn zzftnVar = this.zzc;
        if (zzftnVar == null) {
            zzftnVar = this;
        }
        return zzftqVar.zzk(obj, subList, zzftnVar);
    }

    @Override // java.util.List
    public final ListIterator listIterator(int r2) {
        zzb();
        return new zzfto(this, r2);
    }
}
