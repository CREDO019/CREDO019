package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.common.util.Clock;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcdz extends zzcet {
    private final Clock zzb;
    private final zzcdz zzc = this;
    private final zzgve zzd;
    private final zzgve zze;
    private final zzgve zzf;
    private final zzgve zzg;
    private final zzgve zzh;
    private final zzgve zzi;
    private final zzgve zzj;
    private final zzgve zzk;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzcdz(Context context, Clock clock, com.google.android.gms.ads.internal.util.zzg zzgVar, zzces zzcesVar, zzcdy zzcdyVar) {
        this.zzb = clock;
        zzgur zza = zzgus.zza(context);
        this.zzd = zza;
        zzgur zza2 = zzgus.zza(zzgVar);
        this.zze = zza2;
        zzgur zza3 = zzgus.zza(zzcesVar);
        this.zzf = zza3;
        this.zzg = zzguq.zzc(new zzcdr(zza, zza2, zza3));
        zzgur zza4 = zzgus.zza(clock);
        this.zzh = zza4;
        zzgve zzc = zzguq.zzc(new zzcdt(zza4, zza2, zza3));
        this.zzi = zzc;
        zzcdv zzcdvVar = new zzcdv(zza4, zzc);
        this.zzj = zzcdvVar;
        this.zzk = zzguq.zzc(new zzcey(zza, zzcdvVar));
    }

    @Override // com.google.android.gms.internal.ads.zzcet
    final zzcdq zza() {
        return (zzcdq) this.zzg.zzb();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.ads.zzcet
    public final zzcdu zzb() {
        return new zzcdu(this.zzb, (zzcds) this.zzi.zzb());
    }

    @Override // com.google.android.gms.internal.ads.zzcet
    final zzcex zzc() {
        return (zzcex) this.zzk.zzb();
    }
}
