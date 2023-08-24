package com.google.android.gms.internal.ads;

import android.os.Handler;
import android.util.Pair;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzjr {
    private final zzmz zza;
    private final zzjq zze;
    private final zzsp zzf;
    private final zzpi zzg;
    private final HashMap zzh;
    private final Set zzi;
    private boolean zzj;
    private zzfx zzk;
    private zztz zzl = new zztz(0);
    private final IdentityHashMap zzc = new IdentityHashMap();
    private final Map zzd = new HashMap();
    private final List zzb = new ArrayList();

    public zzjr(zzjq zzjqVar, zzkm zzkmVar, Handler handler, zzmz zzmzVar) {
        this.zza = zzmzVar;
        this.zze = zzjqVar;
        zzsp zzspVar = new zzsp();
        this.zzf = zzspVar;
        zzpi zzpiVar = new zzpi();
        this.zzg = zzpiVar;
        this.zzh = new HashMap();
        this.zzi = new HashSet();
        zzspVar.zzb(handler, zzkmVar);
        zzpiVar.zzb(handler, zzkmVar);
    }

    private final void zzp(int r3, int r4) {
        while (r3 < this.zzb.size()) {
            ((zzjp) this.zzb.get(r3)).zzd += r4;
            r3++;
        }
    }

    private final void zzq(zzjp zzjpVar) {
        zzjo zzjoVar = (zzjo) this.zzh.get(zzjpVar);
        if (zzjoVar != null) {
            zzjoVar.zza.zzi(zzjoVar.zzb);
        }
    }

    private final void zzr() {
        Iterator it = this.zzi.iterator();
        while (it.hasNext()) {
            zzjp zzjpVar = (zzjp) it.next();
            if (zzjpVar.zzc.isEmpty()) {
                zzq(zzjpVar);
                it.remove();
            }
        }
    }

    private final void zzs(zzjp zzjpVar) {
        if (zzjpVar.zze && zzjpVar.zzc.isEmpty()) {
            zzjo zzjoVar = (zzjo) this.zzh.remove(zzjpVar);
            Objects.requireNonNull(zzjoVar);
            zzjoVar.zza.zzp(zzjoVar.zzb);
            zzjoVar.zza.zzs(zzjoVar.zzc);
            zzjoVar.zza.zzr(zzjoVar.zzc);
            this.zzi.remove(zzjpVar);
        }
    }

    private final void zzt(zzjp zzjpVar) {
        zzsb zzsbVar = zzjpVar.zza;
        zzsh zzshVar = new zzsh() { // from class: com.google.android.gms.internal.ads.zzjm
            @Override // com.google.android.gms.internal.ads.zzsh
            public final void zza(zzsi zzsiVar, zzcn zzcnVar) {
                zzjr.this.zze(zzsiVar, zzcnVar);
            }
        };
        zzjn zzjnVar = new zzjn(this, zzjpVar);
        this.zzh.put(zzjpVar, new zzjo(zzsbVar, zzshVar, zzjnVar));
        zzsbVar.zzh(new Handler(zzel.zzE(), null), zzjnVar);
        zzsbVar.zzg(new Handler(zzel.zzE(), null), zzjnVar);
        zzsbVar.zzm(zzshVar, this.zzk, this.zza);
    }

    private final void zzu(int r4, int r5) {
        while (true) {
            r5--;
            if (r5 < r4) {
                return;
            }
            zzjp zzjpVar = (zzjp) this.zzb.remove(r5);
            this.zzd.remove(zzjpVar.zzb);
            zzp(r5, -zzjpVar.zza.zzA().zzc());
            zzjpVar.zze = true;
            if (this.zzj) {
                zzs(zzjpVar);
            }
        }
    }

    public final int zza() {
        return this.zzb.size();
    }

    public final zzcn zzb() {
        if (this.zzb.isEmpty()) {
            return zzcn.zza;
        }
        int r1 = 0;
        for (int r0 = 0; r0 < this.zzb.size(); r0++) {
            zzjp zzjpVar = (zzjp) this.zzb.get(r0);
            zzjpVar.zzd = r1;
            r1 += zzjpVar.zza.zzA().zzc();
        }
        return new zzjw(this.zzb, this.zzl, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zze(zzsi zzsiVar, zzcn zzcnVar) {
        this.zze.zzh();
    }

    public final void zzf(zzfx zzfxVar) {
        zzdd.zzf(!this.zzj);
        this.zzk = zzfxVar;
        for (int r4 = 0; r4 < this.zzb.size(); r4++) {
            zzjp zzjpVar = (zzjp) this.zzb.get(r4);
            zzt(zzjpVar);
            this.zzi.add(zzjpVar);
        }
        this.zzj = true;
    }

    public final void zzg() {
        for (zzjo zzjoVar : this.zzh.values()) {
            try {
                zzjoVar.zza.zzp(zzjoVar.zzb);
            } catch (RuntimeException e) {
                zzdu.zza("MediaSourceList", "Failed to release child source.", e);
            }
            zzjoVar.zza.zzs(zzjoVar.zzc);
            zzjoVar.zza.zzr(zzjoVar.zzc);
        }
        this.zzh.clear();
        this.zzi.clear();
        this.zzj = false;
    }

    public final void zzh(zzse zzseVar) {
        zzjp zzjpVar = (zzjp) this.zzc.remove(zzseVar);
        Objects.requireNonNull(zzjpVar);
        zzjpVar.zza.zzB(zzseVar);
        zzjpVar.zzc.remove(((zzry) zzseVar).zza);
        if (!this.zzc.isEmpty()) {
            zzr();
        }
        zzs(zzjpVar);
    }

    public final boolean zzi() {
        return this.zzj;
    }

    public final zzcn zzj(int r4, List list, zztz zztzVar) {
        if (!list.isEmpty()) {
            this.zzl = zztzVar;
            for (int r6 = r4; r6 < list.size() + r4; r6++) {
                zzjp zzjpVar = (zzjp) list.get(r6 - r4);
                if (r6 > 0) {
                    zzjp zzjpVar2 = (zzjp) this.zzb.get(r6 - 1);
                    zzjpVar.zzc(zzjpVar2.zzd + zzjpVar2.zza.zzA().zzc());
                } else {
                    zzjpVar.zzc(0);
                }
                zzp(r6, zzjpVar.zza.zzA().zzc());
                this.zzb.add(r6, zzjpVar);
                this.zzd.put(zzjpVar.zzb, zzjpVar);
                if (this.zzj) {
                    zzt(zzjpVar);
                    if (this.zzc.isEmpty()) {
                        this.zzi.add(zzjpVar);
                    } else {
                        zzq(zzjpVar);
                    }
                }
            }
        }
        return zzb();
    }

    public final zzcn zzk(int r1, int r2, int r3, zztz zztzVar) {
        zzdd.zzd(zza() >= 0);
        this.zzl = null;
        return zzb();
    }

    public final zzcn zzl(int r3, int r4, zztz zztzVar) {
        boolean z = false;
        if (r3 >= 0 && r3 <= r4 && r4 <= zza()) {
            z = true;
        }
        zzdd.zzd(z);
        this.zzl = zztzVar;
        zzu(r3, r4);
        return zzb();
    }

    public final zzcn zzm(List list, zztz zztzVar) {
        zzu(0, this.zzb.size());
        return zzj(this.zzb.size(), list, zztzVar);
    }

    public final zzcn zzn(zztz zztzVar) {
        int zza = zza();
        if (zztzVar.zzc() != zza) {
            zztzVar = zztzVar.zzf().zzg(0, zza);
        }
        this.zzl = zztzVar;
        return zzb();
    }

    public final zzse zzo(zzsg zzsgVar, zzwf zzwfVar, long j) {
        Object obj = ((Pair) zzsgVar.zza).first;
        zzsg zzc = zzsgVar.zzc(((Pair) zzsgVar.zza).second);
        zzjp zzjpVar = (zzjp) this.zzd.get(obj);
        Objects.requireNonNull(zzjpVar);
        this.zzi.add(zzjpVar);
        zzjo zzjoVar = (zzjo) this.zzh.get(zzjpVar);
        if (zzjoVar != null) {
            zzjoVar.zza.zzk(zzjoVar.zzb);
        }
        zzjpVar.zzc.add(zzc);
        zzry zzD = zzjpVar.zza.zzD(zzc, zzwfVar, j);
        this.zzc.put(zzD, zzjpVar);
        zzr();
        return zzD;
    }
}
