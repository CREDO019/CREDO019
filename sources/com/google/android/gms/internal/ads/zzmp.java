package com.google.android.gms.internal.ads;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzmp {
    private final zzck zza;
    private zzfuv zzb = zzfuv.zzo();
    private zzfuy zzc = zzfuy.zzd();
    private zzsg zzd;
    private zzsg zze;
    private zzsg zzf;

    public zzmp(zzck zzckVar) {
        this.zza = zzckVar;
    }

    private static zzsg zzj(zzcg zzcgVar, zzfuv zzfuvVar, zzsg zzsgVar, zzck zzckVar) {
        zzcn zzn = zzcgVar.zzn();
        int zzg = zzcgVar.zzg();
        Object zzf = zzn.zzo() ? null : zzn.zzf(zzg);
        int zzc = (zzcgVar.zzs() || zzn.zzo()) ? -1 : zzn.zzd(zzg, zzckVar, false).zzc(zzel.zzv(zzcgVar.zzl()));
        for (int r0 = 0; r0 < zzfuvVar.size(); r0++) {
            zzsg zzsgVar2 = (zzsg) zzfuvVar.get(r0);
            if (zzm(zzsgVar2, zzf, zzcgVar.zzs(), zzcgVar.zzd(), zzcgVar.zze(), zzc)) {
                return zzsgVar2;
            }
        }
        if (zzfuvVar.isEmpty() && zzsgVar != null) {
            if (zzm(zzsgVar, zzf, zzcgVar.zzs(), zzcgVar.zzd(), zzcgVar.zze(), zzc)) {
                return zzsgVar;
            }
        }
        return null;
    }

    private final void zzk(zzfux zzfuxVar, zzsg zzsgVar, zzcn zzcnVar) {
        if (zzsgVar == null) {
            return;
        }
        if (zzcnVar.zza(zzsgVar.zza) != -1) {
            zzfuxVar.zza(zzsgVar, zzcnVar);
            return;
        }
        zzcn zzcnVar2 = (zzcn) this.zzc.get(zzsgVar);
        if (zzcnVar2 != null) {
            zzfuxVar.zza(zzsgVar, zzcnVar2);
        }
    }

    private final void zzl(zzcn zzcnVar) {
        zzfux zzfuxVar = new zzfux();
        if (!this.zzb.isEmpty()) {
            for (int r1 = 0; r1 < this.zzb.size(); r1++) {
                zzk(zzfuxVar, (zzsg) this.zzb.get(r1), zzcnVar);
            }
            if (!this.zzb.contains(this.zzd)) {
                zzk(zzfuxVar, this.zzd, zzcnVar);
            }
        } else {
            zzk(zzfuxVar, this.zze, zzcnVar);
            if (!zzfsa.zza(this.zzf, this.zze)) {
                zzk(zzfuxVar, this.zzf, zzcnVar);
            }
            if (!zzfsa.zza(this.zzd, this.zze) && !zzfsa.zza(this.zzd, this.zzf)) {
                zzk(zzfuxVar, this.zzd, zzcnVar);
            }
        }
        this.zzc = zzfuxVar.zzc();
    }

    private static boolean zzm(zzsg zzsgVar, Object obj, boolean z, int r4, int r5, int r6) {
        if (zzsgVar.zza.equals(obj)) {
            if (z) {
                if (zzsgVar.zzb != r4 || zzsgVar.zzc != r5) {
                    return false;
                }
            } else if (zzsgVar.zzb != -1 || zzsgVar.zze != r6) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final zzcn zza(zzsg zzsgVar) {
        return (zzcn) this.zzc.get(zzsgVar);
    }

    public final zzsg zzb() {
        return this.zzd;
    }

    public final zzsg zzc() {
        Object next;
        Object obj;
        if (this.zzb.isEmpty()) {
            return null;
        }
        zzfuv zzfuvVar = this.zzb;
        if (zzfuvVar instanceof List) {
            if (!zzfuvVar.isEmpty()) {
                obj = zzfuvVar.get(zzfuvVar.size() - 1);
            } else {
                throw new NoSuchElementException();
            }
        } else {
            Iterator<E> it = zzfuvVar.iterator();
            do {
                next = it.next();
            } while (it.hasNext());
            obj = next;
        }
        return (zzsg) obj;
    }

    public final zzsg zzd() {
        return this.zze;
    }

    public final zzsg zze() {
        return this.zzf;
    }

    public final void zzg(zzcg zzcgVar) {
        this.zzd = zzj(zzcgVar, this.zzb, this.zze, this.zza);
    }

    public final void zzh(List list, zzsg zzsgVar, zzcg zzcgVar) {
        this.zzb = zzfuv.zzm(list);
        if (!list.isEmpty()) {
            this.zze = (zzsg) list.get(0);
            Objects.requireNonNull(zzsgVar);
            this.zzf = zzsgVar;
        }
        if (this.zzd == null) {
            this.zzd = zzj(zzcgVar, this.zzb, this.zze, this.zza);
        }
        zzl(zzcgVar.zzn());
    }

    public final void zzi(zzcg zzcgVar) {
        this.zzd = zzj(zzcgVar, this.zzb, this.zze, this.zza);
        zzl(zzcgVar.zzn());
    }
}
