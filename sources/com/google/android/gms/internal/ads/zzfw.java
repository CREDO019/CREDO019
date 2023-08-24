package com.google.android.gms.internal.ads;

import android.net.Uri;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfw implements zzev {
    private final zzev zza;
    private long zzb;
    private Uri zzc;
    private Map zzd;

    @Override // com.google.android.gms.internal.ads.zzr
    public final int zza(byte[] bArr, int r4, int r5) throws IOException {
        int zza = this.zza.zza(bArr, r4, r5);
        if (zza != -1) {
            this.zzb += zza;
        }
        return zza;
    }

    @Override // com.google.android.gms.internal.ads.zzev
    public final long zzb(zzfa zzfaVar) throws IOException {
        this.zzc = zzfaVar.zza;
        this.zzd = Collections.emptyMap();
        long zzb = this.zza.zzb(zzfaVar);
        Uri zzc = zzc();
        Objects.requireNonNull(zzc);
        this.zzc = zzc;
        this.zzd = zze();
        return zzb;
    }

    @Override // com.google.android.gms.internal.ads.zzev
    public final Uri zzc() {
        return this.zza.zzc();
    }

    @Override // com.google.android.gms.internal.ads.zzev
    public final void zzd() throws IOException {
        this.zza.zzd();
    }

    @Override // com.google.android.gms.internal.ads.zzev, com.google.android.gms.internal.ads.zzfs
    public final Map zze() {
        return this.zza.zze();
    }

    public final long zzg() {
        return this.zzb;
    }

    public final Uri zzh() {
        return this.zzc;
    }

    public final Map zzi() {
        return this.zzd;
    }

    public zzfw(zzev zzevVar) {
        Objects.requireNonNull(zzevVar);
        this.zza = zzevVar;
        this.zzc = Uri.EMPTY;
        this.zzd = Collections.emptyMap();
    }

    @Override // com.google.android.gms.internal.ads.zzev
    public final void zzf(zzfx zzfxVar) {
        Objects.requireNonNull(zzfxVar);
        this.zza.zzf(zzfxVar);
    }
}
