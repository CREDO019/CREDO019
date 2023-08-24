package com.google.android.gms.ads.internal.client;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.ads.zzarw;
import com.google.android.gms.internal.ads.zzarx;
import com.google.android.gms.internal.ads.zzbdh;
import com.google.android.gms.internal.ads.zzbdi;
import com.google.android.gms.internal.ads.zzbjs;
import com.google.android.gms.internal.ads.zzbjt;
import com.google.android.gms.internal.ads.zzbzi;
import com.google.android.gms.internal.ads.zzbzj;
import com.google.android.gms.internal.ads.zzbzl;
import com.google.android.gms.internal.ads.zzbzm;
import com.google.android.gms.internal.ads.zzcbv;
import com.google.android.gms.internal.ads.zzcbw;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzbr extends zzarw implements zzbs {
    public zzbr() {
        super("com.google.android.gms.ads.internal.client.IAdManager");
    }

    public static zzbs zzac(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManager");
        return queryLocalInterface instanceof zzbs ? (zzbs) queryLocalInterface : new zzbq(iBinder);
    }

    @Override // com.google.android.gms.internal.ads.zzarw
    protected final boolean zzbI(int r3, Parcel parcel, Parcel parcel2, int r6) throws RemoteException {
        zzbf zzbfVar = null;
        zzcg zzcgVar = null;
        zzbi zzbiVar = null;
        zzde zzdeVar = null;
        zzbw zzbwVar = null;
        zzcd zzcdVar = null;
        zzbc zzbcVar = null;
        zzbz zzbzVar = null;
        switch (r3) {
            case 1:
                IObjectWrapper zzn = zzn();
                parcel2.writeNoException();
                zzarx.zzg(parcel2, zzn);
                return true;
            case 2:
                zzx();
                parcel2.writeNoException();
                return true;
            case 3:
                boolean zzZ = zzZ();
                parcel2.writeNoException();
                zzarx.zzd(parcel2, zzZ);
                return true;
            case 4:
                zzarx.zzc(parcel);
                boolean zzaa = zzaa((zzl) zzarx.zza(parcel, zzl.CREATOR));
                parcel2.writeNoException();
                zzarx.zzd(parcel2, zzaa);
                return true;
            case 5:
                zzz();
                parcel2.writeNoException();
                return true;
            case 6:
                zzB();
                parcel2.writeNoException();
                return true;
            case 7:
                IBinder readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdListener");
                    zzbfVar = queryLocalInterface instanceof zzbf ? (zzbf) queryLocalInterface : new zzbd(readStrongBinder);
                }
                zzarx.zzc(parcel);
                zzD(zzbfVar);
                parcel2.writeNoException();
                return true;
            case 8:
                IBinder readStrongBinder2 = parcel.readStrongBinder();
                if (readStrongBinder2 != null) {
                    IInterface queryLocalInterface2 = readStrongBinder2.queryLocalInterface("com.google.android.gms.ads.internal.client.IAppEventListener");
                    zzbzVar = queryLocalInterface2 instanceof zzbz ? (zzbz) queryLocalInterface2 : new zzbx(readStrongBinder2);
                }
                zzarx.zzc(parcel);
                zzG(zzbzVar);
                parcel2.writeNoException();
                return true;
            case 9:
                zzX();
                parcel2.writeNoException();
                return true;
            case 10:
                parcel2.writeNoException();
                return true;
            case 11:
                zzA();
                parcel2.writeNoException();
                return true;
            case 12:
                zzq zzg = zzg();
                parcel2.writeNoException();
                zzarx.zzf(parcel2, zzg);
                return true;
            case 13:
                zzarx.zzc(parcel);
                zzF((zzq) zzarx.zza(parcel, zzq.CREATOR));
                parcel2.writeNoException();
                return true;
            case 14:
                zzbzj zzb = zzbzi.zzb(parcel.readStrongBinder());
                zzarx.zzc(parcel);
                zzM(zzb);
                parcel2.writeNoException();
                return true;
            case 15:
                zzbzm zzb2 = zzbzl.zzb(parcel.readStrongBinder());
                String readString = parcel.readString();
                zzarx.zzc(parcel);
                zzQ(zzb2, readString);
                parcel2.writeNoException();
                return true;
            case 16:
            case 17:
            case 27:
            case 28:
            default:
                return false;
            case 18:
                String zzs = zzs();
                parcel2.writeNoException();
                parcel2.writeString(zzs);
                return true;
            case 19:
                zzbjt zzb3 = zzbjs.zzb(parcel.readStrongBinder());
                zzarx.zzc(parcel);
                zzO(zzb3);
                parcel2.writeNoException();
                return true;
            case 20:
                IBinder readStrongBinder3 = parcel.readStrongBinder();
                if (readStrongBinder3 != null) {
                    IInterface queryLocalInterface3 = readStrongBinder3.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdClickListener");
                    zzbcVar = queryLocalInterface3 instanceof zzbc ? (zzbc) queryLocalInterface3 : new zzba(readStrongBinder3);
                }
                zzarx.zzc(parcel);
                zzC(zzbcVar);
                parcel2.writeNoException();
                return true;
            case 21:
                IBinder readStrongBinder4 = parcel.readStrongBinder();
                if (readStrongBinder4 != null) {
                    IInterface queryLocalInterface4 = readStrongBinder4.queryLocalInterface("com.google.android.gms.ads.internal.client.ICorrelationIdProvider");
                    zzcdVar = queryLocalInterface4 instanceof zzcd ? (zzcd) queryLocalInterface4 : new zzcd(readStrongBinder4);
                }
                zzarx.zzc(parcel);
                zzab(zzcdVar);
                parcel2.writeNoException();
                return true;
            case 22:
                boolean zzh = zzarx.zzh(parcel);
                zzarx.zzc(parcel);
                zzN(zzh);
                parcel2.writeNoException();
                return true;
            case 23:
                boolean zzY = zzY();
                parcel2.writeNoException();
                zzarx.zzd(parcel2, zzY);
                return true;
            case 24:
                zzcbw zzb4 = zzcbv.zzb(parcel.readStrongBinder());
                zzarx.zzc(parcel);
                zzS(zzb4);
                parcel2.writeNoException();
                return true;
            case 25:
                String readString2 = parcel.readString();
                zzarx.zzc(parcel);
                zzT(readString2);
                parcel2.writeNoException();
                return true;
            case 26:
                zzdk zzl = zzl();
                parcel2.writeNoException();
                zzarx.zzg(parcel2, zzl);
                return true;
            case 29:
                zzarx.zzc(parcel);
                zzU((zzff) zzarx.zza(parcel, zzff.CREATOR));
                parcel2.writeNoException();
                return true;
            case 30:
                zzarx.zzc(parcel);
                zzK((zzdo) zzarx.zza(parcel, zzdo.CREATOR));
                parcel2.writeNoException();
                return true;
            case 31:
                String zzr = zzr();
                parcel2.writeNoException();
                parcel2.writeString(zzr);
                return true;
            case 32:
                zzbz zzj = zzj();
                parcel2.writeNoException();
                zzarx.zzg(parcel2, zzj);
                return true;
            case 33:
                zzbf zzi = zzi();
                parcel2.writeNoException();
                zzarx.zzg(parcel2, zzi);
                return true;
            case 34:
                boolean zzh2 = zzarx.zzh(parcel);
                zzarx.zzc(parcel);
                zzL(zzh2);
                parcel2.writeNoException();
                return true;
            case 35:
                String zzt = zzt();
                parcel2.writeNoException();
                parcel2.writeString(zzt);
                return true;
            case 36:
                IBinder readStrongBinder5 = parcel.readStrongBinder();
                if (readStrongBinder5 != null) {
                    IInterface queryLocalInterface5 = readStrongBinder5.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdMetadataListener");
                    zzbwVar = queryLocalInterface5 instanceof zzbw ? (zzbw) queryLocalInterface5 : new zzbu(readStrongBinder5);
                }
                zzarx.zzc(parcel);
                zzE(zzbwVar);
                parcel2.writeNoException();
                return true;
            case 37:
                Bundle zzd = zzd();
                parcel2.writeNoException();
                zzarx.zzf(parcel2, zzd);
                return true;
            case 38:
                String readString3 = parcel.readString();
                zzarx.zzc(parcel);
                zzR(readString3);
                parcel2.writeNoException();
                return true;
            case 39:
                zzarx.zzc(parcel);
                zzI((zzw) zzarx.zza(parcel, zzw.CREATOR));
                parcel2.writeNoException();
                return true;
            case 40:
                zzbdi zze = zzbdh.zze(parcel.readStrongBinder());
                zzarx.zzc(parcel);
                zzH(zze);
                parcel2.writeNoException();
                return true;
            case 41:
                zzdh zzk = zzk();
                parcel2.writeNoException();
                zzarx.zzg(parcel2, zzk);
                return true;
            case 42:
                IBinder readStrongBinder6 = parcel.readStrongBinder();
                if (readStrongBinder6 != null) {
                    IInterface queryLocalInterface6 = readStrongBinder6.queryLocalInterface("com.google.android.gms.ads.internal.client.IOnPaidEventListener");
                    zzdeVar = queryLocalInterface6 instanceof zzde ? (zzde) queryLocalInterface6 : new zzdc(readStrongBinder6);
                }
                zzarx.zzc(parcel);
                zzP(zzdeVar);
                parcel2.writeNoException();
                return true;
            case 43:
                zzl zzlVar = (zzl) zzarx.zza(parcel, zzl.CREATOR);
                IBinder readStrongBinder7 = parcel.readStrongBinder();
                if (readStrongBinder7 != null) {
                    IInterface queryLocalInterface7 = readStrongBinder7.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdLoadCallback");
                    zzbiVar = queryLocalInterface7 instanceof zzbi ? (zzbi) queryLocalInterface7 : new zzbg(readStrongBinder7);
                }
                zzarx.zzc(parcel);
                zzy(zzlVar, zzbiVar);
                parcel2.writeNoException();
                return true;
            case 44:
                IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzarx.zzc(parcel);
                zzW(asInterface);
                parcel2.writeNoException();
                return true;
            case 45:
                IBinder readStrongBinder8 = parcel.readStrongBinder();
                if (readStrongBinder8 != null) {
                    IInterface queryLocalInterface8 = readStrongBinder8.queryLocalInterface("com.google.android.gms.ads.internal.client.IFullScreenContentCallback");
                    zzcgVar = queryLocalInterface8 instanceof zzcg ? (zzcg) queryLocalInterface8 : new zzce(readStrongBinder8);
                }
                zzarx.zzc(parcel);
                zzJ(zzcgVar);
                parcel2.writeNoException();
                return true;
        }
    }
}
