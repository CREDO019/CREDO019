package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfss {
    private final zzfrr zza;
    private final zzfsr zzb;

    private zzfss(zzfsr zzfsrVar) {
        zzfrq zzfrqVar = zzfrq.zza;
        this.zzb = zzfsrVar;
        this.zza = zzfrqVar;
    }

    public static zzfss zzb(int r2) {
        return new zzfss(new zzfso(4000));
    }

    public static zzfss zzc(zzfrr zzfrrVar) {
        return new zzfss(new zzfsm(zzfrrVar));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Iterator zzg(CharSequence charSequence) {
        return this.zzb.zza(this, charSequence);
    }

    public final Iterable zzd(CharSequence charSequence) {
        Objects.requireNonNull(charSequence);
        return new zzfsp(this, charSequence);
    }

    public final List zzf(CharSequence charSequence) {
        Iterator zzg = zzg(charSequence);
        ArrayList arrayList = new ArrayList();
        while (zzg.hasNext()) {
            arrayList.add((String) zzg.next());
        }
        return Collections.unmodifiableList(arrayList);
    }
}
