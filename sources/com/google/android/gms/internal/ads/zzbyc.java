package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.ads.nativead.NativeAd;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbyc extends NativeAd.AdChoicesInfo {
    private final List zza = new ArrayList();
    private String zzb;

    public zzbyc(zzbls zzblsVar) {
        try {
            this.zzb = zzblsVar.zzg();
        } catch (RemoteException e) {
            zzcgn.zzh("", e);
            this.zzb = "";
        }
        try {
            for (Object obj : zzblsVar.zzh()) {
                zzbma zzg = obj instanceof IBinder ? zzblz.zzg((IBinder) obj) : null;
                if (zzg != null) {
                    this.zza.add(new zzbye(zzg));
                }
            }
        } catch (RemoteException e2) {
            zzcgn.zzh("", e2);
        }
    }

    @Override // com.google.android.gms.ads.nativead.NativeAd.AdChoicesInfo
    public final List<NativeAd.Image> getImages() {
        return this.zza;
    }

    @Override // com.google.android.gms.ads.nativead.NativeAd.AdChoicesInfo
    public final CharSequence getText() {
        return this.zzb;
    }
}
