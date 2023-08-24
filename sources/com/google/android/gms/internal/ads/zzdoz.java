package com.google.android.gms.internal.ads;

import androidx.collection.SimpleArrayMap;
import java.util.ArrayList;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdoz {
    public static final zzdoz zza = new zzdoz(new zzdox());
    private final zzbnb zzb;
    private final zzbmy zzc;
    private final zzbno zzd;
    private final zzbnl zze;
    private final zzbsg zzf;
    private final SimpleArrayMap zzg;
    private final SimpleArrayMap zzh;

    private zzdoz(zzdox zzdoxVar) {
        this.zzb = zzdoxVar.zza;
        this.zzc = zzdoxVar.zzb;
        this.zzd = zzdoxVar.zzc;
        this.zzg = new SimpleArrayMap(zzdoxVar.zzf);
        this.zzh = new SimpleArrayMap(zzdoxVar.zzg);
        this.zze = zzdoxVar.zzd;
        this.zzf = zzdoxVar.zze;
    }

    public final zzbmy zza() {
        return this.zzc;
    }

    public final zzbnb zzb() {
        return this.zzb;
    }

    public final zzbne zzc(String str) {
        return (zzbne) this.zzh.get(str);
    }

    public final zzbnh zzd(String str) {
        return (zzbnh) this.zzg.get(str);
    }

    public final zzbnl zze() {
        return this.zze;
    }

    public final zzbno zzf() {
        return this.zzd;
    }

    public final zzbsg zzg() {
        return this.zzf;
    }

    public final ArrayList zzh() {
        ArrayList arrayList = new ArrayList(this.zzg.size());
        for (int r1 = 0; r1 < this.zzg.size(); r1++) {
            arrayList.add((String) this.zzg.keyAt(r1));
        }
        return arrayList;
    }

    public final ArrayList zzi() {
        ArrayList arrayList = new ArrayList();
        if (this.zzd != null) {
            arrayList.add(Integer.toString(6));
        }
        if (this.zzb != null) {
            arrayList.add(Integer.toString(1));
        }
        if (this.zzc != null) {
            arrayList.add(Integer.toString(2));
        }
        if (!this.zzg.isEmpty()) {
            arrayList.add(Integer.toString(3));
        }
        if (this.zzf != null) {
            arrayList.add(Integer.toString(7));
        }
        return arrayList;
    }
}
