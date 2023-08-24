package com.google.android.gms.internal.ads;

import android.os.Handler;
import android.os.Looper;
import android.view.View;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzflq implements zzfkv {
    private static final zzflq zza = new zzflq();
    private static final Handler zzb = new Handler(Looper.getMainLooper());
    private static Handler zzc = null;
    private static final Runnable zzd = new zzflm();
    private static final Runnable zze = new zzfln();
    private int zzg;
    private long zzm;
    private final List zzf = new ArrayList();
    private boolean zzh = false;
    private final List zzi = new ArrayList();
    private final zzflj zzk = new zzflj();
    private final zzfkx zzj = new zzfkx();
    private final zzflk zzl = new zzflk(new zzflt());

    zzflq() {
    }

    public static zzflq zzd() {
        return zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ void zzg(zzflq zzflqVar) {
        zzflqVar.zzg = 0;
        zzflqVar.zzi.clear();
        zzflqVar.zzh = false;
        for (zzfkd zzfkdVar : zzfko.zza().zzb()) {
        }
        zzflqVar.zzm = System.nanoTime();
        zzflqVar.zzk.zzi();
        long nanoTime = System.nanoTime();
        zzfkw zza2 = zzflqVar.zzj.zza();
        if (zzflqVar.zzk.zze().size() > 0) {
            Iterator it = zzflqVar.zzk.zze().iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                JSONObject zza3 = zzfle.zza(0, 0, 0, 0);
                View zza4 = zzflqVar.zzk.zza(str);
                zzfkw zzb2 = zzflqVar.zzj.zzb();
                String zzc2 = zzflqVar.zzk.zzc(str);
                if (zzc2 != null) {
                    JSONObject zza5 = zzb2.zza(zza4);
                    zzfle.zzb(zza5, str);
                    zzfle.zzf(zza5, zzc2);
                    zzfle.zzc(zza3, zza5);
                }
                zzfle.zzi(zza3);
                HashSet hashSet = new HashSet();
                hashSet.add(str);
                zzflqVar.zzl.zzc(zza3, hashSet, nanoTime);
            }
        }
        if (zzflqVar.zzk.zzf().size() > 0) {
            JSONObject zza6 = zzfle.zza(0, 0, 0, 0);
            zzflqVar.zzk(null, zza2, zza6, 1, false);
            zzfle.zzi(zza6);
            zzflqVar.zzl.zzd(zza6, zzflqVar.zzk.zzf(), nanoTime);
            boolean z = zzflqVar.zzh;
        } else {
            zzflqVar.zzl.zzb();
        }
        zzflqVar.zzk.zzg();
        long nanoTime2 = System.nanoTime() - zzflqVar.zzm;
        if (zzflqVar.zzf.size() > 0) {
            for (zzflp zzflpVar : zzflqVar.zzf) {
                int r4 = zzflqVar.zzg;
                TimeUnit.NANOSECONDS.toMillis(nanoTime2);
                zzflpVar.zzb();
                if (zzflpVar instanceof zzflo) {
                    int r42 = zzflqVar.zzg;
                    ((zzflo) zzflpVar).zza();
                }
            }
        }
    }

    private final void zzk(View view, zzfkw zzfkwVar, JSONObject jSONObject, int r11, boolean z) {
        zzfkwVar.zzb(view, jSONObject, this, r11 == 1, z);
    }

    private static final void zzl() {
        Handler handler = zzc;
        if (handler != null) {
            handler.removeCallbacks(zze);
            zzc = null;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzfkv
    public final void zza(View view, zzfkw zzfkwVar, JSONObject jSONObject, boolean z) {
        int zzk;
        boolean z2;
        if (zzflh.zzb(view) != null || (zzk = this.zzk.zzk(view)) == 3) {
            return;
        }
        JSONObject zza2 = zzfkwVar.zza(view);
        zzfle.zzc(jSONObject, zza2);
        String zzd2 = this.zzk.zzd(view);
        if (zzd2 == null) {
            zzfli zzb2 = this.zzk.zzb(view);
            if (zzb2 != null) {
                zzfle.zzd(zza2, zzb2);
                z2 = true;
            } else {
                z2 = false;
            }
            zzk(view, zzfkwVar, zza2, zzk, z || z2);
        } else {
            zzfle.zzb(zza2, zzd2);
            zzfle.zze(zza2, Boolean.valueOf(this.zzk.zzj(view)));
            this.zzk.zzh();
        }
        this.zzg++;
    }

    public final void zzh() {
        zzl();
    }

    public final void zzi() {
        if (zzc == null) {
            Handler handler = new Handler(Looper.getMainLooper());
            zzc = handler;
            handler.post(zzd);
            zzc.postDelayed(zze, 200L);
        }
    }

    public final void zzj() {
        zzl();
        this.zzf.clear();
        zzb.post(new zzfll(this));
    }
}
