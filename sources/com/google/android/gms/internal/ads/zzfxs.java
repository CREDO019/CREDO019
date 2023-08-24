package com.google.android.gms.internal.ads;

import java.util.Set;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzfxs extends zzfxq {
    private zzfxs() {
        super(null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzfxs(zzfxp zzfxpVar) {
        super(null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.ads.zzfxq
    public final int zza(zzfxt zzfxtVar) {
        int zzA;
        synchronized (zzfxtVar) {
            zzA = zzfxt.zzA(zzfxtVar);
        }
        return zzA;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.ads.zzfxq
    public final void zzb(zzfxt zzfxtVar, @CheckForNull Set set, Set set2) {
        Set set3;
        synchronized (zzfxtVar) {
            set3 = zzfxtVar.seenExceptions;
            if (set3 == null) {
                zzfxtVar.seenExceptions = set2;
            }
        }
    }
}
