package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import androidx.collection.ArrayMap;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdoj extends zzczc {
    public static final zzfuv zzc = zzfuv.zzt("3010", "3008", "1005", "1009", "2011", "2007");
    private final zzbbo zzA;
    private final Executor zzd;
    private final zzdoo zze;
    private final zzdow zzf;
    private final zzdpo zzg;
    private final zzdot zzh;
    private final zzdoz zzi;
    private final zzgul zzj;
    private final zzgul zzk;
    private final zzgul zzl;
    private final zzgul zzm;
    private final zzgul zzn;
    private zzdqj zzo;
    private boolean zzp;
    private boolean zzq;
    private boolean zzr;
    private final zzcea zzs;
    private final zzapb zzt;
    private final zzcgt zzu;
    private final Context zzv;
    private final zzdol zzw;
    private final zzenr zzx;
    private final Map zzy;
    private final List zzz;

    public zzdoj(zzczb zzczbVar, Executor executor, zzdoo zzdooVar, zzdow zzdowVar, zzdpo zzdpoVar, zzdot zzdotVar, zzdoz zzdozVar, zzgul zzgulVar, zzgul zzgulVar2, zzgul zzgulVar3, zzgul zzgulVar4, zzgul zzgulVar5, zzcea zzceaVar, zzapb zzapbVar, zzcgt zzcgtVar, Context context, zzdol zzdolVar, zzenr zzenrVar, zzbbo zzbboVar) {
        super(zzczbVar);
        this.zzd = executor;
        this.zze = zzdooVar;
        this.zzf = zzdowVar;
        this.zzg = zzdpoVar;
        this.zzh = zzdotVar;
        this.zzi = zzdozVar;
        this.zzj = zzgulVar;
        this.zzk = zzgulVar2;
        this.zzl = zzgulVar3;
        this.zzm = zzgulVar4;
        this.zzn = zzgulVar5;
        this.zzs = zzceaVar;
        this.zzt = zzapbVar;
        this.zzu = zzcgtVar;
        this.zzv = context;
        this.zzw = zzdolVar;
        this.zzx = zzenrVar;
        this.zzy = new HashMap();
        this.zzz = new ArrayList();
        this.zzA = zzbboVar;
    }

    public static boolean zzP(View view) {
        if (!((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzii)).booleanValue()) {
            return view.isShown() && view.getGlobalVisibleRect(new Rect(), null);
        }
        com.google.android.gms.ads.internal.zzt.zzq();
        long zzt = com.google.android.gms.ads.internal.util.zzs.zzt(view);
        if (view.isShown() && view.getGlobalVisibleRect(new Rect(), null)) {
            if (zzt >= ((Integer) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzij)).intValue()) {
                return true;
            }
        }
        return false;
    }

    private final synchronized View zzR(Map map) {
        if (map == null) {
            return null;
        }
        zzfuv zzfuvVar = zzc;
        int size = zzfuvVar.size();
        int r3 = 0;
        while (r3 < size) {
            WeakReference weakReference = (WeakReference) map.get((String) zzfuvVar.get(r3));
            r3++;
            if (weakReference != null) {
                return (View) weakReference.get();
            }
        }
        return null;
    }

    private final synchronized void zzS(View view, Map map, Map map2) {
        this.zzg.zzd(this.zzo);
        this.zzf.zzp(view, map, map2);
        this.zzq = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: zzT */
    public final synchronized void zzt(zzdqj zzdqjVar) {
        Iterator<String> keys;
        View view;
        zzaox zzc2;
        if (this.zzp) {
            return;
        }
        this.zzo = zzdqjVar;
        this.zzg.zze(zzdqjVar);
        this.zzf.zzx(zzdqjVar.zzf(), zzdqjVar.zzm(), zzdqjVar.zzn(), zzdqjVar, zzdqjVar);
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzcf)).booleanValue() && (zzc2 = this.zzt.zzc()) != null) {
            zzc2.zzn(zzdqjVar.zzf());
        }
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzbw)).booleanValue()) {
            zzfcs zzfcsVar = this.zzb;
            if (zzfcsVar.zzam && (keys = zzfcsVar.zzal.keys()) != null) {
                while (keys.hasNext()) {
                    String next = keys.next();
                    WeakReference weakReference = (WeakReference) this.zzo.zzl().get(next);
                    this.zzy.put(next, false);
                    if (weakReference != null && (view = (View) weakReference.get()) != null) {
                        zzbbn zzbbnVar = new zzbbn(this.zzv, view);
                        this.zzz.add(zzbbnVar);
                        zzbbnVar.zzc(new zzdoi(this, next));
                    }
                }
            }
        }
        if (zzdqjVar.zzi() != null) {
            zzdqjVar.zzi().zzc(this.zzs);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: zzU */
    public final void zzu(zzdqj zzdqjVar) {
        this.zzf.zzy(zzdqjVar.zzf(), zzdqjVar.zzl());
        if (zzdqjVar.zzh() != null) {
            zzdqjVar.zzh().setClickable(false);
            zzdqjVar.zzh().removeAllViews();
        }
        if (zzdqjVar.zzi() != null) {
            zzdqjVar.zzi().zze(this.zzs);
        }
        this.zzo = null;
    }

    public final synchronized void zzA() {
        zzdqj zzdqjVar = this.zzo;
        if (zzdqjVar == null) {
            com.google.android.gms.ads.internal.util.zze.zze("Ad should be associated with an ad view before calling recordCustomClickGesture()");
            return;
        }
        final boolean z = zzdqjVar instanceof zzdpi;
        this.zzd.execute(new Runnable() { // from class: com.google.android.gms.internal.ads.zzdof
            @Override // java.lang.Runnable
            public final void run() {
                zzdoj.this.zzs(z);
            }
        });
    }

    public final synchronized void zzB() {
        if (this.zzq) {
            return;
        }
        this.zzf.zzq();
    }

    public final void zzC(View view) {
        zzdoo zzdooVar = this.zze;
        IObjectWrapper zzu = zzdooVar.zzu();
        zzcmn zzq = zzdooVar.zzq();
        if (!this.zzh.zzd() || zzu == null || zzq == null || view == null) {
            return;
        }
        com.google.android.gms.ads.internal.zzt.zzh().zzc(zzu, view);
    }

    public final synchronized void zzD(View view, MotionEvent motionEvent, View view2) {
        this.zzf.zzr(view, motionEvent, view2);
    }

    public final synchronized void zzE(Bundle bundle) {
        this.zzf.zzs(bundle);
    }

    public final synchronized void zzF(View view) {
        this.zzf.zzt(view);
    }

    public final synchronized void zzG() {
        this.zzf.zzu();
    }

    public final synchronized void zzH(com.google.android.gms.ads.internal.client.zzcq zzcqVar) {
        this.zzf.zzv(zzcqVar);
    }

    public final synchronized void zzI(com.google.android.gms.ads.internal.client.zzde zzdeVar) {
        this.zzx.zza(zzdeVar);
    }

    public final synchronized void zzJ(zzbnu zzbnuVar) {
        this.zzf.zzw(zzbnuVar);
    }

    public final synchronized void zzK(final zzdqj zzdqjVar) {
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzbu)).booleanValue()) {
            com.google.android.gms.ads.internal.util.zzs.zza.post(new Runnable() { // from class: com.google.android.gms.internal.ads.zzdog
                @Override // java.lang.Runnable
                public final void run() {
                    zzdoj.this.zzt(zzdqjVar);
                }
            });
        } else {
            zzt(zzdqjVar);
        }
    }

    public final synchronized void zzL(final zzdqj zzdqjVar) {
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzbu)).booleanValue()) {
            com.google.android.gms.ads.internal.util.zzs.zza.post(new Runnable() { // from class: com.google.android.gms.internal.ads.zzdoc
                @Override // java.lang.Runnable
                public final void run() {
                    zzdoj.this.zzu(zzdqjVar);
                }
            });
        } else {
            zzu(zzdqjVar);
        }
    }

    public final boolean zzM() {
        return this.zzh.zze();
    }

    public final synchronized boolean zzN() {
        return this.zzf.zzz();
    }

    public final boolean zzO() {
        return this.zzh.zzd();
    }

    public final synchronized boolean zzQ(Bundle bundle) {
        if (this.zzq) {
            return true;
        }
        boolean zzA = this.zzf.zzA(bundle);
        this.zzq = zzA;
        return zzA;
    }

    @Override // com.google.android.gms.internal.ads.zzczc
    public final synchronized void zzV() {
        this.zzp = true;
        this.zzd.execute(new Runnable() { // from class: com.google.android.gms.internal.ads.zzdoh
            @Override // java.lang.Runnable
            public final void run() {
                zzdoj.this.zzr();
            }
        });
        super.zzV();
    }

    @Override // com.google.android.gms.internal.ads.zzczc
    public final void zzW() {
        this.zzd.execute(new Runnable() { // from class: com.google.android.gms.internal.ads.zzdod
            @Override // java.lang.Runnable
            public final void run() {
                zzdoj.zzh(zzdoj.this);
            }
        });
        if (this.zze.zzc() != 7) {
            Executor executor = this.zzd;
            final zzdow zzdowVar = this.zzf;
            zzdowVar.getClass();
            executor.execute(new Runnable() { // from class: com.google.android.gms.internal.ads.zzdoe
                @Override // java.lang.Runnable
                public final void run() {
                    zzdow.this.zzo();
                }
            });
        }
        super.zzW();
    }

    public final zzdol zza() {
        return this.zzw;
    }

    public final String zzd() {
        return this.zzh.zzb();
    }

    public final synchronized JSONObject zzf(View view, Map map, Map map2) {
        return this.zzf.zzd(view, map, map2);
    }

    public final synchronized JSONObject zzg(View view, Map map, Map map2) {
        return this.zzf.zze(view, map, map2);
    }

    public final void zzi(View view) {
        IObjectWrapper zzu = this.zze.zzu();
        if (!this.zzh.zzd() || zzu == null || view == null) {
            return;
        }
        com.google.android.gms.ads.internal.zzt.zzh();
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzed)).booleanValue() && zzfjx.zzb()) {
            Object unwrap = ObjectWrapper.unwrap(zzu);
            if (unwrap instanceof zzfjz) {
                ((zzfjz) unwrap).zzb(view, zzfkf.NOT_VISIBLE, "Ad overlay");
            }
        }
    }

    public final synchronized void zzk() {
        this.zzf.zzg();
    }

    public final void zzq(String str, boolean z) {
        String str2;
        zzbyv zzbyvVar;
        zzbyw zzbywVar;
        if (!this.zzh.zzd() || TextUtils.isEmpty(str)) {
            return;
        }
        zzdoo zzdooVar = this.zze;
        zzcmn zzq = zzdooVar.zzq();
        zzcmn zzr = zzdooVar.zzr();
        if (zzq != null || zzr != null) {
            boolean z2 = false;
            boolean z3 = zzq != null;
            boolean z4 = zzr != null;
            if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzeh)).booleanValue()) {
                this.zzh.zza();
                int zzb = this.zzh.zza().zzb();
                int r6 = zzb - 1;
                if (r6 != 0) {
                    if (r6 != 1) {
                        com.google.android.gms.ads.internal.util.zze.zzj("Unknown omid media type: " + (zzb != 1 ? zzb != 2 ? "UNKNOWN" : "DISPLAY" : "VIDEO") + ". Not initializing Omid.");
                        return;
                    } else if (zzq == null) {
                        com.google.android.gms.ads.internal.util.zze.zzj("Omid media type was display but there was no display webview.");
                        return;
                    } else {
                        z2 = true;
                        z4 = false;
                    }
                } else if (zzr == null) {
                    com.google.android.gms.ads.internal.util.zze.zzj("Omid media type was video but there was no video webview.");
                    return;
                } else {
                    z4 = true;
                }
            } else {
                z2 = z3;
            }
            if (z2) {
                str2 = null;
            } else {
                str2 = "javascript";
                zzq = zzr;
            }
            String str3 = str2;
            zzq.zzI();
            if (!com.google.android.gms.ads.internal.zzt.zzh().zze(this.zzv)) {
                com.google.android.gms.ads.internal.util.zze.zzj("Failed to initialize omid in InternalNativeAd");
                return;
            }
            zzcgt zzcgtVar = this.zzu;
            String str4 = zzcgtVar.zzb + "." + zzcgtVar.zzc;
            if (z4) {
                zzbyvVar = zzbyv.VIDEO;
                zzbywVar = zzbyw.DEFINED_BY_JAVASCRIPT;
            } else {
                zzbyvVar = zzbyv.NATIVE_DISPLAY;
                if (this.zze.zzc() == 3) {
                    zzbywVar = zzbyw.UNSPECIFIED;
                } else {
                    zzbywVar = zzbyw.ONE_PIXEL;
                }
            }
            IObjectWrapper zzb2 = com.google.android.gms.ads.internal.zzt.zzh().zzb(str4, zzq.zzI(), "", "javascript", str3, str, zzbywVar, zzbyvVar, this.zzb.zzan);
            if (zzb2 == null) {
                com.google.android.gms.ads.internal.util.zze.zzj("Failed to create omid session in InternalNativeAd");
                return;
            }
            this.zze.zzaa(zzb2);
            zzq.zzar(zzb2);
            if (z4) {
                com.google.android.gms.ads.internal.zzt.zzh().zzc(zzb2, zzr.zzH());
                this.zzr = true;
            }
            if (z) {
                com.google.android.gms.ads.internal.zzt.zzh().zzd(zzb2);
                zzq.zzd("onSdkLoaded", new ArrayMap());
                return;
            }
            return;
        }
        com.google.android.gms.ads.internal.util.zze.zzj("Omid display and video webview are null. Skipping initialization.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzr() {
        this.zzf.zzh();
        this.zze.zzG();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzs(boolean z) {
        this.zzf.zzn(this.zzo.zzf(), this.zzo.zzl(), this.zzo.zzm(), z);
    }

    public final synchronized void zzv(View view, Map map, Map map2, boolean z) {
        if (this.zzq) {
            return;
        }
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzbw)).booleanValue() && this.zzb.zzam) {
            for (String str : this.zzy.keySet()) {
                if (!((Boolean) this.zzy.get(str)).booleanValue()) {
                    return;
                }
            }
        }
        if (!z) {
            if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzcW)).booleanValue() && map != null) {
                for (Map.Entry entry : map.entrySet()) {
                    View view2 = (View) ((WeakReference) entry.getValue()).get();
                    if (view2 != null && zzP(view2)) {
                        zzS(view, map, map2);
                        return;
                    }
                }
            }
            return;
        }
        View zzR = zzR(map);
        if (zzR == null) {
            zzS(view, map, map2);
            return;
        }
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzcX)).booleanValue()) {
            if (zzP(zzR)) {
                zzS(view, map, map2);
                return;
            }
            return;
        }
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzcY)).booleanValue()) {
            Rect rect = new Rect();
            if (zzR.getGlobalVisibleRect(rect, null) && zzR.getHeight() == rect.height() && zzR.getWidth() == rect.width()) {
                zzS(view, map, map2);
                return;
            }
            return;
        }
        zzS(view, map, map2);
    }

    public final synchronized void zzw(com.google.android.gms.ads.internal.client.zzcu zzcuVar) {
        this.zzf.zzi(zzcuVar);
    }

    public final synchronized void zzx(View view, View view2, Map map, Map map2, boolean z) {
        this.zzg.zzc(this.zzo);
        this.zzf.zzj(view, view2, map, map2, z);
        if (this.zzr) {
            zzdoo zzdooVar = this.zze;
            if (zzdooVar.zzr() != null) {
                zzdooVar.zzr().zzd("onSdkAdUserInteractionClick", new ArrayMap());
            }
        }
    }

    public final synchronized void zzy(String str) {
        this.zzf.zzk(str);
    }

    public final synchronized void zzz(Bundle bundle) {
        this.zzf.zzl(bundle);
    }

    public static /* synthetic */ void zzh(zzdoj zzdojVar) {
        try {
            zzdoo zzdooVar = zzdojVar.zze;
            int zzc2 = zzdooVar.zzc();
            if (zzc2 == 1) {
                if (zzdojVar.zzi.zzb() != null) {
                    zzdojVar.zzq("Google", true);
                    zzdojVar.zzi.zzb().zze((zzbmr) zzdojVar.zzj.zzb());
                }
            } else if (zzc2 == 2) {
                if (zzdojVar.zzi.zza() != null) {
                    zzdojVar.zzq("Google", true);
                    zzdojVar.zzi.zza().zze((zzbmp) zzdojVar.zzk.zzb());
                }
            } else if (zzc2 == 3) {
                if (zzdojVar.zzi.zzd(zzdooVar.zzy()) != null) {
                    if (zzdojVar.zze.zzr() != null) {
                        zzdojVar.zzq("Google", true);
                    }
                    zzdojVar.zzi.zzd(zzdojVar.zze.zzy()).zze((zzbmu) zzdojVar.zzn.zzb());
                }
            } else if (zzc2 == 6) {
                if (zzdojVar.zzi.zzf() != null) {
                    zzdojVar.zzq("Google", true);
                    zzdojVar.zzi.zzf().zze((zzbnx) zzdojVar.zzl.zzb());
                }
            } else if (zzc2 != 7) {
                com.google.android.gms.ads.internal.util.zze.zzg("Wrong native template id!");
            } else {
                zzdoz zzdozVar = zzdojVar.zzi;
                if (zzdozVar.zzg() != null) {
                    zzdozVar.zzg().zzg((zzbsa) zzdojVar.zzm.zzb());
                }
            }
        } catch (RemoteException e) {
            com.google.android.gms.ads.internal.util.zze.zzh("RemoteException when notifyAdLoad is called", e);
        }
    }
}
