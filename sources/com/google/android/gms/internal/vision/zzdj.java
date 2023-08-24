package com.google.android.gms.internal.vision;

import java.util.Iterator;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
public abstract class zzdj<E> extends zzdc<E> implements Set<E> {
    @NullableDecl
    private transient zzdf<E> zzlv;

    @Override // java.util.Collection, java.util.Set
    public boolean equals(@NullableDecl Object obj) {
        if (obj == this) {
            return true;
        }
        return zzdo.zza(this, obj);
    }

    @Override // java.util.Collection, java.util.Set
    public int hashCode() {
        return zzdo.zza(this);
    }

    @Override // com.google.android.gms.internal.vision.zzdc
    public zzdf<E> zzcc() {
        zzdf<E> zzdfVar = this.zzlv;
        if (zzdfVar == null) {
            zzdf<E> zzch = zzch();
            this.zzlv = zzch;
            return zzch;
        }
        return zzdfVar;
    }

    zzdf<E> zzch() {
        return zzdf.zza(toArray());
    }

    @Override // com.google.android.gms.internal.vision.zzdc, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public /* synthetic */ Iterator iterator() {
        return iterator();
    }
}
