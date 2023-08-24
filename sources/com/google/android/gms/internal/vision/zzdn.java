package com.google.android.gms.internal.vision;

import java.util.AbstractMap;
import java.util.Map;

/* JADX INFO: Add missing generic type declarations: [V, K] */
/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
final class zzdn<K, V> extends zzdf<Map.Entry<K, V>> {
    private final /* synthetic */ zzdk zzmb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzdn(zzdk zzdkVar) {
        this.zzmb = zzdkVar;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        int r0;
        r0 = this.zzmb.size;
        return r0;
    }

    @Override // java.util.List
    public final /* synthetic */ Object get(int r3) {
        int r0;
        Object[] objArr;
        Object[] objArr2;
        r0 = this.zzmb.size;
        zzct.zzc(r3, r0);
        objArr = this.zzmb.zzlx;
        int r32 = r3 * 2;
        zzdk zzdkVar = this.zzmb;
        Object obj = objArr[r32];
        objArr2 = zzdkVar.zzlx;
        return new AbstractMap.SimpleImmutableEntry(obj, objArr2[r32 + 1]);
    }
}
