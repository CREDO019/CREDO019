package com.google.android.gms.internal.ads;

import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzens implements com.google.android.gms.ads.internal.client.zza, zzdkl {
    private com.google.android.gms.ads.internal.client.zzbc zza;

    @Override // com.google.android.gms.ads.internal.client.zza
    public final synchronized void onAdClicked() {
        com.google.android.gms.ads.internal.client.zzbc zzbcVar = this.zza;
        if (zzbcVar != null) {
            try {
                zzbcVar.zzb();
            } catch (RemoteException e) {
                com.google.android.gms.ads.internal.util.zze.zzk("Remote Exception at onAdClicked.", e);
            }
        }
    }

    public final synchronized void zza(com.google.android.gms.ads.internal.client.zzbc zzbcVar) {
        this.zza = zzbcVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdkl
    public final synchronized void zzq() {
        com.google.android.gms.ads.internal.client.zzbc zzbcVar = this.zza;
        if (zzbcVar != null) {
            try {
                zzbcVar.zzb();
            } catch (RemoteException e) {
                com.google.android.gms.ads.internal.util.zze.zzk("Remote Exception at onPhysicalClick.", e);
            }
        }
    }
}
