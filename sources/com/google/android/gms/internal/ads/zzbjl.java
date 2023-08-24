package com.google.android.gms.internal.ads;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
@ParametersAreNonnullByDefault
@Deprecated
/* loaded from: classes2.dex */
public final class zzbjl {
    private final Map zza = new HashMap();
    private final zzbjn zzb;

    public zzbjl(zzbjn zzbjnVar) {
        this.zzb = zzbjnVar;
    }

    public final zzbjn zza() {
        return this.zzb;
    }

    public final void zzb(String str, zzbjk zzbjkVar) {
        this.zza.put(str, zzbjkVar);
    }

    public final void zzc(String str, String str2, long j) {
        zzbjn zzbjnVar = this.zzb;
        zzbjk zzbjkVar = (zzbjk) this.zza.get(str2);
        String[] strArr = {str};
        if (zzbjkVar != null) {
            zzbjnVar.zze(zzbjkVar, j, strArr);
        }
        this.zza.put(str, new zzbjk(j, null, null));
    }
}
