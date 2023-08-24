package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import com.polidea.rxandroidble.ClientComponent;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdzq {
    private final Context zzf;
    private final WeakReference zzg;
    private final zzdvj zzh;
    private final Executor zzi;
    private final Executor zzj;
    private final ScheduledExecutorService zzk;
    private final zzdxx zzl;
    private final zzcgt zzm;
    private final zzdjp zzo;
    private final zzfje zzp;
    private boolean zza = false;
    private boolean zzb = false;
    private boolean zzc = false;
    private final zzchf zze = new zzchf();
    private final Map zzn = new ConcurrentHashMap();
    private boolean zzq = true;
    private final long zzd = com.google.android.gms.ads.internal.zzt.zzB().elapsedRealtime();

    public zzdzq(Executor executor, Context context, WeakReference weakReference, Executor executor2, zzdvj zzdvjVar, ScheduledExecutorService scheduledExecutorService, zzdxx zzdxxVar, zzcgt zzcgtVar, zzdjp zzdjpVar, zzfje zzfjeVar) {
        this.zzh = zzdvjVar;
        this.zzf = context;
        this.zzg = weakReference;
        this.zzi = executor2;
        this.zzk = scheduledExecutorService;
        this.zzj = executor;
        this.zzl = zzdxxVar;
        this.zzm = zzcgtVar;
        this.zzo = zzdjpVar;
        this.zzp = zzfjeVar;
        zzv("com.google.android.gms.ads.MobileAds", false, "", 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ void zzj(final zzdzq zzdzqVar, String str) {
        int r11 = 5;
        final zzfir zza = zzfiq.zza(zzdzqVar.zzf, 5);
        zza.zzf();
        try {
            ArrayList arrayList = new ArrayList();
            JSONObject jSONObject = new JSONObject(str).getJSONObject("initializer_settings").getJSONObject("config");
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                final String next = keys.next();
                final zzfir zza2 = zzfiq.zza(zzdzqVar.zzf, r11);
                zza2.zzf();
                zza2.zzc(next);
                final Object obj = new Object();
                final zzchf zzchfVar = new zzchf();
                zzfyx zzo = zzfyo.zzo(zzchfVar, ((Long) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzbz)).longValue(), TimeUnit.SECONDS, zzdzqVar.zzk);
                zzdzqVar.zzl.zzc(next);
                zzdzqVar.zzo.zzc(next);
                final long elapsedRealtime = com.google.android.gms.ads.internal.zzt.zzB().elapsedRealtime();
                zzo.zzc(new Runnable() { // from class: com.google.android.gms.internal.ads.zzdzh
                    @Override // java.lang.Runnable
                    public final void run() {
                        zzdzq.this.zzq(obj, zzchfVar, next, elapsedRealtime, zza2);
                    }
                }, zzdzqVar.zzi);
                arrayList.add(zzo);
                final zzdzp zzdzpVar = new zzdzp(zzdzqVar, obj, next, elapsedRealtime, zza2, zzchfVar);
                JSONObject optJSONObject = jSONObject.optJSONObject(next);
                final ArrayList arrayList2 = new ArrayList();
                if (optJSONObject != null) {
                    try {
                        JSONArray jSONArray = optJSONObject.getJSONArray("data");
                        int r2 = 0;
                        while (r2 < jSONArray.length()) {
                            JSONObject jSONObject2 = jSONArray.getJSONObject(r2);
                            String optString = jSONObject2.optString("format", "");
                            JSONObject optJSONObject2 = jSONObject2.optJSONObject("data");
                            Bundle bundle = new Bundle();
                            if (optJSONObject2 != null) {
                                Iterator<String> keys2 = optJSONObject2.keys();
                                while (keys2.hasNext()) {
                                    String next2 = keys2.next();
                                    bundle.putString(next2, optJSONObject2.optString(next2, ""));
                                    jSONArray = jSONArray;
                                }
                            }
                            JSONArray jSONArray2 = jSONArray;
                            arrayList2.add(new zzbrv(optString, bundle));
                            r2++;
                            jSONArray = jSONArray2;
                        }
                    } catch (JSONException unused) {
                    }
                }
                zzdzqVar.zzv(next, false, "", 0);
                try {
                    try {
                        final zzfei zzc = zzdzqVar.zzh.zzc(next, new JSONObject());
                        zzdzqVar.zzj.execute(new Runnable() { // from class: com.google.android.gms.internal.ads.zzdzl
                            @Override // java.lang.Runnable
                            public final void run() {
                                zzdzq.this.zzn(zzc, zzdzpVar, arrayList2, next);
                            }
                        });
                    } catch (zzfds unused2) {
                        zzdzpVar.zze("Failed to create Adapter.");
                    }
                } catch (RemoteException e) {
                    zzcgn.zzh("", e);
                }
                r11 = 5;
            }
            zzfyo.zza(arrayList).zza(new Callable() { // from class: com.google.android.gms.internal.ads.zzdzi
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    zzdzq.this.zzf(zza);
                    return null;
                }
            }, zzdzqVar.zzi);
        } catch (JSONException e2) {
            com.google.android.gms.ads.internal.util.zze.zzb("Malformed CLD response", e2);
            zzdzqVar.zzo.zza("MalformedJson");
            zzdzqVar.zzl.zza("MalformedJson");
            zzdzqVar.zze.zze(e2);
            com.google.android.gms.ads.internal.zzt.zzp().zzt(e2, "AdapterInitializer.updateAdapterStatus");
            zzfje zzfjeVar = zzdzqVar.zzp;
            zza.zze(false);
            zzfjeVar.zzb(zza.zzj());
        }
    }

    private final synchronized zzfyx zzu() {
        String zzc = com.google.android.gms.ads.internal.zzt.zzp().zzh().zzh().zzc();
        if (TextUtils.isEmpty(zzc)) {
            final zzchf zzchfVar = new zzchf();
            com.google.android.gms.ads.internal.zzt.zzp().zzh().zzq(new Runnable() { // from class: com.google.android.gms.internal.ads.zzdzm
                @Override // java.lang.Runnable
                public final void run() {
                    zzdzq.this.zzo(zzchfVar);
                }
            });
            return zzchfVar;
        }
        return zzfyo.zzi(zzc);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzv(String str, boolean z, String str2, int r6) {
        this.zzn.put(str, new zzbrl(str, z, r6, str2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ Object zzf(zzfir zzfirVar) throws Exception {
        this.zze.zzd(true);
        zzfje zzfjeVar = this.zzp;
        zzfirVar.zze(true);
        zzfjeVar.zzb(zzfirVar.zzj());
        return null;
    }

    public final List zzg() {
        ArrayList arrayList = new ArrayList();
        for (String str : this.zzn.keySet()) {
            zzbrl zzbrlVar = (zzbrl) this.zzn.get(str);
            arrayList.add(new zzbrl(str, zzbrlVar.zzb, zzbrlVar.zzc, zzbrlVar.zzd));
        }
        return arrayList;
    }

    public final void zzl() {
        this.zzq = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzm() {
        synchronized (this) {
            if (this.zzc) {
                return;
            }
            zzv("com.google.android.gms.ads.MobileAds", false, "Timeout.", (int) (com.google.android.gms.ads.internal.zzt.zzB().elapsedRealtime() - this.zzd));
            this.zzl.zzb("com.google.android.gms.ads.MobileAds", ClientComponent.NamedSchedulers.TIMEOUT);
            this.zzo.zzb("com.google.android.gms.ads.MobileAds", ClientComponent.NamedSchedulers.TIMEOUT);
            this.zze.zze(new Exception());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzn(zzfei zzfeiVar, zzbrp zzbrpVar, List list, String str) {
        try {
            try {
                Context context = (Context) this.zzg.get();
                if (context == null) {
                    context = this.zzf;
                }
                zzfeiVar.zzi(context, zzbrpVar, list);
            } catch (RemoteException e) {
                zzcgn.zzh("", e);
            }
        } catch (zzfds unused) {
            zzbrpVar.zze("Failed to initialize adapter. " + str + " does not implement the initialize() method.");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzo(final zzchf zzchfVar) {
        this.zzi.execute(new Runnable() { // from class: com.google.android.gms.internal.ads.zzdzf
            @Override // java.lang.Runnable
            public final void run() {
                zzchf zzchfVar2 = zzchfVar;
                String zzc = com.google.android.gms.ads.internal.zzt.zzp().zzh().zzh().zzc();
                if (!TextUtils.isEmpty(zzc)) {
                    zzchfVar2.zzd(zzc);
                } else {
                    zzchfVar2.zze(new Exception());
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzp() {
        this.zzl.zze();
        this.zzo.zze();
        this.zzb = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzq(Object obj, zzchf zzchfVar, String str, long j, zzfir zzfirVar) {
        synchronized (obj) {
            if (!zzchfVar.isDone()) {
                zzv(str, false, "Timeout.", (int) (com.google.android.gms.ads.internal.zzt.zzB().elapsedRealtime() - j));
                this.zzl.zzb(str, ClientComponent.NamedSchedulers.TIMEOUT);
                this.zzo.zzb(str, ClientComponent.NamedSchedulers.TIMEOUT);
                zzfje zzfjeVar = this.zzp;
                zzfirVar.zze(false);
                zzfjeVar.zzb(zzfirVar.zzj());
                zzchfVar.zzd(false);
            }
        }
    }

    public final void zzr() {
        if (!((Boolean) zzbkt.zza.zze()).booleanValue()) {
            if (this.zzm.zzc >= ((Integer) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzby)).intValue() && this.zzq) {
                if (this.zza) {
                    return;
                }
                synchronized (this) {
                    if (this.zza) {
                        return;
                    }
                    this.zzl.zzf();
                    this.zzo.zzf();
                    this.zze.zzc(new Runnable() { // from class: com.google.android.gms.internal.ads.zzdzg
                        @Override // java.lang.Runnable
                        public final void run() {
                            zzdzq.this.zzp();
                        }
                    }, this.zzi);
                    this.zza = true;
                    zzfyx zzu = zzu();
                    this.zzk.schedule(new Runnable() { // from class: com.google.android.gms.internal.ads.zzdzj
                        @Override // java.lang.Runnable
                        public final void run() {
                            zzdzq.this.zzm();
                        }
                    }, ((Long) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzbA)).longValue(), TimeUnit.SECONDS);
                    zzfyo.zzr(zzu, new zzdzo(this), this.zzi);
                    return;
                }
            }
        }
        if (this.zza) {
            return;
        }
        zzv("com.google.android.gms.ads.MobileAds", true, "", 0);
        this.zze.zzd(false);
        this.zza = true;
        this.zzb = true;
    }

    public final void zzs(final zzbrs zzbrsVar) {
        this.zze.zzc(new Runnable() { // from class: com.google.android.gms.internal.ads.zzdzk
            @Override // java.lang.Runnable
            public final void run() {
                zzdzq zzdzqVar = zzdzq.this;
                try {
                    zzbrsVar.zzb(zzdzqVar.zzg());
                } catch (RemoteException e) {
                    zzcgn.zzh("", e);
                }
            }
        }, this.zzj);
    }

    public final boolean zzt() {
        return this.zzb;
    }
}
