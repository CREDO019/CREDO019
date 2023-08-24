package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import androidx.collection.SimpleArrayMap;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.Collections;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdoo {
    private int zza;
    private com.google.android.gms.ads.internal.client.zzdk zzb;
    private zzbls zzc;
    private View zzd;
    private List zze;
    private com.google.android.gms.ads.internal.client.zzef zzg;
    private Bundle zzh;
    private zzcmn zzi;
    private zzcmn zzj;
    private zzcmn zzk;
    private IObjectWrapper zzl;
    private View zzm;
    private View zzn;
    private IObjectWrapper zzo;
    private double zzp;
    private zzbma zzq;
    private zzbma zzr;
    private String zzs;
    private float zzv;
    private String zzw;
    private final SimpleArrayMap zzt = new SimpleArrayMap();
    private final SimpleArrayMap zzu = new SimpleArrayMap();
    private List zzf = Collections.emptyList();

    public static zzdoo zzab(zzbvq zzbvqVar) {
        try {
            zzdon zzaf = zzaf(zzbvqVar.zzg(), null);
            zzbls zzh = zzbvqVar.zzh();
            String zzo = zzbvqVar.zzo();
            List zzr = zzbvqVar.zzr();
            String zzm = zzbvqVar.zzm();
            Bundle zzf = zzbvqVar.zzf();
            String zzn = zzbvqVar.zzn();
            IObjectWrapper zzl = zzbvqVar.zzl();
            String zzq = zzbvqVar.zzq();
            String zzp = zzbvqVar.zzp();
            double zze = zzbvqVar.zze();
            zzbma zzi = zzbvqVar.zzi();
            zzdoo zzdooVar = new zzdoo();
            zzdooVar.zza = 2;
            zzdooVar.zzb = zzaf;
            zzdooVar.zzc = zzh;
            zzdooVar.zzd = (View) zzah(zzbvqVar.zzj());
            zzdooVar.zzU("headline", zzo);
            zzdooVar.zze = zzr;
            zzdooVar.zzU(TtmlNode.TAG_BODY, zzm);
            zzdooVar.zzh = zzf;
            zzdooVar.zzU("call_to_action", zzn);
            zzdooVar.zzm = (View) zzah(zzbvqVar.zzk());
            zzdooVar.zzo = zzl;
            zzdooVar.zzU("store", zzq);
            zzdooVar.zzU("price", zzp);
            zzdooVar.zzp = zze;
            zzdooVar.zzq = zzi;
            return zzdooVar;
        } catch (RemoteException e) {
            com.google.android.gms.ads.internal.util.zze.zzk("Failed to get native ad from app install ad mapper", e);
            return null;
        }
    }

    public static zzdoo zzac(zzbvr zzbvrVar) {
        try {
            zzdon zzaf = zzaf(zzbvrVar.zzf(), null);
            zzbls zzg = zzbvrVar.zzg();
            String zzo = zzbvrVar.zzo();
            List zzp = zzbvrVar.zzp();
            String zzm = zzbvrVar.zzm();
            Bundle zze = zzbvrVar.zze();
            String zzn = zzbvrVar.zzn();
            IObjectWrapper zzk = zzbvrVar.zzk();
            String zzl = zzbvrVar.zzl();
            zzbma zzh = zzbvrVar.zzh();
            zzdoo zzdooVar = new zzdoo();
            zzdooVar.zza = 1;
            zzdooVar.zzb = zzaf;
            zzdooVar.zzc = zzg;
            zzdooVar.zzd = (View) zzah(zzbvrVar.zzi());
            zzdooVar.zzU("headline", zzo);
            zzdooVar.zze = zzp;
            zzdooVar.zzU(TtmlNode.TAG_BODY, zzm);
            zzdooVar.zzh = zze;
            zzdooVar.zzU("call_to_action", zzn);
            zzdooVar.zzm = (View) zzah(zzbvrVar.zzj());
            zzdooVar.zzo = zzk;
            zzdooVar.zzU("advertiser", zzl);
            zzdooVar.zzr = zzh;
            return zzdooVar;
        } catch (RemoteException e) {
            com.google.android.gms.ads.internal.util.zze.zzk("Failed to get native ad from content ad mapper", e);
            return null;
        }
    }

    public static zzdoo zzad(zzbvq zzbvqVar) {
        try {
            return zzag(zzaf(zzbvqVar.zzg(), null), zzbvqVar.zzh(), (View) zzah(zzbvqVar.zzj()), zzbvqVar.zzo(), zzbvqVar.zzr(), zzbvqVar.zzm(), zzbvqVar.zzf(), zzbvqVar.zzn(), (View) zzah(zzbvqVar.zzk()), zzbvqVar.zzl(), zzbvqVar.zzq(), zzbvqVar.zzp(), zzbvqVar.zze(), zzbvqVar.zzi(), null, 0.0f);
        } catch (RemoteException e) {
            com.google.android.gms.ads.internal.util.zze.zzk("Failed to get native ad assets from app install ad mapper", e);
            return null;
        }
    }

    public static zzdoo zzae(zzbvr zzbvrVar) {
        try {
            return zzag(zzaf(zzbvrVar.zzf(), null), zzbvrVar.zzg(), (View) zzah(zzbvrVar.zzi()), zzbvrVar.zzo(), zzbvrVar.zzp(), zzbvrVar.zzm(), zzbvrVar.zze(), zzbvrVar.zzn(), (View) zzah(zzbvrVar.zzj()), zzbvrVar.zzk(), null, null, -1.0d, zzbvrVar.zzh(), zzbvrVar.zzl(), 0.0f);
        } catch (RemoteException e) {
            com.google.android.gms.ads.internal.util.zze.zzk("Failed to get native ad assets from content ad mapper", e);
            return null;
        }
    }

    private static zzdon zzaf(com.google.android.gms.ads.internal.client.zzdk zzdkVar, zzbvu zzbvuVar) {
        if (zzdkVar == null) {
            return null;
        }
        return new zzdon(zzdkVar, zzbvuVar);
    }

    private static zzdoo zzag(com.google.android.gms.ads.internal.client.zzdk zzdkVar, zzbls zzblsVar, View view, String str, List list, String str2, Bundle bundle, String str3, View view2, IObjectWrapper iObjectWrapper, String str4, String str5, double d, zzbma zzbmaVar, String str6, float f) {
        zzdoo zzdooVar = new zzdoo();
        zzdooVar.zza = 6;
        zzdooVar.zzb = zzdkVar;
        zzdooVar.zzc = zzblsVar;
        zzdooVar.zzd = view;
        zzdooVar.zzU("headline", str);
        zzdooVar.zze = list;
        zzdooVar.zzU(TtmlNode.TAG_BODY, str2);
        zzdooVar.zzh = bundle;
        zzdooVar.zzU("call_to_action", str3);
        zzdooVar.zzm = view2;
        zzdooVar.zzo = iObjectWrapper;
        zzdooVar.zzU("store", str4);
        zzdooVar.zzU("price", str5);
        zzdooVar.zzp = d;
        zzdooVar.zzq = zzbmaVar;
        zzdooVar.zzU("advertiser", str6);
        zzdooVar.zzP(f);
        return zzdooVar;
    }

    private static Object zzah(IObjectWrapper iObjectWrapper) {
        if (iObjectWrapper == null) {
            return null;
        }
        return ObjectWrapper.unwrap(iObjectWrapper);
    }

    public static zzdoo zzs(zzbvu zzbvuVar) {
        try {
            return zzag(zzaf(zzbvuVar.zzj(), zzbvuVar), zzbvuVar.zzk(), (View) zzah(zzbvuVar.zzm()), zzbvuVar.zzs(), zzbvuVar.zzv(), zzbvuVar.zzq(), zzbvuVar.zzi(), zzbvuVar.zzr(), (View) zzah(zzbvuVar.zzn()), zzbvuVar.zzo(), zzbvuVar.zzu(), zzbvuVar.zzt(), zzbvuVar.zze(), zzbvuVar.zzl(), zzbvuVar.zzp(), zzbvuVar.zzf());
        } catch (RemoteException e) {
            com.google.android.gms.ads.internal.util.zze.zzk("Failed to get native ad assets from unified ad mapper", e);
            return null;
        }
    }

    public final synchronized String zzA() {
        return this.zzw;
    }

    public final synchronized String zzB() {
        return zzD("price");
    }

    public final synchronized String zzC() {
        return zzD("store");
    }

    public final synchronized String zzD(String str) {
        return (String) this.zzu.get(str);
    }

    public final synchronized List zzE() {
        return this.zze;
    }

    public final synchronized List zzF() {
        return this.zzf;
    }

    public final synchronized void zzG() {
        zzcmn zzcmnVar = this.zzi;
        if (zzcmnVar != null) {
            zzcmnVar.destroy();
            this.zzi = null;
        }
        zzcmn zzcmnVar2 = this.zzj;
        if (zzcmnVar2 != null) {
            zzcmnVar2.destroy();
            this.zzj = null;
        }
        zzcmn zzcmnVar3 = this.zzk;
        if (zzcmnVar3 != null) {
            zzcmnVar3.destroy();
            this.zzk = null;
        }
        this.zzl = null;
        this.zzt.clear();
        this.zzu.clear();
        this.zzb = null;
        this.zzc = null;
        this.zzd = null;
        this.zze = null;
        this.zzh = null;
        this.zzm = null;
        this.zzn = null;
        this.zzo = null;
        this.zzq = null;
        this.zzr = null;
        this.zzs = null;
    }

    public final synchronized void zzH(zzbls zzblsVar) {
        this.zzc = zzblsVar;
    }

    public final synchronized void zzI(String str) {
        this.zzs = str;
    }

    public final synchronized void zzJ(com.google.android.gms.ads.internal.client.zzef zzefVar) {
        this.zzg = zzefVar;
    }

    public final synchronized void zzK(zzbma zzbmaVar) {
        this.zzq = zzbmaVar;
    }

    public final synchronized void zzL(String str, zzblm zzblmVar) {
        if (zzblmVar == null) {
            this.zzt.remove(str);
        } else {
            this.zzt.put(str, zzblmVar);
        }
    }

    public final synchronized void zzM(zzcmn zzcmnVar) {
        this.zzj = zzcmnVar;
    }

    public final synchronized void zzN(List list) {
        this.zze = list;
    }

    public final synchronized void zzO(zzbma zzbmaVar) {
        this.zzr = zzbmaVar;
    }

    public final synchronized void zzP(float f) {
        this.zzv = f;
    }

    public final synchronized void zzQ(List list) {
        this.zzf = list;
    }

    public final synchronized void zzR(zzcmn zzcmnVar) {
        this.zzk = zzcmnVar;
    }

    public final synchronized void zzS(String str) {
        this.zzw = str;
    }

    public final synchronized void zzT(double d) {
        this.zzp = d;
    }

    public final synchronized void zzU(String str, String str2) {
        if (str2 == null) {
            this.zzu.remove(str);
        } else {
            this.zzu.put(str, str2);
        }
    }

    public final synchronized void zzV(int r1) {
        this.zza = r1;
    }

    public final synchronized void zzW(com.google.android.gms.ads.internal.client.zzdk zzdkVar) {
        this.zzb = zzdkVar;
    }

    public final synchronized void zzX(View view) {
        this.zzm = view;
    }

    public final synchronized void zzY(zzcmn zzcmnVar) {
        this.zzi = zzcmnVar;
    }

    public final synchronized void zzZ(View view) {
        this.zzn = view;
    }

    public final synchronized double zza() {
        return this.zzp;
    }

    public final synchronized void zzaa(IObjectWrapper iObjectWrapper) {
        this.zzl = iObjectWrapper;
    }

    public final synchronized float zzb() {
        return this.zzv;
    }

    public final synchronized int zzc() {
        return this.zza;
    }

    public final synchronized Bundle zzd() {
        if (this.zzh == null) {
            this.zzh = new Bundle();
        }
        return this.zzh;
    }

    public final synchronized View zze() {
        return this.zzd;
    }

    public final synchronized View zzf() {
        return this.zzm;
    }

    public final synchronized View zzg() {
        return this.zzn;
    }

    public final synchronized SimpleArrayMap zzh() {
        return this.zzt;
    }

    public final synchronized SimpleArrayMap zzi() {
        return this.zzu;
    }

    public final synchronized com.google.android.gms.ads.internal.client.zzdk zzj() {
        return this.zzb;
    }

    public final synchronized com.google.android.gms.ads.internal.client.zzef zzk() {
        return this.zzg;
    }

    public final synchronized zzbls zzl() {
        return this.zzc;
    }

    public final zzbma zzm() {
        List list = this.zze;
        if (list != null && !list.isEmpty()) {
            Object obj = this.zze.get(0);
            if (obj instanceof IBinder) {
                return zzblz.zzg((IBinder) obj);
            }
        }
        return null;
    }

    public final synchronized zzbma zzn() {
        return this.zzq;
    }

    public final synchronized zzbma zzo() {
        return this.zzr;
    }

    public final synchronized zzcmn zzp() {
        return this.zzj;
    }

    public final synchronized zzcmn zzq() {
        return this.zzk;
    }

    public final synchronized zzcmn zzr() {
        return this.zzi;
    }

    public final synchronized IObjectWrapper zzt() {
        return this.zzo;
    }

    public final synchronized IObjectWrapper zzu() {
        return this.zzl;
    }

    public final synchronized String zzv() {
        return zzD("advertiser");
    }

    public final synchronized String zzw() {
        return zzD(TtmlNode.TAG_BODY);
    }

    public final synchronized String zzx() {
        return zzD("call_to_action");
    }

    public final synchronized String zzy() {
        return this.zzs;
    }

    public final synchronized String zzz() {
        return zzD("headline");
    }
}
