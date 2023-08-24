package com.google.android.gms.internal.ads;

import android.os.Bundle;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdtf implements com.google.android.gms.ads.internal.client.zza, zzbol, com.google.android.gms.ads.internal.overlay.zzo, zzbon, com.google.android.gms.ads.internal.overlay.zzz {
    private com.google.android.gms.ads.internal.client.zza zza;
    private zzbol zzb;
    private com.google.android.gms.ads.internal.overlay.zzo zzc;
    private zzbon zzd;
    private com.google.android.gms.ads.internal.overlay.zzz zze;

    private zzdtf() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzdtf(zzdte zzdteVar) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final synchronized void zzi(com.google.android.gms.ads.internal.client.zza zzaVar, zzbol zzbolVar, com.google.android.gms.ads.internal.overlay.zzo zzoVar, zzbon zzbonVar, com.google.android.gms.ads.internal.overlay.zzz zzzVar) {
        this.zza = zzaVar;
        this.zzb = zzbolVar;
        this.zzc = zzoVar;
        this.zzd = zzbonVar;
        this.zze = zzzVar;
    }

    @Override // com.google.android.gms.ads.internal.client.zza
    public final synchronized void onAdClicked() {
        com.google.android.gms.ads.internal.client.zza zzaVar = this.zza;
        if (zzaVar != null) {
            zzaVar.onAdClicked();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbol
    public final synchronized void zza(String str, Bundle bundle) {
        zzbol zzbolVar = this.zzb;
        if (zzbolVar != null) {
            zzbolVar.zza(str, bundle);
        }
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzo
    public final synchronized void zzb() {
        com.google.android.gms.ads.internal.overlay.zzo zzoVar = this.zzc;
        if (zzoVar != null) {
            zzoVar.zzb();
        }
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzo
    public final synchronized void zzbC() {
        com.google.android.gms.ads.internal.overlay.zzo zzoVar = this.zzc;
        if (zzoVar != null) {
            zzoVar.zzbC();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbon
    public final synchronized void zzbD(String str, String str2) {
        zzbon zzbonVar = this.zzd;
        if (zzbonVar != null) {
            zzbonVar.zzbD(str, str2);
        }
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzo
    public final synchronized void zzbK() {
        com.google.android.gms.ads.internal.overlay.zzo zzoVar = this.zzc;
        if (zzoVar != null) {
            zzoVar.zzbK();
        }
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzo
    public final synchronized void zzbr() {
        com.google.android.gms.ads.internal.overlay.zzo zzoVar = this.zzc;
        if (zzoVar != null) {
            zzoVar.zzbr();
        }
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzo
    public final synchronized void zze() {
        com.google.android.gms.ads.internal.overlay.zzo zzoVar = this.zzc;
        if (zzoVar != null) {
            zzoVar.zze();
        }
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzo
    public final synchronized void zzf(int r2) {
        com.google.android.gms.ads.internal.overlay.zzo zzoVar = this.zzc;
        if (zzoVar != null) {
            zzoVar.zzf(r2);
        }
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzz
    public final synchronized void zzg() {
        com.google.android.gms.ads.internal.overlay.zzz zzzVar = this.zze;
        if (zzzVar != null) {
            ((zzdtg) zzzVar).zza.zzb();
        }
    }
}
