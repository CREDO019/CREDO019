package com.google.android.gms.internal.ads;

import java.util.Objects;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfzl extends zzfyw {
    final /* synthetic */ zzfzn zza;
    private final zzfxu zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfzl(zzfzn zzfznVar, zzfxu zzfxuVar) {
        this.zza = zzfznVar;
        Objects.requireNonNull(zzfxuVar);
        this.zzb = zzfxuVar;
    }

    @Override // com.google.android.gms.internal.ads.zzfyw
    final /* bridge */ /* synthetic */ Object zza() throws Exception {
        zzfyx zza = this.zzb.zza();
        zzfsf.zzd(zza, "AsyncCallable.call returned null instead of a Future. Did you mean to return immediateFuture(null)? %s", this.zzb);
        return zza;
    }

    @Override // com.google.android.gms.internal.ads.zzfyw
    final String zzb() {
        return this.zzb.toString();
    }

    @Override // com.google.android.gms.internal.ads.zzfyw
    final void zzd(Throwable th) {
        this.zza.zze(th);
    }

    @Override // com.google.android.gms.internal.ads.zzfyw
    final /* synthetic */ void zze(Object obj) {
        this.zza.zzt((zzfyx) obj);
    }

    @Override // com.google.android.gms.internal.ads.zzfyw
    final boolean zzg() {
        return this.zza.isDone();
    }
}
