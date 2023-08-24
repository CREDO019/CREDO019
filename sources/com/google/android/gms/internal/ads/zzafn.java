package com.google.android.gms.internal.ads;

import android.net.Uri;
import android.util.Pair;
import com.google.android.exoplayer2.C1856C;
import com.google.android.exoplayer2.extractor.mp4.Atom;
import com.google.android.exoplayer2.metadata.mp4.MdtaMetadataEntry;
import com.google.android.exoplayer2.util.MimeTypes;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzafn implements zzzf, zzaai {
    public static final zzzm zza = new zzzm() { // from class: com.google.android.gms.internal.ads.zzafk
        @Override // com.google.android.gms.internal.ads.zzzm
        public final zzzf[] zza() {
            zzzm zzzmVar = zzafn.zza;
            return new zzzf[]{new zzafn(0)};
        }

        @Override // com.google.android.gms.internal.ads.zzzm
        public final /* synthetic */ zzzf[] zzb(Uri uri, Map map) {
            return zzzl.zza(this, uri, map);
        }
    };
    private final zzed zzb;
    private final zzed zzc;
    private final zzed zzd;
    private final zzed zze;
    private final ArrayDeque zzf;
    private final zzafq zzg;
    private final List zzh;
    private int zzi;
    private int zzj;
    private long zzk;
    private int zzl;
    private zzed zzm;
    private int zzn;
    private int zzo;
    private int zzp;
    private int zzq;
    private zzzi zzr;
    private zzafm[] zzs;
    private long[][] zzt;
    private int zzu;
    private long zzv;
    private int zzw;
    private zzadq zzx;

    public zzafn() {
        this(0);
    }

    private static int zzf(int r1) {
        if (r1 != 1751476579) {
            return r1 != 1903435808 ? 0 : 1;
        }
        return 2;
    }

    private static int zzi(zzafv zzafvVar, long j) {
        int zza2 = zzafvVar.zza(j);
        return zza2 == -1 ? zzafvVar.zzb(j) : zza2;
    }

    private static long zzj(zzafv zzafvVar, long j, long j2) {
        int zzi = zzi(zzafvVar, j);
        return zzi == -1 ? j2 : Math.min(zzafvVar.zzc[zzi], j2);
    }

    private final void zzk() {
        this.zzi = 0;
        this.zzl = 0;
    }

    private final void zzl(long j) throws zzbu {
        zzbq zzbqVar;
        zzbq zzbqVar2;
        long j2;
        List list;
        int r24;
        int r4;
        int r42;
        while (!this.zzf.isEmpty() && ((zzaes) this.zzf.peek()).zza == j) {
            zzaes zzaesVar = (zzaes) this.zzf.pop();
            if (zzaesVar.zzd == 1836019574) {
                ArrayList arrayList = new ArrayList();
                boolean z = this.zzw == 1;
                zzzu zzzuVar = new zzzu();
                zzaet zzb = zzaesVar.zzb(Atom.TYPE_udta);
                if (zzb != null) {
                    Pair zza2 = zzafc.zza(zzb);
                    zzbq zzbqVar3 = (zzbq) zza2.first;
                    zzbq zzbqVar4 = (zzbq) zza2.second;
                    if (zzbqVar3 != null) {
                        zzzuVar.zzb(zzbqVar3);
                    }
                    zzbqVar = zzbqVar4;
                    zzbqVar2 = zzbqVar3;
                } else {
                    zzbqVar = null;
                    zzbqVar2 = null;
                }
                zzaes zza3 = zzaesVar.zza(Atom.TYPE_meta);
                zzbq zzb2 = zza3 != null ? zzafc.zzb(zza3) : null;
                long j3 = C1856C.TIME_UNSET;
                zzbq zzbqVar5 = zzb2;
                List zzc = zzafc.zzc(zzaesVar, zzzuVar, C1856C.TIME_UNSET, null, false, z, new zzfru() { // from class: com.google.android.gms.internal.ads.zzafl
                    @Override // com.google.android.gms.internal.ads.zzfru
                    public final Object apply(Object obj) {
                        zzafs zzafsVar = (zzafs) obj;
                        zzzm zzzmVar = zzafn.zza;
                        return zzafsVar;
                    }
                });
                int size = zzc.size();
                long j4 = -9223372036854775807L;
                int r8 = 0;
                int r9 = -1;
                while (true) {
                    j2 = 0;
                    if (r8 >= size) {
                        break;
                    }
                    zzafv zzafvVar = (zzafv) zzc.get(r8);
                    if (zzafvVar.zzb == 0) {
                        list = zzc;
                        r24 = size;
                    } else {
                        zzafs zzafsVar = zzafvVar.zza;
                        list = zzc;
                        long j5 = zzafsVar.zze;
                        if (j5 == j3) {
                            j5 = zzafvVar.zzh;
                        }
                        long max = Math.max(j4, j5);
                        r24 = size;
                        zzafm zzafmVar = new zzafm(zzafsVar, zzafvVar, this.zzr.zzv(r8, zzafsVar.zzb));
                        if (MimeTypes.AUDIO_TRUEHD.equals(zzafsVar.zzf.zzm)) {
                            r4 = zzafvVar.zze * 16;
                        } else {
                            r4 = zzafvVar.zze + 30;
                        }
                        zzad zzb3 = zzafsVar.zzf.zzb();
                        zzb3.zzL(r4);
                        if (zzafsVar.zzb == 2 && j5 > 0 && (r42 = zzafvVar.zzb) > 1) {
                            zzb3.zzE(r42 / (((float) j5) / 1000000.0f));
                        }
                        int r2 = zzafsVar.zzb;
                        int r3 = zzafj.zzb;
                        if (r2 == 1 && zzzuVar.zza()) {
                            zzb3.zzC(zzzuVar.zza);
                            zzb3.zzD(zzzuVar.zzb);
                        }
                        int r22 = zzafsVar.zzb;
                        zzbq[] zzbqVarArr = new zzbq[2];
                        zzbqVarArr[0] = zzbqVar;
                        zzbqVarArr[1] = this.zzh.isEmpty() ? null : new zzbq(this.zzh);
                        zzbq zzbqVar6 = new zzbq(new zzbp[0]);
                        if (r22 == 1) {
                            if (zzbqVar2 != null) {
                                zzbqVar6 = zzbqVar2;
                            }
                        } else if (r22 == 2 && zzbqVar5 != null) {
                            int r23 = 0;
                            while (true) {
                                if (r23 >= zzbqVar5.zza()) {
                                    break;
                                }
                                zzbp zzb4 = zzbqVar5.zzb(r23);
                                if (zzb4 instanceof zzadn) {
                                    zzadn zzadnVar = (zzadn) zzb4;
                                    if (MdtaMetadataEntry.KEY_ANDROID_CAPTURE_FPS.equals(zzadnVar.zza)) {
                                        zzbqVar6 = new zzbq(zzadnVar);
                                        break;
                                    }
                                }
                                r23++;
                            }
                        }
                        for (int r25 = 0; r25 < 2; r25++) {
                            zzbqVar6 = zzbqVar6.zzd(zzbqVarArr[r25]);
                        }
                        if (zzbqVar6.zza() > 0) {
                            zzb3.zzM(zzbqVar6);
                        }
                        zzafmVar.zzc.zzk(zzb3.zzY());
                        if (zzafsVar.zzb == 2 && r9 == -1) {
                            r9 = arrayList.size();
                        }
                        arrayList.add(zzafmVar);
                        j4 = max;
                    }
                    r8++;
                    zzc = list;
                    size = r24;
                    j3 = C1856C.TIME_UNSET;
                }
                this.zzu = r9;
                this.zzv = j4;
                zzafm[] zzafmVarArr = (zzafm[]) arrayList.toArray(new zzafm[0]);
                this.zzs = zzafmVarArr;
                int length = zzafmVarArr.length;
                long[][] jArr = new long[length];
                int[] r5 = new int[length];
                long[] jArr2 = new long[length];
                boolean[] zArr = new boolean[length];
                for (int r7 = 0; r7 < zzafmVarArr.length; r7++) {
                    jArr[r7] = new long[zzafmVarArr[r7].zzb.zzb];
                    jArr2[r7] = zzafmVarArr[r7].zzb.zzf[0];
                }
                int r72 = 0;
                while (r72 < zzafmVarArr.length) {
                    long j6 = Long.MAX_VALUE;
                    int r11 = -1;
                    for (int r10 = 0; r10 < zzafmVarArr.length; r10++) {
                        if (!zArr[r10]) {
                            long j7 = jArr2[r10];
                            if (j7 <= j6) {
                                r11 = r10;
                                j6 = j7;
                            }
                        }
                    }
                    int r82 = r5[r11];
                    long[] jArr3 = jArr[r11];
                    jArr3[r82] = j2;
                    zzafv zzafvVar2 = zzafmVarArr[r11].zzb;
                    j2 += zzafvVar2.zzd[r82];
                    int r83 = r82 + 1;
                    r5[r11] = r83;
                    if (r83 < jArr3.length) {
                        jArr2[r11] = zzafvVar2.zzf[r83];
                    } else {
                        zArr[r11] = true;
                        r72++;
                    }
                }
                this.zzt = jArr;
                this.zzr.zzB();
                this.zzr.zzL(this);
                this.zzf.clear();
                this.zzi = 2;
            } else if (!this.zzf.isEmpty()) {
                ((zzaes) this.zzf.peek()).zzc(zzaesVar);
            }
        }
        if (this.zzi != 2) {
            zzk();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzzf
    public final void zzb(zzzi zzziVar) {
        this.zzr = zzziVar;
    }

    @Override // com.google.android.gms.internal.ads.zzzf
    public final void zzc(long j, long j2) {
        zzafm[] zzafmVarArr;
        this.zzf.clear();
        this.zzl = 0;
        this.zzn = -1;
        this.zzo = 0;
        this.zzp = 0;
        this.zzq = 0;
        if (j == 0) {
            zzk();
            return;
        }
        for (zzafm zzafmVar : this.zzs) {
            zzafv zzafvVar = zzafmVar.zzb;
            int zza2 = zzafvVar.zza(j2);
            if (zza2 == -1) {
                zza2 = zzafvVar.zzb(j2);
            }
            zzafmVar.zze = zza2;
            zzaan zzaanVar = zzafmVar.zzd;
            if (zzaanVar != null) {
                zzaanVar.zzb();
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzzf
    public final boolean zzd(zzzg zzzgVar) throws IOException {
        return zzafr.zzb(zzzgVar, false);
    }

    @Override // com.google.android.gms.internal.ads.zzaai
    public final long zze() {
        return this.zzv;
    }

    @Override // com.google.android.gms.internal.ads.zzaai
    public final zzaag zzg(long j) {
        long j2;
        long j3;
        int zzb;
        zzafm[] zzafmVarArr = this.zzs;
        if (zzafmVarArr.length == 0) {
            zzaaj zzaajVar = zzaaj.zza;
            return new zzaag(zzaajVar, zzaajVar);
        }
        int r1 = this.zzu;
        long j4 = -1;
        if (r1 != -1) {
            zzafv zzafvVar = zzafmVarArr[r1].zzb;
            int zzi = zzi(zzafvVar, j);
            if (zzi == -1) {
                zzaaj zzaajVar2 = zzaaj.zza;
                return new zzaag(zzaajVar2, zzaajVar2);
            }
            long j5 = zzafvVar.zzf[zzi];
            j2 = zzafvVar.zzc[zzi];
            if (j5 >= j || zzi >= zzafvVar.zzb - 1 || (zzb = zzafvVar.zzb(j)) == -1 || zzb == zzi) {
                j3 = -9223372036854775807L;
            } else {
                j3 = zzafvVar.zzf[zzb];
                j4 = zzafvVar.zzc[zzb];
            }
            j = j5;
        } else {
            j2 = Long.MAX_VALUE;
            j3 = -9223372036854775807L;
        }
        int r0 = 0;
        while (true) {
            zzafm[] zzafmVarArr2 = this.zzs;
            if (r0 >= zzafmVarArr2.length) {
                break;
            }
            if (r0 != this.zzu) {
                zzafv zzafvVar2 = zzafmVarArr2[r0].zzb;
                long zzj = zzj(zzafvVar2, j, j2);
                if (j3 != C1856C.TIME_UNSET) {
                    j4 = zzj(zzafvVar2, j3, j4);
                }
                j2 = zzj;
            }
            r0++;
        }
        zzaaj zzaajVar3 = new zzaaj(j, j2);
        return j3 == C1856C.TIME_UNSET ? new zzaag(zzaajVar3, zzaajVar3) : new zzaag(zzaajVar3, new zzaaj(j3, j4));
    }

    @Override // com.google.android.gms.internal.ads.zzaai
    public final boolean zzh() {
        return true;
    }

    public zzafn(int r3) {
        this.zzi = 0;
        this.zzg = new zzafq();
        this.zzh = new ArrayList();
        this.zze = new zzed(16);
        this.zzf = new ArrayDeque();
        this.zzb = new zzed(zzaac.zza);
        this.zzc = new zzed(4);
        this.zzd = new zzed();
        this.zzn = -1;
        this.zzr = zzzi.zza;
        this.zzs = new zzafm[0];
    }

    /* JADX WARN: Code restructure failed: missing block: B:194:0x037e, code lost:
        r3 = true;
     */
    /* JADX WARN: Removed duplicated region for block: B:259:0x0085 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x007f  */
    @Override // com.google.android.gms.internal.ads.zzzf
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int zza(com.google.android.gms.internal.ads.zzzg r33, com.google.android.gms.internal.ads.zzaaf r34) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1033
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzafn.zza(com.google.android.gms.internal.ads.zzzg, com.google.android.gms.internal.ads.zzaaf):int");
    }
}
