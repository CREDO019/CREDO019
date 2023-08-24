package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcsu extends com.google.android.gms.ads.internal.client.zzcl {
    private final Context zza;
    private final zzcgt zzb;
    private final zzdvj zzc;
    private final zzegm zzd;
    private final zzeml zze;
    private final zzdzq zzf;
    private final zzces zzg;
    private final zzdvo zzh;
    private final zzeai zzi;
    private final zzblh zzj;
    private final zzfje zzk;
    private final zzfeg zzl;
    private boolean zzm = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzcsu(Context context, zzcgt zzcgtVar, zzdvj zzdvjVar, zzegm zzegmVar, zzeml zzemlVar, zzdzq zzdzqVar, zzces zzcesVar, zzdvo zzdvoVar, zzeai zzeaiVar, zzblh zzblhVar, zzfje zzfjeVar, zzfeg zzfegVar) {
        this.zza = context;
        this.zzb = zzcgtVar;
        this.zzc = zzdvjVar;
        this.zzd = zzegmVar;
        this.zze = zzemlVar;
        this.zzf = zzdzqVar;
        this.zzg = zzcesVar;
        this.zzh = zzdvoVar;
        this.zzi = zzeaiVar;
        this.zzj = zzblhVar;
        this.zzk = zzfjeVar;
        this.zzl = zzfegVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzb() {
        if (com.google.android.gms.ads.internal.zzt.zzp().zzh().zzO()) {
            if (com.google.android.gms.ads.internal.zzt.zzt().zzj(this.zza, com.google.android.gms.ads.internal.zzt.zzp().zzh().zzl(), this.zzb.zza)) {
                return;
            }
            com.google.android.gms.ads.internal.zzt.zzp().zzh().zzB(false);
            com.google.android.gms.ads.internal.zzt.zzp().zzh().zzA("");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzc(Runnable runnable) {
        Preconditions.checkMainThread("Adapters must be initialized on the main thread.");
        Map zze = com.google.android.gms.ads.internal.zzt.zzp().zzh().zzh().zze();
        if (zze.isEmpty()) {
            return;
        }
        if (runnable != null) {
            try {
                runnable.run();
            } catch (Throwable th) {
                com.google.android.gms.ads.internal.util.zze.zzk("Could not initialize rewarded ads.", th);
                return;
            }
        }
        if (this.zzc.zzd()) {
            HashMap hashMap = new HashMap();
            for (zzbva zzbvaVar : zze.values()) {
                for (zzbuz zzbuzVar : zzbvaVar.zza) {
                    String str = zzbuzVar.zzk;
                    for (String str2 : zzbuzVar.zzc) {
                        if (!hashMap.containsKey(str2)) {
                            hashMap.put(str2, new ArrayList());
                        }
                        if (str != null) {
                            ((Collection) hashMap.get(str2)).add(str);
                        }
                    }
                }
            }
            JSONObject jSONObject = new JSONObject();
            for (Map.Entry entry : hashMap.entrySet()) {
                String str3 = (String) entry.getKey();
                try {
                    zzegn zza = this.zzd.zza(str3, jSONObject);
                    if (zza != null) {
                        zzfei zzfeiVar = (zzfei) zza.zzb;
                        if (!zzfeiVar.zzA() && zzfeiVar.zzz()) {
                            zzfeiVar.zzj(this.zza, (zzeih) zza.zzc, (List) entry.getValue());
                            com.google.android.gms.ads.internal.util.zze.zze("Initialized rewarded video mediation adapter " + str3);
                        }
                    }
                } catch (zzfds e) {
                    com.google.android.gms.ads.internal.util.zze.zzk("Failed to initialize rewarded video mediation adapter \"" + str3 + "\"", e);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzd() {
        zzfep.zzb(this.zza, true);
    }

    @Override // com.google.android.gms.ads.internal.client.zzcm
    public final synchronized float zze() {
        return com.google.android.gms.ads.internal.zzt.zzs().zza();
    }

    @Override // com.google.android.gms.ads.internal.client.zzcm
    public final String zzf() {
        return this.zzb.zza;
    }

    @Override // com.google.android.gms.ads.internal.client.zzcm
    public final List zzg() throws RemoteException {
        return this.zzf.zzg();
    }

    @Override // com.google.android.gms.ads.internal.client.zzcm
    public final void zzh(String str) {
        this.zze.zzf(str);
    }

    @Override // com.google.android.gms.ads.internal.client.zzcm
    public final void zzi() {
        this.zzf.zzl();
    }

    @Override // com.google.android.gms.ads.internal.client.zzcm
    public final synchronized void zzj() {
        if (this.zzm) {
            com.google.android.gms.ads.internal.util.zze.zzj("Mobile ads is initialized already.");
            return;
        }
        zzbiy.zzc(this.zza);
        com.google.android.gms.ads.internal.zzt.zzp().zzr(this.zza, this.zzb);
        com.google.android.gms.ads.internal.zzt.zzc().zzi(this.zza);
        this.zzm = true;
        this.zzf.zzr();
        this.zze.zzd();
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzdc)).booleanValue()) {
            this.zzh.zzc();
        }
        this.zzi.zzf();
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzhO)).booleanValue()) {
            zzcha.zza.execute(new Runnable() { // from class: com.google.android.gms.internal.ads.zzcsq
                @Override // java.lang.Runnable
                public final void run() {
                    zzcsu.this.zzb();
                }
            });
        }
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zziv)).booleanValue()) {
            zzcha.zza.execute(new Runnable() { // from class: com.google.android.gms.internal.ads.zzcsp
                @Override // java.lang.Runnable
                public final void run() {
                    zzcsu.this.zzu();
                }
            });
        }
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzco)).booleanValue()) {
            zzcha.zza.execute(new Runnable() { // from class: com.google.android.gms.internal.ads.zzcsr
                @Override // java.lang.Runnable
                public final void run() {
                    zzcsu.this.zzd();
                }
            });
        }
    }

    @Override // com.google.android.gms.ads.internal.client.zzcm
    public final void zzk(String str, IObjectWrapper iObjectWrapper) {
        String str2;
        Runnable runnable;
        zzbiy.zzc(this.zza);
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzde)).booleanValue()) {
            com.google.android.gms.ads.internal.zzt.zzq();
            str2 = com.google.android.gms.ads.internal.util.zzs.zzo(this.zza);
        } else {
            str2 = "";
        }
        boolean z = true;
        String str3 = true != TextUtils.isEmpty(str2) ? str2 : str;
        if (TextUtils.isEmpty(str3)) {
            return;
        }
        boolean booleanValue = ((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzdb)).booleanValue() | ((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzaK)).booleanValue();
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzaK)).booleanValue()) {
            final Runnable runnable2 = (Runnable) ObjectWrapper.unwrap(iObjectWrapper);
            runnable = new Runnable() { // from class: com.google.android.gms.internal.ads.zzcss
                @Override // java.lang.Runnable
                public final void run() {
                    final zzcsu zzcsuVar = zzcsu.this;
                    final Runnable runnable3 = runnable2;
                    zzcha.zze.execute(new Runnable() { // from class: com.google.android.gms.internal.ads.zzcst
                        @Override // java.lang.Runnable
                        public final void run() {
                            zzcsu.this.zzc(runnable3);
                        }
                    });
                }
            };
        } else {
            runnable = null;
            z = booleanValue;
        }
        Runnable runnable3 = runnable;
        if (z) {
            com.google.android.gms.ads.internal.zzt.zza().zza(this.zza, this.zzb, str3, runnable3, this.zzk);
        }
    }

    @Override // com.google.android.gms.ads.internal.client.zzcm
    public final void zzl(com.google.android.gms.ads.internal.client.zzcy zzcyVar) throws RemoteException {
        this.zzi.zzg(zzcyVar, zzeah.API);
    }

    @Override // com.google.android.gms.ads.internal.client.zzcm
    public final void zzm(IObjectWrapper iObjectWrapper, String str) {
        if (iObjectWrapper == null) {
            com.google.android.gms.ads.internal.util.zze.zzg("Wrapped context is null. Failed to open debug menu.");
            return;
        }
        Context context = (Context) ObjectWrapper.unwrap(iObjectWrapper);
        if (context == null) {
            com.google.android.gms.ads.internal.util.zze.zzg("Context is null. Failed to open debug menu.");
            return;
        }
        com.google.android.gms.ads.internal.util.zzas zzasVar = new com.google.android.gms.ads.internal.util.zzas(context);
        zzasVar.zzn(str);
        zzasVar.zzo(this.zzb.zza);
        zzasVar.zzr();
    }

    @Override // com.google.android.gms.ads.internal.client.zzcm
    public final void zzn(zzbvf zzbvfVar) throws RemoteException {
        this.zzl.zze(zzbvfVar);
    }

    @Override // com.google.android.gms.ads.internal.client.zzcm
    public final synchronized void zzo(boolean z) {
        com.google.android.gms.ads.internal.zzt.zzs().zzc(z);
    }

    @Override // com.google.android.gms.ads.internal.client.zzcm
    public final synchronized void zzp(float f) {
        com.google.android.gms.ads.internal.zzt.zzs().zzd(f);
    }

    @Override // com.google.android.gms.ads.internal.client.zzcm
    public final synchronized void zzq(String str) {
        zzbiy.zzc(this.zza);
        if (!TextUtils.isEmpty(str)) {
            if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzdb)).booleanValue()) {
                com.google.android.gms.ads.internal.zzt.zza().zza(this.zza, this.zzb, str, null, this.zzk);
            }
        }
    }

    @Override // com.google.android.gms.ads.internal.client.zzcm
    public final void zzr(zzbrs zzbrsVar) throws RemoteException {
        this.zzf.zzs(zzbrsVar);
    }

    @Override // com.google.android.gms.ads.internal.client.zzcm
    public final void zzs(com.google.android.gms.ads.internal.client.zzez zzezVar) throws RemoteException {
        this.zzg.zzq(this.zza, zzezVar);
    }

    @Override // com.google.android.gms.ads.internal.client.zzcm
    public final synchronized boolean zzt() {
        return com.google.android.gms.ads.internal.zzt.zzs().zze();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzu() {
        this.zzj.zza(new zzcai());
    }
}
