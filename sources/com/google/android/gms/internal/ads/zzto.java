package com.google.android.gms.internal.ads;

import android.support.p001v4.media.session.PlaybackStateCompat;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzto implements zzvz {
    public long zza;
    public long zzb;
    public zzvy zzc;
    public zzto zzd;

    public zzto(long j, int r3) {
        zze(j, 65536);
    }

    public final int zza(long j) {
        long j2 = this.zza;
        int r2 = this.zzc.zzb;
        return (int) (j - j2);
    }

    public final zzto zzb() {
        this.zzc = null;
        zzto zztoVar = this.zzd;
        this.zzd = null;
        return zztoVar;
    }

    @Override // com.google.android.gms.internal.ads.zzvz
    public final zzvy zzc() {
        zzvy zzvyVar = this.zzc;
        Objects.requireNonNull(zzvyVar);
        return zzvyVar;
    }

    @Override // com.google.android.gms.internal.ads.zzvz
    public final zzvz zzd() {
        zzto zztoVar = this.zzd;
        if (zztoVar == null || zztoVar.zzc == null) {
            return null;
        }
        return zztoVar;
    }

    public final void zze(long j, int r5) {
        zzdd.zzf(this.zzc == null);
        this.zza = j;
        this.zzb = j + PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH;
    }
}
