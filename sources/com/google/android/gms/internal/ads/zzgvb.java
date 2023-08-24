package com.google.android.gms.internal.ads;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgvb {
    private final List zza;
    private final List zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzgvb(int r1, int r2, zzgva zzgvaVar) {
        this.zza = zzguo.zzc(r1);
        this.zzb = zzguo.zzc(r2);
    }

    public final zzgvb zza(zzgve zzgveVar) {
        this.zzb.add(zzgveVar);
        return this;
    }

    public final zzgvb zzb(zzgve zzgveVar) {
        this.zza.add(zzgveVar);
        return this;
    }

    public final zzgvc zzc() {
        return new zzgvc(this.zza, this.zzb, null);
    }
}
