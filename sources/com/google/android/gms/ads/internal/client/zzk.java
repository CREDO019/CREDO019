package com.google.android.gms.ads.internal.client;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.RemoteCreator;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.ads.dynamite.ModuleDescriptor;
import com.google.android.gms.internal.ads.zzbiy;
import com.google.android.gms.internal.ads.zzbvf;
import com.google.android.gms.internal.ads.zzcad;
import com.google.android.gms.internal.ads.zzcaf;
import com.google.android.gms.internal.ads.zzcgn;
import com.google.android.gms.internal.ads.zzcgp;
import com.google.android.gms.internal.ads.zzcgq;
import com.google.android.gms.internal.ads.zzcgr;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzk extends RemoteCreator {
    private zzcaf zza;

    public zzk() {
        super("com.google.android.gms.ads.AdManagerCreatorImpl");
    }

    @Override // com.google.android.gms.dynamic.RemoteCreator
    protected final /* synthetic */ Object getRemoteCreator(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManagerCreator");
        return queryLocalInterface instanceof zzbt ? (zzbt) queryLocalInterface : new zzbt(iBinder);
    }

    public final zzbs zza(Context context, zzq zzqVar, String str, zzbvf zzbvfVar, int r15) {
        zzbiy.zzc(context);
        if (((Boolean) zzay.zzc().zzb(zzbiy.zzim)).booleanValue()) {
            try {
                IBinder zze = ((zzbt) zzcgr.zzb(context, "com.google.android.gms.ads.ChimeraAdManagerCreatorImpl", new zzcgp() { // from class: com.google.android.gms.ads.internal.client.zzj
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // com.google.android.gms.internal.ads.zzcgp
                    public final Object zza(Object obj) {
                        if (obj == 0) {
                            return null;
                        }
                        IInterface queryLocalInterface = obj.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManagerCreator");
                        return queryLocalInterface instanceof zzbt ? (zzbt) queryLocalInterface : new zzbt(obj);
                    }
                })).zze(ObjectWrapper.wrap(context), zzqVar, str, zzbvfVar, ModuleDescriptor.MODULE_VERSION, r15);
                if (zze == null) {
                    return null;
                }
                IInterface queryLocalInterface = zze.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManager");
                return queryLocalInterface instanceof zzbs ? (zzbs) queryLocalInterface : new zzbq(zze);
            } catch (RemoteException | zzcgq | NullPointerException e) {
                zzcaf zza = zzcad.zza(context);
                this.zza = zza;
                zza.zzd(e, "AdManagerCreator.newAdManagerByDynamiteLoader");
                zzcgn.zzl("#007 Could not call remote method.", e);
                return null;
            }
        }
        try {
            IBinder zze2 = ((zzbt) getRemoteCreatorInstance(context)).zze(ObjectWrapper.wrap(context), zzqVar, str, zzbvfVar, ModuleDescriptor.MODULE_VERSION, r15);
            if (zze2 == null) {
                return null;
            }
            IInterface queryLocalInterface2 = zze2.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManager");
            return queryLocalInterface2 instanceof zzbs ? (zzbs) queryLocalInterface2 : new zzbq(zze2);
        } catch (RemoteException | RemoteCreator.RemoteCreatorException e2) {
            zzcgn.zzf("Could not create remote AdManager.", e2);
            return null;
        }
    }
}
