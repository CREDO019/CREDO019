package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcyf implements zzgur {
    private final zzgve zza;

    public zzcyf(zzgve zzgveVar) {
        this.zza = zzgveVar;
    }

    @Override // com.google.android.gms.internal.ads.zzgve
    /* renamed from: zza */
    public final Boolean zzb() {
        boolean z = true;
        if (((zzdcp) this.zza).zza().zza() == null) {
            if (!((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzeC)).booleanValue()) {
                z = false;
            }
        }
        return Boolean.valueOf(z);
    }
}
