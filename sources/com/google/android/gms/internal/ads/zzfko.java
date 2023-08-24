package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfko {
    private static final zzfko zza = new zzfko();
    private final ArrayList zzb = new ArrayList();
    private final ArrayList zzc = new ArrayList();

    private zzfko() {
    }

    public static zzfko zza() {
        return zza;
    }

    public final Collection zzb() {
        return Collections.unmodifiableCollection(this.zzc);
    }

    public final Collection zzc() {
        return Collections.unmodifiableCollection(this.zzb);
    }

    public final void zzd(zzfkd zzfkdVar) {
        this.zzb.add(zzfkdVar);
    }

    public final void zze(zzfkd zzfkdVar) {
        boolean zzg = zzg();
        this.zzb.remove(zzfkdVar);
        this.zzc.remove(zzfkdVar);
        if (!zzg || zzg()) {
            return;
        }
        zzfku.zzb().zzf();
    }

    public final void zzf(zzfkd zzfkdVar) {
        boolean zzg = zzg();
        this.zzc.add(zzfkdVar);
        if (zzg) {
            return;
        }
        zzfku.zzb().zze();
    }

    public final boolean zzg() {
        return this.zzc.size() > 0;
    }
}
