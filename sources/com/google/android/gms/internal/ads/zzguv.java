package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzguv extends zzgun {
    private static final zzgve zza = zzgus.zza(Collections.emptyMap());

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzguv(Map map, zzgut zzgutVar) {
        super(map);
    }

    public static zzguu zzc(int r2) {
        return new zzguu(r2, null);
    }

    @Override // com.google.android.gms.internal.ads.zzgve
    /* renamed from: zzd */
    public final Map zzb() {
        LinkedHashMap zzb = zzguo.zzb(zza().size());
        for (Map.Entry entry : zza().entrySet()) {
            zzb.put(entry.getKey(), ((zzgve) entry.getValue()).zzb());
        }
        return Collections.unmodifiableMap(zzb);
    }
}
