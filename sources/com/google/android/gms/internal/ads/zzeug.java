package com.google.android.gms.internal.ads;

import android.os.Bundle;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzeug implements zzeun {
    private final boolean zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzeug(zzfan zzfanVar) {
        this.zza = zzfanVar != null;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final int zza() {
        return 36;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final zzfyx zzb() {
        return zzfyo.zzi(this.zza ? new zzeum() { // from class: com.google.android.gms.internal.ads.zzeuf
            @Override // com.google.android.gms.internal.ads.zzeum
            public final void zzf(Object obj) {
                ((Bundle) obj).putBoolean("sdk_prefetch", true);
            }
        } : null);
    }
}
