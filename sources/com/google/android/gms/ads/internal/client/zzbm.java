package com.google.android.gms.ads.internal.client;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.ads.formats.AdManagerAdViewOptions;
import com.google.android.gms.ads.formats.PublisherAdViewOptions;
import com.google.android.gms.internal.ads.zzarv;
import com.google.android.gms.internal.ads.zzarx;
import com.google.android.gms.internal.ads.zzblo;
import com.google.android.gms.internal.ads.zzbmy;
import com.google.android.gms.internal.ads.zzbnb;
import com.google.android.gms.internal.ads.zzbne;
import com.google.android.gms.internal.ads.zzbnh;
import com.google.android.gms.internal.ads.zzbnl;
import com.google.android.gms.internal.ads.zzbno;
import com.google.android.gms.internal.ads.zzbrx;
import com.google.android.gms.internal.ads.zzbsg;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbm extends zzarv implements zzbo {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbm(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.client.IAdLoaderBuilder");
    }

    @Override // com.google.android.gms.ads.internal.client.zzbo
    public final zzbl zze() throws RemoteException {
        zzbl zzbjVar;
        Parcel zzbk = zzbk(1, zza());
        IBinder readStrongBinder = zzbk.readStrongBinder();
        if (readStrongBinder == null) {
            zzbjVar = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdLoader");
            zzbjVar = queryLocalInterface instanceof zzbl ? (zzbl) queryLocalInterface : new zzbj(readStrongBinder);
        }
        zzbk.recycle();
        return zzbjVar;
    }

    @Override // com.google.android.gms.ads.internal.client.zzbo
    public final void zzf(zzbmy zzbmyVar) throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.ads.internal.client.zzbo
    public final void zzg(zzbnb zzbnbVar) throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.ads.internal.client.zzbo
    public final void zzh(String str, zzbnh zzbnhVar, zzbne zzbneVar) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zzarx.zzg(zza, zzbnhVar);
        zzarx.zzg(zza, zzbneVar);
        zzbl(5, zza);
    }

    @Override // com.google.android.gms.ads.internal.client.zzbo
    public final void zzi(zzbsg zzbsgVar) throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.ads.internal.client.zzbo
    public final void zzj(zzbnl zzbnlVar, zzq zzqVar) throws RemoteException {
        Parcel zza = zza();
        zzarx.zzg(zza, zzbnlVar);
        zzarx.zze(zza, zzqVar);
        zzbl(8, zza);
    }

    @Override // com.google.android.gms.ads.internal.client.zzbo
    public final void zzk(zzbno zzbnoVar) throws RemoteException {
        Parcel zza = zza();
        zzarx.zzg(zza, zzbnoVar);
        zzbl(10, zza);
    }

    @Override // com.google.android.gms.ads.internal.client.zzbo
    public final void zzl(zzbf zzbfVar) throws RemoteException {
        Parcel zza = zza();
        zzarx.zzg(zza, zzbfVar);
        zzbl(2, zza);
    }

    @Override // com.google.android.gms.ads.internal.client.zzbo
    public final void zzm(AdManagerAdViewOptions adManagerAdViewOptions) throws RemoteException {
        Parcel zza = zza();
        zzarx.zze(zza, adManagerAdViewOptions);
        zzbl(15, zza);
    }

    @Override // com.google.android.gms.ads.internal.client.zzbo
    public final void zzn(zzbrx zzbrxVar) throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.ads.internal.client.zzbo
    public final void zzo(zzblo zzbloVar) throws RemoteException {
        Parcel zza = zza();
        zzarx.zze(zza, zzbloVar);
        zzbl(6, zza);
    }

    @Override // com.google.android.gms.ads.internal.client.zzbo
    public final void zzp(PublisherAdViewOptions publisherAdViewOptions) throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.ads.internal.client.zzbo
    public final void zzq(zzcd zzcdVar) throws RemoteException {
        throw null;
    }
}
