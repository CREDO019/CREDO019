package com.google.android.gms.ads.nonagon.signalgeneration;

import com.google.android.gms.ads.internal.client.zzay;
import com.google.android.gms.internal.ads.zzbiy;
import com.google.android.gms.internal.ads.zzdcf;
import com.google.android.gms.internal.ads.zzfhj;
import com.google.android.gms.internal.ads.zzfhp;
import com.google.android.gms.internal.ads.zzgur;
import com.google.android.gms.internal.ads.zzgve;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzai implements zzgur {
    private final zzgve zza;
    private final zzgve zzb;
    private final zzgve zzc;

    public zzai(zzgve zzgveVar, zzgve zzgveVar2, zzgve zzgveVar3) {
        this.zza = zzgveVar;
        this.zzb = zzgveVar2;
        this.zzc = zzgveVar3;
    }

    @Override // com.google.android.gms.internal.ads.zzgve
    public final /* bridge */ /* synthetic */ Object zzb() {
        return ((zzfhp) this.zza.zzb()).zzb(zzfhj.GENERATE_SIGNALS, ((zzdcf) this.zzc).zzb().zzc()).zzf(((zzal) this.zzb).zzb()).zzi(((Integer) zzay.zzc().zzb(zzbiy.zzeB)).intValue(), TimeUnit.SECONDS).zza();
    }
}
