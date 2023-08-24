package com.google.android.gms.internal.ads;

import java.lang.ref.WeakReference;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcor implements zzgur {
    private final zzcon zza;

    public zzcor(zzcon zzconVar) {
        this.zza = zzconVar;
    }

    public final WeakReference zza() {
        WeakReference zzf = this.zza.zzf();
        zzguz.zzb(zzf);
        return zzf;
    }

    @Override // com.google.android.gms.internal.ads.zzgve
    public final /* synthetic */ Object zzb() {
        WeakReference zzf = this.zza.zzf();
        zzguz.zzb(zzf);
        return zzf;
    }
}
