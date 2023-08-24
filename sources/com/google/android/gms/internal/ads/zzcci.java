package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzcci extends zzarw implements zzccj {
    public zzcci() {
        super("com.google.android.gms.ads.internal.rewarded.client.IRewardedAd");
    }

    public static zzccj zzq(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.rewarded.client.IRewardedAd");
        return queryLocalInterface instanceof zzccj ? (zzccj) queryLocalInterface : new zzcch(iBinder);
    }

    @Override // com.google.android.gms.internal.ads.zzarw
    protected final boolean zzbI(int r3, Parcel parcel, Parcel parcel2, int r6) throws RemoteException {
        zzccq zzccqVar = null;
        zzccq zzccqVar2 = null;
        zzccr zzccrVar = null;
        zzccm zzccmVar = null;
        switch (r3) {
            case 1:
                com.google.android.gms.ads.internal.client.zzl zzlVar = (com.google.android.gms.ads.internal.client.zzl) zzarx.zza(parcel, com.google.android.gms.ads.internal.client.zzl.CREATOR);
                IBinder readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.rewarded.client.IRewardedAdLoadCallback");
                    zzccqVar = queryLocalInterface instanceof zzccq ? (zzccq) queryLocalInterface : new zzcco(readStrongBinder);
                }
                zzarx.zzc(parcel);
                zzf(zzlVar, zzccqVar);
                parcel2.writeNoException();
                return true;
            case 2:
                IBinder readStrongBinder2 = parcel.readStrongBinder();
                if (readStrongBinder2 != null) {
                    IInterface queryLocalInterface2 = readStrongBinder2.queryLocalInterface("com.google.android.gms.ads.internal.rewarded.client.IRewardedAdCallback");
                    zzccmVar = queryLocalInterface2 instanceof zzccm ? (zzccm) queryLocalInterface2 : new zzcck(readStrongBinder2);
                }
                zzarx.zzc(parcel);
                zzk(zzccmVar);
                parcel2.writeNoException();
                return true;
            case 3:
                boolean zzo = zzo();
                parcel2.writeNoException();
                zzarx.zzd(parcel2, zzo);
                return true;
            case 4:
                String zze = zze();
                parcel2.writeNoException();
                parcel2.writeString(zze);
                return true;
            case 5:
                IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzarx.zzc(parcel);
                zzm(asInterface);
                parcel2.writeNoException();
                return true;
            case 6:
                IBinder readStrongBinder3 = parcel.readStrongBinder();
                if (readStrongBinder3 != null) {
                    IInterface queryLocalInterface3 = readStrongBinder3.queryLocalInterface("com.google.android.gms.ads.internal.rewarded.client.IRewardedAdSkuListener");
                    zzccrVar = queryLocalInterface3 instanceof zzccr ? (zzccr) queryLocalInterface3 : new zzccr(readStrongBinder3);
                }
                zzarx.zzc(parcel);
                zzp(zzccrVar);
                parcel2.writeNoException();
                return true;
            case 7:
                zzarx.zzc(parcel);
                zzl((zzccx) zzarx.zza(parcel, zzccx.CREATOR));
                parcel2.writeNoException();
                return true;
            case 8:
                com.google.android.gms.ads.internal.client.zzdb zzb = com.google.android.gms.ads.internal.client.zzda.zzb(parcel.readStrongBinder());
                zzarx.zzc(parcel);
                zzi(zzb);
                parcel2.writeNoException();
                return true;
            case 9:
                Bundle zzb2 = zzb();
                parcel2.writeNoException();
                zzarx.zzf(parcel2, zzb2);
                return true;
            case 10:
                IObjectWrapper asInterface2 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                boolean zzh = zzarx.zzh(parcel);
                zzarx.zzc(parcel);
                zzn(asInterface2, zzh);
                parcel2.writeNoException();
                return true;
            case 11:
                zzccg zzd = zzd();
                parcel2.writeNoException();
                zzarx.zzg(parcel2, zzd);
                return true;
            case 12:
                com.google.android.gms.ads.internal.client.zzdh zzc = zzc();
                parcel2.writeNoException();
                zzarx.zzg(parcel2, zzc);
                return true;
            case 13:
                com.google.android.gms.ads.internal.client.zzde zzb3 = com.google.android.gms.ads.internal.client.zzdd.zzb(parcel.readStrongBinder());
                zzarx.zzc(parcel);
                zzj(zzb3);
                parcel2.writeNoException();
                return true;
            case 14:
                com.google.android.gms.ads.internal.client.zzl zzlVar2 = (com.google.android.gms.ads.internal.client.zzl) zzarx.zza(parcel, com.google.android.gms.ads.internal.client.zzl.CREATOR);
                IBinder readStrongBinder4 = parcel.readStrongBinder();
                if (readStrongBinder4 != null) {
                    IInterface queryLocalInterface4 = readStrongBinder4.queryLocalInterface("com.google.android.gms.ads.internal.rewarded.client.IRewardedAdLoadCallback");
                    zzccqVar2 = queryLocalInterface4 instanceof zzccq ? (zzccq) queryLocalInterface4 : new zzcco(readStrongBinder4);
                }
                zzarx.zzc(parcel);
                zzg(zzlVar2, zzccqVar2);
                parcel2.writeNoException();
                return true;
            case 15:
                boolean zzh2 = zzarx.zzh(parcel);
                zzarx.zzc(parcel);
                zzh(zzh2);
                parcel2.writeNoException();
                return true;
            default:
                return false;
        }
    }
}
