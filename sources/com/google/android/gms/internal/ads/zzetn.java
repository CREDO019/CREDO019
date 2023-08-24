package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzetn implements zzeun {
    final String zza;
    private final zzfyy zzb;
    private final ScheduledExecutorService zzc;
    private final zzeml zzd;
    private final Context zze;
    private final zzfdn zzf;
    private final zzemh zzg;
    private final zzdvj zzh;

    public zzetn(zzfyy zzfyyVar, ScheduledExecutorService scheduledExecutorService, String str, zzeml zzemlVar, Context context, zzfdn zzfdnVar, zzemh zzemhVar, zzdvj zzdvjVar) {
        this.zzb = zzfyyVar;
        this.zzc = scheduledExecutorService;
        this.zza = str;
        this.zzd = zzemlVar;
        this.zze = context;
        this.zzf = zzfdnVar;
        this.zzg = zzemhVar;
        this.zzh = zzdvjVar;
    }

    public static /* synthetic */ zzfyx zzc(zzetn zzetnVar) {
        String str;
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzit)).booleanValue()) {
            str = zzetnVar.zzf.zzf.toLowerCase(Locale.ROOT);
        } else {
            str = zzetnVar.zzf.zzf;
        }
        Map zza = zzetnVar.zzd.zza(zzetnVar.zza, str);
        final ArrayList arrayList = new ArrayList();
        Iterator it = ((zzfuy) zza).entrySet().iterator();
        while (true) {
            Bundle bundle = null;
            if (!it.hasNext()) {
                break;
            }
            Map.Entry entry = (Map.Entry) it.next();
            String str2 = (String) entry.getKey();
            List list = (List) entry.getValue();
            Bundle bundle2 = zzetnVar.zzf.zzd.zzm;
            if (bundle2 != null) {
                bundle = bundle2.getBundle(str2);
            }
            arrayList.add(zzetnVar.zze(str2, list, bundle, true, true));
        }
        for (Map.Entry entry2 : ((zzfuy) zzetnVar.zzd.zzb()).entrySet()) {
            zzemp zzempVar = (zzemp) entry2.getValue();
            String str3 = zzempVar.zza;
            Bundle bundle3 = zzetnVar.zzf.zzd.zzm;
            arrayList.add(zzetnVar.zze(str3, Collections.singletonList(zzempVar.zzd), bundle3 != null ? bundle3.getBundle(str3) : null, zzempVar.zzb, zzempVar.zzc));
        }
        return zzfyo.zzc(arrayList).zza(new Callable() { // from class: com.google.android.gms.internal.ads.zzetk
            @Override // java.util.concurrent.Callable
            public final Object call() {
                List<zzfyx> list2 = arrayList;
                JSONArray jSONArray = new JSONArray();
                for (zzfyx zzfyxVar : list2) {
                    if (((JSONObject) zzfyxVar.get()) != null) {
                        jSONArray.put(zzfyxVar.get());
                    }
                }
                if (jSONArray.length() == 0) {
                    return null;
                }
                return new zzeto(jSONArray.toString());
            }
        }, zzetnVar.zzb);
    }

    private final zzfyf zze(final String str, final List list, final Bundle bundle, final boolean z, final boolean z2) {
        zzfyf zzv = zzfyf.zzv(zzfyo.zzl(new zzfxu() { // from class: com.google.android.gms.internal.ads.zzetl
            @Override // com.google.android.gms.internal.ads.zzfxu
            public final zzfyx zza() {
                return zzetn.this.zzd(str, list, bundle, z, z2);
            }
        }, this.zzb));
        if (!((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzbs)).booleanValue()) {
            zzv = (zzfyf) zzfyo.zzo(zzv, ((Long) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzbl)).longValue(), TimeUnit.MILLISECONDS, this.zzc);
        }
        return (zzfyf) zzfyo.zzf(zzv, Throwable.class, new zzfru() { // from class: com.google.android.gms.internal.ads.zzetm
            @Override // com.google.android.gms.internal.ads.zzfru
            public final Object apply(Object obj) {
                Throwable th = (Throwable) obj;
                com.google.android.gms.ads.internal.util.zze.zzg("Error calling adapter: ".concat(String.valueOf(str)));
                return null;
            }
        }, this.zzb);
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final int zza() {
        return 32;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final zzfyx zzb() {
        return zzfyo.zzl(new zzfxu() { // from class: com.google.android.gms.internal.ads.zzeti
            @Override // com.google.android.gms.internal.ads.zzfxu
            public final zzfyx zza() {
                return zzetn.zzc(zzetn.this);
            }
        }, this.zzb);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzfyx zzd(String str, List list, Bundle bundle, boolean z, boolean z2) throws Exception {
        zzbwy zzbwyVar;
        zzbwy zzb;
        zzchf zzchfVar = new zzchf();
        if (z2) {
            this.zzg.zzb(str);
            zzb = this.zzg.zza(str);
        } else {
            try {
                zzb = this.zzh.zzb(str);
            } catch (RemoteException e) {
                com.google.android.gms.ads.internal.util.zze.zzh("Couldn't create RTB adapter : ", e);
                zzbwyVar = null;
            }
        }
        zzbwyVar = zzb;
        if (zzbwyVar == null) {
            if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzbn)).booleanValue()) {
                zzemo.zzb(str, zzchfVar);
            } else {
                throw null;
            }
        } else {
            final zzemo zzemoVar = new zzemo(str, zzbwyVar, zzchfVar);
            if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzbs)).booleanValue()) {
                this.zzc.schedule(new Runnable() { // from class: com.google.android.gms.internal.ads.zzetj
                    @Override // java.lang.Runnable
                    public final void run() {
                        zzemo.this.zzc();
                    }
                }, ((Long) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzbl)).longValue(), TimeUnit.MILLISECONDS);
            }
            if (z) {
                zzbwyVar.zzh(ObjectWrapper.wrap(this.zze), this.zza, bundle, (Bundle) list.get(0), this.zzf.zze, zzemoVar);
            } else {
                zzemoVar.zzd();
            }
        }
        return zzchfVar;
    }
}
