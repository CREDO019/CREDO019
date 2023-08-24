package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzbnw extends zzarw implements zzbnx {
    public zzbnw() {
        super("com.google.android.gms.ads.internal.formats.client.IUnifiedNativeAd");
    }

    @Override // com.google.android.gms.internal.ads.zzarw
    protected final boolean zzbI(int r2, Parcel parcel, Parcel parcel2, int r5) throws RemoteException {
        zzbnu zzbnsVar;
        switch (r2) {
            case 2:
                String zzq = zzq();
                parcel2.writeNoException();
                parcel2.writeString(zzq);
                return true;
            case 3:
                List zzu = zzu();
                parcel2.writeNoException();
                parcel2.writeList(zzu);
                return true;
            case 4:
                String zzo = zzo();
                parcel2.writeNoException();
                parcel2.writeString(zzo);
                return true;
            case 5:
                zzbma zzk = zzk();
                parcel2.writeNoException();
                zzarx.zzg(parcel2, zzk);
                return true;
            case 6:
                String zzp = zzp();
                parcel2.writeNoException();
                parcel2.writeString(zzp);
                return true;
            case 7:
                String zzn = zzn();
                parcel2.writeNoException();
                parcel2.writeString(zzn);
                return true;
            case 8:
                double zze = zze();
                parcel2.writeNoException();
                parcel2.writeDouble(zze);
                return true;
            case 9:
                String zzt = zzt();
                parcel2.writeNoException();
                parcel2.writeString(zzt);
                return true;
            case 10:
                String zzs = zzs();
                parcel2.writeNoException();
                parcel2.writeString(zzs);
                return true;
            case 11:
                com.google.android.gms.ads.internal.client.zzdk zzh = zzh();
                parcel2.writeNoException();
                zzarx.zzg(parcel2, zzh);
                return true;
            case 12:
                String zzr = zzr();
                parcel2.writeNoException();
                parcel2.writeString(zzr);
                return true;
            case 13:
                zzx();
                parcel2.writeNoException();
                return true;
            case 14:
                zzbls zzi = zzi();
                parcel2.writeNoException();
                zzarx.zzg(parcel2, zzi);
                return true;
            case 15:
                zzarx.zzc(parcel);
                zzz((Bundle) zzarx.zza(parcel, Bundle.CREATOR));
                parcel2.writeNoException();
                return true;
            case 16:
                zzarx.zzc(parcel);
                boolean zzI = zzI((Bundle) zzarx.zza(parcel, Bundle.CREATOR));
                parcel2.writeNoException();
                zzarx.zzd(parcel2, zzI);
                return true;
            case 17:
                zzarx.zzc(parcel);
                zzB((Bundle) zzarx.zza(parcel, Bundle.CREATOR));
                parcel2.writeNoException();
                return true;
            case 18:
                IObjectWrapper zzm = zzm();
                parcel2.writeNoException();
                zzarx.zzg(parcel2, zzm);
                return true;
            case 19:
                IObjectWrapper zzl = zzl();
                parcel2.writeNoException();
                zzarx.zzg(parcel2, zzl);
                return true;
            case 20:
                Bundle zzf = zzf();
                parcel2.writeNoException();
                zzarx.zzf(parcel2, zzf);
                return true;
            case 21:
                IBinder readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder == null) {
                    zzbnsVar = null;
                } else {
                    IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.IUnconfirmedClickListener");
                    zzbnsVar = queryLocalInterface instanceof zzbnu ? (zzbnu) queryLocalInterface : new zzbns(readStrongBinder);
                }
                zzarx.zzc(parcel);
                zzF(zzbnsVar);
                parcel2.writeNoException();
                return true;
            case 22:
                zzw();
                parcel2.writeNoException();
                return true;
            case 23:
                List zzv = zzv();
                parcel2.writeNoException();
                parcel2.writeList(zzv);
                return true;
            case 24:
                boolean zzH = zzH();
                parcel2.writeNoException();
                zzarx.zzd(parcel2, zzH);
                return true;
            case 25:
                com.google.android.gms.ads.internal.client.zzcu zzb = com.google.android.gms.ads.internal.client.zzct.zzb(parcel.readStrongBinder());
                zzarx.zzc(parcel);
                zzy(zzb);
                parcel2.writeNoException();
                return true;
            case 26:
                com.google.android.gms.ads.internal.client.zzcq zzb2 = com.google.android.gms.ads.internal.client.zzcp.zzb(parcel.readStrongBinder());
                zzarx.zzc(parcel);
                zzD(zzb2);
                parcel2.writeNoException();
                return true;
            case 27:
                zzC();
                parcel2.writeNoException();
                return true;
            case 28:
                zzA();
                parcel2.writeNoException();
                return true;
            case 29:
                zzblx zzj = zzj();
                parcel2.writeNoException();
                zzarx.zzg(parcel2, zzj);
                return true;
            case 30:
                boolean zzG = zzG();
                parcel2.writeNoException();
                zzarx.zzd(parcel2, zzG);
                return true;
            case 31:
                com.google.android.gms.ads.internal.client.zzdh zzg = zzg();
                parcel2.writeNoException();
                zzarx.zzg(parcel2, zzg);
                return true;
            case 32:
                com.google.android.gms.ads.internal.client.zzde zzb3 = com.google.android.gms.ads.internal.client.zzdd.zzb(parcel.readStrongBinder());
                zzarx.zzc(parcel);
                zzE(zzb3);
                parcel2.writeNoException();
                return true;
            default:
                return false;
        }
    }
}
