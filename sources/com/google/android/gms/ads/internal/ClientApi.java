package com.google.android.gms.ads.internal;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import com.google.android.gms.ads.internal.client.zzay;
import com.google.android.gms.ads.internal.client.zzbo;
import com.google.android.gms.ads.internal.client.zzbs;
import com.google.android.gms.ads.internal.client.zzcb;
import com.google.android.gms.ads.internal.client.zzcm;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.overlay.zzac;
import com.google.android.gms.ads.internal.overlay.zzae;
import com.google.android.gms.ads.internal.overlay.zzaf;
import com.google.android.gms.ads.internal.overlay.zzy;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.ads.dynamite.ModuleDescriptor;
import com.google.android.gms.internal.ads.zzbiy;
import com.google.android.gms.internal.ads.zzbme;
import com.google.android.gms.internal.ads.zzbmk;
import com.google.android.gms.internal.ads.zzbqm;
import com.google.android.gms.internal.ads.zzbqp;
import com.google.android.gms.internal.ads.zzbvf;
import com.google.android.gms.internal.ads.zzbyq;
import com.google.android.gms.internal.ads.zzbza;
import com.google.android.gms.internal.ads.zzcbt;
import com.google.android.gms.internal.ads.zzccj;
import com.google.android.gms.internal.ads.zzcfe;
import com.google.android.gms.internal.ads.zzcgt;
import com.google.android.gms.internal.ads.zzcok;
import com.google.android.gms.internal.ads.zzdpi;
import com.google.android.gms.internal.ads.zzdpk;
import com.google.android.gms.internal.ads.zzdyw;
import com.google.android.gms.internal.ads.zzems;
import com.google.android.gms.internal.ads.zzexq;
import com.google.android.gms.internal.ads.zzexr;
import com.google.android.gms.internal.ads.zzeze;
import com.google.android.gms.internal.ads.zzfax;
import com.google.android.gms.internal.ads.zzfcl;
import java.util.HashMap;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public class ClientApi extends zzcb {
    @Override // com.google.android.gms.ads.internal.client.zzcc
    public final zzbo zzb(IObjectWrapper iObjectWrapper, String str, zzbvf zzbvfVar, int r4) {
        Context context = (Context) ObjectWrapper.unwrap(iObjectWrapper);
        return new zzems(zzcok.zza(context, zzbvfVar, r4), context, str);
    }

    @Override // com.google.android.gms.ads.internal.client.zzcc
    public final zzbs zzc(IObjectWrapper iObjectWrapper, com.google.android.gms.ads.internal.client.zzq zzqVar, String str, zzbvf zzbvfVar, int r5) {
        Context context = (Context) ObjectWrapper.unwrap(iObjectWrapper);
        zzexq zzr = zzcok.zza(context, zzbvfVar, r5).zzr();
        zzr.zza(str);
        zzr.zzb(context);
        zzexr zzc = zzr.zzc();
        if (r5 >= ((Integer) zzay.zzc().zzb(zzbiy.zzen)).intValue()) {
            return zzc.zzb();
        }
        return zzc.zza();
    }

    @Override // com.google.android.gms.ads.internal.client.zzcc
    public final zzbs zzd(IObjectWrapper iObjectWrapper, com.google.android.gms.ads.internal.client.zzq zzqVar, String str, zzbvf zzbvfVar, int r5) {
        Context context = (Context) ObjectWrapper.unwrap(iObjectWrapper);
        zzeze zzs = zzcok.zza(context, zzbvfVar, r5).zzs();
        zzs.zzc(context);
        zzs.zza(zzqVar);
        zzs.zzb(str);
        return zzs.zzd().zza();
    }

    @Override // com.google.android.gms.ads.internal.client.zzcc
    public final zzbs zze(IObjectWrapper iObjectWrapper, com.google.android.gms.ads.internal.client.zzq zzqVar, String str, zzbvf zzbvfVar, int r5) {
        Context context = (Context) ObjectWrapper.unwrap(iObjectWrapper);
        zzfax zzt = zzcok.zza(context, zzbvfVar, r5).zzt();
        zzt.zzc(context);
        zzt.zza(zzqVar);
        zzt.zzb(str);
        return zzt.zzd().zza();
    }

    @Override // com.google.android.gms.ads.internal.client.zzcc
    public final zzbs zzf(IObjectWrapper iObjectWrapper, com.google.android.gms.ads.internal.client.zzq zzqVar, String str, int r8) {
        return new zzs((Context) ObjectWrapper.unwrap(iObjectWrapper), zzqVar, str, new zzcgt(ModuleDescriptor.MODULE_VERSION, r8, true, false));
    }

    @Override // com.google.android.gms.ads.internal.client.zzcc
    public final zzcm zzg(IObjectWrapper iObjectWrapper, int r3) {
        return zzcok.zza((Context) ObjectWrapper.unwrap(iObjectWrapper), null, r3).zzb();
    }

    @Override // com.google.android.gms.ads.internal.client.zzcc
    public final zzbme zzh(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2) {
        return new zzdpk((FrameLayout) ObjectWrapper.unwrap(iObjectWrapper), (FrameLayout) ObjectWrapper.unwrap(iObjectWrapper2), ModuleDescriptor.MODULE_VERSION);
    }

    @Override // com.google.android.gms.ads.internal.client.zzcc
    public final zzbmk zzi(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3) {
        return new zzdpi((View) ObjectWrapper.unwrap(iObjectWrapper), (HashMap) ObjectWrapper.unwrap(iObjectWrapper2), (HashMap) ObjectWrapper.unwrap(iObjectWrapper3));
    }

    @Override // com.google.android.gms.ads.internal.client.zzcc
    public final zzbqp zzj(IObjectWrapper iObjectWrapper, zzbvf zzbvfVar, int r3, zzbqm zzbqmVar) {
        Context context = (Context) ObjectWrapper.unwrap(iObjectWrapper);
        zzdyw zzj = zzcok.zza(context, zzbvfVar, r3).zzj();
        zzj.zzb(context);
        zzj.zza(zzbqmVar);
        return zzj.zzc().zzd();
    }

    @Override // com.google.android.gms.ads.internal.client.zzcc
    public final zzbyq zzk(IObjectWrapper iObjectWrapper, zzbvf zzbvfVar, int r3) {
        return zzcok.zza((Context) ObjectWrapper.unwrap(iObjectWrapper), zzbvfVar, r3).zzl();
    }

    @Override // com.google.android.gms.ads.internal.client.zzcc
    public final zzbza zzl(IObjectWrapper iObjectWrapper) {
        Activity activity = (Activity) ObjectWrapper.unwrap(iObjectWrapper);
        AdOverlayInfoParcel zza = AdOverlayInfoParcel.zza(activity.getIntent());
        if (zza == null) {
            return new com.google.android.gms.ads.internal.overlay.zzt(activity);
        }
        int r1 = zza.zzk;
        if (r1 != 1) {
            if (r1 != 2) {
                if (r1 != 3) {
                    if (r1 != 4) {
                        if (r1 == 5) {
                            return new zzac(activity);
                        }
                        return new com.google.android.gms.ads.internal.overlay.zzt(activity);
                    }
                    return new zzy(activity, zza);
                }
                return new zzaf(activity);
            }
            return new zzae(activity);
        }
        return new com.google.android.gms.ads.internal.overlay.zzs(activity);
    }

    @Override // com.google.android.gms.ads.internal.client.zzcc
    public final zzcbt zzm(IObjectWrapper iObjectWrapper, zzbvf zzbvfVar, int r3) {
        Context context = (Context) ObjectWrapper.unwrap(iObjectWrapper);
        zzfcl zzu = zzcok.zza(context, zzbvfVar, r3).zzu();
        zzu.zzb(context);
        return zzu.zzc().zzb();
    }

    @Override // com.google.android.gms.ads.internal.client.zzcc
    public final zzccj zzn(IObjectWrapper iObjectWrapper, String str, zzbvf zzbvfVar, int r4) {
        Context context = (Context) ObjectWrapper.unwrap(iObjectWrapper);
        zzfcl zzu = zzcok.zza(context, zzbvfVar, r4).zzu();
        zzu.zzb(context);
        zzu.zza(str);
        return zzu.zzc().zza();
    }

    @Override // com.google.android.gms.ads.internal.client.zzcc
    public final zzcfe zzo(IObjectWrapper iObjectWrapper, zzbvf zzbvfVar, int r3) {
        return zzcok.zza((Context) ObjectWrapper.unwrap(iObjectWrapper), zzbvfVar, r3).zzo();
    }
}
