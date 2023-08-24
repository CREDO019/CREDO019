package com.google.android.gms.ads.internal.client;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.ads.zzarw;
import com.google.android.gms.internal.ads.zzarx;
import com.google.android.gms.internal.ads.zzbme;
import com.google.android.gms.internal.ads.zzbmk;
import com.google.android.gms.internal.ads.zzbql;
import com.google.android.gms.internal.ads.zzbqm;
import com.google.android.gms.internal.ads.zzbqp;
import com.google.android.gms.internal.ads.zzbve;
import com.google.android.gms.internal.ads.zzbvf;
import com.google.android.gms.internal.ads.zzbyq;
import com.google.android.gms.internal.ads.zzbza;
import com.google.android.gms.internal.ads.zzcbt;
import com.google.android.gms.internal.ads.zzccj;
import com.google.android.gms.internal.ads.zzcfe;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzcb extends zzarw implements zzcc {
    public zzcb() {
        super("com.google.android.gms.ads.internal.client.IClientApi");
    }

    @Override // com.google.android.gms.internal.ads.zzarw
    protected final boolean zzbI(int r7, Parcel parcel, Parcel parcel2, int r10) throws RemoteException {
        switch (r7) {
            case 1:
                IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzq zzqVar = (zzq) zzarx.zza(parcel, zzq.CREATOR);
                String readString = parcel.readString();
                zzbvf zzf = zzbve.zzf(parcel.readStrongBinder());
                int readInt = parcel.readInt();
                zzarx.zzc(parcel);
                zzbs zzd = zzd(asInterface, zzqVar, readString, zzf, readInt);
                parcel2.writeNoException();
                zzarx.zzg(parcel2, zzd);
                return true;
            case 2:
                IObjectWrapper asInterface2 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzq zzqVar2 = (zzq) zzarx.zza(parcel, zzq.CREATOR);
                String readString2 = parcel.readString();
                zzbvf zzf2 = zzbve.zzf(parcel.readStrongBinder());
                int readInt2 = parcel.readInt();
                zzarx.zzc(parcel);
                zzbs zze = zze(asInterface2, zzqVar2, readString2, zzf2, readInt2);
                parcel2.writeNoException();
                zzarx.zzg(parcel2, zze);
                return true;
            case 3:
                IObjectWrapper asInterface3 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                String readString3 = parcel.readString();
                zzbvf zzf3 = zzbve.zzf(parcel.readStrongBinder());
                int readInt3 = parcel.readInt();
                zzarx.zzc(parcel);
                zzbo zzb = zzb(asInterface3, readString3, zzf3, readInt3);
                parcel2.writeNoException();
                zzarx.zzg(parcel2, zzb);
                return true;
            case 4:
                IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzarx.zzc(parcel);
                parcel2.writeNoException();
                zzarx.zzg(parcel2, null);
                return true;
            case 5:
                IObjectWrapper asInterface4 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                IObjectWrapper asInterface5 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzarx.zzc(parcel);
                zzbme zzh = zzh(asInterface4, asInterface5);
                parcel2.writeNoException();
                zzarx.zzg(parcel2, zzh);
                return true;
            case 6:
                IObjectWrapper asInterface6 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzbvf zzf4 = zzbve.zzf(parcel.readStrongBinder());
                int readInt4 = parcel.readInt();
                zzarx.zzc(parcel);
                zzcbt zzm = zzm(asInterface6, zzf4, readInt4);
                parcel2.writeNoException();
                zzarx.zzg(parcel2, zzm);
                return true;
            case 7:
                IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzarx.zzc(parcel);
                parcel2.writeNoException();
                zzarx.zzg(parcel2, null);
                return true;
            case 8:
                IObjectWrapper asInterface7 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzarx.zzc(parcel);
                zzbza zzl = zzl(asInterface7);
                parcel2.writeNoException();
                zzarx.zzg(parcel2, zzl);
                return true;
            case 9:
                IObjectWrapper asInterface8 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                int readInt5 = parcel.readInt();
                zzarx.zzc(parcel);
                zzcm zzg = zzg(asInterface8, readInt5);
                parcel2.writeNoException();
                zzarx.zzg(parcel2, zzg);
                return true;
            case 10:
                String readString4 = parcel.readString();
                int readInt6 = parcel.readInt();
                zzarx.zzc(parcel);
                zzbs zzf5 = zzf(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), (zzq) zzarx.zza(parcel, zzq.CREATOR), readString4, readInt6);
                parcel2.writeNoException();
                zzarx.zzg(parcel2, zzf5);
                return true;
            case 11:
                IObjectWrapper asInterface9 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                IObjectWrapper asInterface10 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                IObjectWrapper asInterface11 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzarx.zzc(parcel);
                zzbmk zzi = zzi(asInterface9, asInterface10, asInterface11);
                parcel2.writeNoException();
                zzarx.zzg(parcel2, zzi);
                return true;
            case 12:
                IObjectWrapper asInterface12 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                String readString5 = parcel.readString();
                zzbvf zzf6 = zzbve.zzf(parcel.readStrongBinder());
                int readInt7 = parcel.readInt();
                zzarx.zzc(parcel);
                zzccj zzn = zzn(asInterface12, readString5, zzf6, readInt7);
                parcel2.writeNoException();
                zzarx.zzg(parcel2, zzn);
                return true;
            case 13:
                IObjectWrapper asInterface13 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzq zzqVar3 = (zzq) zzarx.zza(parcel, zzq.CREATOR);
                String readString6 = parcel.readString();
                zzbvf zzf7 = zzbve.zzf(parcel.readStrongBinder());
                int readInt8 = parcel.readInt();
                zzarx.zzc(parcel);
                zzbs zzc = zzc(asInterface13, zzqVar3, readString6, zzf7, readInt8);
                parcel2.writeNoException();
                zzarx.zzg(parcel2, zzc);
                return true;
            case 14:
                IObjectWrapper asInterface14 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzbvf zzf8 = zzbve.zzf(parcel.readStrongBinder());
                int readInt9 = parcel.readInt();
                zzarx.zzc(parcel);
                zzcfe zzo = zzo(asInterface14, zzf8, readInt9);
                parcel2.writeNoException();
                zzarx.zzg(parcel2, zzo);
                return true;
            case 15:
                IObjectWrapper asInterface15 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzbvf zzf9 = zzbve.zzf(parcel.readStrongBinder());
                int readInt10 = parcel.readInt();
                zzarx.zzc(parcel);
                zzbyq zzk = zzk(asInterface15, zzf9, readInt10);
                parcel2.writeNoException();
                zzarx.zzg(parcel2, zzk);
                return true;
            case 16:
                IObjectWrapper asInterface16 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzbvf zzf10 = zzbve.zzf(parcel.readStrongBinder());
                int readInt11 = parcel.readInt();
                zzbqm zzc2 = zzbql.zzc(parcel.readStrongBinder());
                zzarx.zzc(parcel);
                zzbqp zzj = zzj(asInterface16, zzf10, readInt11, zzc2);
                parcel2.writeNoException();
                zzarx.zzg(parcel2, zzj);
                return true;
            default:
                return false;
        }
    }
}
