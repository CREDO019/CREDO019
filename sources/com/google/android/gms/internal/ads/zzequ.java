package com.google.android.gms.internal.ads;

import android.os.Bundle;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzequ implements zzeun {
    private final zzezo zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzequ(zzezo zzezoVar) {
        this.zza = zzezoVar;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final int zza() {
        return 15;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final zzfyx zzb() {
        zzezo zzezoVar = this.zza;
        zzeum zzeumVar = null;
        if (zzezoVar != null && zzezoVar.zza() != null && !zzezoVar.zza().isEmpty()) {
            zzeumVar = new zzeum() { // from class: com.google.android.gms.internal.ads.zzeqt
                @Override // com.google.android.gms.internal.ads.zzeum
                public final void zzf(Object obj) {
                    zzequ.this.zzc((Bundle) obj);
                }
            };
        }
        return zzfyo.zzi(zzeumVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzc(Bundle bundle) {
        bundle.putString("key_schema", this.zza.zza());
    }
}
