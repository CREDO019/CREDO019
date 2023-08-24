package com.google.android.gms.internal.ads;

import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfqz extends zzfqw {
    final /* synthetic */ zzfqw zza;
    final /* synthetic */ zzfrg zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzfqz(zzfrg zzfrgVar, TaskCompletionSource taskCompletionSource, zzfqw zzfqwVar) {
        super(taskCompletionSource);
        this.zzb = zzfrgVar;
        this.zza = zzfqwVar;
    }

    @Override // com.google.android.gms.internal.ads.zzfqw
    public final void zza() {
        zzfrg.zzm(this.zzb, this.zza);
    }
}
