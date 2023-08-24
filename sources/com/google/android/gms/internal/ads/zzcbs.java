package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzcbs extends zzarw implements zzcbt {
    public zzcbs() {
        super("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd");
    }

    @Override // com.google.android.gms.internal.ads.zzarw
    protected final boolean zzbI(int r3, Parcel parcel, Parcel parcel2, int r6) throws RemoteException {
        if (r3 == 1) {
            zzarx.zzc(parcel);
            zzg((zzcbx) zzarx.zza(parcel, zzcbx.CREATOR));
            parcel2.writeNoException();
        } else if (r3 != 2) {
            zzcbw zzcbwVar = null;
            zzcbr zzcbrVar = null;
            if (r3 == 3) {
                IBinder readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdListener");
                    zzcbwVar = queryLocalInterface instanceof zzcbw ? (zzcbw) queryLocalInterface : new zzcbu(readStrongBinder);
                }
                zzarx.zzc(parcel);
                zzo(zzcbwVar);
                parcel2.writeNoException();
            } else if (r3 == 34) {
                boolean zzh = zzarx.zzh(parcel);
                zzarx.zzc(parcel);
                zzn(zzh);
                parcel2.writeNoException();
            } else {
                switch (r3) {
                    case 5:
                        boolean zzs = zzs();
                        parcel2.writeNoException();
                        zzarx.zzd(parcel2, zzs);
                        break;
                    case 6:
                        zzh();
                        parcel2.writeNoException();
                        break;
                    case 7:
                        zzj();
                        parcel2.writeNoException();
                        break;
                    case 8:
                        zze();
                        parcel2.writeNoException();
                        break;
                    case 9:
                        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                        zzarx.zzc(parcel);
                        zzi(asInterface);
                        parcel2.writeNoException();
                        break;
                    case 10:
                        IObjectWrapper asInterface2 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                        zzarx.zzc(parcel);
                        zzk(asInterface2);
                        parcel2.writeNoException();
                        break;
                    case 11:
                        IObjectWrapper asInterface3 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                        zzarx.zzc(parcel);
                        zzf(asInterface3);
                        parcel2.writeNoException();
                        break;
                    case 12:
                        String zzd = zzd();
                        parcel2.writeNoException();
                        parcel2.writeString(zzd);
                        break;
                    case 13:
                        String readString = parcel.readString();
                        zzarx.zzc(parcel);
                        zzp(readString);
                        parcel2.writeNoException();
                        break;
                    case 14:
                        com.google.android.gms.ads.internal.client.zzbw zzb = com.google.android.gms.ads.internal.client.zzbv.zzb(parcel.readStrongBinder());
                        zzarx.zzc(parcel);
                        zzl(zzb);
                        parcel2.writeNoException();
                        break;
                    case 15:
                        Bundle zzb2 = zzb();
                        parcel2.writeNoException();
                        zzarx.zzf(parcel2, zzb2);
                        break;
                    case 16:
                        IBinder readStrongBinder2 = parcel.readStrongBinder();
                        if (readStrongBinder2 != null) {
                            IInterface queryLocalInterface2 = readStrongBinder2.queryLocalInterface("com.google.android.gms.ads.internal.reward.client.IRewardedAdSkuListener");
                            zzcbrVar = queryLocalInterface2 instanceof zzcbr ? (zzcbr) queryLocalInterface2 : new zzcbr(readStrongBinder2);
                        }
                        zzarx.zzc(parcel);
                        zzu(zzcbrVar);
                        parcel2.writeNoException();
                        break;
                    case 17:
                        parcel.readString();
                        zzarx.zzc(parcel);
                        parcel2.writeNoException();
                        break;
                    case 18:
                        IObjectWrapper asInterface4 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                        zzarx.zzc(parcel);
                        zzr(asInterface4);
                        parcel2.writeNoException();
                        break;
                    case 19:
                        String readString2 = parcel.readString();
                        zzarx.zzc(parcel);
                        zzm(readString2);
                        parcel2.writeNoException();
                        break;
                    case 20:
                        boolean zzt = zzt();
                        parcel2.writeNoException();
                        zzarx.zzd(parcel2, zzt);
                        break;
                    case 21:
                        com.google.android.gms.ads.internal.client.zzdh zzc = zzc();
                        parcel2.writeNoException();
                        zzarx.zzg(parcel2, zzc);
                        break;
                    default:
                        return false;
                }
            }
        } else {
            zzq();
            parcel2.writeNoException();
        }
        return true;
    }
}
