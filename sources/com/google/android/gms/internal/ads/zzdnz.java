package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.common.util.Clock;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdnz implements zzgur {
    private final zzgve zza;
    private final zzgve zzb;
    private final zzgve zzc;
    private final zzgve zzd;

    public zzdnz(zzgve zzgveVar, zzgve zzgveVar2, zzgve zzgveVar3, zzgve zzgveVar4) {
        this.zza = zzgveVar;
        this.zzb = zzgveVar2;
        this.zzc = zzgveVar3;
        this.zzd = zzgveVar4;
    }

    @Override // com.google.android.gms.internal.ads.zzgve
    public final /* bridge */ /* synthetic */ Object zzb() {
        return new zzcvs((Executor) this.zzb.zzb(), new zzcve((Context) this.zzc.zzb(), (zzbbi) this.zza.zzb()), (Clock) this.zzd.zzb());
    }
}
