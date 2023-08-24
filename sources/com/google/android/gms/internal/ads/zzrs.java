package com.google.android.gms.internal.ads;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzrs {
    private final zzzm zza;
    private final Map zzb = new HashMap();
    private final Set zzc = new HashSet();
    private final Map zzd = new HashMap();
    private zzeu zze;

    public zzrs(zzzm zzzmVar) {
        this.zza = zzzmVar;
    }

    public final void zza(zzeu zzeuVar) {
        if (zzeuVar != this.zze) {
            this.zze = zzeuVar;
            this.zzd.clear();
        }
    }
}
