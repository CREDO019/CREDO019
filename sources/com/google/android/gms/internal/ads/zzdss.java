package com.google.android.gms.internal.ads;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import androidx.collection.ArrayMap;
import androidx.collection.SimpleArrayMap;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.Arrays;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdss extends zzbmt {
    private final Context zza;
    private final zzdoo zzb;
    private zzdpo zzc;
    private zzdoj zzd;

    public zzdss(Context context, zzdoo zzdooVar, zzdpo zzdpoVar, zzdoj zzdojVar) {
        this.zza = context;
        this.zzb = zzdooVar;
        this.zzc = zzdpoVar;
        this.zzd = zzdojVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbmu
    public final com.google.android.gms.ads.internal.client.zzdk zze() {
        return this.zzb.zzj();
    }

    @Override // com.google.android.gms.internal.ads.zzbmu
    public final zzbma zzf(String str) {
        return (zzbma) this.zzb.zzh().get(str);
    }

    @Override // com.google.android.gms.internal.ads.zzbmu
    public final IObjectWrapper zzg() {
        return ObjectWrapper.wrap(this.zza);
    }

    @Override // com.google.android.gms.internal.ads.zzbmu
    public final String zzh() {
        return this.zzb.zzy();
    }

    @Override // com.google.android.gms.internal.ads.zzbmu
    public final String zzi(String str) {
        return (String) this.zzb.zzi().get(str);
    }

    @Override // com.google.android.gms.internal.ads.zzbmu
    public final List zzj() {
        SimpleArrayMap zzh = this.zzb.zzh();
        SimpleArrayMap zzi = this.zzb.zzi();
        String[] strArr = new String[zzh.size() + zzi.size()];
        int r3 = 0;
        int r4 = 0;
        int r5 = 0;
        while (r4 < zzh.size()) {
            strArr[r5] = (String) zzh.keyAt(r4);
            r4++;
            r5++;
        }
        while (r3 < zzi.size()) {
            strArr[r5] = (String) zzi.keyAt(r3);
            r3++;
            r5++;
        }
        return Arrays.asList(strArr);
    }

    @Override // com.google.android.gms.internal.ads.zzbmu
    public final void zzk() {
        zzdoj zzdojVar = this.zzd;
        if (zzdojVar != null) {
            zzdojVar.zzV();
        }
        this.zzd = null;
        this.zzc = null;
    }

    @Override // com.google.android.gms.internal.ads.zzbmu
    public final void zzl() {
        String zzA = this.zzb.zzA();
        if ("Google".equals(zzA)) {
            com.google.android.gms.ads.internal.util.zze.zzj("Illegal argument specified for omid partner name.");
        } else if (TextUtils.isEmpty(zzA)) {
            com.google.android.gms.ads.internal.util.zze.zzj("Not starting OMID session. OM partner name has not been configured.");
        } else {
            zzdoj zzdojVar = this.zzd;
            if (zzdojVar != null) {
                zzdojVar.zzq(zzA, false);
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbmu
    public final void zzm(String str) {
        zzdoj zzdojVar = this.zzd;
        if (zzdojVar != null) {
            zzdojVar.zzy(str);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbmu
    public final void zzn() {
        zzdoj zzdojVar = this.zzd;
        if (zzdojVar != null) {
            zzdojVar.zzB();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbmu
    public final void zzo(IObjectWrapper iObjectWrapper) {
        zzdoj zzdojVar;
        Object unwrap = ObjectWrapper.unwrap(iObjectWrapper);
        if (!(unwrap instanceof View) || this.zzb.zzu() == null || (zzdojVar = this.zzd) == null) {
            return;
        }
        zzdojVar.zzC((View) unwrap);
    }

    @Override // com.google.android.gms.internal.ads.zzbmu
    public final boolean zzp() {
        zzdoj zzdojVar = this.zzd;
        return (zzdojVar == null || zzdojVar.zzO()) && this.zzb.zzq() != null && this.zzb.zzr() == null;
    }

    @Override // com.google.android.gms.internal.ads.zzbmu
    public final boolean zzq(IObjectWrapper iObjectWrapper) {
        zzdpo zzdpoVar;
        Object unwrap = ObjectWrapper.unwrap(iObjectWrapper);
        if ((unwrap instanceof ViewGroup) && (zzdpoVar = this.zzc) != null && zzdpoVar.zzf((ViewGroup) unwrap)) {
            this.zzb.zzr().zzaq(new zzdsr(this));
            return true;
        }
        return false;
    }

    @Override // com.google.android.gms.internal.ads.zzbmu
    public final boolean zzr() {
        IObjectWrapper zzu = this.zzb.zzu();
        if (zzu != null) {
            com.google.android.gms.ads.internal.zzt.zzh().zzd(zzu);
            if (this.zzb.zzq() != null) {
                this.zzb.zzq().zzd("onSdkLoaded", new ArrayMap());
                return true;
            }
            return true;
        }
        com.google.android.gms.ads.internal.util.zze.zzj("Trying to start OMID session before creation.");
        return false;
    }
}
