package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.C1856C;
import java.io.IOException;
import java.util.ArrayList;
import java.util.IdentityHashMap;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzayb implements zzaxy, zzaxx {
    public final zzaxy[] zza;
    private final IdentityHashMap zzb = new IdentityHashMap();
    private zzaxx zzc;
    private int zzd;
    private zzayp zze;
    private zzaxy[] zzf;
    private zzaym zzg;

    public zzayb(zzaxy... zzaxyVarArr) {
        this.zza = zzaxyVarArr;
    }

    @Override // com.google.android.gms.internal.ads.zzaxy
    public final long zzB(zzayt[] zzaytVarArr, boolean[] zArr, zzayk[] zzaykVarArr, boolean[] zArr2, long j) {
        int length;
        zzayk[] zzaykVarArr2 = zzaykVarArr;
        int length2 = zzaytVarArr.length;
        int[] r4 = new int[length2];
        int[] r3 = new int[length2];
        int r6 = 0;
        while (true) {
            length = zzaytVarArr.length;
            if (r6 >= length) {
                break;
            }
            zzayk zzaykVar = zzaykVarArr2[r6];
            r4[r6] = zzaykVar == null ? -1 : ((Integer) this.zzb.get(zzaykVar)).intValue();
            r3[r6] = -1;
            zzayt zzaytVar = zzaytVarArr[r6];
            if (zzaytVar != null) {
                zzayo zzd = zzaytVar.zzd();
                int r9 = 0;
                while (true) {
                    zzaxy[] zzaxyVarArr = this.zza;
                    if (r9 >= zzaxyVarArr.length) {
                        break;
                    } else if (zzaxyVarArr[r9].zzn().zza(zzd) != -1) {
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
        zzayk[] zzaykVarArr3 = new zzayk[length];
        zzayk[] zzaykVarArr4 = new zzayk[length];
        zzayt[] zzaytVarArr2 = new zzayt[length];
        ArrayList arrayList = new ArrayList(this.zza.length);
        long j2 = j;
        int r12 = 0;
        while (r12 < this.zza.length) {
            for (int r8 = 0; r8 < zzaytVarArr.length; r8++) {
                zzayt zzaytVar2 = null;
                zzaykVarArr4[r8] = r4[r8] == r12 ? zzaykVarArr2[r8] : null;
                if (r3[r8] == r12) {
                    zzaytVar2 = zzaytVarArr[r8];
                }
                zzaytVarArr2[r8] = zzaytVar2;
            }
            int r5 = r12;
            zzayt[] zzaytVarArr3 = zzaytVarArr2;
            ArrayList arrayList2 = arrayList;
            long zzB = this.zza[r12].zzB(zzaytVarArr2, zArr, zzaykVarArr4, zArr2, j2);
            if (r5 == 0) {
                j2 = zzB;
            } else if (zzB != j2) {
                throw new IllegalStateException("Children enabled at different positions");
            }
            boolean z = false;
            for (int r82 = 0; r82 < zzaytVarArr.length; r82++) {
                if (r3[r82] == r5) {
                    zzazy.zze(zzaykVarArr4[r82] != null);
                    zzayk zzaykVar2 = zzaykVarArr4[r82];
                    zzaykVarArr3[r82] = zzaykVar2;
                    this.zzb.put(zzaykVar2, Integer.valueOf(r5));
                    z = true;
                } else if (r4[r82] == r5) {
                    zzazy.zze(zzaykVarArr4[r82] == null);
                }
            }
            if (z) {
                arrayList2.add(this.zza[r5]);
            }
            r12 = r5 + 1;
            arrayList = arrayList2;
            zzaytVarArr2 = zzaytVarArr3;
            zzaykVarArr2 = zzaykVarArr;
        }
        zzayk[] zzaykVarArr5 = zzaykVarArr2;
        ArrayList arrayList3 = arrayList;
        System.arraycopy(zzaykVarArr3, 0, zzaykVarArr5, 0, length);
        zzaxy[] zzaxyVarArr2 = new zzaxy[arrayList3.size()];
        this.zzf = zzaxyVarArr2;
        arrayList3.toArray(zzaxyVarArr2);
        this.zzg = new zzaxm(this.zzf);
        return j2;
    }

    @Override // com.google.android.gms.internal.ads.zzaxy, com.google.android.gms.internal.ads.zzaym
    public final long zza() {
        return this.zzg.zza();
    }

    @Override // com.google.android.gms.internal.ads.zzaxy, com.google.android.gms.internal.ads.zzaym
    public final boolean zzbj(long j) {
        return this.zzg.zzbj(j);
    }

    @Override // com.google.android.gms.internal.ads.zzayl
    public final /* bridge */ /* synthetic */ void zze(zzaym zzaymVar) {
        zzaxy zzaxyVar = (zzaxy) zzaymVar;
        if (this.zze == null) {
            return;
        }
        this.zzc.zze(this);
    }

    @Override // com.google.android.gms.internal.ads.zzaxx
    public final void zzf(zzaxy zzaxyVar) {
        int r11 = this.zzd - 1;
        this.zzd = r11;
        if (r11 > 0) {
            return;
        }
        int r3 = 0;
        for (zzaxy zzaxyVar2 : this.zza) {
            r3 += zzaxyVar2.zzn().zzb;
        }
        zzayo[] zzayoVarArr = new zzayo[r3];
        int r4 = 0;
        for (zzaxy zzaxyVar3 : this.zza) {
            zzayp zzn = zzaxyVar3.zzn();
            int r6 = zzn.zzb;
            int r7 = 0;
            while (r7 < r6) {
                zzayoVarArr[r4] = zzn.zzb(r7);
                r7++;
                r4++;
            }
        }
        this.zze = new zzayp(zzayoVarArr);
        this.zzc.zzf(this);
    }

    @Override // com.google.android.gms.internal.ads.zzaxy
    public final long zzg() {
        long j = Long.MAX_VALUE;
        for (zzaxy zzaxyVar : this.zzf) {
            long zzg = zzaxyVar.zzg();
            if (zzg != Long.MIN_VALUE) {
                j = Math.min(j, zzg);
            }
        }
        if (j == Long.MAX_VALUE) {
            return Long.MIN_VALUE;
        }
        return j;
    }

    @Override // com.google.android.gms.internal.ads.zzaxy
    public final long zzh() {
        zzaxy[] zzaxyVarArr;
        long zzh = this.zza[0].zzh();
        int r0 = 1;
        while (true) {
            zzaxy[] zzaxyVarArr2 = this.zza;
            if (r0 >= zzaxyVarArr2.length) {
                if (zzh != C1856C.TIME_UNSET) {
                    for (zzaxy zzaxyVar : this.zzf) {
                        if (zzaxyVar != this.zza[0] && zzaxyVar.zzi(zzh) != zzh) {
                            throw new IllegalStateException("Children seeked to different positions");
                        }
                    }
                }
                return zzh;
            } else if (zzaxyVarArr2[r0].zzh() != C1856C.TIME_UNSET) {
                throw new IllegalStateException("Child reported discontinuity");
            } else {
                r0++;
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaxy
    public final long zzi(long j) {
        long zzi = this.zzf[0].zzi(j);
        int r0 = 1;
        while (true) {
            zzaxy[] zzaxyVarArr = this.zzf;
            if (r0 >= zzaxyVarArr.length) {
                return zzi;
            }
            if (zzaxyVarArr[r0].zzi(zzi) != zzi) {
                throw new IllegalStateException("Children seeked to different positions");
            }
            r0++;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaxy
    public final zzayp zzn() {
        return this.zze;
    }

    @Override // com.google.android.gms.internal.ads.zzaxy
    public final void zzq(long j) {
        for (zzaxy zzaxyVar : this.zzf) {
            zzaxyVar.zzq(j);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaxy
    public final void zzs() throws IOException {
        for (zzaxy zzaxyVar : this.zza) {
            zzaxyVar.zzs();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaxy
    public final void zzw(zzaxx zzaxxVar, long j) {
        this.zzc = zzaxxVar;
        zzaxy[] zzaxyVarArr = this.zza;
        this.zzd = zzaxyVarArr.length;
        for (zzaxy zzaxyVar : zzaxyVarArr) {
            zzaxyVar.zzw(this, j);
        }
    }
}
