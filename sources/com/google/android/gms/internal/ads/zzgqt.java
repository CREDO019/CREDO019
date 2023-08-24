package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzgqt extends zzgrd {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgqt(int r2) {
        super(r2, null);
    }

    @Override // com.google.android.gms.internal.ads.zzgrd
    public final void zza() {
        if (!zzj()) {
            for (int r0 = 0; r0 < zzb(); r0++) {
                Map.Entry zzg = zzg(r0);
                if (((zzgod) zzg.getKey()).zzc()) {
                    zzg.setValue(Collections.unmodifiableList((List) zzg.getValue()));
                }
            }
            for (Map.Entry entry : zzc()) {
                if (((zzgod) entry.getKey()).zzc()) {
                    entry.setValue(Collections.unmodifiableList((List) entry.getValue()));
                }
            }
        }
        super.zza();
    }
}
