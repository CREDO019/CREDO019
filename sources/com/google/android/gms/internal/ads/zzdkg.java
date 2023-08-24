package com.google.android.gms.internal.ads;

import android.content.Context;
import android.view.View;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdkg extends zzdih implements zzbbm {
    private final Map zzb;
    private final Context zzc;
    private final zzfcs zzd;

    public zzdkg(Context context, Set set, zzfcs zzfcsVar) {
        super(set);
        this.zzb = new WeakHashMap(1);
        this.zzc = context;
        this.zzd = zzfcsVar;
    }

    public final synchronized void zza(View view) {
        zzbbn zzbbnVar = (zzbbn) this.zzb.get(view);
        if (zzbbnVar == null) {
            zzbbnVar = new zzbbn(this.zzc, view);
            zzbbnVar.zzc(this);
            this.zzb.put(view, zzbbnVar);
        }
        if (this.zzd.zzY) {
            if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzbh)).booleanValue()) {
                zzbbnVar.zzg(((Long) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzbg)).longValue());
                return;
            }
        }
        zzbbnVar.zzf();
    }

    public final synchronized void zzb(View view) {
        if (this.zzb.containsKey(view)) {
            ((zzbbn) this.zzb.get(view)).zze(this);
            this.zzb.remove(view);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbbm
    public final synchronized void zzc(final zzbbl zzbblVar) {
        zzo(new zzdig() { // from class: com.google.android.gms.internal.ads.zzdkf
            @Override // com.google.android.gms.internal.ads.zzdig
            public final void zza(Object obj) {
                ((zzbbm) obj).zzc(zzbbl.this);
            }
        });
    }
}
