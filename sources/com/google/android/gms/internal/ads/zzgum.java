package com.google.android.gms.internal.ads;

import java.util.LinkedHashMap;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public class zzgum {
    final LinkedHashMap zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgum(int r1) {
        this.zza = zzguo.zzb(r1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzgum zza(Object obj, zzgve zzgveVar) {
        LinkedHashMap linkedHashMap = this.zza;
        zzguz.zza(obj, "key");
        zzguz.zza(zzgveVar, "provider");
        linkedHashMap.put(obj, zzgveVar);
        return this;
    }
}
