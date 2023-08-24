package com.google.android.gms.ads.nonagon.signalgeneration;

import com.google.android.gms.internal.ads.zzcha;
import com.google.android.gms.internal.ads.zzebr;
import com.google.android.gms.internal.ads.zzfyy;
import com.google.android.gms.internal.ads.zzgur;
import com.google.android.gms.internal.ads.zzguz;
import com.google.android.gms.internal.ads.zzgve;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzal implements zzgur {
    private final zzgve zza;
    private final zzgve zzb;

    public zzal(zzgve zzgveVar, zzgve zzgveVar2) {
        this.zza = zzgveVar;
        this.zzb = zzgveVar2;
    }

    @Override // com.google.android.gms.internal.ads.zzgve
    /* renamed from: zza */
    public final zzak zzb() {
        zzfyy zzfyyVar = zzcha.zza;
        zzguz.zzb(zzfyyVar);
        return new zzak(zzfyyVar, ((zzebr) this.zzb).zzb());
    }
}
