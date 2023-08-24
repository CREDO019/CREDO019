package com.google.android.gms.internal.ads;

import java.util.AbstractMap;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzfwf extends zzfuv {
    final /* synthetic */ zzfwg zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfwf(zzfwg zzfwgVar) {
        this.zza = zzfwgVar;
    }

    @Override // java.util.List
    public final /* bridge */ /* synthetic */ Object get(int r3) {
        int r0;
        Object[] objArr;
        Object[] objArr2;
        r0 = this.zza.zzc;
        zzfsf.zza(r3, r0, "index");
        zzfwg zzfwgVar = this.zza;
        int r32 = r3 + r3;
        objArr = zzfwgVar.zzb;
        Object obj = objArr[r32];
        obj.getClass();
        objArr2 = zzfwgVar.zzb;
        Object obj2 = objArr2[r32 + 1];
        obj2.getClass();
        return new AbstractMap.SimpleImmutableEntry(obj, obj2);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        int r0;
        r0 = this.zza.zzc;
        return r0;
    }

    @Override // com.google.android.gms.internal.ads.zzfuq
    public final boolean zzf() {
        return true;
    }
}
