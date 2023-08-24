package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.List;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
abstract class zzfxy extends zzfxo {
    @CheckForNull
    private List zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfxy(zzfuq zzfuqVar, boolean z) {
        super(zzfuqVar, true, true);
        List zza;
        if (zzfuqVar.isEmpty()) {
            zza = Collections.emptyList();
        } else {
            zza = zzfvj.zza(zzfuqVar.size());
        }
        for (int r0 = 0; r0 < zzfuqVar.size(); r0++) {
            zza.add(null);
        }
        this.zza = zza;
    }

    abstract Object zzG(List list);

    @Override // com.google.android.gms.internal.ads.zzfxo
    final void zzg(int r3, Object obj) {
        List list = this.zza;
        if (list != null) {
            list.set(r3, new zzfxx(obj));
        }
    }

    @Override // com.google.android.gms.internal.ads.zzfxo
    final void zzv() {
        List list = this.zza;
        if (list != null) {
            zzd(zzG(list));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.ads.zzfxo
    public final void zzz(int r1) {
        super.zzz(r1);
        this.zza = null;
    }
}
