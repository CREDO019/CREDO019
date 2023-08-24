package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzaby implements zzzi {
    private final long zzb;
    private final zzzi zzc;

    public zzaby(long j, zzzi zzziVar) {
        this.zzb = j;
        this.zzc = zzziVar;
    }

    @Override // com.google.android.gms.internal.ads.zzzi
    public final void zzB() {
        this.zzc.zzB();
    }

    @Override // com.google.android.gms.internal.ads.zzzi
    public final void zzL(zzaai zzaaiVar) {
        this.zzc.zzL(new zzabx(this, zzaaiVar));
    }

    @Override // com.google.android.gms.internal.ads.zzzi
    public final zzaam zzv(int r2, int r3) {
        return this.zzc.zzv(r2, r3);
    }
}
