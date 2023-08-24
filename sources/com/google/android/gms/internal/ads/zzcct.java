package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.rewarded.RewardItem;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcct implements RewardItem {
    private final zzccg zza;

    public zzcct(zzccg zzccgVar) {
        this.zza = zzccgVar;
    }

    @Override // com.google.android.gms.ads.rewarded.RewardItem
    public final int getAmount() {
        zzccg zzccgVar = this.zza;
        if (zzccgVar != null) {
            try {
                return zzccgVar.zze();
            } catch (RemoteException e) {
                zzcgn.zzk("Could not forward getAmount to RewardItem", e);
            }
        }
        return 0;
    }

    @Override // com.google.android.gms.ads.rewarded.RewardItem
    public final String getType() {
        zzccg zzccgVar = this.zza;
        if (zzccgVar != null) {
            try {
                return zzccgVar.zzf();
            } catch (RemoteException e) {
                zzcgn.zzk("Could not forward getType to RewardItem", e);
            }
        }
        return null;
    }
}
