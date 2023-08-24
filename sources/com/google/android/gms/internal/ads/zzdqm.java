package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.MotionEvent;
import android.view.View;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
@ParametersAreNonnullByDefault
/* loaded from: classes2.dex */
public final class zzdqm implements zzdow {
    private final zzbvu zza;
    private final zzddq zzb;
    private final zzdcw zzc;
    private final zzdkj zzd;
    private final Context zze;
    private final zzfcs zzf;
    private final zzcgt zzg;
    private final zzfdn zzh;
    private boolean zzi = false;
    private boolean zzj = false;
    private boolean zzk = true;
    private final zzbvq zzl;
    private final zzbvr zzm;

    public zzdqm(zzbvq zzbvqVar, zzbvr zzbvrVar, zzbvu zzbvuVar, zzddq zzddqVar, zzdcw zzdcwVar, zzdkj zzdkjVar, Context context, zzfcs zzfcsVar, zzcgt zzcgtVar, zzfdn zzfdnVar, byte[] bArr) {
        this.zzl = zzbvqVar;
        this.zzm = zzbvrVar;
        this.zza = zzbvuVar;
        this.zzb = zzddqVar;
        this.zzc = zzdcwVar;
        this.zzd = zzdkjVar;
        this.zze = context;
        this.zzf = zzfcsVar;
        this.zzg = zzcgtVar;
        this.zzh = zzfdnVar;
    }

    private final void zza(View view) {
        try {
            zzbvu zzbvuVar = this.zza;
            if (zzbvuVar == null || zzbvuVar.zzA()) {
                zzbvq zzbvqVar = this.zzl;
                if (zzbvqVar == null || zzbvqVar.zzx()) {
                    zzbvr zzbvrVar = this.zzm;
                    if (zzbvrVar == null || zzbvrVar.zzv()) {
                        return;
                    }
                    this.zzm.zzq(ObjectWrapper.wrap(view));
                    this.zzc.onAdClicked();
                    if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zziq)).booleanValue()) {
                        this.zzd.zzq();
                        return;
                    }
                    return;
                }
                this.zzl.zzs(ObjectWrapper.wrap(view));
                this.zzc.onAdClicked();
                if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zziq)).booleanValue()) {
                    this.zzd.zzq();
                    return;
                }
                return;
            }
            this.zza.zzw(ObjectWrapper.wrap(view));
            this.zzc.onAdClicked();
            if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zziq)).booleanValue()) {
                this.zzd.zzq();
            }
        } catch (RemoteException e) {
            com.google.android.gms.ads.internal.util.zze.zzk("Failed to call handleClick", e);
        }
    }

    private static final HashMap zzb(Map map) {
        HashMap hashMap = new HashMap();
        if (map == null) {
            return hashMap;
        }
        synchronized (map) {
            for (Map.Entry entry : map.entrySet()) {
                View view = (View) ((WeakReference) entry.getValue()).get();
                if (view != null) {
                    hashMap.put((String) entry.getKey(), view);
                }
            }
        }
        return hashMap;
    }

    @Override // com.google.android.gms.internal.ads.zzdow
    public final boolean zzA(Bundle bundle) {
        return false;
    }

    @Override // com.google.android.gms.internal.ads.zzdow
    public final JSONObject zzd(View view, Map map, Map map2) {
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzdow
    public final JSONObject zze(View view, Map map, Map map2) {
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzdow
    public final void zzf() {
        throw null;
    }

    @Override // com.google.android.gms.internal.ads.zzdow
    public final void zzg() {
    }

    @Override // com.google.android.gms.internal.ads.zzdow
    public final void zzh() {
    }

    @Override // com.google.android.gms.internal.ads.zzdow
    public final void zzi(com.google.android.gms.ads.internal.client.zzcu zzcuVar) {
        com.google.android.gms.ads.internal.util.zze.zzj("Mute This Ad is not supported for 3rd party ads");
    }

    @Override // com.google.android.gms.internal.ads.zzdow
    public final void zzj(View view, View view2, Map map, Map map2, boolean z) {
        if (this.zzj && this.zzf.zzM) {
            return;
        }
        zza(view);
    }

    @Override // com.google.android.gms.internal.ads.zzdow
    public final void zzk(String str) {
    }

    @Override // com.google.android.gms.internal.ads.zzdow
    public final void zzl(Bundle bundle) {
    }

    @Override // com.google.android.gms.internal.ads.zzdow
    public final void zzn(View view, Map map, Map map2, boolean z) {
        if (!this.zzj) {
            com.google.android.gms.ads.internal.util.zze.zzj("Custom click reporting for 3p ads failed. enableCustomClickGesture is not set.");
        } else if (!this.zzf.zzM) {
            com.google.android.gms.ads.internal.util.zze.zzj("Custom click reporting for 3p ads failed. Ad unit id not in allow list.");
        } else {
            zza(view);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdow
    public final void zzo() {
    }

    @Override // com.google.android.gms.internal.ads.zzdow
    public final void zzp(View view, Map map, Map map2) {
        try {
            if (!this.zzi) {
                this.zzi = com.google.android.gms.ads.internal.zzt.zzt().zzn(this.zze, this.zzg.zza, this.zzf.zzD.toString(), this.zzh.zzf);
            }
            if (this.zzk) {
                zzbvu zzbvuVar = this.zza;
                if (zzbvuVar != null && !zzbvuVar.zzB()) {
                    this.zza.zzx();
                    this.zzb.zza();
                    return;
                }
                zzbvq zzbvqVar = this.zzl;
                if (zzbvqVar != null && !zzbvqVar.zzy()) {
                    this.zzl.zzt();
                    this.zzb.zza();
                    return;
                }
                zzbvr zzbvrVar = this.zzm;
                if (zzbvrVar == null || zzbvrVar.zzw()) {
                    return;
                }
                this.zzm.zzr();
                this.zzb.zza();
            }
        } catch (RemoteException e) {
            com.google.android.gms.ads.internal.util.zze.zzk("Failed to call recordImpression", e);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdow
    public final void zzq() {
    }

    @Override // com.google.android.gms.internal.ads.zzdow
    public final void zzr(View view, MotionEvent motionEvent, View view2) {
    }

    @Override // com.google.android.gms.internal.ads.zzdow
    public final void zzs(Bundle bundle) {
    }

    @Override // com.google.android.gms.internal.ads.zzdow
    public final void zzt(View view) {
    }

    @Override // com.google.android.gms.internal.ads.zzdow
    public final void zzu() {
        this.zzj = true;
    }

    @Override // com.google.android.gms.internal.ads.zzdow
    public final void zzv(com.google.android.gms.ads.internal.client.zzcq zzcqVar) {
        com.google.android.gms.ads.internal.util.zze.zzj("Mute This Ad is not supported for 3rd party ads");
    }

    @Override // com.google.android.gms.internal.ads.zzdow
    public final void zzw(zzbnu zzbnuVar) {
    }

    @Override // com.google.android.gms.internal.ads.zzdow
    public final void zzx(View view, Map map, Map map2, View.OnTouchListener onTouchListener, View.OnClickListener onClickListener) {
        Object obj;
        IObjectWrapper zzn;
        try {
            IObjectWrapper wrap = ObjectWrapper.wrap(view);
            JSONObject jSONObject = this.zzf.zzal;
            boolean z = true;
            if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzbq)).booleanValue() && jSONObject.length() != 0) {
                Map hashMap = map == null ? new HashMap() : map;
                Map hashMap2 = map2 == null ? new HashMap() : map2;
                HashMap hashMap3 = new HashMap();
                hashMap3.putAll(hashMap);
                hashMap3.putAll(hashMap2);
                Iterator<String> keys = jSONObject.keys();
                loop0: while (keys.hasNext()) {
                    String next = keys.next();
                    JSONArray optJSONArray = jSONObject.optJSONArray(next);
                    if (optJSONArray != null) {
                        WeakReference weakReference = (WeakReference) hashMap3.get(next);
                        if (weakReference != null && (obj = weakReference.get()) != null) {
                            Class<?> cls = obj.getClass();
                            if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzbr)).booleanValue() && next.equals("3010")) {
                                zzbvu zzbvuVar = this.zza;
                                Object obj2 = null;
                                if (zzbvuVar != null) {
                                    try {
                                        zzn = zzbvuVar.zzn();
                                    } catch (RemoteException | IllegalArgumentException unused) {
                                    }
                                } else {
                                    zzbvq zzbvqVar = this.zzl;
                                    if (zzbvqVar != null) {
                                        zzn = zzbvqVar.zzk();
                                    } else {
                                        zzbvr zzbvrVar = this.zzm;
                                        zzn = zzbvrVar != null ? zzbvrVar.zzj() : null;
                                    }
                                }
                                if (zzn != null) {
                                    obj2 = ObjectWrapper.unwrap(zzn);
                                }
                                if (obj2 != null) {
                                    cls = obj2.getClass();
                                }
                            }
                            try {
                                ArrayList<String> arrayList = new ArrayList();
                                com.google.android.gms.ads.internal.util.zzbu.zzc(optJSONArray, arrayList);
                                com.google.android.gms.ads.internal.zzt.zzq();
                                ClassLoader classLoader = this.zze.getClassLoader();
                                for (String str : arrayList) {
                                    if (Class.forName(str, false, classLoader).isAssignableFrom(cls)) {
                                        break;
                                    }
                                }
                            } catch (JSONException unused2) {
                                continue;
                            }
                        }
                        z = false;
                        break;
                    }
                }
            }
            this.zzk = z;
            HashMap zzb = zzb(map);
            HashMap zzb2 = zzb(map2);
            zzbvu zzbvuVar2 = this.zza;
            if (zzbvuVar2 != null) {
                zzbvuVar2.zzy(wrap, ObjectWrapper.wrap(zzb), ObjectWrapper.wrap(zzb2));
                return;
            }
            zzbvq zzbvqVar2 = this.zzl;
            if (zzbvqVar2 != null) {
                zzbvqVar2.zzv(wrap, ObjectWrapper.wrap(zzb), ObjectWrapper.wrap(zzb2));
                this.zzl.zzu(wrap);
                return;
            }
            zzbvr zzbvrVar2 = this.zzm;
            if (zzbvrVar2 != null) {
                zzbvrVar2.zzt(wrap, ObjectWrapper.wrap(zzb), ObjectWrapper.wrap(zzb2));
                this.zzm.zzs(wrap);
            }
        } catch (RemoteException e) {
            com.google.android.gms.ads.internal.util.zze.zzk("Failed to call trackView", e);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdow
    public final void zzy(View view, Map map) {
        try {
            IObjectWrapper wrap = ObjectWrapper.wrap(view);
            zzbvu zzbvuVar = this.zza;
            if (zzbvuVar != null) {
                zzbvuVar.zzz(wrap);
                return;
            }
            zzbvq zzbvqVar = this.zzl;
            if (zzbvqVar != null) {
                zzbvqVar.zzw(wrap);
                return;
            }
            zzbvr zzbvrVar = this.zzm;
            if (zzbvrVar != null) {
                zzbvrVar.zzu(wrap);
            }
        } catch (RemoteException e) {
            com.google.android.gms.ads.internal.util.zze.zzk("Failed to call untrackView", e);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdow
    public final boolean zzz() {
        return this.zzf.zzM;
    }
}
