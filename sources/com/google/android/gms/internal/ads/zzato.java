package com.google.android.gms.internal.ads;

import android.os.Handler;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzato {
    private final Handler zza;
    private final zzatp zzb;

    public zzato(Handler handler, zzatp zzatpVar) {
        Objects.requireNonNull(handler);
        this.zza = handler;
        this.zzb = zzatpVar;
    }

    public final void zzb(int r3) {
        this.zza.post(new zzatn(this, r3));
    }

    public final void zzc(int r10, long j, long j2) {
        this.zza.post(new zzatl(this, r10, j, j2));
    }

    public final void zzd(String str, long j, long j2) {
        this.zza.post(new zzatj(this, str, j, j2));
    }

    public final void zze(zzaum zzaumVar) {
        this.zza.post(new zzatm(this, zzaumVar));
    }

    public final void zzf(zzaum zzaumVar) {
        this.zza.post(new zzati(this, zzaumVar));
    }

    public final void zzg(zzass zzassVar) {
        this.zza.post(new zzatk(this, zzassVar));
    }
}
