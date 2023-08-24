package com.google.android.gms.internal.ads;

import android.net.Uri;
import android.util.Log;
import android.util.Pair;
import android.util.SparseArray;
import androidx.core.view.ViewCompat;
import com.google.android.exoplayer2.C1856C;
import com.google.android.exoplayer2.extractor.mp4.Atom;
import com.google.android.exoplayer2.extractor.p011ts.PsExtractor;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.common.base.Ascii;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzafi implements zzzf {
    public static final zzzm zza = new zzzm() { // from class: com.google.android.gms.internal.ads.zzafe
        @Override // com.google.android.gms.internal.ads.zzzm
        public final zzzf[] zza() {
            zzzm zzzmVar = zzafi.zza;
            return new zzzf[]{new zzafi(0, null)};
        }

        @Override // com.google.android.gms.internal.ads.zzzm
        public final /* synthetic */ zzzf[] zzb(Uri uri, Map map) {
            return zzzl.zza(this, uri, map);
        }
    };
    private static final byte[] zzb = {-94, 57, 79, 82, 90, -101, 79, Ascii.DC4, -94, 68, 108, 66, 124, 100, -115, -12};
    private static final zzaf zzc;
    private int zzA;
    private int zzB;
    private boolean zzC;
    private zzzi zzD;
    private zzaam[] zzE;
    private zzaam[] zzF;
    private boolean zzG;
    private final List zzd;
    private final SparseArray zze;
    private final zzed zzf;
    private final zzed zzg;
    private final zzed zzh;
    private final byte[] zzi;
    private final zzed zzj;
    private final zzacd zzk;
    private final zzed zzl;
    private final ArrayDeque zzm;
    private final ArrayDeque zzn;
    private int zzo;
    private int zzp;
    private long zzq;
    private int zzr;
    private zzed zzs;
    private long zzt;
    private int zzu;
    private long zzv;
    private long zzw;
    private long zzx;
    private zzafh zzy;
    private int zzz;

    static {
        zzad zzadVar = new zzad();
        zzadVar.zzS(MimeTypes.APPLICATION_EMSG);
        zzc = zzadVar.zzY();
    }

    public zzafi() {
        this(0, null);
    }

    private static int zze(int r2) throws zzbu {
        if (r2 >= 0) {
            return r2;
        }
        throw zzbu.zza("Unexpected negative value: " + r2, null);
    }

    private static zzx zzf(List list) {
        int size = list.size();
        ArrayList arrayList = null;
        for (int r2 = 0; r2 < size; r2++) {
            zzaet zzaetVar = (zzaet) list.get(r2);
            if (zzaetVar.zzd == 1886614376) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                byte[] zzH = zzaetVar.zza.zzH();
                UUID zza2 = zzafp.zza(zzH);
                if (zza2 == null) {
                    Log.w("FragmentedMp4Extractor", "Skipped pssh atom (failed to extract uuid)");
                } else {
                    arrayList.add(new zzw(zza2, null, MimeTypes.VIDEO_MP4, zzH));
                }
            }
        }
        if (arrayList == null) {
            return null;
        }
        return new zzx(arrayList);
    }

    private final void zzg() {
        this.zzo = 0;
        this.zzr = 0;
    }

    private static void zzh(zzed zzedVar, int r4, zzafu zzafuVar) throws zzbu {
        zzedVar.zzF(r4 + 8);
        int zze = zzedVar.zze() & ViewCompat.MEASURED_SIZE_MASK;
        if ((zze & 1) != 0) {
            throw zzbu.zzc("Overriding TrackEncryptionBox parameters is unsupported.");
        }
        boolean z = (zze & 2) != 0;
        int zzn = zzedVar.zzn();
        if (zzn == 0) {
            Arrays.fill(zzafuVar.zzl, 0, zzafuVar.zze, false);
            return;
        }
        int r2 = zzafuVar.zze;
        if (zzn != r2) {
            throw zzbu.zza("Senc sample count " + zzn + " is different from fragment sample count" + r2, null);
        }
        Arrays.fill(zzafuVar.zzl, 0, zzn, z);
        zzafuVar.zza(zzedVar.zza());
        zzed zzedVar2 = zzafuVar.zzn;
        zzedVar.zzB(zzedVar2.zzH(), 0, zzedVar2.zzd());
        zzafuVar.zzn.zzF(0);
        zzafuVar.zzo = false;
    }

    private final void zzi(long j) throws zzbu {
        zzafi zzafiVar;
        SparseArray sparseArray;
        zzaes zzaesVar;
        int r22;
        int r29;
        byte[] bArr;
        int r15;
        int r9;
        int r4;
        int r13;
        byte[] bArr2;
        byte[] bArr3;
        int r7;
        boolean z;
        int r27;
        zzaes zzaesVar2;
        byte[] bArr4;
        zzafu zzafuVar;
        List list;
        int r222;
        int r292;
        int r23;
        zzaes zzaesVar3;
        int r40;
        int r2;
        int r41;
        int r3;
        int r42;
        int r0;
        int r43;
        int r44;
        int r45;
        int r46;
        zzafd zzafdVar;
        long j2;
        final zzafi zzafiVar2 = this;
        while (!zzafiVar2.zzm.isEmpty() && ((zzaes) zzafiVar2.zzm.peek()).zza == j) {
            zzaes zzaesVar4 = (zzaes) zzafiVar2.zzm.pop();
            int r1 = zzaesVar4.zzd;
            int r6 = 12;
            int r72 = 8;
            if (r1 == 1836019574) {
                zzx zzf = zzf(zzaesVar4.zzb);
                zzaes zza2 = zzaesVar4.zza(Atom.TYPE_mvex);
                Objects.requireNonNull(zza2);
                SparseArray sparseArray2 = new SparseArray();
                int size = zza2.zzb.size();
                long j3 = C1856C.TIME_UNSET;
                int r92 = 0;
                while (r92 < size) {
                    zzaet zzaetVar = (zzaet) zza2.zzb.get(r92);
                    int r14 = zzaetVar.zzd;
                    if (r14 == 1953654136) {
                        zzed zzedVar = zzaetVar.zza;
                        zzedVar.zzF(r6);
                        Pair create = Pair.create(Integer.valueOf(zzedVar.zze()), new zzafd(zzedVar.zze() - 1, zzedVar.zze(), zzedVar.zze(), zzedVar.zze()));
                        sparseArray2.put(((Integer) create.first).intValue(), (zzafd) create.second);
                    } else if (r14 == 1835362404) {
                        zzed zzedVar2 = zzaetVar.zza;
                        zzedVar2.zzF(8);
                        j3 = zzaeu.zze(zzedVar2.zze()) == 0 ? zzedVar2.zzs() : zzedVar2.zzt();
                    }
                    r92++;
                    r6 = 12;
                }
                List zzc2 = zzafc.zzc(zzaesVar4, new zzzu(), j3, zzf, false, false, new zzfru() { // from class: com.google.android.gms.internal.ads.zzaff
                    @Override // com.google.android.gms.internal.ads.zzfru
                    public final Object apply(Object obj) {
                        return (zzafs) obj;
                    }
                });
                int size2 = zzc2.size();
                if (zzafiVar2.zze.size() == 0) {
                    for (int r10 = 0; r10 < size2; r10++) {
                        zzafv zzafvVar = (zzafv) zzc2.get(r10);
                        zzafs zzafsVar = zzafvVar.zza;
                        zzafiVar2.zze.put(zzafsVar.zza, new zzafh(zzafiVar2.zzD.zzv(r10, zzafsVar.zzb), zzafvVar, zzj(sparseArray2, zzafsVar.zza)));
                        zzafiVar2.zzw = Math.max(zzafiVar2.zzw, zzafsVar.zze);
                    }
                    zzafiVar2.zzD.zzB();
                } else {
                    zzdd.zzf(zzafiVar2.zze.size() == size2);
                    for (int r102 = 0; r102 < size2; r102++) {
                        zzafv zzafvVar2 = (zzafv) zzc2.get(r102);
                        zzafs zzafsVar2 = zzafvVar2.zza;
                        ((zzafh) zzafiVar2.zze.get(zzafsVar2.zza)).zzh(zzafvVar2, zzj(sparseArray2, zzafsVar2.zza));
                    }
                }
            } else {
                if (r1 == 1836019558) {
                    SparseArray sparseArray3 = zzafiVar2.zze;
                    byte[] bArr5 = zzafiVar2.zzi;
                    int size3 = zzaesVar4.zzc.size();
                    int r8 = 0;
                    while (r8 < size3) {
                        zzaes zzaesVar5 = (zzaes) zzaesVar4.zzc.get(r8);
                        if (zzaesVar5.zzd == 1953653094) {
                            zzaet zzb2 = zzaesVar5.zzb(Atom.TYPE_tfhd);
                            Objects.requireNonNull(zzb2);
                            zzed zzedVar3 = zzb2.zza;
                            zzedVar3.zzF(r72);
                            int zze = zzedVar3.zze() & ViewCompat.MEASURED_SIZE_MASK;
                            zzafh zzafhVar = (zzafh) sparseArray3.get(zzedVar3.zze());
                            if (zzafhVar == null) {
                                zzafhVar = null;
                            } else {
                                if ((zze & 1) != 0) {
                                    long zzt = zzedVar3.zzt();
                                    zzafu zzafuVar2 = zzafhVar.zzb;
                                    zzafuVar2.zzb = zzt;
                                    zzafuVar2.zzc = zzt;
                                }
                                zzafd zzafdVar2 = zzafhVar.zze;
                                int zze2 = (zze & 2) != 0 ? zzedVar3.zze() - 1 : zzafdVar2.zza;
                                if ((zze & 8) != 0) {
                                    r15 = zzedVar3.zze();
                                } else {
                                    r15 = zzafdVar2.zzb;
                                }
                                if ((zze & 16) != 0) {
                                    r9 = zzedVar3.zze();
                                } else {
                                    r9 = zzafdVar2.zzc;
                                }
                                if ((zze & 32) != 0) {
                                    r4 = zzedVar3.zze();
                                } else {
                                    r4 = zzafdVar2.zzd;
                                }
                                zzafhVar.zzb.zza = new zzafd(zze2, r15, r9, r4);
                            }
                            if (zzafhVar != null) {
                                zzafu zzafuVar3 = zzafhVar.zzb;
                                long j4 = zzafuVar3.zzp;
                                boolean z2 = zzafuVar3.zzq;
                                zzafhVar.zzi();
                                zzafhVar.zzl = true;
                                zzaet zzb3 = zzaesVar5.zzb(Atom.TYPE_tfdt);
                                if (zzb3 != null) {
                                    zzed zzedVar4 = zzb3.zza;
                                    zzedVar4.zzF(r72);
                                    zzafuVar3.zzp = zzaeu.zze(zzedVar4.zze()) == 1 ? zzedVar4.zzt() : zzedVar4.zzs();
                                    zzafuVar3.zzq = true;
                                } else {
                                    zzafuVar3.zzp = j4;
                                    zzafuVar3.zzq = z2;
                                }
                                List list2 = zzaesVar5.zzb;
                                int size4 = list2.size();
                                int r11 = 0;
                                int r12 = 0;
                                int r152 = 0;
                                while (true) {
                                    r13 = Atom.TYPE_trun;
                                    if (r11 >= size4) {
                                        break;
                                    }
                                    zzaet zzaetVar2 = (zzaet) list2.get(r11);
                                    SparseArray sparseArray4 = sparseArray3;
                                    if (zzaetVar2.zzd == 1953658222) {
                                        zzed zzedVar5 = zzaetVar2.zza;
                                        zzedVar5.zzF(12);
                                        int zzn = zzedVar5.zzn();
                                        if (zzn > 0) {
                                            r152 += zzn;
                                            r12++;
                                        }
                                    }
                                    r11++;
                                    sparseArray3 = sparseArray4;
                                }
                                sparseArray = sparseArray3;
                                zzafhVar.zzh = 0;
                                zzafhVar.zzg = 0;
                                zzafhVar.zzf = 0;
                                zzafu zzafuVar4 = zzafhVar.zzb;
                                zzafuVar4.zzd = r12;
                                zzafuVar4.zze = r152;
                                if (zzafuVar4.zzg.length < r12) {
                                    zzafuVar4.zzf = new long[r12];
                                    zzafuVar4.zzg = new int[r12];
                                }
                                if (zzafuVar4.zzh.length < r152) {
                                    int r153 = (r152 * 125) / 100;
                                    zzafuVar4.zzh = new int[r153];
                                    zzafuVar4.zzi = new long[r153];
                                    zzafuVar4.zzj = new boolean[r153];
                                    zzafuVar4.zzl = new boolean[r153];
                                }
                                int r16 = 0;
                                int r73 = 0;
                                int r112 = 0;
                                while (true) {
                                    long j5 = 0;
                                    if (r16 >= size4) {
                                        break;
                                    }
                                    zzaet zzaetVar3 = (zzaet) list2.get(r16);
                                    if (zzaetVar3.zzd == r13) {
                                        int r122 = r112 + 1;
                                        zzed zzedVar6 = zzaetVar3.zza;
                                        zzedVar6.zzF(8);
                                        int zze3 = zzedVar6.zze() & ViewCompat.MEASURED_SIZE_MASK;
                                        list = list2;
                                        zzafs zzafsVar3 = zzafhVar.zzd.zza;
                                        r222 = size3;
                                        zzafu zzafuVar5 = zzafhVar.zzb;
                                        r23 = size4;
                                        zzafd zzafdVar3 = zzafuVar5.zza;
                                        int r24 = zzel.zza;
                                        zzafuVar5.zzg[r112] = zzedVar6.zzn();
                                        long[] jArr = zzafuVar5.zzf;
                                        zzaesVar2 = zzaesVar4;
                                        bArr4 = bArr5;
                                        long j6 = zzafuVar5.zzb;
                                        jArr[r112] = j6;
                                        if ((zze3 & 1) != 0) {
                                            r27 = r16;
                                            jArr[r112] = j6 + zzedVar6.zze();
                                        } else {
                                            r27 = r16;
                                        }
                                        int r02 = zze3 & 4;
                                        int r17 = zzafdVar3.zzd;
                                        if (r02 != 0) {
                                            r17 = zzedVar6.zze();
                                        }
                                        int r25 = zze3 & 256;
                                        int r32 = zze3 & 512;
                                        int r123 = zze3 & 1024;
                                        int r132 = zze3 & 2048;
                                        int r28 = r17;
                                        long[] jArr2 = zzafsVar3.zzh;
                                        if (jArr2 != null) {
                                            r292 = r8;
                                            zzaesVar3 = zzaesVar5;
                                            if (jArr2.length == 1 && jArr2[0] == 0) {
                                                j5 = ((long[]) zzel.zzH(zzafsVar3.zzi))[0];
                                            }
                                        } else {
                                            r292 = r8;
                                            zzaesVar3 = zzaesVar5;
                                        }
                                        int[] r18 = zzafuVar5.zzh;
                                        long[] jArr3 = zzafuVar5.zzi;
                                        boolean[] zArr = zzafuVar5.zzj;
                                        zzafuVar = zzafuVar3;
                                        int r47 = zzafuVar5.zzg[r112] + r73;
                                        long j7 = zzafsVar3.zzc;
                                        int r5 = r73;
                                        long j8 = zzafuVar5.zzp;
                                        while (r5 < r47) {
                                            if (r25 != 0) {
                                                r40 = r25;
                                                r2 = zzedVar6.zze();
                                            } else {
                                                r40 = r25;
                                                r2 = zzafdVar3.zzb;
                                            }
                                            zze(r2);
                                            if (r32 != 0) {
                                                r41 = r32;
                                                r3 = zzedVar6.zze();
                                            } else {
                                                r41 = r32;
                                                r3 = zzafdVar3.zzc;
                                            }
                                            zze(r3);
                                            if (r123 != 0) {
                                                r42 = r02;
                                                r0 = zzedVar6.zze();
                                            } else {
                                                r42 = r02;
                                                if (r5 == 0) {
                                                    if (r02 != 0) {
                                                        r0 = r28;
                                                        r5 = 0;
                                                    } else {
                                                        r5 = 0;
                                                    }
                                                }
                                                r0 = zzafdVar3.zzd;
                                            }
                                            if (r132 != 0) {
                                                r43 = r47;
                                                r44 = r123;
                                                r45 = r132;
                                                r46 = zzedVar6.zze();
                                            } else {
                                                r43 = r47;
                                                r44 = r123;
                                                r45 = r132;
                                                r46 = 0;
                                            }
                                            long zzw = zzel.zzw((r46 + j8) - j5, 1000000L, j7);
                                            jArr3[r5] = zzw;
                                            if (zzafuVar5.zzq) {
                                                zzafdVar = zzafdVar3;
                                                j2 = j7;
                                            } else {
                                                zzafdVar = zzafdVar3;
                                                j2 = j7;
                                                jArr3[r5] = zzw + zzafhVar.zzd.zzh;
                                            }
                                            r18[r5] = r3;
                                            zArr[r5] = 1 == (((r0 >> 16) & 1) ^ 1);
                                            j8 += r2;
                                            r5++;
                                            zzafdVar3 = zzafdVar;
                                            j7 = j2;
                                            r25 = r40;
                                            r32 = r41;
                                            r02 = r42;
                                            r47 = r43;
                                            r123 = r44;
                                            r132 = r45;
                                        }
                                        zzafuVar5.zzp = j8;
                                        r112 = r122;
                                        r73 = r47;
                                    } else {
                                        r27 = r16;
                                        zzaesVar2 = zzaesVar4;
                                        bArr4 = bArr5;
                                        zzafuVar = zzafuVar3;
                                        list = list2;
                                        r222 = size3;
                                        r292 = r8;
                                        r23 = size4;
                                        zzaesVar3 = zzaesVar5;
                                    }
                                    r16 = r27 + 1;
                                    list2 = list;
                                    size3 = r222;
                                    size4 = r23;
                                    zzaesVar4 = zzaesVar2;
                                    bArr5 = bArr4;
                                    r8 = r292;
                                    zzaesVar5 = zzaesVar3;
                                    zzafuVar3 = zzafuVar;
                                    r13 = Atom.TYPE_trun;
                                }
                                zzaesVar = zzaesVar4;
                                byte[] bArr6 = bArr5;
                                zzafu zzafuVar6 = zzafuVar3;
                                r22 = size3;
                                r29 = r8;
                                zzaes zzaesVar6 = zzaesVar5;
                                zzafs zzafsVar4 = zzafhVar.zzd.zza;
                                zzafd zzafdVar4 = zzafuVar6.zza;
                                Objects.requireNonNull(zzafdVar4);
                                zzaft zza3 = zzafsVar4.zza(zzafdVar4.zza);
                                zzaet zzb4 = zzaesVar6.zzb(Atom.TYPE_saiz);
                                if (zzb4 != null) {
                                    Objects.requireNonNull(zza3);
                                    zzed zzedVar7 = zzb4.zza;
                                    int r33 = zza3.zzd;
                                    zzedVar7.zzF(8);
                                    if ((zzedVar7.zze() & 1) == 1) {
                                        zzedVar7.zzG(8);
                                    }
                                    int zzk = zzedVar7.zzk();
                                    int zzn2 = zzedVar7.zzn();
                                    int r62 = zzafuVar6.zze;
                                    if (zzn2 > r62) {
                                        throw zzbu.zza("Saiz sample count " + zzn2 + " is greater than fragment sample count" + r62, null);
                                    }
                                    if (zzk != 0) {
                                        boolean z3 = zzk > r33;
                                        r7 = zzk * zzn2;
                                        z = false;
                                        Arrays.fill(zzafuVar6.zzl, 0, zzn2, z3);
                                    } else {
                                        boolean[] zArr2 = zzafuVar6.zzl;
                                        r7 = 0;
                                        for (int r63 = 0; r63 < zzn2; r63++) {
                                            int zzk2 = zzedVar7.zzk();
                                            r7 += zzk2;
                                            zArr2[r63] = zzk2 > r33;
                                        }
                                        z = false;
                                    }
                                    Arrays.fill(zzafuVar6.zzl, zzn2, zzafuVar6.zze, z);
                                    if (r7 > 0) {
                                        zzafuVar6.zza(r7);
                                    }
                                }
                                zzaet zzb5 = zzaesVar6.zzb(Atom.TYPE_saio);
                                if (zzb5 != null) {
                                    zzed zzedVar8 = zzb5.zza;
                                    zzedVar8.zzF(8);
                                    int zze4 = zzedVar8.zze();
                                    if ((zze4 & 1) == 1) {
                                        zzedVar8.zzG(8);
                                    }
                                    int zzn3 = zzedVar8.zzn();
                                    if (zzn3 != 1) {
                                        throw zzbu.zza("Unexpected saio entry count: " + zzn3, null);
                                    }
                                    zzafuVar6.zzc += zzaeu.zze(zze4) == 0 ? zzedVar8.zzs() : zzedVar8.zzt();
                                }
                                zzaet zzb6 = zzaesVar6.zzb(Atom.TYPE_senc);
                                if (zzb6 != null) {
                                    zzh(zzb6.zza, 0, zzafuVar6);
                                }
                                String str = zza3 != null ? zza3.zzb : null;
                                zzed zzedVar9 = null;
                                zzed zzedVar10 = null;
                                for (int r48 = 0; r48 < zzaesVar6.zzb.size(); r48++) {
                                    zzaet zzaetVar4 = (zzaet) zzaesVar6.zzb.get(r48);
                                    zzed zzedVar11 = zzaetVar4.zza;
                                    int r52 = zzaetVar4.zzd;
                                    if (r52 == 1935828848) {
                                        zzedVar11.zzF(12);
                                        if (zzedVar11.zze() == 1936025959) {
                                            zzedVar9 = zzedVar11;
                                        }
                                    } else if (r52 == 1936158820) {
                                        zzedVar11.zzF(12);
                                        if (zzedVar11.zze() == 1936025959) {
                                            zzedVar10 = zzedVar11;
                                        }
                                    }
                                }
                                if (zzedVar9 != null && zzedVar10 != null) {
                                    zzedVar9.zzF(8);
                                    int zze5 = zzedVar9.zze();
                                    zzedVar9.zzG(4);
                                    if (zzaeu.zze(zze5) == 1) {
                                        zzedVar9.zzG(4);
                                    }
                                    if (zzedVar9.zze() == 1) {
                                        zzedVar10.zzF(8);
                                        int zze6 = zzaeu.zze(zzedVar10.zze());
                                        zzedVar10.zzG(4);
                                        if (zze6 == 1) {
                                            if (zzedVar10.zzs() == 0) {
                                                throw zzbu.zzc("Variable length description in sgpd found (unsupported)");
                                            }
                                        } else if (zze6 >= 2) {
                                            zzedVar10.zzG(4);
                                        }
                                        if (zzedVar10.zzs() == 1) {
                                            zzedVar10.zzG(1);
                                            int zzk3 = zzedVar10.zzk();
                                            int r35 = (zzk3 & PsExtractor.VIDEO_STREAM_MASK) >> 4;
                                            int r36 = zzk3 & 15;
                                            if (zzedVar10.zzk() == 1) {
                                                int zzk4 = zzedVar10.zzk();
                                                byte[] bArr7 = new byte[16];
                                                zzedVar10.zzB(bArr7, 0, 16);
                                                if (zzk4 == 0) {
                                                    int zzk5 = zzedVar10.zzk();
                                                    byte[] bArr8 = new byte[zzk5];
                                                    zzedVar10.zzB(bArr8, 0, zzk5);
                                                    bArr3 = bArr8;
                                                } else {
                                                    bArr3 = null;
                                                }
                                                zzafuVar6.zzk = true;
                                                zzafuVar6.zzm = new zzaft(true, str, zzk4, bArr7, r35, r36, bArr3);
                                            }
                                        } else {
                                            throw zzbu.zzc("Entry count in sgpd != 1 (unsupported).");
                                        }
                                    } else {
                                        throw zzbu.zzc("Entry count in sbgp != 1 (unsupported).");
                                    }
                                }
                                int size5 = zzaesVar6.zzb.size();
                                int r34 = 0;
                                while (r34 < size5) {
                                    zzaet zzaetVar5 = (zzaet) zzaesVar6.zzb.get(r34);
                                    if (zzaetVar5.zzd == 1970628964) {
                                        zzed zzedVar12 = zzaetVar5.zza;
                                        zzedVar12.zzF(8);
                                        bArr2 = bArr6;
                                        zzedVar12.zzB(bArr2, 0, 16);
                                        if (Arrays.equals(bArr2, zzb)) {
                                            zzh(zzedVar12, 16, zzafuVar6);
                                        }
                                    } else {
                                        bArr2 = bArr6;
                                    }
                                    r34++;
                                    bArr6 = bArr2;
                                }
                                bArr = bArr6;
                                bArr5 = bArr;
                                size3 = r22;
                                zzaesVar4 = zzaesVar;
                                r72 = 8;
                                r8 = r29 + 1;
                                sparseArray3 = sparseArray;
                            }
                        }
                        sparseArray = sparseArray3;
                        zzaesVar = zzaesVar4;
                        r22 = size3;
                        r29 = r8;
                        bArr = bArr5;
                        bArr5 = bArr;
                        size3 = r22;
                        zzaesVar4 = zzaesVar;
                        r72 = 8;
                        r8 = r29 + 1;
                        sparseArray3 = sparseArray;
                    }
                    zzx zzf2 = zzf(zzaesVar4.zzb);
                    zzafiVar = this;
                    if (zzf2 != null) {
                        int size6 = zzafiVar.zze.size();
                        for (int r49 = 0; r49 < size6; r49++) {
                            zzafh zzafhVar2 = (zzafh) zzafiVar.zze.valueAt(r49);
                            zzafs zzafsVar5 = zzafhVar2.zzd.zza;
                            zzafd zzafdVar5 = zzafhVar2.zzb.zza;
                            int r93 = zzel.zza;
                            zzaft zza4 = zzafsVar5.zza(zzafdVar5.zza);
                            zzx zzb7 = zzf2.zzb(zza4 != null ? zza4.zzb : null);
                            zzad zzb8 = zzafhVar2.zzd.zza.zzf.zzb();
                            zzb8.zzB(zzb7);
                            zzafhVar2.zza.zzk(zzb8.zzY());
                        }
                    }
                    if (zzafiVar.zzv != C1856C.TIME_UNSET) {
                        int size7 = zzafiVar.zze.size();
                        for (int r103 = 0; r103 < size7; r103++) {
                            zzafh zzafhVar3 = (zzafh) zzafiVar.zze.valueAt(r103);
                            long j9 = zzafiVar.zzv;
                            int r26 = zzafhVar3.zzf;
                            while (true) {
                                zzafu zzafuVar7 = zzafhVar3.zzb;
                                if (r26 < zzafuVar7.zze && zzafuVar7.zzi[r26] < j9) {
                                    if (zzafuVar7.zzj[r26]) {
                                        zzafhVar3.zzi = r26;
                                    }
                                    r26++;
                                }
                            }
                        }
                        zzafiVar.zzv = C1856C.TIME_UNSET;
                    }
                } else {
                    zzafiVar = zzafiVar2;
                    if (!zzafiVar.zzm.isEmpty()) {
                        ((zzaes) zzafiVar.zzm.peek()).zzc(zzaesVar4);
                    }
                }
                zzafiVar2 = zzafiVar;
            }
        }
        zzg();
    }

    private static final zzafd zzj(SparseArray sparseArray, int r3) {
        if (sparseArray.size() == 1) {
            return (zzafd) sparseArray.valueAt(0);
        }
        zzafd zzafdVar = (zzafd) sparseArray.get(r3);
        Objects.requireNonNull(zzafdVar);
        return zzafdVar;
    }

    /* JADX WARN: Code restructure failed: missing block: B:100:0x0246, code lost:
        r23 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:101:0x0248, code lost:
        r5.zzs(r10, r20, r33.zzz, 0, r23);
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x025b, code lost:
        if (r33.zzn.isEmpty() != false) goto L354;
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x025d, code lost:
        r1 = (com.google.android.gms.internal.ads.zzafg) r33.zzn.removeFirst();
        r33.zzu -= r1.zzc;
        r3 = r1.zza;
     */
    /* JADX WARN: Code restructure failed: missing block: B:105:0x0270, code lost:
        if (r1.zzb == false) goto L348;
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x0272, code lost:
        r3 = r3 + r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x0273, code lost:
        r5 = r33.zzE;
        r6 = r5.length;
        r8 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:108:0x0277, code lost:
        if (r8 >= r6) goto L353;
     */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x0279, code lost:
        r5[r8].zzs(r3, 1, r1.zzc, r33.zzu, null);
        r8 = r8 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:111:0x0291, code lost:
        if (r2.zzk() != false) goto L358;
     */
    /* JADX WARN: Code restructure failed: missing block: B:112:0x0293, code lost:
        r33.zzy = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:113:0x0295, code lost:
        r33.zzo = 3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0099, code lost:
        r4 = 6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x009c, code lost:
        if (r33.zzo != 3) goto L333;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x009e, code lost:
        r3 = r2.zzb();
        r33.zzz = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00a8, code lost:
        if (r2.zzf >= r2.zzi) goto L325;
     */
    /* JADX WARN: Code restructure failed: missing block: B:401:?, code lost:
        return 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00aa, code lost:
        ((com.google.android.gms.internal.ads.zzyv) r1).zzo(r3, false);
        r1 = r2.zzf();
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00b3, code lost:
        if (r1 != null) goto L314;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00b6, code lost:
        r3 = r2.zzb.zzn;
        r1 = r1.zzd;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00bc, code lost:
        if (r1 == 0) goto L317;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00be, code lost:
        r3.zzG(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00c9, code lost:
        if (r2.zzb.zzb(r2.zzf) == false) goto L320;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00cb, code lost:
        r3.zzG(r3.zzo() * 6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00d8, code lost:
        if (r2.zzk() != false) goto L323;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00da, code lost:
        r33.zzy = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x00dc, code lost:
        r33.zzo = 3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00de, code lost:
        return 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x00e7, code lost:
        if (r2.zzd.zza.zzg != 1) goto L328;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x00e9, code lost:
        r33.zzz = r3 - 8;
        ((com.google.android.gms.internal.ads.zzyv) r1).zzo(8, false);
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0103, code lost:
        if (com.google.android.exoplayer2.util.MimeTypes.AUDIO_AC4.equals(r2.zzd.zza.zzf.zzm) == false) goto L332;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0105, code lost:
        r33.zzA = r2.zzc(r33.zzz, 7);
        com.google.android.gms.internal.ads.zzyj.zzb(r33.zzz, r33.zzj);
        r2.zza.zzq(r33.zzj, 7);
        r3 = r33.zzA + 7;
        r33.zzA = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0122, code lost:
        r3 = r2.zzc(r33.zzz, 0);
        r33.zzA = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x012a, code lost:
        r33.zzz += r3;
        r33.zzo = 4;
        r33.zzB = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x0134, code lost:
        r3 = r2.zzd.zza;
        r5 = r2.zza;
        r10 = r2.zze();
        r6 = r3.zzj;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x0140, code lost:
        if (r6 != 0) goto L361;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0142, code lost:
        r3 = r33.zzA;
        r4 = r33.zzz;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x0146, code lost:
        if (r3 >= r4) goto L338;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0148, code lost:
        r33.zzA += r5.zze(r1, r4 - r3, false);
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0153, code lost:
        r13 = r33.zzg.zzH();
        r13[0] = 0;
        r13[1] = 0;
        r13[2] = 0;
        r14 = r6 + 1;
        r6 = 4 - r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x0169, code lost:
        if (r33.zzA >= r33.zzz) goto L392;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x016b, code lost:
        r12 = r33.zzB;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x016f, code lost:
        if (r12 != 0) goto L386;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x0171, code lost:
        ((com.google.android.gms.internal.ads.zzyv) r1).zzn(r13, r6, r14, r9);
        r33.zzg.zzF(r9);
        r12 = r33.zzg.zze();
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x0182, code lost:
        if (r12 <= 0) goto L383;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x0184, code lost:
        r33.zzB = r12 - 1;
        r33.zzf.zzF(r9);
        r5.zzq(r33.zzf, 4);
        r5.zzq(r33.zzg, r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x019b, code lost:
        if (r33.zzF.length <= 0) goto L382;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x019d, code lost:
        r12 = r3.zzf.zzm;
        r17 = r13[4];
        r9 = com.google.android.gms.internal.ads.zzaac.zza;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x01ab, code lost:
        if (com.google.android.exoplayer2.util.MimeTypes.VIDEO_H264.equals(r12) == false) goto L378;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x01af, code lost:
        if ((r17 & 31) == r4) goto L374;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x01b2, code lost:
        r9 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x01b8, code lost:
        if (com.google.android.exoplayer2.util.MimeTypes.VIDEO_H265.equals(r12) == false) goto L382;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x01bf, code lost:
        if (((r17 & 126) >> r8) != 39) goto L382;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x01c2, code lost:
        r9 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x01c3, code lost:
        r33.zzC = r9;
        r33.zzA += 5;
        r33.zzz += r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x01d7, code lost:
        throw com.google.android.gms.internal.ads.zzbu.zza("Invalid NAL length", null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x01da, code lost:
        if (r33.zzC == false) goto L391;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x01dc, code lost:
        r33.zzh.zzC(r12);
        ((com.google.android.gms.internal.ads.zzyv) r1).zzn(r33.zzh.zzH(), 0, r33.zzB, false);
        r5.zzq(r33.zzh, r33.zzB);
        r4 = r33.zzB;
        r8 = r33.zzh;
        r8 = com.google.android.gms.internal.ads.zzaac.zzb(r8.zzH(), r8.zzd());
        r33.zzh.zzF(com.google.android.exoplayer2.util.MimeTypes.VIDEO_H265.equals(r3.zzf.zzm) ? 1 : 0);
        r33.zzh.zzE(r8);
        com.google.android.gms.internal.ads.zzys.zza(r10, r33.zzh, r33.zzF);
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x0221, code lost:
        r4 = r5.zze(r1, r12, false);
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x0227, code lost:
        r33.zzA += r4;
        r33.zzB -= r4;
        r4 = 6;
        r8 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x0233, code lost:
        r9 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x0237, code lost:
        r20 = r2.zza();
        r1 = r2.zzf();
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x023f, code lost:
        if (r1 == null) goto L360;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x0241, code lost:
        r23 = r1.zzc;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r9v0 */
    /* JADX WARN: Type inference failed for: r9v1, types: [int, boolean] */
    /* JADX WARN: Type inference failed for: r9v10 */
    @Override // com.google.android.gms.internal.ads.zzzf
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int zza(com.google.android.gms.internal.ads.zzzg r34, com.google.android.gms.internal.ads.zzaaf r35) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1866
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzafi.zza(com.google.android.gms.internal.ads.zzzg, com.google.android.gms.internal.ads.zzaaf):int");
    }

    @Override // com.google.android.gms.internal.ads.zzzf
    public final void zzb(zzzi zzziVar) {
        this.zzD = zzziVar;
        zzg();
        zzaam[] zzaamVarArr = new zzaam[2];
        this.zzE = zzaamVarArr;
        int r0 = 0;
        zzaam[] zzaamVarArr2 = (zzaam[]) zzel.zzae(zzaamVarArr, 0);
        this.zzE = zzaamVarArr2;
        for (zzaam zzaamVar : zzaamVarArr2) {
            zzaamVar.zzk(zzc);
        }
        this.zzF = new zzaam[this.zzd.size()];
        int r6 = 100;
        while (r0 < this.zzF.length) {
            int r1 = r6 + 1;
            zzaam zzv = this.zzD.zzv(r6, 3);
            zzv.zzk((zzaf) this.zzd.get(r0));
            this.zzF[r0] = zzv;
            r0++;
            r6 = r1;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzzf
    public final void zzc(long j, long j2) {
        int size = this.zze.size();
        for (int r0 = 0; r0 < size; r0++) {
            ((zzafh) this.zze.valueAt(r0)).zzi();
        }
        this.zzn.clear();
        this.zzu = 0;
        this.zzv = j2;
        this.zzm.clear();
        zzg();
    }

    @Override // com.google.android.gms.internal.ads.zzzf
    public final boolean zzd(zzzg zzzgVar) throws IOException {
        return zzafr.zza(zzzgVar);
    }

    public zzafi(int r2, zzej zzejVar) {
        this.zzd = Collections.unmodifiableList(Collections.emptyList());
        this.zzk = new zzacd();
        this.zzl = new zzed(16);
        this.zzf = new zzed(zzaac.zza);
        this.zzg = new zzed(5);
        this.zzh = new zzed();
        byte[] bArr = new byte[16];
        this.zzi = bArr;
        this.zzj = new zzed(bArr);
        this.zzm = new ArrayDeque();
        this.zzn = new ArrayDeque();
        this.zze = new SparseArray();
        this.zzw = C1856C.TIME_UNSET;
        this.zzv = C1856C.TIME_UNSET;
        this.zzx = C1856C.TIME_UNSET;
        this.zzD = zzzi.zza;
        this.zzE = new zzaam[0];
        this.zzF = new zzaam[0];
    }
}
