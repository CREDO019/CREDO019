package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzbwx extends zzarw implements zzbwy {
    public zzbwx() {
        super("com.google.android.gms.ads.internal.mediation.client.rtb.IRtbAdapter");
    }

    public static zzbwy zzb(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.rtb.IRtbAdapter");
        return queryLocalInterface instanceof zzbwy ? (zzbwy) queryLocalInterface : new zzbww(iBinder);
    }

    @Override // com.google.android.gms.internal.ads.zzarw
    protected final boolean zzbI(int r15, Parcel parcel, Parcel parcel2, int r18) throws RemoteException {
        zzbxb zzbxbVar = null;
        zzbws zzbwqVar = null;
        zzbwm zzbwkVar = null;
        zzbwv zzbwtVar = null;
        zzbws zzbwqVar2 = null;
        zzbwv zzbwtVar2 = null;
        zzbwp zzbwnVar = null;
        zzbwm zzbwkVar2 = null;
        if (r15 == 1) {
            IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
            String readString = parcel.readString();
            Bundle bundle = (Bundle) zzarx.zza(parcel, Bundle.CREATOR);
            Bundle bundle2 = (Bundle) zzarx.zza(parcel, Bundle.CREATOR);
            com.google.android.gms.ads.internal.client.zzq zzqVar = (com.google.android.gms.ads.internal.client.zzq) zzarx.zza(parcel, com.google.android.gms.ads.internal.client.zzq.CREATOR);
            IBinder readStrongBinder = parcel.readStrongBinder();
            if (readStrongBinder != null) {
                IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.rtb.ISignalsCallback");
                zzbxbVar = queryLocalInterface instanceof zzbxb ? (zzbxb) queryLocalInterface : new zzbwz(readStrongBinder);
            }
            zzbxb zzbxbVar2 = zzbxbVar;
            zzarx.zzc(parcel);
            zzh(asInterface, readString, bundle, bundle2, zzqVar, zzbxbVar2);
            parcel2.writeNoException();
        } else if (r15 == 2) {
            zzbxl zzf = zzf();
            parcel2.writeNoException();
            zzarx.zzf(parcel2, zzf);
        } else if (r15 == 3) {
            zzbxl zzg = zzg();
            parcel2.writeNoException();
            zzarx.zzf(parcel2, zzg);
        } else if (r15 == 5) {
            com.google.android.gms.ads.internal.client.zzdk zze = zze();
            parcel2.writeNoException();
            zzarx.zzg(parcel2, zze);
        } else if (r15 == 10) {
            IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
            zzarx.zzc(parcel);
            parcel2.writeNoException();
        } else if (r15 != 11) {
            switch (r15) {
                case 13:
                    String readString2 = parcel.readString();
                    String readString3 = parcel.readString();
                    com.google.android.gms.ads.internal.client.zzl zzlVar = (com.google.android.gms.ads.internal.client.zzl) zzarx.zza(parcel, com.google.android.gms.ads.internal.client.zzl.CREATOR);
                    IObjectWrapper asInterface2 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                    IBinder readStrongBinder2 = parcel.readStrongBinder();
                    if (readStrongBinder2 != null) {
                        IInterface queryLocalInterface2 = readStrongBinder2.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.rtb.IBannerCallback");
                        zzbwkVar2 = queryLocalInterface2 instanceof zzbwm ? (zzbwm) queryLocalInterface2 : new zzbwk(readStrongBinder2);
                    }
                    zzbwm zzbwmVar = zzbwkVar2;
                    zzarx.zzc(parcel);
                    zzi(readString2, readString3, zzlVar, asInterface2, zzbwmVar, zzbvk.zzb(parcel.readStrongBinder()), (com.google.android.gms.ads.internal.client.zzq) zzarx.zza(parcel, com.google.android.gms.ads.internal.client.zzq.CREATOR));
                    parcel2.writeNoException();
                    break;
                case 14:
                    String readString4 = parcel.readString();
                    String readString5 = parcel.readString();
                    com.google.android.gms.ads.internal.client.zzl zzlVar2 = (com.google.android.gms.ads.internal.client.zzl) zzarx.zza(parcel, com.google.android.gms.ads.internal.client.zzl.CREATOR);
                    IObjectWrapper asInterface3 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                    IBinder readStrongBinder3 = parcel.readStrongBinder();
                    if (readStrongBinder3 != null) {
                        IInterface queryLocalInterface3 = readStrongBinder3.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.rtb.IInterstitialCallback");
                        zzbwnVar = queryLocalInterface3 instanceof zzbwp ? (zzbwp) queryLocalInterface3 : new zzbwn(readStrongBinder3);
                    }
                    zzbwp zzbwpVar = zzbwnVar;
                    zzbvl zzb = zzbvk.zzb(parcel.readStrongBinder());
                    zzarx.zzc(parcel);
                    zzk(readString4, readString5, zzlVar2, asInterface3, zzbwpVar, zzb);
                    parcel2.writeNoException();
                    break;
                case 15:
                    IObjectWrapper asInterface4 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                    zzarx.zzc(parcel);
                    boolean zzq = zzq(asInterface4);
                    parcel2.writeNoException();
                    zzarx.zzd(parcel2, zzq);
                    break;
                case 16:
                    String readString6 = parcel.readString();
                    String readString7 = parcel.readString();
                    com.google.android.gms.ads.internal.client.zzl zzlVar3 = (com.google.android.gms.ads.internal.client.zzl) zzarx.zza(parcel, com.google.android.gms.ads.internal.client.zzl.CREATOR);
                    IObjectWrapper asInterface5 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                    IBinder readStrongBinder4 = parcel.readStrongBinder();
                    if (readStrongBinder4 != null) {
                        IInterface queryLocalInterface4 = readStrongBinder4.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.rtb.IRewardedCallback");
                        zzbwtVar2 = queryLocalInterface4 instanceof zzbwv ? (zzbwv) queryLocalInterface4 : new zzbwt(readStrongBinder4);
                    }
                    zzbwv zzbwvVar = zzbwtVar2;
                    zzbvl zzb2 = zzbvk.zzb(parcel.readStrongBinder());
                    zzarx.zzc(parcel);
                    zzo(readString6, readString7, zzlVar3, asInterface5, zzbwvVar, zzb2);
                    parcel2.writeNoException();
                    break;
                case 17:
                    IObjectWrapper asInterface6 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                    zzarx.zzc(parcel);
                    boolean zzr = zzr(asInterface6);
                    parcel2.writeNoException();
                    zzarx.zzd(parcel2, zzr);
                    break;
                case 18:
                    String readString8 = parcel.readString();
                    String readString9 = parcel.readString();
                    com.google.android.gms.ads.internal.client.zzl zzlVar4 = (com.google.android.gms.ads.internal.client.zzl) zzarx.zza(parcel, com.google.android.gms.ads.internal.client.zzl.CREATOR);
                    IObjectWrapper asInterface7 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                    IBinder readStrongBinder5 = parcel.readStrongBinder();
                    if (readStrongBinder5 != null) {
                        IInterface queryLocalInterface5 = readStrongBinder5.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.rtb.INativeCallback");
                        zzbwqVar2 = queryLocalInterface5 instanceof zzbws ? (zzbws) queryLocalInterface5 : new zzbwq(readStrongBinder5);
                    }
                    zzbws zzbwsVar = zzbwqVar2;
                    zzbvl zzb3 = zzbvk.zzb(parcel.readStrongBinder());
                    zzarx.zzc(parcel);
                    zzl(readString8, readString9, zzlVar4, asInterface7, zzbwsVar, zzb3);
                    parcel2.writeNoException();
                    break;
                case 19:
                    String readString10 = parcel.readString();
                    zzarx.zzc(parcel);
                    zzp(readString10);
                    parcel2.writeNoException();
                    break;
                case 20:
                    String readString11 = parcel.readString();
                    String readString12 = parcel.readString();
                    com.google.android.gms.ads.internal.client.zzl zzlVar5 = (com.google.android.gms.ads.internal.client.zzl) zzarx.zza(parcel, com.google.android.gms.ads.internal.client.zzl.CREATOR);
                    IObjectWrapper asInterface8 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                    IBinder readStrongBinder6 = parcel.readStrongBinder();
                    if (readStrongBinder6 != null) {
                        IInterface queryLocalInterface6 = readStrongBinder6.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.rtb.IRewardedCallback");
                        zzbwtVar = queryLocalInterface6 instanceof zzbwv ? (zzbwv) queryLocalInterface6 : new zzbwt(readStrongBinder6);
                    }
                    zzbwv zzbwvVar2 = zzbwtVar;
                    zzbvl zzb4 = zzbvk.zzb(parcel.readStrongBinder());
                    zzarx.zzc(parcel);
                    zzn(readString11, readString12, zzlVar5, asInterface8, zzbwvVar2, zzb4);
                    parcel2.writeNoException();
                    break;
                case 21:
                    String readString13 = parcel.readString();
                    String readString14 = parcel.readString();
                    com.google.android.gms.ads.internal.client.zzl zzlVar6 = (com.google.android.gms.ads.internal.client.zzl) zzarx.zza(parcel, com.google.android.gms.ads.internal.client.zzl.CREATOR);
                    IObjectWrapper asInterface9 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                    IBinder readStrongBinder7 = parcel.readStrongBinder();
                    if (readStrongBinder7 != null) {
                        IInterface queryLocalInterface7 = readStrongBinder7.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.rtb.IBannerCallback");
                        zzbwkVar = queryLocalInterface7 instanceof zzbwm ? (zzbwm) queryLocalInterface7 : new zzbwk(readStrongBinder7);
                    }
                    zzbwm zzbwmVar2 = zzbwkVar;
                    zzarx.zzc(parcel);
                    zzj(readString13, readString14, zzlVar6, asInterface9, zzbwmVar2, zzbvk.zzb(parcel.readStrongBinder()), (com.google.android.gms.ads.internal.client.zzq) zzarx.zza(parcel, com.google.android.gms.ads.internal.client.zzq.CREATOR));
                    parcel2.writeNoException();
                    break;
                case 22:
                    String readString15 = parcel.readString();
                    String readString16 = parcel.readString();
                    com.google.android.gms.ads.internal.client.zzl zzlVar7 = (com.google.android.gms.ads.internal.client.zzl) zzarx.zza(parcel, com.google.android.gms.ads.internal.client.zzl.CREATOR);
                    IObjectWrapper asInterface10 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                    IBinder readStrongBinder8 = parcel.readStrongBinder();
                    if (readStrongBinder8 != null) {
                        IInterface queryLocalInterface8 = readStrongBinder8.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.rtb.INativeCallback");
                        zzbwqVar = queryLocalInterface8 instanceof zzbws ? (zzbws) queryLocalInterface8 : new zzbwq(readStrongBinder8);
                    }
                    zzbws zzbwsVar2 = zzbwqVar;
                    zzarx.zzc(parcel);
                    zzm(readString15, readString16, zzlVar7, asInterface10, zzbwsVar2, zzbvk.zzb(parcel.readStrongBinder()), (zzblo) zzarx.zza(parcel, zzblo.CREATOR));
                    parcel2.writeNoException();
                    break;
                default:
                    return false;
            }
        } else {
            parcel.createStringArray();
            Bundle[] bundleArr = (Bundle[]) parcel.createTypedArray(Bundle.CREATOR);
            zzarx.zzc(parcel);
            parcel2.writeNoException();
        }
        return true;
    }
}
