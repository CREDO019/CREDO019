package com.google.android.gms.ads.internal.client;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.ads.formats.AdManagerAdViewOptions;
import com.google.android.gms.ads.formats.PublisherAdViewOptions;
import com.google.android.gms.internal.ads.zzarw;
import com.google.android.gms.internal.ads.zzarx;
import com.google.android.gms.internal.ads.zzblo;
import com.google.android.gms.internal.ads.zzbmx;
import com.google.android.gms.internal.ads.zzbmy;
import com.google.android.gms.internal.ads.zzbna;
import com.google.android.gms.internal.ads.zzbnb;
import com.google.android.gms.internal.ads.zzbnd;
import com.google.android.gms.internal.ads.zzbne;
import com.google.android.gms.internal.ads.zzbng;
import com.google.android.gms.internal.ads.zzbnh;
import com.google.android.gms.internal.ads.zzbnk;
import com.google.android.gms.internal.ads.zzbnn;
import com.google.android.gms.internal.ads.zzbno;
import com.google.android.gms.internal.ads.zzbrx;
import com.google.android.gms.internal.ads.zzbsf;
import com.google.android.gms.internal.ads.zzbsg;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzbn extends zzarw implements zzbo {
    public zzbn() {
        super("com.google.android.gms.ads.internal.client.IAdLoaderBuilder");
    }

    @Override // com.google.android.gms.internal.ads.zzarw
    protected final boolean zzbI(int r2, Parcel parcel, Parcel parcel2, int r5) throws RemoteException {
        zzbf zzbfVar = null;
        zzcd zzcdVar = null;
        switch (r2) {
            case 1:
                zzbl zze = zze();
                parcel2.writeNoException();
                zzarx.zzg(parcel2, zze);
                return true;
            case 2:
                IBinder readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdListener");
                    zzbfVar = queryLocalInterface instanceof zzbf ? (zzbf) queryLocalInterface : new zzbd(readStrongBinder);
                }
                zzarx.zzc(parcel);
                zzl(zzbfVar);
                parcel2.writeNoException();
                return true;
            case 3:
                zzbmy zzb = zzbmx.zzb(parcel.readStrongBinder());
                zzarx.zzc(parcel);
                zzf(zzb);
                parcel2.writeNoException();
                return true;
            case 4:
                zzbnb zzb2 = zzbna.zzb(parcel.readStrongBinder());
                zzarx.zzc(parcel);
                zzg(zzb2);
                parcel2.writeNoException();
                return true;
            case 5:
                String readString = parcel.readString();
                zzbnh zzb3 = zzbng.zzb(parcel.readStrongBinder());
                zzbne zzb4 = zzbnd.zzb(parcel.readStrongBinder());
                zzarx.zzc(parcel);
                zzh(readString, zzb3, zzb4);
                parcel2.writeNoException();
                return true;
            case 6:
                zzarx.zzc(parcel);
                zzo((zzblo) zzarx.zza(parcel, zzblo.CREATOR));
                parcel2.writeNoException();
                return true;
            case 7:
                IBinder readStrongBinder2 = parcel.readStrongBinder();
                if (readStrongBinder2 != null) {
                    IInterface queryLocalInterface2 = readStrongBinder2.queryLocalInterface("com.google.android.gms.ads.internal.client.ICorrelationIdProvider");
                    zzcdVar = queryLocalInterface2 instanceof zzcd ? (zzcd) queryLocalInterface2 : new zzcd(readStrongBinder2);
                }
                zzarx.zzc(parcel);
                zzq(zzcdVar);
                parcel2.writeNoException();
                return true;
            case 8:
                zzarx.zzc(parcel);
                zzj(zzbnk.zzb(parcel.readStrongBinder()), (zzq) zzarx.zza(parcel, zzq.CREATOR));
                parcel2.writeNoException();
                return true;
            case 9:
                zzarx.zzc(parcel);
                zzp((PublisherAdViewOptions) zzarx.zza(parcel, PublisherAdViewOptions.CREATOR));
                parcel2.writeNoException();
                return true;
            case 10:
                zzbno zzb5 = zzbnn.zzb(parcel.readStrongBinder());
                zzarx.zzc(parcel);
                zzk(zzb5);
                parcel2.writeNoException();
                return true;
            case 11:
            case 12:
            default:
                return false;
            case 13:
                zzarx.zzc(parcel);
                zzn((zzbrx) zzarx.zza(parcel, zzbrx.CREATOR));
                parcel2.writeNoException();
                return true;
            case 14:
                zzbsg zzb6 = zzbsf.zzb(parcel.readStrongBinder());
                zzarx.zzc(parcel);
                zzi(zzb6);
                parcel2.writeNoException();
                return true;
            case 15:
                zzarx.zzc(parcel);
                zzm((AdManagerAdViewOptions) zzarx.zza(parcel, AdManagerAdViewOptions.CREATOR));
                parcel2.writeNoException();
                return true;
        }
    }
}
