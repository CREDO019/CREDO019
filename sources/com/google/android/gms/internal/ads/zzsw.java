package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.C1856C;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Objects;
import org.apache.logging.log4j.message.ParameterizedMessage;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzsw implements zzse, zzsd {
    private final zzse[] zza;
    private zzsd zze;
    private zzue zzf;
    private final zzrr zzi;
    private final ArrayList zzc = new ArrayList();
    private final HashMap zzd = new HashMap();
    private zzty zzh = new zzrq(new zzty[0]);
    private final IdentityHashMap zzb = new IdentityHashMap();
    private zzse[] zzg = new zzse[0];

    public zzsw(zzrr zzrrVar, long[] jArr, zzse[] zzseVarArr, byte... bArr) {
        this.zzi = zzrrVar;
        this.zza = zzseVarArr;
        for (int r5 = 0; r5 < zzseVarArr.length; r5++) {
            long j = jArr[r5];
            if (j != 0) {
                this.zza[r5] = new zzsu(zzseVarArr[r5], j);
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzse
    public final long zza(long j, zzkb zzkbVar) {
        zzse[] zzseVarArr = this.zzg;
        return (zzseVarArr.length > 0 ? zzseVarArr[0] : this.zza[0]).zza(j, zzkbVar);
    }

    @Override // com.google.android.gms.internal.ads.zzse, com.google.android.gms.internal.ads.zzty
    public final long zzb() {
        return this.zzh.zzb();
    }

    @Override // com.google.android.gms.internal.ads.zzse, com.google.android.gms.internal.ads.zzty
    public final long zzc() {
        return this.zzh.zzc();
    }

    @Override // com.google.android.gms.internal.ads.zzse
    public final long zzd() {
        zzse[] zzseVarArr;
        zzse[] zzseVarArr2;
        long j = -9223372036854775807L;
        for (zzse zzseVar : this.zzg) {
            long zzd = zzseVar.zzd();
            if (zzd != C1856C.TIME_UNSET) {
                if (j == C1856C.TIME_UNSET) {
                    for (zzse zzseVar2 : this.zzg) {
                        if (zzseVar2 == zzseVar) {
                            break;
                        } else if (zzseVar2.zze(zzd) != zzd) {
                            throw new IllegalStateException("Unexpected child seekToUs result.");
                        }
                    }
                    j = zzd;
                } else if (zzd != j) {
                    throw new IllegalStateException("Conflicting discontinuities.");
                }
            } else if (j != C1856C.TIME_UNSET && zzseVar.zze(j) != j) {
                throw new IllegalStateException("Unexpected child seekToUs result.");
            }
        }
        return j;
    }

    @Override // com.google.android.gms.internal.ads.zzse
    public final long zze(long j) {
        long zze = this.zzg[0].zze(j);
        int r0 = 1;
        while (true) {
            zzse[] zzseVarArr = this.zzg;
            if (r0 >= zzseVarArr.length) {
                return zze;
            }
            if (zzseVarArr[r0].zze(zze) != zze) {
                throw new IllegalStateException("Unexpected child seekToUs result.");
            }
            r0++;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzse
    public final long zzf(zzvq[] zzvqVarArr, boolean[] zArr, zztw[] zztwVarArr, boolean[] zArr2, long j) {
        int length;
        zzvq zzvqVar;
        int length2 = zzvqVarArr.length;
        int[] r4 = new int[length2];
        int[] r3 = new int[length2];
        int r6 = 0;
        while (true) {
            length = zzvqVarArr.length;
            zzvqVar = null;
            if (r6 >= length) {
                break;
            }
            zztw zztwVar = zztwVarArr[r6];
            Integer num = zztwVar != null ? (Integer) this.zzb.get(zztwVar) : null;
            r4[r6] = num == null ? -1 : num.intValue();
            r3[r6] = -1;
            zzvq zzvqVar2 = zzvqVarArr[r6];
            if (zzvqVar2 != null) {
                zzcp zzcpVar = (zzcp) this.zzd.get(zzvqVar2.zze());
                Objects.requireNonNull(zzcpVar);
                int r9 = 0;
                while (true) {
                    zzse[] zzseVarArr = this.zza;
                    if (r9 >= zzseVarArr.length) {
                        break;
                    } else if (zzseVarArr[r9].zzh().zza(zzcpVar) != -1) {
                        r3[r6] = r9;
                        break;
                    } else {
                        r9++;
                    }
                }
            }
            r6++;
        }
        this.zzb.clear();
        zztw[] zztwVarArr2 = new zztw[length];
        zztw[] zztwVarArr3 = new zztw[length];
        zzvq[] zzvqVarArr2 = new zzvq[length];
        ArrayList arrayList = new ArrayList(this.zza.length);
        long j2 = j;
        int r12 = 0;
        while (r12 < this.zza.length) {
            for (int r92 = 0; r92 < zzvqVarArr.length; r92++) {
                zztwVarArr3[r92] = r4[r92] == r12 ? zztwVarArr[r92] : zzvqVar;
                if (r3[r92] == r12) {
                    zzvq zzvqVar3 = zzvqVarArr[r92];
                    Objects.requireNonNull(zzvqVar3);
                    zzcp zzcpVar2 = (zzcp) this.zzd.get(zzvqVar3.zze());
                    Objects.requireNonNull(zzcpVar2);
                    zzvqVarArr2[r92] = new zzst(zzvqVar3, zzcpVar2);
                } else {
                    zzvqVarArr2[r92] = zzvqVar;
                }
            }
            int r5 = r12;
            ArrayList arrayList2 = arrayList;
            zztw[] zztwVarArr4 = zztwVarArr3;
            zzvq[] zzvqVarArr3 = zzvqVarArr2;
            long zzf = this.zza[r12].zzf(zzvqVarArr2, zArr, zztwVarArr3, zArr2, j2);
            if (r5 == 0) {
                j2 = zzf;
            } else if (zzf != j2) {
                throw new IllegalStateException("Children enabled at different positions.");
            }
            boolean z = false;
            for (int r93 = 0; r93 < zzvqVarArr.length; r93++) {
                if (r3[r93] == r5) {
                    zztw zztwVar2 = zztwVarArr4[r93];
                    Objects.requireNonNull(zztwVar2);
                    zztwVarArr2[r93] = zztwVar2;
                    this.zzb.put(zztwVar2, Integer.valueOf(r5));
                    z = true;
                } else if (r4[r93] == r5) {
                    zzdd.zzf(zztwVarArr4[r93] == null);
                }
            }
            if (z) {
                arrayList2.add(this.zza[r5]);
            }
            r12 = r5 + 1;
            arrayList = arrayList2;
            zztwVarArr3 = zztwVarArr4;
            zzvqVarArr2 = zzvqVarArr3;
            zzvqVar = null;
        }
        System.arraycopy(zztwVarArr2, 0, zztwVarArr, 0, length);
        zzse[] zzseVarArr2 = (zzse[]) arrayList.toArray(new zzse[0]);
        this.zzg = zzseVarArr2;
        this.zzh = new zzrq(zzseVarArr2);
        return j2;
    }

    @Override // com.google.android.gms.internal.ads.zztx
    public final /* bridge */ /* synthetic */ void zzg(zzty zztyVar) {
        zzse zzseVar = (zzse) zztyVar;
        zzsd zzsdVar = this.zze;
        Objects.requireNonNull(zzsdVar);
        zzsdVar.zzg(this);
    }

    @Override // com.google.android.gms.internal.ads.zzse
    public final zzue zzh() {
        zzue zzueVar = this.zzf;
        Objects.requireNonNull(zzueVar);
        return zzueVar;
    }

    @Override // com.google.android.gms.internal.ads.zzsd
    public final void zzi(zzse zzseVar) {
        this.zzc.remove(zzseVar);
        if (!this.zzc.isEmpty()) {
            return;
        }
        int r3 = 0;
        for (zzse zzseVar2 : this.zza) {
            r3 += zzseVar2.zzh().zzc;
        }
        zzcp[] zzcpVarArr = new zzcp[r3];
        int r0 = 0;
        int r2 = 0;
        while (true) {
            zzse[] zzseVarArr = this.zza;
            if (r0 < zzseVarArr.length) {
                zzue zzh = zzseVarArr[r0].zzh();
                int r4 = zzh.zzc;
                int r5 = 0;
                while (r5 < r4) {
                    zzcp zzb = zzh.zzb(r5);
                    zzcp zzc = zzb.zzc(r0 + ParameterizedMessage.ERROR_MSG_SEPARATOR + zzb.zzc);
                    this.zzd.put(zzc, zzb);
                    zzcpVarArr[r2] = zzc;
                    r5++;
                    r2++;
                }
                r0++;
            } else {
                this.zzf = new zzue(zzcpVarArr);
                zzsd zzsdVar = this.zze;
                Objects.requireNonNull(zzsdVar);
                zzsdVar.zzi(this);
                return;
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzse
    public final void zzj(long j, boolean z) {
        for (zzse zzseVar : this.zzg) {
            zzseVar.zzj(j, false);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzse
    public final void zzk() throws IOException {
        for (zzse zzseVar : this.zza) {
            zzseVar.zzk();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzse
    public final void zzl(zzsd zzsdVar, long j) {
        this.zze = zzsdVar;
        Collections.addAll(this.zzc, this.zza);
        for (zzse zzseVar : this.zza) {
            zzseVar.zzl(this, j);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzse, com.google.android.gms.internal.ads.zzty
    public final void zzm(long j) {
        this.zzh.zzm(j);
    }

    public final zzse zzn(int r2) {
        zzse zzseVar;
        zzse zzseVar2 = this.zza[r2];
        if (zzseVar2 instanceof zzsu) {
            zzseVar = ((zzsu) zzseVar2).zza;
            return zzseVar;
        }
        return zzseVar2;
    }

    @Override // com.google.android.gms.internal.ads.zzse, com.google.android.gms.internal.ads.zzty
    public final boolean zzo(long j) {
        if (!this.zzc.isEmpty()) {
            int size = this.zzc.size();
            for (int r2 = 0; r2 < size; r2++) {
                ((zzse) this.zzc.get(r2)).zzo(j);
            }
            return false;
        }
        return this.zzh.zzo(j);
    }

    @Override // com.google.android.gms.internal.ads.zzse, com.google.android.gms.internal.ads.zzty
    public final boolean zzp() {
        return this.zzh.zzp();
    }
}
