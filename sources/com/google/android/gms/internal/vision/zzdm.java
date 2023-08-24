package com.google.android.gms.internal.vision;

import java.util.Iterator;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
final class zzdm<K> extends zzdj<K> {
    private final transient zzdf<K> zzlm;
    private final transient zzdg<K, ?> zzlw;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzdm(zzdg<K, ?> zzdgVar, zzdf<K> zzdfVar) {
        this.zzlw = zzdgVar;
        this.zzlm = zzdfVar;
    }

    @Override // com.google.android.gms.internal.vision.zzdc
    public final zzdr<K> zzby() {
        return (zzdr) zzcc().iterator();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzdc
    public final int zza(Object[] objArr, int r3) {
        return zzcc().zza(objArr, r3);
    }

    @Override // com.google.android.gms.internal.vision.zzdj, com.google.android.gms.internal.vision.zzdc
    public final zzdf<K> zzcc() {
        return this.zzlm;
    }

    @Override // com.google.android.gms.internal.vision.zzdc, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(@NullableDecl Object obj) {
        return this.zzlw.get(obj) != null;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.zzlw.size();
    }

    @Override // com.google.android.gms.internal.vision.zzdj, com.google.android.gms.internal.vision.zzdc, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final /* synthetic */ Iterator iterator() {
        return iterator();
    }
}
