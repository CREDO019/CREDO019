package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfxw extends zzfxy {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfxw(zzfuq zzfuqVar, boolean z) {
        super(zzfuqVar, true);
        zzw();
    }

    @Override // com.google.android.gms.internal.ads.zzfxy
    public final /* bridge */ /* synthetic */ Object zzG(List list) {
        ArrayList zza = zzfvj.zza(list.size());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            zzfxx zzfxxVar = (zzfxx) it.next();
            zza.add(zzfxxVar != null ? zzfxxVar.zza : null);
        }
        return Collections.unmodifiableList(zza);
    }
}
