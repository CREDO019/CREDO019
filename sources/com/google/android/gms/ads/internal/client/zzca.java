package com.google.android.gms.ads.internal.client;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.ads.dynamite.ModuleDescriptor;
import com.google.android.gms.internal.ads.zzarv;
import com.google.android.gms.internal.ads.zzarx;
import com.google.android.gms.internal.ads.zzbmd;
import com.google.android.gms.internal.ads.zzbme;
import com.google.android.gms.internal.ads.zzbmj;
import com.google.android.gms.internal.ads.zzbmk;
import com.google.android.gms.internal.ads.zzbqm;
import com.google.android.gms.internal.ads.zzbqo;
import com.google.android.gms.internal.ads.zzbqp;
import com.google.android.gms.internal.ads.zzbvf;
import com.google.android.gms.internal.ads.zzbyp;
import com.google.android.gms.internal.ads.zzbyq;
import com.google.android.gms.internal.ads.zzbyz;
import com.google.android.gms.internal.ads.zzbza;
import com.google.android.gms.internal.ads.zzcbt;
import com.google.android.gms.internal.ads.zzcci;
import com.google.android.gms.internal.ads.zzccj;
import com.google.android.gms.internal.ads.zzcfd;
import com.google.android.gms.internal.ads.zzcfe;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzca extends zzarv implements zzcc {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzca(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.client.IClientApi");
    }

    @Override // com.google.android.gms.ads.internal.client.zzcc
    public final zzbo zzb(IObjectWrapper iObjectWrapper, String str, zzbvf zzbvfVar, int r4) throws RemoteException {
        zzbo zzbmVar;
        Parcel zza = zza();
        zzarx.zzg(zza, iObjectWrapper);
        zza.writeString(str);
        zzarx.zzg(zza, zzbvfVar);
        zza.writeInt(ModuleDescriptor.MODULE_VERSION);
        Parcel zzbk = zzbk(3, zza);
        IBinder readStrongBinder = zzbk.readStrongBinder();
        if (readStrongBinder == null) {
            zzbmVar = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdLoaderBuilder");
            zzbmVar = queryLocalInterface instanceof zzbo ? (zzbo) queryLocalInterface : new zzbm(readStrongBinder);
        }
        zzbk.recycle();
        return zzbmVar;
    }

    @Override // com.google.android.gms.ads.internal.client.zzcc
    public final zzbs zzc(IObjectWrapper iObjectWrapper, zzq zzqVar, String str, zzbvf zzbvfVar, int r5) throws RemoteException {
        zzbs zzbqVar;
        Parcel zza = zza();
        zzarx.zzg(zza, iObjectWrapper);
        zzarx.zze(zza, zzqVar);
        zza.writeString(str);
        zzarx.zzg(zza, zzbvfVar);
        zza.writeInt(ModuleDescriptor.MODULE_VERSION);
        Parcel zzbk = zzbk(13, zza);
        IBinder readStrongBinder = zzbk.readStrongBinder();
        if (readStrongBinder == null) {
            zzbqVar = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManager");
            zzbqVar = queryLocalInterface instanceof zzbs ? (zzbs) queryLocalInterface : new zzbq(readStrongBinder);
        }
        zzbk.recycle();
        return zzbqVar;
    }

    @Override // com.google.android.gms.ads.internal.client.zzcc
    public final zzbs zzd(IObjectWrapper iObjectWrapper, zzq zzqVar, String str, zzbvf zzbvfVar, int r5) throws RemoteException {
        zzbs zzbqVar;
        Parcel zza = zza();
        zzarx.zzg(zza, iObjectWrapper);
        zzarx.zze(zza, zzqVar);
        zza.writeString(str);
        zzarx.zzg(zza, zzbvfVar);
        zza.writeInt(ModuleDescriptor.MODULE_VERSION);
        Parcel zzbk = zzbk(1, zza);
        IBinder readStrongBinder = zzbk.readStrongBinder();
        if (readStrongBinder == null) {
            zzbqVar = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManager");
            zzbqVar = queryLocalInterface instanceof zzbs ? (zzbs) queryLocalInterface : new zzbq(readStrongBinder);
        }
        zzbk.recycle();
        return zzbqVar;
    }

    @Override // com.google.android.gms.ads.internal.client.zzcc
    public final zzbs zze(IObjectWrapper iObjectWrapper, zzq zzqVar, String str, zzbvf zzbvfVar, int r5) throws RemoteException {
        zzbs zzbqVar;
        Parcel zza = zza();
        zzarx.zzg(zza, iObjectWrapper);
        zzarx.zze(zza, zzqVar);
        zza.writeString(str);
        zzarx.zzg(zza, zzbvfVar);
        zza.writeInt(ModuleDescriptor.MODULE_VERSION);
        Parcel zzbk = zzbk(2, zza);
        IBinder readStrongBinder = zzbk.readStrongBinder();
        if (readStrongBinder == null) {
            zzbqVar = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManager");
            zzbqVar = queryLocalInterface instanceof zzbs ? (zzbs) queryLocalInterface : new zzbq(readStrongBinder);
        }
        zzbk.recycle();
        return zzbqVar;
    }

    @Override // com.google.android.gms.ads.internal.client.zzcc
    public final zzbs zzf(IObjectWrapper iObjectWrapper, zzq zzqVar, String str, int r4) throws RemoteException {
        zzbs zzbqVar;
        Parcel zza = zza();
        zzarx.zzg(zza, iObjectWrapper);
        zzarx.zze(zza, zzqVar);
        zza.writeString(str);
        zza.writeInt(ModuleDescriptor.MODULE_VERSION);
        Parcel zzbk = zzbk(10, zza);
        IBinder readStrongBinder = zzbk.readStrongBinder();
        if (readStrongBinder == null) {
            zzbqVar = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManager");
            zzbqVar = queryLocalInterface instanceof zzbs ? (zzbs) queryLocalInterface : new zzbq(readStrongBinder);
        }
        zzbk.recycle();
        return zzbqVar;
    }

    @Override // com.google.android.gms.ads.internal.client.zzcc
    public final zzcm zzg(IObjectWrapper iObjectWrapper, int r4) throws RemoteException {
        zzcm zzckVar;
        Parcel zza = zza();
        zzarx.zzg(zza, iObjectWrapper);
        zza.writeInt(ModuleDescriptor.MODULE_VERSION);
        Parcel zzbk = zzbk(9, zza);
        IBinder readStrongBinder = zzbk.readStrongBinder();
        if (readStrongBinder == null) {
            zzckVar = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IMobileAdsSettingManager");
            zzckVar = queryLocalInterface instanceof zzcm ? (zzcm) queryLocalInterface : new zzck(readStrongBinder);
        }
        zzbk.recycle();
        return zzckVar;
    }

    @Override // com.google.android.gms.ads.internal.client.zzcc
    public final zzbme zzh(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2) throws RemoteException {
        Parcel zza = zza();
        zzarx.zzg(zza, iObjectWrapper);
        zzarx.zzg(zza, iObjectWrapper2);
        Parcel zzbk = zzbk(5, zza);
        zzbme zzbB = zzbmd.zzbB(zzbk.readStrongBinder());
        zzbk.recycle();
        return zzbB;
    }

    @Override // com.google.android.gms.ads.internal.client.zzcc
    public final zzbmk zzi(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3) throws RemoteException {
        Parcel zza = zza();
        zzarx.zzg(zza, iObjectWrapper);
        zzarx.zzg(zza, iObjectWrapper2);
        zzarx.zzg(zza, iObjectWrapper3);
        Parcel zzbk = zzbk(11, zza);
        zzbmk zze = zzbmj.zze(zzbk.readStrongBinder());
        zzbk.recycle();
        return zze;
    }

    @Override // com.google.android.gms.ads.internal.client.zzcc
    public final zzbqp zzj(IObjectWrapper iObjectWrapper, zzbvf zzbvfVar, int r3, zzbqm zzbqmVar) throws RemoteException {
        Parcel zza = zza();
        zzarx.zzg(zza, iObjectWrapper);
        zzarx.zzg(zza, zzbvfVar);
        zza.writeInt(ModuleDescriptor.MODULE_VERSION);
        zzarx.zzg(zza, zzbqmVar);
        Parcel zzbk = zzbk(16, zza);
        zzbqp zzb = zzbqo.zzb(zzbk.readStrongBinder());
        zzbk.recycle();
        return zzb;
    }

    @Override // com.google.android.gms.ads.internal.client.zzcc
    public final zzbyq zzk(IObjectWrapper iObjectWrapper, zzbvf zzbvfVar, int r3) throws RemoteException {
        Parcel zza = zza();
        zzarx.zzg(zza, iObjectWrapper);
        zzarx.zzg(zza, zzbvfVar);
        zza.writeInt(ModuleDescriptor.MODULE_VERSION);
        Parcel zzbk = zzbk(15, zza);
        zzbyq zzb = zzbyp.zzb(zzbk.readStrongBinder());
        zzbk.recycle();
        return zzb;
    }

    @Override // com.google.android.gms.ads.internal.client.zzcc
    public final zzbza zzl(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel zza = zza();
        zzarx.zzg(zza, iObjectWrapper);
        Parcel zzbk = zzbk(8, zza);
        zzbza zzF = zzbyz.zzF(zzbk.readStrongBinder());
        zzbk.recycle();
        return zzF;
    }

    @Override // com.google.android.gms.ads.internal.client.zzcc
    public final zzcbt zzm(IObjectWrapper iObjectWrapper, zzbvf zzbvfVar, int r3) throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.ads.internal.client.zzcc
    public final zzccj zzn(IObjectWrapper iObjectWrapper, String str, zzbvf zzbvfVar, int r4) throws RemoteException {
        Parcel zza = zza();
        zzarx.zzg(zza, iObjectWrapper);
        zza.writeString(str);
        zzarx.zzg(zza, zzbvfVar);
        zza.writeInt(ModuleDescriptor.MODULE_VERSION);
        Parcel zzbk = zzbk(12, zza);
        zzccj zzq = zzcci.zzq(zzbk.readStrongBinder());
        zzbk.recycle();
        return zzq;
    }

    @Override // com.google.android.gms.ads.internal.client.zzcc
    public final zzcfe zzo(IObjectWrapper iObjectWrapper, zzbvf zzbvfVar, int r3) throws RemoteException {
        Parcel zza = zza();
        zzarx.zzg(zza, iObjectWrapper);
        zzarx.zzg(zza, zzbvfVar);
        zza.writeInt(ModuleDescriptor.MODULE_VERSION);
        Parcel zzbk = zzbk(14, zza);
        zzcfe zzb = zzcfd.zzb(zzbk.readStrongBinder());
        zzbk.recycle();
        return zzb;
    }
}
