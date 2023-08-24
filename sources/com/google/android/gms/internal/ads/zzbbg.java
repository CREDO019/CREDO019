package com.google.android.gms.internal.ads;

import android.os.Handler;
import android.view.Surface;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbbg {
    private final Handler zza;
    private final zzbbh zzb;

    public zzbbg(Handler handler, zzbbh zzbbhVar) {
        Objects.requireNonNull(handler);
        this.zza = handler;
        this.zzb = zzbbhVar;
    }

    public final void zzb(String str, long j, long j2) {
        this.zza.post(new zzbba(this, str, j, j2));
    }

    public final void zzc(zzaum zzaumVar) {
        this.zza.post(new zzbbf(this, zzaumVar));
    }

    public final void zzd(int r3, long j) {
        this.zza.post(new zzbbc(this, r3, j));
    }

    public final void zze(zzaum zzaumVar) {
        this.zza.post(new zzbaz(this, zzaumVar));
    }

    public final void zzf(zzass zzassVar) {
        this.zza.post(new zzbbb(this, zzassVar));
    }

    public final void zzg(Surface surface) {
        this.zza.post(new zzbbe(this, surface));
    }

    public final void zzh(int r9, int r10, int r11, float f) {
        this.zza.post(new zzbbd(this, r9, r10, r11, f));
    }
}
