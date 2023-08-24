package com.google.android.gms.ads.internal.client;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.ads.dynamite.ModuleDescriptor;
import com.google.android.gms.internal.ads.zzbiy;
import com.google.android.gms.internal.ads.zzcad;
import com.google.android.gms.internal.ads.zzcaf;
import com.google.android.gms.internal.ads.zzcgp;
import com.google.android.gms.internal.ads.zzcgq;
import com.google.android.gms.internal.ads.zzcgr;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzao extends zzav {
    final /* synthetic */ Context zza;
    final /* synthetic */ zzau zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzao(zzau zzauVar, Context context) {
        this.zzb = zzauVar;
        this.zza = context;
    }

    @Override // com.google.android.gms.ads.internal.client.zzav
    protected final /* bridge */ /* synthetic */ Object zza() {
        zzau.zzs(this.zza, "mobile_ads_settings");
        return new zzes();
    }

    @Override // com.google.android.gms.ads.internal.client.zzav
    public final /* bridge */ /* synthetic */ Object zzb(zzcc zzccVar) throws RemoteException {
        return zzccVar.zzg(ObjectWrapper.wrap(this.zza), ModuleDescriptor.MODULE_VERSION);
    }

    @Override // com.google.android.gms.ads.internal.client.zzav
    public final /* bridge */ /* synthetic */ Object zzc() throws RemoteException {
        zzcaf zzcafVar;
        zzek zzekVar;
        zzbiy.zzc(this.zza);
        if (((Boolean) zzay.zzc().zzb(zzbiy.zzim)).booleanValue()) {
            try {
                IBinder zze = ((zzcn) zzcgr.zzb(this.zza, "com.google.android.gms.ads.ChimeraMobileAdsSettingManagerCreatorImpl", new zzcgp() { // from class: com.google.android.gms.ads.internal.client.zzan
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // com.google.android.gms.internal.ads.zzcgp
                    public final Object zza(Object obj) {
                        if (obj == 0) {
                            return null;
                        }
                        IInterface queryLocalInterface = obj.queryLocalInterface("com.google.android.gms.ads.internal.client.IMobileAdsSettingManagerCreator");
                        return queryLocalInterface instanceof zzcn ? (zzcn) queryLocalInterface : new zzcn(obj);
                    }
                })).zze(ObjectWrapper.wrap(this.zza), ModuleDescriptor.MODULE_VERSION);
                if (zze == null) {
                    return null;
                }
                IInterface queryLocalInterface = zze.queryLocalInterface("com.google.android.gms.ads.internal.client.IMobileAdsSettingManager");
                return queryLocalInterface instanceof zzcm ? (zzcm) queryLocalInterface : new zzck(zze);
            } catch (RemoteException | zzcgq | NullPointerException e) {
                this.zzb.zzh = zzcad.zza(this.zza);
                zzcafVar = this.zzb.zzh;
                zzcafVar.zzd(e, "ClientApiBroker.getMobileAdsSettingsManager");
                return null;
            }
        }
        zzekVar = this.zzb.zzc;
        return zzekVar.zza(this.zza);
    }
}
