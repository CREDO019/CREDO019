package com.google.android.gms.ads.nonagon.signalgeneration;

import android.net.Uri;
import android.os.RemoteException;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.google.android.gms.ads.internal.client.zzay;
import com.google.android.gms.internal.ads.zzbiy;
import com.google.android.gms.internal.ads.zzbzp;
import com.google.android.gms.internal.ads.zzcgn;
import com.google.android.gms.internal.ads.zzfju;
import com.google.android.gms.internal.ads.zzfyk;
import java.util.ArrayList;
import java.util.Iterator;
import javax.annotation.Nonnull;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzx implements zzfyk {
    final /* synthetic */ zzbzp zza;
    final /* synthetic */ boolean zzb;
    final /* synthetic */ zzaa zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzx(zzaa zzaaVar, zzbzp zzbzpVar, boolean z) {
        this.zzc = zzaaVar;
        this.zza = zzbzpVar;
        this.zzb = z;
    }

    @Override // com.google.android.gms.internal.ads.zzfyk
    public final void zza(Throwable th) {
        try {
            zzbzp zzbzpVar = this.zza;
            String message = th.getMessage();
            zzbzpVar.zze("Internal error: " + message);
        } catch (RemoteException e) {
            zzcgn.zzh("", e);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzfyk
    public final /* bridge */ /* synthetic */ void zzb(@Nonnull Object obj) {
        boolean z;
        String str;
        Uri zzW;
        zzfju zzfjuVar;
        zzfju zzfjuVar2;
        ArrayList arrayList = (ArrayList) obj;
        try {
            this.zza.zzf(arrayList);
            z = this.zzc.zzt;
            if (z || this.zzb) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    Uri uri = (Uri) it.next();
                    if (this.zzc.zzO(uri)) {
                        str = this.zzc.zzC;
                        zzW = zzaa.zzW(uri, str, IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
                        zzfjuVar = this.zzc.zzs;
                        zzfjuVar.zzc(zzW.toString(), null);
                    } else {
                        if (((Boolean) zzay.zzc().zzb(zzbiy.zzgx)).booleanValue()) {
                            zzfjuVar2 = this.zzc.zzs;
                            zzfjuVar2.zzc(uri.toString(), null);
                        }
                    }
                }
            }
        } catch (RemoteException e) {
            zzcgn.zzh("", e);
        }
    }
}
