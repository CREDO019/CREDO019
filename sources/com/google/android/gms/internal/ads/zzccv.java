package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.ads.dynamite.ModuleDescriptor;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzccv {
    public static final zzccj zza(Context context, String str, zzbvf zzbvfVar) {
        try {
            IBinder zze = ((zzccn) zzcgr.zzb(context, "com.google.android.gms.ads.rewarded.ChimeraRewardedAdCreatorImpl", new zzcgp() { // from class: com.google.android.gms.internal.ads.zzccu
                /* JADX WARN: Multi-variable type inference failed */
                @Override // com.google.android.gms.internal.ads.zzcgp
                public final Object zza(Object obj) {
                    if (obj == 0) {
                        return null;
                    }
                    IInterface queryLocalInterface = obj.queryLocalInterface("com.google.android.gms.ads.internal.rewarded.client.IRewardedAdCreator");
                    return queryLocalInterface instanceof zzccn ? (zzccn) queryLocalInterface : new zzccn(obj);
                }
            })).zze(ObjectWrapper.wrap(context), str, zzbvfVar, ModuleDescriptor.MODULE_VERSION);
            if (zze == null) {
                return null;
            }
            IInterface queryLocalInterface = zze.queryLocalInterface("com.google.android.gms.ads.internal.rewarded.client.IRewardedAd");
            return queryLocalInterface instanceof zzccj ? (zzccj) queryLocalInterface : new zzcch(zze);
        } catch (RemoteException | zzcgq e) {
            zzcgn.zzl("#007 Could not call remote method.", e);
            return null;
        }
    }
}
