package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdui implements zzgur {
    private final zzgve zza;

    public zzdui(zzgve zzgveVar) {
        this.zza = zzgveVar;
    }

    @Override // com.google.android.gms.internal.ads.zzgve
    public final /* bridge */ /* synthetic */ Object zzb() {
        zzbev zzbevVar;
        if (((zzdcp) this.zza).zza().zzo.zza == 3) {
            zzbevVar = zzbev.REWARDED_INTERSTITIAL;
        } else {
            zzbevVar = zzbev.REWARD_BASED_VIDEO_AD;
        }
        zzguz.zzb(zzbevVar);
        return zzbevVar;
    }
}
