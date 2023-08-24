package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.RemoteException;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdpo {
    private final com.google.android.gms.ads.internal.util.zzg zza;
    private final zzfdn zzb;
    private final zzdot zzc;
    private final zzdoo zzd;
    private final zzdpz zze;
    private final zzdqh zzf;
    private final Executor zzg;
    private final Executor zzh;
    private final zzblo zzi;
    private final zzdol zzj;

    public zzdpo(com.google.android.gms.ads.internal.util.zzg zzgVar, zzfdn zzfdnVar, zzdot zzdotVar, zzdoo zzdooVar, zzdpz zzdpzVar, zzdqh zzdqhVar, Executor executor, Executor executor2, zzdol zzdolVar) {
        this.zza = zzgVar;
        this.zzb = zzfdnVar;
        this.zzi = zzfdnVar.zzi;
        this.zzc = zzdotVar;
        this.zzd = zzdooVar;
        this.zze = zzdpzVar;
        this.zzf = zzdqhVar;
        this.zzg = executor;
        this.zzh = executor2;
        this.zzj = zzdolVar;
    }

    private final boolean zzh(ViewGroup viewGroup, boolean z) {
        View zzg;
        FrameLayout.LayoutParams layoutParams;
        if (z) {
            zzg = this.zzd.zzf();
        } else {
            zzg = this.zzd.zzg();
        }
        if (zzg == null) {
            return false;
        }
        viewGroup.removeAllViews();
        if (zzg.getParent() instanceof ViewGroup) {
            ((ViewGroup) zzg.getParent()).removeView(zzg);
        }
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzcU)).booleanValue()) {
            layoutParams = new FrameLayout.LayoutParams(-1, -1, 17);
        } else {
            layoutParams = new FrameLayout.LayoutParams(-2, -2, 17);
        }
        viewGroup.addView(zzg, layoutParams);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zza(ViewGroup viewGroup) {
        boolean z = viewGroup != null;
        zzdoo zzdooVar = this.zzd;
        if (zzdooVar.zzf() != null) {
            if (zzdooVar.zzc() == 2 || zzdooVar.zzc() == 1) {
                this.zza.zzI(this.zzb.zzf, String.valueOf(zzdooVar.zzc()), z);
            } else if (zzdooVar.zzc() == 6) {
                this.zza.zzI(this.zzb.zzf, "2", z);
                this.zza.zzI(this.zzb.zzf, IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE, z);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzb(zzdqj zzdqjVar) {
        ViewGroup viewGroup;
        View view;
        final ViewGroup viewGroup2;
        zzblx zza;
        Drawable drawable;
        if (this.zzc.zzf() || this.zzc.zze()) {
            String[] strArr = {NativeAd.ASSET_ADCHOICES_CONTAINER_VIEW, "3011"};
            for (int r3 = 0; r3 < 2; r3++) {
                View zzg = zzdqjVar.zzg(strArr[r3]);
                if (zzg != null && (zzg instanceof ViewGroup)) {
                    viewGroup = (ViewGroup) zzg;
                    break;
                }
            }
        }
        viewGroup = null;
        Context context = zzdqjVar.zzf().getContext();
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        zzdoo zzdooVar = this.zzd;
        if (zzdooVar.zze() != null) {
            view = zzdooVar.zze();
            zzblo zzbloVar = this.zzi;
            if (zzbloVar != null && viewGroup == null) {
                zzg(layoutParams, zzbloVar.zze);
                view.setLayoutParams(layoutParams);
            }
        } else if (zzdooVar.zzl() instanceof zzblj) {
            zzblj zzbljVar = (zzblj) zzdooVar.zzl();
            if (viewGroup == null) {
                zzg(layoutParams, zzbljVar.zzc());
            }
            View zzblkVar = new zzblk(context, zzbljVar, layoutParams);
            zzblkVar.setContentDescription((CharSequence) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzcS));
            view = zzblkVar;
        } else {
            view = null;
        }
        if (view != null) {
            if (view.getParent() instanceof ViewGroup) {
                ((ViewGroup) view.getParent()).removeView(view);
            }
            if (viewGroup != null) {
                viewGroup.removeAllViews();
                viewGroup.addView(view);
            } else {
                com.google.android.gms.ads.formats.zza zzaVar = new com.google.android.gms.ads.formats.zza(zzdqjVar.zzf().getContext());
                zzaVar.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
                zzaVar.addView(view);
                FrameLayout zzh = zzdqjVar.zzh();
                if (zzh != null) {
                    zzh.addView(zzaVar);
                }
            }
            zzdqjVar.zzq(zzdqjVar.zzk(), view, true);
        }
        zzfuv zzfuvVar = zzdpk.zza;
        int size = zzfuvVar.size();
        int r6 = 0;
        while (true) {
            if (r6 >= size) {
                viewGroup2 = null;
                break;
            }
            View zzg2 = zzdqjVar.zzg((String) zzfuvVar.get(r6));
            r6++;
            if (zzg2 instanceof ViewGroup) {
                viewGroup2 = (ViewGroup) zzg2;
                break;
            }
        }
        this.zzh.execute(new Runnable() { // from class: com.google.android.gms.internal.ads.zzdpl
            @Override // java.lang.Runnable
            public final void run() {
                zzdpo.this.zza(viewGroup2);
            }
        });
        if (viewGroup2 == null) {
            return;
        }
        if (zzh(viewGroup2, true)) {
            zzdoo zzdooVar2 = this.zzd;
            if (zzdooVar2.zzr() != null) {
                zzdooVar2.zzr().zzaq(new zzdpn(zzdqjVar, viewGroup2));
            }
        } else if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzie)).booleanValue() && zzh(viewGroup2, false)) {
            zzdoo zzdooVar3 = this.zzd;
            if (zzdooVar3.zzp() != null) {
                zzdooVar3.zzp().zzaq(new zzdpn(zzdqjVar, viewGroup2));
            }
        } else {
            viewGroup2.removeAllViews();
            View zzf = zzdqjVar.zzf();
            Context context2 = zzf != null ? zzf.getContext() : null;
            if (context2 == null || (zza = this.zzj.zza()) == null) {
                return;
            }
            try {
                IObjectWrapper zzi = zza.zzi();
                if (zzi == null || (drawable = (Drawable) ObjectWrapper.unwrap(zzi)) == null) {
                    return;
                }
                ImageView imageView = new ImageView(context2);
                imageView.setImageDrawable(drawable);
                IObjectWrapper zzj = zzdqjVar != null ? zzdqjVar.zzj() : null;
                if (zzj != null) {
                    if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzfi)).booleanValue()) {
                        imageView.setScaleType((ImageView.ScaleType) ObjectWrapper.unwrap(zzj));
                        imageView.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
                        viewGroup2.addView(imageView);
                    }
                }
                imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                imageView.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
                viewGroup2.addView(imageView);
            } catch (RemoteException unused) {
                com.google.android.gms.ads.internal.util.zze.zzj("Could not get main image drawable");
            }
        }
    }

    public final void zzc(zzdqj zzdqjVar) {
        if (zzdqjVar == null || this.zze == null || zzdqjVar.zzh() == null || !this.zzc.zzg()) {
            return;
        }
        try {
            zzdqjVar.zzh().addView(this.zze.zza());
        } catch (zzcmy e) {
            com.google.android.gms.ads.internal.util.zze.zzb("web view can not be obtained", e);
        }
    }

    public final void zzd(zzdqj zzdqjVar) {
        if (zzdqjVar == null) {
            return;
        }
        Context context = zzdqjVar.zzf().getContext();
        if (com.google.android.gms.ads.internal.util.zzbx.zzh(context, this.zzc.zza)) {
            if (!(context instanceof Activity)) {
                com.google.android.gms.ads.internal.util.zze.zze("Activity context is needed for policy validator.");
            } else if (this.zzf == null || zzdqjVar.zzh() == null) {
            } else {
                try {
                    WindowManager windowManager = (WindowManager) context.getSystemService("window");
                    windowManager.addView(this.zzf.zza(zzdqjVar.zzh(), windowManager), com.google.android.gms.ads.internal.util.zzbx.zzb());
                } catch (zzcmy e) {
                    com.google.android.gms.ads.internal.util.zze.zzb("web view can not be obtained", e);
                }
            }
        }
    }

    public final void zze(final zzdqj zzdqjVar) {
        this.zzg.execute(new Runnable() { // from class: com.google.android.gms.internal.ads.zzdpm
            @Override // java.lang.Runnable
            public final void run() {
                zzdpo.this.zzb(zzdqjVar);
            }
        });
    }

    public final boolean zzf(ViewGroup viewGroup) {
        return zzh(viewGroup, true);
    }

    private static void zzg(RelativeLayout.LayoutParams layoutParams, int r6) {
        if (r6 == 0) {
            layoutParams.addRule(10);
            layoutParams.addRule(9);
        } else if (r6 == 2) {
            layoutParams.addRule(12);
            layoutParams.addRule(11);
        } else if (r6 == 3) {
            layoutParams.addRule(12);
            layoutParams.addRule(9);
        } else {
            layoutParams.addRule(10);
            layoutParams.addRule(11);
        }
    }
}
