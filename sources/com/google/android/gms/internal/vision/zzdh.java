package com.google.android.gms.internal.vision;

import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: Add missing generic type declarations: [E] */
/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
public final class zzdh<E> extends zzdf<E> {
    private final transient int length;
    private final transient int offset;
    private final /* synthetic */ zzdf zzls;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzdh(zzdf zzdfVar, int r2, int r3) {
        this.zzls = zzdfVar;
        this.offset = r2;
        this.length = r3;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.length;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzdc
    public final Object[] zzbz() {
        return this.zzls.zzbz();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzdc
    public final int zzca() {
        return this.zzls.zzca() + this.offset;
    }

    @Override // com.google.android.gms.internal.vision.zzdc
    final int zzcb() {
        return this.zzls.zzca() + this.offset + this.length;
    }

    @Override // java.util.List
    public final E get(int r3) {
        zzct.zzc(r3, this.length);
        return this.zzls.get(r3 + this.offset);
    }

    @Override // com.google.android.gms.internal.vision.zzdf
    public final zzdf<E> zze(int r3, int r4) {
        zzct.zza(r3, r4, this.length);
        zzdf zzdfVar = this.zzls;
        int r1 = this.offset;
        return (zzdf) zzdfVar.subList(r3 + r1, r4 + r1);
    }

    @Override // com.google.android.gms.internal.vision.zzdf, java.util.List
    public final /* synthetic */ List subList(int r1, int r2) {
        return subList(r1, r2);
    }
}
