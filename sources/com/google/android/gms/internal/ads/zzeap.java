package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzeap implements com.google.android.gms.ads.internal.overlay.zzo, zzcny {
    private final Context zza;
    private final zzcgt zzb;
    private zzeai zzc;
    private zzcmn zzd;
    private boolean zze;
    private boolean zzf;
    private long zzg;
    private com.google.android.gms.ads.internal.client.zzcy zzh;
    private boolean zzi;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzeap(Context context, zzcgt zzcgtVar) {
        this.zza = context;
        this.zzb = zzcgtVar;
    }

    private final synchronized void zzj() {
        if (this.zze && this.zzf) {
            zzcha.zze.execute(new Runnable() { // from class: com.google.android.gms.internal.ads.zzeao
                @Override // java.lang.Runnable
                public final void run() {
                    zzeap.this.zzh();
                }
            });
        }
    }

    private final synchronized boolean zzk(com.google.android.gms.ads.internal.client.zzcy zzcyVar) {
        if (!((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzhz)).booleanValue()) {
            com.google.android.gms.ads.internal.util.zze.zzj("Ad inspector had an internal error.");
            try {
                zzcyVar.zze(zzfem.zzd(16, null, null));
            } catch (RemoteException unused) {
            }
            return false;
        } else if (this.zzc == null) {
            com.google.android.gms.ads.internal.util.zze.zzj("Ad inspector had an internal error.");
            try {
                zzcyVar.zze(zzfem.zzd(16, null, null));
            } catch (RemoteException unused2) {
            }
            return false;
        } else {
            if (!this.zze && !this.zzf) {
                if (com.google.android.gms.ads.internal.zzt.zzB().currentTimeMillis() >= this.zzg + ((Integer) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzhC)).intValue()) {
                    return true;
                }
            }
            com.google.android.gms.ads.internal.util.zze.zzj("Ad inspector cannot be opened because it is already open.");
            try {
                zzcyVar.zze(zzfem.zzd(19, null, null));
            } catch (RemoteException unused3) {
            }
            return false;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzcny
    public final synchronized void zza(boolean z) {
        if (z) {
            com.google.android.gms.ads.internal.util.zze.zza("Ad inspector loaded.");
            this.zze = true;
            zzj();
            return;
        }
        com.google.android.gms.ads.internal.util.zze.zzj("Ad inspector failed to load.");
        try {
            com.google.android.gms.ads.internal.client.zzcy zzcyVar = this.zzh;
            if (zzcyVar != null) {
                zzcyVar.zze(zzfem.zzd(17, null, null));
            }
        } catch (RemoteException unused) {
        }
        this.zzi = true;
        this.zzd.destroy();
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzo
    public final synchronized void zzb() {
        this.zzf = true;
        zzj();
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzo
    public final void zzbC() {
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzo
    public final void zzbK() {
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzo
    public final void zzbr() {
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzo
    public final void zze() {
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzo
    public final synchronized void zzf(int r4) {
        this.zzd.destroy();
        if (!this.zzi) {
            com.google.android.gms.ads.internal.util.zze.zza("Inspector closed.");
            com.google.android.gms.ads.internal.client.zzcy zzcyVar = this.zzh;
            if (zzcyVar != null) {
                try {
                    zzcyVar.zze(null);
                } catch (RemoteException unused) {
                }
            }
        }
        this.zzf = false;
        this.zze = false;
        this.zzg = 0L;
        this.zzi = false;
        this.zzh = null;
    }

    public final void zzg(zzeai zzeaiVar) {
        this.zzc = zzeaiVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzh() {
        this.zzd.zzb("window.inspectorInfo", this.zzc.zzd().toString());
    }

    public final synchronized void zzi(com.google.android.gms.ads.internal.client.zzcy zzcyVar, zzbpr zzbprVar) {
        if (zzk(zzcyVar)) {
            try {
                com.google.android.gms.ads.internal.zzt.zzA();
                zzcmn zza = zzcmz.zza(this.zza, zzcoc.zza(), "", false, false, null, null, this.zzb, null, null, null, zzbel.zza(), null, null);
                this.zzd = zza;
                zzcoa zzP = zza.zzP();
                if (zzP == null) {
                    com.google.android.gms.ads.internal.util.zze.zzj("Failed to obtain a web view for the ad inspector");
                    try {
                        zzcyVar.zze(zzfem.zzd(17, "Failed to obtain a web view for the ad inspector", null));
                        return;
                    } catch (RemoteException unused) {
                        return;
                    }
                }
                this.zzh = zzcyVar;
                zzP.zzL(null, null, null, null, null, false, null, null, null, null, null, null, null, null, zzbprVar, null, new zzbqh(this.zza));
                zzP.zzz(this);
                this.zzd.loadUrl((String) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzhA));
                com.google.android.gms.ads.internal.zzt.zzj();
                com.google.android.gms.ads.internal.overlay.zzm.zza(this.zza, new AdOverlayInfoParcel(this, this.zzd, 1, this.zzb), true);
                this.zzg = com.google.android.gms.ads.internal.zzt.zzB().currentTimeMillis();
            } catch (zzcmy e) {
                com.google.android.gms.ads.internal.util.zze.zzk("Failed to obtain a web view for the ad inspector", e);
                try {
                    zzcyVar.zze(zzfem.zzd(17, "Failed to obtain a web view for the ad inspector", null));
                } catch (RemoteException unused2) {
                }
            }
        }
    }
}
