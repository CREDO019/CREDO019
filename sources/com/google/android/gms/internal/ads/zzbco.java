package com.google.android.gms.internal.ads;

import java.util.Comparator;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzbco implements Comparator {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbco(zzbcq zzbcqVar) {
    }

    @Override // java.util.Comparator
    public final /* bridge */ /* synthetic */ int compare(Object obj, Object obj2) {
        zzbcu zzbcuVar = (zzbcu) obj;
        zzbcu zzbcuVar2 = (zzbcu) obj2;
        int r0 = zzbcuVar.zzc - zzbcuVar2.zzc;
        return r0 != 0 ? r0 : (int) (zzbcuVar.zza - zzbcuVar2.zza);
    }
}
