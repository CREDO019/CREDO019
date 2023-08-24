package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.facebook.common.util.UriUtil;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdsw implements zzbpq {
    private final zzbne zza;
    private final zzdtl zzb;
    private final zzgul zzc;

    public zzdsw(zzdoz zzdozVar, zzdoo zzdooVar, zzdtl zzdtlVar, zzgul zzgulVar) {
        this.zza = zzdozVar.zzc(zzdooVar.zzy());
        this.zzb = zzdtlVar;
        this.zzc = zzgulVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbpq
    public final void zza(Object obj, Map map) {
        String str = (String) map.get(UriUtil.LOCAL_ASSET_SCHEME);
        try {
            this.zza.zze((zzbmu) this.zzc.zzb(), str);
        } catch (RemoteException e) {
            com.google.android.gms.ads.internal.util.zze.zzk("Failed to call onCustomClick for asset " + str + ".", e);
        }
    }

    public final void zzb() {
        if (this.zza == null) {
            return;
        }
        this.zzb.zzi("/nativeAdCustomClick", this);
    }
}
