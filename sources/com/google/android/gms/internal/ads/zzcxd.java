package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.concurrent.Executor;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcxd extends zzcxa {
    private final Context zzc;
    private final View zzd;
    private final zzcmn zze;
    private final zzfct zzf;
    private final zzcza zzg;
    private final zzdoz zzh;
    private final zzdkn zzi;
    private final zzgul zzj;
    private final Executor zzk;
    private com.google.android.gms.ads.internal.client.zzq zzl;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzcxd(zzczb zzczbVar, Context context, zzfct zzfctVar, View view, zzcmn zzcmnVar, zzcza zzczaVar, zzdoz zzdozVar, zzdkn zzdknVar, zzgul zzgulVar, Executor executor) {
        super(zzczbVar);
        this.zzc = context;
        this.zzd = view;
        this.zze = zzcmnVar;
        this.zzf = zzfctVar;
        this.zzg = zzczaVar;
        this.zzh = zzdozVar;
        this.zzi = zzdknVar;
        this.zzj = zzgulVar;
        this.zzk = executor;
    }

    public static /* synthetic */ void zzi(zzcxd zzcxdVar) {
        zzdoz zzdozVar = zzcxdVar.zzh;
        if (zzdozVar.zze() == null) {
            return;
        }
        try {
            zzdozVar.zze().zze((com.google.android.gms.ads.internal.client.zzbs) zzcxdVar.zzj.zzb(), ObjectWrapper.wrap(zzcxdVar.zzc));
        } catch (RemoteException e) {
            com.google.android.gms.ads.internal.util.zze.zzh("RemoteException when notifyAdLoad is called", e);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzczc
    public final void zzW() {
        this.zzk.execute(new Runnable() { // from class: com.google.android.gms.internal.ads.zzcxc
            @Override // java.lang.Runnable
            public final void run() {
                zzcxd.zzi(zzcxd.this);
            }
        });
        super.zzW();
    }

    @Override // com.google.android.gms.internal.ads.zzcxa
    public final int zza() {
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzgF)).booleanValue() && this.zzb.zzai) {
            if (!((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzgG)).booleanValue()) {
                return 0;
            }
        }
        return this.zza.zzb.zzb.zzc;
    }

    @Override // com.google.android.gms.internal.ads.zzcxa
    public final View zzc() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.ads.zzcxa
    public final com.google.android.gms.ads.internal.client.zzdk zzd() {
        try {
            return this.zzg.zza();
        } catch (zzfds unused) {
            return null;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzcxa
    public final zzfct zze() {
        com.google.android.gms.ads.internal.client.zzq zzqVar = this.zzl;
        if (zzqVar != null) {
            return zzfdr.zzc(zzqVar);
        }
        zzfcs zzfcsVar = this.zzb;
        if (zzfcsVar.zzad) {
            for (String str : zzfcsVar.zza) {
                if (str == null || !str.contains("FirstParty")) {
                }
            }
            return new zzfct(this.zzd.getWidth(), this.zzd.getHeight(), false);
        }
        return zzfdr.zzb(this.zzb.zzs, this.zzf);
    }

    @Override // com.google.android.gms.internal.ads.zzcxa
    public final zzfct zzf() {
        return this.zzf;
    }

    @Override // com.google.android.gms.internal.ads.zzcxa
    public final void zzg() {
        this.zzi.zza();
    }

    @Override // com.google.android.gms.internal.ads.zzcxa
    public final void zzh(ViewGroup viewGroup, com.google.android.gms.ads.internal.client.zzq zzqVar) {
        zzcmn zzcmnVar;
        if (viewGroup == null || (zzcmnVar = this.zze) == null) {
            return;
        }
        zzcmnVar.zzai(zzcoc.zzc(zzqVar));
        viewGroup.setMinimumHeight(zzqVar.zzc);
        viewGroup.setMinimumWidth(zzqVar.zzf);
        this.zzl = zzqVar;
    }
}
