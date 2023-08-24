package com.google.android.gms.ads.internal.client;

import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.ads.zzbrs;
import com.google.android.gms.internal.ads.zzbvf;
import com.google.android.gms.internal.ads.zzcgg;
import com.google.android.gms.internal.ads.zzcgn;
import java.util.Collections;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzes extends zzcl {
    private zzbrs zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzb() {
        zzbrs zzbrsVar = this.zza;
        if (zzbrsVar != null) {
            try {
                zzbrsVar.zzb(Collections.emptyList());
            } catch (RemoteException e) {
                zzcgn.zzk("Could not notify onComplete event.", e);
            }
        }
    }

    @Override // com.google.android.gms.ads.internal.client.zzcm
    public final float zze() throws RemoteException {
        return 1.0f;
    }

    @Override // com.google.android.gms.ads.internal.client.zzcm
    public final String zzf() {
        return "";
    }

    @Override // com.google.android.gms.ads.internal.client.zzcm
    public final List zzg() throws RemoteException {
        return Collections.emptyList();
    }

    @Override // com.google.android.gms.ads.internal.client.zzcm
    public final void zzh(String str) throws RemoteException {
    }

    @Override // com.google.android.gms.ads.internal.client.zzcm
    public final void zzi() {
    }

    @Override // com.google.android.gms.ads.internal.client.zzcm
    public final void zzj() throws RemoteException {
        zzcgn.zzg("The initialization is not processed because MobileAdsSettingsManager is not created successfully.");
        zzcgg.zza.post(new Runnable() { // from class: com.google.android.gms.ads.internal.client.zzer
            @Override // java.lang.Runnable
            public final void run() {
                zzes.this.zzb();
            }
        });
    }

    @Override // com.google.android.gms.ads.internal.client.zzcm
    public final void zzk(String str, IObjectWrapper iObjectWrapper) throws RemoteException {
    }

    @Override // com.google.android.gms.ads.internal.client.zzcm
    public final void zzl(zzcy zzcyVar) {
    }

    @Override // com.google.android.gms.ads.internal.client.zzcm
    public final void zzm(IObjectWrapper iObjectWrapper, String str) throws RemoteException {
    }

    @Override // com.google.android.gms.ads.internal.client.zzcm
    public final void zzn(zzbvf zzbvfVar) throws RemoteException {
    }

    @Override // com.google.android.gms.ads.internal.client.zzcm
    public final void zzo(boolean z) throws RemoteException {
    }

    @Override // com.google.android.gms.ads.internal.client.zzcm
    public final void zzp(float f) throws RemoteException {
    }

    @Override // com.google.android.gms.ads.internal.client.zzcm
    public final void zzq(String str) throws RemoteException {
    }

    @Override // com.google.android.gms.ads.internal.client.zzcm
    public final void zzr(zzbrs zzbrsVar) throws RemoteException {
        this.zza = zzbrsVar;
    }

    @Override // com.google.android.gms.ads.internal.client.zzcm
    public final void zzs(zzez zzezVar) throws RemoteException {
    }

    @Override // com.google.android.gms.ads.internal.client.zzcm
    public final boolean zzt() throws RemoteException {
        return false;
    }
}
