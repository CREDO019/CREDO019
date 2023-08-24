package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzep implements zzev {
    private final boolean zza;
    private final ArrayList zzb = new ArrayList(1);
    private int zzc;
    private zzfa zzd;

    /* JADX INFO: Access modifiers changed from: protected */
    public zzep(boolean z) {
        this.zza = z;
    }

    @Override // com.google.android.gms.internal.ads.zzev, com.google.android.gms.internal.ads.zzfs
    public /* synthetic */ Map zze() {
        return Collections.emptyMap();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void zzg(int r5) {
        zzfa zzfaVar = this.zzd;
        int r1 = zzel.zza;
        for (int r12 = 0; r12 < this.zzc; r12++) {
            ((zzfx) this.zzb.get(r12)).zza(this, zzfaVar, this.zza, r5);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void zzh() {
        zzfa zzfaVar = this.zzd;
        int r1 = zzel.zza;
        for (int r12 = 0; r12 < this.zzc; r12++) {
            ((zzfx) this.zzb.get(r12)).zzb(this, zzfaVar, this.zza);
        }
        this.zzd = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void zzi(zzfa zzfaVar) {
        for (int r0 = 0; r0 < this.zzc; r0++) {
            ((zzfx) this.zzb.get(r0)).zzc(this, zzfaVar, this.zza);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void zzj(zzfa zzfaVar) {
        this.zzd = zzfaVar;
        for (int r0 = 0; r0 < this.zzc; r0++) {
            ((zzfx) this.zzb.get(r0)).zzd(this, zzfaVar, this.zza);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzev
    public final void zzf(zzfx zzfxVar) {
        Objects.requireNonNull(zzfxVar);
        if (this.zzb.contains(zzfxVar)) {
            return;
        }
        this.zzb.add(zzfxVar);
        this.zzc++;
    }
}
