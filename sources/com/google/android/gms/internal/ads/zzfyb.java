package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import javax.annotation.CheckForNull;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfyb extends zzfxo {
    @CheckForNull
    private zzfya zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfyb(zzfuq zzfuqVar, boolean z, Executor executor, Callable callable) {
        super(zzfuqVar, z, false);
        this.zza = new zzfxz(this, callable, executor);
        zzw();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ zzfya zzG(zzfyb zzfybVar, zzfya zzfyaVar) {
        zzfybVar.zza = null;
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzfxo
    final void zzg(int r1, @CheckForNull Object obj) {
    }

    @Override // com.google.android.gms.internal.ads.zzfxf
    protected final void zzr() {
        zzfya zzfyaVar = this.zza;
        if (zzfyaVar != null) {
            zzfyaVar.zzh();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzfxo
    final void zzv() {
        zzfya zzfyaVar = this.zza;
        if (zzfyaVar != null) {
            zzfyaVar.zzf();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzfxo
    final void zzz(int r2) {
        super.zzz(r2);
        if (r2 == 1) {
            this.zza = null;
        }
    }
}
