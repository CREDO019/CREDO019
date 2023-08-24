package com.google.android.gms.internal.ads;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzepk implements zzeun {
    private final Set zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzepk(Set set) {
        this.zza = set;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final int zza() {
        return 8;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final zzfyx zzb() {
        final ArrayList arrayList = new ArrayList();
        for (String str : this.zza) {
            arrayList.add(str);
        }
        return zzfyo.zzi(new zzeum() { // from class: com.google.android.gms.internal.ads.zzepj
            @Override // com.google.android.gms.internal.ads.zzeum
            public final void zzf(Object obj) {
                ((Bundle) obj).putStringArrayList("ad_types", arrayList);
            }
        });
    }
}
