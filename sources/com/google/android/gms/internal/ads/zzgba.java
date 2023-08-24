package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgba {
    private final ConcurrentMap zza;
    private final zzgau zzb;
    private final Class zzc;
    private final zzgfc zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzgba(ConcurrentMap concurrentMap, zzgau zzgauVar, zzgfc zzgfcVar, Class cls, zzgaz zzgazVar) {
        this.zza = concurrentMap;
        this.zzb = zzgauVar;
        this.zzc = cls;
        this.zzd = zzgfcVar;
    }

    @Nullable
    public final zzgau zza() {
        return this.zzb;
    }

    public final Class zzb() {
        return this.zzc;
    }

    public final List zzc(byte[] bArr) {
        List list = (List) this.zza.get(new zzgaw(bArr, null));
        return list != null ? list : Collections.emptyList();
    }
}
