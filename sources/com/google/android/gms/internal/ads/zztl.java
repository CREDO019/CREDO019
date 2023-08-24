package com.google.android.gms.internal.ads;

import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zztl implements zzss {
    private final zzeu zzc;
    private int zzd;
    private final zztk zze;
    private final zzwj zzf;
    private final zzpf zzg;

    public zztl(zzeu zzeuVar, zztk zztkVar, byte[] bArr) {
        zzpf zzpfVar = new zzpf();
        zzwj zzwjVar = new zzwj(-1);
        this.zzc = zzeuVar;
        this.zze = zztkVar;
        this.zzg = zzpfVar;
        this.zzf = zzwjVar;
        this.zzd = 1048576;
    }

    public final zztl zza(int r1) {
        this.zzd = r1;
        return this;
    }

    public final zztn zzb(zzbg zzbgVar) {
        Objects.requireNonNull(zzbgVar.zzd);
        return new zztn(zzbgVar, this.zzc, this.zze, zzpo.zza, this.zzf, this.zzd, null, null);
    }
}
