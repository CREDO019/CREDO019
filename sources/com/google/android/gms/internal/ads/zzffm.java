package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.HashMap;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzffm {
    private final HashMap zza = new HashMap();

    public final zzffl zza(zzffc zzffcVar, Context context, zzfeu zzfeuVar, zzffs zzffsVar) {
        zzffl zzfflVar = (zzffl) this.zza.get(zzffcVar);
        if (zzfflVar == null) {
            zzfez zzfezVar = new zzfez(zzfff.zza(zzffcVar, context));
            zzffl zzfflVar2 = new zzffl(zzfezVar, new zzffu(zzfezVar, zzfeuVar, zzffsVar));
            this.zza.put(zzffcVar, zzfflVar2);
            return zzfflVar2;
        }
        return zzfflVar;
    }
}
