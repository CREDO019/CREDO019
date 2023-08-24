package com.google.android.gms.internal.ads;

import android.os.Parcelable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzfez implements zzfey {
    private final ConcurrentHashMap zza;
    private final zzfff zzb;
    private final zzffb zzc = new zzffb();

    public zzfez(zzfff zzfffVar) {
        this.zza = new ConcurrentHashMap(zzfffVar.zzd);
        this.zzb = zzfffVar;
    }

    private final void zzf() {
        Parcelable.Creator<zzfff> creator = zzfff.CREATOR;
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzfs)).booleanValue()) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.zzb.zzb);
            sb.append(" PoolCollection");
            sb.append(this.zzc.zzb());
            int r3 = 0;
            for (Map.Entry entry : this.zza.entrySet()) {
                r3++;
                sb.append(r3);
                sb.append(". ");
                sb.append(entry.getValue());
                sb.append("#");
                sb.append(((zzffi) entry.getKey()).hashCode());
                sb.append("    ");
                for (int r5 = 0; r5 < ((zzfex) entry.getValue()).zzb(); r5++) {
                    sb.append("[O]");
                }
                for (int zzb = ((zzfex) entry.getValue()).zzb(); zzb < this.zzb.zzd; zzb++) {
                    sb.append("[ ]");
                }
                sb.append("\n");
                sb.append(((zzfex) entry.getValue()).zzg());
                sb.append("\n");
            }
            while (r3 < this.zzb.zzc) {
                r3++;
                sb.append(r3);
                sb.append(".\n");
            }
            com.google.android.gms.ads.internal.util.zze.zze(sb.toString());
        }
    }

    @Override // com.google.android.gms.internal.ads.zzfey
    public final zzfff zza() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.ads.zzfey
    public final synchronized zzffh zzb(zzffi zzffiVar) {
        zzffh zzffhVar;
        zzfex zzfexVar = (zzfex) this.zza.get(zzffiVar);
        if (zzfexVar != null) {
            zzffhVar = zzfexVar.zze();
            if (zzffhVar == null) {
                this.zzc.zze();
            }
            zzffv zzf = zzfexVar.zzf();
            if (zzffhVar != null) {
                zzbfa zza = zzbfg.zza();
                zzbey zza2 = zzbez.zza();
                zza2.zzd(2);
                zzbfc zza3 = zzbfd.zza();
                zza3.zza(zzf.zza);
                zza3.zzb(zzf.zzb);
                zza2.zza(zza3);
                zza.zza(zza2);
                zzffhVar.zza.zzb().zzc().zze((zzbfg) zza.zzal());
            }
            zzf();
        } else {
            this.zzc.zzf();
            zzf();
            zzffhVar = null;
        }
        return zzffhVar;
    }

    @Override // com.google.android.gms.internal.ads.zzfey
    @Deprecated
    public final zzffi zzc(com.google.android.gms.ads.internal.client.zzl zzlVar, String str, com.google.android.gms.ads.internal.client.zzw zzwVar) {
        return new zzffj(zzlVar, str, new zzcbe(this.zzb.zza).zza().zzk, this.zzb.zzf, zzwVar);
    }

    @Override // com.google.android.gms.internal.ads.zzfey
    public final synchronized boolean zzd(zzffi zzffiVar, zzffh zzffhVar) {
        boolean zzh;
        zzfex zzfexVar = (zzfex) this.zza.get(zzffiVar);
        zzffhVar.zzd = com.google.android.gms.ads.internal.zzt.zzB().currentTimeMillis();
        if (zzfexVar == null) {
            zzfff zzfffVar = this.zzb;
            zzfexVar = new zzfex(zzfffVar.zzd, zzfffVar.zze * 1000);
            int size = this.zza.size();
            zzfff zzfffVar2 = this.zzb;
            if (size == zzfffVar2.zzc) {
                int r2 = zzfffVar2.zzg;
                int r3 = r2 - 1;
                zzffi zzffiVar2 = null;
                if (r2 == 0) {
                    throw null;
                }
                long j = Long.MAX_VALUE;
                if (r3 == 0) {
                    for (Map.Entry entry : this.zza.entrySet()) {
                        if (((zzfex) entry.getValue()).zzc() < j) {
                            j = ((zzfex) entry.getValue()).zzc();
                            zzffiVar2 = (zzffi) entry.getKey();
                        }
                    }
                    if (zzffiVar2 != null) {
                        this.zza.remove(zzffiVar2);
                    }
                } else if (r3 == 1) {
                    for (Map.Entry entry2 : this.zza.entrySet()) {
                        if (((zzfex) entry2.getValue()).zzd() < j) {
                            j = ((zzfex) entry2.getValue()).zzd();
                            zzffiVar2 = (zzffi) entry2.getKey();
                        }
                    }
                    if (zzffiVar2 != null) {
                        this.zza.remove(zzffiVar2);
                    }
                } else if (r3 == 2) {
                    int r32 = Integer.MAX_VALUE;
                    for (Map.Entry entry3 : this.zza.entrySet()) {
                        if (((zzfex) entry3.getValue()).zza() < r32) {
                            r32 = ((zzfex) entry3.getValue()).zza();
                            zzffiVar2 = (zzffi) entry3.getKey();
                        }
                    }
                    if (zzffiVar2 != null) {
                        this.zza.remove(zzffiVar2);
                    }
                }
                this.zzc.zzg();
            }
            this.zza.put(zzffiVar, zzfexVar);
            this.zzc.zzd();
        }
        zzh = zzfexVar.zzh(zzffhVar);
        this.zzc.zzc();
        zzffa zza = this.zzc.zza();
        zzffv zzf = zzfexVar.zzf();
        if (zzffhVar != null) {
            zzbfa zza2 = zzbfg.zza();
            zzbey zza3 = zzbez.zza();
            zza3.zzd(2);
            zzbfe zza4 = zzbff.zza();
            zza4.zza(zza.zza);
            zza4.zzb(zza.zzb);
            zza4.zzc(zzf.zzb);
            zza3.zzc(zza4);
            zza2.zza(zza3);
            zzffhVar.zza.zzb().zzc().zzf((zzbfg) zza2.zzal());
        }
        zzf();
        return zzh;
    }

    @Override // com.google.android.gms.internal.ads.zzfey
    public final synchronized boolean zze(zzffi zzffiVar) {
        zzfex zzfexVar = (zzfex) this.zza.get(zzffiVar);
        if (zzfexVar != null) {
            return zzfexVar.zzb() < this.zzb.zzd;
        }
        return true;
    }
}
